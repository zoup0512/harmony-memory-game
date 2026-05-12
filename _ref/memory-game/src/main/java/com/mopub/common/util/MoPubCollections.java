package com.mopub.common.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

public class MoPubCollections {
    public static <T> void addAllNonNull(@NonNull Collection<? super T> collection, @Nullable T... tArr) {
        Collections.addAll(collection, tArr);
        collection.removeAll(Collections.singleton(null));
    }
}
