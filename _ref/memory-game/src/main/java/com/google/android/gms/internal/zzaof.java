package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzaof extends zzanh<Object> {
    public static final zzani bfu = new zzani() {
        public <T> zzanh<T> zza(zzamp com_google_android_gms_internal_zzamp, zzaol<T> com_google_android_gms_internal_zzaol_T) {
            return com_google_android_gms_internal_zzaol_T.m() == Object.class ? new zzaof(com_google_android_gms_internal_zzamp) : null;
        }
    };
    private final zzamp beq;

    private zzaof(zzamp com_google_android_gms_internal_zzamp) {
        this.beq = com_google_android_gms_internal_zzamp;
    }

    public void zza(zzaoo com_google_android_gms_internal_zzaoo, Object obj) throws IOException {
        if (obj == null) {
            com_google_android_gms_internal_zzaoo.l();
            return;
        }
        zzanh zzk = this.beq.zzk(obj.getClass());
        if (zzk instanceof zzaof) {
            com_google_android_gms_internal_zzaoo.j();
            com_google_android_gms_internal_zzaoo.k();
            return;
        }
        zzk.zza(com_google_android_gms_internal_zzaoo, obj);
    }

    public Object zzb(zzaom com_google_android_gms_internal_zzaom) throws IOException {
        switch (com_google_android_gms_internal_zzaom.b()) {
            case BEGIN_ARRAY:
                List arrayList = new ArrayList();
                com_google_android_gms_internal_zzaom.beginArray();
                while (com_google_android_gms_internal_zzaom.hasNext()) {
                    arrayList.add(zzb(com_google_android_gms_internal_zzaom));
                }
                com_google_android_gms_internal_zzaom.endArray();
                return arrayList;
            case BEGIN_OBJECT:
                Map com_google_android_gms_internal_zzant = new zzant();
                com_google_android_gms_internal_zzaom.beginObject();
                while (com_google_android_gms_internal_zzaom.hasNext()) {
                    com_google_android_gms_internal_zzant.put(com_google_android_gms_internal_zzaom.nextName(), zzb(com_google_android_gms_internal_zzaom));
                }
                com_google_android_gms_internal_zzaom.endObject();
                return com_google_android_gms_internal_zzant;
            case STRING:
                return com_google_android_gms_internal_zzaom.nextString();
            case NUMBER:
                return Double.valueOf(com_google_android_gms_internal_zzaom.nextDouble());
            case BOOLEAN:
                return Boolean.valueOf(com_google_android_gms_internal_zzaom.nextBoolean());
            case NULL:
                com_google_android_gms_internal_zzaom.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }
}
