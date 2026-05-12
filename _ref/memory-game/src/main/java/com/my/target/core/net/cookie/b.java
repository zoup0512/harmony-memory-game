package com.my.target.core.net.cookie;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.mopub.common.Constants;
import com.my.target.Tracer;
import com.yalantis.ucrop.util.FileUtils;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@TargetApi(9)
/* compiled from: MyTargetCookieStore */
public final class b implements CookieStore {
    private SharedPreferences a;
    private Map<URI, Set<HttpCookie>> b;

    public b(Context context) {
        this.a = context.getSharedPreferences("mytarget_httpcookie_prefs", 0);
        a();
    }

    private void a() {
        this.b = new HashMap();
        for (Entry entry : this.a.getAll().entrySet()) {
            try {
                URI uri = new URI(((String) entry.getKey()).split("\\|", 2)[0]);
                String str = (String) entry.getValue();
                c cVar = new c();
                HttpCookie a = c.a(str);
                Set set = (Set) this.b.get(uri);
                if (set == null) {
                    set = new HashSet();
                    this.b.put(uri, set);
                }
                set.add(a);
            } catch (URISyntaxException e) {
                Tracer.d(e.getMessage());
            }
        }
    }

    public final synchronized void add(URI uri, HttpCookie httpCookie) {
        URI a = a(uri, httpCookie);
        Set set = (Set) this.b.get(a);
        if (set == null) {
            set = new HashSet();
            this.b.put(a, set);
        }
        set.remove(httpCookie);
        set.add(httpCookie);
        Editor edit = this.a.edit();
        edit.putString(a.toString() + "|" + httpCookie.getName(), new c().a(httpCookie));
        edit.apply();
    }

    private static URI a(URI uri, HttpCookie httpCookie) {
        if (httpCookie.getDomain() == null) {
            return uri;
        }
        String substring;
        String domain = httpCookie.getDomain();
        if (domain.charAt(0) == '.') {
            substring = domain.substring(1);
        } else {
            substring = domain;
        }
        try {
            String str;
            if (uri.getScheme() == null) {
                str = Constants.HTTP;
            } else {
                str = uri.getScheme();
            }
            return new URI(str, substring, httpCookie.getPath() == null ? "/" : httpCookie.getPath(), null);
        } catch (URISyntaxException e) {
            Tracer.d(e.getMessage());
            return uri;
        }
    }

    public final synchronized List<HttpCookie> get(URI uri) {
        return a(uri);
    }

    public final synchronized List<HttpCookie> getCookies() {
        List<HttpCookie> arrayList;
        arrayList = new ArrayList();
        for (URI a : this.b.keySet()) {
            arrayList.addAll(a(a));
        }
        return arrayList;
    }

    private List<HttpCookie> a(URI uri) {
        Collection hashSet = new HashSet();
        for (URI uri2 : this.b.keySet()) {
            int i;
            String host = uri2.getHost();
            String host2 = uri.getHost();
            if (host2.equals(host) || host2.endsWith(new StringBuilder(FileUtils.HIDDEN_PREFIX).append(host).toString())) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                host = uri2.getPath();
                host2 = uri.getPath();
                if (host2.equals(host) || ((host2.startsWith(host) && host.charAt(host.length() - 1) == '/') || (host2.startsWith(host) && host2.substring(host.length()).charAt(0) == '/'))) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (i != 0) {
                    hashSet.addAll((Collection) this.b.get(uri2));
                }
            }
        }
        List arrayList = new ArrayList();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            HttpCookie httpCookie = (HttpCookie) it.next();
            if (httpCookie.hasExpired()) {
                arrayList.add(httpCookie);
                it.remove();
            }
        }
        if (!arrayList.isEmpty()) {
            a(uri, arrayList);
        }
        return new ArrayList(hashSet);
    }

    private void a(URI uri, List<HttpCookie> list) {
        Editor edit = this.a.edit();
        for (HttpCookie name : list) {
            edit.remove(uri.toString() + "|" + name.getName());
        }
        edit.apply();
    }

    public final synchronized List<URI> getURIs() {
        return new ArrayList(this.b.keySet());
    }

    public final synchronized boolean remove(URI uri, HttpCookie httpCookie) {
        boolean z;
        Set set = (Set) this.b.get(uri);
        z = set != null && set.remove(httpCookie);
        if (z) {
            Editor edit = this.a.edit();
            edit.remove(uri.toString() + "|" + httpCookie.getName());
            edit.apply();
        }
        return z;
    }

    public final synchronized boolean removeAll() {
        this.b.clear();
        this.a.edit().clear().apply();
        return true;
    }
}
