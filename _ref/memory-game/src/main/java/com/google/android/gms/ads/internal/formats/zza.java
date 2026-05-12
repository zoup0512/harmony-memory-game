package com.google.android.gms.ads.internal.formats;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.applovin.sdk.AppLovinErrorCodes;
import com.google.android.gms.internal.zzin;
import java.util.List;

@zzin
public class zza {
    private static final int zzbes = Color.rgb(12, 174, 206);
    private static final int zzbet = Color.rgb(AppLovinErrorCodes.NO_FILL, AppLovinErrorCodes.NO_FILL, AppLovinErrorCodes.NO_FILL);
    static final int zzbeu = zzbet;
    static final int zzbev = zzbes;
    private final int mBackgroundColor;
    private final int mTextColor;
    private final String zzbew;
    private final List<Drawable> zzbex;
    private final int zzbey;
    private final int zzbez;
    private final int zzbfa;

    public zza(String str, List<Drawable> list, Integer num, Integer num2, Integer num3, int i, int i2) {
        this.zzbew = str;
        this.zzbex = list;
        this.mBackgroundColor = num != null ? num.intValue() : zzbeu;
        this.mTextColor = num2 != null ? num2.intValue() : zzbev;
        this.zzbey = num3 != null ? num3.intValue() : 12;
        this.zzbez = i;
        this.zzbfa = i2;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public String getText() {
        return this.zzbew;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public int getTextSize() {
        return this.zzbey;
    }

    public List<Drawable> zzkp() {
        return this.zzbex;
    }

    public int zzkq() {
        return this.zzbez;
    }

    public int zzkr() {
        return this.zzbfa;
    }
}
