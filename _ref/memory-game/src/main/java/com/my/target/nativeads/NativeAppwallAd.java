package com.my.target.nativeads;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.my.target.Tracer;
import com.my.target.ads.CustomParams;
import com.my.target.ads.MyTargetActivity;
import com.my.target.core.facades.a;
import com.my.target.core.models.c;
import com.my.target.core.models.sections.b;
import com.my.target.core.models.sections.f;
import com.my.target.nativeads.banners.NativeAppwallBanner;
import com.my.target.nativeads.models.ImageData;
import com.my.target.nativeads.views.AppwallAdTeaserView;
import com.my.target.nativeads.views.AppwallAdView;
import com.my.target.nativeads.views.AppwallAdView.BannerClickListener;
import com.my.target.nativeads.views.AppwallAdView.BannerVisibilityListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class NativeAppwallAd extends a {
    private static final boolean AUTOLOAD_IMAGES = true;
    private com.my.target.core.ui.a adDialog;
    private final com.my.target.core.a adParams;
    private List<NativeAppwallBanner> appwallBannerList;
    private b appwallSection;
    private final BannerClickListener bannerClickListener;
    private final Map<String, NativeAppwallBanner> bannerIdMap;
    private OnClickListener clickListener;
    private final OnDismissListener dialogDismissListener;
    private AppwallAdView externalAppwallAdView;
    private boolean hideStatusBarInDialog;
    private AppwallAdListener listener;
    private String title;
    private int titleBackgroundColor;
    private int titleSupplementaryColor;
    private int titleTextColor;
    private final BannerVisibilityListener visibilityListener;

    public interface AppwallAdListener {
        void onClick(NativeAppwallBanner nativeAppwallBanner, NativeAppwallAd nativeAppwallAd);

        void onDismissDialog(NativeAppwallAd nativeAppwallAd);

        void onLoad(NativeAppwallAd nativeAppwallAd);

        void onNoAd(String str, NativeAppwallAd nativeAppwallAd);
    }

    public NativeAppwallAd(int i, Context context) {
        this(i, context, null);
    }

    public NativeAppwallAd(int i, Context context, CustomParams customParams) {
        this.bannerIdMap = new HashMap();
        this.appwallBannerList = new ArrayList();
        this.visibilityListener = new BannerVisibilityListener() {
            public void onBannersShown(List<NativeAppwallBanner> list) {
                NativeAppwallAd.this.handleBannersShow(list);
            }
        };
        this.title = "Apps";
        this.titleBackgroundColor = -12232093;
        this.titleSupplementaryColor = -13220531;
        this.titleTextColor = -1;
        this.hideStatusBarInDialog = false;
        this.bannerClickListener = new BannerClickListener() {
            public void onBannerClick(AppwallAdTeaserView appwallAdTeaserView) {
                NativeAppwallAd.this.doBannerClick(appwallAdTeaserView.getBanner());
                if (NativeAppwallAd.this.externalAppwallAdView != null) {
                    NativeAppwallAd.this.externalAppwallAdView.notifyDataSetChanged();
                }
            }
        };
        this.dialogDismissListener = new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                com.my.target.core.ui.a aVar = (com.my.target.core.ui.a) dialogInterface;
                aVar.setOnDismissListener(null);
                if (aVar == NativeAppwallAd.this.adDialog) {
                    NativeAppwallAd.this.adDialog = null;
                    if (NativeAppwallAd.this.listener != null) {
                        NativeAppwallAd.this.listener.onDismissDialog(NativeAppwallAd.this);
                    }
                }
            }
        };
        this.clickListener = new OnClickListener() {
            public void onClick(View view) {
                if (view.getTag() == null || !(view.getTag() instanceof String)) {
                    Tracer.d("Banner " + view + " is not registered with AppwallAd");
                    return;
                }
                NativeAppwallAd.this.doBannerClick((NativeAppwallBanner) NativeAppwallAd.this.bannerIdMap.get((String) view.getTag()));
            }
        };
        this.adParams = new com.my.target.core.a(i, "appwall");
        if (customParams != null) {
            this.adParams.a(customParams);
        }
        Tracer.i("NativeAppwallAd created. Version: 4.5.10");
        init(this.adParams, context);
    }

    public void load() {
        if (this.adParams.a() <= 0 || this.adData == null || this.adData.d()) {
            super.load();
        } else {
            onLoad(this.adData);
        }
    }

    protected void onLoad(c cVar) {
        if (cVar.g()) {
            this.appwallBannerList = new ArrayList();
            b bVar = (b) cVar.c("appwall");
            this.appwallBannerList.addAll(getBannersFromSection(bVar));
            if (this.appwallBannerList.size() == 0) {
                bVar = (b) cVar.c("showcaseApps");
                this.appwallBannerList.addAll(getBannersFromSection(bVar));
                if (this.appwallBannerList.size() == 0) {
                    bVar = (b) cVar.c("showcaseGames");
                    this.appwallBannerList.addAll(getBannersFromSection(bVar));
                }
            }
            this.appwallSection = bVar;
            doAutoLoadImages();
            return;
        }
        internalOnNoAd();
    }

    private void internalOnLoad() {
        JSONObject c = this.adData.c();
        if (!(this.adParams.a() <= 0 || c == null || this.adData.j())) {
            com.my.target.core.factories.b.a(this.adParams.a(), this.adParams.d(), c.toString(), this.context).b();
        }
        if (this.listener != null) {
            this.listener.onLoad(this);
        }
    }

    private void internalOnNoAd() {
        if (this.listener != null) {
            this.listener.onNoAd("No ad", this);
        }
    }

    private void doAutoLoadImages() {
        com.my.target.core.net.b.a anonymousClass5 = new com.my.target.core.net.b.a() {
            public void onLoad() {
                NativeAppwallAd.this.internalOnLoad();
            }
        };
        List arrayList = new ArrayList();
        for (NativeAppwallBanner nativeAppwallBanner : this.appwallBannerList) {
            ImageData statusIcon = nativeAppwallBanner.getStatusIcon();
            ImageData coinsIcon = nativeAppwallBanner.getCoinsIcon();
            ImageData gotoAppIcon = nativeAppwallBanner.getGotoAppIcon();
            ImageData icon = nativeAppwallBanner.getIcon();
            ImageData labelIcon = nativeAppwallBanner.getLabelIcon();
            ImageData bubbleIcon = nativeAppwallBanner.getBubbleIcon();
            ImageData itemHighlightIcon = nativeAppwallBanner.getItemHighlightIcon();
            ImageData crossNotifIcon = nativeAppwallBanner.getCrossNotifIcon();
            if (statusIcon != null) {
                arrayList.add(statusIcon);
            }
            if (coinsIcon != null) {
                arrayList.add(coinsIcon);
            }
            if (gotoAppIcon != null) {
                arrayList.add(gotoAppIcon);
            }
            if (icon != null) {
                arrayList.add(icon);
            }
            if (labelIcon != null) {
                arrayList.add(labelIcon);
            }
            if (bubbleIcon != null) {
                arrayList.add(bubbleIcon);
            }
            if (itemHighlightIcon != null) {
                arrayList.add(itemHighlightIcon);
            }
            if (crossNotifIcon != null) {
                arrayList.add(crossNotifIcon);
            }
        }
        com.my.target.core.net.b.a().a(arrayList, this.context, anonymousClass5);
    }

    private List<NativeAppwallBanner> getBannersFromSection(b bVar) {
        List<NativeAppwallBanner> arrayList = new ArrayList();
        if (bVar != null && bVar.b() > 0) {
            Iterator it = bVar.g().iterator();
            while (it.hasNext()) {
                com.my.target.core.models.banners.b bVar2 = (com.my.target.core.models.banners.b) it.next();
                arrayList.add(bVar2);
                this.bannerIdMap.put(bVar2.getId(), bVar2);
            }
        }
        return arrayList;
    }

    protected void onLoadError(String str) {
        if (this.listener != null) {
            this.listener.onNoAd(str, this);
        }
    }

    public void show() {
        if (this.appwallBannerList.size() == 0) {
            Tracer.i("AppwallAd.show: No ad");
            return;
        }
        MyTargetActivity.ad = this;
        Intent intent = new Intent(this.context, MyTargetActivity.class);
        intent.setAction("com.my.target.actions.appwall");
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }

    public void showDialog() {
        if (this.adDialog != null && this.adDialog.isShowing()) {
            Tracer.i("AppwallAd.showDialog: dialog already showing");
        } else if (this.appwallBannerList.size() == 0) {
            Tracer.i("AppwallAd.showDialog: No ad");
        } else {
            this.adDialog = new com.my.target.core.ui.a(this, this.hideStatusBarInDialog, this.context);
            this.adDialog.setOnDismissListener(this.dialogDismissListener);
            this.adDialog.show();
        }
    }

    public void dismissDialog() {
        if (this.adDialog != null && this.adDialog.isShowing()) {
            this.adDialog.dismiss();
        }
    }

    public boolean hasNotifications() {
        for (NativeAppwallBanner isHasNotification : this.appwallBannerList) {
            if (isHasNotification.isHasNotification()) {
                return true;
            }
        }
        return false;
    }

    public void registerAppwallAdView(AppwallAdView appwallAdView) {
        appwallAdView.setBannerClickListener(this.bannerClickListener);
        appwallAdView.setBannerVisibilityListener(this.visibilityListener);
        this.externalAppwallAdView = appwallAdView;
    }

    public void unregisterAppwallAdView(AppwallAdView appwallAdView) {
        if (appwallAdView != this.externalAppwallAdView) {
            Tracer.i("No such AppwallAdView registered");
            return;
        }
        this.externalAppwallAdView.setBannerClickListener(null);
        this.externalAppwallAdView.setBannerVisibilityListener(null);
        this.externalAppwallAdView = null;
    }

    public List<NativeAppwallBanner> getBanners() {
        return this.appwallBannerList;
    }

    public void setListener(AppwallAdListener appwallAdListener) {
        this.listener = appwallAdListener;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void handleBannerClick(NativeAppwallBanner nativeAppwallBanner) {
        doBannerClick(nativeAppwallBanner);
    }

    public String prepareBannerClickLink(NativeAppwallBanner nativeAppwallBanner) {
        com.my.target.core.models.banners.c cVar = (com.my.target.core.models.banners.c) nativeAppwallBanner;
        String b = c.b(cVar, this.context);
        this.adData.a(this.adParams, this.appwallSection, cVar, this.context);
        return b;
    }

    public void handleBannersShow(List<NativeAppwallBanner> list) {
        f fVar = this.appwallSection;
        Context context = this.context;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            c.a((com.my.target.core.models.banners.c) it.next(), fVar, context);
        }
    }

    public void handleBannerShow(NativeAppwallBanner nativeAppwallBanner) {
        c.a((com.my.target.core.models.banners.c) nativeAppwallBanner, this.appwallSection, this.context);
    }

    private void doBannerClick(NativeAppwallBanner nativeAppwallBanner) {
        if (nativeAppwallBanner == null) {
            Tracer.d("Something horrible happened");
        } else if (this.adData == null) {
            Tracer.d("AdData is null, click will not be processed.");
        } else {
            try {
                com.my.target.core.models.banners.c cVar = (com.my.target.core.models.banners.c) nativeAppwallBanner;
                this.adData.a(cVar, this.context);
                this.adData.a(this.adParams, this.appwallSection, cVar, this.context);
            } catch (Throwable th) {
                Tracer.d(th.toString());
            }
            if (this.listener != null) {
                this.listener.onClick(nativeAppwallBanner, this);
            }
        }
    }

    public int getTitleBackgroundColor() {
        return this.titleBackgroundColor;
    }

    public void setTitleBackgroundColor(int i) {
        this.titleBackgroundColor = i;
    }

    public int getTitleSupplementaryColor() {
        return this.titleSupplementaryColor;
    }

    public void setTitleSupplementaryColor(int i) {
        this.titleSupplementaryColor = i;
    }

    public int getTitleTextColor() {
        return this.titleTextColor;
    }

    public void setTitleTextColor(int i) {
        this.titleTextColor = i;
    }

    public long getCachePeriod() {
        if (this.adParams == null) {
            return 0;
        }
        return this.adParams.a();
    }

    public void setCachePeriod(long j) {
        if (this.adParams != null) {
            this.adParams.a(j);
        }
    }

    public boolean isHideStatusBarInDialog() {
        return this.hideStatusBarInDialog;
    }

    public void setHideStatusBarInDialog(boolean z) {
        this.hideStatusBarInDialog = z;
    }
}
