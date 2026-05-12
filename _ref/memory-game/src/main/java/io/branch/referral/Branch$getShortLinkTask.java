package io.branch.referral;

import android.os.AsyncTask;

class Branch$getShortLinkTask extends AsyncTask<ServerRequest, Void, ServerResponse> {
    final /* synthetic */ Branch this$0;

    private Branch$getShortLinkTask(Branch branch) {
        this.this$0 = branch;
    }

    protected ServerResponse doInBackground(ServerRequest... serverRequests) {
        return Branch.access$1400(this.this$0).createCustomUrlSync(serverRequests[0].getPost());
    }
}
