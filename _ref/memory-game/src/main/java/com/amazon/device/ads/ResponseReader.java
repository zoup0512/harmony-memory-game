package com.amazon.device.ads;

import java.io.InputStream;
import org.json.JSONObject;

class ResponseReader {
    private static final String LOGTAG = ResponseReader.class.getSimpleName();
    private boolean enableLog = false;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private final InputStream stream;

    ResponseReader(InputStream inputStream) {
        this.stream = inputStream;
    }

    public InputStream getInputStream() {
        return this.stream;
    }

    public void enableLog(boolean z) {
        this.enableLog = z;
    }

    public void setExternalLogTag(String str) {
        if (str == null) {
            this.logger.withLogTag(LOGTAG);
        } else {
            this.logger.withLogTag(LOGTAG + " " + str);
        }
    }

    public String readAsString() {
        String readStringFromInputStream = StringUtils.readStringFromInputStream(this.stream);
        if (this.enableLog) {
            this.logger.d("Response Body: %s", readStringFromInputStream);
        }
        return readStringFromInputStream;
    }

    public JSONObject readAsJSON() {
        return JSONUtils.getJSONObjectFromString(readAsString());
    }
}
