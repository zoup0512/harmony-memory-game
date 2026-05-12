package com.amazonaws.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TimingInfo {
    static final int UNKNOWN = -1;
    private Long endTimeNano;
    private final Long startEpochTimeMilli;
    private final long startTimeNano;

    public static TimingInfo startTiming() {
        return new TimingInfo(Long.valueOf(System.currentTimeMillis()), System.nanoTime(), null);
    }

    public static TimingInfo startTimingFullSupport() {
        return new TimingInfoFullSupport(Long.valueOf(System.currentTimeMillis()), System.nanoTime(), null);
    }

    public static TimingInfo startTimingFullSupport(long startTimeNano) {
        return new TimingInfoFullSupport(null, startTimeNano, null);
    }

    public static TimingInfo newTimingInfoFullSupport(long startTimeNano, long endTimeNano) {
        return new TimingInfoFullSupport(null, startTimeNano, Long.valueOf(endTimeNano));
    }

    public static TimingInfo newTimingInfoFullSupport(long startEpochTimeMilli, long startTimeNano, long endTimeNano) {
        return new TimingInfoFullSupport(Long.valueOf(startEpochTimeMilli), startTimeNano, Long.valueOf(endTimeNano));
    }

    public static TimingInfo unmodifiableTimingInfo(long startTimeNano, Long endTimeNano) {
        return new TimingInfoUnmodifiable(null, startTimeNano, endTimeNano);
    }

    public static TimingInfo unmodifiableTimingInfo(long startEpochTimeMilli, long startTimeNano, Long endTimeNano) {
        return new TimingInfoUnmodifiable(Long.valueOf(startEpochTimeMilli), startTimeNano, endTimeNano);
    }

    protected TimingInfo(Long startEpochTimeMilli, long startTimeNano, Long endTimeNano) {
        this.startEpochTimeMilli = startEpochTimeMilli;
        this.startTimeNano = startTimeNano;
        this.endTimeNano = endTimeNano;
    }

    @Deprecated
    public final long getStartTime() {
        if (isStartEpochTimeMilliKnown()) {
            return this.startEpochTimeMilli.longValue();
        }
        return TimeUnit.NANOSECONDS.toMillis(this.startTimeNano);
    }

    @Deprecated
    public final long getStartEpochTimeMilli() {
        Long v = getStartEpochTimeMilliIfKnown();
        return v == null ? -1 : v.longValue();
    }

    public final Long getStartEpochTimeMilliIfKnown() {
        return this.startEpochTimeMilli;
    }

    public final long getStartTimeNano() {
        return this.startTimeNano;
    }

    @Deprecated
    public final long getEndTime() {
        return getEndEpochTimeMilli();
    }

    @Deprecated
    public final long getEndEpochTimeMilli() {
        Long v = getEndEpochTimeMilliIfKnown();
        return v == null ? -1 : v.longValue();
    }

    public final Long getEndEpochTimeMilliIfKnown() {
        return (isStartEpochTimeMilliKnown() && isEndTimeKnown()) ? Long.valueOf(this.startEpochTimeMilli.longValue() + TimeUnit.NANOSECONDS.toMillis(this.endTimeNano.longValue() - this.startTimeNano)) : null;
    }

    public final long getEndTimeNano() {
        return this.endTimeNano == null ? -1 : this.endTimeNano.longValue();
    }

    public final Long getEndTimeNanoIfKnown() {
        return this.endTimeNano;
    }

    @Deprecated
    public final double getTimeTakenMillis() {
        Double v = getTimeTakenMillisIfKnown();
        return v == null ? -1.0d : v.doubleValue();
    }

    public final Double getTimeTakenMillisIfKnown() {
        return isEndTimeKnown() ? Double.valueOf(durationMilliOf(this.startTimeNano, this.endTimeNano.longValue())) : null;
    }

    public static double durationMilliOf(long startTimeNano, long endTimeNano) {
        return ((double) TimeUnit.NANOSECONDS.toMicros(endTimeNano - startTimeNano)) / 1000.0d;
    }

    @Deprecated
    public final long getElapsedTimeMillis() {
        Double v = getTimeTakenMillisIfKnown();
        return v == null ? -1 : v.longValue();
    }

    public final boolean isEndTimeKnown() {
        return this.endTimeNano != null;
    }

    public final boolean isStartEpochTimeMilliKnown() {
        return this.startEpochTimeMilli != null;
    }

    public final String toString() {
        return String.valueOf(getTimeTakenMillis());
    }

    @Deprecated
    public void setEndTime(long endTimeMilli) {
        this.endTimeNano = Long.valueOf(TimeUnit.MILLISECONDS.toNanos(endTimeMilli));
    }

    public void setEndTimeNano(long endTimeNano) {
        this.endTimeNano = Long.valueOf(endTimeNano);
    }

    public TimingInfo endTiming() {
        this.endTimeNano = Long.valueOf(System.nanoTime());
        return this;
    }

    public void addSubMeasurement(String subMeasurementName, TimingInfo timingInfo) {
    }

    public TimingInfo getSubMeasurement(String subMeasurementName) {
        return null;
    }

    public TimingInfo getSubMeasurement(String subMesurementName, int index) {
        return null;
    }

    public TimingInfo getLastSubMeasurement(String subMeasurementName) {
        return null;
    }

    public List<TimingInfo> getAllSubMeasurements(String subMeasurementName) {
        return null;
    }

    public Map<String, List<TimingInfo>> getSubMeasurementsByName() {
        return Collections.emptyMap();
    }

    public Number getCounter(String key) {
        return null;
    }

    public Map<String, Number> getAllCounters() {
        return Collections.emptyMap();
    }

    public void setCounter(String key, long count) {
    }

    public void incrementCounter(String key) {
    }
}
