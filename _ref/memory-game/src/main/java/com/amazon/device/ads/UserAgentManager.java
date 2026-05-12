package com.amazon.device.ads;

import android.content.Context;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;

class UserAgentManager {
    private final ThreadRunner threadRunner;
    private String userAgentStringWithSDKVersion;
    private String userAgentStringWithoutSDKVersion;
    private final WebViewFactory webViewFactory;

    public UserAgentManager() {
        this(new ThreadRunner(), WebViewFactory.getInstance());
    }

    UserAgentManager(ThreadRunner threadRunner, WebViewFactory webViewFactory) {
        this.threadRunner = threadRunner;
        this.webViewFactory = webViewFactory;
    }

    public String getUserAgentString() {
        return this.userAgentStringWithSDKVersion;
    }

    public void setUserAgentString(String str) {
        if (str != null && !str.equals(this.userAgentStringWithoutSDKVersion) && !str.equals(this.userAgentStringWithSDKVersion)) {
            this.userAgentStringWithoutSDKVersion = str;
            this.userAgentStringWithSDKVersion = str + " " + Version.getUserAgentSDKVersion();
        }
    }

    public void populateUserAgentString(final Context context) {
        this.threadRunner.execute(new Runnable() {
            public void run() {
                UserAgentManager.this.setUserAgentString(UserAgentManager.this.webViewFactory.createWebView(context).getSettings().getUserAgentString());
            }
        }, ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }
}
