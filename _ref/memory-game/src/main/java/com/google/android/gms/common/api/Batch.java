package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.internal.zzpo;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends zzpo<BatchResult> {
    private int rH;
    private boolean rI;
    private boolean rJ;
    private final PendingResult<?>[] rK;
    private final Object zzail;

    public static final class Builder {
        private GoogleApiClient gY;
        private List<PendingResult<?>> rM = new ArrayList();

        public Builder(GoogleApiClient googleApiClient) {
            this.gY = googleApiClient;
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken(this.rM.size());
            this.rM.add(pendingResult);
            return batchResultToken;
        }

        public Batch build() {
            return new Batch(this.rM, this.gY);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzail = new Object();
        this.rH = list.size();
        this.rK = new PendingResult[this.rH];
        if (list.isEmpty()) {
            zzc(new BatchResult(Status.sq, this.rK));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            PendingResult pendingResult = (PendingResult) list.get(i);
            this.rK[i] = pendingResult;
            pendingResult.zza(new zza(this) {
                final /* synthetic */ Batch rL;

                {
                    this.rL = r1;
                }

                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void zzv(com.google.android.gms.common.api.Status r6) {
                    /*
                    r5 = this;
                    r0 = r5.rL;
                    r1 = r0.zzail;
                    monitor-enter(r1);
                    r0 = r5.rL;	 Catch:{ all -> 0x0039 }
                    r0 = r0.isCanceled();	 Catch:{ all -> 0x0039 }
                    if (r0 == 0) goto L_0x0011;
                L_0x000f:
                    monitor-exit(r1);	 Catch:{ all -> 0x0039 }
                L_0x0010:
                    return;
                L_0x0011:
                    r0 = r6.isCanceled();	 Catch:{ all -> 0x0039 }
                    if (r0 == 0) goto L_0x003c;
                L_0x0017:
                    r0 = r5.rL;	 Catch:{ all -> 0x0039 }
                    r2 = 1;
                    r0.rJ = r2;	 Catch:{ all -> 0x0039 }
                L_0x001d:
                    r0 = r5.rL;	 Catch:{ all -> 0x0039 }
                    r0.rH = r0.rH - 1;	 Catch:{ all -> 0x0039 }
                    r0 = r5.rL;	 Catch:{ all -> 0x0039 }
                    r0 = r0.rH;	 Catch:{ all -> 0x0039 }
                    if (r0 != 0) goto L_0x0037;
                L_0x002a:
                    r0 = r5.rL;	 Catch:{ all -> 0x0039 }
                    r0 = r0.rJ;	 Catch:{ all -> 0x0039 }
                    if (r0 == 0) goto L_0x0049;
                L_0x0032:
                    r0 = r5.rL;	 Catch:{ all -> 0x0039 }
                    super.cancel();	 Catch:{ all -> 0x0039 }
                L_0x0037:
                    monitor-exit(r1);	 Catch:{ all -> 0x0039 }
                    goto L_0x0010;
                L_0x0039:
                    r0 = move-exception;
                    monitor-exit(r1);	 Catch:{ all -> 0x0039 }
                    throw r0;
                L_0x003c:
                    r0 = r6.isSuccess();	 Catch:{ all -> 0x0039 }
                    if (r0 != 0) goto L_0x001d;
                L_0x0042:
                    r0 = r5.rL;	 Catch:{ all -> 0x0039 }
                    r2 = 1;
                    r0.rI = r2;	 Catch:{ all -> 0x0039 }
                    goto L_0x001d;
                L_0x0049:
                    r0 = r5.rL;	 Catch:{ all -> 0x0039 }
                    r0 = r0.rI;	 Catch:{ all -> 0x0039 }
                    if (r0 == 0) goto L_0x0069;
                L_0x0051:
                    r0 = new com.google.android.gms.common.api.Status;	 Catch:{ all -> 0x0039 }
                    r2 = 13;
                    r0.<init>(r2);	 Catch:{ all -> 0x0039 }
                L_0x0058:
                    r2 = r5.rL;	 Catch:{ all -> 0x0039 }
                    r3 = new com.google.android.gms.common.api.BatchResult;	 Catch:{ all -> 0x0039 }
                    r4 = r5.rL;	 Catch:{ all -> 0x0039 }
                    r4 = r4.rK;	 Catch:{ all -> 0x0039 }
                    r3.<init>(r0, r4);	 Catch:{ all -> 0x0039 }
                    r2.zzc(r3);	 Catch:{ all -> 0x0039 }
                    goto L_0x0037;
                L_0x0069:
                    r0 = com.google.android.gms.common.api.Status.sq;	 Catch:{ all -> 0x0039 }
                    goto L_0x0058;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.Batch.1.zzv(com.google.android.gms.common.api.Status):void");
                }
            });
        }
    }

    public void cancel() {
        super.cancel();
        for (PendingResult cancel : this.rK) {
            cancel.cancel();
        }
    }

    public BatchResult createFailedResult(Status status) {
        return new BatchResult(status, this.rK);
    }

    public /* synthetic */ Result zzc(Status status) {
        return createFailedResult(status);
    }
}
