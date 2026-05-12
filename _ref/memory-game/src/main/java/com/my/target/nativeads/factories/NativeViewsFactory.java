package com.my.target.nativeads.factories;

import android.content.Context;
import com.my.target.core.models.banners.f;
import com.my.target.nativeads.NativeAd;
import com.my.target.nativeads.NativeAppwallAd;
import com.my.target.nativeads.banners.NativeAppwallBanner;
import com.my.target.nativeads.banners.NativePromoBanner;
import com.my.target.nativeads.views.AppwallAdTeaserView;
import com.my.target.nativeads.views.AppwallAdView;
import com.my.target.nativeads.views.ChatListAdView;
import com.my.target.nativeads.views.ContentStreamAdView;
import com.my.target.nativeads.views.ContentWallAdView;
import com.my.target.nativeads.views.MediaAdView;
import com.my.target.nativeads.views.NewsFeedAdView;

public class NativeViewsFactory {
    public static MediaAdView getMediaAdView(Context context) {
        return new MediaAdView(context);
    }

    public static ChatListAdView getChatListView(NativeAd nativeAd, Context context) {
        return getChatListView((NativePromoBanner) nativeAd.getBanner(), context);
    }

    public static ChatListAdView getChatListView(NativePromoBanner nativePromoBanner, Context context) {
        ChatListAdView chatListAdView = new ChatListAdView(context);
        chatListAdView.setupView((f) nativePromoBanner);
        return chatListAdView;
    }

    public static ContentStreamAdView getContentStreamView(NativeAd nativeAd, Context context) {
        return getContentStreamView((NativePromoBanner) nativeAd.getBanner(), context);
    }

    public static ContentStreamAdView getContentStreamView(NativePromoBanner nativePromoBanner, Context context) {
        ContentStreamAdView contentStreamAdView = new ContentStreamAdView(context);
        contentStreamAdView.setupView((f) nativePromoBanner);
        return contentStreamAdView;
    }

    public static NewsFeedAdView getNewsFeedView(NativeAd nativeAd, Context context) {
        return getNewsFeedView((NativePromoBanner) nativeAd.getBanner(), context);
    }

    public static NewsFeedAdView getNewsFeedView(NativePromoBanner nativePromoBanner, Context context) {
        NewsFeedAdView newsFeedAdView = new NewsFeedAdView(context);
        newsFeedAdView.setupView((f) nativePromoBanner);
        return newsFeedAdView;
    }

    public static ContentWallAdView getContentWallView(NativeAd nativeAd, Context context) {
        return getContentWallView((NativePromoBanner) nativeAd.getBanner(), context);
    }

    public static ContentWallAdView getContentWallView(NativePromoBanner nativePromoBanner, Context context) {
        ContentWallAdView contentWallAdView = new ContentWallAdView(context);
        contentWallAdView.setupView((f) nativePromoBanner);
        return contentWallAdView;
    }

    public static AppwallAdView getAppwallView(NativeAppwallAd nativeAppwallAd, Context context) {
        AppwallAdView appwallAdView = new AppwallAdView(context);
        appwallAdView.setupView(nativeAppwallAd);
        return appwallAdView;
    }

    public static AppwallAdTeaserView getAppwallAppView(NativeAppwallBanner nativeAppwallBanner, Context context) {
        AppwallAdTeaserView appwallAdTeaserView = new AppwallAdTeaserView(context);
        appwallAdTeaserView.setNativeAppwallBanner(nativeAppwallBanner);
        return appwallAdTeaserView;
    }
}
