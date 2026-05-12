package com.my.target.core.factories;

import com.my.target.core.enums.a;
import com.my.target.core.models.sections.b;
import com.my.target.core.models.sections.c;
import com.my.target.core.models.sections.e;
import com.my.target.core.models.sections.f;
import com.my.target.core.models.sections.g;

/* compiled from: SectionsFactory */
public final class d {
    public static f a(String str, int i) {
        String a = a.a(str);
        if (a != null) {
            if (a.equals(a.a)) {
                return new g(str, i);
            }
            if (a.equals(a.b) || a.equals(a.c)) {
                return new b(str, i);
            }
            if (a.equals(a.d)) {
                return new c(str, i);
            }
            if (a.equals(a.e)) {
                return new e(str, i);
            }
            if (a.equals(a.f)) {
                return new com.my.target.core.models.sections.d(str, i);
            }
        }
        return null;
    }
}
