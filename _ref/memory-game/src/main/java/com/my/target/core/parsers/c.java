package com.my.target.core.parsers;

import android.text.TextUtils;
import android.util.Pair;
import android.util.Xml;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.mobileads.VastIconXmlManager;
import com.my.target.Tracer;
import com.my.target.core.models.d;
import com.my.target.core.models.g;
import com.my.target.core.models.i;
import com.my.target.core.models.sections.f;
import com.my.target.core.models.sections.h;
import com.my.target.nativeads.models.VideoData;
import com.yalantis.ucrop.util.FileUtils;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: VASTParser */
public class c {
    private static final String[] d = new String[]{"linkTxt"};
    public ArrayList<b> a = new ArrayList();
    public boolean b;
    public String c;
    private ArrayList<String> e = new ArrayList();
    private String f;
    private ArrayList<i> g;
    private ArrayList<com.my.target.core.models.banners.i> h = new ArrayList();
    private final com.my.target.core.parsers.a.a i;

    /* compiled from: VASTParser */
    public class a extends Exception {
        final /* synthetic */ c a;

        public a(c cVar, String str) {
            this.a = cVar;
            super(str);
        }
    }

    /* compiled from: VASTParser */
    static class b {
        public List<Pair<String, String>> a = new ArrayList();
        public List<Pair<String, String>> b = new ArrayList();
        public int c;
        public float d;
        private final com.my.target.core.models.banners.i e = ((com.my.target.core.models.banners.i) com.my.target.core.factories.a.a("", AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, com.my.target.core.enums.a.g));

        public final com.my.target.core.models.banners.i a() {
            return this.e;
        }
    }

    private c(com.my.target.core.parsers.a.a aVar) {
        this.i = aVar;
    }

    public static boolean a(String str) {
        String trim = str.trim();
        return trim.startsWith("<VAST") || trim.startsWith("<?xml");
    }

