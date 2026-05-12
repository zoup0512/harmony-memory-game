package com.amazonaws.mobileconnectors.s3.transfermanager;

import com.amazonaws.mobileconnectors.s3.transfermanager.internal.MultipleFileTransfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferStateChangeListener;
import java.util.concurrent.CountDownLatch;

/* compiled from: MultipleFileTransferChangeStateListener */
final class MultipleFileTransferStateChangeListener implements TransferStateChangeListener {
    private final CountDownLatch latch;
    private final MultipleFileTransfer<?> multipleFileTransfer;

    public MultipleFileTransferStateChangeListener(CountDownLatch latch, MultipleFileTransfer<?> multipleFileTransfer) {
        this.latch = latch;
        this.multipleFileTransfer = multipleFileTransfer;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void transferStateChanged(com.amazonaws.mobileconnectors.s3.transfermanager.Transfer r5, com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState r6) {
        /*
        r4 = this;
        r1 = r4.latch;	 Catch:{ InterruptedException -> 0x001a }
        r1.await();	 Catch:{ InterruptedException -> 0x001a }
        r2 = r4.multipleFileTransfer;
        monitor-enter(r2);
        r1 = r4.multipleFileTransfer;	 Catch:{ all -> 0x002e }
        r1 = r1.getState();	 Catch:{ all -> 0x002e }
        if (r1 == r6) goto L_0x0018;
    L_0x0010:
        r1 = r4.multipleFileTransfer;	 Catch:{ all -> 0x002e }
        r1 = r1.isDone();	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x0023;
    L_0x0018:
        monitor-exit(r2);	 Catch:{ all -> 0x002e }
    L_0x0019:
        return;
    L_0x001a:
        r0 = move-exception;
        r1 = new com.amazonaws.AmazonClientException;
        r2 = "Couldn't wait for all downloads to be queued";
        r1.<init>(r2);
        throw r1;
    L_0x0023:
        r1 = com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState.InProgress;	 Catch:{ all -> 0x002e }
        if (r6 != r1) goto L_0x0031;
    L_0x0027:
        r1 = r4.multipleFileTransfer;	 Catch:{ all -> 0x002e }
        r1.setState(r6);	 Catch:{ all -> 0x002e }
    L_0x002c:
        monitor-exit(r2);	 Catch:{ all -> 0x002e }
        goto L_0x0019;
    L_0x002e:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x002e }
        throw r1;
    L_0x0031:
        r1 = r4.multipleFileTransfer;	 Catch:{ all -> 0x002e }
        r1 = r1.getMonitor();	 Catch:{ all -> 0x002e }
        r1 = r1.isDone();	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x0043;
    L_0x003d:
        r1 = r4.multipleFileTransfer;	 Catch:{ all -> 0x002e }
        r1.collateFinalState();	 Catch:{ all -> 0x002e }
        goto L_0x002c;
    L_0x0043:
        r1 = r4.multipleFileTransfer;	 Catch:{ all -> 0x002e }
        r3 = com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState.InProgress;	 Catch:{ all -> 0x002e }
        r1.setState(r3);	 Catch:{ all -> 0x002e }
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.mobileconnectors.s3.transfermanager.MultipleFileTransferStateChangeListener.transferStateChanged(com.amazonaws.mobileconnectors.s3.transfermanager.Transfer, com.amazonaws.mobileconnectors.s3.transfermanager.Transfer$TransferState):void");
    }
}
