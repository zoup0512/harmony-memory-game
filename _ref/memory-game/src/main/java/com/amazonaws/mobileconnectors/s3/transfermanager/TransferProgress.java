package com.amazonaws.mobileconnectors.s3.transfermanager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
public final class TransferProgress {
    private static final Log log = LogFactory.getLog(TransferProgress.class);
    protected volatile long bytesTransferred = 0;
    protected volatile long totalBytesToTransfer = -1;

    @Deprecated
    public long getBytesTransfered() {
        return getBytesTransferred();
    }

    public long getBytesTransferred() {
        return this.bytesTransferred;
    }

    public long getTotalBytesToTransfer() {
        return this.totalBytesToTransfer;
    }

    @Deprecated
    public synchronized double getPercentTransfered() {
        return getPercentTransferred();
    }

    public synchronized double getPercentTransferred() {
        double d;
        d = getBytesTransferred() < 0 ? 0.0d : this.totalBytesToTransfer < 0 ? -1.0d : (((double) this.bytesTransferred) / ((double) this.totalBytesToTransfer)) * 100.0d;
        return d;
    }

    public synchronized void updateProgress(long bytes) {
        this.bytesTransferred += bytes;
        if (this.totalBytesToTransfer > -1 && this.bytesTransferred > this.totalBytesToTransfer) {
            this.bytesTransferred = this.totalBytesToTransfer;
            if (log.isDebugEnabled()) {
                log.debug("Number of bytes transfered is more than the actual total bytes to transfer. Total number of bytes to Transfer : " + this.totalBytesToTransfer + ". Bytes Transferred : " + (this.bytesTransferred + bytes));
            }
        }
    }

    public void setTotalBytesToTransfer(long totalBytesToTransfer) {
        this.totalBytesToTransfer = totalBytesToTransfer;
    }
}
