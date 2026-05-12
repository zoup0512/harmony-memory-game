package com.amazonaws.http.conn;

import com.amazonaws.metrics.AwsSdkMetrics;
import com.amazonaws.metrics.ServiceLatencyProvider;
import com.amazonaws.util.AWSServiceMetrics;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ClientConnectionRequest;

class ClientConnectionRequestFactory {
    private static final Class<?>[] interfaces = new Class[]{ClientConnectionRequest.class, Wrapped.class};
    private static final Log log = LogFactory.getLog(ClientConnectionRequestFactory.class);

    private static class Handler implements InvocationHandler {
        private final ClientConnectionRequest orig;

        Handler(ClientConnectionRequest orig) {
            this.orig = orig;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ServiceLatencyProvider latencyProvider;
            try {
                Object invoke;
                if ("getConnection".equals(method.getName())) {
                    latencyProvider = new ServiceLatencyProvider(AWSServiceMetrics.HttpClientGetConnectionTime);
                    invoke = method.invoke(this.orig, args);
                    AwsSdkMetrics.getServiceMetricCollector().collectLatency(latencyProvider.endTiming());
                } else {
                    invoke = method.invoke(this.orig, args);
                }
                return invoke;
            } catch (InvocationTargetException e) {
                ClientConnectionRequestFactory.log.debug("", e);
                throw e.getCause();
            } catch (Throwable th) {
                AwsSdkMetrics.getServiceMetricCollector().collectLatency(latencyProvider.endTiming());
            }
        }
    }

    ClientConnectionRequestFactory() {
    }

    static ClientConnectionRequest wrap(ClientConnectionRequest orig) {
        if (!(orig instanceof Wrapped)) {
            return (ClientConnectionRequest) Proxy.newProxyInstance(ClientConnectionRequestFactory.class.getClassLoader(), interfaces, new Handler(orig));
        }
        throw new IllegalArgumentException();
    }
}
