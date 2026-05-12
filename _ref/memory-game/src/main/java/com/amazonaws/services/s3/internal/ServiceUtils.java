package com.amazonaws.services.s3.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.Request;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.HttpUtils;
import com.amazonaws.util.Md5Utils;
import com.amazonaws.util.StringUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceUtils {
    public static final boolean APPEND_MODE = true;
    public static final boolean OVERWRITE_MODE = false;
    @Deprecated
    protected static final DateUtils dateUtils = new DateUtils();
    private static final Log log = LogFactory.getLog(ServiceUtils.class);

    public interface RetryableS3DownloadTask {
        S3Object getS3ObjectStream();

        boolean needIntegrityCheck();
    }

    public static com.amazonaws.services.s3.model.S3Object retryableDownloadS3ObjectToFile(java.io.File r7, com.amazonaws.services.s3.internal.ServiceUtils.RetryableS3DownloadTask r8, boolean r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:39:? in {12, 13, 15, 21, 24, 29, 30, 32, 33, 35, 36, 37, 38, 40, 41} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r1 = 0;
    L_0x0001:
        r2 = 0;
        r3 = r8.getS3ObjectStream();
        if (r3 != 0) goto L_0x000a;
    L_0x0008:
        r3 = 0;
    L_0x0009:
        return r3;
    L_0x000a:
        r4 = r8.needIntegrityCheck();	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        downloadObjectToFile(r3, r7, r4, r9);	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r4 = r3.getObjectContent();
        r4.abort();
    L_0x0018:
        if (r2 != 0) goto L_0x0001;
    L_0x001a:
        goto L_0x0009;
    L_0x001b:
        r0 = move-exception;
        r4 = r0.isRetryable();	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        if (r4 != 0) goto L_0x002c;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
    L_0x0022:
        throw r0;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
    L_0x0023:
        r4 = move-exception;
        r5 = r3.getObjectContent();
        r5.abort();
        throw r4;
    L_0x002c:
        r4 = r0.getCause();	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r4 = r4 instanceof java.net.SocketException;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        if (r4 != 0) goto L_0x003c;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
    L_0x0034:
        r4 = r0.getCause();	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r4 = r4 instanceof javax.net.ssl.SSLProtocolException;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        if (r4 == 0) goto L_0x003d;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
    L_0x003c:
        throw r0;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
    L_0x003d:
        r2 = 1;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        if (r1 == 0) goto L_0x0041;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
    L_0x0040:
        throw r0;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
    L_0x0041:
        r4 = log;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r5 = new java.lang.StringBuilder;	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r5.<init>();	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r6 = "Retry the download of object ";	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r5 = r5.append(r6);	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r6 = r3.getKey();	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r5 = r5.append(r6);	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r6 = " (bucket ";	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r5 = r5.append(r6);	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r6 = r3.getBucketName();	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r5 = r5.append(r6);	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r6 = ")";	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r5 = r5.append(r6);	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r5 = r5.toString();	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r4.info(r5, r0);	 Catch:{ AmazonClientException -> 0x001b, all -> 0x0023 }
        r1 = 1;
        r4 = r3.getObjectContent();
        r4.abort();
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.services.s3.internal.ServiceUtils.retryableDownloadS3ObjectToFile(java.io.File, com.amazonaws.services.s3.internal.ServiceUtils$RetryableS3DownloadTask, boolean):com.amazonaws.services.s3.model.S3Object");
    }

    public static Date parseIso8601Date(String dateString) {
        return DateUtils.parseISO8601Date(dateString);
    }

    public static String formatIso8601Date(Date date) {
        return DateUtils.formatISO8601Date(date);
    }

    public static Date parseRfc822Date(String dateString) {
        return DateUtils.parseRFC822Date(dateString);
    }

    public static String formatRfc822Date(Date date) {
        return DateUtils.formatRFC822Date(date);
    }

    public static boolean isMultipartUploadETag(String eTag) {
        return eTag.contains("-");
    }

    public static byte[] toByteArray(String s) {
        return s.getBytes(StringUtils.UTF8);
    }

    public static String removeQuotes(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.startsWith("\"")) {
            s = s.substring(1);
        }
        if (s.endsWith("\"")) {
            return s.substring(0, s.length() - 1);
        }
        return s;
    }

    public static URL convertRequestToUrl(Request<?> request) {
        return convertRequestToUrl(request, false);
    }

    public static URL convertRequestToUrl(Request<?> request, boolean removeLeadingSlashInResourcePath) {
        String resourcePath = HttpUtils.urlEncode(request.getResourcePath(), true);
        if (removeLeadingSlashInResourcePath && resourcePath.startsWith("/")) {
            resourcePath = resourcePath.substring(1);
        }
        String urlString = request.getEndpoint() + ("/" + resourcePath).replaceAll("(?<=/)/", "%2F");
        boolean firstParam = true;
        for (String param : request.getParameters().keySet()) {
            if (firstParam) {
                urlString = urlString + "?";
                firstParam = false;
            } else {
                urlString = urlString + "&";
            }
            urlString = urlString + param + "=" + HttpUtils.urlEncode((String) request.getParameters().get(param), false);
        }
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new AmazonClientException("Unable to convert request to well formed URL: " + e.getMessage(), e);
        }
    }

    public static String join(List<String> strings) {
        String result = "";
        boolean first = true;
        for (String s : strings) {
            if (!first) {
                result = result + ", ";
            }
            result = result + s;
            first = false;
        }
        return result;
    }

    public static void downloadObjectToFile(S3Object s3Object, File destinationFile, boolean performIntegrityCheck, boolean appendData) {
        IOException e;
        Throwable th;
        File parentDirectory = destinationFile.getParentFile();
        if (!(parentDirectory == null || parentDirectory.exists())) {
            parentDirectory.mkdirs();
        }
        OutputStream outputStream = null;
        try {
            OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(destinationFile, appendData));
            try {
                byte[] buffer = new byte[10240];
                while (true) {
                    int bytesRead = s3Object.getObjectContent().read(buffer);
                    if (bytesRead > -1) {
                        outputStream2.write(buffer, 0, bytesRead);
                    } else {
                        try {
                            break;
                        } catch (Exception e2) {
                        }
                    }
                }
                outputStream2.close();
                try {
                    s3Object.getObjectContent().close();
                } catch (Exception e3) {
                }
                byte[] clientSideHash = null;
                byte[] serverSideHash = null;
                try {
                    if (!isMultipartUploadETag(s3Object.getObjectMetadata().getETag())) {
                        clientSideHash = Md5Utils.computeMD5Hash(new FileInputStream(destinationFile));
                        serverSideHash = BinaryUtils.fromHex(s3Object.getObjectMetadata().getETag());
                    }
                } catch (Exception e4) {
                    log.warn("Unable to calculate MD5 hash to validate download: " + e4.getMessage(), e4);
                }
                if (performIntegrityCheck && clientSideHash != null && serverSideHash != null && !Arrays.equals(clientSideHash, serverSideHash)) {
                    throw new AmazonClientException("Unable to verify integrity of data download.  Client calculated content hash didn't match hash calculated by Amazon S3.  The data stored in '" + destinationFile.getAbsolutePath() + "' may be corrupt.");
                }
            } catch (IOException e5) {
                e = e5;
                outputStream = outputStream2;
                try {
                    s3Object.getObjectContent().abort();
                    throw new AmazonClientException("Unable to store object contents to disk: " + e.getMessage(), e);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        outputStream.close();
                    } catch (Exception e6) {
                    }
                    try {
                        s3Object.getObjectContent().close();
                    } catch (Exception e7) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                outputStream = outputStream2;
                outputStream.close();
                s3Object.getObjectContent().close();
                throw th;
            }
        } catch (IOException e8) {
            e = e8;
            s3Object.getObjectContent().abort();
            throw new AmazonClientException("Unable to store object contents to disk: " + e.getMessage(), e);
        }
    }

    public static boolean skipMd5CheckPerResponse(ObjectMetadata metadata) {
        if (metadata == null) {
            return false;
        }
        boolean sseKMS = ObjectMetadata.KMS_SERVER_SIDE_ENCRYPTION.equals(metadata.getSSEAlgorithm());
        if (metadata.getSSECustomerAlgorithm() != null || sseKMS) {
            return true;
        }
        return false;
    }

    public static boolean skipMd5CheckPerRequest(AmazonWebServiceRequest request) {
        if (System.getProperty("com.amazonaws.services.s3.disableGetObjectMD5Validation") != null) {
            return true;
        }
        if (request instanceof GetObjectRequest) {
            GetObjectRequest getObjectRequest = (GetObjectRequest) request;
            if (!(getObjectRequest.getRange() == null && getObjectRequest.getSSECustomerKey() == null)) {
                return true;
            }
        } else if (request instanceof PutObjectRequest) {
            PutObjectRequest putObjectRequest = (PutObjectRequest) request;
            ObjectMetadata om = putObjectRequest.getMetadata();
            if ((om == null || om.getSSEAlgorithm() == null) && putObjectRequest.getSSECustomerKey() == null) {
                return false;
            }
            return true;
        } else if (request instanceof UploadPartRequest) {
            if (((UploadPartRequest) request).getSSECustomerKey() == null) {
                return false;
            }
            return true;
        }
        return false;
    }
}
