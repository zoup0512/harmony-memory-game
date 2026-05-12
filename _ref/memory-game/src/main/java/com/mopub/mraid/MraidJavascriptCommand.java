package com.mopub.mraid;

import android.support.annotation.NonNull;
import org.nexage.sourcekit.mraid.MRAIDNativeFeature;

public enum MraidJavascriptCommand {
    CLOSE("close"),
    EXPAND("expand") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return placementType == PlacementType.INLINE;
        }
    },
    USE_CUSTOM_CLOSE("usecustomclose"),
    OPEN("open") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    RESIZE("resize") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    SET_ORIENTATION_PROPERTIES("setOrientationProperties"),
    PLAY_VIDEO("playVideo") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return placementType == PlacementType.INLINE;
        }
    },
    STORE_PICTURE(MRAIDNativeFeature.STORE_PICTURE) {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    CREATE_CALENDAR_EVENT("createCalendarEvent") {
        boolean requiresClick(@NonNull PlacementType placementType) {
            return true;
        }
    },
    UNSPECIFIED("");
    
    @NonNull
    private final String mJavascriptString;

    private MraidJavascriptCommand(String str) {
        this.mJavascriptString = str;
    }

    static MraidJavascriptCommand fromJavascriptString(@NonNull String str) {
        for (MraidJavascriptCommand mraidJavascriptCommand : values()) {
            if (mraidJavascriptCommand.mJavascriptString.equals(str)) {
                return mraidJavascriptCommand;
            }
        }
        return UNSPECIFIED;
    }

    String toJavascriptString() {
        return this.mJavascriptString;
    }

    boolean requiresClick(@NonNull PlacementType placementType) {
        return false;
    }
}
