package com.amazonaws.auth.policy.actions;

import com.amazonaws.auth.policy.Action;

public enum SecurityTokenServiceActions implements Action {
    AllSecurityTokenServiceActions("sts:*"),
    AssumeRole("sts:AssumeRole"),
    AssumeRoleWithWebIdentity("sts:AssumeRoleWithWebIdentity"),
    GetCallerIdentity("sts:GetCallerIdentity"),
    GetFederationToken("sts:GetFederationToken"),
    GetSessionToken("sts:GetSessionToken");
    
    private final String action;

    private SecurityTokenServiceActions(String action) {
        this.action = action;
    }

    public String getActionName() {
        return this.action;
    }
}
