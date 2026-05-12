package com.cmcm.adsdk;

import com.mopub.common.AdType;

public final class Const {
    public static String CM_AD_DETAIL_URL = "http://ad.cmcm.com/";
    public static final String CONFIG_URL = "http://unconf.adkmob.com/b/";
    public static final String CONFIG_URL_CN = "http://unconf.mobad.ijinshan.com/b/";
    public static final String CONFIG_URL_UFS = "http://ufs.adkmob.com/p/";
    public static final String HOST_NAME = "unconf.adkmob.com";
    public static final String HOST_NAME_CN = "unconf.mobad.ijinshan.com";
    public static final String HOST_NAME_UFS = "ufs.adkmob.com";
    public static final String KEY_AB = "ab";
    public static final String KEY_BD = "bd";
    public static final String KEY_CB = "cb";
    public static final String KEY_CM = "cm";
    public static final String KEY_CM_BANNER = "cmb";
    public static final String KEY_CM_INTERSTITIAL = "cmi";
    public static final String KEY_FACEBOOK_VIDEO = "fbv";
    public static final String KEY_FB = "fb";
    public static final String KEY_FB_B = "fb_b";
    public static final String KEY_FB_H = "fb_h";
    public static final String KEY_FB_INTERSTITIAL = "fbi";
    public static final String KEY_FB_L = "fb_l";
    public static final String KEY_GDT = "gdt";
    public static final String KEY_ICLICK = "ic";
    public static final String KEY_ICLICK_VIDEO = "ic_video";
    public static final String KEY_IM = "im";
    public static final String KEY_JUHE = "ad";
    public static final String KEY_LOOPME_VIDEO = "lpv";
    public static final String KEY_MOPUB_VIDEO = "mpv";
    public static final String KEY_MP = "mp";
    public static final String KEY_MP_BANNER = "mpb";
    public static final String KEY_PM = "pm";
    public static final String KEY_VAST_VIDEO = "vav";
    public static final String KEY_VUNGLE_VIDEO = "vgv";
    public static final String KEY_YH = "yh";
    public static final int NET_TIMEOUT = 15000;
    public static String REPORT_BANNER_SUFFIX = "banner";
    public static String REPORT_INTERSTITIAL_SUFFIX = AdType.INTERSTITIAL;
    public static String REPORT_SPLASH_SUFFIX = "splash";
    public static final String TAG = "CMCMADSDK";
    public static final String VERSION = "3.4.7";

    public static final class cacheTime {
        public static final long admob = 3600000;
        public static final long baidu = 1800000;
        public static final long cm = 3600000;
        public static final long facebook = 10800000;
        public static final long gdt = 1800000;
        public static final long min_cache_time = 1800000;
        public static final long mopub = 3600000;
        public static final long pubmatic = 3600000;
        public static final long yahoo = 4500000;
    }

    public static final class pkgName {
        public static final String admob = "com.admob.native";
        public static final String baidu = "com.baidu.ad";
        public static final String cm = "com.cmcm.ad";
        public static final String cmb = "com.ad";
        public static final String facebook = "com.facebook.ad";
        public static final String gdt = "com.gdt.ad";
        public static final String iclick = "com.iclick.ad";
        public static final String mopub = "com.mopub.ad";
        public static final String pubmatic = "com.pubmatic.ad";
        public static final String yahoo = "com.yahoo.ad";
    }

    public static final class res {
        public static final int admob = 3002;
        public static final int baidu = 3004;
        public static final int facebook = 3000;
        public static final int gdt = 500;
        public static final int iclick = 3009;
        public static final int mopub = 3003;
        public static final int pubmatic = 3007;
        public static final int yahoo = 3008;
    }
}
