package com.amazon.device.ads;

import java.util.Iterator;
import java.util.Set;

class AdData implements Iterable<AAXCreative> {
    private int adHeight;
    private int adWidth;
    private String creative;
    private Set<AAXCreative> creativeTypes;
    private long expirationTimeMs = -1;
    private boolean fetched;
    private String impPixelUrl;
    private String instrPixelUrl;
    private AdProperties properties;

    protected String getCreative() {
        return this.creative;
    }

    protected void setCreative(String str) {
        this.creative = str;
    }

    protected AdProperties getProperties() {
        return this.properties;
    }

    protected void setProperties(AdProperties adProperties) {
        this.properties = adProperties;
    }

    protected Set<AAXCreative> getCreativeTypes() {
        return this.creativeTypes;
    }

    protected void setCreativeTypes(Set<AAXCreative> set) {
        this.creativeTypes = set;
    }

    protected String getInstrumentationPixelUrl() {
        return this.instrPixelUrl;
    }

    protected void setInstrumentationPixelUrl(String str) {
        this.instrPixelUrl = str;
    }

    protected String getImpressionPixelUrl() {
        return this.impPixelUrl;
    }

    protected void setImpressionPixelUrl(String str) {
        this.impPixelUrl = str;
    }

    public boolean getIsFetched() {
        return this.fetched;
    }

    public void setFetched(boolean z) {
        this.fetched = z;
    }

    protected void setHeight(int i) {
        this.adHeight = i;
    }

    public int getHeight() {
        return this.adHeight;
    }

    protected void setWidth(int i) {
        this.adWidth = i;
    }

    public int getWidth() {
        return this.adWidth;
    }

    protected void setExpirationTimeMillis(long j) {
        this.expirationTimeMs = j;
    }

    public boolean isExpired() {
        if (this.expirationTimeMs >= 0 && System.currentTimeMillis() > this.expirationTimeMs) {
            return true;
        }
        return false;
    }

    public long getTimeToExpire() {
        return this.expirationTimeMs - System.currentTimeMillis();
    }

    public Iterator<AAXCreative> iterator() {
        return this.creativeTypes.iterator();
    }
}
