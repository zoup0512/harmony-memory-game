package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.zzin;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@zzin
public final class zzad {
    public static final String DEVICE_ID_EMULATOR = zzm.zziw().zzcu("emulator");
    private final boolean zzakp;
    private final int zzaud;
    private final int zzaug;
    private final String zzauh;
    private final String zzauj;
    private final Bundle zzaul;
    private final String zzaun;
    private final boolean zzaup;
    private final Bundle zzavs;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzavt;
    private final SearchAdRequest zzavu;
    private final Set<String> zzavv;
    private final Set<String> zzavw;
    private final Date zzfp;
    private final Set<String> zzfr;
    private final Location zzft;

    public static final class zza {
        private boolean zzakp = false;
        private int zzaud = -1;
        private int zzaug = -1;
        private String zzauh;
        private String zzauj;
        private final Bundle zzaul = new Bundle();
        private String zzaun;
        private boolean zzaup;
        private final Bundle zzavs = new Bundle();
        private final HashSet<String> zzavx = new HashSet();
        private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzavy = new HashMap();
        private final HashSet<String> zzavz = new HashSet();
        private final HashSet<String> zzawa = new HashSet();
        private Date zzfp;
        private Location zzft;

        public void setManualImpressionsEnabled(boolean z) {
            this.zzakp = z;
        }

        @Deprecated
        public void zza(NetworkExtras networkExtras) {
            if (networkExtras instanceof AdMobExtras) {
                zza(AdMobAdapter.class, ((AdMobExtras) networkExtras).getExtras());
            } else {
                this.zzavy.put(networkExtras.getClass(), networkExtras);
            }
        }

        public void zza(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzavs.putBundle(cls.getName(), bundle);
        }

        public void zza(Date date) {
            this.zzfp = date;
        }

        public void zzaf(String str) {
            this.zzavx.add(str);
        }

        public void zzag(String str) {
            this.zzavz.add(str);
        }

        public void zzah(String str) {
            this.zzavz.remove(str);
        }

        public void zzai(String str) {
            this.zzauj = str;
        }

        public void zzaj(String str) {
            this.zzauh = str;
        }

        public void zzak(String str) {
            this.zzaun = str;
        }

        public void zzal(String str) {
            this.zzawa.add(str);
        }

        public void zzb(Location location) {
            this.zzft = location;
        }

        public void zzb(Class<? extends CustomEvent> cls, Bundle bundle) {
            if (this.zzavs.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
                this.zzavs.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
            }
            this.zzavs.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(cls.getName(), bundle);
        }

        public void zzf(String str, String str2) {
            this.zzaul.putString(str, str2);
        }

        public void zzn(boolean z) {
            this.zzaug = z ? 1 : 0;
        }

        public void zzo(boolean z) {
            this.zzaup = z;
        }

        public void zzt(int i) {
            this.zzaud = i;
        }
    }

    public zzad(zza com_google_android_gms_ads_internal_client_zzad_zza) {
        this(com_google_android_gms_ads_internal_client_zzad_zza, null);
    }

    public zzad(zza com_google_android_gms_ads_internal_client_zzad_zza, SearchAdRequest searchAdRequest) {
        this.zzfp = com_google_android_gms_ads_internal_client_zzad_zza.zzfp;
        this.zzauj = com_google_android_gms_ads_internal_client_zzad_zza.zzauj;
        this.zzaud = com_google_android_gms_ads_internal_client_zzad_zza.zzaud;
        this.zzfr = Collections.unmodifiableSet(com_google_android_gms_ads_internal_client_zzad_zza.zzavx);
        this.zzft = com_google_android_gms_ads_internal_client_zzad_zza.zzft;
        this.zzakp = com_google_android_gms_ads_internal_client_zzad_zza.zzakp;
        this.zzavs = com_google_android_gms_ads_internal_client_zzad_zza.zzavs;
        this.zzavt = Collections.unmodifiableMap(com_google_android_gms_ads_internal_client_zzad_zza.zzavy);
        this.zzauh = com_google_android_gms_ads_internal_client_zzad_zza.zzauh;
        this.zzaun = com_google_android_gms_ads_internal_client_zzad_zza.zzaun;
        this.zzavu = searchAdRequest;
        this.zzaug = com_google_android_gms_ads_internal_client_zzad_zza.zzaug;
        this.zzavv = Collections.unmodifiableSet(com_google_android_gms_ads_internal_client_zzad_zza.zzavz);
        this.zzaul = com_google_android_gms_ads_internal_client_zzad_zza.zzaul;
        this.zzavw = Collections.unmodifiableSet(com_google_android_gms_ads_internal_client_zzad_zza.zzawa);
        this.zzaup = com_google_android_gms_ads_internal_client_zzad_zza.zzaup;
    }

    public Date getBirthday() {
        return this.zzfp;
    }

    public String getContentUrl() {
        return this.zzauj;
    }

    public Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> cls) {
        Bundle bundle = this.zzavs.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        return bundle != null ? bundle.getBundle(cls.getName()) : null;
    }

    public Bundle getCustomTargeting() {
        return this.zzaul;
    }

    public int getGender() {
        return this.zzaud;
    }

    public Set<String> getKeywords() {
        return this.zzfr;
    }

    public Location getLocation() {
        return this.zzft;
    }

    public boolean getManualImpressionsEnabled() {
        return this.zzakp;
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return (NetworkExtras) this.zzavt.get(cls);
    }

    public Bundle getNetworkExtrasBundle(Class<? extends MediationAdapter> cls) {
        return this.zzavs.getBundle(cls.getName());
    }

    public String getPublisherProvidedId() {
        return this.zzauh;
    }

    public boolean isDesignedForFamilies() {
        return this.zzaup;
    }

    public boolean isTestDevice(Context context) {
        return this.zzavv.contains(zzm.zziw().zzaq(context));
    }

    public String zzje() {
        return this.zzaun;
    }

    public SearchAdRequest zzjf() {
        return this.zzavu;
    }

    public Map<Class<? extends NetworkExtras>, NetworkExtras> zzjg() {
        return this.zzavt;
    }

    public Bundle zzjh() {
        return this.zzavs;
    }

    public int zzji() {
        return this.zzaug;
    }

    public Set<String> zzjj() {
        return this.zzavw;
    }
}
