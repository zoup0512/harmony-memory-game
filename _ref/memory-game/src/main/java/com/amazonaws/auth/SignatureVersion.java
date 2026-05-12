package com.amazonaws.auth;

import com.facebook.appevents.AppEventsConstants;

public enum SignatureVersion {
    V1(AppEventsConstants.EVENT_PARAM_VALUE_YES),
    V2("2");
    
    private String value;

    private SignatureVersion(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
