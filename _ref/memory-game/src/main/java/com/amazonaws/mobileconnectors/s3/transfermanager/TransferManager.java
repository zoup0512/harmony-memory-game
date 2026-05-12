package com.amazonaws.mobileconnectors.s3.transfermanager;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.event.ProgressListenerChain.ProgressEventFilter;
import com.amazonaws.mobileconnectors.s3.transfermanager.Transfer.TransferState;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.CopyCallable;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.CopyImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.CopyMonitor;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.DownloadImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.DownloadMonitor;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.MultipleFileDownloadImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.MultipleFileTransferMonitor;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.MultipleFileUploadImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.S3ProgressListener;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.S3ProgressListenerChain;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferManagerUtils;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferProgressUpdatingListener;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.TransferStateChangeListener;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.UploadCallable;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.UploadImpl;
import com.amazonaws.mobileconnectors.s3.transfermanager.internal.UploadMonitor;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.internal.ServiceUtils;
import com.amazonaws.services.s3.internal.ServiceUtils.RetryableS3DownloadTask;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListMultipartUploadsRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.MultipartUpload;
import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.util.Mimetypes;
import com.amazonaws.util.VersionInfoUtils;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
public class TransferManager {
    private static final String DEFAULT_DELIMITER = "/";
    private static final String USER_AGENT = (TransferManager.class.getName() + DEFAULT_DELIMITER + VersionInfoUtils.getVersion());
    private static final String USER_AGENT_MULTIPART = (TransferManager.class.getName() + "_multipart/" + VersionInfoUtils.getVersion());
    private static final ThreadFactory daemonThreadFactory = new ThreadFactory() {
        final AtomicInteger threadCount = new AtomicInteger(0);

        public Thread newThread(Runnable r) {
            int threadNumber = this.threadCount.incrementAndGet();
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("S3TransferManagerTimedThread-" + threadNumber);
            return thread;
        }
    };
    private static final Log log = LogFactory.getLog(TransferManager.class);
    private TransferManagerConfiguration configuration;
    private final AmazonS3 s3;
    private final ExecutorService threadPool;
    private final ScheduledExecutorService timedThreadPool;

    public TransferManager() {
        this(new AmazonS3Client(new DefaultAWSCredentialsProviderChain()));
    }

    public TransferManager(AWSCredentialsProvider credentialsProvider) {
        this(new AmazonS3Client(credentialsProvider));
    }

    public TransferManager(AWSCredentials credentials) {
        this(new AmazonS3Client(credentials));
    }

    public TransferManager(AmazonS3 s3) {
        this(s3, TransferManagerUtils.createDefaultExecutorService());
    }

    public TransferManager(AmazonS3 s3, ExecutorService threadPool) {
        this.timedThreadPool = new ScheduledThreadPoolExecutor(1, daemonThreadFactory);
        this.s3 = s3;
        this.threadPool = threadPool;
        this.configuration = new TransferManagerConfiguration();
    }

    public void setConfiguration(TransferManagerConfiguration configuration) {
        this.configuration = configuration;
    }

    public TransferManagerConfiguration getConfiguration() {
        return this.configuration;
    }

    public AmazonS3 getAmazonS3Client() {
        return this.s3;
    }

    public Upload upload(String bucketName, String key, InputStream input, ObjectMetadata objectMetadata) throws AmazonServiceException, AmazonClientException {
        return upload(new PutObjectRequest(bucketName, key, input, objectMetadata));
    }

    public Upload upload(String bucketName, String key, File file) throws AmazonServiceException, AmazonClientException {
        return upload(new PutObjectRequest(bucketName, key, file));
    }

    public Upload upload(PutObjectRequest putObjectRequest) throws AmazonServiceException, AmazonClientException {
        return doUpload(putObjectRequest, null, null, null);
    }

    public Upload upload(PutObjectRequest putObjectRequest, S3ProgressListener progressListener) throws AmazonServiceException, AmazonClientException {
        return doUpload(putObjectRequest, null, progressListener, null);
    }

