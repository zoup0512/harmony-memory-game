package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzak;
import com.google.android.gms.plus.internal.zzg;

public final class PlusOneButton extends FrameLayout {
    public static final int ANNOTATION_BUBBLE = 1;
    public static final int ANNOTATION_INLINE = 2;
    public static final int ANNOTATION_NONE = 0;
    public static final int DEFAULT_ACTIVITY_REQUEST_CODE = -1;
    public static final int SIZE_MEDIUM = 1;
    public static final int SIZE_SMALL = 0;
    public static final int SIZE_STANDARD = 3;
    public static final int SIZE_TALL = 2;
    private View arC;
    private int arD;
    private int arE;
    private OnPlusOneClickListener arF;
    private int mSize;
    private String zzae;

    public interface OnPlusOneClickListener {
        void onPlusOneClick(Intent intent);
    }

    protected class DefaultOnPlusOneClickListener implements OnClickListener, OnPlusOneClickListener {
        private final OnPlusOneClickListener arG;
        final /* synthetic */ PlusOneButton arH;

        public DefaultOnPlusOneClickListener(PlusOneButton plusOneButton, OnPlusOneClickListener onPlusOneClickListener) {
            this.arH = plusOneButton;
            this.arG = onPlusOneClickListener;
        }

        public void onClick(View view) {
            Intent intent = (Intent) this.arH.arC.getTag();
            if (this.arG != null) {
                this.arG.onPlusOneClick(intent);
            } else {
                onPlusOneClick(intent);
            }
        }

        public void onPlusOneClick(Intent intent) {
            Context context = this.arH.getContext();
            if ((context instanceof Activity) && intent != null) {
                ((Activity) context).startActivityForResult(intent, this.arH.arE);
            }
        }
    }

    public PlusOneButton(Context context) {
        this(context, null);
    }

    public PlusOneButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSize = getSize(context, attributeSet);
        this.arD = getAnnotation(context, attributeSet);
        this.arE = -1;
        zzca(getContext());
        if (!isInEditMode()) {
        }
    }

    protected static int getAnnotation(Context context, AttributeSet attributeSet) {
        String zza = zzak.zza("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, attributeSet, true, false, "PlusOneButton");
        return "INLINE".equalsIgnoreCase(zza) ? 2 : !"NONE".equalsIgnoreCase(zza) ? 1 : 0;
    }

    protected static int getSize(Context context, AttributeSet attributeSet) {
        String zza = zzak.zza("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", context, attributeSet, true, false, "PlusOneButton");
        return "SMALL".equalsIgnoreCase(zza) ? 0 : "MEDIUM".equalsIgnoreCase(zza) ? 1 : "TALL".equalsIgnoreCase(zza) ? 2 : 3;
    }

    private void zzca(Context context) {
        if (this.arC != null) {
            removeView(this.arC);
        }
        this.arC = zzg.zza(context, this.mSize, this.arD, this.zzae, this.arE);
        setOnPlusOneClickListener(this.arF);
        addView(this.arC);
    }

    public void initialize(String str, int i) {
        zzab.zza(getContext() instanceof Activity, (Object) "To use this method, the PlusOneButton must be placed in an Activity. Use initialize(String, OnPlusOneClickListener).");
        this.zzae = str;
        this.arE = i;
        zzca(getContext());
    }

    public void initialize(String str, OnPlusOneClickListener onPlusOneClickListener) {
        this.zzae = str;
        this.arE = 0;
        zzca(getContext());
        setOnPlusOneClickListener(onPlusOneClickListener);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.arC.layout(0, 0, i3 - i, i4 - i2);
    }

    protected void onMeasure(int i, int i2) {
        View view = this.arC;
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void plusOneClick() {
        this.arC.performClick();
    }

    public void setAnnotation(int i) {
        this.arD = i;
        zzca(getContext());
    }

    public void setIntent(Intent intent) {
        this.arC.setTag(intent);
    }

    public void setOnPlusOneClickListener(OnPlusOneClickListener onPlusOneClickListener) {
        this.arF = onPlusOneClickListener;
        this.arC.setOnClickListener(new DefaultOnPlusOneClickListener(this, onPlusOneClickListener));
    }

    public void setSize(int i) {
        this.mSize = i;
        zzca(getContext());
    }
}
