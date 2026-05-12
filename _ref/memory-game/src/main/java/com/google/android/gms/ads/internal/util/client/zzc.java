package com.google.android.gms.ads.internal.util.client;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zza.zza;
import com.google.android.gms.internal.zzin;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zzin
public class zzc implements zza {
    @Nullable
    private final String zzbjf;

    public zzc() {
        this(null);
    }

    public zzc(@Nullable String str) {
        this.zzbjf = str;
    }

    @WorkerThread
    public void zzcr(String str) {
        HttpURLConnection httpURLConnection;
        String valueOf;
        try {
            String str2 = "Pinging URL: ";
            valueOf = String.valueOf(str);
            zzb.zzcv(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            zzm.zziw().zza(true, httpURLConnection, this.zzbjf);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                zzb.zzcx(new StringBuilder(String.valueOf(str).length() + 65).append("Received non-success response code ").append(responseCode).append(" from pinging URL: ").append(str).toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            valueOf = String.valueOf(e.getMessage());
            zzb.zzcx(new StringBuilder((String.valueOf(str).length() + 32) + String.valueOf(valueOf).length()).append("Error while parsing ping URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (IOException e2) {
            valueOf = String.valueOf(e2.getMessage());
            zzb.zzcx(new StringBuilder((String.valueOf(str).length() + 27) + String.valueOf(valueOf).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (RuntimeException e3) {
            valueOf = String.valueOf(e3.getMessage());
            zzb.zzcx(new StringBuilder((String.valueOf(str).length() + 27) + String.valueOf(valueOf).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (Throwable th) {
            httpURLConnection.disconnect();
        }
    }
}
