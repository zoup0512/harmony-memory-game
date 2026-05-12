package com.appodeal.ads.e;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build.VERSION;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ao;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.networks.SpotXActivity;
import com.appodeal.ads.networks.k;
import com.appodeal.ads.networks.spotx.SpotXVPAIDView;
import com.mopub.common.FullAdType;
import org.nexage.sourcekit.util.DefaultMediaPicker;
import org.nexage.sourcekit.vast.VASTPlayer;
import org.nexage.sourcekit.vast.model.VASTModel;
import org.nexage.sourcekit.vast.processor.VASTProcessor;

public class u extends aq {
    private static ap b;
    private static boolean e = false;
    private boolean c;
    private SpotXVPAIDView d;
    private boolean f;
    private boolean g;
    private VASTPlayer h;
    private ad i;

    private class a implements com.appodeal.ads.networks.spotx.a.a {
        final /* synthetic */ u a;

        private a(u uVar) {
            this.a = uVar;
        }

        public void a(int i, int i2) {
            am.b(i, i2, u.b);
        }

        public void a(String str, int i, int i2) {
            try {
                Pair a = this.a.b(str);
                if (a.first == null || ((String) a.first).equals("") || ((String) a.first).isEmpty() || a.second == null || ((String) a.second).equals("") || ((String) a.second).isEmpty()) {
                    am.b(i, i2, u.b);
                } else {
                    this.a.d = new SpotXVPAIDView(Appodeal.b, a, new v(u.b, i, i2), true, this.a.c);
                }
            } catch (Throwable e) {
                Appodeal.a(e);
                am.b(i, i2, u.b);
            }
        }
    }

    private class b implements com.appodeal.ads.networks.k.a {
        final /* synthetic */ u a;

        private b(u uVar) {
            this.a = uVar;
        }

        public void a(int i, int i2) {
            am.b(i, i2, u.b);
        }

        public void a(String str, int i, int i2, String str2) {
            try {
                this.a.h = new VASTPlayer(Appodeal.b);
                this.a.h.setPrecache(true);
                if (str2 != null) {
                    this.a.h.setXmlUrl(str2);
                }
                this.a.h.loadVideoWithData(str, this.a.i);
            } catch (Throwable e) {
                Appodeal.a(e);
                am.b(i, i2, u.b);
            }
        }
    }

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new u();
            }
            b = new ap(str, i(), aqVar);
        }
        return b;
    }

    private static String[] i() {
        return new String[]{"com.appodeal.ads.networks.SpotXActivity", "org.nexage.sourcekit.vast.activity.VASTActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        this.f = ((ar) ak.m.get(i)).l.optBoolean("vpaid");
        this.g = ((ar) ak.m.get(i)).l.optBoolean(FullAdType.VAST);
        this.i = new ad(b, i, i2);
        if (this.f) {
            if (VERSION.SDK_INT >= 17) {
                String replace = ((ar) ak.m.get(i)).l.optString("url").replace(" ", "+");
                this.c = ((ar) ak.m.get(i)).l.optBoolean("preload", false);
                com.appodeal.ads.networks.spotx.a aVar = new com.appodeal.ads.networks.spotx.a(activity, new a(), i, i2, replace);
                return;
            }
            am.b(i, i2, b);
        } else if (this.g) {
            Activity activity2 = activity;
            k kVar = new k(activity2, new b(), i, i2, ((ar) ak.m.get(i)).l.optString("url").replace(" ", "+"));
        } else {
            am.b(i, i2, b);
        }
    }

    public void a(Activity activity, int i) {
        if (this.f) {
            if (this.d.a() || !this.c) {
                Intent intent = new Intent(activity, SpotXActivity.class);
                intent.addFlags(268435456);
                intent.addFlags(8388608);
                intent.putExtra("type", com.appodeal.ads.ao.b.REWARDED);
                intent.putExtra("videoClass", b.a());
                try {
                    activity.startActivity(intent);
                    am.a(i, b);
                    return;
                } catch (ActivityNotFoundException e) {
                    Appodeal.a("SpotXActivity not found - did you declare it in AndroidManifest.xml?");
                    am.a(true);
                    return;
                }
            }
            am.a(true);
        } else if (this.g) {
            this.h.play(com.appodeal.ads.ao.b.REWARDED, ao.a(), true, this.i);
            am.a(i, b);
        }
    }

    public boolean e() {
        return true;
    }

    private Pair<String, String> b(String str) {
        String str2 = "";
        try {
            VASTProcessor vASTProcessor = new VASTProcessor(new DefaultMediaPicker(Appodeal.b));
            if (vASTProcessor.process(str) != 0) {
                return new Pair("", "");
            }
            Object pickedMediaFileURL;
            VASTModel model = vASTProcessor.getModel();
            if (model.getPickedMediaFileType().equals(WebRequest.CONTENT_TYPE_JAVASCRIPT)) {
                pickedMediaFileURL = model.getPickedMediaFileURL();
            } else {
                String str3 = str2;
            }
            return new Pair(pickedMediaFileURL, model.getAdParameterms());
        } catch (Exception e) {
            return new Pair("", "");
        }
    }

    public SpotXVPAIDView g() {
        return this.d;
    }

    public boolean d() {
        return e;
    }

    public void b(boolean z) {
        e = z;
    }
}
