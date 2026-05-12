package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import java.util.List;

class PlacementData {
    private static final int MAX_ADS = 200;
    public static final int NOT_FOUND = -1;
    @NonNull
    private final int[] mAdjustedAdPositions = new int[200];
    private int mDesiredCount = 0;
    @NonNull
    private final int[] mDesiredInsertionPositions = new int[200];
    @NonNull
    private final int[] mDesiredOriginalPositions = new int[200];
    @NonNull
    private final NativeAd[] mNativeAds = new NativeAd[200];
    @NonNull
    private final int[] mOriginalAdPositions = new int[200];
    private int mPlacedCount = 0;

    private PlacementData(@NonNull int[] iArr) {
        this.mDesiredCount = Math.min(iArr.length, 200);
        System.arraycopy(iArr, 0, this.mDesiredInsertionPositions, 0, this.mDesiredCount);
        System.arraycopy(iArr, 0, this.mDesiredOriginalPositions, 0, this.mDesiredCount);
    }

    @NonNull
    static PlacementData fromAdPositioning(@NonNull MoPubClientPositioning moPubClientPositioning) {
        int size;
        int i = 0;
        List<Integer> fixedPositions = moPubClientPositioning.getFixedPositions();
        int repeatingInterval = moPubClientPositioning.getRepeatingInterval();
        if (repeatingInterval == Integer.MAX_VALUE) {
            size = fixedPositions.size();
        } else {
            size = 200;
        }
        int[] iArr = new int[size];
        int i2 = 0;
        for (Integer intValue : fixedPositions) {
            i2 = intValue.intValue() - i;
            int i3 = i + 1;
            iArr[i] = i2;
            i = i3;
        }
        while (i < size) {
            i2 = (i2 + repeatingInterval) - 1;
            i3 = i + 1;
            iArr[i] = i2;
            i = i3;
        }
        return new PlacementData(iArr);
    }

    @NonNull
    static PlacementData empty() {
        return new PlacementData(new int[0]);
    }

    boolean shouldPlaceAd(int i) {
        if (binarySearch(this.mDesiredInsertionPositions, 0, this.mDesiredCount, i) >= 0) {
            return true;
        }
        return false;
    }

    int nextInsertionPosition(int i) {
        int binarySearchGreaterThan = binarySearchGreaterThan(this.mDesiredInsertionPositions, this.mDesiredCount, i);
        if (binarySearchGreaterThan == this.mDesiredCount) {
            return -1;
        }
        return this.mDesiredInsertionPositions[binarySearchGreaterThan];
    }

    int previousInsertionPosition(int i) {
        int binarySearchFirstEquals = binarySearchFirstEquals(this.mDesiredInsertionPositions, this.mDesiredCount, i);
        if (binarySearchFirstEquals == 0) {
            return -1;
        }
        return this.mDesiredInsertionPositions[binarySearchFirstEquals - 1];
    }

