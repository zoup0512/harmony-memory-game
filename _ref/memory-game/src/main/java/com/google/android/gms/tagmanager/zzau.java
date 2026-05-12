package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class zzau extends Thread implements zzat {
    private static zzau awa;
    private final LinkedBlockingQueue<Runnable> avZ = new LinkedBlockingQueue();
    private volatile zzav awb;
    private volatile boolean mClosed = false;
    private final Context mContext;
    private volatile boolean zzbte = false;

    private zzau(Context context) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    private String zze(Throwable th) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    static zzau zzec(Context context) {
        if (awa == null) {
            awa = new zzau(context);
        }
        return awa;
    }

    public void run() {
        while (!this.mClosed) {
            try {
                Runnable runnable = (Runnable) this.avZ.take();
                if (!this.zzbte) {
                    runnable.run();
                }
            } catch (InterruptedException e) {
                zzbn.zzcw(e.toString());
            } catch (Throwable th) {
                String str = "Error on Google TagManager Thread: ";
                String valueOf = String.valueOf(zze(th));
                zzbn.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                zzbn.e("Google TagManager is shutting down.");
                this.zzbte = true;
            }
        }
    }

    void zzm(String str, long j) {
        final zzau com_google_android_gms_tagmanager_zzau = this;
        final long j2 = j;
        final String str2 = str;
        zzp(new Runnable(this) {
            final /* synthetic */ zzau awe;

            public void run() {
                if (this.awe.awb == null) {
                    zzdb zzcdc = zzdb.zzcdc();
                    zzcdc.zza(this.awe.mContext, com_google_android_gms_tagmanager_zzau);
                    this.awe.awb = zzcdc.zzcdf();
                }
                this.awe.awb.zzg(j2, str2);
            }
        });
    }

    public void zzov(String str) {
        zzm(str, System.currentTimeMillis());
    }

    public void zzp(Runnable runnable) {
        this.avZ.add(runnable);
    }
}
