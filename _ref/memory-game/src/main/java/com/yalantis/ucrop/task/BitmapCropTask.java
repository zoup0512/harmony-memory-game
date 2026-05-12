package com.yalantis.ucrop.task;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.model.CropParameters;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.model.ImageState;
import com.yalantis.ucrop.util.FileUtils;
import com.yalantis.ucrop.util.ImageHeaderParser;
import java.io.File;
import java.io.IOException;

public class BitmapCropTask extends AsyncTask<Void, Void, Throwable> {
    private static final String TAG = "BitmapCropTask";
    private final CompressFormat mCompressFormat;
    private final int mCompressQuality;
    private final BitmapCropCallback mCropCallback;
    private final RectF mCropRect;
    private float mCurrentAngle;
    private final RectF mCurrentImageRect;
    private float mCurrentScale;
    private final ExifInfo mExifInfo;
    private final String mImageInputPath;
    private final String mImageOutputPath;
    private final int mMaxResultImageSizeX;
    private final int mMaxResultImageSizeY;
    private Bitmap mViewBitmap;

    public native boolean cropCImg(String str, String str2, int i, int i2, int i3, int i4, float f, float f2, int i5, int i6, int i7, int i8) throws IOException, OutOfMemoryError;

    static {
        System.loadLibrary("ucrop");
    }

    public BitmapCropTask(@Nullable Bitmap viewBitmap, @NonNull ImageState imageState, @NonNull CropParameters cropParameters, @Nullable BitmapCropCallback cropCallback) {
        this.mViewBitmap = viewBitmap;
        this.mCropRect = imageState.getCropRect();
        this.mCurrentImageRect = imageState.getCurrentImageRect();
        this.mCurrentScale = imageState.getCurrentScale();
        this.mCurrentAngle = imageState.getCurrentAngle();
        this.mMaxResultImageSizeX = cropParameters.getMaxResultImageSizeX();
        this.mMaxResultImageSizeY = cropParameters.getMaxResultImageSizeY();
        this.mCompressFormat = cropParameters.getCompressFormat();
        this.mCompressQuality = cropParameters.getCompressQuality();
        this.mImageInputPath = cropParameters.getImageInputPath();
        this.mImageOutputPath = cropParameters.getImageOutputPath();
        this.mExifInfo = cropParameters.getExifInfo();
        this.mCropCallback = cropCallback;
    }

    @Nullable
    protected Throwable doInBackground(Void... params) {
        if (this.mViewBitmap == null) {
            return new NullPointerException("ViewBitmap is null");
        }
        if (this.mViewBitmap.isRecycled()) {
            return new NullPointerException("ViewBitmap is recycled");
        }
        if (this.mCurrentImageRect.isEmpty()) {
            return new NullPointerException("CurrentImageRect is empty");
        }
        try {
            crop(resize());
            this.mViewBitmap = null;
            return null;
        } catch (Throwable throwable) {
            return throwable;
        }
    }

    private float resize() {
        boolean swapSides = true;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.mImageInputPath, options);
        if (!(this.mExifInfo.getExifDegrees() == 90 || this.mExifInfo.getExifDegrees() == 270)) {
            swapSides = false;
        }
        this.mCurrentScale /= Math.min(((float) (swapSides ? options.outHeight : options.outWidth)) / ((float) this.mViewBitmap.getWidth()), ((float) (swapSides ? options.outWidth : options.outHeight)) / ((float) this.mViewBitmap.getHeight()));
        if (this.mMaxResultImageSizeX <= 0 || this.mMaxResultImageSizeY <= 0) {
            return DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        float cropWidth = this.mCropRect.width() / this.mCurrentScale;
        float cropHeight = this.mCropRect.height() / this.mCurrentScale;
        if (cropWidth <= ((float) this.mMaxResultImageSizeX) && cropHeight <= ((float) this.mMaxResultImageSizeY)) {
            return DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        float resizeScale = Math.min(((float) this.mMaxResultImageSizeX) / cropWidth, ((float) this.mMaxResultImageSizeY) / cropHeight);
        this.mCurrentScale /= resizeScale;
        return resizeScale;
    }

    private boolean crop(float resizeScale) throws IOException {
        ExifInterface originalExif = new ExifInterface(this.mImageInputPath);
        int top = Math.round((this.mCropRect.top - this.mCurrentImageRect.top) / this.mCurrentScale);
        int left = Math.round((this.mCropRect.left - this.mCurrentImageRect.left) / this.mCurrentScale);
        int width = Math.round(this.mCropRect.width() / this.mCurrentScale);
        int height = Math.round(this.mCropRect.height() / this.mCurrentScale);
        boolean shouldCrop = shouldCrop(width, height);
        Log.i(TAG, "Should crop: " + shouldCrop);
        if (shouldCrop) {
            boolean cropped = cropCImg(this.mImageInputPath, this.mImageOutputPath, left, top, width, height, this.mCurrentAngle, resizeScale, this.mCompressFormat.ordinal(), this.mCompressQuality, this.mExifInfo.getExifDegrees(), this.mExifInfo.getExifTranslation());
            if (!cropped) {
                return cropped;
            }
            ImageHeaderParser.copyExif(originalExif, width, height, this.mImageOutputPath);
            return cropped;
        }
        FileUtils.copyFile(this.mImageInputPath, this.mImageOutputPath);
        return false;
    }

    private boolean shouldCrop(int width, int height) {
        int pixelError = 1 + Math.round(((float) Math.max(width, height)) / 1000.0f);
        return (this.mMaxResultImageSizeX > 0 && this.mMaxResultImageSizeY > 0) || Math.abs(this.mCropRect.left - this.mCurrentImageRect.left) > ((float) pixelError) || Math.abs(this.mCropRect.top - this.mCurrentImageRect.top) > ((float) pixelError) || Math.abs(this.mCropRect.bottom - this.mCurrentImageRect.bottom) > ((float) pixelError) || Math.abs(this.mCropRect.right - this.mCurrentImageRect.right) > ((float) pixelError);
    }

    protected void onPostExecute(@Nullable Throwable t) {
        if (this.mCropCallback == null) {
            return;
        }
        if (t == null) {
            this.mCropCallback.onBitmapCropped(Uri.fromFile(new File(this.mImageOutputPath)));
        } else {
            this.mCropCallback.onCropFailure(t);
        }
    }
}
