package com.cube.memorygames.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import com.applovin.sdk.AppLovinEventTypes;
import com.yalantis.ucrop.util.FileUtils;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageHelper {
    private static final int MAX_IMAGE_DIMENSION = 1024;

    public static Intent getTakePictureIntent(File imageFile) {
        Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        takePictureIntent.putExtra("output", Uri.fromFile(imageFile));
        return takePictureIntent;
    }

    public static Intent getChoosePictureIntent() {
        Intent photoPickerIntent = new Intent("android.intent.action.PICK");
        photoPickerIntent.setType(FileUtils.MIME_TYPE_IMAGE);
        return photoPickerIntent;
    }

    public static File createImageFile() throws IOException {
        return File.createTempFile("JPEG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] getCorrectRotatedBitmap(android.content.Context r22, android.net.Uri r23) throws java.io.IOException {
        /*
        r3 = r22.getContentResolver();
        r0 = r23;
        r14 = r3.openInputStream(r0);
        r11 = new android.graphics.BitmapFactory$Options;
        r11.<init>();
        r3 = 1;
        r11.inJustDecodeBounds = r3;
        r3 = 0;
        android.graphics.BitmapFactory.decodeStream(r14, r3, r11);
        r3 = r11.outWidth;
        r4 = -1;
        if (r3 != r4) goto L_0x0022;
    L_0x001b:
        r12 = getRealPath(r22, r23);
        android.graphics.BitmapFactory.decodeFile(r12, r11);
    L_0x0022:
        if (r14 == 0) goto L_0x0027;
    L_0x0024:
        r14.close();
    L_0x0027:
        r17 = getOrientation(r22, r23);
        r3 = 90;
        r0 = r17;
        if (r0 == r3) goto L_0x0037;
    L_0x0031:
        r3 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        r0 = r17;
        if (r0 != r3) goto L_0x00c7;
    L_0x0037:
        r0 = r11.outHeight;
        r19 = r0;
        r0 = r11.outWidth;
        r18 = r0;
    L_0x003f:
        r3 = r22.getContentResolver();
        r0 = r23;
        r14 = r3.openInputStream(r0);
        r3 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = r19;
        if (r0 > r3) goto L_0x0055;
    L_0x004f:
        r3 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = r18;
        if (r0 <= r3) goto L_0x00d1;
    L_0x0055:
        r0 = r19;
        r3 = (float) r0;
        r4 = 1149239296; // 0x44800000 float:1024.0 double:5.67799655E-315;
        r21 = r3 / r4;
        r0 = r18;
        r3 = (float) r0;
        r4 = 1149239296; // 0x44800000 float:1024.0 double:5.67799655E-315;
        r13 = r3 / r4;
        r0 = r21;
        r15 = java.lang.Math.max(r0, r13);
        r16 = new android.graphics.BitmapFactory$Options;
        r16.<init>();
        r3 = (int) r15;
        r0 = r16;
        r0.inSampleSize = r3;
        r3 = 0;
        r0 = r16;
        r2 = android.graphics.BitmapFactory.decodeStream(r14, r3, r0);
    L_0x007a:
        if (r14 == 0) goto L_0x007f;
    L_0x007c:
        r14.close();
    L_0x007f:
        if (r17 <= 0) goto L_0x009b;
    L_0x0081:
        r7 = new android.graphics.Matrix;
        r7.<init>();
        r0 = r17;
        r3 = (float) r0;
        r7.postRotate(r3);
        r3 = 0;
        r4 = 0;
        r5 = r2.getWidth();
        r6 = r2.getHeight();
        r8 = 1;
        r2 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8);
    L_0x009b:
        r0 = r23;
        r1 = r22;
        r20 = getMimeType(r0, r1);
        r10 = new java.io.ByteArrayOutputStream;
        r10.<init>();
        r3 = android.text.TextUtils.isEmpty(r20);
        if (r3 != 0) goto L_0x00bf;
    L_0x00ae:
        r3 = "image/png";
        r0 = r20;
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x00d6;
    L_0x00b8:
        r3 = android.graphics.Bitmap.CompressFormat.PNG;
        r4 = 100;
        r2.compress(r3, r4, r10);
    L_0x00bf:
        r9 = r10.toByteArray();
        r10.close();
        return r9;
    L_0x00c7:
        r0 = r11.outWidth;
        r19 = r0;
        r0 = r11.outHeight;
        r18 = r0;
        goto L_0x003f;
    L_0x00d1:
        r2 = android.graphics.BitmapFactory.decodeStream(r14);
        goto L_0x007a;
    L_0x00d6:
        r3 = "image/jpg";
        r0 = r20;
        r3 = r0.equals(r3);
        if (r3 != 0) goto L_0x00ea;
    L_0x00e0:
        r3 = "image/jpeg";
        r0 = r20;
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x00bf;
    L_0x00ea:
        r3 = android.graphics.Bitmap.CompressFormat.JPEG;
        r4 = 100;
        r2.compress(r3, r4, r10);
        goto L_0x00bf;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cube.memorygames.utils.ImageHelper.getCorrectRotatedBitmap(android.content.Context, android.net.Uri):byte[]");
    }

    public static String getMimeType(Uri uri, Context context) {
        if (uri.getScheme().equals(AppLovinEventTypes.USER_VIEWED_CONTENT)) {
            return context.getContentResolver().getType(uri);
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase());
    }

    private static int getOrientation(Context context, Uri photoUri) throws IOException {
        int orientation = Integer.parseInt(new ExifInterface(getRealPath(context, photoUri)).getAttribute("Orientation"));
        if (orientation == 6) {
            return 90;
        }
        if (orientation == 3) {
            return 180;
        }
        if (orientation == 8) {
            return 270;
        }
        return 0;
    }

    public static String getRealPath(Context context, Uri uri) {
        Uri filePathUri = uri;
        if (uri.getScheme().equals(AppLovinEventTypes.USER_VIEWED_CONTENT)) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }
            return Uri.parse(cursor.getString(cursor.getColumnIndexOrThrow("_data"))).getPath();
        } else if (uri.getScheme().equals(TransferTable.COLUMN_FILE)) {
            return filePathUri.getPath();
        } else {
            return null;
        }
    }
}
