package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.amazon.device.ads.AdEvent.AdEventType;
import com.amazon.device.ads.InAppBrowser.InAppBrowserBuilder;
import com.amazon.device.ads.JavascriptInteractor.Executor;
import com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestFactory;
import com.amazon.device.ads.WebRequest.WebResponse;
import com.mopub.common.Constants;
import com.mopub.mraid.MraidNativeCommandHandler;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDNativeFeature;

class MRAIDAdSDKBridge implements AdSDKBridge {
    private static final int CLOSE_BUTTON_SIZE = 50;
    private static final String CONTENT_DESCRIPTION_RESIZED_VIEW = "resizedView";
    private static final String ERROR_EVENT_FORMAT = "mraidBridge.error('%s', '%s');";
    private static final String JAVASCRIPT = ("(function (window, console) {\n    var is_array = function (obj) {\n        return Object.prototype.toString.call(obj) === '[object Array]';\n    },\n    registerViewabilityInterest = function(){\n       mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"RegisterViewabilityInterest\", null);\n    },\n    deregisterViewabilityInterest = function(){\n       mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"DeregisterViewabilityInterest\", null);\n    },\n    forEach = function (array, fn) {\n        var i;\n        for (i = 0; i < array.length; i++) {\n            if (i in array) {\n                fn.call(null, array[i], i);\n            }\n        }\n    },\n    events = {\n            error: 'error',\n            ready: 'ready',\n            sizeChange: 'sizeChange',\n            stateChange: 'stateChange',\n            viewableChange: 'viewableChange'\n    },\n    states = [\"loading\",\"default\",\"expanded\",\"resized\",\"hidden\"],\n    placementTypes = [\"inline\", \"interstitial\"],\n    listeners = [],\n    version = '2.0',\n    currentState = \"loading\",\n    currentlyViewable = false,\n    supportedFeatures = null,\n    orientationProperties = {\"allowOrientationChange\":true,\"forceOrientation\":\"none\"},\n    // Error Event fires listeners\n    invokeListeners = function(event, args) {\n        var eventListeners = listeners[event] || [];\n        // fire all the listeners\n        forEach(eventListeners, function(listener){\n            try {\n                listener.apply(null, args);\n            }catch(e){\n                debug(\"Error executing \" + event + \" listener\");\n                debug(e);\n            }\n        });\n    },\n    debug = function(msg) {\n        console.log(\"MRAID log: \" + msg);\n    },\n    readyEvent = function() {\n        debug(\"MRAID ready\");\n        invokeListeners(\"ready\");\n    },\n    errorEvent = function(message, action) {\n        debug(\"error: \" + message + \" action: \" + action);\n        var args = [message, action];\n        invokeListeners(\"error\", args);\n    },\n    stateChangeEvent = function(state) {\n        debug(\"stateChange: \" + state);\n        var args = [state];\n        currentState = state;\n        invokeListeners(\"stateChange\", args);\n    },\n    viewableChangeEvent = function(viewable) {\n        if (viewable != currentlyViewable) {            debug(\"viewableChange: \" + viewable);\n            var args = [viewable];\n            invokeListeners(\"viewableChange\", args);\n            currentlyViewable = viewable;\n        }\n    }, \n    sizeChangeEvent = function(width, height) {\n        debug(\"sizeChange: \" + width + \"x\" + height);\n        var args = [width, height];\n        invokeListeners(\"sizeChange\", args);\n    };\n    window.mraidBridge = {\n            error : errorEvent,\n            ready : readyEvent,\n            stateChange : stateChangeEvent,\n            sizeChange : sizeChangeEvent,\n            viewableChange : viewableChangeEvent\n    };\n    // Define the mraid object\n    window.mraid = {\n            // Command Flow\n            addEventListener : function(event, listener){\n                var eventListeners = listeners[event] || [],\n                alreadyRegistered = false;\n                \n                //verify the event is one that will actually occur\n                if (!events.hasOwnProperty(event)){\n                    return;\n                }\n                \n                //register first set of listeners for this event\n                if (!is_array(listeners[event])) {\n                    listeners[event] = eventListeners;\n                }\n                \n                forEach(eventListeners, function(l){ \n                    // Listener already registered, so no need to add it.\n                        if (listener === l){\n                            alreadyRegistered = true;\n                        }\n                    }\n                );\n                if (!alreadyRegistered){\n                    debug('Registering Listener for ' + event + ': ' + listener)\n                    listeners[event].push(listener);\n                    if (event = 'viewableChange'){ \n                       registerViewabilityInterest();  \n                    } \n                }\n            },\n            removeEventListener : function(event, listener){\n                if (listeners.hasOwnProperty(event)) {\n                    var eventListeners = listeners[event];\n                    if (eventListeners) {\n                        var idx = eventListeners.indexOf(listener);\n                        if (idx !== -1) {\n                           eventListeners.splice(idx, 1);\n                           if (event = 'viewableChange'){ \n                               deregisterViewabilityInterest();  \n                           } \n                        }\n                    }\n                }\n            },\n            useCustomClose: function(bool){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"UseCustomClose\", JSON.stringify({useCustomClose: bool}));\n            },\n            // Support\n            supports: function(feature){\n                if (!supportedFeatures)\n                {\n                    supportedFeatures = JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Supports\", null));\n                }\n                return supportedFeatures[feature];\n            },\n            // Properties\n            getVersion: function(){\n                return version;\n            },\n            getState: function(){\n                return currentState;\n            },\n            getPlacementType: function(){\n                var json = JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetPlacementType\", null));\n                return json.placementType;\n            },\n            isViewable: function(){\n                var json = JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"IsViewable\", null));\n                return json.isViewable;\n            },\n            getExpandProperties: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetExpandProperties\", null));\n            },\n            setExpandProperties: function(properties){\n                //Backwards compatibility with MRAID 1.0 creatives\n                if (!!properties.lockOrientation){\n                    mraid.setOrientationProperties({\"allowOrientationChange\":false});\n                }\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"SetExpandProperties\", JSON.stringify(properties));\n            },\n            getOrientationProperties: function(){\n                return orientationProperties;\n            },\n            setOrientationProperties: function(properties){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"SetOrientationProperties\", JSON.stringify(properties));\n            },\n            getResizeProperties: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetResizeProperties\", null));\n            },\n            setResizeProperties: function(properties){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"SetResizeProperties\", JSON.stringify(properties));\n            },\n            getCurrentPosition: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetCurrentPosition\", null));\n            },\n            getMaxSize: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetMaxSize\", null));\n            },\n            getDefaultPosition: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetDefaultPosition\", null));\n            },\n            getScreenSize: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetScreenSize\", null));\n            },\n            // Operations\n            open: function(url) {\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Open\", JSON.stringify({url: url}));\n            },\n            close: function() {\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Close\", null);\n            },\n            expand: function(url) {\n                if (url !== undefined) {\n                    mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Expand\", JSON.stringify({url: url}));\n                } else {\n                    mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Expand\", JSON.stringify({url: \"\"}));\n                }\n            },\n            resize: function() {\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Resize\", null);\n            },\n            createCalendarEvent: function(eventObject) {\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"CreateCalendarEvent\", JSON.stringify(eventObject));\n            },\n            playVideo: function(url){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"PlayVideo\", JSON.stringify({url: url}));\n            },\n            storePicture: function(url){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"StorePicture\", JSON.stringify({url: url}));\n            }\n    };\n})(window, console);\n");
    private static final String LOGTAG = MRAIDAdSDKBridge.class.getSimpleName();
    private static final String MRAID_BRIDGE_NAME = "mraidObject";
    private static final String PLACEMENT_TYPE_INLINE = "inline";
    private static final String PLACEMENT_TYPE_INTERSTITIAL = "interstitial";
    private final AdControlAccessor adControlAccessor;
    private final AdUtils2 adUtils;
    private final AlertDialogFactory alertDialogFactory;
    private final AndroidBuildInfo buildInfo;
    private final Position defaultPosition;
    private final ExpandProperties expandProperties;
    private boolean expandedWithUrl;
    private final GraphicsUtils graphicsUtils;
    private final IntentBuilderFactory intentBuilderFactory;
    private final JavascriptInteractor javascriptInteractor;
    private final LayoutFactory layoutFactory;
    private final MobileAdsLogger logger;
    private final OrientationProperties orientationProperties;
    private final PermissionChecker permissionChecker;
    private final ResizeProperties resizeProperties;
    private ViewGroup resizedView;
    private FrameLayout rootView;
    private SDKEventListener sdkEventListener;
    private final ThreadRunner threadRunner;
    private final ViewUtils viewUtils;
    private final WebRequestFactory webRequestFactory;
    private final WebUtils2 webUtils;

