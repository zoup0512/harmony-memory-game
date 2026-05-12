package com.mopub.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.DiskLruCache.Editor;
import com.mopub.common.DiskLruCache.Snapshot;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Streams;
import com.mopub.common.util.Utils;
import com.yalantis.ucrop.util.FileUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CacheService {
    private static final int APP_VERSION = 1;
    private static final int DISK_CACHE_INDEX = 0;
    static final String UNIQUE_CACHE_NAME = "mopub-cache";
    private static final int VALUE_COUNT = 1;
    private static DiskLruCache sDiskLruCache;

    public static boolean initializeDiskCache(Context context) {
        if (context == null) {
            return false;
        }
        if (sDiskLruCache == null) {
            File diskCacheDirectory = getDiskCacheDirectory(context);
            if (diskCacheDirectory == null) {
                return false;
            }
            try {
                sDiskLruCache = DiskLruCache.open(diskCacheDirectory, 1, 1, DeviceUtils.diskCacheSizeBytes(diskCacheDirectory));
            } catch (Throwable e) {
                MoPubLog.d("Unable to create DiskLruCache", e);
                return false;
            }
        }
        return true;
    }

    public static void initialize(Context context) {
        initializeDiskCache(context);
    }

    public static String createValidDiskCacheKey(String str) {
        return Utils.sha1(str);
    }

    @Nullable
    public static File getDiskCacheDirectory(@NonNull Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        return new File(cacheDir.getPath() + File.separator + UNIQUE_CACHE_NAME);
    }

    public static boolean containsKeyDiskCache(String str) {
        if (sDiskLruCache == null) {
            return false;
        }
        try {
            if (sDiskLruCache.get(createValidDiskCacheKey(str)) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getFilePathDiskCache(String str) {
        if (sDiskLruCache == null) {
            return null;
        }
        return sDiskLruCache.getDirectory() + File.separator + createValidDiskCacheKey(str) + FileUtils.HIDDEN_PREFIX + 0;
    }

    public static byte[] getFromDiskCache(String str) {
        Throwable e;
        Throwable th;
        byte[] bArr = null;
        if (sDiskLruCache != null) {
            Snapshot snapshot;
            try {
                snapshot = sDiskLruCache.get(createValidDiskCacheKey(str));
                if (snapshot != null) {
                    try {
                        InputStream inputStream = snapshot.getInputStream(0);
                        if (inputStream != null) {
                            byte[] bArr2 = new byte[((int) snapshot.getLength(0))];
                            Closeable bufferedInputStream;
                            try {
                                bufferedInputStream = new BufferedInputStream(inputStream);
                                Streams.readStream(bufferedInputStream, bArr2);
                                Streams.closeStream(bufferedInputStream);
                                bArr = bArr2;
                            } catch (Throwable e2) {
                                Throwable th2 = e2;
                                bArr = bArr2;
                                th = th2;
                                try {
                                    MoPubLog.d("Unable to get from DiskLruCache", th);
                                    if (snapshot != null) {
                                        snapshot.close();
                                    }
                                    return bArr;
                                } catch (Throwable th3) {
                                    e2 = th3;
                                    if (snapshot != null) {
                                        snapshot.close();
                                    }
                                    throw e2;
                                }
                            } catch (Throwable th4) {
                                Streams.closeStream(bufferedInputStream);
                            }
                        }
                        if (snapshot != null) {
                            snapshot.close();
                        }
                    } catch (Exception e3) {
                        th = e3;
                        MoPubLog.d("Unable to get from DiskLruCache", th);
                        if (snapshot != null) {
                            snapshot.close();
                        }
                        return bArr;
                    }
                } else if (snapshot != null) {
                    snapshot.close();
                }
            } catch (Exception e4) {
                th = e4;
                snapshot = null;
                MoPubLog.d("Unable to get from DiskLruCache", th);
                if (snapshot != null) {
                    snapshot.close();
                }
                return bArr;
            } catch (Throwable th5) {
                snapshot = null;
                e2 = th5;
                if (snapshot != null) {
                    snapshot.close();
                }
                throw e2;
            }
        }
        return bArr;
    }

    public static void getFromDiskCacheAsync(String str, DiskLruCacheGetListener diskLruCacheGetListener) {
        new DiskLruCacheGetTask(str, diskLruCacheGetListener).execute(new Void[0]);
    }

    public static boolean putToDiskCache(String str, byte[] bArr) {
        return putToDiskCache(str, new ByteArrayInputStream(bArr));
    }

    public static boolean putToDiskCache(String str, InputStream inputStream) {
        if (sDiskLruCache == null) {
            return false;
        }
        Editor editor = null;
        try {
            editor = sDiskLruCache.edit(createValidDiskCacheKey(str));
            if (editor == null) {
                return false;
            }
            OutputStream bufferedOutputStream = new BufferedOutputStream(editor.newOutputStream(0));
            Streams.copyContent(inputStream, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            sDiskLruCache.flush();
            editor.commit();
            return true;
        } catch (Throwable e) {
            MoPubLog.d("Unable to put to DiskLruCache", e);
            if (editor == null) {
                return false;
            }
            try {
                editor.abort();
                return false;
            } catch (IOException e2) {
                return false;
            }
        }
    }

    public static void putToDiskCacheAsync(String str, byte[] bArr) {
        new DiskLruCachePutTask(str, bArr).execute(new Void[0]);
    }

    @Deprecated
    @VisibleForTesting
    public static void clearAndNullCaches() {
        if (sDiskLruCache != null) {
            try {
                sDiskLruCache.delete();
                sDiskLruCache = null;
            } catch (IOException e) {
                sDiskLruCache = null;
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    public static DiskLruCache getDiskLruCache() {
        return sDiskLruCache;
    }
}
