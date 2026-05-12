package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener;

class zzo implements ContainerHolder {
    private boolean ED;
    private Container auN;
    private Container auO;
    private zzb auP;
    private zza auQ;
    private TagManager auR;
    private Status bY;
    private final Looper zzahv;

    public interface zza {
        String zzcan();

        void zzcap();

        void zzoi(String str);
    }

    private class zzb extends Handler {
        private final ContainerAvailableListener auS;
        final /* synthetic */ zzo auT;

        public zzb(zzo com_google_android_gms_tagmanager_zzo, ContainerAvailableListener containerAvailableListener, Looper looper) {
            this.auT = com_google_android_gms_tagmanager_zzo;
            super(looper);
            this.auS = containerAvailableListener;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    zzok((String) message.obj);
                    return;
                default:
                    zzbn.e("Don't know how to handle this message.");
                    return;
            }
        }

        public void zzoj(String str) {
            sendMessage(obtainMessage(1, str));
        }

        protected void zzok(String str) {
            this.auS.onContainerAvailable(this.auT, str);
        }
    }

    public zzo(Status status) {
        this.bY = status;
        this.zzahv = null;
    }

    public zzo(TagManager tagManager, Looper looper, Container container, zza com_google_android_gms_tagmanager_zzo_zza) {
        this.auR = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzahv = looper;
        this.auN = container;
        this.auQ = com_google_android_gms_tagmanager_zzo_zza;
        this.bY = Status.sq;
        tagManager.zza(this);
    }

    private void zzcao() {
        if (this.auP != null) {
            this.auP.zzoj(this.auO.zzcal());
        }
    }

    public synchronized Container getContainer() {
        Container container = null;
        synchronized (this) {
            if (this.ED) {
                zzbn.e("ContainerHolder is released.");
            } else {
                if (this.auO != null) {
                    this.auN = this.auO;
                    this.auO = null;
                }
                container = this.auN;
            }
        }
        return container;
    }

    String getContainerId() {
        if (!this.ED) {
            return this.auN.getContainerId();
        }
        zzbn.e("getContainerId called on a released ContainerHolder.");
        return "";
    }

    public Status getStatus() {
        return this.bY;
    }

    public synchronized void refresh() {
        if (this.ED) {
            zzbn.e("Refreshing a released ContainerHolder.");
        } else {
            this.auQ.zzcap();
        }
    }

    public synchronized void release() {
        if (this.ED) {
            zzbn.e("Releasing a released ContainerHolder.");
        } else {
            this.ED = true;
            this.auR.zzb(this);
            this.auN.release();
            this.auN = null;
            this.auO = null;
            this.auQ = null;
            this.auP = null;
        }
    }

    public synchronized void setContainerAvailableListener(ContainerAvailableListener containerAvailableListener) {
        if (this.ED) {
            zzbn.e("ContainerHolder is released.");
        } else if (containerAvailableListener == null) {
            this.auP = null;
        } else {
            this.auP = new zzb(this, containerAvailableListener, this.zzahv);
            if (this.auO != null) {
                zzcao();
            }
        }
    }

    public synchronized void zza(Container container) {
        if (!this.ED) {
            if (container == null) {
                zzbn.e("Unexpected null container.");
            } else {
                this.auO = container;
                zzcao();
            }
        }
    }

    String zzcan() {
        if (!this.ED) {
            return this.auQ.zzcan();
        }
        zzbn.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    public synchronized void zzog(String str) {
        if (!this.ED) {
            this.auN.zzog(str);
        }
    }

    void zzoi(String str) {
        if (this.ED) {
            zzbn.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.auQ.zzoi(str);
        }
    }
}
