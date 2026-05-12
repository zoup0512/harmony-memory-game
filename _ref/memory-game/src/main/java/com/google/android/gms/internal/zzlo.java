package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;

@TargetApi(11)
@zzin
public class zzlo extends WebChromeClient {
    private final zzlh zzbgf;

    public zzlo(zzlh com_google_android_gms_internal_zzlh) {
        this.zzbgf = com_google_android_gms_internal_zzlh;
    }

    private final Context zza(WebView webView) {
        if (!(webView instanceof zzlh)) {
            return webView.getContext();
        }
        zzlh com_google_android_gms_internal_zzlh = (zzlh) webView;
        Context zzue = com_google_android_gms_internal_zzlh.zzue();
        return zzue == null ? com_google_android_gms_internal_zzlh.getContext() : zzue;
    }

    private static void zza(Builder builder, String str, JsResult jsResult) {
        builder.setMessage(str).setPositiveButton(17039370, new 3(jsResult)).setNegativeButton(17039360, new 2(jsResult)).setOnCancelListener(new 1(jsResult)).create().show();
    }

    private static void zza(Context context, Builder builder, String str, String str2, JsPromptResult jsPromptResult) {
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        View textView = new TextView(context);
        textView.setText(str);
        View editText = new EditText(context);
        editText.setText(str2);
        linearLayout.addView(textView);
        linearLayout.addView(editText);
        builder.setView(linearLayout).setPositiveButton(17039370, new 6(jsPromptResult, editText)).setNegativeButton(17039360, new 5(jsPromptResult)).setOnCancelListener(new 4(jsPromptResult)).create().show();
    }

    private final boolean zzvo() {
        return zzu.zzfq().zza(this.zzbgf.getContext().getPackageManager(), this.zzbgf.getContext().getPackageName(), "android.permission.ACCESS_FINE_LOCATION") || zzu.zzfq().zza(this.zzbgf.getContext().getPackageManager(), this.zzbgf.getContext().getPackageName(), "android.permission.ACCESS_COARSE_LOCATION");
    }

    public final void onCloseWindow(WebView webView) {
        if (webView instanceof zzlh) {
            zzd zzuh = ((zzlh) webView).zzuh();
            if (zzuh == null) {
                zzb.zzcx("Tried to close an AdWebView not associated with an overlay.");
                return;
            } else {
                zzuh.close();
                return;
            }
        }
        zzb.zzcx("Tried to close a WebView that wasn't an AdWebView.");
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String valueOf = String.valueOf(consoleMessage.message());
        String valueOf2 = String.valueOf(consoleMessage.sourceId());
        valueOf = new StringBuilder((String.valueOf(valueOf).length() + 19) + String.valueOf(valueOf2).length()).append("JS: ").append(valueOf).append(" (").append(valueOf2).append(":").append(consoleMessage.lineNumber()).append(")").toString();
        if (valueOf.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        switch (7.zzcql[consoleMessage.messageLevel().ordinal()]) {
            case 1:
                zzb.e(valueOf);
                break;
            case 2:
                zzb.zzcx(valueOf);
                break;
            case 3:
            case 4:
                zzb.zzcw(valueOf);
                break;
            case 5:
                zzb.zzcv(valueOf);
                break;
            default:
                zzb.zzcw(valueOf);
                break;
        }
        return super.onConsoleMessage(consoleMessage);
    }

    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebViewTransport webViewTransport = (WebViewTransport) message.obj;
        WebView webView2 = new WebView(webView.getContext());
        webView2.setWebViewClient(this.zzbgf.zzuj());
        webViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }

    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
        long j4 = 5242880 - j3;
        if (j4 <= 0) {
            quotaUpdater.updateQuota(j);
            return;
        }
        if (j == 0) {
            if (j2 > j4 || j2 > 1048576) {
                j2 = 0;
            }
        } else if (j2 == 0) {
            j2 = Math.min(Math.min(PlaybackStateCompat.ACTION_PREPARE_FROM_URI, j4) + j, 1048576);
        } else {
            if (j2 <= Math.min(1048576 - j, j4)) {
                j += j2;
            }
            j2 = j;
        }
        quotaUpdater.updateQuota(j2);
    }

    public final void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
        if (callback != null) {
            callback.invoke(str, zzvo(), true);
        }
    }

    public final void onHideCustomView() {
        zzd zzuh = this.zzbgf.zzuh();
        if (zzuh == null) {
            zzb.zzcx("Could not get ad overlay when hiding custom view.");
        } else {
            zzuh.zznu();
        }
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), str, str2, null, jsResult, null, false);
    }

    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), str, str2, null, jsResult, null, false);
    }

    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), str, str2, null, jsResult, null, false);
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return zza(zza(webView), str, str2, str3, null, jsPromptResult, true);
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
        long j3 = PlaybackStateCompat.ACTION_PREPARE_FROM_URI + j;
        if (5242880 - j2 < j3) {
            quotaUpdater.updateQuota(0);
        } else {
            quotaUpdater.updateQuota(j3);
        }
    }

    public final void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        zza(view, -1, customViewCallback);
    }

    protected final void zza(View view, int i, CustomViewCallback customViewCallback) {
        zzd zzuh = this.zzbgf.zzuh();
        if (zzuh == null) {
            zzb.zzcx("Could not get ad overlay when showing custom view.");
            customViewCallback.onCustomViewHidden();
            return;
        }
        zzuh.zza(view, customViewCallback);
        zzuh.setRequestedOrientation(i);
    }

    protected boolean zza(Context context, String str, String str2, String str3, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
        try {
            Builder builder = new Builder(context);
            builder.setTitle(str);
            if (z) {
                zza(context, builder, str2, str3, jsPromptResult);
            } else {
                zza(builder, str2, jsResult);
            }
        } catch (Throwable e) {
            zzb.zzd("Fail to display Dialog.", e);
        }
        return true;
    }
}
