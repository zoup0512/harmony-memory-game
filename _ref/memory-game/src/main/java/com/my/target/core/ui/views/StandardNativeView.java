package com.my.target.core.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.amazonaws.services.s3.internal.Constants;
import com.cube.memorygames.games.Game1MemoryGridActivity;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.my.target.core.engines.h;
import com.my.target.core.models.banners.g;
import com.my.target.core.utils.b;
import com.my.target.core.utils.l;
import com.my.target.nativeads.banners.NavigationType;
import com.my.target.nativeads.views.StarsRatingView;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import org.nexage.sourcekit.vast.model.VASTModel;

@SuppressLint({"ViewConstructor"})
public class StandardNativeView extends RelativeLayout {
    private final Runnable A;
    private OnClickListener B;
    private final BorderedTextView a;
    private final TextView b;
    private final TextView c;
    private final TextView d;
    private final TextView e;
    private final TextView f;
    private final TextView g;
    private final Button h;
    private final StarsRatingView i;
    private final CacheImageView j;
    private final LinearLayout k;
    private final LinearLayout l;
    private final LinearLayout m;
    private final RelativeLayout n;
    private final RelativeLayout o;
    private final RelativeLayout p;
    private final ViewFlipper q;
    private final l r;
    private final int s;
    private final FrameLayout t;
    private a u;
    private h v;
    private String w;
    private g x;
    private final Runnable y;
    private final Runnable z;

    public interface a {
        void g();
    }

    public StandardNativeView(Context context, int i) {
        this(context, i, (byte) 0);
    }

