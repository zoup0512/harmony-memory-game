package com.mopub.nativeads;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubServerPositioning;
import java.util.List;
import java.util.WeakHashMap;

public class MoPubAdAdapter extends BaseAdapter {
    @Nullable
    private MoPubNativeAdLoadedListener mAdLoadedListener;
    @NonNull
    private final Adapter mOriginalAdapter;
    @NonNull
    private final MoPubStreamAdPlacer mStreamAdPlacer;
    @NonNull
    private final WeakHashMap<View, Integer> mViewPositionMap;
    @NonNull
    private final VisibilityTracker mVisibilityTracker;

    public MoPubAdAdapter(@NonNull Activity activity, @NonNull Adapter adapter) {
        this(activity, adapter, MoPubNativeAdPositioning.serverPositioning());
    }

    public MoPubAdAdapter(@NonNull Activity activity, @NonNull Adapter adapter, @NonNull MoPubServerPositioning moPubServerPositioning) {
        this(new MoPubStreamAdPlacer(activity, moPubServerPositioning), adapter, new VisibilityTracker(activity));
    }

    public MoPubAdAdapter(@NonNull Activity activity, @NonNull Adapter adapter, @NonNull MoPubClientPositioning moPubClientPositioning) {
        this(new MoPubStreamAdPlacer(activity, moPubClientPositioning), adapter, new VisibilityTracker(activity));
    }

    @VisibleForTesting
    MoPubAdAdapter(@NonNull MoPubStreamAdPlacer moPubStreamAdPlacer, @NonNull Adapter adapter, @NonNull VisibilityTracker visibilityTracker) {
        this.mOriginalAdapter = adapter;
        this.mStreamAdPlacer = moPubStreamAdPlacer;
        this.mViewPositionMap = new WeakHashMap();
        this.mVisibilityTracker = visibilityTracker;
        this.mVisibilityTracker.setVisibilityTrackerListener(new VisibilityTrackerListener() {
            public void onVisibilityChanged(@NonNull List<View> list, List<View> list2) {
                MoPubAdAdapter.this.handleVisibilityChange(list);
            }
        });
        this.mOriginalAdapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                MoPubAdAdapter.this.mStreamAdPlacer.setItemCount(MoPubAdAdapter.this.mOriginalAdapter.getCount());
                MoPubAdAdapter.this.notifyDataSetChanged();
            }

