package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.mobileconnectors.s3.transfermanager.Download;
import com.amazonaws.mobileconnectors.s3.transfermanager.PersistableDownload;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferProgress;
import com.amazonaws.mobileconnectors.s3.transfermanager.exception.PauseException;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import java.io.File;
import java.io.IOException;

public class DownloadImpl extends AbstractTransfer implements Download {
    private final PersistableDownload persistableDownload;
    S3Object s3Object;

    public DownloadImpl(String description, TransferProgress transferProgress, ProgressListenerChain progressListenerChain, S3Object s3Object, TransferStateChangeListener listener, GetObjectRequest getObjectRequest, File file) {
        super(description, transferProgress, progressListenerChain, listener);
        this.s3Object = s3Object;
        this.persistableDownload = captureDownloadState(getObjectRequest, file);
        S3ProgressPublisher.publishTransferPersistable(progressListenerChain, this.persistableDownload);
    }

    public ObjectMetadata getObjectMetadata() {
        return this.s3Object.getObjectMetadata();
    }

    public String getBucketName() {
        return this.s3Object.getBucketName();
    }

    public String getKey() {
        return this.s3Object.getKey();
    }

    public synchronized void abort() throws IOException {
        this.monitor.getFuture().cancel(true);
        if (this.s3Object != null) {
            this.s3Object.getObjectContent().abort();
        }
        setState(TransferState.Canceled);
    }

    public synchronized void abortWithoutNotifyingStateChangeListener() throws IOException {
        this.monitor.getFuture().cancel(true);
        synchronized (this) {
            this.state = TransferState.Canceled;
        }
    }

    public synchronized void setS3Object(S3Object s3Object) {
        this.s3Object = s3Object;
    }

    public void setState(TransferState state) {
        super.setState(state);
        if (state == TransferState.Completed) {
            fireProgressEvent(4);
        }
    }

    private PersistableDownload captureDownloadState(GetObjectRequest getObjectRequest, File file) {
        if (getObjectRequest.getSSECustomerKey() == null) {
            return new PersistableDownload(getObjectRequest.getBucketName(), getObjectRequest.getKey(), getObjectRequest.getVersionId(), getObjectRequest.getRange(), getObjectRequest.getResponseHeaders(), getObjectRequest.isRequesterPays(), file.getAbsolutePath());
        }
        return null;
    }

    public PersistableDownload pause() throws PauseException {
        TransferState currentState = getState();
        this.monitor.getFuture().cancel(true);
        if (this.persistableDownload != null) {
            return this.persistableDownload;
        }
        throw new PauseException(TransferManagerUtils.determinePauseStatus(currentState, true));
    }
}
