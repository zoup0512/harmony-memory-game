package io.branch.referral;

class Branch$3 implements StrongMatchCheckEvents {
    final /* synthetic */ Branch this$0;

    Branch$3(Branch this$0) {
        this.this$0 = this$0;
    }

    public void onStrongMatchCheckFinished() {
        Branch.access$300(this.this$0).unlockProcessWait(PROCESS_WAIT_LOCK.STRONG_MATCH_PENDING_WAIT_LOCK);
        Branch.access$400(this.this$0);
    }
}
