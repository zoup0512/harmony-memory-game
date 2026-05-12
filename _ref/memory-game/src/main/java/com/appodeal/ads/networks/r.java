package com.appodeal.ads.networks;

import android.app.Activity;
import android.location.Location;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.UserSettings;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.an;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.places.model.PlaceFields;
import com.mopub.common.GpsHelper;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class r extends AsyncTask<Void, Void, String> {
    private final a a;
    private final int b;
    private final int c;
    private final String d;

    public interface a {
        void a(int i, int i2);

        void a(String str, int i, int i2);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((String) obj);
    }

    public r(Activity activity, a aVar, int i, int i2, String str, int i3) {
        this.a = aVar;
        this.b = i;
        this.c = i2;
        String packageName = activity.getPackageName();
        Pair f = an.f(activity);
        Builder buildUpon = Uri.parse("https://api.pubnative.net/api/partner/v2/promotions/native/video").buildUpon();
        buildUpon.appendQueryParameter("app_token", str).appendQueryParameter("bundle_id", packageName).appendQueryParameter("icon_size", "80x80").appendQueryParameter("banner_size", "1200x627").appendQueryParameter("os", AbstractSpiCall.ANDROID_CLIENT_TYPE).appendQueryParameter("device_model", Build.MODEL).appendQueryParameter("os_version", VERSION.RELEASE).appendQueryParameter("locale", Locale.getDefault().getLanguage()).appendQueryParameter("device_resolution", String.valueOf(f.first) + "x" + String.valueOf(f.second)).appendQueryParameter("device_type", an.n(activity) ? "tablet" : PlaceFields.PHONE).appendQueryParameter("partner", "Appodeal").appendQueryParameter("ad_count", String.valueOf(i3));
        packageName = activity.getSharedPreferences("appodeal", 0).getString(GpsHelper.ADVERTISING_ID_KEY, null);
        if (packageName == null) {
            packageName = an.l(activity);
        }
        buildUpon.appendQueryParameter("android_advertiser_id", packageName);
        Location e = an.e(activity);
        if (e != null) {
            buildUpon.appendQueryParameter("lat", String.valueOf(e.getLatitude()));
            buildUpon.appendQueryParameter("long", String.valueOf(e.getLongitude()));
        } else if (UserSettings.userData != null) {
            try {
                JSONObject optJSONObject = UserSettings.userData.optJSONObject("user_settings");
                if (optJSONObject != null) {
                    if (optJSONObject.has("lat")) {
                        buildUpon.appendQueryParameter("lat", optJSONObject.getString("lat"));
                    }
                    if (optJSONObject.has("lon")) {
                        buildUpon.appendQueryParameter("long", optJSONObject.getString("lon"));
                    }
                }
            } catch (JSONException e2) {
                Appodeal.a(e2.toString());
            }
        }
        UserSettings userSettings = Appodeal.getUserSettings(activity);
        Gender gender = userSettings.getGender();
        if (gender != null) {
            switch (gender.getValue()) {
                case 0:
                    packageName = FacebookRequestErrorClassification.KEY_OTHER;
                    break;
                case 1:
                    packageName = "female";
                    break;
                case 2:
                    packageName = "male";
                    break;
                default:
                    packageName = null;
                    break;
            }
            if (packageName != null) {
                buildUpon.appendQueryParameter("gender", packageName);
            }
        }
        Integer age = userSettings.getAge();
        if (age != null) {
            buildUpon.appendQueryParameter("age", String.valueOf(age));
        }
        packageName = userSettings.getInterests();
        if (packageName != null) {
            buildUpon.appendQueryParameter("keywords", packageName);
        }
        this.d = buildUpon.build().toString();
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ r a;

            {
                this.a = r1;
            }

            public void run() {
                if (VERSION.SDK_INT >= 11) {
                    this.a.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    this.a.execute(new Void[0]);
                }
            }
        });
    }

    protected String a(Void... voidArr) {
        HttpURLConnection httpURLConnection;
        Throwable th;
        HttpURLConnection httpURLConnection2 = null;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(this.d).openConnection();
            try {
                httpURLConnection3.setConnectTimeout(20000);
                httpURLConnection3.setReadTimeout(20000);
                httpURLConnection3.setRequestProperty("User-Agent", System.getProperty("http.agent"));
                String a = an.a(httpURLConnection3.getInputStream());
                if (a == null || a.isEmpty() || a.equals(" ")) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                return a;
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
    }

    protected void a(String str) {
        super.onPostExecute(str);
        try {
            if (this.a == null) {
                return;
            }
            if (str == null) {
                this.a.a(this.b, this.c);
            } else {
                this.a.a(str, this.b, this.c);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}
