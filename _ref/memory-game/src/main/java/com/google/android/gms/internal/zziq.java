package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import com.facebook.applinks.AppLinkData;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.SearchAdRequestParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zziz.zza;
import io.branch.indexing.ContentDiscoveryManifest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public final class zziq {
    private static final SimpleDateFormat zzcel = new SimpleDateFormat("yyyyMMdd", Locale.US);

    public static AdResponseParcel zza(Context context, AdRequestInfoParcel adRequestInfoParcel, String str) {
        String optString;
        try {
            String str2;
            JSONObject jSONObject = new JSONObject(str);
            String optString2 = jSONObject.optString("ad_base_url", null);
            Object optString3 = jSONObject.optString("ad_url", null);
            String optString4 = jSONObject.optString("ad_size", null);
            String optString5 = jSONObject.optString("ad_slot_size", optString4);
            boolean z = (adRequestInfoParcel == null || adRequestInfoParcel.zzcax == 0) ? false : true;
            CharSequence optString6 = jSONObject.optString("ad_json", null);
            if (optString6 == null) {
                optString6 = jSONObject.optString("ad_html", null);
            }
            if (optString6 == null) {
                optString6 = jSONObject.optString("body", null);
            }
            long j = -1;
            String optString7 = jSONObject.optString("debug_dialog", null);
            long j2 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1;
            optString = jSONObject.optString("orientation", null);
            int i = -1;
            if (DeviceInfo.ORIENTATION_PORTRAIT.equals(optString)) {
                i = zzu.zzfs().zztk();
            } else if (DeviceInfo.ORIENTATION_LANDSCAPE.equals(optString)) {
                i = zzu.zzfs().zztj();
            }
            AdResponseParcel adResponseParcel = null;
            if (!TextUtils.isEmpty(optString6) || TextUtils.isEmpty(optString3)) {
                CharSequence charSequence = optString6;
            } else {
                adResponseParcel = zzip.zza(adRequestInfoParcel, context, adRequestInfoParcel.zzaow.zzcs, optString3, null, null, null, null);
                optString2 = adResponseParcel.zzbto;
                str2 = adResponseParcel.body;
                j = adResponseParcel.zzccc;
            }
            if (str2 == null) {
                return new AdResponseParcel(0);
            }
            long j3;
            String optString8;
            String str3;
            boolean optBoolean;
            JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
            List list = adResponseParcel == null ? null : adResponseParcel.zzbnm;
            if (optJSONArray != null) {
                list = zza(optJSONArray, list);
            }
            optJSONArray = jSONObject.optJSONArray("impression_urls");
            List list2 = adResponseParcel == null ? null : adResponseParcel.zzbnn;
            if (optJSONArray != null) {
                list2 = zza(optJSONArray, list2);
            }
            optJSONArray = jSONObject.optJSONArray("manual_impression_urls");
            List list3 = adResponseParcel == null ? null : adResponseParcel.zzcca;
            if (optJSONArray != null) {
                list3 = zza(optJSONArray, list3);
            }
            if (adResponseParcel != null) {
                if (adResponseParcel.orientation != -1) {
                    i = adResponseParcel.orientation;
                }
                if (adResponseParcel.zzcbx > 0) {
                    j3 = adResponseParcel.zzcbx;
                    optString8 = jSONObject.optString("active_view");
                    str3 = null;
                    optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
                    if (optBoolean) {
                        str3 = jSONObject.optString("ad_passback_url", null);
                    }
                    return new AdResponseParcel(adRequestInfoParcel, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString8, jSONObject.optBoolean("custom_render_allowed", false), z, adRequestInfoParcel.zzcaz, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), RewardItemParcel.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), AutoClickProtectionConfigurationParcel.zzh(jSONObject.optJSONObject("auto_protection_configuration")), adRequestInfoParcel.zzcbq, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optString("safe_browsing"), jSONObject.optBoolean("render_in_browser", adRequestInfoParcel.zzbnq), optString5);
                }
            }
            j3 = j2;
            optString8 = jSONObject.optString("active_view");
            str3 = null;
            optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            if (optBoolean) {
                str3 = jSONObject.optString("ad_passback_url", null);
            }
            return new AdResponseParcel(adRequestInfoParcel, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString8, jSONObject.optBoolean("custom_render_allowed", false), z, adRequestInfoParcel.zzcaz, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), RewardItemParcel.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), AutoClickProtectionConfigurationParcel.zzh(jSONObject.optJSONObject("auto_protection_configuration")), adRequestInfoParcel.zzcbq, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optString("safe_browsing"), jSONObject.optBoolean("render_in_browser", adRequestInfoParcel.zzbnq), optString5);
        } catch (JSONException e) {
            String str4 = "Could not parse the inline ad response: ";
            optString = String.valueOf(e.getMessage());
            zzb.zzcx(optString.length() != 0 ? str4.concat(optString) : new String(str4));
            return new AdResponseParcel(0);
        }
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new LinkedList();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    @Nullable
    public static JSONObject zza(Context context, AdRequestInfoParcel adRequestInfoParcel, zziv com_google_android_gms_internal_zziv, zza com_google_android_gms_internal_zziz_zza, Location location, zzcv com_google_android_gms_internal_zzcv, String str, List<String> list, Bundle bundle, String str2) {
        String str3;
        String valueOf;
        try {
            Object obj;
            HashMap hashMap = new HashMap();
            if (list.size() > 0) {
                hashMap.put("eid", TextUtils.join(",", list));
            }
            if (adRequestInfoParcel.zzcaq != null) {
                hashMap.put("ad_pos", adRequestInfoParcel.zzcaq);
            }
            zza(hashMap, adRequestInfoParcel.zzcar);
            if (adRequestInfoParcel.zzapa.zzaut != null) {
                obj = null;
                Object obj2 = null;
                for (AdSizeParcel adSizeParcel : adRequestInfoParcel.zzapa.zzaut) {
                    if (!adSizeParcel.zzauv && r3 == null) {
                        hashMap.put("format", adSizeParcel.zzaur);
                        obj2 = 1;
                    }
                    if (adSizeParcel.zzauv && r2 == null) {
                        hashMap.put("fluid", "height");
                        obj = 1;
                    }
                    if (obj2 != null && r2 != null) {
                        break;
                    }
                }
            } else {
                hashMap.put("format", adRequestInfoParcel.zzapa.zzaur);
                if (adRequestInfoParcel.zzapa.zzauv) {
                    hashMap.put("fluid", "height");
                }
            }
            if (adRequestInfoParcel.zzapa.width == -1) {
                hashMap.put("smart_w", "full");
            }
            if (adRequestInfoParcel.zzapa.height == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (adRequestInfoParcel.zzapa.zzaut != null) {
                StringBuilder stringBuilder = new StringBuilder();
                obj = null;
                for (AdSizeParcel adSizeParcel2 : adRequestInfoParcel.zzapa.zzaut) {
                    if (adSizeParcel2.zzauv) {
                        obj = 1;
                    } else {
                        if (stringBuilder.length() != 0) {
                            stringBuilder.append("|");
                        }
                        stringBuilder.append(adSizeParcel2.width == -1 ? (int) (((float) adSizeParcel2.widthPixels) / com_google_android_gms_internal_zziv.zzcbd) : adSizeParcel2.width);
                        stringBuilder.append("x");
                        stringBuilder.append(adSizeParcel2.height == -2 ? (int) (((float) adSizeParcel2.heightPixels) / com_google_android_gms_internal_zziv.zzcbd) : adSizeParcel2.height);
                    }
                }
                if (obj != null) {
                    if (stringBuilder.length() != 0) {
                        stringBuilder.insert(0, "|");
                    }
                    stringBuilder.insert(0, "320x50");
                }
                hashMap.put("sz", stringBuilder);
            }
            if (adRequestInfoParcel.zzcax != 0) {
                hashMap.put("native_version", Integer.valueOf(adRequestInfoParcel.zzcax));
                if (!adRequestInfoParcel.zzapa.zzauw) {
                    hashMap.put("native_templates", adRequestInfoParcel.zzaps);
                    hashMap.put("native_image_orientation", zzc(adRequestInfoParcel.zzapo));
                    if (!adRequestInfoParcel.zzcbi.isEmpty()) {
                        hashMap.put("native_custom_templates", adRequestInfoParcel.zzcbi);
                    }
                }
            }
            hashMap.put("slotname", adRequestInfoParcel.zzaou);
            hashMap.put(ContentDiscoveryManifest.PACKAGE_NAME_KEY, adRequestInfoParcel.applicationInfo.packageName);
            if (adRequestInfoParcel.zzcas != null) {
                hashMap.put("vc", Integer.valueOf(adRequestInfoParcel.zzcas.versionCode));
            }
            hashMap.put("ms", str);
            hashMap.put("seq_num", adRequestInfoParcel.zzcau);
            hashMap.put("session_id", adRequestInfoParcel.zzcav);
            hashMap.put("js", adRequestInfoParcel.zzaow.zzcs);
            zza(hashMap, com_google_android_gms_internal_zziv, com_google_android_gms_internal_zziz_zza, adRequestInfoParcel.zzcbv);
            zza(hashMap, str2);
            hashMap.put("platform", Build.MANUFACTURER);
            hashMap.put("submodel", Build.MODEL);
            if (location != null) {
                zza(hashMap, location);
            } else if (adRequestInfoParcel.zzcar.versionCode >= 2 && adRequestInfoParcel.zzcar.zzatu != null) {
                zza(hashMap, adRequestInfoParcel.zzcar.zzatu);
            }
            if (adRequestInfoParcel.versionCode >= 2) {
                hashMap.put("quality_signals", adRequestInfoParcel.zzcaw);
            }
            if (adRequestInfoParcel.versionCode >= 4 && adRequestInfoParcel.zzcaz) {
                hashMap.put("forceHttps", Boolean.valueOf(adRequestInfoParcel.zzcaz));
            }
            if (bundle != null) {
                hashMap.put("content_info", bundle);
            }
            if (adRequestInfoParcel.versionCode >= 5) {
                hashMap.put("u_sd", Float.valueOf(adRequestInfoParcel.zzcbd));
                hashMap.put("sh", Integer.valueOf(adRequestInfoParcel.zzcbc));
                hashMap.put("sw", Integer.valueOf(adRequestInfoParcel.zzcbb));
            } else {
                hashMap.put("u_sd", Float.valueOf(com_google_android_gms_internal_zziv.zzcbd));
                hashMap.put("sh", Integer.valueOf(com_google_android_gms_internal_zziv.zzcbc));
                hashMap.put("sw", Integer.valueOf(com_google_android_gms_internal_zziv.zzcbb));
            }
            if (adRequestInfoParcel.versionCode >= 6) {
                if (!TextUtils.isEmpty(adRequestInfoParcel.zzcbe)) {
                    try {
                        hashMap.put("view_hierarchy", new JSONObject(adRequestInfoParcel.zzcbe));
                    } catch (Throwable e) {
                        zzb.zzd("Problem serializing view hierarchy to JSON", e);
                    }
                }
                hashMap.put("correlation_id", Long.valueOf(adRequestInfoParcel.zzcbf));
            }
            if (adRequestInfoParcel.versionCode >= 7) {
                hashMap.put("request_id", adRequestInfoParcel.zzcbg);
            }
            if (adRequestInfoParcel.versionCode >= 11 && adRequestInfoParcel.zzcbk != null) {
                hashMap.put("capability", adRequestInfoParcel.zzcbk.toBundle());
            }
            if (adRequestInfoParcel.versionCode >= 12 && !TextUtils.isEmpty(adRequestInfoParcel.zzcbl)) {
                hashMap.put("anchor", adRequestInfoParcel.zzcbl);
            }
            if (adRequestInfoParcel.versionCode >= 13) {
                hashMap.put("android_app_volume", Float.valueOf(adRequestInfoParcel.zzcbm));
            }
            if (adRequestInfoParcel.versionCode >= 18) {
                hashMap.put("android_app_muted", Boolean.valueOf(adRequestInfoParcel.zzcbs));
            }
            if (adRequestInfoParcel.versionCode >= 14 && adRequestInfoParcel.zzcbn > 0) {
                hashMap.put("target_api", Integer.valueOf(adRequestInfoParcel.zzcbn));
            }
            if (adRequestInfoParcel.versionCode >= 15) {
                hashMap.put("scroll_index", Integer.valueOf(adRequestInfoParcel.zzcbo == -1 ? -1 : adRequestInfoParcel.zzcbo));
            }
            if (adRequestInfoParcel.versionCode >= 16) {
                hashMap.put("_activity_context", Boolean.valueOf(adRequestInfoParcel.zzcbp));
            }
            if (adRequestInfoParcel.versionCode >= 18) {
                if (!TextUtils.isEmpty(adRequestInfoParcel.zzcbt)) {
                    try {
                        hashMap.put("app_settings", new JSONObject(adRequestInfoParcel.zzcbt));
                    } catch (Throwable e2) {
                        zzb.zzd("Problem creating json from app settings", e2);
                    }
                }
                hashMap.put("render_in_browser", Boolean.valueOf(adRequestInfoParcel.zzbnq));
            }
            if (adRequestInfoParcel.versionCode >= 18) {
                hashMap.put("android_num_video_cache_tasks", Integer.valueOf(adRequestInfoParcel.zzcbu));
            }
            if (zzb.zzaz(2)) {
                str3 = "Ad Request JSON: ";
                valueOf = String.valueOf(zzu.zzfq().zzam((Map) hashMap).toString(2));
                zzkd.v(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            }
            return zzu.zzfq().zzam((Map) hashMap);
        } catch (JSONException e3) {
            str3 = "Problem serializing ad request to JSON: ";
            valueOf = String.valueOf(e3.getMessage());
            zzb.zzcx(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put(Model.KEY_loadtime, valueOf2);
        hashMap.put("uule", hashMap2);
    }

    private static void zza(HashMap<String, Object> hashMap, AdRequestParcel adRequestParcel) {
        String zzsy = zzkb.zzsy();
        if (zzsy != null) {
            hashMap.put("abf", zzsy);
        }
        if (adRequestParcel.zzatm != -1) {
            hashMap.put("cust_age", zzcel.format(new Date(adRequestParcel.zzatm)));
        }
        if (adRequestParcel.extras != null) {
            hashMap.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, adRequestParcel.extras);
        }
        if (adRequestParcel.zzatn != -1) {
            hashMap.put("cust_gender", Integer.valueOf(adRequestParcel.zzatn));
        }
        if (adRequestParcel.zzato != null) {
            hashMap.put("kw", adRequestParcel.zzato);
        }
        if (adRequestParcel.zzatq != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(adRequestParcel.zzatq));
        }
        if (adRequestParcel.zzatp) {
            hashMap.put("adtest", "on");
        }
        if (adRequestParcel.versionCode >= 2) {
            if (adRequestParcel.zzatr) {
                hashMap.put("d_imp_hdr", Integer.valueOf(1));
            }
            if (!TextUtils.isEmpty(adRequestParcel.zzats)) {
                hashMap.put("ppid", adRequestParcel.zzats);
            }
            if (adRequestParcel.zzatt != null) {
                zza((HashMap) hashMap, adRequestParcel.zzatt);
            }
        }
        if (adRequestParcel.versionCode >= 3 && adRequestParcel.zzatv != null) {
            hashMap.put("url", adRequestParcel.zzatv);
        }
        if (adRequestParcel.versionCode >= 5) {
            if (adRequestParcel.zzatx != null) {
                hashMap.put("custom_targeting", adRequestParcel.zzatx);
            }
            if (adRequestParcel.zzaty != null) {
                hashMap.put("category_exclusions", adRequestParcel.zzaty);
            }
            if (adRequestParcel.zzatz != null) {
                hashMap.put("request_agent", adRequestParcel.zzatz);
            }
        }
        if (adRequestParcel.versionCode >= 6 && adRequestParcel.zzaua != null) {
            hashMap.put("request_pkg", adRequestParcel.zzaua);
        }
        if (adRequestParcel.versionCode >= 7) {
            hashMap.put("is_designed_for_families", Boolean.valueOf(adRequestParcel.zzaub));
        }
    }

    private static void zza(HashMap<String, Object> hashMap, SearchAdRequestParcel searchAdRequestParcel) {
        Object obj;
        Object obj2 = null;
        if (Color.alpha(searchAdRequestParcel.zzawz) != 0) {
            hashMap.put("acolor", zzau(searchAdRequestParcel.zzawz));
        }
        if (Color.alpha(searchAdRequestParcel.backgroundColor) != 0) {
            hashMap.put("bgcolor", zzau(searchAdRequestParcel.backgroundColor));
        }
        if (!(Color.alpha(searchAdRequestParcel.zzaxa) == 0 || Color.alpha(searchAdRequestParcel.zzaxb) == 0)) {
            hashMap.put("gradientto", zzau(searchAdRequestParcel.zzaxa));
            hashMap.put("gradientfrom", zzau(searchAdRequestParcel.zzaxb));
        }
        if (Color.alpha(searchAdRequestParcel.zzaxc) != 0) {
            hashMap.put("bcolor", zzau(searchAdRequestParcel.zzaxc));
        }
        hashMap.put("bthick", Integer.toString(searchAdRequestParcel.zzaxd));
        switch (searchAdRequestParcel.zzaxe) {
            case 0:
                obj = "none";
                break;
            case 1:
                obj = "dashed";
                break;
            case 2:
                obj = "dotted";
                break;
            case 3:
                obj = "solid";
                break;
            default:
                obj = null;
                break;
        }
        if (obj != null) {
            hashMap.put("btype", obj);
        }
        switch (searchAdRequestParcel.zzaxf) {
            case 0:
                obj2 = "light";
                break;
            case 1:
                obj2 = "medium";
                break;
            case 2:
                obj2 = "dark";
                break;
        }
        if (obj2 != null) {
            hashMap.put("callbuttoncolor", obj2);
        }
        if (searchAdRequestParcel.zzaxg != null) {
            hashMap.put("channel", searchAdRequestParcel.zzaxg);
        }
        if (Color.alpha(searchAdRequestParcel.zzaxh) != 0) {
            hashMap.put("dcolor", zzau(searchAdRequestParcel.zzaxh));
        }
        if (searchAdRequestParcel.zzaxi != null) {
            hashMap.put("font", searchAdRequestParcel.zzaxi);
        }
        if (Color.alpha(searchAdRequestParcel.zzaxj) != 0) {
            hashMap.put("hcolor", zzau(searchAdRequestParcel.zzaxj));
        }
        hashMap.put("headersize", Integer.toString(searchAdRequestParcel.zzaxk));
        if (searchAdRequestParcel.zzaxl != null) {
            hashMap.put("q", searchAdRequestParcel.zzaxl);
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zziv com_google_android_gms_internal_zziv, zza com_google_android_gms_internal_zziz_zza, Bundle bundle) {
        hashMap.put("am", Integer.valueOf(com_google_android_gms_internal_zziv.zzcgd));
        hashMap.put("cog", zzab(com_google_android_gms_internal_zziv.zzcge));
        hashMap.put("coh", zzab(com_google_android_gms_internal_zziv.zzcgf));
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zziv.zzcgg)) {
            hashMap.put("carrier", com_google_android_gms_internal_zziv.zzcgg);
        }
        hashMap.put("gl", com_google_android_gms_internal_zziv.zzcgh);
        if (com_google_android_gms_internal_zziv.zzcgi) {
            hashMap.put("simulator", Integer.valueOf(1));
        }
        if (com_google_android_gms_internal_zziv.zzcgj) {
            hashMap.put("is_sidewinder", Integer.valueOf(1));
        }
        hashMap.put("ma", zzab(com_google_android_gms_internal_zziv.zzcgk));
        hashMap.put("sp", zzab(com_google_android_gms_internal_zziv.zzcgl));
        hashMap.put("hl", com_google_android_gms_internal_zziv.zzcgm);
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zziv.zzcgn)) {
            hashMap.put(ContentDiscoveryManifest.MANIFEST_VERSION_KEY, com_google_android_gms_internal_zziv.zzcgn);
        }
        hashMap.put("muv", Integer.valueOf(com_google_android_gms_internal_zziv.zzcgo));
        if (com_google_android_gms_internal_zziv.zzcgp != -2) {
            hashMap.put("cnt", Integer.valueOf(com_google_android_gms_internal_zziv.zzcgp));
        }
        hashMap.put("gnt", Integer.valueOf(com_google_android_gms_internal_zziv.zzcgq));
        hashMap.put("pt", Integer.valueOf(com_google_android_gms_internal_zziv.zzcgr));
        hashMap.put("rm", Integer.valueOf(com_google_android_gms_internal_zziv.zzcgs));
        hashMap.put("riv", Integer.valueOf(com_google_android_gms_internal_zziv.zzcgt));
        Bundle bundle2 = new Bundle();
        bundle2.putString("build", com_google_android_gms_internal_zziv.zzcgy);
        Bundle bundle3 = new Bundle();
        bundle3.putBoolean("is_charging", com_google_android_gms_internal_zziv.zzcgv);
        bundle3.putDouble("battery_level", com_google_android_gms_internal_zziv.zzcgu);
        bundle2.putBundle("battery", bundle3);
        bundle3 = new Bundle();
        bundle3.putInt("active_network_state", com_google_android_gms_internal_zziv.zzcgx);
        bundle3.putBoolean("active_network_metered", com_google_android_gms_internal_zziv.zzcgw);
        if (com_google_android_gms_internal_zziz_zza != null) {
            Bundle bundle4 = new Bundle();
            bundle4.putInt("predicted_latency_micros", 0);
            bundle4.putLong("predicted_down_throughput_bps", 0);
            bundle4.putLong("predicted_up_throughput_bps", 0);
            bundle3.putBundle("predictions", bundle4);
        }
        bundle2.putBundle("network", bundle3);
        bundle3 = new Bundle();
        bundle3.putBoolean("is_browser_custom_tabs_capable", com_google_android_gms_internal_zziv.zzcgz);
        bundle2.putBundle("browser", bundle3);
        if (bundle != null) {
            bundle2.putBundle("android_mem_info", zzf(bundle));
        }
        hashMap.put("device", bundle2);
    }

    private static void zza(HashMap<String, Object> hashMap, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("doritos", str);
        hashMap.put("pii", bundle);
    }

    private static Integer zzab(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    private static String zzau(int i) {
        return String.format(Locale.US, "#%06x", new Object[]{Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK & i)});
    }

    private static String zzc(NativeAdOptionsParcel nativeAdOptionsParcel) {
        switch (nativeAdOptionsParcel != null ? nativeAdOptionsParcel.zzbgq : 0) {
            case 1:
                return DeviceInfo.ORIENTATION_PORTRAIT;
            case 2:
                return DeviceInfo.ORIENTATION_LANDSCAPE;
            default:
                return "any";
        }
    }

    public static JSONObject zzc(AdResponseParcel adResponseParcel) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (adResponseParcel.zzbto != null) {
            jSONObject.put("ad_base_url", adResponseParcel.zzbto);
        }
        if (adResponseParcel.zzccb != null) {
            jSONObject.put("ad_size", adResponseParcel.zzccb);
        }
        jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, adResponseParcel.zzauu);
        if (adResponseParcel.zzauu) {
            jSONObject.put("ad_json", adResponseParcel.body);
        } else {
            jSONObject.put("ad_html", adResponseParcel.body);
        }
        if (adResponseParcel.zzccd != null) {
            jSONObject.put("debug_dialog", adResponseParcel.zzccd);
        }
        if (adResponseParcel.zzcbx != -1) {
            jSONObject.put("interstitial_timeout", ((double) adResponseParcel.zzcbx) / 1000.0d);
        }
        if (adResponseParcel.orientation == zzu.zzfs().zztk()) {
            jSONObject.put("orientation", DeviceInfo.ORIENTATION_PORTRAIT);
        } else if (adResponseParcel.orientation == zzu.zzfs().zztj()) {
            jSONObject.put("orientation", DeviceInfo.ORIENTATION_LANDSCAPE);
        }
        if (adResponseParcel.zzbnm != null) {
            jSONObject.put("click_urls", zzk(adResponseParcel.zzbnm));
        }
        if (adResponseParcel.zzbnn != null) {
            jSONObject.put("impression_urls", zzk(adResponseParcel.zzbnn));
        }
        if (adResponseParcel.zzcca != null) {
            jSONObject.put("manual_impression_urls", zzk(adResponseParcel.zzcca));
        }
        if (adResponseParcel.zzccg != null) {
            jSONObject.put("active_view", adResponseParcel.zzccg);
        }
        jSONObject.put("ad_is_javascript", adResponseParcel.zzcce);
        if (adResponseParcel.zzccf != null) {
            jSONObject.put("ad_passback_url", adResponseParcel.zzccf);
        }
        jSONObject.put("mediation", adResponseParcel.zzcby);
        jSONObject.put("custom_render_allowed", adResponseParcel.zzcch);
        jSONObject.put("content_url_opted_out", adResponseParcel.zzcci);
        jSONObject.put("prefetch", adResponseParcel.zzccj);
        if (adResponseParcel.zzbns != -1) {
            jSONObject.put("refresh_interval_milliseconds", adResponseParcel.zzbns);
        }
        if (adResponseParcel.zzcbz != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", adResponseParcel.zzcbz);
        }
        if (!TextUtils.isEmpty(adResponseParcel.zzccm)) {
            jSONObject.put("gws_query_id", adResponseParcel.zzccm);
        }
        jSONObject.put("fluid", adResponseParcel.zzauv ? "height" : "");
        jSONObject.put("native_express", adResponseParcel.zzauw);
        if (adResponseParcel.zzcco != null) {
            jSONObject.put("video_start_urls", zzk(adResponseParcel.zzcco));
        }
        if (adResponseParcel.zzccp != null) {
            jSONObject.put("video_complete_urls", zzk(adResponseParcel.zzccp));
        }
        if (adResponseParcel.zzccn != null) {
            jSONObject.put("rewards", adResponseParcel.zzccn.zzrw());
        }
        jSONObject.put("use_displayed_impression", adResponseParcel.zzccq);
        jSONObject.put("auto_protection_configuration", adResponseParcel.zzccr);
        jSONObject.put("render_in_browser", adResponseParcel.zzbnq);
        return jSONObject;
    }

    private static Bundle zzf(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("runtime_free", Long.toString(bundle.getLong("runtime_free_memory", -1)));
        bundle2.putString("runtime_max", Long.toString(bundle.getLong("runtime_max_memory", -1)));
        bundle2.putString("runtime_total", Long.toString(bundle.getLong("runtime_total_memory", -1)));
        MemoryInfo memoryInfo = (MemoryInfo) bundle.getParcelable("debug_memory_info");
        if (memoryInfo != null) {
            bundle2.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
            bundle2.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
            bundle2.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
            bundle2.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
            bundle2.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
            bundle2.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
            bundle2.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
            bundle2.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
            bundle2.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
        }
        return bundle2;
    }

    @Nullable
    static JSONArray zzk(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }
}
