package com.mopub.volley.toolbox;

public interface Authenticator {
    String getAuthToken();

    void invalidateAuthToken(String str);
}
