package com.mopub.common;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.logging.MoPubLog;
import com.mopub.network.TrackingRequest;
import java.util.EnumSet;
import java.util.Iterator;

public class UrlHandler {
    private static final ResultActions EMPTY_CLICK_LISTENER = new ResultActions() {
        public void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction) {
        }

        public void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction) {
        }
    };
    private static final MoPubSchemeListener EMPTY_MOPUB_SCHEME_LISTENER = new MoPubSchemeListener() {
        public void onFinishLoad() {
        }

        public void onClose() {
        }

        public void onFailLoad() {
        }
    };
    private boolean mAlreadySucceeded;
    @Nullable
    private String mDspCreativeId;
    @NonNull
    private MoPubSchemeListener mMoPubSchemeListener;
    @NonNull
    private ResultActions mResultActions;
    private boolean mSkipShowMoPubBrowser;
    @NonNull
    private EnumSet<UrlAction> mSupportedUrlActions;
    private boolean mTaskPending;

    public interface ResultActions {
        void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction);

        void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction);
    }

    public interface MoPubSchemeListener {
        void onClose();

        void onFailLoad();

        void onFinishLoad();
    }

    public static class Builder {
        @Nullable
        private String creativeId;
        @NonNull
        private MoPubSchemeListener moPubSchemeListener = UrlHandler.EMPTY_MOPUB_SCHEME_LISTENER;
        @NonNull
        private ResultActions resultActions = UrlHandler.EMPTY_CLICK_LISTENER;
        private boolean skipShowMoPubBrowser = false;
        @NonNull
        private EnumSet<UrlAction> supportedUrlActions = EnumSet.of(UrlAction.NOOP);

        public Builder withSupportedUrlActions(@NonNull UrlAction urlAction, @Nullable UrlAction... urlActionArr) {
            this.supportedUrlActions = EnumSet.of(urlAction, urlActionArr);
            return this;
        }

        public Builder withSupportedUrlActions(@NonNull EnumSet<UrlAction> enumSet) {
            this.supportedUrlActions = EnumSet.copyOf(enumSet);
            return this;
        }

        public Builder withResultActions(@NonNull ResultActions resultActions) {
            this.resultActions = resultActions;
            return this;
        }

        public Builder withMoPubSchemeListener(@NonNull MoPubSchemeListener moPubSchemeListener) {
            this.moPubSchemeListener = moPubSchemeListener;
            return this;
        }

        public Builder withoutMoPubBrowser() {
            this.skipShowMoPubBrowser = true;
            return this;
        }

        public Builder withDspCreativeId(@Nullable String str) {
            this.creativeId = str;
            return this;
        }

        public UrlHandler build() {
            return new UrlHandler(this.supportedUrlActions, this.resultActions, this.moPubSchemeListener, this.skipShowMoPubBrowser, this.creativeId);
        }
    }

    private UrlHandler(@NonNull EnumSet<UrlAction> enumSet, @NonNull ResultActions resultActions, @NonNull MoPubSchemeListener moPubSchemeListener, boolean z, @Nullable String str) {
        this.mSupportedUrlActions = EnumSet.copyOf(enumSet);
        this.mResultActions = resultActions;
        this.mMoPubSchemeListener = moPubSchemeListener;
        this.mSkipShowMoPubBrowser = z;
        this.mDspCreativeId = str;
        this.mAlreadySucceeded = false;
        this.mTaskPending = false;
    }

    @NonNull
    EnumSet<UrlAction> getSupportedUrlActions() {
        return EnumSet.copyOf(this.mSupportedUrlActions);
    }

    @NonNull
    ResultActions getResultActions() {
        return this.mResultActions;
    }

    @NonNull
    MoPubSchemeListener getMoPubSchemeListener() {
        return this.mMoPubSchemeListener;
    }

    boolean shouldSkipShowMoPubBrowser() {
        return this.mSkipShowMoPubBrowser;
    }

    public void handleUrl(@NonNull Context context, @NonNull String str) {
        Preconditions.checkNotNull(context);
        handleUrl(context, str, true);
    }

    public void handleUrl(@NonNull Context context, @NonNull String str, boolean z) {
        Preconditions.checkNotNull(context);
        handleUrl(context, str, z, null);
    }

    public void handleUrl(@NonNull Context context, @NonNull String str, boolean z, @Nullable Iterable<String> iterable) {
        Preconditions.checkNotNull(context);
        if (TextUtils.isEmpty(str)) {
            failUrlHandling(str, null, "Attempted to handle empty url.", null);
            return;
        }
        final Context context2 = context;
        final boolean z2 = z;
        final Iterable<String> iterable2 = iterable;
        final String str2 = str;
        UrlResolutionTask.getResolvedUrl(str, new UrlResolutionListener() {
            public void onSuccess(@NonNull String str) {
                UrlHandler.this.mTaskPending = false;
                UrlHandler.this.handleResolvedUrl(context2, str, z2, iterable2);
            }

            public void onFailure(@NonNull String str, @Nullable Throwable th) {
                UrlHandler.this.mTaskPending = false;
                UrlHandler.this.failUrlHandling(str2, null, str, th);
            }
        });
        this.mTaskPending = true;
    }

    public boolean handleResolvedUrl(@NonNull Context context, @NonNull String str, boolean z, @Nullable Iterable<String> iterable) {
        if (TextUtils.isEmpty(str)) {
            failUrlHandling(str, null, "Attempted to handle empty url.", null);
            return false;
        }
        UrlAction urlAction = UrlAction.NOOP;
        Uri parse = Uri.parse(str);
        Iterator it = this.mSupportedUrlActions.iterator();
        UrlAction urlAction2 = urlAction;
        while (it.hasNext()) {
            urlAction = (UrlAction) it.next();
            if (urlAction.shouldTryHandlingUrl(parse)) {
                try {
                    urlAction.handleUrl(this, context, parse, z, this.mDspCreativeId);
                    if (!(this.mAlreadySucceeded || this.mTaskPending || UrlAction.IGNORE_ABOUT_SCHEME.equals(urlAction) || UrlAction.HANDLE_MOPUB_SCHEME.equals(urlAction))) {
                        TrackingRequest.makeTrackingHttpRequest((Iterable) iterable, context, Name.CLICK_REQUEST);
                        this.mResultActions.urlHandlingSucceeded(parse.toString(), urlAction);
                        this.mAlreadySucceeded = true;
                    }
                    return true;
                } catch (Throwable e) {
                    MoPubLog.d(e.getMessage(), e);
                }
            } else {
                urlAction = urlAction2;
                urlAction2 = urlAction;
            }
        }
        failUrlHandling(str, urlAction2, "Link ignored. Unable to handle url: " + str, null);
        return false;
    }

    private void failUrlHandling(@Nullable String str, @Nullable UrlAction urlAction, @NonNull String str2, @Nullable Throwable th) {
        Preconditions.checkNotNull(str2);
        if (urlAction == null) {
            urlAction = UrlAction.NOOP;
        }
        MoPubLog.d(str2, th);
        this.mResultActions.urlHandlingFailed(str, urlAction);
    }
}
