package com.mopub.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Constants;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.UrlAction;
import com.mopub.common.logging.MoPubLog;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;

public class Intents {
    private Intents() {
    }

    public static void startActivity(@NonNull Context context, @NonNull Intent intent) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        try {
            context.startActivity(intent);
        } catch (Throwable e) {
            throw new IntentNotResolvableException(e);
        }
    }

    public static Intent getStartActivityIntent(@NonNull Context context, @NonNull Class cls, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }

    public static boolean deviceCanHandleIntent(@NonNull Context context, @NonNull Intent intent) {
        try {
            if (context.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static Intent intentForNativeBrowserScheme(@NonNull Uri uri) {
        Preconditions.checkNotNull(uri);
        if (!UrlAction.OPEN_NATIVE_BROWSER.shouldTryHandlingUrl(uri)) {
            throw new UrlParseException("URL does not have mopubnativebrowser:// scheme.");
        } else if ("navigate".equals(uri.getHost())) {
            try {
                String queryParameter = uri.getQueryParameter("url");
                if (queryParameter == null) {
                    throw new UrlParseException("URL missing 'url' query parameter.");
                }
                return new Intent("android.intent.action.VIEW", Uri.parse(queryParameter));
            } catch (UnsupportedOperationException e) {
                MoPubLog.w("Could not handle url: " + uri);
                throw new UrlParseException("Passed-in URL did not create a hierarchical URI.");
            }
        } else {
            throw new UrlParseException("URL missing 'navigate' host parameter.");
        }
    }

    public static Intent intentForShareTweet(@NonNull Uri uri) {
        if (UrlAction.HANDLE_SHARE_TWEET.shouldTryHandlingUrl(uri)) {
            try {
                CharSequence queryParameter = uri.getQueryParameter("screen_name");
                CharSequence queryParameter2 = uri.getQueryParameter("tweet_id");
                if (TextUtils.isEmpty(queryParameter)) {
                    throw new UrlParseException("URL missing non-empty 'screen_name' query parameter.");
                } else if (TextUtils.isEmpty(queryParameter2)) {
                    throw new UrlParseException("URL missing non-empty 'tweet_id' query parameter.");
                } else {
                    String format = String.format("https://twitter.com/%s/status/%s", new Object[]{queryParameter, queryParameter2});
                    String format2 = String.format("Check out @%s's Tweet: %s", new Object[]{queryParameter, format});
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                    intent.putExtra("android.intent.extra.SUBJECT", format2);
                    intent.putExtra("android.intent.extra.TEXT", format2);
                    return intent;
                }
            } catch (UnsupportedOperationException e) {
                MoPubLog.w("Could not handle url: " + uri);
                throw new UrlParseException("Passed-in URL did not create a hierarchical URI.");
            }
        }
        throw new UrlParseException("URL does not have mopubshare://tweet? format.");
    }

    public static void showMoPubBrowserForUrl(@NonNull Context context, @NonNull Uri uri, @Nullable String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        MoPubLog.d("Final URI to show in browser: " + uri);
        Bundle bundle = new Bundle();
        bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, uri.toString());
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, str);
        }
        launchIntentForUserClick(context, getStartActivityIntent(context, MoPubBrowser.class, bundle), "Could not show MoPubBrowser for url: " + uri + "\n\tPerhaps you forgot to declare com.mopub.common.MoPubBrowser in your Android manifest file.");
    }

    public static void launchIntentForUserClick(@NonNull Context context, @NonNull Intent intent, @Nullable String str) {
        NoThrow.checkNotNull(context);
        NoThrow.checkNotNull(intent);
        try {
            startActivity(context, intent);
        } catch (IntentNotResolvableException e) {
            throw new IntentNotResolvableException(str + "\n" + e.getMessage());
        }
    }

    public static void launchApplicationUrl(@NonNull Context context, @NonNull Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        if (deviceCanHandleIntent(context, intent)) {
            launchApplicationIntent(context, intent);
            return;
        }
        throw new IntentNotResolvableException("Could not handle application specific action: " + uri + "\n\tYou may be running in the emulator or another device which does not have the required application.");
    }

    public static void launchApplicationIntent(@NonNull Context context, @NonNull Intent intent) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (deviceCanHandleIntent(context, intent)) {
            String str = "Unable to open intent: " + intent;
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            launchIntentForUserClick(context, intent, str);
            return;
        }
        Object stringExtra = intent.getStringExtra("browser_fallback_url");
        if (!TextUtils.isEmpty(stringExtra)) {
            Uri parse = Uri.parse(stringExtra);
            String scheme = parse.getScheme();
            if (Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme)) {
                showMoPubBrowserForUrl(context, parse, null);
            } else {
                launchApplicationUrl(context, parse);
            }
        } else if ("market".equalsIgnoreCase(intent.getScheme())) {
            throw new IntentNotResolvableException("Device could not handle neither intent nor market url.\nIntent: " + intent.toString());
        } else {
            launchApplicationUrl(context, getPlayStoreUri(intent));
        }
    }

    @NonNull
    public static Uri getPlayStoreUri(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        return Uri.parse("market://details?id=" + intent.getPackage());
    }

    public static void launchActionViewIntent(@NonNull Context context, @NonNull Uri uri, @Nullable String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        launchIntentForUserClick(context, intent, str);
    }

    @Deprecated
    public static boolean canHandleApplicationUrl(Context context, Uri uri) {
        return false;
    }

    @Deprecated
    public static boolean canHandleApplicationUrl(Context context, Uri uri, boolean z) {
        return false;
    }
}
