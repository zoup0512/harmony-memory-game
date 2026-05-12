package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amazon.device.ads.WebRequest;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.List;

public class PlusShare$Builder {
    private boolean arI;
    private ArrayList<Uri> arJ;
    private final Context mContext;
    private final Intent mIntent = new Intent().setAction("android.intent.action.SEND");

    public PlusShare$Builder(Activity activity) {
        this.mContext = activity;
        this.mIntent.addFlags(524288);
        if (activity != null && activity.getComponentName() != null) {
            this.arI = true;
        }
    }

    public PlusShare$Builder(Context context) {
        this.mContext = context;
    }

    public PlusShare$Builder addCallToAction(String str, Uri uri, String str2) {
        zzab.zza(this.arI, (Object) "Must include the launching activity with PlusShare.Builder constructor before setting call-to-action");
        boolean z = (uri == null || TextUtils.isEmpty(uri.toString())) ? false : true;
        zzab.zzb(z, (Object) "Must provide a call to action URL");
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(PlusShare.KEY_CALL_TO_ACTION_LABEL, str);
        }
        bundle.putString("url", uri.toString());
        if (!TextUtils.isEmpty(str2)) {
            zzab.zza(PlusShare.zznb(str2), (Object) "The specified deep-link ID was malformed.");
            bundle.putString(PlusShare.KEY_CALL_TO_ACTION_DEEP_LINK_ID, str2);
        }
        this.mIntent.putExtra(PlusShare.EXTRA_CALL_TO_ACTION, bundle);
        this.mIntent.putExtra(PlusShare.EXTRA_IS_INTERACTIVE_POST, true);
        this.mIntent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        return this;
    }

    public PlusShare$Builder addStream(Uri uri) {
        Uri uri2 = (Uri) this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        if (uri2 == null) {
            return setStream(uri);
        }
        if (this.arJ == null) {
            this.arJ = new ArrayList();
        }
        this.arJ.add(uri2);
        this.arJ.add(uri);
        return this;
    }

    public Intent getIntent() {
        boolean z = true;
        boolean z2 = this.arJ != null && this.arJ.size() > 1;
        boolean equals = "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
        boolean booleanExtra = this.mIntent.getBooleanExtra(PlusShare.EXTRA_IS_INTERACTIVE_POST, false);
        boolean z3 = (z2 && booleanExtra) ? false : true;
        zzab.zza(z3, (Object) "Call-to-action buttons are only available for URLs.");
        z3 = !booleanExtra || this.mIntent.hasExtra(PlusShare.EXTRA_CONTENT_URL);
        zzab.zza(z3, (Object) "The content URL is required for interactive posts.");
        if (!(!booleanExtra || this.mIntent.hasExtra(PlusShare.EXTRA_CONTENT_URL) || this.mIntent.hasExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID))) {
            z = false;
        }
        zzab.zza(z, (Object) "Must set content URL or content deep-link ID to use a call-to-action button.");
        if (this.mIntent.hasExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID)) {
            zzab.zza(PlusShare.zznb(this.mIntent.getStringExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID)), (Object) "The specified deep-link ID was malformed.");
        }
        if (!z2 && equals) {
            this.mIntent.setAction("android.intent.action.SEND");
            if (this.arJ == null || this.arJ.isEmpty()) {
                this.mIntent.removeExtra("android.intent.extra.STREAM");
            } else {
                this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable) this.arJ.get(0));
            }
            this.arJ = null;
        }
        if (z2 && !equals) {
            this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
            if (this.arJ == null || this.arJ.isEmpty()) {
                this.mIntent.removeExtra("android.intent.extra.STREAM");
            } else {
                this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.arJ);
            }
        }
        if ("com.google.android.gms.plus.action.SHARE_INTERNAL_GOOGLE".equals(this.mIntent.getAction())) {
            this.mIntent.setPackage("com.google.android.gms");
            return this.mIntent;
        } else if (this.mIntent.hasExtra("android.intent.extra.STREAM")) {
            this.mIntent.setPackage("com.google.android.apps.plus");
            return this.mIntent;
        } else {
            this.mIntent.setAction("com.google.android.gms.plus.action.SHARE_GOOGLE");
            this.mIntent.setPackage("com.google.android.gms");
            return this.mIntent;
        }
    }

    public PlusShare$Builder setContentDeepLinkId(String str) {
        return setContentDeepLinkId(str, null, null, null);
    }

    public PlusShare$Builder setContentDeepLinkId(String str, String str2, String str3, Uri uri) {
        zzab.zzb(this.arI, (Object) "Must include the launching activity with PlusShare.Builder constructor before setting deep links");
        zzab.zzb(!TextUtils.isEmpty(str), (Object) "The deepLinkId parameter is required.");
        Bundle zza = PlusShare.zza(str2, str3, uri);
        this.mIntent.putExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_ID, str);
        this.mIntent.putExtra(PlusShare.EXTRA_CONTENT_DEEP_LINK_METADATA, zza);
        this.mIntent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        return this;
    }

    public PlusShare$Builder setContentUrl(Uri uri) {
        Object obj = null;
        if (uri != null) {
            obj = uri.toString();
        }
        if (TextUtils.isEmpty(obj)) {
            this.mIntent.removeExtra(PlusShare.EXTRA_CONTENT_URL);
        } else {
            this.mIntent.putExtra(PlusShare.EXTRA_CONTENT_URL, obj);
        }
        return this;
    }

    public PlusShare$Builder setRecipients(Person person, List<Person> list) {
        this.mIntent.putExtra(PlusShare.EXTRA_SENDER_ID, person != null ? person.getId() : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        int size = list != null ? list.size() : 0;
        if (size == 0) {
            this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_IDS");
            this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES");
        } else {
            ArrayList arrayList = new ArrayList(size);
            ArrayList arrayList2 = new ArrayList(size);
            for (Person person2 : list) {
                arrayList.add(person2.getId());
                arrayList2.add(person2.getDisplayName());
            }
            this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_IDS", arrayList);
            this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES", arrayList2);
        }
        return this;
    }

    public PlusShare$Builder setStream(Uri uri) {
        this.arJ = null;
        this.mIntent.putExtra("android.intent.extra.STREAM", uri);
        return this;
    }

    public PlusShare$Builder setText(CharSequence charSequence) {
        this.mIntent.putExtra("android.intent.extra.TEXT", charSequence);
        return this;
    }

    public PlusShare$Builder setType(String str) {
        this.mIntent.setType(str);
        return this;
    }
}
