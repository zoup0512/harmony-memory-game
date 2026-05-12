package com.amazonaws.mobileconnectors.s3.transferutility;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public enum TransferState {
    WAITING,
    IN_PROGRESS,
    PAUSED,
    RESUMED_WAITING,
    COMPLETED,
    CANCELED,
    FAILED,
    WAITING_FOR_NETWORK,
    PART_COMPLETED,
    PENDING_CANCEL,
    PENDING_PAUSE,
    PENDING_NETWORK_DISCONNECT,
    UNKNOWN;
    
    private static final Map<String, TransferState> map = null;

    static {
        map = new HashMap();
        TransferState[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            TransferState state = values[i];
            map.put(state.toString(), state);
            i++;
        }
    }

    public static TransferState getState(String stateAsString) {
        if (map.containsKey(stateAsString)) {
            return (TransferState) map.get(stateAsString);
        }
        Log.e("TransferState", "Unknown state " + stateAsString + " transfer will be have state set to UNKNOWN.");
        return UNKNOWN;
    }
}
