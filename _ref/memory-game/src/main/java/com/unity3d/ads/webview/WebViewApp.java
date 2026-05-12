package com.unity3d.ads.webview;

import android.os.Build.VERSION;
import android.os.ConditionVariable;
import android.os.Looper;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.unity3d.ads.configuration.Configuration;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.properties.ClientProperties;
import com.unity3d.ads.properties.SdkProperties;
import com.unity3d.ads.webview.bridge.CallbackStatus;
import com.unity3d.ads.webview.bridge.Invocation;
import com.unity3d.ads.webview.bridge.NativeCallback;
import com.unity3d.ads.webview.bridge.WebViewBridge;
import com.yalantis.ucrop.util.FileUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;

public class WebViewApp extends WebViewClient {
    private static ConditionVariable _conditionVariable;
    private static WebViewApp _currentApp;
    private Configuration _configuration;
    private boolean _initialized;
    private HashMap<String, NativeCallback> _nativeCallbacks;
    private boolean _webAppLoaded;
    private WebView _webView;

    private class WebAppChromeClient extends WebChromeClient {
        private WebAppChromeClient() {
        }

        public void onConsoleMessage(String str, int i, String str2) {
            File file;
            try {
                file = new File(str2);
            } catch (Exception e) {
                DeviceLog.exception("Could not handle sourceId", e);
                file = null;
            }
            if (file != null) {
                str2 = file.getName();
            }
            if (VERSION.SDK_INT < 19) {
                DeviceLog.debug("JavaScript (sourceId=" + str2 + ", line=" + i + "): " + str);
            }
        }
    }

    private class WebAppClient extends WebViewClient {
        private WebAppClient() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            DeviceLog.debug("onPageFinished url: " + str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            DeviceLog.debug("Trying to load url: " + str);
            return false;
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            if (webView != null) {
                DeviceLog.error("WEBVIEW_ERROR: " + webView.toString());
            }
            if (webResourceRequest != null) {
                DeviceLog.error("WEBVIEW_ERROR: " + webResourceRequest.toString());
            }
            if (webResourceError != null) {
                DeviceLog.error("WEBVIEW_ERROR: " + webResourceError.toString());
            }
        }
    }

    private WebViewApp(Configuration configuration) {
        this._webAppLoaded = false;
        this._initialized = false;
        setConfiguration(configuration);
        WebViewBridge.setClassTable(getConfiguration().getWebAppApiClassList());
        this._webView = new WebView(ClientProperties.getApplicationContext());
        this._webView.setWebViewClient(new WebAppClient());
        this._webView.setWebChromeClient(new WebAppChromeClient());
    }

    public WebViewApp() {
        this._webAppLoaded = false;
        this._initialized = false;
    }

    public void setWebAppLoaded(boolean z) {
        this._webAppLoaded = z;
    }

    public boolean isWebAppLoaded() {
        return this._webAppLoaded;
    }

    public void setWebAppInitialized(boolean z) {
        this._initialized = z;
        _conditionVariable.open();
    }

    public boolean isWebAppInitialized() {
        return this._initialized;
    }

    public WebView getWebView() {
        return this._webView;
    }

    public void setWebView(WebView webView) {
        this._webView = webView;
    }

    public Configuration getConfiguration() {
        return this._configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this._configuration = configuration;
    }

    private void invokeJavascriptMethod(String str, String str2, JSONArray jSONArray) {
        String str3 = "javascript:window." + str + FileUtils.HIDDEN_PREFIX + str2 + "(" + jSONArray.toString() + ");";
        DeviceLog.debug("Invoking javascript: " + str3);
        getWebView().invokeJavascript(str3);
    }

