package com.cmcm.picks.vastvideo;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdError;
import com.cmcm.adsdk.CMAdManagerFactory;
import com.cmcm.adsdk.ImageDownloadListener;
import com.cmcm.picks.init.ICallBack;
import com.cmcm.picks.init.a;
import com.cmcm.picks.loader.Ad;
import com.cmcm.picks.vastvideo.h.b;
import com.cmcm.utils.ThreadHelper;
import com.cmcm.utils.g;
import com.cmcm.utils.i;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VastAd {
    public static final String TAG = VastAd.class.getSimpleName();
    private boolean a = false;
    private Context b;
    private String c;
    private boolean d = false;
    private VastVideoListener e;
    private VastVideoLogListener f;
    private Ad g;
    private VastModel h;
    private List<Ad> i = new ArrayList();

    public interface VastVideoListener {
        void onLoadError(int i);

        void onLoadSuccess();
    }

    public interface VastVideoLogListener {
        void onLogPrint(int i);
    }

    public VastAd(Context context, String posId) {
        this.b = context.getApplicationContext();
        this.c = posId;
    }

    public String getVastTitle() {
        return getVastModel().a();
    }

    public VastModel getVastModel() {
        return this.h;
    }

    private void a(Ad ad) {
        this.g = ad;
        if (ad != null && ad.getCreateTime() == 0) {
        }
    }

    private void a(VastModel vastModel) {
        this.h = vastModel;
    }

    public void setVastVideoLogListener(VastVideoLogListener vastVideoLogListener) {
        this.f = vastVideoLogListener;
    }

    private boolean a() {
        if (this.h == null || this.h.b() || TextUtils.isEmpty(this.h.w()) || this.g == null || this.g.isShowed() || !this.g.isAvailAble()) {
            return false;
        }
        return true;
    }

    private boolean b() {
        return this.h != null && b.a().a(this.h.w());
    }

    public void setIsNotWifiDownLoad(boolean isNotWifiDownLoad) {
        this.d = isNotWifiDownLoad;
    }

    public void setListener(VastVideoListener vastVideoListener) {
        this.e = vastVideoListener;
    }

    public void load(VastVideoListener vastVideoListener) {
        this.e = vastVideoListener;
        if (a()) {
            g.a(TAG, "have valid vastad, and video file path = " + getVastModel().w());
            if (b()) {
                g.a(TAG, "file not exist, but ad is valid");
                b(this.g);
                return;
            }
            a(true, true, 0);
        } else if (this.b == null || TextUtils.isEmpty(this.c) || this.e == null) {
            a(true, false, 30000);
        } else if (VERSION.SDK_INT < 14 || !i.e(this.b)) {
            a(true, false, (int) CMAdError.VAST_NTEWORK_ERROR);
        } else if (!this.d && !i.c(this.b)) {
            a(true, false, (int) CMAdError.VAST_NTEWORK_ERROR);
        } else if (this.a) {
            a(false, false, (int) CMAdError.VAST_LOADING_ERROR);
        } else {
            this.a = true;
            c();
            if (this.i == null || this.i.isEmpty()) {
                g.a(TAG, "picks to load ad");
                a.getInstance().loadad(Integer.valueOf(this.c).intValue(), new ICallBack(this) {
                    final /* synthetic */ VastAd a;

                    {
                        this.a = r1;
                    }

                    public void onLoadSuccess(List<Ad> listAd) {
                        if (listAd == null || listAd.isEmpty()) {
                            onLoadError();
                            return;
                        }
                        g.a(VastAd.TAG, "picks load success ,and size =" + listAd.size());
                        this.a.i = this.a.a((List) listAd);
                        if (this.a.i == null || this.a.i.isEmpty()) {
                            onLoadError();
                        } else {
                            this.a.b((Ad) this.a.i.remove(0));
                        }
                    }

                    public void onLoadError() {
                        g.a(VastAd.TAG, "picks load error");
                        this.a.a(true, false, (int) CMAdError.NO_FILL_ERROR);
                    }

                    public void onPreExecute() {
                    }
                }, 5, true);
                return;
            }
            g.a(TAG, "picks have vaild cache");
            b((Ad) this.i.remove(0));
        }
    }

    private void c() {
        if (this.i != null && !this.i.isEmpty()) {
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                Ad ad = (Ad) it.next();
                if (ad != null && (ad.isShowed() || !ad.isAvailAble())) {
                    it.remove();
                }
            }
        }
    }

    private List<Ad> a(List<Ad> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Ad ad = (Ad) it.next();
            if (ad != null && (ad.getAppShowType() != Ad.SHOW_TYPE_VAST || TextUtils.isEmpty(ad.getHtml()))) {
                ad.setShowed(true);
                it.remove();
            }
        }
        return list;
    }

    private void a(int i) {
        b(i);
        ThreadHelper.postOnUiThread(new Runnable(this) {
            final /* synthetic */ VastAd a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d();
            }
        });
    }

    private void b(int i) {
        if (this.f != null) {
            this.f.onLogPrint(i);
        }
    }

    private void d() {
        if (this.g != null) {
            this.g.setShowed(true);
            g.a(TAG, "the ad =[" + this.g.getTitle() + "] is invalid，issue load next");
        }
        c();
        if (this.i == null || this.i.isEmpty()) {
            a(true, false, (int) CMAdError.VAST_NO_VALID_AD);
        } else {
            b((Ad) this.i.remove(0));
        }
    }

    private void b(Ad ad) {
        if (ad == null) {
            a((int) CMAdError.VAST_NO_VALID_AD);
            return;
        }
        try {
            a(ad);
            String a = f.a(ad.getHtml());
            g.a(TAG, "resolve vast url, url =" + a);
            parseVastXML(a);
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
            a((int) CMAdError.VAST_TAG_ERROR);
        }
    }

    public void parseVastXML(String url) {
        if (TextUtils.isEmpty(url)) {
            a((int) CMAdError.VAST_TAG_ERROR);
        } else {
            new g().a(url, new g.a(this) {
                final /* synthetic */ VastAd a;

                {
                    this.a = r1;
                }

                public void a(VastModel vastModel) {
                    ThreadHelper.assertOnUiThread();
                    Object obj = null;
                    if (vastModel != null) {
                        String a = vastModel.a(this.a.b);
                        g.a(VastAd.TAG, "vast:parse vast success, and media file url= :" + a);
                        if (!TextUtils.isEmpty(a)) {
                            obj = 1;
                            vastModel.a(this.a.g);
                            this.a.a(vastModel);
                            this.a.a(this.a.b, a);
                        }
                    }
                    if (obj == null) {
                        a(vastModel, "parse model status is wrong");
                    }
                }

                public void a(VastModel vastModel, String str) {
                    if (vastModel != null) {
                        a.a(vastModel);
                    }
                    g.a(VastAd.TAG, "vast:parse failed, error=" + str + ",then issue next");
                    this.a.a((int) CMAdError.VAST_PARSE_MODEL_ERROR);
                }
            });
        }
    }

    private void a(Context context, String str) {
        ThreadHelper.post(new h(context, str, new b(this) {
            final /* synthetic */ VastAd a;

            {
                this.a = r1;
            }

            public void a(String str) {
                boolean z = true;
                ThreadHelper.assertOnUiThread();
                if (!TextUtils.isEmpty(str)) {
                    g.a(VastAd.TAG, "vast:download complete, the filepath = " + str);
                    boolean a = this.a.a(str);
                    g.a(VastAd.TAG, "save cache ad is " + a);
                    if (a) {
                        this.a.a(true, true, 0);
                        b.a().a(str, System.currentTimeMillis());
                        if (!z) {
                            b(String.valueOf(CMAdError.VAST_DOWNLOAD_ERROR));
                        }
                    }
                }
                z = false;
                if (!z) {
                    b(String.valueOf(CMAdError.VAST_DOWNLOAD_ERROR));
                }
            }

            public void b(String str) {
                g.d(VastAd.TAG, "download video file fail：" + str);
                this.a.a((int) CMAdError.VAST_DOWNLOAD_ERROR);
            }
        }));
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            File file = new File(str);
            if (file == null || !file.exists()) {
                return false;
            }
            getVastModel().g(str);
            ImageDownloadListener imageDownloadListener = CMAdManagerFactory.getImageDownloadListener();
            if (!(imageDownloadListener == null || getVastModel() == null)) {
                if (!TextUtils.isEmpty(getVastModel().v())) {
                    imageDownloadListener.getBitmap(getVastModel().v(), null);
                }
                if (!TextUtils.isEmpty(getVastModel().u())) {
                    imageDownloadListener.getBitmap(getVastModel().u(), null);
                }
            }
            return true;
        } catch (Exception e) {
            g.d(TAG, "pushVastAd : " + e.toString());
            return false;
        }
    }

    private void a(boolean z, boolean z2, int i) {
        if (z) {
            this.a = false;
        }
        if (this.e == null) {
            return;
        }
        if (z2) {
            this.e.onLoadSuccess();
        } else {
            this.e.onLoadError(i);
        }
    }

    public VastView createVastView(VastVideoProgressListener vastVideoProgressListener) {
        g.d(TAG, "vast create view, and cache valid = : " + a() + ",file not exist =" + b());
        if (!a() || b()) {
            return null;
        }
        g.d(TAG, "use vast model ,and model video file path " + getVastModel().w());
        return new VastView(this.b, getVastModel(), vastVideoProgressListener);
    }
}
