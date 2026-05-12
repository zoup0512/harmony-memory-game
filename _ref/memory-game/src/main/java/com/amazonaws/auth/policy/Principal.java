package com.amazonaws.auth.policy;

public class Principal {
    public static final Principal All = new Principal("*", "*");
    public static final Principal AllServices = new Principal("Service", "*");
    public static final Principal AllUsers = new Principal("AWS", "*");
    public static final Principal AllWebProviders = new Principal("Federated", "*");
    private final String id;
    private final String provider;

    public enum Services {
        AWSDataPipeline("datapipeline.amazonaws.com"),
        AmazonElasticTranscoder("elastictranscoder.amazonaws.com"),
        AmazonEC2("ec2.amazonaws.com"),
        AWSOpsWorks("opsworks.amazonaws.com"),
        AWSCloudHSM("cloudhsm.amazonaws.com"),
        AllServices("*");
        
        private String serviceId;

        private Services(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getServiceId() {
            return this.serviceId;
        }

        public static Services fromString(String serviceId) {
            if (serviceId != null) {
                for (Services s : values()) {
                    if (s.getServiceId().equalsIgnoreCase(serviceId)) {
                        return s;
                    }
                }
            }
            return null;
        }
    }

    public enum WebIdentityProviders {
        Facebook("graph.facebook.com"),
        Google("accounts.google.com"),
        Amazon("www.amazon.com"),
        AllProviders("*");
        
        private String webIdentityProvider;

        private WebIdentityProviders(String webIdentityProvider) {
            this.webIdentityProvider = webIdentityProvider;
        }

        public String getWebIdentityProvider() {
            return this.webIdentityProvider;
        }

        public static WebIdentityProviders fromString(String webIdentityProvider) {
            if (webIdentityProvider != null) {
                for (WebIdentityProviders provider : values()) {
                    if (provider.getWebIdentityProvider().equalsIgnoreCase(webIdentityProvider)) {
                        return provider;
                    }
                }
            }
            return null;
        }
    }

    public Principal(Services service) {
        if (service == null) {
            throw new IllegalArgumentException("Null AWS service name specified");
        }
        this.id = service.getServiceId();
        this.provider = "Service";
    }

    public Principal(String provider, String id) {
        this.provider = provider;
        if (provider.equals("AWS")) {
            id = id.replaceAll("-", "");
        }
        this.id = id;
    }

    public Principal(String accountId) {
        if (accountId == null) {
            throw new IllegalArgumentException("Null AWS account ID specified");
        }
        this.id = accountId.replaceAll("-", "");
        this.provider = "AWS";
    }

    public Principal(WebIdentityProviders webIdentityProvider) {
        if (webIdentityProvider == null) {
            throw new IllegalArgumentException("Null web identity provider specified");
        }
        this.id = webIdentityProvider.getWebIdentityProvider();
        this.provider = "Federated";
    }

    public String getProvider() {
        return this.provider;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return ((this.provider.hashCode() + 31) * 31) + this.id.hashCode();
    }

    public boolean equals(Object principal) {
        if (this == principal) {
            return true;
        }
        if (principal == null) {
            return false;
        }
        if (!(principal instanceof Principal)) {
            return false;
        }
        Principal other = (Principal) principal;
        if (getProvider().equals(other.getProvider()) && getId().equals(other.getId())) {
            return true;
        }
        return false;
    }
}
