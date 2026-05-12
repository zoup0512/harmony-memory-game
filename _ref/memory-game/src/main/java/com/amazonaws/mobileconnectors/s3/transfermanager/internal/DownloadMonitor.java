package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import java.util.concurrent.Future;

public class DownloadMonitor implements TransferMonitor {
    private final DownloadImpl download;
    private final Future<?> future;

    public DownloadMonitor(DownloadImpl download, Future<?> future) {
        this.download = download;
        this.future = future;
    }

    public Future<?> getFuture() {
        return this.future;
    }

    public boolean isDone() {
        return this.download.isDone();
    }
}
