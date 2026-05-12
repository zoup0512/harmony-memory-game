package com.appodeal.ads.e;

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
import com.appodeal.ads.ak;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.utils.g;
import com.appodeal.ads.utils.h;
import com.appodeal.ads.utils.u;
import java.util.ArrayList;

public class f extends aq {
    private static ap b;

    public static ap g() {
        if (b == null) {
            aq aqVar = null;
            if (an.a(new String[0])) {
                aqVar = new f();
            }
            b = new ap("debug_rewarded_video", h(), aqVar);
        }
        return b;
    }

    private static String[] h() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
    }

    public void a(Activity activity, final int i) {
        final View relativeLayout = new RelativeLayout(activity);
        ArrayList arrayList = new ArrayList();
        g.a(arrayList, ((ar) ak.m.get(i)).c);
        View listView = new ListView(activity);
        listView.setAdapter(new h(activity, arrayList));
        listView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ f c;

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((ViewGroup) relativeLayout.getParent()).removeView(relativeLayout);
                ak.a(((u) adapterView.getAdapter().getItem(i)).a, true, i);
            }
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ f a;

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

    public boolean f() {
        return true;
    }
}
