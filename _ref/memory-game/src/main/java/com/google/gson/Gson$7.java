package com.google.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongArray;

class Gson$7 extends TypeAdapter<AtomicLongArray> {
    final /* synthetic */ TypeAdapter val$longAdapter;

    Gson$7(TypeAdapter typeAdapter) {
        this.val$longAdapter = typeAdapter;
    }

    public void write(JsonWriter out, AtomicLongArray value) throws IOException {
        out.beginArray();
        int length = value.length();
        for (int i = 0; i < length; i++) {
            this.val$longAdapter.write(out, Long.valueOf(value.get(i)));
        }
        out.endArray();
    }

    public AtomicLongArray read(JsonReader in) throws IOException {
        List<Long> list = new ArrayList();
        in.beginArray();
        while (in.hasNext()) {
            list.add(Long.valueOf(((Number) this.val$longAdapter.read(in)).longValue()));
        }
        in.endArray();
        int length = list.size();
        AtomicLongArray array = new AtomicLongArray(length);
        for (int i = 0; i < length; i++) {
            array.set(i, ((Long) list.get(i)).longValue());
        }
        return array;
    }
}
