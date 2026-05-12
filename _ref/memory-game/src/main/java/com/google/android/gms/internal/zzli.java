package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.TransportMediator;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzu;
import com.mopub.common.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@zzin
public class zzli extends WebViewClient {
    private static final String[] zzcoj = new String[]{"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzcok = new String[]{"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private final Object zzail;
    private boolean zzark;
    private zza zzatk;
    protected zzlh zzbgf;
    private zzel zzbhm;
    private zzet zzbir;
    private zze zzbit;
    private zzha zzbiu;
    private zzer zzbiw;
    private zzhg zzbqn;
    private zza zzbya;
    private final HashMap<String, List<zzep>> zzcol;
    private zzg zzcom;
    private zzb zzcon;
    private boolean zzcoo;
    private boolean zzcop;
    private zzp zzcoq;
    private final zzhe zzcor;
    private zzd zzcos;
    @Nullable
    protected zzjo zzcot;
    private boolean zzcou;
    private boolean zzcov;
    private boolean zzcow;
    private int zzcox;

    public zzli(zzlh com_google_android_gms_internal_zzlh, boolean z) {
        this(com_google_android_gms_internal_zzlh, z, new zzhe(com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzlh.zzuf(), new zzcu(com_google_android_gms_internal_zzlh.getContext())), null);
    }

    zzli(zzlh com_google_android_gms_internal_zzlh, boolean z, zzhe com_google_android_gms_internal_zzhe, zzha com_google_android_gms_internal_zzha) {
        this.zzcol = new HashMap();
        this.zzail = new Object();
        this.zzcoo = false;
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzark = z;
        this.zzcor = com_google_android_gms_internal_zzhe;
        this.zzbiu = com_google_android_gms_internal_zzha;
    }

    private void zza(Context context, String str, String str2, String str3) {
        if (((Boolean) zzdc.zzbav.get()).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString("err", str);
            bundle.putString("code", str2);
            bundle.putString("host", zzda(str3));
            zzu.zzfq().zza(context, this.zzbgf.zzum().zzcs, "gmob-apps", bundle, true);
        }
    }

    private String zzda(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Uri parse = Uri.parse(str);
        return parse.getHost() != null ? parse.getHost() : "";
    }

    private static boolean zzh(Uri uri) {
        String scheme = uri.getScheme();
        return Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme);
    }

    private void zzvb() {
        synchronized (this.zzail) {
            this.zzcop = true;
        }
        this.zzcox++;
        zzve();
    }

    private void zzvc() {
        this.zzcox--;
        zzve();
    }

    private void zzvd() {
        this.zzcow = true;
        zzve();
    }

