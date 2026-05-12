package org.nexage.sourcekit.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.amazon.device.ads.WebRequest;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.nexage.sourcekit.vast.model.VASTMediaFile;
import org.nexage.sourcekit.vast.processor.VASTMediaPicker;

public class DefaultMediaPicker implements VASTMediaPicker {
    private static final String TAG = "DefaultMediaPicker";
    private static final int maxPixels = 5000;
    String SUPPORTED_VIDEO_TYPE_REGEX = "video/.*(?i)(mp4|3gpp|mp2t|webm|matroska)";
    private Context context;
    private int deviceArea;
    private int deviceHeight;
    private int deviceWidth;

    private class AreaComparator implements Comparator<VASTMediaFile> {
        private AreaComparator() {
        }

        public int compare(VASTMediaFile vASTMediaFile, VASTMediaFile vASTMediaFile2) {
            int intValue = vASTMediaFile2.getWidth().intValue() * vASTMediaFile2.getHeight().intValue();
            int abs = Math.abs((vASTMediaFile.getWidth().intValue() * vASTMediaFile.getHeight().intValue()) - DefaultMediaPicker.this.deviceArea);
            intValue = Math.abs(intValue - DefaultMediaPicker.this.deviceArea);
            VASTLog.v(DefaultMediaPicker.TAG, "AreaComparator: obj1:" + abs + " obj2:" + intValue);
            if (abs < intValue) {
                return -1;
            }
            if (abs > intValue) {
                return 1;
            }
            return 0;
        }
    }

    public DefaultMediaPicker(Context context) {
        this.context = context;
        setDeviceWidthHeight();
    }

    public DefaultMediaPicker(int i, int i2) {
        setDeviceWidthHeight(i, i2);
    }

    public VASTMediaFile pickVideo(List<VASTMediaFile> list) {
        if (list == null || prefilterMediaFiles(list) == 0) {
            return null;
        }
        Collections.sort(list, new AreaComparator());
        return getBestMatch(list);
    }

    private int prefilterMediaFiles(List<VASTMediaFile> list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            VASTMediaFile vASTMediaFile = (VASTMediaFile) it.next();
            if (TextUtils.isEmpty(vASTMediaFile.getType())) {
                VASTLog.d(TAG, "Validator error: mediaFile type empty");
                it.remove();
            } else {
                BigInteger height = vASTMediaFile.getHeight();
                if (height == null) {
                    VASTLog.d(TAG, "Validator error: mediaFile height null");
                    it.remove();
                } else {
                    int intValue = height.intValue();
                    if (intValue <= 0 || intValue >= maxPixels) {
                        VASTLog.d(TAG, "Validator error: mediaFile height invalid: " + intValue);
                        it.remove();
                    } else {
                        height = vASTMediaFile.getWidth();
                        if (height == null) {
                            VASTLog.d(TAG, "Validator error: mediaFile width null");
                            it.remove();
                        } else {
                            intValue = height.intValue();
                            if (intValue <= 0 || intValue >= maxPixels) {
                                VASTLog.d(TAG, "Validator error: mediaFile width invalid: " + intValue);
                                it.remove();
                            } else if (TextUtils.isEmpty(vASTMediaFile.getValue())) {
                                VASTLog.d(TAG, "Validator error: mediaFile url empty");
                                it.remove();
                            }
                        }
                    }
                }
            }
        }
        return list.size();
    }

    private void setDeviceWidthHeight() {
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        this.deviceWidth = displayMetrics.widthPixels;
        this.deviceHeight = displayMetrics.heightPixels;
        this.deviceArea = this.deviceWidth * this.deviceHeight;
    }

    private void setDeviceWidthHeight(int i, int i2) {
        this.deviceWidth = i;
        this.deviceHeight = i2;
        this.deviceArea = this.deviceWidth * this.deviceHeight;
    }

    private boolean isMediaFileCompatible(VASTMediaFile vASTMediaFile) {
        return vASTMediaFile.getType().matches(this.SUPPORTED_VIDEO_TYPE_REGEX);
    }

    private boolean isVPAIDMediaFileCompatible(VASTMediaFile vASTMediaFile) {
        return vASTMediaFile.getType().equals(WebRequest.CONTENT_TYPE_JAVASCRIPT) && vASTMediaFile.getApiFramework().equals("VPAID");
    }

    private VASTMediaFile getBestMatch(List<VASTMediaFile> list) {
        VASTLog.d(TAG, "getBestMatch");
        for (VASTMediaFile vASTMediaFile : list) {
            if (isMediaFileCompatible(vASTMediaFile)) {
                return vASTMediaFile;
            }
        }
        if (VERSION.SDK_INT >= 17) {
            for (VASTMediaFile vASTMediaFile2 : list) {
                if (isVPAIDMediaFileCompatible(vASTMediaFile2)) {
                    return vASTMediaFile2;
                }
            }
        }
        return null;
    }
}
