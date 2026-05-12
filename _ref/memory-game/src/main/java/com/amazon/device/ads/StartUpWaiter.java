package com.amazon.device.ads;

import android.util.SparseArray;
import com.amazon.device.ads.Settings.SettingsListener;
import com.amazon.device.ads.ThreadUtils.MainThreadScheduler;
import com.amazon.device.ads.ThreadUtils.RunnableExecutor;
import com.amazon.device.ads.ThreadUtils.ThreadPoolScheduler;

abstract class StartUpWaiter implements ConfigurationListener, SettingsListener {
    static final int CALLBACK_ON_MAIN_THREAD = 0;
    static final int DEFAULT = 1;
    private static final SparseArray<RunnableExecutor> executors = new SparseArray();
    private int callbackType = 1;
    private final Configuration configuration;
    private final Settings settings;

    protected abstract void startUpFailed();

    protected abstract void startUpReady();

    static {
        putRunnableExecutor(0, new MainThreadScheduler());
        putRunnableExecutor(1, new ThreadPoolScheduler());
    }

    public StartUpWaiter(Settings settings, Configuration configuration) {
        this.settings = settings;
        this.configuration = configuration;
    }

    public void start() {
        this.settings.listenForSettings(this);
    }

    public void startAndCallbackOnMainThread() {
        this.callbackType = 0;
        start();
    }

    public void settingsLoaded() {
        this.configuration.queueConfigurationListener(this);
    }

    public void onConfigurationReady() {
        onFinished(new Runnable() {
            public void run() {
                StartUpWaiter.this.startUpReady();
            }
        });
    }

    public void onConfigurationFailure() {
        onFinished(new Runnable() {
            public void run() {
                StartUpWaiter.this.startUpFailed();
            }
        });
    }

    private void onFinished(Runnable runnable) {
        getExecutor(this.callbackType).execute(runnable);
    }

    Settings getSettings() {
        return this.settings;
    }

    Configuration getConfiguration() {
        return this.configuration;
    }

    static RunnableExecutor getExecutor(int i) {
        return (RunnableExecutor) executors.get(i, executors.get(1));
    }

    static void putRunnableExecutor(int i, RunnableExecutor runnableExecutor) {
        if (runnableExecutor == null) {
            executors.remove(i);
        } else {
            executors.put(i, runnableExecutor);
        }
    }
}
