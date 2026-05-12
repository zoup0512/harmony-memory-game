package com.appodeal.ads.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.g.b;
import com.appodeal.ads.h;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.networks.n;
import com.my.target.ads.MyTargetView;

public class r extends k {
    private static h c;
    private MyTargetView d;

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new r();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        int i3 = ((l) g.x.get(i)).l.getInt("mailru_slot_id");
        this.d = new MyTargetView(activity);
        this.b = 50;
        if (g.s) {
            this.d.setLayoutParams(new LayoutParams(-1, Math.round(((float) this.b) * an.i(activity))));
        } else {
            this.d.setLayoutParams(new LayoutParams(Math.round(320.0f * an.i(activity)), Math.round(((float) this.b) * an.i(activity))));
        }
        this.d.init(i3, n.a((Context) activity), Boolean.valueOf(false));
        this.d.setListener(new s(c, i, i2));
        this.d.load();
    }

    public void a(Activity activity, h hVar, int i, b bVar, boolean z, b bVar2) {
        this.d.start();
        super.a(activity, hVar, i, bVar, z, bVar2);
    }

    public ViewGroup c() {
        return this.d;
    }

    public void a(View view) {
        if (view instanceof MyTargetView) {
            ((MyTargetView) view).destroy();
        }
    }
}
