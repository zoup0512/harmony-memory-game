package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferProgress;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class MultipleFileTransfer<T extends Transfer> extends AbstractTransfer {
    private AtomicBoolean subTransferStarted = new AtomicBoolean(false);
    protected final Collection<? extends T> subTransfers;

    MultipleFileTransfer(String description, TransferProgress transferProgress, ProgressListenerChain progressListenerChain, Collection<? extends T> subTransfers) {
        super(description, transferProgress, progressListenerChain);
        this.subTransfers = subTransfers;
    }

    public void collateFinalState() {
        boolean seenCanceled = false;
        for (Transfer download : this.subTransfers) {
            if (download.getState() == TransferState.Failed) {
                setState(TransferState.Failed);
                return;
            } else if (download.getState() == TransferState.Canceled) {
                seenCanceled = true;
            }
        }
        if (seenCanceled) {
            setState(TransferState.Canceled);
        } else {
            setState(TransferState.Completed);
        }
    }

    public void setState(TransferState state) {
        super.setState(state);
        switch (state) {
            case Waiting:
                fireProgressEvent(1);
                return;
            case InProgress:
                if (this.subTransferStarted.compareAndSet(false, true)) {
                    fireProgressEvent(2);
                    return;
                }
                return;
            case Completed:
                fireProgressEvent(4);
                return;
            case Canceled:
                fireProgressEvent(16);
                return;
            case Failed:
                fireProgressEvent(8);
                return;
            default:
                return;
        }
    }
}
