package com.my.target.core.ui.views.chrome;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.volley.DefaultRetryPolicy;
import com.my.target.core.utils.l;
import java.net.URI;
import java.net.URISyntaxException;

public class CustomWebView extends LinearLayout {
    public static final int a = l.b();
    public static final int b = l.b();
    private final l c;
    private final ImageButton d;
    private final LinearLayout e;
    private final TextView f;
    private final TextView g;
    private final FrameLayout h;
    private final View i;
    private final FrameLayout j;
    private final ImageButton k;
    private a l;
    private final RelativeLayout m;
    private final WebView n;
    private final ProgressBar o;
    private WebViewClient p = new WebViewClient(this) {
        final /* synthetic */ CustomWebView a;

        {
            this.a = r1;
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            this.a.f.setText(CustomWebView.b(str));
            return true;
        }
    };
    private WebChromeClient q = new WebChromeClient(this) {
        final /* synthetic */ CustomWebView a;

        {
            this.a = r1;
        }

        public final void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            this.a.g.setText(webView.getTitle());
            this.a.g.setVisibility(0);
        }

        public final void onProgressChanged(WebView webView, int i) {
            if (i < 100 && this.a.o.getVisibility() == 8) {
                this.a.o.setVisibility(0);
                this.a.i.setVisibility(8);
            }
            this.a.o.setProgress(i);
            if (i >= 100) {
                this.a.o.setVisibility(8);
                this.a.i.setVisibility(0);
            }
        }
    };
    private OnClickListener r = new OnClickListener(this) {
        final /* synthetic */ CustomWebView a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            if (this.a.l != null) {
                this.a.l.onWebViewClosed();
            }
        }
    };
    private OnClickListener s = new OnClickListener(this) {
        final /* synthetic */ CustomWebView a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            Object url = this.a.n.getUrl();
            if (!TextUtils.isEmpty(url)) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                intent.addFlags(268435456);
                this.a.getContext().startActivity(intent);
            }
        }
    };

    public interface a {
        void onWebViewClosed();
    }

    public CustomWebView(Context context) {
        super(context);
        this.m = new RelativeLayout(context);
        this.n = new WebView(context);
        this.d = new ImageButton(context);
        this.e = new LinearLayout(context);
        this.f = new TextView(context);
        this.g = new TextView(context);
        this.h = new FrameLayout(context);
        this.j = new FrameLayout(context);
        this.k = new ImageButton(context);
        this.o = new ProgressBar(context, null, 16842872);
        this.i = new View(context);
        this.c = new l(context);
    }

    public final void a() {
        int complexToDimensionPixelSize;
        setOrientation(1);
        setGravity(16);
        TypedValue typedValue = new TypedValue();
        int a = this.c.a(50);
        if (getContext().getTheme().resolveAttribute(16843499, typedValue, true)) {
            complexToDimensionPixelSize = TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        } else {
            complexToDimensionPixelSize = a;
        }
        this.m.setLayoutParams(new LayoutParams(-1, complexToDimensionPixelSize));
        this.h.setLayoutParams(new LayoutParams(complexToDimensionPixelSize, complexToDimensionPixelSize));
        this.h.setId(a);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.d.setLayoutParams(layoutParams);
        ImageButton imageButton = this.d;
        int i = complexToDimensionPixelSize / 4;
        int a2 = this.c.a(2);
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth((float) a2);
        paint.setAntiAlias(true);
        paint.setColor(-7829368);
        paint.setStrokeCap(Cap.BUTT);
        paint.setStyle(Style.STROKE);
        canvas.drawLine(0.0f, 0.0f, (float) i, (float) i, paint);
        canvas.drawLine(0.0f, (float) i, (float) i, 0.0f, paint);
        imageButton.setImageBitmap(createBitmap);
        this.d.setOnClickListener(this.r);
        layoutParams = new RelativeLayout.LayoutParams(complexToDimensionPixelSize, complexToDimensionPixelSize);
        if (l.b(18)) {
            layoutParams.addRule(21);
        } else {
            layoutParams.addRule(11);
        }
        this.j.setLayoutParams(layoutParams);
        this.j.setId(b);
        layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.k.setLayoutParams(layoutParams);
        ImageButton imageButton2 = this.k;
        Context context = getContext();
        byte[] decode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAQAAABIkb+zAAAAAmJLR0QAAKqNIzIAAAAJcEhZcwAACxMAAAsTAQCanBgAAAAHdElNRQfgAR0KGztQKbC4AAAA8UlEQVR42u2aQQ6EMAzE6IhH8wR+DfeVoCAySRfs82plQ0FNxTQBAAAAAMBXab0frFut4NIeBFTLX4lo4+ufJ+i1z8BI1//sHvz9HSCAAAIIIIAAAiqZo/bl0Vzdi7GECCCAAAJGDlg392wtr77/eEBufXeC/PreBGXoOxOUo+9LUJa+K0F5+p4EZeo7EpSrH5+gbP3oBOXrxyaoQj8yQTX6cQmq0o9KUJ1+TML89A+Ozot+1VznSkxkBBBAAAEEEEAAAQQQ4J8HeqPH3f3+UDMxS4iAgDXt5cVf7iruvVJx/Tuv0aWN//k9AAAAAAB8lx0xVUXCRDTw+wAAAABJRU5ErkJggg==", 0);
        Options options = new Options();
        options.inDensity = 640;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        imageButton2.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length, options));
        this.k.setScaleType(ScaleType.CENTER_INSIDE);
        this.k.setOnClickListener(this.s);
        l.a(this.d, 0, -3355444);
        l.a(this.k, 0, -3355444);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(1, a);
        layoutParams.addRule(0, b);
        this.e.setLayoutParams(layoutParams);
        this.e.setOrientation(1);
        this.e.setPadding(this.c.a(4), this.c.a(4), this.c.a(4), this.c.a(4));
        layoutParams = new LayoutParams(-2, 0);
        layoutParams.weight = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.g.setVisibility(8);
        this.g.setLayoutParams(layoutParams);
        this.g.setTextColor(-16777216);
        this.g.setTextSize(2, RadialCountdown.TEXT_SIZE_SP);
        this.g.setSingleLine();
        this.g.setEllipsize(TruncateAt.MIDDLE);
        layoutParams = new LayoutParams(-2, 0);
        layoutParams.weight = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f.setLayoutParams(layoutParams);
        this.f.setSingleLine();
        this.f.setTextSize(2, 12.0f);
        this.f.setEllipsize(TruncateAt.MIDDLE);
        this.n.setLayoutParams(new LayoutParams(-1, -1));
        this.n.getSettings().setJavaScriptEnabled(true);
        this.n.getSettings().setLoadWithOverviewMode(true);
        this.n.getSettings().setUseWideViewPort(true);
        this.n.getSettings().setBuiltInZoomControls(true);
        if (l.b(11)) {
            this.n.getSettings().setDisplayZoomControls(false);
        }
        this.n.setWebViewClient(this.p);
        this.n.setWebChromeClient(this.q);
        Drawable clipDrawable = new ClipDrawable(new ColorDrawable(-16537100), 3, 1);
        LayerDrawable layerDrawable = (LayerDrawable) this.o.getProgressDrawable();
        layerDrawable.setDrawableByLayerId(16908288, new ColorDrawable(-1968642));
        layerDrawable.setDrawableByLayerId(16908301, clipDrawable);
        this.o.setProgressDrawable(layerDrawable);
        this.o.setLayoutParams(new LayoutParams(-1, this.c.a(2)));
        this.o.setProgress(0);
        this.e.addView(this.g);
        this.e.addView(this.f);
        this.h.addView(this.d);
        this.j.addView(this.k);
        this.m.addView(this.h);
        this.m.addView(this.e);
        this.m.addView(this.j);
        addView(this.m);
        this.i.setBackgroundColor(-5592406);
        layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        this.i.setVisibility(8);
        this.i.setLayoutParams(layoutParams);
        addView(this.o);
        addView(this.i);
        addView(this.n);
    }

    private static String b(String str) {
        try {
            URI uri = new URI(str);
            str = uri.getScheme() + "://" + uri.getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return str;
    }

    public void setListener(a aVar) {
        this.l = aVar;
    }

    public final boolean b() {
        return this.n.canGoBack();
    }

    public final void c() {
        this.n.goBack();
    }

    public final void d() {
        if (this.n != null) {
            this.n.setWebChromeClient(null);
            this.n.setWebViewClient(null);
            this.n.destroy();
        }
    }

    public void setUrl(String str) {
        this.n.loadUrl(str);
        this.f.setText(b(str));
    }
}
