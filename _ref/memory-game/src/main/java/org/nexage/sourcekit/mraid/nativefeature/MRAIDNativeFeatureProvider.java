package org.nexage.sourcekit.mraid.nativefeature;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import com.mopub.mraid.MraidNativeCommandHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDBrowser;
import org.nexage.sourcekit.mraid.internal.MRAIDLog;
import org.nexage.sourcekit.mraid.internal.MRAIDNativeFeatureManager;

public class MRAIDNativeFeatureProvider {
    private static final String TAG = "MRAIDNativeFeatureProvider";
    private final Context context;
    private final MRAIDNativeFeatureManager nativeFeatureManager;

    public MRAIDNativeFeatureProvider(Context context, MRAIDNativeFeatureManager mRAIDNativeFeatureManager) {
        this.context = context;
        this.nativeFeatureManager = mRAIDNativeFeatureManager;
    }

    public final void callTel(String str) {
        if (this.nativeFeatureManager.isTelSupported()) {
            this.context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(str)));
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    @TargetApi(14)
    public void createCalendarEvent(String str) {
        if (this.nativeFeatureManager.isCalendarSupported()) {
            try {
                JSONObject jSONObject = new JSONObject(str.replace("\\", "").replace("\"{", "{").replace("}\"", "}"));
                String optString = jSONObject.optString("description", "Untitled");
                String optString2 = jSONObject.optString("location", "unknown");
                String optString3 = jSONObject.optString("summary");
                String[] strArr = new String[]{"yyyy-MM-dd'T'HH:mmZ", "yyyy-MM-dd'T'HH:mm:ssZ"};
                String[] strArr2 = new String[]{jSONObject.getString("start"), jSONObject.optString("end")};
                long j = 0;
                long j2 = 0;
                for (int i = 0; i < strArr2.length; i++) {
                    if (!TextUtils.isEmpty(strArr2[i])) {
                        strArr2[i] = strArr2[i].replaceAll("([+-]\\d\\d):(\\d\\d)$", "$1$2");
                        int i2 = 0;
                        while (i2 < strArr.length) {
                            try {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strArr[i2]);
                                if (i == 0) {
                                    j2 = simpleDateFormat.parse(strArr2[i]).getTime();
                                } else {
                                    j = simpleDateFormat.parse(strArr2[i]).getTime();
                                }
                            } catch (ParseException e) {
                                i2++;
                            }
                        }
                    }
                }
                Intent type = new Intent("android.intent.action.INSERT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE);
                type.putExtra("title", optString);
                type.putExtra("description", optString3);
                type.putExtra("eventLocation", optString2);
                if (j2 > 0) {
                    type.putExtra("beginTime", j2);
                }
                if (j > 0) {
                    type.putExtra("endTime", j);
                }
                this.context.startActivity(type);
            } catch (JSONException e2) {
                MRAIDLog.e(TAG, "Error parsing JSON: " + e2.getLocalizedMessage());
            }
        }
    }

    public void playVideo(String str) {
        this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public void openBrowser(String str) {
        if (str.startsWith("market:")) {
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } else if (str.startsWith("http:") || str.startsWith("https:")) {
            Intent intent = new Intent(this.context, MRAIDBrowser.class);
            intent.putExtra(MRAIDBrowser.URL_EXTRA, str);
            intent.putExtra(MRAIDBrowser.MANAGER_EXTRA, this.nativeFeatureManager.getSupportedNativeFeatures());
            intent.addFlags(268435456);
            this.context.startActivity(intent);
        }
    }

    public void storePicture(final String str) {
        if (this.nativeFeatureManager.isStorePictureSupported()) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        MRAIDNativeFeatureProvider.this.storePictureInGallery(str);
                    } catch (Exception e) {
                        MRAIDLog.e(MRAIDNativeFeatureProvider.TAG, e.getLocalizedMessage());
                    }
                }
            }).start();
        }
    }

    public void sendSms(String str) {
        if (this.nativeFeatureManager.isSmsSupported()) {
            this.context.startActivity(new Intent("android.intent.action.SENDTO", Uri.parse(str)));
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    private void storePictureInGallery(String str) {
        String str2 = getAlbumDir() + "/img" + new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date()) + ".png";
        MRAIDLog.i(TAG, "Saving image into: " + str2);
        try {
            copyStream(new URL(str).openStream(), new FileOutputStream(new File(str2)));
            MediaScannerConnection.scanFile(this.context, new String[]{r1.getAbsolutePath()}, null, new OnScanCompletedListener() {
                public void onScanCompleted(String str, Uri uri) {
                    MRAIDLog.d("File saves successfully to " + str);
                }
            });
            MRAIDLog.i(TAG, "Saved image successfully");
        } catch (MalformedURLException e) {
            MRAIDLog.e(TAG, "Not able to save image due to invalid URL: " + e.getLocalizedMessage());
        } catch (IOException e2) {
            MRAIDLog.e(TAG, "Unable to save image: " + e2.getLocalizedMessage());
        }
    }

    private void copyStream(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            MRAIDLog.i(TAG, "Error saving picture: " + e.getLocalizedMessage());
        }
    }

    private File getAlbumDir() {
        File file;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "NexageAd");
            if (!(file.mkdirs() || file.exists())) {
                MRAIDLog.i(TAG, "Failed to create camera directory");
                return null;
            }
        }
        MRAIDLog.i(TAG, "External storage is not mounted READ/WRITE.");
        file = null;
        return file;
    }
}
