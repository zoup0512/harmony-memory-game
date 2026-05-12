package io.branch.referral;

import android.util.Log;
import io.branch.referral.Defines.Jsonkey;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

class Branch$BranchPostTask extends BranchAsyncTask<Void, Void, ServerResponse> {
    final /* synthetic */ Branch this$0;
    ServerRequest thisReq_;
    int timeOut_ = 0;

    public Branch$BranchPostTask(Branch branch, ServerRequest request) {
        this.this$0 = branch;
        this.thisReq_ = request;
        this.timeOut_ = Branch.access$200(branch).getTimeout();
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.thisReq_.onPreExecute();
    }

    protected ServerResponse doInBackground(Void... voids) {
        if (this.thisReq_ instanceof ServerRequestInitSession) {
            ((ServerRequestInitSession) this.thisReq_).updateLinkReferrerParams();
        }
        this.this$0.addExtraInstrumentationData(this.thisReq_.getRequestPath() + "-" + Jsonkey.Queue_Wait_Time.getKey(), String.valueOf(this.thisReq_.getQueueWaitTime()));
        if (this.thisReq_.isGAdsParamsRequired() && !BranchUtil.isTestModeEnabled(Branch.access$100(this.this$0))) {
            this.thisReq_.updateGAdsParams(Branch.access$1500(this.this$0));
        }
        if (this.thisReq_.isGetRequest()) {
            return Branch.access$1400(this.this$0).make_restful_get(this.thisReq_.getRequestUrl(), this.thisReq_.getGetParams(), this.thisReq_.getRequestPath(), this.timeOut_);
        }
        return Branch.access$1400(this.this$0).make_restful_post(this.thisReq_.getPostWithInstrumentationValues(Branch.access$1600(this.this$0)), this.thisReq_.getRequestUrl(), this.thisReq_.getRequestPath(), this.timeOut_);
    }

    protected void onPostExecute(ServerResponse serverResponse) {
        super.onPostExecute(serverResponse);
        if (serverResponse != null) {
            try {
                int status = serverResponse.getStatusCode();
                Branch.access$1702(this.this$0, true);
                if (status != 200) {
                    if (this.thisReq_ instanceof ServerRequestInitSession) {
                        Branch.access$802(this.this$0, Branch$SESSION_STATE.UNINITIALISED);
                    }
                    if (status == 409) {
                        Branch.access$300(this.this$0).remove(this.thisReq_);
                        if (this.thisReq_ instanceof ServerRequestCreateUrl) {
                            ((ServerRequestCreateUrl) this.thisReq_).handleDuplicateURLError();
                        } else {
                            Log.i("BranchSDK", "Branch API Error: Conflicting resource error code from API");
                            Branch.access$1800(this.this$0, 0, status);
                        }
                    } else {
                        ServerRequest req;
                        Branch.access$1702(this.this$0, false);
                        ArrayList<ServerRequest> requestToFail = new ArrayList();
                        for (int i = 0; i < Branch.access$300(this.this$0).getSize(); i++) {
                            requestToFail.add(Branch.access$300(this.this$0).peekAt(i));
                        }
                        Iterator it = requestToFail.iterator();
                        while (it.hasNext()) {
                            req = (ServerRequest) it.next();
                            if (req == null || !req.shouldRetryOnFail()) {
                                Branch.access$300(this.this$0).remove(req);
                            }
                        }
                        Branch.access$1902(this.this$0, 0);
                        it = requestToFail.iterator();
                        while (it.hasNext()) {
                            req = (ServerRequest) it.next();
                            if (req != null) {
                                req.handleFailure(status, serverResponse.getFailReason());
                                if (req.shouldRetryOnFail()) {
                                    req.clearCallbacks();
                                }
                            }
                        }
                    }
                } else {
                    Branch.access$1702(this.this$0, true);
                    if (this.thisReq_ instanceof ServerRequestCreateUrl) {
                        if (serverResponse.getObject() != null) {
                            Branch.access$2000(this.this$0).put(((ServerRequestCreateUrl) this.thisReq_).getLinkPost(), serverResponse.getObject().getString("url"));
                        }
                    } else if (this.thisReq_ instanceof ServerRequestLogout) {
                        Branch.access$2000(this.this$0).clear();
                        Branch.access$300(this.this$0).clear();
                    }
                    Branch.access$300(this.this$0).dequeue();
                    if ((this.thisReq_ instanceof ServerRequestInitSession) || (this.thisReq_ instanceof ServerRequestIdentifyUserRequest)) {
                        JSONObject respJson = serverResponse.getObject();
                        if (respJson != null) {
                            boolean updateRequestsInQueue = false;
                            if (respJson.has(Jsonkey.SessionID.getKey())) {
                                Branch.access$200(this.this$0).setSessionID(respJson.getString(Jsonkey.SessionID.getKey()));
                                updateRequestsInQueue = true;
                            }
                            if (respJson.has(Jsonkey.IdentityID.getKey())) {
                                if (!Branch.access$200(this.this$0).getIdentityID().equals(respJson.getString(Jsonkey.IdentityID.getKey()))) {
                                    Branch.access$2000(this.this$0).clear();
                                    Branch.access$200(this.this$0).setIdentityID(respJson.getString(Jsonkey.IdentityID.getKey()));
                                    updateRequestsInQueue = true;
                                }
                            }
                            if (respJson.has(Jsonkey.DeviceFingerprintID.getKey())) {
                                Branch.access$200(this.this$0).setDeviceFingerPrintID(respJson.getString(Jsonkey.DeviceFingerprintID.getKey()));
                                updateRequestsInQueue = true;
                            }
                            if (updateRequestsInQueue) {
                                Branch.access$2100(this.this$0);
                            }
                            if (this.thisReq_ instanceof ServerRequestInitSession) {
                                Branch.access$802(this.this$0, Branch$SESSION_STATE.INITIALISED);
                                this.thisReq_.onRequestSucceeded(serverResponse, Branch.access$2200());
                                Branch.access$2302(this.this$0, ((ServerRequestInitSession) this.thisReq_).hasCallBack());
                                if (!((ServerRequestInitSession) this.thisReq_).handleBranchViewIfAvailable(serverResponse)) {
                                    Branch.access$2400(this.this$0);
                                }
                                if (Branch.access$2500(this.this$0) != null) {
                                    Branch.access$2500(this.this$0).countDown();
                                }
                                if (Branch.access$2600(this.this$0) != null) {
                                    Branch.access$2600(this.this$0).countDown();
                                }
                            } else {
                                this.thisReq_.onRequestSucceeded(serverResponse, Branch.access$2200());
                            }
                        }
                    } else {
                        this.thisReq_.onRequestSucceeded(serverResponse, Branch.access$2200());
                    }
                }
                Branch.access$1902(this.this$0, 0);
                if (Branch.access$1700(this.this$0) && Branch.access$800(this.this$0) != Branch$SESSION_STATE.UNINITIALISED) {
                    Branch.access$400(this.this$0);
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }
}
