package org.nexage.sourcekit.mraid.rtb;

import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.utils.b;
import java.io.Serializable;
import org.json.JSONObject;

public class RtbInfo implements Serializable {
    private String adContent;
    private String adNetwork;
    private int appodealAdType;
    private String encryptedAdUnitInfo;
    private String encryptedSessionInfo;

    public RtbInfo(String str, String str2, String str3, String str4, int i) {
        this.adContent = str;
        this.encryptedAdUnitInfo = str2;
        this.encryptedSessionInfo = str3;
        this.adNetwork = str4;
        this.appodealAdType = i;
    }

    public JSONObject getReportInfo(int i, Context context, String str) {
        Object obj;
        JSONObject a = b.a(context, this.adNetwork, this.appodealAdType, this.adContent, this.encryptedAdUnitInfo, this.encryptedSessionInfo);
        switch (i) {
            case 0:
                obj = "irrelevant";
                break;
            case 1:
                obj = "repetitive";
                break;
            default:
                try {
                    obj = "inappropriate";
                    break;
                } catch (Throwable e) {
                    Appodeal.a(e);
                    break;
                }
        }
        a.put("reason", obj);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("contentType", "image/jpeg");
        jSONObject.put("body", str);
        a.put("screenshot", jSONObject);
        return a;
    }
}
