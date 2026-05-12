package com.my.target.core.models;

/* compiled from: AbstractMediaData */
public abstract class a<T> implements f<T> {
    protected int height;
    private T t;
    protected String url;
    protected int width;

    public String getUrl() {
        return this.url;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setData(T t) {
        this.t = t;
    }

    public T getData() {
        return this.t;
    }
}
