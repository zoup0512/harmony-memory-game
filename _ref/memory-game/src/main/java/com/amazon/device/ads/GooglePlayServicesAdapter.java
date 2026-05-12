package com.amazon.device.ads;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

class GooglePlayServicesAdapter {
    private static final String LOGTAG = GooglePlayServicesAdapter.class.getSimpleName();
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    GooglePlayServicesAdapter() {
    }

    public AdvertisingInfo getAdvertisingIdentifierInfo() {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(MobileAdsInfoStore.getInstance().getApplicationContext());
            this.logger.v("The Google Play Services Advertising Identifier was successfully retrieved.");
            String id = advertisingIdInfo.getId();
            return new AdvertisingInfo().setAdvertisingIdentifier(id).setLimitAdTrackingEnabled(advertisingIdInfo.isLimitAdTrackingEnabled());
        } catch (IllegalStateException e) {
            this.logger.e("The Google Play Services Advertising Identifier could not be retrieved: %s", e.getMessage());
            return new AdvertisingInfo();
        } catch (IOException e2) {
            this.logger.e("Retrieving the Google Play Services Advertising Identifier caused an IOException.");
            return new AdvertisingInfo();
        } catch (GooglePlayServicesNotAvailableException e3) {
            this.logger.v("Retrieving the Google Play Services Advertising Identifier caused a GooglePlayServicesNotAvailableException.");
            return AdvertisingInfo.createNotAvailable();
        } catch (GooglePlayServicesRepairableException e4) {
            this.logger.v("Retrieving the Google Play Services Advertising Identifier caused a GooglePlayServicesRepairableException.");
            return new AdvertisingInfo();
        }
    }
}
