package org.nexage.sourcekit.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.amazon.device.ads.DeviceInfo;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.utils.Log.LogLevel;
import com.mopub.common.AdType;
import io.branch.referral.Branch;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.nexage.sourcekit.mraid.internal.MRAIDHtmlProcessor;
import org.nexage.sourcekit.mraid.internal.MRAIDLog;
import org.nexage.sourcekit.mraid.internal.MRAIDLog.LOG_LEVEL;
import org.nexage.sourcekit.mraid.internal.MRAIDNativeFeatureManager;
import org.nexage.sourcekit.mraid.internal.MRAIDParser;
import org.nexage.sourcekit.mraid.properties.MRAIDOrientationProperties;
import org.nexage.sourcekit.mraid.properties.MRAIDResizeProperties;
import org.nexage.sourcekit.mraid.rtb.ReportButton;
import org.nexage.sourcekit.mraid.rtb.ReportView;
import org.nexage.sourcekit.mraid.rtb.ReportView.ComplainedCallback;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;
import org.nexage.sourcekit.vast.view.VastCountdown;

@SuppressLint({"ViewConstructor"})
public class MRAIDView extends RelativeLayout implements ComplainedCallback {
    private static final int CLOSE_REGION_SIZE = 50;
    private static final int PROGRESS_TIMER_INTERVAL = 40;
    public static final int STATE_DEFAULT = 1;
    public static final int STATE_EXPANDED = 2;
    public static final int STATE_HIDDEN = 4;
    public static final int STATE_LOADING = 0;
    public static final int STATE_RESIZED = 3;
    private static final String TAG = "MRAIDView";
    public static final String VERSION = "1.1.1";
    private String baseUrl;
    private VastCountdown closeRegion;
    private int closeTime;
    private int closeTimerPosition;
    private int contentViewTop;
    private Context context;
    private Rect currentPosition;
    private WebView currentWebView;
    private Rect defaultPosition;
    private DisplayMetrics displayMetrics;
    private RelativeLayout expandedView;
    private GestureDetector gestureDetector;
    private Handler handler;
    private Activity interstitialActivity;
    private boolean isActionBarShowing;
    private boolean isCloseClickable;
    private boolean isClosing;
    private boolean isComplained;
    private boolean isExpandingFromDefault;
    private boolean isExpandingPart2;
    private boolean isForceNotFullScreen;
    private boolean isForcingFullScreen;
    private boolean isFullScreen;
    private final boolean isInterstitial;
    private boolean isLaidOut;
    private boolean isPageFinished;
    private boolean isShown;
    private boolean isTouched;
    private boolean isViewable;
    private MRAIDViewListener listener;
    private String mData;
    private Size maxSize;
    private String mraidJs;
    private MRAIDWebChromeClient mraidWebChromeClient;
    private MRAIDWebViewClient mraidWebViewClient;
    private MRAIDNativeFeatureListener nativeFeatureListener;
    private MRAIDNativeFeatureManager nativeFeatureManager;
    private MRAIDOrientationProperties orientationProperties;
    private int origTitleBarVisibility;
    private final int originalRequestedOrientation;
    private boolean preload;
    private MRAIDResizeProperties resizeProperties;
    private RelativeLayout resizedView;
    private RtbInfo rtbInfo;
    private Size screenSize;
    private int state;
    private View titleBar;
    private boolean useCustomClose;
    private WebView webView;
    private WebView webViewPart2;

    private class MRAIDWebChromeClient extends WebChromeClient {
        private MRAIDWebChromeClient() {
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (consoleMessage == null || consoleMessage.message() == null) {
                return false;
            }
            if (!consoleMessage.message().contains("Uncaught ReferenceError")) {
                MRAIDLog.i("JS console", consoleMessage.message() + (consoleMessage.sourceId() == null ? "" : " at " + consoleMessage.sourceId()) + ":" + consoleMessage.lineNumber());
            }
            if (consoleMessage.message().contains("AppodealAlert")) {
                Log.e("Appodeal", consoleMessage.message().replace("AppodealAlert:", ""));
            }
            return true;
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            MRAIDLog.d("JS alert", str2);
            return handlePopups(jsResult);
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            MRAIDLog.d("JS confirm", str2);
            return handlePopups(jsResult);
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            MRAIDLog.d("JS prompt", str2);
            return handlePopups(jsPromptResult);
        }

        private boolean handlePopups(JsResult jsResult) {
            jsResult.cancel();
            return true;
        }
    }

    private class MRAIDWebViewClient extends WebViewClient {
        private MRAIDWebViewClient() {
        }

        public void onPageFinished(WebView webView, String str) {
            MRAIDLog.d(MRAIDView.TAG, "onPageFinished: " + str);
            super.onPageFinished(webView, str);
            if (MRAIDView.this.state == 0) {
                MRAIDView.this.isPageFinished = true;
                MRAIDView.this.injectJavaScript("mraid.setPlacementType('" + (MRAIDView.this.isInterstitial ? AdType.INTERSTITIAL : "inline") + "');");
                MRAIDView.this.setSupportedServices();
                if (MRAIDView.this.isLaidOut) {
                    MRAIDView.this.setScreenSize();
                    MRAIDView.this.setMaxSize();
                    MRAIDView.this.setCurrentPosition();
                    MRAIDView.this.setDefaultPosition();
                    if (MRAIDView.this.isInterstitial) {
                        MRAIDView.this.showAsInterstitial(null);
                    } else {
                        MRAIDView.this.state = 1;
                        MRAIDView.this.fireStateChangeEvent();
                        MRAIDView.this.fireReadyEvent();
                        if (MRAIDView.this.isViewable) {
                            MRAIDView.this.fireViewableChangeEvent();
                        }
                    }
                }
                if (!(MRAIDView.this.listener == null || str.equals("data:text/html,<html></html>") || !MRAIDView.this.preload)) {
                    MRAIDView.this.listener.mraidViewLoaded(MRAIDView.this);
                }
            }
            if (MRAIDView.this.isExpandingPart2) {
                MRAIDView.this.isExpandingPart2 = false;
                MRAIDView.this.handler.post(new Runnable() {
                    public void run() {
                        MRAIDView.this.injectJavaScript("mraid.setPlacementType('" + (MRAIDView.this.isInterstitial ? AdType.INTERSTITIAL : "inline") + "');");
                        MRAIDView.this.setSupportedServices();
                        MRAIDView.this.setScreenSize();
                        MRAIDView.this.setDefaultPosition();
                        MRAIDLog.d(MRAIDView.TAG, "calling fireStateChangeEvent 2");
                        MRAIDView.this.fireStateChangeEvent();
                        MRAIDView.this.fireReadyEvent();
                        if (MRAIDView.this.isViewable) {
                            MRAIDView.this.fireViewableChangeEvent();
                        }
                    }
                });
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            MRAIDLog.d(MRAIDView.TAG, "onReceivedError: " + str);
            super.onReceivedError(webView, i, str, str2);
        }

        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            if (webResourceRequest.hasGesture()) {
                MRAIDView.this.isTouched = true;
            }
            return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            MRAIDLog.d(MRAIDView.TAG, "shouldOverrideUrlLoading: " + str);
            if (str.startsWith("mraid://")) {
                MRAIDView.this.parseCommandUrl(str);
            } else {
                MRAIDView.this.open(str, webView);
            }
            return true;
        }
    }

    private final class Size {
        public int height;
        public int width;

        private Size() {
        }
    }

