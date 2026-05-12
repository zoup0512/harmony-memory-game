package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.io.Writer;

public class bm extends Writer implements Serializable {
    private final StringBuilder a;

    public bm() {
        this.a = new StringBuilder();
    }

    public bm(int i) {
        this.a = new StringBuilder(i);
    }

    public Writer append(char value) {
        this.a.append(value);
        return this;
    }

    public Writer append(CharSequence value) {
        this.a.append(value);
        return this;
    }

    public Writer append(CharSequence value, int start, int end) {
        this.a.append(value, start, end);
        return this;
    }

    public void close() {
    }

    public void flush() {
    }

    public void write(String value) {
        if (value != null) {
            this.a.append(value);
        }
    }

    public void write(char[] value, int offset, int length) {
        if (value != null) {
            this.a.append(value, offset, length);
        }
    }

    public String toString() {
        return this.a.toString();
    }
}
