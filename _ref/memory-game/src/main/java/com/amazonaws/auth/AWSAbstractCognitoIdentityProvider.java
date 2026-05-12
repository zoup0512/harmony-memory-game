package com.amazonaws.auth;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.GetIdRequest;
import com.amazonaws.services.cognitoidentity.model.GetIdResult;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenRequest;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AWSAbstractCognitoIdentityProvider implements AWSCognitoIdentityProvider {
    private final String accountId;
    protected final AmazonCognitoIdentity cib;
    protected String identityId;
    private final String identityPoolId;
    protected List<IdentityChangedListener> listeners;
    protected Map<String, String> loginsMap;
    protected String token;

    public abstract String getProviderName();

    public AWSAbstractCognitoIdentityProvider(String accountId, String identityPoolId, AmazonCognitoIdentity cibClient) {
        this.accountId = accountId;
        this.identityPoolId = identityPoolId;
        this.loginsMap = new HashMap();
        this.listeners = new ArrayList();
        this.cib = cibClient;
    }

    @Deprecated
    public AWSAbstractCognitoIdentityProvider(String accountId, String identityPoolId, ClientConfiguration clientConfiguration) {
        this(accountId, identityPoolId, new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration));
    }

    public AWSAbstractCognitoIdentityProvider(String accountId, String identityPoolId, ClientConfiguration clientConfiguration, Regions region) {
        this(accountId, identityPoolId, new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration));
        this.cib.setRegion(Region.getRegion(region));
    }

    @Deprecated
    public AWSAbstractCognitoIdentityProvider(String accountId, String identityPoolId) {
        this(accountId, identityPoolId, new ClientConfiguration());
    }

    public AWSAbstractCognitoIdentityProvider(String accountId, String identityPoolId, Regions region) {
        this(accountId, identityPoolId, new ClientConfiguration(), region);
    }

    public String getIdentityId() {
        if (this.identityId == null) {
            GetIdRequest getIdRequest = new GetIdRequest().withAccountId(getAccountId()).withIdentityPoolId(getIdentityPoolId()).withLogins(this.loginsMap);
            appendUserAgent(getIdRequest, getUserAgent());
            GetIdResult getIdResult = this.cib.getId(getIdRequest);
            if (getIdResult.getIdentityId() != null) {
                identityChanged(getIdResult.getIdentityId());
            }
        }
        return this.identityId;
    }

    protected void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getToken() {
        if (this.token == null) {
            GetOpenIdTokenRequest getTokenRequest = new GetOpenIdTokenRequest().withIdentityId(getIdentityId()).withLogins(this.loginsMap);
            appendUserAgent(getTokenRequest, getUserAgent());
            GetOpenIdTokenResult getTokenResult = this.cib.getOpenIdToken(getTokenRequest);
            if (!getTokenResult.getIdentityId().equals(getIdentityId())) {
                identityChanged(getTokenResult.getIdentityId());
            }
            this.token = getTokenResult.getToken();
        }
        return this.token;
    }

    protected void setToken(String token) {
        this.token = token;
    }

    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public Map<String, String> getLogins() {
        return this.loginsMap;
    }

    public void setLogins(Map<String, String> logins) {
        this.loginsMap = logins;
    }

    public boolean isAuthenticated() {
        return this.loginsMap != null && this.loginsMap.size() > 0;
    }

    public void unregisterIdentityChangedListener(IdentityChangedListener listener) {
        this.listeners.remove(listener);
    }

    public void registerIdentityChangedListener(IdentityChangedListener listener) {
        this.listeners.add(listener);
    }

    public void identityChanged(String newIdentityId) {
        if (this.identityId == null || !this.identityId.equals(newIdentityId)) {
            String oldIdentityId = this.identityId;
            this.identityId = newIdentityId;
            for (IdentityChangedListener listener : this.listeners) {
                listener.identityChanged(oldIdentityId, this.identityId);
            }
        }
    }

    protected void appendUserAgent(AmazonWebServiceRequest request, String userAgent) {
        request.getRequestClientOptions().appendUserAgent(userAgent);
    }

    public void clearListeners() {
        this.listeners.clear();
    }

    protected String getUserAgent() {
        return "";
    }

    protected void update(String identityId, String token) {
        if (this.identityId == null || !this.identityId.equals(identityId)) {
            identityChanged(identityId);
        }
        if (this.token == null || !this.token.equals(token)) {
            this.token = token;
        }
    }

    public String refresh() {
        getIdentityId();
        String token = getToken();
        update(getIdentityId(), token);
        return token;
    }
}
