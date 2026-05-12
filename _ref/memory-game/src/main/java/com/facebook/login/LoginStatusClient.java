package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient;

final class LoginStatusClient extends PlatformServiceClient {
    private final String loggerRef;

    LoginStatusClient(Context context, String applicationId, String loggerRef) {
        super(context, NativeProtocol.MESSAGE_GET_LOGIN_STATUS_REQUEST, NativeProtocol.MESSAGE_GET_LOGIN_STATUS_REPLY, NativeProtocol.PROTOCOL_VERSION_20170411, applicationId);
        this.loggerRef = loggerRef;
    }

    protected void populateRequestBundle(Bundle data) {
        data.putString(NativeProtocol.EXTRA_LOGGER_REF, this.loggerRef);
    }
}
