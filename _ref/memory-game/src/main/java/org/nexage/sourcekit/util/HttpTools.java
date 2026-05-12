package org.nexage.sourcekit.util;

import android.text.TextUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTools {
    private static final String TAG = HttpTools.class.getName();

    public static void httpGetURL(final String str) {
        if (TextUtils.isEmpty(str)) {
            VASTLog.w(TAG, "url is null or empty");
        } else {
            new Thread() {
                public void run() {
                    Exception exception;
                    Throwable th;
                    HttpURLConnection httpURLConnection = null;
                    try {
                        VASTLog.v(HttpTools.TAG, "connection to URL:" + str);
                        URL url = new URL(str);
                        HttpURLConnection.setFollowRedirects(true);
                        HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
                        try {
                            httpURLConnection2.setConnectTimeout(5000);
                            httpURLConnection2.setRequestProperty("Connection", "close");
                            httpURLConnection2.setRequestMethod(HttpRequest.METHOD_GET);
                            VASTLog.v(HttpTools.TAG, "response code:" + httpURLConnection2.getResponseCode() + ", for URL:" + str);
                            if (httpURLConnection2 != null) {
                                try {
                                    httpURLConnection2.disconnect();
                                } catch (Exception e) {
                                }
                            }
                        } catch (Exception e2) {
                            Exception exception2 = e2;
                            httpURLConnection = httpURLConnection2;
                            exception = exception2;
                            try {
                                VASTLog.w(HttpTools.TAG, str + ": " + exception.getMessage() + ":" + exception.toString());
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Exception e3) {
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Exception e4) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            Throwable th4 = th3;
                            httpURLConnection = httpURLConnection2;
                            th = th4;
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    } catch (Exception e5) {
                        exception = e5;
                        VASTLog.w(HttpTools.TAG, str + ": " + exception.getMessage() + ":" + exception.toString());
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    }
                }
            }.start();
        }
    }
}
