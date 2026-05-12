package org.nexage.sourcekit.vast.model;

import java.util.ArrayList;
import java.util.List;

public class VideoClicks {
    private String clickThrough;
    private List<String> clickTracking;
    private List<String> customClick;

    public String getClickThrough() {
        return this.clickThrough;
    }

    public void setClickThrough(String str) {
        this.clickThrough = str;
    }

    public List<String> getClickTracking() {
        if (this.clickTracking == null) {
            this.clickTracking = new ArrayList();
        }
        return this.clickTracking;
    }

    public List<String> getCustomClick() {
        if (this.customClick == null) {
            this.customClick = new ArrayList();
        }
        return this.customClick;
    }

    public String toString() {
        return "VideoClicks [clickThrough=" + this.clickThrough + ", clickTracking=[" + listToString(this.clickTracking) + "], customClick=[" + listToString(this.customClick) + "] ]";
    }

    private String listToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list == null) {
            return "";
        }
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append((String) list.get(i));
        }
        return stringBuilder.toString();
    }
}
