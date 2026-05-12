package com.cmcm.picks;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.R;
import com.cmcm.utils.g;

public class PicksLoadingActivity extends Activity {
    public static boolean a = true;
    static Handler b = new Handler(Looper.getMainLooper());
    private static final String c = PicksLoadingActivity.class.getSimpleName();
    private static ProgressBar d;
    private static int e = 0;

    static class a implements Runnable {
        private int a;
        private int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public void run() {
            if (PicksLoadingActivity.e < this.a) {
                PicksLoadingActivity.a(this.b);
                if (PicksLoadingActivity.d != null) {
                    PicksLoadingActivity.d.setProgress(PicksLoadingActivity.e);
                }
                PicksLoadingActivity.b.postDelayed(this, 50);
            }
        }
    }

    static /* synthetic */ int a(int i) {
        int i2 = e + i;
        e = i2;
        return i2;
    }

    String a(ViewGroup viewGroup, int i) {
        int i2;
        String str = "";
        String str2 = "";
        for (i2 = 0; i2 < i; i2++) {
            str2 = str2 + " ";
        }
        if (viewGroup != null) {
            String str3 = str + str2 + a((View) viewGroup) + "\n";
            str2 = str2 + " ";
            str = str3;
            for (i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt instanceof ViewGroup) {
                    str = str + a((ViewGroup) childAt, i + 1);
                } else if (childAt != null) {
                    str = str + str2 + a(childAt) + "\n";
                }
            }
        }
        return str;
    }

    String a(View view) {
        if (view == null) {
            return "[null]";
        }
        return view.getClass().getSimpleName() + "[" + view.getId() + "]" + view.toString();
    }

    public static void a(Context context) {
        a(context, 0);
    }

    public static void b(Context context) {
        if (d != null) {
            e = 100;
            d.setProgress(e);
        }
        a(context, 1);
    }

    public static void a(Context context, int i) {
        Intent intent = new Intent(context, PicksLoadingActivity.class);
        if (i == 0) {
            a = true;
        } else {
            intent.putExtra("tag_close_dialog", true);
        }
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        g.a(c, "oncreate");
        boolean booleanExtra = getIntent().getBooleanExtra("tag_close_dialog", false);
        g.a(c, "isClose" + booleanExtra);
        if (booleanExtra) {
            finish();
            return;
        }
        requestWindowFeature(2);
        requestWindowFeature(5);
        setContentView(R.layout.activity_picks_loading);
        g.b(Const.TAG, a((ViewGroup) getWindow().getDecorView(), 0));
        d();
        e();
    }

    private void d() {
        try {
            d = (ProgressBar) findViewById(R.id.wait_progressbar);
            d.setProgress(0);
            g.b(c, "initUI");
            d.setMax(100);
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
            finish();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        g.a(c, "onNewIntent");
        if (intent.getBooleanExtra("tag_close_dialog", false)) {
            finish();
        }
    }

    private void e() {
        if (VERSION.SDK_INT >= 11) {
            a(0, 80, true);
        } else {
            b.postDelayed(new a(80, 8), 50);
        }
        b.postDelayed(new Runnable(this) {
            final /* synthetic */ PicksLoadingActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f();
            }
        }, 500);
    }

    private void f() {
        if (VERSION.SDK_INT >= 11) {
            a(e, 94, false);
        } else {
            b.postDelayed(new a(94, 2), 50);
        }
    }

    @TargetApi(11)
    public void a(int i, int i2, boolean z) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
        if (z) {
            ofInt.setDuration(500);
        }
        ofInt.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ PicksLoadingActivity a;

            {
                this.a = r1;
            }

            @TargetApi(11)
            public void onAnimationUpdate(ValueAnimator animation) {
                PicksLoadingActivity.e = ((Integer) animation.getAnimatedValue()).intValue();
                g.b(PicksLoadingActivity.c, "attrAnimation status =" + PicksLoadingActivity.e);
                PicksLoadingActivity.d.setProgress(PicksLoadingActivity.e);
            }
        });
        ofInt.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        g.b(c, "onDestory");
        e = 0;
    }
}
