package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.Plus$zza;
import com.google.android.gms.plus.internal.zze;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.Collection;

public final class zzvc implements People {

    private static abstract class zza extends Plus$zza<People$LoadPeopleResult> {
        private zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzdv(status);
        }

        public People$LoadPeopleResult zzdv(final Status status) {
            return new People$LoadPeopleResult(this) {
                final /* synthetic */ zza ase;

                public String getNextPageToken() {
                    return null;
                }

                public PersonBuffer getPersonBuffer() {
                    return null;
                }

                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    public Person getCurrentPerson(GoogleApiClient googleApiClient) {
        return Plus.zzf(googleApiClient, true).zzbyc();
    }

    @SuppressLint({"MissingRemoteException"})
    public PendingResult<People$LoadPeopleResult> load(GoogleApiClient googleApiClient, final Collection<String> collection) {
        return googleApiClient.zzc(new zza(this, googleApiClient) {
            final /* synthetic */ zzvc asb;

            protected void zza(zze com_google_android_gms_plus_internal_zze) {
                com_google_android_gms_plus_internal_zze.zza(this, collection);
            }
        });
    }

    @SuppressLint({"MissingRemoteException"})
    public PendingResult<People$LoadPeopleResult> load(GoogleApiClient googleApiClient, final String... strArr) {
        return googleApiClient.zzc(new zza(this, googleApiClient) {
            final /* synthetic */ zzvc asb;

            protected void zza(zze com_google_android_gms_plus_internal_zze) {
                com_google_android_gms_plus_internal_zze.zzd(this, strArr);
            }
        });
    }

    @SuppressLint({"MissingRemoteException"})
    public PendingResult<People$LoadPeopleResult> loadConnected(GoogleApiClient googleApiClient) {
        return googleApiClient.zzc(new zza(this, googleApiClient) {
            final /* synthetic */ zzvc asb;

            protected void zza(zze com_google_android_gms_plus_internal_zze) {
                com_google_android_gms_plus_internal_zze.zzv(this);
            }
        });
    }

    @SuppressLint({"MissingRemoteException"})
    public PendingResult<People$LoadPeopleResult> loadVisible(GoogleApiClient googleApiClient, final int i, final String str) {
        return googleApiClient.zzc(new zza(this, googleApiClient) {
            final /* synthetic */ zzvc asb;

            protected void zza(zze com_google_android_gms_plus_internal_zze) {
                zza(com_google_android_gms_plus_internal_zze.zza(this, i, str));
            }
        });
    }

    @SuppressLint({"MissingRemoteException"})
    public PendingResult<People$LoadPeopleResult> loadVisible(GoogleApiClient googleApiClient, final String str) {
        return googleApiClient.zzc(new zza(this, googleApiClient) {
            final /* synthetic */ zzvc asb;

            protected void zza(zze com_google_android_gms_plus_internal_zze) {
                zza(com_google_android_gms_plus_internal_zze.zzu(this, str));
            }
        });
    }
}
