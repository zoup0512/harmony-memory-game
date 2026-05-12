package com.chartboost.sdk;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.bg;
import com.chartboost.sdk.impl.bh;
import com.chartboost.sdk.impl.bi;
import com.chartboost.sdk.impl.d;
import com.chartboost.sdk.impl.f;
import com.chartboost.sdk.impl.g;
import com.chartboost.sdk.impl.h;
import com.chartboost.sdk.impl.k;
import com.chartboost.sdk.impl.l;

public class i {
    private static i a = new i();

    public static i a() {
        return a;
    }

    public ay a(Context context) {
        return new ay(context);
    }

    public bh b(Context context) {
        return new bh(context);
    }

    public bg a(View view, ViewGroup viewGroup, View view2, bh bhVar, bi biVar) {
        return new bg(view, viewGroup, view2, bhVar, biVar);
    }

    public RelativeLayout c(Context context) {
        return new RelativeLayout(context);
    }

    public av d(Context context) {
        return new av(context);
    }

    public l a(Context context, f fVar) {
        return new l(context, fVar);
    }

    public d b(Context context, f fVar) {
        return new d(context, fVar);
    }

    public com.chartboost.sdk.impl.i c(Context context, f fVar) {
        return new com.chartboost.sdk.impl.i(context, fVar);
    }

    public h e(Context context) {
        return new h(context);
    }

    public k f(Context context) {
        return new k(context);
    }

    public TextView g(Context context) {
        return new TextView(context);
    }

    public ImageView h(Context context) {
        return new ImageView(context);
    }

    public g d(Context context, f fVar) {
        return new g(context, fVar);
    }
}
