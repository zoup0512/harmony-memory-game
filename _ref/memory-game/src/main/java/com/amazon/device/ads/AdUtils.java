package com.amazon.device.ads;

import android.content.Context;
import android.content.pm.ActivityInfo;
import java.util.HashSet;

class AdUtils {
    public static final String REQUIRED_ACTIVITY = "com.amazon.device.ads.AdActivity";
    private static AdUtilsExecutor executor = new AdUtilsExecutor();

    static class AdUtilsExecutor {
        private boolean hasRequiredActivities = false;
        private final HashSet<String> requiredActivities = new HashSet();

        AdUtilsExecutor() {
            this.requiredActivities.add(AdUtils.REQUIRED_ACTIVITY);
        }

        boolean checkDefinedActivities(Context context) {
            boolean z = true;
            if (!this.hasRequiredActivities) {
                HashSet hashSet = new HashSet();
                try {
                    for (ActivityInfo activityInfo : context.getPackageManager().getPackageArchiveInfo(context.getPackageCodePath(), 1).activities) {
                        hashSet.add(activityInfo.name);
                    }
                    this.hasRequiredActivities = hashSet.containsAll(this.requiredActivities);
                    z = this.hasRequiredActivities;
                } catch (Exception e) {
                    this.hasRequiredActivities = true;
                }
            }
            return z;
        }

        void setConnectionMetrics(ConnectionInfo connectionInfo, MetricsCollector metricsCollector) {
            if (connectionInfo != null) {
                if (connectionInfo.isWiFi()) {
                    metricsCollector.incrementMetric(MetricType.WIFI_PRESENT);
                } else {
                    metricsCollector.setMetricString(MetricType.CONNECTION_TYPE, connectionInfo.getConnectionType());
                }
            }
            DeviceInfo deviceInfo = MobileAdsInfoStore.getInstance().getDeviceInfo();
            if (deviceInfo.getCarrier() != null) {
                metricsCollector.setMetricString(MetricType.CARRIER_NAME, deviceInfo.getCarrier());
            }
        }

        double getViewportInitialScale(double d) {
            return AndroidTargetUtils.isAtLeastAndroidAPI(19) ? 1.0d : d;
        }

        double calculateScalingMultiplier(int i, int i2, int i3, int i4) {
            double d = ((double) i3) / ((double) i);
            double d2 = ((double) i4) / ((double) i2);
            if ((d2 < d || d == 0.0d) && d2 != 0.0d) {
                d = d2;
            }
            return d == 0.0d ? 1.0d : d;
        }

        int pixelToDeviceIndependentPixel(int i) {
            return (int) (((float) i) / getScalingFactorAsFloat());
        }

        int deviceIndependentPixelToPixel(int i) {
            return (int) (i == -1 ? (float) i : ((float) i) * getScalingFactorAsFloat());
        }

        float getScalingFactorAsFloat() {
            return MobileAdsInfoStore.getInstance().getDeviceInfo().getScalingFactorAsFloat();
        }
    }

    private AdUtils() {
    }

    static boolean checkDefinedActivities(Context context) {
        return executor.checkDefinedActivities(context);
    }

    static void setConnectionMetrics(ConnectionInfo connectionInfo, MetricsCollector metricsCollector) {
        executor.setConnectionMetrics(connectionInfo, metricsCollector);
    }

    public static double getViewportInitialScale(double d) {
        return executor.getViewportInitialScale(d);
    }

    public static double calculateScalingMultiplier(int i, int i2, int i3, int i4) {
        return executor.calculateScalingMultiplier(i, i2, i3, i4);
    }

    public static int pixelToDeviceIndependentPixel(int i) {
        return executor.pixelToDeviceIndependentPixel(i);
    }

    public static int deviceIndependentPixelToPixel(int i) {
        return executor.deviceIndependentPixelToPixel(i);
    }

    public static float getScalingFactorAsFloat() {
        return executor.getScalingFactorAsFloat();
    }
}
