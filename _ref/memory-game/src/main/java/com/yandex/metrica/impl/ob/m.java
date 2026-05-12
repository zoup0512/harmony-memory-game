package com.yandex.metrica.impl.ob;

import java.util.Collections;
import java.util.List;

public class m<BaseHandler> extends n<BaseHandler> {
    private final List<BaseHandler> a;

    public m(List<BaseHandler> list) {
        this.a = Collections.unmodifiableList(list);
    }

    public List<? extends BaseHandler> a() {
        return this.a;
    }
}
