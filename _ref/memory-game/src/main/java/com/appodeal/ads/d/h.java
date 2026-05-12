package com.appodeal.ads.d;

import android.content.Context;
import android.os.AsyncTask;
import com.appodeal.ads.Appodeal;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h implements Serializable {
    private JSONObject a;
    private JSONArray b = new JSONArray();
    private JSONObject c;
    private String d;
    private String e;
    private String f;
    private boolean g = false;
    private boolean h = false;
    private JSONObject i;
    private List<c> j = new ArrayList();
    private String k;

    private class a extends AsyncTask<JSONObject, Void, Void> {
        final /* synthetic */ h a;

        private a(h hVar) {
            this.a = hVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((JSONObject[]) objArr);
        }

        protected Void a(JSONObject... jSONObjectArr) {
            HttpURLConnection httpURLConnection;
            Throwable th;
            HttpURLConnection httpURLConnection2 = null;
            try {
                JSONObject jSONObject = jSONObjectArr[0];
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL("http://adwatch.appodeal.com/api/v1/rtb/submit").openConnection();
                try {
                    httpURLConnection3.setConnectTimeout(20000);
                    httpURLConnection3.setReadTimeout(20000);
                    httpURLConnection3.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    httpURLConnection3.setDoOutput(true);
                    httpURLConnection3.setRequestMethod(HttpRequest.METHOD_POST);
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection3.getOutputStream());
                    dataOutputStream.write(jSONObject.toString().getBytes(Charset.forName("UTF-8")));
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    switch (httpURLConnection3.getResponseCode()) {
                        case 200:
                            Appodeal.a("Stats send");
                            break;
                    }
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                } catch (Throwable e) {
                    Throwable th2 = e;
                    httpURLConnection = httpURLConnection3;
                    th = th2;
                    try {
                        Appodeal.a(th);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        httpURLConnection2 = httpURLConnection;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable e2) {
                    httpURLConnection2 = httpURLConnection3;
                    th = e2;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                th = e3;
                httpURLConnection = null;
                Appodeal.a(th);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
            return null;
        }
    }

    public void a(a aVar) {
        if (aVar != null && aVar.i() != null) {
            this.b.put(aVar.i().c());
        }
    }

    public JSONObject a() {
        try {
            if (this.b.length() > 0) {
                this.a.put("answered_bidders", this.b);
            }
            this.a.put("auction_inited", this.h);
        } catch (JSONException e) {
        }
        return this.a;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("rtb_check", this.c);
            jSONObject.put("rtb_answered_bidders", this.b);
            jSONObject.put("rtb_stat_requests", this.a);
            jSONObject.put("bidder_name", this.e);
            jSONObject.put("bidder_id", this.f);
            jSONObject.put("waterfall_id", this.d);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(JSONObject jSONObject) {
        this.c = jSONObject;
    }

    public void b(JSONObject jSONObject) {
        this.a = jSONObject;
    }

    public JSONObject c() {
        JSONObject jSONObject = this.c == null ? new JSONObject() : this.c;
        if (jSONObject.has("adm")) {
            jSONObject.remove("adm");
        }
        if (jSONObject.has("mfr_id")) {
            jSONObject.remove("mfr_id");
        }
        if (jSONObject.has("bidder_id")) {
            jSONObject.remove("bidder_id");
        }
        return jSONObject;
    }

    public boolean d() {
        return this.c != null && this.c.has("bidder_id") && this.c.has("id") && this.c.has(Param.PRICE);
    }

    public String e() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public boolean f() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void g() {
        this.h = true;
    }

    public void c(JSONObject jSONObject) {
        try {
            this.i = new JSONObject(jSONObject.toString());
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public boolean h() {
        return this.i != null;
    }

    public void a(c cVar) {
        this.j.add(cVar);
    }

    public void d(String str) {
        this.k = str;
    }

    public void a(Context context) {
        try {
            if (this.j.size() > 0) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("encryptedSessionInfo", this.k);
                jSONObject.put("timestamp", System.currentTimeMillis());
                jSONObject.put("package", context.getPackageName());
                jSONObject.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, this.i);
                JSONArray jSONArray = new JSONArray();
                for (c cVar : this.j) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("encryptedBidderInfo", cVar.f().c());
                    jSONObject2.put("bidderName", cVar.f().d());
                    jSONObject2.put("response", cVar.e());
                    jSONObject2.put("winner", cVar.g());
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("responses", jSONArray);
                new a().execute(new JSONObject[]{jSONObject});
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}
