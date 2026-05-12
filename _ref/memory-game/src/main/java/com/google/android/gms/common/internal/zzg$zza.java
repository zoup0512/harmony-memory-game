package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Set;

public final class zzg$zza {
    public final Set<Scope> dT;
    public final boolean yn;

    public zzg$zza(Set<Scope> set, boolean z) {
        zzab.zzy(set);
        this.dT = Collections.unmodifiableSet(set);
        this.yn = z;
    }
}
