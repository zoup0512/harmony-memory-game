package com.cmcm.picks.mixad;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.picks.mixad.MixBoxManager.POS_TYPE;
import com.cmcm.utils.Commons;
import com.cmcm.utils.g;
import com.cube.memorygames.games.Game1MemoryGridActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MixBeans implements Comparable<MixBeans> {
    private static final String KEY_CLICKED = "clicked";
    private static final String KEY_CLICKED_TIME = "clickedTime";
    private static final String KEY_SHOWED = "showed";
    private static final String KEY_SHOWED_TIME = "showedTime";
    public static final int SOURCE_HOT = 1;
    public static final int SOURCE_OTHER = 2;
    private static final String TAG = MixBeans.class.getSimpleName();
    private static final int TYPE_BROWSER = 256;
    private static final int TYPE_DEEPLINK = 512;
    private static final int TYPE_GP = 8;
    private static final int TYPE_WEBVIEW = 64;
    String background;
    int box_reddot;
    String button_txt;
    String click_tracking_url;
    Context context;
    String deeplink;
    String des;
    String desc;
    String et;
    int frequency;
    int giftimes;
    int localtime;
    private boolean mbClicked = false;
    List<String> mlistExcludePkgs;
    int mt_type;
    String pic_url;
    String pic_url_w1080;
    String pic_url_w400;
    String pic_url_w480;
    String pic_url_w540;
    String pic_url_w720;
    String pkg;
    String pkg_url;
    String posId;
    Integer priority;
    int res_type;
    int shift;
    int source;
    int splash_showtime;
    String st;
    int sug_type;
    String third_imp_url;
    String title;
    int type;

    protected void setClicked(String posId, boolean bClicked) {
        this.mbClicked = bClicked;
        MixSPUtil.putBoolean(posId, this.pkg + getTitleHashCode() + KEY_CLICKED, true);
    }

    public boolean isClicked() {
        return this.mbClicked;
    }

    public int getSource() {
        return this.source;
    }

    public String getButton_txt() {
        return this.button_txt;
    }

    public int getType() {
        return this.type;
    }

    public int getPriority() {
        return this.priority.intValue();
    }

    public String getPic_url() {
        return this.pic_url;
    }

    public int getMt_type() {
        return this.mt_type;
    }

    public String getPkg_url() {
        return this.pkg_url;
    }

    public String getSt() {
        return this.st;
    }

    public String getEt() {
        return this.et;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPkg() {
        return this.pkg;
    }

    public String getDeeplink() {
        return this.deeplink;
    }

    public int getGiftimes() {
        return this.giftimes;
    }

    public int getLocaltime() {
        return this.localtime;
    }

    public int getBox_reddot() {
        return this.box_reddot;
    }

    public int getShift() {
        return this.shift;
    }

    public String getBackground() {
        return this.background;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public int getRes_type() {
        return this.res_type;
    }

    public String getDes() {
        return this.des;
    }

    public String getThird_imp_url() {
        return this.third_imp_url;
    }

    public String getClick_tracking_url() {
        return this.click_tracking_url;
    }

    public int getSug_type() {
        return this.sug_type;
    }

    public int getSplashShowtime() {
        if (this.splash_showtime < 1 || this.splash_showtime > 100) {
            return 5;
        }
        return this.splash_showtime;
    }

    public String getSplashImageUrl() {
        int screenWidth = Commons.getScreenWidth(this.context);
        if (screenWidth <= 0) {
            return null;
        }
        return getCalculatedUrl(screenWidth);
    }

    private String getCalculatedUrl(int width) {
        ArrayList anonymousClass1 = new ArrayList<Integer>() {
            {
                add(Integer.valueOf(1080));
                add(Integer.valueOf(720));
                add(Integer.valueOf(540));
                add(Integer.valueOf(480));
                add(Integer.valueOf(Game1MemoryGridActivity.START_ANIMATION_DURATION));
            }
        };
        ArrayList arrayList = new ArrayList();
        Map hashMap = new HashMap();
        resetUrlMap(hashMap);
        Iterator it = anonymousClass1.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            if (intValue <= width) {
                arrayList.add(Integer.valueOf(intValue));
                it.remove();
            }
        }
        for (intValue = anonymousClass1.size() - 1; intValue >= 0; intValue--) {
            arrayList.add(anonymousClass1.get(intValue));
        }
        anonymousClass1.clear();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            int intValue2 = ((Integer) it2.next()).intValue();
            if (!TextUtils.isEmpty((CharSequence) hashMap.get(Integer.valueOf(intValue2)))) {
                return (String) hashMap.get(Integer.valueOf(intValue2));
            }
        }
        return null;
    }

    private void resetUrlMap(Map<Integer, String> urlMap) {
        if (urlMap != null) {
            urlMap.clear();
            urlMap.put(Integer.valueOf(1080), this.pic_url_w1080);
            urlMap.put(Integer.valueOf(720), this.pic_url_w720);
            urlMap.put(Integer.valueOf(540), this.pic_url_w540);
            urlMap.put(Integer.valueOf(480), this.pic_url_w480);
            urlMap.put(Integer.valueOf(Game1MemoryGridActivity.START_ANIMATION_DURATION), this.pic_url_w400);
        }
    }

    public boolean isClickedInOneDay(String posId) {
        return isDoneInOneDay(posId, this.pkg + getTitleHashCode() + KEY_CLICKED_TIME, this.pkg + getTitleHashCode() + KEY_CLICKED);
    }

    public boolean isShowedInOneDay(String posId) {
        return isDoneInOneDay(posId, this.pkg + getTitleHashCode() + KEY_SHOWED_TIME, this.pkg + getTitleHashCode() + KEY_SHOWED);
    }

    private boolean isDoneInOneDay(String posId, String dayKey, String actionKey) {
        if (MixBoxAdHelper.isTodayHadFlag(posId, dayKey)) {
            return MixSPUtil.getBoolean(posId, actionKey, false);
        }
        MixBoxAdHelper.setTodayHadFlag(posId, dayKey);
        MixSPUtil.putBoolean(posId, actionKey, false);
        return false;
    }

    public int getTitleHashCode() {
        if (TextUtils.isEmpty(this.title)) {
            return 0;
        }
        return this.title.hashCode();
    }

    public boolean isValid(POS_TYPE type, int days) {
        boolean z = true;
        if (!isDataValid()) {
            g.a(TAG + this.title, "data invalid");
            return false;
        } else if (!isValidType(type)) {
            g.a(TAG + this.title, "invalid data type");
            return false;
        } else if (!isInValidTime(days)) {
            g.a(TAG + this.title, "out of valid time");
            return false;
        } else if (days > 0) {
            return true;
        } else {
            if (isInstalled(this.context)) {
                g.a(TAG + this.title, "is installed");
                if (!isHaveDeeplink()) {
                    return false;
                }
                boolean isMatchShift = isMatchShift();
                g.a(TAG + this.title, "is deeplink");
                return isMatchShift;
            } else if (isMatchExcludePkgs(this.context)) {
                g.a(TAG + this.title, "is installed");
                return false;
            } else {
                boolean isMatchShift2 = isMatchShift();
                String str = TAG + this.title;
                StringBuilder append = new StringBuilder().append("isShift:");
                if (this.shift != 1) {
                    z = false;
                }
                g.a(str, append.append(z).append(", whether can shift : ").append(isMatchShift2).toString());
                return isMatchShift2;
            }
        }
    }

    private boolean isDataValid() {
        if (this.type < 1 || this.type > 6 || this.priority.intValue() < 0 || this.priority.intValue() > 100 || TextUtils.isEmpty(this.pic_url) || ((this.mt_type != 8 && this.mt_type != 256 && this.mt_type != 64 && this.mt_type != 512) || TextUtils.isEmpty(this.pkg_url) || TextUtils.isEmpty(this.st) || TextUtils.isEmpty(this.et) || TextUtils.isEmpty(this.title) || TextUtils.isEmpty(this.pkg) || (isShift() && this.frequency <= 0))) {
            return false;
        }
        return true;
    }

    private boolean isInValidTime(int days) {
        return MixBoxAdHelper.isInLimitTime(this.localtime, this.st, this.et, days);
    }

    private boolean isValidType(POS_TYPE type) {
        if (type == null) {
            return true;
        }
        switch (type) {
            case enum_box:
                if (getType() == 2 || getType() == 1) {
                    return true;
                }
                return false;
            case enum_common_card:
                if (getType() == 3 || getType() == 4) {
                    return true;
                }
                return false;
            case enum_splash:
                if (getType() == 5 || getType() == 6) {
                    return true;
                }
                return false;
            default:
                return true;
        }
    }

    private boolean isMatchShift() {
        if (isShift() && !isCanShift(this.posId, getPkg(), getTitle())) {
            return false;
        }
        return true;
    }

    private boolean isShift() {
        return this.shift == 1;
    }

    private boolean isCanShift(String posId, String pkg, String title) {
        long j = MixSPUtil.getLong(posId, pkg + getTitleHashCode(), 0);
        if (j != 0 && System.currentTimeMillis() - j <= (Long.valueOf((long) this.frequency).longValue() * 60) * 1000) {
            return false;
        }
        return true;
    }

    private boolean isHaveDeeplink() {
        return this.mt_type == 512 && !TextUtils.isEmpty(this.deeplink);
    }

    private boolean isInstalled(Context context) {
        if (TextUtils.isEmpty(this.pkg) || context == null) {
            return false;
        }
        return Commons.isHasPackage(context, this.pkg);
    }

    private boolean isMatchExcludePkgs(Context context) {
        if (this.mlistExcludePkgs == null || this.mlistExcludePkgs.size() <= 0 || context == null) {
            return false;
        }
        for (String isHasPackage : this.mlistExcludePkgs) {
            if (Commons.isHasPackage(context, isHasPackage)) {
                return true;
            }
        }
        return false;
    }

    public void savePkg(String posId, String pkg, String adTitle, long showTime) {
        g.a(TAG + adTitle, " impression");
        MixSPUtil.putBoolean(posId, pkg + getTitleHashCode() + KEY_SHOWED, true);
        final String str = pkg;
        final long j = showTime;
        final String str2 = posId;
        final String str3 = adTitle;
        new Thread("savePkg thread") {
            public void run() {
                super.run();
                if (!TextUtils.isEmpty(str) && j != 0) {
                    MixSPUtil.putLong(str2, str + str3.hashCode(), j);
                }
            }
        }.start();
    }

    public int compareTo(MixBeans bean) {
        return this.priority.compareTo(bean.priority);
    }
}