    static class AlertDialogFactory {
        AlertDialogFactory() {
        }

        public Builder createBuilder(Context context) {
            return new Builder(context);
        }
    }

    private static class CloseJSIF extends JavascriptMethodExecutor {
        private static final String name = "Close";
        private final MRAIDAdSDKBridge bridge;

        public CloseJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.close();
            return null;
        }
    }

    private static class CreateCalendarEventJSIF extends JavascriptMethodExecutor {
        private static final String name = "CreateCalendarEvent";
        private final MRAIDAdSDKBridge bridge;

        public CreateCalendarEventJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.createCalendarEvent(JSONUtils.getStringFromJSON(jSONObject, "description", null), JSONUtils.getStringFromJSON(jSONObject, "location", null), JSONUtils.getStringFromJSON(jSONObject, "summary", null), JSONUtils.getStringFromJSON(jSONObject, "start", null), JSONUtils.getStringFromJSON(jSONObject, "end", null));
            return null;
        }
    }

    private static class DeregisterViewabilityInterestJSIF extends JavascriptMethodExecutor {
        private static final String name = "DeregisterViewabilityInterest";
        private final MRAIDAdSDKBridge bridge;

        public DeregisterViewabilityInterestJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        protected JSONObject execute(JSONObject jSONObject) {
            this.bridge.deregisterViewabilityInterest();
            return null;
        }
    }

    private static class ExpandJSIF extends JavascriptMethodExecutor {
        private static final String name = "Expand";
        private final MRAIDAdSDKBridge bridge;

        public ExpandJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.expand(JSONUtils.getStringFromJSON(jSONObject, "url", null));
            return null;
        }
    }

    private static class GetCurrentPositionJSIF extends JavascriptMethodExecutor {
        private static final String name = "GetCurrentPosition";
        private final MRAIDAdSDKBridge bridge;

        public GetCurrentPositionJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getCurrentPosition();
        }
    }

    private static class GetDefaultPositionJSIF extends JavascriptMethodExecutor {
        private static final String name = "GetDefaultPosition";
        private final MRAIDAdSDKBridge bridge;

        public GetDefaultPositionJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getDefaultPosition();
        }
    }

    private static class GetExpandPropertiesJSIF extends JavascriptMethodExecutor {
        private static final String name = "GetExpandProperties";
        private final MRAIDAdSDKBridge bridge;

        public GetExpandPropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getExpandPropertiesForCreative();
        }
    }

    private static class GetMaxSizeJSIF extends JavascriptMethodExecutor {
        private static final String name = "GetMaxSize";
        private final MRAIDAdSDKBridge bridge;

        public GetMaxSizeJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getMaxSize();
        }
    }

    private static class GetPlacementTypeJSIF extends JavascriptMethodExecutor {
        private static final String name = "GetPlacementType";
        private final MRAIDAdSDKBridge bridge;

        public GetPlacementTypeJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            JSONUtils.put(jSONObject2, "placementType", this.bridge.getPlacementType());
            return jSONObject2;
        }
    }

    private static class GetResizePropertiesJSIF extends JavascriptMethodExecutor {
        private static final String name = "GetResizeProperties";
        private final MRAIDAdSDKBridge bridge;

        public GetResizePropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getResizeProperties();
        }
    }

    private static class GetScreenSizeJSIF extends JavascriptMethodExecutor {
        private static final String name = "GetScreenSize";
        private final MRAIDAdSDKBridge bridge;

        public GetScreenSizeJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getScreenSize();
        }
    }

    private static class IsViewableJSIF extends JavascriptMethodExecutor {
        private static final String name = "IsViewable";
        private final MRAIDAdSDKBridge bridge;

        public IsViewableJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            JSONUtils.put(jSONObject2, "isViewable", this.bridge.isViewable());
            return jSONObject2;
        }
    }

    private static class OpenJSIF extends JavascriptMethodExecutor {
        private static final String name = "Open";
        private final MRAIDAdSDKBridge bridge;

        public OpenJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.open(JSONUtils.getStringFromJSON(jSONObject, "url", null));
            return null;
        }
    }

    private static class PlayVideoJSIF extends JavascriptMethodExecutor {
        private static final String name = "PlayVideo";
        private final MRAIDAdSDKBridge bridge;

        public PlayVideoJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.playVideo(JSONUtils.getStringFromJSON(jSONObject, "url", null));
            return null;
        }
    }

    private static class RegisterViewabilityInterestJSIF extends JavascriptMethodExecutor {
        private static final String name = "RegisterViewabilityInterest";
        private final MRAIDAdSDKBridge bridge;

        public RegisterViewabilityInterestJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        protected JSONObject execute(JSONObject jSONObject) {
            this.bridge.registerViewabilityInterest();
            return null;
        }
    }

    private static class ResizeJSIF extends JavascriptMethodExecutor {
        private static final String name = "Resize";
        private final MRAIDAdSDKBridge bridge;

        public ResizeJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.resize();
            return null;
        }
    }

    private static class SetExpandPropertiesJSIF extends JavascriptMethodExecutor {
        private static final String name = "SetExpandProperties";
        private final MRAIDAdSDKBridge bridge;

        public SetExpandPropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.setExpandProperties(jSONObject);
            return null;
        }
    }

    private static class SetOrientationPropertiesJSIF extends JavascriptMethodExecutor {
        private static final String name = "SetOrientationProperties";
        private final MRAIDAdSDKBridge bridge;

        public SetOrientationPropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.setOrientationProperties(jSONObject);
            return null;
        }
    }

    private static class SetResizePropertiesJSIF extends JavascriptMethodExecutor {
        private static final String name = "SetResizeProperties";
        private final MRAIDAdSDKBridge bridge;

        public SetResizePropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.setResizeProperties(jSONObject);
            return null;
        }
    }

    private static class StorePictureJSIF extends JavascriptMethodExecutor {
        private static final String name = "StorePicture";
        private final MRAIDAdSDKBridge bridge;

        public StorePictureJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.storePicture(JSONUtils.getStringFromJSON(jSONObject, "url", null));
            return null;
        }
    }

    private static class SupportsJSIF extends JavascriptMethodExecutor {
        private static final String name = "Supports";
        private final MRAIDAdSDKBridge bridge;

        public SupportsJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getSupportedFeatures();
        }
    }

    private static class UseCustomCloseJSIF extends JavascriptMethodExecutor {
        private static final String name = "UseCustomClose";
        private final MRAIDAdSDKBridge bridge;

        public UseCustomCloseJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.setUseCustomClose(JSONUtils.getBooleanFromJSON(jSONObject, "useCustomClose", false));
            return null;
        }
    }

    MRAIDAdSDKBridge(AdControlAccessor adControlAccessor, JavascriptInteractor javascriptInteractor) {
        this(adControlAccessor, javascriptInteractor, new PermissionChecker(), new MobileAdsLoggerFactory(), new WebRequestFactory(), ThreadUtils.getThreadRunner(), new GraphicsUtils(), new AlertDialogFactory(), new WebUtils2(), new AdUtils2(), new IntentBuilderFactory(), new ExpandProperties(), new OrientationProperties(), new Position(), new ResizeProperties(), new AndroidBuildInfo(), new LayoutFactory(), new ViewUtils());
    }

    MRAIDAdSDKBridge(AdControlAccessor adControlAccessor, JavascriptInteractor javascriptInteractor, PermissionChecker permissionChecker, MobileAdsLoggerFactory mobileAdsLoggerFactory, WebRequestFactory webRequestFactory, ThreadRunner threadRunner, GraphicsUtils graphicsUtils, AlertDialogFactory alertDialogFactory, WebUtils2 webUtils2, AdUtils2 adUtils2, IntentBuilderFactory intentBuilderFactory, ExpandProperties expandProperties, OrientationProperties orientationProperties, Position position, ResizeProperties resizeProperties, AndroidBuildInfo androidBuildInfo, LayoutFactory layoutFactory, ViewUtils viewUtils) {
        this.expandedWithUrl = true;
        this.adControlAccessor = adControlAccessor;
        this.javascriptInteractor = javascriptInteractor;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.permissionChecker = permissionChecker;
        this.webRequestFactory = webRequestFactory;
        this.threadRunner = threadRunner;
        this.graphicsUtils = graphicsUtils;
        this.alertDialogFactory = alertDialogFactory;
        this.webUtils = webUtils2;
        this.adUtils = adUtils2;
        this.intentBuilderFactory = intentBuilderFactory;
        this.expandProperties = expandProperties;
        this.orientationProperties = orientationProperties;
        this.defaultPosition = position;
        this.resizeProperties = resizeProperties;
        this.buildInfo = androidBuildInfo;
        this.layoutFactory = layoutFactory;
        this.viewUtils = viewUtils;
        populateJavascriptExecutorsInInteractor();
    }

    private void populateJavascriptExecutorsInInteractor() {
        this.javascriptInteractor.addMethodExecutor(new CloseJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new CreateCalendarEventJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new ExpandJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetCurrentPositionJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetDefaultPositionJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetExpandPropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetMaxSizeJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetPlacementTypeJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetResizePropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetScreenSizeJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new OpenJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new PlayVideoJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new ResizeJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new SetExpandPropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new SetOrientationPropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new SetResizePropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new StorePictureJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new SupportsJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new UseCustomCloseJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new IsViewableJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new RegisterViewabilityInterestJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new DeregisterViewabilityInterestJSIF(this));
    }

    public boolean hasNativeExecution() {
        return true;
    }

    public Executor getJavascriptInteractorExecutor() {
        return this.javascriptInteractor.getExecutor();
    }

    public String getName() {
        return MRAID_BRIDGE_NAME;
    }

    public String getJavascript() {
        return JAVASCRIPT;
    }

    public SDKEventListener getSDKEventListener() {
        if (this.sdkEventListener == null) {
            this.sdkEventListener = new MRAIDAdSDKEventListener(this);
        }
        return this.sdkEventListener;
    }

    private Context getContext() {
        return this.adControlAccessor.getContext();
    }

    void updateDefaultPosition(int i, int i2, int i3, int i4) {
        this.defaultPosition.setSize(new Size(i, i2));
        this.defaultPosition.setX(i3);
        this.defaultPosition.setY(i4);
    }

    public JSONObject getCurrentPosition() {
        if (this.adControlAccessor.getCurrentPosition() != null) {
            return this.adControlAccessor.getCurrentPosition().toJSONObject();
        }
        fireErrorEvent("Current position is unavailable because the ad has not yet been displayed.", "getCurrentPosition");
        return new Position(new Size(0, 0), 0, 0).toJSONObject();
    }

    public JSONObject getDefaultPosition() {
        return this.defaultPosition.toJSONObject();
    }

    public JSONObject getMaxSize() {
        Size maxSize = this.adControlAccessor.getMaxSize();
        if (maxSize == null) {
            return new Size(0, 0).toJSONObject();
        }
        return maxSize.toJSONObject();
    }

    public JSONObject getScreenSize() {
        Size screenSize = this.adControlAccessor.getScreenSize();
        if (screenSize == null) {
            return new Size(0, 0).toJSONObject();
        }
        return screenSize.toJSONObject();
    }

    public String getPlacementType() {
        if (this.adControlAccessor.isInterstitial()) {
            return "interstitial";
        }
        return PLACEMENT_TYPE_INLINE;
    }

    public String getOrientationProperties() {
        return this.orientationProperties.toString();
    }

    public void setOrientationProperties(JSONObject jSONObject) {
        if (this.adControlAccessor.isInterstitial() && !this.adControlAccessor.isModal()) {
            this.adControlAccessor.orientationChangeAttemptedWhenNotAllowed();
        }
        this.orientationProperties.fromJSONObject(jSONObject);
        orientationPropertyChange();
    }

    public JSONObject getExpandPropertiesForCreative() {
        ExpandProperties toClone = this.expandProperties.toClone();
        Size size = null;
        if (toClone.getWidth() == -1) {
            size = this.adControlAccessor.getScreenSize();
            toClone.setWidth(size.getWidth());
        }
        if (toClone.getHeight() == -1) {
            if (size == null) {
                size = this.adControlAccessor.getScreenSize();
            }
            toClone.setHeight(size.getHeight());
        }
        return toClone.toJSONObject();
    }

    public void setExpandProperties(JSONObject jSONObject) {
        this.expandProperties.fromJSONObject(jSONObject);
        showNativeCloseButtonIfNeeded();
    }

    public JSONObject getResizeProperties() {
        return this.resizeProperties.toJSONObject();
    }

    public void setResizeProperties(JSONObject jSONObject) {
        if (!this.resizeProperties.fromJSONObject(jSONObject)) {
            fireErrorEvent("Invalid resize properties", "setResizeProperties");
        } else if (this.resizeProperties.getWidth() < 50 || this.resizeProperties.getHeight() < 50) {
            fireErrorEvent("Resize properties width and height must be greater than 50dp in order to fit the close button.", "setResizeProperties");
            this.resizeProperties.reset();
        } else {
            Size maxSize = this.adControlAccessor.getMaxSize();
            if (this.resizeProperties.getWidth() > maxSize.getWidth() || this.resizeProperties.getHeight() > maxSize.getHeight()) {
                fireErrorEvent("Resize properties width and height cannot be larger than the maximum size.", "setResizeProperties");
                this.resizeProperties.reset();
            } else if (this.resizeProperties.getAllowOffscreen()) {
                Size computeResizeSizeInPixels = computeResizeSizeInPixels(this.resizeProperties);
                int deviceIndependentPixelToPixel = this.adUtils.deviceIndependentPixelToPixel(this.defaultPosition.getX() + this.resizeProperties.getOffsetX());
                int deviceIndependentPixelToPixel2 = this.adUtils.deviceIndependentPixelToPixel(this.defaultPosition.getY() + this.resizeProperties.getOffsetY());
                if (!isValidClosePosition(RelativePosition.fromString(this.resizeProperties.getCustomClosePosition()), deviceIndependentPixelToPixel2, deviceIndependentPixelToPixel, computeResizeSizeInPixels, this.adUtils.deviceIndependentPixelToPixel(maxSize.getWidth()), this.adUtils.deviceIndependentPixelToPixel(maxSize.getHeight()))) {
                    fireErrorEvent("Invalid resize properties. Close event area must be entirely on screen.", "setResizeProperties");
                    this.resizeProperties.reset();
                }
            }
        }
    }

    public void setUseCustomClose(boolean z) {
        this.expandProperties.setUseCustomClose(Boolean.valueOf(z));
        showNativeCloseButtonIfNeeded();
    }

    private void showNativeCloseButtonIfNeeded() {
        if (this.adControlAccessor.isModal()) {
            this.adControlAccessor.showNativeCloseButtonImage(!this.expandProperties.getUseCustomClose().booleanValue());
        }
    }

    public void close() {
        if (!this.adControlAccessor.closeAd()) {
            fireErrorEvent("Unable to close ad in its current state.", "close");
        }
    }

    private AdControlAccessor getAdControlAccessor() {
        return this.adControlAccessor;
    }

    public void expand(String str) {
        if (this.adControlAccessor.isInterstitial()) {
            fireErrorEvent("Unable to expand an interstitial ad placement", "expand");
        } else if (this.adControlAccessor.isModal()) {
            fireErrorEvent("Unable to expand while expanded.", "expand");
        } else if (!this.adControlAccessor.isVisible()) {
            fireErrorEvent("Unable to expand ad while it is not visible.", "expand");
        } else if ((this.expandProperties.getWidth() < 50 && this.expandProperties.getWidth() != -1) || (this.expandProperties.getHeight() < 50 && this.expandProperties.getHeight() != -1)) {
            fireErrorEvent("Expand size is too small, must leave room for close.", "expand");
        } else if (StringUtils.isNullOrWhiteSpace(str)) {
            AdControllerFactory.cacheAdControlAccessor(this.adControlAccessor);
            launchExpandActivity(null, this.expandProperties);
        } else if (this.webUtils.isUrlValid(str)) {
            final ExpandProperties toClone = this.expandProperties.toClone();
            this.adControlAccessor.preloadUrl(str, new PreloadCallback() {
                public void onPreloadComplete(String str) {
                    MRAIDAdSDKBridge.this.getAdControlAccessor().injectJavascriptPreload("mraidBridge.stateChange('expanded');");
                    MRAIDAdSDKBridge.this.getAdControlAccessor().injectJavascriptPreload("mraidBridge.ready();");
                    AdControllerFactory.cacheAdControlAccessor(MRAIDAdSDKBridge.this.getAdControlAccessor());
                    MRAIDAdSDKBridge.this.launchExpandActivity(str, toClone);
                }
            });
        } else {
            fireErrorEvent("Unable to expand with invalid URL.", "expand");
        }
    }

    private void launchExpandActivity(String str, ExpandProperties expandProperties) {
        if (this.intentBuilderFactory.createIntentBuilder().withClass(AdActivity.class).withContext(this.adControlAccessor.getContext().getApplicationContext()).withExtra("adapter", ModalAdActivityAdapter.class.getName()).withExtra("url", str).withExtra("expandProperties", expandProperties.toString()).withExtra("orientationProperties", this.orientationProperties.toString()).fireIntent()) {
            this.logger.d("Successfully expanded ad");
        }
    }

    public void resize() {
        if (this.adControlAccessor.isInterstitial()) {
            fireErrorEvent("Unable to resize an interstitial ad placement.", "resize");
        } else if (this.adControlAccessor.isModal()) {
            fireErrorEvent("Unable to resize while expanded.", "resize");
        } else if (!this.adControlAccessor.isVisible()) {
            fireErrorEvent("Unable to resize ad while it is not visible.", "resize");
        } else if (this.resizeProperties == null || !this.resizeProperties.areResizePropertiesSet()) {
            fireErrorEvent("Resize properties must be set before calling resize.", "resize");
        } else {
            resizeAd(this.resizeProperties);
        }
    }

    public void playVideo(String str) {
        if (!this.adControlAccessor.isVisible()) {
            fireErrorEvent("Unable to play a video while the ad is not visible", "playVideo");
        } else if (StringUtils.isNullOrEmpty(str)) {
            fireErrorEvent("Unable to play a video without a URL", "playVideo");
        } else {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("url", str);
                Intent intent = new Intent(getContext(), AdActivity.class);
                intent.putExtra("adapter", VideoActionHandler.class.getName());
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                this.logger.d("Failed to open VideoAction activity");
                fireErrorEvent("Internal SDK Failure. Unable to launch VideoActionHandler", "playVideo");
            }
        }
    }

    public void open(String str) {
        if (this.adControlAccessor.isVisible()) {
            this.logger.d("Opening URL " + str);
            String scheme;
            if (this.webUtils.isUrlValid(str)) {
                scheme = WebUtils.getScheme(str);
                if (Constants.HTTP.equals(scheme) || Constants.HTTPS.equals(scheme)) {
                    new InAppBrowserBuilder().withContext(getContext()).withExternalBrowserButton().isInterstitial(this.adControlAccessor.isInterstitial()).withUrl(str).show();
                    return;
                } else {
                    this.adControlAccessor.loadUrl(str);
                    return;
                }
            }
            scheme = "URL " + str + " is not a valid URL";
            this.logger.d(scheme);
            fireErrorEvent(scheme, "open");
            return;
        }
        fireErrorEvent("Unable to open a URL while the ad is not visible", "open");
    }

    public JSONObject getSupportedFeatures() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sms", getContext().getPackageManager().hasSystemFeature("android.hardware.telephony"));
            jSONObject.put("tel", getContext().getPackageManager().hasSystemFeature("android.hardware.telephony"));
            jSONObject.put(MRAIDNativeFeature.CALENDAR, AndroidTargetUtils.isAtLeastAndroidAPI(14));
            jSONObject.put(MRAIDNativeFeature.STORE_PICTURE, this.permissionChecker.hasWriteExternalStoragePermission(getContext()));
            jSONObject.put(MRAIDNativeFeature.INLINE_VIDEO, AndroidTargetUtils.isAtLeastAndroidAPI(11));
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public void createCalendarEvent(String str, String str2, String str3, String str4, String str5) {
        if (AndroidTargetUtils.isAtLeastAndroidAPI(14)) {
            try {
                createCalendarIntent(new CalendarEventParameters(str, str2, str3, str4, str5));
                return;
            } catch (IllegalArgumentException e) {
                this.logger.d(e.getMessage());
                fireErrorEvent(e.getMessage(), "createCalendarEvent");
                return;
            }
        }
        String str6 = "API version does not support calendar operations.";
        this.logger.d("API version does not support calendar operations.");
        fireErrorEvent("API version does not support calendar operations.", "createCalendarEvent");
    }

    @TargetApi(14)
    private void createCalendarIntent(CalendarEventParameters calendarEventParameters) {
        Intent type = new Intent("android.intent.action.INSERT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE);
        type.putExtra("title", calendarEventParameters.getDescription());
        if (!StringUtils.isNullOrEmpty(calendarEventParameters.getLocation())) {
            type.putExtra("eventLocation", calendarEventParameters.getLocation());
        }
        if (!StringUtils.isNullOrEmpty(calendarEventParameters.getSummary())) {
            type.putExtra("description", calendarEventParameters.getSummary());
        }
        type.putExtra("beginTime", calendarEventParameters.getStart().getTime());
        if (calendarEventParameters.getEnd() != null) {
            type.putExtra("endTime", calendarEventParameters.getEnd().getTime());
        }
        getContext().startActivity(type);
    }

    public void storePicture(final String str) {
        if (this.permissionChecker.hasWriteExternalStoragePermission(getContext())) {
            this.threadRunner.execute(new Runnable() {
                public void run() {
                    MRAIDAdSDKBridge.this.fetchPicture(str);
                }
            }, ExecutionStyle.RUN_ASAP, ExecutionThread.BACKGROUND_THREAD);
        } else {
            fireErrorEvent("Picture could not be stored because permission was denied.", MRAIDNativeFeature.STORE_PICTURE);
        }
    }

    private void fetchPicture(String str) {
        WebRequest createWebRequest = this.webRequestFactory.createWebRequest();
        createWebRequest.enableLog(true);
        createWebRequest.setUrlString(str);
        try {
            WebResponse makeCall = createWebRequest.makeCall();
            if (makeCall == null) {
                fireErrorEvent("Server could not be contacted to download picture.", MRAIDNativeFeature.STORE_PICTURE);
                return;
            }
            final Bitmap readAsBitmap = new ImageResponseReader(makeCall.getResponseReader(), this.graphicsUtils).readAsBitmap();
            if (readAsBitmap == null) {
                fireErrorEvent("Picture could not be retrieved from server.", MRAIDNativeFeature.STORE_PICTURE);
            } else {
                this.threadRunner.execute(new Runnable() {
                    public void run() {
                        MRAIDAdSDKBridge.this.savePicture(readAsBitmap);
                    }
                }, ExecutionStyle.SCHEDULE, ExecutionThread.MAIN_THREAD);
            }
        } catch (WebRequestException e) {
            fireErrorEvent("Server could not be contacted to download picture.", MRAIDNativeFeature.STORE_PICTURE);
        }
    }

    private void savePicture(final Bitmap bitmap) {
        Builder createBuilder = this.alertDialogFactory.createBuilder(getContext());
        createBuilder.setTitle("Would you like to save the image to your gallery?");
        createBuilder.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (StringUtils.isNullOrEmpty(MRAIDAdSDKBridge.this.graphicsUtils.insertImageInMediaStore(MRAIDAdSDKBridge.this.getContext(), bitmap, "AdImage", "Image created by rich media ad."))) {
                    MRAIDAdSDKBridge.this.fireErrorEvent("Picture could not be stored to device.", MRAIDNativeFeature.STORE_PICTURE);
                    return;
                }
                MediaScannerConnection.scanFile(MRAIDAdSDKBridge.this.getContext(), new String[]{r1}, (String[]) null, (OnScanCompletedListener) null);
            }
        });
        createBuilder.setNegativeButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MRAIDAdSDKBridge.this.fireErrorEvent("User chose not to store image.", MRAIDNativeFeature.STORE_PICTURE);
            }
        });
        createBuilder.show();
    }

    void collapseExpandedAd(final AdControlAccessor adControlAccessor) {
        this.logger.d("Collapsing expanded ad " + this);
        this.threadRunner.execute(new Runnable() {
            public void run() {
                MRAIDAdSDKBridge.this.collapseExpandedAdOnThread(adControlAccessor);
            }
        }, ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }

    @SuppressLint({"InlinedApi"})
    private void collapseExpandedAdOnThread(final AdControlAccessor adControlAccessor) {
        adControlAccessor.setAdActivity(null);
        if (this.expandedWithUrl) {
            this.logger.d("Expanded With URL");
            adControlAccessor.popView();
        } else {
            this.logger.d("Not Expanded with URL");
        }
        adControlAccessor.moveViewBackToParent(new LayoutParams(-1, -1, 17));
        adControlAccessor.removeCloseButton();
        adControlAccessor.fireAdEvent(new AdEvent(AdEventType.CLOSED));
        adControlAccessor.injectJavascript("mraidBridge.stateChange('default');");
        adControlAccessor.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                adControlAccessor.removeOnGlobalLayoutListener(this);
                MRAIDAdSDKBridge.this.reportSizeChangeEvent();
            }
        });
    }

    void resizeAd(final ResizeProperties resizeProperties) {
        final Size computeResizeSizeInPixels = computeResizeSizeInPixels(resizeProperties);
        this.threadRunner.execute(new Runnable() {
            public void run() {
                MRAIDAdSDKBridge.this.resizeAdOnThread(resizeProperties, computeResizeSizeInPixels);
            }
        }, ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }

    private void resizeAdOnThread(final ResizeProperties resizeProperties, final Size size) {
        Size maxSize = this.adControlAccessor.getMaxSize();
        if (maxSize == null) {
            this.adControlAccessor.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    MRAIDAdSDKBridge.this.adControlAccessor.removeOnGlobalLayoutListener(this);
                    MRAIDAdSDKBridge.this.resizeAdHelper(resizeProperties, size, MRAIDAdSDKBridge.this.adControlAccessor.getMaxSize());
                }
            });
        } else {
            resizeAdHelper(resizeProperties, size, maxSize);
        }
    }

    private void resizeAdHelper(ResizeProperties resizeProperties, Size size, Size size2) {
        if (size2 == null) {
            this.logger.d("Size is null");
            return;
        }
        createResizedView();
        int deviceIndependentPixelToPixel = this.adUtils.deviceIndependentPixelToPixel(this.defaultPosition.getX() + resizeProperties.getOffsetX());
        int deviceIndependentPixelToPixel2 = this.adUtils.deviceIndependentPixelToPixel(this.defaultPosition.getY() + resizeProperties.getOffsetY());
        RelativePosition fromString = RelativePosition.fromString(resizeProperties.getCustomClosePosition());
        int deviceIndependentPixelToPixel3 = this.adUtils.deviceIndependentPixelToPixel(size2.getWidth());
        int deviceIndependentPixelToPixel4 = this.adUtils.deviceIndependentPixelToPixel(size2.getHeight());
        if (!resizeProperties.getAllowOffscreen()) {
            if (size.getWidth() > deviceIndependentPixelToPixel3) {
                size.setWidth(deviceIndependentPixelToPixel3);
            }
            if (size.getHeight() > deviceIndependentPixelToPixel4) {
                size.setHeight(deviceIndependentPixelToPixel4);
            }
            if (deviceIndependentPixelToPixel < 0) {
                deviceIndependentPixelToPixel = 0;
            } else if (size.getWidth() + deviceIndependentPixelToPixel > deviceIndependentPixelToPixel3) {
                deviceIndependentPixelToPixel = deviceIndependentPixelToPixel3 - size.getWidth();
            }
            if (deviceIndependentPixelToPixel2 < 0) {
                deviceIndependentPixelToPixel2 = 0;
            } else if (size.getHeight() + deviceIndependentPixelToPixel2 > deviceIndependentPixelToPixel4) {
                deviceIndependentPixelToPixel2 = deviceIndependentPixelToPixel4 - size.getHeight();
            }
        } else if (!isValidClosePosition(fromString, deviceIndependentPixelToPixel2, deviceIndependentPixelToPixel, size, deviceIndependentPixelToPixel3, deviceIndependentPixelToPixel4)) {
            fireErrorEvent("Resize failed because close event area must be entirely on screen.", "resize");
            return;
        }
        this.adControlAccessor.moveViewToViewGroup(this.resizedView, new RelativeLayout.LayoutParams(size.getWidth(), size.getHeight()), false);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(size.getWidth(), size.getHeight());
        layoutParams.gravity = 48;
        layoutParams.leftMargin = deviceIndependentPixelToPixel;
        layoutParams.topMargin = deviceIndependentPixelToPixel2;
        if (this.rootView.equals(this.resizedView.getParent())) {
            this.resizedView.setLayoutParams(layoutParams);
        } else {
            this.rootView.addView(this.resizedView, layoutParams);
        }
        this.adControlAccessor.enableCloseButton(false, fromString);
        final ViewTreeObserver viewTreeObserver = this.resizedView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                MRAIDAdSDKBridge.this.viewUtils.removeOnGlobalLayoutListener(viewTreeObserver, this);
                int[] iArr = new int[2];
                MRAIDAdSDKBridge.this.resizedView.getLocationOnScreen(iArr);
                Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + MRAIDAdSDKBridge.this.resizedView.getWidth(), iArr[1] + MRAIDAdSDKBridge.this.resizedView.getHeight());
                AdEvent adEvent = new AdEvent(AdEventType.RESIZED);
                adEvent.setParameter(AdEvent.POSITION_ON_SCREEN, rect);
                MRAIDAdSDKBridge.this.adControlAccessor.fireAdEvent(adEvent);
                MRAIDAdSDKBridge.this.adControlAccessor.injectJavascript("mraidBridge.stateChange('resized');");
                MRAIDAdSDKBridge.this.reportSizeChangeEvent();
            }
        });
    }

    private boolean isViewable() {
        return this.adControlAccessor.isViewable();
    }

    private boolean isValidClosePosition(RelativePosition relativePosition, int i, int i2, Size size, int i3, int i4) {
        int deviceIndependentPixelToPixel = this.adUtils.deviceIndependentPixelToPixel(50);
        int i5;
        int i6;
        switch (relativePosition) {
            case TOP_LEFT:
                i5 = i + deviceIndependentPixelToPixel;
                i6 = i2 + deviceIndependentPixelToPixel;
                break;
            case TOP_RIGHT:
                i6 = size.getWidth() + i2;
                i5 = i + deviceIndependentPixelToPixel;
                i2 = i6 - deviceIndependentPixelToPixel;
                break;
            case TOP_CENTER:
                i2 = ((size.getWidth() / 2) + i2) - (deviceIndependentPixelToPixel / 2);
                i5 = i + deviceIndependentPixelToPixel;
                i6 = i2 + deviceIndependentPixelToPixel;
                break;
            case BOTTOM_LEFT:
                i5 = i + size.getHeight();
                i = i5 - deviceIndependentPixelToPixel;
                i6 = i2 + deviceIndependentPixelToPixel;
                break;
            case BOTTOM_RIGHT:
                i5 = i + size.getHeight();
                i6 = size.getWidth() + i2;
                i = i5 - deviceIndependentPixelToPixel;
                i2 = i6 - deviceIndependentPixelToPixel;
                break;
            case BOTTOM_CENTER:
                i5 = i + size.getHeight();
                i2 = ((size.getWidth() / 2) + i2) - (deviceIndependentPixelToPixel / 2);
                i = i5 - deviceIndependentPixelToPixel;
                i6 = i2 + deviceIndependentPixelToPixel;
                break;
            case CENTER:
                i = ((size.getHeight() / 2) + i) - (deviceIndependentPixelToPixel / 2);
                i2 = ((size.getWidth() / 2) + i2) - (deviceIndependentPixelToPixel / 2);
                i5 = i + deviceIndependentPixelToPixel;
                i6 = i2 + deviceIndependentPixelToPixel;
                break;
            default:
                i6 = 0;
                i2 = 0;
                i5 = 0;
                i = 0;
                break;
        }
        if (i < 0 || r8 < 0 || r2 > i4 || r1 > i3) {
            return false;
        }
        return true;
    }

    private Size computeResizeSizeInPixels(ResizeProperties resizeProperties) {
        return new Size(this.adUtils.deviceIndependentPixelToPixel(resizeProperties.getWidth()), this.adUtils.deviceIndependentPixelToPixel(resizeProperties.getHeight()));
    }

    private void createResizedView() {
        if (this.resizedView == null) {
            if (this.rootView == null) {
                this.rootView = (FrameLayout) this.adControlAccessor.getRootView();
            }
            this.resizedView = this.layoutFactory.createLayout(getContext(), LayoutType.RELATIVE_LAYOUT, CONTENT_DESCRIPTION_RESIZED_VIEW);
        }
    }

    void reportSizeChangeEvent() {
        Position currentPosition = this.adControlAccessor.getCurrentPosition();
        if (currentPosition != null) {
            this.adControlAccessor.injectJavascript("mraidBridge.sizeChange(" + currentPosition.getSize().getWidth() + "," + currentPosition.getSize().getHeight() + ");");
        }
    }

    void orientationPropertyChange() {
        if (this.adControlAccessor.isVisible() && this.adControlAccessor.isModal()) {
            Context adActivity = this.adControlAccessor.getAdActivity();
            if (adActivity == null) {
                this.logger.e("unable to handle orientation property change on a non-expanded ad");
                return;
            }
            int requestedOrientation = adActivity.getRequestedOrientation();
            Position currentPosition = this.adControlAccessor.getCurrentPosition();
            this.logger.d("Current Orientation: " + requestedOrientation);
            switch (this.orientationProperties.getForceOrientation()) {
                case PORTRAIT:
                    adActivity.setRequestedOrientation(7);
                    break;
                case LANDSCAPE:
                    adActivity.setRequestedOrientation(6);
                    break;
            }
            if (ForceOrientation.NONE.equals(this.orientationProperties.getForceOrientation())) {
                if (this.orientationProperties.isAllowOrientationChange().booleanValue()) {
                    if (adActivity.getRequestedOrientation() != -1) {
                        adActivity.setRequestedOrientation(-1);
                    }
                } else if (this.adControlAccessor.isModal()) {
                    adActivity.setRequestedOrientation(DisplayUtils.determineCanonicalScreenOrientation(adActivity, this.buildInfo));
                }
            }
            int requestedOrientation2 = adActivity.getRequestedOrientation();
            this.logger.d("New Orientation: " + requestedOrientation2);
            if (requestedOrientation2 != requestedOrientation && currentPosition != null) {
                if (currentPosition.getSize().getWidth() != this.adControlAccessor.getCurrentPosition().getSize().getWidth()) {
                    this.adControlAccessor.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            MRAIDAdSDKBridge.this.adControlAccessor.removeOnGlobalLayoutListener(this);
                            MRAIDAdSDKBridge.this.reportSizeChangeEvent();
                        }
                    });
                }
            }
        }
    }

    private void registerViewabilityInterest() {
        this.adControlAccessor.registerViewabilityInterest();
    }

    private void deregisterViewabilityInterest() {
        this.adControlAccessor.deregisterViewabilityInterest();
    }

    private void fireErrorEvent(String str, String str2) {
        this.adControlAccessor.injectJavascript(String.format(Locale.US, ERROR_EVENT_FORMAT, new Object[]{str, str2}));
    }
}
