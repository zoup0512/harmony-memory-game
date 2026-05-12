package io.branch.referral;

import org.json.JSONArray;

public interface Branch$BranchListResponseListener {
    void onReceivingResponse(JSONArray jSONArray, BranchError branchError);
}
