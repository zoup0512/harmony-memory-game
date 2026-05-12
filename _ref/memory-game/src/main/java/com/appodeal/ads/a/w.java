package com.appodeal.ads.a;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.mopub.common.AdType;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.MRAIDViewListener;

public class w extends k {
    private static h c;
    private MRAIDView d;
    private int e;

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new w();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        this.a = ((l) g.x.get(i)).l.getString(AdType.HTML);
        this.e = Integer.parseInt(((l) g.x.get(i)).l.getString("width"));
        this.b = Integer.parseInt(((l) g.x.get(i)).l.getString("height"));
        if (this.e > g.d() || this.b > g.c()) {
            this.e = (this.e * 50) / this.b;
            this.b = 50;
            if (this.e > g.d() || this.b > g.c()) {
                j.b(i, i2, c);
                return;
            }
        }
        MRAIDViewListener qVar = new q(c, i, i2);
        String[] strArr = null;
        MRAIDViewListener mRAIDViewListener = qVar;
        this.d = new MRAIDView(Appodeal.b, null, this.a, strArr, qVar, mRAIDViewListener, this.e, this.b, a(c.a(), i));
    }

    public ViewGroup c() {
        return this.d;
    }

    public int d() {
        return Math.round(((float) this.e) * an.i(Appodeal.b));
    }

    public void a(View view) {
        if (view instanceof MRAIDView) {
            ((MRAIDView) view).destroy();
        }
    }

    public boolean g() {
        return true;
    }
}
