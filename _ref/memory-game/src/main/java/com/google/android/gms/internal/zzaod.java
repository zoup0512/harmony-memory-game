package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class zzaod extends zzaoo {
    private static final Writer bfC = new Writer() {
        public void close() throws IOException {
            throw new AssertionError();
        }

        public void flush() throws IOException {
            throw new AssertionError();
        }

        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    };
    private static final zzanb bfD = new zzanb("closed");
    private final List<zzamv> bfB = new ArrayList();
    private String bfE;
    private zzamv bfF = zzamx.bei;

    public zzaod() {
        super(bfC);
    }

    private zzamv g() {
        return (zzamv) this.bfB.get(this.bfB.size() - 1);
    }

    private void zzd(zzamv com_google_android_gms_internal_zzamv) {
        if (this.bfE != null) {
            if (!com_google_android_gms_internal_zzamv.zzczj() || y()) {
                ((zzamy) g()).zza(this.bfE, com_google_android_gms_internal_zzamv);
            }
            this.bfE = null;
        } else if (this.bfB.isEmpty()) {
            this.bfF = com_google_android_gms_internal_zzamv;
        } else {
            zzamv g = g();
            if (g instanceof zzams) {
                ((zzams) g).zzc(com_google_android_gms_internal_zzamv);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public void close() throws IOException {
        if (this.bfB.isEmpty()) {
            this.bfB.add(bfD);
            return;
        }
        throw new IOException("Incomplete document");
    }

    public zzamv f() {
        if (this.bfB.isEmpty()) {
            return this.bfF;
        }
        String valueOf = String.valueOf(this.bfB);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 34).append("Expected one JSON element but was ").append(valueOf).toString());
    }

    public void flush() throws IOException {
    }

    public zzaoo h() throws IOException {
        zzamv com_google_android_gms_internal_zzams = new zzams();
        zzd(com_google_android_gms_internal_zzams);
        this.bfB.add(com_google_android_gms_internal_zzams);
        return this;
    }

    public zzaoo i() throws IOException {
        if (this.bfB.isEmpty() || this.bfE != null) {
            throw new IllegalStateException();
        } else if (g() instanceof zzams) {
            this.bfB.remove(this.bfB.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public zzaoo j() throws IOException {
        zzamv com_google_android_gms_internal_zzamy = new zzamy();
        zzd(com_google_android_gms_internal_zzamy);
        this.bfB.add(com_google_android_gms_internal_zzamy);
        return this;
    }

    public zzaoo k() throws IOException {
        if (this.bfB.isEmpty() || this.bfE != null) {
            throw new IllegalStateException();
        } else if (g() instanceof zzamy) {
            this.bfB.remove(this.bfB.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public zzaoo l() throws IOException {
        zzd(zzamx.bei);
        return this;
    }

    public zzaoo zza(Number number) throws IOException {
        if (number == null) {
            return l();
        }
        if (!isLenient()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                String valueOf = String.valueOf(number);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("JSON forbids NaN and infinities: ").append(valueOf).toString());
            }
        }
        zzd(new zzanb(number));
        return this;
    }

    public zzaoo zzcr(long j) throws IOException {
        zzd(new zzanb(Long.valueOf(j)));
        return this;
    }

    public zzaoo zzda(boolean z) throws IOException {
        zzd(new zzanb(Boolean.valueOf(z)));
        return this;
    }

    public zzaoo zztr(String str) throws IOException {
        if (this.bfB.isEmpty() || this.bfE != null) {
            throw new IllegalStateException();
        } else if (g() instanceof zzamy) {
            this.bfE = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public zzaoo zzts(String str) throws IOException {
        if (str == null) {
            return l();
        }
        zzd(new zzanb(str));
        return this;
    }
}
