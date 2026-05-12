package com.amazonaws.services.s3.model;

public class MultiFactorAuthentication {
    private String deviceSerialNumber;
    private String token;

    public MultiFactorAuthentication(String deviceSerialNumber, String token) {
        this.deviceSerialNumber = deviceSerialNumber;
        this.token = token;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public MultiFactorAuthentication withDeviceSerialNumber(String deviceSerialNumber) {
        setDeviceSerialNumber(deviceSerialNumber);
        return this;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public MultiFactorAuthentication withToken(String token) {
        setToken(token);
        return this;
    }
}
