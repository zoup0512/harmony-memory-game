package com.my.target.core.ui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.my.target.Tracer;
import com.my.target.core.models.banners.f;
import com.my.target.core.utils.l;
import com.my.target.nativeads.factories.NativeViewsFactory;
import com.my.target.nativeads.views.MediaAdView;
import com.my.target.nativeads.views.StarsRatingView;

public abstract class AbstractNativeAdView extends RelativeLayout {
    protected static final int AD_ID = 262;
    protected static final int AGE_ID = 256;
    protected static final int COLOR_PLACEHOLDER_GRAY = -1118482;
    protected static final int CTA_ID = 266;
    protected static final int DESC_ID = 261;
    protected static final int ICON_ID = 257;
    protected static final int IMAGE_ID = 263;
    protected static final int LABELS_ID = 258;
    protected static final int RATING_ID = 269;
    protected static final int STARS_ID = 267;
    protected static final int TITLE_2_ID = 264;
    protected static final int TITLE_ID = 259;
    protected static final int URL_2_ID = 265;
    protected static final int URL_ID = 260;
    protected static final int VOTES_ID = 268;
    protected final TextView advertisingLabel;
    protected LayoutParams advertisingLabelParams;
    protected final BorderedTextView ageRestrictionLabel;
    protected LayoutParams ageRestrictionParams;
    protected f banner;
    protected final Button ctaButton;
    protected LayoutParams ctaParams;
    protected final TextView descriptionLabel;
    protected LayoutParams descriptionLabelParams;
    protected final TextView disclaimerLabel;
    protected LayoutParams disclaimerParams;
    protected LayoutParams iconImageParams;
    protected final CacheImageView iconImageView;
    protected final LinearLayout labelsLayout;
    protected LayoutParams labelsLayoutParams;
    protected final MediaAdView mediaAdView;
    protected LayoutParams mediaAdViewParams;
    protected final LinearLayout ratingLayout;
    protected LayoutParams ratingParams;
    protected LinearLayout.LayoutParams starsParams;
    protected final StarsRatingView starsView;
    protected final TextView title2Label;
    protected LayoutParams title2Params;
    protected final TextView titleLabel;
    protected LayoutParams titleLayoutParams;
    protected final l uiUtils;
    protected final TextView url2Label;
    protected LayoutParams url2Params;
    protected final TextView urlLabel;
    protected LayoutParams urlLabelParams;
    protected final TextView votesLabel;

    public abstract void loadImages();

    public AbstractNativeAdView(Context context) {
        this(context, null);
    }

