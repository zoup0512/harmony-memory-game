package com.google.android.gms.plus;

import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.zzab;
import java.util.HashSet;
import java.util.Set;

public final class Plus$PlusOptions implements Optional {
    final String arA;
    final Set<String> arB;

    public static final class Builder {
        String arA;
        final Set<String> arB = new HashSet();

        public Builder addActivityTypes(String... strArr) {
            zzab.zzb((Object) strArr, (Object) "activityTypes may not be null.");
            for (Object add : strArr) {
                this.arB.add(add);
            }
            return this;
        }

        public Plus$PlusOptions build() {
            return new Plus$PlusOptions();
        }

        public Builder setServerClientId(String str) {
            this.arA = str;
            return this;
        }
    }

    private Plus$PlusOptions() {
        this.arA = null;
        this.arB = new HashSet();
    }

    private Plus$PlusOptions(Builder builder) {
        this.arA = builder.arA;
        this.arB = builder.arB;
    }

    public static Builder builder() {
        return new Builder();
    }
}
