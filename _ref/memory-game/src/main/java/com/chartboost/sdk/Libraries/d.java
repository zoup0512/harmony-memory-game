package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.c;
import com.chartboost.sdk.impl.a;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public final class d {
    private d() {
    }

    protected static String a() {
        Info a;
        try {
            a = a.a().a(c.x());
        } catch (IOException e) {
            a = null;
        } catch (GooglePlayServicesRepairableException e2) {
            a = null;
        } catch (GooglePlayServicesNotAvailableException e3) {
            a = null;
        } catch (Throwable e4) {
            CBLogging.a("CBIdentityAdv", "Security Exception when retrieving AD id", e4);
            a = null;
        } catch (Exception e5) {
            CBLogging.a("CBIdentityAdv", "General Exception when retrieving AD id", e5);
            com.chartboost.sdk.Tracking.a.a(d.class, "getAdvertisingIdClientInfo", e5);
            a = null;
        }
        if (a == null) {
            c.a(c.a.UNKNOWN);
            return null;
        }
        if (a.isLimitAdTrackingEnabled()) {
            c.a(c.a.TRACKING_DISABLED);
        } else {
            c.a(c.a.TRACKING_ENABLED);
        }
        return a.getId();
    }
}
