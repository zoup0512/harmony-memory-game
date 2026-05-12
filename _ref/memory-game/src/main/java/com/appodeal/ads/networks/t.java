package com.appodeal.ads.networks;

public class t {
    public static String a(String str, String str2, String str3, String str4) {
        return "<script language=\"JavaScript\" type=\"text/javascript\">\nrp_account  = '" + str + "';\nrp_site     = '" + str2 + "';\nrp_zonesize = '" + str3 + "';\nrp_adtype   = '" + str4 + "';\nrp_callback = onAdsLoaded;\nfunction onAdsLoaded(response) {\n   if (response.status == \"ok\") {\n       var ad;\n       var html;\n       for (var i = 0; i < response.ads.length; i++) {\n           ad = response.ads[i];\n           if (ad.status == \"ok\") {\n               if (ad.type == \"script\") {\n                   document.write(\"<script type='text/javascript'>\"+ad.script+\"</scr\"+\"ipt>\");\n               }\n               if (ad.type == \"html\") {\n                   document.write(ad.html);\n               }\n           } else {\n                document.write(\"<div>status=\"+ad.status+\"</div>\");\n           }\n       }\n   }\n}\n</script>\n<script type=\"text/javascript\" src=\"http://ads.rubiconproject.com/ad/12530.js\"></script>";
    }
}
