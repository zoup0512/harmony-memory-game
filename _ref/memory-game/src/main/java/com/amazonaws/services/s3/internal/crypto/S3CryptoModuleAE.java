package com.amazonaws.services.s3.internal.crypto;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.internal.InputSubstream;
import com.amazonaws.services.s3.internal.RepeatableFileInputStream;
import com.amazonaws.services.s3.internal.S3Direct;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.CopyPartRequest;
import com.amazonaws.services.s3.model.CopyPartResult;
import com.amazonaws.services.s3.model.CryptoConfiguration;
import com.amazonaws.services.s3.model.CryptoStorageMode;
import com.amazonaws.services.s3.model.EncryptionMaterialsProvider;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
import com.amazonaws.util.json.JsonUtils;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

class S3CryptoModuleAE extends S3CryptoModuleBase<MultipartUploadCryptoContext> {
    private static final boolean IS_MULTI_PART = true;

    static {
        CryptoRuntime.enableBouncyCastle();
    }

    S3CryptoModuleAE(S3Direct s3, AWSCredentialsProvider credentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, ClientConfiguration clientConfig, CryptoConfiguration cryptoConfig) {
        super(s3, credentialsProvider, encryptionMaterialsProvider, clientConfig, cryptoConfig, new S3CryptoScheme(ContentCryptoScheme.AES_GCM));
    }

    S3CryptoModuleAE(S3Direct s3, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfig) {
        this(s3, new DefaultAWSCredentialsProviderChain(), encryptionMaterialsProvider, new ClientConfiguration(), cryptoConfig);
    }

    protected boolean isStrict() {
        return false;
    }

    protected void securityCheck(ContentCryptoMaterial cekMaterial, S3ObjectWrapper retrieved) {
    }

    public PutObjectResult putObjectSecurely(PutObjectRequest putObjectRequest) throws AmazonClientException, AmazonServiceException {
        appendUserAgent(putObjectRequest, AmazonS3EncryptionClient.USER_AGENT);
        if (this.cryptoConfig.getStorageMode() == CryptoStorageMode.InstructionFile) {
            return putObjectUsingInstructionFile(putObjectRequest);
        }
        return putObjectUsingMetadata(putObjectRequest);
    }

    private PutObjectResult putObjectUsingMetadata(PutObjectRequest req) throws AmazonClientException, AmazonServiceException {
        ContentCryptoMaterial cekMaterial = createContentCryptoMaterial(req);
        PutObjectRequest wrappedReq = wrapWithCipher(req, cekMaterial);
        req.setMetadata(updateMetadataWithContentCryptoMaterial(req.getMetadata(), req.getFile(), cekMaterial));
        return this.s3.putObject(wrappedReq);
    }

