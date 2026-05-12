package com.yandex.metrica.impl;

import com.yandex.metrica.IReporter;
import com.yandex.metrica.impl.i.a;

public class aq extends i {
    private final IReporter a;

    aq(IReporter iReporter, a aVar) {
        super(aVar);
        this.a = iReporter;
    }

    void b(Throwable th) {
        this.a.reportUnhandledException(th);
    }
}
