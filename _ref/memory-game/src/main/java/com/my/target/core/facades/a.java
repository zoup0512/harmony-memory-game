package com.my.target.core.facades;

import android.content.Context;
import android.text.TextUtils;
import com.my.target.core.async.commands.b;
import com.my.target.core.async.commands.e;
import com.my.target.core.models.c;
import com.my.target.core.models.d;

/* compiled from: AbstractAd */
public abstract class a implements g {
    protected c adData;
    protected Context context;
    private com.my.target.core.async.commands.b.a<c> executeListener = new com.my.target.core.async.commands.b.a<c>(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(b bVar, Object obj) {
            c cVar = (c) obj;
            if (cVar != null) {
                this.a.adData = cVar;
                this.a.onLoad(cVar);
                return;
            }
            this.a.onLoadError(bVar.a());
        }
    };
    protected com.my.target.core.a params;

    protected abstract void onLoad(c cVar);

    protected abstract void onLoadError(String str);

    protected void init(com.my.target.core.a aVar, Context context) {
        this.params = aVar;
        this.context = context;
    }

    public void load() {
        b dVar;
        com.my.target.core.a aVar = this.params;
        Context context = this.context;
        String str = com.my.target.core.net.a.a(aVar.c()) + aVar.d() + "/";
        if (!TextUtils.isEmpty(com.my.target.core.net.a.a)) {
            str = com.my.target.core.net.a.a;
        }
        d dVar2 = new d(str);
        if ("appwall".equals(aVar.c())) {
            dVar = new com.my.target.core.async.commands.d(dVar2, aVar, context);
        } else if ("instreamads".equals(aVar.c())) {
            dVar = new e(dVar2, aVar, context);
        } else {
            dVar = new com.my.target.core.async.commands.c(dVar2, aVar, context);
        }
        dVar.a(this.executeListener);
        dVar.b();
    }

    public void setTrackingEnvironmentEnabled(boolean z) {
        if (this.params != null) {
            this.params.a(z);
        }
    }
}
