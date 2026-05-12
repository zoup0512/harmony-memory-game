package com.chartboost.sdk.impl;

import java.io.IOException;

public class ap extends RuntimeException {
    public ap(String str) {
        super(str);
    }

    public ap(IOException iOException) {
        super(iOException);
    }
}
