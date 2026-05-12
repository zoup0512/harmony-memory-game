package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.applovin.sdk.AppLovinEventTypes;
import com.mopub.common.Constants;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FileUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;

public class BitmapLoadTask extends AsyncTask<Void, Void, BitmapWorkerResult> {
    private static final String TAG = "BitmapWorkerTask";
    private final BitmapLoadCallback mBitmapLoadCallback;
    private final Context mContext;
    private Uri mInputUri;
    private Uri mOutputUri;
    private final int mRequiredHeight;
    private final int mRequiredWidth;

    public static class BitmapWorkerResult {
        Bitmap mBitmapResult;
        Exception mBitmapWorkerException;
        ExifInfo mExifInfo;

        public BitmapWorkerResult(@NonNull Bitmap bitmapResult, @NonNull ExifInfo exifInfo) {
            this.mBitmapResult = bitmapResult;
            this.mExifInfo = exifInfo;
        }

        public BitmapWorkerResult(@NonNull Exception bitmapWorkerException) {
            this.mBitmapWorkerException = bitmapWorkerException;
        }
    }

    public BitmapLoadTask(@NonNull Context context, @NonNull Uri inputUri, @Nullable Uri outputUri, int requiredWidth, int requiredHeight, BitmapLoadCallback loadCallback) {
        this.mContext = context;
        this.mInputUri = inputUri;
        this.mOutputUri = outputUri;
        this.mRequiredWidth = requiredWidth;
        this.mRequiredHeight = requiredHeight;
        this.mBitmapLoadCallback = loadCallback;
    }

