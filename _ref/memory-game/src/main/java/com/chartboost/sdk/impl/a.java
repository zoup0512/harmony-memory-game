package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static a b = new a(new Handler(Looper.getMainLooper()));
    public final Handler a;

    public a(Handler handler) {
        this.a = handler;
    }

    public static a a() {
        return b;
    }

    public File b() {
        return Environment.getExternalStorageDirectory();
    }

    public String c() {
        return Environment.getExternalStorageState();
    }

    public boolean a(int i) {
        return VERSION.SDK_INT >= i;
    }

    public Info a(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        return AdvertisingIdClient.getAdvertisingIdInfo(context);
    }

    public JSONObject d() {
        return new JSONObject();
    }

    public JSONArray e() {
        return new JSONArray();
    }

    public JSONObject a(String str) throws JSONException {
        return new JSONObject(str);
    }

    public JSONArray b(String str) throws JSONException {
        return new JSONArray(str);
    }

    public JSONArray a(Collection collection) throws JSONException {
        return new JSONArray(collection);
    }

    public boolean f() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public boolean a(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence);
    }

    public Activity a(Activity activity) {
        return activity;
    }

    public void b(int i) {
        Process.setThreadPriority(i);
    }

    public Bitmap a(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, null);
    }

    public Context a(View view) {
        return view.getContext();
    }
}
