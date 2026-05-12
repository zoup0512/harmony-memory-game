package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.Libraries.h.a;
import java.io.File;

public class i {
    public final File a;
    public final File b;
    public final File c;
    public final File d;
    public final File e;
    public final File f;
    public final File g;
    public final File h;

    i(File file) {
        this.a = new File(file, ".chartboost");
        if (!this.a.exists()) {
            this.a.mkdirs();
        }
        this.b = a(this.a, a.StyleSheets.toString());
        this.c = a(this.a, a.Html.toString());
        this.d = a(this.a, a.Images.toString());
        this.e = a(this.a, a.Javascript.toString());
        this.f = a(this.a, a.TemplateMetaData.toString());
        this.g = a(this.a, a.Videos.toString());
        this.h = new File(this.a, ".adId");
    }

    private static File a(File file, String str) {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file2;
    }
}
