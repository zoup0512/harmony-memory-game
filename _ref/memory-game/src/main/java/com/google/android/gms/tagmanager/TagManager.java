package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
    private static TagManager aym;
    private final DataLayer auG;
    private final zzs axg;
    private final zza ayj;
    private final zzda ayk;
    private final ConcurrentMap<zzo, Boolean> ayl;
    private final Context mContext;

    public interface zza {
        zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzs com_google_android_gms_tagmanager_zzs);
    }

    TagManager(Context context, zza com_google_android_gms_tagmanager_TagManager_zza, DataLayer dataLayer, zzda com_google_android_gms_tagmanager_zzda) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.ayk = com_google_android_gms_tagmanager_zzda;
        this.ayj = com_google_android_gms_tagmanager_TagManager_zza;
        this.ayl = new ConcurrentHashMap();
        this.auG = dataLayer;
        this.auG.zza(new zzb(this) {
            final /* synthetic */ TagManager ayn;

            {
                this.ayn = r1;
            }

            public void zzaw(Map<String, Object> map) {
                Object obj = map.get("event");
                if (obj != null) {
                    this.ayn.zzpe(obj.toString());
                }
            }
        });
        this.auG.zza(new zzd(this.mContext));
        this.axg = new zzs();
        zzcdi();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (aym == null) {
                if (context == null) {
                    zzbn.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                aym = new TagManager(context, new zza() {
                    public zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzs com_google_android_gms_tagmanager_zzs) {
                        return new zzp(context, tagManager, looper, str, i, com_google_android_gms_tagmanager_zzs);
                    }
                }, new DataLayer(new zzw(context)), zzdb.zzcdc());
            }
            tagManager = aym;
        }
        return tagManager;
    }

    @TargetApi(14)
    private void zzcdi() {
        if (VERSION.SDK_INT >= 14) {
            this.mContext.registerComponentCallbacks(new ComponentCallbacks2(this) {
                final /* synthetic */ TagManager ayn;

                {
                    this.ayn = r1;
                }

                public void onConfigurationChanged(Configuration configuration) {
                }

                public void onLowMemory() {
                }

                public void onTrimMemory(int i) {
                    if (i == 20) {
                        this.ayn.dispatch();
                    }
                }
            });
        }
    }

    private void zzpe(String str) {
        for (zzo zzog : this.ayl.keySet()) {
            zzog.zzog(str);
        }
    }

    public void dispatch() {
        this.ayk.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.auG;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i) {
        PendingResult zza = this.ayj.zza(this.mContext, this, null, str, i, this.axg);
        zza.zzcaq();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i, Handler handler) {
        PendingResult zza = this.ayj.zza(this.mContext, this, handler.getLooper(), str, i, this.axg);
        zza.zzcaq();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i) {
        PendingResult zza = this.ayj.zza(this.mContext, this, null, str, i, this.axg);
        zza.zzcas();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i, Handler handler) {
        PendingResult zza = this.ayj.zza(this.mContext, this, handler.getLooper(), str, i, this.axg);
        zza.zzcas();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i) {
        PendingResult zza = this.ayj.zza(this.mContext, this, null, str, i, this.axg);
        zza.zzcar();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i, Handler handler) {
        PendingResult zza = this.ayj.zza(this.mContext, this, handler.getLooper(), str, i, this.axg);
        zza.zzcar();
        return zza;
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzbn.setLogLevel(z ? 2 : 5);
    }

    public void zza(zzo com_google_android_gms_tagmanager_zzo) {
        this.ayl.put(com_google_android_gms_tagmanager_zzo, Boolean.valueOf(true));
    }

    public boolean zzb(zzo com_google_android_gms_tagmanager_zzo) {
        return this.ayl.remove(com_google_android_gms_tagmanager_zzo) != null;
    }

    synchronized boolean zzt(Uri uri) {
        boolean z;
        zzci zzcci = zzci.zzcci();
        if (zzcci.zzt(uri)) {
            String containerId = zzcci.getContainerId();
            switch (zzcci.zzccj()) {
                case NONE:
                    for (zzo com_google_android_gms_tagmanager_zzo : this.ayl.keySet()) {
                        if (com_google_android_gms_tagmanager_zzo.getContainerId().equals(containerId)) {
                            com_google_android_gms_tagmanager_zzo.zzoi(null);
                            com_google_android_gms_tagmanager_zzo.refresh();
                        }
                    }
                    break;
                case CONTAINER:
                case CONTAINER_DEBUG:
                    for (zzo com_google_android_gms_tagmanager_zzo2 : this.ayl.keySet()) {
                        if (com_google_android_gms_tagmanager_zzo2.getContainerId().equals(containerId)) {
                            com_google_android_gms_tagmanager_zzo2.zzoi(zzcci.zzcck());
                            com_google_android_gms_tagmanager_zzo2.refresh();
                        } else if (com_google_android_gms_tagmanager_zzo2.zzcan() != null) {
                            com_google_android_gms_tagmanager_zzo2.zzoi(null);
                            com_google_android_gms_tagmanager_zzo2.refresh();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
