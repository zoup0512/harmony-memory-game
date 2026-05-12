package com.cmcm.picks.vastvideo;

import android.text.TextUtils;
import com.cmcm.utils.ThreadHelper;
import com.facebook.internal.ServerProtocol;
import com.mopub.mobileads.VastLinearXmlManager;
import com.mopub.mobileads.VastResourceXmlManager;
import com.yalantis.ucrop.util.FileUtils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: VastXmlParse */
public class g {
    private String a;

    /* compiled from: VastXmlParse */
    public interface a {
        void a(VastModel vastModel);

        void a(VastModel vastModel, String str);
    }

    private void a(VastModel vastModel, boolean z, a aVar, String str) {
        if (aVar != null) {
            final boolean z2 = z;
            final a aVar2 = aVar;
            final VastModel vastModel2 = vastModel;
            final String str2 = str;
            ThreadHelper.postOnUiThread(new Runnable(this) {
                final /* synthetic */ g e;

                public void run() {
                    if (z2) {
                        aVar2.a(vastModel2);
                    } else {
                        aVar2.a(vastModel2, str2);
                    }
                }
            });
        }
    }

    public void a(final String str, final a aVar) {
        ThreadHelper.post(new Runnable(this) {
            final /* synthetic */ g c;

            public void run() {
                com.cmcm.utils.g.a(VastAd.TAG, "xml to parse");
                try {
                    VastModel a = this.c.a(new VastModel(), str);
                    if (a != null) {
                        this.c.a(a, true, aVar, null);
                    } else {
                        this.c.a(a, false, aVar, "parse model null");
                    }
                } catch (Exception e) {
                    com.cmcm.utils.g.a(VastAd.TAG, "xml parse failed :" + e.toString());
                    if (com.cmcm.utils.g.a) {
                        e.printStackTrace();
                    }
                    this.c.a(null, false, aVar, "parse model exception");
                }
            }
        });
    }

    private VastModel a(VastModel vastModel, String str) {
        if (vastModel.z() > 5) {
            return null;
        }
        VastModel b = b(vastModel, str);
        if (!(b == null || !b.x() || b.y() == null || b.y().isEmpty())) {
            a(b, (String) b.y().remove(0));
        }
        return b;
    }

