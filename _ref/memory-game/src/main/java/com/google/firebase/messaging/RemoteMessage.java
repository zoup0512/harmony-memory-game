package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.Map;
import java.util.Map.Entry;

public final class RemoteMessage extends AbstractSafeParcelable {
    public static final Creator<RemoteMessage> CREATOR = new zzc();
    Bundle bI;
    private Notification baR;
    final int mVersionCode;
    private Map<String, String> zzctd;

    public static class Builder {
        private final Bundle bI = new Bundle();
        private final Map<String, String> zzctd = new ArrayMap();

        public Builder(String str) {
            if (TextUtils.isEmpty(str)) {
                String str2 = "Invalid to: ";
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            this.bI.putString("google.to", str);
        }

        public Builder addData(String str, String str2) {
            this.zzctd.put(str, str2);
            return this;
        }

        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            for (Entry entry : this.zzctd.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
            bundle.putAll(this.bI);
            String token = FirebaseInstanceId.getInstance().getToken();
            if (token != null) {
                this.bI.putString("from", token);
            } else {
                this.bI.remove("from");
            }
            return new RemoteMessage(bundle);
        }

        public Builder clearData() {
            this.zzctd.clear();
            return this;
        }

        public Builder setCollapseKey(String str) {
            this.bI.putString("collapse_key", str);
            return this;
        }

        public Builder setData(Map<String, String> map) {
            this.zzctd.clear();
            this.zzctd.putAll(map);
            return this;
        }

        public Builder setMessageId(String str) {
            this.bI.putString("google.message_id", str);
            return this;
        }

        public Builder setMessageType(String str) {
            this.bI.putString("message_type", str);
            return this;
        }

        public Builder setTtl(int i) {
            this.bI.putString("google.ttl", String.valueOf(i));
            return this;
        }
    }

    public static class Notification {
        private final String DZ;
        private final String JE;
        private final String baS;
        private final String[] baT;
        private final String baU;
        private final String[] baV;
        private final String baW;
        private final String baX;
        private final String baY;
        private final String mTag;
        private final String zzbfi;

        private Notification(Bundle bundle) {
            this.DZ = zza.zzf(bundle, "gcm.n.title");
            this.baS = zza.zzh(bundle, "gcm.n.title");
            this.baT = zzj(bundle, "gcm.n.title");
            this.zzbfi = zza.zzf(bundle, "gcm.n.body");
            this.baU = zza.zzh(bundle, "gcm.n.body");
            this.baV = zzj(bundle, "gcm.n.body");
            this.baW = zza.zzf(bundle, "gcm.n.icon");
            this.baX = zza.zzat(bundle);
            this.mTag = zza.zzf(bundle, "gcm.n.tag");
            this.JE = zza.zzf(bundle, "gcm.n.color");
            this.baY = zza.zzf(bundle, "gcm.n.click_action");
        }

        private String[] zzj(Bundle bundle, String str) {
            Object[] zzi = zza.zzi(bundle, str);
            if (zzi == null) {
                return null;
            }
            String[] strArr = new String[zzi.length];
            for (int i = 0; i < zzi.length; i++) {
                strArr[i] = String.valueOf(zzi[i]);
            }
            return strArr;
        }

        public String getBody() {
            return this.zzbfi;
        }

        public String[] getBodyLocalizationArgs() {
            return this.baV;
        }

        public String getBodyLocalizationKey() {
            return this.baU;
        }

        public String getClickAction() {
            return this.baY;
        }

        public String getColor() {
            return this.JE;
        }

        public String getIcon() {
            return this.baW;
        }

        public String getSound() {
            return this.baX;
        }

        public String getTag() {
            return this.mTag;
        }

        public String getTitle() {
            return this.DZ;
        }

        public String[] getTitleLocalizationArgs() {
            return this.baT;
        }

        public String getTitleLocalizationKey() {
            return this.baS;
        }
    }

    RemoteMessage(int i, Bundle bundle) {
        this.mVersionCode = i;
        this.bI = bundle;
    }

    RemoteMessage(Bundle bundle) {
        this(1, bundle);
    }

    public String getCollapseKey() {
        return this.bI.getString("collapse_key");
    }

    public Map<String, String> getData() {
        if (this.zzctd == null) {
            this.zzctd = new ArrayMap();
            for (String str : this.bI.keySet()) {
                Object obj = this.bI.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!(str.startsWith("google.") || str.startsWith("gcm.") || str.equals("from") || str.equals("message_type") || str.equals("collapse_key"))) {
                        this.zzctd.put(str, str2);
                    }
                }
            }
        }
        return this.zzctd;
    }

    public String getFrom() {
        return this.bI.getString("from");
    }

    public String getMessageId() {
        String string = this.bI.getString("google.message_id");
        return string == null ? this.bI.getString("message_id") : string;
    }

    public String getMessageType() {
        return this.bI.getString("message_type");
    }

    public Notification getNotification() {
        if (this.baR == null && zza.zzac(this.bI)) {
            this.baR = new Notification(this.bI);
        }
        return this.baR;
    }

    public long getSentTime() {
        return this.bI.getLong("google.sent_time");
    }

    public String getTo() {
        return this.bI.getString("google.to");
    }

    public int getTtl() {
        Object obj = this.bI.get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                String valueOf = String.valueOf(obj);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(valueOf).length() + 13).append("Invalid TTL: ").append(valueOf).toString());
            }
        }
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }

    void zzaf(Intent intent) {
        intent.putExtras(this.bI);
    }
}
