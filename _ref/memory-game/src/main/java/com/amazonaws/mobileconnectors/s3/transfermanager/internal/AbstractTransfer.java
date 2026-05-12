package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.event.ProgressListenerCallbackExecutor;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferProgress;
import com.amazonaws.services.s3.model.LegacyS3ProgressListener;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public abstract class AbstractTransfer implements Transfer {
    private final String description;
    protected TransferMonitor monitor;
    protected final ProgressListenerChain progressListenerChain;
    protected volatile TransferState state;
    protected final Collection<TransferStateChangeListener> stateChangeListeners;
    private final TransferProgress transferProgress;

    AbstractTransfer(String description, TransferProgress transferProgress, ProgressListenerChain progressListenerChain) {
        this(description, transferProgress, progressListenerChain, null);
    }

    AbstractTransfer(String description, TransferProgress transferProgress, ProgressListenerChain progressListenerChain, TransferStateChangeListener stateChangeListener) {
        this.state = TransferState.Waiting;
        this.stateChangeListeners = new LinkedList();
        this.description = description;
        this.progressListenerChain = progressListenerChain;
        this.transferProgress = transferProgress;
        addStateChangeListener(stateChangeListener);
    }

    public synchronized boolean isDone() {
        boolean z;
        z = this.state == TransferState.Failed || this.state == TransferState.Completed || this.state == TransferState.Canceled;
        return z;
    }

    public void waitForCompletion() throws AmazonClientException, AmazonServiceException, InterruptedException {
        Object obj = null;
        while (true) {
            try {
                if (!this.monitor.isDone() || r2 == null) {
                    obj = this.monitor.getFuture().get();
                } else {
                    return;
                }
            } catch (ExecutionException e) {
                rethrowExecutionException(e);
                return;
            }
        }
    }

    public AmazonClientException waitForException() throws InterruptedException {
        while (!this.monitor.isDone()) {
            try {
                this.monitor.getFuture().get();
            } catch (ExecutionException e) {
                return unwrapExecutionException(e);
            }
        }
        this.monitor.getFuture().get();
        return null;
    }

    public String getDescription() {
        return this.description;
    }

    public synchronized TransferState getState() {
        return this.state;
    }

    public void setState(TransferState state) {
        synchronized (this) {
            this.state = state;
        }
        for (TransferStateChangeListener listener : this.stateChangeListeners) {
            listener.transferStateChanged(this, state);
        }
    }

    public void notifyStateChangeListeners(TransferState state) {
        for (TransferStateChangeListener listener : this.stateChangeListeners) {
            listener.transferStateChanged(this, state);
        }
    }

    public synchronized void addProgressListener(ProgressListener listener) {
        this.progressListenerChain.addProgressListener(listener);
    }

    public synchronized void removeProgressListener(ProgressListener listener) {
        this.progressListenerChain.removeProgressListener(listener);
    }

    @Deprecated
    public synchronized void addProgressListener(com.amazonaws.services.s3.model.ProgressListener listener) {
        this.progressListenerChain.addProgressListener(new LegacyS3ProgressListener(listener));
    }

    @Deprecated
    public synchronized void removeProgressListener(com.amazonaws.services.s3.model.ProgressListener listener) {
        this.progressListenerChain.removeProgressListener(new LegacyS3ProgressListener(listener));
    }

    public synchronized void addStateChangeListener(TransferStateChangeListener listener) {
        if (listener != null) {
            this.stateChangeListeners.add(listener);
        }
    }

    public synchronized void removeStateChangeListener(TransferStateChangeListener listener) {
        if (listener != null) {
            this.stateChangeListeners.remove(listener);
        }
    }

    public TransferProgress getProgress() {
        return this.transferProgress;
    }

    public void setMonitor(TransferMonitor monitor) {
        this.monitor = monitor;
    }

    public TransferMonitor getMonitor() {
        return this.monitor;
    }

    protected void fireProgressEvent(int eventType) {
        ProgressListenerCallbackExecutor.progressChanged(this.progressListenerChain, new ProgressEvent(eventType, 0));
    }

    protected void rethrowExecutionException(ExecutionException e) {
        throw unwrapExecutionException(e);
    }

    protected AmazonClientException unwrapExecutionException(ExecutionException e) {
        Throwable t = e.getCause();
        if (t instanceof AmazonClientException) {
            return (AmazonClientException) t;
        }
        return new AmazonClientException("Unable to complete transfer: " + t.getMessage(), t);
    }
}
