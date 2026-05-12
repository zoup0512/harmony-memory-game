package com.my.target.nativeads.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.StateSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.my.target.core.models.banners.f;
import com.my.target.core.net.b;
import com.my.target.core.ui.views.AbstractNativeAdView;
import com.my.target.nativeads.banners.NavigationType;

public class NewsFeedAdView extends AbstractNativeAdView {
    public NewsFeedAdView(Context context) {
        super(context);
    }

    public TextView getAgeRestrictionTextView() {
        return this.ageRestrictionLabel;
    }

    public TextView getAdvertisingTextView() {
        return this.advertisingLabel;
    }

    public ImageView getIconImageView() {
        return this.iconImageView;
    }

    public TextView getTitleTextView() {
        return this.titleLabel;
    }

    public TextView getDomainOrCategoryTextView() {
        return this.urlLabel;
    }

    public Button getCtaButtonView() {
        return this.ctaButton;
    }

    public TextView getVotesTextView() {
        return this.votesLabel;
    }

    public StarsRatingView getStarsRatingView() {
        return this.starsView;
    }

    public TextView getDisclaimerTextView() {
        return this.disclaimerLabel;
    }

    public void setupView(f fVar) {
        super.setupView(fVar);
        if ("web".equals(fVar.getNavigationType())) {
            this.urlLabel.setVisibility(0);
            this.ratingLayout.setVisibility(8);
            this.urlLabel.setText(fVar.getDomain());
            this.url2Label.setText(fVar.getDomain());
        } else if (NavigationType.STORE.equals(fVar.getNavigationType())) {
            Object category = fVar.getCategory();
            Object subcategory = fVar.getSubcategory();
            CharSequence charSequence = "";
            if (!TextUtils.isEmpty(category)) {
                charSequence = charSequence + category;
            }
            if (!(TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(subcategory))) {
                charSequence = charSequence + ", ";
            }
            if (!TextUtils.isEmpty(subcategory)) {
                charSequence = charSequence + subcategory;
            }
            if (fVar.w()) {
                this.urlLabel.setVisibility(8);
                ((ViewGroup) this.ratingLayout.getParent()).removeView(this.ratingLayout);
                this.labelsLayout.addView(this.ratingLayout, 1);
                this.url2Label.setVisibility(8);
                this.ratingLayout.setVisibility(0);
                this.starsView.setRating(fVar.getRating());
                this.votesLabel.setText(Integer.toString(fVar.getVotes()));
            } else {
                this.urlLabel.setVisibility(0);
                this.urlLabel.setText(charSequence);
                this.ratingLayout.setVisibility(8);
            }
        }
        ((ViewGroup) this.ctaButton.getParent()).removeView(this.ctaButton);
        this.ctaParams = new LayoutParams(-2, this.uiUtils.a(30));
        this.ctaParams.addRule(3, 258);
        this.ctaParams.addRule(11);
        this.ctaParams.topMargin = -this.uiUtils.a(23);
        this.ctaButton.setLayoutParams(this.ctaParams);
        addView(this.ctaButton);
        this.mediaAdView.setVisibility(8);
        this.url2Label.setVisibility(8);
        this.title2Label.setVisibility(8);
        this.descriptionLabel.setVisibility(8);
        if (fVar.getIcon().getData() != null) {
            this.iconImageView.setImageBitmap((Bitmap) fVar.getIcon().getData());
        } else {
            this.iconImageView.setBackgroundColor(-1118482);
            this.iconImageView.setPlaceholderWidth(fVar.getIcon().getWidth());
            this.iconImageView.setPlaceholderHeight(fVar.getIcon().getHeight());
        }
        this.titleLabel.setText(fVar.getTitle());
        this.advertisingLabel.setText(fVar.getAdvertisingLabel());
        this.ctaButton.setText(fVar.getCtaText());
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, 0});
        gradientDrawable.setStroke(1, -7829368);
        gradientDrawable.setCornerRadius((float) this.uiUtils.a(5));
        Drawable gradientDrawable2 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-3806472, -3806472});
        gradientDrawable2.setStroke(1, -7829368);
        gradientDrawable2.setCornerRadius((float) this.uiUtils.a(5));
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, gradientDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
        if (fVar.getAgeRestrictions() == null || fVar.getAgeRestrictions().length() <= 0) {
            this.ageRestrictionLabel.setVisibility(8);
        } else {
            this.ageRestrictionLabel.setText(fVar.getAgeRestrictions());
        }
        CharSequence disclaimer = fVar.getDisclaimer();
        if (disclaimer == null || disclaimer.length() <= 0) {
            this.disclaimerLabel.setVisibility(8);
            if ("web".equals(fVar.getNavigationType())) {
                String domain = fVar.getDomain();
                if (domain != null && domain.length() > 0) {
                    ((ViewGroup) this.urlLabel.getParent()).removeView(this.urlLabel);
                    ((ViewGroup) this.ctaButton.getParent()).removeView(this.ctaButton);
                    View relativeLayout = new RelativeLayout(getContext());
                    relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    this.urlLabelParams = new LayoutParams(-2, -2);
                    this.urlLabelParams.addRule(9);
                    this.urlLabelParams.addRule(0, this.ctaButton.getId());
                    this.urlLabelParams.rightMargin = this.uiUtils.a(3);
                    this.urlLabel.setLayoutParams(this.urlLabelParams);
                    this.ctaParams = new LayoutParams(-2, this.uiUtils.a(30));
                    this.ctaParams.addRule(11);
                    this.ctaParams.topMargin = this.uiUtils.a(9);
                    this.ctaButton.setLayoutParams(this.ctaParams);
                    relativeLayout.addView(this.urlLabel);
                    relativeLayout.addView(this.ctaButton);
                    this.labelsLayout.addView(relativeLayout, 1);
                    return;
                }
                return;
            }
            return;
        }
        ((ViewGroup) this.disclaimerLabel.getParent()).removeView(this.disclaimerLabel);
        ((ViewGroup) this.ctaButton.getParent()).removeView(this.ctaButton);
        relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.disclaimerLabel.setText(disclaimer);
        this.disclaimerParams = new LayoutParams(-2, -2);
        this.disclaimerParams.addRule(9);
        this.disclaimerParams.addRule(0, this.ctaButton.getId());
        this.disclaimerParams.rightMargin = this.uiUtils.a(3);
        this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
        this.ctaParams = new LayoutParams(-2, this.uiUtils.a(30));
        this.ctaParams.addRule(11);
        this.ctaButton.setLayoutParams(this.ctaParams);
        relativeLayout.addView(this.disclaimerLabel);
        relativeLayout.addView(this.ctaButton);
        this.labelsLayout.addView(relativeLayout, 2);
    }

    public void loadImages() {
        if (this.banner != null) {
            if (this.banner.getIcon().getData() == null) {
                b.a().a(this.banner.getIcon(), this.iconImageView);
            } else {
                this.iconImageView.setImageBitmap((Bitmap) this.banner.getIcon().getData());
            }
        }
    }
}
