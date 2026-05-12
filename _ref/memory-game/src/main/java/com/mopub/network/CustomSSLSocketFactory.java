package com.mopub.network;

import android.net.SSLCertificateSocketFactory;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class CustomSSLSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory mCertificateSocketFactory;

    private CustomSSLSocketFactory() {
    }

    public static CustomSSLSocketFactory getDefault(int i) {
        CustomSSLSocketFactory customSSLSocketFactory = new CustomSSLSocketFactory();
        customSSLSocketFactory.mCertificateSocketFactory = SSLCertificateSocketFactory.getDefault(i, null);
        return customSSLSocketFactory;
    }

    public Socket createSocket() {
        Socket createSocket = this.mCertificateSocketFactory.createSocket();
        enableTlsIfAvailable(createSocket);
        return createSocket;
    }

    public Socket createSocket(String str, int i) {
        Socket createSocket = this.mCertificateSocketFactory.createSocket(str, i);
        enableTlsIfAvailable(createSocket);
        return createSocket;
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        Socket createSocket = this.mCertificateSocketFactory.createSocket(str, i, inetAddress, i2);
        enableTlsIfAvailable(createSocket);
        return createSocket;
    }

    public Socket createSocket(InetAddress inetAddress, int i) {
        Socket createSocket = this.mCertificateSocketFactory.createSocket(inetAddress, i);
        enableTlsIfAvailable(createSocket);
        return createSocket;
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        Socket createSocket = this.mCertificateSocketFactory.createSocket(inetAddress, i, inetAddress2, i2);
        enableTlsIfAvailable(createSocket);
        return createSocket;
    }

    public String[] getDefaultCipherSuites() {
        return this.mCertificateSocketFactory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.mCertificateSocketFactory.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        Socket createSocket = this.mCertificateSocketFactory.createSocket(socket, str, i, z);
        enableTlsIfAvailable(createSocket);
        return createSocket;
    }

    private void enableTlsIfAvailable(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        }
    }
}
