package com.google.android.gms.analytics.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Pair;
import com.amazonaws.regions.ServiceAbbreviations;
import com.google.android.gms.common.internal.zzab;
import java.util.UUID;

public class zzai extends zzd {
    private SharedPreferences ah;
    private long ai;
    private long aj = -1;
    private final zza ak = new zza(ServiceAbbreviations.CloudWatch, zzyy().zzaci());

    public final class zza {
        private final long al;
        final /* synthetic */ zzai am;
        private final String mName;

        private zza(zzai com_google_android_gms_analytics_internal_zzai, String str, long j) {
            this.am = com_google_android_gms_analytics_internal_zzai;
            zzab.zzhr(str);
            zzab.zzbo(j > 0);
            this.mName = str;
            this.al = j;
        }

        private void zzadt() {
            long currentTimeMillis = this.am.zzyw().currentTimeMillis();
            Editor edit = this.am.ah.edit();
            edit.remove(zzady());
            edit.remove(zzadz());
            edit.putLong(zzadx(), currentTimeMillis);
            edit.commit();
        }

        private long zzadu() {
            long zzadw = zzadw();
            return zzadw == 0 ? 0 : Math.abs(zzadw - this.am.zzyw().currentTimeMillis());
        }

        private long zzadw() {
            return this.am.ah.getLong(zzadx(), 0);
        }

        private String zzadx() {
            return String.valueOf(this.mName).concat(":start");
        }

        private String zzady() {
            return String.valueOf(this.mName).concat(":count");
        }

        public Pair<String, Long> zzadv() {
            long zzadu = zzadu();
            if (zzadu < this.al) {
                return null;
            }
            if (zzadu > this.al * 2) {
                zzadt();
                return null;
            }
            String string = this.am.ah.getString(zzadz(), null);
            zzadu = this.am.ah.getLong(zzady(), 0);
            zzadt();
            return (string == null || zzadu <= 0) ? null : new Pair(string, Long.valueOf(zzadu));
        }

        protected String zzadz() {
            return String.valueOf(this.mName).concat(":value");
        }

        public void zzev(String str) {
            if (zzadw() == 0) {
                zzadt();
            }
            if (str == null) {
                str = "";
            }
            synchronized (this) {
                long j = this.am.ah.getLong(zzady(), 0);
                if (j <= 0) {
                    Editor edit = this.am.ah.edit();
                    edit.putString(zzadz(), str);
                    edit.putLong(zzady(), 1);
                    edit.apply();
                    return;
                }
                Object obj = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / (j + 1) ? 1 : null;
                Editor edit2 = this.am.ah.edit();
                if (obj != null) {
                    edit2.putString(zzadz(), str);
                }
                edit2.putLong(zzady(), j + 1);
                edit2.apply();
            }
        }
    }

    protected zzai(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
    }

    public long zzadn() {
        zzwu();
        zzzg();
        if (this.ai == 0) {
            long j = this.ah.getLong("first_run", 0);
            if (j != 0) {
                this.ai = j;
            } else {
                j = zzyw().currentTimeMillis();
                Editor edit = this.ah.edit();
                edit.putLong("first_run", j);
                if (!edit.commit()) {
                    zzek("Failed to commit first run time");
                }
                this.ai = j;
            }
        }
        return this.ai;
    }

    public zzal zzado() {
        return new zzal(zzyw(), zzadn());
    }

    public long zzadp() {
        zzwu();
        zzzg();
        if (this.aj == -1) {
            this.aj = this.ah.getLong("last_dispatch", 0);
        }
        return this.aj;
    }

    public void zzadq() {
        zzwu();
        zzzg();
        long currentTimeMillis = zzyw().currentTimeMillis();
        Editor edit = this.ah.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.aj = currentTimeMillis;
    }

    public String zzadr() {
        zzwu();
        zzzg();
        CharSequence string = this.ah.getString("installation_campaign", null);
        return TextUtils.isEmpty(string) ? null : string;
    }

    public zza zzads() {
        return this.ak;
    }

    public void zzeu(String str) {
        zzwu();
        zzzg();
        Editor edit = this.ah.edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("installation_campaign");
        } else {
            edit.putString("installation_campaign", str);
        }
        if (!edit.commit()) {
            zzek("Failed to commit campaign data");
        }
    }

    protected void zzwv() {
        this.ah = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }
}
