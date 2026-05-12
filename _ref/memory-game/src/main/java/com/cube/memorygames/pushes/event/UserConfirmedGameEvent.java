package com.cube.memorygames.pushes.event;

import com.cube.memorygames.MemoryApplicationModel;

public class UserConfirmedGameEvent extends OnlineEvent {
    public static final String FIELD_QUEUE_ENTRY_ID = "FIELD_QUEUE_ENTRY_ID";
    private String queueEntryId;

    public String getQueueEntryId() {
        return this.queueEntryId;
    }

    public static OnlineEvent create(String s) {
        if (s == null) {
            return null;
        }
        return (UserConfirmedGameEvent) MemoryApplicationModel.getInstance().getGsonParser().fromJson(s, UserConfirmedGameEvent.class);
    }
}
