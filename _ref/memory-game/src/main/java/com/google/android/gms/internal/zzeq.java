package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.GraphResponse;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzeq implements zzep {
    private final Context mContext;
    private final VersionInfoParcel zzalo;

    @zzin
    static class zza {
        private final String mValue;
        private final String zzaxp;

        public zza(String str, String str2) {
            this.zzaxp = str;
            this.mValue = str2;
        }

        public String getKey() {
            return this.zzaxp;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @zzin
    static class zzb {
        private final String zzbii;
        private final URL zzbij;
        private final ArrayList<zza> zzbik;
        private final String zzbil;

        public zzb(String str, URL url, ArrayList<zza> arrayList, String str2) {
            this.zzbii = str;
            this.zzbij = url;
            if (arrayList == null) {
                this.zzbik = new ArrayList();
            } else {
                this.zzbik = arrayList;
            }
            this.zzbil = str2;
        }

        public String zzle() {
            return this.zzbii;
        }

        public URL zzlf() {
            return this.zzbij;
        }

        public ArrayList<zza> zzlg() {
            return this.zzbik;
        }

        public String zzlh() {
            return this.zzbil;
        }
    }

    @zzin
    class zzc {
        final /* synthetic */ zzeq zzbif;
        private final zzd zzbim;
        private final boolean zzbin;
        private final String zzbio;

        public zzc(zzeq com_google_android_gms_internal_zzeq, boolean z, zzd com_google_android_gms_internal_zzeq_zzd, String str) {
            this.zzbif = com_google_android_gms_internal_zzeq;
            this.zzbin = z;
            this.zzbim = com_google_android_gms_internal_zzeq_zzd;
            this.zzbio = str;
        }

        public String getReason() {
            return this.zzbio;
        }

        public boolean isSuccess() {
            return this.zzbin;
        }

        public zzd zzli() {
            return this.zzbim;
        }
    }

    @zzin
    static class zzd {
        private final String zzbfi;
        private final String zzbii;
        private final int zzbip;
        private final List<zza> zzbiq;

        public zzd(String str, int i, List<zza> list, String str2) {
            this.zzbii = str;
            this.zzbip = i;
            if (list == null) {
                this.zzbiq = new ArrayList();
            } else {
                this.zzbiq = list;
            }
            this.zzbfi = str2;
        }

        public String getBody() {
            return this.zzbfi;
        }

        public int getResponseCode() {
            return this.zzbip;
        }

        public String zzle() {
            return this.zzbii;
        }

        public Iterable<zza> zzlj() {
            return this.zzbiq;
        }
    }

    public zzeq(Context context, VersionInfoParcel versionInfoParcel) {
        this.mContext = context;
        this.zzalo = versionInfoParcel;
    }

    protected zzc zza(zzb com_google_android_gms_internal_zzeq_zzb) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) com_google_android_gms_internal_zzeq_zzb.zzlf().openConnection();
            zzu.zzfq().zza(this.mContext, this.zzalo.zzcs, false, httpURLConnection);
            Iterator it = com_google_android_gms_internal_zzeq_zzb.zzlg().iterator();
            while (it.hasNext()) {
                zza com_google_android_gms_internal_zzeq_zza = (zza) it.next();
                httpURLConnection.addRequestProperty(com_google_android_gms_internal_zzeq_zza.getKey(), com_google_android_gms_internal_zzeq_zza.getValue());
            }
            if (!TextUtils.isEmpty(com_google_android_gms_internal_zzeq_zzb.zzlh())) {
                httpURLConnection.setDoOutput(true);
                byte[] bytes = com_google_android_gms_internal_zzeq_zzb.zzlh().getBytes();
                httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
            }
            List arrayList = new ArrayList();
            if (httpURLConnection.getHeaderFields() != null) {
                for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
                    for (String com_google_android_gms_internal_zzeq_zza2 : (List) entry.getValue()) {
                        arrayList.add(new zza((String) entry.getKey(), com_google_android_gms_internal_zzeq_zza2));
                    }
                }
            }
            return new zzc(this, true, new zzd(com_google_android_gms_internal_zzeq_zzb.zzle(), httpURLConnection.getResponseCode(), arrayList, zzu.zzfq().zza(new InputStreamReader(httpURLConnection.getInputStream()))), null);
        } catch (Exception e) {
            return new zzc(this, false, null, e.toString());
        }
    }

    protected JSONObject zza(zzd com_google_android_gms_internal_zzeq_zzd) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", com_google_android_gms_internal_zzeq_zzd.zzle());
            if (com_google_android_gms_internal_zzeq_zzd.getBody() != null) {
                jSONObject.put("body", com_google_android_gms_internal_zzeq_zzd.getBody());
            }
            JSONArray jSONArray = new JSONArray();
            for (zza com_google_android_gms_internal_zzeq_zza : com_google_android_gms_internal_zzeq_zzd.zzlj()) {
                jSONArray.put(new JSONObject().put(TransferTable.COLUMN_KEY, com_google_android_gms_internal_zzeq_zza.getKey()).put(Param.VALUE, com_google_android_gms_internal_zzeq_zza.getValue()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", com_google_android_gms_internal_zzeq_zzd.getResponseCode());
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        zzkg.zza(new 1(this, map, com_google_android_gms_internal_zzlh));
    }

    public JSONObject zzav(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            Object obj = "";
            try {
                obj = jSONObject.optString("http_request_id");
                zzc zza = zza(zzc(jSONObject));
                if (zza.isSuccess()) {
                    jSONObject2.put("response", zza(zza.zzli()));
                    jSONObject2.put(GraphResponse.SUCCESS_KEY, true);
                    return jSONObject2;
                }
                jSONObject2.put("response", new JSONObject().put("http_request_id", obj));
                jSONObject2.put(GraphResponse.SUCCESS_KEY, false);
                jSONObject2.put("reason", zza.getReason());
                return jSONObject2;
            } catch (Exception e) {
                try {
                    jSONObject2.put("response", new JSONObject().put("http_request_id", obj));
                    jSONObject2.put(GraphResponse.SUCCESS_KEY, false);
                    jSONObject2.put("reason", e.toString());
                    return jSONObject2;
                } catch (JSONException e2) {
                    return jSONObject2;
                }
            }
        } catch (JSONException e3) {
            com.google.android.gms.ads.internal.util.client.zzb.e("The request is not a valid JSON.");
            try {
                return new JSONObject().put(GraphResponse.SUCCESS_KEY, false);
            } catch (JSONException e4) {
                return new JSONObject();
            }
        }
    }

    protected zzb zzc(JSONObject jSONObject) {
        URL url;
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString("url");
        String optString3 = jSONObject.optString("post_body", null);
        try {
            url = new URL(optString2);
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Error constructing http request.", e);
            url = null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new zza(optJSONObject.optString(TransferTable.COLUMN_KEY), optJSONObject.optString(Param.VALUE)));
            }
        }
        return new zzb(optString, url, arrayList, optString3);
    }
}
