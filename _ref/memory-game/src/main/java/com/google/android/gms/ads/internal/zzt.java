package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzu.zza;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzbw;
import com.google.android.gms.internal.zzbx;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.internal.zzho;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkg;
import java.util.Map;
import java.util.concurrent.Future;

@zzin
public class zzt extends zza {
    private final Context mContext;
    @Nullable
    private zzq zzalf;
    private final VersionInfoParcel zzalo;
    private final AdSizeParcel zzani;
    private final Future<zzbw> zzanj = zzfg();
    private final zzb zzank;
    @Nullable
    private WebView zzanl = new WebView(this.mContext);
    @Nullable
    private zzbw zzanm;
    private AsyncTask<Void, Void, Void> zzann;

    public zzt(Context context, AdSizeParcel adSizeParcel, String str, VersionInfoParcel versionInfoParcel) {
        this.mContext = context;
        this.zzalo = versionInfoParcel;
        this.zzani = adSizeParcel;
        this.zzank = new zzb(str);
        zzfd();
    }

    private void zzfd() {
        zzj(0);
        this.zzanl.setVerticalScrollBarEnabled(false);
        this.zzanl.getSettings().setJavaScriptEnabled(true);
        this.zzanl.setWebViewClient(new 1(this));
        this.zzanl.setOnTouchListener(new 2(this));
    }

    private Future<zzbw> zzfg() {
        return zzkg.zza(new 3(this));
    }

    private String zzx(String str) {
        if (this.zzanm == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzanm.zzd(parse, this.mContext);
        } catch (Throwable e) {
            zzb.zzd("Unable to process ad data", e);
        } catch (Throwable e2) {
            zzb.zzd("Unable to parse ad click url", e2);
        }
        return parse.toString();
    }

    private void zzy(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
    }

    public void destroy() throws RemoteException {
        zzab.zzhi("destroy must be called on the main UI thread.");
        this.zzann.cancel(true);
        this.zzanj.cancel(true);
        this.zzanl.destroy();
        this.zzanl = null;
    }

    @Nullable
    public String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public boolean isLoading() throws RemoteException {
        return false;
    }

    public boolean isReady() throws RemoteException {
        return false;
    }

    public void pause() throws RemoteException {
        zzab.zzhi("pause must be called on the main UI thread.");
    }

    public void resume() throws RemoteException {
        zzab.zzhi("resume must be called on the main UI thread.");
    }

    public void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    public void setUserId(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void stopLoading() throws RemoteException {
    }

    public void zza(AdSizeParcel adSizeParcel) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public void zza(VideoOptionsParcel videoOptionsParcel) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzp com_google_android_gms_ads_internal_client_zzp) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) throws RemoteException {
        this.zzalf = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzdo com_google_android_gms_internal_zzdo) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzho com_google_android_gms_internal_zzho) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzhs com_google_android_gms_internal_zzhs, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public boolean zzb(AdRequestParcel adRequestParcel) throws RemoteException {
        zzab.zzb(this.zzanl, "This Search Ad has already been torn down");
        this.zzank.zzh(adRequestParcel);
        this.zzann = new zza(this, null).execute(new Void[0]);
        return true;
    }

    public com.google.android.gms.dynamic.zzd zzdm() throws RemoteException {
        zzab.zzhi("getAdFrame must be called on the main UI thread.");
        return zze.zzac(this.zzanl);
    }

    public AdSizeParcel zzdn() throws RemoteException {
        return this.zzani;
    }

    public void zzdp() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Nullable
    public com.google.android.gms.ads.internal.client.zzab zzdq() {
        return null;
    }

    String zzfe() {
        String valueOf;
        Uri zzc;
        Throwable e;
        String valueOf2;
        Builder builder = new Builder();
        builder.scheme("https://").appendEncodedPath((String) zzdc.zzbdb.get());
        builder.appendQueryParameter("query", this.zzank.getQuery());
        builder.appendQueryParameter("pubId", this.zzank.zzfj());
        Map zzfk = this.zzank.zzfk();
        for (String valueOf3 : zzfk.keySet()) {
            builder.appendQueryParameter(valueOf3, (String) zzfk.get(valueOf3));
        }
        Uri build = builder.build();
        if (this.zzanm != null) {
            try {
                zzc = this.zzanm.zzc(build, this.mContext);
            } catch (zzbx e2) {
                e = e2;
                zzb.zzd("Unable to process ad data", e);
                zzc = build;
                valueOf2 = String.valueOf(zzff());
                valueOf3 = String.valueOf(zzc.getEncodedQuery());
                return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
            } catch (RemoteException e3) {
                e = e3;
                zzb.zzd("Unable to process ad data", e);
                zzc = build;
                valueOf2 = String.valueOf(zzff());
                valueOf3 = String.valueOf(zzc.getEncodedQuery());
                return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
            }
            valueOf2 = String.valueOf(zzff());
            valueOf3 = String.valueOf(zzc.getEncodedQuery());
            return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
        }
        zzc = build;
        valueOf2 = String.valueOf(zzff());
        valueOf3 = String.valueOf(zzc.getEncodedQuery());
        return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
    }

    String zzff() {
        String str;
        CharSequence zzfi = this.zzank.zzfi();
        if (TextUtils.isEmpty(zzfi)) {
            str = "www.google.com";
        } else {
            CharSequence charSequence = zzfi;
        }
        String valueOf = String.valueOf("https://");
        String str2 = (String) zzdc.zzbdb.get();
        return new StringBuilder(((String.valueOf(valueOf).length() + 0) + String.valueOf(str).length()) + String.valueOf(str2).length()).append(valueOf).append(str).append(str2).toString();
    }

    void zzj(int i) {
        if (this.zzanl != null) {
            this.zzanl.setLayoutParams(new LayoutParams(-1, i));
        }
    }

    int zzw(String str) {
        int i = 0;
        Object queryParameter = Uri.parse(str).getQueryParameter("height");
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                i = zzm.zziw().zza(this.mContext, Integer.parseInt(queryParameter));
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }
}
