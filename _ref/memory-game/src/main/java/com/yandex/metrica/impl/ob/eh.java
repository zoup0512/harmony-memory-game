package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.ek.a;
import org.json.JSONObject;

public class eh extends ei<JSONObject> {
    protected /* synthetic */ Object b(em emVar) throws ek {
        return a(emVar);
    }

    public eh(int i, String str, JSONObject jSONObject) {
        super(i, str, jSONObject == null ? null : jSONObject.toString());
    }

    protected JSONObject a(em emVar) throws ek {
        a aVar;
        try {
            return new JSONObject(new String(emVar.a, ej.a(emVar.b, "utf-8")));
        } catch (Throwable e) {
            aVar = a.PARSE;
            throw new ek(e);
        } catch (Throwable e2) {
            aVar = a.PARSE;
            throw new ek(e2);
        }
    }
}
