package com.amazonaws.auth.policy;

@Deprecated
public enum STSActions implements Action {
    AssumeRole("sts:AssumeRole"),
    AssumeRoleWithWebIdentity("sts:AssumeRoleWithWebIdentity");
    
    private final String action;

    private STSActions(String action) {
        this.action = action;
    }

    public String getActionName() {
        return this.action;
    }
}
