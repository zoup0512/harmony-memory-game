package com.my.target.nativeads.banners;

import com.my.target.nativeads.models.ImageData;

public interface NativePromoBanner {
    String getAdvertisingLabel();

    String getAgeRestrictions();

    String getCategory();

    String getCtaText();

    String getDescription();

    String getDisclaimer();

    String getDomain();

    ImageData getIcon();

    ImageData getImage();

    String getNavigationType();

    float getRating();

    String getSubcategory();

    String getTitle();

    int getVotes();
}
