package com.google.android.gms.ads.search;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class DynamicHeightSearchAdRequest$Builder {
    private final SearchAdRequest$Builder zzcqz = new SearchAdRequest$Builder();
    private final Bundle zzcra = new Bundle();

    public DynamicHeightSearchAdRequest$Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
        this.zzcqz.addCustomEventExtrasBundle(cls, bundle);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder addNetworkExtras(NetworkExtras networkExtras) {
        this.zzcqz.addNetworkExtras(networkExtras);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
        this.zzcqz.addNetworkExtrasBundle(cls, bundle);
        return this;
    }

    public DynamicHeightSearchAdRequest build() {
        this.zzcqz.addNetworkExtrasBundle(AdMobAdapter.class, this.zzcra);
        return new DynamicHeightSearchAdRequest(this, null);
    }

    public DynamicHeightSearchAdRequest$Builder setAdBorderSelectors(String str) {
        this.zzcra.putString("csa_adBorderSelectors", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setAdTest(boolean z) {
        this.zzcra.putString("csa_adtest", z ? "on" : "off");
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setAdjustableLineHeight(int i) {
        this.zzcra.putString("csa_adjustableLineHeight", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setAdvancedOptionValue(String str, String str2) {
        this.zzcra.putString(str, str2);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setAttributionSpacingBelow(int i) {
        this.zzcra.putString("csa_attributionSpacingBelow", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setBorderSelections(String str) {
        this.zzcra.putString("csa_borderSelections", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setChannel(String str) {
        this.zzcra.putString("csa_channel", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setColorAdBorder(String str) {
        this.zzcra.putString("csa_colorAdBorder", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setColorAdSeparator(String str) {
        this.zzcra.putString("csa_colorAdSeparator", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setColorAnnotation(String str) {
        this.zzcra.putString("csa_colorAnnotation", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setColorAttribution(String str) {
        this.zzcra.putString("csa_colorAttribution", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setColorBackground(String str) {
        this.zzcra.putString("csa_colorBackground", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setColorBorder(String str) {
        this.zzcra.putString("csa_colorBorder", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setColorDomainLink(String str) {
        this.zzcra.putString("csa_colorDomainLink", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setColorText(String str) {
        this.zzcra.putString("csa_colorText", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setColorTitleLink(String str) {
        this.zzcra.putString("csa_colorTitleLink", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setCssWidth(int i) {
        this.zzcra.putString("csa_width", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setDetailedAttribution(boolean z) {
        this.zzcra.putString("csa_detailedAttribution", Boolean.toString(z));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setFontFamily(int i) {
        this.zzcra.putString("csa_fontFamily", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setFontFamilyAttribution(String str) {
        this.zzcra.putString("csa_fontFamilyAttribution", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setFontSizeAnnotation(int i) {
        this.zzcra.putString("csa_fontSizeAnnotation", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setFontSizeAttribution(int i) {
        this.zzcra.putString("csa_fontSizeAttribution", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setFontSizeDescription(int i) {
        this.zzcra.putString("csa_fontSizeDescription", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setFontSizeDomainLink(int i) {
        this.zzcra.putString("csa_fontSizeDomainLink", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setFontSizeTitle(int i) {
        this.zzcra.putString("csa_fontSizeTitle", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setHostLanguage(String str) {
        this.zzcra.putString("csa_hl", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setIsClickToCallEnabled(boolean z) {
        this.zzcra.putString("csa_clickToCall", Boolean.toString(z));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setIsLocationEnabled(boolean z) {
        this.zzcra.putString("csa_location", Boolean.toString(z));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setIsPlusOnesEnabled(boolean z) {
        this.zzcra.putString("csa_plusOnes", Boolean.toString(z));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setIsSellerRatingsEnabled(boolean z) {
        this.zzcra.putString("csa_sellerRatings", Boolean.toString(z));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setIsSiteLinksEnabled(boolean z) {
        this.zzcra.putString("csa_siteLinks", Boolean.toString(z));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setIsTitleBold(boolean z) {
        this.zzcra.putString("csa_titleBold", Boolean.toString(z));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setIsTitleUnderlined(boolean z) {
        this.zzcra.putString("csa_noTitleUnderline", Boolean.toString(!z));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setLocationColor(String str) {
        this.zzcra.putString("csa_colorLocation", str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setLocationFontSize(int i) {
        this.zzcra.putString("csa_fontSizeLocation", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setLongerHeadlines(boolean z) {
        this.zzcra.putString("csa_longerHeadlines", Boolean.toString(z));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setNumber(int i) {
        this.zzcra.putString("csa_number", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setPage(int i) {
        this.zzcra.putString("csa_adPage", Integer.toString(i));
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setQuery(String str) {
        this.zzcqz.setQuery(str);
        return this;
    }

    public DynamicHeightSearchAdRequest$Builder setVerticalSpacing(int i) {
        this.zzcra.putString("csa_verticalSpacing", Integer.toString(i));
        return this;
    }
}
