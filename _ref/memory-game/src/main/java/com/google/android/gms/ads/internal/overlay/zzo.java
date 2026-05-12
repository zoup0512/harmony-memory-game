package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.internal.zzin;

@zzin
public class zzo extends FrameLayout implements OnClickListener {
    private final ImageButton zzbug;
    private final zzu zzbuh;

    public zzo(Context context, int i, zzu com_google_android_gms_ads_internal_overlay_zzu) {
        super(context);
        this.zzbuh = com_google_android_gms_ads_internal_overlay_zzu;
        setOnClickListener(this);
        this.zzbug = new ImageButton(context);
        this.zzbug.setImageResource(17301527);
        this.zzbug.setBackgroundColor(0);
        this.zzbug.setOnClickListener(this);
        this.zzbug.setPadding(0, 0, 0, 0);
        this.zzbug.setContentDescription("Interstitial close button");
        int zza = zzm.zziw().zza(context, i);
        addView(this.zzbug, new LayoutParams(zza, zza, 17));
    }

    public void onClick(View view) {
        if (this.zzbuh != null) {
            this.zzbuh.zznv();
        }
    }

    public void zza(boolean z, boolean z2) {
        if (!z2) {
            this.zzbug.setVisibility(0);
        } else if (z) {
            this.zzbug.setVisibility(4);
        } else {
            this.zzbug.setVisibility(8);
        }
    }
}
