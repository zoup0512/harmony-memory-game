package com.cmcm.picks.vastvideo;

import android.os.AsyncTask;
import android.text.TextUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/* compiled from: VastReporHelper */
public class d extends AsyncTask<Void, Void, Void> {
    public List<String> a;

    protected /* synthetic */ Object doInBackground(Object[] x0) {
        return a((Void[]) x0);
    }

    public d(List<String> list) {
        this.a = list;
    }

    protected Void a(Void... voidArr) {
        if (!(this.a == null || this.a.isEmpty())) {
            for (String a : this.a) {
                a(a);
            }
        }
        return null;
    }

    public String a(String str) {
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        Throwable th;
        Throwable th2;
        HttpURLConnection httpURLConnection2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                httpURLConnection.setRequestProperty("Charset", "utf8");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(false);
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode > 207) {
                    bufferedReader = null;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    char[] cArr = new char[1024];
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while (true) {
                        try {
                            int read = bufferedReader.read(cArr);
                            if (read < 0) {
                                break;
                            } else if (read > 0) {
                                stringBuilder.append(cArr, 0, read);
                            }
                        } catch (Exception e) {
                        } catch (Throwable th3) {
                            th = th3;
                            httpURLConnection2 = httpURLConnection;
                            th2 = th;
                        }
                    }
                    if (stringBuilder.length() > 0) {
                        httpURLConnection2 = stringBuilder.toString();
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception e2) {
                    }
                }
                if (bufferedReader == null) {
                    return httpURLConnection2;
                }
                try {
                    bufferedReader.close();
                    return httpURLConnection2;
                } catch (Exception e3) {
                    return httpURLConnection2;
                }
            } catch (Exception e4) {
                bufferedReader = null;
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception e5) {
                    }
                }
                if (bufferedReader != null) {
                    return null;
                }
                try {
                    bufferedReader.close();
                    return null;
                } catch (Exception e6) {
                    return null;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
                httpURLConnection2 = httpURLConnection;
                th2 = th;
                if (httpURLConnection2 != null) {
                    try {
                        httpURLConnection2.disconnect();
                    } catch (Exception e7) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e8) {
                    }
                }
                throw th2;
            }
        } catch (Exception e9) {
            httpURLConnection = null;
            bufferedReader = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                return null;
            }
            bufferedReader.close();
            return null;
        } catch (Throwable th5) {
            th2 = th5;
            bufferedReader = null;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th2;
        }
    }
}
