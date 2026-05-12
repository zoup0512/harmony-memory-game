package org.nexage.sourcekit.vast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.amazon.device.ads.WebRequest;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ao.b;
import com.appodeal.ads.utils.Log.LogLevel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;
import org.nexage.sourcekit.util.DefaultMediaPicker;
import org.nexage.sourcekit.util.NetworkTools;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.util.VASTLog.LOG_LEVEL;
import org.nexage.sourcekit.vast.activity.VASTActivity;
import org.nexage.sourcekit.vast.activity.VPAIDActivity;
import org.nexage.sourcekit.vast.model.VASTModel;
import org.nexage.sourcekit.vast.processor.VASTProcessor;

public class VASTPlayer {
    public static final int ERROR_CACHE = 8;
    public static final int ERROR_EXCEEDED_WRAPPER_LIMIT = 6;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NO_NETWORK = 1;
    public static final int ERROR_POST_VALIDATION = 5;
    public static final int ERROR_SCHEMA_VALIDATION = 4;
    public static final int ERROR_VIDEO_PLAYBACK = 7;
    public static final int ERROR_XML_OPEN_OR_READ = 2;
    public static final int ERROR_XML_PARSE = 3;
    private static final String TAG = "VASTPlayer";
    public static final String VERSION = "1.3";
    public static VASTPlayerListener listener;
    private final String cacheDirectory = "/vast_rtb_cache/";
    private final int cacheSize = 5;
    private Context context;
    private boolean disableLongVideo = true;
    private Uri fileUrl;
    private int maxDuration = 0;
    private boolean precache = false;
    private RtbInfo rtbInfo;
    private VASTModel vastModel;
    private String xmlUrl;

    class Pair implements Comparable {
        public File mFile;
        public long mLastModified;

        public Pair(File file) {
            this.mFile = file;
            this.mLastModified = file.lastModified();
        }

        public int compareTo(@NonNull Object obj) {
            Pair pair = (Pair) obj;
            if (this.mLastModified > pair.mLastModified) {
                return -1;
            }
            return this.mLastModified == pair.mLastModified ? 0 : 1;
        }
    }

    public interface VASTPlayerListener {
        void vastClick(String str, Activity activity);

        void vastComplete();

        void vastDismiss();

        void vastError(int i);

        void vastReady();

        void vastShown();
    }

    public VASTPlayer(Context context) {
        if (Appodeal.getLogLevel() == LogLevel.verbose) {
            VASTLog.setLoggingLevel(LOG_LEVEL.verbose);
        } else {
            VASTLog.setLoggingLevel(LOG_LEVEL.error);
        }
        this.context = context;
    }

