package com.yalantis.ucrop;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.UCrop.Options;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.model.AspectRatio;
import com.yalantis.ucrop.util.SelectedStateListDrawable;
import com.yalantis.ucrop.view.CropImageView;
import com.yalantis.ucrop.view.GestureCropImageView;
import com.yalantis.ucrop.view.OverlayView;
import com.yalantis.ucrop.view.TransformImageView.TransformImageListener;
import com.yalantis.ucrop.view.UCropView;
import com.yalantis.ucrop.view.widget.AspectRatioTextView;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class UCropActivity extends AppCompatActivity {
    public static final int ALL = 3;
    public static final CompressFormat DEFAULT_COMPRESS_FORMAT = CompressFormat.JPEG;
    public static final int DEFAULT_COMPRESS_QUALITY = 90;
    public static final int NONE = 0;
    public static final int ROTATE = 2;
    private static final int ROTATE_WIDGET_SENSITIVITY_COEFFICIENT = 42;
    public static final int SCALE = 1;
    private static final int SCALE_WIDGET_SENSITIVITY_COEFFICIENT = 15000;
    private static final int TABS_COUNT = 3;
    private static final String TAG = "UCropActivity";
    private int mActiveWidgetColor;
    private int[] mAllowedGestures = new int[]{1, 2, 3};
    private View mBlockingView;
    private CompressFormat mCompressFormat = DEFAULT_COMPRESS_FORMAT;
    private int mCompressQuality = 90;
    private List<ViewGroup> mCropAspectRatioViews = new ArrayList();
    private GestureCropImageView mGestureCropImageView;
    private TransformImageListener mImageListener = new TransformImageListener() {
        public void onRotate(float currentAngle) {
            UCropActivity.this.setAngleText(currentAngle);
        }

        public void onScale(float currentScale) {
            UCropActivity.this.setScaleText(currentScale);
        }

        public void onLoadComplete() {
            UCropActivity.this.mUCropView.animate().alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(300).setInterpolator(new AccelerateInterpolator());
            UCropActivity.this.mBlockingView.setClickable(false);
            UCropActivity.this.mShowLoader = false;
            UCropActivity.this.supportInvalidateOptionsMenu();
        }

        public void onLoadFailure(@NonNull Exception e) {
            UCropActivity.this.setResultError(e);
            UCropActivity.this.finish();
        }
    };
    private ViewGroup mLayoutAspectRatio;
    private ViewGroup mLayoutRotate;
    private ViewGroup mLayoutScale;
    private int mLogoColor;
    private OverlayView mOverlayView;
    private boolean mShowBottomControls;
    private boolean mShowLoader = true;
    private final OnClickListener mStateClickListener = new OnClickListener() {
        public void onClick(View v) {
            if (!v.isSelected()) {
                UCropActivity.this.setWidgetState(v.getId());
            }
        }
    };
    private int mStatusBarColor;
    private TextView mTextViewRotateAngle;
    private TextView mTextViewScalePercent;
    private int mToolbarColor;
    private String mToolbarTitle;
    private int mToolbarWidgetColor;
    private UCropView mUCropView;
    private ViewGroup mWrapperStateAspectRatio;
    private ViewGroup mWrapperStateRotate;
    private ViewGroup mWrapperStateScale;

    @Retention(RetentionPolicy.SOURCE)
    public @interface GestureTypes {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucrop_activity_photobox);
        Intent intent = getIntent();
        setupViews(intent);
        setImageData(intent);
        setInitialState();
        addBlockingView();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ucrop_menu_activity, menu);
        MenuItem menuItemLoader = menu.findItem(R.id.menu_loader);
        Drawable menuItemLoaderIcon = menuItemLoader.getIcon();
        if (menuItemLoaderIcon != null) {
            try {
                menuItemLoaderIcon.mutate();
                menuItemLoaderIcon.setColorFilter(this.mToolbarWidgetColor, Mode.SRC_ATOP);
                menuItemLoader.setIcon(menuItemLoaderIcon);
            } catch (IllegalStateException e) {
                Log.e(TAG, String.format("%s - %s", new Object[]{e.getMessage(), getString(R.string.ucrop_mutate_exception_hint)}));
            }
            ((Animatable) menuItemLoader.getIcon()).start();
        }
        MenuItem menuItemCrop = menu.findItem(R.id.menu_crop);
        Drawable menuItemCropIcon = menuItemCrop.getIcon();
        if (menuItemCropIcon != null) {
            menuItemCropIcon.mutate();
            menuItemCropIcon.setColorFilter(this.mToolbarWidgetColor, Mode.SRC_ATOP);
            menuItemCrop.setIcon(menuItemCropIcon);
        }
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_crop).setVisible(!this.mShowLoader);
        menu.findItem(R.id.menu_loader).setVisible(this.mShowLoader);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_crop) {
            cropAndSaveImage();
        } else if (item.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStop() {
        super.onStop();
        if (this.mGestureCropImageView != null) {
            this.mGestureCropImageView.cancelAllAnimations();
        }
    }

    private void setImageData(@NonNull Intent intent) {
        Uri inputUri = (Uri) intent.getParcelableExtra(UCrop.EXTRA_INPUT_URI);
        Uri outputUri = (Uri) intent.getParcelableExtra(UCrop.EXTRA_OUTPUT_URI);
        processOptions(intent);
        if (inputUri == null || outputUri == null) {
            setResultError(new NullPointerException(getString(R.string.ucrop_error_input_data_is_absent)));
            finish();
            return;
        }
        try {
            this.mGestureCropImageView.setImageUri(inputUri, outputUri);
        } catch (Exception e) {
            setResultError(e);
            finish();
        }
    }

    private void processOptions(@NonNull Intent intent) {
        String compressionFormatName = intent.getStringExtra(Options.EXTRA_COMPRESSION_FORMAT_NAME);
        CompressFormat compressFormat = null;
        if (!TextUtils.isEmpty(compressionFormatName)) {
            compressFormat = CompressFormat.valueOf(compressionFormatName);
        }
        if (compressFormat == null) {
            compressFormat = DEFAULT_COMPRESS_FORMAT;
        }
        this.mCompressFormat = compressFormat;
        this.mCompressQuality = intent.getIntExtra(Options.EXTRA_COMPRESSION_QUALITY, 90);
        int[] allowedGestures = intent.getIntArrayExtra(Options.EXTRA_ALLOWED_GESTURES);
        if (allowedGestures != null && allowedGestures.length == 3) {
            this.mAllowedGestures = allowedGestures;
        }
        this.mGestureCropImageView.setMaxBitmapSize(intent.getIntExtra(Options.EXTRA_MAX_BITMAP_SIZE, 0));
        this.mGestureCropImageView.setMaxScaleMultiplier(intent.getFloatExtra(Options.EXTRA_MAX_SCALE_MULTIPLIER, CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER));
        this.mGestureCropImageView.setImageToWrapCropBoundsAnimDuration((long) intent.getIntExtra(Options.EXTRA_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 500));
        this.mOverlayView.setFreestyleCropEnabled(intent.getBooleanExtra(Options.EXTRA_FREE_STYLE_CROP, false));
        this.mOverlayView.setDimmedColor(intent.getIntExtra(Options.EXTRA_DIMMED_LAYER_COLOR, getResources().getColor(R.color.ucrop_color_default_dimmed)));
        this.mOverlayView.setOvalDimmedLayer(intent.getBooleanExtra(Options.EXTRA_OVAL_DIMMED_LAYER, false));
        this.mOverlayView.setShowCropFrame(intent.getBooleanExtra(Options.EXTRA_SHOW_CROP_FRAME, true));
        this.mOverlayView.setCropFrameColor(intent.getIntExtra(Options.EXTRA_CROP_FRAME_COLOR, getResources().getColor(R.color.ucrop_color_default_crop_frame)));
        this.mOverlayView.setCropFrameStrokeWidth(intent.getIntExtra(Options.EXTRA_CROP_FRAME_STROKE_WIDTH, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_frame_stoke_width)));
        this.mOverlayView.setShowCropGrid(intent.getBooleanExtra(Options.EXTRA_SHOW_CROP_GRID, true));
        this.mOverlayView.setCropGridRowCount(intent.getIntExtra(Options.EXTRA_CROP_GRID_ROW_COUNT, 2));
        this.mOverlayView.setCropGridColumnCount(intent.getIntExtra(Options.EXTRA_CROP_GRID_COLUMN_COUNT, 2));
        this.mOverlayView.setCropGridColor(intent.getIntExtra(Options.EXTRA_CROP_GRID_COLOR, getResources().getColor(R.color.ucrop_color_default_crop_grid)));
        this.mOverlayView.setCropGridStrokeWidth(intent.getIntExtra(Options.EXTRA_CROP_GRID_STROKE_WIDTH, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_grid_stoke_width)));
        int cropPadding = intent.getIntExtra(Options.EXTRA_CROP_PADDING, getResources().getDimensionPixelSize(R.dimen.ucrop_padding_crop_frame));
        this.mOverlayView.setPadding(cropPadding, cropPadding, cropPadding, cropPadding);
        this.mGestureCropImageView.setPadding(cropPadding, cropPadding, cropPadding, cropPadding);
        this.mGestureCropImageView.post(new Runnable() {
            public void run() {
                Log.d("CALVINATOR", "Scale : " + UCropActivity.this.mGestureCropImageView.getCurrentScale());
                UCropActivity.this.mGestureCropImageView.zoomInImage(1.5f);
            }
        });
        float aspectRatioX = intent.getFloatExtra(UCrop.EXTRA_ASPECT_RATIO_X, 0.0f);
        float aspectRatioY = intent.getFloatExtra(UCrop.EXTRA_ASPECT_RATIO_Y, 0.0f);
        int aspectRationSelectedByDefault = intent.getIntExtra(Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, 0);
        ArrayList<AspectRatio> aspectRatioList = intent.getParcelableArrayListExtra(Options.EXTRA_ASPECT_RATIO_OPTIONS);
        if (aspectRatioX > 0.0f && aspectRatioY > 0.0f) {
            if (this.mWrapperStateAspectRatio != null) {
                this.mWrapperStateAspectRatio.setVisibility(8);
            }
            this.mGestureCropImageView.setTargetAspectRatio(aspectRatioX / aspectRatioY);
        } else if (aspectRatioList == null || aspectRationSelectedByDefault >= aspectRatioList.size()) {
            this.mGestureCropImageView.setTargetAspectRatio(0.0f);
        } else {
            this.mGestureCropImageView.setTargetAspectRatio(((AspectRatio) aspectRatioList.get(aspectRationSelectedByDefault)).getAspectRatioX() / ((AspectRatio) aspectRatioList.get(aspectRationSelectedByDefault)).getAspectRatioY());
        }
        int maxSizeX = intent.getIntExtra(UCrop.EXTRA_MAX_SIZE_X, 0);
        int maxSizeY = intent.getIntExtra(UCrop.EXTRA_MAX_SIZE_Y, 0);
        if (maxSizeX > 0 && maxSizeY > 0) {
            this.mGestureCropImageView.setMaxResultImageSizeX(maxSizeX);
            this.mGestureCropImageView.setMaxResultImageSizeY(maxSizeY);
        }
    }

    private void setupViews(@NonNull Intent intent) {
        boolean z;
        this.mStatusBarColor = intent.getIntExtra(Options.EXTRA_STATUS_BAR_COLOR, ContextCompat.getColor(this, R.color.ucrop_color_statusbar));
        this.mToolbarColor = intent.getIntExtra(Options.EXTRA_TOOL_BAR_COLOR, ContextCompat.getColor(this, R.color.ucrop_color_toolbar));
        this.mActiveWidgetColor = intent.getIntExtra(Options.EXTRA_UCROP_COLOR_WIDGET_ACTIVE, ContextCompat.getColor(this, R.color.ucrop_color_widget_active));
        this.mToolbarWidgetColor = intent.getIntExtra(Options.EXTRA_UCROP_WIDGET_COLOR_TOOLBAR, ContextCompat.getColor(this, R.color.ucrop_color_toolbar_widget));
        this.mToolbarTitle = intent.getStringExtra(Options.EXTRA_UCROP_TITLE_TEXT_TOOLBAR);
        this.mToolbarTitle = !TextUtils.isEmpty(this.mToolbarTitle) ? this.mToolbarTitle : getResources().getString(R.string.ucrop_label_edit_photo);
        this.mLogoColor = intent.getIntExtra(Options.EXTRA_UCROP_LOGO_COLOR, ContextCompat.getColor(this, R.color.ucrop_color_default_logo));
        if (intent.getBooleanExtra(Options.EXTRA_HIDE_BOTTOM_CONTROLS, false)) {
            z = false;
        } else {
            z = true;
        }
        this.mShowBottomControls = z;
        setupAppBar();
        initiateRootViews();
        if (this.mShowBottomControls) {
            View.inflate(this, R.layout.ucrop_controls, (ViewGroup) findViewById(R.id.ucrop_photobox));
            this.mWrapperStateAspectRatio = (ViewGroup) findViewById(R.id.state_aspect_ratio);
            this.mWrapperStateAspectRatio.setOnClickListener(this.mStateClickListener);
            this.mWrapperStateRotate = (ViewGroup) findViewById(R.id.state_rotate);
            this.mWrapperStateRotate.setOnClickListener(this.mStateClickListener);
            this.mWrapperStateScale = (ViewGroup) findViewById(R.id.state_scale);
            this.mWrapperStateScale.setOnClickListener(this.mStateClickListener);
            this.mLayoutAspectRatio = (ViewGroup) findViewById(R.id.layout_aspect_ratio);
            this.mLayoutRotate = (ViewGroup) findViewById(R.id.layout_rotate_wheel);
            this.mLayoutScale = (ViewGroup) findViewById(R.id.layout_scale_wheel);
            setupAspectRatioWidget(intent);
            setupRotateWidget();
            setupScaleWidget();
            setupStatesWrapper();
        }
    }

    private void setupAppBar() {
        setStatusBarColor(this.mStatusBarColor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(this.mToolbarColor);
        toolbar.setTitleTextColor(this.mToolbarWidgetColor);
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setTextColor(this.mToolbarWidgetColor);
        toolbarTitle.setText(this.mToolbarTitle);
        Drawable stateButtonDrawable = ContextCompat.getDrawable(this, R.drawable.ucrop_ic_cross).mutate();
        stateButtonDrawable.setColorFilter(this.mToolbarWidgetColor, Mode.SRC_ATOP);
        toolbar.setNavigationIcon(stateButtonDrawable);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initiateRootViews() {
        this.mUCropView = (UCropView) findViewById(R.id.ucrop);
        this.mGestureCropImageView = this.mUCropView.getCropImageView();
        this.mOverlayView = this.mUCropView.getOverlayView();
        this.mGestureCropImageView.setTransformImageListener(this.mImageListener);
        ((ImageView) findViewById(R.id.image_view_logo)).setColorFilter(this.mLogoColor, Mode.SRC_ATOP);
    }

    private void setupStatesWrapper() {
        ImageView stateScaleImageView = (ImageView) findViewById(R.id.image_view_state_scale);
        ImageView stateRotateImageView = (ImageView) findViewById(R.id.image_view_state_rotate);
        ImageView stateAspectRatioImageView = (ImageView) findViewById(R.id.image_view_state_aspect_ratio);
        stateScaleImageView.setImageDrawable(new SelectedStateListDrawable(stateScaleImageView.getDrawable(), this.mActiveWidgetColor));
        stateRotateImageView.setImageDrawable(new SelectedStateListDrawable(stateRotateImageView.getDrawable(), this.mActiveWidgetColor));
        stateAspectRatioImageView.setImageDrawable(new SelectedStateListDrawable(stateAspectRatioImageView.getDrawable(), this.mActiveWidgetColor));
    }

    @TargetApi(21)
    private void setStatusBarColor(@ColorInt int color) {
        if (VERSION.SDK_INT >= 21 && getWindow() != null) {
            getWindow().setStatusBarColor(color);
        }
    }

    private void setupAspectRatioWidget(@NonNull Intent intent) {
        int aspectRationSelectedByDefault = intent.getIntExtra(Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, 0);
        ArrayList<AspectRatio> aspectRatioList = intent.getParcelableArrayListExtra(Options.EXTRA_ASPECT_RATIO_OPTIONS);
        if (aspectRatioList == null || aspectRatioList.isEmpty()) {
            aspectRationSelectedByDefault = 2;
            aspectRatioList = new ArrayList();
            aspectRatioList.add(new AspectRatio(null, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            aspectRatioList.add(new AspectRatio(null, 3.0f, 4.0f));
            aspectRatioList.add(new AspectRatio(getString(R.string.ucrop_label_original).toUpperCase(), 0.0f, 0.0f));
            aspectRatioList.add(new AspectRatio(null, 3.0f, 2.0f));
            aspectRatioList.add(new AspectRatio(null, 16.0f, 9.0f));
        }
        LinearLayout wrapperAspectRatioList = (LinearLayout) findViewById(R.id.layout_aspect_ratio);
        LayoutParams lp = new LayoutParams(0, -1);
        lp.weight = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        Iterator it = aspectRatioList.iterator();
        while (it.hasNext()) {
            AspectRatio aspectRatio = (AspectRatio) it.next();
            FrameLayout wrapperAspectRatio = (FrameLayout) getLayoutInflater().inflate(R.layout.ucrop_aspect_ratio, null);
            wrapperAspectRatio.setLayoutParams(lp);
            AspectRatioTextView aspectRatioTextView = (AspectRatioTextView) wrapperAspectRatio.getChildAt(0);
            aspectRatioTextView.setActiveColor(this.mActiveWidgetColor);
            aspectRatioTextView.setAspectRatio(aspectRatio);
            wrapperAspectRatioList.addView(wrapperAspectRatio);
            this.mCropAspectRatioViews.add(wrapperAspectRatio);
        }
        ((ViewGroup) this.mCropAspectRatioViews.get(aspectRationSelectedByDefault)).setSelected(true);
        for (ViewGroup cropAspectRatioView : this.mCropAspectRatioViews) {
            cropAspectRatioView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    UCropActivity.this.mGestureCropImageView.setTargetAspectRatio(((AspectRatioTextView) ((ViewGroup) v).getChildAt(0)).getAspectRatio(v.isSelected()));
                    UCropActivity.this.mGestureCropImageView.setImageToWrapCropBounds();
                    if (!v.isSelected()) {
                        for (View cropAspectRatioView : UCropActivity.this.mCropAspectRatioViews) {
                            cropAspectRatioView.setSelected(cropAspectRatioView == v);
                        }
                    }
                }
            });
        }
    }

    private void setupRotateWidget() {
        this.mTextViewRotateAngle = (TextView) findViewById(R.id.text_view_rotate);
        ((HorizontalProgressWheelView) findViewById(R.id.rotate_scroll_wheel)).setScrollingListener(new ScrollingListener() {
            public void onScroll(float delta, float totalDistance) {
                UCropActivity.this.mGestureCropImageView.postRotate(delta / 42.0f);
            }

            public void onScrollEnd() {
                UCropActivity.this.mGestureCropImageView.setImageToWrapCropBounds();
            }

            public void onScrollStart() {
                UCropActivity.this.mGestureCropImageView.cancelAllAnimations();
            }
        });
        ((HorizontalProgressWheelView) findViewById(R.id.rotate_scroll_wheel)).setMiddleLineColor(this.mActiveWidgetColor);
        findViewById(R.id.wrapper_reset_rotate).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                UCropActivity.this.resetRotation();
            }
        });
        findViewById(R.id.wrapper_rotate_by_angle).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                UCropActivity.this.rotateByAngle(90);
            }
        });
    }

    private void setupScaleWidget() {
        this.mTextViewScalePercent = (TextView) findViewById(R.id.text_view_scale);
        ((HorizontalProgressWheelView) findViewById(R.id.scale_scroll_wheel)).setScrollingListener(new ScrollingListener() {
            public void onScroll(float delta, float totalDistance) {
                if (delta > 0.0f) {
                    UCropActivity.this.mGestureCropImageView.zoomInImage(UCropActivity.this.mGestureCropImageView.getCurrentScale() + (((UCropActivity.this.mGestureCropImageView.getMaxScale() - UCropActivity.this.mGestureCropImageView.getMinScale()) / 15000.0f) * delta));
                } else {
                    UCropActivity.this.mGestureCropImageView.zoomOutImage(UCropActivity.this.mGestureCropImageView.getCurrentScale() + (((UCropActivity.this.mGestureCropImageView.getMaxScale() - UCropActivity.this.mGestureCropImageView.getMinScale()) / 15000.0f) * delta));
                }
            }

            public void onScrollEnd() {
                UCropActivity.this.mGestureCropImageView.setImageToWrapCropBounds();
            }

            public void onScrollStart() {
                UCropActivity.this.mGestureCropImageView.cancelAllAnimations();
            }
        });
        ((HorizontalProgressWheelView) findViewById(R.id.scale_scroll_wheel)).setMiddleLineColor(this.mActiveWidgetColor);
    }

    private void setAngleText(float angle) {
        if (this.mTextViewRotateAngle != null) {
            this.mTextViewRotateAngle.setText(String.format(Locale.getDefault(), "%.1f°", new Object[]{Float.valueOf(angle)}));
        }
    }

    private void setScaleText(float scale) {
        if (this.mTextViewScalePercent != null) {
            this.mTextViewScalePercent.setText(String.format(Locale.getDefault(), "%d%%", new Object[]{Integer.valueOf((int) (100.0f * scale))}));
        }
    }

    private void resetRotation() {
        this.mGestureCropImageView.postRotate(-this.mGestureCropImageView.getCurrentAngle());
        this.mGestureCropImageView.setImageToWrapCropBounds();
    }

    private void rotateByAngle(int angle) {
        this.mGestureCropImageView.postRotate((float) angle);
        this.mGestureCropImageView.setImageToWrapCropBounds();
    }

    private void setInitialState() {
        if (!this.mShowBottomControls) {
            this.mGestureCropImageView.setScaleEnabled(true);
            this.mGestureCropImageView.setRotateEnabled(true);
        } else if (this.mWrapperStateAspectRatio.getVisibility() == 0) {
            setWidgetState(R.id.state_aspect_ratio);
        } else {
            setWidgetState(R.id.state_scale);
        }
    }

    private void setWidgetState(@IdRes int stateViewId) {
        int i = 8;
        if (this.mShowBottomControls) {
            boolean z;
            int i2;
            this.mWrapperStateAspectRatio.setSelected(stateViewId == R.id.state_aspect_ratio);
            ViewGroup viewGroup = this.mWrapperStateRotate;
            if (stateViewId == R.id.state_rotate) {
                z = true;
            } else {
                z = false;
            }
            viewGroup.setSelected(z);
            viewGroup = this.mWrapperStateScale;
            if (stateViewId == R.id.state_scale) {
                z = true;
            } else {
                z = false;
            }
            viewGroup.setSelected(z);
            viewGroup = this.mLayoutAspectRatio;
            if (stateViewId == R.id.state_aspect_ratio) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            viewGroup.setVisibility(i2);
            viewGroup = this.mLayoutRotate;
            if (stateViewId == R.id.state_rotate) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            viewGroup.setVisibility(i2);
            ViewGroup viewGroup2 = this.mLayoutScale;
            if (stateViewId == R.id.state_scale) {
                i = 0;
            }
            viewGroup2.setVisibility(i);
            if (stateViewId == R.id.state_scale) {
                setAllowedGestures(0);
            } else if (stateViewId == R.id.state_rotate) {
                setAllowedGestures(1);
            } else {
                setAllowedGestures(2);
            }
        }
    }

    private void setAllowedGestures(int tab) {
        boolean z;
        boolean z2 = false;
        GestureCropImageView gestureCropImageView = this.mGestureCropImageView;
        if (this.mAllowedGestures[tab] == 3 || this.mAllowedGestures[tab] == 1) {
            z = true;
        } else {
            z = false;
        }
        gestureCropImageView.setScaleEnabled(z);
        GestureCropImageView gestureCropImageView2 = this.mGestureCropImageView;
        if (this.mAllowedGestures[tab] == 3 || this.mAllowedGestures[tab] == 2) {
            z2 = true;
        }
        gestureCropImageView2.setRotateEnabled(z2);
    }

    private void addBlockingView() {
        if (this.mBlockingView == null) {
            this.mBlockingView = new View(this);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -1);
            lp.addRule(3, R.id.toolbar);
            this.mBlockingView.setLayoutParams(lp);
            this.mBlockingView.setClickable(true);
        }
        ((RelativeLayout) findViewById(R.id.ucrop_photobox)).addView(this.mBlockingView);
    }

    protected void cropAndSaveImage() {
        this.mBlockingView.setClickable(true);
        this.mShowLoader = true;
        supportInvalidateOptionsMenu();
        this.mGestureCropImageView.cropAndSaveImage(this.mCompressFormat, this.mCompressQuality, new BitmapCropCallback() {
            public void onBitmapCropped(@NonNull Uri resultUri) {
                UCropActivity.this.setResultUri(resultUri, UCropActivity.this.mGestureCropImageView.getTargetAspectRatio());
                UCropActivity.this.finish();
            }

            public void onCropFailure(@NonNull Throwable t) {
                UCropActivity.this.setResultError(t);
                UCropActivity.this.finish();
            }
        });
    }

    protected void setResultUri(Uri uri, float resultAspectRatio) {
        setResult(-1, new Intent().putExtra(UCrop.EXTRA_OUTPUT_URI, uri).putExtra(UCrop.EXTRA_OUTPUT_CROP_ASPECT_RATIO, resultAspectRatio));
    }

    protected void setResultError(Throwable throwable) {
        setResult(96, new Intent().putExtra(UCrop.EXTRA_ERROR, throwable));
    }
}
