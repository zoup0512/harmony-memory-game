package com.my.target.core.communication.js;

import com.my.target.core.communication.js.events.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: JSEventProcessor */
public final class c {
    private HashMap<String, ArrayList<b>> a = new HashMap();

    public final boolean a(f fVar) {
        String a = fVar.a();
        if (!this.a.containsKey(a)) {
            return false;
        }
        ArrayList arrayList = new ArrayList((Collection) this.a.get(a));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (arrayList.contains(bVar)) {
                bVar.a(fVar);
            }
        }
        return true;
    }

    public final boolean a(String str, b bVar) {
        ArrayList arrayList;
        if (this.a.containsKey(str)) {
            arrayList = (ArrayList) this.a.get(str);
            if (arrayList.contains(bVar)) {
                return false;
            }
            arrayList.add(bVar);
        } else {
            arrayList = new ArrayList();
            arrayList.add(bVar);
            this.a.put(str, arrayList);
        }
        return true;
    }

    public final void a() {
        for (Entry value : this.a.entrySet()) {
            ((ArrayList) value.getValue()).clear();
        }
        this.a.clear();
    }
}
