package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;

class NativeUrlGenerator extends AdUrlGenerator {
    @Nullable
    private String mDesiredAssets;
    @Nullable
    private String mSequenceNumber;

    NativeUrlGenerator(Context context) {
        super(context);
    }

    @NonNull
    public NativeUrlGenerator withAdUnitId(String str) {
        this.mAdUnitId = str;
        return this;
    }

    @NonNull
    NativeUrlGenerator withRequest(@Nullable RequestParameters requestParameters) {
        if (requestParameters != null) {
            this.mKeywords = requestParameters.getKeywords();
            this.mLocation = requestParameters.getLocation();
            this.mDesiredAssets = requestParameters.getDesiredAssets();
        }
        return this;
    }

    @NonNull
    NativeUrlGenerator withSequenceNumber(int i) {
        this.mSequenceNumber = String.valueOf(i);
        return this;
    }

    public String generateUrlString(String str) {
        initUrlString(str, Constants.AD_HANDLER);
        addBaseParams(ClientMetadata.getInstance(this.mContext));
        setDesiredAssets();
        setSequenceNumber();
        return getFinalUrlString();
    }

    private void setSequenceNumber() {
        if (!TextUtils.isEmpty(this.mSequenceNumber)) {
            addParam("MAGIC_NO", this.mSequenceNumber);
        }
    }

    private void setDesiredAssets() {
        if (!TextUtils.isEmpty(this.mDesiredAssets)) {
            addParam("assets", this.mDesiredAssets);
        }
    }

    protected void setSdkVersion(String str) {
        addParam("nsv", str);
    }
}
