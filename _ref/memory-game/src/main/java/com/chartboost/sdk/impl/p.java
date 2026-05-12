package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e.a;

@SuppressLint({"ViewConstructor"})
public class p extends o {
    private t a;
    private Button b;
    private av c;
    private a d;

    public p(t tVar, Context context) {
        super(tVar, context);
        this.a = tVar;
        this.b = new Button(context);
        this.b.setTextColor(-14571545);
        this.b.setText("Preview");
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ p a;

            {
                this.a = r1;
            }

            public void onClick(View v) {
                this.a.c();
            }
        });
        addView(this.b, 2);
    }

    public void a(a aVar, int i) {
        super.a(aVar, i);
        this.d = aVar;
    }

    private void c() {
        CBLogging.c(this, "play the video");
        if (this.c == null) {
            this.c = new av(getContext());
            this.a.e().addView(this.c, new LayoutParams(-1, -1));
            this.c.setVisibility(8);
        }
        this.c.a().a(new OnCompletionListener(this) {
            final /* synthetic */ p a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer arg0) {
                aw.a(false, this.a.c);
            }
        });
        aw.a(true, this.c);
        this.c.a().a();
    }
}
