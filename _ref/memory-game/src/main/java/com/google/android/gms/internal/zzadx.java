package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class zzadx {
    public static final Integer aDe = Integer.valueOf(0);
    public static final Integer aDf = Integer.valueOf(1);
    private final ExecutorService axl;
    private final Context mContext;

    public zzadx(Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }

    zzadx(Context context, ExecutorService executorService) {
        this.mContext = context;
        this.axl = executorService;
    }
}
