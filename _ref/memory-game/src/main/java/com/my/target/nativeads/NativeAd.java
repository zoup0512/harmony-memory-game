package com.my.target.nativeads;

import android.content.Context;
import android.widget.ImageView;
import com.my.target.Tracer;
import com.my.target.ads.CustomParams;
import com.my.target.core.facades.b;
import com.my.target.core.facades.b.a;
import com.my.target.nativeads.banners.NativePromoBanner;
import com.my.target.nativeads.views.MediaAdView;
import java.util.ArrayList;
import java.util.List;

public class NativeAd extends b<NativePromoBanner> {
    private static final List<String> supportedFormats = new ArrayList<String>() {
        {
            add("promo");
        }
    };

    public interface NativeAdListener extends a<NativeAd> {
    }

    public NativeAd(int i, Context context) {
        this(i, context, null);
    }

    public NativeAd(int i, Context context, CustomParams customParams) {
        super(i, supportedFormats, context, customParams);
        Tracer.i("NativeAd created. Version: 4.5.10");
    }

    public void loadImageToView(MediaAdView mediaAdView) {
        if (this.banner != null) {
            loadImageDataToView(this.banner.getImage(), mediaAdView.getImageView());
        }
    }

    public void loadIconToView(ImageView imageView) {
        if (this.banner != null) {
            loadImageDataToView(this.banner.getIcon(), imageView);
        }
    }
}
