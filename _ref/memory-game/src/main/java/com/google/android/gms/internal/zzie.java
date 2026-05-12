package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzju.zza;

@TargetApi(19)
@zzin
public class zzie extends zzid {
    private Object zzbyj = new Object();
    private PopupWindow zzbyk;
    private boolean zzbyl = false;

    zzie(Context context, zza com_google_android_gms_internal_zzju_zza, zzlh com_google_android_gms_internal_zzlh, zzic.zza com_google_android_gms_internal_zzic_zza) {
        super(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzic_zza);
    }

    private void zzqe() {
        synchronized (this.zzbyj) {
            this.zzbyl = true;
            if ((this.mContext instanceof Activity) && ((Activity) this.mContext).isDestroyed()) {
                this.zzbyk = null;
            }
            if (this.zzbyk != null) {
                if (this.zzbyk.isShowing()) {
                    this.zzbyk.dismiss();
                }
                this.zzbyk = null;
            }
        }
    }

    public void cancel() {
        zzqe();
        super.cancel();
    }

    protected void zzaj(int i) {
        zzqe();
        super.zzaj(i);
    }

    protected void zzqd() {
        Window window = this.mContext instanceof Activity ? ((Activity) this.mContext).getWindow() : null;
        if (window != null && window.getDecorView() != null && !((Activity) this.mContext).isDestroyed()) {
            View frameLayout = new FrameLayout(this.mContext);
            frameLayout.setLayoutParams(new LayoutParams(-1, -1));
            frameLayout.addView(this.zzbgf.getView(), -1, -1);
            synchronized (this.zzbyj) {
                if (this.zzbyl) {
                    return;
                }
                this.zzbyk = new PopupWindow(frameLayout, 1, 1, false);
                this.zzbyk.setOutsideTouchable(true);
                this.zzbyk.setClippingEnabled(false);
                zzb.zzcv("Displaying the 1x1 popup off the screen.");
                try {
                    this.zzbyk.showAtLocation(window.getDecorView(), 0, -1, -1);
                } catch (Exception e) {
                    this.zzbyk = null;
                }
            }
        }
    }
}
