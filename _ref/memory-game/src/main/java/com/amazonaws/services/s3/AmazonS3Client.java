package com.amazonaws.services.s3;

import com.amazonaws.AbortedException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonServiceException.ErrorType;
import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.DefaultRequest;
import com.amazonaws.HttpMethod;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.Presigner;
import com.amazonaws.auth.Signer;
import com.amazonaws.auth.SignerFactory;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListenerCallbackExecutor;
import com.amazonaws.event.ProgressReportingInputStream;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.http.UrlHttpClient;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.metrics.AwsSdkMetrics;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.s3.internal.AWSS3V4Signer;
import com.amazonaws.services.s3.internal.BucketNameUtils;
import com.amazonaws.services.s3.internal.Constants;
import com.amazonaws.services.s3.internal.DeleteObjectsResponse;
import com.amazonaws.services.s3.internal.DigestValidationInputStream;
import com.amazonaws.services.s3.internal.InputSubstream;
import com.amazonaws.services.s3.internal.MD5DigestCalculatingInputStream;
import com.amazonaws.services.s3.internal.ObjectExpirationHeaderHandler;
import com.amazonaws.services.s3.internal.RepeatableFileInputStream;
import com.amazonaws.services.s3.internal.ResponseHeaderHandlerChain;
import com.amazonaws.services.s3.internal.S3ErrorResponseHandler;
import com.amazonaws.services.s3.internal.S3ExecutionContext;
import com.amazonaws.services.s3.internal.S3MetadataResponseHandler;
import com.amazonaws.services.s3.internal.S3ObjectResponseHandler;
import com.amazonaws.services.s3.internal.S3QueryStringSigner;
import com.amazonaws.services.s3.internal.S3Signer;
import com.amazonaws.services.s3.internal.S3StringResponseHandler;
import com.amazonaws.services.s3.internal.S3VersionHeaderHandler;
import com.amazonaws.services.s3.internal.S3XmlResponseHandler;
import com.amazonaws.services.s3.internal.ServerSideEncryptionHeaderHandler;
import com.amazonaws.services.s3.internal.ServiceUtils;
import com.amazonaws.services.s3.internal.ServiceUtils.RetryableS3DownloadTask;
import com.amazonaws.services.s3.internal.XmlWriter;
import com.amazonaws.services.s3.metrics.S3ServiceMetric;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.BucketAccelerateConfiguration;
import com.amazonaws.services.s3.model.BucketCrossOriginConfiguration;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.BucketLoggingConfiguration;
import com.amazonaws.services.s3.model.BucketNotificationConfiguration;
import com.amazonaws.services.s3.model.BucketPolicy;
import com.amazonaws.services.s3.model.BucketReplicationConfiguration;
import com.amazonaws.services.s3.model.BucketTaggingConfiguration;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.amazonaws.services.s3.model.BucketWebsiteConfiguration;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.CopyPartRequest;
import com.amazonaws.services.s3.model.CopyPartResult;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteBucketCrossOriginConfigurationRequest;
import com.amazonaws.services.s3.model.DeleteBucketLifecycleConfigurationRequest;
import com.amazonaws.services.s3.model.DeleteBucketPolicyRequest;
import com.amazonaws.services.s3.model.DeleteBucketReplicationConfigurationRequest;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.DeleteBucketTaggingConfigurationRequest;
import com.amazonaws.services.s3.model.DeleteBucketWebsiteConfigurationRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.DeleteVersionRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GenericBucketRequest;
import com.amazonaws.services.s3.model.GetBucketAccelerateConfigurationRequest;
import com.amazonaws.services.s3.model.GetBucketAclRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.services.s3.model.GetBucketPolicyRequest;
import com.amazonaws.services.s3.model.GetBucketReplicationConfigurationRequest;
import com.amazonaws.services.s3.model.GetBucketWebsiteConfigurationRequest;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.GetRequestPaymentConfigurationRequest;
import com.amazonaws.services.s3.model.Grant;
import com.amazonaws.services.s3.model.Grantee;
import com.amazonaws.services.s3.model.HeadBucketRequest;
import com.amazonaws.services.s3.model.HeadBucketResult;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.ListBucketsRequest;
import com.amazonaws.services.s3.model.ListMultipartUploadsRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ListPartsRequest;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.MultiFactorAuthentication;
import com.amazonaws.services.s3.model.MultiObjectDeleteException;
import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Owner;
import com.amazonaws.services.s3.model.PartListing;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.RequestPaymentConfiguration;
import com.amazonaws.services.s3.model.RequestPaymentConfiguration.Payer;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.RestoreObjectRequest;
import com.amazonaws.services.s3.model.S3AccelerateUnsupported;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.SSECustomerKey;
import com.amazonaws.services.s3.model.SetBucketAccelerateConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketAclRequest;
import com.amazonaws.services.s3.model.SetBucketCrossOriginConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketLifecycleConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketLoggingConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketNotificationConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketPolicyRequest;
import com.amazonaws.services.s3.model.SetBucketReplicationConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketTaggingConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketVersioningConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketWebsiteConfigurationRequest;
import com.amazonaws.services.s3.model.SetRequestPaymentConfigurationRequest;
import com.amazonaws.services.s3.model.StorageClass;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
import com.amazonaws.services.s3.model.VersionListing;
import com.amazonaws.services.s3.model.transform.AclXmlFactory;
import com.amazonaws.services.s3.model.transform.BucketConfigurationXmlFactory;
import com.amazonaws.services.s3.model.transform.HeadBucketResultHandler;
import com.amazonaws.services.s3.model.transform.MultiObjectDeleteXmlFactory;
import com.amazonaws.services.s3.model.transform.RequestPaymentConfigurationXmlFactory;
import com.amazonaws.services.s3.model.transform.RequestXmlFactory;
import com.amazonaws.services.s3.model.transform.Unmarshallers.AccessControlListUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketAccelerateConfigurationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketCrossOriginConfigurationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketLifecycleConfigurationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketLocationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketLoggingConfigurationnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketNotificationConfigurationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketReplicationConfigurationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketTaggingConfigurationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketVersioningConfigurationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.BucketWebsiteConfigurationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.CompleteMultipartUploadResultUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.CopyObjectUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.DeleteObjectsResultUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.InitiateMultipartUploadResultUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.ListBucketsOwnerUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.ListBucketsUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.ListMultipartUploadsResultUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.ListObjectsUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.ListObjectsV2Unmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.ListPartsResultUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.RequestPaymentConfigurationUnmarshaller;
import com.amazonaws.services.s3.model.transform.Unmarshallers.VersionListUnmarshaller;
import com.amazonaws.services.s3.model.transform.XmlResponsesSaxParser.CompleteMultipartUploadHandler;
import com.amazonaws.services.s3.model.transform.XmlResponsesSaxParser.CopyObjectResultHandler;
import com.amazonaws.services.s3.util.Mimetypes;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import com.amazonaws.util.AWSRequestMetrics.Field;
import com.amazonaws.util.AwsHostNameUtils;
import com.amazonaws.util.Base64;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.DateUtils;
import com.amazonaws.util.HttpUtils;
import com.amazonaws.util.LengthCheckInputStream;
import com.amazonaws.util.Md5Utils;
import com.amazonaws.util.ServiceClientHolderInputStream;
import com.amazonaws.util.StringUtils;
import com.facebook.places.model.PlaceFields;
import com.yalantis.ucrop.util.FileUtils;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AmazonS3Client extends AmazonWebServiceClient implements AmazonS3 {
    public static final String S3_SERVICE_NAME = "s3";
    private static final String S3_SIGNER = "S3SignerType";
    private static final String S3_V4_SIGNER = "AWSS3V4SignerType";
    private static final BucketConfigurationXmlFactory bucketConfigurationXmlFactory = new BucketConfigurationXmlFactory();
    private static Log log = LogFactory.getLog(AmazonS3Client.class);
    private static final RequestPaymentConfigurationXmlFactory requestPaymentConfigurationXmlFactory = new RequestPaymentConfigurationXmlFactory();
    private final AWSCredentialsProvider awsCredentialsProvider;
    private S3ClientOptions clientOptions;
    volatile String clientRegion;
    private final S3ErrorResponseHandler errorResponseHandler;
    private final S3XmlResponseHandler<Void> voidResponseHandler;

    static {
        AwsSdkMetrics.addAll(Arrays.asList(S3ServiceMetric.values()));
        SignerFactory.registerSigner(S3_SIGNER, S3Signer.class);
        SignerFactory.registerSigner(S3_V4_SIGNER, AWSS3V4Signer.class);
    }

    @Deprecated
    public AmazonS3Client() {
        this(new DefaultAWSCredentialsProviderChain());
    }

    public AmazonS3Client(AWSCredentials awsCredentials) {
        this(awsCredentials, new ClientConfiguration());
    }

    public AmazonS3Client(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(awsCredentials), clientConfiguration);
    }

    public AmazonS3Client(AWSCredentialsProvider credentialsProvider) {
        this(credentialsProvider, new ClientConfiguration());
    }

    public AmazonS3Client(AWSCredentialsProvider credentialsProvider, ClientConfiguration clientConfiguration) {
        this(credentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AmazonS3Client(AWSCredentialsProvider credentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(clientConfiguration, new UrlHttpClient(clientConfiguration), requestMetricCollector);
        this.errorResponseHandler = new S3ErrorResponseHandler();
        this.voidResponseHandler = new S3XmlResponseHandler(null);
        this.clientOptions = new S3ClientOptions();
        this.awsCredentialsProvider = credentialsProvider;
        init();
    }

    public AmazonS3Client(AWSCredentialsProvider credentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(clientConfiguration, httpClient);
        this.errorResponseHandler = new S3ErrorResponseHandler();
        this.voidResponseHandler = new S3XmlResponseHandler(null);
        this.clientOptions = new S3ClientOptions();
        this.awsCredentialsProvider = credentialsProvider;
        init();
    }

    public AmazonS3Client(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    private void init() {
        setEndpoint(Constants.S3_HOSTNAME);
        HandlerChainFactory chainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(chainFactory.newRequestHandlerChain("/com/amazonaws/services/s3/request.handlers"));
        this.requestHandler2s.addAll(chainFactory.newRequestHandler2Chain("/com/amazonaws/services/s3/request.handler2s"));
    }

    public void setEndpoint(String endpoint) {
        if (endpoint.endsWith(Constants.S3_ACCELERATE_HOSTNAME)) {
            throw new IllegalStateException("To enable accelerate mode, please use AmazonS3Client.setS3ClientOptions(S3ClientOptions.builder().setAccelerateModeEnabled(true).build());");
        }
        super.setEndpoint(endpoint);
        if (!endpoint.endsWith(Constants.S3_HOSTNAME)) {
            this.clientRegion = AwsHostNameUtils.parseRegionName(this.endpoint.getHost(), "s3");
        }
    }

    public void setRegion(Region region) {
        super.setRegion(region);
        this.clientRegion = region.getName();
    }

    public void setS3ClientOptions(S3ClientOptions clientOptions) {
        this.clientOptions = new S3ClientOptions(clientOptions);
    }

    public VersionListing listNextBatchOfVersions(VersionListing previousVersionListing) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(previousVersionListing, "The previous version listing parameter must be specified when listing the next batch of versions in a bucket");
        if (previousVersionListing.isTruncated()) {
            return listVersions(new ListVersionsRequest(previousVersionListing.getBucketName(), previousVersionListing.getPrefix(), previousVersionListing.getNextKeyMarker(), previousVersionListing.getNextVersionIdMarker(), previousVersionListing.getDelimiter(), new Integer(previousVersionListing.getMaxKeys())).withEncodingType(previousVersionListing.getEncodingType()));
        }
        VersionListing emptyListing = new VersionListing();
        emptyListing.setBucketName(previousVersionListing.getBucketName());
        emptyListing.setDelimiter(previousVersionListing.getDelimiter());
        emptyListing.setKeyMarker(previousVersionListing.getNextKeyMarker());
        emptyListing.setVersionIdMarker(previousVersionListing.getNextVersionIdMarker());
        emptyListing.setMaxKeys(previousVersionListing.getMaxKeys());
        emptyListing.setPrefix(previousVersionListing.getPrefix());
        emptyListing.setEncodingType(previousVersionListing.getEncodingType());
        emptyListing.setTruncated(false);
        return emptyListing;
    }

    public VersionListing listVersions(String bucketName, String prefix) throws AmazonClientException, AmazonServiceException {
        return listVersions(new ListVersionsRequest(bucketName, prefix, null, null, null, null));
    }

    public VersionListing listVersions(String bucketName, String prefix, String keyMarker, String versionIdMarker, String delimiter, Integer maxKeys) throws AmazonClientException, AmazonServiceException {
        return listVersions(new ListVersionsRequest().withBucketName(bucketName).withPrefix(prefix).withDelimiter(delimiter).withKeyMarker(keyMarker).withVersionIdMarker(versionIdMarker).withMaxResults(maxKeys));
    }

    public VersionListing listVersions(ListVersionsRequest listVersionsRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(listVersionsRequest.getBucketName(), "The bucket name parameter must be specified when listing versions in a bucket");
        Request request = createRequest(listVersionsRequest.getBucketName(), null, listVersionsRequest, HttpMethodName.GET);
        request.addParameter("versions", null);
        if (listVersionsRequest.getPrefix() != null) {
            request.addParameter("prefix", listVersionsRequest.getPrefix());
        }
        if (listVersionsRequest.getKeyMarker() != null) {
            request.addParameter("key-marker", listVersionsRequest.getKeyMarker());
        }
        if (listVersionsRequest.getVersionIdMarker() != null) {
            request.addParameter("version-id-marker", listVersionsRequest.getVersionIdMarker());
        }
        if (listVersionsRequest.getDelimiter() != null) {
            request.addParameter("delimiter", listVersionsRequest.getDelimiter());
        }
        if (listVersionsRequest.getMaxResults() != null && listVersionsRequest.getMaxResults().intValue() >= 0) {
            request.addParameter("max-keys", listVersionsRequest.getMaxResults().toString());
        }
        if (listVersionsRequest.getEncodingType() != null) {
            request.addParameter("encoding-type", listVersionsRequest.getEncodingType());
        }
        return (VersionListing) invoke(request, new VersionListUnmarshaller(), listVersionsRequest.getBucketName(), null);
    }

    public ObjectListing listObjects(String bucketName) throws AmazonClientException, AmazonServiceException {
        return listObjects(new ListObjectsRequest(bucketName, null, null, null, null));
    }

    public ObjectListing listObjects(String bucketName, String prefix) throws AmazonClientException, AmazonServiceException {
        return listObjects(new ListObjectsRequest(bucketName, prefix, null, null, null));
    }

    public ObjectListing listObjects(ListObjectsRequest listObjectsRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(listObjectsRequest.getBucketName(), "The bucket name parameter must be specified when listing objects in a bucket");
        Request request = createRequest(listObjectsRequest.getBucketName(), null, listObjectsRequest, HttpMethodName.GET);
        if (listObjectsRequest.getPrefix() != null) {
            request.addParameter("prefix", listObjectsRequest.getPrefix());
        }
        if (listObjectsRequest.getMarker() != null) {
            request.addParameter("marker", listObjectsRequest.getMarker());
        }
        if (listObjectsRequest.getDelimiter() != null) {
            request.addParameter("delimiter", listObjectsRequest.getDelimiter());
        }
        if (listObjectsRequest.getMaxKeys() != null && listObjectsRequest.getMaxKeys().intValue() >= 0) {
            request.addParameter("max-keys", listObjectsRequest.getMaxKeys().toString());
        }
        if (listObjectsRequest.getEncodingType() != null) {
            request.addParameter("encoding-type", listObjectsRequest.getEncodingType());
        }
        return (ObjectListing) invoke(request, new ListObjectsUnmarshaller(), listObjectsRequest.getBucketName(), null);
    }

    public ListObjectsV2Result listObjectsV2(String bucketName) throws AmazonClientException, AmazonServiceException {
        return listObjectsV2(new ListObjectsV2Request().withBucketName(bucketName));
    }

    public ListObjectsV2Result listObjectsV2(String bucketName, String prefix) throws AmazonClientException, AmazonServiceException {
        return listObjectsV2(new ListObjectsV2Request().withBucketName(bucketName).withPrefix(prefix));
    }

    public ListObjectsV2Result listObjectsV2(ListObjectsV2Request listObjectsV2Request) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(listObjectsV2Request.getBucketName(), "The bucket name parameter must be specified when listing objects in a bucket");
        Request request = createRequest(listObjectsV2Request.getBucketName(), null, listObjectsV2Request, HttpMethodName.GET);
        request.addParameter("list-type", "2");
        if (listObjectsV2Request.getStartAfter() != null) {
            request.addParameter("start-after", listObjectsV2Request.getStartAfter());
        }
        if (listObjectsV2Request.getContinuationToken() != null) {
            request.addParameter("continuation-token", listObjectsV2Request.getContinuationToken());
        }
        if (listObjectsV2Request.getDelimiter() != null) {
            request.addParameter("delimiter", listObjectsV2Request.getDelimiter());
        }
        if (listObjectsV2Request.getMaxKeys() != null && listObjectsV2Request.getMaxKeys().intValue() >= 0) {
            request.addParameter("max-keys", listObjectsV2Request.getMaxKeys().toString());
        }
        if (listObjectsV2Request.getPrefix() != null) {
            request.addParameter("prefix", listObjectsV2Request.getPrefix());
        }
        if (listObjectsV2Request.getEncodingType() != null) {
            request.addParameter("encoding-type", listObjectsV2Request.getEncodingType());
        }
        if (listObjectsV2Request.isFetchOwner()) {
            request.addParameter("fetch-owner", Boolean.toString(listObjectsV2Request.isFetchOwner()));
        }
        return (ListObjectsV2Result) invoke(request, new ListObjectsV2Unmarshaller(), listObjectsV2Request.getBucketName(), null);
    }

    public ObjectListing listNextBatchOfObjects(ObjectListing previousObjectListing) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(previousObjectListing, "The previous object listing parameter must be specified when listing the next batch of objects in a bucket");
        if (previousObjectListing.isTruncated()) {
            return listObjects(new ListObjectsRequest(previousObjectListing.getBucketName(), previousObjectListing.getPrefix(), previousObjectListing.getNextMarker(), previousObjectListing.getDelimiter(), new Integer(previousObjectListing.getMaxKeys())).withEncodingType(previousObjectListing.getEncodingType()));
        }
        ObjectListing emptyListing = new ObjectListing();
        emptyListing.setBucketName(previousObjectListing.getBucketName());
        emptyListing.setDelimiter(previousObjectListing.getDelimiter());
        emptyListing.setMarker(previousObjectListing.getNextMarker());
        emptyListing.setMaxKeys(previousObjectListing.getMaxKeys());
        emptyListing.setPrefix(previousObjectListing.getPrefix());
        emptyListing.setEncodingType(previousObjectListing.getEncodingType());
        emptyListing.setTruncated(false);
        return emptyListing;
    }

    public Owner getS3AccountOwner() throws AmazonClientException, AmazonServiceException {
        return (Owner) invoke(createRequest(null, null, new ListBucketsRequest(), HttpMethodName.GET), new ListBucketsOwnerUnmarshaller(), null, null);
    }

    public List<Bucket> listBuckets(ListBucketsRequest listBucketsRequest) throws AmazonClientException, AmazonServiceException {
        return (List) invoke(createRequest(null, null, listBucketsRequest, HttpMethodName.GET), new ListBucketsUnmarshaller(), null, null);
    }

    public List<Bucket> listBuckets() throws AmazonClientException, AmazonServiceException {
        return listBuckets(new ListBucketsRequest());
    }

    public String getBucketLocation(GetBucketLocationRequest getBucketLocationRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(getBucketLocationRequest, "The request parameter must be specified when requesting a bucket's location");
        String bucketName = getBucketLocationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting a bucket's location");
        Request request = createRequest(bucketName, null, getBucketLocationRequest, HttpMethodName.GET);
        request.addParameter("location", null);
        return (String) invoke(request, new BucketLocationUnmarshaller(), bucketName, null);
    }

    public String getBucketLocation(String bucketName) throws AmazonClientException, AmazonServiceException {
        return getBucketLocation(new GetBucketLocationRequest(bucketName));
    }

    public Bucket createBucket(String bucketName) throws AmazonClientException, AmazonServiceException {
        return createBucket(new CreateBucketRequest(bucketName));
    }

    public Bucket createBucket(String bucketName, com.amazonaws.services.s3.model.Region region) throws AmazonClientException, AmazonServiceException {
        return createBucket(new CreateBucketRequest(bucketName, region));
    }

    public Bucket createBucket(String bucketName, String region) throws AmazonClientException, AmazonServiceException {
        return createBucket(new CreateBucketRequest(bucketName, region));
    }

    public Bucket createBucket(CreateBucketRequest createBucketRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(createBucketRequest, "The CreateBucketRequest parameter must be specified when creating a bucket");
        String bucketName = createBucketRequest.getBucketName();
        String region = createBucketRequest.getRegion();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when creating a bucket");
        if (bucketName != null) {
            bucketName = bucketName.trim();
        }
        BucketNameUtils.validateBucketName(bucketName);
        Request request = createRequest(bucketName, null, createBucketRequest, HttpMethodName.PUT);
        if (createBucketRequest.getAccessControlList() != null) {
            addAclHeaders(request, createBucketRequest.getAccessControlList());
        } else if (createBucketRequest.getCannedAcl() != null) {
            request.addHeader(Headers.S3_CANNED_ACL, createBucketRequest.getCannedAcl().toString());
        }
        if (!this.endpoint.getHost().equals(Constants.S3_HOSTNAME) && (region == null || region.isEmpty())) {
            try {
                region = RegionUtils.getRegionByEndpoint(this.endpoint.getHost()).getName();
            } catch (IllegalArgumentException e) {
            }
        }
        if (!(region == null || StringUtils.upperCase(region).equals(com.amazonaws.services.s3.model.Region.US_Standard.toString()))) {
            XmlWriter xml = new XmlWriter();
            xml.start("CreateBucketConfiguration", "xmlns", Constants.XML_NAMESPACE);
            xml.start("LocationConstraint").value(region).end();
            xml.end();
            byte[] bytes = xml.getBytes();
            request.addHeader("Content-Length", String.valueOf(bytes.length));
            request.setContent(new ByteArrayInputStream(bytes));
        }
        invoke(request, this.voidResponseHandler, bucketName, null);
        return new Bucket(bucketName);
    }

    public AccessControlList getObjectAcl(String bucketName, String key) throws AmazonClientException, AmazonServiceException {
        return getObjectAcl(bucketName, key, null);
    }

    public AccessControlList getObjectAcl(String bucketName, String key, String versionId) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting an object's ACL");
        assertParameterNotNull(key, "The key parameter must be specified when requesting an object's ACL");
        return getAcl(bucketName, key, versionId, null);
    }

    public void setObjectAcl(String bucketName, String key, AccessControlList acl) throws AmazonClientException, AmazonServiceException {
        setObjectAcl(bucketName, key, null, acl);
    }

    public void setObjectAcl(String bucketName, String key, CannedAccessControlList acl) throws AmazonClientException, AmazonServiceException {
        setObjectAcl(bucketName, key, null, acl);
    }

    public void setObjectAcl(String bucketName, String key, String versionId, AccessControlList acl) throws AmazonClientException, AmazonServiceException {
        setObjectAcl0(bucketName, key, versionId, acl, null);
    }

    public void setObjectAcl(String bucketName, String key, String versionId, AccessControlList acl, RequestMetricCollector requestMetricCollector) throws AmazonClientException, AmazonServiceException {
        setObjectAcl0(bucketName, key, versionId, acl, requestMetricCollector);
    }

    private void setObjectAcl0(String bucketName, String key, String versionId, AccessControlList acl, RequestMetricCollector requestMetricCollector) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting an object's ACL");
        assertParameterNotNull(key, "The key parameter must be specified when setting an object's ACL");
        assertParameterNotNull(acl, "The ACL parameter must be specified when setting an object's ACL");
        setAcl(bucketName, key, versionId, acl, new GenericBucketRequest(bucketName).withRequestMetricCollector(requestMetricCollector));
    }

    public void setObjectAcl(String bucketName, String key, String versionId, CannedAccessControlList acl) throws AmazonClientException, AmazonServiceException {
        setObjectAcl0(bucketName, key, versionId, acl, null);
    }

    public void setObjectAcl(String bucketName, String key, String versionId, CannedAccessControlList acl, RequestMetricCollector requestMetricCollector) {
        setObjectAcl0(bucketName, key, versionId, acl, requestMetricCollector);
    }

    private void setObjectAcl0(String bucketName, String key, String versionId, CannedAccessControlList acl, RequestMetricCollector requestMetricCollector) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting an object's ACL");
        assertParameterNotNull(key, "The key parameter must be specified when setting an object's ACL");
        assertParameterNotNull(acl, "The ACL parameter must be specified when setting an object's ACL");
        setAcl(bucketName, key, versionId, acl, new GenericBucketRequest(bucketName).withRequestMetricCollector(requestMetricCollector));
    }

    public AccessControlList getBucketAcl(String bucketName) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting a bucket's ACL");
        return getAcl(bucketName, null, null, null);
    }

    public AccessControlList getBucketAcl(GetBucketAclRequest getBucketAclRequest) throws AmazonClientException, AmazonServiceException {
        String bucketName = getBucketAclRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting a bucket's ACL");
        return getAcl(bucketName, null, null, getBucketAclRequest);
    }

    public void setBucketAcl(String bucketName, AccessControlList acl) throws AmazonClientException, AmazonServiceException {
        setBucketAcl0(bucketName, acl, null);
    }

    public void setBucketAcl(String bucketName, AccessControlList acl, RequestMetricCollector requestMetricCollector) {
        setBucketAcl0(bucketName, acl, requestMetricCollector);
    }

    private void setBucketAcl0(String bucketName, AccessControlList acl, RequestMetricCollector requestMetricCollector) {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting a bucket's ACL");
        assertParameterNotNull(acl, "The ACL parameter must be specified when setting a bucket's ACL");
        setAcl(bucketName, null, null, acl, new GenericBucketRequest(bucketName).withRequestMetricCollector(requestMetricCollector));
    }

    public void setBucketAcl(SetBucketAclRequest setBucketAclRequest) throws AmazonClientException, AmazonServiceException {
        String bucketName = setBucketAclRequest.getBucketName();
        AccessControlList acl = setBucketAclRequest.getAcl();
        CannedAccessControlList cannedAcl = setBucketAclRequest.getCannedAcl();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting a bucket's ACL");
        if (acl != null) {
            setAcl(bucketName, null, null, acl, (AmazonWebServiceRequest) setBucketAclRequest);
        } else if (cannedAcl != null) {
            setAcl(bucketName, null, null, cannedAcl, (AmazonWebServiceRequest) setBucketAclRequest);
        } else {
            assertParameterNotNull(null, "The ACL parameter must be specified when setting a bucket's ACL");
        }
    }

    public void setBucketAcl(String bucketName, CannedAccessControlList acl) throws AmazonClientException, AmazonServiceException {
        setBucketAcl0(bucketName, acl, null);
    }

    public void setBucketAcl(String bucketName, CannedAccessControlList acl, RequestMetricCollector requestMetricCollector) throws AmazonClientException, AmazonServiceException {
        setBucketAcl0(bucketName, acl, requestMetricCollector);
    }

    private void setBucketAcl0(String bucketName, CannedAccessControlList acl, RequestMetricCollector col) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting a bucket's ACL");
        assertParameterNotNull(acl, "The ACL parameter must be specified when setting a bucket's ACL");
        setAcl(bucketName, null, null, acl, new GenericBucketRequest(bucketName).withRequestMetricCollector(col));
    }

    public ObjectMetadata getObjectMetadata(String bucketName, String key) throws AmazonClientException, AmazonServiceException {
        return getObjectMetadata(new GetObjectMetadataRequest(bucketName, key));
    }

    public ObjectMetadata getObjectMetadata(GetObjectMetadataRequest getObjectMetadataRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(getObjectMetadataRequest, "The GetObjectMetadataRequest parameter must be specified when requesting an object's metadata");
        String bucketName = getObjectMetadataRequest.getBucketName();
        String key = getObjectMetadataRequest.getKey();
        String versionId = getObjectMetadataRequest.getVersionId();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting an object's metadata");
        assertParameterNotNull(key, "The key parameter must be specified when requesting an object's metadata");
        Request request = createRequest(bucketName, key, getObjectMetadataRequest, HttpMethodName.HEAD);
        if (versionId != null) {
            request.addParameter("versionId", versionId);
        }
        populateSseCpkRequestParameters(request, getObjectMetadataRequest.getSSECustomerKey());
        return (ObjectMetadata) invoke(request, new S3MetadataResponseHandler(), bucketName, key);
    }

    public S3Object getObject(String bucketName, String key) throws AmazonClientException, AmazonServiceException {
        return getObject(new GetObjectRequest(bucketName, key));
    }

    public boolean doesBucketExist(String bucketName) throws AmazonClientException, AmazonServiceException {
        try {
            headBucket(new HeadBucketRequest(bucketName));
            return true;
        } catch (AmazonServiceException ase) {
            if (ase.getStatusCode() == 301 || ase.getStatusCode() == 403) {
                return true;
            }
            if (ase.getStatusCode() == Constants.NO_SUCH_BUCKET_STATUS_CODE) {
                return false;
            }
            throw ase;
        }
    }

    public boolean doesObjectExist(String bucketName, String objectName) throws AmazonServiceException, AmazonClientException {
        try {
            getObjectMetadata(bucketName, objectName);
            return true;
        } catch (AmazonS3Exception e) {
            if (e.getStatusCode() == Constants.NO_SUCH_BUCKET_STATUS_CODE) {
                return false;
            }
            throw e;
        }
    }

    public HeadBucketResult headBucket(HeadBucketRequest headBucketRequest) throws AmazonClientException, AmazonServiceException {
        String bucketName = headBucketRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucketName parameter must be specified.");
        return (HeadBucketResult) invoke(createRequest(bucketName, null, headBucketRequest, HttpMethodName.HEAD), new HeadBucketResultHandler(), bucketName, null);
    }

    public void changeObjectStorageClass(String bucketName, String key, StorageClass newStorageClass) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucketName parameter must be specified when changing an object's storage class");
        assertParameterNotNull(key, "The key parameter must be specified when changing an object's storage class");
        assertParameterNotNull(newStorageClass, "The newStorageClass parameter must be specified when changing an object's storage class");
        copyObject(new CopyObjectRequest(bucketName, key, bucketName, key).withStorageClass(newStorageClass.toString()));
    }

    public void setObjectRedirectLocation(String bucketName, String key, String newRedirectLocation) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucketName parameter must be specified when changing an object's storage class");
        assertParameterNotNull(key, "The key parameter must be specified when changing an object's storage class");
        assertParameterNotNull(newRedirectLocation, "The newStorageClass parameter must be specified when changing an object's storage class");
        copyObject(new CopyObjectRequest(bucketName, key, bucketName, key).withRedirectLocation(newRedirectLocation));
    }

    public S3Object getObject(GetObjectRequest getObjectRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(getObjectRequest, "The GetObjectRequest parameter must be specified when requesting an object");
        assertParameterNotNull(getObjectRequest.getBucketName(), "The bucket name parameter must be specified when requesting an object");
        assertParameterNotNull(getObjectRequest.getKey(), "The key parameter must be specified when requesting an object");
        Request<GetObjectRequest> request = createRequest(getObjectRequest.getBucketName(), getObjectRequest.getKey(), getObjectRequest, HttpMethodName.GET);
        if (getObjectRequest.getVersionId() != null) {
            request.addParameter("versionId", getObjectRequest.getVersionId());
        }
        long[] range = getObjectRequest.getRange();
        if (range != null) {
            String rangeHeader = "bytes=" + Long.toString(range[0]) + "-";
            if (range[1] >= 0) {
                rangeHeader = rangeHeader + Long.toString(range[1]);
            }
            request.addHeader(Headers.RANGE, rangeHeader);
        }
        if (getObjectRequest.isRequesterPays()) {
            request.addHeader(Headers.REQUESTER_PAYS_HEADER, Constants.REQUESTER_PAYS);
        }
        addResponseHeaderParameters(request, getObjectRequest.getResponseHeaders());
        addDateHeader(request, Headers.GET_OBJECT_IF_MODIFIED_SINCE, getObjectRequest.getModifiedSinceConstraint());
        addDateHeader(request, Headers.GET_OBJECT_IF_UNMODIFIED_SINCE, getObjectRequest.getUnmodifiedSinceConstraint());
        addStringListHeader(request, Headers.GET_OBJECT_IF_MATCH, getObjectRequest.getMatchingETagConstraints());
        addStringListHeader(request, "If-None-Match", getObjectRequest.getNonmatchingETagConstraints());
        populateSseCpkRequestParameters(request, getObjectRequest.getSSECustomerKey());
        ProgressListenerCallbackExecutor progressListenerCallbackExecutor = ProgressListenerCallbackExecutor.wrapListener(getObjectRequest.getGeneralProgressListener());
        try {
            InputStream input;
            S3Object s3Object = (S3Object) invoke((Request) request, (HttpResponseHandler) new S3ObjectResponseHandler(), getObjectRequest.getBucketName(), getObjectRequest.getKey());
            s3Object.setBucketName(getObjectRequest.getBucketName());
            s3Object.setKey(getObjectRequest.getKey());
            InputStream input2 = new ServiceClientHolderInputStream(s3Object.getObjectContent(), this);
            if (progressListenerCallbackExecutor != null) {
                InputStream progressReportingInputStream = new ProgressReportingInputStream(input2, progressListenerCallbackExecutor);
                progressReportingInputStream.setFireCompletedEvent(true);
                input = progressReportingInputStream;
                fireProgressEvent(progressListenerCallbackExecutor, 2);
                input2 = input;
            }
            if (ServiceUtils.skipMd5CheckPerRequest(getObjectRequest) || ServiceUtils.skipMd5CheckPerResponse(s3Object.getObjectMetadata())) {
                input = new LengthCheckInputStream(input2, s3Object.getObjectMetadata().getContentLength(), true);
            } else {
                String etag = s3Object.getObjectMetadata().getETag();
                if (!(etag == null || ServiceUtils.isMultipartUploadETag(etag))) {
                    try {
                        input = new DigestValidationInputStream(input2, MessageDigest.getInstance(CommonUtils.MD5_INSTANCE), BinaryUtils.fromHex(s3Object.getObjectMetadata().getETag()));
                    } catch (NoSuchAlgorithmException e) {
                        log.warn("No MD5 digest algorithm available.  Unable to calculate checksum and verify data integrity.", e);
                    }
                }
                input = input2;
            }
            s3Object.setObjectContent(new S3ObjectInputStream(input));
            return s3Object;
        } catch (AmazonS3Exception ase) {
            if (ase.getStatusCode() == 412 || ase.getStatusCode() == 304) {
                fireProgressEvent(progressListenerCallbackExecutor, 16);
                return null;
            }
            fireProgressEvent(progressListenerCallbackExecutor, 8);
            throw ase;
        }
    }

    public ObjectMetadata getObject(final GetObjectRequest getObjectRequest, File destinationFile) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(destinationFile, "The destination file parameter must be specified when downloading an object directly to a file");
        boolean mode = false;
        if (getObjectRequest.getRange() != null && getObjectRequest.getRange()[0] > 0) {
            mode = true;
        }
        S3Object s3Object = ServiceUtils.retryableDownloadS3ObjectToFile(destinationFile, new RetryableS3DownloadTask() {
            public S3Object getS3ObjectStream() {
                return AmazonS3Client.this.getObject(getObjectRequest);
            }

            public boolean needIntegrityCheck() {
                return !ServiceUtils.skipMd5CheckPerRequest(getObjectRequest);
            }
        }, mode);
        if (s3Object == null) {
            return null;
        }
        return s3Object.getObjectMetadata();
    }

    public void deleteBucket(String bucketName) throws AmazonClientException, AmazonServiceException {
        deleteBucket(new DeleteBucketRequest(bucketName));
    }

    public void deleteBucket(DeleteBucketRequest deleteBucketRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(deleteBucketRequest, "The DeleteBucketRequest parameter must be specified when deleting a bucket");
        String bucketName = deleteBucketRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when deleting a bucket");
        invoke(createRequest(bucketName, null, deleteBucketRequest, HttpMethodName.DELETE), this.voidResponseHandler, bucketName, null);
    }

    public PutObjectResult putObject(String bucketName, String key, File file) throws AmazonClientException, AmazonServiceException {
        return putObject(new PutObjectRequest(bucketName, key, file).withMetadata(new ObjectMetadata()));
    }

    public PutObjectResult putObject(String bucketName, String key, InputStream input, ObjectMetadata metadata) throws AmazonClientException, AmazonServiceException {
        return putObject(new PutObjectRequest(bucketName, key, input, metadata));
    }

    public PutObjectResult putObject(PutObjectRequest putObjectRequest) throws AmazonClientException, AmazonServiceException {
        InputStream input;
        assertParameterNotNull(putObjectRequest, "The PutObjectRequest parameter must be specified when uploading an object");
        String bucketName = putObjectRequest.getBucketName();
        String key = putObjectRequest.getKey();
        ObjectMetadata metadata = putObjectRequest.getMetadata();
        InputStream inputStream = putObjectRequest.getInputStream();
        ProgressListenerCallbackExecutor progressListenerCallbackExecutor = ProgressListenerCallbackExecutor.wrapListener(putObjectRequest.getGeneralProgressListener());
        if (metadata == null) {
            metadata = new ObjectMetadata();
        }
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when uploading an object");
        assertParameterNotNull(key, "The key parameter must be specified when uploading an object");
        boolean skipContentMd5Check = ServiceUtils.skipMd5CheckPerRequest(putObjectRequest);
        if (putObjectRequest.getFile() != null) {
            File file = putObjectRequest.getFile();
            metadata.setContentLength(file.length());
            boolean calculateMD5 = metadata.getContentMD5() == null;
            if (metadata.getContentType() == null) {
                metadata.setContentType(Mimetypes.getInstance().getMimetype(file));
            }
            if (calculateMD5 && !skipContentMd5Check) {
                try {
                    metadata.setContentMD5(Md5Utils.md5AsBase64(file));
                } catch (Exception e) {
                    throw new AmazonClientException("Unable to calculate MD5 hash: " + e.getMessage(), e);
                }
            }
            try {
                InputStream repeatableFileInputStream = new RepeatableFileInputStream(file);
            } catch (Throwable fnfe) {
                throw new AmazonClientException("Unable to find file to upload", fnfe);
            }
        }
        Request<PutObjectRequest> request = createRequest(bucketName, key, putObjectRequest, HttpMethodName.PUT);
        if (putObjectRequest.getAccessControlList() != null) {
            addAclHeaders(request, putObjectRequest.getAccessControlList());
        } else if (putObjectRequest.getCannedAcl() != null) {
            request.addHeader(Headers.S3_CANNED_ACL, putObjectRequest.getCannedAcl().toString());
        }
        if (putObjectRequest.getStorageClass() != null) {
            request.addHeader(Headers.STORAGE_CLASS, putObjectRequest.getStorageClass());
        }
        if (putObjectRequest.getRedirectLocation() != null) {
            request.addHeader(Headers.REDIRECT_LOCATION, putObjectRequest.getRedirectLocation());
            if (inputStream == null) {
                setZeroContentLength(request);
                repeatableFileInputStream = new ByteArrayInputStream(new byte[0]);
            }
        }
        populateSseCpkRequestParameters(request, putObjectRequest.getSSECustomerKey());
        Long contentLength = (Long) metadata.getRawMetadataValue("Content-Length");
        if (contentLength != null) {
            long expectedLength = contentLength.longValue();
            if (expectedLength >= 0) {
                inputStream = new LengthCheckInputStream(inputStream, expectedLength, false);
                request.addHeader("Content-Length", contentLength.toString());
            }
            input = inputStream;
        } else if (inputStream.markSupported()) {
            request.addHeader("Content-Length", String.valueOf(calculateContentLength(inputStream)));
            input = inputStream;
        } else {
            log.warn("No content length specified for stream data.  Stream contents will be buffered in memory and could result in out of memory errors.");
            InputStream bais = toByteArray(inputStream);
            request.addHeader("Content-Length", String.valueOf(bais.available()));
            input = bais;
        }
        if (progressListenerCallbackExecutor != null) {
            repeatableFileInputStream = new ProgressReportingInputStream(input, progressListenerCallbackExecutor);
            fireProgressEvent(progressListenerCallbackExecutor, 2);
        } else {
            inputStream = input;
        }
        MD5DigestCalculatingInputStream md5DigestStream = null;
        if (metadata.getContentMD5() == null && !skipContentMd5Check) {
            MD5DigestCalculatingInputStream mD5DigestCalculatingInputStream = new MD5DigestCalculatingInputStream(inputStream);
            inputStream = mD5DigestCalculatingInputStream;
        }
        if (metadata.getContentType() == null) {
            metadata.setContentType(Mimetypes.MIMETYPE_OCTET_STREAM);
        }
        populateRequestMetadata(request, metadata);
        request.setContent(inputStream);
        request.addHeader(HttpHeader.EXPECT, "100-continue");
        try {
            ObjectMetadata returnedMetadata = (ObjectMetadata) invoke((Request) request, (HttpResponseHandler) new S3MetadataResponseHandler(), bucketName, key);
            try {
                inputStream.close();
            } catch (AbortedException e2) {
            } catch (Exception e3) {
                log.debug("Unable to cleanly close input stream: " + e3.getMessage(), e3);
            }
            String contentMd5 = metadata.getContentMD5();
            if (md5DigestStream != null) {
                contentMd5 = BinaryUtils.toBase64(md5DigestStream.getMd5Digest());
            }
            if (returnedMetadata == null || contentMd5 == null || skipContentMd5Check || Arrays.equals(BinaryUtils.fromBase64(contentMd5), BinaryUtils.fromHex(returnedMetadata.getETag()))) {
                fireProgressEvent(progressListenerCallbackExecutor, 4);
                PutObjectResult result = new PutObjectResult();
                result.setETag(returnedMetadata.getETag());
                result.setVersionId(returnedMetadata.getVersionId());
                result.setSSEAlgorithm(returnedMetadata.getSSEAlgorithm());
                result.setSSEKMSKeyId(returnedMetadata.getSSEKMSKeyId());
                result.setSSECustomerAlgorithm(returnedMetadata.getSSECustomerAlgorithm());
                result.setSSECustomerKeyMd5(returnedMetadata.getSSECustomerKeyMd5());
                result.setExpirationTime(returnedMetadata.getExpirationTime());
                result.setExpirationTimeRuleId(returnedMetadata.getExpirationTimeRuleId());
                result.setContentMd5(contentMd5);
                return result;
            }
            fireProgressEvent(progressListenerCallbackExecutor, 8);
            throw new AmazonClientException("Unable to verify integrity of data upload.  Client calculated content hash didn't match hash calculated by Amazon S3.  You may need to delete the data stored in Amazon S3.");
        } catch (AmazonClientException ace) {
            fireProgressEvent(progressListenerCallbackExecutor, 8);
            throw ace;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (AbortedException e4) {
            } catch (Exception e32) {
                log.debug("Unable to cleanly close input stream: " + e32.getMessage(), e32);
            }
        }
    }

    private long calculateContentLength(InputStream is) {
        long len = 0;
        byte[] buf = new byte[8192];
        is.mark(-1);
        while (true) {
            try {
                int read = is.read(buf);
                if (read != -1) {
                    len += (long) read;
                } else {
                    is.reset();
                    return len;
                }
            } catch (IOException ioe) {
                throw new AmazonClientException("Could not calculate content length.", ioe);
            }
        }
    }

    private static void addAclHeaders(Request<? extends AmazonWebServiceRequest> request, AccessControlList acl) {
        Set<Grant> grants = acl.getGrants();
        Map<Permission, Collection<Grantee>> grantsByPermission = new HashMap();
        for (Grant grant : grants) {
            if (!grantsByPermission.containsKey(grant.getPermission())) {
                grantsByPermission.put(grant.getPermission(), new LinkedList());
            }
            ((Collection) grantsByPermission.get(grant.getPermission())).add(grant.getGrantee());
        }
        for (Permission permission : Permission.values()) {
            if (grantsByPermission.containsKey(permission)) {
                Collection<Grantee> grantees = (Collection) grantsByPermission.get(permission);
                boolean seenOne = false;
                StringBuilder granteeString = new StringBuilder();
                for (Grantee grantee : grantees) {
                    if (seenOne) {
                        granteeString.append(", ");
                    } else {
                        seenOne = true;
                    }
                    granteeString.append(grantee.getTypeIdentifier()).append("=").append("\"").append(grantee.getIdentifier()).append("\"");
                }
                request.addHeader(permission.getHeaderName(), granteeString.toString());
            }
        }
    }

    public CopyObjectResult copyObject(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey) throws AmazonClientException, AmazonServiceException {
        return copyObject(new CopyObjectRequest(sourceBucketName, sourceKey, destinationBucketName, destinationKey));
    }

    public CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(copyObjectRequest.getSourceBucketName(), "The source bucket name must be specified when copying an object");
        assertParameterNotNull(copyObjectRequest.getSourceKey(), "The source object key must be specified when copying an object");
        assertParameterNotNull(copyObjectRequest.getDestinationBucketName(), "The destination bucket name must be specified when copying an object");
        assertParameterNotNull(copyObjectRequest.getDestinationKey(), "The destination object key must be specified when copying an object");
        String destinationKey = copyObjectRequest.getDestinationKey();
        String destinationBucketName = copyObjectRequest.getDestinationBucketName();
        Request request = createRequest(destinationBucketName, destinationKey, copyObjectRequest, HttpMethodName.PUT);
        populateRequestWithCopyObjectParameters(request, copyObjectRequest);
        setZeroContentLength(request);
        AmazonS3Exception ase;
        try {
            CopyObjectResultHandler copyObjectResultHandler = (CopyObjectResultHandler) invoke(request, new ResponseHeaderHandlerChain(new CopyObjectUnmarshaller(), new ServerSideEncryptionHeaderHandler(), new S3VersionHeaderHandler(), new ObjectExpirationHeaderHandler()), destinationBucketName, destinationKey);
            if (copyObjectResultHandler.getErrorCode() != null) {
                String errorCode = copyObjectResultHandler.getErrorCode();
                String errorMessage = copyObjectResultHandler.getErrorMessage();
                String requestId = copyObjectResultHandler.getErrorRequestId();
                String hostId = copyObjectResultHandler.getErrorHostId();
                ase = new AmazonS3Exception(errorMessage);
                ase.setErrorCode(errorCode);
                ase.setErrorType(ErrorType.Service);
                ase.setRequestId(requestId);
                ase.setExtendedRequestId(hostId);
                ase.setServiceName(request.getServiceName());
                ase.setStatusCode(200);
                throw ase;
            }
            CopyObjectResult copyObjectResult = new CopyObjectResult();
            copyObjectResult.setETag(copyObjectResultHandler.getETag());
            copyObjectResult.setLastModifiedDate(copyObjectResultHandler.getLastModified());
            copyObjectResult.setVersionId(copyObjectResultHandler.getVersionId());
            copyObjectResult.setSSEAlgorithm(copyObjectResultHandler.getSSEAlgorithm());
            copyObjectResult.setSSEKMSKeyId(copyObjectResultHandler.getSSEKMSKeyId());
            copyObjectResult.setSSECustomerAlgorithm(copyObjectResultHandler.getSSECustomerAlgorithm());
            copyObjectResult.setSSECustomerKeyMd5(copyObjectResultHandler.getSSECustomerKeyMd5());
            copyObjectResult.setExpirationTime(copyObjectResultHandler.getExpirationTime());
            copyObjectResult.setExpirationTimeRuleId(copyObjectResultHandler.getExpirationTimeRuleId());
            return copyObjectResult;
        } catch (AmazonS3Exception ase2) {
            if (ase2.getStatusCode() == Constants.FAILED_PRECONDITION_STATUS_CODE) {
                return null;
            }
            throw ase2;
        }
    }

    public CopyPartResult copyPart(CopyPartRequest copyPartRequest) {
        assertParameterNotNull(copyPartRequest.getSourceBucketName(), "The source bucket name must be specified when copying a part");
        assertParameterNotNull(copyPartRequest.getSourceKey(), "The source object key must be specified when copying a part");
        assertParameterNotNull(copyPartRequest.getDestinationBucketName(), "The destination bucket name must be specified when copying a part");
        assertParameterNotNull(copyPartRequest.getUploadId(), "The upload id must be specified when copying a part");
        assertParameterNotNull(copyPartRequest.getDestinationKey(), "The destination object key must be specified when copying a part");
        assertParameterNotNull(Integer.valueOf(copyPartRequest.getPartNumber()), "The part number must be specified when copying a part");
        String destinationKey = copyPartRequest.getDestinationKey();
        String destinationBucketName = copyPartRequest.getDestinationBucketName();
        Request request = createRequest(destinationBucketName, destinationKey, copyPartRequest, HttpMethodName.PUT);
        populateRequestWithCopyPartParameters(request, copyPartRequest);
        request.addParameter("uploadId", copyPartRequest.getUploadId());
        request.addParameter("partNumber", Integer.toString(copyPartRequest.getPartNumber()));
        setZeroContentLength(request);
        AmazonS3Exception ase;
        try {
            CopyObjectResultHandler copyObjectResultHandler = (CopyObjectResultHandler) invoke(request, new ResponseHeaderHandlerChain(new CopyObjectUnmarshaller(), new ServerSideEncryptionHeaderHandler(), new S3VersionHeaderHandler()), destinationBucketName, destinationKey);
            if (copyObjectResultHandler.getErrorCode() != null) {
                String errorCode = copyObjectResultHandler.getErrorCode();
                String errorMessage = copyObjectResultHandler.getErrorMessage();
                String requestId = copyObjectResultHandler.getErrorRequestId();
                String hostId = copyObjectResultHandler.getErrorHostId();
                ase = new AmazonS3Exception(errorMessage);
                ase.setErrorCode(errorCode);
                ase.setErrorType(ErrorType.Service);
                ase.setRequestId(requestId);
                ase.setExtendedRequestId(hostId);
                ase.setServiceName(request.getServiceName());
                ase.setStatusCode(200);
                throw ase;
            }
            CopyPartResult copyPartResult = new CopyPartResult();
            copyPartResult.setETag(copyObjectResultHandler.getETag());
            copyPartResult.setPartNumber(copyPartRequest.getPartNumber());
            copyPartResult.setLastModifiedDate(copyObjectResultHandler.getLastModified());
            copyPartResult.setVersionId(copyObjectResultHandler.getVersionId());
            copyPartResult.setSSEAlgorithm(copyObjectResultHandler.getSSEAlgorithm());
            copyPartResult.setSSECustomerAlgorithm(copyObjectResultHandler.getSSECustomerAlgorithm());
            copyPartResult.setSSECustomerKeyMd5(copyObjectResultHandler.getSSECustomerKeyMd5());
            return copyPartResult;
        } catch (AmazonS3Exception ase2) {
            if (ase2.getStatusCode() == Constants.FAILED_PRECONDITION_STATUS_CODE) {
                return null;
            }
            throw ase2;
        }
    }

    public void deleteObject(String bucketName, String key) throws AmazonClientException, AmazonServiceException {
        deleteObject(new DeleteObjectRequest(bucketName, key));
    }

    public void deleteObject(DeleteObjectRequest deleteObjectRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(deleteObjectRequest, "The delete object request must be specified when deleting an object");
        assertParameterNotNull(deleteObjectRequest.getBucketName(), "The bucket name must be specified when deleting an object");
        assertParameterNotNull(deleteObjectRequest.getKey(), "The key must be specified when deleting an object");
        invoke(createRequest(deleteObjectRequest.getBucketName(), deleteObjectRequest.getKey(), deleteObjectRequest, HttpMethodName.DELETE), this.voidResponseHandler, deleteObjectRequest.getBucketName(), deleteObjectRequest.getKey());
    }

    public DeleteObjectsResult deleteObjects(DeleteObjectsRequest deleteObjectsRequest) {
        Request request = createRequest(deleteObjectsRequest.getBucketName(), null, deleteObjectsRequest, HttpMethodName.POST);
        request.addParameter("delete", null);
        if (deleteObjectsRequest.getMfa() != null) {
            populateRequestWithMfaDetails(request, deleteObjectsRequest.getMfa());
        }
        byte[] content = new MultiObjectDeleteXmlFactory().convertToXmlByteArray(deleteObjectsRequest);
        request.addHeader("Content-Length", String.valueOf(content.length));
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        request.setContent(new ByteArrayInputStream(content));
        try {
            request.addHeader(Headers.CONTENT_MD5, BinaryUtils.toBase64(Md5Utils.computeMD5Hash(content)));
            DeleteObjectsResponse response = (DeleteObjectsResponse) invoke(request, new DeleteObjectsResultUnmarshaller(), deleteObjectsRequest.getBucketName(), null);
            if (response.getErrors().isEmpty()) {
                return new DeleteObjectsResult(response.getDeletedObjects());
            }
            throw new MultiObjectDeleteException(response.getErrors(), response.getDeletedObjects());
        } catch (Exception e) {
            throw new AmazonClientException("Couldn't compute md5 sum", e);
        }
    }

    public void deleteVersion(String bucketName, String key, String versionId) throws AmazonClientException, AmazonServiceException {
        deleteVersion(new DeleteVersionRequest(bucketName, key, versionId));
    }

    public void deleteVersion(DeleteVersionRequest deleteVersionRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(deleteVersionRequest, "The delete version request object must be specified when deleting a version");
        String bucketName = deleteVersionRequest.getBucketName();
        String key = deleteVersionRequest.getKey();
        String versionId = deleteVersionRequest.getVersionId();
        assertParameterNotNull(bucketName, "The bucket name must be specified when deleting a version");
        assertParameterNotNull(key, "The key must be specified when deleting a version");
        assertParameterNotNull(versionId, "The version ID must be specified when deleting a version");
        Request request = createRequest(bucketName, key, deleteVersionRequest, HttpMethodName.DELETE);
        if (versionId != null) {
            request.addParameter("versionId", versionId);
        }
        if (deleteVersionRequest.getMfa() != null) {
            populateRequestWithMfaDetails(request, deleteVersionRequest.getMfa());
        }
        invoke(request, this.voidResponseHandler, bucketName, key);
    }

    public void setBucketVersioningConfiguration(SetBucketVersioningConfigurationRequest setBucketVersioningConfigurationRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(setBucketVersioningConfigurationRequest, "The SetBucketVersioningConfigurationRequest object must be specified when setting versioning configuration");
        String bucketName = setBucketVersioningConfigurationRequest.getBucketName();
        BucketVersioningConfiguration versioningConfiguration = setBucketVersioningConfigurationRequest.getVersioningConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting versioning configuration");
        assertParameterNotNull(versioningConfiguration, "The bucket versioning parameter must be specified when setting versioning configuration");
        if (versioningConfiguration.isMfaDeleteEnabled() != null) {
            assertParameterNotNull(setBucketVersioningConfigurationRequest.getMfa(), "The MFA parameter must be specified when changing MFA Delete status in the versioning configuration");
        }
        Request request = createRequest(bucketName, null, setBucketVersioningConfigurationRequest, HttpMethodName.PUT);
        request.addParameter("versioning", null);
        if (!(versioningConfiguration.isMfaDeleteEnabled() == null || setBucketVersioningConfigurationRequest.getMfa() == null)) {
            populateRequestWithMfaDetails(request, setBucketVersioningConfigurationRequest.getMfa());
        }
        byte[] bytes = bucketConfigurationXmlFactory.convertToXmlByteArray(versioningConfiguration);
        request.addHeader("Content-Length", String.valueOf(bytes.length));
        request.setContent(new ByteArrayInputStream(bytes));
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public BucketVersioningConfiguration getBucketVersioningConfiguration(String bucketName) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when querying versioning configuration");
        Request request = createRequest(bucketName, null, new GenericBucketRequest(bucketName), HttpMethodName.GET);
        request.addParameter("versioning", null);
        return (BucketVersioningConfiguration) invoke(request, new BucketVersioningConfigurationUnmarshaller(), bucketName, null);
    }

    public BucketWebsiteConfiguration getBucketWebsiteConfiguration(String bucketName) throws AmazonClientException, AmazonServiceException {
        return getBucketWebsiteConfiguration(new GetBucketWebsiteConfigurationRequest(bucketName));
    }

    public BucketWebsiteConfiguration getBucketWebsiteConfiguration(GetBucketWebsiteConfigurationRequest getBucketWebsiteConfigurationRequest) throws AmazonClientException, AmazonServiceException {
        String bucketName = getBucketWebsiteConfigurationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting a bucket's website configuration");
        Request request = createRequest(bucketName, null, getBucketWebsiteConfigurationRequest, HttpMethodName.GET);
        request.addParameter(PlaceFields.WEBSITE, null);
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        try {
            return (BucketWebsiteConfiguration) invoke(request, new BucketWebsiteConfigurationUnmarshaller(), bucketName, null);
        } catch (AmazonServiceException ase) {
            if (ase.getStatusCode() == Constants.NO_SUCH_BUCKET_STATUS_CODE) {
                return null;
            }
            throw ase;
        }
    }

    public BucketLifecycleConfiguration getBucketLifecycleConfiguration(String bucketName) {
        Request request = createRequest(bucketName, null, new GenericBucketRequest(bucketName), HttpMethodName.GET);
        request.addParameter("lifecycle", null);
        try {
            return (BucketLifecycleConfiguration) invoke(request, new BucketLifecycleConfigurationUnmarshaller(), bucketName, null);
        } catch (AmazonServiceException ase) {
            switch (ase.getStatusCode()) {
                case Constants.NO_SUCH_BUCKET_STATUS_CODE /*404*/:
                    return null;
                default:
                    throw ase;
            }
        }
    }

    public void setBucketLifecycleConfiguration(String bucketName, BucketLifecycleConfiguration bucketLifecycleConfiguration) {
        setBucketLifecycleConfiguration(new SetBucketLifecycleConfigurationRequest(bucketName, bucketLifecycleConfiguration));
    }

    public void setBucketLifecycleConfiguration(SetBucketLifecycleConfigurationRequest setBucketLifecycleConfigurationRequest) {
        assertParameterNotNull(setBucketLifecycleConfigurationRequest, "The set bucket lifecycle configuration request object must be specified.");
        String bucketName = setBucketLifecycleConfigurationRequest.getBucketName();
        BucketLifecycleConfiguration bucketLifecycleConfiguration = setBucketLifecycleConfigurationRequest.getLifecycleConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting bucket lifecycle configuration.");
        assertParameterNotNull(bucketLifecycleConfiguration, "The lifecycle configuration parameter must be specified when setting bucket lifecycle configuration.");
        Request request = createRequest(bucketName, null, setBucketLifecycleConfigurationRequest, HttpMethodName.PUT);
        request.addParameter("lifecycle", null);
        byte[] content = new BucketConfigurationXmlFactory().convertToXmlByteArray(bucketLifecycleConfiguration);
        request.addHeader("Content-Length", String.valueOf(content.length));
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        request.setContent(new ByteArrayInputStream(content));
        try {
            request.addHeader(Headers.CONTENT_MD5, BinaryUtils.toBase64(Md5Utils.computeMD5Hash(content)));
            invoke(request, this.voidResponseHandler, bucketName, null);
        } catch (Exception e) {
            throw new AmazonClientException("Couldn't compute md5 sum", e);
        }
    }

    public void deleteBucketLifecycleConfiguration(String bucketName) {
        deleteBucketLifecycleConfiguration(new DeleteBucketLifecycleConfigurationRequest(bucketName));
    }

    public void deleteBucketLifecycleConfiguration(DeleteBucketLifecycleConfigurationRequest deleteBucketLifecycleConfigurationRequest) {
        assertParameterNotNull(deleteBucketLifecycleConfigurationRequest, "The delete bucket lifecycle configuration request object must be specified.");
        String bucketName = deleteBucketLifecycleConfigurationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when deleting bucket lifecycle configuration.");
        Request request = createRequest(bucketName, null, deleteBucketLifecycleConfigurationRequest, HttpMethodName.DELETE);
        request.addParameter("lifecycle", null);
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public BucketCrossOriginConfiguration getBucketCrossOriginConfiguration(String bucketName) {
        Request request = createRequest(bucketName, null, new GenericBucketRequest(bucketName), HttpMethodName.GET);
        request.addParameter("cors", null);
        try {
            return (BucketCrossOriginConfiguration) invoke(request, new BucketCrossOriginConfigurationUnmarshaller(), bucketName, null);
        } catch (AmazonServiceException ase) {
            switch (ase.getStatusCode()) {
                case Constants.NO_SUCH_BUCKET_STATUS_CODE /*404*/:
                    return null;
                default:
                    throw ase;
            }
        }
    }

    public void setBucketCrossOriginConfiguration(String bucketName, BucketCrossOriginConfiguration bucketCrossOriginConfiguration) {
        setBucketCrossOriginConfiguration(new SetBucketCrossOriginConfigurationRequest(bucketName, bucketCrossOriginConfiguration));
    }

    public void setBucketCrossOriginConfiguration(SetBucketCrossOriginConfigurationRequest setBucketCrossOriginConfigurationRequest) {
        assertParameterNotNull(setBucketCrossOriginConfigurationRequest, "The set bucket cross origin configuration request object must be specified.");
        String bucketName = setBucketCrossOriginConfigurationRequest.getBucketName();
        BucketCrossOriginConfiguration bucketCrossOriginConfiguration = setBucketCrossOriginConfigurationRequest.getCrossOriginConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting bucket cross origin configuration.");
        assertParameterNotNull(bucketCrossOriginConfiguration, "The cross origin configuration parameter must be specified when setting bucket cross origin configuration.");
        Request request = createRequest(bucketName, null, setBucketCrossOriginConfigurationRequest, HttpMethodName.PUT);
        request.addParameter("cors", null);
        byte[] content = new BucketConfigurationXmlFactory().convertToXmlByteArray(bucketCrossOriginConfiguration);
        request.addHeader("Content-Length", String.valueOf(content.length));
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        request.setContent(new ByteArrayInputStream(content));
        try {
            request.addHeader(Headers.CONTENT_MD5, BinaryUtils.toBase64(Md5Utils.computeMD5Hash(content)));
            invoke(request, this.voidResponseHandler, bucketName, null);
        } catch (Exception e) {
            throw new AmazonClientException("Couldn't compute md5 sum", e);
        }
    }

    public void deleteBucketCrossOriginConfiguration(String bucketName) {
        deleteBucketCrossOriginConfiguration(new DeleteBucketCrossOriginConfigurationRequest(bucketName));
    }

    public void deleteBucketCrossOriginConfiguration(DeleteBucketCrossOriginConfigurationRequest deleteBucketCrossOriginConfigurationRequest) {
        assertParameterNotNull(deleteBucketCrossOriginConfigurationRequest, "The delete bucket cross origin configuration request object must be specified.");
        String bucketName = deleteBucketCrossOriginConfigurationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when deleting bucket cross origin configuration.");
        Request request = createRequest(bucketName, null, deleteBucketCrossOriginConfigurationRequest, HttpMethodName.DELETE);
        request.addParameter("cors", null);
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public BucketTaggingConfiguration getBucketTaggingConfiguration(String bucketName) {
        Request request = createRequest(bucketName, null, new GenericBucketRequest(bucketName), HttpMethodName.GET);
        request.addParameter("tagging", null);
        try {
            return (BucketTaggingConfiguration) invoke(request, new BucketTaggingConfigurationUnmarshaller(), bucketName, null);
        } catch (AmazonServiceException ase) {
            switch (ase.getStatusCode()) {
                case Constants.NO_SUCH_BUCKET_STATUS_CODE /*404*/:
                    return null;
                default:
                    throw ase;
            }
        }
    }

    public void setBucketTaggingConfiguration(String bucketName, BucketTaggingConfiguration bucketTaggingConfiguration) {
        setBucketTaggingConfiguration(new SetBucketTaggingConfigurationRequest(bucketName, bucketTaggingConfiguration));
    }

    public void setBucketTaggingConfiguration(SetBucketTaggingConfigurationRequest setBucketTaggingConfigurationRequest) {
        assertParameterNotNull(setBucketTaggingConfigurationRequest, "The set bucket tagging configuration request object must be specified.");
        String bucketName = setBucketTaggingConfigurationRequest.getBucketName();
        BucketTaggingConfiguration bucketTaggingConfiguration = setBucketTaggingConfigurationRequest.getTaggingConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting bucket tagging configuration.");
        assertParameterNotNull(bucketTaggingConfiguration, "The tagging configuration parameter must be specified when setting bucket tagging configuration.");
        Request request = createRequest(bucketName, null, setBucketTaggingConfigurationRequest, HttpMethodName.PUT);
        request.addParameter("tagging", null);
        byte[] content = new BucketConfigurationXmlFactory().convertToXmlByteArray(bucketTaggingConfiguration);
        request.addHeader("Content-Length", String.valueOf(content.length));
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        request.setContent(new ByteArrayInputStream(content));
        try {
            request.addHeader(Headers.CONTENT_MD5, BinaryUtils.toBase64(Md5Utils.computeMD5Hash(content)));
            invoke(request, this.voidResponseHandler, bucketName, null);
        } catch (Exception e) {
            throw new AmazonClientException("Couldn't compute md5 sum", e);
        }
    }

    public void deleteBucketTaggingConfiguration(String bucketName) {
        deleteBucketTaggingConfiguration(new DeleteBucketTaggingConfigurationRequest(bucketName));
    }

    public void deleteBucketTaggingConfiguration(DeleteBucketTaggingConfigurationRequest deleteBucketTaggingConfigurationRequest) {
        assertParameterNotNull(deleteBucketTaggingConfigurationRequest, "The delete bucket tagging configuration request object must be specified.");
        String bucketName = deleteBucketTaggingConfigurationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when deleting bucket tagging configuration.");
        Request request = createRequest(bucketName, null, deleteBucketTaggingConfigurationRequest, HttpMethodName.DELETE);
        request.addParameter("tagging", null);
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public void setBucketWebsiteConfiguration(String bucketName, BucketWebsiteConfiguration configuration) throws AmazonClientException, AmazonServiceException {
        setBucketWebsiteConfiguration(new SetBucketWebsiteConfigurationRequest(bucketName, configuration));
    }

    public void setBucketWebsiteConfiguration(SetBucketWebsiteConfigurationRequest setBucketWebsiteConfigurationRequest) throws AmazonClientException, AmazonServiceException {
        String bucketName = setBucketWebsiteConfigurationRequest.getBucketName();
        BucketWebsiteConfiguration configuration = setBucketWebsiteConfigurationRequest.getConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting a bucket's website configuration");
        assertParameterNotNull(configuration, "The bucket website configuration parameter must be specified when setting a bucket's website configuration");
        if (configuration.getRedirectAllRequestsTo() == null) {
            assertParameterNotNull(configuration.getIndexDocumentSuffix(), "The bucket website configuration parameter must specify the index document suffix when setting a bucket's website configuration");
        }
        Request request = createRequest(bucketName, null, setBucketWebsiteConfigurationRequest, HttpMethodName.PUT);
        request.addParameter(PlaceFields.WEBSITE, null);
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        byte[] bytes = bucketConfigurationXmlFactory.convertToXmlByteArray(configuration);
        request.addHeader("Content-Length", String.valueOf(bytes.length));
        request.setContent(new ByteArrayInputStream(bytes));
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public void deleteBucketWebsiteConfiguration(String bucketName) throws AmazonClientException, AmazonServiceException {
        deleteBucketWebsiteConfiguration(new DeleteBucketWebsiteConfigurationRequest(bucketName));
    }

    public void deleteBucketWebsiteConfiguration(DeleteBucketWebsiteConfigurationRequest deleteBucketWebsiteConfigurationRequest) throws AmazonClientException, AmazonServiceException {
        String bucketName = deleteBucketWebsiteConfigurationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when deleting a bucket's website configuration");
        Request request = createRequest(bucketName, null, deleteBucketWebsiteConfigurationRequest, HttpMethodName.DELETE);
        request.addParameter(PlaceFields.WEBSITE, null);
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public void setBucketNotificationConfiguration(String bucketName, BucketNotificationConfiguration bucketNotificationConfiguration) throws AmazonClientException, AmazonServiceException {
        setBucketNotificationConfiguration(new SetBucketNotificationConfigurationRequest(bucketName, bucketNotificationConfiguration));
    }

    public void setBucketNotificationConfiguration(SetBucketNotificationConfigurationRequest setBucketNotificationConfigurationRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(setBucketNotificationConfigurationRequest, "The set bucket notification configuration request object must be specified.");
        String bucketName = setBucketNotificationConfigurationRequest.getBucketName();
        BucketNotificationConfiguration bucketNotificationConfiguration = setBucketNotificationConfigurationRequest.getNotificationConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting bucket notification configuration.");
        assertParameterNotNull(bucketNotificationConfiguration, "The notification configuration parameter must be specified when setting bucket notification configuration.");
        Request request = createRequest(bucketName, null, setBucketNotificationConfigurationRequest, HttpMethodName.PUT);
        request.addParameter("notification", null);
        byte[] bytes = bucketConfigurationXmlFactory.convertToXmlByteArray(bucketNotificationConfiguration);
        request.addHeader("Content-Length", String.valueOf(bytes.length));
        request.setContent(new ByteArrayInputStream(bytes));
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public BucketNotificationConfiguration getBucketNotificationConfiguration(String bucketName) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when querying notification configuration");
        Request request = createRequest(bucketName, null, new GenericBucketRequest(bucketName), HttpMethodName.GET);
        request.addParameter("notification", null);
        return (BucketNotificationConfiguration) invoke(request, new BucketNotificationConfigurationUnmarshaller(), bucketName, null);
    }

    public BucketLoggingConfiguration getBucketLoggingConfiguration(String bucketName) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting a bucket's logging status");
        Request request = createRequest(bucketName, null, new GenericBucketRequest(bucketName), HttpMethodName.GET);
        request.addParameter("logging", null);
        return (BucketLoggingConfiguration) invoke(request, new BucketLoggingConfigurationnmarshaller(), bucketName, null);
    }

    public void setBucketLoggingConfiguration(SetBucketLoggingConfigurationRequest setBucketLoggingConfigurationRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(setBucketLoggingConfigurationRequest, "The set bucket logging configuration request object must be specified when enabling server access logging");
        String bucketName = setBucketLoggingConfigurationRequest.getBucketName();
        BucketLoggingConfiguration loggingConfiguration = setBucketLoggingConfigurationRequest.getLoggingConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when enabling server access logging");
        assertParameterNotNull(loggingConfiguration, "The logging configuration parameter must be specified when enabling server access logging");
        Request request = createRequest(bucketName, null, setBucketLoggingConfigurationRequest, HttpMethodName.PUT);
        request.addParameter("logging", null);
        byte[] bytes = bucketConfigurationXmlFactory.convertToXmlByteArray(loggingConfiguration);
        request.addHeader("Content-Length", String.valueOf(bytes.length));
        request.setContent(new ByteArrayInputStream(bytes));
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public BucketAccelerateConfiguration getBucketAccelerateConfiguration(String bucketName) throws AmazonServiceException, AmazonClientException {
        return getBucketAccelerateConfiguration(new GetBucketAccelerateConfigurationRequest(bucketName));
    }

    public BucketAccelerateConfiguration getBucketAccelerateConfiguration(GetBucketAccelerateConfigurationRequest getBucketAccelerateConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        assertParameterNotNull(getBucketAccelerateConfigurationRequest, "getBucketAccelerateConfigurationRequest must be specified.");
        String bucketName = getBucketAccelerateConfigurationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when querying accelerate configuration");
        Request request = createRequest(bucketName, null, getBucketAccelerateConfigurationRequest, HttpMethodName.GET);
        request.addParameter("accelerate", null);
        return (BucketAccelerateConfiguration) invoke(request, new BucketAccelerateConfigurationUnmarshaller(), bucketName, null);
    }

    public void setBucketAccelerateConfiguration(String bucketName, BucketAccelerateConfiguration accelerateConfiguration) throws AmazonServiceException, AmazonClientException {
        setBucketAccelerateConfiguration(new SetBucketAccelerateConfigurationRequest(bucketName, accelerateConfiguration));
    }

    public void setBucketAccelerateConfiguration(SetBucketAccelerateConfigurationRequest setBucketAccelerateConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        assertParameterNotNull(setBucketAccelerateConfigurationRequest, "setBucketAccelerateConfigurationRequest must be specified");
        String bucketName = setBucketAccelerateConfigurationRequest.getBucketName();
        BucketAccelerateConfiguration accelerateConfiguration = setBucketAccelerateConfigurationRequest.getAccelerateConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting accelerate configuration.");
        assertParameterNotNull(accelerateConfiguration, "The bucket accelerate configuration parameter must be specified.");
        assertParameterNotNull(accelerateConfiguration.getStatus(), "The status parameter must be specified when updating bucket accelerate configuration.");
        Request request = createRequest(bucketName, null, setBucketAccelerateConfigurationRequest, HttpMethodName.PUT);
        request.addParameter("accelerate", null);
        byte[] bytes = bucketConfigurationXmlFactory.convertToXmlByteArray(accelerateConfiguration);
        request.addHeader("Content-Length", String.valueOf(bytes.length));
        request.setContent(new ByteArrayInputStream(bytes));
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public BucketPolicy getBucketPolicy(String bucketName) throws AmazonClientException, AmazonServiceException {
        return getBucketPolicy(new GetBucketPolicyRequest(bucketName));
    }

    public void setBucketPolicy(String bucketName, String policyText) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name must be specified when setting a bucket policy");
        assertParameterNotNull(policyText, "The policy text must be specified when setting a bucket policy");
        Request request = createRequest(bucketName, null, new GenericBucketRequest(bucketName), HttpMethodName.PUT);
        request.addParameter("policy", null);
        byte[] bytes = ServiceUtils.toByteArray(policyText);
        request.addHeader("Content-Length", String.valueOf(bytes.length));
        request.setContent(new ByteArrayInputStream(bytes));
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public void deleteBucketPolicy(String bucketName) throws AmazonClientException, AmazonServiceException {
        deleteBucketPolicy(new DeleteBucketPolicyRequest(bucketName));
    }

    public BucketPolicy getBucketPolicy(GetBucketPolicyRequest getBucketPolicyRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(getBucketPolicyRequest, "The request object must be specified when getting a bucket policy");
        String bucketName = getBucketPolicyRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name must be specified when getting a bucket policy");
        Request request = createRequest(bucketName, null, getBucketPolicyRequest, HttpMethodName.GET);
        request.addParameter("policy", null);
        BucketPolicy result = new BucketPolicy();
        try {
            result.setPolicyText((String) invoke(request, new S3StringResponseHandler(), bucketName, null));
        } catch (AmazonServiceException ase) {
            if (!ase.getErrorCode().equals("NoSuchBucketPolicy")) {
                throw ase;
            }
        }
        return result;
    }

    public void setBucketPolicy(SetBucketPolicyRequest setBucketPolicyRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(setBucketPolicyRequest, "The request object must be specified when setting a bucket policy");
        String bucketName = setBucketPolicyRequest.getBucketName();
        String policyText = setBucketPolicyRequest.getPolicyText();
        assertParameterNotNull(bucketName, "The bucket name must be specified when setting a bucket policy");
        assertParameterNotNull(policyText, "The policy text must be specified when setting a bucket policy");
        Request request = createRequest(bucketName, null, setBucketPolicyRequest, HttpMethodName.PUT);
        request.addParameter("policy", null);
        byte[] bytes = ServiceUtils.toByteArray(policyText);
        request.addHeader("Content-Length", String.valueOf(bytes.length));
        request.setContent(new ByteArrayInputStream(bytes));
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public void deleteBucketPolicy(DeleteBucketPolicyRequest deleteBucketPolicyRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(deleteBucketPolicyRequest, "The request object must be specified when deleting a bucket policy");
        String bucketName = deleteBucketPolicyRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name must be specified when deleting a bucket policy");
        Request request = createRequest(bucketName, null, deleteBucketPolicyRequest, HttpMethodName.DELETE);
        request.addParameter("policy", null);
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    public URL generatePresignedUrl(String bucketName, String key, Date expiration) throws AmazonClientException {
        return generatePresignedUrl(bucketName, key, expiration, HttpMethod.GET);
    }

    public URL generatePresignedUrl(String bucketName, String key, Date expiration, HttpMethod method) throws AmazonClientException {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key, method);
        request.setExpiration(expiration);
        return generatePresignedUrl(request);
    }

    public URL generatePresignedUrl(GeneratePresignedUrlRequest generatePresignedUrlRequest) throws AmazonClientException {
        assertParameterNotNull(generatePresignedUrlRequest, "The request parameter must be specified when generating a pre-signed URL");
        String bucketName = generatePresignedUrlRequest.getBucketName();
        String key = generatePresignedUrlRequest.getKey();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when generating a pre-signed URL");
        assertParameterNotNull(generatePresignedUrlRequest.getMethod(), "The HTTP method request parameter must be specified when generating a pre-signed URL");
        if (generatePresignedUrlRequest.getExpiration() == null) {
            generatePresignedUrlRequest.setExpiration(new Date(System.currentTimeMillis() + 900000));
        }
        Request<GeneratePresignedUrlRequest> request = createRequest(bucketName, key, generatePresignedUrlRequest, HttpMethodName.valueOf(generatePresignedUrlRequest.getMethod().toString()));
        for (Entry<String, String> entry : generatePresignedUrlRequest.getRequestParameters().entrySet()) {
            request.addParameter((String) entry.getKey(), (String) entry.getValue());
        }
        if (generatePresignedUrlRequest.getContentType() != null) {
            request.addHeader("Content-Type", generatePresignedUrlRequest.getContentType());
        }
        if (generatePresignedUrlRequest.getContentMd5() != null) {
            request.addHeader(Headers.CONTENT_MD5, generatePresignedUrlRequest.getContentMd5());
        }
        populateSseCpkRequestParameters(request, generatePresignedUrlRequest.getSSECustomerKey());
        addResponseHeaderParameters(request, generatePresignedUrlRequest.getResponseHeaders());
        Signer signer = createSigner(request, bucketName, key);
        if (signer instanceof Presigner) {
            ((Presigner) signer).presignRequest(request, this.awsCredentialsProvider.getCredentials(), generatePresignedUrlRequest.getExpiration());
        } else {
            presignRequest(request, generatePresignedUrlRequest.getMethod(), bucketName, key, generatePresignedUrlRequest.getExpiration(), null);
        }
        return ServiceUtils.convertRequestToUrl(request, true);
    }

    public void abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(abortMultipartUploadRequest, "The request parameter must be specified when aborting a multipart upload");
        assertParameterNotNull(abortMultipartUploadRequest.getBucketName(), "The bucket name parameter must be specified when aborting a multipart upload");
        assertParameterNotNull(abortMultipartUploadRequest.getKey(), "The key parameter must be specified when aborting a multipart upload");
        assertParameterNotNull(abortMultipartUploadRequest.getUploadId(), "The upload ID parameter must be specified when aborting a multipart upload");
        String bucketName = abortMultipartUploadRequest.getBucketName();
        String key = abortMultipartUploadRequest.getKey();
        Request request = createRequest(bucketName, key, abortMultipartUploadRequest, HttpMethodName.DELETE);
        request.addParameter("uploadId", abortMultipartUploadRequest.getUploadId());
        invoke(request, this.voidResponseHandler, bucketName, key);
    }

    public CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(completeMultipartUploadRequest, "The request parameter must be specified when completing a multipart upload");
        String bucketName = completeMultipartUploadRequest.getBucketName();
        String key = completeMultipartUploadRequest.getKey();
        String uploadId = completeMultipartUploadRequest.getUploadId();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when completing a multipart upload");
        assertParameterNotNull(key, "The key parameter must be specified when completing a multipart upload");
        assertParameterNotNull(uploadId, "The upload ID parameter must be specified when completing a multipart upload");
        assertParameterNotNull(completeMultipartUploadRequest.getPartETags(), "The part ETags parameter must be specified when completing a multipart upload");
        Request request = createRequest(bucketName, key, completeMultipartUploadRequest, HttpMethodName.POST);
        request.addParameter("uploadId", uploadId);
        byte[] xml = RequestXmlFactory.convertToXmlByteArray(completeMultipartUploadRequest.getPartETags());
        request.addHeader("Content-Type", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        request.addHeader("Content-Length", String.valueOf(xml.length));
        request.setContent(new ByteArrayInputStream(xml));
        HttpResponseHandler responseHandler = new ResponseHeaderHandlerChain(new CompleteMultipartUploadResultUnmarshaller(), new ServerSideEncryptionHeaderHandler(), new ObjectExpirationHeaderHandler());
        CompleteMultipartUploadHandler handler = (CompleteMultipartUploadHandler) invoke(request, responseHandler, bucketName, key);
        if (handler.getCompleteMultipartUploadResult() != null) {
            handler.getCompleteMultipartUploadResult().setVersionId((String) responseHandler.getResponseHeaders().get(Headers.S3_VERSION_ID));
            return handler.getCompleteMultipartUploadResult();
        }
        throw handler.getAmazonS3Exception();
    }

    public InitiateMultipartUploadResult initiateMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(initiateMultipartUploadRequest, "The request parameter must be specified when initiating a multipart upload");
        assertParameterNotNull(initiateMultipartUploadRequest.getBucketName(), "The bucket name parameter must be specified when initiating a multipart upload");
        assertParameterNotNull(initiateMultipartUploadRequest.getKey(), "The key parameter must be specified when initiating a multipart upload");
        Request request = createRequest(initiateMultipartUploadRequest.getBucketName(), initiateMultipartUploadRequest.getKey(), initiateMultipartUploadRequest, HttpMethodName.POST);
        request.addParameter("uploads", null);
        if (initiateMultipartUploadRequest.getStorageClass() != null) {
            request.addHeader(Headers.STORAGE_CLASS, initiateMultipartUploadRequest.getStorageClass().toString());
        }
        if (initiateMultipartUploadRequest.getRedirectLocation() != null) {
            request.addHeader(Headers.REDIRECT_LOCATION, initiateMultipartUploadRequest.getRedirectLocation());
        }
        if (initiateMultipartUploadRequest.getAccessControlList() != null) {
            addAclHeaders(request, initiateMultipartUploadRequest.getAccessControlList());
        } else if (initiateMultipartUploadRequest.getCannedACL() != null) {
            request.addHeader(Headers.S3_CANNED_ACL, initiateMultipartUploadRequest.getCannedACL().toString());
        }
        if (initiateMultipartUploadRequest.objectMetadata != null) {
            populateRequestMetadata(request, initiateMultipartUploadRequest.objectMetadata);
        }
        populateSseCpkRequestParameters(request, initiateMultipartUploadRequest.getSSECustomerKey());
        setZeroContentLength(request);
        request.setContent(new ByteArrayInputStream(new byte[0]));
        return (InitiateMultipartUploadResult) invoke(request, new ResponseHeaderHandlerChain(new InitiateMultipartUploadResultUnmarshaller(), new ServerSideEncryptionHeaderHandler()), initiateMultipartUploadRequest.getBucketName(), initiateMultipartUploadRequest.getKey());
    }

    public MultipartUploadListing listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(listMultipartUploadsRequest, "The request parameter must be specified when listing multipart uploads");
        assertParameterNotNull(listMultipartUploadsRequest.getBucketName(), "The bucket name parameter must be specified when listing multipart uploads");
        Request request = createRequest(listMultipartUploadsRequest.getBucketName(), null, listMultipartUploadsRequest, HttpMethodName.GET);
        request.addParameter("uploads", null);
        if (listMultipartUploadsRequest.getKeyMarker() != null) {
            request.addParameter("key-marker", listMultipartUploadsRequest.getKeyMarker());
        }
        if (listMultipartUploadsRequest.getMaxUploads() != null) {
            request.addParameter("max-uploads", listMultipartUploadsRequest.getMaxUploads().toString());
        }
        if (listMultipartUploadsRequest.getUploadIdMarker() != null) {
            request.addParameter("upload-id-marker", listMultipartUploadsRequest.getUploadIdMarker());
        }
        if (listMultipartUploadsRequest.getDelimiter() != null) {
            request.addParameter("delimiter", listMultipartUploadsRequest.getDelimiter());
        }
        if (listMultipartUploadsRequest.getPrefix() != null) {
            request.addParameter("prefix", listMultipartUploadsRequest.getPrefix());
        }
        if (listMultipartUploadsRequest.getEncodingType() != null) {
            request.addParameter("encoding-type", listMultipartUploadsRequest.getEncodingType());
        }
        return (MultipartUploadListing) invoke(request, new ListMultipartUploadsResultUnmarshaller(), listMultipartUploadsRequest.getBucketName(), null);
    }

    public PartListing listParts(ListPartsRequest listPartsRequest) throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(listPartsRequest, "The request parameter must be specified when listing parts");
        assertParameterNotNull(listPartsRequest.getBucketName(), "The bucket name parameter must be specified when listing parts");
        assertParameterNotNull(listPartsRequest.getKey(), "The key parameter must be specified when listing parts");
        assertParameterNotNull(listPartsRequest.getUploadId(), "The upload ID parameter must be specified when listing parts");
        Request request = createRequest(listPartsRequest.getBucketName(), listPartsRequest.getKey(), listPartsRequest, HttpMethodName.GET);
        request.addParameter("uploadId", listPartsRequest.getUploadId());
        if (listPartsRequest.getMaxParts() != null) {
            request.addParameter("max-parts", listPartsRequest.getMaxParts().toString());
        }
        if (listPartsRequest.getPartNumberMarker() != null) {
            request.addParameter("part-number-marker", listPartsRequest.getPartNumberMarker().toString());
        }
        if (listPartsRequest.getEncodingType() != null) {
            request.addParameter("encoding-type", listPartsRequest.getEncodingType());
        }
        return (PartListing) invoke(request, new ListPartsResultUnmarshaller(), listPartsRequest.getBucketName(), listPartsRequest.getKey());
    }

    public UploadPartResult uploadPart(UploadPartRequest uploadPartRequest) throws AmazonClientException, AmazonServiceException {
        InputStream inputStream;
        assertParameterNotNull(uploadPartRequest, "The request parameter must be specified when uploading a part");
        String bucketName = uploadPartRequest.getBucketName();
        String key = uploadPartRequest.getKey();
        String uploadId = uploadPartRequest.getUploadId();
        int partNumber = uploadPartRequest.getPartNumber();
        long partSize = uploadPartRequest.getPartSize();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when uploading a part");
        assertParameterNotNull(key, "The key parameter must be specified when uploading a part");
        assertParameterNotNull(uploadId, "The upload ID parameter must be specified when uploading a part");
        assertParameterNotNull(Integer.valueOf(partNumber), "The part number parameter must be specified when uploading a part");
        assertParameterNotNull(Long.valueOf(partSize), "The part size parameter must be specified when uploading a part");
        Request<UploadPartRequest> request = createRequest(bucketName, key, uploadPartRequest, HttpMethodName.PUT);
        request.addParameter("uploadId", uploadId);
        request.addParameter("partNumber", Integer.toString(partNumber));
        addHeaderIfNotNull(request, Headers.CONTENT_MD5, uploadPartRequest.getMd5Digest());
        request.addHeader("Content-Length", Long.toString(partSize));
        request.addHeader(HttpHeader.EXPECT, "100-continue");
        populateSseCpkRequestParameters(request, uploadPartRequest.getSSECustomerKey());
        if (uploadPartRequest.getInputStream() != null) {
            inputStream = uploadPartRequest.getInputStream();
        } else if (uploadPartRequest.getFile() != null) {
            try {
                inputStream = new InputSubstream(new RepeatableFileInputStream(uploadPartRequest.getFile()), uploadPartRequest.getFileOffset(), partSize, true);
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("The specified file doesn't exist", e);
            }
        } else {
            throw new IllegalArgumentException("A File or InputStream must be specified when uploading part");
        }
        MD5DigestCalculatingInputStream md5DigestStream = null;
        if (uploadPartRequest.getMd5Digest() == null && !ServiceUtils.skipMd5CheckPerRequest(uploadPartRequest)) {
            md5DigestStream = new MD5DigestCalculatingInputStream(inputStream);
            inputStream = md5DigestStream;
        }
        ProgressListenerCallbackExecutor progressListenerCallbackExecutor = ProgressListenerCallbackExecutor.wrapListener(uploadPartRequest.getGeneralProgressListener());
        if (progressListenerCallbackExecutor != null) {
            InputStream inputStream2 = new ProgressReportingInputStream(inputStream, progressListenerCallbackExecutor);
            fireProgressEvent(progressListenerCallbackExecutor, 1024);
            inputStream = inputStream2;
        }
        try {
            request.setContent(inputStream);
            ObjectMetadata metadata = (ObjectMetadata) invoke((Request) request, new S3MetadataResponseHandler(), bucketName, key);
            if (metadata == null || md5DigestStream == null || ServiceUtils.skipMd5CheckPerResponse(metadata) || Arrays.equals(md5DigestStream.getMd5Digest(), BinaryUtils.fromHex(metadata.getETag()))) {
                fireProgressEvent(progressListenerCallbackExecutor, 2048);
                UploadPartResult result = new UploadPartResult();
                result.setETag(metadata.getETag());
                result.setPartNumber(partNumber);
                result.setSSEAlgorithm(metadata.getSSEAlgorithm());
                result.setSSEKMSKeyId(metadata.getSSEKMSKeyId());
                result.setSSECustomerAlgorithm(metadata.getSSECustomerAlgorithm());
                result.setSSECustomerKeyMd5(metadata.getSSECustomerKeyMd5());
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                    }
                }
                return result;
            }
            throw new AmazonClientException("Unable to verify integrity of data upload.  Client calculated content hash didn't match hash calculated by Amazon S3.  You may need to delete the data stored in Amazon S3.");
        } catch (AmazonClientException ace) {
            fireProgressEvent(progressListenerCallbackExecutor, 4096);
            throw ace;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e3) {
                }
            }
        }
    }

    public S3ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest request) {
        return (S3ResponseMetadata) this.client.getResponseMetadataForRequest(request);
    }

    public void restoreObject(RestoreObjectRequest restoreObjectRequest) throws AmazonServiceException {
        String bucketName = restoreObjectRequest.getBucketName();
        String key = restoreObjectRequest.getKey();
        String versionId = restoreObjectRequest.getVersionId();
        int expirationIndays = restoreObjectRequest.getExpirationInDays();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when copying a glacier object");
        assertParameterNotNull(key, "The key parameter must be specified when copying a glacier object");
        if (expirationIndays == -1) {
            throw new IllegalArgumentException("The expiration in days parameter must be specified when copying a glacier object");
        }
        Request request = createRequest(bucketName, key, restoreObjectRequest, HttpMethodName.POST);
        request.addParameter("restore", null);
        if (versionId != null) {
            request.addParameter("versionId", versionId);
        }
        byte[] content = RequestXmlFactory.convertToXmlByteArray(restoreObjectRequest);
        request.addHeader("Content-Length", String.valueOf(content.length));
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        request.setContent(new ByteArrayInputStream(content));
        try {
            request.addHeader(Headers.CONTENT_MD5, BinaryUtils.toBase64(Md5Utils.computeMD5Hash(content)));
            invoke(request, this.voidResponseHandler, bucketName, key);
        } catch (Exception e) {
            throw new AmazonClientException("Couldn't compute md5 sum", e);
        }
    }

    public void restoreObject(String bucketName, String key, int expirationInDays) throws AmazonServiceException {
        restoreObject(new RestoreObjectRequest(bucketName, key, expirationInDays));
    }

    private void assertParameterNotNull(Object parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void fireProgressEvent(ProgressListenerCallbackExecutor progressListenerCallbackExecutor, int eventType) {
        if (progressListenerCallbackExecutor != null) {
            ProgressEvent event = new ProgressEvent(0);
            event.setEventCode(eventType);
            progressListenerCallbackExecutor.progressChanged(event);
        }
    }

    private AccessControlList getAcl(String bucketName, String key, String versionId, AmazonWebServiceRequest originalRequest) {
        if (originalRequest == null) {
            originalRequest = new GenericBucketRequest(bucketName);
        }
        Request request = createRequest(bucketName, key, originalRequest, HttpMethodName.GET);
        request.addParameter("acl", null);
        if (versionId != null) {
            request.addParameter("versionId", versionId);
        }
        return (AccessControlList) invoke(request, new AccessControlListUnmarshaller(), bucketName, key);
    }

    private void setAcl(String bucketName, String key, String versionId, CannedAccessControlList cannedAcl, AmazonWebServiceRequest originalRequest) {
        if (originalRequest == null) {
            originalRequest = new GenericBucketRequest(bucketName);
        }
        Request request = createRequest(bucketName, key, originalRequest, HttpMethodName.PUT);
        request.addParameter("acl", null);
        request.addHeader(Headers.S3_CANNED_ACL, cannedAcl.toString());
        if (versionId != null) {
            request.addParameter("versionId", versionId);
        }
        invoke(request, this.voidResponseHandler, bucketName, key);
    }

    private void setAcl(String bucketName, String key, String versionId, AccessControlList acl, AmazonWebServiceRequest originalRequest) {
        if (originalRequest == null) {
            originalRequest = new GenericBucketRequest(bucketName);
        }
        Request request = createRequest(bucketName, key, originalRequest, HttpMethodName.PUT);
        request.addParameter("acl", null);
        if (versionId != null) {
            request.addParameter("versionId", versionId);
        }
        byte[] aclAsXml = new AclXmlFactory().convertToXmlByteArray(acl);
        request.addHeader("Content-Type", WebRequest.CONTENT_TYPE_PLAIN_TEXT);
        request.addHeader("Content-Length", String.valueOf(aclAsXml.length));
        request.setContent(new ByteArrayInputStream(aclAsXml));
        invoke(request, this.voidResponseHandler, bucketName, key);
    }

    protected Signer createSigner(Request<?> request, String bucketName, String key) {
        Signer signer = getSigner();
        if (upgradeToSigV4(request) && !(signer instanceof AWSS3V4Signer)) {
            AWSS3V4Signer v4Signer = new AWSS3V4Signer();
            v4Signer.setServiceName(getServiceNameIntern());
            String regionOverride = getSignerRegionOverride();
            if (regionOverride == null) {
                regionOverride = this.clientRegion;
            }
            if (regionOverride == null) {
                throw new AmazonClientException("Signature Version 4 requires knowing the region of the bucket you're trying to access. You can configure a region by calling AmazonS3Client.setRegion(Region) or AmazonS3Client.setEndpoint(String) with a region-specific endpoint such as \"s3-us-west-2.amazonaws.com\".");
            }
            v4Signer.setRegionName(regionOverride);
            return v4Signer;
        } else if (!(signer instanceof S3Signer)) {
            return signer;
        } else {
            StringBuilder append = new StringBuilder().append("/").append(bucketName != null ? bucketName + "/" : "");
            if (key == null) {
                key = "";
            }
            return new S3Signer(request.getHttpMethod().toString(), append.append(key).toString());
        }
    }

    private boolean upgradeToSigV4(Request<?> request) {
        if (System.getProperty(SDKGlobalConfiguration.ENFORCE_S3_SIGV4_SYSTEM_PROPERTY) == null && this.endpoint.getHost().endsWith(Constants.S3_HOSTNAME)) {
            return false;
        }
        return true;
    }

    protected <T> void presignRequest(Request<T> request, HttpMethod methodName, String bucketName, String key, Date expiration, String subResource) {
        beforeRequest(request);
        String resourcePath = ("/" + (bucketName != null ? bucketName + "/" : "") + (key != null ? HttpUtils.urlEncode(key, true) : "") + (subResource != null ? "?" + subResource : "")).replaceAll("(?<=/)/", "%2F");
        AWSCredentials credentials = this.awsCredentialsProvider.getCredentials();
        AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
        if (!(originalRequest == null || originalRequest.getRequestCredentials() == null)) {
            credentials = originalRequest.getRequestCredentials();
        }
        new S3QueryStringSigner(methodName.toString(), resourcePath, expiration).sign(request, credentials);
        if (request.getHeaders().containsKey(Headers.SECURITY_TOKEN)) {
            request.addParameter(Headers.SECURITY_TOKEN, (String) request.getHeaders().get(Headers.SECURITY_TOKEN));
            request.getHeaders().remove(Headers.SECURITY_TOKEN);
        }
    }

    private <T> void beforeRequest(Request<T> request) {
        if (this.requestHandler2s != null) {
            for (RequestHandler2 requestHandler2 : this.requestHandler2s) {
                requestHandler2.beforeRequest(request);
            }
        }
    }

    private URI convertToVirtualHostEndpoint(String bucketName) {
        try {
            return new URI(this.endpoint.getScheme() + "://" + bucketName + FileUtils.HIDDEN_PREFIX + this.endpoint.getAuthority());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid bucket name: " + bucketName, e);
        }
    }

    protected static void populateRequestMetadata(Request<?> request, ObjectMetadata metadata) {
        Map<String, Object> rawMetadata = metadata.getRawMetadata();
        if (rawMetadata.get(Headers.SERVER_SIDE_ENCRYPTION_KMS_KEY_ID) == null || ObjectMetadata.KMS_SERVER_SIDE_ENCRYPTION.equals(rawMetadata.get(Headers.SERVER_SIDE_ENCRYPTION))) {
            if (rawMetadata != null) {
                for (Entry<String, Object> entry : rawMetadata.entrySet()) {
                    request.addHeader((String) entry.getKey(), entry.getValue().toString());
                }
            }
            Date httpExpiresDate = metadata.getHttpExpiresDate();
            if (httpExpiresDate != null) {
                request.addHeader("Expires", DateUtils.formatRFC822Date(httpExpiresDate));
            }
            Map<String, String> userMetadata = metadata.getUserMetadata();
            if (userMetadata != null) {
                for (Entry<String, String> entry2 : userMetadata.entrySet()) {
                    String key = (String) entry2.getKey();
                    String value = (String) entry2.getValue();
                    if (key != null) {
                        key = key.trim();
                    }
                    if (value != null) {
                        value = value.trim();
                    }
                    request.addHeader(Headers.S3_USER_METADATA_PREFIX + key, value);
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("If you specify a KMS key id for server side encryption, you must also set the SSEAlgorithm to ObjectMetadata.KMS_SERVER_SIDE_ENCRYPTION");
    }

    private void populateRequestWithMfaDetails(Request<?> request, MultiFactorAuthentication mfa) {
        if (mfa != null) {
            String endpoint = request.getEndpoint().toString();
            if (endpoint.startsWith("http://")) {
                request.setEndpoint(URI.create(endpoint.replace("http://", "https://")));
                log.info("Overriding current endpoint to use HTTPS as required by S3 for requests containing an MFA header");
            }
            request.addHeader(Headers.S3_MFA, mfa.getDeviceSerialNumber() + " " + mfa.getToken());
        }
    }

    private static void populateRequestWithCopyObjectParameters(Request<? extends AmazonWebServiceRequest> request, CopyObjectRequest copyObjectRequest) {
        String copySourceHeader = "/" + HttpUtils.urlEncode(copyObjectRequest.getSourceBucketName(), true) + "/" + HttpUtils.urlEncode(copyObjectRequest.getSourceKey(), true);
        if (copyObjectRequest.getSourceVersionId() != null) {
            copySourceHeader = copySourceHeader + "?versionId=" + copyObjectRequest.getSourceVersionId();
        }
        request.addHeader("x-amz-copy-source", copySourceHeader);
        addDateHeader(request, Headers.COPY_SOURCE_IF_MODIFIED_SINCE, copyObjectRequest.getModifiedSinceConstraint());
        addDateHeader(request, Headers.COPY_SOURCE_IF_UNMODIFIED_SINCE, copyObjectRequest.getUnmodifiedSinceConstraint());
        addStringListHeader(request, Headers.COPY_SOURCE_IF_MATCH, copyObjectRequest.getMatchingETagConstraints());
        addStringListHeader(request, Headers.COPY_SOURCE_IF_NO_MATCH, copyObjectRequest.getNonmatchingETagConstraints());
        if (copyObjectRequest.getAccessControlList() != null) {
            addAclHeaders(request, copyObjectRequest.getAccessControlList());
        } else if (copyObjectRequest.getCannedAccessControlList() != null) {
            request.addHeader(Headers.S3_CANNED_ACL, copyObjectRequest.getCannedAccessControlList().toString());
        }
        if (copyObjectRequest.getStorageClass() != null) {
            request.addHeader(Headers.STORAGE_CLASS, copyObjectRequest.getStorageClass());
        }
        if (copyObjectRequest.getRedirectLocation() != null) {
            request.addHeader(Headers.REDIRECT_LOCATION, copyObjectRequest.getRedirectLocation());
        }
        ObjectMetadata newObjectMetadata = copyObjectRequest.getNewObjectMetadata();
        if (newObjectMetadata != null) {
            request.addHeader(Headers.METADATA_DIRECTIVE, "REPLACE");
            populateRequestMetadata(request, newObjectMetadata);
        }
        populateSourceSseCpkRequestParameters(request, copyObjectRequest.getSourceSSECustomerKey());
        populateSseCpkRequestParameters(request, copyObjectRequest.getDestinationSSECustomerKey());
    }

    private static void populateRequestWithCopyPartParameters(Request<?> request, CopyPartRequest copyPartRequest) {
        String copySourceHeader = "/" + HttpUtils.urlEncode(copyPartRequest.getSourceBucketName(), true) + "/" + HttpUtils.urlEncode(copyPartRequest.getSourceKey(), true);
        if (copyPartRequest.getSourceVersionId() != null) {
            copySourceHeader = copySourceHeader + "?versionId=" + copyPartRequest.getSourceVersionId();
        }
        request.addHeader("x-amz-copy-source", copySourceHeader);
        addDateHeader(request, Headers.COPY_SOURCE_IF_MODIFIED_SINCE, copyPartRequest.getModifiedSinceConstraint());
        addDateHeader(request, Headers.COPY_SOURCE_IF_UNMODIFIED_SINCE, copyPartRequest.getUnmodifiedSinceConstraint());
        addStringListHeader(request, Headers.COPY_SOURCE_IF_MATCH, copyPartRequest.getMatchingETagConstraints());
        addStringListHeader(request, Headers.COPY_SOURCE_IF_NO_MATCH, copyPartRequest.getNonmatchingETagConstraints());
        if (!(copyPartRequest.getFirstByte() == null || copyPartRequest.getLastByte() == null)) {
            request.addHeader(Headers.COPY_PART_RANGE, "bytes=" + copyPartRequest.getFirstByte() + "-" + copyPartRequest.getLastByte());
        }
        populateSourceSseCpkRequestParameters(request, copyPartRequest.getSourceSSECustomerKey());
        populateSseCpkRequestParameters(request, copyPartRequest.getDestinationSSECustomerKey());
    }

    private static void populateSseCpkRequestParameters(Request<?> request, SSECustomerKey sseKey) {
        if (sseKey != null) {
            addHeaderIfNotNull(request, Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM, sseKey.getAlgorithm());
            addHeaderIfNotNull(request, Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY, sseKey.getKey());
            addHeaderIfNotNull(request, Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5, sseKey.getMd5());
            if (sseKey.getKey() != null && sseKey.getMd5() == null) {
                request.addHeader(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5, Md5Utils.md5AsBase64(Base64.decode(sseKey.getKey())));
            }
        }
    }

    private static void populateSourceSseCpkRequestParameters(Request<?> request, SSECustomerKey sseKey) {
        if (sseKey != null) {
            addHeaderIfNotNull(request, Headers.COPY_SOURCE_SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM, sseKey.getAlgorithm());
            addHeaderIfNotNull(request, Headers.COPY_SOURCE_SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY, sseKey.getKey());
            addHeaderIfNotNull(request, Headers.COPY_SOURCE_SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5, sseKey.getMd5());
            if (sseKey.getKey() != null && sseKey.getMd5() == null) {
                request.addHeader(Headers.COPY_SOURCE_SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5, Md5Utils.md5AsBase64(Base64.decode(sseKey.getKey())));
            }
        }
    }

    private static void addHeaderIfNotNull(Request<?> request, String header, String value) {
        if (value != null) {
            request.addHeader(header, value);
        }
    }

    private static void addDateHeader(Request<?> request, String header, Date value) {
        if (value != null) {
            request.addHeader(header, ServiceUtils.formatRfc822Date(value));
        }
    }

    private static void addStringListHeader(Request<?> request, String header, List<String> values) {
        if (values != null && !values.isEmpty()) {
            request.addHeader(header, ServiceUtils.join(values));
        }
    }

    private static void addResponseHeaderParameters(Request<?> request, ResponseHeaderOverrides responseHeaders) {
        if (responseHeaders != null) {
            if (responseHeaders.getCacheControl() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CACHE_CONTROL, responseHeaders.getCacheControl());
            }
            if (responseHeaders.getContentDisposition() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_DISPOSITION, responseHeaders.getContentDisposition());
            }
            if (responseHeaders.getContentEncoding() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_ENCODING, responseHeaders.getContentEncoding());
            }
            if (responseHeaders.getContentLanguage() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_LANGUAGE, responseHeaders.getContentLanguage());
            }
            if (responseHeaders.getContentType() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_TYPE, responseHeaders.getContentType());
            }
            if (responseHeaders.getExpires() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_EXPIRES, responseHeaders.getExpires());
            }
        }
    }

    public String getResourceUrl(String bucketName, String key) {
        try {
            return getUrl(bucketName, key).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public URL getUrl(String bucketName, String key) {
        Request<?> request = new DefaultRequest(Constants.S3_SERVICE_NAME);
        configRequest(request, bucketName, key);
        return ServiceUtils.convertRequestToUrl(request);
    }

    public com.amazonaws.services.s3.model.Region getRegion() {
        String authority = this.endpoint.getAuthority();
        if (Constants.S3_HOSTNAME.equals(authority)) {
            return com.amazonaws.services.s3.model.Region.US_Standard;
        }
        Matcher m = com.amazonaws.services.s3.model.Region.S3_REGIONAL_ENDPOINT_PATTERN.matcher(authority);
        if (m.matches()) {
            return com.amazonaws.services.s3.model.Region.fromValue(m.group(1));
        }
        throw new IllegalStateException("S3 client with invalid S3 endpoint configured");
    }

    protected <X extends AmazonWebServiceRequest> Request<X> createRequest(String bucketName, String key, X originalRequest, HttpMethodName httpMethod) {
        Request<X> request = new DefaultRequest(originalRequest, Constants.S3_SERVICE_NAME);
        request.setHttpMethod(httpMethod);
        configRequest(request, bucketName, key);
        return request;
    }

    private void configRequest(Request<?> request, String bucketName, String key) {
        if (this.clientOptions.isAccelerateModeEnabled() && !(request.getOriginalRequest() instanceof S3AccelerateUnsupported) && BucketNameUtils.isDNSBucketName(bucketName)) {
            String str;
            request.setEndpoint(URI.create(this.clientConfiguration.getProtocol() + "://" + bucketName + FileUtils.HIDDEN_PREFIX + Constants.S3_ACCELERATE_HOSTNAME));
            if (key == null || !key.startsWith("/")) {
                str = key;
            } else {
                str = "/" + key;
            }
            request.setResourcePath(str);
        } else if (this.clientOptions.isPathStyleAccess() || !BucketNameUtils.isDNSBucketName(bucketName) || validIP(this.endpoint.getHost())) {
            request.setEndpoint(this.endpoint);
            if (bucketName != null) {
                request.setResourcePath(bucketName + "/" + (key != null ? key : ""));
            }
        } else {
            request.setEndpoint(convertToVirtualHostEndpoint(bucketName));
            if (key != null && key.startsWith("/")) {
                key = "/" + key;
            }
            request.setResourcePath(key);
        }
    }

    private boolean validIP(String IP) {
        if (IP == null) {
            return false;
        }
        String[] tokens = IP.split("\\.");
        if (tokens.length != 4) {
            return false;
        }
        int length = tokens.length;
        int i = 0;
        while (i < length) {
            try {
                int tokenInt = Integer.parseInt(tokens[i]);
                if (tokenInt < 0 || tokenInt > 255) {
                    return false;
                }
                i++;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private <X, Y extends AmazonWebServiceRequest> X invoke(Request<Y> request, Unmarshaller<X, InputStream> unmarshaller, String bucketName, String key) {
        return invoke((Request) request, new S3XmlResponseHandler(unmarshaller), bucketName, key);
    }

    protected final ExecutionContext createExecutionContext(AmazonWebServiceRequest req) {
        boolean isMetricsEnabled = isRequestMetricsEnabled(req) || AmazonWebServiceClient.isProfilingEnabled();
        return new S3ExecutionContext(this.requestHandler2s, isMetricsEnabled, this);
    }

    private <X, Y extends AmazonWebServiceRequest> X invoke(Request<Y> request, HttpResponseHandler<AmazonWebServiceResponse<X>> responseHandler, String bucket, String key) {
        AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
        ExecutionContext executionContext = createExecutionContext(originalRequest);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        request.setAWSRequestMetrics(awsRequestMetrics);
        awsRequestMetrics.startEvent(Field.ClientExecuteTime);
        Response<X> response = null;
        try {
            request.setTimeOffset(this.timeOffset);
            if (!request.getHeaders().containsKey("Content-Type")) {
                request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            }
            AWSCredentials credentials = this.awsCredentialsProvider.getCredentials();
            if (originalRequest.getRequestCredentials() != null) {
                credentials = originalRequest.getRequestCredentials();
            }
            executionContext.setSigner(createSigner(request, bucket, key));
            executionContext.setCredentials(credentials);
            response = this.client.execute(request, responseHandler, this.errorResponseHandler, executionContext);
            X awsResponse = response.getAwsResponse();
            return awsResponse;
        } finally {
            endClientExecution(awsRequestMetrics, request, response);
        }
    }

    public void enableRequesterPays(String bucketName) {
        setBucketRequestPayment(new SetRequestPaymentConfigurationRequest(bucketName, new RequestPaymentConfiguration(Payer.Requester)));
    }

    public void disableRequesterPays(String bucketName) {
        setBucketRequestPayment(new SetRequestPaymentConfigurationRequest(bucketName, new RequestPaymentConfiguration(Payer.BucketOwner)));
    }

    public boolean isRequesterPaysEnabled(String bucketName) {
        return getBucketRequestPayment(new GetRequestPaymentConfigurationRequest(bucketName)).getPayer() == Payer.Requester;
    }

    private void setBucketRequestPayment(SetRequestPaymentConfigurationRequest setRequestPaymentConfigurationRequest) {
        String bucketName = setRequestPaymentConfigurationRequest.getBucketName();
        RequestPaymentConfiguration configuration = setRequestPaymentConfigurationRequest.getConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified while setting the Requester Pays.");
        assertParameterNotNull(configuration, "The request payment configuration parameter must be specified when setting the Requester Pays.");
        Request request = createRequest(bucketName, null, setRequestPaymentConfigurationRequest, HttpMethodName.PUT);
        request.addParameter("requestPayment", null);
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        byte[] bytes = requestPaymentConfigurationXmlFactory.convertToXmlByteArray(configuration);
        request.addHeader("Content-Length", String.valueOf(bytes.length));
        request.setContent(new ByteArrayInputStream(bytes));
        invoke(request, this.voidResponseHandler, bucketName, null);
    }

    private RequestPaymentConfiguration getBucketRequestPayment(GetRequestPaymentConfigurationRequest getRequestPaymentConfigurationRequest) {
        String bucketName = getRequestPaymentConfigurationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified while getting the Request Payment Configuration.");
        Request request = createRequest(bucketName, null, getRequestPaymentConfigurationRequest, HttpMethodName.GET);
        request.addParameter("requestPayment", null);
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        return (RequestPaymentConfiguration) invoke(request, new RequestPaymentConfigurationUnmarshaller(), bucketName, null);
    }

    private void setZeroContentLength(Request<?> req) {
        req.addHeader("Content-Length", String.valueOf(0));
    }

    private ByteArrayInputStream toByteArray(InputStream is) {
        byte[] buf = new byte[262144];
        int len = 0;
        int available = 262144;
        while (available > 0) {
            try {
                int read = is.read(buf, len, available);
                if (read == -1) {
                    break;
                }
                len += read;
                available -= read;
            } catch (IOException ioe) {
                throw new AmazonClientException("Failed to read from inputstream", ioe);
            }
        }
        if (is.read() != -1) {
            throw new AmazonClientException("Input stream exceeds 256k buffer.");
        }
        is.close();
        return new ByteArrayInputStream(buf, 0, len);
    }

    public void setBucketReplicationConfiguration(String bucketName, BucketReplicationConfiguration configuration) throws AmazonServiceException, AmazonClientException {
        setBucketReplicationConfiguration(new SetBucketReplicationConfigurationRequest(bucketName, configuration));
    }

    public void setBucketReplicationConfiguration(SetBucketReplicationConfigurationRequest setBucketReplicationConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        assertParameterNotNull(setBucketReplicationConfigurationRequest, "The set bucket replication configuration request object must be specified.");
        String bucketName = setBucketReplicationConfigurationRequest.getBucketName();
        BucketReplicationConfiguration bucketReplicationConfiguration = setBucketReplicationConfigurationRequest.getReplicationConfiguration();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting replication configuration.");
        assertParameterNotNull(bucketReplicationConfiguration, "The replication configuration parameter must be specified when setting replication configuration.");
        Request request = createRequest(bucketName, null, setBucketReplicationConfigurationRequest, HttpMethodName.PUT);
        request.addParameter("replication", null);
        byte[] bytes = bucketConfigurationXmlFactory.convertToXmlByteArray(bucketReplicationConfiguration);
        request.addHeader("Content-Length", String.valueOf(bytes.length));
        request.addHeader("Content-Type", Mimetypes.MIMETYPE_XML);
        request.setContent(new ByteArrayInputStream(bytes));
        try {
            request.addHeader(Headers.CONTENT_MD5, BinaryUtils.toBase64(Md5Utils.computeMD5Hash(bytes)));
            invoke(request, this.voidResponseHandler, bucketName, null);
        } catch (Exception e) {
            throw new AmazonClientException("Not able to compute MD5 of the replication rule configuration. Exception Message : " + e.getMessage(), e);
        }
    }

    public BucketReplicationConfiguration getBucketReplicationConfiguration(String bucketName) throws AmazonServiceException, AmazonClientException {
        return getBucketReplicationConfiguration(new GetBucketReplicationConfigurationRequest(bucketName));
    }

    public BucketReplicationConfiguration getBucketReplicationConfiguration(GetBucketReplicationConfigurationRequest getBucketReplicationConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        assertParameterNotNull(getBucketReplicationConfigurationRequest, "The bucket request parameter must be specified when retrieving replication configuration");
        String bucketName = getBucketReplicationConfigurationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket request must specify a bucket name when retrieving replication configuration");
        Request request = createRequest(bucketName, null, getBucketReplicationConfigurationRequest, HttpMethodName.GET);
        request.addParameter("replication", null);
        return (BucketReplicationConfiguration) invoke(request, new BucketReplicationConfigurationUnmarshaller(), bucketName, null);
    }

    public void deleteBucketReplicationConfiguration(String bucketName) throws AmazonServiceException, AmazonClientException {
        deleteBucketReplicationConfiguration(new DeleteBucketReplicationConfigurationRequest(bucketName));
    }

    public void deleteBucketReplicationConfiguration(DeleteBucketReplicationConfigurationRequest deleteBucketReplicationConfigurationRequest) throws AmazonServiceException, AmazonClientException {
        String bucketName = deleteBucketReplicationConfigurationRequest.getBucketName();
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when deleting replication configuration");
        Request request = createRequest(bucketName, null, deleteBucketReplicationConfigurationRequest, HttpMethodName.DELETE);
        request.addParameter("replication", null);
        invoke(request, this.voidResponseHandler, bucketName, null);
    }
}
