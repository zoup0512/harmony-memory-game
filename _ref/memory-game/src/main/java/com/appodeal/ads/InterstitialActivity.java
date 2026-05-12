package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class InterstitialActivity extends Activity {
    public RelativeLayout a;
    public ProgressBar b;
    private int c = -1;
    private o d;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (getIntent().hasExtra("requestId")) {
                this.c = getIntent().getIntExtra("requestId", -1);
                setRequestedOrientation(an.j(this));
                o a = n.a((Context) this, getIntent().getStringExtra("interstitialClass"));
                if (a != null) {
                    this.d = a;
                    this.d.g().a(this, this.c);
                    return;
                }
                finish();
                overridePendingTransition(0, 0);
                q.a();
                return;
            }
            finish();
            overridePendingTransition(0, 0);
        } catch (Throwable e) {
            Appodeal.a(e);
            finish();
            overridePendingTransition(0, 0);
            q.a();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        as.a(this, this.d, this.c);
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(0, 0);
    }
}
