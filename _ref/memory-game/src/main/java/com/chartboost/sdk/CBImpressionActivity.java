package com.chartboost.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.l;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.a;
import com.facebook.places.model.PlaceFields;

@SuppressLint({"Registered"})
public class CBImpressionActivity extends Activity {
    protected static final String a = CBImpressionActivity.class.getSimpleName();
    private Activity b = null;
    private final PhoneStateListener c = new PhoneStateListener(this) {
        final /* synthetic */ CBImpressionActivity a;

        {
            this.a = r1;
        }

        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == 1) {
                CBLogging.a(CBImpressionActivity.a, "##### Phone call State: Ringing");
                CBLogging.a(CBImpressionActivity.a, "##### Pausing the impression");
                this.a.onPause();
            } else if (state == 0) {
                CBLogging.a(CBImpressionActivity.a, "##### Phone call State: Idle");
                CBLogging.a(CBImpressionActivity.a, "##### Resuming the impression");
                this.a.onResume();
            } else if (state == 2) {
                CBLogging.a(CBImpressionActivity.a, "##### Phone call State: OffHook");
                CBLogging.a(CBImpressionActivity.a, "##### Pausing the impression");
                this.a.onPause();
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    };

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.b != null) {
            return this.b.dispatchTouchEvent(event);
        }
        return super.dispatchTouchEvent(event);
    }

    public void forwardTouchEvents(Activity host) {
        this.b = host;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() == null || getIntent().getBooleanExtra("isChartboost", false)) {
            a();
            requestWindowFeature(1);
            getWindow().setWindowAnimations(0);
            f.q().a(this);
            setContentView(new RelativeLayout(this));
            a(this);
            f.b().c();
            CBLogging.a(CBImpressionActivity.class.getName(), "Impression Activity onCreate() called");
            return;
        }
        CBLogging.b(a, "This activity cannot be called from outside chartboost SDK");
        finish();
    }

    @TargetApi(11)
    private void a() {
        if (a.a().a(11)) {
            getWindow().setFlags(16777216, 16777216);
        }
    }

    protected void onStart() {
        super.onStart();
        if (!f.q().i()) {
            f.q().a((Activity) this);
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(PlaceFields.PHONE);
            if (telephonyManager != null) {
                telephonyManager.listen(this.c, 32);
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "onStart", e);
        }
    }

    protected void onResume() {
        super.onResume();
        if (!f.q().i()) {
            f.q().a(l.a((Activity) this));
        }
        CBUtility.a(a.a().a((Activity) this));
    }

    protected void onPause() {
        super.onPause();
        if (!f.q().i()) {
            f.q().b(l.a((Activity) this));
        }
    }

    protected void onStop() {
        super.onStop();
        if (!f.q().i()) {
            f.q().c(l.a((Activity) this));
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(PlaceFields.PHONE);
            if (telephonyManager != null) {
                telephonyManager.listen(this.c, 0);
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "onStop", e);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!f.q().i()) {
            f.q().b((Activity) this);
        }
    }

    public void onBackPressed() {
        if (!f.q().d()) {
            super.onBackPressed();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (VERSION.SDK_INT >= 14 && getWindow() != null && getWindow().getDecorView() != null && !getWindow().getDecorView().isHardwareAccelerated()) {
            CBLogging.b(a, "The activity passed down is not hardware accelerated, so Chartboost cannot show ads");
            com.chartboost.sdk.Model.a c = f.c().c();
            if (c != null) {
                c.a(CBImpressionError.HARDWARE_ACCELERATION_DISABLED);
                f.k().a(c.q().e(), c.e, c.p(), CBImpressionError.HARDWARE_ACCELERATION_DISABLED);
            }
            finish();
        }
    }

    protected static void a(final Activity activity) {
        if (!a.a().a(14)) {
            CBUtility.c().post(new Runnable() {
                public void run() {
                    CBLogging.e("VideoInit", "preparing activity for video surface");
                    activity.addContentView(new SurfaceView(activity), new LayoutParams(0, 0));
                }
            });
        }
    }
}
