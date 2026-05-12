package com.google.android.gms.ads.mediation;

import android.os.Bundle;

public interface MediationAdapter {

    public static class zza {
        private int zzcqo;

        public zza zzbb(int i) {
            this.zzcqo = i;
            return this;
        }

        public Bundle zzvp() {
            Bundle bundle = new Bundle();
            bundle.putInt("capabilities", this.zzcqo);
            return bundle;
        }
    }

    void onDestroy();

    void onPause();

    void onResume();
}
