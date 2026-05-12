package com.unity3d.ads2.configuration;

import com.unity3d.ads2.log.DeviceLog;
import com.unity3d.ads2.properties.SdkProperties;
import com.unity3d.ads2.request.WebRequest;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.net.MalformedURLException;
import org.json.JSONObject;

public class Configuration {
    private String _url;
    private Class[] _webAppApiClassList;
    private String _webViewData;
    private String _webViewHash;
    private String _webViewUrl;
    private String _webViewVersion;

    public Configuration(String str) {
        this._url = str;
    }

    public void setConfigUrl(String str) {
        this._url = str;
    }

    public String getConfigUrl() {
        return this._url;
    }

    public void setWebAppApiClassList(Class[] clsArr) {
        this._webAppApiClassList = clsArr;
    }

    public Class[] getWebAppApiClassList() {
        return this._webAppApiClassList;
    }

    public String getWebViewUrl() {
        return this._webViewUrl;
    }

    public void setWebViewUrl(String str) {
        this._webViewUrl = str;
    }

    public String getWebViewHash() {
        return this._webViewHash;
    }

    public void setWebViewHash(String str) {
        this._webViewHash = str;
    }

    public String getWebViewVersion() {
        return this._webViewVersion;
    }

    public String getWebViewData() {
        return this._webViewData;
    }

    public void setWebViewData(String str) {
        this._webViewData = str;
    }

    protected String buildQueryString() {
        return "?ts=" + System.currentTimeMillis() + "&sdkVersion=" + SdkProperties.getVersionCode() + "&sdkVersionName=" + SdkProperties.getVersionName();
    }

    protected void makeRequest() {
        if (this._url == null) {
            throw new MalformedURLException("Base URL is null");
        }
        String str = this._url + buildQueryString();
        DeviceLog.debug("Requesting configuration with: " + str);
        JSONObject jSONObject = new JSONObject(new WebRequest(str, HttpRequest.METHOD_GET, null).makeRequest());
        this._webViewUrl = jSONObject.getString("url");
        if (!jSONObject.isNull(SettingsJsonConstants.ICON_HASH_KEY)) {
            this._webViewHash = jSONObject.getString(SettingsJsonConstants.ICON_HASH_KEY);
        }
        if (jSONObject.has("version")) {
            this._webViewVersion = jSONObject.getString("version");
        }
        if (this._webViewUrl == null || this._webViewUrl.isEmpty()) {
            throw new MalformedURLException("Invalid data. Web view URL is null or empty");
        }
    }
}
