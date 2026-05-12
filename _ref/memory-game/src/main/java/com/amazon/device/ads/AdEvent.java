package com.amazon.device.ads;

class AdEvent {
    public static final String POSITION_ON_SCREEN = "positionOnScreen";
    private final AdEventType adEventType;
    private String customType;
    private final ParameterMap parameters = new ParameterMap();

    public enum AdEventType {
        EXPANDED,
        CLOSED,
        CLICKED,
        RESIZED,
        OTHER
    }

    public AdEvent(AdEventType adEventType) {
        this.adEventType = adEventType;
    }

    AdEventType getAdEventType() {
        return this.adEventType;
    }

    void setCustomType(String str) {
        this.customType = str;
    }

    public String getCustomType() {
        return this.customType;
    }

    AdEvent setParameter(String str, Object obj) {
        this.parameters.setParameter(str, obj);
        return this;
    }

    public ParameterMap getParameters() {
        return this.parameters;
    }
}
