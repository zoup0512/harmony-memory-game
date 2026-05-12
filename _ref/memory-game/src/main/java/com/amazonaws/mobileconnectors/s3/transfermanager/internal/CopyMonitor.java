package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListenerCallbackExecutor;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.model.CopyResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.PartETag;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CopyMonitor implements Callable<CopyResult>, TransferMonitor {
    private final CopyObjectRequest copyObjectRequest;
    private final List<Future<PartETag>> futures = new ArrayList();
    private boolean isCopyDone = false;
    private final CopyCallable multipartCopyCallable;
    private Future<CopyResult> nextFuture;
    private int pollInterval = 5000;
    private final ProgressListenerCallbackExecutor progressListenerChainCallbackExecutor;
    private final AmazonS3 s3;
    private final ExecutorService threadPool;
    private ScheduledExecutorService timedThreadPool;
    private final CopyImpl transfer;
    private String uploadId;

    public synchronized Future<CopyResult> getFuture() {
        return this.nextFuture;
    }

    private synchronized void setNextFuture(Future<CopyResult> nextFuture) {
        this.nextFuture = nextFuture;
    }

    public synchronized boolean isDone() {
        return this.isCopyDone;
    }

    private synchronized void markAllDone() {
        this.isCopyDone = true;
    }

    public CopyMonitor(TransferManager manager, CopyImpl transfer, ExecutorService threadPool, CopyCallable multipartCopyCallable, CopyObjectRequest copyObjectRequest, ProgressListenerChain progressListenerChain) {
        this.s3 = manager.getAmazonS3Client();
        this.multipartCopyCallable = multipartCopyCallable;
        this.threadPool = threadPool;
        this.copyObjectRequest = copyObjectRequest;
        this.transfer = transfer;
        this.progressListenerChainCallbackExecutor = ProgressListenerCallbackExecutor.wrapListener(progressListenerChain);
        setNextFuture(threadPool.submit(this));
    }

    public CopyResult call() throws Exception {
        try {
            if (this.uploadId == null) {
                return copy();
            }
            return poll();
        } catch (CancellationException e) {
            this.transfer.setState(TransferState.Canceled);
            fireProgressEvent(16);
            throw new AmazonClientException("Upload canceled");
        } catch (Exception e2) {
            this.transfer.setState(TransferState.Failed);
            fireProgressEvent(8);
            throw e2;
        }
    }

    public void setTimedThreadPool(ScheduledExecutorService timedThreadPool) {
        this.timedThreadPool = timedThreadPool;
    }

    private CopyResult poll() throws InterruptedException {
        for (Future<PartETag> f : this.futures) {
            if (!f.isDone()) {
                reschedule();
                return null;
            }
        }
        for (Future<PartETag> f2 : this.futures) {
            if (f2.isCancelled()) {
                throw new CancellationException();
            }
        }
        return completeMultipartUpload();
    }

    private void fireProgressEvent(int eventType) {
        if (this.progressListenerChainCallbackExecutor != null) {
            ProgressEvent event = new ProgressEvent(0);
            event.setEventCode(eventType);
            this.progressListenerChainCallbackExecutor.progressChanged(event);
        }
    }

    private CopyResult copy() throws Exception, InterruptedException {
        CopyResult result = this.multipartCopyCallable.call();
        if (result != null) {
            copyComplete();
        } else {
            this.uploadId = this.multipartCopyCallable.getMultipartUploadId();
            this.futures.addAll(this.multipartCopyCallable.getFutures());
            reschedule();
        }
        return result;
    }

    private void copyComplete() {
        markAllDone();
        this.transfer.setState(TransferState.Completed);
        if (this.multipartCopyCallable.isMultipartCopy()) {
            fireProgressEvent(4);
        }
    }

    private void reschedule() {
        setNextFuture(this.timedThreadPool.schedule(new Callable<CopyResult>() {
            public CopyResult call() throws Exception {
                CopyMonitor.this.setNextFuture(CopyMonitor.this.threadPool.submit(CopyMonitor.this));
                return null;
            }
        }, (long) this.pollInterval, TimeUnit.MILLISECONDS));
    }

    private CopyResult completeMultipartUpload() {
        CompleteMultipartUploadResult completeMultipartUploadResult = this.s3.completeMultipartUpload(new CompleteMultipartUploadRequest(this.copyObjectRequest.getDestinationBucketName(), this.copyObjectRequest.getDestinationKey(), this.uploadId, collectPartETags()));
        copyComplete();
        CopyResult copyResult = new CopyResult();
        copyResult.setSourceBucketName(this.copyObjectRequest.getSourceBucketName());
        copyResult.setSourceKey(this.copyObjectRequest.getSourceKey());
        copyResult.setDestinationBucketName(completeMultipartUploadResult.getBucketName());
        copyResult.setDestinationKey(completeMultipartUploadResult.getKey());
        copyResult.setETag(completeMultipartUploadResult.getETag());
        copyResult.setVersionId(completeMultipartUploadResult.getVersionId());
        return copyResult;
    }

    private List<PartETag> collectPartETags() {
        List<PartETag> partETags = new ArrayList(this.futures.size());
        for (Future<PartETag> future : this.futures) {
            try {
                partETags.add(future.get());
            } catch (Exception e) {
                throw new AmazonClientException("Unable to copy part: " + e.getCause().getMessage(), e.getCause());
            }
        }
        return partETags;
    }
}
