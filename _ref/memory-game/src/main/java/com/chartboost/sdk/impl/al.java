package com.chartboost.sdk.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.PriorityBlockingQueue;

public class al {
    public HttpURLConnection a(w<?> wVar) throws IOException {
        return (HttpURLConnection) new URL(wVar.c).openConnection();
    }

    public ak a(an anVar, PriorityBlockingQueue<w<?>> priorityBlockingQueue) {
        return new ak(anVar, this, priorityBlockingQueue);
    }
}
