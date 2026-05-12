package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.yandex.metrica.impl.a.a;
import com.yandex.metrica.impl.ob.cr;
import com.yandex.metrica.impl.ob.cs;
import com.yandex.metrica.impl.ob.ct;
import com.yandex.metrica.impl.ob.cy;
import com.yandex.metrica.impl.ob.cz;
import com.yandex.metrica.impl.ob.da;
import org.json.JSONArray;
import org.json.JSONObject;

public final class k {
    private Context a;
    private ContentValues b;
    private com.yandex.metrica.impl.ob.k c;

    public k(Context context) {
        this.a = context;
    }

    public k a(ContentValues contentValues) {
        this.b = contentValues;
        return this;
    }

    public k a(com.yandex.metrica.impl.ob.k kVar) {
        this.c = kVar;
        return this;
    }

    public void a(h hVar, a aVar) {
        int i;
        cr a;
        Location location;
        Location t;
        Object obj;
        JSONArray g;
        JSONArray a2;
        this.b.put("name", hVar.a());
        this.b.put(Param.VALUE, hVar.b());
        this.b.put("type", Integer.valueOf(hVar.c()));
        this.b.put("custom_type", Integer.valueOf(hVar.d()));
        this.b.put("error_environment", hVar.i());
        this.b.put("user_info", hVar.k());
        this.b.put("truncated", Integer.valueOf(hVar.o()));
        ContentValues contentValues = this.b;
        String str = "connection_type";
        Context context = this.a;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (ah.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    i = 1;
                } else if (activeNetworkInfo.getType() == 0) {
                    i = 0;
                }
                contentValues.put(str, Integer.valueOf(i));
                a = cy.a(this.a);
                a.a(new da(this) {
                    final /* synthetic */ k a;

                    {
                        this.a = r1;
                    }

                    public void a(cz czVar) {
                        this.a.b.put("cellular_connection_type", czVar.b().g());
                    }
                });
                a.a(new ct(this) {
                    final /* synthetic */ k a;

                    {
                        this.a = r1;
                    }

                    public void a(cs[] csVarArr) {
                        try {
                            JSONArray jSONArray = new JSONArray();
                            for (cs csVar : csVarArr) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.putOpt("cell_id", csVar.e());
                                jSONObject.putOpt("signal_strength", csVar.a());
                                jSONObject.putOpt("lac", csVar.d());
                                jSONObject.putOpt("country_code", csVar.b());
                                jSONObject.putOpt("operator_id", csVar.c());
                                jSONObject.putOpt("operator_name", csVar.f());
                                jSONObject.putOpt("is_connected", Boolean.valueOf(csVar.h()));
                                jSONObject.putOpt("cell_type", Integer.valueOf(csVar.i()));
                                jSONObject.putOpt("pci", csVar.j());
                                jSONArray.put(jSONObject);
                            }
                            this.a.b.put("cell_info", jSONArray.toString());
                        } catch (Exception e) {
                        }
                    }
                });
                this.b.put("app_environment", aVar.a);
                this.b.put("app_environment_revision", Long.valueOf(aVar.b));
                if (this.c.j().m()) {
                    location = null;
                } else {
                    t = this.c.j().t();
                    if (t == null) {
                        t = y.a(this.a).c();
                        if (t == null) {
                            location = y.a(this.a).d();
                        }
                    }
                    location = t;
                }
                if (location != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("lat", location.getLatitude());
                        jSONObject.put("lon", location.getLongitude());
                        jSONObject.putOpt("timestamp", Long.valueOf(location.getTime()));
                        jSONObject.putOpt("precision", location.hasAccuracy() ? Float.valueOf(location.getAccuracy()) : null);
                        str = "direction";
                        if (location.hasBearing()) {
                            obj = null;
                        } else {
                            obj = Float.valueOf(location.getBearing());
                        }
                        jSONObject.putOpt(str, obj);
                        str = TransferTable.COLUMN_SPEED;
                        if (location.hasSpeed()) {
                            obj = null;
                        } else {
                            obj = Float.valueOf(location.getSpeed());
                        }
                        jSONObject.putOpt(str, obj);
                        str = "altitude";
                        if (location.hasAltitude()) {
                            obj = null;
                        } else {
                            obj = Double.valueOf(location.getAltitude());
                        }
                        jSONObject.putOpt(str, obj);
                        jSONObject.putOpt("provider", be.c(location.getProvider(), null));
                        this.b.put("location_info", jSONObject.toString());
                    } catch (Exception e) {
                    }
                }
                g = hVar.g();
                a2 = bi.a(this.a).a();
                if (a2.length() <= g.length()) {
                    this.b.put("wifi_network_info", a2.toString());
                } else {
                    this.b.put("wifi_network_info", g.toString());
                }
            }
        }
        i = 2;
        contentValues.put(str, Integer.valueOf(i));
        a = cy.a(this.a);
        a.a(/* anonymous class already generated */);
        a.a(/* anonymous class already generated */);
        this.b.put("app_environment", aVar.a);
        this.b.put("app_environment_revision", Long.valueOf(aVar.b));
        if (this.c.j().m()) {
            location = null;
        } else {
            t = this.c.j().t();
            if (t == null) {
                t = y.a(this.a).c();
                if (t == null) {
                    location = y.a(this.a).d();
                }
            }
            location = t;
        }
        if (location != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("lat", location.getLatitude());
            jSONObject2.put("lon", location.getLongitude());
            jSONObject2.putOpt("timestamp", Long.valueOf(location.getTime()));
            if (location.hasAccuracy()) {
            }
            jSONObject2.putOpt("precision", location.hasAccuracy() ? Float.valueOf(location.getAccuracy()) : null);
            str = "direction";
            if (location.hasBearing()) {
                obj = null;
            } else {
                obj = Float.valueOf(location.getBearing());
            }
            jSONObject2.putOpt(str, obj);
            str = TransferTable.COLUMN_SPEED;
            if (location.hasSpeed()) {
                obj = null;
            } else {
                obj = Float.valueOf(location.getSpeed());
            }
            jSONObject2.putOpt(str, obj);
            str = "altitude";
            if (location.hasAltitude()) {
                obj = null;
            } else {
                obj = Double.valueOf(location.getAltitude());
            }
            jSONObject2.putOpt(str, obj);
            jSONObject2.putOpt("provider", be.c(location.getProvider(), null));
            this.b.put("location_info", jSONObject2.toString());
        }
        g = hVar.g();
        a2 = bi.a(this.a).a();
        if (a2.length() <= g.length()) {
            this.b.put("wifi_network_info", g.toString());
        } else {
            this.b.put("wifi_network_info", a2.toString());
        }
    }

    public void a() {
        av h = this.c.h();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("dId", h.c());
            jSONObject.putOpt("uId", h.b());
            jSONObject.putOpt("appVer", h.x());
            jSONObject.putOpt("appBuild", h.z());
            jSONObject.putOpt("kitVer", h.h());
            jSONObject.putOpt("clientKitVer", h.i());
            jSONObject.putOpt("kitBuildNumber", h.k());
            jSONObject.putOpt("kitBuildType", h.l());
            jSONObject.putOpt("osVer", h.q());
            jSONObject.putOpt("osApiLev", Integer.valueOf(h.r()));
            jSONObject.putOpt("lang", h.w());
            jSONObject.putOpt("root", h.E());
        } catch (Exception e) {
            jSONObject = new JSONObject();
        }
        this.b.put("report_request_parameters", jSONObject.toString());
    }
}
