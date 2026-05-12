package com.amazon.device.ads;

import java.util.HashMap;

class ParameterMap {
    private final HashMap<String, Object> parameters = new HashMap();

    ParameterMap() {
    }

    public void setParameter(String str, Object obj) {
        this.parameters.put(str, obj);
    }

    public Object getParameter(String str) {
        return this.parameters.get(str);
    }

    public String getStringParameter(String str) {
        return (String) this.parameters.get(str);
    }

    public Boolean getBooleanParameter(String str) {
        return (Boolean) this.parameters.get(str);
    }

    public Integer getIntParameter(String str) {
        return (Integer) this.parameters.get(str);
    }

    public Double getDoubleParameter(String str) {
        return (Double) this.parameters.get(str);
    }

    public Long getLongParameter(String str) {
        return (Long) this.parameters.get(str);
    }
}
