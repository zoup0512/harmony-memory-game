package com.cmcm.utils;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.cmcm.adsdk.R;

/* compiled from: DownloadCheckDialog */
public class d {

    /* compiled from: DownloadCheckDialog */
    public interface a {
        void cancelDownload();

        void handleDownload();
    }

    public static void a(Context context, final a aVar) {
        if (context != null && aVar != null) {
            if (i.d(context)) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.gps_dialog, null);
                final Dialog create = new Builder(context).create();
                create.getWindow().requestFeature(1);
                ((TextView) inflate.findViewById(R.id.tv_cancel)).setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        create.dismiss();
                        aVar.cancelDownload();
                    }
                });
                ((TextView) inflate.findViewById(R.id.tv_download)).setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        create.dismiss();
                        aVar.handleDownload();
                    }
                });
                if (Commons.isMiui()) {
                    create.getWindow().setType(2005);
                } else {
                    create.getWindow().setType(2003);
                }
                create.show();
                create.getWindow().setContentView(inflate);
                return;
            }
            aVar.handleDownload();
        }
    }
}
