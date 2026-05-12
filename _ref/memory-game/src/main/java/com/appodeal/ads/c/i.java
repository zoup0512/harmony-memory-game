package com.appodeal.ads.c;

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
import com.appodeal.ads.aa;
import com.appodeal.ads.an;
import com.appodeal.ads.utils.g;
import com.appodeal.ads.utils.h;
import com.appodeal.ads.utils.u;
import com.appodeal.ads.v;
import com.appodeal.ads.w;
import com.appodeal.ads.z;
import java.util.ArrayList;

public class i extends z {
    private static w b;

    public static w h() {
        if (b == null) {
            z zVar = null;
            if (an.a(new String[0])) {
                zVar = new i();
            }
            b = new w("debug_mrec", zVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2) {
    }

    public void a(Activity activity, w wVar, final int i, boolean z) {
        final View relativeLayout = new RelativeLayout(activity);
        ArrayList arrayList = new ArrayList();
        g.a(arrayList, ((aa) v.t.get(i)).d, false, g.a(arrayList, ((aa) v.t.get(i)).c, true, 0));
        View listView = new ListView(activity);
        listView.setAdapter(new h(activity, arrayList));
        listView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ i c;

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((ViewGroup) relativeLayout.getParent()).removeView(relativeLayout);
                u uVar = (u) adapterView.getAdapter().getItem(i);
                int size = uVar.a - ((aa) v.t.get(i)).c.size();
                if (size < 0) {
                    v.a(uVar.a, true, i);
                } else {
                    v.b(size, true, i);
                }
            }
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ i a;

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

    public ViewGroup c() {
        return null;
    }

    public void a(View view) {
    }

    public boolean g() {
        return true;
    }
}
