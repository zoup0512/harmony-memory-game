package com.mopub.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.amazon.device.ads.AdWebViewClient;
import com.facebook.places.model.PlaceFields;
import com.mopub.common.UrlHandler.MoPubSchemeListener;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import com.mopub.network.TrackingRequest;
import java.net.URISyntaxException;

public enum UrlAction {
    HANDLE_MOPUB_SCHEME(false) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return "mopub".equalsIgnoreCase(uri.getScheme());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
            String host = uri.getHost();
            MoPubSchemeListener moPubSchemeListener = urlHandler.getMoPubSchemeListener();
            if ("finishLoad".equalsIgnoreCase(host)) {
                moPubSchemeListener.onFinishLoad();
            } else if ("close".equalsIgnoreCase(host)) {
                moPubSchemeListener.onClose();
            } else if ("failLoad".equalsIgnoreCase(host)) {
                moPubSchemeListener.onFailLoad();
            } else {
                throw new IntentNotResolvableException("Could not handle MoPub Scheme url: " + uri);
            }
        }
    },
    IGNORE_ABOUT_SCHEME(false) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return PlaceFields.ABOUT.equalsIgnoreCase(uri.getScheme());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
            MoPubLog.d("Link to about page ignored.");
        }
    },
    HANDLE_PHONE_SCHEME(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            return "tel".equalsIgnoreCase(scheme) || AdWebViewClient.VOICEMAIL.equalsIgnoreCase(scheme) || "sms".equalsIgnoreCase(scheme) || AdWebViewClient.MAILTO.equalsIgnoreCase(scheme) || AdWebViewClient.GEO.equalsIgnoreCase(scheme) || AdWebViewClient.GOOGLE_STREETVIEW.equalsIgnoreCase(scheme);
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
            Intents.launchActionViewIntent(context, uri, "Could not handle intent with URI: " + uri + "\n\tIs this intent supported on your phone?");
        }
    },
    OPEN_NATIVE_BROWSER(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return "mopubnativebrowser".equalsIgnoreCase(uri.getScheme());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
            String str2 = "Unable to load mopub native browser url: " + uri;
            try {
                Intents.launchIntentForUserClick(context, Intents.intentForNativeBrowserScheme(uri), str2);
            } catch (UrlParseException e) {
                throw new IntentNotResolvableException(str2 + "\n\t" + e.getMessage());
            }
        }
    },
    OPEN_APP_MARKET(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            String host = uri.getHost();
            return "play.google.com".equalsIgnoreCase(host) || "market.android.com".equalsIgnoreCase(host) || "market".equalsIgnoreCase(scheme) || uri.toString().toLowerCase().startsWith("play.google.com/") || uri.toString().toLowerCase().startsWith("market.android.com/");
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
            Intents.launchApplicationUrl(context, uri);
        }
    },
    OPEN_IN_APP_BROWSER(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            String scheme = uri.getScheme();
            return Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme);
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
            if (!urlHandler.shouldSkipShowMoPubBrowser()) {
                Intents.showMoPubBrowserForUrl(context, uri, str);
            }
        }
    },
    HANDLE_SHARE_TWEET(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            Preconditions.checkNotNull(uri);
            return "mopubshare".equalsIgnoreCase(uri.getScheme()) && "tweet".equalsIgnoreCase(uri.getHost());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(uri);
            String str2 = "Share via";
            String str3 = "Could not handle share tweet intent with URI " + uri;
            try {
                Intents.launchIntentForUserClick(context, Intent.createChooser(Intents.intentForShareTweet(uri), "Share via"), str3);
            } catch (UrlParseException e) {
                throw new IntentNotResolvableException(str3 + "\n\t" + e.getMessage());
            }
        }
    },
    FOLLOW_DEEP_LINK_WITH_FALLBACK(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return "deeplink+".equalsIgnoreCase(uri.getScheme());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
            if ("navigate".equalsIgnoreCase(uri.getHost())) {
                try {
                    String queryParameter = uri.getQueryParameter("primaryUrl");
                    Iterable queryParameters = uri.getQueryParameters("primaryTrackingUrl");
                    String queryParameter2 = uri.getQueryParameter("fallbackUrl");
                    Iterable queryParameters2 = uri.getQueryParameters("fallbackTrackingUrl");
                    if (queryParameter == null) {
                        throw new IntentNotResolvableException("Deeplink+ did not have 'primaryUrl' query param.");
                    }
                    Uri parse = Uri.parse(queryParameter);
                    if (shouldTryHandlingUrl(parse)) {
                        throw new IntentNotResolvableException("Deeplink+ had another Deeplink+ as the 'primaryUrl'.");
                    }
                    try {
                        Intents.launchApplicationUrl(context, parse);
                        TrackingRequest.makeTrackingHttpRequest(queryParameters, context, Name.CLICK_REQUEST);
                        return;
                    } catch (IntentNotResolvableException e) {
                        if (queryParameter2 == null) {
                            throw new IntentNotResolvableException("Unable to handle 'primaryUrl' for Deeplink+ and 'fallbackUrl' was missing.");
                        } else if (shouldTryHandlingUrl(Uri.parse(queryParameter2))) {
                            throw new IntentNotResolvableException("Deeplink+ URL had another Deeplink+ URL as the 'fallbackUrl'.");
                        } else {
                            urlHandler.handleUrl(context, queryParameter2, true, queryParameters2);
                            return;
                        }
                    }
                } catch (UnsupportedOperationException e2) {
                    throw new IntentNotResolvableException("Deeplink+ URL was not a hierarchical URI.");
                }
            }
            throw new IntentNotResolvableException("Deeplink+ URL did not have 'navigate' as the host.");
        }
    },
    FOLLOW_DEEP_LINK(true) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return !TextUtils.isEmpty(uri.getScheme());
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
            if (Constants.INTENT_SCHEME.equalsIgnoreCase(uri.getScheme())) {
                try {
                    Intents.launchApplicationIntent(context, Intent.parseUri(uri.toString(), 1));
                    return;
                } catch (URISyntaxException e) {
                    throw new IntentNotResolvableException("Intent uri had invalid syntax: " + uri.toString());
                }
            }
            Intents.launchApplicationUrl(context, uri);
        }
    },
    NOOP(false) {
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return false;
        }

        protected void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str) {
        }
    };
    
    private final boolean mRequiresUserInteraction;

    protected abstract void performAction(@NonNull Context context, @NonNull Uri uri, @NonNull UrlHandler urlHandler, @Nullable String str);

    public abstract boolean shouldTryHandlingUrl(@NonNull Uri uri);

    public void handleUrl(UrlHandler urlHandler, @NonNull Context context, @NonNull Uri uri, boolean z, @Nullable String str) {
        MoPubLog.d("Ad event URL: " + uri);
        if (!this.mRequiresUserInteraction || z) {
            performAction(context, uri, urlHandler, str);
            return;
        }
        throw new IntentNotResolvableException("Attempted to handle action without user interaction.");
    }

    private UrlAction(boolean z) {
        this.mRequiresUserInteraction = z;
    }
}
