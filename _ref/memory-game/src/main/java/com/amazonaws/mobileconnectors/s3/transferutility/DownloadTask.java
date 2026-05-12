package com.amazonaws.mobileconnectors.s3.transferutility;

import android.util.Log;
import com.amazonaws.AmazonClientException;
import com.amazonaws.retry.RetryUtils;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

class DownloadTask implements Callable<Boolean> {
    private static final String TAG = "DownloadTask";
    private final TransferRecord download;
    private final NetworkInfoReceiver networkInfo;
    private final AmazonS3 s3;
    private final TransferStatusUpdater updater;

    public DownloadTask(TransferRecord download, AmazonS3 s3, TransferStatusUpdater updater, NetworkInfoReceiver networkInfo) {
        this.download = download;
        this.s3 = s3;
        this.updater = updater;
        this.networkInfo = networkInfo;
    }

    public Boolean call() throws Exception {
        if (this.networkInfo.isNetworkConnected()) {
            this.updater.updateState(this.download.id, TransferState.IN_PROGRESS);
            GetObjectRequest getObjectRequest = new GetObjectRequest(this.download.bucketName, this.download.key);
            TransferUtility.appendTransferServiceUserAgentString(getObjectRequest);
            File file = new File(this.download.file);
            long bytesCurrent = file.length();
            if (bytesCurrent > 0) {
                Log.d(TAG, String.format("Resume transfer %d from %d bytes", new Object[]{Integer.valueOf(this.download.id), Long.valueOf(bytesCurrent)}));
                getObjectRequest.setRange(bytesCurrent, -1);
            }
            getObjectRequest.setGeneralProgressListener(this.updater.newProgressListener(this.download.id));
            try {
                S3Object object = this.s3.getObject(getObjectRequest);
                if (object == null) {
                    this.updater.throwError(this.download.id, new IllegalStateException("AmazonS3.getObject returns null"));
                    this.updater.updateState(this.download.id, TransferState.FAILED);
                    return Boolean.valueOf(false);
                }
                long bytesTotal = object.getObjectMetadata().getInstanceLength();
                this.updater.updateProgress(this.download.id, bytesCurrent, bytesTotal);
                saveToFile(object.getObjectContent(), file);
                this.updater.updateProgress(this.download.id, bytesTotal, bytesTotal);
                this.updater.updateState(this.download.id, TransferState.COMPLETED);
                return Boolean.valueOf(true);
            } catch (Exception e) {
                if (RetryUtils.isInterrupted(e)) {
                    Log.d(TAG, "Transfer " + this.download.id + " is interrupted by user");
                } else if (e.getCause() == null || !(e.getCause() instanceof IOException) || this.networkInfo.isNetworkConnected()) {
                    Log.e(TAG, "Failed to download: " + this.download.id + " due to " + e.getMessage());
                    this.updater.throwError(this.download.id, e);
                    this.updater.updateState(this.download.id, TransferState.FAILED);
                } else {
                    Log.d(TAG, "Transfer " + this.download.id + " waits for network");
                    this.updater.updateState(this.download.id, TransferState.WAITING_FOR_NETWORK);
                }
                return Boolean.valueOf(false);
            }
        }
        this.updater.updateState(this.download.id, TransferState.WAITING_FOR_NETWORK);
        return Boolean.valueOf(false);
    }

    private void saveToFile(InputStream is, File file) {
        IOException e;
        Throwable th;
        boolean append = false;
        File parentDirectory = file.getParentFile();
        if (!(parentDirectory == null || parentDirectory.exists())) {
            parentDirectory.mkdirs();
        }
        if (file.length() > 0) {
            append = true;
        }
        OutputStream outputStream = null;
        try {
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file, append));
            try {
                byte[] buffer = new byte[16384];
                while (true) {
                    int bytesRead = is.read(buffer);
                    if (bytesRead == -1) {
                        break;
                    }
                    os.write(buffer, 0, bytesRead);
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e2) {
                    }
                }
                try {
                    is.close();
                } catch (IOException e3) {
                }
            } catch (IOException e4) {
                e = e4;
                outputStream = os;
                try {
                    throw new AmazonClientException("Unable to store object contents to disk: " + e.getMessage(), e);
                } catch (Throwable th2) {
                    th = th2;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e5) {
                        }
                    }
                    try {
                        is.close();
                    } catch (IOException e6) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                outputStream = os;
                if (outputStream != null) {
                    outputStream.close();
                }
                is.close();
                throw th;
            }
        } catch (IOException e7) {
            e = e7;
            throw new AmazonClientException("Unable to store object contents to disk: " + e.getMessage(), e);
        }
    }
}
