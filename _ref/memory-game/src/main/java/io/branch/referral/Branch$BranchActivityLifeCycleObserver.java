package io.branch.referral;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import io.branch.indexing.ContentDiscoverer;
import java.lang.ref.WeakReference;

@TargetApi(14)
class Branch$BranchActivityLifeCycleObserver implements ActivityLifecycleCallbacks {
    private int activityCnt_;
    final /* synthetic */ Branch this$0;

    private Branch$BranchActivityLifeCycleObserver(Branch branch) {
        this.this$0 = branch;
        this.activityCnt_ = 0;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Branch.access$602(this.this$0, Branch.access$700(this.this$0) ? Branch$INTENT_STATE.PENDING : Branch$INTENT_STATE.READY);
        if (BranchViewHandler.getInstance().isInstallOrOpenBranchViewPending(activity.getApplicationContext())) {
            BranchViewHandler.getInstance().showPendingBranchView(activity);
        }
    }

    public void onActivityStarted(Activity activity) {
        Branch.access$602(this.this$0, Branch.access$700(this.this$0) ? Branch$INTENT_STATE.PENDING : Branch$INTENT_STATE.READY);
        if (Branch.access$800(this.this$0) == Branch$SESSION_STATE.INITIALISED) {
            try {
                ContentDiscoverer.getInstance().discoverContent(activity, this.this$0.sessionReferredLink_);
            } catch (Exception e) {
            }
        }
        if (this.activityCnt_ < 1) {
            if (Branch.access$800(this.this$0) == Branch$SESSION_STATE.INITIALISED) {
                Branch.access$802(this.this$0, Branch$SESSION_STATE.UNINITIALISED);
            }
            if (BranchUtil.isTestModeEnabled(Branch.access$100(this.this$0))) {
                Branch.access$200(this.this$0).setExternDebug();
            }
            Branch.access$200(this.this$0).setLogging(Branch.getIsLogging());
            Branch.access$900(this.this$0, activity);
        } else if (Branch.access$1000(this.this$0, activity.getIntent())) {
            Branch.access$802(this.this$0, Branch$SESSION_STATE.UNINITIALISED);
            Branch.access$900(this.this$0, activity);
        }
        this.activityCnt_++;
    }

    public void onActivityResumed(Activity activity) {
        if (Branch.access$1000(this.this$0, activity.getIntent())) {
            Branch.access$802(this.this$0, Branch$SESSION_STATE.UNINITIALISED);
            Branch.access$900(this.this$0, activity);
        }
        this.this$0.currentActivityReference_ = new WeakReference(activity);
        if (Branch.access$700(this.this$0)) {
            Branch.access$602(this.this$0, Branch$INTENT_STATE.READY);
            Branch.access$1100(this.this$0, activity);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (Branch.access$1200(this.this$0) != null) {
            Branch.access$1200(this.this$0).cancelShareLinkDialog(true);
        }
    }

    public void onActivityStopped(Activity activity) {
        ContentDiscoverer.getInstance().onActivityStopped(activity);
        this.activityCnt_--;
        if (this.activityCnt_ < 1) {
            Branch.access$1300(this.this$0);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.this$0.currentActivityReference_ != null && this.this$0.currentActivityReference_.get() == activity) {
            this.this$0.currentActivityReference_.clear();
        }
        BranchViewHandler.getInstance().onCurrentActivityDestroyed(activity);
    }
}
