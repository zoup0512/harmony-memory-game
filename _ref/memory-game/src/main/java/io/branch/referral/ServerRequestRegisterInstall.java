package io.branch.referral;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.Defines.RequestPath;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestRegisterInstall extends ServerRequestInitSession {
    Branch$BranchReferralInitListener callback_;
    final SystemObserver systemObserver_;

    public ServerRequestRegisterInstall(Context context, Branch$BranchReferralInitListener callback, SystemObserver sysObserver, String installID) {
        super(context, RequestPath.RegisterInstall.getPath());
        this.systemObserver_ = sysObserver;
        this.callback_ = callback;
        JSONObject installPost = new JSONObject();
        try {
            if (!installID.equals("bnc_no_value")) {
                installPost.put(Jsonkey.LinkClickID.getKey(), installID);
            }
            if (!sysObserver.getAppVersion().equals("bnc_no_value")) {
                installPost.put(Jsonkey.AppVersion.getKey(), sysObserver.getAppVersion());
            }
            if (this.prefHelper_.getExternDebug()) {
                String uriScheme = sysObserver.getURIScheme();
                if (!uriScheme.equals("bnc_no_value")) {
                    installPost.put(Jsonkey.URIScheme.getKey(), uriScheme);
                }
            }
            installPost.put(Jsonkey.FaceBookAppLinkChecked.getKey(), this.prefHelper_.getIsAppLinkTriggeredInit());
            installPost.put(Jsonkey.IsReferrable.getKey(), this.prefHelper_.getIsReferrable());
            installPost.put(Jsonkey.Update.getKey(), sysObserver.getUpdateState());
            installPost.put(Jsonkey.Debug.getKey(), this.prefHelper_.getExternDebug());
            setPost(installPost);
        } catch (JSONException ex) {
            ex.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestRegisterInstall(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
        this.systemObserver_ = new SystemObserver(context);
    }

    public boolean hasCallBack() {
        return this.callback_ != null;
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        super.onRequestSucceeded(resp, branch);
        try {
            this.prefHelper_.setUserURL(resp.getObject().getString(Jsonkey.Link.getKey()));
            if (resp.getObject().has(Jsonkey.Data.getKey())) {
                JSONObject dataObj = new JSONObject(resp.getObject().getString(Jsonkey.Data.getKey()));
                if (dataObj.has(Jsonkey.Clicked_Branch_Link.getKey()) && dataObj.getBoolean(Jsonkey.Clicked_Branch_Link.getKey()) && this.prefHelper_.getInstallParams().equals("bnc_no_value") && this.prefHelper_.getIsReferrable() == 1) {
                    this.prefHelper_.setInstallParams(resp.getObject().getString(Jsonkey.Data.getKey()));
                }
            }
            if (resp.getObject().has(Jsonkey.LinkClickID.getKey())) {
                this.prefHelper_.setLinkClickID(resp.getObject().getString(Jsonkey.LinkClickID.getKey()));
            } else {
                this.prefHelper_.setLinkClickID("bnc_no_value");
            }
            if (resp.getObject().has(Jsonkey.Data.getKey())) {
                this.prefHelper_.setSessionParams(resp.getObject().getString(Jsonkey.Data.getKey()));
            } else {
                this.prefHelper_.setSessionParams("bnc_no_value");
            }
            if (this.callback_ != null) {
                this.callback_.onInitFinished(branch.getLatestReferringParams(), null);
            }
            this.prefHelper_.setAppVersion(this.systemObserver_.getAppVersion());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        onInitSessionCompleted(resp, branch);
    }

    public void setInitFinishedCallback(Branch$BranchReferralInitListener callback) {
        if (callback != null) {
            this.callback_ = callback;
        }
    }

    public void handleFailure(int statusCode, String causeMsg) {
        if (this.callback_ != null) {
            JSONObject obj = new JSONObject();
            try {
                obj.put(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, "Trouble reaching server. Please try again in a few minutes");
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            this.callback_.onInitFinished(obj, new BranchError("Trouble initializing Branch. " + causeMsg, statusCode));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        if (this.callback_ != null) {
            this.callback_.onInitFinished(null, new BranchError("Trouble initializing Branch.", -102));
        }
        return true;
    }

    public boolean isGetRequest() {
        return false;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }

    public String getRequestActionName() {
        return "install";
    }
}
