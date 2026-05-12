package com.cmcm.picks.init;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.picks.loader.Ad;
import com.cmcm.picks.loader.b;
import com.cmcm.picks.loader.g;
import com.cmcm.picks.loader.h;
import com.cmcm.utils.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PicksMobBase */
public abstract class a {
    private static a mInstance;
    public static IPicksBrowserCallBack sIPicksCallBack;

    public abstract void downloadSuccessReport(String str, Ad ad, String str2);

    public abstract boolean ishaveInternalDown();

    public abstract void reportDowned(String str);

    public abstract void reportInstall(String str);

    public static a getInstance() {
        if (mInstance == null) {
            synchronized (a.class) {
                if (mInstance == null) {
                    mInstance = new PicksMob();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        com.cmcm.picks.gaid.a.c().b();
        initUserAgent();
    }

    private void initUserAgent() {
        if (VERSION.SDK_INT >= 10) {
            g.d();
        }
    }

    public void loadad(int posid, ICallBack cb, int adn) {
        loadAd(0, posid, cb, adn, false, true);
    }

    public void loadad(int posid, ICallBack cb, int adn, boolean filterShowed) {
        loadAd(0, posid, cb, adn, false, filterShowed);
    }

    private void loadAd(int pageNum, int posid, ICallBack cb, int adn, boolean isEnforceLoadFromRemote, boolean filterShowed) {
        if (adn <= 0 || adn > 30 || pageNum < 0) {
            onError(cb);
        } else if (i.e(CMAdManager.getContext())) {
            final ICallBack iCallBack = cb;
            final boolean z = filterShowed;
            final int i = pageNum;
            final int i2 = posid;
            final int i3 = adn;
            b anonymousClass1 = new b(this, pageNum, adn, String.valueOf(posid)) {
                final /* synthetic */ a f;

                public void a(h hVar) {
                    this.f.onError(iCallBack);
                }

                public void b(h hVar) {
                    super.b(hVar);
                    if (hVar == null) {
                        this.f.onError(iCallBack);
                        return;
                    }
                    List arrayList = new ArrayList();
                    if (hVar.b() == null || hVar.b().size() <= 0) {
                        this.f.onError(iCallBack);
                        return;
                    }
                    arrayList.addAll(hVar.b());
                    if (z) {
                        Object obj = arrayList.size() > 0 ? 1 : null;
                        this.f.filterShowed(arrayList);
                        if (obj != null && arrayList.size() == 0) {
                            this.f.loadFromNet(i, i2, iCallBack, i3);
                            return;
                        }
                    }
                    hVar.b(arrayList);
                    if (arrayList.size() > 0) {
                        this.f.onSuccess(iCallBack, arrayList);
                    } else {
                        this.f.onError(iCallBack);
                    }
                }

                protected void onPreExecute() {
                    super.onPreExecute();
                    if (iCallBack != null) {
                        iCallBack.onPreExecute();
                    }
                }
            };
            if (isEnforceLoadFromRemote) {
                anonymousClass1.c();
            }
            if (VERSION.SDK_INT >= 11) {
                anonymousClass1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
            } else {
                anonymousClass1.execute(new Void[0]);
            }
        } else {
            onError(cb);
        }
    }

    private void onError(ICallBack cb) {
        if (cb != null) {
            cb.onLoadError();
        }
    }

    private void onSuccess(ICallBack cb, List<Ad> ads) {
        if (cb != null) {
            cb.onLoadSuccess(ads);
        }
    }

    private void filterShowed(List<Ad> list) {
        if (list != null && list.size() > 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Ad ad = (Ad) it.next();
                if (ad == null || ad.isShowed()) {
                    it.remove();
                }
            }
        }
    }

    private void loadFromNet(int pageNum, int posid, ICallBack cb, int adn) {
        loadAd(pageNum, posid, cb, adn, true, true);
    }

    public void setPicksBrowserCallBack(IPicksBrowserCallBack callBack) {
        sIPicksCallBack = callBack;
    }

    public IPicksBrowserCallBack getIPicksCallBack() {
        return sIPicksCallBack;
    }
}
