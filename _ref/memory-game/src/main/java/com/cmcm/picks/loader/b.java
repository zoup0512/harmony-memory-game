package com.cmcm.picks.loader;

import android.os.AsyncTask;
import com.cmcm.picks.loader.f.a;
import com.cmcm.utils.ThreadHelper;
import com.cmcm.utils.e;
import com.cmcm.utils.f;
import com.cmcm.utils.g;
import com.facebook.appevents.AppEventsConstants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.net.URI;
import java.util.List;

/* compiled from: AppLoader */
public class b extends AsyncTask<Void, Void, h> {
    private boolean a = false;
    private boolean b = false;
    private int c = -1;
    private long d;
    protected a g;
    protected String h;
    protected int i = 0;
    protected int j = 10;

    protected /* synthetic */ Object doInBackground(Object[] x0) {
        return a((Void[]) x0);
    }

    protected /* synthetic */ void onPostExecute(Object x0) {
        c((h) x0);
    }

    protected String a() {
        return this.h;
    }

    public b(int i, int i2, String str) {
        this.h = str;
        this.i = i;
        this.j = i2;
    }

    protected URI b() {
        this.g = new a();
        this.g.a(this.h).c(this.i).b(this.j);
        if (this.i == 0) {
            this.g.d(0);
        } else {
            this.g.d(m());
        }
        return this.g.a();
    }

    protected void onPreExecute() {
    }

    protected h a(Void... voidArr) {
        if (!d()) {
            return j();
        }
        e();
        h a = a(b());
        g();
        if (a != null && e(a)) {
            e.a().a(a() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.i, a);
            d(a);
        }
        this.b = false;
        return a;
    }

    protected final void c(final h hVar) {
        ThreadHelper.postOnUiThread(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                if (hVar == null) {
                    this.b.a(hVar);
                } else if (hVar.c()) {
                    this.b.b(hVar);
                } else {
                    this.b.a(hVar);
                }
            }
        });
    }

    public void c() {
        this.b = true;
    }

    protected boolean d() {
        return i() || this.i != 0 || this.b;
    }

    protected void d(h hVar) {
        if (this.i == 0) {
            l();
        }
    }

    protected h a(URI uri) {
        if (uri == null) {
            return null;
        }
        String b = f.b(null, uri.toASCIIString(), true);
        if (b != null) {
            return h.a(this.h, b);
        }
        return null;
    }

    protected void e() {
        if (k() && i.a().b(a()) > 0) {
            e.a().b(a());
            ThreadHelper.post(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        i.a().a(this.a.a());
                    } catch (Exception e) {
                        if (g.a) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    private boolean k() {
        return this.i == 0;
    }

    private void l() {
        this.d = System.currentTimeMillis();
        g.a(h(), Long.valueOf(this.d));
    }

    protected long f() {
        if (this.d <= 0) {
            this.d = g.b(h(), Long.valueOf(0)).longValue();
        }
        return this.d;
    }

    protected void g() {
        e.a().b(a());
        this.d = 0;
        g.a(h(), Long.valueOf(0));
    }

    protected String h() {
        return a() + "_cache_time_" + a.a;
    }

    protected boolean i() {
        return System.currentTimeMillis() - (g.a(Long.valueOf(this.h)) + f()) > 0;
    }

    protected h j() {
        if (i()) {
            if (f() > 0) {
                e();
            }
            return null;
        }
        h a = e.a().a(a() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.i);
        if (a != null) {
            return a;
        }
        a = new h();
        List a2 = i.a().a(a(), null, null, null);
        if (a2.isEmpty()) {
            return null;
        }
        a.a(a2);
        e.a().a(a(), a);
        return a;
    }

    public boolean e(h hVar) {
        return i.a().a(a(), hVar.b()) == hVar.b().size();
    }

    public void a(h hVar) {
    }

    public void b(h hVar) {
        g.a(a() + "_pageloader_offset", hVar.a() + "");
    }

    private int m() {
        return Integer.parseInt(g.b(a() + "_pageloader_offset", AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }
}
