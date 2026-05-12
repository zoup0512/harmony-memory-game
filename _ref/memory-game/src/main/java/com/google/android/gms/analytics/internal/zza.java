package com.google.android.gms.analytics.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Locale;

public class zza extends zzd {
    public static boolean zzcwb;
    private Info zzcwc;
    private final zzal zzcwd;
    private String zzcwe;
    private boolean zzcwf = false;
    private Object zzcwg = new Object();

    zza(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
        this.zzcwd = new zzal(com_google_android_gms_analytics_internal_zzf.zzyw());
    }

    private boolean zza(Info info, Info info2) {
        Object obj = null;
        CharSequence id = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id)) {
            return true;
        }
        String zzaav = zzzc().zzaav();
        synchronized (this.zzcwg) {
            String valueOf;
            String valueOf2;
            if (!this.zzcwf) {
                this.zzcwe = zzyn();
                this.zzcwf = true;
            } else if (TextUtils.isEmpty(this.zzcwe)) {
                if (info != null) {
                    obj = info.getId();
                }
                if (obj == null) {
                    valueOf = String.valueOf(id);
                    String valueOf3 = String.valueOf(zzaav);
                    boolean zzeg = zzeg(valueOf3.length() != 0 ? valueOf.concat(valueOf3) : new String(valueOf));
                    return zzeg;
                }
                valueOf2 = String.valueOf(obj);
                valueOf = String.valueOf(zzaav);
                this.zzcwe = zzef(valueOf.length() != 0 ? valueOf2.concat(valueOf) : new String(valueOf2));
            }
            valueOf2 = String.valueOf(id);
            valueOf = String.valueOf(zzaav);
            obj = zzef(valueOf.length() != 0 ? valueOf2.concat(valueOf) : new String(valueOf2));
            if (TextUtils.isEmpty(obj)) {
                return false;
            } else if (obj.equals(this.zzcwe)) {
                return true;
            } else {
                if (TextUtils.isEmpty(this.zzcwe)) {
                    valueOf = zzaav;
                } else {
                    zzeh("Resetting the client id because Advertising Id changed.");
                    obj = zzzc().zzaaw();
                    zza("New client Id", obj);
                }
                String valueOf4 = String.valueOf(id);
                valueOf3 = String.valueOf(obj);
                zzeg = zzeg(valueOf3.length() != 0 ? valueOf4.concat(valueOf3) : new String(valueOf4));
                return zzeg;
            }
        }
    }

    private static String zzef(String str) {
        if (zzao.zzfa(CommonUtils.MD5_INSTANCE) == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzao.zzfa(CommonUtils.MD5_INSTANCE).digest(str.getBytes()))});
    }

    private boolean zzeg(String str) {
        try {
            String zzef = zzef(str);
            zzeh("Storing hashed adid.");
            FileOutputStream openFileOutput = getContext().openFileOutput("gaClientIdData", 0);
            openFileOutput.write(zzef.getBytes());
            openFileOutput.close();
            this.zzcwe = zzef;
            return true;
        } catch (IOException e) {
            zze("Error creating hash file", e);
            return false;
        }
    }

    private synchronized Info zzyl() {
        if (this.zzcwd.zzx(1000)) {
            this.zzcwd.start();
            Info zzym = zzym();
            if (zza(this.zzcwc, zzym)) {
                this.zzcwc = zzym;
            } else {
                zzel("Failed to reset client id on adid change. Not using adid");
                this.zzcwc = new Info("", false);
            }
        }
        return this.zzcwc;
    }

    protected void zzwv() {
    }

    public boolean zzxz() {
        zzzg();
        Info zzyl = zzyl();
        return (zzyl == null || zzyl.isLimitAdTrackingEnabled()) ? false : true;
    }

    public String zzyk() {
        zzzg();
        Info zzyl = zzyl();
        CharSequence id = zzyl != null ? zzyl.getId() : null;
        return TextUtils.isEmpty(id) ? null : id;
    }

    protected Info zzym() {
        Info info = null;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
        } catch (IllegalStateException e) {
            zzek("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
        } catch (Throwable th) {
            if (!zzcwb) {
                zzcwb = true;
                zzd("Error getting advertiser id", th);
            }
        }
        return info;
    }

    protected String zzyn() {
        Object obj;
        String str = null;
        try {
            FileInputStream openFileInput = getContext().openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int read = openFileInput.read(bArr, 0, 128);
            if (openFileInput.available() > 0) {
                zzek("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                getContext().deleteFile("gaClientIdData");
                return null;
            } else if (read <= 0) {
                zzeh("Hash file is empty.");
                openFileInput.close();
                return null;
            } else {
                String str2 = new String(bArr, 0, read);
                try {
                    openFileInput.close();
                    return str2;
                } catch (FileNotFoundException e) {
                    return str2;
                } catch (IOException e2) {
                    IOException iOException = e2;
                    str = str2;
                    IOException iOException2 = iOException;
                    zzd("Error reading Hash file, deleting it", obj);
                    getContext().deleteFile("gaClientIdData");
                    return str;
                }
            }
        } catch (FileNotFoundException e3) {
            return null;
        } catch (IOException e4) {
            obj = e4;
            zzd("Error reading Hash file, deleting it", obj);
            getContext().deleteFile("gaClientIdData");
            return str;
        }
    }
}