    private Upload doUpload(PutObjectRequest putObjectRequest, TransferStateChangeListener stateListener, S3ProgressListener progressListener, PersistableUpload persistableUpload) throws AmazonServiceException, AmazonClientException {
        appendSingleObjectUserAgent(putObjectRequest);
        String multipartUploadId = persistableUpload != null ? persistableUpload.getMultipartUploadId() : null;
        if (putObjectRequest.getMetadata() == null) {
            putObjectRequest.setMetadata(new ObjectMetadata());
        }
        ObjectMetadata metadata = putObjectRequest.getMetadata();
        File file = TransferManagerUtils.getRequestFile(putObjectRequest);
        if (file != null) {
            metadata.setContentLength(file.length());
            if (metadata.getContentType() == null) {
                metadata.setContentType(Mimetypes.getInstance().getMimetype(file));
            }
        } else if (multipartUploadId != null) {
            throw new IllegalArgumentException("Unable to resume the upload. No file specified.");
        }
        String description = "Uploading to " + putObjectRequest.getBucketName() + DEFAULT_DELIMITER + putObjectRequest.getKey();
        TransferProgress transferProgress = new TransferProgress();
        transferProgress.setTotalBytesToTransfer(TransferManagerUtils.getContentLength(putObjectRequest));
        ProgressListenerChain listenerChain = new S3ProgressListenerChain(new TransferProgressUpdatingListener(transferProgress), putObjectRequest.getGeneralProgressListener(), progressListener);
        putObjectRequest.setGeneralProgressListener(listenerChain);
        UploadImpl upload = new UploadImpl(description, transferProgress, listenerChain, stateListener);
        UploadMonitor watcher = new UploadMonitor(this, upload, this.threadPool, new UploadCallable(this, this.threadPool, upload, putObjectRequest, listenerChain, multipartUploadId, transferProgress), putObjectRequest, listenerChain);
        watcher.setTimedThreadPool(this.timedThreadPool);
        upload.setMonitor(watcher);
        return upload;
    }

    public Download download(String bucket, String key, File file) {
        return download(new GetObjectRequest(bucket, key), file);
    }

    public Download download(GetObjectRequest getObjectRequest, File file) {
        return doDownload(getObjectRequest, file, null, null, false);
    }

    public Download download(GetObjectRequest getObjectRequest, File file, S3ProgressListener progressListener) {
        return doDownload(getObjectRequest, file, null, progressListener, false);
    }

    private Download doDownload(GetObjectRequest getObjectRequest, File file, TransferStateChangeListener stateListener, S3ProgressListener s3progressListener, boolean resumeExistingDownload) {
        appendSingleObjectUserAgent(getObjectRequest);
        String description = "Downloading from " + getObjectRequest.getBucketName() + DEFAULT_DELIMITER + getObjectRequest.getKey();
        TransferProgress transferProgress = new TransferProgress();
        S3ProgressListenerChain listenerChain = new S3ProgressListenerChain(new TransferProgressUpdatingListener(transferProgress), getObjectRequest.getGeneralProgressListener(), s3progressListener);
        getObjectRequest.setGeneralProgressListener(new ProgressListenerChain(new ProgressEventFilter() {
            public ProgressEvent filter(ProgressEvent progressEvent) {
                if (progressEvent.getEventCode() == 4) {
                    progressEvent.setEventCode(0);
                }
                return progressEvent;
            }
        }, listenerChain));
        GetObjectMetadataRequest getObjectMetadataRequest = new GetObjectMetadataRequest(getObjectRequest.getBucketName(), getObjectRequest.getKey());
        if (getObjectRequest.getSSECustomerKey() != null) {
            getObjectMetadataRequest.setSSECustomerKey(getObjectRequest.getSSECustomerKey());
        }
        ObjectMetadata objectMetadata = this.s3.getObjectMetadata(getObjectMetadataRequest);
        DownloadImpl download = new DownloadImpl(description, transferProgress, listenerChain, null, stateListener, getObjectRequest, file);
        long startingByte = 0;
        long lastByte = objectMetadata.getContentLength() - 1;
        if (getObjectRequest.getRange() != null && getObjectRequest.getRange().length == 2) {
            startingByte = getObjectRequest.getRange()[0];
            lastByte = getObjectRequest.getRange()[1];
        }
        long totalBytesToDownload = (lastByte - startingByte) + 1;
        transferProgress.setTotalBytesToTransfer(totalBytesToDownload);
        if (resumeExistingDownload && file.exists()) {
            long numberOfBytesRead = file.length();
            startingByte += numberOfBytesRead;
            getObjectRequest.setRange(startingByte, lastByte);
            transferProgress.updateProgress(Math.min(numberOfBytesRead, totalBytesToDownload));
            totalBytesToDownload = (lastByte - startingByte) + 1;
        }
        if (totalBytesToDownload < 0) {
            throw new IllegalArgumentException("Unable to determine the range for download operation.");
        }
        CountDownLatch latch = new CountDownLatch(1);
        download.setMonitor(new DownloadMonitor(download, submitDownloadTask(getObjectRequest, file, resumeExistingDownload, latch, download)));
        latch.countDown();
        return download;
    }

