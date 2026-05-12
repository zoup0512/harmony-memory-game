package com.my.target.nativeads.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.my.target.core.models.banners.f;
import com.my.target.core.net.b;
import com.my.target.core.ui.views.AbstractNativeAdView;
import com.my.target.nativeads.banners.NavigationType;

public class ContentStreamAdView extends AbstractNativeAdView {
    public ContentStreamAdView(Context context) {
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

    public MediaAdView getMediaAdView() {
        return this.mediaAdView;
    }

    public TextView getSecondTitleTextView() {
        return this.title2Label;
    }

    public TextView getSecondDomainOrCategoryTextView() {
        return this.url2Label;
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
        CharSequence charSequence;
        super.setupView(fVar);
        if ("web".equals(fVar.getNavigationType())) {
            this.url2Label.setVisibility(0);
            this.ratingLayout.setVisibility(8);
            this.urlLabel.setText(fVar.getDomain());
            this.url2Label.setText(fVar.getDomain());
            this.disclaimerParams = new LayoutParams(-2, -2);
            this.disclaimerParams.addRule(3, 265);
            this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
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
                this.url2Label.setVisibility(8);
                this.ratingLayout.setVisibility(0);
                this.urlLabel.setText(charSequence);
                this.starsView.setRating(fVar.getRating());
                this.votesLabel.setText(Integer.toString(fVar.getVotes()));
                this.disclaimerParams = new LayoutParams(-2, -2);
                this.disclaimerParams.addRule(3, 269);
                this.disclaimerParams.addRule(0, 266);
                this.disclaimerParams.rightMargin = this.uiUtils.a(4);
                this.disclaimerParams.addRule(9, -1);
                this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
            } else {
                this.url2Label.setVisibility(0);
                this.ratingLayout.setVisibility(8);
                this.url2Label.setText(charSequence);
                this.disclaimerParams = new LayoutParams(-2, -2);
                this.disclaimerParams.addRule(3, 265);
                this.disclaimerParams.addRule(0, 266);
                this.disclaimerParams.rightMargin = this.uiUtils.a(4);
                this.disclaimerParams.addRule(9, -1);
                this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
            }
            this.urlLabel.setText(charSequence);
        }
        charSequence = fVar.getDisclaimer();
        if (charSequence == null || charSequence.length() <= 0) {
            this.disclaimerLabel.setVisibility(8);
        } else {
            this.disclaimerLabel.setText(charSequence);
        }
        if (fVar.getImage().getData() != null) {
            this.mediaAdView.getImageView().setImageBitmap((Bitmap) fVar.getImage().getData());
        } else {
            this.mediaAdView.setPlaceHolderDimension(fVar.getImage().getWidth(), fVar.getImage().getHeight());
        }
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
        if (fVar.getAgeRestrictions() == null || fVar.getAgeRestrictions().equals("")) {
            this.ageRestrictionLabel.setVisibility(8);
        } else {
            this.ageRestrictionLabel.setText(fVar.getAgeRestrictions());
        }
        this.ctaButton.setText(fVar.getCtaText());
        this.title2Label.setText(fVar.getTitle());
        if (fVar.getAgeRestrictions() == null || fVar.getAgeRestrictions().length() <= 0) {
            this.ageRestrictionLabel.setVisibility(8);
        } else {
            this.ageRestrictionLabel.setText(fVar.getAgeRestrictions());
        }
    }

    public void loadImages() {
        if (this.banner != null) {
            if (this.banner.getIcon().getData() == null) {
                b.a().a(this.banner.getIcon(), this.iconImageView);
            } else {
                this.iconImageView.setImageBitmap((Bitmap) this.banner.getIcon().getData());
            }
            if (this.banner.getImage().getData() == null) {
                b.a().a(this.banner.getImage(), this.mediaAdView.getImageView());
            } else {
                this.mediaAdView.getImageView().setImageBitmap((Bitmap) this.banner.getImage().getData());
            }
        }
    }
}
