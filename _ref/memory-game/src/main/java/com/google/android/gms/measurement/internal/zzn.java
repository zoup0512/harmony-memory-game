package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzqf;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

public class zzn extends zzaa {
    private static final X500Principal ajm = new X500Principal("CN=Android Debug,O=Android,C=US");
    private String ahJ;
    private String ahQ;
    private int ajn;
    private long ajo;
    private String zzcjf;
    private String zzcum;
    private String zzcun;

    zzn(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    String zzbps() {
        zzzg();
        return this.ahJ;
    }

    String zzbpy() {
        zzzg();
        return this.ahQ;
    }

    long zzbpz() {
        return zzbsf().zzbpz();
    }

    long zzbqa() {
        zzzg();
        return this.ajo;
    }

    public /* bridge */ /* synthetic */ void zzbrs() {
        super.zzbrs();
    }

    public /* bridge */ /* synthetic */ zzc zzbrt() {
        return super.zzbrt();
    }

    public /* bridge */ /* synthetic */ zzac zzbru() {
        return super.zzbru();
    }

    public /* bridge */ /* synthetic */ zzn zzbrv() {
        return super.zzbrv();
    }

    public /* bridge */ /* synthetic */ zzg zzbrw() {
        return super.zzbrw();
    }

    public /* bridge */ /* synthetic */ zzad zzbrx() {
        return super.zzbrx();
    }

    public /* bridge */ /* synthetic */ zze zzbry() {
        return super.zzbry();
    }

    public /* bridge */ /* synthetic */ zzal zzbrz() {
        return super.zzbrz();
    }

    public /* bridge */ /* synthetic */ zzv zzbsa() {
        return super.zzbsa();
    }

    public /* bridge */ /* synthetic */ zzaf zzbsb() {
        return super.zzbsb();
    }

    public /* bridge */ /* synthetic */ zzw zzbsc() {
        return super.zzbsc();
    }

    public /* bridge */ /* synthetic */ zzp zzbsd() {
        return super.zzbsd();
    }

    public /* bridge */ /* synthetic */ zzt zzbse() {
        return super.zzbse();
    }

    public /* bridge */ /* synthetic */ zzd zzbsf() {
        return super.zzbsf();
    }

    int zzbst() {
        zzzg();
        return this.ajn;
    }

    boolean zzbsu() {
        try {
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(ajm);
            }
        } catch (CertificateException e) {
            zzbsd().zzbsv().zzj("Error obtaining certificate", e);
        } catch (NameNotFoundException e2) {
            zzbsd().zzbsv().zzj("Package name not found", e2);
        }
        return true;
    }

    protected void zzdq(Status status) {
        if (status == null) {
            zzbsd().zzbsv().log("GoogleService failed to initialize (no status)");
        } else {
            zzbsd().zzbsv().zze("GoogleService failed to initialize, status", Integer.valueOf(status.getStatusCode()), status.getStatusMessage());
        }
    }

    AppMetadata zzlv(String str) {
        return new AppMetadata(zzsh(), zzbps(), zzxc(), (long) zzbst(), zzbpy(), zzbpz(), zzbqa(), str, this.ahD.isEnabled(), !zzbse().akm, zzbse().zzbpu());
    }

    String zzsh() {
        zzzg();
        return this.zzcjf;
    }

    public /* bridge */ /* synthetic */ void zzwu() {
        super.zzwu();
    }

    protected void zzwv() {
        String str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        int i = Integer.MIN_VALUE;
        String str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        PackageManager packageManager = getContext().getPackageManager();
        String packageName = getContext().getPackageName();
        String installerPackageName = packageManager.getInstallerPackageName(packageName);
        if (installerPackageName == null) {
            installerPackageName = "manual_install";
        } else if ("com.android.vending".equals(installerPackageName)) {
            installerPackageName = "";
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                if (!TextUtils.isEmpty(applicationLabel)) {
                    str2 = applicationLabel.toString();
                }
                str = packageInfo.versionName;
                i = packageInfo.versionCode;
            }
        } catch (NameNotFoundException e) {
            zzbsd().zzbsv().zzj("Error retrieving package info: appName", str2);
        }
        this.zzcjf = packageName;
        this.ahQ = installerPackageName;
        this.zzcun = str;
        this.ajn = i;
        this.zzcum = str2;
        MessageDigest zzfa = zzal.zzfa(CommonUtils.MD5_INSTANCE);
        if (zzfa == null) {
            zzbsd().zzbsv().log("Could not get MD5 instance");
            this.ajo = -1;
        } else {
            this.ajo = 0;
            try {
                if (!zzbsu()) {
                    PackageInfo packageInfo2 = packageManager.getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo2.signatures != null && packageInfo2.signatures.length > 0) {
                        this.ajo = zzal.zzx(zzfa.digest(packageInfo2.signatures[0].toByteArray()));
                    }
                }
            } catch (NameNotFoundException e2) {
                zzbsd().zzbsv().zzj("Package name not found", e2);
            }
        }
        Status zzc = zzbsf().zzabc() ? zzqf.zzc(getContext(), "-", true) : zzqf.zzcb(getContext());
        boolean z = zzc != null && zzc.isSuccess();
        if (!z) {
            zzdq(zzc);
        }
        if (z) {
            Boolean zzbre = zzbsf().zzbre();
            if (zzbsf().zzbrd()) {
                zzbsd().zzbta().log("Collection disabled with firebase_analytics_collection_deactivated=1");
                z = false;
            } else if (zzbre != null && !zzbre.booleanValue()) {
                zzbsd().zzbta().log("Collection disabled with firebase_analytics_collection_enabled=0");
                z = false;
            } else if (zzbre == null && zzbsf().zzaqp()) {
                zzbsd().zzbta().log("Collection disabled with google_app_measurement_enable=0");
                z = false;
            } else {
                zzbsd().zzbtc().log("Collection enabled");
                z = true;
            }
        } else {
            z = false;
        }
        this.ahJ = "";
        if (!zzbsf().zzabc()) {
            try {
                String zzaqo = zzqf.zzaqo();
                if (TextUtils.isEmpty(zzaqo)) {
                    zzaqo = "";
                }
                this.ahJ = zzaqo;
                if (z) {
                    zzbsd().zzbtc().zze("App package, google app id", this.zzcjf, this.ahJ);
                }
            } catch (IllegalStateException e3) {
                zzbsd().zzbsv().zzj("getGoogleAppId or isMeasurementEnabled failed with exception", e3);
            }
        }
    }

    String zzxc() {
        zzzg();
        return this.zzcun;
    }

    public /* bridge */ /* synthetic */ void zzyv() {
        super.zzyv();
    }

    public /* bridge */ /* synthetic */ zze zzyw() {
        return super.zzyw();
    }
}
