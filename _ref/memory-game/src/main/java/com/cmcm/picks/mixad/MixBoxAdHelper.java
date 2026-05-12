package com.cmcm.picks.mixad;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.utils.g;
import com.facebook.share.internal.ShareConstants;
import com.my.target.nativeads.banners.NavigationType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONObject;

public class MixBoxAdHelper {
    private static final String THREADNAME = "clearUselessPkg";
    private static final int TIME_GMT = 2;
    private static final int TIME_LOCAL = 1;

    public static List<MixBeans> analyzeListStr(String strPosId, List<String> listJsonStr, Context context) {
        if (!(listJsonStr == null || listJsonStr.isEmpty())) {
            List<MixBeans> arrayList = new ArrayList();
            try {
                for (String jSONObject : listJsonStr) {
                    JSONObject jSONObject2 = new JSONObject(jSONObject);
                    MixBeans mixBeans = new MixBeans();
                    mixBeans.posId = strPosId;
                    mixBeans.context = context.getApplicationContext();
                    mixBeans.source = jSONObject2.optInt(ShareConstants.FEED_SOURCE_PARAM);
                    mixBeans.type = jSONObject2.optInt("type");
                    mixBeans.priority = Integer.valueOf(jSONObject2.optInt("priority", -1));
                    mixBeans.mt_type = jSONObject2.optInt("mt_type");
                    mixBeans.giftimes = jSONObject2.optInt("giftimes");
                    mixBeans.localtime = jSONObject2.optInt("localtime");
                    mixBeans.box_reddot = jSONObject2.optInt("box_reddot");
                    mixBeans.shift = jSONObject2.optInt("shift");
                    mixBeans.frequency = jSONObject2.optInt("frequency");
                    mixBeans.button_txt = jSONObject2.optString("button_txt");
                    mixBeans.desc = jSONObject2.optString("desc");
                    mixBeans.pic_url = jSONObject2.optString("pic_url");
                    mixBeans.pkg_url = jSONObject2.optString("pkg_url");
                    mixBeans.st = jSONObject2.optString("st");
                    mixBeans.et = jSONObject2.optString("et");
                    mixBeans.title = jSONObject2.optString("title");
                    updatePkgs(jSONObject2.optString("pkg"), mixBeans);
                    mixBeans.deeplink = jSONObject2.optString(NavigationType.DEEPLINK);
                    mixBeans.background = jSONObject2.optString("background");
                    mixBeans.splash_showtime = jSONObject2.optInt("showtime");
                    mixBeans.pic_url_w400 = jSONObject2.optString("pic_url_w400");
                    mixBeans.pic_url_w480 = jSONObject2.optString("pic_url_w480");
                    mixBeans.pic_url_w540 = jSONObject2.optString("pic_url_w540");
                    mixBeans.pic_url_w720 = jSONObject2.optString("pic_url_w720");
                    mixBeans.pic_url_w1080 = jSONObject2.optString("pic_url_w1080");
                    mixBeans.res_type = jSONObject2.optInt("res_type");
                    mixBeans.des = jSONObject2.optString("des");
                    mixBeans.third_imp_url = jSONObject2.optString("third_imp_url");
                    mixBeans.click_tracking_url = jSONObject2.optString("click_tracking_url");
                    mixBeans.sug_type = jSONObject2.optInt("sug_type");
                    arrayList.add(mixBeans);
                }
                if (!(arrayList == null || arrayList.isEmpty())) {
                    Collections.sort(arrayList);
                }
                return arrayList;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static void clearUselessPkg(String strPosId, List<MixBeans> mListAd) {
        if (mListAd != null && !mListAd.isEmpty() && !TextUtils.isEmpty(strPosId)) {
            Map all = MixSPUtil.getAll();
            Set<String> keySet = all == null ? null : all.keySet();
            if (keySet != null) {
                for (String str : keySet) {
                    for (int i = 0; i < mListAd.size(); i++) {
                        String valueOf = String.valueOf(strPosId + ((MixBeans) mListAd.get(i)).pkg + ((MixBeans) mListAd.get(i)).getTitleHashCode());
                        if (!TextUtils.isEmpty(str) && str.equals(valueOf)) {
                            break;
                        }
                        if (i == mListAd.size() - 1) {
                            MixSPUtil.remove(strPosId, str);
                        }
                    }
                }
            }
        }
    }

    private static void updatePkgs(String strPkg, MixBeans beans) {
        if (!TextUtils.isEmpty(strPkg) && beans != null) {
            String[] split = strPkg.split(";");
            if (split == null || split.length <= 0) {
                split = new String[]{strPkg};
            }
            for (Object obj : r0) {
                if (!TextUtils.isEmpty(obj)) {
                    if (beans.mlistExcludePkgs == null) {
                        beans.mlistExcludePkgs = new ArrayList();
                    }
                    beans.mlistExcludePkgs.add(obj.trim());
                }
            }
            if (beans.mlistExcludePkgs != null && beans.mlistExcludePkgs.size() > 0) {
                beans.pkg = (String) beans.mlistExcludePkgs.get(0);
            }
        }
    }

    public static boolean isInLimitTime(int localtime, String startTime, String endTime, int days) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
        if (2 == localtime) {
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
            return false;
        }
        try {
            Date parse = simpleDateFormat.parse(startTime);
            Date parse2 = simpleDateFormat.parse(endTime);
            if (parse2.before(parse)) {
                return false;
            }
            if (days > 0) {
                Date date = new Date();
                Date date2 = new Date();
                date2.setDate(date.getDate() + days);
                if (date.after(parse2) || date2.before(parse)) {
                    return false;
                }
                return true;
            } else if (parse.before(new Date()) && parse2.after(new Date())) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            if (!g.a) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isTodayHadFlag(String posid, String key) {
        Object string = MixSPUtil.getString(posid, key, "");
        if (!TextUtils.isEmpty(string) && getTodayIntevalDaysEx(string, -1) == 0) {
            return true;
        }
        return false;
    }

    public static void setTodayHadFlag(String posId, String key) {
        if (!TextUtils.isEmpty(key)) {
            MixSPUtil.putString(posId, key, getNowFormatDate("yyyyMMdd"));
        }
    }

    private static String getNowFormatDate(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    private static int getTodayIntevalDaysEx(String startDate, int nValueDefault) {
        try {
            if (TextUtils.isEmpty(startDate)) {
                return nValueDefault;
            }
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date parse = simpleDateFormat.parse(startDate);
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
            if (date.getTime() >= parse.getTime()) {
                return (int) ((date.getTime() - parse.getTime()) / 86400000);
            }
            return nValueDefault;
        } catch (Exception e) {
            return nValueDefault;
        }
    }
}
