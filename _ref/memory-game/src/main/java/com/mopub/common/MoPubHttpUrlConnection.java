package com.mopub.common;

import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;
import com.mopub.network.Networking;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

public abstract class MoPubHttpUrlConnection extends HttpURLConnection {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 10000;

    private MoPubHttpUrlConnection(URL url) {
        super(url);
    }

    public static HttpURLConnection getHttpUrlConnection(@NonNull String str) {
        Preconditions.checkNotNull(str);
        if (isUrlImproperlyEncoded(str)) {
            throw new IllegalArgumentException("URL is improperly encoded: " + str);
        }
        try {
            str = urlEncode(str);
        } catch (Exception e) {
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestProperty("User-Agent", Networking.getCachedUserAgent());
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        return httpURLConnection;
    }

    @NonNull
    public static String urlEncode(@NonNull String str) {
        Preconditions.checkNotNull(str);
        if (isUrlImproperlyEncoded(str)) {
            throw new UnsupportedEncodingException("URL is improperly encoded: " + str);
        }
        URI encodeUrl;
        if (isUrlUnencoded(str)) {
            encodeUrl = encodeUrl(str);
        } else {
            encodeUrl = new URI(str);
        }
        return encodeUrl.toURL().toString();
    }

    static boolean isUrlImproperlyEncoded(@NonNull String str) {
        try {
            URLDecoder.decode(str, "UTF-8");
            return false;
        } catch (UnsupportedEncodingException e) {
            MoPubLog.w("Url is improperly encoded: " + str);
            return true;
        }
    }

    static boolean isUrlUnencoded(@NonNull String str) {
        try {
            URI uri = new URI(str);
            return false;
        } catch (URISyntaxException e) {
            return true;
        }
    }

    @NonNull
    static URI encodeUrl(@NonNull String str) {
        try {
            URL url = new URL(str);
            return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        } catch (Exception e) {
            MoPubLog.w("Failed to encode url: " + str);
            throw e;
        }
    }
}
