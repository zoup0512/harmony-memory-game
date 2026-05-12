package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.zzc;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.yalantis.ucrop.util.FileUtils;
import java.lang.reflect.Field;

public final class zzsb {
    private static zzsc KG;
    private static final zza KH = new zza() {
        public int zzd(Context context, String str, boolean z) {
            return zzsb.zzd(context, str, z);
        }

        public int zzt(Context context, String str) {
            return zzsb.zzt(context, str);
        }
    };
    public static final zzb KI = new zzb() {
        public zzb zza(Context context, String str, zza com_google_android_gms_internal_zzsb_zzb_zza) {
            zzb com_google_android_gms_internal_zzsb_zzb_zzb = new zzb();
            com_google_android_gms_internal_zzsb_zzb_zzb.KQ = com_google_android_gms_internal_zzsb_zzb_zza.zzd(context, str, true);
            if (com_google_android_gms_internal_zzsb_zzb_zzb.KQ != 0) {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = 1;
            } else {
                com_google_android_gms_internal_zzsb_zzb_zzb.KP = com_google_android_gms_internal_zzsb_zzb_zza.zzt(context, str);
                if (com_google_android_gms_internal_zzsb_zzb_zzb.KP != 0) {
                    com_google_android_gms_internal_zzsb_zzb_zzb.KR = -1;
                }
            }
            return com_google_android_gms_internal_zzsb_zzb_zzb;
        }
    };
    public static final zzb KJ = new zzb() {
        public zzb zza(Context context, String str, zza com_google_android_gms_internal_zzsb_zzb_zza) {
            zzb com_google_android_gms_internal_zzsb_zzb_zzb = new zzb();
            com_google_android_gms_internal_zzsb_zzb_zzb.KP = com_google_android_gms_internal_zzsb_zzb_zza.zzt(context, str);
            if (com_google_android_gms_internal_zzsb_zzb_zzb.KP != 0) {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = -1;
            } else {
                com_google_android_gms_internal_zzsb_zzb_zzb.KQ = com_google_android_gms_internal_zzsb_zzb_zza.zzd(context, str, true);
                if (com_google_android_gms_internal_zzsb_zzb_zzb.KQ != 0) {
                    com_google_android_gms_internal_zzsb_zzb_zzb.KR = 1;
                }
            }
            return com_google_android_gms_internal_zzsb_zzb_zzb;
        }
    };
    public static final zzb KK = new zzb() {
        public zzb zza(Context context, String str, zza com_google_android_gms_internal_zzsb_zzb_zza) {
            zzb com_google_android_gms_internal_zzsb_zzb_zzb = new zzb();
            com_google_android_gms_internal_zzsb_zzb_zzb.KP = com_google_android_gms_internal_zzsb_zzb_zza.zzt(context, str);
            com_google_android_gms_internal_zzsb_zzb_zzb.KQ = com_google_android_gms_internal_zzsb_zzb_zza.zzd(context, str, true);
            if (com_google_android_gms_internal_zzsb_zzb_zzb.KP == 0 && com_google_android_gms_internal_zzsb_zzb_zzb.KQ == 0) {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = 0;
            } else if (com_google_android_gms_internal_zzsb_zzb_zzb.KP >= com_google_android_gms_internal_zzsb_zzb_zzb.KQ) {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = -1;
            } else {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = 1;
            }
            return com_google_android_gms_internal_zzsb_zzb_zzb;
        }
    };
    public static final zzb KL = new zzb() {
        public zzb zza(Context context, String str, zza com_google_android_gms_internal_zzsb_zzb_zza) {
            zzb com_google_android_gms_internal_zzsb_zzb_zzb = new zzb();
            com_google_android_gms_internal_zzsb_zzb_zzb.KP = com_google_android_gms_internal_zzsb_zzb_zza.zzt(context, str);
            com_google_android_gms_internal_zzsb_zzb_zzb.KQ = com_google_android_gms_internal_zzsb_zzb_zza.zzd(context, str, true);
            if (com_google_android_gms_internal_zzsb_zzb_zzb.KP == 0 && com_google_android_gms_internal_zzsb_zzb_zzb.KQ == 0) {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = 0;
            } else if (com_google_android_gms_internal_zzsb_zzb_zzb.KQ >= com_google_android_gms_internal_zzsb_zzb_zzb.KP) {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = 1;
            } else {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = -1;
            }
            return com_google_android_gms_internal_zzsb_zzb_zzb;
        }
    };
    public static final zzb KM = new zzb() {
        public zzb zza(Context context, String str, zza com_google_android_gms_internal_zzsb_zzb_zza) {
            zzb com_google_android_gms_internal_zzsb_zzb_zzb = new zzb();
            com_google_android_gms_internal_zzsb_zzb_zzb.KP = com_google_android_gms_internal_zzsb_zzb_zza.zzt(context, str);
            if (com_google_android_gms_internal_zzsb_zzb_zzb.KP != 0) {
                com_google_android_gms_internal_zzsb_zzb_zzb.KQ = com_google_android_gms_internal_zzsb_zzb_zza.zzd(context, str, false);
            } else {
                com_google_android_gms_internal_zzsb_zzb_zzb.KQ = com_google_android_gms_internal_zzsb_zzb_zza.zzd(context, str, true);
            }
            if (com_google_android_gms_internal_zzsb_zzb_zzb.KP == 0 && com_google_android_gms_internal_zzsb_zzb_zzb.KQ == 0) {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = 0;
            } else if (com_google_android_gms_internal_zzsb_zzb_zzb.KQ >= com_google_android_gms_internal_zzsb_zzb_zzb.KP) {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = 1;
            } else {
                com_google_android_gms_internal_zzsb_zzb_zzb.KR = -1;
            }
            return com_google_android_gms_internal_zzsb_zzb_zzb;
        }
    };
    private final Context KN;

