package com.amazon.device.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

class MetricsCollector {
    private static final String LOGTAG = MetricsCollector.class.getSimpleName();
    private String adTypeMetricTag;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    protected Vector<MetricHit> metricHits = new Vector(60);

    static class CompositeMetricsCollector extends MetricsCollector {
        private final ArrayList<MetricsCollector> collectors;

        public CompositeMetricsCollector(ArrayList<MetricsCollector> arrayList) {
            this.collectors = arrayList;
        }

        public void incrementMetric(MetricType metricType) {
            Iterator it = this.collectors.iterator();
            while (it.hasNext()) {
                ((MetricsCollector) it.next()).incrementMetric(metricType);
            }
        }

        public void setMetricString(MetricType metricType, String str) {
            Iterator it = this.collectors.iterator();
            while (it.hasNext()) {
                ((MetricsCollector) it.next()).setMetricString(metricType, str);
            }
        }

        public void publishMetricInMilliseconds(MetricType metricType, long j) {
            Iterator it = this.collectors.iterator();
            while (it.hasNext()) {
                ((MetricsCollector) it.next()).publishMetricInMilliseconds(metricType, j);
            }
        }

        public void publishMetricInMillisecondsFromNanoseconds(MetricType metricType, long j) {
            Iterator it = this.collectors.iterator();
            while (it.hasNext()) {
                ((MetricsCollector) it.next()).publishMetricInMillisecondsFromNanoseconds(metricType, j);
            }
        }

        public void startMetricInMillisecondsFromNanoseconds(MetricType metricType, long j) {
            Iterator it = this.collectors.iterator();
            while (it.hasNext()) {
                ((MetricsCollector) it.next()).startMetricInMillisecondsFromNanoseconds(metricType, j);
            }
        }

        public void startMetric(MetricType metricType) {
            Iterator it = this.collectors.iterator();
            while (it.hasNext()) {
                ((MetricsCollector) it.next()).startMetric(metricType);
            }
        }

        public void stopMetricInMillisecondsFromNanoseconds(MetricType metricType, long j) {
            Iterator it = this.collectors.iterator();
            while (it.hasNext()) {
                ((MetricsCollector) it.next()).stopMetricInMillisecondsFromNanoseconds(metricType, j);
            }
        }

        public void stopMetric(MetricType metricType) {
            Iterator it = this.collectors.iterator();
            while (it.hasNext()) {
                ((MetricsCollector) it.next()).stopMetric(metricType);
            }
        }
    }

    static class MetricHit {
        public final MetricType metric;

        public MetricHit(MetricType metricType) {
            this.metric = metricType;
        }
    }

    static class MetricHitIncrement extends MetricHit {
        public final int increment;

        public MetricHitIncrement(MetricType metricType, int i) {
            super(metricType);
            this.increment = i;
        }
    }

    static class MetricHitStartTime extends MetricHit {
        public final long startTime;

        public MetricHitStartTime(MetricType metricType, long j) {
            super(metricType);
            this.startTime = j;
        }
    }

    static class MetricHitStopTime extends MetricHit {
        public final long stopTime;

        public MetricHitStopTime(MetricType metricType, long j) {
            super(metricType);
            this.stopTime = j;
        }
    }

    static class MetricHitString extends MetricHit {
        public final String text;

        public MetricHitString(MetricType metricType, String str) {
            super(metricType);
            this.text = str;
        }
    }

    static class MetricHitTotalTime extends MetricHit {
        public final long totalTime;

        public MetricHitTotalTime(MetricType metricType, long j) {
            super(metricType);
            this.totalTime = j;
        }
    }

    public Vector<MetricHit> getMetricHits() {
        return this.metricHits;
    }

    public void incrementMetric(MetricType metricType) {
        this.logger.d("METRIC Increment " + metricType.toString());
        this.metricHits.add(new MetricHitIncrement(metricType, 1));
    }

    public void setMetricString(MetricType metricType, String str) {
        this.logger.d("METRIC Set " + metricType.toString() + ": " + str);
        this.metricHits.add(new MetricHitString(metricType, str));
    }

    public void publishMetricInMilliseconds(MetricType metricType, long j) {
        this.logger.d("METRIC Publish " + metricType.toString());
        this.metricHits.add(new MetricHitTotalTime(metricType, j));
    }

    public void publishMetricInMillisecondsFromNanoseconds(MetricType metricType, long j) {
        publishMetricInMilliseconds(metricType, NumberUtils.convertToMillisecondsFromNanoseconds(j));
    }

    public void startMetricInMillisecondsFromNanoseconds(MetricType metricType, long j) {
        this.logger.d("METRIC Start " + metricType.toString());
        this.metricHits.add(new MetricHitStartTime(metricType, NumberUtils.convertToMillisecondsFromNanoseconds(j)));
    }

    public void startMetric(MetricType metricType) {
        startMetricInMillisecondsFromNanoseconds(metricType, System.nanoTime());
    }

    public void stopMetricInMillisecondsFromNanoseconds(MetricType metricType, long j) {
        this.logger.d("METRIC Stop " + metricType.toString());
        this.metricHits.add(new MetricHitStopTime(metricType, NumberUtils.convertToMillisecondsFromNanoseconds(j)));
    }

    public void stopMetric(MetricType metricType) {
        stopMetricInMillisecondsFromNanoseconds(metricType, System.nanoTime());
    }

    public void setAdTypeMetricTag(String str) {
        this.adTypeMetricTag = str;
    }

    public String getAdTypeMetricTag() {
        return this.adTypeMetricTag;
    }

    public boolean isMetricsCollectorEmpty() {
        return this.metricHits.isEmpty();
    }
}
