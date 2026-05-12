package com.google.android.gms.analytics;

import android.content.Context;
import com.amazonaws.services.s3.internal.Constants;
import com.google.android.gms.analytics.internal.zzae;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

public class ExceptionReporter implements UncaughtExceptionHandler {
    private final Context mContext;
    private final UncaughtExceptionHandler zzcsc;
    private final Tracker zzcsd;
    private ExceptionParser zzcse;
    private GoogleAnalytics zzcsf;

    public ExceptionReporter(Tracker tracker, UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (context == null) {
            throw new NullPointerException("context cannot be null");
        } else {
            this.zzcsc = uncaughtExceptionHandler;
            this.zzcsd = tracker;
            this.zzcse = new StandardExceptionParser(context, new ArrayList());
            this.mContext = context.getApplicationContext();
            String str = "ExceptionReporter created, original handler is ";
            String valueOf = String.valueOf(uncaughtExceptionHandler == null ? Constants.NULL_VERSION_ID : uncaughtExceptionHandler.getClass().getName());
            zzae.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    public ExceptionParser getExceptionParser() {
        return this.zzcse;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.zzcse = exceptionParser;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String str = "UncaughtException";
        if (this.zzcse != null) {
            str = this.zzcse.getDescription(thread != null ? thread.getName() : null, th);
        }
        String str2 = "Reporting uncaught exception: ";
        String valueOf = String.valueOf(str);
        zzae.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzcsd.send(new HitBuilders$ExceptionBuilder().setDescription(str).setFatal(true).build());
        GoogleAnalytics zzvx = zzvx();
        zzvx.dispatchLocalHits();
        zzvx.zzwc();
        if (this.zzcsc != null) {
            zzae.v("Passing exception to the original handler");
            this.zzcsc.uncaughtException(thread, th);
        }
    }

    GoogleAnalytics zzvx() {
        if (this.zzcsf == null) {
            this.zzcsf = GoogleAnalytics.getInstance(this.mContext);
        }
        return this.zzcsf;
    }

    UncaughtExceptionHandler zzvy() {
        return this.zzcsc;
    }
}
