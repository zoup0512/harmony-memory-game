package io.branch.referral;

import android.app.Activity;
import android.content.Context;
import io.branch.indexing.ContentDiscoverer;
import io.branch.indexing.ContentDiscoveryManifest;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

abstract class ServerRequestInitSession extends ServerRequest {
    static final String ACTION_INSTALL = "install";
    static final String ACTION_OPEN = "open";
    private final ContentDiscoveryManifest contentDiscoveryManifest_ = ContentDiscoveryManifest.getInstance(this.context_);
    private final Context context_;

    public abstract String getRequestActionName();

    public abstract boolean hasCallBack();

    ServerRequestInitSession(Context context, String requestPath) {
        super(context, requestPath);
        this.context_ = context;
    }

    ServerRequestInitSession(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
        this.context_ = context;
    }

    protected void setPost(JSONObject post) {
        super.setPost(post);
        updateEnvironment(this.context_, post);
    }

    public boolean isGAdsParamsRequired() {
        return true;
    }

    static boolean isInitSessionAction(String actionName) {
        if (actionName != null) {
            return actionName.equalsIgnoreCase(ACTION_OPEN) || actionName.equalsIgnoreCase(ACTION_INSTALL);
        } else {
            return false;
        }
    }

    boolean handleBranchViewIfAvailable(ServerResponse resp) {
        if (resp == null || resp.getObject() == null || !resp.getObject().has(Jsonkey.BranchViewData.getKey())) {
            return false;
        }
        try {
            JSONObject branchViewJsonObj = resp.getObject().getJSONObject(Jsonkey.BranchViewData.getKey());
            String actionName = getRequestActionName();
            if (Branch.getInstance().currentActivityReference_ == null || Branch.getInstance().currentActivityReference_.get() == null) {
                return BranchViewHandler.getInstance().markInstallOrOpenBranchViewPending(branchViewJsonObj, actionName);
            }
            Activity currentActivity = (Activity) Branch.getInstance().currentActivityReference_.get();
            boolean isActivityEnabledForBranchView = true;
            if (currentActivity instanceof Branch$IBranchViewControl) {
                isActivityEnabledForBranchView = !((Branch$IBranchViewControl) currentActivity).skipBranchViewsOnThisActivity();
            }
            if (isActivityEnabledForBranchView) {
                return BranchViewHandler.getInstance().showBranchView(branchViewJsonObj, actionName, currentActivity, Branch.getInstance());
            }
            return BranchViewHandler.getInstance().markInstallOrOpenBranchViewPending(branchViewJsonObj, actionName);
        } catch (JSONException e) {
            return false;
        }
    }

    public void onRequestSucceeded(ServerResponse response, Branch branch) {
        try {
            this.prefHelper_.setLinkClickIdentifier("bnc_no_value");
            this.prefHelper_.setGoogleSearchInstallIdentifier("bnc_no_value");
            this.prefHelper_.setExternalIntentUri("bnc_no_value");
            this.prefHelper_.setExternalIntentExtra("bnc_no_value");
            this.prefHelper_.setAppLink("bnc_no_value");
            this.prefHelper_.setPushIdentifier("bnc_no_value");
            this.prefHelper_.setIsAppLinkTriggeredInit(Boolean.valueOf(false));
            this.prefHelper_.setInstallReferrerParams("bnc_no_value");
            this.prefHelper_.setIsFullAppConversion(false);
            if (response.getObject() != null && response.getObject().has(Jsonkey.Data.getKey())) {
                new ExtendedAnswerProvider().provideData(this instanceof ServerRequestRegisterInstall ? ExtendedAnswerProvider.KIT_EVENT_INSTALL : ExtendedAnswerProvider.KIT_EVENT_OPEN, new JSONObject(response.getObject().getString(Jsonkey.Data.getKey())), this.prefHelper_.getIdentityID());
            }
        } catch (JSONException e) {
        }
    }

    void onInitSessionCompleted(ServerResponse response, Branch branch) {
        if (this.contentDiscoveryManifest_ != null) {
            this.contentDiscoveryManifest_.onBranchInitialised(response.getObject());
            if (branch.currentActivityReference_ != null) {
                try {
                    ContentDiscoverer.getInstance().onSessionStarted((Activity) branch.currentActivityReference_.get(), branch.sessionReferredLink_);
                } catch (Exception e) {
                }
            }
        }
    }

    void updateLinkReferrerParams() {
        if (!this.prefHelper_.getLinkClickIdentifier().equals("bnc_no_value")) {
            try {
                getPost().put(Jsonkey.LinkIdentifier.getKey(), this.prefHelper_.getLinkClickIdentifier());
            } catch (JSONException e) {
            }
        }
        if (!this.prefHelper_.getGoogleSearchInstallIdentifier().equals("bnc_no_value")) {
            try {
                getPost().put(Jsonkey.GoogleSearchInstallReferrer.getKey(), this.prefHelper_.getGoogleSearchInstallIdentifier());
            } catch (JSONException e2) {
            }
        }
        if (this.prefHelper_.isFullAppConversion()) {
            try {
                getPost().put(Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
                getPost().put(Jsonkey.IsFullAppConv.getKey(), true);
            } catch (JSONException e3) {
            }
        }
    }

    public void onPreExecute() {
        JSONObject post = getPost();
        try {
            if (!this.prefHelper_.getLinkClickIdentifier().equals("bnc_no_value")) {
                post.put(Jsonkey.LinkIdentifier.getKey(), this.prefHelper_.getLinkClickIdentifier());
            }
            if (!this.prefHelper_.getGoogleSearchInstallIdentifier().equals("bnc_no_value")) {
                post.put(Jsonkey.GoogleSearchInstallReferrer.getKey(), this.prefHelper_.getGoogleSearchInstallIdentifier());
            }
            if (!this.prefHelper_.getAppLink().equals("bnc_no_value")) {
                post.put(Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
            }
            if (!this.prefHelper_.getPushIdentifier().equals("bnc_no_value")) {
                post.put(Jsonkey.AndroidPushIdentifier.getKey(), this.prefHelper_.getPushIdentifier());
            }
            if (!this.prefHelper_.getExternalIntentUri().equals("bnc_no_value")) {
                post.put(Jsonkey.External_Intent_URI.getKey(), this.prefHelper_.getExternalIntentUri());
            }
            if (!this.prefHelper_.getExternalIntentExtra().equals("bnc_no_value")) {
                post.put(Jsonkey.External_Intent_Extra.getKey(), this.prefHelper_.getExternalIntentExtra());
            }
            if (this.contentDiscoveryManifest_ != null) {
                JSONObject cdObj = new JSONObject();
                cdObj.put(ContentDiscoveryManifest.MANIFEST_VERSION_KEY, this.contentDiscoveryManifest_.getManifestVersion());
                cdObj.put(ContentDiscoveryManifest.PACKAGE_NAME_KEY, this.context_.getPackageName());
                post.put(ContentDiscoveryManifest.CONTENT_DISCOVER_KEY, cdObj);
            }
        } catch (JSONException e) {
        }
    }
}
