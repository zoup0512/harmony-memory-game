package com.unity3d.ads2.connectivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;
import com.unity3d.ads2.log.DeviceLog;
import com.unity3d.ads2.properties.ClientProperties;
import com.unity3d.ads2.webview.WebViewApp;
import com.unity3d.ads2.webview.WebViewEventCategory;
import java.util.HashSet;
import java.util.Iterator;

public class ConnectivityMonitor {
    private static int _connected = -1;
    private static HashSet<IConnectivityListener> _listeners = null;
    private static boolean _listening = false;
    private static int _networkType = -1;
    private static boolean _webappMonitoring = false;
    private static boolean _wifi = false;

    public static void setConnectionMonitoring(boolean z) {
        _webappMonitoring = z;
        updateListeningStatus();
    }

    public static void addListener(IConnectivityListener iConnectivityListener) {
        if (_listeners == null) {
            _listeners = new HashSet();
        }
        _listeners.add(iConnectivityListener);
        updateListeningStatus();
    }

    public static void removeListener(IConnectivityListener iConnectivityListener) {
        if (_listeners != null) {
            _listeners.remove(iConnectivityListener);
            updateListeningStatus();
        }
    }

    public static void stopAll() {
        _listeners = null;
        _webappMonitoring = false;
        updateListeningStatus();
    }

    private static void updateListeningStatus() {
        if (_webappMonitoring || !(_listeners == null || _listeners.isEmpty())) {
            startListening();
        } else {
            stopListening();
        }
    }

    private static void startListening() {
        if (!_listening) {
            _listening = true;
            initConnectionStatus();
            if (VERSION.SDK_INT < 21) {
                ConnectivityChangeReceiver.register();
            } else {
                ConnectivityNetworkCallback.register();
            }
        }
    }

    private static void stopListening() {
        if (_listening) {
            _listening = false;
            if (VERSION.SDK_INT < 21) {
                ConnectivityChangeReceiver.unregister();
            } else {
                ConnectivityNetworkCallback.unregister();
            }
        }
    }

    private static void initConnectionStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                _connected = 0;
                return;
            }
            boolean z;
            _connected = 1;
            if (activeNetworkInfo.getType() == 1) {
                z = true;
            } else {
                z = false;
            }
            _wifi = z;
            if (!_wifi) {
                _networkType = ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkType();
            }
        }
    }

    public static void connected() {
        if (_connected != 1) {
            DeviceLog.debug("Unity Ads connectivity change: connected");
            initConnectionStatus();
            if (_listeners != null) {
                Iterator it = _listeners.iterator();
                while (it.hasNext()) {
                    ((IConnectivityListener) it.next()).onConnected();
                }
            }
            sendToWebview(ConnectivityEvent.CONNECTED, _wifi, _networkType);
        }
    }

    public static void disconnected() {
        if (_connected != 0) {
            _connected = 0;
            DeviceLog.debug("Unity Ads connectivity change: disconnected");
            if (_listeners != null) {
                Iterator it = _listeners.iterator();
                while (it.hasNext()) {
                    ((IConnectivityListener) it.next()).onDisconnected();
                }
            }
            sendToWebview(ConnectivityEvent.DISCONNECTED, false, 0);
        }
    }

    public static void connectionStatusChanged() {
        boolean z = true;
        if (_connected == 1) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() != 1) {
                    z = false;
                }
                int networkType = ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkType();
                if (z != _wifi || (networkType != _networkType && !_wifi)) {
                    _wifi = z;
                    _networkType = networkType;
                    DeviceLog.debug("Unity Ads connectivity change: network change");
                    sendToWebview(ConnectivityEvent.NETWORK_CHANGE, z, networkType);
                }
            }
        }
    }

    private static void sendToWebview(ConnectivityEvent connectivityEvent, boolean z, int i) {
        if (_webappMonitoring) {
            WebViewApp currentApp = WebViewApp.getCurrentApp();
            if (currentApp != null && currentApp.isWebAppLoaded()) {
                switch (connectivityEvent) {
                    case CONNECTED:
                        if (z) {
                            currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.CONNECTED, Boolean.valueOf(z), Integer.valueOf(0));
                            return;
                        }
                        currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.CONNECTED, Boolean.valueOf(z), Integer.valueOf(i));
                        return;
                    case DISCONNECTED:
                        currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.DISCONNECTED, new Object[0]);
                        return;
                    case NETWORK_CHANGE:
                        if (z) {
                            currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.NETWORK_CHANGE, Boolean.valueOf(z), Integer.valueOf(0));
                            return;
                        }
                        currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.NETWORK_CHANGE, Boolean.valueOf(z), Integer.valueOf(i));
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
