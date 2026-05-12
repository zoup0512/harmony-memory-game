package com.google.android.gms.ads.formats;

import com.google.android.gms.internal.zzin;

@zzin
public final class NativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int ORIENTATION_ANY = 0;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zzaiv;
    private final int zzaiw;
    private final boolean zzaix;
    private final int zzaiy;

    public @interface AdChoicesPlacement {
    }

    public static final class Builder {
        private boolean zzaiv = false;
        private int zzaiw = 0;
        private boolean zzaix = false;
        private int zzaiy = 1;

        public NativeAdOptions build() {
            return new NativeAdOptions();
        }

        public Builder setAdChoicesPlacement(@AdChoicesPlacement int i) {
            this.zzaiy = i;
            return this;
        }

        public Builder setImageOrientation(int i) {
            this.zzaiw = i;
            return this;
        }

        public Builder setRequestMultipleImages(boolean z) {
            this.zzaix = z;
            return this;
        }

        public Builder setReturnUrlsForImageAssets(boolean z) {
            this.zzaiv = z;
            return this;
        }
    }

    private NativeAdOptions(Builder builder) {
        this.zzaiv = builder.zzaiv;
        this.zzaiw = builder.zzaiw;
        this.zzaix = builder.zzaix;
        this.zzaiy = builder.zzaiy;
    }

    public int getAdChoicesPlacement() {
        return this.zzaiy;
    }

    public int getImageOrientation() {
        return this.zzaiw;
    }

    public boolean shouldRequestMultipleImages() {
        return this.zzaix;
    }

    public boolean shouldReturnUrlsForImageAssets() {
        return this.zzaiv;
    }
}
