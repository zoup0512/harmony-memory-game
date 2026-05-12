package com.google.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

class Gson$6 extends TypeAdapter<AtomicLong> {
    final /* synthetic */ TypeAdapter val$longAdapter;

    Gson$6(TypeAdapter typeAdapter) {
        this.val$longAdapter = typeAdapter;
    }

    public void write(JsonWriter out, AtomicLong value) throws IOException {
        this.val$longAdapter.write(out, Long.valueOf(value.get()));
    }

    public AtomicLong read(JsonReader in) throws IOException {
        return new AtomicLong(((Number) this.val$longAdapter.read(in)).longValue());
    }
}
