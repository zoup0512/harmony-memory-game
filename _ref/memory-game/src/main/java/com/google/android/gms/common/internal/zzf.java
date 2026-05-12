package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class zzf {
    public static final zzf xN = zza((CharSequence) "\t\n\u000b\f\r     　 ᠎ ").zza(zza(' ', ' '));
    public static final zzf xO = zza((CharSequence) "\t\n\u000b\f\r     　").zza(zza(' ', ' ')).zza(zza(' ', ' '));
    public static final zzf xP = zza('\u0000', '');
    public static final zzf xQ;
    public static final zzf xR = zza('\t', '\r').zza(zza('\u001c', ' ')).zza(zzc(' ')).zza(zzc('᠎')).zza(zza(' ', ' ')).zza(zza(' ', '​')).zza(zza(' ', ' ')).zza(zzc(' ')).zza(zzc('　'));
    public static final zzf xS = new zzf() {
        public boolean zzd(char c) {
            return Character.isDigit(c);
        }
    };
    public static final zzf xT = new zzf() {
        public boolean zzd(char c) {
            return Character.isLetter(c);
        }
    };
    public static final zzf xU = new zzf() {
        public boolean zzd(char c) {
            return Character.isLetterOrDigit(c);
        }
    };
    public static final zzf xV = new zzf() {
        public boolean zzd(char c) {
            return Character.isUpperCase(c);
        }
    };
    public static final zzf xW = new zzf() {
        public boolean zzd(char c) {
            return Character.isLowerCase(c);
        }
    };
    public static final zzf xX = zza('\u0000', '\u001f').zza(zza('', ''));
    public static final zzf xY = zza('\u0000', ' ').zza(zza('', ' ')).zza(zzc('­')).zza(zza('؀', '؃')).zza(zza((CharSequence) "۝܏ ឴឵᠎")).zza(zza(' ', '‏')).zza(zza(' ', ' ')).zza(zza(' ', '⁤')).zza(zza('⁪', '⁯')).zza(zzc('　')).zza(zza('?', '')).zza(zza((CharSequence) "﻿￹￺￻"));
    public static final zzf xZ = zza('\u0000', 'ӹ').zza(zzc('־')).zza(zza('א', 'ת')).zza(zzc('׳')).zza(zzc('״')).zza(zza('؀', 'ۿ')).zza(zza('ݐ', 'ݿ')).zza(zza('฀', '๿')).zza(zza('Ḁ', '₯')).zza(zza('℀', '℺')).zza(zza('ﭐ', '﷿')).zza(zza('ﹰ', '﻿')).zza(zza('｡', 'ￜ'));
    public static final zzf ya = new zzf() {
        public zzf zza(zzf com_google_android_gms_common_internal_zzf) {
            zzab.zzy(com_google_android_gms_common_internal_zzf);
            return this;
        }

        public boolean zzb(CharSequence charSequence) {
            zzab.zzy(charSequence);
            return true;
        }

        public boolean zzd(char c) {
            return true;
        }
    };
    public static final zzf yb = new zzf() {
        public zzf zza(zzf com_google_android_gms_common_internal_zzf) {
            return (zzf) zzab.zzy(com_google_android_gms_common_internal_zzf);
        }

        public boolean zzb(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        public boolean zzd(char c) {
            return false;
        }
    };

    class AnonymousClass11 extends zzf {
        final /* synthetic */ char yh;

        AnonymousClass11(char c) {
            this.yh = c;
        }

        public zzf zza(zzf com_google_android_gms_common_internal_zzf) {
            return com_google_android_gms_common_internal_zzf.zzd(this.yh) ? com_google_android_gms_common_internal_zzf : super.zza(com_google_android_gms_common_internal_zzf);
        }

        public boolean zzd(char c) {
            return c == this.yh;
        }
    }

    class AnonymousClass2 extends zzf {
        final /* synthetic */ char yc;
        final /* synthetic */ char yd;

        AnonymousClass2(char c, char c2) {
            this.yc = c;
            this.yd = c2;
        }

        public boolean zzd(char c) {
            return c == this.yc || c == this.yd;
        }
    }

    class AnonymousClass3 extends zzf {
        final /* synthetic */ char[] ye;

        AnonymousClass3(char[] cArr) {
            this.ye = cArr;
        }

        public boolean zzd(char c) {
            return Arrays.binarySearch(this.ye, c) >= 0;
        }
    }

    class AnonymousClass4 extends zzf {
        final /* synthetic */ char yf;
        final /* synthetic */ char yg;

        AnonymousClass4(char c, char c2) {
            this.yf = c;
            this.yg = c2;
        }

        public boolean zzd(char c) {
            return this.yf <= c && c <= this.yg;
        }
    }

    private static class zza extends zzf {
        List<zzf> yi;

        zza(List<zzf> list) {
            this.yi = list;
        }

        public zzf zza(zzf com_google_android_gms_common_internal_zzf) {
            List arrayList = new ArrayList(this.yi);
            arrayList.add((zzf) zzab.zzy(com_google_android_gms_common_internal_zzf));
            return new zza(arrayList);
        }

        public boolean zzd(char c) {
            for (zzf zzd : this.yi) {
                if (zzd.zzd(c)) {
                    return true;
                }
            }
            return false;
        }
    }

    static {
        zzf zza = zza('0', '9');
        zzf com_google_android_gms_common_internal_zzf = zza;
        for (char c : "٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".toCharArray()) {
            com_google_android_gms_common_internal_zzf = com_google_android_gms_common_internal_zzf.zza(zza(c, (char) (c + 9)));
        }
        xQ = com_google_android_gms_common_internal_zzf;
    }

    public static zzf zza(char c, char c2) {
        zzab.zzbo(c2 >= c);
        return new AnonymousClass4(c, c2);
    }

    public static zzf zza(CharSequence charSequence) {
        switch (charSequence.length()) {
            case 0:
                return yb;
            case 1:
                return zzc(charSequence.charAt(0));
            case 2:
                return new AnonymousClass2(charSequence.charAt(0), charSequence.charAt(1));
            default:
                char[] toCharArray = charSequence.toString().toCharArray();
                Arrays.sort(toCharArray);
                return new AnonymousClass3(toCharArray);
        }
    }

    public static zzf zzc(char c) {
        return new AnonymousClass11(c);
    }

    public zzf zza(zzf com_google_android_gms_common_internal_zzf) {
        return new zza(Arrays.asList(new zzf[]{this, (zzf) zzab.zzy(com_google_android_gms_common_internal_zzf)}));
    }

    public boolean zzb(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!zzd(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean zzd(char c);
}