    @NonNull
    protected BitmapWorkerResult doInBackground(Void... params) {
        Exception e;
        if (this.mInputUri == null) {
            return new BitmapWorkerResult(new NullPointerException("Input Uri cannot be null"));
        }
        try {
            processInputUri();
            try {
                ParcelFileDescriptor parcelFileDescriptor = this.mContext.getContentResolver().openFileDescriptor(this.mInputUri, "r");
                if (parcelFileDescriptor == null) {
                    return new BitmapWorkerResult(new NullPointerException("ParcelFileDescriptor was null for given Uri: [" + this.mInputUri + "]"));
                }
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                if (options.outWidth == -1 || options.outHeight == -1) {
                    return new BitmapWorkerResult(new IllegalArgumentException("Bounds for bitmap could not be retrieved from the Uri: [" + this.mInputUri + "]"));
                }
                options.inSampleSize = BitmapLoadUtils.calculateInSampleSize(options, this.mRequiredWidth, this.mRequiredHeight);
                options.inJustDecodeBounds = false;
                Bitmap decodeSampledBitmap = null;
                boolean decodeAttemptSuccess = false;
                while (!decodeAttemptSuccess) {
                    try {
                        decodeSampledBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                        decodeAttemptSuccess = true;
                    } catch (OutOfMemoryError error) {
                        Log.e(TAG, "doInBackground: BitmapFactory.decodeFileDescriptor: ", error);
                        options.inSampleSize *= 2;
                    }
                }
                if (decodeSampledBitmap == null) {
                    return new BitmapWorkerResult(new IllegalArgumentException("Bitmap could not be decoded from the Uri: [" + this.mInputUri + "]"));
                }
                if (VERSION.SDK_INT >= 16) {
                    BitmapLoadUtils.close(parcelFileDescriptor);
                }
                int exifOrientation = BitmapLoadUtils.getExifOrientation(this.mContext, this.mInputUri);
                int exifDegrees = BitmapLoadUtils.exifToDegrees(exifOrientation);
                int exifTranslation = BitmapLoadUtils.exifToTranslation(exifOrientation);
                ExifInfo exifInfo = new ExifInfo(exifOrientation, exifDegrees, exifTranslation);
                Matrix matrix = new Matrix();
                if (exifDegrees != 0) {
                    matrix.preRotate((float) exifDegrees);
                }
                if (exifTranslation != 1) {
                    matrix.postScale((float) exifTranslation, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }
                if (matrix.isIdentity()) {
                    return new BitmapWorkerResult(decodeSampledBitmap, exifInfo);
                }
                return new BitmapWorkerResult(BitmapLoadUtils.transformBitmap(decodeSampledBitmap, matrix), exifInfo);
            } catch (FileNotFoundException e2) {
                return new BitmapWorkerResult(e2);
            }
        } catch (NullPointerException e3) {
            e = e3;
            return new BitmapWorkerResult(e);
        } catch (IOException e4) {
            e = e4;
            return new BitmapWorkerResult(e);
        }
    }

    private void processInputUri() throws NullPointerException, IOException {
        Exception e;
        String inputUriScheme = this.mInputUri.getScheme();
        Log.d(TAG, "Uri scheme: " + inputUriScheme);
        if (Constants.HTTP.equals(inputUriScheme) || Constants.HTTPS.equals(inputUriScheme)) {
            try {
                downloadFile(this.mInputUri, this.mOutputUri);
                return;
            } catch (NullPointerException e2) {
                e = e2;
            } catch (IOException e3) {
                e = e3;
            }
        } else if (AppLovinEventTypes.USER_VIEWED_CONTENT.equals(inputUriScheme)) {
            String path = FileUtils.getPath(this.mContext, this.mInputUri);
            if (TextUtils.isEmpty(path) || !new File(path).exists()) {
                try {
                    copyFile(this.mInputUri, this.mOutputUri);
                    return;
                } catch (NullPointerException e4) {
                    e = e4;
                    Log.e(TAG, "Copying failed", e);
                    throw e;
                } catch (IOException e5) {
                    e = e5;
                    Log.e(TAG, "Copying failed", e);
                    throw e;
                }
            }
            this.mInputUri = Uri.fromFile(new File(path));
            return;
        } else if (!TransferTable.COLUMN_FILE.equals(inputUriScheme)) {
            Log.e(TAG, "Invalid Uri scheme " + inputUriScheme);
            throw new IllegalArgumentException("Invalid Uri scheme" + inputUriScheme);
        } else {
            return;
        }
        Log.e(TAG, "Downloading failed", e);
        throw e;
    }

    private void copyFile(@NonNull Uri inputUri, @Nullable Uri outputUri) throws NullPointerException, IOException {
        Throwable th;
        Log.d(TAG, "copyFile");
        if (outputUri == null) {
            throw new NullPointerException("Output Uri is null - cannot copy image");
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = this.mContext.getContentResolver().openInputStream(inputUri);
            OutputStream outputStream2 = new FileOutputStream(new File(outputUri.getPath()));
            if (inputStream == null) {
                try {
                    throw new NullPointerException("InputStream for given input Uri is null");
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = outputStream2;
                    BitmapLoadUtils.close(outputStream);
                    BitmapLoadUtils.close(inputStream);
                    this.mInputUri = this.mOutputUri;
                    throw th;
                }
            }
            byte[] buffer = new byte[1024];
            while (true) {
                int length = inputStream.read(buffer);
                if (length > 0) {
                    outputStream2.write(buffer, 0, length);
                } else {
                    BitmapLoadUtils.close(outputStream2);
                    BitmapLoadUtils.close(inputStream);
                    this.mInputUri = this.mOutputUri;
                    return;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            BitmapLoadUtils.close(outputStream);
            BitmapLoadUtils.close(inputStream);
            this.mInputUri = this.mOutputUri;
            throw th;
        }
    }

    private void downloadFile(@NonNull Uri inputUri, @Nullable Uri outputUri) throws NullPointerException, IOException {
        Log.d(TAG, "downloadFile");
        if (outputUri == null) {
            throw new NullPointerException("Output Uri is null - cannot download image");
        }
        OkHttpClient client = new OkHttpClient();
        BufferedSource source = null;
        Sink sink = null;
        Response response = null;
        try {
            response = client.newCall(new Builder().url(inputUri.toString()).build()).execute();
            source = response.body().source();
            OutputStream outputStream = this.mContext.getContentResolver().openOutputStream(outputUri);
            if (outputStream != null) {
                sink = Okio.sink(outputStream);
                source.readAll(sink);
                return;
            }
            throw new NullPointerException("OutputStream for given output Uri is null");
        } finally {
            BitmapLoadUtils.close(source);
            BitmapLoadUtils.close(sink);
            if (response != null) {
                BitmapLoadUtils.close(response.body());
            }
            client.dispatcher().cancelAll();
            this.mInputUri = this.mOutputUri;
        }
    }

    protected void onPostExecute(@NonNull BitmapWorkerResult result) {
        if (result.mBitmapWorkerException == null) {
            this.mBitmapLoadCallback.onBitmapLoaded(result.mBitmapResult, result.mExifInfo, this.mInputUri.getPath(), this.mOutputUri == null ? null : this.mOutputUri.getPath());
        } else {
            this.mBitmapLoadCallback.onFailure(result.mBitmapWorkerException);
        }
    }
}