    public boolean sendEvent(Enum enumR, Enum enumR2, Object... objArr) {
        if (isWebAppLoaded()) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(enumR.name());
            jSONArray.put(enumR2.name());
            for (Object put : objArr) {
                jSONArray.put(put);
            }
            try {
                invokeJavascriptMethod("nativebridge", "handleEvent", jSONArray);
                return true;
            } catch (Exception e) {
                DeviceLog.exception("Error while sending event to WebView", e);
                return false;
            }
        }
        DeviceLog.debug("sendEvent ignored because web app is not loaded");
        return false;
    }

    public boolean invokeMethod(String str, String str2, Method method, Object... objArr) {
        if (isWebAppLoaded()) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONArray.put(str2);
            if (method != null) {
                NativeCallback nativeCallback = new NativeCallback(method);
                addCallback(nativeCallback);
                jSONArray.put(nativeCallback.getId());
            } else {
                jSONArray.put(null);
            }
            if (objArr != null) {
                for (Object put : objArr) {
                    jSONArray.put(put);
                }
            }
            try {
                invokeJavascriptMethod("nativebridge", "handleInvocation", jSONArray);
                return true;
            } catch (Exception e) {
                DeviceLog.exception("Error invoking javascript method", e);
                return false;
            }
        }
        DeviceLog.debug("invokeMethod ignored because web app is not loaded");
        return false;
    }

    public boolean invokeCallback(Invocation invocation) {
        if (isWebAppLoaded()) {
            JSONArray jSONArray = new JSONArray();
            ArrayList responses = invocation.getResponses();
            if (!(responses == null || responses.isEmpty())) {
                Iterator it = responses.iterator();
                while (it.hasNext()) {
                    responses = (ArrayList) it.next();
                    CallbackStatus callbackStatus = (CallbackStatus) responses.get(0);
                    Enum enumR = (Enum) responses.get(1);
                    Object[] objArr = (Object[]) responses.get(2);
                    String str = (String) objArr[0];
                    Object[] copyOfRange = Arrays.copyOfRange(objArr, 1, objArr.length);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(str);
                    arrayList.add(callbackStatus.toString());
                    JSONArray jSONArray2 = new JSONArray();
                    if (enumR != null) {
                        jSONArray2.put(enumR.name());
                    }
                    for (Object put : copyOfRange) {
                        jSONArray2.put(put);
                    }
                    arrayList.add(jSONArray2);
                    JSONArray jSONArray3 = new JSONArray();
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        jSONArray3.put(it2.next());
                    }
                    jSONArray.put(jSONArray3);
                }
            }
            try {
                invokeJavascriptMethod("nativebridge", "handleCallback", jSONArray);
            } catch (Exception e) {
                DeviceLog.exception("Error while invoking batch response for WebView", e);
            }
            return true;
        }
        DeviceLog.debug("invokeBatchCallback ignored because web app is not loaded");
        return false;
    }

    public void addCallback(NativeCallback nativeCallback) {
        if (this._nativeCallbacks == null) {
            this._nativeCallbacks = new HashMap();
        }
        synchronized (this._nativeCallbacks) {
            this._nativeCallbacks.put(nativeCallback.getId(), nativeCallback);
        }
    }

    public void removeCallback(NativeCallback nativeCallback) {
        if (this._nativeCallbacks != null) {
            synchronized (this._nativeCallbacks) {
                this._nativeCallbacks.remove(nativeCallback.getId());
            }
        }
    }

    public NativeCallback getCallback(String str) {
        NativeCallback nativeCallback;
        synchronized (this._nativeCallbacks) {
            nativeCallback = (NativeCallback) this._nativeCallbacks.get(str);
        }
        return nativeCallback;
    }

    public static WebViewApp getCurrentApp() {
        return _currentApp;
    }

    public static void setCurrentApp(WebViewApp webViewApp) {
        _currentApp = webViewApp;
    }

    public static boolean create(final Configuration configuration) {
        DeviceLog.entered();
        if (Thread.currentThread().equals(Looper.getMainLooper().getThread())) {
            throw new IllegalThreadStateException("Cannot call create() from main thread!");
        }
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                String str;
                WebViewApp webViewApp = new WebViewApp(configuration);
                String str2 = "?platform=android";
                try {
                    if (configuration.getWebViewUrl() != null) {
                        str2 = str2 + "&origin=" + URLEncoder.encode(configuration.getWebViewUrl(), "UTF-8");
                    }
                } catch (Exception e) {
                    DeviceLog.exception("Unsupported charset when encoding origin url", e);
                }
                try {
                    if (configuration.getWebViewVersion() != null) {
                        str2 = str2 + "&version=" + URLEncoder.encode(configuration.getWebViewVersion(), "UTF-8");
                    }
                    str = str2;
                } catch (Exception e2) {
                    DeviceLog.exception("Unsupported charset when encoding webview version", e2);
                    str = str2;
                }
                webViewApp.getWebView().loadDataWithBaseURL("file://" + SdkProperties.getLocalWebViewFile() + str, configuration.getWebViewData(), "text/html", "UTF-8", null);
                WebViewApp.setCurrentApp(webViewApp);
            }
        });
        _conditionVariable = new ConditionVariable();
        return _conditionVariable.block(60000);
    }
}
