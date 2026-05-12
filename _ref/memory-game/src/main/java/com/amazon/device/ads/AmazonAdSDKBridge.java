package com.amazon.device.ads;

import com.amazon.device.ads.JavascriptInteractor.Executor;
import com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor;
import com.amazon.device.ads.SDKEvent.SDKEventType;
import java.util.Locale;
import org.json.JSONObject;

class AmazonAdSDKBridge implements AdSDKBridge {
    private static final String BRIDGE_NAME = "amazonObject";
    private static final String ERROR_EVENT_FORMAT = "amazonBridge.error('%s', '%s');";
    private static final String JAVASCRIPT = ("(function (window, console){\n    var version = '1.0',\n    debug = function(msg) {\n        console.log(\"Amazon Javascript log: \" + msg);\n    },\n    is_array = function (obj) {\n        return Object.prototype.toString.call(obj) === '[object Array]';\n    },\n    forEach = function (array, fn) {\n        var i;\n        for (i = 0; i < array.length; i++) {\n            if (i in array) {\n                fn.call(null, array[i], i);\n            }\n        }\n    },\n    listeners = [],\n    events = {\n        backButton: 'backButton'\n    },\n    invokeListeners = function(event, args) {\n        var eventListeners = listeners[event] || [];\n        // fire all the listeners\n        forEach(eventListeners, function(listener){\n            try {\n                listener.apply(null, args);\n            }catch(e){\n                debug(\"Error executing \" + event + \" listener\");\n                debug(e);\n            }\n        });\n    },\n    backButtonEvent = function() {\n        invokeListeners(\"backButton\");\n    };\n    window.amazonBridge = {\n        backButton : backButtonEvent\n    };\n    window.amazon = {\n        // Command Flow\n        addEventListener : function(event, listener){\n            var eventListeners = listeners[event] || [],\n            alreadyRegistered = false;\n            \n            //verify the event is one that will actually occur\n            if (!events.hasOwnProperty(event)){\n                return;\n            }\n            \n            //register first set of listeners for this event\n            if (!is_array(listeners[event])) {\n                listeners[event] = eventListeners;\n            }\n            \n            forEach(eventListeners, function(l){ \n                // Listener already registered, so no need to add it.\n                    if (listener === l){\n                        alreadyRegistered = true;\n                    }\n                }\n            );\n            if (!alreadyRegistered){\n                listeners[event].push(listener);\n            }\n        },\n        removeEventListener : function(event, listener){\n            if (listeners.hasOwnProperty(event)) {\n                var eventListeners = listeners[event];\n                if (eventListeners) {\n                    var idx = eventListeners.indexOf(listener);\n                    if (idx !== -1) {\n                        eventListeners.splice(idx, 1);\n                    }\n                }\n            }\n        },\n        enableCloseButton: function(enable){\n            amazonObject." + JavascriptInteractor.getExecutorMethodName() + "(\"EnableCloseButton\", JSON.stringify({\"enable\": enable}));\n" + "        },\n" + "        overrideBackButton: function(override){\n" + "            amazonObject." + JavascriptInteractor.getExecutorMethodName() + "(\"OverrideBackButton\", JSON.stringify({\"override\": override}));\n" + "        },\n" + "        openInExternalBrowser: function(url){\n" + "            amazonObject." + JavascriptInteractor.getExecutorMethodName() + "(\"OpenInExternalBrowser\", JSON.stringify({\"url\": url}));\n" + "        },\n" + "        getSDKVersion: function(){\n" + "            var json = JSON.parse(amazonObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetSDKVersion\", null));\n" + "            return json.sdkVersion;\n" + "        },\n" + "        getVersion: function(){\n" + "            return version;\n" + "        },\n" + "    };\n" + "})(window, console);");
    private static final String LOGTAG = AmazonAdSDKBridge.class.getSimpleName();
    private final AdControlAccessor adControlAccessor;
    private final JavascriptInteractor javascriptInteractor;
    private final AmazonAdSDKEventListener listener;
    private final MobileAdsLogger logger;
    private final WebUtils2 webUtils;

    private static class AmazonAdSDKEventListener implements SDKEventListener {
        private final AmazonAdSDKBridge bridge;

        public AmazonAdSDKEventListener(AmazonAdSDKBridge amazonAdSDKBridge) {
            this.bridge = amazonAdSDKBridge;
        }

        public void onSDKEvent(SDKEvent sDKEvent, AdControlAccessor adControlAccessor) {
            if (sDKEvent.getEventType().equals(SDKEventType.BACK_BUTTON_PRESSED)) {
                this.bridge.onBackButton();
            }
        }
    }

