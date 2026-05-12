package com.unity3d.ads.api;

import com.unity3d.ads.device.StorageError;
import com.unity3d.ads.device.StorageManager;
import com.unity3d.ads.device.StorageManager.StorageType;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;
import org.json.JSONArray;
import org.json.JSONObject;

public class Storage {
    @WebViewExposed
    public static void set(String str, String str2, Integer num, WebViewCallback webViewCallback) {
        set(str, str2, (Object) num, webViewCallback);
    }

    @WebViewExposed
    public static void set(String str, String str2, Boolean bool, WebViewCallback webViewCallback) {
        set(str, str2, (Object) bool, webViewCallback);
    }

    @WebViewExposed
    public static void set(String str, String str2, Long l, WebViewCallback webViewCallback) {
        set(str, str2, (Object) l, webViewCallback);
    }

    @WebViewExposed
    public static void set(String str, String str2, Double d, WebViewCallback webViewCallback) {
        set(str, str2, (Object) d, webViewCallback);
    }

    @WebViewExposed
    public static void set(String str, String str2, String str3, WebViewCallback webViewCallback) {
        set(str, str2, (Object) str3, webViewCallback);
    }

    @WebViewExposed
    public static void set(String str, String str2, JSONObject jSONObject, WebViewCallback webViewCallback) {
        set(str, str2, (Object) jSONObject, webViewCallback);
    }

    @WebViewExposed
    public static void set(String str, String str2, JSONArray jSONArray, WebViewCallback webViewCallback) {
        set(str, str2, (Object) jSONArray, webViewCallback);
    }

    private static void set(String str, String str2, Object obj, WebViewCallback webViewCallback) {
        com.unity3d.ads.device.Storage storage = getStorage(str);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, new Object[]{str, str2, obj});
        } else if (storage.set(str2, obj)) {
            webViewCallback.invoke(new Object[]{str2, obj});
        } else {
            webViewCallback.error(StorageError.COULDNT_SET_VALUE, new Object[]{str2, obj});
        }
    }

    @WebViewExposed
    public static void get(String str, String str2, WebViewCallback webViewCallback) {
        com.unity3d.ads.device.Storage storage = getStorage(str);
        if (storage != null) {
            if (storage.get(str2) == null) {
                webViewCallback.error(StorageError.COULDNT_GET_VALUE, new Object[]{str2});
                return;
            }
            webViewCallback.invoke(new Object[]{storage.get(str2)});
            return;
        }
        webViewCallback.error(StorageError.COULDNT_GET_STORAGE, new Object[]{str, str2});
    }

    @WebViewExposed
    public static void getKeys(String str, String str2, Boolean bool, WebViewCallback webViewCallback) {
        com.unity3d.ads.device.Storage storage = getStorage(str);
        if (storage != null) {
            if (storage.getKeys(str2, bool.booleanValue()) != null) {
                webViewCallback.invoke(new Object[]{new JSONArray(storage.getKeys(str2, bool.booleanValue()))});
                return;
            }
            webViewCallback.invoke(new Object[]{new JSONArray()});
            return;
        }
        webViewCallback.error(StorageError.COULDNT_GET_STORAGE, new Object[]{str, str2});
    }

    @WebViewExposed
    public static void read(String str, WebViewCallback webViewCallback) {
        com.unity3d.ads.device.Storage storage = getStorage(str);
        if (storage != null) {
            storage.readStorage();
            webViewCallback.invoke(new Object[]{str});
            return;
        }
        webViewCallback.error(StorageError.COULDNT_GET_STORAGE, new Object[]{str});
    }

    @WebViewExposed
    public static void write(String str, WebViewCallback webViewCallback) {
        com.unity3d.ads.device.Storage storage = getStorage(str);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, new Object[]{str});
        } else if (storage.writeStorage()) {
            webViewCallback.invoke(new Object[]{str});
        } else {
            webViewCallback.error(StorageError.COULDNT_WRITE_STORAGE_TO_CACHE, new Object[]{str});
        }
    }

    @WebViewExposed
    public static void clear(String str, WebViewCallback webViewCallback) {
        com.unity3d.ads.device.Storage storage = getStorage(str);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, new Object[]{str});
        } else if (storage.clearStorage()) {
            webViewCallback.invoke(new Object[]{str});
        } else {
            webViewCallback.error(StorageError.COULDNT_CLEAR_STORAGE, new Object[]{str});
        }
    }

    @WebViewExposed
    public static void delete(String str, String str2, WebViewCallback webViewCallback) {
        com.unity3d.ads.device.Storage storage = getStorage(str);
        if (storage == null) {
            webViewCallback.error(StorageError.COULDNT_GET_STORAGE, new Object[]{str});
        } else if (storage.delete(str2)) {
            webViewCallback.invoke(new Object[]{str});
        } else {
            webViewCallback.error(StorageError.COULDNT_DELETE_VALUE, new Object[]{str});
        }
    }

    private static com.unity3d.ads.device.Storage getStorage(String str) {
        return StorageManager.getStorage(StorageType.valueOf(str));
    }
}
