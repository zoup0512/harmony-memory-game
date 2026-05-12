package com.amazonaws;

import com.amazonaws.retry.PredefinedRetryPolicies;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.util.VersionInfoUtils;
import java.net.InetAddress;
import javax.net.ssl.TrustManager;

public class ClientConfiguration {
    public static final int DEFAULT_CONNECTION_TIMEOUT = 15000;
    public static final int DEFAULT_MAX_CONNECTIONS = 10;
    public static final RetryPolicy DEFAULT_RETRY_POLICY = PredefinedRetryPolicies.DEFAULT;
    public static final int DEFAULT_SOCKET_TIMEOUT = 15000;
    public static final String DEFAULT_USER_AGENT = VersionInfoUtils.getUserAgent();
    public static final boolean DEFAULT_USE_REAPER = true;
    private int connectionTimeout = 15000;
    private InetAddress localAddress;
    private int maxConnections = 10;
    private int maxErrorRetry = -1;
    private boolean preemptiveBasicProxyAuth;
    private Protocol protocol = Protocol.HTTPS;
    @Deprecated
    private String proxyDomain = null;
    private String proxyHost = null;
    private String proxyPassword = null;
    private int proxyPort = -1;
    private String proxyUsername = null;
    @Deprecated
    private String proxyWorkstation = null;
    private RetryPolicy retryPolicy = DEFAULT_RETRY_POLICY;
    private String signerOverride;
    private int socketReceiveBufferSizeHint = 0;
    private int socketSendBufferSizeHint = 0;
    private int socketTimeout = 15000;
    private TrustManager trustManager = null;
    private boolean useReaper = true;
    private String userAgent = DEFAULT_USER_AGENT;

