package com.unity3d.ads.webview.bridge;

import com.unity3d.ads.BuildConfig;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.webview.WebViewApp;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONException;

public class WebViewBridge {
    private static HashMap<String, HashMap<String, HashMap<Integer, Method>>> _classTable;

    public static void setClassTable(Class[] clsArr) {
        if (clsArr != null) {
            _classTable = new HashMap();
            for (Class cls : clsArr) {
                if (cls != null && cls.getPackage().getName().startsWith(BuildConfig.APPLICATION_ID)) {
                    HashMap hashMap = new HashMap();
                    for (Method method : cls.getMethods()) {
                        if (method.getAnnotation(WebViewExposed.class) != null) {
                            HashMap hashMap2;
                            String name = method.getName();
                            if (hashMap.containsKey(name)) {
                                hashMap2 = (HashMap) hashMap.get(name);
                            } else {
                                hashMap2 = new HashMap();
                            }
                            hashMap2.put(Integer.valueOf(Arrays.deepHashCode(method.getParameterTypes())), method);
                            hashMap.put(name, hashMap2);
                        }
                    }
                    _classTable.put(cls.getName(), hashMap);
                }
            }
        }
    }

    private static Method findMethod(String str, String str2, Object[] objArr) {
        if (_classTable.containsKey(str)) {
            HashMap hashMap = (HashMap) _classTable.get(str);
            if (hashMap.containsKey(str2)) {
                return (Method) ((HashMap) hashMap.get(str2)).get(Integer.valueOf(Arrays.deepHashCode(getTypes(objArr))));
            }
            throw new NoSuchMethodException();
        }
        throw new NoSuchMethodException();
    }

    private static Class<?>[] getTypes(Object[] objArr) {
        Class<?>[] clsArr;
        if (objArr == null) {
            clsArr = new Class[1];
        } else {
            clsArr = new Class[(objArr.length + 1)];
        }
        if (objArr != null) {
            for (int i = 0; i < objArr.length; i++) {
                clsArr[i] = objArr[i].getClass();
            }
        }
        clsArr[clsArr.length - 1] = WebViewCallback.class;
        return clsArr;
    }

    private static Object[] getValues(Object[] objArr, WebViewCallback webViewCallback) {
        Object[] objArr2;
        int i = 1;
        if (objArr != null) {
            int length = objArr.length;
            if (webViewCallback == null) {
                i = 0;
            }
            objArr2 = new Object[(i + length)];
        } else if (webViewCallback == null) {
            return null;
        } else {
            objArr2 = new Object[1];
        }
        if (objArr != null) {
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        }
        if (webViewCallback == null) {
            return objArr2;
        }
        objArr2[objArr2.length - 1] = webViewCallback;
        return objArr2;
    }

    public static void handleInvocation(String str, String str2, Object[] objArr, WebViewCallback webViewCallback) {
        Exception e;
        JSONException e2;
        try {
            try {
                findMethod(str, str2, objArr).invoke(null, getValues(objArr, webViewCallback));
            } catch (JSONException e3) {
                e = e3;
                webViewCallback.error(WebViewBridgeError.INVOCATION_FAILED, str, str2, objArr, e.getMessage());
                throw e;
            } catch (InvocationTargetException e4) {
                e = e4;
                webViewCallback.error(WebViewBridgeError.INVOCATION_FAILED, str, str2, objArr, e.getMessage());
                throw e;
            } catch (IllegalAccessException e5) {
                e = e5;
                webViewCallback.error(WebViewBridgeError.INVOCATION_FAILED, str, str2, objArr, e.getMessage());
                throw e;
            } catch (IllegalArgumentException e6) {
                e = e6;
                webViewCallback.error(WebViewBridgeError.INVOCATION_FAILED, str, str2, objArr, e.getMessage());
                throw e;
            }
        } catch (JSONException e7) {
            e2 = e7;
            webViewCallback.error(WebViewBridgeError.METHOD_NOT_FOUND, str, str2, objArr);
            throw e2;
        } catch (NoSuchMethodException e8) {
            e2 = e8;
            webViewCallback.error(WebViewBridgeError.METHOD_NOT_FOUND, str, str2, objArr);
            throw e2;
        }
    }

    public static void handleCallback(String str, String str2, Object[] objArr) {
        IllegalAccessException e;
        try {
            WebViewApp.getCurrentApp().getCallback(str).invoke(str2, getValues(objArr, null));
        } catch (InvocationTargetException e2) {
            e = e2;
            DeviceLog.error("Error while invoking method");
            throw e;
        } catch (IllegalAccessException e3) {
            e = e3;
            DeviceLog.error("Error while invoking method");
            throw e;
        } catch (JSONException e4) {
            e = e4;
            DeviceLog.error("Error while invoking method");
            throw e;
        } catch (IllegalArgumentException e5) {
            e = e5;
            DeviceLog.error("Error while invoking method");
            throw e;
        }
    }
}
