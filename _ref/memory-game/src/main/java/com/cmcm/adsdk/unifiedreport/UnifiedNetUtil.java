package com.cmcm.adsdk.unifiedreport;

import com.cmcm.utils.g;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class UnifiedNetUtil {
    public static final String TAG = "NetUtil";

    public static String doGetString(String url, int timeout, int retryTimes) {
        HttpURLConnection httpURLConnection;
        Throwable th;
        Throwable th2;
        String str;
        HttpURLConnection httpURLConnection2 = null;
        if (url == null || timeout <= 0 || retryTimes <= 0) {
            return null;
        }
        BufferedReader bufferedReader;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            try {
                httpURLConnection.setConnectTimeout(timeout);
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                httpURLConnection.setRequestProperty("Charset", "utf-8");
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
                Object obj;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                        obj = httpURLConnection2;
                    } catch (Exception e3) {
                        obj = httpURLConnection2;
                    }
                } else {
                    obj = httpURLConnection2;
                }
            } catch (Exception e4) {
                bufferedReader = null;
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception e5) {
                    }
                }
                if (bufferedReader == null) {
                    try {
                        bufferedReader.close();
                        str = null;
                    } catch (Exception e6) {
                        str = null;
                    }
                } else {
                    str = null;
                }
                if (str == null) {
                    return str;
                }
                return doGetString(url, timeout, retryTimes - 1);
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
            if (bufferedReader == null) {
                str = null;
            } else {
                bufferedReader.close();
                str = null;
            }
            if (str == null) {
                return doGetString(url, timeout, retryTimes - 1);
            }
            return str;
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
        if (str == null) {
            return doGetString(url, timeout, retryTimes - 1);
        }
        return str;
    }

    public static String doPostString(String url, int timeout, int retryTimes, String jsonstr) {
        BufferedWriter bufferedWriter;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter2;
        Throwable th;
        Throwable th2;
        HttpURLConnection httpURLConnection2 = null;
        if (url == null || timeout <= 0 || retryTimes <= 0) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(url).openConnection();
            try {
                httpURLConnection3.setConnectTimeout(timeout);
                httpURLConnection3.setRequestMethod(HttpRequest.METHOD_POST);
                httpURLConnection3.setRequestProperty("Charset", "utf-8");
                httpURLConnection3.setRequestProperty("Content-Type", "application/json");
                httpURLConnection3.setUseCaches(false);
                httpURLConnection3.setDoInput(true);
                httpURLConnection3.setDoOutput(false);
                httpURLConnection3.connect();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection3.getOutputStream()));
            } catch (Exception e) {
                httpURLConnection = httpURLConnection3;
                bufferedReader = null;
                bufferedWriter2 = null;
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception e2) {
                    }
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e3) {
                            if (g.a) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }
                if (bufferedReader != null) {
                    return null;
                }
                try {
                    bufferedReader.close();
                    return null;
                } catch (Exception e4) {
                    return null;
                }
            } catch (Throwable th3) {
                bufferedReader = null;
                httpURLConnection2 = httpURLConnection3;
                th = th3;
                Object obj = null;
                if (httpURLConnection2 != null) {
                    try {
                        httpURLConnection2.disconnect();
                    } catch (Exception e5) {
                    }
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e6) {
                            if (g.a) {
                                e6.printStackTrace();
                            }
                        }
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e7) {
                    }
                }
                throw th;
            }
            try {
                bufferedWriter.write(jsonstr);
                bufferedWriter.flush();
                int responseCode = httpURLConnection3.getResponseCode();
                g.b(TAG, "resCode response = " + responseCode);
                if (responseCode < 200 || responseCode > 207) {
                    bufferedReader = null;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    char[] cArr = new char[1024];
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection3.getInputStream()));
                    while (true) {
                        try {
                            int read = bufferedReader.read(cArr);
                            if (read < 0) {
                                break;
                            } else if (read > 0) {
                                stringBuilder.append(cArr, 0, read);
                            }
                        } catch (Exception e8) {
                            BufferedWriter bufferedWriter3 = bufferedWriter;
                            httpURLConnection = httpURLConnection3;
                            bufferedWriter2 = bufferedWriter3;
                        } catch (Throwable th4) {
                            th2 = th4;
                            httpURLConnection2 = httpURLConnection3;
                            th = th2;
                        }
                    }
                    if (stringBuilder.length() > 0) {
                        httpURLConnection2 = stringBuilder.toString();
                    }
                }
                if (httpURLConnection3 != null) {
                    try {
                        httpURLConnection3.disconnect();
                    } catch (Exception e9) {
                    }
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e32) {
                            if (g.a) {
                                e32.printStackTrace();
                            }
                        }
                    }
                }
                if (bufferedReader == null) {
                    return httpURLConnection2;
                }
                try {
                    bufferedReader.close();
                    return httpURLConnection2;
                } catch (Exception e10) {
                    return httpURLConnection2;
                }
            } catch (Exception e11) {
                bufferedReader = null;
                HttpURLConnection httpURLConnection4 = httpURLConnection3;
                bufferedWriter2 = bufferedWriter;
                httpURLConnection = httpURLConnection4;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                }
                if (bufferedReader != null) {
                    return null;
                }
                bufferedReader.close();
                return null;
            } catch (Throwable th5) {
                th2 = th5;
                bufferedReader = null;
                httpURLConnection2 = httpURLConnection3;
                th = th2;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (Exception e12) {
            bufferedWriter2 = null;
            httpURLConnection = null;
            bufferedReader = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                if (bufferedWriter2 != null) {
                    bufferedWriter2.close();
                }
            }
            if (bufferedReader != null) {
                return null;
            }
            bufferedReader.close();
            return null;
        } catch (Throwable th6) {
            th = th6;
            bufferedWriter = null;
            bufferedReader = null;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }
}