    void placeAd(int i, NativeAd nativeAd) {
        int binarySearchFirstEquals = binarySearchFirstEquals(this.mDesiredInsertionPositions, this.mDesiredCount, i);
        if (binarySearchFirstEquals == this.mDesiredCount || this.mDesiredInsertionPositions[binarySearchFirstEquals] != i) {
            MoPubLog.w("Attempted to insert an ad at an invalid position");
            return;
        }
        int i2 = this.mDesiredOriginalPositions[binarySearchFirstEquals];
        int binarySearchGreaterThan = binarySearchGreaterThan(this.mOriginalAdPositions, this.mPlacedCount, i2);
        if (binarySearchGreaterThan < this.mPlacedCount) {
            int i3 = this.mPlacedCount - binarySearchGreaterThan;
            System.arraycopy(this.mOriginalAdPositions, binarySearchGreaterThan, this.mOriginalAdPositions, binarySearchGreaterThan + 1, i3);
            System.arraycopy(this.mAdjustedAdPositions, binarySearchGreaterThan, this.mAdjustedAdPositions, binarySearchGreaterThan + 1, i3);
            System.arraycopy(this.mNativeAds, binarySearchGreaterThan, this.mNativeAds, binarySearchGreaterThan + 1, i3);
        }
        this.mOriginalAdPositions[binarySearchGreaterThan] = i2;
        this.mAdjustedAdPositions[binarySearchGreaterThan] = i;
        this.mNativeAds[binarySearchGreaterThan] = nativeAd;
        this.mPlacedCount++;
        i2 = (this.mDesiredCount - binarySearchFirstEquals) - 1;
        System.arraycopy(this.mDesiredInsertionPositions, binarySearchFirstEquals + 1, this.mDesiredInsertionPositions, binarySearchFirstEquals, i2);
        System.arraycopy(this.mDesiredOriginalPositions, binarySearchFirstEquals + 1, this.mDesiredOriginalPositions, binarySearchFirstEquals, i2);
        this.mDesiredCount--;
        while (binarySearchFirstEquals < this.mDesiredCount) {
            int[] iArr = this.mDesiredInsertionPositions;
            iArr[binarySearchFirstEquals] = iArr[binarySearchFirstEquals] + 1;
            binarySearchFirstEquals++;
        }
        for (binarySearchFirstEquals = binarySearchGreaterThan + 1; binarySearchFirstEquals < this.mPlacedCount; binarySearchFirstEquals++) {
            iArr = this.mAdjustedAdPositions;
            iArr[binarySearchFirstEquals] = iArr[binarySearchFirstEquals] + 1;
        }
    }

    boolean isPlacedAd(int i) {
        if (binarySearch(this.mAdjustedAdPositions, 0, this.mPlacedCount, i) >= 0) {
            return true;
        }
        return false;
    }

    @Nullable
    NativeAd getPlacedAd(int i) {
        int binarySearch = binarySearch(this.mAdjustedAdPositions, 0, this.mPlacedCount, i);
        if (binarySearch < 0) {
            return null;
        }
        return this.mNativeAds[binarySearch];
    }

    @NonNull
    int[] getPlacedAdPositions() {
        Object obj = new int[this.mPlacedCount];
        System.arraycopy(this.mAdjustedAdPositions, 0, obj, 0, this.mPlacedCount);
        return obj;
    }

    int getOriginalPosition(int i) {
        int binarySearch = binarySearch(this.mAdjustedAdPositions, 0, this.mPlacedCount, i);
        if (binarySearch < 0) {
            return i - (binarySearch ^ -1);
        }
        return -1;
    }

    int getAdjustedPosition(int i) {
        return binarySearchGreaterThan(this.mOriginalAdPositions, this.mPlacedCount, i) + i;
    }

    int getOriginalCount(int i) {
        if (i == 0) {
            return 0;
        }
        int originalPosition = getOriginalPosition(i - 1);
        if (originalPosition != -1) {
            return originalPosition + 1;
        }
        return -1;
    }

    int getAdjustedCount(int i) {
        if (i == 0) {
            return 0;
        }
        return getAdjustedPosition(i - 1) + 1;
    }

    int clearAdsInRange(int i, int i2) {
        int i3;
        int i4 = 0;
        int[] iArr = new int[this.mPlacedCount];
        int[] iArr2 = new int[this.mPlacedCount];
        int i5 = 0;
        for (i3 = 0; i3 < this.mPlacedCount; i3++) {
            int i6 = this.mOriginalAdPositions[i3];
            int i7 = this.mAdjustedAdPositions[i3];
            if (i <= i7 && i7 < i2) {
                iArr[i5] = i6;
                iArr2[i5] = i7 - i5;
                this.mNativeAds[i3].destroy();
                this.mNativeAds[i3] = null;
                i5++;
            } else if (i5 > 0) {
                int i8 = i3 - i5;
                this.mOriginalAdPositions[i8] = i6;
                this.mAdjustedAdPositions[i8] = i7 - i5;
                this.mNativeAds[i8] = this.mNativeAds[i3];
            }
        }
        if (i5 == 0) {
            return 0;
        }
        i6 = binarySearchFirstEquals(this.mDesiredInsertionPositions, this.mDesiredCount, iArr2[0]);
        for (i3 = this.mDesiredCount - 1; i3 >= i6; i3--) {
            this.mDesiredOriginalPositions[i3 + i5] = this.mDesiredOriginalPositions[i3];
            this.mDesiredInsertionPositions[i3 + i5] = this.mDesiredInsertionPositions[i3] - i5;
        }
        while (i4 < i5) {
            this.mDesiredOriginalPositions[i6 + i4] = iArr[i4];
            this.mDesiredInsertionPositions[i6 + i4] = iArr2[i4];
            i4++;
        }
        this.mDesiredCount += i5;
        this.mPlacedCount -= i5;
        return i5;
    }

