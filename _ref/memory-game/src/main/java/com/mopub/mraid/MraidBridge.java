package com.mopub.mraid;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.device.ads.DeviceInfo;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.mopub.common.AdReport;
import com.mopub.common.AdType;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.common.Constants;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.BaseWebView;
import com.mopub.mobileads.ViewGestureDetector;
import com.mopub.mobileads.ViewGestureDetector$UserClickListener;
import com.mopub.mobileads.resource.MraidJavascript;
import com.mopub.network.Networking;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;

public class MraidBridge {
    private final String FILTERED_JAVASCRIPT_SOURCE;
    private final AdReport mAdReport;
    private boolean mHasLoaded;
    private boolean mIsClicked;
    @Nullable
    private MraidBridgeListener mMraidBridgeListener;
    @NonNull
    private final MraidNativeCommandHandler mMraidNativeCommandHandler;
    @Nullable
    private MraidWebView mMraidWebView;
    private final WebViewClient mMraidWebViewClient;
    @NonNull
    private final PlacementType mPlacementType;

    public interface MraidBridgeListener {
        void onClose();

        boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage);

        void onExpand(URI uri, boolean z);

        boolean onJsAlert(@NonNull String str, @NonNull JsResult jsResult);

        void onOpen(URI uri);

        void onPageFailedToLoad();

        void onPageLoaded();

        void onPlayVideo(URI uri);

        void onResize(int i, int i2, int i3, int i4, @NonNull ClosePosition closePosition, boolean z);

        void onSetOrientationProperties(boolean z, MraidOrientation mraidOrientation);

        void onUseCustomClose(boolean z);