    private Future<?> submitDownloadTask(GetObjectRequest getObjectRequest, File file, boolean resumeExistingDownload, CountDownLatch latch, DownloadImpl download) {
        final CountDownLatch countDownLatch = latch;
        final DownloadImpl downloadImpl = download;
        final File file2 = file;
        final GetObjectRequest getObjectRequest2 = getObjectRequest;
        final boolean z = resumeExistingDownload;
        return this.threadPool.submit(new Callable<Object>() {
            public Object call() throws Exception {
                try {
                    countDownLatch.await();
                    downloadImpl.setState(TransferState.InProgress);
                    if (ServiceUtils.retryableDownloadS3ObjectToFile(file2, new RetryableS3DownloadTask() {
                        public S3Object getS3ObjectStream() {
                            S3Object s3Object = TransferManager.this.s3.getObject(getObjectRequest2);
                            downloadImpl.setS3Object(s3Object);
                            return s3Object;
                        }

                        public boolean needIntegrityCheck() {
                            if (ServiceUtils.skipMd5CheckPerRequest(getObjectRequest2) || (TransferManager.this.s3 instanceof AmazonS3EncryptionClient)) {
                                return false;
                            }
                            return true;
                        }
                    }, z) == null) {
                        downloadImpl.setState(TransferState.Canceled);
                        downloadImpl.setMonitor(new DownloadMonitor(downloadImpl, null));
                        return downloadImpl;
                    }
                    downloadImpl.setState(TransferState.Completed);
                    return Boolean.valueOf(true);
                } catch (Throwable t) {
                    if (downloadImpl.getState() != TransferState.Canceled) {
                        downloadImpl.setState(TransferState.Failed);
                    }
                    if (t instanceof Exception) {
                        Exception t2 = (Exception) t;
                    } else {
                        Error t3 = (Error) t;
                    }
                }
            }
        });
    }

