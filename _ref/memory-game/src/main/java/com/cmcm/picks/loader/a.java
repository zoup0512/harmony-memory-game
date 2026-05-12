package com.cmcm.picks.loader;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.cmcm.utils.g;
import com.mopub.common.AdType;
import com.my.target.nativeads.banners.NavigationType;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AdUtil */
public class a {
    public static int a = 50;

    public static Ad a(String str, JSONObject jSONObject) {
        boolean z = true;
        Ad ad = new Ad();
        if (jSONObject == null) {
            return ad;
        }
        ad.setTitle(jSONObject.optString("title", ""));
        ad.setDesc(jSONObject.optString("desc", ""));
        ad.setPicUrl(jSONObject.optString("pic_url", ""));
        ad.setPkg(jSONObject.optString("pkg", ""));
        ad.setPkgUrl(jSONObject.optString("pkg_url", ""));
        ad.setDes(jSONObject.optString("des", ""));
        ad.setDownloadNum(jSONObject.optString("download_num", ""));
        ad.setRating(jSONObject.optDouble("rating", 0.0d));
        ad.setPkgSize(jSONObject.optString("pkg_size", ""));
        ad.setResType(jSONObject.optInt("res_type", 0));
        ad.setMtType(jSONObject.optInt("mt_type", 0));
        ad.setAppShowType(jSONObject.optInt("app_show_type", 0));
        ad.setBackground(jSONObject.optString("background", ""));
        ad.setButtonTxt(jSONObject.optString("button_txt", ""));
        ad.setHtml(jSONObject.optString(AdType.HTML, ""));
        ad.setExtension(jSONObject.optString("extension", ""));
        ad.setDeepLink(jSONObject.optString(NavigationType.DEEPLINK, ""));
        ad.setPriority(jSONObject.optInt("priority", 0));
        ad.setClickTrackingUrl(jSONObject.optString("click_tracking_url", ""));
        ad.setThirdImpUrl(jSONObject.optString("third_imp_url", ""));
        ad.setCreateTime(jSONObject.optLong("create_time", System.currentTimeMillis()));
        ad.setPosid(str);
        if (jSONObject.optInt("is_show", 0) != 1) {
            z = false;
        }
        ad.setShowed(z);
        ad.setExtPicks(jSONObject.optString("ext_pics", ""));
        if (!jSONObject.isNull("mpa")) {
            try {
                a(jSONObject.getJSONArray("mpa").toString(), ad);
            } catch (JSONException e) {
                if (g.a) {
                    e.printStackTrace();
                }
            }
        }
        return ad;
    }

    private static void a(String str, Ad ad) {
        ad.setMpa(str);
        b(str, ad);
    }

