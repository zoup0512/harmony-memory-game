package com.unity3d.ads.cache;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.amazonaws.services.s3.Headers;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.api.Request;
import com.unity3d.ads.device.Device;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.request.IWebRequestProgressListener;
import com.unity3d.ads.request.WebRequest;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CacheThreadHandler extends Handler {
    private boolean _active = false;
    private boolean _canceled = false;
    private WebRequest _currentRequest = null;

    CacheThreadHandler() {
    }

    public void handleMessage(Message message) {
        Bundle data = message.getData();
        String string = data.getString(ShareConstants.FEED_SOURCE_PARAM);
        String string2 = data.getString("target");
        int i = data.getInt("connectTimeout");
        int i2 = data.getInt("readTimeout");
        int i3 = data.getInt("progressInterval");
        switch (message.what) {
            case 1:
                downloadFile(string, string2, new File(string2).length(), i, i2, i3);
                return;
            default:
                return;
        }
    }

    public void setCancelStatus(boolean z) {
        this._canceled = z;
        if (z && this._currentRequest != null) {
            this._active = false;
            this._currentRequest.cancel();
        }
    }

    public boolean isActive() {
        return this._active;
    }

    private void downloadFile(String str, String str2, long j, int i, int i2, int i3) {
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream;
        if (!this._canceled && str != null && str2 != null) {
            if (j > 0) {
                DeviceLog.debug("Unity Ads cache: resuming download " + str + " to " + str2 + " at " + j + " bytes");
            } else {
                DeviceLog.debug("Unity Ads cache: start downloading " + str + " to " + str2);
            }
            if (Device.isActiveNetworkConnected()) {
                this._active = true;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                File file = new File(str2);
                FileOutputStream fileOutputStream2 = null;
                try {
                    FileOutputStream fileOutputStream3 = new FileOutputStream(file, j > 0);
                    try {
                        this._currentRequest = getWebRequest(str, j, i, i2);
                        final long j2 = j;
                        final int i4 = i3;
                        this._currentRequest.setProgressListener(new IWebRequestProgressListener() {
                            private long lastProgressEventTime = System.currentTimeMillis();

                            public void onRequestStart(String str, long j, int i, Map<String, List<String>> map) {
                                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_STARTED, str, Long.valueOf(j2), Long.valueOf(j), Integer.valueOf(i), Request.getResponseHeadersMap(map));
                            }

                            public void onRequestProgress(String str, long j, long j2) {
                                if (i4 > 0 && System.currentTimeMillis() - this.lastProgressEventTime > ((long) i4)) {
                                    this.lastProgressEventTime = System.currentTimeMillis();
                                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_PROGRESS, str, Long.valueOf(j), Long.valueOf(j2));
                                }
                            }
                        });
                        long makeStreamRequest = this._currentRequest.makeStreamRequest(fileOutputStream3);
                        this._active = false;
                        postProcessDownload(elapsedRealtime, str, file, makeStreamRequest, this._currentRequest.getContentLength(), this._currentRequest.isCanceled(), this._currentRequest.getResponseCode(), this._currentRequest.getResponseHeaders());
                        this._currentRequest = null;
                        if (fileOutputStream3 != null) {
                            try {
                                fileOutputStream3.close();
                                return;
                            } catch (Exception e2) {
                                DeviceLog.exception("Error closing stream", e2);
                                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e2.getMessage());
                                return;
                            }
                        }
                        return;
                    } catch (FileNotFoundException e3) {
                        e2 = e3;
                        fileOutputStream2 = fileOutputStream3;
                        try {
                            DeviceLog.exception("Couldn't create target file", e2);
                            this._active = false;
                            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e2.getMessage());
                            this._currentRequest = null;
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                    return;
                                } catch (Exception e22) {
                                    DeviceLog.exception("Error closing stream", e22);
                                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e22.getMessage());
                                    return;
                                }
                            }
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = fileOutputStream2;
                            this._currentRequest = null;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e4) {
                                    DeviceLog.exception("Error closing stream", e4);
                                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e4.getMessage());
                                }
                            }
                            throw th;
                        }
                    } catch (MalformedURLException e5) {
                        e22 = e5;
                        try {
                            DeviceLog.exception("Malformed URL", e22);
                            this._active = false;
                            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.MALFORMED_URL, str, e22.getMessage());
                            this._currentRequest = null;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                    return;
                                } catch (Exception e222) {
                                    DeviceLog.exception("Error closing stream", e222);
                                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e222.getMessage());
                                    return;
                                }
                            }
                            return;
                        } catch (Throwable th3) {
                            th = th3;
                            this._currentRequest = null;
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (IOException e6) {
                        e222 = e6;
                        DeviceLog.exception("Couldn't request stream", e222);
                        this._active = false;
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e222.getMessage());
                        this._currentRequest = null;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                return;
                            } catch (Exception e2222) {
                                DeviceLog.exception("Error closing stream", e2222);
                                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e2222.getMessage());
                                return;
                            }
                        }
                        return;
                    }
                } catch (FileNotFoundException e7) {
                    e2222 = e7;
                    DeviceLog.exception("Couldn't create target file", e2222);
                    this._active = false;
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e2222.getMessage());
                    this._currentRequest = null;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                        return;
                    }
                    return;
                } catch (MalformedURLException e8) {
                    e2222 = e8;
                    fileOutputStream = null;
                    DeviceLog.exception("Malformed URL", e2222);
                    this._active = false;
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.MALFORMED_URL, str, e2222.getMessage());
                    this._currentRequest = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                        return;
                    }
                    return;
                } catch (IOException e9) {
                    e2222 = e9;
                    fileOutputStream = null;
                    DeviceLog.exception("Couldn't request stream", e2222);
                    this._active = false;
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e2222.getMessage());
                    this._currentRequest = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                        return;
                    }
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    this._currentRequest = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            }
            DeviceLog.debug("Unity Ads cache: download cancelled, no internet connection available");
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.NO_INTERNET, str);
        }
    }

    private void postProcessDownload(long j, String str, File file, long j2, long j3, boolean z, int i, Map<String, List<String>> map) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        if (!file.setReadable(true, false)) {
            DeviceLog.debug("Unity Ads cache: could not set file readable!");
        }
        if (z) {
            DeviceLog.debug("Unity Ads cache: downloading of " + str + " stopped");
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_STOPPED, str, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(elapsedRealtime), Integer.valueOf(i), Request.getResponseHeadersMap(map));
            return;
        }
        DeviceLog.debug("Unity Ads cache: File " + file.getName() + " of " + j2 + " bytes downloaded in " + elapsedRealtime + "ms");
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_END, str, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(elapsedRealtime), Integer.valueOf(i), Request.getResponseHeadersMap(map));
    }

    private WebRequest getWebRequest(String str, long j, int i, int i2) {
        Map hashMap = new HashMap();
        if (j > 0) {
            hashMap.put(Headers.RANGE, new ArrayList(Arrays.asList(new String[]{"bytes=" + j + "-"})));
        }
        return new WebRequest(str, HttpRequest.METHOD_GET, hashMap, i, i2);
    }
}