    public AbstractNativeAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AbstractNativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ageRestrictionLabel = new BorderedTextView(context);
        this.advertisingLabel = new TextView(context);
        this.iconImageView = new CacheImageView(context);
        this.labelsLayout = new LinearLayout(context);
        this.titleLabel = new TextView(context);
        this.urlLabel = new TextView(context);
        this.descriptionLabel = new TextView(context);
        this.mediaAdView = NativeViewsFactory.getMediaAdView(context);
        this.title2Label = new TextView(context);
        this.url2Label = new TextView(context);
        this.ratingLayout = new LinearLayout(context);
        this.starsView = new StarsRatingView(context);
        this.votesLabel = new TextView(context);
        this.disclaimerLabel = new TextView(context);
        this.ctaButton = new Button(context);
        this.uiUtils = new l(context);
        initView();
    }

    public void initView() {
        setPadding(this.uiUtils.a(12), this.uiUtils.a(12), this.uiUtils.a(12), this.uiUtils.a(12));
        this.ageRestrictionLabel.setId(256);
        this.ageRestrictionLabel.setBorder(1, -7829368);
        this.ageRestrictionLabel.setPadding(this.uiUtils.a(2), 0, 0, 0);
        this.ageRestrictionParams = new LayoutParams(-2, -2);
        this.ageRestrictionParams.rightMargin = this.uiUtils.a(9);
        this.ageRestrictionLabel.setLayoutParams(this.ageRestrictionParams);
        this.advertisingLabel.setId(AD_ID);
        this.advertisingLabelParams = new LayoutParams(-2, -2);
        this.advertisingLabelParams.addRule(1, 256);
        this.advertisingLabel.setLayoutParams(this.advertisingLabelParams);
        this.iconImageView.setId(257);
        this.iconImageParams = new LayoutParams(this.uiUtils.a(54), this.uiUtils.a(54));
        this.iconImageParams.addRule(3, AD_ID);
        this.iconImageParams.topMargin = this.uiUtils.a(9);
        this.iconImageView.setLayoutParams(this.iconImageParams);
        this.labelsLayout.setId(LABELS_ID);
        this.labelsLayout.setOrientation(1);
        this.labelsLayout.setMinimumHeight(this.uiUtils.a(54));
        this.labelsLayoutParams = new LayoutParams(-1, -2);
        this.labelsLayoutParams.addRule(3, AD_ID);
        this.labelsLayoutParams.addRule(1, 257);
        this.labelsLayoutParams.leftMargin = this.uiUtils.a(9);
        this.labelsLayoutParams.topMargin = this.uiUtils.a(3);
        this.labelsLayout.setLayoutParams(this.labelsLayoutParams);
        this.titleLabel.setId(TITLE_ID);
        this.titleLayoutParams = new LayoutParams(-2, -2);
        this.titleLabel.setLayoutParams(this.titleLayoutParams);
        this.urlLabel.setId(URL_ID);
        this.urlLabelParams = new LayoutParams(-2, -2);
        this.urlLabelParams.topMargin = this.uiUtils.a(9);
        this.urlLabel.setLayoutParams(this.urlLabelParams);
        this.descriptionLabel.setId(DESC_ID);
        this.descriptionLabelParams = new LayoutParams(-2, -2);
        this.descriptionLabelParams.topMargin = this.uiUtils.a(9);
        this.descriptionLabel.setLayoutParams(this.descriptionLabelParams);
        this.mediaAdView.setId(IMAGE_ID);
        this.mediaAdViewParams = new LayoutParams(-1, -2);
        this.mediaAdViewParams.addRule(3, LABELS_ID);
        this.mediaAdViewParams.topMargin = this.uiUtils.a(9);
        this.mediaAdView.setLayoutParams(this.mediaAdViewParams);
        this.title2Label.setId(TITLE_2_ID);
        this.title2Params = new LayoutParams(-2, -2);
        this.title2Params.addRule(3, IMAGE_ID);
        this.title2Params.topMargin = this.uiUtils.a(9);
        this.title2Label.setLayoutParams(this.title2Params);
        this.url2Label.setId(URL_2_ID);
        this.url2Params = new LayoutParams(-2, -2);
        this.url2Params.addRule(3, TITLE_2_ID);
        this.url2Label.setLayoutParams(this.url2Params);
        this.ratingLayout.setId(RATING_ID);
        this.ratingLayout.setOrientation(0);
        this.ratingParams = new LayoutParams(-2, -2);
        this.ratingParams.addRule(3, TITLE_2_ID);
        this.ratingLayout.setLayoutParams(this.ratingParams);
        this.starsView.setId(STARS_ID);
        this.starsParams = new LinearLayout.LayoutParams(this.uiUtils.a(73), this.uiUtils.a(12));
        this.starsParams.topMargin = this.uiUtils.a(4);
        this.starsView.setLayoutParams(this.starsParams);
        this.votesLabel.setId(VOTES_ID);
        this.disclaimerParams = new LayoutParams(-2, -2);
        this.disclaimerParams.addRule(3, RATING_ID);
        this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
        this.ctaButton.setId(CTA_ID);
        this.ctaButton.setPadding(this.uiUtils.a(10), 0, this.uiUtils.a(10), 0);
        this.ctaParams = new LayoutParams(-2, this.uiUtils.a(30));
        this.ctaParams.addRule(3, TITLE_2_ID);
        this.ctaParams.addRule(11);
        this.ctaButton.setLayoutParams(this.ctaParams);
        this.ctaButton.setTransformationMethod(null);
        l.a(this, 0, -3806472);
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, 0});
        gradientDrawable.setStroke(this.uiUtils.a(1), -3355444);
        gradientDrawable.setCornerRadius((float) this.uiUtils.a(1));
        Drawable gradientDrawable2 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-3806472, -3806472});
        gradientDrawable2.setStroke(this.uiUtils.a(1), -3355444);
        gradientDrawable2.setCornerRadius((float) this.uiUtils.a(1));
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, gradientDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
        this.ctaButton.setBackgroundDrawable(stateListDrawable);
        setClickable(true);
        addView(this.ageRestrictionLabel);
        addView(this.advertisingLabel);
        addView(this.iconImageView);
        addView(this.labelsLayout);
        this.labelsLayout.addView(this.titleLabel);
        this.labelsLayout.addView(this.urlLabel);
        this.labelsLayout.addView(this.descriptionLabel);
        addView(this.mediaAdView);
        addView(this.title2Label);
        addView(this.url2Label);
        addView(this.ctaButton);
        addView(this.ratingLayout);
        addView(this.disclaimerLabel);
        this.ratingLayout.addView(this.starsView);
        this.ratingLayout.addView(this.votesLabel);
        updateDefaultParams();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (x > ((float) this.ctaButton.getLeft()) && x < ((float) this.ctaButton.getRight()) && y > ((float) this.ctaButton.getTop()) && y < ((float) this.ctaButton.getBottom())) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (this.banner == null || this.banner.k() == null || x <= ((float) this.mediaAdView.getLeft()) || x >= ((float) this.mediaAdView.getRight()) || y <= ((float) this.mediaAdView.getTop()) || y >= ((float) this.mediaAdView.getBottom())) {
            return onTouchEvent(motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private void updateDefaultParams() {
        this.ageRestrictionLabel.setTextColor(-6710887);
        this.ageRestrictionLabel.setBorder(1, -6710887);
        this.ageRestrictionLabel.setBackgroundColor(0);
        this.advertisingLabel.setTextSize(2, 14.0f);
        this.advertisingLabel.setTextColor(-6710887);
        this.titleLabel.setTextColor(-16777216);
        this.titleLabel.setTextSize(2, 16.0f);
        this.titleLabel.setTypeface(null, 1);
        this.urlLabel.setTextColor(-6710887);
        this.urlLabel.setTextSize(2, 14.0f);
        this.descriptionLabel.setTextColor(-16777216);
        this.descriptionLabel.setTextSize(2, 14.0f);
        this.title2Label.setTextColor(-16777216);
        this.title2Label.setTextSize(2, 16.0f);
        this.title2Label.setTypeface(null, 1);
        this.url2Label.setTextColor(-6710887);
        this.url2Label.setTextSize(2, 14.0f);
        this.votesLabel.setTextColor(-6710887);
        this.votesLabel.setTextSize(2, 14.0f);
        this.disclaimerLabel.setTextColor(-6710887);
        this.disclaimerLabel.setTextSize(2, 12.0f);
        this.ctaButton.setTextColor(-6710887);
    }

    public void setupView(f fVar) {
        this.banner = fVar;
        Tracer.d("Setup banner type: " + fVar.a());
    }
}
