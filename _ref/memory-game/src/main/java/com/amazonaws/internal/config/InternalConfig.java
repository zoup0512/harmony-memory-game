package com.amazonaws.internal.config;

import com.amazonaws.regions.ServiceAbbreviations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InternalConfig {
    private static final String SERVICE_REGION_DELIMITOR = "/";
    private static final Log log = LogFactory.getLog(InternalConfig.class);
    private final SignerConfig defaultSignerConfig = getDefaultSigner();
    private final List<HostRegexToRegionMapping> hostRegexToRegionMappings = getDefaultHostRegexToRegionMappings();
    private final Map<String, HttpClientConfig> httpClients = getDefaultHttpClients();
    private final Map<String, SignerConfig> regionSigners = getDefaultRegionSigners();
    private final Map<String, SignerConfig> serviceRegionSigners = getDefaultServiceRegionSigners();
    private final Map<String, SignerConfig> serviceSigners = getDefaultServiceSigners();

    public static class Factory {
        private static final InternalConfig SINGELTON = new InternalConfig();

        static {
            try {
            } catch (RuntimeException ex) {
                throw ex;
            } catch (Exception ex2) {
                throw new IllegalStateException("Fatal: Failed to load the internal config for AWS Android SDK", ex2);
            }
        }

        public static InternalConfig getInternalConfig() {
            return SINGELTON;
        }
    }

    InternalConfig() {
    }

    public SignerConfig getSignerConfig(String serviceName) {
        return getSignerConfig(serviceName, null);
    }

    public HttpClientConfig getHttpClientConfig(String httpClientName) {
        return (HttpClientConfig) this.httpClients.get(httpClientName);
    }

    public SignerConfig getSignerConfig(String serviceName, String regionName) {
        if (serviceName == null) {
            throw new IllegalArgumentException();
        }
        SignerConfig signerConfig;
        if (regionName != null) {
            signerConfig = (SignerConfig) this.serviceRegionSigners.get(serviceName + SERVICE_REGION_DELIMITOR + regionName);
            if (signerConfig != null) {
                return signerConfig;
            }
            signerConfig = (SignerConfig) this.regionSigners.get(regionName);
            if (signerConfig != null) {
                return signerConfig;
            }
        }
        signerConfig = (SignerConfig) this.serviceSigners.get(serviceName);
        return signerConfig == null ? this.defaultSignerConfig : signerConfig;
    }

    public List<HostRegexToRegionMapping> getHostRegexToRegionMappings() {
        return Collections.unmodifiableList(this.hostRegexToRegionMappings);
    }

    private static Map<String, HttpClientConfig> getDefaultHttpClients() {
        Map<String, HttpClientConfig> ret = new HashMap();
        ret.put("AmazonCloudWatchClient", new HttpClientConfig(ServiceAbbreviations.CloudWatch));
        ret.put("AmazonSimpleDBClient", new HttpClientConfig(ServiceAbbreviations.SimpleDB));
        ret.put("AmazonSimpleEmailServiceClient", new HttpClientConfig("email"));
        ret.put("AWSSecurityTokenServiceClient", new HttpClientConfig(ServiceAbbreviations.STS));
        ret.put("AmazonCognitoIdentityClient", new HttpClientConfig("cognito-identity"));
        ret.put("AmazonCognitoIdentityProviderClient", new HttpClientConfig("cognito-idp"));
        ret.put("AmazonCognitoSyncClient", new HttpClientConfig("cognito-sync"));
        ret.put("AmazonKinesisFirehoseClient", new HttpClientConfig("firehose"));
        ret.put("AWSIotClient", new HttpClientConfig("execute-api"));
        return ret;
    }

    private static Map<String, SignerConfig> getDefaultRegionSigners() {
        Map<String, SignerConfig> ret = new HashMap();
        ret.put("eu-central-1", new SignerConfig("AWS4SignerType"));
        ret.put("cn-north-1", new SignerConfig("AWS4SignerType"));
        return ret;
    }

    private static Map<String, SignerConfig> getDefaultServiceRegionSigners() {
        Map<String, SignerConfig> ret = new HashMap();
        ret.put("s3/eu-central-1", new SignerConfig("AWSS3V4SignerType"));
        ret.put("s3/cn-north-1", new SignerConfig("AWSS3V4SignerType"));
        return ret;
    }

    private static Map<String, SignerConfig> getDefaultServiceSigners() {
        Map<String, SignerConfig> ret = new HashMap();
        ret.put(ServiceAbbreviations.EC2, new SignerConfig("QueryStringSignerType"));
        ret.put("email", new SignerConfig("AWS3SignerType"));
        ret.put("s3", new SignerConfig("S3SignerType"));
        ret.put(ServiceAbbreviations.SimpleDB, new SignerConfig("QueryStringSignerType"));
        return ret;
    }

    private static SignerConfig getDefaultSigner() {
        return new SignerConfig("AWS4SignerType");
    }

    private static List<HostRegexToRegionMapping> getDefaultHostRegexToRegionMappings() {
        List<HostRegexToRegionMapping> ret = new ArrayList();
        ret.add(new HostRegexToRegionMapping("(.+\\.)?s3\\.amazonaws\\.com", "us-east-1"));
        ret.add(new HostRegexToRegionMapping("(.+\\.)?s3-external-1\\.amazonaws\\.com", "us-east-1"));
        ret.add(new HostRegexToRegionMapping("(.+\\.)?s3-fips-us-gov-west-1\\.amazonaws\\.com", "us-gov-west-1"));
        return ret;
    }

    void dump() {
        log.debug("defaultSignerConfig: " + this.defaultSignerConfig + "\n" + "serviceRegionSigners: " + this.serviceRegionSigners + "\n" + "regionSigners: " + this.regionSigners + "\n" + "serviceSigners: " + this.serviceSigners + "\n" + "hostRegexToRegionMappings: " + this.hostRegexToRegionMappings);
    }
}
