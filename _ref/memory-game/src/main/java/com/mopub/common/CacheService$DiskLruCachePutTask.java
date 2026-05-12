package com.mopub.common;

import android.os.AsyncTask;

class CacheService$DiskLruCachePutTask extends AsyncTask<Void, Void, Void> {
    private final byte[] mContent;
    private final String mKey;

    CacheService$DiskLruCachePutTask(String str, byte[] bArr) {
        this.mKey = str;
        this.mContent = bArr;
    }

    protected Void doInBackground(Void... voidArr) {
        CacheService.putToDiskCache(this.mKey, this.mContent);
        return null;
    }
}
