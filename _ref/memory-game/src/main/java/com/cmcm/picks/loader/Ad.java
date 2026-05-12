package com.cmcm.picks.loader;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class Ad {
    public static final int MT_TYPE_OPEN_BROWSER = 256;
    public static final int MT_TYPE_OPEN_DEEPLINK = 512;
    public static final int MT_TYPE_OPEN_DOWNLOAD = 8;
    public static final int MT_TYPE_OPEN_INTERNAL = 64;
    public static final int SHOW_TYPE_AD_MPA = 70008;
    public static final int SHOW_TYPE_HAVE_PIC_BIG_CARD = 50000;
    public static final int SHOW_TYPE_NEWS_SMALL_PIC = 70003;
    public static final int SHOW_TYPE_NEWS_THREE_PIC = 70002;
    public static final int SHOW_TYPE_NO_PIC_SMALL_CARD = 50001;
    public static final int SHOW_TYPE_VAST = 50006;
    private int app_show_type = -1;
    private String background;
    private String button_txt;
    private String des;
    private String desc;
    private String download_num;
    private String html;
    private String mClickTrackingUrl;
    private long mCreateTime;
    private String mDeepLink;
    private String mExtPicks;
    private String mExtension;
    private boolean mIsShowed = false;
    private String mPosid;
    private int mPriority;
    private String mThirdImpUrl;
    private String mpa;
    private MpaModule mpaModule;
    private int mt_type;
    private String pic_url;
    private String pkg;
    private String pkg_size;
    private String pkg_url;
    private double rating;
    private int res_type;
    private String title;

    public boolean isPriority() {
        return getPriority() == 1;
    }

    public String getClickTrackingUrl() {
        return this.mClickTrackingUrl;
    }

    public void setClickTrackingUrl(String clickTrackingUrl) {
        this.mClickTrackingUrl = clickTrackingUrl;
    }

    public String getThirdImpUrl() {
        return this.mThirdImpUrl;
    }

    public void setThirdImpUrl(String thirdImpUrl) {
        this.mThirdImpUrl = thirdImpUrl;
    }

    public String getPosid() {
        return this.mPosid;
    }

    public void setPosid(String posid) {
        this.mPosid = posid;
    }

    public String getExtPick() {
        return this.mExtPicks;
    }

    public void setExtPicks(String extPicks) {
        this.mExtPicks = extPicks;
    }

    public long getCreateTime() {
        return this.mCreateTime;
    }

    public void setCreateTime(long createTime) {
        this.mCreateTime = createTime;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public void setPriority(int priority) {
        this.mPriority = priority;
    }

    public String getDeepLink() {
        return this.mDeepLink;
    }

    public void setDeepLink(String deeplink) {
        this.mDeepLink = deeplink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setPicUrl(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPicUrl() {
        return this.pic_url;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getPkg() {
        return this.pkg;
    }

    public void setPkgUrl(String pkg_url) {
        this.pkg_url = pkg_url;
    }

    public String getPkgUrl() {
        return this.pkg_url;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return this.des;
    }

    public void setDownloadNum(String download_num) {
        this.download_num = download_num;
    }

    public String getDownloadNum() {
        return this.download_num;
    }

    public void setPkgSize(String ps) {
        this.pkg_size = ps;
    }

    public String getpkg_size() {
        return this.pkg_size;
    }

    public void setResType(int rt) {
        this.res_type = rt;
    }

    public int getResType() {
        return this.res_type;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setMtType(int mt_type) {
        this.mt_type = mt_type;
    }

    public int getMtType() {
        return this.mt_type;
    }

    public int getAppShowType() {
        return this.app_show_type;
    }

    public void setAppShowType(int app_show_type) {
        this.app_show_type = app_show_type;
    }

    public boolean isOpenInternal() {
        return this.mt_type == 64;
    }

    public boolean isOpenBrowser() {
        return this.mt_type == 256;
    }

    public boolean isDeepLink() {
        return this.mt_type == 512;
    }

    public boolean isAvailAble() {
        return System.currentTimeMillis() - getCreateTime() < g.b(getPosid());
    }

    public void setShowed(boolean isShow) {
        this.mIsShowed = isShow;
    }

    public boolean isShowed() {
        return this.mIsShowed;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBackground() {
        return this.background;
    }

    public String getButtonTxt() {
        return this.button_txt;
    }

    public void setButtonTxt(String button_txt) {
        this.button_txt = button_txt;
    }

    public String getExtension() {
        return this.mExtension;
    }

    public void setExtension(String strExtension) {
        this.mExtension = strExtension;
    }

    public static Ad createAd(String packageName) {
        Ad ad = new Ad();
        ad.setDes("");
        ad.pkg = packageName;
        return ad;
    }

    public String getHtml() {
        return this.html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<String> getExtPics() {
        List<String> arrayList = new ArrayList();
        CharSequence background = getBackground();
        if (!TextUtils.isEmpty(background)) {
            arrayList.add(background);
        }
        Object extPick = getExtPick();
        if (!TextUtils.isEmpty(extPick)) {
            try {
                JSONArray jSONArray = new JSONArray(extPick);
                for (int i = 0; i < jSONArray.length(); i++) {
                    Object obj = jSONArray.get(i);
                    if (obj != null) {
                        arrayList.add(obj.toString());
                    }
                }
            } catch (Exception e) {
            }
        }
        return arrayList;
    }

    public boolean isMtTypeAvail() {
        return this.mt_type == 256 || this.mt_type == 512 || this.mt_type == 8 || this.mt_type == 64;
    }

    public static int getShowTypeHavePicBigCard() {
        return SHOW_TYPE_HAVE_PIC_BIG_CARD;
    }

    public String getMpa() {
        return this.mpa;
    }

    public void setMpa(String mpa) {
        this.mpa = mpa;
    }

    public MpaModule getMpaModule() {
        return this.mpaModule;
    }

    public void setMpaModule(MpaModule mpaModule) {
        this.mpaModule = mpaModule;
    }
}
