package com.google.android.gms.internal;

import com.amazonaws.util.DateUtils;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class zzaoa extends zzanh<Date> {
    public static final zzani bfu = new zzani() {
        public <T> zzanh<T> zza(zzamp com_google_android_gms_internal_zzamp, zzaol<T> com_google_android_gms_internal_zzaol_T) {
            return com_google_android_gms_internal_zzaol_T.m() == Date.class ? new zzaoa() : null;
        }
    };
    private final DateFormat bdE = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat bdF = DateFormat.getDateTimeInstance(2, 2);
    private final DateFormat bdG = a();

    private static DateFormat a() {
        DateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    private synchronized Date zztq(String str) {
        Date parse;
        try {
            parse = this.bdF.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.bdE.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = this.bdG.parse(str);
                } catch (Throwable e3) {
                    throw new zzane(str, e3);
                }
            }
        }
        return parse;
    }

    public synchronized void zza(zzaoo com_google_android_gms_internal_zzaoo, Date date) throws IOException {
        if (date == null) {
            com_google_android_gms_internal_zzaoo.l();
        } else {
            com_google_android_gms_internal_zzaoo.zzts(this.bdE.format(date));
        }
    }

    public /* synthetic */ Object zzb(zzaom com_google_android_gms_internal_zzaom) throws IOException {
        return zzk(com_google_android_gms_internal_zzaom);
    }

    public Date zzk(zzaom com_google_android_gms_internal_zzaom) throws IOException {
        if (com_google_android_gms_internal_zzaom.b() != zzaon.NULL) {
            return zztq(com_google_android_gms_internal_zzaom.nextString());
        }
        com_google_android_gms_internal_zzaom.nextNull();
        return null;
    }
}
