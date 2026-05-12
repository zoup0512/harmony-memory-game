package com.amazonaws.services.s3.model;

public class RedirectRule {
    private String hostName;
    private String httpRedirectCode;
    private String protocol;
    private String replaceKeyPrefixWith;
    private String replaceKeyWith;

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getprotocol() {
        return this.protocol;
    }

    public RedirectRule withProtocol(String protocol) {
        setProtocol(protocol);
        return this;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return this.hostName;
    }

    public RedirectRule withHostName(String hostName) {
        setHostName(hostName);
        return this;
    }

    public void setReplaceKeyPrefixWith(String replaceKeyPrefixWith) {
        this.replaceKeyPrefixWith = replaceKeyPrefixWith;
    }

    public String getReplaceKeyPrefixWith() {
        return this.replaceKeyPrefixWith;
    }

    public RedirectRule withReplaceKeyPrefixWith(String replaceKeyPrefixWith) {
        setReplaceKeyPrefixWith(replaceKeyPrefixWith);
        return this;
    }

    public void setReplaceKeyWith(String replaceKeyWith) {
        this.replaceKeyWith = replaceKeyWith;
    }

    public String getReplaceKeyWith() {
        return this.replaceKeyWith;
    }

    public RedirectRule withReplaceKeyWith(String replaceKeyWith) {
        setReplaceKeyWith(replaceKeyWith);
        return this;
    }

    public void setHttpRedirectCode(String httpRedirectCode) {
        this.httpRedirectCode = httpRedirectCode;
    }

    public String getHttpRedirectCode() {
        return this.httpRedirectCode;
    }

    public RedirectRule withHttpRedirectCode(String httpRedirectCode) {
        this.httpRedirectCode = httpRedirectCode;
        return this;
    }
}
