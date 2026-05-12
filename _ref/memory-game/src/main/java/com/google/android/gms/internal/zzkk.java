package com.google.android.gms.internal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;

@zzin
public class zzkk {
    private final Context mContext;
    private int mState;
    private final float zzbre;
    private String zzcll;
    private float zzclm;
    private float zzcln;
    private float zzclo;

    public zzkk(Context context) {
        this.mState = 0;
        this.mContext = context;
        this.zzbre = context.getResources().getDisplayMetrics().density;
    }

    public zzkk(Context context, String str) {
        this(context);
        this.zzcll = str;
    }

    private void showDialog() {
        if (this.mContext instanceof Activity) {
            Object zzct = zzct(this.zzcll);
            Builder builder = new Builder(this.mContext);
            builder.setMessage(zzct);
            builder.setTitle("Ad Information");
            builder.setPositiveButton("Share", new 1(this, zzct));
            builder.setNegativeButton("Close", new 2(this));
            builder.create().show();
            return;
        }
        zzb.zzcw("Can not create dialog without Activity Context");
    }

    static String zzct(String str) {
        if (TextUtils.isEmpty(str)) {
            return "No debug information";
        }
        Uri build = new Uri.Builder().encodedQuery(str.replaceAll("\\+", "%20")).build();
        StringBuilder stringBuilder = new StringBuilder();
        Map zzf = zzu.zzfq().zzf(build);
        for (String str2 : zzf.keySet()) {
            stringBuilder.append(str2).append(" = ").append((String) zzf.get(str2)).append("\n\n");
        }
        Object trim = stringBuilder.toString().trim();
        return TextUtils.isEmpty(trim) ? "No debug information" : trim;
    }

    void zza(int i, float f, float f2) {
        if (i == 0) {
            this.mState = 0;
            this.zzclm = f;
            this.zzcln = f2;
            this.zzclo = f2;
        } else if (this.mState == -1) {
        } else {
            if (i == 2) {
                if (f2 > this.zzcln) {
                    this.zzcln = f2;
                } else if (f2 < this.zzclo) {
                    this.zzclo = f2;
                }
                if (this.zzcln - this.zzclo > 30.0f * this.zzbre) {
                    this.mState = -1;
                    return;
                }
                if (this.mState == 0 || this.mState == 2) {
                    if (f - this.zzclm >= 50.0f * this.zzbre) {
                        this.zzclm = f;
                        this.mState++;
                    }
                } else if ((this.mState == 1 || this.mState == 3) && f - this.zzclm <= -50.0f * this.zzbre) {
                    this.zzclm = f;
                    this.mState++;
                }
                if (this.mState == 1 || this.mState == 3) {
                    if (f > this.zzclm) {
                        this.zzclm = f;
                    }
                } else if (this.mState == 2 && f < this.zzclm) {
                    this.zzclm = f;
                }
            } else if (i == 1 && this.mState == 4) {
                showDialog();
            }
        }
    }

    public void zzcs(String str) {
        this.zzcll = str;
    }

    public void zze(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
}
