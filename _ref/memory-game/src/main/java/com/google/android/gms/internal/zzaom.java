package com.google.android.gms.internal;

import com.amazonaws.services.s3.internal.Constants;
import com.applovin.sdk.AppLovinTargetingData;
import com.cube.memorygames.activity.ProfileActivity;
import com.facebook.internal.ServerProtocol;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class zzaom implements Closeable {
    private static final char[] bhc = ")]}'\n".toCharArray();
    private boolean bhd = false;
    private final char[] bhe = new char[1024];
    private int bhf = 0;
    private int bhg = 0;
    private int bhh = 0;
    private long bhi;
    private int bhj;
    private String bhk;
    private int[] bhl = new int[32];
    private int bhm = 0;
    private String[] bhn;
    private int[] bho;
    private final Reader in;
    private int limit = 0;
    private int pos = 0;

    static {
        zzanr.beV = new zzanr() {
            public void zzi(zzaom com_google_android_gms_internal_zzaom) throws IOException {
                if (com_google_android_gms_internal_zzaom instanceof zzaoc) {
                    ((zzaoc) com_google_android_gms_internal_zzaom).e();
                    return;
                }
                int zzag = com_google_android_gms_internal_zzaom.bhh;
                if (zzag == 0) {
                    zzag = com_google_android_gms_internal_zzaom.o();
                }
                if (zzag == 13) {
                    com_google_android_gms_internal_zzaom.bhh = 9;
                } else if (zzag == 12) {
                    com_google_android_gms_internal_zzaom.bhh = 8;
                } else if (zzag == 14) {
                    com_google_android_gms_internal_zzaom.bhh = 10;
                } else {
                    String valueOf = String.valueOf(com_google_android_gms_internal_zzaom.b());
                    int zzai = com_google_android_gms_internal_zzaom.getLineNumber();
                    int zzaj = com_google_android_gms_internal_zzaom.getColumnNumber();
                    String path = com_google_android_gms_internal_zzaom.getPath();
                    throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 70) + String.valueOf(path).length()).append("Expected a name but was ").append(valueOf).append(" ").append(" at line ").append(zzai).append(" column ").append(zzaj).append(" path ").append(path).toString());
                }
            }
        };
    }

    public zzaom(Reader reader) {
        int[] iArr = this.bhl;
        int i = this.bhm;
        this.bhm = i + 1;
        iArr[i] = 6;
        this.bhn = new String[32];
        this.bho = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.in = reader;
    }

    private int getColumnNumber() {
        return (this.pos - this.bhg) + 1;
    }

    private int getLineNumber() {
        return this.bhf + 1;
    }

    private int o() throws IOException {
        int zzdb;
        int i = this.bhl[this.bhm - 1];
        if (i == 1) {
            this.bhl[this.bhm - 1] = 2;
        } else if (i == 2) {
            switch (zzdb(true)) {
                case 44:
                    break;
                case 59:
                    t();
                    break;
                case 93:
                    this.bhh = 4;
                    return 4;
                default:
                    throw zztu("Unterminated array");
            }
        } else if (i == 3 || i == 5) {
            this.bhl[this.bhm - 1] = 4;
            if (i == 5) {
                switch (zzdb(true)) {
                    case 44:
                        break;
                    case 59:
                        t();
                        break;
                    case ProfileActivity.REQUEST_CODE_UCROP /*125*/:
                        this.bhh = 2;
                        return 2;
                    default:
                        throw zztu("Unterminated object");
                }
            }
            zzdb = zzdb(true);
            switch (zzdb) {
                case 34:
                    this.bhh = 13;
                    return 13;
                case 39:
                    t();
                    this.bhh = 12;
                    return 12;
                case ProfileActivity.REQUEST_CODE_UCROP /*125*/:
                    if (i != 5) {
                        this.bhh = 2;
                        return 2;
                    }
                    throw zztu("Expected name");
                default:
                    t();
                    this.pos--;
                    if (zze((char) zzdb)) {
                        this.bhh = 14;
                        return 14;
                    }
                    throw zztu("Expected name");
            }
        } else if (i == 4) {
            this.bhl[this.bhm - 1] = 5;
            switch (zzdb(true)) {
                case 58:
                    break;
                case 61:
                    t();
                    if ((this.pos < this.limit || zzafm(1)) && this.bhe[this.pos] == '>') {
                        this.pos++;
                        break;
                    }
                default:
                    throw zztu("Expected ':'");
            }
        } else if (i == 6) {
            if (this.bhd) {
                w();
            }
            this.bhl[this.bhm - 1] = 7;
        } else if (i == 7) {
            if (zzdb(false) == -1) {
                this.bhh = 17;
                return 17;
            }
            t();
            this.pos--;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (zzdb(true)) {
            case 34:
                if (this.bhm == 1) {
                    t();
                }
                this.bhh = 9;
                return 9;
            case 39:
                t();
                this.bhh = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.bhh = 3;
                return 3;
            case 93:
                if (i == 1) {
                    this.bhh = 4;
                    return 4;
                }
                break;
            case ProfileActivity.REQUEST_CODE_ASK_PERMISSIONS /*123*/:
                this.bhh = 1;
                return 1;
            default:
                this.pos--;
                if (this.bhm == 1) {
                    t();
                }
                zzdb = p();
                if (zzdb != 0) {
                    return zzdb;
                }
                zzdb = q();
                if (zzdb != 0) {
                    return zzdb;
                }
                if (zze(this.bhe[this.pos])) {
                    t();
                    this.bhh = 10;
                    return 10;
                }
                throw zztu("Expected value");
        }
        if (i == 1 || i == 2) {
            t();
            this.pos--;
            this.bhh = 7;
            return 7;
        }
        throw zztu("Unexpected value");
    }

    private int p() throws IOException {
        String str;
        int i;
        char c = this.bhe[this.pos];
        String str2;
        if (c == 't' || c == 'T') {
            str = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
            str2 = "TRUE";
            i = 5;
        } else if (c == AppLovinTargetingData.GENDER_FEMALE || c == 'F') {
            str = "false";
            str2 = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = Constants.NULL_VERSION_ID;
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            if (this.pos + i2 >= this.limit && !zzafm(i2 + 1)) {
                return 0;
            }
            char c2 = this.bhe[this.pos + i2];
            if (c2 != str.charAt(i2) && c2 != r1.charAt(i2)) {
                return 0;
            }
            i2++;
        }
        if ((this.pos + length < this.limit || zzafm(length + 1)) && zze(this.bhe[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.bhh = i;
        return i;
    }

    private int q() throws IOException {
        char[] cArr = this.bhe;
        int i = this.pos;
        long j = 0;
        Object obj = null;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = this.limit;
        int i6 = i;
        while (true) {
            Object obj2;
            if (i6 + i4 == i5) {
                if (i4 == cArr.length) {
                    return 0;
                }
                if (zzafm(i4 + 1)) {
                    i6 = this.pos;
                    i5 = this.limit;
                } else if (i3 != 2 && i2 != 0 && (j != Long.MIN_VALUE || obj != null)) {
                    if (obj == null) {
                        j = -j;
                    }
                    this.bhi = j;
                    this.pos += i4;
                    this.bhh = 15;
                    return 15;
                } else if (i3 == 2 && i3 != 4 && i3 != 7) {
                    return 0;
                } else {
                    this.bhj = i4;
                    this.bhh = 16;
                    return 16;
                }
            }
            char c = cArr[i6 + i4];
            int i7;
            switch (c) {
                case '+':
                    if (i3 != 5) {
                        return 0;
                    }
                    i = 6;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case '-':
                    if (i3 == 0) {
                        i = 1;
                        i7 = i2;
                        obj2 = 1;
                        i3 = i7;
                        continue;
                    } else if (i3 == 5) {
                        i = 6;
                        i3 = i2;
                        obj2 = obj;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (i3 != 2) {
                        return 0;
                    }
                    i = 3;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case 'E':
                case 'e':
                    if (i3 != 2 && i3 != 4) {
                        return 0;
                    }
                    i = 5;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        if (i3 != 1 && i3 != 0) {
                            if (i3 != 2) {
                                if (i3 != 3) {
                                    if (i3 != 5 && i3 != 6) {
                                        i = i3;
                                        i3 = i2;
                                        obj2 = obj;
                                        break;
                                    }
                                    i = 7;
                                    i3 = i2;
                                    obj2 = obj;
                                    break;
                                }
                                i = 4;
                                i3 = i2;
                                obj2 = obj;
                                break;
                            } else if (j != 0) {
                                long j2 = (10 * j) - ((long) (c - 48));
                                i = (j > -922337203685477580L || (j == -922337203685477580L && j2 < j)) ? 1 : 0;
                                i &= i2;
                                obj2 = obj;
                                j = j2;
                                i7 = i3;
                                i3 = i;
                                i = i7;
                                break;
                            } else {
                                return 0;
                            }
                        }
                        j = (long) (-(c - 48));
                        i = 2;
                        i3 = i2;
                        obj2 = obj;
                        continue;
                    } else if (zze(c)) {
                        return 0;
                    }
                    break;
            }
            if (i3 != 2) {
            }
            if (i3 == 2) {
            }
            this.bhj = i4;
            this.bhh = 16;
            return 16;
            i4++;
            obj = obj2;
            i2 = i3;
            i3 = i;
        }
    }

    private String r() throws IOException {
        StringBuilder stringBuilder = null;
        int i = 0;
        while (true) {
            String str;
            if (this.pos + i < this.limit) {
                switch (this.bhe[this.pos + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case ProfileActivity.REQUEST_CODE_ASK_PERMISSIONS /*123*/:
                    case ProfileActivity.REQUEST_CODE_UCROP /*125*/:
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        t();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.bhe.length) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(this.bhe, this.pos, i);
                this.pos = i + this.pos;
                i = !zzafm(1) ? 0 : 0;
            } else if (zzafm(i + 1)) {
            }
            if (stringBuilder == null) {
                str = new String(this.bhe, this.pos, i);
            } else {
                stringBuilder.append(this.bhe, this.pos, i);
                str = stringBuilder.toString();
            }
            this.pos = i + this.pos;
            return str;
        }
    }

    private void s() throws IOException {
        do {
            int i = 0;
            while (this.pos + i < this.limit) {
                switch (this.bhe[this.pos + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case ProfileActivity.REQUEST_CODE_ASK_PERMISSIONS /*123*/:
                    case ProfileActivity.REQUEST_CODE_UCROP /*125*/:
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        t();
                        break;
                    default:
                        i++;
                }
                this.pos = i + this.pos;
                return;
            }
            this.pos = i + this.pos;
        } while (zzafm(1));
    }

    private void t() throws IOException {
        if (!this.bhd) {
            throw zztu("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void u() throws IOException {
        char c;
        do {
            if (this.pos < this.limit || zzafm(1)) {
                char[] cArr = this.bhe;
                int i = this.pos;
                this.pos = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.bhf++;
                    this.bhg = this.pos;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    private char v() throws IOException {
        if (this.pos != this.limit || zzafm(1)) {
            char[] cArr = this.bhe;
            int i = this.pos;
            this.pos = i + 1;
            char c = cArr[i];
            switch (c) {
                case '\n':
                    this.bhf++;
                    this.bhg = this.pos;
                    return c;
                case 'b':
                    return '\b';
                case 'f':
                    return '\f';
                case 'n':
                    return '\n';
                case 'r':
                    return '\r';
                case 't':
                    return '\t';
                case 'u':
                    if (this.pos + 4 <= this.limit || zzafm(4)) {
                        int i2 = this.pos;
                        int i3 = i2 + 4;
                        int i4 = i2;
                        c = '\u0000';
                        for (i = i4; i < i3; i++) {
                            char c2 = this.bhe[i];
                            c = (char) (c << 4);
                            if (c2 >= '0' && c2 <= '9') {
                                c = (char) (c + (c2 - 48));
                            } else if (c2 >= 'a' && c2 <= AppLovinTargetingData.GENDER_FEMALE) {
                                c = (char) (c + ((c2 - 97) + 10));
                            } else if (c2 < 'A' || c2 > 'F') {
                                String str = "\\u";
                                String valueOf = String.valueOf(new String(this.bhe, this.pos, 4));
                                throw new NumberFormatException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                            } else {
                                c = (char) (c + ((c2 - 65) + 10));
                            }
                        }
                        this.pos += 4;
                        return c;
                    }
                    throw zztu("Unterminated escape sequence");
                default:
                    return c;
            }
        }
        throw zztu("Unterminated escape sequence");
    }

    private void w() throws IOException {
        zzdb(true);
        this.pos--;
        if (this.pos + bhc.length <= this.limit || zzafm(bhc.length)) {
            int i = 0;
            while (i < bhc.length) {
                if (this.bhe[this.pos + i] == bhc[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.pos += bhc.length;
        }
    }

    private void zzafl(int i) {
        if (this.bhm == this.bhl.length) {
            Object obj = new int[(this.bhm * 2)];
            Object obj2 = new int[(this.bhm * 2)];
            Object obj3 = new String[(this.bhm * 2)];
            System.arraycopy(this.bhl, 0, obj, 0, this.bhm);
            System.arraycopy(this.bho, 0, obj2, 0, this.bhm);
            System.arraycopy(this.bhn, 0, obj3, 0, this.bhm);
            this.bhl = obj;
            this.bho = obj2;
            this.bhn = obj3;
        }
        int[] iArr = this.bhl;
        int i2 = this.bhm;
        this.bhm = i2 + 1;
        iArr[i2] = i;
    }

    private boolean zzafm(int i) throws IOException {
        Object obj = this.bhe;
        this.bhg -= this.pos;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(obj, this.pos, obj, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            int read = this.in.read(obj, this.limit, obj.length - this.limit);
            if (read == -1) {
                return false;
            }
            this.limit = read + this.limit;
            if (this.bhf == 0 && this.bhg == 0 && this.limit > 0 && obj[0] == '﻿') {
                this.pos++;
                this.bhg++;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    private int zzdb(boolean z) throws IOException {
        char[] cArr = this.bhe;
        int i = this.pos;
        int i2 = this.limit;
        while (true) {
            int lineNumber;
            if (i == i2) {
                this.pos = i;
                if (zzafm(1)) {
                    i = this.pos;
                    i2 = this.limit;
                } else if (!z) {
                    return -1;
                } else {
                    String valueOf = String.valueOf("End of input at line ");
                    lineNumber = getLineNumber();
                    throw new EOFException(new StringBuilder(String.valueOf(valueOf).length() + 30).append(valueOf).append(lineNumber).append(" column ").append(getColumnNumber()).toString());
                }
            }
            lineNumber = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.bhf++;
                this.bhg = lineNumber;
                i = lineNumber;
            } else if (c == ' ' || c == '\r') {
                i = lineNumber;
            } else if (c == '\t') {
                i = lineNumber;
            } else if (c == '/') {
                this.pos = lineNumber;
                if (lineNumber == i2) {
                    this.pos--;
                    boolean zzafm = zzafm(2);
                    this.pos++;
                    if (!zzafm) {
                        return c;
                    }
                }
                t();
                switch (cArr[this.pos]) {
                    case '*':
                        this.pos++;
                        if (zztt("*/")) {
                            i = this.pos + 2;
                            i2 = this.limit;
                            break;
                        }
                        throw zztu("Unterminated comment");
                    case '/':
                        this.pos++;
                        u();
                        i = this.pos;
                        i2 = this.limit;
                        break;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.pos = lineNumber;
                t();
                u();
                i = this.pos;
                i2 = this.limit;
            } else {
                this.pos = lineNumber;
                return c;
            }
        }
    }

    private boolean zze(char c) throws IOException {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case ProfileActivity.REQUEST_CODE_ASK_PERMISSIONS /*123*/:
            case ProfileActivity.REQUEST_CODE_UCROP /*125*/:
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                t();
                break;
            default:
                return true;
        }
        return false;
    }

    private String zzf(char c) throws IOException {
        char[] cArr = this.bhe;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.pos = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    return stringBuilder.toString();
                }
                if (c2 == '\\') {
                    this.pos = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    stringBuilder.append(v());
                    i = this.pos;
                    i2 = this.limit;
                    i4 = i;
                } else if (c2 == '\n') {
                    this.bhf++;
                    this.bhg = i4;
                }
                i3 = i4;
            }
            stringBuilder.append(cArr, i, i3 - i);
            this.pos = i3;
        } while (zzafm(1));
        throw zztu("Unterminated string");
    }

    private void zzg(char c) throws IOException {
        char[] cArr = this.bhe;
        do {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            while (i3 < i2) {
                i = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.pos = i;
                    return;
                }
                if (c2 == '\\') {
                    this.pos = i;
                    v();
                    i = this.pos;
                    i2 = this.limit;
                } else if (c2 == '\n') {
                    this.bhf++;
                    this.bhg = i;
                }
                i3 = i;
            }
            this.pos = i3;
        } while (zzafm(1));
        throw zztu("Unterminated string");
    }

    private boolean zztt(String str) throws IOException {
        while (true) {
            if (this.pos + str.length() > this.limit && !zzafm(str.length())) {
                return false;
            }
            if (this.bhe[this.pos] == '\n') {
                this.bhf++;
                this.bhg = this.pos + 1;
            } else {
                int i = 0;
                while (i < str.length()) {
                    if (this.bhe[this.pos + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    private IOException zztu(String str) throws IOException {
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new zzaop(new StringBuilder((String.valueOf(str).length() + 45) + String.valueOf(path).length()).append(str).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public zzaon b() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        switch (i) {
            case 1:
                return zzaon.BEGIN_OBJECT;
            case 2:
                return zzaon.END_OBJECT;
            case 3:
                return zzaon.BEGIN_ARRAY;
            case 4:
                return zzaon.END_ARRAY;
            case 5:
            case 6:
                return zzaon.BOOLEAN;
            case 7:
                return zzaon.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return zzaon.STRING;
            case 12:
            case 13:
            case 14:
                return zzaon.NAME;
            case 15:
            case 16:
                return zzaon.NUMBER;
            case 17:
                return zzaon.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public void beginArray() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 3) {
            zzafl(1);
            this.bho[this.bhm - 1] = 0;
            this.bhh = 0;
            return;
        }
        String valueOf = String.valueOf(b());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 74) + String.valueOf(path).length()).append("Expected BEGIN_ARRAY but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void beginObject() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 1) {
            zzafl(3);
            this.bhh = 0;
            return;
        }
        String valueOf = String.valueOf(b());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 75) + String.valueOf(path).length()).append("Expected BEGIN_OBJECT but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void close() throws IOException {
        this.bhh = 0;
        this.bhl[0] = 8;
        this.bhm = 1;
        this.in.close();
    }

    public void endArray() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 4) {
            this.bhm--;
            int[] iArr = this.bho;
            int i2 = this.bhm - 1;
            iArr[i2] = iArr[i2] + 1;
            this.bhh = 0;
            return;
        }
        String valueOf = String.valueOf(b());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 72) + String.valueOf(path).length()).append("Expected END_ARRAY but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public void endObject() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 2) {
            this.bhm--;
            this.bhn[this.bhm] = null;
            int[] iArr = this.bho;
            int i2 = this.bhm - 1;
            iArr[i2] = iArr[i2] + 1;
            this.bhh = 0;
            return;
        }
        String valueOf = String.valueOf(b());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 73) + String.valueOf(path).length()).append("Expected END_OBJECT but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public String getPath() {
        StringBuilder append = new StringBuilder().append('$');
        int i = this.bhm;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.bhl[i2]) {
                case 1:
                case 2:
                    append.append('[').append(this.bho[i2]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    append.append('.');
                    if (this.bhn[i2] == null) {
                        break;
                    }
                    append.append(this.bhn[i2]);
                    break;
                default:
                    break;
            }
        }
        return append.toString();
    }

    public boolean hasNext() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public final boolean isLenient() {
        return this.bhd;
    }

    public boolean nextBoolean() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 5) {
            this.bhh = 0;
            int[] iArr = this.bho;
            i = this.bhm - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (i == 6) {
            this.bhh = 0;
            int[] iArr2 = this.bho;
            r2 = this.bhm - 1;
            iArr2[r2] = iArr2[r2] + 1;
            return false;
        } else {
            String valueOf = String.valueOf(b());
            r2 = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 72) + String.valueOf(path).length()).append("Expected a boolean but was ").append(valueOf).append(" at line ").append(r2).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
    }

    public double nextDouble() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 15) {
            this.bhh = 0;
            int[] iArr = this.bho;
            int i2 = this.bhm - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.bhi;
        }
        if (i == 16) {
            this.bhk = new String(this.bhe, this.pos, this.bhj);
            this.pos += this.bhj;
        } else if (i == 8 || i == 9) {
            this.bhk = zzf(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.bhk = r();
        } else if (i != 11) {
            String valueOf = String.valueOf(b());
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 71) + String.valueOf(path).length()).append("Expected a double but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.bhh = 11;
        double parseDouble = Double.parseDouble(this.bhk);
        if (this.bhd || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.bhk = null;
            this.bhh = 0;
            int[] iArr2 = this.bho;
            columnNumber = this.bhm - 1;
            iArr2[columnNumber] = iArr2[columnNumber] + 1;
            return parseDouble;
        }
        columnNumber = getLineNumber();
        int columnNumber2 = getColumnNumber();
        String path2 = getPath();
        throw new zzaop(new StringBuilder(String.valueOf(path2).length() + 102).append("JSON forbids NaN and infinities: ").append(parseDouble).append(" at line ").append(columnNumber).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
    }

    public int nextInt() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        int[] iArr;
        int i2;
        if (i == 15) {
            i = (int) this.bhi;
            if (this.bhi != ((long) i)) {
                long j = this.bhi;
                int lineNumber = getLineNumber();
                int columnNumber = getColumnNumber();
                String path = getPath();
                throw new NumberFormatException(new StringBuilder(String.valueOf(path).length() + 89).append("Expected an int but was ").append(j).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
            }
            this.bhh = 0;
            iArr = this.bho;
            i2 = this.bhm - 1;
            iArr[i2] = iArr[i2] + 1;
        } else {
            String valueOf;
            int columnNumber2;
            String path2;
            if (i == 16) {
                this.bhk = new String(this.bhe, this.pos, this.bhj);
                this.pos += this.bhj;
            } else if (i == 8 || i == 9) {
                this.bhk = zzf(i == 8 ? '\'' : '\"');
                try {
                    i = Integer.parseInt(this.bhk);
                    this.bhh = 0;
                    iArr = this.bho;
                    i2 = this.bhm - 1;
                    iArr[i2] = iArr[i2] + 1;
                } catch (NumberFormatException e) {
                }
            } else {
                valueOf = String.valueOf(b());
                i2 = getLineNumber();
                columnNumber2 = getColumnNumber();
                path2 = getPath();
                throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path2).length()).append("Expected an int but was ").append(valueOf).append(" at line ").append(i2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
            }
            this.bhh = 11;
            double parseDouble = Double.parseDouble(this.bhk);
            i = (int) parseDouble;
            if (((double) i) != parseDouble) {
                valueOf = this.bhk;
                i2 = getLineNumber();
                columnNumber2 = getColumnNumber();
                path2 = getPath();
                throw new NumberFormatException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path2).length()).append("Expected an int but was ").append(valueOf).append(" at line ").append(i2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
            }
            this.bhk = null;
            this.bhh = 0;
            iArr = this.bho;
            i2 = this.bhm - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return i;
    }

    public long nextLong() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 15) {
            this.bhh = 0;
            int[] iArr = this.bho;
            int i2 = this.bhm - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.bhi;
        }
        long parseLong;
        int i3;
        if (i == 16) {
            this.bhk = new String(this.bhe, this.pos, this.bhj);
            this.pos += this.bhj;
        } else if (i == 8 || i == 9) {
            this.bhk = zzf(i == 8 ? '\'' : '\"');
            try {
                parseLong = Long.parseLong(this.bhk);
                this.bhh = 0;
                int[] iArr2 = this.bho;
                i3 = this.bhm - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            String valueOf = String.valueOf(b());
            int lineNumber = getLineNumber();
            i3 = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path).length()).append("Expected a long but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(i3).append(" path ").append(path).toString());
        }
        this.bhh = 11;
        double parseDouble = Double.parseDouble(this.bhk);
        parseLong = (long) parseDouble;
        if (((double) parseLong) != parseDouble) {
            valueOf = this.bhk;
            lineNumber = getLineNumber();
            i3 = getColumnNumber();
            path = getPath();
            throw new NumberFormatException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path).length()).append("Expected a long but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(i3).append(" path ").append(path).toString());
        }
        this.bhk = null;
        this.bhh = 0;
        iArr2 = this.bho;
        i3 = this.bhm - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseLong;
    }

    public String nextName() throws IOException {
        String r;
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 14) {
            r = r();
        } else if (i == 12) {
            r = zzf('\'');
        } else if (i == 13) {
            r = zzf('\"');
        } else {
            String valueOf = String.valueOf(b());
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 69) + String.valueOf(path).length()).append("Expected a name but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.bhh = 0;
        this.bhn[this.bhm - 1] = r;
        return r;
    }

    public void nextNull() throws IOException {
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 7) {
            this.bhh = 0;
            int[] iArr = this.bho;
            int i2 = this.bhm - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        String valueOf = String.valueOf(b());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 67) + String.valueOf(path).length()).append("Expected null but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }

    public String nextString() throws IOException {
        String r;
        int lineNumber;
        int i = this.bhh;
        if (i == 0) {
            i = o();
        }
        if (i == 10) {
            r = r();
        } else if (i == 8) {
            r = zzf('\'');
        } else if (i == 9) {
            r = zzf('\"');
        } else if (i == 11) {
            r = this.bhk;
            this.bhk = null;
        } else if (i == 15) {
            r = Long.toString(this.bhi);
        } else if (i == 16) {
            r = new String(this.bhe, this.pos, this.bhj);
            this.pos += this.bhj;
        } else {
            String valueOf = String.valueOf(b());
            lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            throw new IllegalStateException(new StringBuilder((String.valueOf(valueOf).length() + 71) + String.valueOf(path).length()).append("Expected a string but was ").append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
        }
        this.bhh = 0;
        int[] iArr = this.bho;
        lineNumber = this.bhm - 1;
        iArr[lineNumber] = iArr[lineNumber] + 1;
        return r;
    }

    public final void setLenient(boolean z) {
        this.bhd = z;
    }

    public void skipValue() throws IOException {
        int i = 0;
        do {
            int i2 = this.bhh;
            if (i2 == 0) {
                i2 = o();
            }
            if (i2 == 3) {
                zzafl(1);
                i++;
            } else if (i2 == 1) {
                zzafl(3);
                i++;
            } else if (i2 == 4) {
                this.bhm--;
                i--;
            } else if (i2 == 2) {
                this.bhm--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                s();
            } else if (i2 == 8 || i2 == 12) {
                zzg('\'');
            } else if (i2 == 9 || i2 == 13) {
                zzg('\"');
            } else if (i2 == 16) {
                this.pos += this.bhj;
            }
            this.bhh = 0;
        } while (i != 0);
        int[] iArr = this.bho;
        int i3 = this.bhm - 1;
        iArr[i3] = iArr[i3] + 1;
        this.bhn[this.bhm - 1] = Constants.NULL_VERSION_ID;
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getSimpleName());
        int lineNumber = getLineNumber();
        return new StringBuilder(String.valueOf(valueOf).length() + 39).append(valueOf).append(" at line ").append(lineNumber).append(" column ").append(getColumnNumber()).toString();
    }
}