    public final void onLoadResource(WebView webView, String str) {
        String str2 = "Loading resource: ";
        String valueOf = String.valueOf(str);
        zzkd.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        synchronized (this.zzail) {
            if (this.zzcou) {
                zzkd.v("Blank page loaded, 1...");
                this.zzbgf.zzuo();
                return;
            }
            this.zzcov = true;
            zzve();
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        String valueOf = (i >= 0 || (-i) - 1 >= zzcoj.length) ? String.valueOf(i) : zzcoj[(-i) - 1];
        zza(this.zzbgf.getContext(), "http_err", valueOf, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            String valueOf = (primaryError < 0 || primaryError >= zzcok.length) ? String.valueOf(primaryError) : zzcok[primaryError];
            zza(this.zzbgf.getContext(), "ssl_err", valueOf, zzu.zzfs().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void reset() {
        if (this.zzcot != null) {
            this.zzcot.zzrx();
            this.zzcot = null;
        }
        synchronized (this.zzail) {
            this.zzcol.clear();
            this.zzatk = null;
            this.zzcom = null;
            this.zzbya = null;
            this.zzbhm = null;
            this.zzcoo = false;
            this.zzark = false;
            this.zzcop = false;
            this.zzbiw = null;
            this.zzcoq = null;
            this.zzcon = null;
            if (this.zzbiu != null) {
                this.zzbiu.zzs(true);
                this.zzbiu = null;
            }
        }
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case TransportMediator.KEYCODE_MEDIA_PLAY /*126*/:
            case TransportMediator.KEYCODE_MEDIA_PAUSE /*127*/:
            case 128:
            case 129:
            case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
            case 222:
                return true;
            default:
                return false;
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "AdWebView shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(str);
        zzkd.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        } else if (this.zzcoo && webView == this.zzbgf.getWebView() && zzh(parse)) {
            if (this.zzatk != null && ((Boolean) zzdc.zzazu.get()).booleanValue()) {
                this.zzatk.onAdClicked();
                if (this.zzcot != null) {
                    this.zzcot.zzci(str);
                }
                this.zzatk = null;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        } else if (this.zzbgf.getWebView().willNotDraw()) {
            str2 = "AdWebView unable to handle URL: ";
            valueOf = String.valueOf(str);
            zzb.zzcx(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            Uri uri;
            try {
                zzas zzul = this.zzbgf.zzul();
                if (zzul != null && zzul.zzc(parse)) {
                    parse = zzul.zzb(parse, this.zzbgf.getContext());
                }
                uri = parse;
            } catch (zzat e) {
                String str3 = "Unable to append parameter to URL: ";
                str2 = String.valueOf(str);
                zzb.zzcx(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                uri = parse;
            }
            if (this.zzbit == null || this.zzbit.zzel()) {
                zza(new AdLauncherIntentInfoParcel("android.intent.action.VIEW", uri.toString(), null, null, null, null, null));
            } else {
                this.zzbit.zzt(str);
            }
        }
        return true;
    }

    public void zza(int i, int i2, boolean z) {
        this.zzcor.zze(i, i2);
        if (this.zzbiu != null) {
            this.zzbiu.zza(i, i2, z);
        }
    }

    public void zza(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzel com_google_android_gms_internal_zzel, zzp com_google_android_gms_ads_internal_overlay_zzp, boolean z, zzer com_google_android_gms_internal_zzer, @Nullable zzet com_google_android_gms_internal_zzet, zze com_google_android_gms_ads_internal_zze, zzhg com_google_android_gms_internal_zzhg, @Nullable zzjo com_google_android_gms_internal_zzjo) {
        if (com_google_android_gms_ads_internal_zze == null) {
            com_google_android_gms_ads_internal_zze = new zze(this.zzbgf.getContext());
        }
        this.zzbiu = new zzha(this.zzbgf, com_google_android_gms_internal_zzhg);
        this.zzcot = com_google_android_gms_internal_zzjo;
        zza("/appEvent", new zzek(com_google_android_gms_internal_zzel));
        zza("/backButton", zzeo.zzbhx);
        zza("/refresh", zzeo.zzbhy);
        zza("/canOpenURLs", zzeo.zzbho);
        zza("/canOpenIntents", zzeo.zzbhp);
        zza("/click", zzeo.zzbhq);
        zza("/close", zzeo.zzbhr);
        zza("/customClose", zzeo.zzbht);
        zza("/instrument", zzeo.zzbic);
        zza("/delayPageLoaded", new zze(this, null));
        zza("/httpTrack", zzeo.zzbhu);
        zza("/log", zzeo.zzbhv);
        zza("/mraid", new zzev(com_google_android_gms_ads_internal_zze, this.zzbiu));
        zza("/mraidLoaded", this.zzcor);
        zza("/open", new zzew(com_google_android_gms_internal_zzer, com_google_android_gms_ads_internal_zze, this.zzbiu));
        zza("/precache", zzeo.zzbib);
        zza("/touch", zzeo.zzbhw);
        zza("/video", zzeo.zzbhz);
        zza("/videoMeta", zzeo.zzbia);
        zza("/appStreaming", zzeo.zzbhs);
        if (com_google_android_gms_internal_zzet != null) {
            zza("/setInterstitialProperties", new zzes(com_google_android_gms_internal_zzet));
        }
        this.zzatk = com_google_android_gms_ads_internal_client_zza;
        this.zzcom = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbhm = com_google_android_gms_internal_zzel;
        this.zzbiw = com_google_android_gms_internal_zzer;
        this.zzcoq = com_google_android_gms_ads_internal_overlay_zzp;
        this.zzbit = com_google_android_gms_ads_internal_zze;
        this.zzbqn = com_google_android_gms_internal_zzhg;
        this.zzbir = com_google_android_gms_internal_zzet;
        zzak(z);
    }

    public final void zza(AdLauncherIntentInfoParcel adLauncherIntentInfoParcel) {
        zzg com_google_android_gms_ads_internal_overlay_zzg = null;
        boolean zzun = this.zzbgf.zzun();
        zza com_google_android_gms_ads_internal_client_zza = (!zzun || this.zzbgf.zzdn().zzaus) ? this.zzatk : null;
        if (!zzun) {
            com_google_android_gms_ads_internal_overlay_zzg = this.zzcom;
        }
        zza(new AdOverlayInfoParcel(adLauncherIntentInfoParcel, com_google_android_gms_ads_internal_client_zza, com_google_android_gms_ads_internal_overlay_zzg, this.zzcoq, this.zzbgf.zzum()));
    }

    public void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean zzmw = this.zzbiu != null ? this.zzbiu.zzmw() : false;
        com.google.android.gms.ads.internal.overlay.zze zzfo = zzu.zzfo();
        Context context = this.zzbgf.getContext();
        if (!zzmw) {
            z = true;
        }
        zzfo.zza(context, adOverlayInfoParcel, z);
        if (this.zzcot != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzbtj != null) {
                str = adOverlayInfoParcel.zzbtj.url;
            }
            this.zzcot.zzci(str);
        }
    }

    public void zza(zza com_google_android_gms_internal_zzli_zza) {
        this.zzbya = com_google_android_gms_internal_zzli_zza;
    }

    public void zza(zzb com_google_android_gms_internal_zzli_zzb) {
        this.zzcon = com_google_android_gms_internal_zzli_zzb;
    }

    public void zza(zzd com_google_android_gms_internal_zzli_zzd) {
        this.zzcos = com_google_android_gms_internal_zzli_zzd;
    }

    public void zza(String str, zzep com_google_android_gms_internal_zzep) {
        synchronized (this.zzail) {
            List list = (List) this.zzcol.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzcol.put(str, list);
            }
            list.add(com_google_android_gms_internal_zzep);
        }
    }

    public final void zza(boolean z, int i) {
        zza com_google_android_gms_ads_internal_client_zza = (!this.zzbgf.zzun() || this.zzbgf.zzdn().zzaus) ? this.zzatk : null;
        zza(new AdOverlayInfoParcel(com_google_android_gms_ads_internal_client_zza, this.zzcom, this.zzcoq, this.zzbgf, z, i, this.zzbgf.zzum()));
    }

    public final void zza(boolean z, int i, String str) {
        zzg com_google_android_gms_ads_internal_overlay_zzg = null;
        boolean zzun = this.zzbgf.zzun();
        zza com_google_android_gms_ads_internal_client_zza = (!zzun || this.zzbgf.zzdn().zzaus) ? this.zzatk : null;
        if (!zzun) {
            com_google_android_gms_ads_internal_overlay_zzg = new zzc(this.zzbgf, this.zzcom);
        }
        zza(new AdOverlayInfoParcel(com_google_android_gms_ads_internal_client_zza, com_google_android_gms_ads_internal_overlay_zzg, this.zzbhm, this.zzcoq, this.zzbgf, z, i, str, this.zzbgf.zzum(), this.zzbiw));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zzun = this.zzbgf.zzun();
        zza com_google_android_gms_ads_internal_client_zza = (!zzun || this.zzbgf.zzdn().zzaus) ? this.zzatk : null;
        zza(new AdOverlayInfoParcel(com_google_android_gms_ads_internal_client_zza, zzun ? null : new zzc(this.zzbgf, this.zzcom), this.zzbhm, this.zzcoq, this.zzbgf, z, i, str, str2, this.zzbgf.zzum(), this.zzbiw));
    }

    public void zzak(boolean z) {
        this.zzcoo = z;
    }

    public void zzb(String str, zzep com_google_android_gms_internal_zzep) {
        synchronized (this.zzail) {
            List list = (List) this.zzcol.get(str);
            if (list == null) {
                return;
            }
            list.remove(com_google_android_gms_internal_zzep);
        }
    }

    public void zzd(int i, int i2) {
        if (this.zzbiu != null) {
            this.zzbiu.zzd(i, i2);
        }
    }

    public boolean zzho() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzark;
        }
        return z;
    }

    public void zzi(Uri uri) {
        String path = uri.getPath();
        List<zzep> list = (List) this.zzcol.get(path);
        if (list != null) {
            Map zzf = zzu.zzfq().zzf(uri);
            if (zzb.zzaz(2)) {
                String str = "Received GMSG: ";
                path = String.valueOf(path);
                zzkd.v(path.length() != 0 ? str.concat(path) : new String(str));
                for (String path2 : zzf.keySet()) {
                    str = (String) zzf.get(path2);
                    zzkd.v(new StringBuilder((String.valueOf(path2).length() + 4) + String.valueOf(str).length()).append("  ").append(path2).append(": ").append(str).toString());
                }
            }
            for (zzep zza : list) {
                zza.zza(this.zzbgf, zzf);
            }
            return;
        }
        String valueOf = String.valueOf(uri);
        zzkd.v(new StringBuilder(String.valueOf(valueOf).length() + 32).append("No GMSG handler found for GMSG: ").append(valueOf).toString());
    }

    public void zzl(zzlh com_google_android_gms_internal_zzlh) {
        this.zzbgf = com_google_android_gms_internal_zzlh;
    }

    public final void zznx() {
        synchronized (this.zzail) {
            this.zzcoo = false;
            this.zzark = true;
            zzu.zzfq().runOnUiThread(new 2(this));
        }
    }

    public zze zzux() {
        return this.zzbit;
    }

    public boolean zzuy() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcop;
        }
        return z;
    }

    public void zzuz() {
        synchronized (this.zzail) {
            zzkd.v("Loading blank page in WebView, 2...");
            this.zzcou = true;
            this.zzbgf.zzcy("about:blank");
        }
    }

    public void zzva() {
        if (this.zzcot != null) {
            zzkh.zzclc.post(new 1(this));
        }
    }

    public final void zzve() {
        if (this.zzbya != null && ((this.zzcov && this.zzcox <= 0) || this.zzcow)) {
            this.zzbya.zza(this.zzbgf, !this.zzcow);
            this.zzbya = null;
        }
        this.zzbgf.zzuv();
    }

    public zzd zzvf() {
        return this.zzcos;
    }
}
