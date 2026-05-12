package com.unity3d.ads2.log;

import com.yalantis.ucrop.util.FileUtils;

class DeviceLogEntry {
    private DeviceLogLevel _logLevel = null;
    private String _originalMessage = null;
    private StackTraceElement _stackTraceElement = null;

    public DeviceLogEntry(DeviceLogLevel deviceLogLevel, String str, StackTraceElement stackTraceElement) {
        this._logLevel = deviceLogLevel;
        this._originalMessage = str;
        this._stackTraceElement = stackTraceElement;
    }

    public DeviceLogLevel getLogLevel() {
        return this._logLevel;
    }

    public String getParsedMessage() {
        String str = this._originalMessage;
        String str2 = "UnknownClass";
        String str3 = "unknownMethod";
        int i = -1;
        if (this._stackTraceElement != null) {
            str2 = this._stackTraceElement.getClassName();
            str3 = this._stackTraceElement.getMethodName();
            i = this._stackTraceElement.getLineNumber();
        }
        if (!(str == null || str.isEmpty())) {
            str = " :: " + str;
        }
        if (str == null) {
            str = "";
        }
        return str2 + FileUtils.HIDDEN_PREFIX + str3 + "()" + (" (line:" + i + ")") + str;
    }
}
