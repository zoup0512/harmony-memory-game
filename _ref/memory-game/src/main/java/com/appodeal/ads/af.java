package com.appodeal.ads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.Native.NativeAdType;
import com.appodeal.ads.utils.j;
import com.appodeal.ads.utils.j.a;
import com.appodeal.ads.utils.k;
import com.appodeal.ads.utils.l;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.nexage.sourcekit.vast.model.VASTModel;

public abstract class af {
    protected List<NativeAd> a;
    private int b = 0;

    public abstract void a(Activity activity, int i, int i2, int i3);

    public void a(Activity activity, int i) {
    }

    public List<NativeAd> a() {
        return this.a;
    }

    public boolean b() {
        return false;
    }

    protected void a(int i, int i2, ac acVar, int i3) {
        for (NativeAd a : this.a) {
            a(a, i, i2, acVar);
        }
    }

    private void a(NativeAd nativeAd, int i, int i2, ac acVar) {
        String str;
        ab abVar = (ab) nativeAd;
        String iconUrl = nativeAd.getIconUrl();
        String mainImageUrl = nativeAd.getMainImageUrl();
        if (!abVar.containsVideo() || (!(mainImageUrl == null || mainImageUrl.isEmpty()) || Native.z == null)) {
            str = mainImageUrl;
        } else {
            abVar.e = Native.z;
            str = Native.z;
        }
        String g = abVar.g();
        String h = abVar.h();
        if (Native.o) {
            this.b++;
            a(abVar, i, i2, acVar, iconUrl);
        }
        if (Native.n || Native.A != NativeAdType.NoVideo) {
            this.b++;
            b(abVar, i, i2, acVar, str);
            if (Native.A == NativeAdType.Video) {
                if (g != null && !g.isEmpty()) {
                    this.b++;
                    c(abVar, i, i2, acVar, g);
                } else if (!(h == null || h.isEmpty())) {
                    this.b++;
                    d(abVar, i, i2, acVar, h);
                }
            }
        }
        b(i, i2, acVar);
    }

    private void a(ab abVar, int i, int i2, ac acVar, String str) {
        if (str == null || str.isEmpty()) {
            this.b--;
            b(i, i2, acVar);
            return;
        }
        final ab abVar2 = abVar;
        final int i3 = i;
        final int i4 = i2;
        final ac acVar2 = acVar;
        a(new j(new a(this) {
            final /* synthetic */ af e;

            public void a(Bitmap bitmap) {
                abVar2.b(bitmap);
                this.e.b = this.e.b - 1;
                this.e.b(i3, i4, acVar2);
            }
        }, str, false));
    }

    private void b(ab abVar, int i, int i2, ac acVar, String str) {
        if (str == null || str.isEmpty()) {
            this.b--;
            b(i, i2, acVar);
            return;
        }
        final ab abVar2 = abVar;
        final int i3 = i;
        final int i4 = i2;
        final ac acVar2 = acVar;
        a(new j(new a(this) {
            final /* synthetic */ af e;

            public void a(Bitmap bitmap) {
                abVar2.a(bitmap);
                this.e.b = this.e.b - 1;
                this.e.b(i3, i4, acVar2);
            }
        }, str, true));
    }

    private void c(ab abVar, int i, int i2, ac acVar, String str) {
        if (str == null || str.isEmpty()) {
            this.b--;
            b(i, i2, acVar);
            return;
        }
        final ab abVar2 = abVar;
        final int i3 = i;
        final int i4 = i2;
        final ac acVar2 = acVar;
        a(new k(Appodeal.b, new k.a(this) {
            final /* synthetic */ af e;

            public void a(Uri uri) {
                abVar2.a(uri);
                if (abVar2.getImage() == null && VERSION.SDK_INT >= 10 && uri != null && new File(uri.getPath()).exists()) {
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(Appodeal.b, uri);
                    Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(Long.parseLong(mediaMetadataRetriever.extractMetadata(9)), 2);
                    if (frameAtTime != null) {
                        abVar2.a(frameAtTime);
                    }
                }
                this.e.b = this.e.b - 1;
                this.e.b(i3, i4, acVar2);
            }

            public void a() {
                this.e.b = this.e.b - 1;
                this.e.b(i3, i4, acVar2);
            }
        }, str));
    }

    private void d(ab abVar, int i, int i2, ac acVar, String str) {
        final ab abVar2 = abVar;
        final int i3 = i;
        final int i4 = i2;
        final ac acVar2 = acVar;
        a(new l(Appodeal.b, new l.a(this) {
            final /* synthetic */ af e;

            public void a(Uri uri, VASTModel vASTModel) {
                abVar2.a(vASTModel);
                abVar2.a(uri);
                if (abVar2.getImage() == null && VERSION.SDK_INT >= 10 && uri != null && new File(uri.getPath()).exists()) {
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(Appodeal.b, uri);
                    Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(Long.parseLong(mediaMetadataRetriever.extractMetadata(9)), 2);
                    if (frameAtTime != null) {
                        abVar2.a(frameAtTime);
                    }
                }
                this.e.b = this.e.b - 1;
                this.e.b(i3, i4, acVar2);
            }

            public void a() {
                this.e.b = this.e.b - 1;
                this.e.b(i3, i4, acVar2);
            }
        }, str));
    }

    private void b(int i, int i2, ac acVar) {
        if (this.b == 0) {
            a(i, i2, acVar);
        }
    }

    public void a(int i, int i2, ac acVar) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            NativeAd nativeAd = (NativeAd) it.next();
            if (!ac.a(nativeAd)) {
                try {
                    ((ab) nativeAd).c();
                    ((ab) nativeAd).l();
                    it.remove();
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        }
        if (this.a.size() > 0) {
            ae.a(i, i2, acVar, this.a.size());
        } else {
            ae.a(i, i2, acVar);
        }
    }

    private void a(j jVar) {
        if (VERSION.SDK_INT >= 11) {
            jVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new a[0]);
        } else {
            jVar.execute(new a[0]);
        }
    }

    private void a(k kVar) {
        if (VERSION.SDK_INT >= 11) {
            kVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new k.a[0]);
        } else {
            kVar.execute(new k.a[0]);
        }
    }

    private void a(l lVar) {
        if (VERSION.SDK_INT >= 11) {
            lVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new l.a[0]);
        } else {
            lVar.execute(new l.a[0]);
        }
    }
}
