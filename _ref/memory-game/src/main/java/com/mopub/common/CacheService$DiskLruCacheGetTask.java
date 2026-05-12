package com.mopub.common;

import android.os.AsyncTask;

class CacheService$DiskLruCacheGetTask extends AsyncTask<Void, Void, byte[]> {
    private final CacheService$DiskLruCacheGetListener mDiskLruCacheGetListener;
    private final String mKey;

    CacheService$DiskLruCacheGetTask(String str, CacheService$DiskLruCacheGetListener cacheService$DiskLruCacheGetListener) {
        this.mDiskLruCacheGetListener = cacheService$DiskLruCacheGetListener;
        this.mKey = str;
    }

    protected byte[] doInBackground(Void... voidArr) {
        return CacheService.getFromDiskCache(this.mKey);
    }

    protected void onPostExecute(byte[] bArr) {
        if (isCancelled()) {
            onCancelled();
        } else if (this.mDiskLruCacheGetListener != null) {
            this.mDiskLruCacheGetListener.onComplete(this.mKey, bArr);
        }
    }

    protected void onCancelled() {
        if (this.mDiskLruCacheGetListener != null) {
            this.mDiskLruCacheGetListener.onComplete(this.mKey, null);
        }
    }
}
