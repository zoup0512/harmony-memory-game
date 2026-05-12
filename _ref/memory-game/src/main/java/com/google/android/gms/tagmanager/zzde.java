package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

class zzde implements zzac {
    private final zzb ayh;
    private final zza ayi;
    private final Context mContext;
    private final String zzbjf;

    public interface zza {
        void zza(zzar com_google_android_gms_tagmanager_zzar);

        void zzb(zzar com_google_android_gms_tagmanager_zzar);

        void zzc(zzar com_google_android_gms_tagmanager_zzar);
    }

    interface zzb {
        HttpURLConnection zzd(URL url) throws IOException;
    }

    zzde(Context context, zza com_google_android_gms_tagmanager_zzde_zza) {
        this(new zzb() {
            public HttpURLConnection zzd(URL url) throws IOException {
                return (HttpURLConnection) url.openConnection();
            }
        }, context, com_google_android_gms_tagmanager_zzde_zza);
    }

    zzde(zzb com_google_android_gms_tagmanager_zzde_zzb, Context context, zza com_google_android_gms_tagmanager_zzde_zza) {
        this.ayh = com_google_android_gms_tagmanager_zzde_zzb;
        this.mContext = context.getApplicationContext();
        this.ayi = com_google_android_gms_tagmanager_zzde_zza;
        this.zzbjf = zza("GoogleTagManager", "4.00", VERSION.RELEASE, zzc(Locale.getDefault()), Build.MODEL, Build.ID);
    }

    static String zzc(Locale locale) {
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locale.getLanguage().toLowerCase());
        if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
            stringBuilder.append("-").append(locale.getCountry().toLowerCase());
        }
        return stringBuilder.toString();
    }

    String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    public void zzai(List<zzar> list) {
        Throwable th;
        InputStream inputStream;
        Object obj;
        Throwable th2;
        Object obj2;
        IOException iOException;
        int min = Math.min(list.size(), 40);
        Object obj3 = 1;
        int i = 0;
        while (i < min) {
            Object obj4;
            zzar com_google_android_gms_tagmanager_zzar = (zzar) list.get(i);
            URL zzd = zzd(com_google_android_gms_tagmanager_zzar);
            if (zzd == null) {
                zzbn.zzcx("No destination: discarding hit.");
                this.ayi.zzb(com_google_android_gms_tagmanager_zzar);
                obj4 = obj3;
            } else {
                try {
                    HttpURLConnection zzd2 = this.ayh.zzd(zzd);
                    if (obj3 != null) {
                        try {
                            zzbs.zzee(this.mContext);
                            obj3 = null;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream = null;
                            obj = obj3;
                            th2 = th;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    obj2 = obj;
                                    iOException = e;
                                }
                            }
                            zzd2.disconnect();
                            throw th2;
                        }
                    }
                    zzd2.setRequestProperty("User-Agent", this.zzbjf);
                    int responseCode = zzd2.getResponseCode();
                    InputStream inputStream2 = zzd2.getInputStream();
                    if (responseCode != 200) {
                        try {
                            zzbn.zzcx("Bad response: " + responseCode);
                            this.ayi.zzc(com_google_android_gms_tagmanager_zzar);
                        } catch (Throwable th32) {
                            th = th32;
                            inputStream = inputStream2;
                            obj = obj3;
                            th2 = th;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            zzd2.disconnect();
                            throw th2;
                        }
                    }
                    this.ayi.zza(com_google_android_gms_tagmanager_zzar);
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    zzd2.disconnect();
                    obj4 = obj3;
                } catch (IOException e2) {
                    iOException = e2;
                    obj2 = obj3;
                    String str = "Exception sending hit: ";
                    String valueOf = String.valueOf(iOException.getClass().getSimpleName());
                    zzbn.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    zzbn.zzcx(iOException.getMessage());
                    this.ayi.zzc(com_google_android_gms_tagmanager_zzar);
                    obj4 = obj2;
                    i++;
                    obj3 = obj4;
                }
            }
            i++;
            obj3 = obj4;
        }
    }

    public boolean zzcbg() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbn.v("...no network connectivity");
        return false;
    }

    URL zzd(zzar com_google_android_gms_tagmanager_zzar) {
        try {
            return new URL(com_google_android_gms_tagmanager_zzar.zzcbt());
        } catch (MalformedURLException e) {
            zzbn.e("Error trying to parse the GTM url.");
            return null;
        }
    }
}
