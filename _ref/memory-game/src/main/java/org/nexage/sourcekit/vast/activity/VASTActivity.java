package org.nexage.sourcekit.vast.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.internal.view.SupportMenu;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.ao.b;
import com.mopub.mobileads.VastIconXmlManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.nexage.sourcekit.mraid.rtb.ReportButton;
import org.nexage.sourcekit.mraid.rtb.ReportView;
import org.nexage.sourcekit.mraid.rtb.ReportView.ComplainedCallback;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;
import org.nexage.sourcekit.util.HttpTools;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.VASTPlayer;
import org.nexage.sourcekit.vast.VASTPlayer.VASTPlayerListener;
import org.nexage.sourcekit.vast.model.Extensions;
import org.nexage.sourcekit.vast.model.TRACKING_EVENTS_TYPE;
import org.nexage.sourcekit.vast.model.VASTCompanion;
import org.nexage.sourcekit.vast.model.VASTModel;
import org.nexage.sourcekit.vast.view.VastCountdown;
import org.nexage.sourcekit.vast.view.VastLinearCountdown;

public class VASTActivity extends Activity implements OnCompletionListener, OnErrorListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, ComplainedCallback {
    private static final long QUARTILE_TIMER_INTERVAL = 250;
    private static final long SKIP_TIMER_INTERVAL = 50;
    private static String TAG = "VASTActivity";
    private static final long VIDEO_PROGRESS_TIMER_INTERVAL = 50;
    private boolean autoClose;
    private ImageButton bannerCloseButton;
    private boolean canSkip = false;
    OnTouchListener companionOnTouchListener = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                case 1:
                    VASTActivity.this.touchedWebViews.add(view);
                    if (!view.hasFocus()) {
                        view.requestFocus();
                        break;
                    }
                    break;
            }
            return false;
        }
    };
    WebChromeClient companionWebChromeClient = new WebChromeClient() {
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            VASTLog.d("JS alert", str2);
            return handlePopups(jsResult);
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            VASTLog.d("JS confirm", str2);
            return handlePopups(jsResult);
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            VASTLog.d("JS prompt", str2);
            return handlePopups(jsPromptResult);
        }

        private boolean handlePopups(JsResult jsResult) {
            jsResult.cancel();
            return true;
        }
    };
    WebViewClient companionWebViewClient = new WebViewClient() {
        public void onPageFinished(WebView webView, String str) {
            webView.setBackgroundColor(0);
            if (VERSION.SDK_INT >= 11) {
                webView.setLayerType(1, null);
            }
        }

        @TargetApi(24)
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            if (webResourceRequest.hasGesture()) {
                VASTActivity.this.touchedWebViews.add(webView);
            }
            return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (VASTActivity.this.touchedWebViews.contains(webView)) {
                VASTLog.d(VASTActivity.TAG, "banner clicked");
                VASTActivity.this.processClickThroughEvent(str);
            }
            return true;
        }
    };
    private int duration;
    private Uri fileUrl;
    private boolean hasBanner;
    private boolean isComplained = false;
    private final String learnMoreText = "Learn more";
    private VASTCompanion mBanner;
    private HashMap<TRACKING_EVENTS_TYPE, List<String>> mBannerTrackingEventMap;
    private WebView mBannerView;
    private RelativeLayout mBottomPanel;
    private ImageView mCloseButton;
    private VASTCompanion mCompanion;
    private boolean mCompanionShown = false;
    private HashMap<TRACKING_EVENTS_TYPE, List<String>> mCompanionTrackingEventMap;
    private View mCompanionView;
    private int mCurrentVideoPosition;
    private Extensions mExtensions;
    private boolean mIsCompleted = false;
    private boolean mIsPlayBackError = false;
    private boolean mIsProcessedImpressions = false;
    private boolean mIsVideoPaused = false;
    VASTPlayerListener mListener;
    private final int mMaxProgressTrackingPoints = 20;
    private MediaPlayer mMediaPlayer;
    private RelativeLayout mOverlay;
    private ProgressBar mProgressBar;
    private int mQuartile = 0;
    private ImageView mRepeatButton;
    private RelativeLayout mRootLayout;
    private float mScreenDensity;
    private int mScreenHeight;
    private int mScreenWidth;
    private VastCountdown mSkipButton;
    private int mSkipTime = 5;
    private Timer mSkipTimer;
    private Timer mStartVideoProgressTimer;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private RelativeLayout mTimerPanel;
    private HashMap<TRACKING_EVENTS_TYPE, List<String>> mTrackingEventMap;
    private Timer mTrackingEventTimer;
    private b mType;
    private VASTModel mVastModel = null;
    private int mVideoHeight;
    private LinkedList<Integer> mVideoProgressTracker = null;
    private int mVideoWidth;
    private int maxDuration;
    private RtbInfo rtbInfo;
    private List<View> touchedWebViews = new ArrayList();
    private VastLinearCountdown vastCountdown;
    private boolean woBanner;

    protected void onCreate(Bundle bundle) {
        VASTLog.d(TAG, "in onCreate");
        super.onCreate(bundle);
        getWindow().addFlags(128);
        if (bundle != null) {
            this.mCompanionShown = bundle.getBoolean("mCompanionShown", false);
            this.canSkip = bundle.getBoolean("canSkip", false);
            this.mCurrentVideoPosition = bundle.getInt("mCurrentVideoPosition");
            this.duration = bundle.getInt(VastIconXmlManager.DURATION);
            this.isComplained = bundle.getBoolean("isComplained");
            this.mIsProcessedImpressions = bundle.getBoolean("mIsProcessedImpressions");
            this.mIsPlayBackError = bundle.getBoolean("mIsPlayBackError");
            this.mIsCompleted = bundle.getBoolean("mIsCompleted");
        }
        int i = getResources().getConfiguration().orientation;
        VASTLog.d(TAG, "currentOrientation:" + i);
        if (i != 2) {
            VASTLog.d(TAG, "Orientation is not landscape.....forcing landscape");
            setRequestedOrientation(0);
        } else {
            VASTLog.d(TAG, "orientation is landscape");
            Intent intent = getIntent();
            this.mListener = VASTPlayer.listener;
            this.mVastModel = (VASTModel) intent.getSerializableExtra("com.nexage.android.vast.player.vastModel");
            Parcelable parcelableExtra = intent.getParcelableExtra("android.net.url");
            this.woBanner = intent.getBooleanExtra("com.nexage.android.vast.player.woBanners", false);
            this.autoClose = intent.getBooleanExtra("com.nexage.android.vast.player.autoClose", true);
            this.maxDuration = intent.getIntExtra("com.nexage.android.vast.player.maxDuration", 0);
            this.mCompanion = this.mVastModel.getCompanion(an.f(this));
            this.mBanner = this.mVastModel.getBanner();
            this.mExtensions = this.mVastModel.getExtensions();
            if (intent.hasExtra("com.nexage.android.vast.player.type")) {
                this.mType = (b) intent.getExtras().get("com.nexage.android.vast.player.type");
            } else {
                VASTLog.e(TAG, "video type undefined.");
                this.mType = b.a;
            }
            this.rtbInfo = null;
            if (intent.hasExtra("com.nexage.android.vast.player.reportInfo")) {
                this.rtbInfo = (RtbInfo) intent.getSerializableExtra("com.nexage.android.vast.player.reportInfo");
            }
            this.fileUrl = (Uri) parcelableExtra;
            if (this.mVastModel == null) {
                VASTLog.e(TAG, "vastModel is null. Stopping activity.");
                finishActivity();
            } else {
                hideTitleStatusBars();
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                this.mScreenWidth = displayMetrics.widthPixels;
                this.mScreenHeight = displayMetrics.heightPixels;
                ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                this.mScreenDensity = displayMetrics.density;
                this.mTrackingEventMap = this.mVastModel.getTrackingUrls();
                if (this.mExtensions == null) {
                    this.mSkipTime = this.mSkipTime > this.mVastModel.getSkipoffset() ? this.mSkipTime : this.mVastModel.getSkipoffset();
                } else {
                    this.mSkipTime = this.mExtensions.getSkipTime2();
                }
                createUIComponents();
            }
        }
        if (this.mListener != null) {
            this.mListener.vastShown();
        }
    }

    protected void onStart() {
        VASTLog.d(TAG, "entered onStart --(life cycle event)");
        super.onStart();
    }

    protected void onResume() {
        VASTLog.d(TAG, "entered on onResume --(life cycle event)");
        super.onResume();
    }

    protected void onStop() {
        VASTLog.d(TAG, "entered on onStop --(life cycle event)");
        super.onStop();
    }

    protected void onRestart() {
        VASTLog.d(TAG, "entered on onRestart --(life cycle event)");
        super.onRestart();
        if (!this.mCompanionShown) {
            createUIComponents();
        }
    }

    public void restartVideo() {
        if (this.mCompanionShown) {
            finishActivity();
        } else {
            createUIComponents();
        }
    }

    protected void onPause() {
        VASTLog.d(TAG, "entered on onPause --(life cycle event)");
        super.onPause();
        if (this.mMediaPlayer != null) {
            this.mCurrentVideoPosition = this.mMediaPlayer.getCurrentPosition();
        }
        cleanActivityUp();
    }

    protected void onDestroy() {
        VASTLog.d(TAG, "entered on onDestroy --(life cycle event)");
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        VASTLog.d(TAG, "entered onSaveInstanceState ");
        super.onSaveInstanceState(bundle);
        bundle.putInt("mCurrentVideoPosition", this.mCurrentVideoPosition);
        bundle.putInt(VastIconXmlManager.DURATION, this.duration);
        bundle.putBoolean("mCompanionShown", this.mCompanionShown);
        bundle.putBoolean("canSkip", this.canSkip);
        bundle.putBoolean("isComplained", this.isComplained);
        bundle.putBoolean("mIsProcessedImpressions", this.mIsProcessedImpressions);
        bundle.putBoolean("mIsPlayBackError", this.mIsPlayBackError);
        bundle.putBoolean("mIsCompleted", this.mIsCompleted);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        VASTLog.d(TAG, "in onRestoreInstanceState");
        super.onRestoreInstanceState(bundle);
        this.mCurrentVideoPosition = bundle.getInt("mCurrentVideoPosition");
        this.duration = bundle.getInt(VastIconXmlManager.DURATION);
        this.mCompanionShown = bundle.getBoolean("mCompanionShown");
        this.canSkip = bundle.getBoolean("canSkip");
        this.isComplained = bundle.getBoolean("isComplained");
        this.mIsProcessedImpressions = bundle.getBoolean("mIsProcessedImpressions");
        this.mIsPlayBackError = bundle.getBoolean("mIsPlayBackError");
        this.mIsCompleted = bundle.getBoolean("mIsCompleted");
    }

    private void hideTitleStatusBars() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
    }

    private void createUIComponents() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        createRootLayout(layoutParams);
        createSurface(layoutParams);
        createMediaPlayer();
        createOverlay(layoutParams);
        createSkipTimePanel();
        boolean z = (this.mBanner == null || this.woBanner) ? false : true;
        this.hasBanner = z;
        createBottomPanel();
        if (!this.woBanner) {
            createCompanion();
            if (this.mCompanion != null) {
                this.mCompanionTrackingEventMap = this.mCompanion.getTrackings();
            }
        }
        setContentView(this.mRootLayout);
        createProgressBar();
        if (this.mCompanionShown) {
            showCompanion();
        }
    }

    private void createProgressBar() {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(13);
        this.mProgressBar = new ProgressBar(this);
        this.mProgressBar.setLayoutParams(layoutParams);
        this.mRootLayout.addView(this.mProgressBar);
        this.mProgressBar.setVisibility(8);
    }

    public void showProgressBar() {
        this.mProgressBar.setVisibility(0);
    }

    public void hideProgressBar() {
        this.mProgressBar.setVisibility(8);
    }

    private void createRootLayout(LayoutParams layoutParams) {
        this.mRootLayout = new RelativeLayout(this);
        this.mRootLayout.setLayoutParams(layoutParams);
        this.mRootLayout.setPadding(0, 0, 0, 0);
        this.mRootLayout.setBackgroundColor(-16777216);
    }

    private void createSurface(LayoutParams layoutParams) {
        this.mSurfaceView = new SurfaceView(this);
        this.mSurfaceView.setLayoutParams(layoutParams);
        this.mSurfaceHolder = this.mSurfaceView.getHolder();
        this.mSurfaceHolder.addCallback(this);
        this.mSurfaceHolder.setType(3);
        this.mRootLayout.addView(this.mSurfaceView);
    }

    private void createMediaPlayer() {
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setOnCompletionListener(this);
        this.mMediaPlayer.setOnErrorListener(this);
        this.mMediaPlayer.setOnPreparedListener(this);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this);
        this.mMediaPlayer.setAudioStreamType(3);
    }

    private void createOverlay(LayoutParams layoutParams) {
        this.mOverlay = new RelativeLayout(this);
        this.mOverlay.setLayoutParams(layoutParams);
        this.mOverlay.setPadding(0, 0, 0, 0);
        this.mOverlay.setBackgroundColor(0);
        this.mRootLayout.addView(this.mOverlay);
    }

    private void createBottomPanel() {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, Math.round(30.0f * an.i(this)));
        layoutParams2.addRule(12);
        this.mBottomPanel = new RelativeLayout(this);
        this.mBottomPanel.setLayoutParams(layoutParams2);
        this.mBottomPanel.setPadding(0, 0, 0, 0);
        this.mOverlay.addView(this.mBottomPanel);
        if (this.hasBanner) {
            createBanner();
            this.mBannerTrackingEventMap = this.mBanner.getTrackings();
            processBannerEvent(TRACKING_EVENTS_TYPE.creativeView);
        } else {
            layoutParams = new LayoutParams(-2, -1);
            layoutParams.addRule(15);
            View textView = new TextView(this);
            textView.setPadding(10, 10, 10, 10);
            textView.setBackgroundColor(Color.parseColor("#6b000000"));
            textView.setVisibility(0);
            textView.setTextColor(-1);
            textView.setGravity(16);
            if (this.mExtensions == null) {
                layoutParams.addRule(11);
                textView.setText("Learn more");
                textView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        VASTActivity.this.infoClicked();
                    }
                });
            } else {
                layoutParams.addRule(14);
                if (this.mExtensions.isControls() && this.mExtensions.isClickable()) {
                    if (this.mExtensions.getLinkTxt() != null) {
                        textView.setText(this.mExtensions.getLinkTxt());
                    } else {
                        textView.setText("Learn more");
                    }
                    textView.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            VASTActivity.this.infoClicked();
                        }
                    });
                } else {
                    textView.setOnClickListener(null);
                    textView.setOnLongClickListener(null);
                    textView.setClickable(false);
                    textView.setVisibility(8);
                }
            }
            textView.setLayoutParams(layoutParams);
            this.mBottomPanel.addView(textView);
        }
        this.vastCountdown = new VastLinearCountdown(this);
        layoutParams = new LayoutParams(-1, 5);
        layoutParams.addRule(12);
        this.vastCountdown.setLayoutParams(layoutParams);
        this.vastCountdown.changePercentage(0.0f);
        this.vastCountdown.setVisibility(4);
        this.mBottomPanel.addView(this.vastCountdown);
    }

    @SuppressLint({"SetTextI18n"})
    private void createSkipTimePanel() {
        int round = Math.round(50.0f * an.i(this));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, round);
        layoutParams.addRule(10);
        this.mTimerPanel = new RelativeLayout(this);
        this.mTimerPanel.setLayoutParams(layoutParams);
        this.mTimerPanel.setPadding(0, 0, 0, 0);
        this.mTimerPanel.setVisibility(0);
        layoutParams = new LayoutParams(round, round);
        layoutParams.addRule(11);
        layoutParams.addRule(15);
        this.mSkipButton = new VastCountdown(this);
        this.mSkipButton.setLayoutParams(layoutParams);
        this.mSkipButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (VASTActivity.this.isSkippable()) {
                    VASTActivity.this.closeClicked();
                }
            }
        });
        if (this.canSkip) {
            this.mSkipButton.changePercentage(100, 0);
        }
        if (this.mExtensions != null || this.mType == b.b) {
            this.mSkipButton.setVisibility(8);
        }
        this.mRepeatButton = new ImageView(this);
        this.mRepeatButton.setImageResource(17301540);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(9);
        layoutParams2.addRule(10);
        this.mRepeatButton.setLayoutParams(layoutParams2);
        this.mRepeatButton.setBackgroundColor(Color.parseColor("#6b000000"));
        this.mRepeatButton.setVisibility(8);
        this.mRepeatButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                VASTActivity.this.mCurrentVideoPosition = 0;
                VASTActivity.this.mIsCompleted = false;
                VASTActivity.this.createUIComponents();
            }
        });
        this.mCloseButton = new ImageView(this);
        this.mCloseButton.setImageResource(17301560);
        layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(11);
        layoutParams2.addRule(10);
        this.mCloseButton.setLayoutParams(layoutParams2);
        this.mCloseButton.setBackgroundColor(Color.parseColor("#6b000000"));
        this.mCloseButton.setVisibility(8);
        this.mCloseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                VASTActivity.this.finishActivity();
            }
        });
        this.mTimerPanel.addView(this.mSkipButton);
        this.mTimerPanel.addView(this.mRepeatButton);
        this.mTimerPanel.addView(this.mCloseButton);
        this.mOverlay.addView(this.mTimerPanel);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void createCompanion() {
        if (this.mCompanion != null) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this.mCompanionView = new WebView(this);
            ((WebView) this.mCompanionView).setInitialScale(1);
            ((WebView) this.mCompanionView).getSettings().setJavaScriptEnabled(true);
            ((WebView) this.mCompanionView).getSettings().setLoadWithOverviewMode(true);
            ((WebView) this.mCompanionView).getSettings().setUseWideViewPort(true);
            this.mCompanionView.setScrollContainer(false);
            this.mCompanionView.setVerticalScrollBarEnabled(false);
            this.mCompanionView.setHorizontalScrollBarEnabled(false);
            this.mCompanionView.setScrollBarStyle(33554432);
            this.mCompanionView.setFocusableInTouchMode(false);
            this.mCompanionView.setBackgroundColor(Color.parseColor("#6b000000"));
            if (VERSION.SDK_INT >= 11) {
                this.mCompanionView.setLayerType(1, null);
            }
            ((WebView) this.mCompanionView).setWebViewClient(this.companionWebViewClient);
            ((WebView) this.mCompanionView).setWebChromeClient(this.companionWebChromeClient);
            this.mCompanionView.setOnTouchListener(this.companionOnTouchListener);
            this.mCompanionView.setVisibility(8);
            this.mCompanionView.setLayoutParams(layoutParams);
            String html = this.mCompanion.getHtml(this.mScreenWidth, this.mScreenHeight, this.mScreenDensity);
            if (html != null) {
                ((WebView) this.mCompanionView).loadDataWithBaseURL("", html, "text/html", "utf-8", null);
            }
        } else {
            this.mCompanionView = new RelativeLayout(this);
            this.mCompanionView.setVisibility(8);
        }
        this.bannerCloseButton = new ImageButton(this);
        this.bannerCloseButton.setVisibility(8);
        this.bannerCloseButton.setImageResource(17301560);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(11);
        layoutParams2.addRule(10);
        this.bannerCloseButton.setLayoutParams(layoutParams2);
        this.bannerCloseButton.setBackgroundColor(Color.parseColor("#6b000000"));
        this.bannerCloseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                VASTActivity.this.processCompanionEvent(TRACKING_EVENTS_TYPE.close);
                VASTActivity.this.finishActivity();
            }
        });
        this.mRootLayout.addView(this.mCompanionView);
        this.mRootLayout.addView(this.bannerCloseButton);
    }

    private void showCompanion() {
        this.mProgressBar.setVisibility(8);
        this.mBottomPanel.setVisibility(8);
        this.mTimerPanel.setVisibility(8);
        if (this.mCompanion != null) {
            this.mSurfaceView.setVisibility(8);
        } else if (VERSION.SDK_INT < 10 || this.fileUrl == null || !new File(this.fileUrl.getPath()).exists()) {
            r0 = new LayoutParams(-2, -2);
            r0.addRule(11);
            r0.addRule(12);
            r0.setMargins(10, 10, 10, 10);
            View button = new Button(this);
            button.setText("Learn more");
            button.setLayoutParams(r0);
            button.setBackgroundColor(SupportMenu.CATEGORY_MASK);
            button.setTextColor(-1);
            ((RelativeLayout) this.mCompanionView).addView(button);
            this.mOverlay.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VASTActivity.this.infoClicked();
                    VASTActivity.this.finishActivity();
                }
            });
            this.mCompanionView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VASTActivity.this.infoClicked();
                    VASTActivity.this.finishActivity();
                }
            });
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VASTActivity.this.infoClicked();
                    VASTActivity.this.finishActivity();
                }
            });
        } else {
            try {
                View imageView;
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(this, this.fileUrl);
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime((long) (this.duration * 1000), 2);
                if (frameAtTime != null) {
                    imageView = new ImageView(this);
                    imageView.setImageBitmap(frameAtTime);
                    imageView.setAdjustViewBounds(true);
                    imageView.setScaleType(ScaleType.FIT_CENTER);
                    r0 = new LayoutParams(-1, -1);
                    r0.addRule(13);
                    imageView.setLayoutParams(r0);
                    ((RelativeLayout) this.mCompanionView).addView(imageView);
                    this.mCompanionView.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            VASTActivity.this.infoClicked();
                        }
                    });
                    this.mSurfaceView.setVisibility(8);
                } else {
                    this.mOverlay.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            VASTActivity.this.infoClicked();
                            VASTActivity.this.finishActivity();
                        }
                    });
                }
                r0 = new LayoutParams(-2, -2);
                r0.addRule(11);
                r0.addRule(12);
                r0.setMargins(10, 10, 10, 10);
                imageView = new Button(this);
                imageView.setText("Learn more");
                imageView.setLayoutParams(r0);
                imageView.setBackgroundColor(SupportMenu.CATEGORY_MASK);
                imageView.setTextColor(-1);
                ((RelativeLayout) this.mCompanionView).addView(imageView);
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        VASTActivity.this.infoClicked();
                    }
                });
                mediaMetadataRetriever.release();
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
        cleanUpMediaPlayer();
        this.mCompanionView.setVisibility(0);
        this.bannerCloseButton.setVisibility(0);
        if (this.rtbInfo != null) {
            View createReportButton = createReportButton();
            this.mRootLayout.addView(createReportButton);
            createReportButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (VASTActivity.this.isComplained) {
                        Appodeal.a("Ad was complained before");
                        return;
                    }
                    VASTActivity.this.mRootLayout.addView(VASTActivity.this.createReportView());
                }
            });
        }
        processCompanionEvent(TRACKING_EVENTS_TYPE.creativeView);
        this.mCompanionShown = true;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void createBanner() {
        int round;
        int round2;
        if (an.n(this) && this.mBanner.getWidth() == 728 && this.mBanner.getHeight() == 90) {
            round = Math.round(728.0f * an.i(this));
            round2 = Math.round(90.0f * an.i(this));
        } else {
            round = Math.round(320.0f * an.i(this));
            round2 = Math.round(50.0f * an.i(this));
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(round, round2);
        layoutParams.addRule(13);
        layoutParams.addRule(12);
        layoutParams.setMargins(0, 0, 0, 5);
        this.mBannerView = new WebView(this);
        this.mBannerView.getSettings().setJavaScriptEnabled(true);
        this.mBannerView.setScrollContainer(false);
        this.mBannerView.setVerticalScrollBarEnabled(false);
        this.mBannerView.setHorizontalScrollBarEnabled(false);
        this.mBannerView.setScrollBarStyle(33554432);
        this.mBannerView.setFocusableInTouchMode(false);
        this.mBannerView.setBackgroundColor(0);
        if (VERSION.SDK_INT >= 11) {
            this.mBannerView.setLayerType(1, null);
        }
        this.mBannerView.setWebViewClient(this.companionWebViewClient);
        this.mBannerView.setWebChromeClient(this.companionWebChromeClient);
        this.mBannerView.setOnTouchListener(this.companionOnTouchListener);
        this.mBannerView.setLayoutParams(layoutParams);
        this.mBannerView.setPadding(0, 0, 0, 0);
        String html = this.mBanner.getHtml(round, round2, this.mScreenDensity);
        if (html != null) {
            this.mBannerView.loadDataWithBaseURL("", html, "text/html", "utf-8", null);
        }
        this.mRootLayout.addView(this.mBannerView);
    }

    public void hideBanner() {
        if (this.hasBanner) {
            this.mBannerView.setVisibility(8);
        }
    }

    private View createReportButton() {
        View reportButton = new ReportButton(this);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(50, 50);
        layoutParams.addRule(12, 1);
        layoutParams.addRule(9, 1);
        reportButton.setLayoutParams(layoutParams);
        return reportButton;
    }

    private View createReportView() {
        View reportView = new ReportView((Context) this, this.mRootLayout);
        reportView.registerCallback(this);
        reportView.setBackgroundColor(-1073741824);
        reportView.setInfo(this.rtbInfo);
        try {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, Math.round((getResources().getDisplayMetrics().xdpi / 160.0f) * 50.0f));
            layoutParams.addRule(12);
            reportView.setLayoutParams(layoutParams);
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return reportView;
    }

    private void infoClicked() {
        VASTLog.d(TAG, "entered infoClicked:");
        processClickThroughEvent(this.mVastModel.getVideoClicks().getClickThrough());
        if (this.mExtensions != null) {
            fireUrls(this.mExtensions.getAddClick());
        }
    }

    private void processClickThroughEvent(String str) {
        VASTLog.d(TAG, "entered processClickThroughEvent:");
        VASTLog.d(TAG, "clickThrough url: " + str);
        if (str != null) {
            fireUrls(this.mVastModel.getVideoClicks().getClickTracking());
            if (this.mMediaPlayer != null) {
                if (this.mMediaPlayer.isPlaying()) {
                    this.mMediaPlayer.pause();
                }
                this.mCurrentVideoPosition = this.mMediaPlayer.getCurrentPosition();
            }
            cleanActivityUp();
            if (this.mListener != null) {
                this.mListener.vastClick(str, this);
            }
        }
    }

    private void closeClicked() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.seekTo(this.mMediaPlayer.getDuration());
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            stopQuartileTimer();
            stopVideoProgressTimer();
            stopSkipTimer();
        }
        VASTLog.d(TAG, "entered closeClicked()");
        if (this.mIsPlayBackError) {
            finishActivity();
        } else {
            processEvent(TRACKING_EVENTS_TYPE.close);
            if (this.mExtensions != null) {
                fireUrls(this.mExtensions.getSkipAd());
            }
            if (this.maxDuration > 0 && this.mType == b.b && this.mListener != null) {
                this.mListener.vastComplete();
            }
            finishVAST();
        }
        VASTLog.d(TAG, "leaving closeClicked()");
    }

    public void onBackPressed() {
        VASTLog.d(TAG, "entered onBackPressed");
        if (this.mCompanionShown || this.mIsCompleted) {
            processCompanionEvent(TRACKING_EVENTS_TYPE.close);
            finishActivity();
        }
        if (isSkippable()) {
            closeClicked();
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        VASTLog.d(TAG, "surfaceCreated -- (SurfaceHolder callback)");
        try {
            if (this.mMediaPlayer == null) {
                createMediaPlayer();
            }
            if (!this.mCompanionShown) {
                showProgressBar();
                this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
                String pickedMediaFileURL = this.mVastModel.getPickedMediaFileURL();
                VASTLog.d(TAG, "URL for media file:" + pickedMediaFileURL);
                if (this.fileUrl == null) {
                    this.mMediaPlayer.setDataSource(pickedMediaFileURL);
                } else {
                    this.mMediaPlayer.setDataSource(this, this.fileUrl);
                }
                this.mMediaPlayer.prepareAsync();
            }
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        VASTLog.d(TAG, "entered surfaceChanged -- (SurfaceHolder callback)");
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        VASTLog.d(TAG, "entered surfaceDestroyed -- (SurfaceHolder callback)");
        cleanUpMediaPlayer();
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        VASTLog.d(TAG, "entered onVideoSizeChanged -- (MediaPlayer callback)");
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        VASTLog.d(TAG, "video size: " + this.mVideoWidth + "x" + this.mVideoHeight);
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        VASTLog.d(TAG, "entered onPrepared called --(MediaPlayer callback) ....about to play");
        if (!this.mCompanionShown) {
            calculateAspectRatio();
            this.mMediaPlayer.start();
            processEvent(TRACKING_EVENTS_TYPE.creativeView);
            processEvent(TRACKING_EVENTS_TYPE.fullscreen);
            this.duration = this.mMediaPlayer.getDuration();
            startSkipTimer();
            hideProgressBar();
            if (this.mIsVideoPaused) {
                VASTLog.d(TAG, "pausing video");
                this.mMediaPlayer.pause();
            } else {
                startVideoProgressTimer();
            }
            VASTLog.d(TAG, "current location in video:" + this.mCurrentVideoPosition);
            if (this.mCurrentVideoPosition > 0) {
                VASTLog.d(TAG, "seeking to location:" + this.mCurrentVideoPosition);
                this.mMediaPlayer.seekTo(this.mCurrentVideoPosition);
            }
            VASTLog.d(TAG, "current location in player:" + this.mMediaPlayer.getCurrentPosition());
            if (!this.mIsProcessedImpressions) {
                processImpressions();
            }
            startQuartileTimer();
            if (!this.mMediaPlayer.isPlaying() && !this.mIsVideoPaused) {
                this.mMediaPlayer.start();
            }
        }
    }

    private void calculateAspectRatio() {
        VASTLog.d(TAG, "entered calculateAspectRatio");
        if (this.mVideoWidth == 0 || this.mVideoHeight == 0) {
            VASTLog.w(TAG, "mVideoWidth or mVideoHeight is 0, skipping calculateAspectRatio");
            return;
        }
        VASTLog.d(TAG, "calculating aspect ratio");
        double d = (((double) this.mScreenWidth) * 1.0d) / ((double) this.mVideoWidth);
        double d2 = (((double) this.mScreenHeight) * 1.0d) / ((double) this.mVideoHeight);
        double min = Math.min(d, d2);
        int i = (int) (((double) this.mVideoWidth) * min);
        int i2 = (int) (min * ((double) this.mVideoHeight));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(i, i2);
        layoutParams.addRule(13);
        this.mSurfaceView.setLayoutParams(layoutParams);
        this.mSurfaceHolder.setFixedSize(i, i2);
        VASTLog.d(TAG, " screen size: " + this.mScreenWidth + "x" + this.mScreenHeight);
        VASTLog.d(TAG, " video size:  " + this.mVideoWidth + "x" + this.mVideoHeight);
        VASTLog.d(TAG, " widthRatio:   " + d);
        VASTLog.d(TAG, " heightRatio:   " + d2);
        VASTLog.d(TAG, "surface size: " + i + "x" + i2);
    }

    private void cleanActivityUp() {
        cleanUpMediaPlayer();
        stopQuartileTimer();
        stopVideoProgressTimer();
        stopSkipTimer();
    }

    private void cleanUpMediaPlayer() {
        VASTLog.d(TAG, "entered cleanUpMediaPlayer ");
        if (this.mMediaPlayer != null) {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.setOnCompletionListener(null);
            this.mMediaPlayer.setOnErrorListener(null);
            this.mMediaPlayer.setOnPreparedListener(null);
            this.mMediaPlayer.setOnVideoSizeChangedListener(null);
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        VASTLog.e(TAG, "entered onError -- (MediaPlayer callback)");
        this.mIsPlayBackError = true;
        VASTLog.e(TAG, "Shutting down Activity due to Media Player errors: WHAT:" + i + ": EXTRA:" + i2 + ":");
        cleanUpMediaPlayer();
        processErrorEvent();
        finishVAST();
        return true;
    }

    private void processErrorEvent() {
        VASTLog.d(TAG, "entered processErrorEvent");
        try {
            if (this.mVastModel != null) {
                this.mVastModel.sendError(VASTModel.ERROR_CODE_ERROR_SHOWING);
            }
        } catch (Exception e) {
            VASTLog.e(TAG, e.getMessage());
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        VASTLog.d(TAG, "entered onCOMPLETION -- (MediaPlayer callback)");
        stopVideoProgressTimer();
        this.canSkip = true;
        if (!this.mIsPlayBackError && !this.mIsCompleted) {
            this.mIsCompleted = true;
            processEvent(TRACKING_EVENTS_TYPE.complete);
            if (this.mListener != null) {
                this.mListener.vastComplete();
            }
            finishVAST();
        } else if (this.mIsCompleted) {
            finishVAST();
        }
    }

    private void processImpressions() {
        VASTLog.d(TAG, "entered processImpressions");
        this.mIsProcessedImpressions = true;
        fireUrls(this.mVastModel.getImpressions());
    }

    private void fireUrls(List<String> list) {
        VASTLog.d(TAG, "entered fireUrls");
        if (list != null) {
            for (String str : list) {
                VASTLog.v(TAG, "\tfiring url:" + str);
                HttpTools.httpGetURL(str);
            }
            return;
        }
        VASTLog.d(TAG, "\turl list is null");
    }

    private void startSkipTimer() {
        VASTLog.d(TAG, "entered startSkipTimer");
        if (this.canSkip && this.mRepeatButton.getVisibility() == 8) {
            this.mSkipButton.setVisibility(0);
        }
        if (this.mSkipTime != 0 && this.mType == b.a && this.mMediaPlayer != null && this.mMediaPlayer.isPlaying()) {
            this.mSkipTimer = new Timer();
            this.mSkipTimer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        final int currentPosition = VASTActivity.this.mMediaPlayer.getCurrentPosition();
                        if (currentPosition != 0) {
                            final int access$1500 = (VASTActivity.this.mSkipTime * 1000) - currentPosition;
                            if (VASTActivity.this.mExtensions == null) {
                                VASTActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        VASTActivity.this.mSkipButton.changePercentage(VASTActivity.this.mSkipTime != 0 ? (currentPosition * 100) / (VASTActivity.this.mSkipTime * 1000) : 100, (int) Math.ceil(((double) access$1500) / 1000.0d));
                                    }
                                });
                                if (access$1500 <= 0) {
                                    VASTActivity.this.mSkipTime = 0;
                                    VASTActivity.this.canSkip = true;
                                    cancel();
                                }
                            } else if (access$1500 <= 0) {
                                if (VASTActivity.this.mExtensions.isControls()) {
                                    VASTActivity.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            VASTActivity.this.mSkipButton.changePercentage(100, 0);
                                            VASTActivity.this.mSkipButton.setVisibility(0);
                                        }
                                    });
                                    VASTActivity.this.mSkipTime = 0;
                                    VASTActivity.this.canSkip = true;
                                }
                                cancel();
                            }
                        }
                    } catch (Exception e) {
                        Appodeal.a("mediaPlayer.getCurrentPosition exception: " + e.getMessage());
                        cancel();
                    }
                }
            }, 0, 50);
        }
    }

    private void stopSkipTimer() {
        VASTLog.d(TAG, "entered stopSkipTimer");
        if (this.mSkipTimer != null) {
            this.mSkipTimer.cancel();
            this.mSkipTimer = null;
        }
    }

    private void startQuartileTimer() {
        VASTLog.d(TAG, "entered startQuartileTimer");
        stopQuartileTimer();
        if (this.mIsCompleted) {
            VASTLog.d(TAG, "ending quartileTimer because the video has been replayed");
            return;
        }
        final int duration = this.mMediaPlayer.getDuration();
        this.mTrackingEventTimer = new Timer();
        this.mTrackingEventTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    int currentPosition = VASTActivity.this.mMediaPlayer.getCurrentPosition();
                    if (currentPosition != 0) {
                        int i = (currentPosition * 100) / duration;
                        if (currentPosition > VASTActivity.this.maxDuration && VASTActivity.this.maxDuration > 0 && VASTActivity.this.mType == b.b) {
                            VASTActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    VASTActivity.this.mSkipButton.changePercentage(100, 0);
                                    VASTActivity.this.mSkipButton.setVisibility(0);
                                    VASTActivity.this.canSkip = true;
                                }
                            });
                        }
                        if (i >= VASTActivity.this.mQuartile * 25) {
                            if (VASTActivity.this.mQuartile == 0) {
                                VASTLog.i(VASTActivity.TAG, "Video at start: (" + i + "%)");
                                VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.start);
                            } else if (VASTActivity.this.mQuartile == 1) {
                                VASTLog.i(VASTActivity.TAG, "Video at first quartile: (" + i + "%)");
                                VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.firstQuartile);
                            } else if (VASTActivity.this.mQuartile == 2) {
                                VASTLog.i(VASTActivity.TAG, "Video at midpoint: (" + i + "%)");
                                VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.midpoint);
                            } else if (VASTActivity.this.mQuartile == 3) {
                                VASTLog.i(VASTActivity.TAG, "Video at third quartile: (" + i + "%)");
                                VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.thirdQuartile);
                                VASTActivity.this.stopQuartileTimer();
                            }
                            VASTActivity.this.mQuartile = VASTActivity.this.mQuartile + 1;
                        }
                    }
                } catch (Exception e) {
                    VASTLog.w(VASTActivity.TAG, "mediaPlayer.getCurrentPosition exception: " + e.getMessage());
                    cancel();
                }
            }
        }, 0, QUARTILE_TIMER_INTERVAL);
    }

    private void stopQuartileTimer() {
        if (this.mTrackingEventTimer != null) {
            this.mTrackingEventTimer.cancel();
            this.mTrackingEventTimer = null;
        }
    }

    private void startVideoProgressTimer() {
        VASTLog.d(TAG, "entered startVideoProgressTimer");
        VASTLog.v(TAG, "video progressing (start)");
        this.mStartVideoProgressTimer = new Timer();
        this.mVideoProgressTracker = new LinkedList();
        this.mStartVideoProgressTimer.schedule(new TimerTask() {
            int errorCount = 0;
            int maxAmountInList = 19;

            public void run() {
                if (VASTActivity.this.mMediaPlayer != null) {
                    int intValue;
                    if (VASTActivity.this.mVideoProgressTracker.size() == 2 && ((Integer) VASTActivity.this.mVideoProgressTracker.getFirst()).intValue() > ((Integer) VASTActivity.this.mVideoProgressTracker.getLast()).intValue()) {
                        VASTLog.e(VASTActivity.TAG, "video progressing (seek error)");
                        VASTActivity.this.mVideoProgressTracker.removeFirst();
                    }
                    if (VASTActivity.this.mVideoProgressTracker.size() == this.maxAmountInList) {
                        int intValue2 = ((Integer) VASTActivity.this.mVideoProgressTracker.getFirst()).intValue();
                        intValue = ((Integer) VASTActivity.this.mVideoProgressTracker.getLast()).intValue();
                        VASTLog.v(VASTActivity.TAG, "video progressing (position:" + intValue + ", first: " + intValue2 + ")");
                        if (intValue > intValue2) {
                            VASTActivity.this.mVideoProgressTracker.removeFirst();
                        } else {
                            this.errorCount++;
                            if (this.errorCount >= 3) {
                                VASTLog.e(VASTActivity.TAG, "video progressing (detected video hang)");
                                VASTActivity.this.mIsPlayBackError = true;
                                VASTActivity.this.stopVideoProgressTimer();
                                VASTActivity.this.processErrorEvent();
                                VASTActivity.this.finishActivity();
                            }
                        }
                    }
                    try {
                        intValue = VASTActivity.this.mMediaPlayer.getCurrentPosition();
                        VASTActivity.this.mVideoProgressTracker.addLast(Integer.valueOf(intValue));
                        if (VASTActivity.this.duration != 0 && intValue > 0) {
                            VASTLog.v(VASTActivity.TAG, "video percentage:" + Math.round((float) ((intValue * 100) / VASTActivity.this.duration)) + " remaining time: " + Math.round((float) ((VASTActivity.this.duration - intValue) / 1000)));
                            VASTActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    VASTActivity.this.vastCountdown.changePercentage((100.0f * ((float) intValue)) / ((float) VASTActivity.this.duration));
                                    VASTActivity.this.vastCountdown.setVisibility(0);
                                }
                            });
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }, 0, 50);
    }

    private void stopVideoProgressTimer() {
        VASTLog.d(TAG, "entered stopVideoProgressTimer");
        if (this.mStartVideoProgressTimer != null) {
            this.mStartVideoProgressTimer.cancel();
        }
    }

    private void processEvent(TRACKING_EVENTS_TYPE tracking_events_type) {
        VASTLog.i(TAG, "entered Processing Event: " + tracking_events_type);
        fireUrls((List) this.mTrackingEventMap.get(tracking_events_type));
    }

    private void processCompanionEvent(TRACKING_EVENTS_TYPE tracking_events_type) {
        if (this.mCompanion != null) {
            VASTLog.i(TAG, "entered Processing Event: " + tracking_events_type);
            fireUrls((List) this.mCompanionTrackingEventMap.get(tracking_events_type));
        }
    }

    private void processBannerEvent(TRACKING_EVENTS_TYPE tracking_events_type) {
        if (this.hasBanner) {
            VASTLog.i(TAG, "entered Processing Event: " + tracking_events_type);
            fireUrls((List) this.mBannerTrackingEventMap.get(tracking_events_type));
        }
    }

    private void finishVAST() {
        if (this.mExtensions != null) {
            finishActivity();
        } else if (!this.woBanner) {
            VASTLog.i(TAG, "show companion");
            showCompanion();
            hideBanner();
        } else if ((this.mIsCompleted || this.mIsPlayBackError) && !this.autoClose) {
            showCloseButton();
        } else {
            finishActivity();
        }
    }

    private void showCloseButton() {
        this.mSkipButton.setVisibility(8);
        this.mCloseButton.setVisibility(0);
        this.mRepeatButton.setVisibility(0);
        this.canSkip = true;
    }

    private void finishActivity() {
        if (this.mListener != null) {
            this.mListener.vastDismiss();
        }
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    VASTActivity.this.getWindow().clearFlags(128);
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        });
        finish();
    }

    private boolean isSkippable() {
        return this.canSkip;
    }

    public void wasComplained() {
        this.isComplained = true;
    }
}
