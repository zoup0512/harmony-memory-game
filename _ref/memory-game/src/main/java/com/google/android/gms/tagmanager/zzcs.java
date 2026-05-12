package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.zzadw;
import com.google.android.gms.internal.zzadz;
import com.google.android.gms.internal.zzaea;
import com.google.android.gms.internal.zzah.zzj;
import com.google.android.gms.tagmanager.zzbm.zza;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

class zzcs implements Runnable {
    private final String auF;
    private volatile String avc;
    private final zzaea axd;
    private final String axe;
    private zzbm<zzj> axf;
    private volatile zzs axg;
    private volatile String axh;
    private final Context mContext;

    zzcs(Context context, String str, zzaea com_google_android_gms_internal_zzaea, zzs com_google_android_gms_tagmanager_zzs) {
        this.mContext = context;
        this.axd = com_google_android_gms_internal_zzaea;
        this.auF = str;
        this.axg = com_google_android_gms_tagmanager_zzs;
        String valueOf = String.valueOf("/r?id=");
        String valueOf2 = String.valueOf(str);
        this.axe = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        this.avc = this.axe;
        this.axh = null;
    }

    public zzcs(Context context, String str, zzs com_google_android_gms_tagmanager_zzs) {
        this(context, str, new zzaea(), com_google_android_gms_tagmanager_zzs);
    }

    private boolean zzccl() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbn.v("...no network connectivity");
        return false;
    }

    private void zzccm() {
        String valueOf;
        if (zzccl()) {
            zzbn.v("Start loading resource from network ...");
            String zzccn = zzccn();
            zzadz zzchl = this.axd.zzchl();
            String valueOf2;
            try {
                InputStream zzqj = zzchl.zzqj(zzccn);
                try {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    zzadw.zzc(zzqj, byteArrayOutputStream);
                    zzj zzf = zzj.zzf(byteArrayOutputStream.toByteArray());
                    valueOf2 = String.valueOf(zzf);
                    zzbn.v(new StringBuilder(String.valueOf(valueOf2).length() + 43).append("Successfully loaded supplemented resource: ").append(valueOf2).toString());
                    if (zzf.zzwr == null && zzf.zzwq.length == 0) {
                        String str = "No change for container: ";
                        valueOf2 = String.valueOf(this.auF);
                        zzbn.v(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                    }
                    this.axf.onSuccess(zzf);
                    zzbn.v("Load resource from network finished.");
                } catch (Throwable e) {
                    valueOf = String.valueOf(e.getMessage());
                    zzbn.zzd(new StringBuilder((String.valueOf(zzccn).length() + 51) + String.valueOf(valueOf).length()).append("Error when parsing downloaded resources from url: ").append(zzccn).append(" ").append(valueOf).toString(), e);
                    this.axf.zza(zza.SERVER_ERROR);
                    zzchl.close();
                }
            } catch (FileNotFoundException e2) {
                valueOf2 = this.auF;
                zzbn.zzcx(new StringBuilder((String.valueOf(zzccn).length() + 79) + String.valueOf(valueOf2).length()).append("No data is retrieved from the given url: ").append(zzccn).append(". Make sure container_id: ").append(valueOf2).append(" is correct.").toString());
                this.axf.zza(zza.SERVER_ERROR);
            } catch (Throwable e3) {
                valueOf = String.valueOf(e3.getMessage());
                zzbn.zzd(new StringBuilder((String.valueOf(zzccn).length() + 40) + String.valueOf(valueOf).length()).append("Error when loading resources from url: ").append(zzccn).append(" ").append(valueOf).toString(), e3);
                this.axf.zza(zza.IO_ERROR);
            } finally {
                zzchl.close();
            }
        } else {
            this.axf.zza(zza.NOT_AVAILABLE);
        }
    }

    public void run() {
        if (this.axf == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.axf.zzcau();
        zzccm();
    }

    void zza(zzbm<zzj> com_google_android_gms_tagmanager_zzbm_com_google_android_gms_internal_zzah_zzj) {
        this.axf = com_google_android_gms_tagmanager_zzbm_com_google_android_gms_internal_zzah_zzj;
    }

    String zzccn() {
        String valueOf = String.valueOf(this.axg.zzcaw());
        String str = this.avc;
        String valueOf2 = String.valueOf("&v=a65833898");
        valueOf = new StringBuilder(((String.valueOf(valueOf).length() + 0) + String.valueOf(str).length()) + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(valueOf2).toString();
        if (!(this.axh == null || this.axh.trim().equals(""))) {
            valueOf = String.valueOf(valueOf);
            str = String.valueOf("&pv=");
            valueOf2 = this.axh;
            valueOf = new StringBuilder(((String.valueOf(valueOf).length() + 0) + String.valueOf(str).length()) + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(valueOf2).toString();
        }
        if (!zzci.zzcci().zzccj().equals(zza.CONTAINER_DEBUG)) {
            return valueOf;
        }
        str = String.valueOf(valueOf);
        valueOf = String.valueOf("&gtm_debug=x");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    void zzol(String str) {
        if (str == null) {
            this.avc = this.axe;
            return;
        }
        String str2 = "Setting CTFE URL path: ";
        String valueOf = String.valueOf(str);
        zzbn.zzcv(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.avc = str;
    }

    void zzpa(String str) {
        String str2 = "Setting previous container version: ";
        String valueOf = String.valueOf(str);
        zzbn.zzcv(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.axh = str;
    }
}
