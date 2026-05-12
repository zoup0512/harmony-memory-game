package com.amazon.device.ads;

class ReflectionUtils {
    ReflectionUtils() {
    }

    public boolean isClassAvailable(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
