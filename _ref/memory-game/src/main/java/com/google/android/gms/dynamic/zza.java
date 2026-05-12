package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzh;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    private T Ks;
    private Bundle Kt;
    private LinkedList<zza> Ku;
    private final zzf<T> Kv = new zzf<T>(this) {
        final /* synthetic */ zza Kw;

        {
            this.Kw = r1;
        }

        public void zza(T t) {
            this.Kw.Ks = t;
            Iterator it = this.Kw.Ku.iterator();
            while (it.hasNext()) {
                ((zza) it.next()).zzb(this.Kw.Ks);
            }
            this.Kw.Ku.clear();
            this.Kw.Kt = null;
        }
    };

    private interface zza {
        int getState();

        void zzb(LifecycleDelegate lifecycleDelegate);
    }

    class AnonymousClass5 implements OnClickListener {
        final /* synthetic */ Context zzala;
        final /* synthetic */ int zzbjv;

        AnonymousClass5(Context context, int i) {
            this.zzala = context;
            this.zzbjv = i;
        }

        public void onClick(View view) {
            this.zzala.startActivity(GooglePlayServicesUtil.zzfd(this.zzbjv));
        }
    }

    private void zza(Bundle bundle, zza com_google_android_gms_dynamic_zza_zza) {
        if (this.Ks != null) {
            com_google_android_gms_dynamic_zza_zza.zzb(this.Ks);
            return;
        }
        if (this.Ku == null) {
            this.Ku = new LinkedList();
        }
        this.Ku.add(com_google_android_gms_dynamic_zza_zza);
        if (bundle != null) {
            if (this.Kt == null) {
                this.Kt = (Bundle) bundle.clone();
            } else {
                this.Kt.putAll(bundle);
            }
        }
        zza(this.Kv);
    }

    public static void zzb(FrameLayout frameLayout) {
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        CharSequence zzc = zzh.zzc(context, isGooglePlayServicesAvailable, GooglePlayServicesUtil.zzbv(context));
        CharSequence zzh = zzh.zzh(context, isGooglePlayServicesAvailable);
        View linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        View textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(zzc);
        linearLayout.addView(textView);
        if (zzh != null) {
            View button = new Button(context);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(zzh);
            linearLayout.addView(button);
            button.setOnClickListener(new AnonymousClass5(context, isGooglePlayServicesAvailable));
        }
    }

    private void zznd(int i) {
        while (!this.Ku.isEmpty() && ((zza) this.Ku.getLast()).getState() >= i) {
            this.Ku.removeLast();
        }
    }

    public void onCreate(final Bundle bundle) {
        zza(bundle, new zza(this) {
            final /* synthetic */ zza Kw;

            public int getState() {
                return 1;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.Kw.Ks.onCreate(bundle);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        final LayoutInflater layoutInflater2 = layoutInflater;
        final ViewGroup viewGroup2 = viewGroup;
        final Bundle bundle2 = bundle;
        zza(bundle, new zza(this) {
            final /* synthetic */ zza Kw;

            public int getState() {
                return 2;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(this.Kw.Ks.onCreateView(layoutInflater2, viewGroup2, bundle2));
            }
        });
        if (this.Ks == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.Ks != null) {
            this.Ks.onDestroy();
        } else {
            zznd(1);
        }
    }

    public void onDestroyView() {
        if (this.Ks != null) {
            this.Ks.onDestroyView();
        } else {
            zznd(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        zza(bundle2, new zza(this) {
            final /* synthetic */ zza Kw;

            public int getState() {
                return 0;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.Kw.Ks.onInflate(activity, bundle, bundle2);
            }
        });
    }

    public void onLowMemory() {
        if (this.Ks != null) {
            this.Ks.onLowMemory();
        }
    }

    public void onPause() {
        if (this.Ks != null) {
            this.Ks.onPause();
        } else {
            zznd(5);
        }
    }

    public void onResume() {
        zza(null, new zza(this) {
            final /* synthetic */ zza Kw;

            {
                this.Kw = r1;
            }

            public int getState() {
                return 5;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.Kw.Ks.onResume();
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.Ks != null) {
            this.Ks.onSaveInstanceState(bundle);
        } else if (this.Kt != null) {
            bundle.putAll(this.Kt);
        }
    }

    public void onStart() {
        zza(null, new zza(this) {
            final /* synthetic */ zza Kw;

            {
                this.Kw = r1;
            }

            public int getState() {
                return 4;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                this.Kw.Ks.onStart();
            }
        });
    }

    public void onStop() {
        if (this.Ks != null) {
            this.Ks.onStop();
        } else {
            zznd(4);
        }
    }

    protected void zza(FrameLayout frameLayout) {
        zzb(frameLayout);
    }

    protected abstract void zza(zzf<T> com_google_android_gms_dynamic_zzf_T);

    public T zzbbt() {
        return this.Ks;
    }
}
