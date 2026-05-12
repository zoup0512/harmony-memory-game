package com.amazonaws;

import com.mopub.common.Constants;

public enum Protocol {
    HTTP(Constants.HTTP),
    HTTPS(Constants.HTTPS);
    
    private final String protocol;

    private Protocol(String protocol) {
        this.protocol = protocol;
    }

    public String toString() {
        return this.protocol;
    }
}
