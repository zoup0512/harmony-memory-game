package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.internal.zzgj.zza;
import java.util.Map;

@zzin
public final class zzgi extends zza {
    private Map<Class<? extends NetworkExtras>, NetworkExtras> zzbpg;

    private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> zzgk zzbo(String str) throws RemoteException {
        try {
            Class cls = Class.forName(str, false, zzgi.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(cls)) {
                MediationAdapter mediationAdapter = (MediationAdapter) cls.newInstance();
                return new zzgv(mediationAdapter, (com.google.ads.mediation.NetworkExtras) this.zzbpg.get(mediationAdapter.getAdditionalParametersType()));
            } else if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(cls)) {
                return new zzgq((com.google.android.gms.ads.mediation.MediationAdapter) cls.newInstance());
            } else {
                zzb.zzcx(new StringBuilder(String.valueOf(str).length() + 64).append("Could not instantiate mediation adapter: ").append(str).append(" (not a valid adapter).").toString());
                throw new RemoteException();
            }
        } catch (Throwable th) {
            return zzbp(str);
        }
    }

    private zzgk zzbp(String str) throws RemoteException {
        try {
            zzb.zzcv("Reflection failed, retrying using direct instantiation");
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                return new zzgq(new AdMobAdapter());
            }
            if ("com.google.ads.mediation.AdUrlAdapter".equals(str)) {
                return new zzgq(new AdUrlAdapter());
            }
            if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                return new zzgq(new CustomEventAdapter());
            }
            if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                MediationAdapter customEventAdapter = new com.google.ads.mediation.customevent.CustomEventAdapter();
                return new zzgv(customEventAdapter, (CustomEventExtras) this.zzbpg.get(customEventAdapter.getAdditionalParametersType()));
            }
            throw new RemoteException();
        } catch (Throwable th) {
            zzb.zzd(new StringBuilder(String.valueOf(str).length() + 43).append("Could not instantiate mediation adapter: ").append(str).append(". ").toString(), th);
        }
    }

    public zzgk zzbm(String str) throws RemoteException {
        return zzbo(str);
    }

    public boolean zzbn(String str) throws RemoteException {
        boolean z = false;
        try {
            z = CustomEvent.class.isAssignableFrom(Class.forName(str, false, zzgi.class.getClassLoader()));
        } catch (Throwable th) {
            zzb.zzcx(new StringBuilder(String.valueOf(str).length() + 80).append("Could not load custom event implementation class: ").append(str).append(", assuming old implementation.").toString());
        }
        return z;
    }

    public void zzh(Map<Class<? extends NetworkExtras>, NetworkExtras> map) {
        this.zzbpg = map;
    }
}
