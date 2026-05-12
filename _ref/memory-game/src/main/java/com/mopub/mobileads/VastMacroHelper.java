package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VastMacroHelper {
    @NonNull
    private final Map<VastMacro, String> mMacroDataMap = new HashMap();
    @NonNull
    private final List<String> mOriginalUris;

    public VastMacroHelper(@NonNull List<String> list) {
        Preconditions.checkNotNull(list, "uris cannot be null");
        this.mOriginalUris = list;
        this.mMacroDataMap.put(VastMacro.CACHEBUSTING, getCachebustingString());
    }

    @NonNull
    public List<String> getUris() {
        List<String> arrayList = new ArrayList();
        for (String str : this.mOriginalUris) {
            String str2;
            if (!TextUtils.isEmpty(str2)) {
                String str3 = str2;
                for (VastMacro vastMacro : VastMacro.values()) {
                    str2 = (String) this.mMacroDataMap.get(vastMacro);
                    if (str2 == null) {
                        str2 = "";
                    }
                    str3 = str3.replaceAll("\\[" + vastMacro.name() + "\\]", str2);
                }
                arrayList.add(str3);
            }
        }
        return arrayList;
    }

    @NonNull
    public VastMacroHelper withErrorCode(@Nullable VastErrorCode vastErrorCode) {
        if (vastErrorCode != null) {
            this.mMacroDataMap.put(VastMacro.ERRORCODE, vastErrorCode.getErrorCode());
        }
        return this;
    }

    @NonNull
    public VastMacroHelper withContentPlayHead(@Nullable Integer num) {
        if (num != null) {
            CharSequence formatContentPlayHead = formatContentPlayHead(num.intValue());
            if (!TextUtils.isEmpty(formatContentPlayHead)) {
                this.mMacroDataMap.put(VastMacro.CONTENTPLAYHEAD, formatContentPlayHead);
            }
        }
        return this;
    }

    @NonNull
    public VastMacroHelper withAssetUri(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                str = URLEncoder.encode(str, "UTF-8");
            } catch (Throwable e) {
                MoPubLog.w("Failed to encode url", e);
            }
            this.mMacroDataMap.put(VastMacro.ASSETURI, str);
        }
        return this;
    }

    @NonNull
    private String getCachebustingString() {
        return String.format(Locale.US, "%08d", new Object[]{Long.valueOf(Math.round(Math.random() * 1.0E8d))});
    }

    @NonNull
    private String formatContentPlayHead(int i) {
        return String.format("%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toHours((long) i)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes((long) i) % TimeUnit.HOURS.toMinutes(1)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) i) % TimeUnit.MINUTES.toSeconds(1)), Integer.valueOf(i % 1000)});
    }
}
