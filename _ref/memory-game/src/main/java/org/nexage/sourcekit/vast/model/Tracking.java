package org.nexage.sourcekit.vast.model;

public class Tracking {
    private TRACKING_EVENTS_TYPE event;
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public TRACKING_EVENTS_TYPE getEvent() {
        return this.event;
    }

    public void setEvent(TRACKING_EVENTS_TYPE tracking_events_type) {
        this.event = tracking_events_type;
    }

    public String toString() {
        return "Tracking [event=" + this.event + ", value=" + this.value + "]";
    }
}
