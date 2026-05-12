package org.nexage.sourcekit.mraid.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MRAIDHtmlProcessor {
    public static String processRawHtml(String str) {
        int i = 0;
        Object stringBuffer = new StringBuffer(str);
        Matcher matcher = Pattern.compile("<script\\s+[^>]*\\bsrc\\s*=\\s*([\\\"\\'])mraid\\.js\\1[^>]*>\\s*</script>\\n*", 2).matcher(stringBuffer);
        if (matcher.find()) {
            stringBuffer.delete(matcher.start(), matcher.end());
        }
        boolean contains = str.contains("<html");
        boolean contains2 = str.contains("<head");
        boolean contains3 = str.contains("<body");
        if (!contains && (contains2 || contains3)) {
            return str;
        }
        if (contains && !contains3) {
            return str;
        }
        String property = System.getProperty("line.separator");
        if (!contains) {
            stringBuffer.insert(0, "<html>" + property + "<head>" + property + "</head>" + property + "<body><div align='center'>" + property);
            stringBuffer.append("</div></body>").append(property).append("</html>");
        } else if (!contains2) {
            Matcher matcher2 = Pattern.compile("<html[^>]*>", 2).matcher(stringBuffer);
            for (int i2 = 0; matcher2.find(i2); i2 = matcher2.end()) {
                stringBuffer.insert(matcher2.end(), property + "<head>" + property + "</head>");
            }
        }
        String str2 = "<meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no' />";
        String str3 = "<style>" + property + "body { margin:0; padding:0;}" + property + "*:not(input) { -webkit-touch-callout:none; -webkit-user-select:none; -webkit-text-size-adjust:none; }" + property + "</style>";
        Matcher matcher3 = Pattern.compile("<head[^>]*>", 2).matcher(stringBuffer);
        while (matcher3.find(i)) {
            stringBuffer.insert(matcher3.end(), property + str2 + property + str3);
            i = matcher3.end();
        }
        return stringBuffer.toString();
    }
}
