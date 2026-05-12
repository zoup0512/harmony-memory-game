package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

class zzdc {

    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Editor ayc;

        AnonymousClass1(Editor editor) {
            this.ayc = editor;
        }

        public void run() {
            this.ayc.commit();
        }
    }

    static void zza(Editor editor) {
        if (VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            new Thread(new AnonymousClass1(editor)).start();
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    static void zzb(Context context, String str, String str2, String str3) {
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        zza(edit);
    }
}
