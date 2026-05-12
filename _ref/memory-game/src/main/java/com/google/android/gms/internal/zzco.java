package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.util.zzs;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(14)
@zzin
public class zzco extends Thread {
    private boolean mStarted = false;
    private final Object zzail;
    private final int zzarv;
    private final int zzarx;
    private boolean zzasj = false;
    private final zzcn zzask;
    private final zzcm zzasl;
    private final zzim zzasm;
    private final int zzasn;
    private final int zzaso;
    private final int zzasp;
    private boolean zzbl = false;

    @zzin
    class zza {
        final /* synthetic */ zzco zzasr;
        final int zzasx;
        final int zzasy;

        zza(zzco com_google_android_gms_internal_zzco, int i, int i2) {
            this.zzasr = com_google_android_gms_internal_zzco;
            this.zzasx = i;
            this.zzasy = i2;
        }
    }

    public zzco(zzcn com_google_android_gms_internal_zzcn, zzcm com_google_android_gms_internal_zzcm, zzim com_google_android_gms_internal_zzim) {
        this.zzask = com_google_android_gms_internal_zzcn;
        this.zzasl = com_google_android_gms_internal_zzcm;
        this.zzasm = com_google_android_gms_internal_zzim;
        this.zzail = new Object();
        this.zzarv = ((Integer) zzdc.zzazi.get()).intValue();
        this.zzaso = ((Integer) zzdc.zzazj.get()).intValue();
        this.zzarx = ((Integer) zzdc.zzazk.get()).intValue();
        this.zzasp = ((Integer) zzdc.zzazl.get()).intValue();
        this.zzasn = ((Integer) zzdc.zzazm.get()).intValue();
        setName("ContentFetchTask");
    }

    public void run() {
        while (true) {
            try {
                if (zzia()) {
                    Activity activity = this.zzask.getActivity();
                    if (activity == null) {
                        zzb.zzcv("ContentFetchThread: no activity. Sleeping.");
                        zzic();
                    } else {
                        zza(activity);
                    }
                } else {
                    zzb.zzcv("ContentFetchTask: sleeping");
                    zzic();
                }
                Thread.sleep((long) (this.zzasn * 1000));
            } catch (Throwable th) {
                zzb.zzb("Error in ContentFetchTask", th);
                this.zzasm.zza(th, true);
            }
            synchronized (this.zzail) {
                while (this.zzasj) {
                    try {
                        zzb.zzcv("ContentFetchTask: waiting");
                        this.zzail.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    public void wakeup() {
        synchronized (this.zzail) {
            this.zzasj = false;
            this.zzail.notifyAll();
            zzb.zzcv("ContentFetchThread: wakeup");
        }
    }

    zza zza(@Nullable View view, zzcl com_google_android_gms_internal_zzcl) {
        int i = 0;
        if (view == null) {
            return new zza(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zza(this, 0, 0);
            }
            com_google_android_gms_internal_zzcl.zze(text.toString(), globalVisibleRect);
            return new zza(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzlh)) {
            com_google_android_gms_internal_zzcl.zzhv();
            return zza((WebView) view, com_google_android_gms_internal_zzcl, globalVisibleRect) ? new zza(this, 0, 1) : new zza(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zza(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i2 = 0;
            int i3 = 0;
            while (i < viewGroup.getChildCount()) {
                zza zza = zza(viewGroup.getChildAt(i), com_google_android_gms_internal_zzcl);
                i3 += zza.zzasx;
                i2 += zza.zzasy;
                i++;
            }
            return new zza(this, i3, i2);
        }
    }

    void zza(@Nullable Activity activity) {
        if (activity != null) {
            View view = null;
            try {
                if (!(activity.getWindow() == null || activity.getWindow().getDecorView() == null)) {
                    view = activity.getWindow().getDecorView().findViewById(16908290);
                }
            } catch (Throwable th) {
                zzb.zzcv("Failed getting root view of activity. Content not extracted.");
            }
            if (view != null) {
                zze(view);
            }
        }
    }

    void zza(zzcl com_google_android_gms_internal_zzcl, WebView webView, String str, boolean z) {
        com_google_android_gms_internal_zzcl.zzhu();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (TextUtils.isEmpty(webView.getTitle())) {
                    com_google_android_gms_internal_zzcl.zzd(optString, z);
                } else {
                    String valueOf = String.valueOf(webView.getTitle());
                    com_google_android_gms_internal_zzcl.zzd(new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(optString).length()).append(valueOf).append("\n").append(optString).toString(), z);
                }
            }
            if (com_google_android_gms_internal_zzcl.zzhq()) {
                this.zzasl.zzb(com_google_android_gms_internal_zzcl);
            }
        } catch (JSONException e) {
            zzb.zzcv("Json string may be malformed.");
        } catch (Throwable th) {
            zzb.zza("Failed to get webview content.", th);
            this.zzasm.zza(th, true);
        }
    }

    boolean zza(RunningAppProcessInfo runningAppProcessInfo) {
        return runningAppProcessInfo.importance == 100;
    }

    @TargetApi(19)
    boolean zza(WebView webView, zzcl com_google_android_gms_internal_zzcl, boolean z) {
        if (!zzs.zzavu()) {
            return false;
        }
        com_google_android_gms_internal_zzcl.zzhv();
        webView.post(new 2(this, com_google_android_gms_internal_zzcl, webView, z));
        return true;
    }

    boolean zze(@Nullable View view) {
        if (view == null) {
            return false;
        }
        view.post(new 1(this, view));
        return true;
    }

    void zzf(View view) {
        try {
            zzcl com_google_android_gms_internal_zzcl = new zzcl(this.zzarv, this.zzaso, this.zzarx, this.zzasp);
            zza zza = zza(view, com_google_android_gms_internal_zzcl);
            com_google_android_gms_internal_zzcl.zzhw();
            if (zza.zzasx != 0 || zza.zzasy != 0) {
                if (zza.zzasy != 0 || com_google_android_gms_internal_zzcl.zzhx() != 0) {
                    if (zza.zzasy != 0 || !this.zzasl.zza(com_google_android_gms_internal_zzcl)) {
                        this.zzasl.zzc(com_google_android_gms_internal_zzcl);
                    }
                }
            }
        } catch (Throwable e) {
            zzb.zzb("Exception in fetchContentOnUIThread", e);
            this.zzasm.zza(e, true);
        }
    }

    public void zzhz() {
        synchronized (this.zzail) {
            if (this.mStarted) {
                zzb.zzcv("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    boolean zzia() {
        try {
            Context context = this.zzask.getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (zza(runningAppProcessInfo) && !keyguardManager.inKeyguardRestrictedInputMode() && zzj(context)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public zzcl zzib() {
        return this.zzasl.zzhy();
    }

    public void zzic() {
        synchronized (this.zzail) {
            this.zzasj = true;
            zzb.zzcv("ContentFetchThread: paused, mPause = " + this.zzasj);
        }
    }

    public boolean zzid() {
        return this.zzasj;
    }

    boolean zzj(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager == null ? false : powerManager.isScreenOn();
    }
}
