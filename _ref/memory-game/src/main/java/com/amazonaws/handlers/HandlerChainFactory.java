package com.amazonaws.handlers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.util.ClassLoaderHelper;
import com.amazonaws.util.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HandlerChainFactory {
    public List<RequestHandler2> newRequestHandlerChain(String resource) {
        return createRequestHandlerChain(resource, RequestHandler.class);
    }

    public List<RequestHandler2> newRequestHandler2Chain(String resource) {
        return createRequestHandlerChain(resource, RequestHandler2.class);
    }

    private List<RequestHandler2> createRequestHandlerChain(String resource, Class<?> handlerApiClass) {
        Exception e;
        Throwable th;
        List<RequestHandler2> handlers = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            InputStream input = getClass().getResourceAsStream(resource);
            if (input != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, StringUtils.UTF8));
                while (true) {
                    try {
                        String requestHandlerClassName = reader.readLine();
                        if (requestHandlerClassName == null) {
                            break;
                        }
                        requestHandlerClassName = requestHandlerClassName.trim();
                        if (!requestHandlerClassName.equals("")) {
                            Object requestHandlerObject = ClassLoaderHelper.loadClass(requestHandlerClassName, handlerApiClass, getClass()).newInstance();
                            if (!handlerApiClass.isInstance(requestHandlerObject)) {
                                throw new AmazonClientException("Unable to instantiate request handler chain for client.  Listed request handler ('" + requestHandlerClassName + "') does not implement the " + handlerApiClass + " API.");
                            } else if (handlerApiClass == RequestHandler2.class) {
                                handlers.add((RequestHandler2) requestHandlerObject);
                            } else if (handlerApiClass == RequestHandler.class) {
                                handlers.add(RequestHandler2.adapt((RequestHandler) requestHandlerObject));
                            } else {
                                throw new IllegalStateException();
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        bufferedReader = reader;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = reader;
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e3) {
                    }
                }
                bufferedReader = reader;
            } else if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                }
            }
            return handlers;
        } catch (Exception e5) {
            e = e5;
            try {
                throw new AmazonClientException("Unable to instantiate request handler chain for client: " + e.getMessage(), e);
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e6) {
                    }
                }
                throw th;
            }
        }
    }
}
