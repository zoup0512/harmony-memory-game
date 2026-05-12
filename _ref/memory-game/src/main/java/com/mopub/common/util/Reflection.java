package com.mopub.common.util;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflection {

    public static class MethodBuilder {
        private Class<?> mClass;
        private final Object mInstance;
        private boolean mIsAccessible;
        private boolean mIsStatic;
        private final String mMethodName;
        private List<Class<?>> mParameterClasses = new ArrayList();
        private List<Object> mParameters = new ArrayList();

        public MethodBuilder(Object obj, String str) {
            this.mInstance = obj;
            this.mMethodName = str;
            this.mClass = obj != null ? obj.getClass() : null;
        }

        public <T> MethodBuilder addParam(Class<T> cls, T t) {
            this.mParameterClasses.add(cls);
            this.mParameters.add(t);
            return this;
        }

        public MethodBuilder setAccessible() {
            this.mIsAccessible = true;
            return this;
        }

        public MethodBuilder setStatic(Class<?> cls) {
            this.mIsStatic = true;
            this.mClass = cls;
            return this;
        }

        public Object execute() {
            Method declaredMethodWithTraversal = Reflection.getDeclaredMethodWithTraversal(this.mClass, this.mMethodName, (Class[]) this.mParameterClasses.toArray(new Class[this.mParameterClasses.size()]));
            if (this.mIsAccessible) {
                declaredMethodWithTraversal.setAccessible(true);
            }
            Object[] toArray = this.mParameters.toArray();
            if (this.mIsStatic) {
                return declaredMethodWithTraversal.invoke(null, toArray);
            }
            return declaredMethodWithTraversal.invoke(this.mInstance, toArray);
        }
    }

    public static Method getDeclaredMethodWithTraversal(Class<?> cls, String str, Class<?>... clsArr) {
        while (cls != null) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchMethodException();
    }

    public static boolean classFound(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static <T> T instantiateClassWithEmptyConstructor(@NonNull String str, @NonNull Class<? extends T> cls) {
        Preconditions.checkNotNull(str);
        Constructor declaredConstructor = Class.forName(str).asSubclass(cls).getDeclaredConstructor((Class[]) null);
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance(new Object[0]);
    }
}
