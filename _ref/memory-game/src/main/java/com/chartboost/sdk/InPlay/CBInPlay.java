package com.chartboost.sdk.InPlay;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.c;
import com.chartboost.sdk.f;

public final class CBInPlay {
    private static final String a = CBInPlay.class.getSimpleName();
    private String b;
    private Bitmap c;
    private String d;
    private a e;

    CBInPlay() {
    }

    public void show() {
        a.a(this);
    }

    public void click() {
        a.b(this);
    }

    public String getLocation() {
        return this.b;
    }

    protected void a(String str) {
        this.b = str;
    }

    public Bitmap getAppIcon() {
        return this.c;
    }

    protected void a(Bitmap bitmap) {
        this.c = bitmap;
    }

    public String getAppName() {
        return this.d;
    }

    protected void b(String str) {
        this.d = str;
    }

    protected a a() {
        return this.e;
    }

    protected void a(a aVar) {
        this.e = aVar;
    }

    public static void cacheInPlay(String location) {
        if (((!c.G().booleanValue() || !c.K()) && (!c.M() || !c.Q())) || !c.r()) {
            return;
        }
        if (TextUtils.isEmpty(location)) {
            CBLogging.b(a, "Inplay location cannot be empty");
            if (c.h() != null) {
                c.h().didFailToLoadInPlay(location, CBImpressionError.INVALID_LOCATION);
                return;
            }
            return;
        }
        f.d().a(location);
    }

    public static boolean hasInPlay(String location) {
        if (!c.r()) {
            return false;
        }
        if (!TextUtils.isEmpty(location)) {
            return f.d().b(location);
        }
        CBLogging.b(a, "Inplay location cannot be empty");
        if (c.h() == null) {
            return false;
        }
        c.h().didFailToLoadInPlay(location, CBImpressionError.INVALID_LOCATION);
        return false;
    }

    public static CBInPlay getInPlay(String location) {
        if (((!c.G().booleanValue() || !c.K()) && (!c.M() || !c.Q())) || !c.r()) {
            return null;
        }
        if (!TextUtils.isEmpty(location)) {
            return f.d().c(location);
        }
        CBLogging.b(a, "Inplay location cannot be empty");
        if (c.h() == null) {
            return null;
        }
        c.h().didFailToLoadInPlay(location, CBImpressionError.INVALID_LOCATION);
        return null;
    }
}
