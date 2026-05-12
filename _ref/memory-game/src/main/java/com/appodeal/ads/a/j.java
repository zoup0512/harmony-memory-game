package com.appodeal.ads.a;

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
import com.appodeal.ads.g.b;
import com.appodeal.ads.h;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.utils.g;
import com.appodeal.ads.utils.u;
import java.util.ArrayList;

public class j extends k {
    private static h c;

    public static h h() {
        if (c == null) {
            k kVar = null;
            if (an.a(new String[0])) {
                kVar = new j();
            }
            c = new h("debug_banner_320", kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
    }

    public void a(Activity activity, h hVar, final int i, b bVar, boolean z, b bVar2) {
        final View relativeLayout = new RelativeLayout(activity);
        ArrayList arrayList = new ArrayList();
        g.a(arrayList, ((l) com.appodeal.ads.g.x.get(i)).d, false, g.a(arrayList, ((l) com.appodeal.ads.g.x.get(i)).c, true, 0));
        View listView = new ListView(activity);
        listView.setAdapter(new com.appodeal.ads.utils.h(activity, arrayList));
        listView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ j c;

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((ViewGroup) relativeLayout.getParent()).removeView(relativeLayout);
                u uVar = (u) adapterView.getAdapter().getItem(i);
                int size = uVar.a - ((l) com.appodeal.ads.g.x.get(i)).c.size();
                if (size < 0) {
                    com.appodeal.ads.g.a(uVar.a, true, i);
                } else {
                    com.appodeal.ads.g.b(size, true, i);
                }
            }
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((com.appodeal.ads.utils.h) adapterView.getAdapter()).a();
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
