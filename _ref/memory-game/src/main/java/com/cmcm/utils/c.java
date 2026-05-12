package com.cmcm.utils;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.picks.loader.d;
import com.cmcm.picks.loader.e;
import com.cmcm.picks.loader.g;
import com.cmcm.utils.f.a;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BuinessDataReporter */
public class c extends AsyncTask<Void, Void, Void> {
    private e a;
    private List<d> b;

    protected /* synthetic */ Object doInBackground(Object[] x0) {
        return a((Void[]) x0);
    }

    public void a(d dVar, e eVar) {
        this.a = eVar;
        this.b = new ArrayList();
        this.b.add(dVar);
    }

    private String a() {
        if (CMAdManager.mAdResource != 1) {
            return "http://unrcv.adkmob.com/rp/";
        }
        Object c = g.c();
        if (TextUtils.isEmpty(c)) {
            return "https://ssdk.adkmob.com/rp/";
        }
        return c;
    }

    protected Void a(Void... voidArr) {
        if (this.a != null) {
            a(this.a.a(), b());
        }
        return null;
    }

    private void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            f.a(a(), str + str2, new a(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a(InputStream inputStream) {
                }
            });
        }
    }

    private String b() {
        if (this.b == null || this.b.size() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("&attach=[");
        Object obj = 1;
        for (d dVar : this.b) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(",");
            }
            stringBuilder.append(dVar.a());
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
