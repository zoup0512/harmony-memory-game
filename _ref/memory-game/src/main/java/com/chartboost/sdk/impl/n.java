package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.f;

@SuppressLint({"ViewConstructor"})
public class n extends m {
    private ImageView a;

    public n(t tVar, Context context) {
        super(context);
        this.a = new ImageView(context);
        addView(this.a, new LayoutParams(-1, -1));
    }

    public void a(a aVar, int i) {
        a a = aVar.a("assets").a(CBUtility.a().a() ? DeviceInfo.ORIENTATION_PORTRAIT : DeviceInfo.ORIENTATION_LANDSCAPE);
        if (a.c()) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            String str = "";
            if (!(a.e("checksum") == null || a.e("checksum").isEmpty())) {
                str = a.e("checksum");
            }
            f.m().a(a.e("url"), str, null, this.a, bundle);
        }
    }

    public int a() {
        return CBUtility.a(110, getContext());
    }
}