    private StandardNativeView(Context context, int i, byte b) {
        super(context, null);
        this.y = new Runnable(this) {
            final /* synthetic */ StandardNativeView a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.q != null && this.a.a != null && this.a.b != null) {
                    int width = this.a.q.getWidth();
                    if (width > 0) {
                        this.a.b.setMaxWidth((width - (this.a.a.getText().length() * this.a.r.a(10))) - this.a.r.a(10));
                    }
                }
            }
        };
        this.z = new Runnable(this) {
            final /* synthetic */ StandardNativeView a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.u != null && this.a.q.getDisplayedChild() == this.a.q.getChildCount() - 1) {
                    this.a.u.g();
                }
                this.a.q.showNext();
                this.a.postDelayed(this.a.z, 4000);
            }
        };
        this.A = new Runnable(this) {
            final /* synthetic */ StandardNativeView a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.c != null && this.a.c.getLayout() != null) {
                    int lineEnd = this.a.c.getLayout().getLineEnd(0);
                    if (((float) this.a.c.getText().length()) / ((float) lineEnd) > 2.0f) {
                        ArrayList a = StandardNativeView.b(this.a.c.getText().toString(), lineEnd);
                        this.a.c.setText((CharSequence) a.get(0));
                        for (int i = 1; i < a.size() - 1; i++) {
                            this.a.a((String) a.get(i), this.a.c);
                        }
                        if (a.size() > 1) {
                            String str = (String) a.get(a.size() - 1);
                            if (Math.ceil((double) (((float) str.length()) / ((float) lineEnd))) + Math.ceil((double) (((float) this.a.e.getText().length()) / ((float) lineEnd))) <= 2.0d) {
                                this.a.g.setText(str);
                                this.a.g.setVisibility(0);
                            } else {
                                this.a.a(str, this.a.c);
                                this.a.g.setVisibility(8);
                            }
                            if (!TextUtils.isEmpty(this.a.x.m()) && this.a.l.getParent() == null) {
                                StandardNativeView.a(this.a, lineEnd);
                            } else if (!TextUtils.isEmpty(this.a.g.getText())) {
                                this.a.q.addView(this.a.l);
                            }
                        }
                    } else if (TextUtils.isEmpty(this.a.g.getText()) && !TextUtils.isEmpty(this.a.x.m()) && this.a.l.getParent() == null) {
                        StandardNativeView.a(this.a, lineEnd);
                    }
                }
            }
        };
        this.B = new OnClickListener(this) {
            final /* synthetic */ StandardNativeView a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                this.a.v.a(this.a.w);
            }
        };
        this.s = i;
        this.r = new l(context);
        this.j = new CacheImageView(context);
        this.t = new FrameLayout(context);
        this.q = new ViewFlipper(context);
        this.k = new LinearLayout(context);
        this.h = new Button(context);
        this.b = new TextView(context);
        this.a = new BorderedTextView(context);
        this.i = new StarsRatingView(context);
        this.d = new TextView(context);
        this.f = new TextView(context);
        this.c = new TextView(context);
        this.m = new LinearLayout(context);
        this.l = new LinearLayout(context);
        this.e = new TextView(context);
        this.g = new TextView(context);
        this.n = new RelativeLayout(context);
        this.o = new RelativeLayout(context);
        this.p = new RelativeLayout(context);
        this.j.setId(VASTModel.ERROR_CODE_NO_FILE);
        this.t.setOnClickListener(this.B);
        this.h.setId(403);
        this.h.setPadding(this.r.a(8), this.r.a(8), this.r.a(8), this.r.a(8));
        this.h.setMinimumWidth(this.s);
        this.h.setTextSize(2, CloseButton.TEXT_SIZE_SP);
        this.h.setMaxWidth(this.s * 2);
        this.h.setOnClickListener(this.B);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(11, -1);
        this.h.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(0, 403);
        layoutParams.addRule(1, VASTModel.ERROR_CODE_NO_FILE);
        this.q.setLayoutParams(layoutParams);
        this.q.setInAnimation(b.a());
        this.q.setOutAnimation(b.b());
        this.q.setOnClickListener(this.B);
        this.k.setOrientation(1);
        this.n.setLayoutParams(new LinearLayout.LayoutParams(-1, this.s / 2));
        this.n.setGravity(16);
        this.b.setId(402);
        this.b.setMaxLines(1);
        this.b.setHorizontallyScrolling(true);
        this.b.setSingleLine(true);
        this.b.setMaxEms(25);
        this.b.setTextSize(2, 16.0f);
        this.b.setEllipsize(TruncateAt.END);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = this.r.a(4);
        layoutParams.leftMargin = this.r.a(4);
        layoutParams.topMargin = this.r.a(2);
        layoutParams.addRule(15);
        this.b.setLayoutParams(layoutParams);
        this.a.setId(Game1MemoryGridActivity.START_ANIMATION_DURATION);
        this.a.setBorder(1, -7829368);
        this.a.setGravity(17);
        this.a.setTextSize(2, CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER);
        this.a.setPadding(this.r.a(2), this.r.a(4), 0, 0);
        this.a.setLines(1);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        layoutParams.topMargin = this.r.a(2);
        layoutParams.addRule(1, 402);
        this.a.setLayoutParams(layoutParams);
        this.o.setLayoutParams(new LinearLayout.LayoutParams(-1, this.s / 2));
        this.i.setId(Constants.NO_SUCH_BUCKET_STATUS_CODE);
        layoutParams = new RelativeLayout.LayoutParams(this.r.a(73), this.r.a(12));
        layoutParams.leftMargin = this.r.a(4);
        layoutParams.topMargin = this.r.a(4);
        layoutParams.bottomMargin = this.r.a(2);
        layoutParams.addRule(15);
        this.i.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(1, Constants.NO_SUCH_BUCKET_STATUS_CODE);
        layoutParams.addRule(15);
        this.d.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = this.r.a(4);
        this.f.setLayoutParams(layoutParams);
        this.p.setLayoutParams(new LinearLayout.LayoutParams(-1, this.s));
        this.c.setMaxEms(90);
        this.c.setLineSpacing(0.0f, 1.2f);
        this.c.setMinHeight(this.s);
        this.c.setPadding(this.r.a(4), this.r.a(4), this.r.a(4), this.r.a(4));
        this.c.setGravity(16);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        this.c.setLayoutParams(layoutParams);
        this.m.setOrientation(1);
        this.m.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        layoutParams = new LinearLayout.LayoutParams(-1, this.s);
        this.l.setOrientation(1);
        this.l.setLayoutParams(layoutParams);
        this.l.setGravity(16);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        this.g.setPadding(this.r.a(4), this.r.a(4), this.r.a(4), this.r.a(4));
        this.g.setMinimumHeight(this.s / 2);
        this.g.setLineSpacing(0.0f, 1.2f);
        this.g.setVisibility(8);
        this.e.setLayoutParams(layoutParams);
        this.e.setPadding(this.r.a(4), 0, this.r.a(4), 0);
        this.e.setMinHeight(this.s / 2);
        this.e.setLineSpacing(0.0f, 1.2f);
        this.e.setEllipsize(TruncateAt.END);
        this.h.setTransformationMethod(null);
        addView(this.j);
        addView(this.t);
        addView(this.h);
        this.o.addView(this.i);
        this.o.addView(this.d);
        this.o.addView(this.f);
        this.n.addView(this.b);
        this.n.addView(this.a);
        this.p.addView(this.c);
        this.l.addView(this.g);
        this.l.addView(this.e);
        this.k.addView(this.n);
        this.k.addView(this.o);
        this.q.addView(this.k);
        this.q.addView(this.p);
        addView(this.q);
    }

    private static ArrayList<String> b(String str, int i) {
        ArrayList<String> arrayList = new ArrayList();
        if (str.length() < i) {
            arrayList.add(str);
        } else {
            while (str.length() > i * 2) {
                int lastIndexOf = str.substring(0, (i * 2) - 1).lastIndexOf(" ");
                arrayList.add(str.substring(0, lastIndexOf));
                str = str.substring(lastIndexOf + 1);
            }
            arrayList.add(str);
        }
        return arrayList;
    }

    public void setViewSettings(com.my.target.core.models.h hVar, boolean z) {
        this.b.setTextColor(hVar.j());
        if (hVar.b()) {
            this.b.setTypeface(null, 1);
        } else {
            this.b.setTypeface(null, 0);
        }
        this.a.setTextColor(hVar.l());
        this.a.setBorder(2, hVar.m());
        this.a.setBackgroundColor(hVar.k());
        this.c.setTextColor(hVar.n());
        this.c.setLines(2);
        if (hVar.c()) {
            this.c.setTypeface(null, 1);
        } else {
            this.c.setTypeface(null, 0);
        }
        this.g.setTextColor(hVar.n());
        if (hVar.c()) {
            this.g.setTypeface(null, 1);
        } else {
            this.g.setTypeface(null, 0);
        }
        this.f.setTextColor(hVar.o());
        if (hVar.d()) {
            this.f.setTypeface(null, 1);
        } else {
            this.f.setTypeface(null, 0);
        }
        this.d.setTextColor(hVar.p());
        if (hVar.e()) {
            this.d.setTypeface(null, 1);
        } else {
            this.d.setTypeface(null, 0);
        }
        this.e.setTextColor(hVar.q());
        if (hVar.f()) {
            this.e.setTypeface(null, 1);
        } else {
            this.e.setTypeface(null, 0);
        }
        this.j.setBackgroundColor(hVar.h());
        l.a(this.t, 0, l.c(hVar.i()));
        l.a(this.q, hVar.h(), hVar.i());
        if (z) {
            setBackgroundColor(0);
        } else {
            l.a(this, hVar.h(), hVar.i());
        }
        l.a(this.h, hVar.r(), hVar.s());
        this.h.setTextColor(hVar.t());
        if (hVar.g()) {
            this.h.setTypeface(null, 1);
        } else {
            this.h.setTypeface(null, 0);
        }
    }

    public void setBanner(g gVar) {
        this.w = gVar.getId();
        this.x = gVar;
        if (NavigationType.STORE.equalsIgnoreCase(gVar.getNavigationType())) {
            this.f.setVisibility(8);
            this.i.setVisibility(0);
            this.d.setVisibility(0);
        } else {
            this.f.setVisibility(0);
            this.i.setVisibility(8);
            this.d.setVisibility(8);
        }
        removeCallbacks(this.z);
        removeCallbacks(this.A);
        removeCallbacks(this.y);
        this.m.removeAllViews();
        this.b.setText(gVar.k());
        this.c.setText(gVar.l());
        this.f.setText(gVar.p());
        this.i.setRating(gVar.q());
        this.d.setText(String.valueOf(gVar.o()));
        this.h.setText(TextUtils.isEmpty(gVar.getCtaText()) ? "➜" : gVar.getCtaText());
        if (TextUtils.isEmpty(gVar.getAgeRestrictions())) {
            this.a.setVisibility(8);
        } else {
            this.a.setVisibility(0);
            this.a.setText(gVar.getAgeRestrictions());
        }
        if (TextUtils.isEmpty(gVar.m())) {
            this.e.setVisibility(8);
        } else {
            this.e.setText(gVar.m());
            this.e.setVisibility(0);
        }
        if (!(gVar.n() == null || gVar.n().getData() == null)) {
            this.j.setImageBitmap((Bitmap) gVar.n().getData());
            int width = gVar.n().getWidth();
            int height = gVar.n().getHeight();
            String a = gVar.a();
            if ("teaser".equals(a)) {
                LayoutParams layoutParams;
                this.h.setVisibility(0);
                this.q.setVisibility(0);
                if (width == 0 || height == 0) {
                    layoutParams = new RelativeLayout.LayoutParams(-2, this.s);
                } else {
                    layoutParams = new RelativeLayout.LayoutParams((int) (((float) this.s) / (((float) height) / ((float) width))), this.s);
                }
                this.j.setPadding(this.r.a(2), this.r.a(2), this.r.a(2), this.r.a(2));
                this.j.setLayoutParams(layoutParams);
                this.t.setLayoutParams(layoutParams);
                layoutParams.addRule(9, -1);
                layoutParams.addRule(15, -1);
                this.j.setLayoutParams(layoutParams);
            } else if ("banner".equals(a)) {
                width = (width * this.s) / height;
                this.h.setVisibility(8);
                this.q.setVisibility(8);
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(width, this.s);
                layoutParams2.addRule(14);
                this.j.setLayoutParams(layoutParams2);
                this.t.setLayoutParams(layoutParams2);
            }
        }
        if (!TextUtils.isEmpty(this.a.getText())) {
            post(this.y);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.c.getLayout() != null && this.c.getText() != null) {
            post(this.A);
        }
    }

    private void a(String str, TextView textView) {
        View textView2 = new TextView(getContext());
        textView2.setGravity(16);
        textView2.setPadding(this.r.a(4), this.r.a(4), this.r.a(4), this.r.a(4));
        textView2.setLineSpacing(0.0f, 1.2f);
        textView2.setLines(2);
        textView2.setTypeface(textView.getTypeface());
        textView2.setTextColor(textView.getTextColors());
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-1, this.s));
        textView2.setText(str);
        this.q.addView(textView2);
    }

    private void b() {
        removeCallbacks(this.z);
        postDelayed(this.z, 4000);
    }

    public void setMyTargetClickListener(h hVar) {
        this.v = hVar;
    }

    public final void a() {
        this.q.stopFlipping();
        removeCallbacks(this.z);
        removeCallbacks(this.A);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        removeCallbacks(this.y);
        removeCallbacks(this.A);
        this.q.setDisplayedChild(0);
        while (this.q.getChildCount() > 2) {
            this.q.removeViewAt(this.q.getChildCount() - 1);
        }
        setBanner(this.x);
        b();
    }

    public final void a(boolean z) {
        if (z && this.q.getDisplayedChild() != 0) {
            Animation inAnimation = this.q.getInAnimation();
            Animation outAnimation = this.q.getOutAnimation();
            this.q.setInAnimation(null);
            this.q.setOutAnimation(null);
            this.q.setDisplayedChild(0);
            if (!(inAnimation == null || outAnimation == null)) {
                this.q.setInAnimation(inAnimation);
                this.q.setOutAnimation(outAnimation);
            }
        }
        b();
    }

    public void setAfterLastSlideListener(a aVar) {
        this.u = aVar;
    }

    static /* synthetic */ void a(StandardNativeView standardNativeView, int i) {
        if (((float) standardNativeView.x.m().length()) / ((float) i) > 2.0f) {
            ArrayList b = b(standardNativeView.x.m(), i);
            standardNativeView.e.setText((CharSequence) b.get(0));
            standardNativeView.q.addView(standardNativeView.l);
            for (int i2 = 1; i2 < b.size(); i2++) {
                standardNativeView.a((String) b.get(i2), standardNativeView.e);
            }
            return;
        }
        standardNativeView.q.addView(standardNativeView.l);
    }
}
