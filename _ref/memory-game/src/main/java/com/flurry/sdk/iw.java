package com.flurry.sdk;

import io.fabric.sdk.android.services.network.HttpRequest;

public enum iw {
    GET(HttpRequest.METHOD_GET, 0),
    PUT(HttpRequest.METHOD_PUT, 1),
    POST(HttpRequest.METHOD_POST, 2);
    
    String d;
    int e;

    private iw(String str, int i) {
        this.d = str;
        this.e = i;
    }

    public static iw a(int i) {
        switch (i) {
            case 0:
                return GET;
            case 1:
                return PUT;
            case 2:
                return POST;
            default:
                return null;
        }
    }
}
