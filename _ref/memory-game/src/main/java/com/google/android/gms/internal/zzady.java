package com.google.android.gms.internal;

import com.amazonaws.services.s3.internal.Constants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class zzady implements zzadz {
    private HttpURLConnection aCg;

    zzady() {
    }

    private InputStream zzd(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            return httpURLConnection.getInputStream();
        }
        String str = "Bad response: " + responseCode;
        if (responseCode == Constants.NO_SUCH_BUCKET_STATUS_CODE) {
            throw new FileNotFoundException(str);
        }
        throw new IOException(str);
    }

    private void zze(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public void close() {
        zze(this.aCg);
    }

    public InputStream zzqj(String str) throws IOException {
        this.aCg = zzqk(str);
        return zzd(this.aCg);
    }

    HttpURLConnection zzqk(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        return httpURLConnection;
    }
}
