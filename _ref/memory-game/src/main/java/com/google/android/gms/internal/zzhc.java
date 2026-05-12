package com.google.android.gms.internal;

import android.app.AlertDialog.Builder;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;
import org.nexage.sourcekit.mraid.MRAIDNativeFeature;

@zzin
public class zzhc extends zzhf {
    private final Context mContext;
    private final Map<String, String> zzbeg;

    public zzhc(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        super(com_google_android_gms_internal_zzlh, MRAIDNativeFeature.STORE_PICTURE);
        this.zzbeg = map;
        this.mContext = com_google_android_gms_internal_zzlh.zzue();
    }

    public void execute() {
        if (this.mContext == null) {
            zzbt("Activity context is not available");
        } else if (zzu.zzfq().zzag(this.mContext).zzjr()) {
            String str = (String) this.zzbeg.get("iurl");
            if (TextUtils.isEmpty(str)) {
                zzbt("Image url cannot be empty.");
            } else if (URLUtil.isValidUrl(str)) {
                String zzbs = zzbs(str);
                if (zzu.zzfq().zzcq(zzbs)) {
                    Resources resources = zzu.zzft().getResources();
                    Builder zzaf = zzu.zzfq().zzaf(this.mContext);
                    zzaf.setTitle(resources != null ? resources.getString(R.string.store_picture_title) : "Save image");
                    zzaf.setMessage(resources != null ? resources.getString(R.string.store_picture_message) : "Allow Ad to store image in Picture gallery?");
                    zzaf.setPositiveButton(resources != null ? resources.getString(R.string.accept) : "Accept", new 1(this, str, zzbs));
                    zzaf.setNegativeButton(resources != null ? resources.getString(R.string.decline) : "Decline", new 2(this));
                    zzaf.create().show();
                    return;
                }
                r1 = "Image type not recognized: ";
                str = String.valueOf(zzbs);
                zzbt(str.length() != 0 ? r1.concat(str) : new String(r1));
            } else {
                r1 = "Invalid image url: ";
                str = String.valueOf(str);
                zzbt(str.length() != 0 ? r1.concat(str) : new String(r1));
            }
        } else {
            zzbt("Feature is not supported by the device.");
        }
    }

    String zzbs(String str) {
        return Uri.parse(str).getLastPathSegment();
    }

    Request zzk(String str, String str2) {
        Request request = new Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
        zzu.zzfs().zza(request);
        return request;
    }
}
