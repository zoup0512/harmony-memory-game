package com.google.android.gms.ads;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "300x250_as");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT, "160x600_as");
    private final int zzaie;
    private final int zzaif;
    private final String zzaig;

    public AdSize(int i, int i2) {
        String valueOf = i == -1 ? "FULL" : String.valueOf(i);
        String valueOf2 = i2 == -2 ? "AUTO" : String.valueOf(i2);
        String valueOf3 = String.valueOf("_as");
        this(i, i2, new StringBuilder(((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append(valueOf).append("x").append(valueOf2).append(valueOf3).toString());
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + i);
        } else if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.zzaie = i;
            this.zzaif = i2;
            this.zzaig = str;
        } else {
            throw new IllegalArgumentException("Invalid height for AdSize: " + i2);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.zzaie == adSize.zzaie && this.zzaif == adSize.zzaif && this.zzaig.equals(adSize.zzaig);
    }

    public int getHeight() {
        return this.zzaif;
    }

    public int getHeightInPixels(Context context) {
        switch (this.zzaif) {
            case ProfilePictureView.LARGE /*-4*/:
            case ProfilePictureView.NORMAL /*-3*/:
                return -1;
            case -2:
                return AdSizeParcel.zzb(context.getResources().getDisplayMetrics());
            default:
                return zzm.zziw().zza(context, this.zzaif);
        }
    }

    public int getWidth() {
        return this.zzaie;
    }

    public int getWidthInPixels(Context context) {
        switch (this.zzaie) {
            case ProfilePictureView.LARGE /*-4*/:
            case ProfilePictureView.NORMAL /*-3*/:
                return -1;
            case -1:
                return AdSizeParcel.zza(context.getResources().getDisplayMetrics());
            default:
                return zzm.zziw().zza(context, this.zzaie);
        }
    }

    public int hashCode() {
        return this.zzaig.hashCode();
    }

    public boolean isAutoHeight() {
        return this.zzaif == -2;
    }

    public boolean isFluid() {
        return this.zzaie == -3 && this.zzaif == -4;
    }

    public boolean isFullWidth() {
        return this.zzaie == -1;
    }

    public String toString() {
        return this.zzaig;
    }
}