    public MultipleFileDownload downloadDirectory(String bucketName, String keyPrefix, File destinationDirectory) {
        if (keyPrefix == null) {
            keyPrefix = "";
        }
        List<S3ObjectSummary> objectSummaries = new LinkedList();
        Stack<String> commonPrefixes = new Stack();
        commonPrefixes.add(keyPrefix);
        long totalSize = 0;
        do {
            String prefix = (String) commonPrefixes.pop();
            ObjectListing listObjectsResponse = null;
            do {
                if (listObjectsResponse == null) {
                    listObjectsResponse = this.s3.listObjects(new ListObjectsRequest().withBucketName(bucketName).withDelimiter(DEFAULT_DELIMITER).withPrefix(prefix));
                } else {
                    listObjectsResponse = this.s3.listNextBatchOfObjects(listObjectsResponse);
                }
                for (S3ObjectSummary s : listObjectsResponse.getObjectSummaries()) {
                    if (s.getKey().equals(prefix) || listObjectsResponse.getCommonPrefixes().contains(s.getKey() + DEFAULT_DELIMITER)) {
                        log.debug("Skipping download for object " + s.getKey() + " since it is also a virtual directory");
                    } else {
                        objectSummaries.add(s);
                        totalSize += s.getSize();
                    }
                }
                commonPrefixes.addAll(listObjectsResponse.getCommonPrefixes());
            } while (listObjectsResponse.isTruncated());
        } while (!commonPrefixes.isEmpty());
        ProgressListenerChain additionalListeners = new ProgressListenerChain(new ProgressListener[0]);
        TransferProgress transferProgress = new TransferProgress();
        transferProgress.setTotalBytesToTransfer(totalSize);
        ProgressListener multipleFileTransferProgressUpdatingListener = new MultipleFileTransferProgressUpdatingListener(transferProgress, additionalListeners);
        List<DownloadImpl> downloads = new ArrayList();
        MultipleFileDownloadImpl multipleFileDownload = new MultipleFileDownloadImpl("Downloading from " + bucketName + DEFAULT_DELIMITER + keyPrefix, transferProgress, additionalListeners, keyPrefix, bucketName, downloads);
        multipleFileDownload.setMonitor(new MultipleFileTransferMonitor(multipleFileDownload, downloads));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MultipleFileTransferStateChangeListener transferListener = new MultipleFileTransferStateChangeListener(countDownLatch, multipleFileDownload);
        for (S3ObjectSummary summary : objectSummaries) {
            File f = new File(destinationDirectory, summary.getKey());
            File parentFile = f.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                downloads.add((DownloadImpl) doDownload(new GetObjectRequest(summary.getBucketName(), summary.getKey()).withGeneralProgressListener(multipleFileTransferProgressUpdatingListener), f, transferListener, null, false));
            } else {
                throw new RuntimeException("Couldn't create parent directories for " + f.getAbsolutePath());
            }
        }
        if (downloads.isEmpty()) {
            multipleFileDownload.setState(TransferState.Completed);
        } else {
            countDownLatch.countDown();
        }
        return multipleFileDownload;
    }

    public MultipleFileUpload uploadDirectory(String bucketName, String virtualDirectoryKeyPrefix, File directory, boolean includeSubdirectories) {
        return uploadDirectory(bucketName, virtualDirectoryKeyPrefix, directory, includeSubdirectories, null);
    }

    public MultipleFileUpload uploadDirectory(String bucketName, String virtualDirectoryKeyPrefix, File directory, boolean includeSubdirectories, ObjectMetadataProvider metadataProvider) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            List<File> files = new LinkedList();
            listFiles(directory, files, includeSubdirectories);
            return uploadFileList(bucketName, virtualDirectoryKeyPrefix, directory, files, metadataProvider);
        }
        throw new IllegalArgumentException("Must provide a directory to upload");
    }

    public MultipleFileUpload uploadFileList(String bucketName, String virtualDirectoryKeyPrefix, File directory, List<File> files) {
        return uploadFileList(bucketName, virtualDirectoryKeyPrefix, directory, files, null);
    }

    public MultipleFileUpload uploadFileList(String bucketName, String virtualDirectoryKeyPrefix, File directory, List<File> files, ObjectMetadataProvider metadataProvider) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            if (virtualDirectoryKeyPrefix == null || virtualDirectoryKeyPrefix.length() == 0) {
                virtualDirectoryKeyPrefix = "";
            } else {
                if (!virtualDirectoryKeyPrefix.endsWith(DEFAULT_DELIMITER)) {
                    virtualDirectoryKeyPrefix = virtualDirectoryKeyPrefix + DEFAULT_DELIMITER;
                }
            }
            ProgressListenerChain additionalListeners = new ProgressListenerChain(new ProgressListener[0]);
            TransferProgress progress = new TransferProgress();
            ProgressListener listener = new MultipleFileTransferProgressUpdatingListener(progress, additionalListeners);
            List<UploadImpl> uploads = new LinkedList();
            MultipleFileUploadImpl multipleFileUpload = new MultipleFileUploadImpl("Uploading etc", progress, additionalListeners, virtualDirectoryKeyPrefix, bucketName, uploads);
            multipleFileUpload.setMonitor(new MultipleFileTransferMonitor(multipleFileUpload, uploads));
            CountDownLatch latch = new CountDownLatch(1);
            MultipleFileTransferStateChangeListener transferListener = new MultipleFileTransferStateChangeListener(latch, multipleFileUpload);
            if (files == null || files.isEmpty()) {
                multipleFileUpload.setState(TransferState.Completed);
            } else {
                int startingPosition = directory.getAbsolutePath().length();
                if (!directory.getAbsolutePath().endsWith(File.separator)) {
                    startingPosition++;
                }
                long totalSize = 0;
                for (File f : files) {
                    if (f.isFile()) {
                        totalSize += f.length();
                        String key = f.getAbsolutePath().substring(startingPosition).replaceAll("\\\\", DEFAULT_DELIMITER);
                        ObjectMetadata metadata = new ObjectMetadata();
                        if (metadataProvider != null) {
                            metadataProvider.provideObjectMetadata(f, metadata);
                        }
                        uploads.add((UploadImpl) doUpload(new PutObjectRequest(bucketName, virtualDirectoryKeyPrefix + key, f).withMetadata(metadata).withGeneralProgressListener(listener), transferListener, null, null));
                    }
                }
                progress.setTotalBytesToTransfer(totalSize);
            }
            latch.countDown();
            return multipleFileUpload;
        }
        throw new IllegalArgumentException("Must provide a common base directory for uploaded files");
    }

    private void listFiles(File dir, List<File> results, boolean includeSubDirectories) {
        File[] found = dir.listFiles();
        if (found != null) {
            for (File f : found) {
                if (!f.isDirectory()) {
                    results.add(f);
                } else if (includeSubDirectories) {
                    listFiles(f, results, includeSubDirectories);
                }
            }
        }
    }

    public void abortMultipartUploads(String bucketName, Date date) throws AmazonServiceException, AmazonClientException {
        MultipartUploadListing uploadListing = this.s3.listMultipartUploads((ListMultipartUploadsRequest) appendSingleObjectUserAgent(new ListMultipartUploadsRequest(bucketName)));
        do {
            for (MultipartUpload upload : uploadListing.getMultipartUploads()) {
                if (upload.getInitiated().compareTo(date) < 0) {
                    this.s3.abortMultipartUpload((AbortMultipartUploadRequest) appendSingleObjectUserAgent(new AbortMultipartUploadRequest(bucketName, upload.getKey(), upload.getUploadId())));
                }
            }
            uploadListing = this.s3.listMultipartUploads((ListMultipartUploadsRequest) appendSingleObjectUserAgent(new ListMultipartUploadsRequest(bucketName).withUploadIdMarker(uploadListing.getNextUploadIdMarker()).withKeyMarker(uploadListing.getNextKeyMarker())));
        } while (uploadListing.isTruncated());
    }

    public void shutdownNow() {
        shutdownNow(true);
    }

    public void shutdownNow(boolean shutDownS3Client) {
        this.threadPool.shutdownNow();
        this.timedThreadPool.shutdownNow();
        if (shutDownS3Client && (this.s3 instanceof AmazonS3Client)) {
            ((AmazonS3Client) this.s3).shutdown();
        }
    }

    private void shutdown() {
        this.threadPool.shutdown();
        this.timedThreadPool.shutdown();
    }

    public static <X extends AmazonWebServiceRequest> X appendSingleObjectUserAgent(X request) {
        request.getRequestClientOptions().appendUserAgent(USER_AGENT);
        return request;
    }

    public static <X extends AmazonWebServiceRequest> X appendMultipartUserAgent(X request) {
        request.getRequestClientOptions().appendUserAgent(USER_AGENT_MULTIPART);
        return request;
    }

    public Copy copy(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey) throws AmazonServiceException, AmazonClientException {
        return copy(new CopyObjectRequest(sourceBucketName, sourceKey, destinationBucketName, destinationKey));
    }

    public Copy copy(CopyObjectRequest copyObjectRequest) {
        return copy(copyObjectRequest, null);
    }

    public Copy copy(CopyObjectRequest copyObjectRequest, TransferStateChangeListener stateChangeListener) throws AmazonServiceException, AmazonClientException {
        appendSingleObjectUserAgent(copyObjectRequest);
        assertParameterNotNull(copyObjectRequest.getSourceBucketName(), "The source bucket name must be specified when a copy request is initiated.");
        assertParameterNotNull(copyObjectRequest.getSourceKey(), "The source object key must be specified when a copy request is initiated.");
        assertParameterNotNull(copyObjectRequest.getDestinationBucketName(), "The destination bucket name must be specified when a copy request is initiated.");
        assertParameterNotNull(copyObjectRequest.getDestinationKey(), "The destination object key must be specified when a copy request is initiated.");
        String description = "Copying object from " + copyObjectRequest.getSourceBucketName() + DEFAULT_DELIMITER + copyObjectRequest.getSourceKey() + " to " + copyObjectRequest.getDestinationBucketName() + DEFAULT_DELIMITER + copyObjectRequest.getDestinationKey();
        ObjectMetadata metadata = this.s3.getObjectMetadata(new GetObjectMetadataRequest(copyObjectRequest.getSourceBucketName(), copyObjectRequest.getSourceKey()).withSSECustomerKey(copyObjectRequest.getSourceSSECustomerKey()));
        TransferProgress transferProgress = new TransferProgress();
        transferProgress.setTotalBytesToTransfer(metadata.getContentLength());
        ProgressListenerChain listenerChain = new ProgressListenerChain(new TransferProgressUpdatingListener(transferProgress));
        CopyImpl copy = new CopyImpl(description, transferProgress, listenerChain, stateChangeListener);
        CopyImpl copyImpl = copy;
        CopyMonitor watcher = new CopyMonitor(this, copyImpl, this.threadPool, new CopyCallable(this, this.threadPool, copy, copyObjectRequest, metadata, listenerChain), copyObjectRequest, listenerChain);
        watcher.setTimedThreadPool(this.timedThreadPool);
        copy.setMonitor(watcher);
        return copy;
    }

    public Upload resumeUpload(PersistableUpload persistableUpload) {
        assertParameterNotNull(persistableUpload, "PauseUpload is mandatory to resume a upload.");
        this.configuration.setMinimumUploadPartSize(persistableUpload.getPartSize());
        this.configuration.setMultipartUploadThreshold(persistableUpload.getMutlipartUploadThreshold());
        return doUpload(new PutObjectRequest(persistableUpload.getBucketName(), persistableUpload.getKey(), new File(persistableUpload.getFile())), null, null, persistableUpload);
    }

    public Download resumeDownload(PersistableDownload persistableDownload) {
        assertParameterNotNull(persistableDownload, "PausedDownload is mandatory to resume a download.");
        GetObjectRequest request = new GetObjectRequest(persistableDownload.getBucketName(), persistableDownload.getKey(), persistableDownload.getVersionId());
        if (persistableDownload.getRange() != null && persistableDownload.getRange().length == 2) {
            long[] range = persistableDownload.getRange();
            request.setRange(range[0], range[1]);
        }
        request.setRequesterPays(persistableDownload.isRequesterPays());
        request.setResponseHeaders(persistableDownload.getResponseHeaders());
        return doDownload(request, new File(persistableDownload.getFile()), null, null, true);
    }

    private void assertParameterNotNull(Object parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    protected void finalize() throws Throwable {
        shutdown();
    }
}