    public interface zzb {

        public interface zza {
            int zzd(Context context, String str, boolean z);

            int zzt(Context context, String str);
        }

        public static class zzb {
            public int KP = 0;
            public int KQ = 0;
            public int KR = 0;
        }

        zzb zza(Context context, String str, zza com_google_android_gms_internal_zzsb_zzb_zza);
    }

    class AnonymousClass7 implements zza {
        final /* synthetic */ int KO;

        AnonymousClass7(int i) {
            this.KO = i;
        }

        public int zzd(Context context, String str, boolean z) {
            return 0;
        }

        public int zzt(Context context, String str) {
            return this.KO;
        }
    }

    public static class zza extends Exception {
        private zza(String str) {
            super(str);
        }

        private zza(String str, Throwable th) {
            super(str, th);
        }
    }

    private zzsb(Context context) {
        this.KN = (Context) zzab.zzy(context);
    }

    public static zzsb zza(Context context, zzb com_google_android_gms_internal_zzsb_zzb, String str) throws zza {
        zzb zza = com_google_android_gms_internal_zzsb_zzb.zza(context, str, KH);
        Log.i("DynamiteModule", new StringBuilder((String.valueOf(str).length() + 68) + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza.KP).append(" and remote module ").append(str).append(":").append(zza.KQ).toString());
        if (zza.KR == 0 || ((zza.KR == -1 && zza.KP == 0) || (zza.KR == 1 && zza.KQ == 0))) {
            throw new zza("No acceptable module found. Local version is " + zza.KP + " and remote version is " + zza.KQ + FileUtils.HIDDEN_PREFIX);
        } else if (zza.KR == -1) {
            return zzv(context, str);
        } else {
            if (zza.KR == 1) {
                try {
                    return zza(context, str, zza.KQ);
                } catch (Throwable e) {
                    Throwable th = e;
                    String str2 = "DynamiteModule";
                    String str3 = "Failed to load remote module: ";
                    String valueOf = String.valueOf(th.getMessage());
                    Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    if (zza.KP != 0 && com_google_android_gms_internal_zzsb_zzb.zza(context, str, new AnonymousClass7(zza.KP)).KR == -1) {
                        return zzv(context, str);
                    }
                    throw new zza("Remote load failed. No local fallback found.", th);
                }
            }
            throw new zza("VersionPolicy returned invalid code:" + zza.KR);
        }
    }

    private static zzsb zza(Context context, String str, int i) throws zza {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        zzsc zzcs = zzcs(context);
        if (zzcs == null) {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        try {
            zzd zza = zzcs.zza(zze.zzac(context), str, i);
            if (zze.zzad(zza) != null) {
                return new zzsb((Context) zze.zzad(zza));
            }
            throw new zza("Failed to load remote module.");
        } catch (Throwable e) {
            throw new zza("Failed to load remote module.", e);
        }
    }

    private static zzsc zzcs(Context context) {
        synchronized (zzsb.class) {
            zzsc com_google_android_gms_internal_zzsc;
            if (KG != null) {
                com_google_android_gms_internal_zzsc = KG;
                return com_google_android_gms_internal_zzsc;
            } else if (zzc.zzang().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            } else {
                try {
                    com_google_android_gms_internal_zzsc = com.google.android.gms.internal.zzsc.zza.zzfd((IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                    if (com_google_android_gms_internal_zzsc != null) {
                        KG = com_google_android_gms_internal_zzsc;
                        return com_google_android_gms_internal_zzsc;
                    }
                } catch (Exception e) {
                    String str = "DynamiteModule";
                    String str2 = "Failed to load IDynamiteLoader from GmsCore: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    return null;
                }
            }
        }
    }

    public static int zzd(Context context, String str, boolean z) {
        zzsc zzcs = zzcs(context);
        if (zzcs == null) {
            return 0;
        }
        try {
            return zzcs.zza(zze.zzac(context), str, z);
        } catch (RemoteException e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to retrieve remote module version: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return 0;
        }
    }

    public static int zzt(Context context, String str) {
        String valueOf;
        String valueOf2;
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            valueOf = String.valueOf("com.google.android.gms.dynamite.descriptors.");
            valueOf2 = String.valueOf("ModuleDescriptor");
            Class loadClass = classLoader.loadClass(new StringBuilder(((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length()) + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(FileUtils.HIDDEN_PREFIX).append(valueOf2).toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            valueOf = String.valueOf(declaredField.get(null));
            Log.e("DynamiteModule", new StringBuilder((String.valueOf(valueOf).length() + 51) + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            valueOf = "DynamiteModule";
            valueOf2 = "Failed to load module descriptor class: ";
            String valueOf3 = String.valueOf(e2.getMessage());
            Log.e(valueOf, valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2));
            return 0;
        }
    }

    public static int zzu(Context context, String str) {
        return zzd(context, str, false);
    }

    private static zzsb zzv(Context context, String str) {
        String str2 = "DynamiteModule";
        String str3 = "Selected local version of ";
        String valueOf = String.valueOf(str);
        Log.i(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        return new zzsb(context.getApplicationContext());
    }

    public Context zzbby() {
        return this.KN;
    }

    public IBinder zziu(String str) throws zza {
        Throwable e;
        String str2;
        String valueOf;
        try {
            return (IBinder) this.KN.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException e2) {
            e = e2;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            throw new zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        } catch (InstantiationException e3) {
            e = e3;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        } catch (IllegalAccessException e4) {
            e = e4;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        }
    }
}
