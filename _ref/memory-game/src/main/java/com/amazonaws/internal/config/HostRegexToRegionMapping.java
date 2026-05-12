package com.amazonaws.internal.config;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class HostRegexToRegionMapping {
    private final String hostNameRegex;
    private final String regionName;

    public HostRegexToRegionMapping(String hostNameRegex, String regionName) {
        if (hostNameRegex == null || hostNameRegex.isEmpty()) {
            throw new IllegalArgumentException("Invalid HostRegexToRegionMapping configuration: hostNameRegex must be non-empty");
        }
        try {
            Pattern.compile(hostNameRegex);
            if (regionName == null || regionName.isEmpty()) {
                throw new IllegalArgumentException("Invalid HostRegexToRegionMapping configuration: regionName must be non-empty");
            }
            this.hostNameRegex = hostNameRegex;
            this.regionName = regionName;
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Invalid HostRegexToRegionMapping configuration: hostNameRegex is not a valid regex", e);
        }
    }

    public String getHostNameRegex() {
        return this.hostNameRegex;
    }

    public String getRegionName() {
        return this.regionName;
    }
}
