package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzadw;
import com.google.android.gms.internal.zzadw.zzc;
import com.google.android.gms.internal.zzadw.zzg;
import com.google.android.gms.internal.zzah.zzf;
import com.google.android.gms.internal.zzah.zzi;
import com.google.android.gms.internal.zzah.zzj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    private final String auF;
    private final DataLayer auG;
    private zzcw auH;
    private Map<String, FunctionCallMacroCallback> auI = new HashMap();
    private Map<String, FunctionCallTagCallback> auJ = new HashMap();
    private volatile long auK;
    private volatile String auL = "";
    private final Context mContext;

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    private class zza implements com.google.android.gms.tagmanager.zzt.zza {
        final /* synthetic */ Container auM;

        private zza(Container container) {
            this.auM = container;
        }

        public Object zze(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzoe = this.auM.zzoe(str);
            return zzoe == null ? null : zzoe.getValue(str, map);
        }
    }

    private class zzb implements com.google.android.gms.tagmanager.zzt.zza {
        final /* synthetic */ Container auM;

        private zzb(Container container) {
            this.auM = container;
        }

        public Object zze(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzof = this.auM.zzof(str);
            if (zzof != null) {
                zzof.execute(str, map);
            }
            return zzdl.zzcdt();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzc com_google_android_gms_internal_zzadw_zzc) {
        this.mContext = context;
        this.auG = dataLayer;
        this.auF = str;
        this.auK = j;
        zza(com_google_android_gms_internal_zzadw_zzc);
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzj com_google_android_gms_internal_zzah_zzj) {
        this.mContext = context;
        this.auG = dataLayer;
        this.auF = str;
        this.auK = j;
        zza(com_google_android_gms_internal_zzah_zzj.zzwr);
        if (com_google_android_gms_internal_zzah_zzj.zzwq != null) {
            zza(com_google_android_gms_internal_zzah_zzj.zzwq);
        }
    }

    private void zza(zzc com_google_android_gms_internal_zzadw_zzc) {
        this.auL = com_google_android_gms_internal_zzadw_zzc.getVersion();
        zzc com_google_android_gms_internal_zzadw_zzc2 = com_google_android_gms_internal_zzadw_zzc;
        zza(new zzcw(this.mContext, com_google_android_gms_internal_zzadw_zzc2, this.auG, new zza(), new zzb(), zzoh(this.auL)));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.auG.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.auF));
        }
    }

    private void zza(zzf com_google_android_gms_internal_zzah_zzf) {
        if (com_google_android_gms_internal_zzah_zzf == null) {
            throw new NullPointerException();
        }
        try {
            zza(zzadw.zzb(com_google_android_gms_internal_zzah_zzf));
        } catch (zzg e) {
            String valueOf = String.valueOf(com_google_android_gms_internal_zzah_zzf);
            String valueOf2 = String.valueOf(e.toString());
            zzbn.e(new StringBuilder((String.valueOf(valueOf).length() + 46) + String.valueOf(valueOf2).length()).append("Not loading resource: ").append(valueOf).append(" because it is invalid: ").append(valueOf2).toString());
        }
    }

    private synchronized void zza(zzcw com_google_android_gms_tagmanager_zzcw) {
        this.auH = com_google_android_gms_tagmanager_zzcw;
    }

    private void zza(zzi[] com_google_android_gms_internal_zzah_zziArr) {
        List arrayList = new ArrayList();
        for (Object add : com_google_android_gms_internal_zzah_zziArr) {
            arrayList.add(add);
        }
        zzcam().zzaj(arrayList);
    }

    private synchronized zzcw zzcam() {
        return this.auH;
    }

    public boolean getBoolean(String str) {
        zzcw zzcam = zzcam();
        if (zzcam == null) {
            zzbn.e("getBoolean called for closed container.");
            return zzdl.zzcdr().booleanValue();
        }
        try {
            return zzdl.zzk((com.google.android.gms.internal.zzai.zza) zzcam.zzpc(str).getObject()).booleanValue();
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbn.e(new StringBuilder(String.valueOf(valueOf).length() + 66).append("Calling getBoolean() threw an exception: ").append(valueOf).append(" Returning default value.").toString());
            return zzdl.zzcdr().booleanValue();
        }
    }

    public String getContainerId() {
        return this.auF;
    }

    public double getDouble(String str) {
        zzcw zzcam = zzcam();
        if (zzcam == null) {
            zzbn.e("getDouble called for closed container.");
            return zzdl.zzcdq().doubleValue();
        }
        try {
            return zzdl.zzj((com.google.android.gms.internal.zzai.zza) zzcam.zzpc(str).getObject()).doubleValue();
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbn.e(new StringBuilder(String.valueOf(valueOf).length() + 65).append("Calling getDouble() threw an exception: ").append(valueOf).append(" Returning default value.").toString());
            return zzdl.zzcdq().doubleValue();
        }
    }

    public long getLastRefreshTime() {
        return this.auK;
    }

    public long getLong(String str) {
        zzcw zzcam = zzcam();
        if (zzcam == null) {
            zzbn.e("getLong called for closed container.");
            return zzdl.zzcdp().longValue();
        }
        try {
            return zzdl.zzi((com.google.android.gms.internal.zzai.zza) zzcam.zzpc(str).getObject()).longValue();
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbn.e(new StringBuilder(String.valueOf(valueOf).length() + 63).append("Calling getLong() threw an exception: ").append(valueOf).append(" Returning default value.").toString());
            return zzdl.zzcdp().longValue();
        }
    }

    public String getString(String str) {
        zzcw zzcam = zzcam();
        if (zzcam == null) {
            zzbn.e("getString called for closed container.");
            return zzdl.zzcdt();
        }
        try {
            return zzdl.zzg((com.google.android.gms.internal.zzai.zza) zzcam.zzpc(str).getObject());
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbn.e(new StringBuilder(String.valueOf(valueOf).length() + 65).append("Calling getString() threw an exception: ").append(valueOf).append(" Returning default value.").toString());
            return zzdl.zzcdt();
        }
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String str, FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.auI) {
            this.auI.put(str, functionCallMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(String str, FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.auJ) {
            this.auJ.put(str, functionCallTagCallback);
        }
    }

    void release() {
        this.auH = null;
    }

    public void unregisterFunctionCallMacroCallback(String str) {
        synchronized (this.auI) {
            this.auI.remove(str);
        }
    }

    public void unregisterFunctionCallTagCallback(String str) {
        synchronized (this.auJ) {
            this.auJ.remove(str);
        }
    }

    public String zzcal() {
        return this.auL;
    }

    FunctionCallMacroCallback zzoe(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.auI) {
            functionCallMacroCallback = (FunctionCallMacroCallback) this.auI.get(str);
        }
        return functionCallMacroCallback;
    }

    public FunctionCallTagCallback zzof(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.auJ) {
            functionCallTagCallback = (FunctionCallTagCallback) this.auJ.get(str);
        }
        return functionCallTagCallback;
    }

    public void zzog(String str) {
        zzcam().zzog(str);
    }

    zzai zzoh(String str) {
        if (zzci.zzcci().zzccj().equals(zza.CONTAINER_DEBUG)) {
        }
        return new zzbv();
    }
}
