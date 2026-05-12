package com.yalantis.ucrop.view;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;

class TransformImageView$1 implements BitmapLoadCallback {
    final /* synthetic */ TransformImageView this$0;

    TransformImageView$1(TransformImageView this$0) {
        this.this$0 = this$0;
    }

    public void onBitmapLoaded(@NonNull Bitmap bitmap, @NonNull ExifInfo exifInfo, @NonNull String imageInputPath, @Nullable String imageOutputPath) {
        TransformImageView.access$002(this.this$0, imageInputPath);
        TransformImageView.access$102(this.this$0, imageOutputPath);
        TransformImageView.access$202(this.this$0, exifInfo);
        this.this$0.mBitmapDecoded = true;
        this.this$0.setImageBitmap(bitmap);
    }

    public void onFailure(@NonNull Exception bitmapWorkerException) {
        Log.e("TransformImageView", "onFailure: setImageUri", bitmapWorkerException);
        if (this.this$0.mTransformImageListener != null) {
            this.this$0.mTransformImageListener.onLoadFailure(bitmapWorkerException);
        }
    }
}
