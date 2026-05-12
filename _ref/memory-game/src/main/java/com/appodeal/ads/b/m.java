package com.appodeal.ads.b;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.appodeal.ads.utils.g;
import com.appodeal.ads.utils.h;
import com.appodeal.ads.utils.u;
import java.util.ArrayList;

public class m extends r {
    private static o b;

    public static o f() {
        if (b == null) {
            r rVar = null;
            if (an.a(new String[0])) {
                rVar = new m();
            }
            b = new o("debug", g(), rVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
    }

    public void a(Activity activity, final int i) {
        final View relativeLayout = new RelativeLayout(activity);
        ArrayList arrayList = new ArrayList();
        g.a(arrayList, ((s) n.p.get(i)).d, false, g.a(arrayList, ((s) n.p.get(i)).c, true, 0));
        View listView = new ListView(activity);
        listView.setAdapter(new h(activity, arrayList));
        listView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ m c;

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((ViewGroup) relativeLayout.getParent()).removeView(relativeLayout);
                u uVar = (u) adapterView.getAdapter().getItem(i);
                int size = uVar.a - ((s) n.p.get(i)).c.size();
                if (size < 0) {
                    n.a(uVar.a, true, i);
                } else {
                    n.b(size, true, i);
                }
            }
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((h) adapterView.getAdapter()).a();
                return true;
            }
        });
        listView.setCacheColorHint(Color.parseColor("#FF555555"));
        listView.setDivider(new ColorDrawable(-1));
        listView.setDividerHeight(1);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setBackgroundColor(Color.parseColor("#FF555555"));
        relativeLayout.setTag("appodeal");
        relativeLayout.setClickable(true);
        relativeLayout.addView(listView);
        activity.addContentView(relativeLayout, layoutParams);
    }

    public boolean e() {
        return true;
    }
}