            public void onInvalidated() {
                MoPubAdAdapter.this.notifyDataSetInvalidated();
            }
        });
        this.mStreamAdPlacer.setAdLoadedListener(new MoPubNativeAdLoadedListener() {
            public void onAdLoaded(int i) {
                MoPubAdAdapter.this.handleAdLoaded(i);
            }

            public void onAdRemoved(int i) {
                MoPubAdAdapter.this.handleAdRemoved(i);
            }
        });
        this.mStreamAdPlacer.setItemCount(this.mOriginalAdapter.getCount());
    }

    @VisibleForTesting
    void handleAdLoaded(int i) {
        if (this.mAdLoadedListener != null) {
            this.mAdLoadedListener.onAdLoaded(i);
        }
        notifyDataSetChanged();
    }

    @VisibleForTesting
    void handleAdRemoved(int i) {
        if (this.mAdLoadedListener != null) {
            this.mAdLoadedListener.onAdRemoved(i);
        }
        notifyDataSetChanged();
    }

    public final void registerAdRenderer(@NonNull MoPubAdRenderer moPubAdRenderer) {
        if (NoThrow.checkNotNull(moPubAdRenderer, "Tried to set a null ad renderer on the placer.")) {
            this.mStreamAdPlacer.registerAdRenderer(moPubAdRenderer);
        }
    }

    public final void setAdLoadedListener(@Nullable MoPubNativeAdLoadedListener moPubNativeAdLoadedListener) {
        this.mAdLoadedListener = moPubNativeAdLoadedListener;
    }

    public void loadAds(@NonNull String str) {
        this.mStreamAdPlacer.loadAds(str);
    }

    public void loadAds(@NonNull String str, @Nullable RequestParameters requestParameters) {
        this.mStreamAdPlacer.loadAds(str, requestParameters);
    }

    public boolean isAd(int i) {
        return this.mStreamAdPlacer.isAd(i);
    }

    public void clearAds() {
        this.mStreamAdPlacer.clearAds();
    }

    public void destroy() {
        this.mStreamAdPlacer.destroy();
        this.mVisibilityTracker.destroy();
    }

    public boolean areAllItemsEnabled() {
        return (this.mOriginalAdapter instanceof ListAdapter) && ((ListAdapter) this.mOriginalAdapter).areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        return isAd(i) || ((this.mOriginalAdapter instanceof ListAdapter) && ((ListAdapter) this.mOriginalAdapter).isEnabled(this.mStreamAdPlacer.getOriginalPosition(i)));
    }

    public int getCount() {
        return this.mStreamAdPlacer.getAdjustedCount(this.mOriginalAdapter.getCount());
    }

    @Nullable
    public Object getItem(int i) {
        Object adData = this.mStreamAdPlacer.getAdData(i);
        return adData != null ? adData : this.mOriginalAdapter.getItem(this.mStreamAdPlacer.getOriginalPosition(i));
    }

    public long getItemId(int i) {
        Object adData = this.mStreamAdPlacer.getAdData(i);
        if (adData != null) {
            return (long) (-System.identityHashCode(adData));
        }
        return this.mOriginalAdapter.getItemId(this.mStreamAdPlacer.getOriginalPosition(i));
    }

    public boolean hasStableIds() {
        return this.mOriginalAdapter.hasStableIds();
    }

    @Nullable
    public View getView(int i, View view, ViewGroup viewGroup) {
        View adView = this.mStreamAdPlacer.getAdView(i, view, viewGroup);
        if (adView == null) {
            adView = this.mOriginalAdapter.getView(this.mStreamAdPlacer.getOriginalPosition(i), view, viewGroup);
        }
        this.mViewPositionMap.put(adView, Integer.valueOf(i));
        this.mVisibilityTracker.addView(adView, 0);
        return adView;
    }

    public int getItemViewType(int i) {
        int adViewType = this.mStreamAdPlacer.getAdViewType(i);
        if (adViewType != 0) {
            return (adViewType + this.mOriginalAdapter.getViewTypeCount()) - 1;
        }
        return this.mOriginalAdapter.getItemViewType(this.mStreamAdPlacer.getOriginalPosition(i));
    }

    public int getViewTypeCount() {
        return this.mOriginalAdapter.getViewTypeCount() + this.mStreamAdPlacer.getAdViewTypeCount();
    }

    public boolean isEmpty() {
        return this.mOriginalAdapter.isEmpty() && this.mStreamAdPlacer.getAdjustedCount(0) == 0;
    }

    private void handleVisibilityChange(@NonNull List<View> list) {
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        for (View view : list) {
            Integer num = (Integer) this.mViewPositionMap.get(view);
            if (num != null) {
                i = Math.min(num.intValue(), i);
                i2 = Math.max(num.intValue(), i2);
            }
        }
        this.mStreamAdPlacer.placeAdsInRange(i, i2 + 1);
    }

    public int getOriginalPosition(int i) {
        return this.mStreamAdPlacer.getOriginalPosition(i);
    }

    public int getAdjustedPosition(int i) {
        return this.mStreamAdPlacer.getAdjustedPosition(i);
    }

    public void insertItem(int i) {
        this.mStreamAdPlacer.insertItem(i);
    }

    public void removeItem(int i) {
        this.mStreamAdPlacer.removeItem(i);
    }

    public void setOnClickListener(@NonNull ListView listView, @Nullable final OnItemClickListener onItemClickListener) {
        if (!NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setOnClickListener with a null ListView")) {
            return;
        }
        if (onItemClickListener == null) {
            listView.setOnItemClickListener(null);
        } else {
            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (!MoPubAdAdapter.this.mStreamAdPlacer.isAd(i)) {
                        onItemClickListener.onItemClick(adapterView, view, MoPubAdAdapter.this.mStreamAdPlacer.getOriginalPosition(i), j);
                    }
                }
            });
        }
    }

    public void setOnItemLongClickListener(@NonNull ListView listView, @Nullable final OnItemLongClickListener onItemLongClickListener) {
        if (!NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setOnItemLongClickListener with a null ListView")) {
            return;
        }
        if (onItemLongClickListener == null) {
            listView.setOnItemLongClickListener(null);
        } else {
            listView.setOnItemLongClickListener(new OnItemLongClickListener() {
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (!MoPubAdAdapter.this.isAd(i)) {
                        if (!onItemLongClickListener.onItemLongClick(adapterView, view, MoPubAdAdapter.this.mStreamAdPlacer.getOriginalPosition(i), j)) {
                            return false;
                        }
                    }
                    return true;
                }
            });
        }
    }

    public void setOnItemSelectedListener(@NonNull ListView listView, @Nullable final OnItemSelectedListener onItemSelectedListener) {
        if (!NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setOnItemSelectedListener with a null ListView")) {
            return;
        }
        if (onItemSelectedListener == null) {
            listView.setOnItemSelectedListener(null);
        } else {
            listView.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    if (!MoPubAdAdapter.this.isAd(i)) {
                        onItemSelectedListener.onItemSelected(adapterView, view, MoPubAdAdapter.this.mStreamAdPlacer.getOriginalPosition(i), j);
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    onItemSelectedListener.onNothingSelected(adapterView);
                }
            });
        }
    }

    public void setSelection(@NonNull ListView listView, int i) {
        if (NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.setSelection with a null ListView")) {
            listView.setSelection(this.mStreamAdPlacer.getAdjustedPosition(i));
        }
    }

    public void smoothScrollToPosition(@NonNull ListView listView, int i) {
        if (NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.smoothScrollToPosition with a null ListView")) {
            listView.smoothScrollToPosition(this.mStreamAdPlacer.getAdjustedPosition(i));
        }
    }

    public void refreshAds(@NonNull ListView listView, @NonNull String str) {
        refreshAds(listView, str, null);
    }

    public void refreshAds(@NonNull ListView listView, @NonNull String str, @Nullable RequestParameters requestParameters) {
        if (NoThrow.checkNotNull(listView, "You called MoPubAdAdapter.refreshAds with a null ListView")) {
            View childAt = listView.getChildAt(0);
            int top = childAt == null ? 0 : childAt.getTop();
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            int max = Math.max(firstVisiblePosition - 1, 0);
            while (this.mStreamAdPlacer.isAd(max) && max > 0) {
                max--;
            }
            int lastVisiblePosition = listView.getLastVisiblePosition();
            while (this.mStreamAdPlacer.isAd(lastVisiblePosition) && lastVisiblePosition < getCount() - 1) {
                lastVisiblePosition++;
            }
            max = this.mStreamAdPlacer.getOriginalPosition(max);
            this.mStreamAdPlacer.removeAdsInRange(this.mStreamAdPlacer.getOriginalCount(lastVisiblePosition + 1), this.mStreamAdPlacer.getOriginalCount(getCount()));
            int removeAdsInRange = this.mStreamAdPlacer.removeAdsInRange(0, max);
            if (removeAdsInRange > 0) {
                listView.setSelectionFromTop(firstVisiblePosition - removeAdsInRange, top);
            }
            loadAds(str, requestParameters);
        }
    }
}