    public S3Object getObjectSecurely(GetObjectRequest req) throws AmazonClientException, AmazonServiceException {
        appendUserAgent(req, AmazonS3EncryptionClient.USER_AGENT);
        long[] desiredRange = req.getRange();
        if (!isStrict() || desiredRange == null) {
            long[] adjustedCryptoRange = EncryptionUtils.getAdjustedCryptoRange(desiredRange);
            if (adjustedCryptoRange != null) {
                req.setRange(adjustedCryptoRange[0], adjustedCryptoRange[1]);
            }
            S3Object retrieved = this.s3.getObject(req);
            if (retrieved == null) {
                return null;
            }
            try {
                return decipher(req, desiredRange, adjustedCryptoRange, retrieved);
            } catch (AmazonClientException ace) {
                try {
                    retrieved.getObjectContent().close();
                } catch (Exception e) {
                    this.log.debug("Safely ignoring", e);
                }
                throw ace;
            }
        }
        throw new SecurityException("Range get is not allowed in strict crypto mode");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.amazonaws.services.s3.model.S3Object decipher(com.amazonaws.services.s3.model.GetObjectRequest r9, long[] r10, long[] r11, com.amazonaws.services.s3.model.S3Object r12) {
        /*
        r8 = this;
        r2 = new com.amazonaws.services.s3.internal.crypto.S3ObjectWrapper;
        r2.<init>(r12);
        r3 = r2.hasEncryptionInfo();
        if (r3 == 0) goto L_0x0010;
    L_0x000b:
        r3 = r8.decipherWithMetadata(r10, r11, r2);
    L_0x000f:
        return r3;
    L_0x0010:
        r1 = r8.fetchInstructionFile(r9);
        if (r1 == 0) goto L_0x0031;
    L_0x0016:
        r3 = r1.isInstructionFile();	 Catch:{ all -> 0x006b }
        if (r3 == 0) goto L_0x002a;
    L_0x001c:
        r3 = r8.decipherWithInstructionFile(r10, r11, r2, r1);	 Catch:{ all -> 0x006b }
        r4 = r1.getObjectContent();	 Catch:{ Exception -> 0x0028 }
        r4.close();	 Catch:{ Exception -> 0x0028 }
        goto L_0x000f;
    L_0x0028:
        r4 = move-exception;
        goto L_0x000f;
    L_0x002a:
        r3 = r1.getObjectContent();	 Catch:{ Exception -> 0x009f }
        r3.close();	 Catch:{ Exception -> 0x009f }
    L_0x0031:
        r3 = r8.isStrict();
        if (r3 == 0) goto L_0x0074;
    L_0x0037:
        r2.close();	 Catch:{ IOException -> 0x009b }
    L_0x003a:
        r3 = new java.lang.SecurityException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "S3 object with bucket name: ";
        r4 = r4.append(r5);
        r5 = r12.getBucketName();
        r4 = r4.append(r5);
        r5 = ", key: ";
        r4 = r4.append(r5);
        r5 = r12.getKey();
        r4 = r4.append(r5);
        r5 = " is not encrypted";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.<init>(r4);
        throw r3;
    L_0x006b:
        r3 = move-exception;
        r4 = r1.getObjectContent();	 Catch:{ Exception -> 0x009d }
        r4.close();	 Catch:{ Exception -> 0x009d }
    L_0x0073:
        throw r3;
    L_0x0074:
        r3 = r8.log;
        r4 = "Unable to detect encryption information for object '%s' in bucket '%s'. Returning object without decryption.";
        r5 = 2;
        r5 = new java.lang.Object[r5];
        r6 = 0;
        r7 = r12.getKey();
        r5[r6] = r7;
        r6 = 1;
        r7 = r12.getBucketName();
        r5[r6] = r7;
        r4 = java.lang.String.format(r4, r5);
        r3.warn(r4);
        r3 = 0;
        r0 = r8.adjustToDesiredRange(r2, r10, r3);
        r3 = r0.getS3Object();
        goto L_0x000f;
    L_0x009b:
        r3 = move-exception;
        goto L_0x003a;
    L_0x009d:
        r4 = move-exception;
        goto L_0x0073;
    L_0x009f:
        r3 = move-exception;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.services.s3.internal.crypto.S3CryptoModuleAE.decipher(com.amazonaws.services.s3.model.GetObjectRequest, long[], long[], com.amazonaws.services.s3.model.S3Object):com.amazonaws.services.s3.model.S3Object");
    }

    private S3Object decipherWithInstructionFile(long[] desiredRange, long[] cryptoRange, S3ObjectWrapper retrieved, S3ObjectWrapper instructionFile) {
        Map<String, String> instruction = JsonUtils.jsonToMap(instructionFile.toJsonString());
        ContentCryptoMaterial cekMaterial = ContentCryptoMaterial.fromInstructionFile(instruction, this.kekMaterialsProvider, this.cryptoConfig.getCryptoProvider(), cryptoRange);
        securityCheck(cekMaterial, retrieved);
        return adjustToDesiredRange(decrypt(retrieved, cekMaterial, cryptoRange), desiredRange, instruction).getS3Object();
    }

    private S3Object decipherWithMetadata(long[] desiredRange, long[] cryptoRange, S3ObjectWrapper retrieved) {
        ContentCryptoMaterial cekMaterial = ContentCryptoMaterial.fromObjectMetadata(retrieved.getObjectMetadata(), this.kekMaterialsProvider, this.cryptoConfig.getCryptoProvider(), cryptoRange);
        securityCheck(cekMaterial, retrieved);
        return adjustToDesiredRange(decrypt(retrieved, cekMaterial, cryptoRange), desiredRange, null).getS3Object();
    }

    protected final S3ObjectWrapper adjustToDesiredRange(S3ObjectWrapper s3object, long[] range, Map<String, String> instruction) {
        if (range != null) {
            long maxOffset = (s3object.getObjectMetadata().getInstanceLength() - ((long) (s3object.encryptionSchemeOf(instruction).getTagLengthInBits() / 8))) - 1;
            if (range[1] > maxOffset) {
                range[1] = maxOffset;
                if (range[0] > range[1]) {
                    try {
                        s3object.getObjectContent().close();
                    } catch (IOException ignore) {
                        this.log.trace("", ignore);
                    }
                    s3object.setObjectContent(new ByteArrayInputStream(new byte[0]));
                }
            }
            if (range[0] <= range[1]) {
                try {
                    S3ObjectInputStream objectContent = s3object.getObjectContent();
                    s3object.setObjectContent(new S3ObjectInputStream(new AdjustedRangeInputStream(objectContent, range[0], range[1]), objectContent.getHttpRequest()));
                } catch (IOException e) {
                    throw new AmazonClientException("Error adjusting output to desired byte range: " + e.getMessage());
                }
            }
        }
        return s3object;
    }

    public ObjectMetadata getObjectSecurely(GetObjectRequest getObjectRequest, File destinationFile) throws AmazonClientException, AmazonServiceException {
        IOException e;
        Throwable th;
        assertParameterNotNull(destinationFile, "The destination file parameter must be specified when downloading an object directly to a file");
        S3Object s3Object = getObjectSecurely(getObjectRequest);
        if (s3Object == null) {
            return null;
        }
        OutputStream outputStream = null;
        try {
            OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(destinationFile));
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
                            this.log.debug(e2.getMessage());
                        }
                    }
                }
                outputStream2.close();
                try {
                    s3Object.getObjectContent().close();
                } catch (Exception e22) {
                    this.log.debug(e22.getMessage());
                }
                return s3Object.getObjectMetadata();
            } catch (IOException e3) {
                e = e3;
                outputStream = outputStream2;
                try {
                    throw new AmazonClientException("Unable to store object contents to disk: " + e.getMessage(), e);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        outputStream.close();
                    } catch (Exception e222) {
                        this.log.debug(e222.getMessage());
                    }
                    try {
                        s3Object.getObjectContent().close();
                    } catch (Exception e2222) {
                        this.log.debug(e2222.getMessage());
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
        } catch (IOException e4) {
            e = e4;
            throw new AmazonClientException("Unable to store object contents to disk: " + e.getMessage(), e);
        }
    }

    public CompleteMultipartUploadResult completeMultipartUploadSecurely(CompleteMultipartUploadRequest req) throws AmazonClientException, AmazonServiceException {
        appendUserAgent(req, AmazonS3EncryptionClient.USER_AGENT);
        String uploadId = req.getUploadId();
        MultipartUploadCryptoContext uploadContext = (MultipartUploadCryptoContext) this.multipartUploadContexts.get(uploadId);
        if (uploadContext.hasFinalPartBeenSeen()) {
            CompleteMultipartUploadResult result = this.s3.completeMultipartUpload(req);
            if (this.cryptoConfig.getStorageMode() == CryptoStorageMode.InstructionFile) {
                this.s3.putObject(createInstructionPutRequest(uploadContext.getBucketName(), uploadContext.getKey(), uploadContext.getContentCryptoMaterial()));
            }
            this.multipartUploadContexts.remove(uploadId);
            return result;
        }
        throw new AmazonClientException("Unable to complete an encrypted multipart upload without being told which part was the last.  Without knowing which part was the last, the encrypted data in Amazon S3 is incomplete and corrupt.");
    }

    public InitiateMultipartUploadResult initiateMultipartUploadSecurely(InitiateMultipartUploadRequest req) throws AmazonClientException, AmazonServiceException {
        appendUserAgent(req, AmazonS3EncryptionClient.USER_AGENT);
        ContentCryptoMaterial cekMaterial = createContentCryptoMaterial(req);
        if (this.cryptoConfig.getStorageMode() == CryptoStorageMode.ObjectMetadata) {
            ObjectMetadata metadata = req.getObjectMetadata();
            if (metadata == null) {
                metadata = new ObjectMetadata();
            }
            req.setObjectMetadata(updateMetadataWithContentCryptoMaterial(metadata, null, cekMaterial));
        }
        InitiateMultipartUploadResult result = this.s3.initiateMultipartUpload(req);
        this.multipartUploadContexts.put(result.getUploadId(), new MultipartUploadCryptoContext(req.getBucketName(), req.getKey(), cekMaterial));
        return result;
    }

    public UploadPartResult uploadPartSecurely(UploadPartRequest req) throws AmazonClientException, AmazonServiceException {
        appendUserAgent(req, AmazonS3EncryptionClient.USER_AGENT);
        int blockSize = this.contentCryptoScheme.getBlockSizeInBytes();
        boolean isLastPart = req.isLastPart();
        String uploadId = req.getUploadId();
        long partSize = req.getPartSize();
        boolean partSizeMultipleOfCipherBlockSize = 0 == partSize % ((long) blockSize);
        if (isLastPart || partSizeMultipleOfCipherBlockSize) {
            MultipartUploadCryptoContext uploadContext = (MultipartUploadCryptoContext) this.multipartUploadContexts.get(uploadId);
            if (uploadContext == null) {
                throw new AmazonClientException("No client-side information available on upload ID " + uploadId);
            }
            req.setInputStream(newMultipartS3CipherInputStream(req, uploadContext.getCipherLite()));
            req.setFile(null);
            req.setFileOffset(0);
            if (req.isLastPart()) {
                req.setPartSize(((long) (this.contentCryptoScheme.getTagLengthInBits() / 8)) + partSize);
                if (uploadContext.hasFinalPartBeenSeen()) {
                    throw new AmazonClientException("This part was specified as the last part in a multipart upload, but a previous part was already marked as the last part.  Only the last part of the upload should be marked as the last part.");
                }
                uploadContext.setHasFinalPartBeenSeen(true);
            }
            return this.s3.uploadPart(req);
        }
        throw new AmazonClientException("Invalid part size: part sizes for encrypted multipart uploads must be multiples of the cipher block size (" + blockSize + ") with the exception of the last part.");
    }

    protected final CipherLiteInputStream newMultipartS3CipherInputStream(UploadPartRequest req, CipherLite cipherLite) {
        try {
            InputStream is = req.getInputStream();
            if (req.getFile() != null) {
                is = new InputSubstream(new RepeatableFileInputStream(req.getFile()), req.getFileOffset(), req.getPartSize(), req.isLastPart());
            }
            return new CipherLiteInputStream(is, cipherLite, 2048, true, req.isLastPart());
        } catch (Exception e) {
            throw new AmazonClientException("Unable to create cipher input stream: " + e.getMessage(), e);
        }
    }

    public CopyPartResult copyPartSecurely(CopyPartRequest copyPartRequest) {
        MultipartUploadCryptoContext uploadContext = (MultipartUploadCryptoContext) this.multipartUploadContexts.get(copyPartRequest.getUploadId());
        if (!uploadContext.hasFinalPartBeenSeen()) {
            uploadContext.setHasFinalPartBeenSeen(true);
        }
        return this.s3.copyPart(copyPartRequest);
    }

    private PutObjectResult putObjectUsingInstructionFile(PutObjectRequest putObjectRequest) throws AmazonClientException, AmazonServiceException {
        PutObjectRequest putInstFileRequest = putObjectRequest.clone();
        ContentCryptoMaterial cekMaterial = createContentCryptoMaterial(putObjectRequest);
        PutObjectResult result = this.s3.putObject(wrapWithCipher(putObjectRequest, cekMaterial));
        this.s3.putObject(upateInstructionPutRequest(putInstFileRequest, cekMaterial));
        return result;
    }

    private S3ObjectWrapper decrypt(S3ObjectWrapper wrapper, ContentCryptoMaterial cekMaterial, long[] range) {
        S3ObjectInputStream objectContent = wrapper.getObjectContent();
        wrapper.setObjectContent(new S3ObjectInputStream(new CipherLiteInputStream(objectContent, cekMaterial.getCipherLite(), 2048), objectContent.getHttpRequest()));
        return wrapper;
    }

    private S3ObjectWrapper fetchInstructionFile(GetObjectRequest getObjectRequest) {
        try {
            S3Object o = this.s3.getObject(EncryptionUtils.createInstructionGetRequest(getObjectRequest));
            if (o == null) {
                return null;
            }
            return new S3ObjectWrapper(o);
        } catch (AmazonServiceException e) {
            this.log.debug("Unable to retrieve instruction file : " + e.getMessage());
            return null;
        }
    }

    private void assertParameterNotNull(Object parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    protected final long ciphertextLength(long originalContentLength) {
        return ((long) (this.contentCryptoScheme.getTagLengthInBits() / 8)) + originalContentLength;
    }
}
