package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.Parcelable;
import com.yandex.metrica.CounterConfiguration;

public class bb extends ar {
    private final String e;

    public bb(String str) {
        this.e = str;
    }

    Bundle c() {
        Bundle c = super.c();
        Parcelable counterConfiguration = new CounterConfiguration(b());
        counterConfiguration.b(this.e);
        c.putParcelable("COUNTER_MIGRATION_CFG_OBJ", counterConfiguration);
        return c;
    }
}
