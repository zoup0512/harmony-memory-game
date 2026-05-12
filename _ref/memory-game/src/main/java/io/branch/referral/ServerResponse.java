package io.branch.referral;

import com.yalantis.ucrop.util.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServerResponse {
    private Object post_;
    private int statusCode_;
    private String tag_;

    public ServerResponse(String tag, int statusCode) {
        this.tag_ = tag;
        this.statusCode_ = statusCode;
    }

    public String getTag() {
        return this.tag_;
    }

    public int getStatusCode() {
        return this.statusCode_;
    }

    public void setPost(Object post) {
        this.post_ = post;
    }

    public JSONObject getObject() {
        if (this.post_ instanceof JSONObject) {
            return (JSONObject) this.post_;
        }
        return new JSONObject();
    }

    public JSONArray getArray() {
        if (this.post_ instanceof JSONArray) {
            return (JSONArray) this.post_;
        }
        return null;
    }

    public String getFailReason() {
        String causeMsg = "";
        try {
            JSONObject postObj = getObject();
            if (postObj != null && postObj.has("error") && postObj.getJSONObject("error").has("message")) {
                causeMsg = postObj.getJSONObject("error").getString("message");
                if (causeMsg != null && causeMsg.trim().length() > 0) {
                    causeMsg = causeMsg + FileUtils.HIDDEN_PREFIX;
                }
            }
        } catch (Exception e) {
        }
        return causeMsg;
    }
}