    public MRAIDView(Context context, String str, String str2, String[] strArr, MRAIDViewListener mRAIDViewListener, MRAIDNativeFeatureListener mRAIDNativeFeatureListener, int i, int i2, RtbInfo rtbInfo) {
        this(context, str, str2, strArr, mRAIDViewListener, mRAIDNativeFeatureListener, false, i, i2, rtbInfo, true, 0);
    }

    @SuppressLint({"NewApi"})
    public MRAIDView(Context context, String str, String str2, String[] strArr, MRAIDViewListener mRAIDViewListener, MRAIDNativeFeatureListener mRAIDNativeFeatureListener, boolean z, int i, int i2, RtbInfo rtbInfo, boolean z2) {
        this(context, str, str2, strArr, mRAIDViewListener, mRAIDNativeFeatureListener, false, i, i2, rtbInfo, z2, 0);
    }

    public MRAIDView(Context context, String str, String str2, String[] strArr, MRAIDViewListener mRAIDViewListener, MRAIDNativeFeatureListener mRAIDNativeFeatureListener, boolean z, int i, int i2, RtbInfo rtbInfo, boolean z2, int i3) {
        super(context);
        this.isShown = false;
        this.isCloseClickable = true;
        this.isTouched = false;
        this.closeTimerPosition = 0;
        if (Appodeal.getLogLevel() == LogLevel.verbose) {
            MRAIDLog.setLoggingLevel(LOG_LEVEL.verbose);
        } else {
            MRAIDLog.setLoggingLevel(LOG_LEVEL.error);
        }
        if (strArr == null) {
            strArr = new String[0];
        }
        this.isComplained = false;
        this.rtbInfo = rtbInfo;
        this.context = context;
        this.baseUrl = str;
        this.isInterstitial = z;
        this.preload = z2;
        this.closeTime = i3;
        this.state = 0;
        this.isViewable = false;
        this.useCustomClose = false;
        this.orientationProperties = new MRAIDOrientationProperties();
        this.resizeProperties = new MRAIDResizeProperties();
        this.nativeFeatureManager = new MRAIDNativeFeatureManager(context, new ArrayList(Arrays.asList(strArr)));
        this.listener = mRAIDViewListener;
        this.nativeFeatureListener = mRAIDNativeFeatureListener;
        this.displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
        this.currentPosition = new Rect();
        this.defaultPosition = new Rect();
        this.maxSize = new Size();
        this.screenSize = new Size();
        if (context instanceof Activity) {
            this.originalRequestedOrientation = ((Activity) context).getRequestedOrientation();
        } else {
            this.originalRequestedOrientation = -1;
        }
        MRAIDLog.d(TAG, "originalRequestedOrientation " + getOrientationString(this.originalRequestedOrientation));
        this.gestureDetector = new GestureDetector(getContext(), new SimpleOnGestureListener() {
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return true;
            }
        });
        this.handler = new Handler(Looper.getMainLooper());
        this.mraidWebChromeClient = new MRAIDWebChromeClient();
        this.mraidWebViewClient = new MRAIDWebViewClient();
        this.webView = createWebView();
        this.currentWebView = this.webView;
        this.webView.setBackgroundColor(Color.parseColor("#7F000000"));
        addView(this.webView);
        if (rtbInfo != null) {
            View createReportButton = createReportButton();
            addView(createReportButton);
            createReportButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (MRAIDView.this.isComplained) {
                        Appodeal.a("Ad was complained before");
                        return;
                    }
                    MRAIDView.this.addView(MRAIDView.this.createReportView());
                }
            });
        }
        injectMraidJs(this.webView);
        String processRawHtml = MRAIDHtmlProcessor.processRawHtml(str2);
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
        } else {
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        float f = displayMetrics.density;
        int i4 = point.x;
        int i5 = 0;
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        if (identifier > 0) {
            i5 = context.getResources().getDimensionPixelSize(identifier);
        }
        if (z) {
            i5 = point.y - i5;
        } else {
            i5 = Math.round(((float) i2) * f);
        }
        float f2 = ((float) i4) / ((float) i5);
        float f3 = ((float) i) / ((float) i2);
        int i6;
        if (Float.isNaN(f3)) {
            i6 = i4;
            i4 = i5;
            i5 = i6;
        } else if (f3 <= f2) {
            i4 = i5;
            i5 = Math.round(((float) i5) * f3);
        } else {
            i6 = i4;
            i4 = Math.round(((float) i4) / f3);
            i5 = i6;
        }
        int round = Math.round(((float) i5) / f);
        i5 = Math.round(((float) i4) / f);
        if (round <= i || i5 <= i2 || i == 0 || i2 == 0) {
            i4 = round;
        } else {
            i5 = i2;
            i4 = i;
        }
        String format = String.format("body, p {margin:0; padding:0} img {max-width:%dpx; max-height:%dpx} #appnext-interstitial {min-width:%dpx; min-height:%dpx;}img[width='%d'][height='%d'] {width: %dpx; height: %dpx} .appodeal-outer {display: table; position: absolute; height: 100%%; width: 100%%;}.appodeal-middle {display: table-cell; vertical-align: middle;}.appodeal-inner {margin-left: auto; margin-right: auto; width: %dpx; height: %dpx;}.ad_slug_table {margin-left: auto !important; margin-right: auto !important;} #ad[align='center'] {height: %dpx;} #voxelPlayer {position: relative !important;} #lsm_mobile_ad #wrapper, #lsm_overlay {position: relative !important;}", new Object[]{Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i5)});
        this.mData = String.format("<style type='text/css'>%s</style><div class='appodeal-outer'><div class='appodeal-middle'><div class='appodeal-inner'>%s</div></div></div>", new Object[]{format, processRawHtml});
        if (this.preload) {
            this.webView.loadDataWithBaseURL(str, this.mData, "text/html", "UTF-8", null);
        } else {
            mRAIDViewListener.mraidViewLoaded(this);
        }
        MRAIDLog.d("log level = " + MRAIDLog.getLoggingLevel());
        if (MRAIDLog.getLoggingLevel() == LOG_LEVEL.verbose) {
            injectJavaScript(this.webView, "mraid.logLevel = mraid.LogLevelEnum.DEBUG;");
        } else if (MRAIDLog.getLoggingLevel() == LOG_LEVEL.debug) {
            injectJavaScript(this.webView, "mraid.logLevel = mraid.LogLevelEnum.DEBUG;");
        } else if (MRAIDLog.getLoggingLevel() == LOG_LEVEL.info) {
            injectJavaScript(this.webView, "mraid.logLevel = mraid.LogLevelEnum.INFO;");
        } else if (MRAIDLog.getLoggingLevel() == LOG_LEVEL.warning) {
            injectJavaScript(this.webView, "mraid.logLevel = mraid.LogLevelEnum.WARNING;");
        } else if (MRAIDLog.getLoggingLevel() == LOG_LEVEL.error) {
            injectJavaScript(this.webView, "mraid.logLevel = mraid.LogLevelEnum.ERROR;");
        } else if (MRAIDLog.getLoggingLevel() == LOG_LEVEL.none) {
            injectJavaScript(this.webView, "mraid.logLevel = mraid.LogLevelEnum.NONE;");
        }
    }

    private View createReportButton() {
        View reportButton = new ReportButton(this.context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(50, 50);
        layoutParams.addRule(12, 1);
        layoutParams.addRule(9, 1);
        reportButton.setLayoutParams(layoutParams);
        return reportButton;
    }

    private View createReportView() {
        View reportView = new ReportView(this.context, this.webView);
        reportView.registerCallback(this);
        reportView.setBackgroundColor(-1073741824);
        reportView.setInfo(this.rtbInfo);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(12, 1);
        layoutParams.addRule(9, 1);
        layoutParams.addRule(11, 1);
        layoutParams.addRule(10, 1);
        reportView.setLayoutParams(layoutParams);
        return reportView;
    }

    private View createInterstitialReportView(Activity activity) {
        View reportView = new ReportView((Context) activity, this.webView);
        reportView.registerCallback(this);
        reportView.setBackgroundColor(-1073741824);
        reportView.setInfo(this.rtbInfo);
        try {
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, Math.round((getContext().getResources().getDisplayMetrics().xdpi / 160.0f) * 50.0f));
            layoutParams.addRule(12);
            reportView.setLayoutParams(layoutParams);
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return reportView;
    }

    private static String getVisibilityString(int i) {
        switch (i) {
            case 0:
                return "VISIBLE";
            case 4:
                return "INVISIBLE";
            case 8:
                return "GONE";
            default:
                return "UNKNOWN";
        }
    }

    private static String getOrientationString(int i) {
        switch (i) {
            case -1:
                return "UNSPECIFIED";
            case 0:
                return "LANDSCAPE";
            case 1:
                return "PORTRAIT";
            default:
                return "UNKNOWN";
        }
    }

    public int getState() {
        return this.state;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private WebView createWebView() {
        WebView anonymousClass3 = new WebView(this.context) {
            private static final String TAG = "MRAIDView-WebView";

            protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
                super.onLayout(z, i, i2, i3, i4);
                MRAIDView.this.onLayoutWebView(this, z, i, i2, i3, i4);
            }

            public void onConfigurationChanged(Configuration configuration) {
                super.onConfigurationChanged(configuration);
                MRAIDLog.d(TAG, "onConfigurationChanged " + (configuration.orientation == 1 ? DeviceInfo.ORIENTATION_PORTRAIT : DeviceInfo.ORIENTATION_LANDSCAPE));
                if (MRAIDView.this.isInterstitial) {
                    ((Activity) MRAIDView.this.context).getWindowManager().getDefaultDisplay().getMetrics(MRAIDView.this.displayMetrics);
                }
            }

            protected void onVisibilityChanged(View view, int i) {
                super.onVisibilityChanged(view, i);
                MRAIDLog.d(TAG, "onVisibilityChanged " + MRAIDView.getVisibilityString(i));
                if (MRAIDView.this.isInterstitial) {
                    MRAIDView.this.setViewable(i);
                }
            }

            protected void onWindowVisibilityChanged(int i) {
                super.onWindowVisibilityChanged(i);
                int visibility = getVisibility();
                MRAIDLog.d(TAG, "onWindowVisibilityChanged " + MRAIDView.getVisibilityString(i) + " (actual " + MRAIDView.getVisibilityString(visibility) + ")");
                if (MRAIDView.this.isInterstitial) {
                    MRAIDView.this.setViewable(visibility);
                }
                if (i != 0) {
                    MRAIDView.this.pauseWebView(this);
                } else {
                    MRAIDView.this.resumeWebView(this);
                }
            }
        };
        anonymousClass3.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        anonymousClass3.setScrollContainer(false);
        anonymousClass3.setVerticalScrollBarEnabled(false);
        anonymousClass3.setHorizontalScrollBarEnabled(false);
        anonymousClass3.setScrollBarStyle(33554432);
        anonymousClass3.setFocusableInTouchMode(false);
        anonymousClass3.setOnTouchListener(new OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        MRAIDView.this.isTouched = true;
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
        anonymousClass3.getSettings().setJavaScriptEnabled(true);
        anonymousClass3.getSettings().setDomStorageEnabled(true);
        anonymousClass3.setWebChromeClient(this.mraidWebChromeClient);
        anonymousClass3.setWebViewClient(this.mraidWebViewClient);
        return anonymousClass3;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.gestureDetector.onTouchEvent(motionEvent)) {
            motionEvent.setAction(3);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void clearView() {
        if (this.webView != null) {
            this.webView.setWebChromeClient(null);
            this.webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    MRAIDLog.d(MRAIDView.TAG, "shouldOverrideUrlLoading: " + str);
                    return true;
                }

                @TargetApi(24)
                public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                    MRAIDLog.d(MRAIDView.TAG, "shouldOverrideUrlLoading: " + webResourceRequest.getUrl().toString());
                    return true;
                }
            });
            this.webView.loadUrl("about:blank");
        }
    }

    public void destroy() {
        if (this.webView != null) {
            try {
                this.webView.setWebChromeClient(null);
                this.webView.setWebViewClient(null);
                WebView webView = this.webView;
                this.webView = null;
                this.currentWebView = null;
                webView.destroy();
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    private void parseCommandUrl(String str) {
        MRAIDLog.d(TAG, "parseCommandUrl " + str);
        String str2 = (String) new MRAIDParser().parseCommandUrl(str).get("command");
        String[] strArr = new String[]{"createCalendarEvent", "expand", "open", "playVideo", MRAIDNativeFeature.STORE_PICTURE, "useCustomClose"};
        String[] strArr2 = new String[]{"setOrientationProperties", "setResizeProperties"};
        try {
            if (Arrays.asList(new String[]{"close", "resize", "noFill"}).contains(str2)) {
                getClass().getDeclaredMethod(str2, new Class[0]).invoke(this, new Object[0]);
            } else if (Arrays.asList(strArr).contains(str2)) {
                Object obj;
                Method declaredMethod = getClass().getDeclaredMethod(str2, new Class[]{String.class});
                if (str2.equals("createCalendarEvent")) {
                    obj = "eventJSON";
                } else if (str2.equals("useCustomClose")) {
                    obj = "useCustomClose";
                } else {
                    obj = "url";
                }
                declaredMethod.invoke(this, new Object[]{(String) r1.get(obj)});
            } else if (Arrays.asList(strArr2).contains(str2)) {
                getClass().getDeclaredMethod(str2, new Class[]{Map.class}).invoke(this, new Object[]{r1});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void noFill() {
        MRAIDLog.d("MRAIDView-JS callback", "noFill");
        if (this.listener != null) {
            this.listener.mraidViewNoFill(this);
        }
    }

    private void close() {
        MRAIDLog.d("MRAIDView-JS callback", "close");
        if (this.isCloseClickable || this.useCustomClose) {
            this.handler.post(new Runnable() {
                public void run() {
                    if (MRAIDView.this.state == 0) {
                        return;
                    }
                    if ((MRAIDView.this.state == 1 && !MRAIDView.this.isInterstitial) || MRAIDView.this.state == 4) {
                        return;
                    }
                    if (MRAIDView.this.state == 1 || MRAIDView.this.state == 2) {
                        MRAIDView.this.closeFromExpanded();
                    } else if (MRAIDView.this.state == 3) {
                        MRAIDView.this.closeFromResized();
                    }
                }
            });
        }
    }

    private void createCalendarEvent(String str) {
        MRAIDLog.d("MRAIDView-JS callback", "createCalendarEvent " + str);
        if (this.nativeFeatureListener != null) {
            this.nativeFeatureListener.mraidNativeFeatureCreateCalendarEvent(str);
        }
    }

    @TargetApi(11)
    private void expand(String str, final Activity activity) {
        MRAIDLog.d("MRAIDView-JS callback", "expand " + (str != null ? str : "(1-part)"));
        if (this.isInterstitial && this.state != 0) {
            return;
        }
        if (!this.isInterstitial && this.state != 1 && this.state != 3) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            if (this.isInterstitial || this.state == 1) {
                if (this.webView.getParent() != null) {
                    ((ViewGroup) this.webView.getParent()).removeView(this.webView);
                } else {
                    removeView(this.webView);
                }
            } else if (this.state == 3) {
                removeResizeView();
            }
            expandHelper(this.webView, activity);
            return;
        }
        try {
            String decode = URLDecoder.decode(str, "UTF-8");
            if (!(decode.startsWith("http://") || decode.startsWith("https://"))) {
                decode = this.baseUrl + decode;
            }
            new Thread(new Runnable() {
                public void run() {
                    final Object access$1700 = MRAIDView.this.getStringFromUrl(decode);
                    if (TextUtils.isEmpty(access$1700)) {
                        MRAIDLog.e("Could not load part 2 expanded content for URL: " + decode);
                    } else {
                        ((Activity) MRAIDView.this.context).runOnUiThread(new Runnable() {
                            public void run() {
                                if (MRAIDView.this.state == 3) {
                                    MRAIDView.this.removeResizeView();
                                    MRAIDView.this.addView(MRAIDView.this.webView);
                                }
                                MRAIDView.this.webView.setWebChromeClient(null);
                                MRAIDView.this.webView.setWebViewClient(null);
                                MRAIDView.this.webViewPart2 = MRAIDView.this.createWebView();
                                MRAIDView.this.injectMraidJs(MRAIDView.this.webViewPart2);
                                MRAIDView.this.webViewPart2.loadDataWithBaseURL(MRAIDView.this.baseUrl, access$1700, "text/html", "UTF-8", null);
                                MRAIDView.this.currentWebView = MRAIDView.this.webViewPart2;
                                MRAIDView.this.isExpandingPart2 = true;
                                MRAIDView.this.expandHelper(MRAIDView.this.currentWebView, activity);
                            }
                        });
                    }
                }
            }, "2-part-content").start();
        } catch (UnsupportedEncodingException e) {
        }
    }

    private void open(String str) {
        open(str, null);
    }

    private void open(String str, WebView webView) {
        if (this.isTouched) {
            MRAIDLog.d("MRAIDView-JS callback", "open " + str);
            if (this.nativeFeatureListener == null) {
                return;
            }
            if (str.startsWith("sms")) {
                this.nativeFeatureListener.mraidNativeFeatureSendSms(str);
                return;
            } else if (str.startsWith("tel")) {
                this.nativeFeatureListener.mraidNativeFeatureCallTel(str);
                return;
            } else {
                this.nativeFeatureListener.mraidNativeFeatureOpenBrowser(str, webView);
                return;
            }
        }
        MRAIDLog.d(TAG, "mraid view not touched");
    }

    private void playVideo(String str) {
        try {
            String decode = URLDecoder.decode(str, "UTF-8");
            MRAIDLog.d("MRAIDView-JS callback", "playVideo " + decode);
            if (this.nativeFeatureListener != null) {
                this.nativeFeatureListener.mraidNativeFeaturePlayVideo(decode);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void resize() {
        MRAIDLog.d("MRAIDView-JS callback", "resize");
        if (this.listener != null) {
            if (this.listener.mraidViewResize(this, this.resizeProperties.width, this.resizeProperties.height, this.resizeProperties.offsetX, this.resizeProperties.offsetY)) {
                this.state = 3;
                if (this.resizedView == null) {
                    this.resizedView = new RelativeLayout(this.context);
                    removeAllViews();
                    this.resizedView.addView(this.webView);
                    addCloseRegion(this.resizedView);
                    ((FrameLayout) getRootView().findViewById(16908290)).addView(this.resizedView);
                }
                setCloseRegionPosition(this.resizedView);
                setResizedViewSize();
                setResizedViewPosition();
                this.handler.post(new Runnable() {
                    public void run() {
                        MRAIDView.this.fireStateChangeEvent();
                    }
                });
            }
        }
    }

    private void setOrientationProperties(Map<String, String> map) {
        boolean parseBoolean = Boolean.parseBoolean((String) map.get("allowOrientationChange"));
        String str = (String) map.get("forceOrientation");
        MRAIDLog.d("MRAIDView-JS callback", "setOrientationProperties " + parseBoolean + " " + str);
        if (this.orientationProperties.allowOrientationChange != parseBoolean || this.orientationProperties.forceOrientation != MRAIDOrientationProperties.forceOrientationFromString(str)) {
            this.orientationProperties.allowOrientationChange = parseBoolean;
            this.orientationProperties.forceOrientation = MRAIDOrientationProperties.forceOrientationFromString(str);
            if (this.isInterstitial || this.state == 2) {
                applyOrientationProperties();
            }
        }
    }

    private void setResizeProperties(Map<String, String> map) {
        int parseInt = Integer.parseInt((String) map.get("width"));
        int parseInt2 = Integer.parseInt((String) map.get("height"));
        int parseInt3 = Integer.parseInt((String) map.get("offsetX"));
        int parseInt4 = Integer.parseInt((String) map.get("offsetY"));
        String str = (String) map.get("customClosePosition");
        boolean parseBoolean = Boolean.parseBoolean((String) map.get("allowOffscreen"));
        MRAIDLog.d("MRAIDView-JS callback", "setResizeProperties " + parseInt + " " + parseInt2 + " " + parseInt3 + " " + parseInt4 + " " + str + " " + parseBoolean);
        this.resizeProperties.width = parseInt;
        this.resizeProperties.height = parseInt2;
        this.resizeProperties.offsetX = parseInt3;
        this.resizeProperties.offsetY = parseInt4;
        this.resizeProperties.customClosePosition = MRAIDResizeProperties.customClosePositionFromString(str);
        this.resizeProperties.allowOffscreen = parseBoolean;
    }

    private void storePicture(String str) {
        try {
            String decode = URLDecoder.decode(str, "UTF-8");
            MRAIDLog.d("MRAIDView-JS callback", "storePicture " + decode);
            if (this.nativeFeatureListener != null) {
                this.nativeFeatureListener.mraidNativeFeatureStorePicture(decode);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void useCustomClose(String str) {
        MRAIDLog.d("MRAIDView-JS callback", "useCustomClose " + str);
        boolean parseBoolean = Boolean.parseBoolean(str);
        if (this.useCustomClose != parseBoolean) {
            this.useCustomClose = parseBoolean;
            if (parseBoolean) {
                removeDefaultCloseButton();
            } else {
                showDefaultCloseButton();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getStringFromUrl(java.lang.String r10) {
        /*
        r9 = this;
        r2 = 0;
        r0 = "file:///";
        r0 = r10.startsWith(r0);
        if (r0 == 0) goto L_0x000e;
    L_0x0009:
        r0 = r9.getStringFromFileUrl(r10);
    L_0x000d:
        return r0;
    L_0x000e:
        r0 = new java.net.URL;	 Catch:{ IOException -> 0x00dc }
        r0.<init>(r10);	 Catch:{ IOException -> 0x00dc }
        r0 = r0.openConnection();	 Catch:{ IOException -> 0x00dc }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x00dc }
        r1 = r0.getResponseCode();	 Catch:{ IOException -> 0x00dc }
        r3 = "MRAIDView";
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00dc }
        r4.<init>();	 Catch:{ IOException -> 0x00dc }
        r5 = "response code ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x00dc }
        r4 = r4.append(r1);	 Catch:{ IOException -> 0x00dc }
        r4 = r4.toString();	 Catch:{ IOException -> 0x00dc }
        org.nexage.sourcekit.mraid.internal.MRAIDLog.d(r3, r4);	 Catch:{ IOException -> 0x00dc }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r3) goto L_0x00eb;
    L_0x0039:
        r1 = "MRAIDView";
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00dc }
        r3.<init>();	 Catch:{ IOException -> 0x00dc }
        r4 = "getContentLength ";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x00dc }
        r4 = r0.getContentLength();	 Catch:{ IOException -> 0x00dc }
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x00dc }
        r3 = r3.toString();	 Catch:{ IOException -> 0x00dc }
        org.nexage.sourcekit.mraid.internal.MRAIDLog.d(r1, r3);	 Catch:{ IOException -> 0x00dc }
        r1 = r0.getInputStream();	 Catch:{ IOException -> 0x00dc }
        r3 = 1500; // 0x5dc float:2.102E-42 double:7.41E-321;
        r3 = new byte[r3];	 Catch:{ IOException -> 0x0073, all -> 0x00d9 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0073, all -> 0x00d9 }
        r4.<init>();	 Catch:{ IOException -> 0x0073, all -> 0x00d9 }
    L_0x0062:
        r5 = r1.read(r3);	 Catch:{ IOException -> 0x0073, all -> 0x00d9 }
        r6 = -1;
        if (r5 == r6) goto L_0x009e;
    L_0x0069:
        r6 = new java.lang.String;	 Catch:{ IOException -> 0x0073, all -> 0x00d9 }
        r7 = 0;
        r6.<init>(r3, r7, r5);	 Catch:{ IOException -> 0x0073, all -> 0x00d9 }
        r4.append(r6);	 Catch:{ IOException -> 0x0073, all -> 0x00d9 }
        goto L_0x0062;
    L_0x0073:
        r0 = move-exception;
        r8 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r8;
    L_0x0078:
        r3 = "MRAIDView";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d0 }
        r4.<init>();	 Catch:{ all -> 0x00d0 }
        r5 = "getStringFromUrl failed ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00d0 }
        r1 = r1.getLocalizedMessage();	 Catch:{ all -> 0x00d0 }
        r1 = r4.append(r1);	 Catch:{ all -> 0x00d0 }
        r1 = r1.toString();	 Catch:{ all -> 0x00d0 }
        org.nexage.sourcekit.mraid.internal.MRAIDLog.e(r3, r1);	 Catch:{ all -> 0x00d0 }
        if (r2 == 0) goto L_0x000d;
    L_0x0096:
        r2.close();	 Catch:{ IOException -> 0x009b }
        goto L_0x000d;
    L_0x009b:
        r1 = move-exception;
        goto L_0x000d;
    L_0x009e:
        r2 = r4.toString();	 Catch:{ IOException -> 0x0073, all -> 0x00d9 }
        r3 = "MRAIDView";
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00e0, all -> 0x00d9 }
        r4.<init>();	 Catch:{ IOException -> 0x00e0, all -> 0x00d9 }
        r5 = "getStringFromUrl ok, length=";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x00e0, all -> 0x00d9 }
        r5 = r2.length();	 Catch:{ IOException -> 0x00e0, all -> 0x00d9 }
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x00e0, all -> 0x00d9 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x00e0, all -> 0x00d9 }
        org.nexage.sourcekit.mraid.internal.MRAIDLog.d(r3, r4);	 Catch:{ IOException -> 0x00e0, all -> 0x00d9 }
        r8 = r1;
        r1 = r2;
        r2 = r8;
    L_0x00c1:
        r0.disconnect();	 Catch:{ IOException -> 0x00e6 }
        if (r2 == 0) goto L_0x00c9;
    L_0x00c6:
        r2.close();	 Catch:{ IOException -> 0x00cc }
    L_0x00c9:
        r0 = r1;
        goto L_0x000d;
    L_0x00cc:
        r0 = move-exception;
        r0 = r1;
        goto L_0x000d;
    L_0x00d0:
        r0 = move-exception;
    L_0x00d1:
        if (r2 == 0) goto L_0x00d6;
    L_0x00d3:
        r2.close();	 Catch:{ IOException -> 0x00d7 }
    L_0x00d6:
        throw r0;
    L_0x00d7:
        r1 = move-exception;
        goto L_0x00d6;
    L_0x00d9:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00d1;
    L_0x00dc:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        goto L_0x0078;
    L_0x00e0:
        r0 = move-exception;
        r8 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r8;
        goto L_0x0078;
    L_0x00e6:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x0078;
    L_0x00eb:
        r1 = r2;
        goto L_0x00c1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.nexage.sourcekit.mraid.MRAIDView.getStringFromUrl(java.lang.String):java.lang.String");
    }

    private String getStringFromFileUrl(String str) {
        StringBuilder stringBuilder = new StringBuilder("");
        String[] split = str.split("/");
        if (split[3].equals("android_asset")) {
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(this.context.getAssets().open(split[4])));
                String readLine = bufferedReader.readLine();
                stringBuilder.append(readLine);
                while (readLine != null) {
                    readLine = bufferedReader.readLine();
                    stringBuilder.append(readLine);
                }
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                }
            } catch (IOException e2) {
                MRAIDLog.e("Error fetching file: " + e2.getMessage());
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Exception e3) {
                }
            }
            return stringBuilder.toString();
        }
        MRAIDLog.e("Unknown location to fetch file content");
        return "";
    }

    protected void showAsInterstitial(Activity activity) {
        this.interstitialActivity = activity;
        expand(null, activity);
    }

    private void expandHelper(WebView webView, final Activity activity) {
        if (!this.isInterstitial) {
            this.state = 2;
        }
        applyOrientationProperties();
        forceFullScreen();
        this.expandedView = new RelativeLayout(this.context);
        this.expandedView.addView(webView);
        addCloseRegion(this.expandedView);
        setCloseRegionPosition(this.expandedView);
        if (this.rtbInfo != null) {
            View createReportButton = createReportButton();
            this.expandedView.addView(createReportButton);
            createReportButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (MRAIDView.this.isComplained) {
                        Appodeal.a("Ad was complained before");
                        return;
                    }
                    MRAIDView.this.expandedView.addView(MRAIDView.this.createInterstitialReportView(activity));
                }
            });
        }
        if (!this.preload) {
            webView.loadDataWithBaseURL(this.baseUrl, this.mData, "text/html", "UTF-8", null);
        }
        if (activity != null) {
            activity.addContentView(this.expandedView, new RelativeLayout.LayoutParams(-1, -1));
        } else {
            ((Activity) this.context).addContentView(this.expandedView, new RelativeLayout.LayoutParams(-1, -1));
        }
        this.isExpandingFromDefault = true;
        if (this.isInterstitial) {
            this.isLaidOut = true;
            this.state = 1;
            fireStateChangeEvent();
        }
    }

    private void setResizedViewSize() {
        MRAIDLog.d(TAG, "setResizedViewSize");
        int i = this.resizeProperties.width;
        int i2 = this.resizeProperties.height;
        Log.d(TAG, "setResizedViewSize " + i + "x" + i2);
        this.resizedView.setLayoutParams(new FrameLayout.LayoutParams((int) TypedValue.applyDimension(1, (float) i, this.displayMetrics), (int) TypedValue.applyDimension(1, (float) i2, this.displayMetrics)));
    }

    private void setResizedViewPosition() {
        MRAIDLog.d(TAG, "setResizedViewPosition");
        if (this.resizedView != null) {
            int i = this.resizeProperties.width;
            int i2 = this.resizeProperties.height;
            int i3 = this.resizeProperties.offsetX;
            int applyDimension = (int) TypedValue.applyDimension(1, (float) i, this.displayMetrics);
            i2 = (int) TypedValue.applyDimension(1, (float) i2, this.displayMetrics);
            i = (int) TypedValue.applyDimension(1, (float) i3, this.displayMetrics);
            i3 = (int) TypedValue.applyDimension(1, (float) this.resizeProperties.offsetY, this.displayMetrics);
            int i4 = this.defaultPosition.left + i;
            i3 += this.defaultPosition.top;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.resizedView.getLayoutParams();
            layoutParams.leftMargin = i4;
            layoutParams.topMargin = i3;
            this.resizedView.setLayoutParams(layoutParams);
            if (i4 != this.currentPosition.left || i3 != this.currentPosition.top || applyDimension != this.currentPosition.width() || i2 != this.currentPosition.height()) {
                this.currentPosition.left = i4;
                this.currentPosition.top = i3;
                this.currentPosition.right = i4 + applyDimension;
                this.currentPosition.bottom = i2 + i3;
                setCurrentPosition();
            }
        }
    }

    private void closeFromExpanded() {
        if (this.state == 1 && this.isInterstitial) {
            this.state = 4;
            clearView();
            this.handler.post(new Runnable() {
                public void run() {
                    MRAIDView.this.fireStateChangeEvent();
                    if (MRAIDView.this.listener != null) {
                        MRAIDView.this.listener.mraidViewClose(MRAIDView.this);
                    }
                }
            });
        } else if (this.state == 2 || this.state == 3) {
            this.state = 1;
        }
        this.isClosing = true;
        this.expandedView.removeAllViews();
        ((FrameLayout) ((Activity) this.context).findViewById(16908290)).removeView(this.expandedView);
        this.expandedView = null;
        this.closeRegion = null;
        this.handler.post(new Runnable() {
            public void run() {
                MRAIDView.this.restoreOriginalOrientation();
                MRAIDView.this.restoreOriginalScreenState();
            }
        });
        if (this.webViewPart2 == null) {
            addView(this.webView);
        } else {
            this.webViewPart2.setWebChromeClient(null);
            this.webViewPart2.setWebViewClient(null);
            WebView webView = this.webViewPart2;
            this.webViewPart2 = null;
            webView.destroy();
            this.webView.setWebChromeClient(this.mraidWebChromeClient);
            this.webView.setWebViewClient(this.mraidWebViewClient);
            this.currentWebView = this.webView;
        }
        this.handler.post(new Runnable() {
            public void run() {
                MRAIDView.this.fireStateChangeEvent();
                if (MRAIDView.this.listener != null) {
                    MRAIDView.this.listener.mraidViewClose(MRAIDView.this);
                }
            }
        });
    }

    private void closeFromResized() {
        this.state = 1;
        this.isClosing = true;
        removeResizeView();
        addView(this.webView);
        this.handler.post(new Runnable() {
            public void run() {
                MRAIDView.this.fireStateChangeEvent();
                if (MRAIDView.this.listener != null) {
                    MRAIDView.this.listener.mraidViewClose(MRAIDView.this);
                }
            }
        });
    }

    private void removeResizeView() {
        this.resizedView.removeAllViews();
        ((FrameLayout) ((Activity) this.context).findViewById(16908290)).removeView(this.resizedView);
        this.resizedView = null;
        this.closeRegion = null;
    }

    @TargetApi(11)
    private void forceFullScreen() {
        boolean z;
        boolean z2 = true;
        MRAIDLog.d(TAG, "forceFullScreen");
        Activity activity = (Activity) this.context;
        int i = activity.getWindow().getAttributes().flags;
        this.isFullScreen = (i & 1024) != 0;
        if ((i & 2048) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.isForceNotFullScreen = z;
        this.origTitleBarVisibility = -9;
        if (VERSION.SDK_INT >= 11) {
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                this.isActionBarShowing = actionBar.isShowing();
                actionBar.hide();
                z = true;
                if (!z) {
                    this.titleBar = null;
                    try {
                        this.titleBar = (View) activity.findViewById(16908310).getParent();
                    } catch (NullPointerException e) {
                    }
                    if (this.titleBar != null) {
                        this.origTitleBarVisibility = this.titleBar.getVisibility();
                        this.titleBar.setVisibility(8);
                    }
                }
                MRAIDLog.d(TAG, "isFullScreen " + this.isFullScreen);
                MRAIDLog.d(TAG, "isForceNotFullScreen " + this.isForceNotFullScreen);
                MRAIDLog.d(TAG, "isActionBarShowing " + this.isActionBarShowing);
                MRAIDLog.d(TAG, "origTitleBarVisibility " + getVisibilityString(this.origTitleBarVisibility));
                if (this.isFullScreen) {
                    z2 = false;
                }
                this.isForcingFullScreen = z2;
            }
        }
        z = false;
        if (z) {
            this.titleBar = null;
            this.titleBar = (View) activity.findViewById(16908310).getParent();
            if (this.titleBar != null) {
                this.origTitleBarVisibility = this.titleBar.getVisibility();
                this.titleBar.setVisibility(8);
            }
        }
        MRAIDLog.d(TAG, "isFullScreen " + this.isFullScreen);
        MRAIDLog.d(TAG, "isForceNotFullScreen " + this.isForceNotFullScreen);
        MRAIDLog.d(TAG, "isActionBarShowing " + this.isActionBarShowing);
        MRAIDLog.d(TAG, "origTitleBarVisibility " + getVisibilityString(this.origTitleBarVisibility));
        if (this.isFullScreen) {
            z2 = false;
        }
        this.isForcingFullScreen = z2;
    }

    @TargetApi(11)
    private void restoreOriginalScreenState() {
        Activity activity = (Activity) this.context;
        if (!this.isFullScreen) {
            activity.getWindow().clearFlags(1024);
        }
        if (this.isForceNotFullScreen) {
            activity.getWindow().addFlags(2048);
        }
        if (VERSION.SDK_INT >= 11 && this.isActionBarShowing) {
            activity.getActionBar().show();
        } else if (this.titleBar != null) {
            this.titleBar.setVisibility(this.origTitleBarVisibility);
        }
    }

    private void addCloseRegion(View view) {
        this.closeRegion = new VastCountdown(this.context);
        this.closeRegion.setBackgroundColor(0);
        this.closeRegion.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MRAIDView.this.close();
            }
        });
        if (view == this.expandedView && !this.useCustomClose) {
            showDefaultCloseButton();
        }
        ((ViewGroup) view).addView(this.closeRegion);
    }

    private void showDefaultCloseButton() {
        if (this.closeRegion != null) {
            this.isCloseClickable = false;
            final int i = (this.closeTime == 0 ? 3 : this.closeTime) * 1000;
            this.closeTimerPosition = 0;
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                public void run() {
                    if (MRAIDView.this.closeRegion != null) {
                        MRAIDView.this.closeTimerPosition = MRAIDView.this.closeTimerPosition + 40;
                        MRAIDView.this.closeRegion.changePercentage((MRAIDView.this.closeTimerPosition * 100) / i, (int) Math.ceil(((double) (i - MRAIDView.this.closeTimerPosition)) / 1000.0d));
                        if (MRAIDView.this.closeTimerPosition >= i) {
                            MRAIDView.this.isCloseClickable = true;
                        } else {
                            handler.postDelayed(this, 40);
                        }
                    }
                }
            }, 40);
        }
    }

    private void removeDefaultCloseButton() {
        if (this.closeRegion != null) {
            this.closeRegion.setVisibility(4);
            this.closeRegion.setClickable(false);
            this.closeTimerPosition = 100000;
        }
    }

    private void setCloseRegionPosition(View view) {
        int applyDimension = (int) TypedValue.applyDimension(1, 50.0f, this.displayMetrics);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(applyDimension, applyDimension);
        if (view != this.expandedView) {
            if (view == this.resizedView) {
                switch (this.resizeProperties.customClosePosition) {
                    case 0:
                    case 4:
                        layoutParams.addRule(9);
                        break;
                    case 1:
                    case 3:
                    case 5:
                        layoutParams.addRule(14);
                        break;
                    case 2:
                    case 6:
                        layoutParams.addRule(11);
                        break;
                }
                switch (this.resizeProperties.customClosePosition) {
                    case 0:
                    case 1:
                    case 2:
                        layoutParams.addRule(10);
                        break;
                    case 3:
                        layoutParams.addRule(15);
                        break;
                    case 4:
                    case 5:
                    case 6:
                        layoutParams.addRule(12);
                        break;
                    default:
                        break;
                }
            }
        }
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        this.closeRegion.setLayoutParams(layoutParams);
    }

    @SuppressLint({"NewApi"})
    private void injectMraidJs(WebView webView) {
        if (TextUtils.isEmpty(this.mraidJs)) {
            this.mraidJs = new String(Base64.decode(Assets.mraidJS, 0));
        }
        MRAIDLog.d(TAG, "injectMraidJs ok " + this.mraidJs.length());
        if (VERSION.SDK_INT >= 19) {
            try {
                if (an.s(getContext()) <= 275609860) {
                    webView.loadData("<html></html>", "text/html", "UTF-8");
                }
                webView.evaluateJavascript(this.mraidJs, new ValueCallback<String>() {
                    public void onReceiveValue(String str) {
                    }
                });
                return;
            } catch (Throwable e) {
                Appodeal.a(e);
                webView.loadUrl("javascript:" + this.mraidJs);
                return;
            }
        }
        webView.loadUrl("javascript:" + this.mraidJs);
    }

    @SuppressLint({"NewApi"})
    private void injectJavaScript(String str) {
        injectJavaScript(this.currentWebView, str);
    }

    @SuppressLint({"NewApi"})
    private void injectJavaScript(WebView webView, String str) {
        if (!TextUtils.isEmpty(str) && webView != null) {
            if (VERSION.SDK_INT >= 19) {
                try {
                    MRAIDLog.d(TAG, "evaluating js: " + str);
                    webView.evaluateJavascript(str, new ValueCallback<String>() {
                        public void onReceiveValue(String str) {
                        }
                    });
                    return;
                } catch (Throwable e) {
                    Appodeal.a(e);
                    MRAIDLog.d(TAG, "loading url: " + str);
                    webView.loadUrl("javascript:" + str);
                    return;
                }
            }
            MRAIDLog.d(TAG, "loading url: " + str);
            webView.loadUrl("javascript:" + str);
        }
    }

    private void fireReadyEvent() {
        MRAIDLog.d(TAG, "fireReadyEvent");
        injectJavaScript("mraid.fireReadyEvent();");
    }

    @SuppressLint({"DefaultLocale"})
    private void fireStateChangeEvent() {
        MRAIDLog.d(TAG, "fireStateChangeEvent");
        injectJavaScript("mraid.fireStateChangeEvent('" + new String[]{"loading", Branch.REFERRAL_BUCKET_DEFAULT, "expanded", "resized", "hidden"}[this.state] + "');");
    }

    private void fireViewableChangeEvent() {
        MRAIDLog.d(TAG, "fireViewableChangeEvent");
        injectJavaScript("mraid.fireViewableChangeEvent(" + this.isViewable + ");");
    }

    private int px2dip(int i) {
        return (i * 160) / this.displayMetrics.densityDpi;
    }

    private void setCurrentPosition() {
        int i = this.currentPosition.left;
        int i2 = this.currentPosition.top;
        int width = this.currentPosition.width();
        int height = this.currentPosition.height();
        MRAIDLog.d(TAG, "setCurrentPosition [" + i + "," + i2 + "] (" + width + "x" + height + ")");
        injectJavaScript("mraid.setCurrentPosition(" + px2dip(i) + "," + px2dip(i2) + "," + px2dip(width) + "," + px2dip(height) + ");");
    }

    private void setDefaultPosition() {
        int i = this.defaultPosition.left;
        int i2 = this.defaultPosition.top;
        int width = this.defaultPosition.width();
        int height = this.defaultPosition.height();
        MRAIDLog.d(TAG, "setDefaultPosition [" + i + "," + i2 + "] (" + width + "x" + height + ")");
        injectJavaScript("mraid.setDefaultPosition(" + px2dip(i) + "," + px2dip(i2) + "," + px2dip(width) + "," + px2dip(height) + ");");
    }

    private void setMaxSize() {
        MRAIDLog.d(TAG, "setMaxSize");
        int i = this.maxSize.width;
        int i2 = this.maxSize.height;
        MRAIDLog.d(TAG, "setMaxSize " + i + "x" + i2);
        injectJavaScript("mraid.setMaxSize(" + px2dip(i) + "," + px2dip(i2) + ");");
    }

    private void setScreenSize() {
        MRAIDLog.d(TAG, "setScreenSize");
        int i = this.screenSize.width;
        int i2 = this.screenSize.height;
        MRAIDLog.d(TAG, "setScreenSize " + i + "x" + i2);
        injectJavaScript("mraid.setScreenSize(" + px2dip(i) + "," + px2dip(i2) + ");");
    }

    private void setSupportedServices() {
        MRAIDLog.d(TAG, "setSupportedServices");
        injectJavaScript("mraid.setSupports(mraid.SUPPORTED_FEATURES.CALENDAR, " + this.nativeFeatureManager.isCalendarSupported() + ");");
        injectJavaScript("mraid.setSupports(mraid.SUPPORTED_FEATURES.INLINEVIDEO, " + this.nativeFeatureManager.isInlineVideoSupported() + ");");
        injectJavaScript("mraid.setSupports(mraid.SUPPORTED_FEATURES.SMS, " + this.nativeFeatureManager.isSmsSupported() + ");");
        injectJavaScript("mraid.setSupports(mraid.SUPPORTED_FEATURES.STOREPICTURE, " + this.nativeFeatureManager.isStorePictureSupported() + ");");
        injectJavaScript("mraid.setSupports(mraid.SUPPORTED_FEATURES.TEL, " + this.nativeFeatureManager.isTelSupported() + ");");
    }

    @TargetApi(11)
    private void pauseWebView(WebView webView) {
        MRAIDLog.d(TAG, "pauseWebView " + webView.toString());
        try {
            if (VERSION.SDK_INT >= 11) {
                webView.onPause();
            } else {
                Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(webView, (Object[]) null);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    @TargetApi(11)
    private void resumeWebView(WebView webView) {
        MRAIDLog.d(TAG, "resumeWebView " + webView.toString());
        try {
            if (VERSION.SDK_INT >= 11) {
                webView.onResume();
            } else {
                Class.forName("android.webkit.WebView").getMethod("onResume", (Class[]) null).invoke(webView, (Object[]) null);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        MRAIDLog.d(TAG, "onConfigurationChanged " + (configuration.orientation == 1 ? DeviceInfo.ORIENTATION_PORTRAIT : DeviceInfo.ORIENTATION_LANDSCAPE));
        ((Activity) this.context).getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
    }

    protected void onAttachedToWindow() {
        MRAIDLog.d(TAG, "onAttachedToWindow");
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        MRAIDLog.d(TAG, "onDetachedFromWindow");
        super.onDetachedFromWindow();
    }

    protected void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        MRAIDLog.d(TAG, "onVisibilityChanged " + getVisibilityString(i));
        setViewable(i);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        int visibility = getVisibility();
        MRAIDLog.d(TAG, "onWindowVisibilityChanged " + getVisibilityString(i) + " (actual " + getVisibilityString(visibility) + ")");
        setViewable(visibility);
    }

    private void setViewable(int i) {
        boolean z = i == 0;
        if (z != this.isViewable) {
            this.isViewable = z;
            if (this.isPageFinished && this.isLaidOut) {
                fireViewableChangeEvent();
            }
        }
    }

    @SuppressLint({"DrawAllocation"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        MRAIDLog.w(TAG, "onLayout (" + this.state + ") " + z + " " + i + " " + i2 + " " + i3 + " " + i4);
        if (this.isForcingFullScreen) {
            MRAIDLog.d(TAG, "onLayout ignored");
            return;
        }
        if (this.state == 2 || this.state == 3) {
            calculateScreenSize();
            calculateMaxSize();
        }
        if (this.isClosing) {
            this.isClosing = false;
            this.currentPosition = new Rect(this.defaultPosition);
            setCurrentPosition();
        } else {
            calculatePosition(false);
        }
        if (this.state == 3 && z) {
            this.handler.post(new Runnable() {
                public void run() {
                    MRAIDView.this.setResizedViewPosition();
                }
            });
        }
        this.isLaidOut = true;
        if (this.state == 0 && this.isPageFinished && !this.isInterstitial) {
            this.state = 1;
            fireStateChangeEvent();
            fireReadyEvent();
            if (this.isViewable) {
                fireViewableChangeEvent();
            }
        }
    }

    private void onLayoutWebView(WebView webView, boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = webView == this.currentWebView;
        MRAIDLog.w(TAG, "onLayoutWebView " + (webView == this.webView ? "1 " : "2 ") + z2 + " (" + this.state + ") " + z + " " + i + " " + i2 + " " + i3 + " " + i4);
        if (!z2) {
            MRAIDLog.d(TAG, "onLayoutWebView ignored, not current");
        } else if (this.isForcingFullScreen) {
            MRAIDLog.d(TAG, "onLayoutWebView ignored, isForcingFullScreen");
            this.isForcingFullScreen = false;
        } else {
            if (this.state == 0 || this.state == 1) {
                calculateScreenSize();
                calculateMaxSize();
            }
            if (!this.isClosing) {
                calculatePosition(true);
                if (this.isInterstitial && !this.defaultPosition.equals(this.currentPosition)) {
                    this.defaultPosition = new Rect(this.currentPosition);
                    setDefaultPosition();
                }
            }
            if (this.isExpandingFromDefault) {
                this.isExpandingFromDefault = false;
                if (this.isInterstitial) {
                    this.state = 1;
                    this.isLaidOut = true;
                }
                if (!this.isExpandingPart2) {
                    MRAIDLog.d(TAG, "calling fireStateChangeEvent 1");
                    fireStateChangeEvent();
                }
                if (this.isInterstitial) {
                    fireReadyEvent();
                    if (this.isViewable) {
                        fireViewableChangeEvent();
                    }
                }
                if (this.listener != null) {
                    this.listener.mraidViewExpand(this);
                }
            }
        }
    }

    private void calculateScreenSize() {
        Object obj = 1;
        if (getResources().getConfiguration().orientation != 1) {
            obj = null;
        }
        MRAIDLog.d(TAG, "calculateScreenSize orientation " + (obj != null ? DeviceInfo.ORIENTATION_PORTRAIT : DeviceInfo.ORIENTATION_LANDSCAPE));
        int i = this.displayMetrics.widthPixels;
        int i2 = this.displayMetrics.heightPixels;
        MRAIDLog.d(TAG, "calculateScreenSize screen size " + i + "x" + i2);
        if (i != this.screenSize.width || i2 != this.screenSize.height) {
            this.screenSize.width = i;
            this.screenSize.height = i2;
            if (this.isPageFinished) {
                setScreenSize();
            }
        }
    }

    private void calculateMaxSize() {
        int i;
        Rect rect = new Rect();
        Window window = ((Activity) this.context).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        MRAIDLog.d(TAG, "calculateMaxSize frame [" + rect.left + "," + rect.top + "][" + rect.right + "," + rect.bottom + "] (" + rect.width() + "x" + rect.height() + ")");
        int i2 = rect.top;
        View findViewById = window.findViewById(16908290);
        this.contentViewTop = 0;
        if (findViewById != null) {
            this.contentViewTop = findViewById.getTop();
            i = this.contentViewTop - i2;
            MRAIDLog.d(TAG, "calculateMaxSize statusHeight " + i2);
            MRAIDLog.d(TAG, "calculateMaxSize titleHeight " + i);
            MRAIDLog.d(TAG, "calculateMaxSize contentViewTop " + this.contentViewTop);
        }
        i = rect.width();
        int i3 = this.screenSize.height - this.contentViewTop;
        MRAIDLog.d(TAG, "calculateMaxSize max size " + i + "x" + i3);
        if (i != this.maxSize.width || i3 != this.maxSize.height) {
            this.maxSize.width = i;
            this.maxSize.height = i3;
            if (this.isPageFinished) {
                setMaxSize();
            }
        }
    }

    private void calculatePosition(boolean z) {
        int[] iArr = new int[2];
        View view = z ? this.currentWebView : this;
        String str = z ? "current" : Branch.REFERRAL_BUCKET_DEFAULT;
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        MRAIDLog.d(TAG, "calculatePosition " + str + " locationOnScreen [" + i + "," + i2 + "]");
        MRAIDLog.d(TAG, "calculatePosition " + str + " contentViewTop " + this.contentViewTop);
        i2 -= this.contentViewTop;
        int width = view.getWidth();
        int height = view.getHeight();
        MRAIDLog.d(TAG, "calculatePosition " + str + " position [" + i + "," + i2 + "] (" + width + "x" + height + ")");
        Rect rect = z ? this.currentPosition : this.defaultPosition;
        if (i != rect.left || i2 != rect.top || width != rect.width() || height != rect.height()) {
            if (z) {
                this.currentPosition = new Rect(i, i2, width + i, height + i2);
            } else {
                this.defaultPosition = new Rect(i, i2, width + i, height + i2);
            }
            if (!this.isPageFinished) {
                return;
            }
            if (z) {
                setCurrentPosition();
            } else {
                setDefaultPosition();
            }
        }
    }

    private void applyOrientationProperties() {
        int i = 1;
        MRAIDLog.d(TAG, "applyOrientationProperties " + this.orientationProperties.allowOrientationChange + " " + this.orientationProperties.forceOrientationString());
        if (this.interstitialActivity != null) {
            int i2 = getResources().getConfiguration().orientation == 1 ? 1 : 0;
            MRAIDLog.d(TAG, "currentOrientation " + (i2 != 0 ? DeviceInfo.ORIENTATION_PORTRAIT : DeviceInfo.ORIENTATION_LANDSCAPE));
            if (this.orientationProperties.forceOrientation != 0) {
                if (this.orientationProperties.forceOrientation == 1) {
                    i = 0;
                } else if (this.orientationProperties.allowOrientationChange) {
                    i = -1;
                } else if (i2 == 0) {
                    i = 0;
                }
            }
            this.interstitialActivity.setRequestedOrientation(i);
        }
    }

    private void restoreOriginalOrientation() {
        MRAIDLog.d(TAG, "restoreOriginalOrientation");
        if (this.interstitialActivity != null && this.interstitialActivity.getRequestedOrientation() != this.originalRequestedOrientation) {
            this.interstitialActivity.setRequestedOrientation(this.originalRequestedOrientation);
        }
    }

    public void wasComplained() {
        this.isComplained = true;
    }

    public void show() {
        if (!this.preload && !this.isShown) {
            this.isShown = true;
            this.webView.loadDataWithBaseURL(this.baseUrl, this.mData, "text/html", "UTF-8", null);
        }
    }
}
