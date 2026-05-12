package com.amazon.device.ads;

import com.amazon.device.ads.JSONUtils.JSONUtilities;
import java.util.Locale;
import org.json.JSONObject;

class OrientationProperties {
    private Boolean allowOrientationChange;
    private ForceOrientation forceOrientation;
    private final JSONUtilities jsonUtils;

    public OrientationProperties() {
        this(new JSONUtilities());
    }

    OrientationProperties(JSONUtilities jSONUtilities) {
        this.allowOrientationChange = Boolean.valueOf(true);
        this.forceOrientation = ForceOrientation.NONE;
        this.jsonUtils = jSONUtilities;
    }

    public Boolean isAllowOrientationChange() {
        return this.allowOrientationChange;
    }

    public void setAllowOrientationChange(Boolean bool) {
        this.allowOrientationChange = bool;
    }

    public ForceOrientation getForceOrientation() {
        return this.forceOrientation;
    }

    public void setForceOrientation(ForceOrientation forceOrientation) {
        this.forceOrientation = forceOrientation;
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        this.jsonUtils.put(jSONObject, "forceOrientation", this.forceOrientation.toString());
        this.jsonUtils.put(jSONObject, "allowOrientationChange", this.allowOrientationChange.booleanValue());
        return jSONObject;
    }

    public void fromJSONObject(JSONObject jSONObject) {
        this.allowOrientationChange = Boolean.valueOf(this.jsonUtils.getBooleanFromJSON(jSONObject, "allowOrientationChange", this.allowOrientationChange.booleanValue()));
        this.forceOrientation = ForceOrientation.valueOf(this.jsonUtils.getStringFromJSON(jSONObject, "forceOrientation", this.forceOrientation.toString()).toUpperCase(Locale.US));
    }
}
