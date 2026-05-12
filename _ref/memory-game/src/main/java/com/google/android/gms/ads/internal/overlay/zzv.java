package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.media.TransportMediator;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzin;

@zzin
class zzv implements SensorEventListener {
    private final SensorManager zzbui;
    private final Object zzbuj = new Object();
    private final Display zzbuk;
    private final float[] zzbul = new float[9];
    private final float[] zzbum = new float[9];
    private float[] zzbun;
    private Handler zzbuo;
    private zza zzbup;

    zzv(Context context) {
        this.zzbui = (SensorManager) context.getSystemService("sensor");
        this.zzbuk = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    private void zzf(int i, int i2) {
        float f = this.zzbum[i];
        this.zzbum[i] = this.zzbum[i2];
        this.zzbum[i2] = f;
    }

    int getRotation() {
        return this.zzbuk.getRotation();
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        zza(sensorEvent.values);
    }

    void start() {
        if (this.zzbuo == null) {
            Sensor defaultSensor = this.zzbui.getDefaultSensor(11);
            if (defaultSensor == null) {
                zzb.e("No Sensor of TYPE_ROTATION_VECTOR");
                return;
            }
            HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
            handlerThread.start();
            this.zzbuo = new Handler(handlerThread.getLooper());
            if (!this.zzbui.registerListener(this, defaultSensor, 0, this.zzbuo)) {
                zzb.e("SensorManager.registerListener failed.");
                stop();
            }
        }
    }

    void stop() {
        if (this.zzbuo != null) {
            this.zzbui.unregisterListener(this);
            this.zzbuo.post(new 1(this));
            this.zzbuo = null;
        }
    }

    void zza(zza com_google_android_gms_ads_internal_overlay_zzv_zza) {
        this.zzbup = com_google_android_gms_ads_internal_overlay_zzv_zza;
    }

    void zza(float[] fArr) {
        if (fArr[0] != 0.0f || fArr[1] != 0.0f || fArr[2] != 0.0f) {
            synchronized (this.zzbuj) {
                if (this.zzbun == null) {
                    this.zzbun = new float[9];
                }
            }
            SensorManager.getRotationMatrixFromVector(this.zzbul, fArr);
            switch (getRotation()) {
                case 1:
                    SensorManager.remapCoordinateSystem(this.zzbul, 2, 129, this.zzbum);
                    break;
                case 2:
                    SensorManager.remapCoordinateSystem(this.zzbul, 129, TransportMediator.KEYCODE_MEDIA_RECORD, this.zzbum);
                    break;
                case 3:
                    SensorManager.remapCoordinateSystem(this.zzbul, TransportMediator.KEYCODE_MEDIA_RECORD, 1, this.zzbum);
                    break;
                default:
                    System.arraycopy(this.zzbul, 0, this.zzbum, 0, 9);
                    break;
            }
            zzf(1, 3);
            zzf(2, 6);
            zzf(5, 7);
            synchronized (this.zzbuj) {
                System.arraycopy(this.zzbum, 0, this.zzbun, 0, 9);
            }
            if (this.zzbup != null) {
                this.zzbup.zznz();
            }
        }
    }

    boolean zzb(float[] fArr) {
        boolean z = false;
        synchronized (this.zzbuj) {
            if (this.zzbun == null) {
            } else {
                System.arraycopy(this.zzbun, 0, fArr, 0, this.zzbun.length);
                z = true;
            }
        }
        return z;
    }
}
