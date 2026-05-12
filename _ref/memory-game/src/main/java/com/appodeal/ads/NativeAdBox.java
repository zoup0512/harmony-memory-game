package com.appodeal.ads;

import java.util.ArrayList;
import java.util.List;

public class NativeAdBox {
    private int a = 1;
    private NativeAdBoxListener b;
    private List<NativeAd> c = new ArrayList();

    private class a implements NativeCallbacks {
        final /* synthetic */ NativeAdBox a;

        private a(NativeAdBox nativeAdBox) {
            this.a = nativeAdBox;
        }

        public void onNativeLoaded(List<NativeAd> list) {
            this.a.c.addAll(list);
            if (this.a.b != null) {
                this.a.b.onNativeAdBoxLoaded(this.a.c.size());
            }
            this.a.a();
        }

        public void onNativeFailedToLoad() {
            this.a.a();
        }

        public void onNativeShown(NativeAd nativeAd) {
            if (this.a.b != null) {
                this.a.b.onNativeShown(nativeAd);
            }
        }

        public void onNativeClicked(NativeAd nativeAd) {
            if (this.a.b != null) {
                this.a.b.onNativeClicked(nativeAd);
            }
        }
    }

    NativeAdBox() {
    }

    public void setListener(NativeAdBoxListener nativeAdBoxListener) {
        if (nativeAdBoxListener == null) {
            throw new IllegalArgumentException("NativeAdBoxListener must be not null");
        }
        Appodeal.a("NativeAdBox set listeners");
        this.b = nativeAdBoxListener;
    }

    public void setSize(int i) {
        if (i <= 0 || i > 11) {
            throw new IllegalArgumentException(String.format("Invalid size value: %s", new Object[]{Integer.valueOf(i)}));
        }
        Appodeal.a(String.format("NativeAdBox set size: %s", new Object[]{Integer.valueOf(i)}));
        this.a = i;
    }

    public void load() {
        if (Native.b) {
            Appodeal.a("NativeAdBox start loading");
            Appodeal.setNativeCallbacks(new a());
            a();
            return;
        }
        throw new IllegalStateException("You must initialized Appodeal.NATIVE before");
    }

    private void a() {
        int size = this.a - this.c.size();
        if (size > 0) {
            Appodeal.cache(Appodeal.b, 512, size);
        }
    }

    public List<NativeAd> getNativeAds(int i) {
        Object arrayList;
        if (i >= this.c.size()) {
            arrayList = new ArrayList(this.c);
        } else {
            arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(this.c.get(i2));
            }
        }
        this.c.removeAll(arrayList);
        a();
        return arrayList;
    }
}
