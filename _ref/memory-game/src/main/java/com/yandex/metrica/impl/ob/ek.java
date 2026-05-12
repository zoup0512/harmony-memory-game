package com.yandex.metrica.impl.ob;

public class ek extends Exception {

    public enum a {
        DEFAULT,
        AUTH,
        NETWORK,
        NO_CONNECTION,
        PARSE,
        SERVER,
        TIMEOUT
    }

    public ek(byte b) {
    }

    public ek(Throwable th) {
        super(th);
    }
}
