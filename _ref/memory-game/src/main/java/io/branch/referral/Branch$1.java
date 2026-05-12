package io.branch.referral;

class Branch$1 implements Runnable {
    final /* synthetic */ Branch this$0;

    Branch$1(Branch this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        ServerRequest req = new ServerRequestSendAppList(Branch.access$100(this.this$0));
        if (!req.constructError_ && !req.handleErrors(Branch.access$100(this.this$0))) {
            this.this$0.handleNewRequest(req);
        }
    }
}
