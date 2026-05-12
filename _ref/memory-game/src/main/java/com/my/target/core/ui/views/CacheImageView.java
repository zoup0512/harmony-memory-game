package com.my.target.core.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import com.mopub.volley.DefaultRetryPolicy;
import com.my.target.Tracer;
import com.my.target.core.utils.e;
import com.my.target.nativeads.models.ImageData;

public class CacheImageView extends ImageView {
    private GradientDrawable a;
    private Bitmap b;
    private int c;
    private int d;
    private ImageData e;
    private Paint f = new Paint();
    private Rect g;
    private final Rect h;
    private a i;

    private class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ CacheImageView a;
        private Bitmap b;
        private final Context c;

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        private a(CacheImageView cacheImageView, Context context) {
            this.a = cacheImageView;
            this.c = context;
        }

        private Void a() {
            try {
                if (this.a.e != null) {
                    this.b = (Bitmap) this.a.e.getData();
                    if (this.b == null) {
                        e a = e.a(this.c);
                        if (a != null) {
                            this.b = a.a(this.a.e.getUrl());
                            if (this.b != null) {
                                this.a.e.setData(this.b);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                Tracer.d(th.getMessage());
            }
            return null;
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            if (this.a.e != null) {
                this.a.setImageBitmap(this.b, true);
            }
        }
    }

    public CacheImageView(Context context) {
        super(context);
        this.f.setFilterBitmap(true);
        this.h = new Rect();
    }

    public void setImageData(ImageData imageData) {
        this.e = imageData;
        if (imageData == null) {
            setImageBitmap(null);
            return;
        }
        this.b = (Bitmap) imageData.getData();
        if (this.b != null) {
            setImageBitmap(this.b);
            return;
        }
        this.i = new a(getContext());
        this.i.execute(new Void[0]);
    }

    public final void setImageDrawable(Drawable drawable) {
        Tracer.i("Unable to set custom image drawable to generated view");
    }

    public void setImageResource(int i) {
        Tracer.i("Unable to set custom image resource to generated view");
    }

    public void setImageURI(Uri uri) {
        Tracer.i("Unable to set custom image uri to generated view");
    }

    public void setImageBitmap(Bitmap bitmap, boolean z) {
        if (!z || VERSION.SDK_INT < 12) {
            setImageBitmap(bitmap);
            return;
        }
        setAlpha(0.0f);
        setImageBitmap(bitmap);
        animate().alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(300);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.b = bitmap;
        if (bitmap != null) {
            setBackgroundColor(0);
            this.g = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            requestLayout();
            invalidate();
        }
    }

    public void setBorder(int i, int i2) {
        if (this.a == null) {
            this.a = new GradientDrawable();
        }
        this.a.setStroke(i, i2);
        invalidate();
    }

    public void setPlaceholderWidth(int i) {
        this.c = i;
    }

    public void setPlaceholderHeight(int i) {
        this.d = i;
    }

    protected void onDraw(Canvas canvas) {
        if (this.b != null) {
            if (this.a != null) {
                this.a.draw(canvas);
            }
            canvas.drawBitmap(this.b, this.g, this.h, this.f);
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int width;
        int height;
        float f;
        float f2 = 0.0f;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 0) {
            i3 = Integer.MIN_VALUE;
        } else {
            i3 = mode;
        }
        if (mode2 == 0) {
            mode2 = Integer.MIN_VALUE;
        }
        if (this.b != null) {
            width = this.b.getWidth();
            height = this.b.getHeight();
        } else if (this.d == 0 || this.c == 0) {
            super.onMeasure(i, i2);
            return;
        } else {
            width = this.c;
            height = this.d;
        }
        if (height != 0) {
            f = ((float) width) / ((float) height);
        } else {
            f = 0.0f;
        }
        if (size2 != 0) {
            f2 = ((float) size) / ((float) size2);
        }
        if (i3 == 1073741824 && r0 == 1073741824) {
            setMeasuredDimension(size, size2);
        } else if (i3 == Integer.MIN_VALUE && r0 == Integer.MIN_VALUE) {
            if (f < f2) {
                mode2 = Math.min(height, size2);
                setMeasuredDimension(Math.round(((float) mode2) * f), mode2);
                return;
            }
            mode2 = Math.min(width, size2);
            setMeasuredDimension(mode2, Math.round(((float) mode2) / f));
        } else if (i3 == Integer.MIN_VALUE && r0 == 1073741824) {
            setMeasuredDimension(Math.round(((float) size2) * f), size2);
        } else if (i3 == 1073741824 && r0 == Integer.MIN_VALUE) {
            setMeasuredDimension(size, Math.round(((float) size) / f));
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.a != null) {
            this.a.setBounds(getPaddingLeft(), getPaddingTop(), getWidth(), getHeight());
        }
        this.h.left = getPaddingLeft();
        this.h.top = getPaddingTop();
        this.h.right = getWidth() - getPaddingRight();
        this.h.bottom = getHeight() - getPaddingBottom();
    }
}
