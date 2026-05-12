package com.my.target.core.providers;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: AbstractFPDataProvider */
public abstract class a {
    private Map<String, String> map = new HashMap();

    protected Map<String, String> getMap() {
        return this.map;
    }

    protected boolean addParam(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str2 == null) {
            return removeParam(str);
        }
        this.map.put(str, str2);
        return true;
    }

    protected boolean removeParam(String str) {
        if (!this.map.containsKey(str)) {
            return false;
        }
        this.map.remove(str);
        return true;
    }

    protected void removeAll() {
        this.map.clear();
    }

    protected String getParam(String str) {
        return (String) this.map.get(str);
    }

    public synchronized Map<String, String> getData() {
        Map hashMap;
        hashMap = new HashMap();
        hashMap.putAll(this.map);
        return hashMap;
    }

    public synchronized void putDataTo(Map<String, String> map) {
        map.putAll(this.map);
    }
}
