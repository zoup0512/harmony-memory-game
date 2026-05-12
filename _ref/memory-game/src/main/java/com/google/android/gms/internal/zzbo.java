package com.google.android.gms.internal;

import com.google.android.gms.internal.zzau.zza;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzbo {
    protected static final String TAG = zzbo.class.getSimpleName();
    private final String className;
    private final zzax zzaey;
    private final String zzahf;
    private final int zzahg = 2;
    private volatile Method zzahh = null;
    private List<Class> zzahi;
    private CountDownLatch zzahj = new CountDownLatch(1);

    public zzbo(zzax com_google_android_gms_internal_zzax, String str, String str2, List<Class> list) {
        this.zzaey = com_google_android_gms_internal_zzax;
        this.className = str;
        this.zzahf = str2;
        this.zzahi = new ArrayList(list);
        this.zzaey.zzcd().submit(new Runnable(this) {
            final /* synthetic */ zzbo zzahk;

            {
                this.zzahk = r1;
            }

            public void run() {
                this.zzahk.zzcy();
            }
        });
    }

    private void zzcy() {
        try {
            Class loadClass = this.zzaey.zzce().loadClass(zzd(this.zzaey.zzcg(), this.className));
            if (loadClass != null) {
                this.zzahh = loadClass.getMethod(zzd(this.zzaey.zzcg(), this.zzahf), (Class[]) this.zzahi.toArray(new Class[this.zzahi.size()]));
                if (this.zzahh == null) {
                    this.zzahj.countDown();
                } else {
                    this.zzahj.countDown();
                }
            }
        } catch (zza e) {
        } catch (UnsupportedEncodingException e2) {
        } catch (ClassNotFoundException e3) {
        } catch (NoSuchMethodException e4) {
        } catch (NullPointerException e5) {
        } finally {
            this.zzahj.countDown();
        }
    }

    private String zzd(byte[] bArr, String str) throws zza, UnsupportedEncodingException {
        return new String(this.zzaey.zzcf().zzc(bArr, str), "UTF-8");
    }

    public Method zzcz() {
        if (this.zzahh != null) {
            return this.zzahh;
        }
        try {
            return this.zzahj.await(2, TimeUnit.SECONDS) ? this.zzahh : null;
        } catch (InterruptedException e) {
            return null;
        }
    }
}
