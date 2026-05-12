package com.cube.memorygames.api.network.body;

public class BodyOnlineQueueIdUserId {
    public String queueEntryId;
    public String userId;

    public BodyOnlineQueueIdUserId(String queueEntryId, String userId) {
        this.queueEntryId = queueEntryId;
        this.userId = userId;
    }
}
