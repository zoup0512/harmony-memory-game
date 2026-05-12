package com.google.android.gms.analytics.internal;

public class zzz extends zzq<zzaa> {

    private static class zza implements com.google.android.gms.analytics.internal.zzq.zza<zzaa> {
        private final zzaa G = new zzaa();
        private final zzf zzcwp;

        public zza(zzf com_google_android_gms_analytics_internal_zzf) {
            this.zzcwp = com_google_android_gms_analytics_internal_zzf;
        }

        public /* synthetic */ zzp zzabb() {
            return zzaco();
        }

        public zzaa zzaco() {
            return this.G;
        }

        public void zzc(String str, int i) {
            if ("ga_dispatchPeriod".equals(str)) {
                this.G.I = i;
            } else {
                this.zzcwp.zzyx().zzd("Int xml configuration name not recognized", str);
            }
        }

        public void zzg(String str, boolean z) {
            if ("ga_dryRun".equals(str)) {
                this.G.J = z ? 1 : 0;
                return;
            }
            this.zzcwp.zzyx().zzd("Bool xml configuration name not recognized", str);
        }

        public void zzp(String str, String str2) {
        }

        public void zzq(String str, String str2) {
            if ("ga_appName".equals(str)) {
                this.G.zzcum = str2;
            } else if ("ga_appVersion".equals(str)) {
                this.G.zzcun = str2;
            } else if ("ga_logLevel".equals(str)) {
                this.G.H = str2;
            } else {
                this.zzcwp.zzyx().zzd("String xml configuration name not recognized", str);
            }
        }
    }

    public zzz(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf, new zza(com_google_android_gms_analytics_internal_zzf));
    }
}
