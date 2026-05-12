package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState;

public interface TransferStateChangeListener {
    void transferStateChanged(Transfer transfer, TransferState transferState);
}
