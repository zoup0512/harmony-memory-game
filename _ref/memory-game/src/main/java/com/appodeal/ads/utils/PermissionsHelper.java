package com.appodeal.ads.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import java.io.Serializable;
import java.util.HashMap;

public class PermissionsHelper {
    public static boolean a = true;
    public static boolean b = true;
    private static PermissionsHelper c;
    private AppodealPermissionCallbacks d;

    public interface AppodealPermissionCallbacks {
        void accessCoarseLocationResponse(int i);

        void writeExternalStorageResponse(int i);
    }

    public static PermissionsHelper a() {
        if (c == null) {
            c = new PermissionsHelper();
        }
        return c;
    }

    public void a(Activity activity, AppodealPermissionCallbacks appodealPermissionCallbacks) {
        if (VERSION.SDK_INT >= 23) {
            this.d = appodealPermissionCallbacks;
            Bundle bundle = new Bundle();
            Serializable hashMap = new HashMap(2);
            if (a) {
                hashMap.put(Integer.valueOf(0), "android.permission.WRITE_EXTERNAL_STORAGE");
            }
            if (b) {
                hashMap.put(Integer.valueOf(1), "android.permission.ACCESS_COARSE_LOCATION");
            }
            bundle.putSerializable(NativeProtocol.RESULT_ARGS_PERMISSIONS, hashMap);
            FragmentManager fragmentManager = activity.getFragmentManager();
            Fragment permissionFragment = new PermissionFragment();
            permissionFragment.setArguments(bundle);
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.add(permissionFragment, "PermissionFragment");
            beginTransaction.commit();
        }
    }

    public void a(int i, int i2) {
        switch (i) {
            case 0:
                if (this.d != null) {
                    this.d.writeExternalStorageResponse(i2);
                    return;
                }
                return;
            case 1:
                if (this.d != null) {
                    this.d.accessCoarseLocationResponse(i2);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
