package com.cmcm.utils;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.picks.market.MarketUtils;
import org.apache.http.HttpResponse;
import org.nexage.sourcekit.vast.model.VASTModel;

/* compiled from: ParseUrlUtils */
public class k {
    private a a;
    private b b = null;

    /* compiled from: ParseUrlUtils */
    public interface b {
        void a(String str);
    }

    /* compiled from: ParseUrlUtils */
    public class a extends AsyncTask<String, Void, String> {
        String a = "";
        String b = "";
        String c = "";
        final /* synthetic */ k d;

        public a(k kVar) {
            this.d = kVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] x0) {
            return a((String[]) x0);
        }

        protected /* synthetic */ void onPostExecute(Object x0) {
            a((String) x0);
        }

        protected String a(String... strArr) {
            String str = strArr[0];
            do {
                HttpResponse a = f.a(null, str, true);
                if (a != null && a.getStatusLine() != null) {
                    int statusCode = a.getStatusLine().getStatusCode();
                    if (statusCode != 301 && statusCode != VASTModel.ERROR_CODE_EXCEEDED_WRAPPER_LIMIT) {
                        break;
                    }
                    str = a.getFirstHeader("Location").getValue();
                    if (str == null) {
                        break;
                    }
                } else {
                    break;
                }
            } while (!MarketUtils.isGooglePlayUrl(str));
            return str;
        }

        protected void a(final String str) {
            ThreadHelper.postOnUiThread(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (TextUtils.isEmpty(str) || MarketUtils.isGooglePlayUrl(str)) {
                        this.b.d.b(str);
                        return;
                    }
                    l lVar = new l(CMAdManager.getContext());
                    lVar.a(new com.cmcm.utils.l.a(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(String str) {
                            this.a.b.d.b(str);
                        }
                    });
                    lVar.a(str, this.b.a, this.b.b, this.b.c);
                }
            });
        }
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    private void b(String str) {
        if (this.b != null) {
            this.b.a(str);
        }
    }

    public a a(String str) {
        return a(str, "", "", "");
    }

    public a a(String str, String str2, String str3, String str4) {
        a aVar = new a(this);
        if (VERSION.SDK_INT >= 11) {
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str, str2, str3, str4});
        } else {
            aVar.execute(new String[]{str, str2, str3, str4});
        }
        this.a = aVar;
        return aVar;
    }
}
