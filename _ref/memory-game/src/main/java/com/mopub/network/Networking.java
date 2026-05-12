package com.mopub.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.webkit.WebView;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.DeviceUtils;
import com.mopub.volley.Network;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.toolbox.BasicNetwork;
import com.mopub.volley.toolbox.DiskBasedCache;
import com.mopub.volley.toolbox.ImageLoader;
import com.mopub.volley.toolbox.ImageLoader.ImageCache;
import java.io.File;

public class Networking {
    @VisibleForTesting
    static final String CACHE_DIRECTORY_NAME = "mopub-volley-cache";
    private static final String DEFAULT_USER_AGENT = System.getProperty("http.agent");
    private static volatile MaxWidthImageLoader sMaxWidthImageLoader;
    private static volatile MoPubRequestQueue sRequestQueue;
    private static boolean sUseHttps = false;
    private static volatile String sUserAgent;

    @Nullable
    public static MoPubRequestQueue getRequestQueue() {
        return sRequestQueue;
    }

    @NonNull
    public static MoPubRequestQueue getRequestQueue(@NonNull Context context) {
        MoPubRequestQueue moPubRequestQueue = sRequestQueue;
        if (moPubRequestQueue == null) {
            synchronized (Networking.class) {
                moPubRequestQueue = sRequestQueue;
                if (moPubRequestQueue == null) {
                    Network basicNetwork = new BasicNetwork(new RequestQueueHttpStack(getUserAgent(context.getApplicationContext()), new PlayServicesUrlRewriter(ClientMetadata.getInstance(context).getDeviceId(), context), CustomSSLSocketFactory.getDefault(10000)));
                    File file = new File(context.getCacheDir().getPath() + File.separator + CACHE_DIRECTORY_NAME);
                    moPubRequestQueue = new MoPubRequestQueue(new DiskBasedCache(file, (int) DeviceUtils.diskCacheSizeBytes(file, 10485760)), basicNetwork);
                    sRequestQueue = moPubRequestQueue;
                    moPubRequestQueue.start();
                }
            }
        }
        return moPubRequestQueue;
    }

    @NonNull
    public static ImageLoader getImageLoader(@NonNull Context context) {
        ImageLoader imageLoader = sMaxWidthImageLoader;
        if (imageLoader == null) {
            synchronized (Networking.class) {
                imageLoader = sMaxWidthImageLoader;
                if (imageLoader == null) {
                    RequestQueue requestQueue = getRequestQueue(context);
                    final LruCache anonymousClass1 = new LruCache<String, Bitmap>(DeviceUtils.memoryCacheSizeBytes(context)) {
                        protected int sizeOf(String str, Bitmap bitmap) {
                            if (bitmap != null) {
                                return bitmap.getRowBytes() * bitmap.getHeight();
                            }
                            return super.sizeOf(str, bitmap);
                        }
                    };
                    imageLoader = new MaxWidthImageLoader(requestQueue, context, new ImageCache() {
                        public Bitmap getBitmap(String str) {
                            return (Bitmap) anonymousClass1.get(str);
                        }

                        public void putBitmap(String str, Bitmap bitmap) {
                            anonymousClass1.put(str, bitmap);
                        }
                    });
                    sMaxWidthImageLoader = imageLoader;
                }
            }
        }
        return imageLoader;
    }

    @NonNull
    public static String getUserAgent(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        String str = sUserAgent;
        if (str == null) {
            synchronized (Networking.class) {
                str = sUserAgent;
                if (str == null) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        try {
                            str = new WebView(context).getSettings().getUserAgentString();
                        } catch (Exception e) {
                            str = DEFAULT_USER_AGENT;
                        }
                    } else {
                        str = DEFAULT_USER_AGENT;
                    }
                    sUserAgent = str;
                }
            }
        }
        return str;
    }

    @NonNull
    public static String getCachedUserAgent() {
        String str = sUserAgent;
        if (str == null) {
            return DEFAULT_USER_AGENT;
        }
        return str;
    }

    @VisibleForTesting
    public static synchronized void clearForTesting() {
        synchronized (Networking.class) {
            sRequestQueue = null;
            sMaxWidthImageLoader = null;
            sUserAgent = null;
        }
    }

    @VisibleForTesting
    public static synchronized void setRequestQueueForTesting(MoPubRequestQueue moPubRequestQueue) {
        synchronized (Networking.class) {
            sRequestQueue = moPubRequestQueue;
        }
    }

    @VisibleForTesting
    public static synchronized void setImageLoaderForTesting(MaxWidthImageLoader maxWidthImageLoader) {
        synchronized (Networking.class) {
            sMaxWidthImageLoader = maxWidthImageLoader;
        }
    }

    @VisibleForTesting
    public static synchronized void setUserAgentForTesting(String str) {
        synchronized (Networking.class) {
            sUserAgent = str;
        }
    }

    public static void useHttps(boolean z) {
        sUseHttps = z;
    }

    public static boolean useHttps() {
        return sUseHttps;
    }

    public static String getScheme() {
        return useHttps() ? Constants.HTTPS : Constants.HTTP;
    }

    public static String getBaseUrlScheme() {
        return Constants.HTTP;
    }
}
