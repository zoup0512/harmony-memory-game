package com.cmcm.adsdk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.Const.pkgName;
import com.cmcm.adsdk.base.CMBaseNativeAd;
import com.cmcm.adsdk.nativead.PicksViewCheckHelper;
import com.cmcm.baseapi.ads.INativeAd.ImpressionListener;
import com.cmcm.picks.init.ICallBack;
import com.cmcm.picks.init.a;
import com.cmcm.picks.loader.Ad;
import com.cmcm.picks.market.MarketUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PicksNativeAdapter extends NativeloaderAdapter implements ICallBack {
    private static final int PICKS_DEFAULT_LOAD_NUM = 10;
    private boolean isNeedCheckView = true;
    Context mContext;
    private Map<String, Object> mExtras;
    private int mLoadSize = 1;
    private String mPlacementId;

    class PicksNativeAd extends CMBaseNativeAd implements ImpressionListener {
        private static final int DOWNLOAD_MT_TYPE = 8;
        private Ad mAd;
        private View mAdView;
        private boolean mImpressioned = false;
        private PicksViewCheckHelper mPicksViewCheckHelper;

        public PicksNativeAd(Ad ad, Context context) {
            this.mAd = ad;
            setUpData();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void setUpData() {
            /*
            r6 = this;
            r2 = 0;
            r1 = 1;
            r0 = r6.mAd;
            r0 = r0.getAppShowType();
            r3 = r6.mAd;
            r3 = 70003; // 0x11173 float:9.8095E-41 double:3.4586E-319;
            if (r0 == r3) goto L_0x001c;
        L_0x000f:
            r0 = r6.mAd;
            r0 = r0.getAppShowType();
            r3 = r6.mAd;
            r3 = 70002; // 0x11172 float:9.8094E-41 double:3.45856E-319;
            if (r0 != r3) goto L_0x0047;
        L_0x001c:
            r0 = "CMCMADSDK";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "70003|70002 pic size=";
            r3 = r3.append(r4);
            r4 = r6.mAd;
            r4 = r4.getExtPics();
            r4 = r4.size();
            r3 = r3.append(r4);
            r3 = r3.toString();
            com.cmcm.utils.g.b(r0, r3);
            r0 = r6.mAd;
            r0 = r0.getExtPics();
            r6.setExtPics(r0);
        L_0x0047:
            r0 = r6.mAd;
            r0 = r0.getTitle();
            r6.setTitle(r0);
            r0 = r6.mAd;
            r0 = r0.getBackground();
            r6.setAdCoverImageUrl(r0);
            r0 = r6.mAd;
            r0 = r0.getPicUrl();
            r6.setAdIconUrl(r0);
            r0 = r6.mAd;
            r0 = r0.getButtonTxt();
            r6.setAdCallToAction(r0);
            r0 = r6.mAd;
            r0 = r0.getDesc();
            r6.setAdBody(r0);
            r0 = r6.mAd;
            r4 = r0.getRating();
            r6.setAdStarRate(r4);
            r0 = r6.mAd;
            r0 = r0.getDownloadNum();
            r6.setAdSocialContext(r0);
            r0 = r6.mAd;
            r0 = r0.getMtType();
            r3 = 8;
            if (r0 != r3) goto L_0x00ad;
        L_0x0090:
            r0 = r1;
        L_0x0091:
            r0 = java.lang.Boolean.valueOf(r0);
            r6.setIsDownloadApp(r0);
            r0 = r6.mAd;
            r0 = r0.getPriority();
            if (r0 != r1) goto L_0x00af;
        L_0x00a0:
            r6.setIsPriority(r1);
            r0 = r6.mAd;
            r0 = r0.getMpaModule();
            r6.setMpaModule(r0);
            return;
        L_0x00ad:
            r0 = r2;
            goto L_0x0091;
        L_0x00af:
            r1 = r2;
            goto L_0x00a0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cmcm.adsdk.adapter.PicksNativeAdapter.PicksNativeAd.setUpData():void");
        }

        public String getAdTypeName() {
            return Const.KEY_CM;
        }

        public boolean registerViewForInteraction(View view) {
            if (view != null) {
                if (this.mPicksViewCheckHelper != null) {
                    unregisterView();
                }
                this.mAdView = view;
                if (!this.mImpressioned) {
                    if (PicksNativeAdapter.this.isNeedCheckView) {
                        this.mPicksViewCheckHelper = new PicksViewCheckHelper(PicksNativeAdapter.this.mContext, view, this, false);
                        this.mPicksViewCheckHelper.startWork();
                    } else {
                        onLoggingImpression();
                    }
                }
            }
            return false;
        }

        public void unregisterView() {
            if (this.mAdView != null) {
                this.mAdView = null;
            }
            if (this.mPicksViewCheckHelper != null) {
                this.mPicksViewCheckHelper.stopWork("unregisterView");
            }
        }

        public boolean hasExpired() {
            return !this.mAd.isAvailAble() || this.mAd.isShowed();
        }

        public Object getAdObject() {
            return this.mAd;
        }

        public void onLoggingImpression() {
            if (this.mImpressionListener != null) {
                this.mImpressionListener.onLoggingImpression();
            }
            this.mImpressioned = true;
        }

        public void handleClick() {
            MarketUtils.openOrDownloadAdNoDialog(PicksNativeAdapter.this.mContext, PicksNativeAdapter.this.mPlacementId, this.mAd, null, getExtraReportParams());
        }
    }

    public void loadNativeAd(@NonNull Context context, @NonNull Map<String, Object> extras) {
        this.mContext = context;
        this.mExtras = extras;
        this.mPlacementId = (String) this.mExtras.get(CMBaseNativeAd.KEY_PLACEMENT_ID);
        try {
            int i;
            Object obj = this.mExtras.get(CMBaseNativeAd.KEY_LOAD_SIZE);
            if (obj == null) {
                i = 10;
            } else {
                i = ((Integer) obj).intValue();
            }
            this.isNeedCheckView = ((Boolean) this.mExtras.get(CMBaseNativeAd.KEY_CHECK_VIEW)).booleanValue();
            a.getInstance().loadad(Integer.valueOf(this.mPlacementId).intValue(), this, i);
        } catch (Exception e) {
            onLoadError();
        }
    }

    public int getDefaultLoadNum() {
        return 10;
    }

    public int getReportRes() {
        return 0;
    }

    public String getReportPkgName(String adTypeName) {
        return pkgName.cm;
    }

    public String getAdKeyType() {
        return Const.KEY_CM;
    }

    public long getDefaultCacheTime() {
        return 3600000;
    }

    public void onLoadSuccess(List list) {
        List arrayList = new ArrayList();
        if (!(list == null || list.isEmpty())) {
            for (Object next : list) {
                if (next instanceof Ad) {
                    arrayList.add(new PicksNativeAd((Ad) next, this.mContext));
                }
            }
        }
        if (arrayList.isEmpty()) {
            notifyNativeAdFailed("cm.fake-fill.invalidad");
        } else {
            notifyNativeAdLoaded(arrayList);
        }
    }

    public void onLoadError() {
        notifyNativeAdFailed("");
    }

    public void onPreExecute() {
    }
}
