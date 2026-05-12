package com.cube.memorygames.pushes.event;

import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.pushes.model.OnlineMatchUser;

public class MatchFoundEvent extends OnlineEvent {
    public static final String FIELD_QUEUE_ENTRY_ID = "FIELD_QUEUE_ENTRY_ID";
    public static final String FIELD_USER_INFO = "FIELD_USER_INFO";
    private OnlineMatchUser myOnlineUserInfo;
    private OnlineMatchUser onlineUserInfo;
    private String queueEntryId;

    public String getQueueEntryId() {
        return this.queueEntryId;
    }

    public void setQueueEntryId(String queueEntryId) {
        this.queueEntryId = queueEntryId;
    }

    public OnlineMatchUser getOnlineUserInfo() {
        return this.onlineUserInfo;
    }

    public void setOnlineUserInfo(OnlineMatchUser onlineUserInfo) {
        this.onlineUserInfo = onlineUserInfo;
    }

    public OnlineMatchUser getMyOnlineUserInfo() {
        return this.myOnlineUserInfo;
    }

    public void setMyOnlineUserInfo(OnlineMatchUser myOnlineUserInfo) {
        this.myOnlineUserInfo = myOnlineUserInfo;
    }

    public static OnlineEvent create(String s) {
        if (s == null) {
            return null;
        }
        return (MatchFoundEvent) MemoryApplicationModel.getInstance().getGsonParser().fromJson(s, MatchFoundEvent.class);
    }
}
