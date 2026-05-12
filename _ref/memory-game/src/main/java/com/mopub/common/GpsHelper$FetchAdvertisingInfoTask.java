package com.mopub.common;

import android.content.Context;
import android.os.AsyncTask;
import com.mopub.common.factories.MethodBuilderFactory;
import com.mopub.common.logging.MoPubLog;
import java.lang.ref.WeakReference;

class GpsHelper$FetchAdvertisingInfoTask extends AsyncTask<Void, Void, Void> {
    private WeakReference<Context> mContextWeakReference;
    private WeakReference<GpsHelper$GpsHelperListener> mGpsHelperListenerWeakReference;

    public GpsHelper$FetchAdvertisingInfoTask(Context context, GpsHelper$GpsHelperListener gpsHelper$GpsHelperListener) {
        this.mContextWeakReference = new WeakReference(context);
        this.mGpsHelperListenerWeakReference = new WeakReference(gpsHelper$GpsHelperListener);
    }

    protected Void doInBackground(Void... voidArr) {
        try {
            Context context = (Context) this.mContextWeakReference.get();
            if (context != null) {
                Object execute = MethodBuilderFactory.create(null, "getAdvertisingIdInfo").setStatic(Class.forName(GpsHelper.access$000())).addParam(Context.class, context).execute();
                if (execute != null) {
                    GpsHelper.updateClientMetadata(context, execute);
                }
            }
        } catch (Exception e) {
            MoPubLog.d("Unable to obtain Google AdvertisingIdClient.Info via reflection.");
        }
        return null;
    }

    protected void onPostExecute(Void voidR) {
        GpsHelper$GpsHelperListener gpsHelper$GpsHelperListener = (GpsHelper$GpsHelperListener) this.mGpsHelperListenerWeakReference.get();
        if (gpsHelper$GpsHelperListener != null) {
            gpsHelper$GpsHelperListener.onFetchAdInfoCompleted();
        }
    }
}
