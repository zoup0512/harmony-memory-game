package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MultipleFileTransferMonitor implements TransferMonitor {
    private final Future<?> future = new Future<Object>() {
        public boolean cancel(boolean mayInterruptIfRunning) {
            return true;
        }

        public Object get() throws InterruptedException, ExecutionException {
            Object result = null;
            for (AbstractTransfer download : MultipleFileTransferMonitor.this.subTransfers) {
                result = download.getMonitor().getFuture().get();
            }
            return result;
        }

        public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            Object result = null;
            for (AbstractTransfer subTransfer : MultipleFileTransferMonitor.this.subTransfers) {
                result = subTransfer.getMonitor().getFuture().get(timeout, unit);
            }
            return result;
        }

        public boolean isCancelled() {
            return MultipleFileTransferMonitor.this.transfer.getState() == TransferState.Canceled;
        }

        public boolean isDone() {
            return MultipleFileTransferMonitor.this.isDone();
        }
    };
    private final Collection<? extends AbstractTransfer> subTransfers;
    private final AbstractTransfer transfer;

    public MultipleFileTransferMonitor(AbstractTransfer transfer, Collection<? extends AbstractTransfer> subTransfers) {
        this.subTransfers = subTransfers;
        this.transfer = transfer;
    }

    public Future<?> getFuture() {
        return this.future;
    }

    public synchronized boolean isDone() {
        boolean z;
        for (Transfer subTransfer : this.subTransfers) {
            if (!subTransfer.isDone()) {
                z = false;
                break;
            }
        }
        z = true;
        return z;
    }
}
