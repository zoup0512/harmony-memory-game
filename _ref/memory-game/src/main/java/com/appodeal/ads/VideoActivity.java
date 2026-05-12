package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.appodeal.ads.ao.b;

public class VideoActivity extends Activity {
    public RelativeLayout a;
    public ProgressBar b;
    private b c;
    private int d = -1;
    private ap e;
    private boolean f = true;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (getIntent().hasExtra("requestId") && getIntent().hasExtra("type")) {
                this.d = getIntent().getIntExtra("requestId", -1);
                this.c = (b) getIntent().getSerializableExtra("type");
                ap a;
                switch (this.c) {
                    case NON_REWARDED:
                        a = ah.a((Context) this, getIntent().getStringExtra("videoClass"));
                        if (a != null) {
                            this.e = a;
                            this.e.g().a(this, this.d);
                            return;
                        }
                        finish();
                        overridePendingTransition(0, 0);
                        aj.a();
                        return;
                    case REWARDED:
                        a = ak.a((Context) this, getIntent().getStringExtra("videoClass"));
                        if (a != null) {
                            this.e = a;
                            this.e.g().a(this, this.d);
                            return;
                        }
                        finish();
                        overridePendingTransition(0, 0);
                        am.a();
                        return;
                    default:
                        finish();
                        overridePendingTransition(0, 0);
                        return;
                }
                Appodeal.a(e);
                finish();
                overridePendingTransition(0, 0);
            }
            finish();
            overridePendingTransition(0, 0);
        } catch (Throwable e) {
            Appodeal.a(e);
            finish();
            overridePendingTransition(0, 0);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        as.a(this, this.c, this.d, this.e);
    }

    public void onBackPressed() {
        if (this.f) {
            finish();
            overridePendingTransition(0, 0);
        }
    }

    public void a(boolean z) {
        this.f = z;
    }
}