    public void loadVideoWithUrl(final String str, final VASTPlayerListener vASTPlayerListener) {
        VASTLog.d(TAG, "loadVideoWithUrl " + str);
        this.vastModel = null;
        if (NetworkTools.connectedToInternet(this.context)) {
            new Thread(new Runnable() {
                public void run() {
                    Throwable e;
                    BufferedReader bufferedReader;
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(new URL(str).openStream()));
                        try {
                            StringBuffer stringBuffer = new StringBuffer();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuffer.append(readLine).append(System.getProperty("line.separator"));
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                }
                            }
                            VASTPlayer.this.loadVideoWithData(stringBuffer.toString(), vASTPlayerListener);
                        } catch (Exception e3) {
                            e = e3;
                            try {
                                VASTPlayer.this.sendError(2, vASTPlayerListener);
                                VASTLog.e(VASTPlayer.TAG, e.getMessage(), e);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e4) {
                                    }
                                }
                            } catch (Throwable th) {
                                e = th;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                throw e;
                            }
                        }
                    } catch (Exception e6) {
                        e = e6;
                        bufferedReader = null;
                        VASTPlayer.this.sendError(2, vASTPlayerListener);
                        VASTLog.e(VASTPlayer.TAG, e.getMessage(), e);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        bufferedReader = null;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw e;
                    }
                }
            }).start();
        } else {
            sendError(1, vASTPlayerListener);
        }
    }

    public void loadVideoWithData(final String str, final VASTPlayerListener vASTPlayerListener) {
        VASTLog.v(TAG, "loadVideoWithData\n" + str);
        this.vastModel = null;
        if (NetworkTools.connectedToInternet(this.context)) {
            new Thread(new Runnable() {
                public void run() {
                    VASTProcessor vASTProcessor = new VASTProcessor(new DefaultMediaPicker(VASTPlayer.this.context));
                    int process = vASTProcessor.process(str);
                    if (process == 0) {
                        VASTPlayer.this.vastModel = vASTProcessor.getModel();
                        if (VASTPlayer.this.precache) {
                            try {
                                VASTPlayer.this.cache(VASTPlayer.this.vastModel.getPickedMediaFileURL());
                                if (VASTPlayer.this.fileUrl == null) {
                                    try {
                                        if (VASTPlayer.this.vastModel != null) {
                                            VASTPlayer.this.vastModel.sendError(900);
                                        }
                                    } catch (Exception e) {
                                        VASTLog.e(VASTPlayer.TAG, e.getMessage());
                                    }
                                    VASTPlayer.this.sendError(8, vASTPlayerListener);
                                    return;
                                }
                                Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(VASTPlayer.this.fileUrl.getPath(), 1);
                                if (createVideoThumbnail == null) {
                                    VASTLog.d(VASTPlayer.TAG, "video file not supported");
                                    try {
                                        if (VASTPlayer.this.vastModel != null) {
                                            VASTPlayer.this.vastModel.sendError(403);
                                        }
                                    } catch (Exception e2) {
                                        VASTLog.e(VASTPlayer.TAG, e2.getMessage());
                                    }
                                    VASTPlayer.this.sendError(8, vASTPlayerListener);
                                } else if (createVideoThumbnail.equals(Bitmap.createBitmap(createVideoThumbnail.getWidth(), createVideoThumbnail.getHeight(), createVideoThumbnail.getConfig()))) {
                                    VASTLog.d(VASTPlayer.TAG, "empty thumbnail");
                                    try {
                                        if (VASTPlayer.this.vastModel != null) {
                                            VASTPlayer.this.vastModel.sendError(403);
                                        }
                                    } catch (Exception e22) {
                                        VASTLog.e(VASTPlayer.TAG, e22.getMessage());
                                    }
                                    VASTPlayer.this.sendError(8, vASTPlayerListener);
                                } else if (VERSION.SDK_INT < 10 || VASTPlayer.this.fileUrl == null || !new File(VASTPlayer.this.fileUrl.getPath()).exists()) {
                                    VASTPlayer.this.sendReady(vASTPlayerListener);
                                } else {
                                    try {
                                        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                                        mediaMetadataRetriever.setDataSource(VASTPlayer.this.context, VASTPlayer.this.fileUrl);
                                        long parseLong = Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
                                        if (VASTPlayer.this.maxDuration == 0 || parseLong <= ((long) VASTPlayer.this.maxDuration) || (parseLong > ((long) VASTPlayer.this.maxDuration) && !VASTPlayer.this.disableLongVideo)) {
                                            VASTPlayer.this.sendReady(vASTPlayerListener);
                                        } else {
                                            if (VASTPlayer.this.vastModel != null) {
                                                VASTPlayer.this.vastModel.sendError(403);
                                            }
                                            VASTPlayer.this.sendError(8, vASTPlayerListener);
                                        }
                                    } catch (Exception e222) {
                                        VASTLog.e(VASTPlayer.TAG, e222.getMessage());
                                        VASTPlayer.this.sendError(8, vASTPlayerListener);
                                    }
                                }
                                VASTPlayer.this.clearCache();
                                return;
                            } catch (Exception e3) {
                                VASTPlayer.this.sendError(8, vASTPlayerListener);
                                return;
                            }
                        }
                        return;
                    }
                    VASTPlayer.this.sendError(process, vASTPlayerListener);
                }
            }).start();
        } else {
            sendError(1, vASTPlayerListener);
        }
    }

    private String getCacheDirName() {
        File externalFilesDir = this.context.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            return externalFilesDir.getPath() + "/vast_rtb_cache/";
        }
        return null;
    }

    private void cache(String str) {
        String cacheDirName = getCacheDirName();
        if (cacheDirName == null) {
            throw new FileNotFoundException("No dir for caching file");
        }
        File file = new File(cacheDirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        int length = 230 - file.getPath().length();
        String str2 = "temp" + System.currentTimeMillis();
        String replace = str.substring(0, Math.min(length, str.length())).replace("/", "").replace(":", "");
        File file2 = new File(file, replace);
        if (file2.exists()) {
            this.fileUrl = Uri.fromFile(file2);
            return;
        }
        File file3 = new File(file, str2);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file3);
        long contentLength = (long) httpURLConnection.getContentLength();
        long j = 0;
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                break;
            }
            fileOutputStream.write(bArr, 0, read);
            j += (long) read;
        }
        fileOutputStream.close();
        if (contentLength == j) {
            file3.renameTo(new File(file, replace));
        }
        this.fileUrl = Uri.fromFile(new File(file, replace));
    }

    private void clearCache() {
        int i = 5;
        int i2 = 0;
        try {
            String cacheDirName = getCacheDirName();
            if (cacheDirName != null) {
                File[] listFiles = new File(cacheDirName).listFiles();
                if (listFiles != null && listFiles.length > 5) {
                    Pair[] pairArr = new Pair[listFiles.length];
                    for (int i3 = 0; i3 < listFiles.length; i3++) {
                        pairArr[i3] = new Pair(listFiles[i3]);
                    }
                    Arrays.sort(pairArr);
                    while (i2 < listFiles.length) {
                        listFiles[i2] = pairArr[i2].mFile;
                        i2++;
                    }
                    while (i < listFiles.length) {
                        if (!Uri.fromFile(listFiles[i]).equals(this.fileUrl)) {
                            listFiles[i].delete();
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            VASTLog.e(TAG, e.getMessage());
        }
    }

    public void play(b bVar, boolean z, boolean z2, VASTPlayerListener vASTPlayerListener) {
        VASTLog.d(TAG, "play");
        listener = vASTPlayerListener;
        if (this.vastModel == null) {
            VASTLog.w(TAG, "vastModel is null; nothing to play");
        } else if (NetworkTools.connectedToInternet(this.context)) {
            Intent intent;
            if (this.vastModel.getPickedMediaFileType().equals(WebRequest.CONTENT_TYPE_JAVASCRIPT)) {
                intent = new Intent(this.context, VPAIDActivity.class);
                String xmlUrl = getXmlUrl();
                if (xmlUrl == null || xmlUrl.isEmpty() || xmlUrl.equals(" ")) {
                    sendError(5, vASTPlayerListener);
                    return;
                }
                intent.putExtra("android.net.url", xmlUrl);
                intent.putExtra("com.nexage.android.vast.player.vastModel", this.vastModel);
                intent.putExtra("com.nexage.android.vast.player.type", bVar);
            } else {
                intent = new Intent(this.context, VASTActivity.class);
                intent.putExtra("com.nexage.android.vast.player.vastModel", this.vastModel);
                intent.putExtra("com.nexage.android.vast.player.type", bVar);
                if (this.rtbInfo != null) {
                    intent.putExtra("com.nexage.android.vast.player.reportInfo", this.rtbInfo);
                }
                if (this.fileUrl != null) {
                    intent.putExtra("android.net.url", this.fileUrl);
                }
                intent.putExtra("com.nexage.android.vast.player.woBanners", z);
                intent.putExtra("com.nexage.android.vast.player.autoClose", z2);
                intent.putExtra("com.nexage.android.vast.player.maxDuration", this.maxDuration);
            }
            this.context.startActivity(intent);
        } else {
            sendError(1, vASTPlayerListener);
        }
    }

    private void sendReady(final VASTPlayerListener vASTPlayerListener) {
        VASTLog.d(TAG, "sendReady");
        if (vASTPlayerListener != null) {
            ((Activity) this.context).runOnUiThread(new Runnable() {
                public void run() {
                    vASTPlayerListener.vastReady();
                }
            });
        }
    }

    private void sendError(final int i, final VASTPlayerListener vASTPlayerListener) {
        VASTLog.d(TAG, "sendError");
        if (vASTPlayerListener != null) {
            ((Activity) this.context).runOnUiThread(new Runnable() {
                public void run() {
                    vASTPlayerListener.vastError(i);
                }
            });
        }
    }

    public void setPrecache(boolean z) {
        this.precache = z;
    }

    public void setRtbInfo(RtbInfo rtbInfo) {
        this.rtbInfo = rtbInfo;
    }

    public String getXmlUrl() {
        return this.xmlUrl;
    }

    public void setXmlUrl(String str) {
        this.xmlUrl = str;
    }

    public boolean checkFile() {
        try {
            if (this.fileUrl == null || !new File(this.fileUrl.getPath()).exists()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setMaxDuration(int i) {
        this.maxDuration = i;
    }

    public void setDisableLongVideo(boolean z) {
        this.disableLongVideo = z;
    }
}
