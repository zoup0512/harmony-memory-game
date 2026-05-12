package com.unity3d.ads.api;

import com.unity3d.ads.cache.CacheError;
import com.unity3d.ads.cache.CacheThread;
import com.unity3d.ads.device.Device;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.properties.SdkProperties;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;

public class Cache {
    @WebViewExposed
    public static void download(String str, String str2, WebViewCallback webViewCallback) {
        if (CacheThread.isActive()) {
            webViewCallback.error(CacheError.FILE_ALREADY_CACHING, new Object[0]);
        } else if (Device.isActiveNetworkConnected()) {
            CacheThread.download(str, fileIdToFilename(str2));
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(CacheError.NO_INTERNET, new Object[0]);
        }
    }

    @WebViewExposed
    public static void stop(WebViewCallback webViewCallback) {
        if (CacheThread.isActive()) {
            CacheThread.cancel();
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(CacheError.NOT_CACHING, new Object[0]);
    }

    @WebViewExposed
    public static void isCaching(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Boolean.valueOf(CacheThread.isActive())});
    }

    @WebViewExposed
    public static void getFiles(WebViewCallback webViewCallback) {
        File cacheDirectory = SdkProperties.getCacheDirectory();
        if (cacheDirectory != null) {
            DeviceLog.debug("Unity Ads cache: checking app directory for Unity Ads cached files");
            File[] listFiles = cacheDirectory.listFiles(new 1());
            if (listFiles == null || listFiles.length == 0) {
                webViewCallback.invoke(new Object[]{new JSONArray()});
            }
            try {
                JSONArray jSONArray = new JSONArray();
                for (File file : listFiles) {
                    String substring = file.getName().substring(SdkProperties.getCacheFilePrefix().length());
                    DeviceLog.debug("Unity Ads cache: found " + substring + ", " + file.length() + " bytes");
                    jSONArray.put(getFileJson(substring));
                }
                webViewCallback.invoke(new Object[]{jSONArray});
            } catch (Exception e) {
                DeviceLog.exception("Error creating JSON", e);
                webViewCallback.error(CacheError.JSON_ERROR, new Object[0]);
            }
        }
    }

    @WebViewExposed
    public static void getFileInfo(String str, WebViewCallback webViewCallback) {
        try {
            webViewCallback.invoke(new Object[]{getFileJson(str)});
        } catch (Exception e) {
            DeviceLog.exception("Error creating JSON", e);
            webViewCallback.error(CacheError.JSON_ERROR, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getFilePath(String str, WebViewCallback webViewCallback) {
        if (new File(fileIdToFilename(str)).exists()) {
            webViewCallback.invoke(new Object[]{fileIdToFilename(str)});
            return;
        }
        webViewCallback.error(CacheError.FILE_NOT_FOUND, new Object[0]);
    }

    @WebViewExposed
    public static void deleteFile(String str, WebViewCallback webViewCallback) {
        if (new File(fileIdToFilename(str)).delete()) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(CacheError.FILE_IO_ERROR, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getHash(String str, WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Utilities.Sha256(str)});
    }

    @WebViewExposed
    public static void setTimeouts(Integer num, Integer num2, WebViewCallback webViewCallback) {
        CacheThread.setConnectTimeout(num.intValue());
        CacheThread.setReadTimeout(num2.intValue());
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getTimeouts(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(CacheThread.getConnectTimeout()), Integer.valueOf(CacheThread.getReadTimeout())});
    }

    @WebViewExposed
    public static void setProgressInterval(Integer num, WebViewCallback webViewCallback) {
        CacheThread.setProgressInterval(num.intValue());
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getProgressInterval(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(CacheThread.getProgressInterval())});
    }

    @WebViewExposed
    public static void getFreeSpace(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Long.valueOf(Device.getFreeSpace(SdkProperties.getCacheDirectory()))});
    }

    @WebViewExposed
    public static void getTotalSpace(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Long.valueOf(Device.getTotalSpace(SdkProperties.getCacheDirectory()))});
    }

    private static String fileIdToFilename(String str) {
        return SdkProperties.getCacheDirectory() + "/" + SdkProperties.getCacheFilePrefix() + str;
    }

    private static JSONObject getFileJson(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", str);
        File file = new File(fileIdToFilename(str));
        if (file.exists()) {
            jSONObject.put("found", true);
            jSONObject.put("size", file.length());
            jSONObject.put("mtime", file.lastModified());
        } else {
            jSONObject.put("found", false);
        }
        return jSONObject;
    }
}
