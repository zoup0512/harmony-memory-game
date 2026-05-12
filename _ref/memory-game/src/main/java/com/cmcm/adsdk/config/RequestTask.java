package com.cmcm.adsdk.config;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.cmcm.utils.g;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class RequestTask extends AsyncTask<Void, Void, byte[]> {
    private static final String TAG = "RequestTask";
    private ResultListener mListener;
    private String param;
    private String url;

    public interface ResultListener {
        void result(byte[] bArr);
    }

    public RequestTask(String url, String param, ResultListener listener) {
        this.url = url;
        this.param = param;
        this.mListener = listener;
    }

    protected byte[] doInBackground(Void... params) {
        Object jSONString = getJSONString(this.url, this.param);
        g.a(TAG, "result:" + jSONString);
        return jSONString;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(byte[] result) {
        super.onPostExecute(result);
        if (this.mListener != null) {
            this.mListener.result(result);
        }
    }

    private byte[] getJSONString(String baseUrl, String param) {
        if (!TextUtils.isEmpty(param)) {
            baseUrl = baseUrl + "?" + param;
        }
        g.a(TAG, "Get Url:" + baseUrl);
        try {
            HttpUriRequest httpGet = new HttpGet(baseUrl);
            HttpParams params = httpGet.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 15000);
            HttpConnectionParams.setSoTimeout(params, 15000);
            HttpResponse execute = new DefaultHttpClient().execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return readInputSream(execute.getEntity().getContent());
            }
        } catch (Exception e) {
            g.d(TAG, "get response error..." + e.getMessage());
        }
        return null;
    }

    private byte[] readInputSream(InputStream inStream) throws IOException {
        if (inStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
