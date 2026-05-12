package com.amazonaws.http;

import com.amazonaws.ClientConfiguration;
import com.mopub.common.Constants;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

class ConnectionManagerFactory {
    ConnectionManagerFactory() {
    }

    public static ThreadSafeClientConnManager createThreadSafeClientConnManager(ClientConfiguration config, HttpParams httpClientParams) {
        ConnManagerParams.setMaxConnectionsPerRoute(httpClientParams, new ConnPerRouteBean(config.getMaxConnections()));
        ConnManagerParams.setMaxTotalConnections(httpClientParams, config.getMaxConnections());
        SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
        sslSocketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme(Constants.HTTP, PlainSocketFactory.getSocketFactory(), 80));
        registry.register(new Scheme(Constants.HTTPS, sslSocketFactory, 443));
        ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager(httpClientParams, registry);
        if (config.useReaper()) {
            IdleConnectionReaper.registerConnectionManager(connectionManager);
        }
        return connectionManager;
    }
}
