package com.cmcm.adsdk;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.picks.init.a;
import com.cmcm.picks.loader.f;
import com.cmcm.picks.loader.g;
import com.cmcm.utils.ReceiverUtils;
import com.cmcm.utils.ThreadHelper;

public abstract class CMAdManager {
    public static final int DEFAULT_SSPID = -1;
    public static final int NATIVEAD_RP = 1;
    public static final int THIRD_RP = 2;
    public static boolean isSetDebugCounutry = false;
    public static int mAdResource;
    private static Age mAge;
    private static Context mContext;
    private static Gender mGender;
    private static String mMid;
    private static CMBaseFactory sAdFactory = null;
    private static String sChannelId;

    public enum Age {
        AGE_RANGE_ONE(1),
        AGE_RANGE_TWO(2),
        AGE_RANGE_THREE(3),
        AGE_RANGE_FOUR(4),
        AGE_RANGE_FIVE(5);
        
        private int nAge;

        private Age(int nAge) {
            this.nAge = nAge;
        }

        public int getAge() {
            return this.nAge;
        }
    }

    public enum Gender {
        MALE("M"),
        FEMAL("F");
        
        private String gender;

        private Gender(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return this.gender;
        }
    }

    public static void applicationInit(Context context, String mid) {
        applicationInit(context, mid, "");
    }

    public static void applicationInit(Context context, String mid, String channelId) {
        if (TextUtils.isEmpty(mid)) {
            throw new IllegalArgumentException("PublisherID cannot be null or empty");
        }
        mAdResource = 2;
        mContext = context;
        mMid = mid;
        sChannelId = channelId;
        a.getInstance().init();
        freshConfig();
        ReceiverUtils.a(mContext);
        getFactory();
    }

    private static void freshConfig() {
        if (g.a("config_last_save_time", 86400000)) {
            ThreadHelper.post(new Runnable() {
                public void run() {
                    f.a().a(CMAdManager.getMid());
                }
            });
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getMid() {
        return mMid;
    }

    public static String getChannelId() {
        return sChannelId;
    }

    public static void enableLog() {
        com.cmcm.utils.g.a = true;
    }

    public static void enableTestCountry() {
        isSetDebugCounutry = true;
    }

    public static void setGender(Gender gender) {
        mGender = gender;
    }

    public static Gender getGender() {
        return mGender;
    }

    public static void setAge(int nAge) {
        if (nAge >= 41) {
            mAge = Age.AGE_RANGE_FOUR;
        } else if (nAge >= 31) {
            mAge = Age.AGE_RANGE_THREE;
        } else if (nAge >= 25) {
            mAge = Age.AGE_RANGE_TWO;
        } else if (nAge >= 18) {
            mAge = Age.AGE_RANGE_ONE;
        } else {
            mAge = Age.AGE_RANGE_FIVE;
        }
    }

    public static Age getAge() {
        return mAge;
    }

    public static CMBaseFactory getFactory() {
        if (sAdFactory == null) {
            try {
                Class cls = Class.forName("com.cmcm.adsdk.CMAdManagerFactory");
                if (sAdFactory == null) {
                    sAdFactory = (CMBaseFactory) cls.newInstance();
                    sAdFactory.initConfig();
                    sAdFactory.clearVastCache(mContext);
                }
            } catch (Exception e) {
            }
        }
        return sAdFactory;
    }
}
