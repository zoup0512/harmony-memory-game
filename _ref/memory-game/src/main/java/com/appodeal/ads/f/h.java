package com.appodeal.ads.f;

import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class h implements Comparable {
    private final List<String> a;

    public h(String str) {
        this.a = a(str);
    }

    private static List<String> a(String str) {
        if (str == null) {
            return new LinkedList();
        }
        List<String> linkedList = new LinkedList();
        for (String split : str.split("\\.")) {
            Collections.addAll(linkedList, split.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"));
        }
        return linkedList;
    }

    public int compareTo(@NonNull Object obj) {
        String str;
        Iterator it = this.a.iterator();
        Iterator it2 = ((h) obj).a.iterator();
        while (true) {
            String str2;
            if (it2.hasNext()) {
                str2 = (String) it2.next();
            } else {
                str2 = null;
            }
            if (it.hasNext()) {
                str = (String) it.next();
            } else {
                str = null;
            }
            if (str == null || str2 == null) {
                if (str != null && str2 != null) {
                    return -1;
                }
                if (str == null && str2 == null) {
                    return 1;
                }
                return 0;
            } else if (str.matches("\\d+")) {
                if (!str2.matches("\\d+")) {
                    return 1;
                }
                r0 = Integer.valueOf(str).compareTo(Integer.valueOf(str2));
                if (r0 != 0) {
                    return r0;
                }
            } else if (str2.matches("\\d+")) {
                return -1;
            } else {
                r0 = str.compareTo(str2);
                if (r0 != 0) {
                    return r0;
                }
            }
        }
        if (str != null) {
        }
        if (str == null) {
        }
        return 0;
    }
}
