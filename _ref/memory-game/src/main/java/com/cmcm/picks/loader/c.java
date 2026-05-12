package com.cmcm.picks.loader;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/* compiled from: BaseRequestBuilder */
public class c {
    protected String a = g.e();
    protected int b = -1;
    protected List<NameValuePair> c = new ArrayList();

    public c() {
        a(20);
    }

    public c a(int i) {
        this.c.add(new BasicNameValuePair("v", String.valueOf(i)));
        return this;
    }
}
