package com.amazonaws.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.jar.JarFile;

public enum Classes {
    ;

    public static Class<?> childClassOf(Class<?> parentClass, Object instance) {
        if (instance == null || instance == Object.class) {
            return null;
        }
        if (parentClass != null && parentClass.isInterface()) {
            return null;
        }
        Class<?> childClass = instance.getClass();
        while (true) {
            Class<?> parent = childClass.getSuperclass();
            if (parent == parentClass) {
                return childClass;
            }
            if (parent == null) {
                return null;
            }
            childClass = parent;
        }
    }

    public static JarFile jarFileOf(Class<?> klass) {
        JarFile jarFile = null;
        URL url = klass.getResource("/" + klass.getName().replace('.', '/') + ".class");
        if (url != null) {
            String s = url.getFile();
            int beginIndex = s.indexOf("file:") + "file:".length();
            int endIndex = s.indexOf(".jar!");
            if (endIndex != -1) {
                File file = new File(s.substring(beginIndex, endIndex + ".jar".length()));
                try {
                    if (file.exists()) {
                        jarFile = new JarFile(file);
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return jarFile;
    }
}
