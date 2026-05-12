package com.amazon.device.ads;

class AdSlot {
    private final String KEY_SLOT_ID = "slotId";
    private final AdController adController;
    private AdError adError;
    private final AdTargetingOptions adOptions;
    private boolean deferredLoad = false;
    private final String slotID;
    private int slotNumber;

    AdSlot(AdController adController, AdTargetingOptions adTargetingOptions) {
        this.adController = adController;
        if (adTargetingOptions == null) {
            this.adOptions = new AdTargetingOptions();
            this.slotID = null;
            return;
        }
        this.adOptions = adTargetingOptions;
        this.slotID = this.adOptions.getAdvancedOption("slotId");
    }

    public AdSlot setDeferredLoad(boolean z) {
        this.deferredLoad = z;
        return this;
    }

    public AdTargetingOptions getAdTargetingOptions() {
        return this.adOptions;
    }

    public AdSize getRequestedAdSize() {
        return this.adController.getAdSize();
    }

    void setSlotNumber(int i) {
        this.slotNumber = i;
    }

    int getSlotNumber() {
        return this.slotNumber;
    }

    void setAdError(AdError adError) {
        this.adError = adError;
    }

    AdError getAdError() {
        return this.adError;
    }

    String getMaxSize() {
        return this.adController.getMaxSize();
    }

    MetricsCollector getMetricsCollector() {
        return this.adController.getMetricsCollector();
    }

    void setAdData(AdData adData) {
        this.adController.setAdData(adData);
    }

    boolean isFetched() {
        return this.adController.getAdData() != null && this.adController.getAdData().getIsFetched();
    }

    void adFailed(AdError adError) {
        this.adController.adFailed(adError);
    }

    void initializeAd() {
        this.adController.initialize(this.slotID);
    }

    boolean prepareForAdLoad(long j) {
        return this.adController.prepareForAdLoad(j, this.deferredLoad);
    }

    boolean isValid() {
        return this.adController.isValid();
    }

    void setConnectionInfo(ConnectionInfo connectionInfo) {
        this.adController.setConnectionInfo(connectionInfo);
    }

    boolean canBeUsed() {
        return this.adController.canBeUsed();
    }
}
