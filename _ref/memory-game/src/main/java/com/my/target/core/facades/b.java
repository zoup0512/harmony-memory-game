package com.my.target.core.facades;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.my.target.Tracer;
import com.my.target.ads.CustomParams;
import com.my.target.ads.MyTargetVideoView;
import com.my.target.core.models.banners.f;
import com.my.target.core.models.c;
import com.my.target.core.models.g;
import com.my.target.core.models.sections.e;
import com.my.target.core.utils.l;
import com.my.target.core.utils.n;
import com.my.target.nativeads.models.ImageData;
import com.my.target.nativeads.models.VideoData;
import com.my.target.nativeads.views.MediaAdView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: AbstractNativeAd */
public abstract class b<T> extends a {
    private static final float minViewAlpha = 0.5f;
    private static final float minVisibleSquare = 0.6f;
    private boolean autoLoadImages = false;
    protected f banner;
    private final com.my.target.core.net.b.a imageListener = new com.my.target.core.net.b.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void onLoad() {
            this.a.doLoadSuccess();
        }
    };
    private boolean isShown;
    private a listener;
    private com.my.target.core.controllers.a nativeAdVideoController;
    private final com.my.target.core.utils.a.a showHelper = new com.my.target.core.utils.a.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final boolean a() {
            View view = null;
            boolean z = this.a.nativeAdVideoController != null;
            if (this.a.viewWeakReference != null) {
                view = (View) this.a.viewWeakReference.get();
            }
            if ((this.a.isShown && !z) || view == null) {
                if (view == null && this.a.nativeAdVideoController != null) {
                    this.a.nativeAdVideoController.b();
                }
                return true;
            } else if (this.a.adData == null || this.a.banner == null || view.getVisibility() != 0 || view.getParent() == null) {
                return false;
            } else {
                if (VERSION.SDK_INT >= 11 && view.getAlpha() < b.minViewAlpha) {
                    return false;
                }
                Rect rect = new Rect();
                if (!view.getGlobalVisibleRect(rect)) {
                    return false;
                }
                if (((double) (rect.height() * rect.width())) >= ((double) (view.getHeight() * view.getWidth())) * 0.6000000238418579d) {
                    this.a.handleShow();
                    if (!z) {
                        return true;
                    }
                    this.a.nativeAdVideoController.a();
                    return false;
                } else if (!z) {
                    return false;
                } else {
                    this.a.nativeAdVideoController.d();
                    return false;
                }
            }
        }
    };
    private final com.my.target.core.controllers.a.a statisticsListener = new com.my.target.core.controllers.a.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void a() {
            if (this.a.adData != null && this.a.banner != null && this.a.banner.k() != null) {
                c.c(this.a.banner.k(), this.a.context);
            }
        }

        public final void b() {
            if (this.a.adData != null && this.a.banner != null && this.a.banner.k() != null) {
                c.a(this.a.banner.k(), "fullscreenOn", this.a.context);
            }
        }

        public final void c() {
            if (this.a.adData != null && this.a.banner != null && this.a.banner.k() != null) {
                c.a(this.a.banner.k(), "fullscreenOff", this.a.context);
            }
        }

        public final void d() {
            if (this.a.adData != null && this.a.banner != null && this.a.banner.k() != null) {
                c.a(this.a.banner.k(), "playbackPaused", this.a.context);
            }
        }

        public final void e() {
            if (this.a.adData != null && this.a.banner != null && this.a.banner.k() != null) {
                c.a(this.a.banner.k(), "playbackResumed", this.a.context);
            }
        }

        public final void f() {
            if (this.a.adData != null && this.a.banner != null && this.a.banner.k() != null) {
                c.a(this.a.banner.k(), "volumeOff", this.a.context);
            }
        }

        public final void g() {
            if (this.a.adData != null && this.a.banner != null && this.a.banner.k() != null) {
                c.a(this.a.banner.k(), "volumeOn", this.a.context);
            }
        }

        public final void a(float f, HashSet<g> hashSet) {
            if (this.a.adData != null && this.a.banner != null && this.a.banner.k() != null) {
                c.a((Set) hashSet, f, this.a.context);
            }
        }
    };
    private final List<String> supportedTypes;
    private final OnClickListener viewClickListener = new OnClickListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            Tracer.d("Click received by native ad");
            if (this.a.banner != null && this.a.adData != null) {
                if (this.a.listener != null) {
                    this.a.listener.onClick(this.a);
                }
                this.a.adData.a(this.a.banner, this.a.context);
            }
        }
    };
    private WeakReference<View> viewWeakReference;

    /* compiled from: AbstractNativeAd */
    public interface a<T extends b> {
        void onClick(T t);

        void onLoad(T t);

        void onNoAd(String str, T t);
    }

    public void setListener(a aVar) {
        this.listener = aVar;
    }

    public b(int i, List<String> list, Context context, CustomParams customParams) {
        this.supportedTypes = list;
        com.my.target.core.a aVar = new com.my.target.core.a(i, "nativeads");
        aVar.a(customParams);
        aVar.h();
        init(aVar, context);
    }

    protected void onLoad(c cVar) {
        if (cVar.g()) {
            com.my.target.core.models.sections.f c = cVar.c("nativeads");
            if (c instanceof e) {
                e eVar = (e) c;
                if (eVar.b() > 0) {
                    String str;
                    Iterator it = eVar.g().iterator();
                    while (it.hasNext()) {
                        f fVar = (f) it.next();
                        if (this.supportedTypes.contains(fVar.a())) {
                            this.banner = fVar;
                            break;
                        }
                        str = "Banner " + fVar.getId() + " with type '" + fVar.a() + "' does not matches for Ad types '" + this.supportedTypes + "'";
                        Tracer.d(str);
                        com.my.target.core.async.a.a(str, b.class.getName(), 40, getClass().getSimpleName(), cVar.b(), this.context);
                    }
                    if (this.banner == null) {
                        str = "No supported banners found for Ad types '" + this.supportedTypes + "'";
                        Tracer.d(str);
                        com.my.target.core.async.a.a(str, b.class.getName(), 40, getClass().getSimpleName(), cVar.b(), this.context);
                        doLoadFailure(null);
                        return;
                    } else if (this.autoLoadImages) {
                        doAutoLoadImages();
                        return;
                    } else {
                        doLoadSuccess();
                        return;
                    }
                }
            }
        }
        doLoadFailure(null);
    }

    private void doLoadSuccess() {
        if (this.listener != null) {
            this.listener.onLoad(this);
        }
    }

    private void doLoadFailure(String str) {
        if (str == null) {
            str = "No ad";
        }
        if (this.listener != null) {
            this.listener.onNoAd(str, this);
        }
    }

    protected void onLoadError(String str) {
        doLoadFailure(str);
    }

    public boolean isAutoLoadImages() {
        return this.autoLoadImages;
    }

    public void setAutoLoadImages(boolean z) {
        this.autoLoadImages = z;
    }

    protected void loadImageDataToView(ImageData imageData, ImageView imageView) {
        if (imageData == null || imageView == null) {
            Tracer.i("AbstractNativeAd: invalid or null arguments");
        } else if (imageData.getUrl() == null) {
            Tracer.i("AbstractNativeAd: image data is empty");
        } else {
            com.my.target.core.net.b.a().a(imageData, imageView);
        }
    }

    public final void handleClick() {
        if (this.banner != null) {
            this.adData.a(this.banner, this.context);
            if (this.listener != null) {
                this.listener.onClick(this);
            }
        }
    }

    public final void handleShow() {
        if (!this.isShown && this.banner != null) {
            c.c(this.banner, this.context);
            this.isShown = true;
        }
    }

    public final void registerView(View view) {
        View view2 = null;
        if (this.viewWeakReference != null) {
            view2 = (View) this.viewWeakReference.get();
        }
        if (view != view2) {
            unregisterView();
            this.viewWeakReference = new WeakReference(view);
            doRegisterView(view);
            if (!this.showHelper.a()) {
                com.my.target.core.utils.a.a().a(this.showHelper);
            }
        }
    }

    public final void unregisterView() {
        if (this.viewWeakReference != null) {
            View view = (View) this.viewWeakReference.get();
            if (view != null) {
                doUnregisterView(view);
            }
            com.my.target.core.utils.a.a().b(this.showHelper);
            this.viewWeakReference.clear();
            this.viewWeakReference = null;
        }
    }

    private void doRegisterView(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup instanceof MediaAdView) {
                MediaAdView mediaAdView = (MediaAdView) viewGroup;
                mediaAdView.getProgressBarView().setVisibility(8);
                mediaAdView.getPlayButtonView().setVisibility(8);
                ImageData image = this.banner.getImage();
                if (image != null) {
                    mediaAdView.setPlaceHolderDimension(image.getWidth(), image.getHeight());
                }
                if (l.b(14) && this.banner.k() != null) {
                    VideoData a = n.a(this.banner.k().u(), MyTargetVideoView.DEFAULT_VIDEO_QUALITY);
                    if (a != null) {
                        if (this.nativeAdVideoController == null) {
                            this.nativeAdVideoController = new com.my.target.core.controllers.a(this.banner, a);
                            this.nativeAdVideoController.a(this.viewClickListener);
                            this.nativeAdVideoController.a(this.statisticsListener);
                        }
                        this.nativeAdVideoController.a(mediaAdView);
                        return;
                    }
                }
            }
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                doRegisterView(viewGroup.getChildAt(i));
            }
        }
        view.setOnClickListener(this.viewClickListener);
    }

    private void doUnregisterView(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!(viewGroup instanceof MediaAdView)) {
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    doUnregisterView(viewGroup.getChildAt(i));
                }
            } else if (this.nativeAdVideoController != null) {
                this.nativeAdVideoController.b();
            }
        }
        view.setOnClickListener(null);
    }

    private void doAutoLoadImages() {
        List arrayList = new ArrayList();
        if (this.banner.getImage().getUrl() != null) {
            arrayList.add(this.banner.getImage());
        }
        if (this.banner.getIcon().getUrl() != null) {
            arrayList.add(this.banner.getIcon());
        }
        Tracer.d("Starting load: " + arrayList.size() + " urls");
        if (arrayList.size() > 0) {
            com.my.target.core.net.b.a().a(arrayList, this.context, this.imageListener);
        } else {
            doLoadSuccess();
        }
    }

    public final T getBanner() {
        return this.banner;
    }
}
