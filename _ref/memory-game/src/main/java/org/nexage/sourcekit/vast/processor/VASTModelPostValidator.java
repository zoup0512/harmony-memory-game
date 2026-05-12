package org.nexage.sourcekit.vast.processor;

import android.text.TextUtils;
import java.util.List;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.model.VASTMediaFile;
import org.nexage.sourcekit.vast.model.VASTModel;

public class VASTModelPostValidator {
    private static final String TAG = "VASTModelPostValidator";

    public static boolean validate(VASTModel vASTModel, VASTMediaPicker vASTMediaPicker) {
        boolean z = false;
        VASTLog.d(TAG, "validate");
        if (validateModel(vASTModel)) {
            String str;
            if (vASTMediaPicker != null) {
                List mediaFiles = vASTModel.getMediaFiles();
                if (mediaFiles.isEmpty() && vASTModel != null) {
                    try {
                        vASTModel.sendError(VASTModel.ERROR_CODE_NO_FILE);
                    } catch (Exception e) {
                        VASTLog.e(TAG, e.getMessage());
                    }
                }
                VASTMediaFile pickVideo = vASTMediaPicker.pickVideo(mediaFiles);
                if (pickVideo != null) {
                    Object value = pickVideo.getValue();
                    if (!TextUtils.isEmpty(value)) {
                        z = true;
                        vASTModel.setPickedMediaFile(pickVideo);
                        VASTLog.d(TAG, "mediaPicker selected mediaFile with URL " + value);
                    }
                } else if (vASTModel != null) {
                    try {
                        vASTModel.sendError(403);
                    } catch (Exception e2) {
                        VASTLog.e(TAG, e2.getMessage());
                    }
                }
            } else {
                VASTLog.w(TAG, "mediaPicker: We don't have a compatible media file to play.");
            }
            String str2 = TAG;
            StringBuilder append = new StringBuilder().append("Validator returns: ");
            if (z) {
                str = "valid";
            } else {
                str = "not valid (no media file)";
            }
            VASTLog.d(str2, append.append(str).toString());
        } else {
            VASTLog.d(TAG, "Validator returns: not valid (invalid model)");
        }
        return z;
    }

    private static boolean validateModel(VASTModel vASTModel) {
        VASTLog.d(TAG, "validateModel");
        List mediaFiles = vASTModel.getMediaFiles();
        if (mediaFiles != null && mediaFiles.size() != 0) {
            return true;
        }
        VASTLog.d(TAG, "Validator error: mediaFile list invalid");
        if (vASTModel != null) {
            try {
                vASTModel.sendError(VASTModel.ERROR_CODE_NO_FILE);
            } catch (Exception e) {
                VASTLog.e(TAG, e.getMessage());
            }
        }
        return false;
    }
}
