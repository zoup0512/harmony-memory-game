package com.cube.memorygames.s3;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transfermanager.TransferManager;
import com.amazonaws.mobileconnectors.s3.transfermanager.Upload;
import com.amazonaws.mobileconnectors.s3.transfermanager.model.UploadResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class AmazonS3Wrapper {
    private static final String AMAZON_S3_URL = "s3.amazonaws.com";
    public static final String S3_ACCESS_KEY = "AKIAID7OFMYLWQWRSVJA";
    private static final String S3_AVATARS_FOLDER = "user-avatars";
    public static final String S3_BUCKET = "memorygames";
    public static final String S3_SECRET_KEY = "/+77V7atQZkEdwuAfVQeW1qo+uGdpQxayCcm/5nr";
    private static final String TAG = "AmazonS3Wrapper";
    private Context mContext;
    private TransferManager mTransferManager = new TransferManager(new BasicAWSCredentials(S3_ACCESS_KEY, S3_SECRET_KEY));

    public interface Callback {
        void uploadFinished(Image image);
    }

    public interface GoodCallback {
        void uploadFinished(UploadResult uploadResult);
    }

    public AmazonS3Wrapper(Context context) {
        this.mContext = context;
    }

    public void upload(Uri imageUri, Callback callback) {
        Log.i(TAG, "Gallery - " + imageUri);
        try {
            upload(this.mContext.getContentResolver().openInputStream(imageUri), getFileNameFromURI(imageUri), callback);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found during uploading file from gallery", e);
            callback.uploadFinished(null);
        }
    }

    public void upload(String photoUrl, final String photoId, final Callback callback) {
        Log.i(TAG, "Facebook - " + photoUrl);
        try {
            final URL url = new URL(photoUrl);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        AmazonS3Wrapper.this.upload(url.openStream(), photoId, callback);
                    } catch (IOException e) {
                        Log.i(AmazonS3Wrapper.TAG, "Error during uploading file from facebook", e);
                        callback.uploadFinished(null);
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            Log.i(TAG, "Wrong URL during uploading file from facebook", e);
            callback.uploadFinished(null);
        }
    }

    public void upload(InputStream inputStream, String fileName, Callback callback) {
        String url = "https://memorygames.s3.amazonaws.com/" + (UUID.randomUUID().toString() + "-" + fileName);
    }

    public UploadResult upload(InputStream inputStream, String fileName) {
        Upload upload = this.mTransferManager.upload(S3_BUCKET, UUID.randomUUID().toString() + "-" + fileName, inputStream, null);
        Log.i(TAG, "Start uploading - " + fileName);
        UploadResult uploadResult = null;
        try {
            uploadResult = upload.waitForUploadResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("BUG_DEBUG", "Finish uploading = " + uploadResult.toString());
        return uploadResult;
    }

    private String getFileNameFromURI(Uri contentUri) {
        Cursor cursor = new CursorLoader(this.mContext, contentUri, new String[]{"_data"}, null, null, null).loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow("_data");
        cursor.moveToFirst();
        return new File(cursor.getString(column_index)).getName();
    }
}
