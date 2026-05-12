package com.amazonaws.auth;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityResult;
import com.amazonaws.services.securitytoken.model.Credentials;
import java.util.Date;

public class WebIdentityFederationSessionCredentialsProvider implements AWSCredentialsProvider {
    public static final int DEFAULT_DURATION_SECONDS = 3600;
    public static final int DEFAULT_THRESHOLD_SECONDS = 500;
    private int refreshThreshold;
    private final String roleArn;
    private final AWSSecurityTokenService securityTokenService;
    private AWSSessionCredentials sessionCredentials;
    private Date sessionCredentialsExpiration;
    private int sessionDuration;
    private String subjectFromWIF;
    private final String wifProvider;
    private final String wifToken;

    public WebIdentityFederationSessionCredentialsProvider(String wifToken, String wifProvider, String roleArn) {
        this(wifToken, wifProvider, roleArn, new ClientConfiguration());
    }

    public WebIdentityFederationSessionCredentialsProvider(String wifToken, String wifProvider, String roleArn, ClientConfiguration clientConfiguration) {
        this(wifToken, wifProvider, roleArn, new AWSSecurityTokenServiceClient(new AnonymousAWSCredentials(), clientConfiguration));
    }

    public WebIdentityFederationSessionCredentialsProvider(String wifToken, String wifProvider, String roleArn, AWSSecurityTokenService stsClient) {
        this.securityTokenService = stsClient;
        this.wifProvider = wifProvider;
        this.wifToken = wifToken;
        this.roleArn = roleArn;
        this.sessionDuration = 3600;
        this.refreshThreshold = 500;
    }

    public AWSCredentials getCredentials() {
        if (needsNewSession()) {
            startSession();
        }
        return this.sessionCredentials;
    }

    public void refresh() {
        startSession();
    }

    public void setSessionDuration(int sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public WebIdentityFederationSessionCredentialsProvider withSessionDuration(int sessionDuration) {
        setSessionDuration(sessionDuration);
        return this;
    }

    public int getSessionDuration() {
        return this.sessionDuration;
    }

    public void setRefreshThreshold(int refreshThreshold) {
        this.refreshThreshold = refreshThreshold;
    }

    public WebIdentityFederationSessionCredentialsProvider withRefreshThreshold(int refreshThreshold) {
        setRefreshThreshold(refreshThreshold);
        return this;
    }

    public int getRefreshThreshold() {
        return this.refreshThreshold;
    }

    public String getSubjectFromWIF() {
        return this.subjectFromWIF;
    }

    private void startSession() {
        AssumeRoleWithWebIdentityResult sessionTokenResult = this.securityTokenService.assumeRoleWithWebIdentity(new AssumeRoleWithWebIdentityRequest().withWebIdentityToken(this.wifToken).withProviderId(this.wifProvider).withRoleArn(this.roleArn).withRoleSessionName("ProviderSession").withDurationSeconds(Integer.valueOf(this.sessionDuration)));
        Credentials stsCredentials = sessionTokenResult.getCredentials();
        this.subjectFromWIF = sessionTokenResult.getSubjectFromWebIdentityToken();
        this.sessionCredentials = new BasicSessionCredentials(stsCredentials.getAccessKeyId(), stsCredentials.getSecretAccessKey(), stsCredentials.getSessionToken());
        this.sessionCredentialsExpiration = stsCredentials.getExpiration();
    }

    private boolean needsNewSession() {
        if (this.sessionCredentials != null && this.sessionCredentialsExpiration.getTime() - System.currentTimeMillis() >= ((long) (this.refreshThreshold * 1000))) {
            return false;
        }
        return true;
    }
}