    private VastModel b(VastModel vastModel, String str) {
        HttpURLConnection httpURLConnection;
        Exception exception;
        Throwable th;
        HttpURLConnection httpURLConnection2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        InputStream inputStream = null;
        this.a = "";
        InputStream bufferedInputStream;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection3.setReadTimeout(180000);
                httpURLConnection3.setConnectTimeout(180000);
                if (httpURLConnection3.getResponseCode() != 200) {
                    if (null != null) {
                        try {
                            inputStream.close();
                            httpURLConnection3.disconnect();
                        } catch (IOException e) {
                            if (com.cmcm.utils.g.a) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return null;
                }
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                bufferedInputStream = new BufferedInputStream(httpURLConnection3.getInputStream());
                try {
                    newPullParser.setInput(bufferedInputStream, "utf-8");
                    VastModel vastModel2 = vastModel;
                    for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                        switch (eventType) {
                            case 0:
                                if (vastModel2 == null) {
                                    vastModel2 = new VastModel();
                                }
                                vastModel2.b(false);
                                break;
                            case 2:
                                if (a(vastModel2, newPullParser)) {
                                    break;
                                }
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                        httpURLConnection3.disconnect();
                                    } catch (IOException e2) {
                                        if (com.cmcm.utils.g.a) {
                                            e2.printStackTrace();
                                        }
                                    }
                                }
                                return null;
                            case 3:
                                if ("VAST".equals(newPullParser.getName()) && vastModel2 != null) {
                                    if (bufferedInputStream != null) {
                                        try {
                                            bufferedInputStream.close();
                                            httpURLConnection3.disconnect();
                                        } catch (IOException e22) {
                                            if (com.cmcm.utils.g.a) {
                                                e22.printStackTrace();
                                            }
                                        }
                                    }
                                    return vastModel2;
                                }
                            default:
                                break;
                        }
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                            httpURLConnection3.disconnect();
                        } catch (IOException e222) {
                            if (com.cmcm.utils.g.a) {
                                e222.printStackTrace();
                            }
                        }
                    }
                    return vastModel2;
                } catch (Exception e3) {
                    Exception exception2 = e3;
                    httpURLConnection = httpURLConnection3;
                    exception = exception2;
                } catch (Throwable th2) {
                    httpURLConnection = httpURLConnection3;
                    th = th2;
                }
            } catch (Exception e32) {
                bufferedInputStream = null;
                httpURLConnection2 = httpURLConnection3;
                exception = e32;
                httpURLConnection = httpURLConnection2;
                try {
                    com.cmcm.utils.g.a(VastAd.TAG, "xml parse failed :" + exception.toString());
                    if (com.cmcm.utils.g.a) {
                        exception.printStackTrace();
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                            httpURLConnection.disconnect();
                        } catch (IOException e2222) {
                            if (com.cmcm.utils.g.a) {
                                e2222.printStackTrace();
                            }
                        }
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                            httpURLConnection.disconnect();
                        } catch (IOException e4) {
                            if (com.cmcm.utils.g.a) {
                                e4.printStackTrace();
                            }
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                bufferedInputStream = null;
                httpURLConnection2 = httpURLConnection3;
                th = th4;
                httpURLConnection = httpURLConnection2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e5) {
            exception = e5;
            httpURLConnection = null;
            bufferedInputStream = null;
            com.cmcm.utils.g.a(VastAd.TAG, "xml parse failed :" + exception.toString());
            if (com.cmcm.utils.g.a) {
                exception.printStackTrace();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
                httpURLConnection.disconnect();
            }
            return null;
        } catch (Throwable th5) {
            th = th5;
            httpURLConnection = null;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private boolean a(VastModel vastModel, XmlPullParser xmlPullParser) {
        try {
            String name = xmlPullParser.getName();
            if ("AdTitle".equals(name)) {
                vastModel.b(xmlPullParser.nextText());
            } else if ("Error".equals(name)) {
                r2 = vastModel.c();
                if (r2 == null) {
                    r2 = new ArrayList();
                }
                r2.add(xmlPullParser.nextText());
                vastModel.a(r2);
            } else if ("Impression".equals(name)) {
                r2 = vastModel.d();
                if (r2 == null) {
                    r2 = new ArrayList();
                }
                r2.add(xmlPullParser.nextText());
                vastModel.b(r2);
            } else if ("Tracking".equals(name)) {
                if (!b(vastModel, xmlPullParser)) {
                    return false;
                }
            } else if ("ClickThrough".equals(name)) {
                vastModel.c(xmlPullParser.nextText());
            } else if ("MediaFile".equals(name)) {
                r2 = vastModel.t();
                if (r2 == null) {
                    r2 = new ArrayList();
                }
                a a = a(xmlPullParser);
                if (a != null) {
                    r2.add(a);
                }
                vastModel.q(r2);
            } else if ("Ad".equals(name)) {
                vastModel.a(xmlPullParser.getAttributeValue(null, "id"));
            } else if ("ClickTracking".equals(name)) {
                r2 = vastModel.s();
                if (r2 == null) {
                    r2 = new ArrayList();
                }
                r2.add(xmlPullParser.nextText());
                vastModel.p(r2);
            } else if ("Companion".equals(name)) {
                this.a = xmlPullParser.getAttributeValue(null, "id");
            } else if (VastLinearXmlManager.ICON.equals(name)) {
                vastModel.f(xmlPullParser.nextText());
            } else if (VastResourceXmlManager.STATIC_RESOURCE.equals(name)) {
                name = xmlPullParser.nextText();
                if (!(TextUtils.isEmpty(name) || TextUtils.isEmpty(this.a))) {
                    if ("pre-roll".equals(this.a)) {
                        vastModel.d(name);
                    } else if ("post-roll".equals(this.a)) {
                        vastModel.e(name);
                    }
                }
            } else if ("Wrapper".equals(name)) {
                vastModel.b(true);
                vastModel.a(vastModel.z() + 1);
            } else if ("VASTAdTagURI".equals(name)) {
                r2 = vastModel.y();
                if (r2 == null) {
                    r2 = new ArrayList();
                }
                CharSequence nextText = xmlPullParser.nextText();
                if (TextUtils.isEmpty(nextText)) {
                    r2.add(nextText);
                    vastModel.r(r2);
                } else {
                    r2.add(nextText);
                    vastModel.r(r2);
                }
            }
            return true;
        } catch (Exception e) {
            com.cmcm.utils.g.a(VastAd.TAG, "xml parse failed :" + e.toString());
            if (!com.cmcm.utils.g.a) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    private a a(XmlPullParser xmlPullParser) {
        try {
            String attributeValue = xmlPullParser.getAttributeValue(null, "delivery");
            xmlPullParser.getAttributeValue(null, "type");
            String attributeValue2 = xmlPullParser.getAttributeValue(null, "bitrate");
            String attributeValue3 = xmlPullParser.getAttributeValue(null, "width");
            String attributeValue4 = xmlPullParser.getAttributeValue(null, "height");
            String attributeValue5 = xmlPullParser.getAttributeValue(null, "id");
            String attributeValue6 = xmlPullParser.getAttributeValue(null, "scalable");
            String attributeValue7 = xmlPullParser.getAttributeValue(null, "maintainAspectRatio");
            String nextText = xmlPullParser.nextText();
            if (TextUtils.isEmpty(nextText)) {
                return null;
            }
            String substring = nextText.substring(nextText.lastIndexOf(FileUtils.HIDDEN_PREFIX) + 1);
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            if (!"mp4".equals(substring.trim()) && !"3gp".equals(substring.trim())) {
                return null;
            }
            a aVar = new a();
            if (!TextUtils.isEmpty(attributeValue2) && attributeValue2.matches("[0-9]+")) {
                aVar.c(Integer.parseInt(attributeValue2));
            }
            if (!TextUtils.isEmpty(attributeValue3) && attributeValue3.matches("[0-9]+")) {
                aVar.a(Integer.parseInt(attributeValue3));
            }
            if (!TextUtils.isEmpty(attributeValue4) && attributeValue4.matches("[0-9]+")) {
                aVar.b(Integer.parseInt(attributeValue4));
            }
            if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(attributeValue6)) {
                aVar.a(true);
            } else {
                aVar.a(false);
            }
            if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(attributeValue7)) {
                aVar.b(true);
            } else {
                aVar.b(false);
            }
            aVar.a(attributeValue);
            aVar.b(attributeValue5);
            aVar.d(substring);
            aVar.c(nextText);
            return aVar;
        } catch (Exception e) {
            com.cmcm.utils.g.a(VastAd.TAG, "xml parse failed :" + e.toString());
            if (!com.cmcm.utils.g.a) {
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }

    private boolean b(VastModel vastModel, XmlPullParser xmlPullParser) {
        try {
            String attributeValue = xmlPullParser.getAttributeValue(null, "event");
            List a;
            if ("creativeView".equals(attributeValue)) {
                a = a(vastModel.e());
                a.add(xmlPullParser.nextText());
                vastModel.c(a);
            } else if ("start".equals(attributeValue)) {
                a = a(vastModel.f());
                a.add(xmlPullParser.nextText());
                vastModel.d(a);
            } else if ("firstQuartile".equals(attributeValue)) {
                a = a(vastModel.g());
                a.add(xmlPullParser.nextText());
                vastModel.e(a);
            } else if ("midpoint".equals(attributeValue)) {
                a = a(vastModel.h());
                a.add(xmlPullParser.nextText());
                vastModel.f(a);
            } else if ("thirdQuartile".equals(attributeValue)) {
                a = a(vastModel.i());
                a.add(xmlPullParser.nextText());
                vastModel.g(a);
            } else if ("complete".equals(attributeValue)) {
                a = a(vastModel.j());
                a.add(xmlPullParser.nextText());
                vastModel.h(a);
            } else if ("close".equals(attributeValue)) {
                a = a(vastModel.k());
                a.add(xmlPullParser.nextText());
                vastModel.i(a);
            } else if ("pause".equals(attributeValue)) {
                a = a(vastModel.l());
                a.add(xmlPullParser.nextText());
                vastModel.j(a);
            } else if ("resume".equals(attributeValue)) {
                a = a(vastModel.o());
                a.add(xmlPullParser.nextText());
                vastModel.m(a);
            } else if ("mute".equals(attributeValue)) {
                a = a(vastModel.m());
                a.add(xmlPullParser.nextText());
                vastModel.k(a);
            } else if ("unmute".equals(attributeValue)) {
                a = a(vastModel.n());
                a.add(xmlPullParser.nextText());
                vastModel.l(a);
            } else if ("fullscreen".equals(attributeValue)) {
                a = a(vastModel.p());
                a.add(xmlPullParser.nextText());
                vastModel.n(a);
            } else if ("exitFullscreen".equals(attributeValue)) {
                a = a(vastModel.q());
                a.add(xmlPullParser.nextText());
                vastModel.o(a);
            }
            return true;
        } catch (Exception e) {
            com.cmcm.utils.g.a(VastAd.TAG, "xml parse failed :" + e.toString());
            if (com.cmcm.utils.g.a) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private List<String> a(List<String> list) {
        if (list == null) {
            return new ArrayList();
        }
        return list;
    }
}
