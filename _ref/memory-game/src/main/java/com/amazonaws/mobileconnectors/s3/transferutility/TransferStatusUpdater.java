package com.amazonaws.mobileconnectors.s3.transferutility;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

class TransferStatusUpdater {
    private static HashSet<TransferState> STATES_NOT_TO_NOTIFY = new HashSet(Arrays.asList(new TransferState[]{TransferState.PART_COMPLETED, TransferState.PENDING_CANCEL, TransferState.PENDING_PAUSE, TransferState.PENDING_NETWORK_DISCONNECT}));
    private static final String TAG = "TransferStatusUpdater";
    private static final int UPDATE_THRESHOLD_MS = 1000;
    static final Map<Integer, List<TransferListener>> listeners = new HashMap();
    private final TransferDBUtil dbUtil;
    private final Map<Integer, Long> lastUpdateTime = new HashMap();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Map<Integer, TransferRecord> transfers = new HashMap();

    private class TransferProgressListener implements ProgressListener {
        private long bytesCurrent;
        private final TransferRecord transfer;

        public TransferProgressListener(TransferRecord transfer) {
            this.transfer = transfer;
        }

        public synchronized void progressChanged(ProgressEvent progressEvent) {
            TransferRecord transferRecord;
            if (progressEvent.getEventCode() == 32) {
                transferRecord = this.transfer;
                transferRecord.bytesCurrent -= this.bytesCurrent;
                this.bytesCurrent = 0;
            } else {
                this.bytesCurrent += progressEvent.getBytesTransferred();
                transferRecord = this.transfer;
                transferRecord.bytesCurrent += progressEvent.getBytesTransferred();
            }
            TransferStatusUpdater.this.updateProgress(this.transfer.id, this.transfer.bytesCurrent, this.transfer.bytesTotal);
        }
    }

    TransferStatusUpdater(TransferDBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    Map<Integer, TransferRecord> getTransfers() {
        return Collections.unmodifiableMap(this.transfers);
    }

    void addTransfer(TransferRecord transfer) {
        this.transfers.put(Integer.valueOf(transfer.id), transfer);
    }

    TransferRecord getTransfer(int id) {
        return (TransferRecord) this.transfers.get(Integer.valueOf(id));
    }

    void removeTransfer(int id) {
        this.transfers.remove(Integer.valueOf(id));
        listeners.remove(Integer.valueOf(id));
        this.lastUpdateTime.remove(Integer.valueOf(id));
    }

    void updateState(final int id, final TransferState newState) {
        boolean shouldNotNotify = STATES_NOT_TO_NOTIFY.contains(newState);
        TransferRecord transfer = (TransferRecord) this.transfers.get(Integer.valueOf(id));
        if (transfer != null) {
            shouldNotNotify |= newState.equals(transfer.state);
            transfer.state = newState;
            if (this.dbUtil.updateTransferRecord(transfer) == 0) {
                Log.w(TAG, "Failed to update the status of transfer " + id);
            }
        } else if (this.dbUtil.updateState(id, newState) == 0) {
            Log.w(TAG, "Failed to update the status of transfer " + id);
        }
        if (!shouldNotNotify) {
            final List<TransferListener> list = (List) listeners.get(Integer.valueOf(id));
            if (list != null && !list.isEmpty()) {
                this.mainHandler.post(new Runnable() {
                    public void run() {
                        for (TransferListener l : list) {
                            l.onStateChanged(id, newState);
                        }
                        if (TransferState.COMPLETED.equals(newState) || TransferState.FAILED.equals(newState) || TransferState.CANCELED.equals(newState)) {
                            list.clear();
                        }
                    }
                });
            }
        }
    }

    void updateProgress(int id, long bytesCurrent, long bytesTotal) {
        TransferRecord transfer = (TransferRecord) this.transfers.get(Integer.valueOf(id));
        if (transfer != null) {
            transfer.bytesCurrent = bytesCurrent;
            transfer.bytesTotal = bytesTotal;
        }
        final List<TransferListener> list = (List) listeners.get(Integer.valueOf(id));
        if (list != null && !list.isEmpty()) {
            long timeInMillis = System.currentTimeMillis();
            if (!this.lastUpdateTime.containsKey(Integer.valueOf(id)) || timeInMillis - ((Long) this.lastUpdateTime.get(Integer.valueOf(id))).longValue() > 1000 || bytesCurrent == bytesTotal) {
                this.lastUpdateTime.put(Integer.valueOf(id), Long.valueOf(timeInMillis));
                final int i = id;
                final long j = bytesCurrent;
                final long j2 = bytesTotal;
                this.mainHandler.post(new Runnable() {
                    public void run() {
                        for (TransferListener l : list) {
                            l.onProgressChanged(i, j, j2);
                        }
                    }
                });
            }
        }
    }

    void throwError(final int id, final Exception e) {
        final List<TransferListener> list = (List) listeners.get(Integer.valueOf(id));
        if (list != null && !list.isEmpty()) {
            this.mainHandler.post(new Runnable() {
                public void run() {
                    for (TransferListener l : list) {
                        l.onError(id, e);
                    }
                }
            });
        }
    }

    void clear() {
        listeners.clear();
        this.transfers.clear();
        this.lastUpdateTime.clear();
    }

    static void registerListener(int id, TransferListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener can't be null");
        }
        synchronized (listeners) {
            List<TransferListener> list = (List) listeners.get(Integer.valueOf(id));
            if (list == null) {
                list = new CopyOnWriteArrayList();
                list.add(listener);
                listeners.put(Integer.valueOf(id), list);
            } else if (!list.contains(listener)) {
                list.add(listener);
            }
        }
    }

    static void unregisterListener(int id, TransferListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener can't be null");
        }
        List<TransferListener> list = (List) listeners.get(Integer.valueOf(id));
        if (list != null && !list.isEmpty()) {
            list.remove(listener);
        }
    }

    ProgressListener newProgressListener(int id) {
        TransferRecord transfer = getTransfer(id);
        if (transfer != null) {
            return new TransferProgressListener(transfer);
        }
        throw new IllegalArgumentException("transfer " + id + " doesn't exist");
    }
}
