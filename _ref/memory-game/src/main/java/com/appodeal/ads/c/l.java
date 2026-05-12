package com.appodeal.ads.c;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.aa;
import com.appodeal.ads.an;
import com.appodeal.ads.v;
import com.appodeal.ads.w;
import com.appodeal.ads.y;
import com.appodeal.ads.z;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.MRAIDViewListener;

public class l extends z {
    private static w b;
    private MRAIDView c;

    private class a implements com.appodeal.ads.networks.l.a {
        final /* synthetic */ l a;

        private a(l lVar) {
            this.a = lVar;
        }

        public void a(int i, int i2) {
            y.b(i, i2, l.b);
        }

        public void a(String str, int i, int i2) {
            try {
                MRAIDViewListener nVar = new n(l.b, i, i2);
                this.a.a = str;
                this.a.c = new MRAIDView(Appodeal.b, null, this.a.a, null, nVar, nVar, false, 300, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, this.a.a(l.b.a(), i), false);
            } catch (Throwable e) {
                Appodeal.a(e);
                y.b(i, i2, l.b);
            }
        }
    }

    public static w getInstance(String str, String[] strArr) {
        if (b == null) {
            z zVar = null;
            if (an.a(strArr)) {
                zVar = new l();
            }
            b = new w(str, zVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((aa) v.t.get(i)).l.getString("url");
        Integer valueOf = Integer.valueOf(((aa) v.t.get(i)).l.optInt("speed_limit", 100));
        if (valueOf.intValue() != -1 || an.b((Context) activity).c) {
            com.appodeal.ads.networks.l lVar = new com.appodeal.ads.networks.l(activity, new a(), i, i2, string, valueOf);
            return;
        }
        y.b(i, i2, b);
    }

    public ViewGroup c() {
        this.c.show();
        return this.c;
    }

    public int e() {
        return Math.round(250.0f * an.i(Appodeal.b));
    }

    public void a(View view) {
        if (view instanceof MRAIDView) {
            ((MRAIDView) view).destroy();
        }
    }
}