    public static void a(String str, com.my.target.core.models.c cVar, d dVar, com.my.target.core.parsers.a.a aVar) throws a {
        Exception e;
        c cVar2 = new c(aVar);
        cVar2.g = new ArrayList();
        try {
            String str2;
            com.my.target.core.models.sections.d dVar2;
            cVar2.b(str);
            Iterator it = cVar2.a.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (cVar2.b) {
                    if (TextUtils.isEmpty(bVar.a().getId())) {
                        it.remove();
                    } else if (bVar.a().u().isEmpty()) {
                        it.remove();
                    }
                }
                Collection arrayList = new ArrayList();
                for (Pair pair : bVar.b) {
                    arrayList.add(cVar2.a((String) pair.first, (String) pair.second));
                }
                for (Pair pair2 : bVar.a) {
                    Object iVar;
                    String str3 = (String) pair2.first;
                    str2 = (String) pair2.second;
                    if ("start".equalsIgnoreCase(str3)) {
                        iVar = new i("playbackStarted", str2);
                    } else if ("firstQuartile".equalsIgnoreCase(str3)) {
                        r2 = new g("playheadReachedValue", str2);
                        r2.b(25.0f);
                        r1 = r2;
                    } else if ("midpoint".equalsIgnoreCase(str3)) {
                        r2 = new g("playheadReachedValue", str2);
                        r2.b(50.0f);
                        r1 = r2;
                    } else if ("thirdQuartile".equalsIgnoreCase(str3)) {
                        r2 = new g("playheadReachedValue", str2);
                        r2.b(75.0f);
                        r1 = r2;
                    } else if ("complete".equalsIgnoreCase(str3)) {
                        r2 = new g("playheadReachedValue", str2);
                        r2.b(100.0f);
                        r1 = r2;
                    } else if ("creativeView".equalsIgnoreCase(str3)) {
                        r1 = new i("playbackStarted", str2);
                    } else {
                        if (!("mute".equalsIgnoreCase(str3) || "unmute".equalsIgnoreCase(str3))) {
                            if ("pause".equalsIgnoreCase(str3)) {
                                r1 = new i("playbackPaused", str2);
                            } else if ("resume".equalsIgnoreCase(str3)) {
                                r1 = new i("playbackResumed", str2);
                            } else if ("fullscreen".equalsIgnoreCase(str3)) {
                                r1 = new i("fullscreenOn", str2);
                            } else if ("exitFullscreen".equalsIgnoreCase(str3)) {
                                r1 = new i("fullscreenOff", str2);
                            } else if ("skip".equalsIgnoreCase(str3)) {
                                r1 = new i("closedByUser", str2);
                            } else if ("error".equalsIgnoreCase(str3)) {
                                r1 = new i("error", str2);
                            } else if ("ClickTracking".equalsIgnoreCase(str3)) {
                                r1 = new i("click", str2);
                            }
                        }
                        iVar = null;
                    }
                    if (iVar != null) {
                        arrayList.add(iVar);
                    }
                }
                if (TextUtils.isEmpty(bVar.a().getId())) {
                    cVar2.g.addAll(arrayList);
                } else {
                    com.my.target.core.models.banners.i a = bVar.a();
                    if (bVar.d > 0.0f || bVar.c > 0) {
                        a.f(true);
                        if (bVar.d > 0.0f) {
                            a.b(bVar.d);
                        } else {
                            a.b(((float) bVar.c) * (a.n() / 100.0f));
                        }
                    }
                    a.a((ArrayList) arrayList);
                    if (bVar.a().n() > 0.0f && !bVar.a().u().isEmpty()) {
                        cVar2.h.add(bVar.a());
                    }
                }
            }
            Object obj = cVar2.c;
            ArrayList arrayList2 = cVar2.h;
            ArrayList arrayList3 = cVar2.e;
            ArrayList arrayList4 = cVar2.g;
            CharSequence charSequence = cVar2.f;
            if (dVar == null || !TextUtils.isEmpty(charSequence)) {
                CharSequence charSequence2 = charSequence;
            } else {
                str2 = dVar.m();
            }
            if (cVar.c(com.my.target.core.enums.a.f) != null) {
                dVar2 = (com.my.target.core.models.sections.d) cVar.c(com.my.target.core.enums.a.f);
            } else {
                f fVar = (com.my.target.core.models.sections.d) com.my.target.core.factories.d.a(com.my.target.core.enums.a.f, -1);
                cVar.a(fVar);
            }
            if (dVar2 != null && dVar != null) {
                if (!arrayList2.isEmpty()) {
                    a(cVar, dVar, arrayList2, str2, arrayList3);
                } else if (!TextUtils.isEmpty(obj)) {
                    if (dVar.i() >= 5) {
                        Tracer.d("Max redirect limit reached, redirect was dropped: " + dVar.a());
                    } else if (TextUtils.isEmpty(obj)) {
                        Tracer.d("neither banner nor wrapper is present");
                    } else {
                        d dVar3 = new d(obj);
                        dVar3.a(dVar.l());
                        dVar3.b(str2);
                        Iterator it2 = arrayList4.iterator();
                        while (it2.hasNext()) {
                            dVar3.a((i) it2.next());
                        }
                        it2 = dVar.f().iterator();
                        while (it2.hasNext()) {
                            dVar3.a((i) it2.next());
                        }
                        it2 = dVar.g().iterator();
                        while (it2.hasNext()) {
                            dVar3.b((i) it2.next());
                        }
                        it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            dVar3.b(new i("impression", (String) it2.next()));
                        }
                        dVar.b(dVar3);
                    }
                }
            }
        } catch (XmlPullParserException e2) {
            e = e2;
            throw new a(cVar2, e.getMessage());
        } catch (IOException e3) {
            e = e3;
            throw new a(cVar2, e.getMessage());
        } catch (IllegalStateException e4) {
            e = e4;
            throw new a(cVar2, e.getMessage());
        }
    }

    private static void a(com.my.target.core.models.c cVar, d dVar, ArrayList<com.my.target.core.models.banners.i> arrayList, String str, ArrayList<String> arrayList2) {
        String bVar;
        int size = arrayList.size();
        CharSequence l = dVar.l();
        if (TextUtils.isEmpty(l)) {
            bVar = com.my.target.core.enums.b.PREROLL.toString();
        } else {
            CharSequence charSequence = l;
        }
        h c = ((com.my.target.core.models.sections.d) cVar.c(com.my.target.core.enums.a.f)).c(bVar);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            c.a(new i("impression", (String) it.next()));
        }
        for (int i = 0; i < size; i++) {
            com.my.target.core.models.banners.i iVar = (com.my.target.core.models.banners.i) arrayList.get(i);
            if (!iVar.o()) {
                iVar.f(c.i().a());
                iVar.b(c.i().b());
            }
            ArrayList f = dVar.f();
            float n = iVar.n();
            Iterator it2 = f.iterator();
            while (it2.hasNext()) {
                i iVar2 = (i) it2.next();
                if ("playheadReachedValue".equals(iVar2.c())) {
                    float b = ((g) iVar2).b();
                    if (b > 0.0f) {
                        ((g) iVar2).a((float) ((((double) n) / 100.0d) * ((double) b)));
                    }
                }
            }
            iVar.g(str);
            iVar.a(f);
        }
        if (arrayList.size() <= 0 || !dVar.n()) {
            it = arrayList.iterator();
            while (it.hasNext()) {
                c.a((com.my.target.core.models.banners.c) it.next());
            }
            return;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            c.a(i2, (com.my.target.core.models.banners.c) arrayList.get(i2));
        }
    }

    private void b(String str) throws XmlPullParserException, IOException, a {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        newPullParser.setInput(new StringReader(str));
        newPullParser.nextTag();
        if (2 == newPullParser.getEventType() && "VAST".equals(newPullParser.getName())) {
            newPullParser.require(2, null, "VAST");
            while (newPullParser.nextTag() == 2) {
                if (newPullParser.getEventType() == 2 && newPullParser.getName().equals("Ad")) {
                    a(newPullParser);
                }
            }
            newPullParser.require(3, null, "VAST");
            return;
        }
        Tracer.d("Server responded with non-VAST XML");
    }

    private void a(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, a {
        xmlPullParser.require(2, null, "Ad");
        while (xmlPullParser.nextTag() == 2) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("InLine")) {
                    Tracer.d("VAST file contains inline ad information.");
                    xmlPullParser.require(2, null, "InLine");
                    while (xmlPullParser.nextTag() == 2) {
                        if (xmlPullParser.getEventType() == 2) {
                            String name2 = xmlPullParser.getName();
                            if (name2 != null && name2.equals("Impression")) {
                                xmlPullParser.require(2, null, "Impression");
                                Object c = c(xmlPullParser);
                                if (!TextUtils.isEmpty(c)) {
                                    this.e.add(c);
                                    Tracer.d("Impression tracker url for inline: " + c);
                                }
                                xmlPullParser.require(3, null, "Impression");
                            } else if (name2 != null && name2.equals("Creatives")) {
                                d(xmlPullParser);
                            } else if (name2 == null || !name2.equals("Extensions")) {
                                f(xmlPullParser);
                            } else {
                                e(xmlPullParser);
                            }
                        }
                    }
                    xmlPullParser.require(3, null, "InLine");
                }
                if (name.equals("Wrapper")) {
                    Tracer.d("VAST file contains wrapped ad information. []");
                    b(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, null, "Ad");
    }

    private void b(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, a {
        xmlPullParser.require(2, null, "Wrapper");
        while (xmlPullParser.nextTag() == 2) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name != null && name.equals("Impression")) {
                    xmlPullParser.require(2, null, "Impression");
                    Object c = c(xmlPullParser);
                    if (!TextUtils.isEmpty(c)) {
                        this.e.add(c);
                        Tracer.d("Impression tracker url for wrapper: " + c);
                    }
                    xmlPullParser.require(3, null, "Impression");
                } else if (name != null && name.equals("Creatives")) {
                    d(xmlPullParser);
                } else if (name != null && name.equals("Extensions")) {
                    e(xmlPullParser);
                } else if (name == null || !name.equals("VASTAdTagURI")) {
                    f(xmlPullParser);
                } else {
                    xmlPullParser.require(2, null, "VASTAdTagURI");
                    name = c(xmlPullParser);
                    xmlPullParser.require(3, null, "VASTAdTagURI");
                    this.c = name;
                }
            }
        }
        xmlPullParser.require(3, null, "Wrapper");
    }

    private static String c(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str = "";
        if (xmlPullParser.next() == 4) {
            str = xmlPullParser.getText();
            xmlPullParser.nextTag();
        } else {
            Tracer.d("No text: " + xmlPullParser.getName());
        }
        return str.trim();
    }

    private void d(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, a {
        xmlPullParser.require(2, null, "Creatives");
        while (xmlPullParser.nextTag() == 2) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null || !name.equals("Creative")) {
                    f(xmlPullParser);
                } else {
                    name = xmlPullParser.getAttributeValue(null, "id");
                    xmlPullParser.require(2, null, "Creative");
                    while (xmlPullParser.nextTag() == 2) {
                        if (xmlPullParser.getEventType() == 2) {
                            String name2 = xmlPullParser.getName();
                            if (name2 == null || !name2.equals("Linear")) {
                                f(xmlPullParser);
                            } else {
                                b bVar = new b();
                                bVar.a().j(name);
                                this.i.e = "Creative id = " + name;
                                String attributeValue = xmlPullParser.getAttributeValue(null, "skipoffset");
                                if (attributeValue != null && attributeValue.contains("%")) {
                                    bVar.c = Integer.parseInt(attributeValue.substring(0, attributeValue.length() - 1));
                                    Tracer.d("Linear skipoffset is " + bVar.c + " [%]");
                                } else if (attributeValue != null && attributeValue.contains(":")) {
                                    try {
                                        bVar.d = c(attributeValue);
                                    } catch (NumberFormatException e) {
                                        a.b("Unable to add linear skipoffset, cannot parse '" + attributeValue + "'", this.i, "Time format convert exception");
                                    }
                                }
                                a(xmlPullParser, bVar);
                                if (bVar.a().n() <= 0.0f) {
                                    a.b("Cannot parse videobanner duration ", this.i, "No duration");
                                }
                                this.a.add(bVar);
                            }
                        }
                    }
                    xmlPullParser.require(3, null, "Creative");
                }
            }
        }
        xmlPullParser.require(3, null, "Creatives");
    }

    private void e(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, a {
        String c;
        xmlPullParser.require(2, null, "Extensions");
        while (xmlPullParser.nextTag() == 2) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null || !name.equals("Extension")) {
                    f(xmlPullParser);
                } else {
                    String attributeValue = xmlPullParser.getAttributeValue(null, "type");
                    for (String c2 : d) {
                        if (c2.equals(attributeValue)) {
                            xmlPullParser.require(2, null, "Extension");
                            if ("linkTxt".equals(attributeValue)) {
                                c2 = c(xmlPullParser);
                                try {
                                    this.f = URLDecoder.decode(c2, "utf-8");
                                    Tracer.d("VAST linkTxt decoded text = " + this.f);
                                } catch (UnsupportedEncodingException e) {
                                    a.b("Unable to decode linkTxt extention: '" + c2 + "'", this.i, "Decoding exception");
                                }
                                Tracer.d("VAST linkTxt raw text: " + c2);
                            }
                            xmlPullParser.require(3, null, "Extension");
                        } else {
                            f(xmlPullParser);
                        }
                    }
                }
            }
        }
        xmlPullParser.require(3, null, "Extensions");
    }

    private void a(XmlPullParser xmlPullParser, b bVar) throws IOException, XmlPullParserException, a {
        xmlPullParser.require(2, null, "Linear");
        while (xmlPullParser.nextTag() == 2) {
            String name = xmlPullParser.getName();
            if (xmlPullParser.getEventType() == 2) {
                if (name != null && name.equals("Duration")) {
                    xmlPullParser.require(2, null, "Duration");
                    try {
                        bVar.a().a(c(c(xmlPullParser)));
                    } catch (NumberFormatException e) {
                    }
                    xmlPullParser.require(3, null, "Duration");
                } else if (name != null && name.equals("TrackingEvents")) {
                    xmlPullParser.require(2, null, "TrackingEvents");
                    while (xmlPullParser.nextTag() == 2) {
                        if (xmlPullParser.getEventType() == 2) {
                            name = xmlPullParser.getName();
                            if (name == null || !name.equals("Tracking")) {
                                f(xmlPullParser);
                            } else {
                                name = xmlPullParser.getAttributeValue(null, "event");
                                CharSequence attributeValue = xmlPullParser.getAttributeValue(null, VastIconXmlManager.OFFSET);
                                xmlPullParser.require(2, null, "Tracking");
                                if (!name.equals("progress") || TextUtils.isEmpty(attributeValue)) {
                                    bVar.a.add(new Pair(name, c(xmlPullParser)));
                                } else {
                                    bVar.b.add(new Pair(attributeValue, c(xmlPullParser)));
                                }
                                Tracer.d("Added VAST tracking \"" + name + "\"");
                                xmlPullParser.require(3, null, "Tracking");
                            }
                        }
                    }
                    xmlPullParser.require(3, null, "TrackingEvents");
                } else if (name != null && name.equals("MediaFiles")) {
                    List arrayList = new ArrayList();
                    a(xmlPullParser, (ArrayList) arrayList);
                    if (arrayList.isEmpty()) {
                        a.b("Mediafiles array is empty!", this.i, "Empty mediafiles");
                    } else {
                        bVar.a().a(arrayList);
                        this.b = true;
                    }
                } else if (name == null || !name.equals("VideoClicks")) {
                    f(xmlPullParser);
                } else {
                    b(xmlPullParser, bVar);
                }
            }
        }
        xmlPullParser.require(3, null, "Linear");
    }

    private static void a(XmlPullParser xmlPullParser, ArrayList<VideoData> arrayList) throws a, IOException, XmlPullParserException {
        xmlPullParser.require(2, null, "MediaFiles");
        while (xmlPullParser.nextTag() == 2) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null || !name.equals("MediaFile")) {
                    f(xmlPullParser);
                } else {
                    xmlPullParser.require(2, null, "MediaFile");
                    name = xmlPullParser.getAttributeValue(null, "type");
                    String attributeValue = xmlPullParser.getAttributeValue(null, "bitrate");
                    String attributeValue2 = xmlPullParser.getAttributeValue(null, "width");
                    String attributeValue3 = xmlPullParser.getAttributeValue(null, "height");
                    String replaceAll = c(xmlPullParser).replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
                    if (name != null && "video/mp4".equals(name)) {
                        int intValue;
                        VideoData videoData = new VideoData(replaceAll);
                        videoData.setWidth(attributeValue2 != null ? Integer.valueOf(attributeValue2).intValue() : 0);
                        if (attributeValue3 != null) {
                            intValue = Integer.valueOf(attributeValue3).intValue();
                        } else {
                            intValue = 0;
                        }
                        videoData.setHeight(intValue);
                        if (attributeValue != null) {
                            intValue = Integer.valueOf(attributeValue).intValue();
                        } else {
                            intValue = 0;
                        }
                        videoData.setBitrate(intValue);
                        arrayList.add(videoData);
                    }
                    xmlPullParser.require(3, null, "MediaFile");
                }
            }
        }
        xmlPullParser.require(3, null, "MediaFiles");
    }

    private static void b(XmlPullParser xmlPullParser, b bVar) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, "VideoClicks");
        while (xmlPullParser.nextTag() == 2) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name != null && name.equals("ClickThrough")) {
                    xmlPullParser.require(2, null, "ClickThrough");
                    bVar.a().c(c(xmlPullParser));
                    xmlPullParser.require(3, null, "ClickThrough");
                } else if (name == null || !name.equals("ClickTracking")) {
                    f(xmlPullParser);
                } else {
                    xmlPullParser.require(2, null, "ClickTracking");
                    bVar.a.add(new Pair("ClickTracking", c(xmlPullParser)));
                    xmlPullParser.require(3, null, "ClickTracking");
                }
            }
        }
        xmlPullParser.require(3, null, "VideoClicks");
    }

    private static void f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0) {
            switch (xmlPullParser.next()) {
                case 2:
                    i++;
                    break;
                case 3:
                    i--;
                    break;
                default:
                    break;
            }
        }
    }

    private g a(String str, String str2) {
        g gVar = new g("playheadReachedValue", str2);
        try {
            gVar.a(c(str));
        } catch (NumberFormatException e) {
            a.b("Unable to add progressStat, value: " + str, this.i, "Time format convert exception");
        }
        return gVar;
    }

    private static float c(String str) throws NumberFormatException {
        long j = 0;
        int length = str.length();
        if (str.contains(FileUtils.HIDDEN_PREFIX)) {
            length = str.indexOf(FileUtils.HIDDEN_PREFIX);
            j = Long.parseLong(str.substring(str.indexOf(FileUtils.HIDDEN_PREFIX) + 1));
        }
        return ((float) Long.valueOf((((Long.parseLong(str.substring(str.lastIndexOf(":") + 1, length)) * 1000) + j) + ((Long.parseLong(str.substring(str.indexOf(":") + 1, str.lastIndexOf(":"))) * 60) * 1000)) + (((Long.parseLong(str.substring(0, str.indexOf(":"))) * 60) * 60) * 1000)).longValue()) / 1000.0f;
    }
}
