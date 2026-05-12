package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzj extends zzdj {
    private static final String ID = zzaf.ARBITRARY_PIXEL.toString();
    private static final String URL = zzag.zztw.toString();
    static final String auA;
    private static final Set<String> auB = new HashSet();
    private static final String auy = zzag.ADDITIONAL_PARAMS.toString();
    private static final String auz = zzag.UNREPEATABLE.toString();
    private final zza auC;
    private final Context mContext;

    public interface zza {
        zzas zzcah();
    }

    class AnonymousClass1 implements zza {
        final /* synthetic */ Context zzala;

        AnonymousClass1(Context context) {
            this.zzala = context;
        }

        public zzas zzcah() {
            return zzz.zzdv(this.zzala);
        }
    }

    static {
        String str = ID;
        auA = new StringBuilder(String.valueOf(str).length() + 17).append("gtm_").append(str).append("_unrepeatable").toString();
    }

    public zzj(Context context) {
        this(context, new AnonymousClass1(context));
    }

    zzj(Context context, zza com_google_android_gms_tagmanager_zzj_zza) {
        super(ID, URL);
        this.auC = com_google_android_gms_tagmanager_zzj_zza;
        this.mContext = context;
    }

    private synchronized boolean zzoa(String str) {
        boolean z = true;
        synchronized (this) {
            if (!zzoc(str)) {
                if (zzob(str)) {
                    auB.add(str);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    public void zzax(Map<String, com.google.android.gms.internal.zzai.zza> map) {
        String zzg = map.get(auz) != null ? zzdl.zzg((com.google.android.gms.internal.zzai.zza) map.get(auz)) : null;
        if (zzg == null || !zzoa(zzg)) {
            String valueOf;
            Builder buildUpon = Uri.parse(zzdl.zzg((com.google.android.gms.internal.zzai.zza) map.get(URL))).buildUpon();
            com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza = (com.google.android.gms.internal.zzai.zza) map.get(auy);
            if (com_google_android_gms_internal_zzai_zza != null) {
                Object zzl = zzdl.zzl(com_google_android_gms_internal_zzai_zza);
                if (zzl instanceof List) {
                    for (Object zzl2 : (List) zzl2) {
                        if (zzl2 instanceof Map) {
                            for (Entry entry : ((Map) zzl2).entrySet()) {
                                buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                            }
                        } else {
                            zzg = "ArbitraryPixel: additional params contains non-map: not sending partial hit: ";
                            valueOf = String.valueOf(buildUpon.build().toString());
                            zzbn.e(valueOf.length() != 0 ? zzg.concat(valueOf) : new String(zzg));
                            return;
                        }
                    }
                }
                zzg = "ArbitraryPixel: additional params not a list: not sending partial hit: ";
                valueOf = String.valueOf(buildUpon.build().toString());
                zzbn.e(valueOf.length() != 0 ? zzg.concat(valueOf) : new String(zzg));
                return;
            }
            valueOf = buildUpon.build().toString();
            this.auC.zzcah().zzor(valueOf);
            String str = "ArbitraryPixel: url = ";
            valueOf = String.valueOf(valueOf);
            zzbn.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            if (zzg != null) {
                synchronized (zzj.class) {
                    auB.add(zzg);
                    zzdc.zzb(this.mContext, auA, zzg, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                }
            }
        }
    }

    boolean zzob(String str) {
        return this.mContext.getSharedPreferences(auA, 0).contains(str);
    }

    boolean zzoc(String str) {
        return auB.contains(str);
    }
}
