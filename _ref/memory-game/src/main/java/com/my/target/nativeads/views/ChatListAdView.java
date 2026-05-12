package com.my.target.nativeads.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.my.target.core.models.banners.f;
import com.my.target.core.net.b;
import com.my.target.core.ui.views.AbstractNativeAdView;
import com.my.target.nativeads.banners.NavigationType;

public class ChatListAdView extends AbstractNativeAdView {
    public ChatListAdView(Context context) {
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

    public TextView getDescriptionTextView() {
        return this.descriptionLabel;
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
        CharSequence charSequence;
        super.setupView(fVar);
        if ("web".equals(fVar.getNavigationType())) {
            this.urlLabel.setVisibility(0);
            this.ratingLayout.setVisibility(8);
            this.urlLabel.setText(fVar.getDomain());
            this.url2Label.setText(fVar.getDomain());
        } else if (NavigationType.STORE.equals(fVar.getNavigationType())) {
            Object category = fVar.getCategory();
            Object subcategory = fVar.getSubcategory();
            charSequence = "";
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
        this.mediaAdView.setVisibility(8);
        this.ctaButton.setVisibility(8);
        this.url2Label.setVisibility(8);
        this.title2Label.setVisibility(8);
        if (fVar.getIcon().getData() != null) {
            this.iconImageView.setImageBitmap((Bitmap) fVar.getIcon().getData());
        } else {
            this.iconImageView.setBackgroundColor(-1118482);
            this.iconImageView.setPlaceholderWidth(fVar.getIcon().getWidth());
            this.iconImageView.setPlaceholderHeight(fVar.getIcon().getHeight());
        }
        this.titleLabel.setText(fVar.getTitle());
        this.descriptionLabel.setText(fVar.getDescription());
        this.advertisingLabel.setText(fVar.getAdvertisingLabel());
        if (fVar.getAgeRestrictions() == null || fVar.getAgeRestrictions().length() <= 0) {
            this.ageRestrictionLabel.setVisibility(8);
        } else {
            this.ageRestrictionLabel.setText(fVar.getAgeRestrictions());
        }
        charSequence = fVar.getDisclaimer();
        if (charSequence == null || charSequence.length() <= 0) {
            this.disclaimerLabel.setVisibility(8);
            return;
        }
        removeView(this.disclaimerLabel);
        this.disclaimerParams = new LayoutParams(-2, -2);
        this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
        this.labelsLayout.addView(this.disclaimerLabel);
        this.disclaimerLabel.setText(charSequence);
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
