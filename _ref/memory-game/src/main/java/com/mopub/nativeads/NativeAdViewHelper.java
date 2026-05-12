package com.mopub.nativeads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import java.util.WeakHashMap;

@Deprecated
class NativeAdViewHelper {
    private static final WeakHashMap<View, NativeAd> sNativeAdMap = new WeakHashMap();

    private NativeAdViewHelper() {
    }

    @Deprecated
    @NonNull
    static View getAdView(@Nullable View view, @Nullable ViewGroup viewGroup, @NonNull Activity activity, @Nullable NativeAd nativeAd, @Nullable ViewBinder viewBinder) {
        NoThrow.checkNotNull(viewBinder, "ViewBinder is null.");
        if (view != null) {
            clearNativeAd(activity, view);
        }
        if (nativeAd == null || nativeAd.isDestroyed() || viewBinder == null) {
            MoPubLog.d("NativeAd or viewBinder null or invalid. Returning empty view");
            if (view != null && ViewType.EMPTY.equals(view.getTag())) {
                return view;
            }
            view = new View(activity);
            view.setTag(ViewType.EMPTY);
            view.setVisibility(8);
            return view;
        }
        if (view == null || !ViewType.AD.equals(view.getTag())) {
            view = nativeAd.createAdView(activity, viewGroup);
            view.setTag(ViewType.AD);
        }
        prepareNativeAd(activity, view, nativeAd);
        nativeAd.renderAdView(view);
        return view;
    }

    private static void clearNativeAd(@NonNull Context context, @NonNull View view) {
        NativeAd nativeAd = (NativeAd) sNativeAdMap.get(view);
        if (nativeAd != null) {
            nativeAd.clear(view);
        }
    }

    private static void prepareNativeAd(@NonNull Context context, @NonNull View view, @NonNull NativeAd nativeAd) {
        sNativeAdMap.put(view, nativeAd);
        nativeAd.prepare(view);
    }
}
