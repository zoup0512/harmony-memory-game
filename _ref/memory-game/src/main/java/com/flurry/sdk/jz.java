package com.flurry.sdk;

import com.yalantis.ucrop.util.FileUtils;
import java.util.Locale;

public class jz {
    private static final String a = jz.class.getSimpleName();

    public static String a() {
        Object obj;
        if (c().length() > 0) {
            obj = FileUtils.HIDDEN_PREFIX;
        } else {
            String str = "";
        }
        return String.format(Locale.getDefault(), "Flurry_Android_%d_%d.%d.%d%s%s", new Object[]{Integer.valueOf(b()), Integer.valueOf(((Integer) lp.a().a("ReleaseMajorVersion")).intValue()), Integer.valueOf(((Integer) lp.a().a("ReleaseMinorVersion")).intValue()), Integer.valueOf(((Integer) lp.a().a("ReleasePatchVersion")).intValue()), obj, c()});
    }

    private static String c() {
        return (String) lp.a().a("ReleaseBetaVersion");
    }

    public static int b() {
        int intValue = ((Integer) lp.a().a("AgentVersion")).intValue();
        km.a(4, a, "getAgentVersion() = " + intValue);
        return intValue;
    }
}
