package com.cmcm.adsdk.nativead;

import java.util.Iterator;
import java.util.Vector;

class RequestLoadingStatus {
    private final Vector<Boolean> mLoadingStatus = new Vector();
    int mSize = 0;

    RequestLoadingStatus() {
    }

    public void resetLoadingStatus(int size) {
        this.mSize = size;
        this.mLoadingStatus.clear();
        for (int i = 0; i < this.mSize; i++) {
            this.mLoadingStatus.add(Boolean.valueOf(false));
        }
    }

    public boolean isBeanLoading(int i) {
        if (i < 0 || i >= this.mLoadingStatus.size()) {
            return true;
        }
        return ((Boolean) this.mLoadingStatus.get(i)).booleanValue();
    }

    public boolean setBeanLoading(int i, boolean value) {
        if (i < 0 || i >= this.mLoadingStatus.size()) {
            return false;
        }
        this.mLoadingStatus.set(i, Boolean.valueOf(value));
        return true;
    }

    public int getWaitingBeansNumber() {
        if (this.mLoadingStatus.size() != this.mSize) {
            return 0;
        }
        Iterator it = this.mLoadingStatus.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            if (((Boolean) it.next()).booleanValue()) {
                i2 = i;
            } else {
                i2 = i + 1;
            }
            i = i2;
        }
        return i;
    }
}
