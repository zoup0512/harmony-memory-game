package com.mopub.nativeads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

abstract class TaskManager<T> {
    @NonNull
    protected final AtomicInteger mCompletedCount;
    @NonNull
    protected final AtomicBoolean mFailed;
    @NonNull
    protected final TaskManagerListener<T> mImageTaskManagerListener;
    @NonNull
    protected final Map<String, T> mResults;
    protected final int mSize;

    interface TaskManagerListener<T> {
        void onFail();

        void onSuccess(@NonNull Map<String, T> map);
    }

    abstract void execute();

    TaskManager(@NonNull List<String> list, @NonNull TaskManagerListener<T> taskManagerListener) {
        Preconditions.checkNotNull(list, "Urls list cannot be null");
        Preconditions.checkNotNull(taskManagerListener, "ImageTaskManagerListener cannot be null");
        Preconditions.checkState(!list.contains(null), "Urls list cannot contain null");
        this.mSize = list.size();
        this.mImageTaskManagerListener = taskManagerListener;
        this.mCompletedCount = new AtomicInteger(0);
        this.mFailed = new AtomicBoolean(false);
        this.mResults = Collections.synchronizedMap(new HashMap(this.mSize));
    }
}
