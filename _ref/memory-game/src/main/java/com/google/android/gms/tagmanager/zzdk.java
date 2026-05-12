package com.google.android.gms.tagmanager;

class zzdk extends Number implements Comparable<zzdk> {
    private double ayr;
    private long ays;
    private boolean ayt = false;

    private zzdk(double d) {
        this.ayr = d;
    }

    private zzdk(long j) {
        this.ays = j;
    }

    public static zzdk zza(Double d) {
        return new zzdk(d.doubleValue());
    }

    public static zzdk zzbu(long j) {
        return new zzdk(j);
    }

    public static zzdk zzph(String str) throws NumberFormatException {
        try {
            return new zzdk(Long.parseLong(str));
        } catch (NumberFormatException e) {
            try {
                return new zzdk(Double.parseDouble(str));
            } catch (NumberFormatException e2) {
                throw new NumberFormatException(String.valueOf(str).concat(" is not a valid TypedNumber"));
            }
        }
    }

    public byte byteValue() {
        return (byte) ((int) longValue());
    }

    public /* synthetic */ int compareTo(Object obj) {
        return zza((zzdk) obj);
    }

    public double doubleValue() {
        return zzcdk() ? (double) this.ays : this.ayr;
    }

    public boolean equals(Object obj) {
        return (obj instanceof zzdk) && zza((zzdk) obj) == 0;
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int hashCode() {
        return new Long(longValue()).hashCode();
    }

    public int intValue() {
        return zzcdm();
    }

    public long longValue() {
        return zzcdl();
    }

    public short shortValue() {
        return zzcdn();
    }

    public String toString() {
        return zzcdk() ? Long.toString(this.ays) : Double.toString(this.ayr);
    }

    public int zza(zzdk com_google_android_gms_tagmanager_zzdk) {
        return (zzcdk() && com_google_android_gms_tagmanager_zzdk.zzcdk()) ? new Long(this.ays).compareTo(Long.valueOf(com_google_android_gms_tagmanager_zzdk.ays)) : Double.compare(doubleValue(), com_google_android_gms_tagmanager_zzdk.doubleValue());
    }

    public boolean zzcdj() {
        return !zzcdk();
    }

    public boolean zzcdk() {
        return this.ayt;
    }

    public long zzcdl() {
        return zzcdk() ? this.ays : (long) this.ayr;
    }

    public int zzcdm() {
        return (int) longValue();
    }

    public short zzcdn() {
        return (short) ((int) longValue());
    }
}
