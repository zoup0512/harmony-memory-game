package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.util.DateAndTime;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdAlertReporter {
    private static final String BODY_SEPARATOR = "\n=================\n";
    private static final String DATE_FORMAT_PATTERN = "M/d/yy hh:mm:ss a z";
    private static final String EMAIL_RECIPIENT = "creative-review@mopub.com";
    private static final String EMAIL_SCHEME = "mailto:";
    private static final int IMAGE_QUALITY = 25;
    private static final String MARKUP_FILENAME = "mp_adalert_markup.html";
    private static final String PARAMETERS_FILENAME = "mp_adalert_parameters.txt";
    private static final String SCREEN_SHOT_FILENAME = "mp_adalert_screenshot.png";
    private final Context mContext;
    private final String mDateString = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.US).format(DateAndTime.now());
    private ArrayList<Uri> mEmailAttachments = new ArrayList();
    private Intent mEmailIntent;
    private String mParameters;
    private String mResponse;
    private final View mView;

    public AdAlertReporter(Context context, View view, @Nullable AdReport adReport) {
        this.mView = view;
        this.mContext = context;
        initEmailIntent();
        Bitmap takeScreenShot = takeScreenShot();
        String convertBitmapInWEBPToBase64EncodedString = convertBitmapInWEBPToBase64EncodedString(takeScreenShot);
        this.mParameters = "";
        this.mResponse = "";
        if (adReport != null) {
            this.mParameters = adReport.toString();
            this.mResponse = adReport.getResponseString();
        }
        addEmailSubject();
        addEmailBody(this.mParameters, this.mResponse, convertBitmapInWEBPToBase64EncodedString);
        addTextAttachment(PARAMETERS_FILENAME, this.mParameters);
        addTextAttachment(MARKUP_FILENAME, this.mResponse);
        addImageAttachment(SCREEN_SHOT_FILENAME, takeScreenShot);
    }

    public void send() {
        this.mEmailIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.mEmailAttachments);
        Intent createChooser = Intent.createChooser(this.mEmailIntent, "Send Email...");
        createChooser.addFlags(268435456);
        this.mContext.startActivity(createChooser);
    }

    private void initEmailIntent() {
        Uri parse = Uri.parse(EMAIL_SCHEME);
        this.mEmailIntent = new Intent("android.intent.action.SEND_MULTIPLE");
        this.mEmailIntent.setDataAndType(parse, "plain/text");
        this.mEmailIntent.putExtra("android.intent.extra.EMAIL", new String[]{EMAIL_RECIPIENT});
    }

    private Bitmap takeScreenShot() {
        if (this.mView == null || this.mView.getRootView() == null) {
            return null;
        }
        View rootView = this.mView.getRootView();
        boolean isDrawingCacheEnabled = rootView.isDrawingCacheEnabled();
        rootView.setDrawingCacheEnabled(true);
        Bitmap drawingCache = rootView.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawingCache);
        rootView.setDrawingCacheEnabled(isDrawingCacheEnabled);
        return createBitmap;
    }

    private String convertBitmapInWEBPToBase64EncodedString(Bitmap bitmap) {
        String str = null;
        if (bitmap != null) {
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 25, byteArrayOutputStream);
                str = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
            } catch (Exception e) {
            }
        }
        return str;
    }

    private void addEmailSubject() {
        this.mEmailIntent.putExtra("android.intent.extra.SUBJECT", "New creative violation report - " + this.mDateString);
    }

    private void addEmailBody(String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            stringBuilder.append(strArr[i]);
            if (i != strArr.length - 1) {
                stringBuilder.append(BODY_SEPARATOR);
            }
        }
        this.mEmailIntent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addImageAttachment(java.lang.String r6, android.graphics.Bitmap r7) {
        /*
        r5 = this;
        r0 = 0;
        if (r6 == 0) goto L_0x0005;
    L_0x0003:
        if (r7 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r1 = r5.mContext;	 Catch:{ Exception -> 0x0043, all -> 0x005e }
        r2 = 1;
        r0 = r1.openFileOutput(r6, r2);	 Catch:{ Exception -> 0x0043, all -> 0x005e }
        r1 = android.graphics.Bitmap.CompressFormat.PNG;	 Catch:{ Exception -> 0x0043 }
        r2 = 25;
        r7.compress(r1, r2, r0);	 Catch:{ Exception -> 0x0043 }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0043 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043 }
        r2.<init>();	 Catch:{ Exception -> 0x0043 }
        r3 = r5.mContext;	 Catch:{ Exception -> 0x0043 }
        r3 = r3.getFilesDir();	 Catch:{ Exception -> 0x0043 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0043 }
        r3 = java.io.File.separator;	 Catch:{ Exception -> 0x0043 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0043 }
        r2 = r2.append(r6);	 Catch:{ Exception -> 0x0043 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0043 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0043 }
        r1 = android.net.Uri.fromFile(r1);	 Catch:{ Exception -> 0x0043 }
        r2 = r5.mEmailAttachments;	 Catch:{ Exception -> 0x0043 }
        r2.add(r1);	 Catch:{ Exception -> 0x0043 }
        com.mopub.common.util.Streams.closeStream(r0);
        goto L_0x0005;
    L_0x0043:
        r1 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0066 }
        r1.<init>();	 Catch:{ all -> 0x0066 }
        r2 = "Unable to write text attachment to file: ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0066 }
        r1 = r1.append(r6);	 Catch:{ all -> 0x0066 }
        r1 = r1.toString();	 Catch:{ all -> 0x0066 }
        com.mopub.common.logging.MoPubLog.d(r1);	 Catch:{ all -> 0x0066 }
        com.mopub.common.util.Streams.closeStream(r0);
        goto L_0x0005;
    L_0x005e:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0062:
        com.mopub.common.util.Streams.closeStream(r1);
        throw r0;
    L_0x0066:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0062;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.AdAlertReporter.addImageAttachment(java.lang.String, android.graphics.Bitmap):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addTextAttachment(java.lang.String r6, java.lang.String r7) {
        /*
        r5 = this;
        r0 = 0;
        if (r6 == 0) goto L_0x0005;
    L_0x0003:
        if (r7 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r1 = r5.mContext;	 Catch:{ Exception -> 0x0043, all -> 0x005e }
        r2 = 1;
        r0 = r1.openFileOutput(r6, r2);	 Catch:{ Exception -> 0x0043, all -> 0x005e }
        r1 = r7.getBytes();	 Catch:{ Exception -> 0x0043 }
        r0.write(r1);	 Catch:{ Exception -> 0x0043 }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0043 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043 }
        r2.<init>();	 Catch:{ Exception -> 0x0043 }
        r3 = r5.mContext;	 Catch:{ Exception -> 0x0043 }
        r3 = r3.getFilesDir();	 Catch:{ Exception -> 0x0043 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0043 }
        r3 = java.io.File.separator;	 Catch:{ Exception -> 0x0043 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0043 }
        r2 = r2.append(r6);	 Catch:{ Exception -> 0x0043 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0043 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0043 }
        r1 = android.net.Uri.fromFile(r1);	 Catch:{ Exception -> 0x0043 }
        r2 = r5.mEmailAttachments;	 Catch:{ Exception -> 0x0043 }
        r2.add(r1);	 Catch:{ Exception -> 0x0043 }
        com.mopub.common.util.Streams.closeStream(r0);
        goto L_0x0005;
    L_0x0043:
        r1 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0066 }
        r1.<init>();	 Catch:{ all -> 0x0066 }
        r2 = "Unable to write text attachment to file: ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0066 }
        r1 = r1.append(r6);	 Catch:{ all -> 0x0066 }
        r1 = r1.toString();	 Catch:{ all -> 0x0066 }
        com.mopub.common.logging.MoPubLog.d(r1);	 Catch:{ all -> 0x0066 }
        com.mopub.common.util.Streams.closeStream(r0);
        goto L_0x0005;
    L_0x005e:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0062:
        com.mopub.common.util.Streams.closeStream(r1);
        throw r0;
    L_0x0066:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0062;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.AdAlertReporter.addTextAttachment(java.lang.String, java.lang.String):void");
    }

    @Deprecated
    Intent getEmailIntent() {
        return this.mEmailIntent;
    }

    @Deprecated
    ArrayList<Uri> getEmailAttachments() {
        return this.mEmailAttachments;
    }

    @Deprecated
    String getParameters() {
        return this.mParameters;
    }

    @Deprecated
    String getResponse() {
        return this.mResponse;
    }
}
