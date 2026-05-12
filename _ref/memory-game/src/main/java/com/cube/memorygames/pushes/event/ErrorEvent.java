package com.cube.memorygames.pushes.event;

import com.cube.memorygames.MemoryApplicationModel;
import java.util.HashMap;

public class ErrorEvent extends OnlineEvent {
    public static final String FIELD_ERROR_INFO = "FIELD_ERROR_INFO";
    private String info;

    private ErrorEvent(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    public static OnlineEvent create(String s) {
        if (s == null) {
            return null;
        }
        return new ErrorEvent((String) ((HashMap) MemoryApplicationModel.getInstance().getGsonParser().fromJson(s, HashMap.class)).get(FIELD_ERROR_INFO));
    }
}
