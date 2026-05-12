package com.my.target.core.engines;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.my.target.core.engines.b.a;
import com.my.target.nativeads.NativeAppwallAd;
import com.my.target.nativeads.banners.NativeAppwallBanner;
import com.my.target.nativeads.views.AppwallAdTeaserView;
import com.my.target.nativeads.views.AppwallAdView;
import com.my.target.nativeads.views.AppwallAdView.BannerClickListener;
import com.my.target.nativeads.views.AppwallAdView.BannerVisibilityListener;
import java.util.List;

/* compiled from: AppwallAdEngine */
public final class c extends a implements BannerClickListener, BannerVisibilityListener {
    private NativeAppwallAd c;
    private AppwallAdView d = new AppwallAdView(this.b);

    public c(NativeAppwallAd nativeAppwallAd, ViewGroup viewGroup, Context context) {
        super(viewGroup, context);
        this.c = nativeAppwallAd;
        this.d.setupView(this.c);
        this.d.setBannerClickListener(this);
        this.d.setVisibility(0);
        this.d.setBannerVisibilityListener(this);
        this.d.setLayoutParams(new LayoutParams(-1, -1));
        this.a.addView(this.d);
    }

    public final void onBannerClick(AppwallAdTeaserView appwallAdTeaserView) {
        this.c.handleBannerClick(appwallAdTeaserView.getBanner());
        this.d.notifyDataSetChanged();
    }

    public final void onBannersShown(List<NativeAppwallBanner> list) {
        this.c.handleBannersShow(list);
    }

    public final void a(a aVar) {
    }
}
