package com.unity3d.ads2.device;

import android.text.TextUtils;
import com.unity3d.ads2.device.StorageManager.StorageType;
import com.unity3d.ads2.log.DeviceLog;
import com.unity3d.ads2.misc.Utilities;
import com.unity3d.ads2.webview.WebViewApp;
import com.unity3d.ads2.webview.WebViewEventCategory;
import com.yalantis.ucrop.util.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class Storage {
    private JSONObject _data;
    private String _targetFileName;
    private StorageType _type;

    public Storage(String str, StorageType storageType) {
        this._targetFileName = str;
        this._type = storageType;
    }

    public StorageType getType() {
        return this._type;
    }

    public synchronized boolean set(String str, Object obj) {
        boolean z;
        if (this._data == null || str == null || str.length() == 0 || obj == null) {
            DeviceLog.error("Storage not properly initialized or incorrect parameters:" + this._data + ", " + str + ", " + obj);
            z = false;
        } else {
            createObjectTree(getParentObjectTreeFor(str));
            if (findObject(getParentObjectTreeFor(str)) instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) findObject(getParentObjectTreeFor(str));
                String[] split = str.split("\\.");
                if (jSONObject != null) {
                    try {
                        jSONObject.put(split[split.length - 1], obj);
                    } catch (Exception e) {
                        DeviceLog.exception("Couldn't set value", e);
                        z = false;
                    }
                }
                z = true;
            } else {
                DeviceLog.debug("Cannot set subvalue to an object that is not JSONObject");
                z = false;
            }
        }
        return z;
    }

    public synchronized Object get(String str) {
        Object obj = null;
        synchronized (this) {
            if (this._data == null) {
                DeviceLog.error("Data is NULL, readStorage probably not called");
            } else {
                String[] split = str.split("\\.");
                if (findObject(getParentObjectTreeFor(str)) instanceof JSONObject) {
                    JSONObject jSONObject = (JSONObject) findObject(getParentObjectTreeFor(str));
                    if (jSONObject != null) {
                        Object obj2;
                        try {
                            if (jSONObject.has(split[split.length - 1])) {
                                obj2 = jSONObject.get(split[split.length - 1]);
                                obj = obj2;
                            }
                        } catch (Exception e) {
                            DeviceLog.exception("Error getting data", e);
                        }
                        obj2 = null;
                        obj = obj2;
                    }
                }
            }
        }
        return obj;
    }

    public synchronized List<String> getKeys(String str, boolean z) {
        List<String> list;
        if (get(str) instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) get(str);
            List<String> arrayList = new ArrayList();
            if (jSONObject != null) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    List keys2;
                    if (z) {
                        keys2 = getKeys(str + FileUtils.HIDDEN_PREFIX + str2, z);
                    } else {
                        keys2 = null;
                    }
                    arrayList.add(str2);
                    if (r1 != null) {
                        for (String str3 : r1) {
                            arrayList.add(str2 + FileUtils.HIDDEN_PREFIX + str3);
                        }
                    }
                }
            }
            list = arrayList;
        } else {
            list = null;
        }
        return list;
    }

    public synchronized boolean delete(String str) {
        boolean z;
        if (this._data == null) {
            DeviceLog.error("Data is NULL, readStorage probably not called");
            z = false;
        } else {
            String[] split = str.split("\\.");
            if (findObject(getParentObjectTreeFor(str)) instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) findObject(getParentObjectTreeFor(str));
                if (!(jSONObject == null || jSONObject.remove(split[split.length - 1]) == null)) {
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public synchronized boolean readStorage() {
        boolean z = false;
        synchronized (this) {
            File file = new File(this._targetFileName);
            if (Utilities.readFile(file) != null) {
                try {
                    this._data = new JSONObject(Utilities.readFile(file));
                    z = true;
                } catch (Exception e) {
                    DeviceLog.exception("Error creating storage JSON", e);
                }
            }
        }
        return z;
    }

    public synchronized boolean initStorage() {
        readStorage();
        if (this._data == null) {
            this._data = new JSONObject();
        }
        return true;
    }

    public synchronized boolean writeStorage() {
        boolean writeFile;
        File file = new File(this._targetFileName);
        if (this._data != null) {
            writeFile = Utilities.writeFile(file, this._data.toString());
        } else {
            writeFile = false;
        }
        return writeFile;
    }

    public synchronized boolean clearStorage() {
        this._data = null;
        return new File(this._targetFileName).delete();
    }

    public synchronized void clearData() {
        this._data = null;
    }

    public synchronized boolean hasData() {
        boolean z;
        if (this._data == null || this._data.length() <= 0) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public synchronized boolean storageFileExists() {
        return new File(this._targetFileName).exists();
    }

    public synchronized void sendEvent(StorageEvent storageEvent, Object... objArr) {
        boolean z = false;
        synchronized (this) {
            if (WebViewApp.getCurrentApp() != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(Arrays.asList(objArr));
                arrayList.add(0, this._type.name());
                z = WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORAGE, storageEvent, arrayList.toArray());
            }
            if (!z) {
                DeviceLog.debug("Couldn't send storage event to WebApp");
            }
        }
    }

    private synchronized Object findObject(String str) {
        Object obj;
        String[] split = str.split("\\.");
        obj = this._data;
        if (str.length() != 0) {
            int i = 0;
            while (i < split.length) {
                if (!obj.has(split[i])) {
                    obj = null;
                    break;
                }
                try {
                    JSONObject jSONObject = obj.getJSONObject(split[i]);
                    i++;
                    JSONObject jSONObject2 = jSONObject;
                } catch (Exception e) {
                    DeviceLog.exception("Couldn't read JSONObject: " + split[i], e);
                    obj = null;
                }
            }
        }
        return obj;
    }

    private synchronized void createObjectTree(String str) {
        Exception e;
        String[] split = str.split("\\.");
        JSONObject jSONObject = this._data;
        if (str.length() != 0) {
            JSONObject jSONObject2 = jSONObject;
            for (int i = 0; i < split.length; i++) {
                if (jSONObject2.has(split[i])) {
                    try {
                        jSONObject2 = jSONObject2.getJSONObject(split[i]);
                    } catch (Exception e2) {
                        DeviceLog.exception("Couldn't get existing JSONObject", e2);
                    }
                } else {
                    try {
                        jSONObject = jSONObject2.put(split[i], new JSONObject());
                        try {
                            jSONObject2 = jSONObject.getJSONObject(split[i]);
                        } catch (Exception e3) {
                            Exception exception = e3;
                            jSONObject2 = jSONObject;
                            e2 = exception;
                            DeviceLog.exception("Couldn't create new JSONObject", e2);
                        }
                    } catch (Exception e4) {
                        e2 = e4;
                        DeviceLog.exception("Couldn't create new JSONObject", e2);
                    }
                }
            }
        }
    }

    private synchronized String getParentObjectTreeFor(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList(Arrays.asList(str.split("\\.")));
        arrayList.remove(arrayList.size() - 1);
        return TextUtils.join(FileUtils.HIDDEN_PREFIX, arrayList.toArray());
    }
}
