package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageItemInfo;
import com.facebook.share.internal.ShareConstants;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.ba;
import com.yandex.metrica.impl.ba.a;
import com.yandex.metrica.impl.be;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class bt {
    private br a;

    class AnonymousClass1 extends HashMap<String, Object> {
        final /* synthetic */ StringBuilder a;

        AnonymousClass1(StringBuilder stringBuilder) {
            this.a = stringBuilder;
            put(ShareConstants.WEB_DIALOG_PARAM_DATA, this.a.toString());
        }
    }

    class AnonymousClass2 extends HashMap<String, Object> {
        final /* synthetic */ StringBuilder a;

        AnonymousClass2(StringBuilder stringBuilder) {
            this.a = stringBuilder;
            put("error", this.a.toString());
        }
    }

    class AnonymousClass3 extends HashMap<String, Object> {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        AnonymousClass3(String str, int i) {
            this.a = str;
            this.b = i;
            put(this.a, new HashMap<String, Object>(this) {
                final /* synthetic */ AnonymousClass3 a;

                {
                    this.a = r3;
                    put("candidates_count", Integer.valueOf(this.a.b));
                }
            });
        }
    }

    public bt(br brVar) {
        this.a = brVar;
    }

    public String a(Context context) {
        return b(context);
    }

    String b(Context context) {
        String str;
        bs bsVar = new bs(context);
        List<a> c = c(context);
        List<bn> arrayList = new ArrayList(c.size());
        LinkedList linkedList = new LinkedList();
        for (a aVar : c) {
            a aVar2;
            PackageItemInfo packageItemInfo = aVar2.d;
            int i = -1;
            if (packageItemInfo.metaData != null) {
                i = packageItemInfo.metaData.getInt("metrica:api:level");
            }
            if (i < 29) {
                linkedList.add(aVar2);
            } else {
                Object obj;
                bq a;
                if (this.a.e()) {
                    str = aVar2.d.applicationInfo.packageName;
                    a = this.a.a(context, str);
                    bq b = this.a.b(context, str);
                    if (a == null && b == null) {
                        obj = null;
                    } else {
                        bp bpVar = new bp(aVar2, b, a);
                    }
                } else {
                    a = this.a.a(context, aVar2.d.applicationInfo.packageName);
                    if (a == null) {
                        obj = null;
                    } else if (be.a(a.c())) {
                        obj = null;
                    } else {
                        bn bnVar = new bn(aVar2, a);
                    }
                }
                if (obj != null) {
                    arrayList.add(obj);
                }
            }
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            aVar2 = (a) it.next();
            String f = this.a.f(context, aVar2.d.packageName);
            if (!be.a(f)) {
                arrayList.add(new bn(aVar2, new bq(f, null, -1)));
            }
        }
        str = "";
        if (arrayList.isEmpty()) {
            return str;
        }
        Map hashMap = new HashMap();
        for (bn bnVar2 : arrayList) {
            String a2 = bnVar2.a();
            bo boVar = (bo) hashMap.get(a2);
            if (boVar == null) {
                boVar = new bo(a2, bsVar);
                hashMap.put(a2, boVar);
            }
            boVar.a(bnVar2);
        }
        if (hashMap.size() == 1) {
            Iterator it2 = hashMap.values().iterator();
            if (it2.hasNext()) {
                return ((bo) it2.next()).c();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Smth wrong when iterate through initial candidates list").append('\n');
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", new AnonymousClass2(stringBuilder));
            return str;
        }
        c(context, arrayList);
        return a(context, hashMap);
    }

    private String a(Context context, Map<String, bo> map) {
        a(context, "method_carriers_count", map.size());
        List arrayList = new ArrayList();
        int i = 0;
        for (bo boVar : map.values()) {
            int b = boVar.b();
            if (b > i) {
                arrayList.clear();
                arrayList.add(boVar);
                i = b;
            } else if (b == i) {
                arrayList.add(boVar);
            }
        }
        if (arrayList.size() == 1) {
            return ((bo) arrayList.get(0)).c();
        }
        String a;
        if (((bo) arrayList.get(0)).b() == 1) {
            a = a(context, (ArrayList) arrayList);
        } else {
            a = null;
        }
        if (a != null) {
            return a;
        }
        List a2 = a(arrayList);
        if (a2 == null) {
            return a(context, arrayList);
        }
        return a(context, a2);
    }

    private static List<bo> a(List<bo> list) {
        List<bo> arrayList = new ArrayList();
        for (bo boVar : list) {
            if (!boVar.a()) {
                arrayList.add(boVar);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    private static String a(Context context, ArrayList<bo> arrayList) {
        String packageName = context.getPackageName();
        Iterator it = arrayList.iterator();
        bo boVar = null;
        bo boVar2 = null;
        while (it.hasNext()) {
            bo boVar3 = (bo) it.next();
            if (packageName.equals(((bn) boVar3.d().get(0)).c().e)) {
                boVar = boVar3;
            } else {
                boVar2 = boVar3;
            }
        }
        if (boVar == null) {
            return null;
        }
        if (!boVar2.a()) {
            return boVar2.c();
        }
        if (boVar.a()) {
            return boVar2.c();
        }
        return boVar.c();
    }

    String a(Context context, List<bo> list) {
        a(context, "method_first_installed", list.size());
        List arrayList = new ArrayList();
        Long valueOf = Long.valueOf(Long.MAX_VALUE);
        Long l = valueOf;
        for (bo boVar : list) {
            Long e = boVar.e();
            int compareTo = e.compareTo(l);
            if (compareTo < 0) {
                arrayList.clear();
                arrayList.add(boVar);
                l = e;
            } else if (compareTo == 0) {
                arrayList.add(boVar);
            }
        }
        if (arrayList.size() == 1) {
            return ((bo) arrayList.get(0)).c();
        }
        return b(context, arrayList);
    }

    private static String b(Context context, List<bo> list) {
        a(context, "method_device_id_comparing", list.size());
        String str = "";
        for (bo boVar : list) {
            String c;
            if (boVar.c().compareTo(str) > 0) {
                c = boVar.c();
            } else {
                c = str;
            }
            str = c;
        }
        return str;
    }

    private static void c(Context context, List<bn> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (bn bnVar : list) {
            stringBuilder.append(bnVar.c().d.packageName);
            stringBuilder.append(" ");
            stringBuilder.append(bnVar.toString());
            stringBuilder.append('\n');
        }
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", new AnonymousClass1(stringBuilder));
    }

    List<a> c(Context context) {
        return ba.b(context);
    }

    private static void a(Context context, String str, int i) {
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", new AnonymousClass3(str, i));
    }
}
