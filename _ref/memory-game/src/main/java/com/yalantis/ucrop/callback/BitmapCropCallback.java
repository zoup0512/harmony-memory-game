package com.yalantis.ucrop.callback;

import android.net.Uri;
import android.support.annotation.NonNull;

public interface BitmapCropCallback {
    void onBitmapCropped(@NonNull Uri uri);

    void onCropFailure(@NonNull Throwable th);
}
