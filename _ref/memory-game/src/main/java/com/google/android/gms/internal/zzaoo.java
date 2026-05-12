package com.google.android.gms.internal;

import com.amazonaws.services.s3.internal.Constants;
import com.facebook.internal.ServerProtocol;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class zzaoo implements Closeable, Flushable {
    private static final String[] bhA = new String[128];
    private static final String[] bhB = ((String[]) bhA.clone());
    private boolean bdS;
    private boolean bdT;
    private String bhC;
    private String bhD;
    private boolean bhd;
    private int[] bhl = new int[32];
    private int bhm = 0;
    private final Writer out;
    private String separator;

    static {
        for (int i = 0; i <= 31; i++) {
            bhA[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        bhA[34] = "\\\"";
        bhA[92] = "\\\\";
        bhA[9] = "\\t";
        bhA[8] = "\\b";
        bhA[10] = "\\n";
        bhA[13] = "\\r";
        bhA[12] = "\\f";
        bhB[60] = "\\u003c";
        bhB[62] = "\\u003e";
        bhB[38] = "\\u0026";
        bhB[61] = "\\u003d";
        bhB[39] = "\\u0027";
    }

    public zzaoo(Writer writer) {
        zzafl(6);
        this.separator = ":";
        this.bdS = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.out = writer;
    }

    private void A() throws IOException {
        if (this.bhD != null) {
            C();
            zztv(this.bhD);
            this.bhD = null;
        }
    }

    private void B() throws IOException {
        if (this.bhC != null) {
            this.out.write("\n");
            int i = this.bhm;
            for (int i2 = 1; i2 < i; i2++) {
                this.out.write(this.bhC);
            }
        }
    }

    private void C() throws IOException {
        int z = z();
        if (z == 5) {
            this.out.write(44);
        } else if (z != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        B();
        zzafn(4);
    }

    private int z() {
        if (this.bhm != 0) {
            return this.bhl[this.bhm - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void zzafl(int i) {
        if (this.bhm == this.bhl.length) {
            Object obj = new int[(this.bhm * 2)];
            System.arraycopy(this.bhl, 0, obj, 0, this.bhm);
            this.bhl = obj;
        }
        int[] iArr = this.bhl;
        int i2 = this.bhm;
        this.bhm = i2 + 1;
        iArr[i2] = i;
    }

    private void zzafn(int i) {
        this.bhl[this.bhm - 1] = i;
    }

    private zzaoo zzc(int i, int i2, String str) throws IOException {
        int z = z();
        if (z != i2 && z != i) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.bhD != null) {
            String str2 = "Dangling name: ";
            String valueOf = String.valueOf(this.bhD);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            this.bhm--;
            if (z == i2) {
                B();
            }
            this.out.write(str);
            return this;
        }
    }

    private void zzde(boolean z) throws IOException {
        switch (z()) {
            case 1:
                zzafn(2);
                B();
                return;
            case 2:
                this.out.append(',');
                B();
                return;
            case 4:
                this.out.append(this.separator);
                zzafn(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.bhd) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            default:
                throw new IllegalStateException("Nesting problem.");
        }
        if (this.bhd || z) {
            zzafn(7);
            return;
        }
        throw new IllegalStateException("JSON must start with an array or an object.");
    }

    private zzaoo zzq(int i, String str) throws IOException {
        zzde(true);
        zzafl(i);
        this.out.write(str);
        return this;
    }

    private void zztv(String str) throws IOException {
        int i = 0;
        String[] strArr = this.bdT ? bhB : bhA;
        this.out.write("\"");
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            String str2;
            if (charAt < '') {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
                if (i < i2) {
                    this.out.write(str, i, i2 - i);
                }
                this.out.write(str2);
                i = i2 + 1;
            } else {
                if (charAt == ' ') {
                    str2 = "\\u2028";
                } else if (charAt == ' ') {
                    str2 = "\\u2029";
                }
                if (i < i2) {
                    this.out.write(str, i, i2 - i);
                }
                this.out.write(str2);
                i = i2 + 1;
            }
        }
        if (i < length) {
            this.out.write(str, i, length - i);
        }
        this.out.write("\"");
    }

    public void close() throws IOException {
        this.out.close();
        int i = this.bhm;
        if (i > 1 || (i == 1 && this.bhl[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.bhm = 0;
    }

    public void flush() throws IOException {
        if (this.bhm == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }

    public zzaoo h() throws IOException {
        A();
        return zzq(1, "[");
    }

    public zzaoo i() throws IOException {
        return zzc(1, 2, "]");
    }

    public boolean isLenient() {
        return this.bhd;
    }

    public zzaoo j() throws IOException {
        A();
        return zzq(3, "{");
    }

    public zzaoo k() throws IOException {
        return zzc(3, 5, "}");
    }

    public zzaoo l() throws IOException {
        if (this.bhD != null) {
            if (this.bdS) {
                A();
            } else {
                this.bhD = null;
                return this;
            }
        }
        zzde(false);
        this.out.write(Constants.NULL_VERSION_ID);
        return this;
    }

    public final void setIndent(String str) {
        if (str.length() == 0) {
            this.bhC = null;
            this.separator = ":";
            return;
        }
        this.bhC = str;
        this.separator = ": ";
    }

    public final void setLenient(boolean z) {
        this.bhd = z;
    }

    public final boolean x() {
        return this.bdT;
    }

    public final boolean y() {
        return this.bdS;
    }

    public zzaoo zza(Number number) throws IOException {
        if (number == null) {
            return l();
        }
        A();
        CharSequence obj = number.toString();
        if (this.bhd || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            zzde(false);
            this.out.append(obj);
            return this;
        }
        String valueOf = String.valueOf(number);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 39).append("Numeric values must be finite, but was ").append(valueOf).toString());
    }

    public zzaoo zzcr(long j) throws IOException {
        A();
        zzde(false);
        this.out.write(Long.toString(j));
        return this;
    }

    public zzaoo zzda(boolean z) throws IOException {
        A();
        zzde(false);
        this.out.write(z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        return this;
    }

    public final void zzdc(boolean z) {
        this.bdT = z;
    }

    public final void zzdd(boolean z) {
        this.bdS = z;
    }

    public zzaoo zztr(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.bhD != null) {
            throw new IllegalStateException();
        } else if (this.bhm == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else {
            this.bhD = str;
            return this;
        }
    }

    public zzaoo zzts(String str) throws IOException {
        if (str == null) {
            return l();
        }
        A();
        zzde(false);
        zztv(str);
        return this;
    }
}
