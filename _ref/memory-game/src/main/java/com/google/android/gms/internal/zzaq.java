package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class zzaq extends zzao {
    private static final String TAG = zzaq.class.getSimpleName();
    private static long startTime = 0;
    protected static volatile zzax zzaey = null;
    private static Method zzafo;
    static boolean zzafq = false;
    protected static final Object zzaft = new Object();
    protected boolean zzafn = false;
    protected String zzafp;
    protected boolean zzafr = false;
    protected boolean zzafs = false;

    protected zzaq(Context context, String str) {
        super(context);
        this.zzafp = str;
        this.zzafn = false;
    }

    protected zzaq(Context context, String str, boolean z) {
        super(context);
        this.zzafp = str;
        this.zzafn = z;
    }

    static List<Long> zza(zzax com_google_android_gms_internal_zzax, MotionEvent motionEvent, DisplayMetrics displayMetrics) throws zzaw {
        zzafo = com_google_android_gms_internal_zzax.zzc(zzav.zzcb(), zzav.zzcc());
        if (zzafo == null || motionEvent == null) {
            throw new zzaw();
        }
        try {
            return (ArrayList) zzafo.invoke(null, new Object[]{motionEvent, displayMetrics});
        } catch (Throwable e) {
            throw new zzaw(e);
        } catch (Throwable e2) {
            throw new zzaw(e2);
        }
    }

    protected static synchronized void zza(Context context, boolean z) {
        synchronized (zzaq.class) {
            if (!zzafq) {
                startTime = Calendar.getInstance().getTime().getTime() / 1000;
                zzaey = zzb(context, z);
                zzafq = true;
            }
        }
    }

    private static void zza(zzax com_google_android_gms_internal_zzax) {
        List singletonList = Collections.singletonList(Context.class);
        com_google_android_gms_internal_zzax.zza(zzav.zzbn(), zzav.zzbo(), singletonList);
        com_google_android_gms_internal_zzax.zza(zzav.zzbl(), zzav.zzbm(), singletonList);
        com_google_android_gms_internal_zzax.zza(zzav.zzbx(), zzav.zzby(), singletonList);
        com_google_android_gms_internal_zzax.zza(zzav.zzbv(), zzav.zzbw(), singletonList);
        com_google_android_gms_internal_zzax.zza(zzav.zzbf(), zzav.zzbg(), singletonList);
        com_google_android_gms_internal_zzax.zza(zzav.zzbd(), zzav.zzbe(), singletonList);
        com_google_android_gms_internal_zzax.zza(zzav.zzbb(), zzav.zzbc(), singletonList);
        com_google_android_gms_internal_zzax.zza(zzav.zzbr(), zzav.zzbs(), singletonList);
        com_google_android_gms_internal_zzax.zza(zzav.zzaz(), zzav.zzba(), singletonList);
        com_google_android_gms_internal_zzax.zza(zzav.zzcb(), zzav.zzcc(), Arrays.asList(new Class[]{MotionEvent.class, DisplayMetrics.class}));
        com_google_android_gms_internal_zzax.zza(zzav.zzbj(), zzav.zzbk(), Collections.emptyList());
        com_google_android_gms_internal_zzax.zza(zzav.zzbz(), zzav.zzca(), Collections.emptyList());
        com_google_android_gms_internal_zzax.zza(zzav.zzbt(), zzav.zzbu(), Collections.emptyList());
        com_google_android_gms_internal_zzax.zza(zzav.zzbh(), zzav.zzbi(), Collections.emptyList());
        com_google_android_gms_internal_zzax.zza(zzav.zzbp(), zzav.zzbq(), Collections.emptyList());
    }

    protected static zzax zzb(Context context, boolean z) {
        if (zzaey == null) {
            synchronized (zzaft) {
                if (zzaey == null) {
                    zzax zza = zzax.zza(context, zzav.getKey(), zzav.zzay(), z);
                    zza(zza);
                    zzaey = zza;
                }
            }
        }
        return zzaey;
    }

    protected void zza(zzax com_google_android_gms_internal_zzax, zza com_google_android_gms_internal_zzae_zza) {
        if (com_google_android_gms_internal_zzax.zzcd() != null) {
            zza(zzb(com_google_android_gms_internal_zzax, com_google_android_gms_internal_zzae_zza));
        }
    }

    protected void zza(List<Callable<Void>> list) {
        if (zzaey != null) {
            ExecutorService zzcd = zzaey.zzcd();
            if (zzcd != null && !list.isEmpty()) {
                try {
                    zzcd.invokeAll(list, ((Long) zzdc.zzbbj.get()).longValue(), TimeUnit.MILLISECONDS);
                } catch (Throwable e) {
                    Log.d(TAG, String.format("class methods got exception: %s", new Object[]{zzay.zza(e)}));
                }
            }
        }
    }

    protected List<Callable<Void>> zzb(zzax com_google_android_gms_internal_zzax, zza com_google_android_gms_internal_zzae_zza) {
        int zzat = com_google_android_gms_internal_zzax.zzat();
        List arrayList = new ArrayList();
        List list = arrayList;
        list.add(new zzbb(com_google_android_gms_internal_zzax, zzav.zzbn(), zzav.zzbo(), com_google_android_gms_internal_zzae_zza, zzat, 27));
        list = arrayList;
        list.add(new zzbg(com_google_android_gms_internal_zzax, zzav.zzbj(), zzav.zzbk(), com_google_android_gms_internal_zzae_zza, startTime, zzat, 25));
        list = arrayList;
        list.add(new zzbl(com_google_android_gms_internal_zzax, zzav.zzbt(), zzav.zzbu(), com_google_android_gms_internal_zzae_zza, zzat, 1));
        list = arrayList;
        list.add(new zzbm(com_google_android_gms_internal_zzax, zzav.zzbv(), zzav.zzbw(), com_google_android_gms_internal_zzae_zza, zzat, 31));
        list = arrayList;
        list.add(new zzbn(com_google_android_gms_internal_zzax, zzav.zzbz(), zzav.zzca(), com_google_android_gms_internal_zzae_zza, zzat, 33));
        list = arrayList;
        list.add(new zzba(com_google_android_gms_internal_zzax, zzav.zzbx(), zzav.zzby(), com_google_android_gms_internal_zzae_zza, zzat, 29));
        list = arrayList;
        list.add(new zzbe(com_google_android_gms_internal_zzax, zzav.zzbf(), zzav.zzbg(), com_google_android_gms_internal_zzae_zza, zzat, 5));
        list = arrayList;
        list.add(new zzbk(com_google_android_gms_internal_zzax, zzav.zzbr(), zzav.zzbs(), com_google_android_gms_internal_zzae_zza, zzat, 12));
        list = arrayList;
        list.add(new zzaz(com_google_android_gms_internal_zzax, zzav.zzaz(), zzav.zzba(), com_google_android_gms_internal_zzae_zza, zzat, 3));
        list = arrayList;
        list.add(new zzbd(com_google_android_gms_internal_zzax, zzav.zzbd(), zzav.zzbe(), com_google_android_gms_internal_zzae_zza, zzat, 34));
        list = arrayList;
        list.add(new zzbc(com_google_android_gms_internal_zzax, zzav.zzbb(), zzav.zzbc(), com_google_android_gms_internal_zzae_zza, zzat, 35));
        if (((Boolean) zzdc.zzbbn.get()).booleanValue()) {
            list = arrayList;
            list.add(new zzbf(com_google_android_gms_internal_zzax, zzav.zzbh(), zzav.zzbi(), com_google_android_gms_internal_zzae_zza, zzat, 44));
        }
        if (((Boolean) zzdc.zzbbq.get()).booleanValue()) {
            list = arrayList;
            list.add(new zzbj(com_google_android_gms_internal_zzax, zzav.zzbp(), zzav.zzbq(), com_google_android_gms_internal_zzae_zza, zzat, 22));
        }
        return arrayList;
    }

    protected zza zzc(Context context) {
        zza com_google_android_gms_internal_zzae_zza = new zza();
        if (!TextUtils.isEmpty(this.zzafp)) {
            com_google_android_gms_internal_zzae_zza.zzcs = this.zzafp;
        }
        zzax zzb = zzb(context, this.zzafn);
        zzb.zzcs();
        zza(zzb, com_google_android_gms_internal_zzae_zza);
        zzb.zzct();
        return com_google_android_gms_internal_zzae_zza;
    }

    protected List<Callable<Void>> zzc(zzax com_google_android_gms_internal_zzax, zza com_google_android_gms_internal_zzae_zza) {
        ArrayList arrayList = new ArrayList();
        if (com_google_android_gms_internal_zzax.zzcd() == null) {
            return arrayList;
        }
        int zzat = com_google_android_gms_internal_zzax.zzat();
        arrayList.add(new zzbi(com_google_android_gms_internal_zzax, com_google_android_gms_internal_zzae_zza));
        ArrayList arrayList2 = arrayList;
        arrayList2.add(new zzbl(com_google_android_gms_internal_zzax, zzav.zzbt(), zzav.zzbu(), com_google_android_gms_internal_zzae_zza, zzat, 1));
        arrayList2 = arrayList;
        arrayList2.add(new zzbg(com_google_android_gms_internal_zzax, zzav.zzbj(), zzav.zzbk(), com_google_android_gms_internal_zzae_zza, startTime, zzat, 25));
        if (((Boolean) zzdc.zzbbo.get()).booleanValue()) {
            arrayList2 = arrayList;
            arrayList2.add(new zzbf(com_google_android_gms_internal_zzax, zzav.zzbh(), zzav.zzbi(), com_google_android_gms_internal_zzae_zza, zzat, 44));
        }
        arrayList2 = arrayList;
        arrayList2.add(new zzaz(com_google_android_gms_internal_zzax, zzav.zzaz(), zzav.zzba(), com_google_android_gms_internal_zzae_zza, zzat, 3));
        if (((Boolean) zzdc.zzbbr.get()).booleanValue()) {
            arrayList2 = arrayList;
            arrayList2.add(new zzbj(com_google_android_gms_internal_zzax, zzav.zzbp(), zzav.zzbq(), com_google_android_gms_internal_zzae_zza, zzat, 22));
        }
        return arrayList;
    }

    protected zza zzd(Context context) {
        zza com_google_android_gms_internal_zzae_zza = new zza();
        if (!TextUtils.isEmpty(this.zzafp)) {
            com_google_android_gms_internal_zzae_zza.zzcs = this.zzafp;
        }
        zzax zzb = zzb(context, this.zzafn);
        zzb.zzcs();
        zzd(zzb, com_google_android_gms_internal_zzae_zza);
        zzb.zzct();
        return com_google_android_gms_internal_zzae_zza;
    }

    protected void zzd(zzax com_google_android_gms_internal_zzax, zza com_google_android_gms_internal_zzae_zza) {
        try {
            List zza = zza(com_google_android_gms_internal_zzax, this.zzafd, this.zzafl);
            com_google_android_gms_internal_zzae_zza.zzdf = (Long) zza.get(0);
            com_google_android_gms_internal_zzae_zza.zzdg = (Long) zza.get(1);
            if (((Long) zza.get(2)).longValue() >= 0) {
                com_google_android_gms_internal_zzae_zza.zzdh = (Long) zza.get(2);
            }
            com_google_android_gms_internal_zzae_zza.zzdv = (Long) zza.get(3);
            com_google_android_gms_internal_zzae_zza.zzdw = (Long) zza.get(4);
        } catch (zzaw e) {
        }
        if (this.zzaff > 0) {
            com_google_android_gms_internal_zzae_zza.zzea = Long.valueOf(this.zzaff);
        }
        if (this.zzafg > 0) {
            com_google_android_gms_internal_zzae_zza.zzdz = Long.valueOf(this.zzafg);
        }
        if (this.zzafh > 0) {
            com_google_android_gms_internal_zzae_zza.zzdy = Long.valueOf(this.zzafh);
        }
        if (this.zzafi > 0) {
            com_google_android_gms_internal_zzae_zza.zzeb = Long.valueOf(this.zzafi);
        }
        if (this.zzafj > 0) {
            com_google_android_gms_internal_zzae_zza.zzed = Long.valueOf(this.zzafj);
        }
        try {
            int size = this.zzafe.size() - 1;
            if (size > 0) {
                com_google_android_gms_internal_zzae_zza.zzee = new zza.zza[size];
                for (int i = 0; i < size; i++) {
                    List zza2 = zza(com_google_android_gms_internal_zzax, (MotionEvent) this.zzafe.get(i), this.zzafl);
                    zza.zza com_google_android_gms_internal_zzae_zza_zza = new zza.zza();
                    com_google_android_gms_internal_zzae_zza_zza.zzdf = (Long) zza2.get(0);
                    com_google_android_gms_internal_zzae_zza_zza.zzdg = (Long) zza2.get(1);
                    com_google_android_gms_internal_zzae_zza.zzee[i] = com_google_android_gms_internal_zzae_zza_zza;
                }
            }
        } catch (zzaw e2) {
            com_google_android_gms_internal_zzae_zza.zzee = null;
        }
        zza(zzc(com_google_android_gms_internal_zzax, com_google_android_gms_internal_zzae_zza));
    }
}
