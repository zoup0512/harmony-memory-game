package com.mopub.common.factories;

import com.mopub.common.util.Reflection.MethodBuilder;

public class MethodBuilderFactory {
    protected static MethodBuilderFactory instance = new MethodBuilderFactory();

    @Deprecated
    public static void setInstance(MethodBuilderFactory methodBuilderFactory) {
        instance = methodBuilderFactory;
    }

    public static MethodBuilder create(Object obj, String str) {
        return instance.internalCreate(obj, str);
    }

    protected MethodBuilder internalCreate(Object obj, String str) {
        return new MethodBuilder(obj, str);
    }
}
