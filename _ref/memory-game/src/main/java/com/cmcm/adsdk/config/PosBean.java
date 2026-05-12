package com.cmcm.adsdk.config;

import android.text.TextUtils;
import com.cmcm.adsdk.Const;

public class PosBean implements Comparable<PosBean> {
    public static final String TABLE_NAME = "posinfo";
    private static final String TAG = "PosBean";
    public int adtype;
    public String name;
    public String parameter;
    public String placeid;
    public Integer weight;

    public boolean isFBAd() {
        return Const.KEY_FB.equals(this.name);
    }

    public boolean isCMAd() {
        return Const.KEY_CM.equals(this.name);
    }

    public int getAdType() {
        return this.adtype;
    }

    public String getAdName() {
        return this.name;
    }

    public boolean isValidInfo() {
        return this.weight.intValue() > 0;
    }

    public PosBean(String name, String placeid, Integer weight, int adType, String parameter) {
        this.adtype = adType;
        if (!TextUtils.isEmpty(name)) {
            name.trim();
        }
        this.name = name;
        this.placeid = placeid;
        this.weight = weight;
        this.parameter = parameter;
    }

    public int compareTo(PosBean bean) {
        return bean.weight.compareTo(this.weight);
    }
}
