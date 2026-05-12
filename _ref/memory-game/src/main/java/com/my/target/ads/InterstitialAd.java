package com.my.target.ads;

import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.view.WindowManager.LayoutParams;
import com.my.target.Tracer;
import com.my.target.core.facades.a;
import com.my.target.core.facades.c;
import com.my.target.core.models.banners.d;
import com.my.target.core.models.banners.e;
import com.my.target.core.models.sections.f;
import java.util.Iterator;

public class InterstitialAd extends a {
    private c ad;
    private com.my.target.core.ui.a adDialog;
    private final c.a adListener;
    private final OnDismissListener dialogDismissListener;
    private boolean hideStatusBarInDialog;
    private InterstitialAdListener listener;

    public InterstitialAd(int i, Context context) {
        this(i, context, null);
    }

    public InterstitialAd(int i, Context context, CustomParams customParams) {
        this.hideStatusBarInDialog = false;
        this.adListener = new 1(this);
        this.dialogDismissListener = new 2(this);
        com.my.target.core.a aVar = new com.my.target.core.a(i, "fullscreen");
        if (customParams != null) {
            aVar.a(customParams);
        }
        init(aVar, context);
        Tracer.i("InterstitialAd created. Version: 4.5.10");
    }

    protected void onLoad(com.my.target.core.models.c cVar) {
        c eVar;
        Context context = this.context;
        Iterator it = cVar.f().iterator();
        while (it.hasNext()) {
            f fVar = (f) it.next();
            if (fVar.a().equals("fullscreen") && (fVar instanceof com.my.target.core.models.sections.c)) {
                it = ((com.my.target.core.models.sections.c) fVar).g().iterator();
                while (it.hasNext()) {
                    com.my.target.core.models.banners.a aVar = (com.my.target.core.models.banners.a) it.next();
                    if (!"banner".equals(aVar.a())) {
                        if ("promo".equals(aVar.a())) {
                            Object fVar2 = new com.my.target.core.facades.f((e) aVar, cVar, context);
                            break;
                        }
                    }
                    eVar = new com.my.target.core.facades.e((d) aVar, cVar, context);
                    break;
                }
                eVar = null;
                this.ad = eVar;
                if (this.ad == null) {
                    this.ad.a(this.adListener);
                    this.ad.load();
                } else if (this.listener != null) {
                    this.listener.onNoAd("No ad", this);
                }
            }
        }
        eVar = null;
        this.ad = eVar;
        if (this.ad == null) {
            this.ad.a(this.adListener);
            this.ad.load();
        } else if (this.listener != null) {
            this.listener.onNoAd("No ad", this);
        }
    }

    protected void onLoadError(String str) {
        if (this.listener != null) {
            this.listener.onNoAd("No ad: " + str, this);
        }
    }

    public void show() {
        if (this.ad == null || !this.ad.a()) {
            Tracer.i("InterstitialAd.show: No ad");
            return;
        }
        MyTargetActivity.ad = this.ad;
        Intent intent = new Intent(this.context, MyTargetActivity.class);
        intent.setAction("com.my.target.actions.interstitial");
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }

    public void showDialog() {
        if (this.adDialog != null && this.adDialog.isShowing()) {
            Tracer.i("InterstitialAd.showDialog: dialog already showing");
        } else if (this.ad == null || !this.ad.a()) {
            Tracer.i("InterstitialAd.showDialog: No ad");
        } else {
            this.adDialog = new com.my.target.core.ui.a(this.ad, this.hideStatusBarInDialog, this.context);
            this.adDialog.setOnDismissListener(this.dialogDismissListener);
            this.adDialog.show();
            if (this.ad instanceof com.my.target.core.facades.f) {
                LayoutParams attributes = this.adDialog.getWindow().getAttributes();
                attributes.dimAmount = 0.0f;
                this.adDialog.getWindow().setAttributes(attributes);
                this.adDialog.getWindow().addFlags(2);
            }
        }
    }

    public void dismiss() {
        if (this.adDialog != null && this.adDialog.isShowing()) {
            this.adDialog.dismiss();
        } else if (this.context != null) {
            this.context.sendBroadcast(new Intent(MyTargetActivity.ACTION_CLOSE));
        }
    }

    @Deprecated
    public void dismissDialog() {
        if (this.adDialog != null && this.adDialog.isShowing()) {
            this.adDialog.dismiss();
        }
    }

    public void setListener(InterstitialAdListener interstitialAdListener) {
        this.listener = interstitialAdListener;
    }

    public boolean isHideStatusBarInDialog() {
        return this.hideStatusBarInDialog;
    }

    public void setHideStatusBarInDialog(boolean z) {
        this.hideStatusBarInDialog = z;
    }

    public void destroy() {
        dismiss();
        if (this.ad != null) {
            this.ad.a(null);
            this.ad = null;
        }
    }
}
