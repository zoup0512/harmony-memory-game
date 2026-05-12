package com.appodeal.ads.c;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.aa;
import com.appodeal.ads.an;
import com.appodeal.ads.v;
import com.appodeal.ads.w;
import com.appodeal.ads.z;
import com.mopub.common.AdType;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.MRAIDViewListener;

public class q extends z {
    private static w b;
    private MRAIDView c;
    private int d;
    private int e;

    public static w getInstance(String str, String[] strArr) {
        if (b == null) {
            z zVar = null;
            if (an.a(strArr)) {
                zVar = new q();
            }
            b = new w(str, zVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2) {
        this.a = ((aa) v.t.get(i)).l.getString(AdType.HTML);
        this.d = Integer.parseInt(((aa) v.t.get(i)).l.getString("width"));
        this.e = Integer.parseInt(((aa) v.t.get(i)).l.getString("height"));
        MRAIDViewListener nVar = new n(b, i, i2);
        String[] strArr = null;
        MRAIDViewListener mRAIDViewListener = nVar;
        this.c = new MRAIDView(Appodeal.b, null, this.a, strArr, nVar, mRAIDViewListener, this.d, this.e, a(b.a(), i));
    }

    public ViewGroup c() {
        return this.c;
    }

    public int d() {
        return Math.round(((float) this.d) * an.i(Appodeal.b));
    }

    public int e() {
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