    public ClientConfiguration(ClientConfiguration other) {
        this.connectionTimeout = other.connectionTimeout;
        this.maxConnections = other.maxConnections;
        this.maxErrorRetry = other.maxErrorRetry;
        this.retryPolicy = other.retryPolicy;
        this.localAddress = other.localAddress;
        this.protocol = other.protocol;
        this.proxyDomain = other.proxyDomain;
        this.proxyHost = other.proxyHost;
        this.proxyPassword = other.proxyPassword;
        this.proxyPort = other.proxyPort;
        this.proxyUsername = other.proxyUsername;
        this.proxyWorkstation = other.proxyWorkstation;
        this.preemptiveBasicProxyAuth = other.preemptiveBasicProxyAuth;
        this.socketTimeout = other.socketTimeout;
        this.userAgent = other.userAgent;
        this.useReaper = other.useReaper;
        this.socketReceiveBufferSizeHint = other.socketReceiveBufferSizeHint;
        this.socketSendBufferSizeHint = other.socketSendBufferSizeHint;
        this.signerOverride = other.signerOverride;
        this.trustManager = other.trustManager;
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public ClientConfiguration withProtocol(Protocol protocol) {
        setProtocol(protocol);
        return this;
    }

    public int getMaxConnections() {
        return this.maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public ClientConfiguration withMaxConnections(int maxConnections) {
        setMaxConnections(maxConnections);
        return this;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public ClientConfiguration withUserAgent(String userAgent) {
        setUserAgent(userAgent);
        return this;
    }

    public InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public void setLocalAddress(InetAddress localAddress) {
        this.localAddress = localAddress;
    }

    public ClientConfiguration withLocalAddress(InetAddress localAddress) {
        setLocalAddress(localAddress);
        return this;
    }

    public String getProxyHost() {
        return this.proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public ClientConfiguration withProxyHost(String proxyHost) {
        setProxyHost(proxyHost);
        return this;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public ClientConfiguration withProxyPort(int proxyPort) {
        setProxyPort(proxyPort);
        return this;
    }

    public String getProxyUsername() {
        return this.proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public ClientConfiguration withProxyUsername(String proxyUsername) {
        setProxyUsername(proxyUsername);
        return this;
    }

    public String getProxyPassword() {
        return this.proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public ClientConfiguration withProxyPassword(String proxyPassword) {
        setProxyPassword(proxyPassword);
        return this;
    }

    @Deprecated
    public String getProxyDomain() {
        return this.proxyDomain;
    }

    @Deprecated
    public void setProxyDomain(String proxyDomain) {
        this.proxyDomain = proxyDomain;
    }

    @Deprecated
    public ClientConfiguration withProxyDomain(String proxyDomain) {
        setProxyDomain(proxyDomain);
        return this;
    }

    public String getProxyWorkstation() {
        return this.proxyWorkstation;
    }

    @Deprecated
    public void setProxyWorkstation(String proxyWorkstation) {
        this.proxyWorkstation = proxyWorkstation;
    }

    @Deprecated
    public ClientConfiguration withProxyWorkstation(String proxyWorkstation) {
        setProxyWorkstation(proxyWorkstation);
        return this;
    }

    public RetryPolicy getRetryPolicy() {
        return this.retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public ClientConfiguration withRetryPolicy(RetryPolicy retryPolicy) {
        setRetryPolicy(retryPolicy);
        return this;
    }

    public int getMaxErrorRetry() {
        return this.maxErrorRetry;
    }

    public void setMaxErrorRetry(int maxErrorRetry) {
        if (maxErrorRetry < 0) {
            throw new IllegalArgumentException("maxErrorRetry shoud be non-negative");
        }
        this.maxErrorRetry = maxErrorRetry;
    }

    public ClientConfiguration withMaxErrorRetry(int maxErrorRetry) {
        setMaxErrorRetry(maxErrorRetry);
        return this;
    }

    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public ClientConfiguration withSocketTimeout(int socketTimeout) {
        setSocketTimeout(socketTimeout);
        return this;
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public ClientConfiguration withConnectionTimeout(int connectionTimeout) {
        setConnectionTimeout(connectionTimeout);
        return this;
    }

    public boolean useReaper() {
        return this.useReaper;
    }

    public void setUseReaper(boolean use) {
        this.useReaper = use;
    }

    public ClientConfiguration withReaper(boolean use) {
        setUseReaper(use);
        return this;
    }

    public int[] getSocketBufferSizeHints() {
        return new int[]{this.socketSendBufferSizeHint, this.socketReceiveBufferSizeHint};
    }

    public void setSocketBufferSizeHints(int socketSendBufferSizeHint, int socketReceiveBufferSizeHint) {
        this.socketSendBufferSizeHint = socketSendBufferSizeHint;
        this.socketReceiveBufferSizeHint = socketReceiveBufferSizeHint;
    }

    public ClientConfiguration withSocketBufferSizeHints(int socketSendBufferSizeHint, int socketReceiveBufferSizeHint) {
        setSocketBufferSizeHints(socketSendBufferSizeHint, socketReceiveBufferSizeHint);
        return this;
    }

    public String getSignerOverride() {
        return this.signerOverride;
    }

    public void setSignerOverride(String value) {
        this.signerOverride = value;
    }

    public ClientConfiguration withSignerOverride(String value) {
        setSignerOverride(value);
        return this;
    }

    public boolean isPreemptiveBasicProxyAuth() {
        return this.preemptiveBasicProxyAuth;
    }

    public void setPreemptiveBasicProxyAuth(Boolean preemptiveBasicProxyAuth) {
        this.preemptiveBasicProxyAuth = preemptiveBasicProxyAuth.booleanValue();
    }

    public ClientConfiguration withPreemptiveBasicProxyAuth(boolean preemptiveBasicProxyAuth) {
        setPreemptiveBasicProxyAuth(Boolean.valueOf(preemptiveBasicProxyAuth));
        return this;
    }

    public TrustManager getTrustManager() {
        return this.trustManager;
    }

    public void setTrustManager(TrustManager trustManager) {
        this.trustManager = trustManager;
    }

    public ClientConfiguration withTrustManager(TrustManager trustManager) {
        setTrustManager(trustManager);
        return this;
    }
}
