package com.appodeal.ads;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class LoaderActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (Appodeal.c != null) {
                Appodeal.c.finish();
                Appodeal.c.overridePendingTransition(0, 0);
            }
            Appodeal.c = this;
            View relativeLayout = new RelativeLayout(this);
            relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
            relativeLayout.setBackgroundColor(Color.parseColor("#7F000000"));
            View progressBar = new ProgressBar(this);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(13, -1);
            progressBar.setLayoutParams(layoutParams);
            relativeLayout.addView(progressBar);
            setContentView(relativeLayout);
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ LoaderActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        s a = n.a();
                        if (a != null) {
                            a.x = false;
                        }
                        ar a2 = ah.a();
                        if (a2 != null) {
                            a2.v = false;
                        }
                        a2 = ak.a();
                        if (a2 != null) {
                            a2.v = false;
                        }
                        this.a.finish();
                        this.a.overridePendingTransition(0, 0);
                    } catch (Exception e) {
                        this.a.finish();
                        this.a.overridePendingTransition(0, 0);
                    }
                }
            }, 3000);
        } catch (Exception e) {
            finish();
            overridePendingTransition(0, 0);
        }
    }

    protected void onPause() {
        super.onPause();
        s a = n.a();
        if (a != null) {
            a.x = false;
        }
        ar a2 = ah.a();
        if (a2 != null) {
            a2.v = false;
        }
        a2 = ak.a();
        if (a2 != null) {
            a2.v = false;
        }
        finish();
        overridePendingTransition(0, 0);
    }

    public void onBackPressed() {
        s a = n.a();
        if (a != null) {
            a.x = false;
        }
        ar a2 = ah.a();
        if (a2 != null) {
            a2.v = false;
        }
        a2 = ak.a();
        if (a2 != null) {
            a2.v = false;
        }
        finish();
        overridePendingTransition(0, 0);
    }
}
