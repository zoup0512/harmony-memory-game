package com.mopub.mobileads.factories;

import com.mopub.mobileads.CustomEventBanner;
import java.lang.reflect.Constructor;

public class CustomEventBannerFactory {
    private static CustomEventBannerFactory instance = new CustomEventBannerFactory();

    public static CustomEventBanner create(String str) {
        return instance.internalCreate(str);
    }

    @Deprecated
    public static void setInstance(CustomEventBannerFactory customEventBannerFactory) {
        instance = customEventBannerFactory;
    }

    protected CustomEventBanner internalCreate(String str) {
        Constructor declaredConstructor = Class.forName(str).asSubclass(CustomEventBanner.class).getDeclaredConstructor((Class[]) null);
        declaredConstructor.setAccessible(true);
        return (CustomEventBanner) declaredConstructor.newInstance(new Object[0]);
    }
}