    private static void b(String str, Ad ad) {
        List arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                arrayList.add(new Mpa(jSONObject.getString("mpa_id"), jSONObject.getString("ac"), jSONObject.getString("title"), jSONObject.getString("pic_url"), jSONObject.getString("pkg_url")));
            }
        } catch (JSONException e) {
        }
        MpaModule mpaModule = new MpaModule();
        mpaModule.setMpa(arrayList);
        ad.setMpaModule(mpaModule);
    }

    public static ContentValues a(Ad ad) {
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put("title", a(ad.getTitle()));
            contentValues.put("pic_url", a(ad.getPicUrl()));
            contentValues.put("pkg", a(ad.getPkg()));
            contentValues.put("pkg_url", a(ad.getPkgUrl()));
            contentValues.put("desc", a(ad.getDesc()));
            contentValues.put("des", a(ad.getDes()));
            contentValues.put("download_num", a(ad.getDownloadNum()));
            contentValues.put("rating", Double.valueOf(ad.getRating()));
            contentValues.put("pkg_size", a(ad.getpkg_size()));
            contentValues.put("res_type", Integer.valueOf(ad.getResType()));
            contentValues.put("mt_type", Integer.valueOf(ad.getMtType()));
            contentValues.put("app_show_type", Integer.valueOf(ad.getAppShowType()));
            contentValues.put("background", ad.getBackground());
            contentValues.put("button_txt", ad.getButtonTxt());
            contentValues.put(AdType.HTML, ad.getHtml());
            contentValues.put("extension", ad.getExtension());
            contentValues.put(NavigationType.DEEPLINK, ad.getDeepLink());
            contentValues.put("priority", Integer.valueOf(ad.getPriority()));
            contentValues.put("click_tracking_url", ad.getClickTrackingUrl());
            contentValues.put("third_imp_url", ad.getThirdImpUrl());
            contentValues.put("create_time", Long.valueOf(ad.getCreateTime()));
            contentValues.put("posid", ad.getPosid());
            contentValues.put("is_show", Integer.valueOf(ad.isShowed() ? 1 : 0));
            contentValues.put("ext_pics", ad.getExtPick());
            contentValues.put("mpa", ad.getMpa());
        } catch (Exception e) {
        }
        return contentValues;
    }

    public static ContentValues a(Ad ad, String str) {
        ad.setPosid(str);
        return a(ad);
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    public static Ad a(Cursor cursor) {
        boolean z = true;
        if (cursor == null) {
            return null;
        }
        Ad ad = new Ad();
        ad.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        ad.setPicUrl(cursor.getString(cursor.getColumnIndex("pic_url")));
        ad.setPkg(cursor.getString(cursor.getColumnIndex("pkg")));
        ad.setPkgUrl(cursor.getString(cursor.getColumnIndex("pkg_url")));
        ad.setDes(cursor.getString(cursor.getColumnIndex("des")));
        ad.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
        ad.setDownloadNum(cursor.getString(cursor.getColumnIndex("download_num")));
        ad.setRating(cursor.getDouble(cursor.getColumnIndex("rating")));
        ad.setPkgSize(cursor.getString(cursor.getColumnIndex("pkg_size")));
        ad.setResType(cursor.getInt(cursor.getColumnIndex("res_type")));
        ad.setMtType(cursor.getInt(cursor.getColumnIndex("mt_type")));
        ad.setAppShowType(cursor.getInt(cursor.getColumnIndex("app_show_type")));
        ad.setBackground(cursor.getString(cursor.getColumnIndex("background")));
        ad.setButtonTxt(cursor.getString(cursor.getColumnIndex("button_txt")));
        ad.setHtml(cursor.getString(cursor.getColumnIndex(AdType.HTML)));
        ad.setExtension(cursor.getString(cursor.getColumnIndex("extension")));
        ad.setDeepLink(cursor.getString(cursor.getColumnIndex(NavigationType.DEEPLINK)));
        ad.setPriority(cursor.getInt(cursor.getColumnIndex("priority")));
        ad.setClickTrackingUrl(cursor.getString(cursor.getColumnIndex("click_tracking_url")));
        ad.setThirdImpUrl(cursor.getString(cursor.getColumnIndex("third_imp_url")));
        ad.setCreateTime(cursor.getLong(cursor.getColumnIndex("create_time")));
        ad.setPosid(cursor.getString(cursor.getColumnIndex("posid")));
        if (cursor.getInt(cursor.getColumnIndex("is_show")) != 1) {
            z = false;
        }
        ad.setShowed(z);
        ad.setExtPicks(cursor.getString(cursor.getColumnIndex("ext_pics")));
        ad.setMpa(cursor.getString(cursor.getColumnIndex("mpa")));
        return ad;
    }

    public static void a(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + str + "(" + TransferTable.COLUMN_ID + " INTEGER PRIMARY KEY" + "," + "title" + " TEXT" + "," + "desc" + " TEXT" + "," + "pic_url" + " TEXT" + "," + "pkg" + " TEXT" + "," + "pkg_url" + " TEXT" + "," + "des" + " TEXT" + "," + "download_num" + " TEXT" + "," + "rating" + " DOUBLE" + "," + "pkg_size" + " TEXT" + "," + "res_type" + " INTEGER" + "," + "app_show_type" + " INTEGER" + "," + "mt_type" + " INTEGER" + "," + "background" + " TEXT" + "," + "button_txt" + " TEXT" + "," + "font" + " INTEGER" + "," + AdType.HTML + " TEXT" + "," + "extension" + " TEXT" + "," + NavigationType.DEEPLINK + " TEXT" + "," + "priority" + " INTEGER" + "," + "click_tracking_url" + " TEXT" + "," + "third_imp_url" + " TEXT" + "," + "create_time" + " INTEGER" + "," + "posid" + " TEXT" + "," + "is_show" + " INTEGER" + "," + "mpa" + " TEXT" + "," + "ext_pics" + " TEXT" + ");");
    }

    public static Ad a(String str, int i) {
        Ad ad = new Ad();
        ad.setPkg(str);
        ad.setResType(i);
        return ad;
    }
}
