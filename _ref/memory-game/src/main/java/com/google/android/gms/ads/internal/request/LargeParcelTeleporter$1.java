package com.google.android.gms.ads.internal.request;

import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.internal.zzkd;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class LargeParcelTeleporter$1 implements Runnable {
    final /* synthetic */ OutputStream zzcdc;
    final /* synthetic */ byte[] zzcdd;
    final /* synthetic */ LargeParcelTeleporter zzcde;

    LargeParcelTeleporter$1(LargeParcelTeleporter largeParcelTeleporter, OutputStream outputStream, byte[] bArr) {
        this.zzcde = largeParcelTeleporter;
        this.zzcdc = outputStream;
        this.zzcdd = bArr;
    }

    public void run() {
        Closeable dataOutputStream;
        Throwable e;
        try {
            dataOutputStream = new DataOutputStream(this.zzcdc);
            try {
                dataOutputStream.writeInt(this.zzcdd.length);
                dataOutputStream.write(this.zzcdd);
                zzo.zzb(dataOutputStream);
            } catch (IOException e2) {
                e = e2;
                try {
                    zzkd.zzb("Error transporting the ad response", e);
                    zzu.zzft().zzb(e, true);
                    if (dataOutputStream != null) {
                        zzo.zzb(this.zzcdc);
                    } else {
                        zzo.zzb(dataOutputStream);
                    }
                } catch (Throwable th) {
                    e = th;
                    if (dataOutputStream != null) {
                        zzo.zzb(dataOutputStream);
                    } else {
                        zzo.zzb(this.zzcdc);
                    }
                    throw e;
                }
            }
        } catch (IOException e3) {
            e = e3;
            dataOutputStream = null;
            zzkd.zzb("Error transporting the ad response", e);
            zzu.zzft().zzb(e, true);
            if (dataOutputStream != null) {
                zzo.zzb(dataOutputStream);
            } else {
                zzo.zzb(this.zzcdc);
            }
        } catch (Throwable th2) {
            e = th2;
            dataOutputStream = null;
            if (dataOutputStream != null) {
                zzo.zzb(this.zzcdc);
            } else {
                zzo.zzb(dataOutputStream);
            }
            throw e;
        }
    }
}
