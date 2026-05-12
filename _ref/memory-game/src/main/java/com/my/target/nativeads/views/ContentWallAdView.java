package com.my.target.nativeads.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.my.target.core.models.banners.f;
import com.my.target.core.net.b;
import com.my.target.core.ui.views.AbstractNativeAdView;

public class ContentWallAdView extends AbstractNativeAdView {
    private LinearLayout shadeLayout;
    private LayoutParams shadeParams;

    public ContentWallAdView(Context context) {
        super(context);
    }

    public TextView getAgeRestrictionTextView() {
        return this.ageRestrictionLabel;
    }

    public TextView getAdvertisingTextView() {
        return this.advertisingLabel;
    }

    public MediaAdView getMediaAdView() {
        return this.mediaAdView;
    }

    public void setupView(f fVar) {
        super.setupView(fVar);
        removeView(this.mediaAdView);
        this.disclaimerLabel.setVisibility(8);
        this.mediaAdViewParams = new LayoutParams(-1, -2);
        this.mediaAdView.setLayoutParams(this.mediaAdViewParams);
        addView(this.mediaAdView);
        this.shadeLayout = new LinearLayout(getContext());
        this.shadeLayout.setOrientation(0);
        this.shadeLayout.setPadding(this.uiUtils.a(3), this.uiUtils.a(3), this.uiUtils.a(3), this.uiUtils.a(3));
        this.shadeLayout.setBackgroundColor(Color.parseColor("#55000000"));
        this.shadeParams = new LayoutParams(-1, -2);
        this.shadeLayout.setLayoutParams(this.shadeParams);
        this.ctaButton.setVisibility(8);
        this.urlLabel.setVisibility(8);
        this.url2Label.setVisibility(8);
        this.title2Label.setVisibility(8);
        this.titleLabel.setVisibility(8);
        this.descriptionLabel.setVisibility(8);
        this.iconImageView.setVisibility(8);
        this.ratingLayout.setVisibility(8);
        ((ViewGroup) this.advertisingLabel.getParent()).removeView(this.advertisingLabel);
        ((ViewGroup) this.ageRestrictionLabel.getParent()).removeView(this.ageRestrictionLabel);
        this.shadeLayout.addView(this.ageRestrictionLabel);
        this.shadeLayout.addView(this.advertisingLabel);
        addView(this.shadeLayout);
        this.ageRestrictionLabel.setTextColor(-1);
        this.ageRestrictionLabel.setBorder(1, -1);
        this.advertisingLabel.setTextColor(-1);
        this.advertisingLabel.setPadding(this.uiUtils.a(3), 0, 0, 0);
        if (fVar.getAgeRestrictions() == null || fVar.getAgeRestrictions().length() <= 0) {
            this.ageRestrictionLabel.setVisibility(8);
            this.advertisingLabel.setPadding(0, 0, 0, 0);
        } else {
            this.ageRestrictionLabel.setText(fVar.getAgeRestrictions());
        }
        if (fVar.getImage().getData() != null) {
            this.mediaAdView.getImageView().setImageBitmap((Bitmap) fVar.getImage().getData());
        } else {
            this.mediaAdView.setPlaceHolderDimension(fVar.getImage().getWidth(), fVar.getImage().getHeight());
        }
        this.ageRestrictionLabel.setText(fVar.getAgeRestrictions());
        this.advertisingLabel.setText(fVar.getAdvertisingLabel());
    }

    public void loadImages() {
        if (this.banner != null) {
            if (this.banner.getImage().getData() == null) {
                b.a().a(this.banner.getImage(), this.mediaAdView.getImageView());
            } else {
                this.mediaAdView.getImageView().setImageBitmap((Bitmap) this.banner.getImage().getData());
            }
        }
    }
}