    void clearAds() {
        if (this.mPlacedCount != 0) {
            clearAdsInRange(0, this.mAdjustedAdPositions[this.mPlacedCount - 1] + 1);
        }
    }

    void insertItem(int i) {
        int binarySearchFirstEquals;
        for (binarySearchFirstEquals = binarySearchFirstEquals(this.mDesiredOriginalPositions, this.mDesiredCount, i); binarySearchFirstEquals < this.mDesiredCount; binarySearchFirstEquals++) {
            int[] iArr = this.mDesiredOriginalPositions;
            iArr[binarySearchFirstEquals] = iArr[binarySearchFirstEquals] + 1;
            iArr = this.mDesiredInsertionPositions;
            iArr[binarySearchFirstEquals] = iArr[binarySearchFirstEquals] + 1;
        }
        for (binarySearchFirstEquals = binarySearchFirstEquals(this.mOriginalAdPositions, this.mPlacedCount, i); binarySearchFirstEquals < this.mPlacedCount; binarySearchFirstEquals++) {
            iArr = this.mOriginalAdPositions;
            iArr[binarySearchFirstEquals] = iArr[binarySearchFirstEquals] + 1;
            iArr = this.mAdjustedAdPositions;
            iArr[binarySearchFirstEquals] = iArr[binarySearchFirstEquals] + 1;
        }
    }

    void removeItem(int i) {
        int binarySearchGreaterThan;
        for (binarySearchGreaterThan = binarySearchGreaterThan(this.mDesiredOriginalPositions, this.mDesiredCount, i); binarySearchGreaterThan < this.mDesiredCount; binarySearchGreaterThan++) {
            int[] iArr = this.mDesiredOriginalPositions;
            iArr[binarySearchGreaterThan] = iArr[binarySearchGreaterThan] - 1;
            iArr = this.mDesiredInsertionPositions;
            iArr[binarySearchGreaterThan] = iArr[binarySearchGreaterThan] - 1;
        }
        for (binarySearchGreaterThan = binarySearchGreaterThan(this.mOriginalAdPositions, this.mPlacedCount, i); binarySearchGreaterThan < this.mPlacedCount; binarySearchGreaterThan++) {
            iArr = this.mOriginalAdPositions;
            iArr[binarySearchGreaterThan] = iArr[binarySearchGreaterThan] - 1;
            iArr = this.mAdjustedAdPositions;
            iArr[binarySearchGreaterThan] = iArr[binarySearchGreaterThan] - 1;
        }
    }

    void moveItem(int i, int i2) {
        removeItem(i);
        insertItem(i2);
    }

    private static int binarySearchFirstEquals(int[] iArr, int i, int i2) {
        int binarySearch = binarySearch(iArr, 0, i, i2);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        int i3 = iArr[binarySearch];
        while (binarySearch >= 0 && iArr[binarySearch] == i3) {
            binarySearch--;
        }
        return binarySearch + 1;
    }

    private static int binarySearchGreaterThan(int[] iArr, int i, int i2) {
        int binarySearch = binarySearch(iArr, 0, i, i2);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        int i3 = iArr[binarySearch];
        while (binarySearch < i && iArr[binarySearch] == i3) {
            binarySearch++;
        }
        return binarySearch;
    }

    private static int binarySearch(int[] iArr, int i, int i2, int i3) {
        int i4 = i2 - 1;
        int i5 = i;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else if (i7 <= i3) {
                return i6;
            } else {
                i4 = i6 - 1;
            }
        }
        return i5 ^ -1;
    }
}
