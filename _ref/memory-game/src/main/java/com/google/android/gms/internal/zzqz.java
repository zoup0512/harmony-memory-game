package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzqz<T> {
    private static zza vN = null;
    private static int vO = 0;
    private static String vP = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    private static final Object zzamr = new Object();
    private T vQ = null;
    protected final String zzaxp;
    protected final T zzaxq;

    class AnonymousClass1 extends zzqz<Boolean> {
        AnonymousClass1(String str, Boolean bool) {
            super(str, bool);
        }

        protected /* synthetic */ Object zzgy(String str) {
            return zzgz(str);
        }

        protected Boolean zzgz(String str) {
            return null.zza(this.zzaxp, (Boolean) this.zzaxq);
        }
    }

    class AnonymousClass2 extends zzqz<Long> {
        AnonymousClass2(String str, Long l) {
            super(str, l);
        }

        protected /* synthetic */ Object zzgy(String str) {
            return zzha(str);
        }

        protected Long zzha(String str) {
            return null.getLong(this.zzaxp, (Long) this.zzaxq);
        }
    }

    class AnonymousClass3 extends zzqz<Integer> {
        AnonymousClass3(String str, Integer num) {
            super(str, num);
        }

        protected /* synthetic */ Object zzgy(String str) {
            return zzhb(str);
        }

        protected Integer zzhb(String str) {
            return null.zzb(this.zzaxp, (Integer) this.zzaxq);
        }
    }

    class AnonymousClass4 extends zzqz<Float> {
        AnonymousClass4(String str, Float f) {
            super(str, f);
        }

        protected /* synthetic */ Object zzgy(String str) {
            return zzhc(str);
        }

        protected Float zzhc(String str) {
            return null.zzb(this.zzaxp, (Float) this.zzaxq);
        }
    }

    class AnonymousClass5 extends zzqz<String> {
        AnonymousClass5(String str, String str2) {
            super(str, str2);
        }

        protected /* synthetic */ Object zzgy(String str) {
            return zzhd(str);
        }

        protected String zzhd(String str) {
            return null.getString(this.zzaxp, (String) this.zzaxq);
        }
    }

    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zzb(String str, Float f);

        Integer zzb(String str, Integer num);
    }

    protected zzqz(String str, T t) {
        this.zzaxp = str;
        this.zzaxq = t;
    }

    public static zzqz<Float> zza(String str, Float f) {
        return new AnonymousClass4(str, f);
    }

    public static zzqz<Integer> zza(String str, Integer num) {
        return new AnonymousClass3(str, num);
    }

    public static zzqz<Long> zza(String str, Long l) {
        return new AnonymousClass2(str, l);
    }

    public static zzqz<String> zzab(String str, String str2) {
        return new AnonymousClass5(str, str2);
    }

    public static zzqz<Boolean> zzm(String str, boolean z) {
        return new AnonymousClass1(str, Boolean.valueOf(z));
    }

    public final T get() {
        T zzgy;
        long clearCallingIdentity;
        try {
            zzgy = zzgy(this.zzaxp);
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            zzgy = zzgy(this.zzaxp);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
        return zzgy;
    }

    protected abstract T zzgy(String str);
}
