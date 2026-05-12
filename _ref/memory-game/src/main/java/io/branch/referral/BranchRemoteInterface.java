package io.branch.referral;

import android.content.Context;
import io.branch.referral.Defines.RequestPath;
import org.json.JSONObject;

public class BranchRemoteInterface extends RemoteInterface {
    private SystemObserver sysObserver_;

    public /* bridge */ /* synthetic */ ServerResponse make_restful_get(String str, JSONObject jSONObject, String str2, int i) {
        return super.make_restful_get(str, jSONObject, str2, i);
    }

    public /* bridge */ /* synthetic */ ServerResponse make_restful_post(JSONObject jSONObject, String str, String str2, int i) {
        return super.make_restful_post(jSONObject, str, str2, i);
    }

    public /* bridge */ /* synthetic */ ServerResponse make_restful_post(JSONObject jSONObject, String str, String str2, int i, boolean z) {
        return super.make_restful_post(jSONObject, str, str2, i, z);
    }

    public BranchRemoteInterface(Context context) {
        super(context);
        this.sysObserver_ = new SystemObserver(context);
    }

    public ServerResponse createCustomUrlSync(JSONObject post) {
        return make_restful_post(post, this.prefHelper_.getAPIBaseUrl() + "v1/url", RequestPath.GetURL.getPath(), this.prefHelper_.getTimeout());
    }

    public SystemObserver getSystemObserver() {
        return this.sysObserver_;
    }
}
