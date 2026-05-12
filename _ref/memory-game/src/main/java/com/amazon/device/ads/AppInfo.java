package com.amazon.device.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import io.branch.indexing.ContentDiscoveryManifest;
import org.json.JSONObject;

class AppInfo {
    private final JSONObject packageInfoUrlJSON;
    private final PackageManager packageManager;
    private final String packageName;

    public AppInfo(Context context) {
        this(context, Metrics.getInstance().getMetricsCollector(), new JSONObject());
    }

    AppInfo(Context context, MetricsCollector metricsCollector, JSONObject jSONObject) {
        String str = null;
        this.packageInfoUrlJSON = jSONObject;
        this.packageName = context.getPackageName();
        JSONUtils.put(jSONObject, ContentDiscoveryManifest.PACKAGE_NAME_KEY, this.packageName);
        this.packageManager = context.getPackageManager();
        try {
            CharSequence applicationLabel = this.packageManager.getApplicationLabel(context.getApplicationInfo());
            JSONUtils.put(jSONObject, "lbl", applicationLabel != null ? applicationLabel.toString() : null);
        } catch (ArrayIndexOutOfBoundsException e) {
            metricsCollector.incrementMetric(MetricType.APP_INFO_LABEL_INDEX_OUT_OF_BOUNDS);
        }
        try {
            String str2;
            PackageInfo packageInfo = this.packageManager.getPackageInfo(this.packageName, 0);
            if (packageInfo != null) {
                str2 = packageInfo.versionName;
            } else {
                str2 = null;
            }
            JSONUtils.put(jSONObject, "vn", str2);
            if (packageInfo != null) {
                str = Integer.toString(packageInfo.versionCode);
            }
            JSONUtils.put(jSONObject, "v", str);
        } catch (NameNotFoundException e2) {
        }
    }

    protected AppInfo() {
        this.packageName = null;
        this.packageInfoUrlJSON = null;
        this.packageManager = null;
    }

    public JSONObject getPackageInfoJSON() {
        return this.packageInfoUrlJSON;
    }

    public String getPackageInfoJSONString() {
        if (this.packageInfoUrlJSON != null) {
            return this.packageInfoUrlJSON.toString();
        }
        return null;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public PackageManager getPackageManager() {
        return this.packageManager;
    }
}
