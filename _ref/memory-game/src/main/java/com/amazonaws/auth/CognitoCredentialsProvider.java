package com.amazonaws.auth;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.Credentials;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityResult;
import com.amazonaws.services.cognitoidentity.model.ResourceNotFoundException;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CognitoCredentialsProvider implements AWSCredentialsProvider {
    public static final int DEFAULT_DURATION_SECONDS = 3600;
    public static final int DEFAULT_THRESHOLD_SECONDS = 500;
    protected String authRoleArn;
    private AmazonCognitoIdentity cib;
    protected String customRoleArn;
    private final AWSCognitoIdentityProvider identityProvider;
    protected int refreshThreshold;
    protected AWSSecurityTokenService securityTokenService;
    protected AWSSessionCredentials sessionCredentials;
    protected Date sessionCredentialsExpiration;
    protected int sessionDuration;
    protected String token;
    protected String unauthRoleArn;
    protected boolean useEnhancedFlow;

    public CognitoCredentialsProvider(String accountId, String identityPoolId, String unauthRoleArn, String authRoleArn, Regions region) {
        this(accountId, identityPoolId, unauthRoleArn, authRoleArn, region, new ClientConfiguration());
    }

    public CognitoCredentialsProvider(String accountId, String identityPoolId, String unauthRoleArn, String authRoleArn, Regions region, ClientConfiguration clientConfiguration) {
        AmazonCognitoIdentityClient amazonCognitoIdentityClient = new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration);
        AWSSecurityTokenService aWSSecurityTokenServiceClient = (unauthRoleArn == null && authRoleArn == null) ? null : new AWSSecurityTokenServiceClient(new AnonymousAWSCredentials(), clientConfiguration);
        this(accountId, identityPoolId, unauthRoleArn, authRoleArn, amazonCognitoIdentityClient, aWSSecurityTokenServiceClient);
        this.cib.setRegion(Region.getRegion(region));
    }

    public CognitoCredentialsProvider(String identityPoolId, Regions region) {
        this(null, identityPoolId, null, null, region, new ClientConfiguration());
    }

    public CognitoCredentialsProvider(String identityPoolId, Regions region, ClientConfiguration clientConfiguration) {
        this(null, identityPoolId, null, null, region, clientConfiguration);
    }

    public CognitoCredentialsProvider(String accountId, String identityPoolId, String unauthRoleArn, String authRoleArn, AmazonCognitoIdentityClient cibClient, AWSSecurityTokenService stsClient) {
        this.cib = cibClient;
        this.securityTokenService = stsClient;
        this.unauthRoleArn = unauthRoleArn;
        this.authRoleArn = authRoleArn;
        this.sessionDuration = 3600;
        this.refreshThreshold = 500;
        boolean z = unauthRoleArn == null && authRoleArn == null;
        this.useEnhancedFlow = z;
        if (this.useEnhancedFlow) {
            this.identityProvider = new AWSEnhancedCognitoIdentityProvider(accountId, identityPoolId, (AmazonCognitoIdentity) cibClient);
        } else {
            this.identityProvider = new AWSBasicCognitoIdentityProvider(accountId, identityPoolId, (AmazonCognitoIdentity) cibClient);
        }
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider provider, String unauthArn, String authArn) {
        this(provider, unauthArn, authArn, new AWSSecurityTokenServiceClient(new AnonymousAWSCredentials(), new ClientConfiguration()));
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider provider, String unauthArn, String authArn, AWSSecurityTokenService stsClient) {
        this.identityProvider = provider;
        this.unauthRoleArn = unauthArn;
        this.authRoleArn = authArn;
        this.securityTokenService = stsClient;
        this.sessionDuration = 3600;
        this.refreshThreshold = 500;
        this.useEnhancedFlow = false;
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider provider, Regions region) {
        this(provider, region, new ClientConfiguration());
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider provider, Regions region, ClientConfiguration clientConfiguration) {
        this(provider, new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration));
        this.cib.setRegion(Region.getRegion(region));
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider provider, AmazonCognitoIdentityClient cib) {
        this.cib = cib;
        this.identityProvider = provider;
        this.unauthRoleArn = null;
        this.authRoleArn = null;
        this.securityTokenService = null;
        this.sessionDuration = 3600;
        this.refreshThreshold = 500;
        this.useEnhancedFlow = true;
    }

    public String getIdentityId() {
        return this.identityProvider.getIdentityId();
    }

    public String getToken() {
        return this.identityProvider.getToken();
    }

    public AWSIdentityProvider getIdentityProvider() {
        return this.identityProvider;
    }

    public void setSessionCredentialsExpiration(Date expiration) {
        this.sessionCredentialsExpiration = expiration;
    }

    public Date getSessionCredentitalsExpiration() {
        return this.sessionCredentialsExpiration;
    }

    public String getIdentityPoolId() {
        return this.identityProvider.getIdentityPoolId();
    }

    public AWSSessionCredentials getCredentials() {
        if (needsNewSession()) {
            startSession();
        }
        return this.sessionCredentials;
    }

    public void setSessionDuration(int sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public CognitoCredentialsProvider withSessionDuration(int sessionDuration) {
        setSessionDuration(sessionDuration);
        return this;
    }

    public int getSessionDuration() {
        return this.sessionDuration;
    }

    public void setRefreshThreshold(int refreshThreshold) {
        this.refreshThreshold = refreshThreshold;
    }

    public CognitoCredentialsProvider withRefreshThreshold(int refreshThreshold) {
        setRefreshThreshold(refreshThreshold);
        return this;
    }

    public int getRefreshThreshold() {
        return this.refreshThreshold;
    }

    protected void setIdentityId(String identityId) {
        this.identityProvider.identityChanged(identityId);
    }

    public void setLogins(Map<String, String> logins) {
        this.identityProvider.setLogins(logins);
        clearCredentials();
    }

    public String getCustomRoleArn() {
        return this.customRoleArn;
    }

    public void setCustomRoleArn(String customRoleArn) {
        this.customRoleArn = customRoleArn;
    }

    public AWSCredentialsProvider withLogins(Map<String, String> logins) {
        setLogins(logins);
        return this;
    }

    public Map<String, String> getLogins() {
        return this.identityProvider.getLogins();
    }

    public void refresh() {
        startSession();
    }

    public void clear() {
        clearCredentials();
        setIdentityId(null);
        this.identityProvider.setLogins(new HashMap());
    }

    public void clearCredentials() {
        this.sessionCredentials = null;
        this.sessionCredentialsExpiration = null;
    }

    protected void startSession() {
        try {
            this.token = this.identityProvider.refresh();
        } catch (ResourceNotFoundException e) {
            this.token = retryRefresh();
        } catch (AmazonServiceException ase) {
            if (ase.getErrorCode().equals("ValidationException")) {
                this.token = retryRefresh();
            } else {
                throw ase;
            }
        }
        if (this.useEnhancedFlow) {
            populateCredentialsWithCognito(this.token);
        } else {
            populateCredentialsWithSts(this.token);
        }
    }

    private String retryRefresh() {
        setIdentityId(null);
        this.token = this.identityProvider.refresh();
        return this.token;
    }

    private GetCredentialsForIdentityResult retryGetCredentialsForIdentity() {
        Map<String, String> logins;
        this.token = retryRefresh();
        if (this.token == null || this.token.isEmpty()) {
            logins = getLogins();
        } else {
            logins = new HashMap();
            logins.put("cognito-identity.amazonaws.com", this.token);
        }
        return this.cib.getCredentialsForIdentity(new GetCredentialsForIdentityRequest().withIdentityId(getIdentityId()).withLogins(logins).withCustomRoleArn(this.customRoleArn));
    }

    private void populateCredentialsWithCognito(String token) {
        Map<String, String> logins;
        GetCredentialsForIdentityResult result;
        if (token == null || token.isEmpty()) {
            logins = getLogins();
        } else {
            logins = new HashMap();
            logins.put("cognito-identity.amazonaws.com", token);
        }
        try {
            result = this.cib.getCredentialsForIdentity(new GetCredentialsForIdentityRequest().withIdentityId(getIdentityId()).withLogins(logins).withCustomRoleArn(this.customRoleArn));
        } catch (ResourceNotFoundException e) {
            result = retryGetCredentialsForIdentity();
        } catch (AmazonServiceException ase) {
            if (ase.getErrorCode().equals("ValidationException")) {
                result = retryGetCredentialsForIdentity();
            } else {
                throw ase;
            }
        }
        Credentials credentials = result.getCredentials();
        this.sessionCredentials = new BasicSessionCredentials(credentials.getAccessKeyId(), credentials.getSecretKey(), credentials.getSessionToken());
        this.sessionCredentialsExpiration = credentials.getExpiration();
        if (!result.getIdentityId().equals(getIdentityId())) {
            setIdentityId(result.getIdentityId());
        }
    }

    private void populateCredentialsWithSts(String token) {
        AssumeRoleWithWebIdentityRequest sessionTokenRequest = new AssumeRoleWithWebIdentityRequest().withWebIdentityToken(token).withRoleArn(this.identityProvider.isAuthenticated() ? this.authRoleArn : this.unauthRoleArn).withRoleSessionName("ProviderSession").withDurationSeconds(Integer.valueOf(this.sessionDuration));
        appendUserAgent(sessionTokenRequest, getUserAgent());
        com.amazonaws.services.securitytoken.model.Credentials stsCredentials = this.securityTokenService.assumeRoleWithWebIdentity(sessionTokenRequest).getCredentials();
        this.sessionCredentials = new BasicSessionCredentials(stsCredentials.getAccessKeyId(), stsCredentials.getSecretAccessKey(), stsCredentials.getSessionToken());
        this.sessionCredentialsExpiration = stsCredentials.getExpiration();
    }

    protected boolean needsNewSession() {
        if (this.sessionCredentials == null) {
            return true;
        }
        if (this.sessionCredentialsExpiration.getTime() - (System.currentTimeMillis() - ((long) (SDKGlobalConfiguration.getGlobalTimeOffset() * 1000))) >= ((long) (this.refreshThreshold * 1000))) {
            return false;
        }
        return true;
    }

    private void appendUserAgent(AmazonWebServiceRequest request, String userAgent) {
        request.getRequestClientOptions().appendUserAgent(userAgent);
    }

    protected String getUserAgent() {
        return "";
    }

    public void registerIdentityChangedListener(IdentityChangedListener listener) {
        this.identityProvider.registerIdentityChangedListener(listener);
    }

    public void unregisterIdentityChangedListener(IdentityChangedListener listener) {
        this.identityProvider.unregisterIdentityChangedListener(listener);
    }
}