    private static class EnableCloseButtonJSIF extends JavascriptMethodExecutor {
        private static final String name = "EnableCloseButton";
        private final AmazonAdSDKBridge bridge;

        public EnableCloseButtonJSIF(AmazonAdSDKBridge amazonAdSDKBridge) {
            super(name);
            this.bridge = amazonAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.enableCloseButton(JSONUtils.getBooleanFromJSON(jSONObject, "enable", true));
            return null;
        }
    }

    private static class OpenInExternalBrowserJSIF extends JavascriptMethodExecutor {
        private static final String name = "OpenInExternalBrowser";
        private final AmazonAdSDKBridge bridge;

        public OpenInExternalBrowserJSIF(AmazonAdSDKBridge amazonAdSDKBridge) {
            super(name);
            this.bridge = amazonAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.openInExternalBrowser(JSONUtils.getStringFromJSON(jSONObject, "url", null));
            return null;
        }
    }

    private static class OverrideBackButtonJSIF extends JavascriptMethodExecutor {
        private static final String name = "OverrideBackButton";
        private final AmazonAdSDKBridge bridge;

        public OverrideBackButtonJSIF(AmazonAdSDKBridge amazonAdSDKBridge) {
            super(name);
            this.bridge = amazonAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.overrideBackButton(JSONUtils.getBooleanFromJSON(jSONObject, "override", false));
            return null;
        }
    }

    private static class SDKVersionJSIF extends JavascriptMethodExecutor {
        private static final String name = "GetSDKVersion";
        private final AmazonAdSDKBridge bridge;

        protected SDKVersionJSIF(AmazonAdSDKBridge amazonAdSDKBridge) {
            super(name);
            this.bridge = amazonAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            JSONUtils.put(jSONObject2, "sdkVersion", this.bridge.getSDKVersion());
            return jSONObject2;
        }
    }

    public AmazonAdSDKBridge(AdControlAccessor adControlAccessor, JavascriptInteractor javascriptInteractor) {
        this(adControlAccessor, javascriptInteractor, new WebUtils2(), new MobileAdsLoggerFactory());
    }

    AmazonAdSDKBridge(AdControlAccessor adControlAccessor, JavascriptInteractor javascriptInteractor, WebUtils2 webUtils2, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.listener = new AmazonAdSDKEventListener(this);
        this.adControlAccessor = adControlAccessor;
        this.javascriptInteractor = javascriptInteractor;
        this.webUtils = webUtils2;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.javascriptInteractor.addMethodExecutor(new EnableCloseButtonJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new OverrideBackButtonJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new OpenInExternalBrowserJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new SDKVersionJSIF(this));
    }

    public boolean hasNativeExecution() {
        return true;
    }

    public String getName() {
        return BRIDGE_NAME;
    }

    public String getJavascript() {
        return JAVASCRIPT;
    }

    public SDKEventListener getSDKEventListener() {
        return this.listener;
    }

    public Executor getJavascriptInteractorExecutor() {
        return this.javascriptInteractor.getExecutor();
    }

    private void fireErrorEvent(String str, String str2) {
        this.adControlAccessor.injectJavascript(String.format(Locale.US, ERROR_EVENT_FORMAT, new Object[]{str, str2}));
    }

    private void onBackButton() {
        this.adControlAccessor.injectJavascript("amazonBridge.backButton();");
    }

    private void enableCloseButton(boolean z) {
        if (!canHaveCloseButton()) {
            return;
        }
        if (z) {
            this.adControlAccessor.enableCloseButton(true);
        } else {
            this.adControlAccessor.removeCloseButton();
        }
    }

    private boolean canHaveCloseButton() {
        return this.adControlAccessor.isModal();
    }

    private void overrideBackButton(boolean z) {
        this.adControlAccessor.overrideBackButton(z);
    }

    private String getSDKVersion() {
        return Version.getRawSDKVersion();
    }

    private void openInExternalBrowser(String str) {
        if (this.adControlAccessor.isVisible()) {
            this.logger.d("Opening URL " + str);
            if (this.webUtils.isUrlValid(str)) {
                this.adControlAccessor.openUrl(str);
                return;
            }
            String str2 = "URL " + str + " is not a valid URL";
            this.logger.d(str2);
            fireErrorEvent(str2, "open");
            return;
        }
        fireErrorEvent("Unable to open a URL while the ad is not visible", "open");
    }
}