        void onVisibilityChanged(boolean z);
    }

    public static class MraidWebView extends BaseWebView {
        private boolean mIsVisible;
        @Nullable
        private OnVisibilityChangedListener mOnVisibilityChangedListener;

        public interface OnVisibilityChangedListener {
            void onVisibilityChanged(boolean z);
        }

        public MraidWebView(Context context) {
            super(context);
            this.mIsVisible = getVisibility() == 0;
        }

        void setVisibilityChangedListener(@Nullable OnVisibilityChangedListener onVisibilityChangedListener) {
            this.mOnVisibilityChangedListener = onVisibilityChangedListener;
        }

        protected void onVisibilityChanged(@NonNull View view, int i) {
            super.onVisibilityChanged(view, i);
            boolean z = i == 0;
            if (z != this.mIsVisible) {
                this.mIsVisible = z;
                if (this.mOnVisibilityChangedListener != null) {
                    this.mOnVisibilityChangedListener.onVisibilityChanged(this.mIsVisible);
                }
            }
        }

        public boolean isVisible() {
            return this.mIsVisible;
        }
    }

    MraidBridge(@Nullable AdReport adReport, @NonNull PlacementType placementType) {
        this(adReport, placementType, new MraidNativeCommandHandler());
    }

    @VisibleForTesting
    MraidBridge(@Nullable AdReport adReport, @NonNull PlacementType placementType, @NonNull MraidNativeCommandHandler mraidNativeCommandHandler) {
        this.FILTERED_JAVASCRIPT_SOURCE = MraidJavascript.JAVASCRIPT_SOURCE.replaceAll("(?m)^\\s+", "").replaceAll("(?m)^//.*(?=\\n)", "");
        this.mMraidWebViewClient = new WebViewClient() {
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                MoPubLog.d("Error: " + str);
                super.onReceivedError(webView, i, str, str2);
            }

            public boolean shouldOverrideUrlLoading(@NonNull WebView webView, @NonNull String str) {
                return MraidBridge.this.handleShouldOverrideUrl(str);
            }

            public void onPageFinished(@NonNull WebView webView, @NonNull String str) {
                MraidBridge.this.handlePageFinished();
            }
        };
        this.mAdReport = adReport;
        this.mPlacementType = placementType;
        this.mMraidNativeCommandHandler = mraidNativeCommandHandler;
    }

    void setMraidBridgeListener(@Nullable MraidBridgeListener mraidBridgeListener) {
        this.mMraidBridgeListener = mraidBridgeListener;
    }

    void attachView(@NonNull MraidWebView mraidWebView) {
        this.mMraidWebView = mraidWebView;
        this.mMraidWebView.getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 17 && this.mPlacementType == PlacementType.INTERSTITIAL) {
            mraidWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.mMraidWebView.loadUrl("javascript:" + this.FILTERED_JAVASCRIPT_SOURCE);
        this.mMraidWebView.setScrollContainer(false);
        this.mMraidWebView.setVerticalScrollBarEnabled(false);
        this.mMraidWebView.setHorizontalScrollBarEnabled(false);
        this.mMraidWebView.setBackgroundColor(-16777216);
        this.mMraidWebView.setWebViewClient(this.mMraidWebViewClient);
        this.mMraidWebView.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                if (MraidBridge.this.mMraidBridgeListener != null) {
                    return MraidBridge.this.mMraidBridgeListener.onJsAlert(str2, jsResult);
                }
                return super.onJsAlert(webView, str, str2, jsResult);
            }

            public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
                if (MraidBridge.this.mMraidBridgeListener != null) {
                    return MraidBridge.this.mMraidBridgeListener.onConsoleMessage(consoleMessage);
                }
                return super.onConsoleMessage(consoleMessage);
            }

            public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
                super.onShowCustomView(view, customViewCallback);
            }
        });
        final ViewGestureDetector viewGestureDetector = new ViewGestureDetector(this.mMraidWebView.getContext(), this.mMraidWebView, this.mAdReport);
        viewGestureDetector.setUserClickListener(new ViewGestureDetector$UserClickListener() {
            public void onUserClick() {
                MraidBridge.this.mIsClicked = true;
            }

            public void onResetUserClick() {
                MraidBridge.this.mIsClicked = false;
            }

            public boolean wasClicked() {
                return MraidBridge.this.mIsClicked;
            }
        });
        this.mMraidWebView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                viewGestureDetector.sendTouchEvent(motionEvent);
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
        this.mMraidWebView.setVisibilityChangedListener(new OnVisibilityChangedListener() {
            public void onVisibilityChanged(boolean z) {
                if (MraidBridge.this.mMraidBridgeListener != null) {
                    MraidBridge.this.mMraidBridgeListener.onVisibilityChanged(z);
                }
            }
        });
    }

    void detach() {
        this.mMraidWebView = null;
    }

    public void setContentHtml(@NonNull String str) {
        if (this.mMraidWebView == null) {
            MoPubLog.d("MRAID bridge called setContentHtml before WebView was attached");
            return;
        }
        this.mHasLoaded = false;
        this.mMraidWebView.loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + "/", str, "text/html", "UTF-8", null);
    }

    public void setContentUrl(String str) {
        if (this.mMraidWebView == null) {
            MoPubLog.d("MRAID bridge called setContentHtml while WebView was not attached");
            return;
        }
        this.mHasLoaded = false;
        this.mMraidWebView.loadUrl(str);
    }

    void injectJavaScript(@NonNull String str) {
        if (this.mMraidWebView == null) {
            MoPubLog.d("Attempted to inject Javascript into MRAID WebView while was not attached:\n\t" + str);
            return;
        }
        MoPubLog.v("Injecting Javascript into MRAID WebView:\n\t" + str);
        this.mMraidWebView.loadUrl("javascript:" + str);
    }

    private void fireErrorEvent(@NonNull MraidJavascriptCommand mraidJavascriptCommand, @NonNull String str) {
        injectJavaScript("window.mraidbridge.notifyErrorEvent(" + JSONObject.quote(mraidJavascriptCommand.toJavascriptString()) + ", " + JSONObject.quote(str) + ")");
    }

    private void fireNativeCommandCompleteEvent(@NonNull MraidJavascriptCommand mraidJavascriptCommand) {
        injectJavaScript("window.mraidbridge.nativeCallComplete(" + JSONObject.quote(mraidJavascriptCommand.toJavascriptString()) + ")");
    }

    @VisibleForTesting
    boolean handleShouldOverrideUrl(@NonNull String str) {
        try {
            URI uri = new URI(str);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            if ("mopub".equals(scheme)) {
                if ("failLoad".equals(host) && this.mPlacementType == PlacementType.INLINE && this.mMraidBridgeListener != null) {
                    this.mMraidBridgeListener.onPageFailedToLoad();
                }
                return true;
            } else if (AdType.MRAID.equals(scheme)) {
                Map hashMap = new HashMap();
                for (NameValuePair nameValuePair : URLEncodedUtils.parse(uri, "UTF-8")) {
                    hashMap.put(nameValuePair.getName(), nameValuePair.getValue());
                }
                MraidJavascriptCommand fromJavascriptString = MraidJavascriptCommand.fromJavascriptString(host);
                try {
                    runCommand(fromJavascriptString, hashMap);
                } catch (MraidCommandException e) {
                    fireErrorEvent(fromJavascriptString, e.getMessage());
                }
                fireNativeCommandCompleteEvent(fromJavascriptString);
                return true;
            } else if (!this.mIsClicked) {
                return false;
            } else {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.addFlags(268435456);
                try {
                    if (this.mMraidWebView == null) {
                        MoPubLog.d("WebView was detached. Unable to load a URL");
                        return true;
                    }
                    this.mMraidWebView.getContext().startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e2) {
                    MoPubLog.d("No activity found to handle this URL " + str);
                    return false;
                }
            }
        } catch (URISyntaxException e3) {
            MoPubLog.w("Invalid MRAID URL: " + str);
            fireErrorEvent(MraidJavascriptCommand.UNSPECIFIED, "Mraid command sent an invalid URL");
            return true;
        }
    }

    @VisibleForTesting
    private void handlePageFinished() {
        if (!this.mHasLoaded) {
            this.mHasLoaded = true;
            if (this.mMraidBridgeListener != null) {
                this.mMraidBridgeListener.onPageLoaded();
            }
        }
    }

    @VisibleForTesting
    void runCommand(@NonNull final MraidJavascriptCommand mraidJavascriptCommand, @NonNull Map<String, String> map) {
        if (mraidJavascriptCommand.requiresClick(this.mPlacementType) && !this.mIsClicked) {
            throw new MraidCommandException("Cannot execute this command unless the user clicks");
        } else if (this.mMraidBridgeListener == null) {
            throw new MraidCommandException("Invalid state to execute this command");
        } else if (this.mMraidWebView == null) {
            throw new MraidCommandException("The current WebView is being destroyed");
        } else {
            switch (mraidJavascriptCommand) {
                case CLOSE:
                    this.mMraidBridgeListener.onClose();
                    return;
                case RESIZE:
                    this.mMraidBridgeListener.onResize(checkRange(parseSize((String) map.get("width")), 0, 100000), checkRange(parseSize((String) map.get("height")), 0, 100000), checkRange(parseSize((String) map.get("offsetX")), -100000, 100000), checkRange(parseSize((String) map.get("offsetY")), -100000, 100000), parseClosePosition((String) map.get("customClosePosition"), ClosePosition.TOP_RIGHT), parseBoolean((String) map.get("allowOffscreen"), true));
                    return;
                case EXPAND:
                    this.mMraidBridgeListener.onExpand(parseURI((String) map.get("url"), null), parseBoolean((String) map.get("shouldUseCustomClose"), false));
                    return;
                case USE_CUSTOM_CLOSE:
                    this.mMraidBridgeListener.onUseCustomClose(parseBoolean((String) map.get("shouldUseCustomClose"), false));
                    return;
                case OPEN:
                    this.mMraidBridgeListener.onOpen(parseURI((String) map.get("url")));
                    return;
                case SET_ORIENTATION_PROPERTIES:
                    this.mMraidBridgeListener.onSetOrientationProperties(parseBoolean((String) map.get("allowOrientationChange")), parseOrientation((String) map.get("forceOrientation")));
                    return;
                case PLAY_VIDEO:
                    this.mMraidBridgeListener.onPlayVideo(parseURI((String) map.get(ShareConstants.MEDIA_URI)));
                    return;
                case STORE_PICTURE:
                    this.mMraidNativeCommandHandler.storePicture(this.mMraidWebView.getContext(), parseURI((String) map.get(ShareConstants.MEDIA_URI)).toString(), new MraidCommandFailureListener() {
                        public void onFailure(MraidCommandException mraidCommandException) {
                            MraidBridge.this.fireErrorEvent(mraidJavascriptCommand, mraidCommandException.getMessage());
                        }
                    });
                    return;
                case CREATE_CALENDAR_EVENT:
                    this.mMraidNativeCommandHandler.createCalendarEvent(this.mMraidWebView.getContext(), map);
                    return;
                case UNSPECIFIED:
                    throw new MraidCommandException("Unspecified MRAID Javascript command");
                default:
                    return;
            }
        }
    }

    private ClosePosition parseClosePosition(@NonNull String str, @NonNull ClosePosition closePosition) {
        if (TextUtils.isEmpty(str)) {
            return closePosition;
        }
        if (str.equals("top-left")) {
            return ClosePosition.TOP_LEFT;
        }
        if (str.equals("top-right")) {
            return ClosePosition.TOP_RIGHT;
        }
        if (str.equals("center")) {
            return ClosePosition.CENTER;
        }
        if (str.equals("bottom-left")) {
            return ClosePosition.BOTTOM_LEFT;
        }
        if (str.equals("bottom-right")) {
            return ClosePosition.BOTTOM_RIGHT;
        }
        if (str.equals("top-center")) {
            return ClosePosition.TOP_CENTER;
        }
        if (str.equals("bottom-center")) {
            return ClosePosition.BOTTOM_CENTER;
        }
        throw new MraidCommandException("Invalid close position: " + str);
    }

    private int parseSize(@NonNull String str) {
        try {
            return Integer.parseInt(str, 10);
        } catch (NumberFormatException e) {
            throw new MraidCommandException("Invalid numeric parameter: " + str);
        }
    }

    private MraidOrientation parseOrientation(String str) {
        if (DeviceInfo.ORIENTATION_PORTRAIT.equals(str)) {
            return MraidOrientation.PORTRAIT;
        }
        if (DeviceInfo.ORIENTATION_LANDSCAPE.equals(str)) {
            return MraidOrientation.LANDSCAPE;
        }
        if ("none".equals(str)) {
            return MraidOrientation.NONE;
        }
        throw new MraidCommandException("Invalid orientation: " + str);
    }

    private int checkRange(int i, int i2, int i3) {
        if (i >= i2 && i <= i3) {
            return i;
        }
        throw new MraidCommandException("Integer parameter out of range: " + i);
    }

    private boolean parseBoolean(@Nullable String str, boolean z) {
        return str == null ? z : parseBoolean(str);
    }

    private boolean parseBoolean(String str) {
        if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(str)) {
            return true;
        }
        if ("false".equals(str)) {
            return false;
        }
        throw new MraidCommandException("Invalid boolean parameter: " + str);
    }

    @NonNull
    private URI parseURI(@Nullable String str, URI uri) {
        return str == null ? uri : parseURI(str);
    }

    @NonNull
    private URI parseURI(@Nullable String str) {
        if (str == null) {
            throw new MraidCommandException("Parameter cannot be null");
        }
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            throw new MraidCommandException("Invalid URL parameter: " + str);
        }
    }

    void notifyViewability(boolean z) {
        injectJavaScript("mraidbridge.setIsViewable(" + z + ")");
    }

    void notifyPlacementType(PlacementType placementType) {
        injectJavaScript("mraidbridge.setPlacementType(" + JSONObject.quote(placementType.toJavascriptString()) + ")");
    }

    void notifyViewState(ViewState viewState) {
        injectJavaScript("mraidbridge.setState(" + JSONObject.quote(viewState.toJavascriptString()) + ")");
    }

    void notifySupports(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        injectJavaScript("mraidbridge.setSupports(" + z + "," + z2 + "," + z3 + "," + z4 + "," + z5 + ")");
    }

    @NonNull
    private String stringifyRect(Rect rect) {
        return rect.left + "," + rect.top + "," + rect.width() + "," + rect.height();
    }

    @NonNull
    private String stringifySize(Rect rect) {
        return rect.width() + "," + rect.height();
    }

    public void notifyScreenMetrics(@NonNull MraidScreenMetrics mraidScreenMetrics) {
        injectJavaScript("mraidbridge.setScreenSize(" + stringifySize(mraidScreenMetrics.getScreenRectDips()) + ");mraidbridge.setMaxSize(" + stringifySize(mraidScreenMetrics.getRootViewRectDips()) + ");mraidbridge.setCurrentPosition(" + stringifyRect(mraidScreenMetrics.getCurrentAdRectDips()) + ");mraidbridge.setDefaultPosition(" + stringifyRect(mraidScreenMetrics.getDefaultAdRectDips()) + ")");
        injectJavaScript("mraidbridge.notifySizeChangeEvent(" + stringifySize(mraidScreenMetrics.getCurrentAdRect()) + ")");
    }

    void notifyReady() {
        injectJavaScript("mraidbridge.notifyReadyEvent();");
    }

    boolean isClicked() {
        return this.mIsClicked;
    }

    boolean isVisible() {
        return this.mMraidWebView != null && this.mMraidWebView.isVisible();
    }

    boolean isAttached() {
        return this.mMraidWebView != null;
    }

    boolean isLoaded() {
        return this.mHasLoaded;
    }

    @VisibleForTesting
    MraidWebView getMraidWebView() {
        return this.mMraidWebView;
    }

    @VisibleForTesting
    void setClicked(boolean z) {
        this.mIsClicked = z;
    }
}
