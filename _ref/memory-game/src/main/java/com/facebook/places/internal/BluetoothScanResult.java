package com.facebook.places.internal;

public class BluetoothScanResult {
    public String payload;
    public int rssi;

    public BluetoothScanResult(String payload, int rssi) {
        this.payload = payload;
        this.rssi = rssi;
    }
}
