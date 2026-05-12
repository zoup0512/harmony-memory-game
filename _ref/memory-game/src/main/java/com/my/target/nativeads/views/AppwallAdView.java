package com.my.target.nativeads.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.my.target.nativeads.NativeAppwallAd;
import com.my.target.nativeads.banners.NativeAppwallBanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppwallAdView extends FrameLayout implements OnGlobalLayoutListener, OnScrollListener, OnItemClickListener {
    private BannerClickListener bannerClickListener;
    private BannerVisibilityListener bannerVisibilityListener;
    private ListView listView;
    private HashMap<String, Boolean> viewMap = new HashMap();
    private ViewTreeObserver viewTreeObserver;

    public interface BannerClickListener {
        void onBannerClick(AppwallAdTeaserView appwallAdTeaserView);
    }

    public interface BannerVisibilityListener {
        void onBannersShown(List<NativeAppwallBanner> list);
    }

    public class AppwallAdapter extends ArrayAdapter<NativeAppwallBanner> {
        public AppwallAdapter(Context context, List<NativeAppwallBanner> list) {
            super(context, 0, list);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View appwallCardPlaceholder;
            NativeAppwallBanner nativeAppwallBanner = (NativeAppwallBanner) getItem(i);
            if (view == null) {
                appwallCardPlaceholder = new AppwallCardPlaceholder(new AppwallAdTeaserView(getContext()), getContext());
            } else {
                appwallCardPlaceholder = view;
            }
            ((AppwallCardPlaceholder) appwallCardPlaceholder).getView().setNativeAppwallBanner(nativeAppwallBanner);
            return appwallCardPlaceholder;
        }
    }

    public static class AppwallCardPlaceholder extends FrameLayout {
        private LinearLayout rootLayout;
        private AppwallAdTeaserView view;

        public AppwallCardPlaceholder(AppwallAdTeaserView appwallAdTeaserView, Context context) {
            super(context);
            this.view = appwallAdTeaserView;
            int applyDimension = (int) TypedValue.applyDimension(1, 9.0f, context.getResources().getDisplayMetrics());
            int applyDimension2 = (int) TypedValue.applyDimension(1, 4.0f, context.getResources().getDisplayMetrics());
            int applyDimension3 = (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
            this.rootLayout = new LinearLayout(context);
            this.rootLayout.setOrientation(1);
            this.rootLayout.setBackgroundColor(-1118482);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(applyDimension, applyDimension2, applyDimension, applyDimension2);
            appwallAdTeaserView.setLayoutParams(layoutParams);
            this.rootLayout.addView(appwallAdTeaserView);
            Drawable stateListDrawable;
            if (VERSION.SDK_INT >= 21) {
                appwallAdTeaserView.setElevation((float) applyDimension3);
                Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1, -1});
                Drawable gradientDrawable2 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1118482, -1118482});
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[]{16842919}, gradientDrawable2);
                stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
                appwallAdTeaserView.setBackground(stateListDrawable);
            } else {
                View view = new View(context);
                LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, applyDimension2);
                view.setBackgroundDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-3355444, -1118482}));
                view.setLayoutParams(layoutParams2);
                this.rootLayout.addView(view);
                stateListDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1, -1});
                stateListDrawable.setStroke(1, -3355444);
                Drawable gradientDrawable3 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1118482, -1118482});
                gradientDrawable3.setStroke(1, -3355444);
                Drawable stateListDrawable2 = new StateListDrawable();
                stateListDrawable2.addState(new int[]{16842919}, gradientDrawable3);
                stateListDrawable2.addState(StateSet.WILD_CARD, stateListDrawable);
                appwallAdTeaserView.setBackgroundDrawable(stateListDrawable2);
                layoutParams.setMargins(0, applyDimension2, 0, 0);
                this.rootLayout.setPadding(applyDimension, 0, applyDimension, 0);
            }
            addView(this.rootLayout, -2, -2);
        }

        public AppwallAdTeaserView getView() {
            return this.view;
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        countVisibleBanners();
    }

    public AppwallAdView(Context context) {
        super(context);
        initLayout(context);
        setVerticalFadingEdgeEnabled(false);
        setBackgroundColor(-1);
    }

    private void initLayout(Context context) {
        int applyDimension = (int) TypedValue.applyDimension(1, 4.0f, context.getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 4.0f, context.getResources().getDisplayMetrics());
        this.listView = new ListView(context);
        this.listView.setDividerHeight(0);
        this.listView.setVerticalFadingEdgeEnabled(false);
        this.listView.setOnItemClickListener(this);
        this.listView.setOnScrollListener(this);
        this.listView.setPadding(0, applyDimension, 0, applyDimension2);
        this.listView.setClipToPadding(false);
        addView(this.listView, -1, -1);
        this.listView.setBackgroundColor(-1118482);
    }

    public void setupView(NativeAppwallAd nativeAppwallAd) {
        this.listView.setAdapter(new AppwallAdapter(getContext(), nativeAppwallAd.getBanners()));
    }

    public void notifyDataSetChanged() {
        if (this.listView != null) {
            ((AppwallAdapter) this.listView.getAdapter()).notifyDataSetChanged();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        countVisibleBanners();
        this.viewTreeObserver = getViewTreeObserver();
        if (this.viewTreeObserver.isAlive()) {
            this.viewTreeObserver.addOnGlobalLayoutListener(this);
        }
    }

    public void onGlobalLayout() {
        countVisibleBanners();
    }

    private void countVisibleBanners() {
        if (this.listView != null && this.listView.getAdapter() != null) {
            int firstVisiblePosition = this.listView.getFirstVisiblePosition();
            int lastVisiblePosition = this.listView.getLastVisiblePosition();
            List arrayList = new ArrayList();
            for (int i = firstVisiblePosition; i <= lastVisiblePosition; i++) {
                NativeAppwallBanner nativeAppwallBanner = (NativeAppwallBanner) this.listView.getAdapter().getItem(i);
                if (this.viewMap.get(nativeAppwallBanner.getId()) == null) {
                    arrayList.add(nativeAppwallBanner);
                    this.viewMap.put(nativeAppwallBanner.getId(), Boolean.valueOf(true));
                }
            }
            if (arrayList.size() > 0 && this.bannerVisibilityListener != null) {
                this.bannerVisibilityListener.onBannersShown(arrayList);
            }
        }
    }

    public void removeBanners() {
        this.listView.setAdapter(null);
        if (this.viewTreeObserver != null && this.viewTreeObserver.isAlive()) {
            this.viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    public void setBannerVisibilityListener(BannerVisibilityListener bannerVisibilityListener) {
        this.bannerVisibilityListener = bannerVisibilityListener;
    }

    public void setBannerClickListener(BannerClickListener bannerClickListener) {
        this.bannerClickListener = bannerClickListener;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AppwallCardPlaceholder appwallCardPlaceholder = (AppwallCardPlaceholder) view;
        if (this.bannerClickListener != null) {
            this.bannerClickListener.onBannerClick(appwallCardPlaceholder.getView());
        }
    }
}
