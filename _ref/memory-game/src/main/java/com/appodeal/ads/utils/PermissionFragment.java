package com.appodeal.ads.utils;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.internal.NativeProtocol;
import java.util.HashMap;
import java.util.Map.Entry;

@TargetApi(23)
public class PermissionFragment extends Fragment {
    private HashMap<Integer, String> a;
    private int b = 0;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.a = (HashMap) arguments.getSerializable(NativeProtocol.RESULT_ARGS_PERMISSIONS);
            a();
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void a() {
        this.b += this.a.entrySet().size();
        for (Entry entry : this.a.entrySet()) {
            Integer num = (Integer) entry.getKey();
            if (getActivity().checkSelfPermission((String) entry.getValue()) != 0) {
                requestPermissions(new String[]{(String) entry.getValue()}, num.intValue());
            } else {
                onRequestPermissionsResult(num.intValue(), new String[]{r0}, new int[]{0});
            }
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.b--;
        if (iArr.length == 1) {
            PermissionsHelper.a().a(i, iArr[0]);
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (this.b == 0) {
            getFragmentManager().beginTransaction().remove(this).commit();
        }
    }
}
