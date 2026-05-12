package com.mopub.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubActivity;
import com.mopub.mobileads.MraidActivity;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import java.util.ArrayList;
import java.util.List;

public class ManifestUtils {
    private static final List<Class<? extends Activity>> REQUIRED_NATIVE_SDK_ACTIVITIES = new ArrayList(1);
    private static final List<Class<? extends Activity>> REQUIRED_WEB_VIEW_SDK_ACTIVITIES = new ArrayList(4);
    private static FlagCheckUtil sFlagCheckUtil = new FlagCheckUtil();

    private ManifestUtils() {
    }

    static {
        REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(MoPubActivity.class);
        REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(MraidActivity.class);
        REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(MraidVideoPlayerActivity.class);
        REQUIRED_WEB_VIEW_SDK_ACTIVITIES.add(MoPubBrowser.class);
        REQUIRED_NATIVE_SDK_ACTIVITIES.add(MoPubBrowser.class);
    }

    public static void checkWebViewActivitiesDeclared(@NonNull Context context) {
        if (NoThrow.checkNotNull(context, "context is not allowed to be null")) {
            displayWarningForMissingActivities(context, REQUIRED_WEB_VIEW_SDK_ACTIVITIES);
            displayWarningForMisconfiguredActivities(context, REQUIRED_WEB_VIEW_SDK_ACTIVITIES);
        }
    }

    public static void checkNativeActivitiesDeclared(@NonNull Context context) {
        if (NoThrow.checkNotNull(context, "context is not allowed to be null")) {
            displayWarningForMissingActivities(context, REQUIRED_NATIVE_SDK_ACTIVITIES);
            displayWarningForMisconfiguredActivities(context, REQUIRED_NATIVE_SDK_ACTIVITIES);
        }
    }

    @VisibleForTesting
    static void displayWarningForMissingActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list) {
        List filterDeclaredActivities = filterDeclaredActivities(context, list, false);
        if (!filterDeclaredActivities.isEmpty()) {
            logWarningToast(context);
            logMissingActivities(filterDeclaredActivities);
        }
    }

    @VisibleForTesting
    static void displayWarningForMisconfiguredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list) {
        List misconfiguredActivities = getMisconfiguredActivities(context, filterDeclaredActivities(context, list, true));
        if (!misconfiguredActivities.isEmpty()) {
            logWarningToast(context);
            logMisconfiguredActivities(context, misconfiguredActivities);
        }
    }

    public static boolean isDebuggable(@NonNull Context context) {
        return Utils.bitMaskContainsFlag(context.getApplicationInfo().flags, 2);
    }

    private static List<Class<? extends Activity>> filterDeclaredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list, boolean z) {
        List<Class<? extends Activity>> arrayList = new ArrayList();
        for (Class cls : list) {
            if (Intents.deviceCanHandleIntent(context, new Intent(context, cls)) == z) {
                arrayList.add(cls);
            }
        }
        return arrayList;
    }

    @TargetApi(13)
    private static List<Class<? extends Activity>> getMisconfiguredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list) {
        List<Class<? extends Activity>> arrayList = new ArrayList();
        for (Class cls : list) {
            try {
                ActivityConfigChanges activityConfigChanges = getActivityConfigChanges(context, cls);
                if (!activityConfigChanges.hasKeyboardHidden || !activityConfigChanges.hasOrientation || !activityConfigChanges.hasScreenSize) {
                    arrayList.add(cls);
                }
            } catch (NameNotFoundException e) {
            }
        }
        return arrayList;
    }

    private static void logMissingActivities(@NonNull List<Class<? extends Activity>> list) {
        StringBuilder stringBuilder = new StringBuilder("AndroidManifest permissions for the following required MoPub activities are missing:\n");
        for (Class name : list) {
            stringBuilder.append("\n\t").append(name.getName());
        }
        stringBuilder.append("\n\nPlease update your manifest to include them.");
        MoPubLog.w(stringBuilder.toString());
    }

    private static void logMisconfiguredActivities(@NonNull Context context, @NonNull List<Class<? extends Activity>> list) {
        StringBuilder stringBuilder = new StringBuilder("In AndroidManifest, the android:configChanges param is missing values for the following MoPub activities:\n");
        for (Class cls : list) {
            try {
                ActivityConfigChanges activityConfigChanges = getActivityConfigChanges(context, cls);
                if (!activityConfigChanges.hasKeyboardHidden) {
                    stringBuilder.append("\n\tThe android:configChanges param for activity " + cls.getName() + " must include keyboardHidden.");
                }
                if (!activityConfigChanges.hasOrientation) {
                    stringBuilder.append("\n\tThe android:configChanges param for activity " + cls.getName() + " must include orientation.");
                }
                if (!activityConfigChanges.hasScreenSize) {
                    stringBuilder.append("\n\tThe android:configChanges param for activity " + cls.getName() + " must include screenSize.");
                }
            } catch (NameNotFoundException e) {
            }
        }
        stringBuilder.append("\n\nPlease update your manifest to include them.");
        MoPubLog.w(stringBuilder.toString());
    }

    private static ActivityConfigChanges getActivityConfigChanges(@NonNull Context context, @NonNull Class<? extends Activity> cls) {
        ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(new ComponentName(context, cls.getName()), 0);
        ActivityConfigChanges activityConfigChanges = new ActivityConfigChanges(null);
        activityConfigChanges.hasKeyboardHidden = sFlagCheckUtil.hasFlag(cls, activityInfo.configChanges, 32);
        activityConfigChanges.hasOrientation = sFlagCheckUtil.hasFlag(cls, activityInfo.configChanges, 128);
        activityConfigChanges.hasScreenSize = true;
        if (VersionCode.currentApiLevel().isAtLeast(VersionCode.HONEYCOMB_MR2) && context.getApplicationInfo().targetSdkVersion >= VersionCode.HONEYCOMB_MR2.getApiLevel()) {
            activityConfigChanges.hasScreenSize = sFlagCheckUtil.hasFlag(cls, activityInfo.configChanges, 1024);
        }
        return activityConfigChanges;
    }

    private static void logWarningToast(@NonNull Context context) {
        if (isDebuggable(context)) {
            String str = "ERROR: YOUR MOPUB INTEGRATION IS INCOMPLETE.\nCheck logcat and update your AndroidManifest.xml with the correct activities and configuration.";
            Toast makeText = Toast.makeText(context, "ERROR: YOUR MOPUB INTEGRATION IS INCOMPLETE.\nCheck logcat and update your AndroidManifest.xml with the correct activities and configuration.", 1);
            makeText.setGravity(7, 0, 0);
            makeText.show();
        }
    }

    @Deprecated
    @VisibleForTesting
    static List<Class<? extends Activity>> getRequiredWebViewSdkActivities() {
        return REQUIRED_WEB_VIEW_SDK_ACTIVITIES;
    }

    @Deprecated
    @VisibleForTesting
    static List<Class<? extends Activity>> getRequiredNativeSdkActivities() {
        return REQUIRED_NATIVE_SDK_ACTIVITIES;
    }

    @Deprecated
    @VisibleForTesting
    static void setFlagCheckUtil(FlagCheckUtil flagCheckUtil) {
        sFlagCheckUtil = flagCheckUtil;
    }
}
