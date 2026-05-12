package com.appodeal.ads.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class o extends SSLSocketFactory {
    private final SSLSocketFactory a;

    public class a extends SSLSocket {
        protected final SSLSocket a;
        final /* synthetic */ o b;

        a(o oVar, SSLSocket sSLSocket) {
            this.b = oVar;
            this.a = sSLSocket;
        }

        public String[] getSupportedCipherSuites() {
            return this.a.getSupportedCipherSuites();
        }

        public String[] getEnabledCipherSuites() {
            return this.a.getEnabledCipherSuites();
        }

        public void setEnabledCipherSuites(String[] strArr) {
            this.a.setEnabledCipherSuites(strArr);
        }

        public String[] getSupportedProtocols() {
            return this.a.getSupportedProtocols();
        }

        public String[] getEnabledProtocols() {
            return this.a.getEnabledProtocols();
        }

        public void setEnabledProtocols(String[] strArr) {
            this.a.setEnabledProtocols(strArr);
        }

        public SSLSession getSession() {
            return this.a.getSession();
        }

        public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.a.addHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.a.removeHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void startHandshake() {
            this.a.startHandshake();
        }

        public void setUseClientMode(boolean z) {
            this.a.setUseClientMode(z);
        }

        public boolean getUseClientMode() {
            return this.a.getUseClientMode();
        }

        public void setNeedClientAuth(boolean z) {
            this.a.setNeedClientAuth(z);
        }

        public void setWantClientAuth(boolean z) {
            this.a.setWantClientAuth(z);
        }

        public boolean getNeedClientAuth() {
            return this.a.getNeedClientAuth();
        }

        public boolean getWantClientAuth() {
            return this.a.getWantClientAuth();
        }

        public void setEnableSessionCreation(boolean z) {
            this.a.setEnableSessionCreation(z);
        }

        public boolean getEnableSessionCreation() {
            return this.a.getEnableSessionCreation();
        }

        public void bind(SocketAddress socketAddress) {
            this.a.bind(socketAddress);
        }

        public synchronized void close() {
            this.a.close();
        }

        public void connect(SocketAddress socketAddress) {
            this.a.connect(socketAddress);
        }

        public void connect(SocketAddress socketAddress, int i) {
            this.a.connect(socketAddress, i);
        }

        public SocketChannel getChannel() {
            return this.a.getChannel();
        }

        public InetAddress getInetAddress() {
            return this.a.getInetAddress();
        }

        public InputStream getInputStream() {
            return this.a.getInputStream();
        }

        public boolean getKeepAlive() {
            return this.a.getKeepAlive();
        }

        public InetAddress getLocalAddress() {
            return this.a.getLocalAddress();
        }

        public int getLocalPort() {
            return this.a.getLocalPort();
        }

        public SocketAddress getLocalSocketAddress() {
            return this.a.getLocalSocketAddress();
        }

        public boolean getOOBInline() {
            return this.a.getOOBInline();
        }

        public OutputStream getOutputStream() {
            return this.a.getOutputStream();
        }

        public int getPort() {
            return this.a.getPort();
        }

        public synchronized int getReceiveBufferSize() {
            return this.a.getReceiveBufferSize();
        }

        public SocketAddress getRemoteSocketAddress() {
            return this.a.getRemoteSocketAddress();
        }

        public boolean getReuseAddress() {
            return this.a.getReuseAddress();
        }

        public synchronized int getSendBufferSize() {
            return this.a.getSendBufferSize();
        }

        public int getSoLinger() {
            return this.a.getSoLinger();
        }

        public synchronized int getSoTimeout() {
            return this.a.getSoTimeout();
        }

        public boolean getTcpNoDelay() {
            return this.a.getTcpNoDelay();
        }

        public int getTrafficClass() {
            return this.a.getTrafficClass();
        }

        public boolean isBound() {
            return this.a.isBound();
        }

        public boolean isClosed() {
            return this.a.isClosed();
        }

        public boolean isConnected() {
            return this.a.isConnected();
        }

        public boolean isInputShutdown() {
            return this.a.isInputShutdown();
        }

        public boolean isOutputShutdown() {
            return this.a.isOutputShutdown();
        }

        public void sendUrgentData(int i) {
            this.a.sendUrgentData(i);
        }

        public void setKeepAlive(boolean z) {
            this.a.setKeepAlive(z);
        }

        public void setOOBInline(boolean z) {
            this.a.setOOBInline(z);
        }

        public void setPerformancePreferences(int i, int i2, int i3) {
            this.a.setPerformancePreferences(i, i2, i3);
        }

        public synchronized void setReceiveBufferSize(int i) {
            this.a.setReceiveBufferSize(i);
        }

        public void setReuseAddress(boolean z) {
            this.a.setReuseAddress(z);
        }

        public synchronized void setSendBufferSize(int i) {
            this.a.setSendBufferSize(i);
        }

        public void setSoLinger(boolean z, int i) {
            this.a.setSoLinger(z, i);
        }

        public synchronized void setSoTimeout(int i) {
            this.a.setSoTimeout(i);
        }

        public void setTcpNoDelay(boolean z) {
            this.a.setTcpNoDelay(z);
        }

        public void setTrafficClass(int i) {
            this.a.setTrafficClass(i);
        }

        public void shutdownInput() {
            this.a.shutdownInput();
        }

        public void shutdownOutput() {
            this.a.shutdownOutput();
        }

        public String toString() {
            return this.a.toString();
        }

        public boolean equals(Object obj) {
            return this.a.equals(obj);
        }
    }

    private class b extends a {
        final /* synthetic */ o c;

        private b(o oVar, SSLSocket sSLSocket) {
            this.c = oVar;
            super(oVar, sSLSocket);
        }

        public void setEnabledProtocols(String[] strArr) {
            if (strArr != null && strArr.length == 1 && "SSLv3".equals(strArr[0])) {
                List arrayList = new ArrayList(Arrays.asList(this.a.getEnabledProtocols()));
                if (arrayList.size() > 1) {
                    arrayList.remove("SSLv3");
                    System.out.println("Removed SSLv3 from enabled protocols");
                } else {
                    System.out.println("SSL stuck with protocol available for " + String.valueOf(arrayList));
                }
                strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            super.setEnabledProtocols(strArr);
        }
    }

    public o() {
        this.a = HttpsURLConnection.getDefaultSSLSocketFactory();
    }

    public o(SSLSocketFactory sSLSocketFactory) {
        this.a = sSLSocketFactory;
    }

    public String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }

    private Socket a(Socket socket) {
        if (socket instanceof SSLSocket) {
            return new b((SSLSocket) socket);
        }
        return socket;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return a(this.a.createSocket(socket, str, i, z));
    }

    public Socket createSocket(String str, int i) {
        return a(this.a.createSocket(str, i));
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return a(this.a.createSocket(str, i, inetAddress, i2));
    }

    public Socket createSocket(InetAddress inetAddress, int i) {
        return a(this.a.createSocket(inetAddress, i));
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
    }
}
