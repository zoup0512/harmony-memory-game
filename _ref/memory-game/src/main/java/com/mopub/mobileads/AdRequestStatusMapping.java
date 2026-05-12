package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import java.util.Map;
import java.util.TreeMap;

public class AdRequestStatusMapping {
    @NonNull
    private final Map<String, AdRequestStatus> mAdUnitToAdRequestStatus = new TreeMap();

    private static class AdRequestStatus {
        @Nullable
        private String mClickUrl;
        @Nullable
        private String mFailUrl;
        @Nullable
        private String mImpressionUrl;
        @NonNull
        private LoadingStatus mLoadingStatus;

        public AdRequestStatus(@NonNull LoadingStatus loadingStatus) {
            this(loadingStatus, null, null, null);
        }

        public AdRequestStatus(@NonNull LoadingStatus loadingStatus, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            Preconditions.checkNotNull(loadingStatus);
            this.mLoadingStatus = loadingStatus;
            this.mFailUrl = str;
            this.mImpressionUrl = str2;
            this.mClickUrl = str3;
        }

        @NonNull
        private LoadingStatus getStatus() {
            return this.mLoadingStatus;
        }

        private void setStatus(@NonNull LoadingStatus loadingStatus) {
            this.mLoadingStatus = loadingStatus;
        }

        @Nullable
        private String getFailurl() {
            return this.mFailUrl;
        }

        @Nullable
        private String getImpressionUrl() {
            return this.mImpressionUrl;
        }

        private void setImpressionUrl(@Nullable String str) {
            this.mImpressionUrl = str;
        }

        @Nullable
        private String getClickUrl() {
            return this.mClickUrl;
        }

        private void setClickUrl(@Nullable String str) {
            this.mClickUrl = str;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdRequestStatus)) {
                return false;
            }
            AdRequestStatus adRequestStatus = (AdRequestStatus) obj;
            if (!(this.mLoadingStatus.equals(adRequestStatus.mLoadingStatus) && TextUtils.equals(this.mFailUrl, adRequestStatus.mFailUrl) && TextUtils.equals(this.mImpressionUrl, adRequestStatus.mImpressionUrl) && TextUtils.equals(this.mClickUrl, adRequestStatus.mClickUrl))) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            int hashCode;
            int i = 0;
            int hashCode2 = ((this.mFailUrl != null ? this.mFailUrl.hashCode() : 0) + ((this.mLoadingStatus.ordinal() + 899) * 31)) * 31;
            if (this.mImpressionUrl != null) {
                hashCode = this.mImpressionUrl.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode = (hashCode + hashCode2) * 31;
            if (this.mClickUrl != null) {
                i = this.mClickUrl.hashCode();
            }
            return hashCode + i;
        }
    }

    private enum LoadingStatus {
        LOADING,
        LOADED,
        PLAYED
    }

    void markFail(@NonNull String str) {
        this.mAdUnitToAdRequestStatus.remove(str);
    }

    void markLoading(@NonNull String str) {
        this.mAdUnitToAdRequestStatus.put(str, new AdRequestStatus(LoadingStatus.LOADING));
    }

    void markLoaded(@NonNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.mAdUnitToAdRequestStatus.put(str, new AdRequestStatus(LoadingStatus.LOADED, str2, str3, str4));
    }

    void markPlayed(@NonNull String str) {
        if (this.mAdUnitToAdRequestStatus.containsKey(str)) {
            ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(str)).setStatus(LoadingStatus.PLAYED);
        } else {
            this.mAdUnitToAdRequestStatus.put(str, new AdRequestStatus(LoadingStatus.PLAYED));
        }
    }

    boolean canPlay(@NonNull String str) {
        AdRequestStatus adRequestStatus = (AdRequestStatus) this.mAdUnitToAdRequestStatus.get(str);
        return adRequestStatus != null && LoadingStatus.LOADED.equals(adRequestStatus.getStatus());
    }

    boolean isLoading(@NonNull String str) {
        if (!this.mAdUnitToAdRequestStatus.containsKey(str)) {
            return false;
        }
        return ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(str)).getStatus() == LoadingStatus.LOADING;
    }

    @Nullable
    String getFailoverUrl(@NonNull String str) {
        if (this.mAdUnitToAdRequestStatus.containsKey(str)) {
            return ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(str)).getFailurl();
        }
        return null;
    }

    @Nullable
    String getImpressionTrackerUrlString(@NonNull String str) {
        if (this.mAdUnitToAdRequestStatus.containsKey(str)) {
            return ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(str)).getImpressionUrl();
        }
        return null;
    }

    @Nullable
    String getClickTrackerUrlString(@NonNull String str) {
        if (this.mAdUnitToAdRequestStatus.containsKey(str)) {
            return ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(str)).getClickUrl();
        }
        return null;
    }

    void clearImpressionUrl(@NonNull String str) {
        if (this.mAdUnitToAdRequestStatus.containsKey(str)) {
            ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(str)).setImpressionUrl(null);
        }
    }

    void clearClickUrl(@NonNull String str) {
        if (this.mAdUnitToAdRequestStatus.containsKey(str)) {
            ((AdRequestStatus) this.mAdUnitToAdRequestStatus.get(str)).setClickUrl(null);
        }
    }
}
