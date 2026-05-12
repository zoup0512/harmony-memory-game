package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.model.people.PersonEntity;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class zze extends zzk<zzd> {
    private Person arK;
    private final PlusSession arL;

    static final class zza implements People$LoadPeopleResult {
        private final String arM;
        private final PersonBuffer arN;
        private final Status bY;

        public zza(Status status, DataHolder dataHolder, String str) {
            this.bY = status;
            this.arM = str;
            this.arN = dataHolder != null ? new PersonBuffer(dataHolder) : null;
        }

        public String getNextPageToken() {
            return this.arM;
        }

        public PersonBuffer getPersonBuffer() {
            return this.arN;
        }

        public Status getStatus() {
            return this.bY;
        }

        public void release() {
            if (this.arN != null) {
                this.arN.release();
            }
        }
    }

    static final class zzb extends zza {
        private final com.google.android.gms.internal.zzpm.zzb<People$LoadPeopleResult> act;

        public zzb(com.google.android.gms.internal.zzpm.zzb<People$LoadPeopleResult> com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult) {
            this.act = com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult;
        }

        public void zza(DataHolder dataHolder, String str) {
            Status status = new Status(dataHolder.getStatusCode(), null, dataHolder.zzarc() != null ? (PendingIntent) dataHolder.zzarc().getParcelable("pendingIntent") : null);
            if (!(status.isSuccess() || dataHolder == null)) {
                if (!dataHolder.isClosed()) {
                    dataHolder.close();
                }
                dataHolder = null;
            }
            this.act.setResult(new zza(status, dataHolder, str));
        }
    }

    static final class zzc extends zza {
        private final com.google.android.gms.internal.zzpm.zzb<Status> act;

        public zzc(com.google.android.gms.internal.zzpm.zzb<Status> com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_common_api_Status) {
            this.act = com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_common_api_Status;
        }

        public void zzk(int i, Bundle bundle) {
            this.act.setResult(new Status(i, null, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null));
        }
    }

    public zze(Context context, Looper looper, zzg com_google_android_gms_common_internal_zzg, PlusSession plusSession, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 2, com_google_android_gms_common_internal_zzg, connectionCallbacks, onConnectionFailedListener);
        this.arL = plusSession;
    }

    public static boolean zze(Set<Scope> set) {
        return (set == null || set.isEmpty()) ? false : (set.size() == 1 && set.contains(new Scope("plus_one_placeholder_scope"))) ? false : true;
    }

    public String getAccountName() {
        zzarz();
        try {
            return ((zzd) zzasa()).getAccountName();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public zzr zza(com.google.android.gms.internal.zzpm.zzb<People$LoadPeopleResult> com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult, int i, String str) {
        zzarz();
        Object com_google_android_gms_plus_internal_zze_zzb = new zzb(com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult);
        try {
            return ((zzd) zzasa()).zza(com_google_android_gms_plus_internal_zze_zzb, 1, i, -1, str);
        } catch (RemoteException e) {
            com_google_android_gms_plus_internal_zze_zzb.zza(DataHolder.zzft(8), null);
            return null;
        }
    }

    protected void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (i == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.arK = PersonEntity.zzae(bundle.getByteArray("loaded_person"));
        }
        super.zza(i, iBinder, bundle, i2);
    }

    public void zza(com.google.android.gms.internal.zzpm.zzb<People$LoadPeopleResult> com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult, Collection<String> collection) {
        zzarz();
        zzb com_google_android_gms_plus_internal_zze_zzb = new zzb(com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult);
        try {
            ((zzd) zzasa()).zza(com_google_android_gms_plus_internal_zze_zzb, new ArrayList(collection));
        } catch (RemoteException e) {
            com_google_android_gms_plus_internal_zze_zzb.zza(DataHolder.zzft(8), null);
        }
    }

    protected Bundle zzaeu() {
        Bundle zzbym = this.arL.zzbym();
        zzbym.putStringArray("request_visible_actions", this.arL.zzbyg());
        zzbym.putString("auth_package", this.arL.zzbyi());
        return zzbym;
    }

    public boolean zzafk() {
        return zze(zzasv().zzb(Plus.API));
    }

    protected /* synthetic */ IInterface zzbb(IBinder iBinder) {
        return zzkm(iBinder);
    }

    public void zzbya() {
        zzarz();
        try {
            this.arK = null;
            ((zzd) zzasa()).zzbya();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public Person zzbyc() {
        zzarz();
        return this.arK;
    }

    public void zzd(com.google.android.gms.internal.zzpm.zzb<People$LoadPeopleResult> com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult, String[] strArr) {
        zza(com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult, Arrays.asList(strArr));
    }

    protected zzd zzkm(IBinder iBinder) {
        return com.google.android.gms.plus.internal.zzd.zza.zzkl(iBinder);
    }

    protected String zzqz() {
        return "com.google.android.gms.plus.service.START";
    }

    protected String zzra() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }

    public zzr zzu(com.google.android.gms.internal.zzpm.zzb<People$LoadPeopleResult> com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult, String str) {
        return zza(com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult, 0, str);
    }

    public void zzv(com.google.android.gms.internal.zzpm.zzb<People$LoadPeopleResult> com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult) {
        zzarz();
        Object com_google_android_gms_plus_internal_zze_zzb = new zzb(com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_plus_People_LoadPeopleResult);
        try {
            ((zzd) zzasa()).zza(com_google_android_gms_plus_internal_zze_zzb, 2, 1, -1, null);
        } catch (RemoteException e) {
            com_google_android_gms_plus_internal_zze_zzb.zza(DataHolder.zzft(8), null);
        }
    }

    public void zzw(com.google.android.gms.internal.zzpm.zzb<Status> com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_common_api_Status) {
        zzarz();
        zzbya();
        Object com_google_android_gms_plus_internal_zze_zzc = new zzc(com_google_android_gms_internal_zzpm_zzb_com_google_android_gms_common_api_Status);
        try {
            ((zzd) zzasa()).zzb(com_google_android_gms_plus_internal_zze_zzc);
        } catch (RemoteException e) {
            com_google_android_gms_plus_internal_zze_zzc.zzk(8, null);
        }
    }
}
