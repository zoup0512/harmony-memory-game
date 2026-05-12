package com.google.android.gms.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class zzanp {
    private final Map<Type, zzamr<?>> bed;

    public zzanp(Map<Type, zzamr<?>> map) {
        this.bed = map;
    }

    private <T> zzanu<T> zzc(final Type type, Class<? super T> cls) {
        return Collection.class.isAssignableFrom(cls) ? SortedSet.class.isAssignableFrom(cls) ? new zzanu<T>(this) {
            final /* synthetic */ zzanp beF;

            {
                this.beF = r1;
            }

            public T zzczu() {
                return new TreeSet();
            }
        } : EnumSet.class.isAssignableFrom(cls) ? new zzanu<T>(this) {
            final /* synthetic */ zzanp beF;

            public T zzczu() {
                if (type instanceof ParameterizedType) {
                    Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (type instanceof Class) {
                        return EnumSet.noneOf((Class) type);
                    }
                    String str = "Invalid EnumSet type: ";
                    String valueOf = String.valueOf(type.toString());
                    throw new zzamw(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
                str = "Invalid EnumSet type: ";
                valueOf = String.valueOf(type.toString());
                throw new zzamw(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } : Set.class.isAssignableFrom(cls) ? new zzanu<T>(this) {
            final /* synthetic */ zzanp beF;

            {
                this.beF = r1;
            }

            public T zzczu() {
                return new LinkedHashSet();
            }
        } : Queue.class.isAssignableFrom(cls) ? new zzanu<T>(this) {
            final /* synthetic */ zzanp beF;

            {
                this.beF = r1;
            }

            public T zzczu() {
                return new LinkedList();
            }
        } : new zzanu<T>(this) {
            final /* synthetic */ zzanp beF;

            {
                this.beF = r1;
            }

            public T zzczu() {
                return new ArrayList();
            }
        } : Map.class.isAssignableFrom(cls) ? SortedMap.class.isAssignableFrom(cls) ? new zzanu<T>(this) {
            final /* synthetic */ zzanp beF;

            {
                this.beF = r1;
            }

            public T zzczu() {
                return new TreeMap();
            }
        } : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(zzaol.zzl(((ParameterizedType) type).getActualTypeArguments()[0]).m())) ? new zzanu<T>(this) {
            final /* synthetic */ zzanp beF;

            {
                this.beF = r1;
            }

            public T zzczu() {
                return new zzant();
            }
        } : new zzanu<T>(this) {
            final /* synthetic */ zzanp beF;

            {
                this.beF = r1;
            }

            public T zzczu() {
                return new LinkedHashMap();
            }
        } : null;
    }

    private <T> zzanu<T> zzd(final Type type, final Class<? super T> cls) {
        return new zzanu<T>(this) {
            final /* synthetic */ zzanp beF;
            private final zzanx beG = zzanx.zzczz();

            public T zzczu() {
                try {
                    return this.beG.zzf(cls);
                } catch (Throwable e) {
                    String valueOf = String.valueOf(type);
                    throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 116).append("Unable to invoke no-args constructor for ").append(valueOf).append(". ").append("Register an InstanceCreator with Gson for this type may fix this problem.").toString(), e);
                }
            }
        };
    }

    private <T> zzanu<T> zzl(Class<? super T> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new zzanu<T>(this) {
                final /* synthetic */ zzanp beF;

                public T zzczu() {
                    String valueOf;
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (Throwable e) {
                        valueOf = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to invoke ").append(valueOf).append(" with no args").toString(), e);
                    } catch (InvocationTargetException e2) {
                        valueOf = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to invoke ").append(valueOf).append(" with no args").toString(), e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public String toString() {
        return this.bed.toString();
    }

    public <T> zzanu<T> zzb(zzaol<T> com_google_android_gms_internal_zzaol_T) {
        final Type n = com_google_android_gms_internal_zzaol_T.n();
        Class m = com_google_android_gms_internal_zzaol_T.m();
        final zzamr com_google_android_gms_internal_zzamr = (zzamr) this.bed.get(n);
        if (com_google_android_gms_internal_zzamr != null) {
            return new zzanu<T>(this) {
                final /* synthetic */ zzanp beF;

                public T zzczu() {
                    return com_google_android_gms_internal_zzamr.zza(n);
                }
            };
        }
        com_google_android_gms_internal_zzamr = (zzamr) this.bed.get(m);
        if (com_google_android_gms_internal_zzamr != null) {
            return new zzanu<T>(this) {
                final /* synthetic */ zzanp beF;

                public T zzczu() {
                    return com_google_android_gms_internal_zzamr.zza(n);
                }
            };
        }
        zzanu<T> zzl = zzl(m);
        if (zzl != null) {
            return zzl;
        }
        zzl = zzc(n, m);
        return zzl == null ? zzd(n, m) : zzl;
    }
}
