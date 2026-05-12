package com.mopub.mraid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.amazon.device.ads.CalendarEventParameters;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Intents;
import com.mopub.common.util.Utils;
import com.mopub.common.util.VersionCode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MraidNativeCommandHandler {
    public static final String ANDROID_CALENDAR_CONTENT_TYPE = "vnd.android.cursor.item/event";
    private static final String[] DATE_FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ssZZZZZ", CalendarEventParameters.DATE_FORMAT};
    private static final int MAX_NUMBER_DAYS_IN_MONTH = 31;
    @VisibleForTesting
    static final String MIME_TYPE_HEADER = "Content-Type";

    interface MraidCommandFailureListener {
        void onFailure(MraidCommandException mraidCommandException);
    }

    private static class MoPubMediaScannerConnectionClient implements MediaScannerConnectionClient {
        private final String mFilename;
        private MediaScannerConnection mMediaScannerConnection;
        private final String mMimeType;

        private MoPubMediaScannerConnectionClient(String str, String str2) {
            this.mFilename = str;
            this.mMimeType = str2;
        }

        private void setMediaScannerConnection(MediaScannerConnection mediaScannerConnection) {
            this.mMediaScannerConnection = mediaScannerConnection;
        }

        public void onMediaScannerConnected() {
            if (this.mMediaScannerConnection != null) {
                this.mMediaScannerConnection.scanFile(this.mFilename, this.mMimeType);
            }
        }

        public void onScanCompleted(String str, Uri uri) {
            if (this.mMediaScannerConnection != null) {
                this.mMediaScannerConnection.disconnect();
            }
        }
    }

    void createCalendarEvent(Context context, Map<String, String> map) {
        if (isCalendarAvailable(context)) {
            try {
                Map translateJSParamsToAndroidCalendarEventMapping = translateJSParamsToAndroidCalendarEventMapping(map);
                Intent type = new Intent("android.intent.action.INSERT").setType(ANDROID_CALENDAR_CONTENT_TYPE);
                for (String str : translateJSParamsToAndroidCalendarEventMapping.keySet()) {
                    Object obj = translateJSParamsToAndroidCalendarEventMapping.get(str);
                    if (obj instanceof Long) {
                        type.putExtra(str, ((Long) obj).longValue());
                    } else if (obj instanceof Integer) {
                        type.putExtra(str, ((Integer) obj).intValue());
                    } else {
                        type.putExtra(str, (String) obj);
                    }
                }
                type.setFlags(268435456);
                context.startActivity(type);
                return;
            } catch (ActivityNotFoundException e) {
                MoPubLog.d("no calendar app installed");
                throw new MraidCommandException("Action is unsupported on this device - no calendar app installed");
            } catch (Throwable e2) {
                MoPubLog.d("create calendar: invalid parameters " + e2.getMessage());
                throw new MraidCommandException(e2);
            } catch (Throwable e22) {
                MoPubLog.d("could not create calendar event");
                throw new MraidCommandException(e22);
            }
        }
        MoPubLog.d("unsupported action createCalendarEvent for devices pre-ICS");
        throw new MraidCommandException("Action is unsupported on this device (need Android version Ice Cream Sandwich or above)");
    }

    void storePicture(@NonNull Context context, @NonNull String str, @NonNull MraidCommandFailureListener mraidCommandFailureListener) {
        if (!isStorePictureSupported(context)) {
            MoPubLog.d("Error downloading file - the device does not have an SD card mounted, or the Android permission is not granted.");
            throw new MraidCommandException("Error downloading file  - the device does not have an SD card mounted, or the Android permission is not granted.");
        } else if (context instanceof Activity) {
            showUserDialog(context, str, mraidCommandFailureListener);
        } else {
            Toast.makeText(context, "Downloading image to Picture gallery...", 0).show();
            downloadImage(context, str, mraidCommandFailureListener);
        }
    }

    boolean isTelAvailable(Context context) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return Intents.deviceCanHandleIntent(context, intent);
    }

    boolean isSmsAvailable(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return Intents.deviceCanHandleIntent(context, intent);
    }

    public static boolean isStorePictureSupported(Context context) {
        return "mounted".equals(Environment.getExternalStorageState()) && DeviceUtils.isPermissionGranted(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    static boolean isCalendarAvailable(Context context) {
        return VersionCode.currentApiLevel().isAtLeast(VersionCode.ICE_CREAM_SANDWICH) && Intents.deviceCanHandleIntent(context, new Intent("android.intent.action.INSERT").setType(ANDROID_CALENDAR_CONTENT_TYPE));
    }

    @TargetApi(11)
    boolean isInlineVideoAvailable(@NonNull Activity activity, @NonNull View view) {
        if (VersionCode.currentApiLevel().isBelow(VersionCode.HONEYCOMB_MR1)) {
            return false;
        }
        while (view.isHardwareAccelerated() && !Utils.bitMaskContainsFlag(view.getLayerType(), 1)) {
            if (view.getParent() instanceof View) {
                view = (View) view.getParent();
            } else {
                Window window = activity.getWindow();
                if (window == null || !Utils.bitMaskContainsFlag(window.getAttributes().flags, 16777216)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    @TargetApi(14)
    private Map<String, Object> translateJSParamsToAndroidCalendarEventMapping(Map<String, String> map) {
        Map<String, Object> hashMap = new HashMap();
        if (map.containsKey("description") && map.containsKey("start")) {
            hashMap.put("title", map.get("description"));
            if (!map.containsKey("start") || map.get("start") == null) {
                throw new IllegalArgumentException("Invalid calendar event: start is null.");
            }
            Date parseDate = parseDate((String) map.get("start"));
            if (parseDate != null) {
                hashMap.put("beginTime", Long.valueOf(parseDate.getTime()));
                if (map.containsKey("end") && map.get("end") != null) {
                    parseDate = parseDate((String) map.get("end"));
                    if (parseDate != null) {
                        hashMap.put("endTime", Long.valueOf(parseDate.getTime()));
                    } else {
                        throw new IllegalArgumentException("Invalid calendar event: end time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
                    }
                }
                if (map.containsKey("location")) {
                    hashMap.put("eventLocation", map.get("location"));
                }
                if (map.containsKey("summary")) {
                    hashMap.put("description", map.get("summary"));
                }
                if (map.containsKey("transparency")) {
                    hashMap.put("availability", Integer.valueOf(((String) map.get("transparency")).equals("transparent") ? 1 : 0));
                }
                hashMap.put("rrule", parseRecurrenceRule(map));
                return hashMap;
            }
            throw new IllegalArgumentException("Invalid calendar event: start time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
        }
        throw new IllegalArgumentException("Missing start and description fields");
    }

    private Date parseDate(String str) {
        String[] strArr = DATE_FORMATS;
        int length = strArr.length;
        Date date = null;
        int i = 0;
        while (i < length) {
            try {
                date = new SimpleDateFormat(strArr[i], Locale.US).parse(str);
                if (date != null) {
                    break;
                }
                i++;
            } catch (ParseException e) {
            }
        }
        return date;
    }

    private String parseRecurrenceRule(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        if (map.containsKey("frequency")) {
            int parseInt;
            String str = (String) map.get("frequency");
            if (map.containsKey("interval")) {
                parseInt = Integer.parseInt((String) map.get("interval"));
            } else {
                parseInt = -1;
            }
            if ("daily".equals(str)) {
                stringBuilder.append("FREQ=DAILY;");
                if (parseInt != -1) {
                    stringBuilder.append("INTERVAL=" + parseInt + ";");
                }
            } else if ("weekly".equals(str)) {
                stringBuilder.append("FREQ=WEEKLY;");
                if (parseInt != -1) {
                    stringBuilder.append("INTERVAL=" + parseInt + ";");
                }
                if (map.containsKey("daysInWeek")) {
                    str = translateWeekIntegersToDays((String) map.get("daysInWeek"));
                    if (str == null) {
                        throw new IllegalArgumentException("invalid ");
                    }
                    stringBuilder.append("BYDAY=" + str + ";");
                }
            } else if ("monthly".equals(str)) {
                stringBuilder.append("FREQ=MONTHLY;");
                if (parseInt != -1) {
                    stringBuilder.append("INTERVAL=" + parseInt + ";");
                }
                if (map.containsKey("daysInMonth")) {
                    str = translateMonthIntegersToDays((String) map.get("daysInMonth"));
                    if (str == null) {
                        throw new IllegalArgumentException();
                    }
                    stringBuilder.append("BYMONTHDAY=" + str + ";");
                }
            } else {
                throw new IllegalArgumentException("frequency is only supported for daily, weekly, and monthly.");
            }
        }
        return stringBuilder.toString();
    }

    private String translateWeekIntegersToDays(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean[] zArr = new boolean[7];
        String[] split = str.split(",");
        for (String parseInt : split) {
            int parseInt2 = Integer.parseInt(parseInt);
            if (parseInt2 == 7) {
                parseInt2 = 0;
            }
            if (!zArr[parseInt2]) {
                stringBuilder.append(dayNumberToDayOfWeekString(parseInt2) + ",");
                zArr[parseInt2] = true;
            }
        }
        if (split.length == 0) {
            throw new IllegalArgumentException("must have at least 1 day of the week if specifying repeating weekly");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private String translateMonthIntegersToDays(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean[] zArr = new boolean[63];
        String[] split = str.split(",");
        for (String parseInt : split) {
            int parseInt2 = Integer.parseInt(parseInt);
            if (!zArr[parseInt2 + 31]) {
                stringBuilder.append(dayNumberToDayOfMonthString(parseInt2) + ",");
                zArr[parseInt2 + 31] = true;
            }
        }
        if (split.length == 0) {
            throw new IllegalArgumentException("must have at least 1 day of the month if specifying repeating weekly");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private String dayNumberToDayOfWeekString(int i) {
        switch (i) {
            case 0:
                return "SU";
            case 1:
                return "MO";
            case 2:
                return "TU";
            case 3:
                return "WE";
            case 4:
                return "TH";
            case 5:
                return "FR";
            case 6:
                return "SA";
            default:
                throw new IllegalArgumentException("invalid day of week " + i);
        }
    }

    private String dayNumberToDayOfMonthString(int i) {
        if (i != 0 && i >= -31 && i <= 31) {
            return "" + i;
        }
        throw new IllegalArgumentException("invalid day of month " + i);
    }

    void downloadImage(final Context context, String str, final MraidCommandFailureListener mraidCommandFailureListener) {
        AsyncTasks.safeExecuteOnExecutor(new DownloadImageAsyncTask(context, new MraidNativeCommandHandler$DownloadImageAsyncTask$DownloadImageAsyncTaskListener() {
            public void onSuccess() {
                MoPubLog.d("Image successfully saved.");
            }

            public void onFailure() {
                Toast.makeText(context, "Image failed to download.", 0).show();
                MoPubLog.d("Error downloading and saving image file.");
                mraidCommandFailureListener.onFailure(new MraidCommandException("Error downloading and saving image file."));
            }
        }), str);
    }

    private void showUserDialog(final Context context, final String str, final MraidCommandFailureListener mraidCommandFailureListener) {
        new Builder(context).setTitle("Save Image").setMessage("Download image to Picture gallery?").setNegativeButton("Cancel", null).setPositiveButton("Okay", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MraidNativeCommandHandler.this.downloadImage(context, str, mraidCommandFailureListener);
            }
        }).setCancelable(true).show();
    }
}
