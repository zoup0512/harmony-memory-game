package com.amazon.device.ads;

import android.content.Context;
import java.net.URI;
import java.net.URISyntaxException;

class WebUtils2 {
    private final WebUtilsStatic webUtilsAdapter = new WebUtilsStatic();

    private static class WebUtilsStatic {
        private WebUtilsStatic() {
        }

        boolean launchActivityForIntentLink(String str, Context context) {
            return WebUtils.launchActivityForIntentLink(str, context);
        }

        String getURLEncodedString(String str) {
            return WebUtils.getURLEncodedString(str);
        }

        String getURLDecodedString(String str) {
            return WebUtils.getURLDecodedString(str);
        }

        String getScheme(String str) {
            return WebUtils.getScheme(str);
        }

        String encloseHtml(String str, boolean z) {
            return WebUtils.encloseHtml(str, z);
        }

        void executeWebRequestInThread(String str, boolean z) {
            WebUtils.executeWebRequestInThread(str, z);
        }
    }

    WebUtils2() {
    }

    public boolean isUrlValid(String str) {
        try {
            URI uri = new URI(str);
            return true;
        } catch (URISyntaxException e) {
            return false;
        } catch (NullPointerException e2) {
            return false;
        }
    }

    public boolean launchActivityForIntentLink(String str, Context context) {
        return this.webUtilsAdapter.launchActivityForIntentLink(str, context);
    }

    public String getURLEncodedString(String str) {
        return this.webUtilsAdapter.getURLEncodedString(str);
    }

    public String getURLDecodedString(String str) {
        return this.webUtilsAdapter.getURLDecodedString(str);
    }

    public String getScheme(String str) {
        return this.webUtilsAdapter.getScheme(str);
    }

    public String encloseHtml(String str, boolean z) {
        return this.webUtilsAdapter.encloseHtml(str, z);
    }

    public void executeWebRequestInThread(String str, boolean z) {
        this.webUtilsAdapter.executeWebRequestInThread(str, z);
    }
}
