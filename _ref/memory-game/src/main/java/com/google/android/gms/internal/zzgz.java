package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;

@zzin
public class zzgz extends zzhf {
    private final Context mContext;
    private final Map<String, String> zzbeg;
    private String zzbpy;
    private long zzbpz;
    private long zzbqa;
    private String zzbqb;
    private String zzbqc;

    public zzgz(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        super(com_google_android_gms_internal_zzlh, "createCalendarEvent");
        this.zzbeg = map;
        this.mContext = com_google_android_gms_internal_zzlh.zzue();
        zzmt();
    }

    private String zzbq(String str) {
        return TextUtils.isEmpty((CharSequence) this.zzbeg.get(str)) ? "" : (String) this.zzbeg.get(str);
    }

    private long zzbr(String str) {
        String str2 = (String) this.zzbeg.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void zzmt() {
        this.zzbpy = zzbq("description");
        this.zzbqb = zzbq("summary");
        this.zzbpz = zzbr("start_ticks");
        this.zzbqa = zzbr("end_ticks");
        this.zzbqc = zzbq("location");
    }

    @TargetApi(14)
    Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(Events.CONTENT_URI);
        data.putExtra("title", this.zzbpy);
        data.putExtra("eventLocation", this.zzbqc);
        data.putExtra("description", this.zzbqb);
        if (this.zzbpz > -1) {
            data.putExtra("beginTime", this.zzbpz);
        }
        if (this.zzbqa > -1) {
            data.putExtra("endTime", this.zzbqa);
        }
        data.setFlags(268435456);
        return data;
    }

    public void execute() {
        if (this.mContext == null) {
            zzbt("Activity context is not available.");
        } else if (zzu.zzfq().zzag(this.mContext).zzju()) {
            Builder zzaf = zzu.zzfq().zzaf(this.mContext);
            Resources resources = zzu.zzft().getResources();
            zzaf.setTitle(resources != null ? resources.getString(R.string.create_calendar_title) : "Create calendar event");
            zzaf.setMessage(resources != null ? resources.getString(R.string.create_calendar_message) : "Allow Ad to create a calendar event?");
            zzaf.setPositiveButton(resources != null ? resources.getString(R.string.accept) : "Accept", new 1(this));
            zzaf.setNegativeButton(resources != null ? resources.getString(R.string.decline) : "Decline", new 2(this));
            zzaf.create().show();
        } else {
            zzbt("This feature is not available on the device.");
        }
    }
}
