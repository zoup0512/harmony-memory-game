package com.mopub.mobileads.factories;

import com.mopub.mobileads.CustomEventInterstitial;
import java.lang.reflect.Constructor;

public class CustomEventInterstitialFactory {
    private static CustomEventInterstitialFactory instance = new CustomEventInterstitialFactory();

    public static CustomEventInterstitial create(String str) {
        return instance.internalCreate(str);
    }

    @Deprecated
    public static void setInstance(CustomEventInterstitialFactory customEventInterstitialFactory) {
        instance = customEventInterstitialFactory;
    }

    protected CustomEventInterstitial internalCreate(String str) {
        Constructor declaredConstructor = Class.forName(str).asSubclass(CustomEventInterstitial.class).getDeclaredConstructor((Class[]) null);
        declaredConstructor.setAccessible(true);
        return (CustomEventInterstitial) declaredConstructor.newInstance(new Object[0]);
    }
}
