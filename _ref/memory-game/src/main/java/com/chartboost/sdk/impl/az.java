package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.k;

public abstract class az extends RelativeLayout {
    private static Rect b = new Rect();
    private a a;
    protected boolean c = false;
    protected Button d = null;
    private boolean e = true;

    public class a extends ay {
        final /* synthetic */ az b;

        public a(az azVar, Context context) {
            this.b = azVar;
            super(context);
        }

        protected void a(boolean z) {
            if (this.b.e && z) {
                if (!this.b.c) {
                    if (getDrawable() != null) {
                        getDrawable().setColorFilter(1996488704, Mode.SRC_ATOP);
                    } else if (getBackground() != null) {
                        getBackground().setColorFilter(1996488704, Mode.SRC_ATOP);
                    }
                    invalidate();
                    this.b.c = true;
                }
            } else if (this.b.c) {
                if (getDrawable() != null) {
                    getDrawable().clearColorFilter();
                } else if (getBackground() != null) {
                    getBackground().clearColorFilter();
                }
                invalidate();
                this.b.c = false;
            }
        }

        public void a(k kVar, LayoutParams layoutParams) {
            a(kVar);
            layoutParams.width = kVar.h();
            layoutParams.height = kVar.i();
        }
    }

    protected abstract void a(MotionEvent motionEvent);

    public az(Context context) {
        super(context);
        b();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void b() {
        this.a = new a(this, getContext());
        this.a.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ az a;

            {
                this.a = r1;
            }

            public boolean onTouch(View v, MotionEvent event) {
                boolean a = az.b(v, event);
                switch (event.getActionMasked()) {
                    case 0:
                        this.a.a.a(a);
                        return a;
                    case 1:
                        if (this.a.getVisibility() == 0 && this.a.isEnabled() && a) {
                            this.a.a(event);
                        }
                        this.a.a.a(false);
                        break;
                    case 2:
                        this.a.a.a(a);
                        break;
                    case 3:
                    case 4:
                        this.a.a.a(false);
                        break;
                }
                return true;
            }
        });
        addView(this.a, new RelativeLayout.LayoutParams(-1, -1));
    }

    private static boolean b(View view, MotionEvent motionEvent) {
        view.getLocalVisibleRect(b);
        Rect rect = b;
        rect.left += view.getPaddingLeft();
        rect = b;
        rect.top += view.getPaddingTop();
        rect = b;
        rect.right -= view.getPaddingRight();
        rect = b;
        rect.bottom -= view.getPaddingBottom();
        return b.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
    }

    public void a(String str) {
        if (str != null) {
            a().setText(str);
            addView(a(), new RelativeLayout.LayoutParams(-1, -1));
            this.a.setVisibility(8);
            a(false);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ az a;

                {
                    this.a = r1;
                }

                public void onClick(View v) {
                    this.a.a(null);
                }
            });
        } else if (this.d != null) {
            removeView(a());
            this.d = null;
            this.a.setVisibility(0);
            a(true);
        }
    }

    public TextView a() {
        if (this.d == null) {
            this.d = new Button(getContext());
            this.d.setGravity(17);
        }
        this.d.postInvalidate();
        return this.d;
    }

    public void a(k kVar) {
        this.a.a(kVar);
        a(null);
    }

    public void a(k kVar, RelativeLayout.LayoutParams layoutParams) {
        this.a.a(kVar, layoutParams);
        a(null);
    }

    public void a(ScaleType scaleType) {
        this.a.setScaleType(scaleType);
    }

    public void a(boolean z) {
        this.e = z;
    }
}
