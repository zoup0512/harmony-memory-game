package com.amazon.device.ads;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

class AppEvent {
    private final String eventName;
    private final HashMap<String, String> properties;
    private final long timestamp;

    protected AppEvent(String str) {
        this(str, -1);
    }

    public AppEvent(String str, long j) {
        this.eventName = str;
        this.timestamp = j;
        this.properties = new HashMap();
    }

    public static AppEvent createAppEventWithTimestamp(AppEvent appEvent, long j) {
        return new AppEvent(appEvent.eventName, j);
    }

    public String getEventName() {
        return this.eventName;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public AppEvent setProperty(String str, String str2) {
        this.properties.put(str, str2);
        return this;
    }

    public String getProperty(String str) {
        return (String) this.properties.get(str);
    }

    public Set<Entry<String, String>> getPropertyEntries() {
        return this.properties.entrySet();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(65);
        stringBuilder.append("Application Event {Name: ");
        stringBuilder.append(this.eventName);
        stringBuilder.append(", Timestamp: ");
        stringBuilder.append(this.timestamp);
        for (String str : this.properties.keySet()) {
            stringBuilder.append(", ");
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append((String) this.properties.get(str));
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
