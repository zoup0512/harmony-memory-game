package com.unity3d.ads2.adunit;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.unity3d.ads2.api.AdUnit;
import com.unity3d.ads2.api.VideoPlayer;
import com.unity3d.ads2.log.DeviceLog;
import com.unity3d.ads2.misc.ViewUtilities;
import com.unity3d.ads2.video.VideoPlayerView;
import com.unity3d.ads2.webview.WebViewApp;
import com.unity3d.ads2.webview.WebViewEventCategory;
import java.util.ArrayList;
import java.util.Arrays;

public class AdUnitActivity extends Activity {
    public static final String EXTRA_ACTIVITY_ID = "activityId";
    public static final String EXTRA_KEEP_SCREEN_ON = "keepScreenOn";
    public static final String EXTRA_KEY_EVENT_LIST = "keyEvents";
    public static final String EXTRA_ORIENTATION = "orientation";
    public static final String EXTRA_SYSTEM_UI_VISIBILITY = "systemUiVisibility";
    public static final String EXTRA_VIEWS = "views";
    private int _activityId;
    boolean _keepScreenOn;
    private ArrayList<Integer> _keyEventList;
    private RelativeLayout _layout;
    private int _orientation = -1;
    private int _systemUiVisibility;
    private String[] _views;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (WebViewApp.getCurrentApp() == null) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onCreate");
            finish();
            return;
        }
        Enum enumR;
        AdUnit.setAdUnitActivity(this);
        createLayout();
        ViewUtilities.removeViewFromParent(this._layout);
        addContentView(this._layout, this._layout.getLayoutParams());
        if (bundle == null) {
            this._views = getIntent().getStringArrayExtra("views");
            this._keyEventList = getIntent().getIntegerArrayListExtra("keyEvents");
            if (getIntent().hasExtra("orientation")) {
                this._orientation = getIntent().getIntExtra("orientation", -1);
            }
            if (getIntent().hasExtra("systemUiVisibility")) {
                this._systemUiVisibility = getIntent().getIntExtra("systemUiVisibility", 0);
            }
            if (getIntent().hasExtra("activityId")) {
                this._activityId = getIntent().getIntExtra("activityId", -1);
            }
            enumR = AdUnitEvent.ON_CREATE;
        } else {
            this._views = bundle.getStringArray("views");
            this._orientation = bundle.getInt("orientation", -1);
            this._systemUiVisibility = bundle.getInt("systemUiVisibility", 0);
            this._keyEventList = bundle.getIntegerArrayList("keyEvents");
            this._keepScreenOn = bundle.getBoolean("keepScreenOn");
            this._activityId = bundle.getInt("activityId", -1);
            setKeepScreenOn(this._keepScreenOn);
            enumR = AdUnitEvent.ON_RESTORE;
        }
        setOrientation(this._orientation);
        setSystemUiVisibility(this._systemUiVisibility);
        if (this._views != null && Arrays.asList(this._views).contains("videoplayer")) {
            createVideoPlayer();
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, enumR, Integer.valueOf(this._activityId));
    }

    protected void onStart() {
        super.onStart();
        if (WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_START, Integer.valueOf(this._activityId));
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onStart");
            finish();
        }
    }

    protected void onStop() {
        super.onStop();
        if (WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_STOP, Integer.valueOf(this._activityId));
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onStop");
            finish();
        }
    }

    protected void onResume() {
        super.onResume();
        if (WebViewApp.getCurrentApp() != null) {
            setViews(this._views);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_RESUME, Integer.valueOf(this._activityId));
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onResume");
            finish();
        }
    }

    protected void onPause() {
        super.onPause();
        if (WebViewApp.getCurrentApp() != null) {
            if (isFinishing()) {
                ViewUtilities.removeViewFromParent(WebViewApp.getCurrentApp().getWebView());
            }
            destroyVideoPlayer();
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_PAUSE, Boolean.valueOf(isFinishing()), Integer.valueOf(this._activityId));
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onPause");
            finish();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("orientation", this._orientation);
        bundle.putInt("systemUiVisibility", this._systemUiVisibility);
        bundle.putIntegerArrayList("keyEvents", this._keyEventList);
        bundle.putBoolean("keepScreenOn", this._keepScreenOn);
        bundle.putStringArray("views", this._views);
        bundle.putInt("activityId", this._activityId);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (WebViewApp.getCurrentApp() != null) {
            AdUnit.setAdUnitActivity(null);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.ON_DESTROY, Boolean.valueOf(isFinishing()), Integer.valueOf(this._activityId));
            if (AdUnit.getCurrentAdUnitActivityId() == this._activityId) {
                AdUnit.setAdUnitActivity(null);
            }
        } else if (!isFinishing()) {
            DeviceLog.error("Unity Ads web app is null, closing Unity Ads activity from onDestroy");
            finish();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this._keyEventList == null || !this._keyEventList.contains(Integer.valueOf(i))) {
            return false;
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.ADUNIT, AdUnitEvent.KEY_DOWN, Integer.valueOf(i), Long.valueOf(keyEvent.getEventTime()), Long.valueOf(keyEvent.getDownTime()), Integer.valueOf(keyEvent.getRepeatCount()), Integer.valueOf(this._activityId));
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setViews(java.lang.String[] r6) {
        /*
        r5 = this;
        r1 = 0;
        if (r6 != 0) goto L_0x0005;
    L_0x0003:
        r6 = new java.lang.String[r1];
    L_0x0005:
        r0 = new java.util.ArrayList;
        r2 = java.util.Arrays.asList(r6);
        r0.<init>(r2);
        r2 = r5._views;
        if (r2 != 0) goto L_0x0016;
    L_0x0012:
        r2 = new java.lang.String[r1];
        r5._views = r2;
    L_0x0016:
        r2 = new java.util.ArrayList;
        r3 = r5._views;
        r3 = java.util.Arrays.asList(r3);
        r2.<init>(r3);
        r2.removeAll(r0);
        r3 = r2.iterator();
    L_0x0028:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x0065;
    L_0x002e:
        r0 = r3.next();
        r0 = (java.lang.String) r0;
        r2 = -1;
        r4 = r0.hashCode();
        switch(r4) {
            case 1224424441: goto L_0x004f;
            case 1865295644: goto L_0x0045;
            default: goto L_0x003c;
        };
    L_0x003c:
        r0 = r2;
    L_0x003d:
        switch(r0) {
            case 0: goto L_0x0041;
            case 1: goto L_0x0059;
            default: goto L_0x0040;
        };
    L_0x0040:
        goto L_0x0028;
    L_0x0041:
        r5.destroyVideoPlayer();
        goto L_0x0028;
    L_0x0045:
        r4 = "videoplayer";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x003c;
    L_0x004d:
        r0 = r1;
        goto L_0x003d;
    L_0x004f:
        r4 = "webview";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x003c;
    L_0x0057:
        r0 = 1;
        goto L_0x003d;
    L_0x0059:
        r0 = com.unity3d.ads2.webview.WebViewApp.getCurrentApp();
        r0 = r0.getWebView();
        com.unity3d.ads2.misc.ViewUtilities.removeViewFromParent(r0);
        goto L_0x0028;
    L_0x0065:
        r5._views = r6;
        r2 = r6.length;
        r0 = r1;
    L_0x0069:
        if (r0 >= r2) goto L_0x00aa;
    L_0x006b:
        r1 = r6[r0];
        if (r1 != 0) goto L_0x0072;
    L_0x006f:
        r0 = r0 + 1;
        goto L_0x0069;
    L_0x0072:
        r3 = "videoplayer";
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x0085;
    L_0x007a:
        r5.createVideoPlayer();
        r1 = com.unity3d.ads2.api.VideoPlayer.getVideoPlayerView();
        r5.handleViewPlacement(r1);
        goto L_0x006f;
    L_0x0085:
        r3 = "webview";
        r1 = r1.equals(r3);
        if (r1 == 0) goto L_0x006f;
    L_0x008d:
        r1 = com.unity3d.ads2.webview.WebViewApp.getCurrentApp();
        if (r1 == 0) goto L_0x009f;
    L_0x0093:
        r1 = com.unity3d.ads2.webview.WebViewApp.getCurrentApp();
        r1 = r1.getWebView();
        r5.handleViewPlacement(r1);
        goto L_0x006f;
    L_0x009f:
        r0 = "WebApp IS NULL!";
        com.unity3d.ads2.log.DeviceLog.error(r0);
        r0 = new java.lang.NullPointerException;
        r0.<init>();
        throw r0;
    L_0x00aa:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.ads2.adunit.AdUnitActivity.setViews(java.lang.String[]):void");
    }

    private void handleViewPlacement(View view) {
        if (view.getParent() == null || !view.getParent().equals(this._layout)) {
            ViewUtilities.removeViewFromParent(view);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            layoutParams.setMargins(0, 0, 0, 0);
            view.setPadding(0, 0, 0, 0);
            this._layout.addView(view, layoutParams);
            return;
        }
        this._layout.bringChildToFront(view);
    }

    public String[] getViews() {
        return this._views;
    }

    public void setOrientation(int i) {
        this._orientation = i;
        setRequestedOrientation(i);
    }

    public boolean setKeepScreenOn(boolean z) {
        this._keepScreenOn = z;
        if (getWindow() == null) {
            return false;
        }
        if (z) {
            getWindow().addFlags(128);
        } else {
            getWindow().clearFlags(128);
        }
        return true;
    }

    public boolean setSystemUiVisibility(int i) {
        this._systemUiVisibility = i;
        if (VERSION.SDK_INT < 11) {
            return false;
        }
        try {
            getWindow().getDecorView().setSystemUiVisibility(i);
            return true;
        } catch (Exception e) {
            DeviceLog.exception("Error while setting SystemUIVisibility", e);
            return false;
        }
    }

    public void setKeyEventList(ArrayList<Integer> arrayList) {
        this._keyEventList = arrayList;
    }

    private void createLayout() {
        if (this._layout == null) {
            this._layout = new RelativeLayout(this);
            this._layout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            ViewUtilities.setBackground(this._layout, new ColorDrawable(-16777216));
        }
    }

    private void createVideoPlayer() {
        if (VideoPlayer.getVideoPlayerView() == null) {
            VideoPlayer.setVideoPlayerView(new VideoPlayerView(this));
        }
    }

    private void destroyVideoPlayer() {
        if (VideoPlayer.getVideoPlayerView() != null) {
            VideoPlayer.getVideoPlayerView().stopVideoProgressTimer();
            VideoPlayer.getVideoPlayerView().stopPlayback();
            ViewUtilities.removeViewFromParent(VideoPlayer.getVideoPlayerView());
        }
        VideoPlayer.setVideoPlayerView(null);
    }
}
