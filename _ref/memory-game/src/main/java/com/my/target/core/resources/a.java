package com.my.target.core.resources;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Base64;
import com.mopub.mobileads.resource.DrawableConstants;
import com.my.target.Tracer;

/* compiled from: MyTargetResources */
public final class a {
    public static Bitmap a(int i) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(i, i, Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            Tracer.d("cannot build icon: OOME");
        }
        if (bitmap == null) {
            return null;
        }
        float f = ((float) i) / 100.0f;
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setStyle(Style.STROKE);
        Canvas canvas = new Canvas(bitmap);
        float f2 = 3.0f * f;
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(DrawableConstants.TRANSPARENT_GRAY);
        canvas.drawOval(new RectF(0.0f, 0.0f, (float) i, (float) i), paint2);
        paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Style.STROKE);
        paint2.setStrokeWidth(f2);
        paint2.setColor(-1);
        canvas.drawCircle((float) (i / 2), (float) (i / 2), ((float) (i / 2)) - (f2 / 2.0f), paint2);
        paint.setStrokeWidth(f2);
        paint.setColor(-1);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.setFillType(FillType.EVEN_ODD);
        path.moveTo(33.0f * f, 33.0f * f);
        path.lineTo(66.0f * f, 66.0f * f);
        path.moveTo(33.0f * f, 66.0f * f);
        path.lineTo(50.0f * f, 50.0f * f);
        path.moveTo(55.0f * f, 45.0f * f);
        path.lineTo(66.0f * f, f * 33.0f);
        path.close();
        canvas.drawPath(path, paint);
        return bitmap;
    }

    public static Bitmap a(Context context) {
        byte[] decode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AMXCy8fw79+rQAAAhVJREFUeNrt2y9IXlEYx3H3ooIiiCAIC4JgMRgsCyaLwWaxLK0srZhWVtYWVtYWlpYMNsvK0sKKRTANBivDIIggIiLiZ+URDncHFgzbznN+8d77nvPwvec99zz/xsa6uv4oPMWjzADgK55kBnCvj3icGQBc4hWmsgK41w/sZAPwswLiC9ayAJjGa1wNrt/hAxaaBlBcW8ReZTVc4CUmmwZQ3FvHYQXEd2w3DyDuj/AMJxUQn7HaNIDiuRm8wfUAwi3eY75pAMXzS9ivrIZz7GKiaQDF7zZwVAHxDVvNAyj2h+c4rYD4hJWmARRjzOItbir7wzvMNQ2gGGsZB5XVcIYXGG8aQDHmJo4rII6x2TyAGHc83vpZBcQBlpsGUIw/F/vA7QDCTewbs00DKOZZiS/DUKfxJRk1DaCYbyvOCkMdYaN5ADHnRJwazysg9rHUNIBi7vnwI4b7w3X4HTNNAyhsWA3PcqiT8ERHTQMobNmOWMNQh1hvHkDYMxlRp4sKiD0sNg2gsGsh4pB3AwhXEbecbhpAYd9aRKZVItgPT+v96wAKO3ciVzHUw9J6/wuAsHUqslaXFRC/pfVGY139L9A3wf4Z7AehfhTuzlB3h3tApIfE/jqAtEHRtGHx1ImRtKmxtMnRtOnx1AUSaUtk0hZJpS2TS10ombZUNm2xdPpy+d4w0VtmetNU2ra51I2TuVtnuxrWL/YiKQ6CN9uRAAAAAElFTkSuQmCC", 0);
        Options options = new Options();
        options.inDensity = 420;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        return BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
    }

    public static Bitmap b(Context context) {
        byte[] decode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AMXCjITNKc0rQAAAJFJREFUeNrt2tENgCAMQEEwLuD+QzpC3cBURWLsvV+JNRfhi9YkSSpbP3sYETF0WO89s27m3KX6H1AeYL2wdrs5Y3/4ja/OTZ8B2f074h0z5zoDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/lr6rvDoK+xfmWsLNEmSVLUD47EiX/OuE8UAAAAASUVORK5CYII=", 0);
        Options options = new Options();
        options.inDensity = 420;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        return BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
    }

    public static Bitmap c(Context context) {
        byte[] decode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AMXCjM59gfMOgAAA59JREFUeNrtmkloFEEUhl/N6KgxriiJOHEXF4gENYlgRFA8uyAoCNGggl68CCJ6EQx6cCFqUFzABQQRMYh4EfSi4IJbiFERQVxRgxuRMWri5yE1UBY9yWh6Znq6+z+96a6ZV/8/tbz3qkRChAgRwiMAFgJngWgQyVcDHXTiFKCCRH4Df6MFKAkK+W0W+VfAxCAQV8A+i/wTIB4E8lHgpEX+LjA8COQHAM8t8o3ATKC338kXA59IjXbgEVAPLAb6+k2AHRbhVrrGV+AwMNVPIpx3INncjRi/gTPAWL+IcMIieBGIAXFgGXAU+OggRALY7ItIEdhvkTsNRIz3MWCp3h1sXAGK/RAHnLKIHUzRdgnwwmr7Epjsh3jggkWsNkXbQuC4Q8g8Ld9F6ANctYht7KL9GiNpAnib94ujDo5uaUIdwMo00uafhghNQL98F2EYcA9YlGb75daoOeqHnSHyj+23WyLMC1oRJQpct7LJXkETYSrwyxBhlQQNwAFrFKigCTDK2hXmBnEUnDMEOBZEARYaArwLogAF1jRIq4YQ8YsASqmEiNw2HpUHSgCNB4Y9KYgCPDXs8UEU4LNhF6bzhW7DRmCEiFTqjx+VUtc8LECrYfd3RQA9lxq03SgiZR4WIGbYv9yaAt8Me5DHp8DAFP3ukQDvDTvu8WxrnGG/dUUApdQrEUkYU2a8hwWYnGJH6PEu0GzYczwaCSoRqTIeNbkpwFXDnu/Rf79URJKnyz9E5Kab6i4wYuxvQKEHR8BO8+DE7R+PAu8MB6s9Rj4GvDH6tzYTTvZaVZeIhwSoMfr2HRiUCSd21aXGI+T7WRcu6jLp7Ijh6AMw1AMCmKXxtozeLgOKgC+Gw4ZcFiCB2fpWSRLbsuF0nXUYsSlH5EfqE+IknmblSo0+1m6wRKjOMvnB+jzQHPpl2ezAEOCx0YEOYH2WfMct8rlZkIES4LXVkXqgTwZ9Vjn43JrLFXgC8Mzq0H2g0mU/BTrSa7d8bfHCPlwE3HC41XWyp9fbdIS3Tt8fxprzq7wUivYGdmnithCXgRXpxgw67K4A9ljhdxKPgVK3+q5cFqJcRA6JyAyn1zpFfaBz9RZdtYnpStNonc/PEpEhDt9vE5FaEdmtlPrh2YoEEAEWpbje9j9o1aNrhOQbgOlAncNC2R0SwCVgZaZTb5VFMUpEpEJEpojIGBEZLJ21+zbpLGe3iMgTEXkoIneUUj8lRIgQIUKECJFJ/AEepzU1TSID5QAAAABJRU5ErkJggg==", 0);
        Options options = new Options();
        options.inDensity = 420;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        return BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
    }

    public static Bitmap b(int i) {
        Bitmap createBitmap;
        int i2 = (i / 32) + (i / 4);
        int i3 = i / 8;
        try {
            createBitmap = Bitmap.createBitmap(i, i, Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            Tracer.d("cannot build play icon: OOME");
            createBitmap = null;
        }
        if (createBitmap == null) {
            return null;
        }
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(DrawableConstants.TRANSPARENT_GRAY);
        canvas.drawOval(new RectF(0.0f, 0.0f, (float) i, (float) i), paint);
        paint = new Paint();
        paint.setColor(0);
        canvas.drawPaint(paint);
        paint.setStrokeWidth(4.0f);
        paint.setColor(-16733198);
        paint.setStyle(Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        Point point = new Point(i3 * 3, i2);
        Point point2 = new Point(i3 * 3, i - i2);
        Point point3 = new Point(i - (i3 * 2), i / 2);
        Path path = new Path();
        path.setFillType(FillType.EVEN_ODD);
        path.moveTo((float) point.x, (float) point.y);
        path.lineTo((float) point2.x, (float) point2.y);
        path.lineTo((float) point3.x, (float) point3.y);
        path.lineTo((float) point.x, (float) point.y);
        path.close();
        canvas.drawPath(path, paint);
        return createBitmap;
    }

    public static Bitmap c(int i) {
        Bitmap createBitmap;
        try {
            createBitmap = Bitmap.createBitmap(i, i, Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            Tracer.d("cannot build icon: OOME");
            createBitmap = null;
        }
        if (createBitmap == null) {
            return null;
        }
        float f = ((float) i) / 100.0f;
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setStyle(Style.STROKE);
        Canvas canvas = new Canvas(createBitmap);
        a(f, i, paint, canvas);
        Path path = new Path();
        path.setFillType(FillType.EVEN_ODD);
        path.moveTo(62.0f * f, 40.0f * f);
        path.lineTo(82.0f * f, 60.0f * f);
        path.moveTo(62.0f * f, 60.0f * f);
        path.lineTo(82.0f * f, f * 40.0f);
        canvas.drawPath(path, paint);
        return createBitmap;
    }

    private static Canvas a(float f, int i, Paint paint, Canvas canvas) {
        float f2 = 3.0f * f;
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(DrawableConstants.TRANSPARENT_GRAY);
        canvas.drawOval(new RectF(0.0f, 0.0f, (float) i, (float) i), paint2);
        paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Style.STROKE);
        paint2.setStrokeWidth(f2);
        paint2.setColor(-1);
        canvas.drawCircle((float) (i / 2), (float) (i / 2), ((float) (i / 2)) - (f2 / 2.0f), paint2);
        paint.setStrokeWidth(f2);
        paint.setColor(-1);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.setFillType(FillType.EVEN_ODD);
        path.moveTo(23.0f * f, 38.0f * f);
        path.lineTo(23.0f * f, 60.0f * f);
        path.lineTo(38.0f * f, 60.0f * f);
        path.lineTo(38.0f * f, 38.0f * f);
        path.lineTo(56.0f * f, 27.0f * f);
        path.lineTo(56.0f * f, 71.0f * f);
        path.lineTo(38.0f * f, 60.0f * f);
        path.moveTo(38.0f * f, 38.0f * f);
        path.lineTo(23.0f * f, 38.0f * f);
        path.close();
        canvas.drawPath(path, paint);
        return canvas;
    }

    public static Bitmap d(int i) {
        Bitmap createBitmap;
        try {
            createBitmap = Bitmap.createBitmap(i, i, Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            Tracer.d("cannot build icon: OOME");
            createBitmap = null;
        }
        if (createBitmap == null) {
            return null;
        }
        float f = ((float) i) / 100.0f;
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setStyle(Style.STROKE);
        Canvas canvas = new Canvas(createBitmap);
        a(f, i, paint, canvas);
        canvas.drawArc(new RectF(57.0f * f, 45.0f * f, 67.0f * f, 55.0f * f), 90.0f, -180.0f, false, paint);
        canvas.drawArc(new RectF(52.0f * f, 40.0f * f, 72.0f * f, f * 60.0f), 90.0f, -180.0f, false, paint);
        return createBitmap;
    }
}
