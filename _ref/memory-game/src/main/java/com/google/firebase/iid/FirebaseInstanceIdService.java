package com.google.firebase.iid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.FirebaseApp;

public class FirebaseInstanceIdService extends zzb {
    private static final Object baA = new Object();
    private static boolean baB = false;
    private static BroadcastReceiver baz;
    private boolean baC = false;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void zza(android.content.Context r2, com.google.firebase.iid.FirebaseInstanceId r3) {
        /*
        r1 = baA;
        monitor-enter(r1);
        r0 = baB;	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x001e }
    L_0x0008:
        return;
    L_0x0009:
        monitor-exit(r1);	 Catch:{ all -> 0x001e }
        r0 = r3.zzcwt();
        if (r0 == 0) goto L_0x001a;
    L_0x0010:
        r0 = r3.zzcwv();
        r0 = r0.zzcxa();
        if (r0 == 0) goto L_0x0008;
    L_0x001a:
        zzen(r2);
        goto L_0x0008;
    L_0x001e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceIdService.zza(android.content.Context, com.google.firebase.iid.FirebaseInstanceId):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zza(android.content.Intent r9, boolean r10) {
        /*
        r8 = this;
        r2 = 1;
        r1 = 0;
        r3 = baA;
        monitor-enter(r3);
        r0 = 0;
        baB = r0;	 Catch:{ all -> 0x0010 }
        monitor-exit(r3);	 Catch:{ all -> 0x0010 }
        r0 = com.google.firebase.iid.zzf.zzdi(r8);
        if (r0 != 0) goto L_0x0013;
    L_0x000f:
        return;
    L_0x0010:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0010 }
        throw r0;
    L_0x0013:
        r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance();
        r4 = r0.zzcwv();
        r3 = r0.zzcwt();
        if (r3 != 0) goto L_0x0051;
    L_0x0021:
        r1 = r0.zzcwu();	 Catch:{ IOException -> 0x0039, SecurityException -> 0x0048 }
        if (r1 == 0) goto L_0x0042;
    L_0x0027:
        r1 = r8.baC;	 Catch:{ IOException -> 0x0039, SecurityException -> 0x0048 }
        if (r1 == 0) goto L_0x0032;
    L_0x002b:
        r1 = "FirebaseInstanceId";
        r2 = "get master token succeeded";
        android.util.Log.d(r1, r2);	 Catch:{ IOException -> 0x0039, SecurityException -> 0x0048 }
    L_0x0032:
        zza(r8, r0);	 Catch:{ IOException -> 0x0039, SecurityException -> 0x0048 }
        r8.onTokenRefresh();	 Catch:{ IOException -> 0x0039, SecurityException -> 0x0048 }
        goto L_0x000f;
    L_0x0039:
        r0 = move-exception;
        r0 = r0.getMessage();
        r8.zzd(r9, r0);
        goto L_0x000f;
    L_0x0042:
        r0 = "returned token is null";
        r8.zzd(r9, r0);	 Catch:{ IOException -> 0x0039, SecurityException -> 0x0048 }
        goto L_0x000f;
    L_0x0048:
        r0 = move-exception;
        r1 = "FirebaseInstanceId";
        r2 = "Unable to get master token";
        android.util.Log.e(r1, r2, r0);
        goto L_0x000f;
    L_0x0051:
        r0 = r4.zzcxa();
        r3 = r0;
    L_0x0056:
        if (r3 == 0) goto L_0x00be;
    L_0x0058:
        r0 = "!";
        r0 = r3.split(r0);
        r5 = r0.length;
        r6 = 2;
        if (r5 != r6) goto L_0x0071;
    L_0x0062:
        r5 = r0[r1];
        r6 = r0[r2];
        r0 = -1;
        r7 = r5.hashCode();	 Catch:{ IOException -> 0x00a1 }
        switch(r7) {
            case 83: goto L_0x007a;
            case 84: goto L_0x006e;
            case 85: goto L_0x0084;
            default: goto L_0x006e;
        };
    L_0x006e:
        switch(r0) {
            case 0: goto L_0x008e;
            case 1: goto L_0x00ab;
            default: goto L_0x0071;
        };
    L_0x0071:
        r4.zzsz(r3);
        r0 = r4.zzcxa();
        r3 = r0;
        goto L_0x0056;
    L_0x007a:
        r7 = "S";
        r5 = r5.equals(r7);	 Catch:{ IOException -> 0x00a1 }
        if (r5 == 0) goto L_0x006e;
    L_0x0082:
        r0 = r1;
        goto L_0x006e;
    L_0x0084:
        r7 = "U";
        r5 = r5.equals(r7);	 Catch:{ IOException -> 0x00a1 }
        if (r5 == 0) goto L_0x006e;
    L_0x008c:
        r0 = r2;
        goto L_0x006e;
    L_0x008e:
        r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance();	 Catch:{ IOException -> 0x00a1 }
        r0.zzsw(r6);	 Catch:{ IOException -> 0x00a1 }
        r0 = r8.baC;	 Catch:{ IOException -> 0x00a1 }
        if (r0 == 0) goto L_0x0071;
    L_0x0099:
        r0 = "FirebaseInstanceId";
        r5 = "subscribe operation succeeded";
        android.util.Log.d(r0, r5);	 Catch:{ IOException -> 0x00a1 }
        goto L_0x0071;
    L_0x00a1:
        r0 = move-exception;
        r0 = r0.getMessage();
        r8.zzd(r9, r0);
        goto L_0x000f;
    L_0x00ab:
        r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance();	 Catch:{ IOException -> 0x00a1 }
        r0.zzsx(r6);	 Catch:{ IOException -> 0x00a1 }
        r0 = r8.baC;	 Catch:{ IOException -> 0x00a1 }
        if (r0 == 0) goto L_0x0071;
    L_0x00b6:
        r0 = "FirebaseInstanceId";
        r5 = "unsubscribe operation succeeded";
        android.util.Log.d(r0, r5);	 Catch:{ IOException -> 0x00a1 }
        goto L_0x0071;
    L_0x00be:
        r0 = "FirebaseInstanceId";
        r1 = "topic sync succeeded";
        android.util.Log.d(r0, r1);
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceIdService.zza(android.content.Intent, boolean):void");
    }

    private void zza(zzf com_google_firebase_iid_zzf, Bundle bundle) {
        String zzdi = zzf.zzdi(this);
        if (zzdi == null) {
            Log.w("FirebaseInstanceId", "Unable to respond to ping due to missing target package");
            return;
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        intent.setPackage(zzdi);
        intent.putExtras(bundle);
        com_google_firebase_iid_zzf.zzs(intent);
        intent.putExtra("google.to", "google.com/iid");
        intent.putExtra("google.message_id", zzf.zzbmc());
        sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    private String zzad(Intent intent) {
        String stringExtra = intent.getStringExtra("subtype");
        return stringExtra == null ? "" : stringExtra;
    }

    private static Intent zzafa(int i) {
        Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
        Intent intent = new Intent("ACTION_TOKEN_REFRESH_RETRY");
        intent.putExtra("next_retry_delay_in_seconds", i);
        return FirebaseInstanceIdInternalReceiver.zzh(applicationContext, intent);
    }

    private void zzafb(int i) {
        ((AlarmManager) getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + ((long) (i * 1000)), PendingIntent.getBroadcast(this, 0, zzafa(i * 2), 268435456));
    }

    private int zzb(Intent intent, boolean z) {
        int intExtra = intent == null ? 10 : intent.getIntExtra("next_retry_delay_in_seconds", 0);
        return (intExtra >= 10 || z) ? intExtra >= 10 ? intExtra > 28800 ? 28800 : intExtra : 10 : 30;
    }

    private void zzd(Intent intent, String str) {
        boolean zzeo = zzeo(this);
        final int zzb = zzb(intent, zzeo);
        Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(str).length() + 47).append("background sync failed: ").append(str).append(", retry in ").append(zzb).append("s").toString());
        synchronized (baA) {
            zzafb(zzb);
            baB = true;
        }
        if (!zzeo) {
            if (this.baC) {
                Log.d("FirebaseInstanceId", "device not connected. Connectivity change received registered");
            }
            if (baz == null) {
                baz = new BroadcastReceiver(this) {
                    final /* synthetic */ FirebaseInstanceIdService baE;

                    public void onReceive(Context context, Intent intent) {
                        if (FirebaseInstanceIdService.zzeo(context)) {
                            if (this.baE.baC) {
                                Log.d("FirebaseInstanceId", "connectivity changed. starting background sync.");
                            }
                            this.baE.getApplicationContext().unregisterReceiver(this);
                            context.sendBroadcast(FirebaseInstanceIdService.zzafa(zzb));
                        }
                    }
                };
            }
            getApplicationContext().registerReceiver(baz, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    static void zzen(Context context) {
        if (zzf.zzdi(context) != null) {
            synchronized (baA) {
                if (!baB) {
                    context.sendBroadcast(zzafa(0));
                    baB = true;
                }
            }
        }
    }

    private static boolean zzeo(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private zzd zzsy(String str) {
        if (str == null) {
            return zzd.zzb(this, null);
        }
        Bundle bundle = new Bundle();
        bundle.putString("subtype", str);
        return zzd.zzb(this, bundle);
    }

    @WorkerThread
    public void onTokenRefresh() {
    }

    protected Intent zzaa(Intent intent) {
        return FirebaseInstanceIdInternalReceiver.zzcww();
    }

    protected int zzab(Intent intent) {
        this.baC = Log.isLoggable("FirebaseInstanceId", 3);
        if (intent.getStringExtra("error") == null && intent.getStringExtra("registration_id") == null) {
            return super.zzab(intent);
        }
        String zzad = zzad(intent);
        if (this.baC) {
            String str = "FirebaseInstanceId";
            String str2 = "Register result in service ";
            String valueOf = String.valueOf(zzad);
            Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        zzsy(zzad).zzcwz().zzv(intent);
        zzble();
        return 2;
    }

    public void zzac(Intent intent) {
        String zzad = zzad(intent);
        zzd zzsy = zzsy(zzad);
        String stringExtra = intent.getStringExtra("CMD");
        if (this.baC) {
            String valueOf = String.valueOf(intent.getExtras());
            Log.d("FirebaseInstanceId", new StringBuilder(((String.valueOf(zzad).length() + 18) + String.valueOf(stringExtra).length()) + String.valueOf(valueOf).length()).append("Service command ").append(zzad).append(" ").append(stringExtra).append(" ").append(valueOf).toString());
        }
        if (intent.getStringExtra("unregistered") != null) {
            zzg zzcwy = zzsy.zzcwy();
            if (zzad == null) {
                zzad = "";
            }
            zzcwy.zzkj(zzad);
            zzsy.zzcwz().zzv(intent);
        } else if ("gcm.googleapis.com/refresh".equals(intent.getStringExtra("from"))) {
            zzsy.zzcwy().zzkj(zzad);
            zza(intent, false);
        } else if ("RST".equals(stringExtra)) {
            zzsy.zzblx();
            zzsy.zzcwy().zzkj(zzad);
            zza(intent, true);
        } else if ("RST_FULL".equals(stringExtra)) {
            if (!zzsy.zzcwy().isEmpty()) {
                zzsy.zzblx();
                zzsy.zzcwy().zzbmd();
                zza(intent, true);
            }
        } else if ("SYNC".equals(stringExtra)) {
            zzsy.zzcwy().zzkj(zzad);
            zza(intent, false);
        } else if ("PING".equals(stringExtra)) {
            zza(zzsy.zzcwz(), intent.getExtras());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzm(android.content.Intent r5) {
        /*
        r4 = this;
        r1 = 0;
        r0 = r5.getAction();
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        r0 = "";
    L_0x0009:
        r2 = -1;
        r3 = r0.hashCode();
        switch(r3) {
            case -1737547627: goto L_0x0019;
            default: goto L_0x0011;
        };
    L_0x0011:
        r0 = r2;
    L_0x0012:
        switch(r0) {
            case 0: goto L_0x0023;
            default: goto L_0x0015;
        };
    L_0x0015:
        r4.zzac(r5);
    L_0x0018:
        return;
    L_0x0019:
        r3 = "ACTION_TOKEN_REFRESH_RETRY";
        r0 = r0.equals(r3);
        if (r0 == 0) goto L_0x0011;
    L_0x0021:
        r0 = r1;
        goto L_0x0012;
    L_0x0023:
        r4.zza(r5, r1);
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceIdService.zzm(android.content.Intent):void");
    }
}
