package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

class RewardedVideoData {
    @NonNull
    private final Set<CustomEventRewardedVideoListener> mAdNetworkListeners = new HashSet();
    @NonNull
    private final Map<String, CustomEventRewardedVideo> mAdUnitToCustomEventMap = new TreeMap();
    @NonNull
    private final Map<String, MoPubReward> mAdUnitToRewardMap = new TreeMap();
    @NonNull
    private final Map<String, String> mAdUnitToServerCompletionUrlMap = new TreeMap();
    @Nullable
    private String mCurrentAdUnitId;
    @NonNull
    private final Map<TwoPartKey, Set<String>> mCustomEventToMoPubIdMap = new HashMap();
    @NonNull
    private final Map<Class<? extends CustomEventRewardedVideo>, MoPubReward> mCustomEventToRewardMap = new HashMap();
    @Nullable
    private String mCustomerId;

    private static class TwoPartKey extends Pair<Class<? extends CustomEventRewardedVideo>, String> {
        @NonNull
        final String adNetworkId;
        @NonNull
        final Class<? extends CustomEventRewardedVideo> customEventClass;

        public TwoPartKey(@NonNull Class<? extends CustomEventRewardedVideo> cls, @NonNull String str) {
            super(cls, str);
            this.customEventClass = cls;
            this.adNetworkId = str;
        }
    }

    RewardedVideoData() {
    }

    @Nullable
    CustomEventRewardedVideo getCustomEvent(@NonNull String str) {
        return (CustomEventRewardedVideo) this.mAdUnitToCustomEventMap.get(str);
    }

    @Nullable
    MoPubReward getMoPubReward(@Nullable String str) {
        return (MoPubReward) this.mAdUnitToRewardMap.get(str);
    }

    @Nullable
    String getServerCompletionUrl(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (String) this.mAdUnitToServerCompletionUrlMap.get(str);
    }

    @Nullable
    MoPubReward getLastShownMoPubReward(@NonNull Class<? extends CustomEventRewardedVideo> cls) {
        return (MoPubReward) this.mCustomEventToRewardMap.get(cls);
    }

    @NonNull
    Set<String> getMoPubIdsForAdNetwork(@NonNull Class<? extends CustomEventRewardedVideo> cls, @Nullable String str) {
        if (str == null) {
            Set<String> hashSet = new HashSet();
            for (Entry entry : this.mCustomEventToMoPubIdMap.entrySet()) {
                if (cls == ((TwoPartKey) entry.getKey()).customEventClass) {
                    hashSet.addAll((Collection) entry.getValue());
                }
            }
            return hashSet;
        }
        TwoPartKey twoPartKey = new TwoPartKey(cls, str);
        if (this.mCustomEventToMoPubIdMap.containsKey(twoPartKey)) {
            return (Set) this.mCustomEventToMoPubIdMap.get(twoPartKey);
        }
        return Collections.emptySet();
    }

    void updateAdUnitCustomEventMapping(@NonNull String str, @NonNull CustomEventRewardedVideo customEventRewardedVideo, @Nullable CustomEventRewardedVideoListener customEventRewardedVideoListener, @NonNull String str2) {
        this.mAdUnitToCustomEventMap.put(str, customEventRewardedVideo);
        this.mAdNetworkListeners.add(customEventRewardedVideoListener);
        associateCustomEventWithMoPubId(customEventRewardedVideo.getClass(), str2, str);
    }

    void updateAdUnitRewardMapping(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        Preconditions.checkNotNull(str);
        if (str2 == null || str3 == null) {
            this.mAdUnitToRewardMap.remove(str);
            return;
        }
        try {
            int parseInt = Integer.parseInt(str3);
            if (parseInt >= 0) {
                this.mAdUnitToRewardMap.put(str, MoPubReward.success(str2, parseInt));
            }
        } catch (NumberFormatException e) {
        }
    }

    void updateAdUnitToServerCompletionUrlMapping(@NonNull String str, @Nullable String str2) {
        Preconditions.checkNotNull(str);
        this.mAdUnitToServerCompletionUrlMap.put(str, str2);
    }

    void updateCustomEventLastShownRewardMapping(@NonNull Class<? extends CustomEventRewardedVideo> cls, @Nullable MoPubReward moPubReward) {
        Preconditions.checkNotNull(cls);
        this.mCustomEventToRewardMap.put(cls, moPubReward);
    }

    void associateCustomEventWithMoPubId(@NonNull Class<? extends CustomEventRewardedVideo> cls, @NonNull String str, @NonNull String str2) {
        Set set;
        TwoPartKey twoPartKey = new TwoPartKey(cls, str);
        Iterator it = this.mCustomEventToMoPubIdMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!((TwoPartKey) entry.getKey()).equals(twoPartKey) && ((Set) entry.getValue()).contains(str2)) {
                ((Set) entry.getValue()).remove(str2);
                if (((Set) entry.getValue()).isEmpty()) {
                    it.remove();
                }
                set = (Set) this.mCustomEventToMoPubIdMap.get(twoPartKey);
                if (set == null) {
                    set = new HashSet();
                    this.mCustomEventToMoPubIdMap.put(twoPartKey, set);
                }
                set.add(str2);
            }
        }
        set = (Set) this.mCustomEventToMoPubIdMap.get(twoPartKey);
        if (set == null) {
            set = new HashSet();
            this.mCustomEventToMoPubIdMap.put(twoPartKey, set);
        }
        set.add(str2);
    }

    void setCurrentAdUnitId(@Nullable String str) {
        this.mCurrentAdUnitId = str;
    }

    @Nullable
    String getCurrentAdUnitId() {
        return this.mCurrentAdUnitId;
    }

    void setCustomerId(@Nullable String str) {
        this.mCustomerId = str;
    }

    @Nullable
    String getCustomerId() {
        return this.mCustomerId;
    }
}
