package com.amazonaws.services.s3.model;

public class RoutingRule {
    RoutingRuleCondition condition;
    RedirectRule redirect;

    public void setCondition(RoutingRuleCondition condition) {
        this.condition = condition;
    }

    public RoutingRuleCondition getCondition() {
        return this.condition;
    }

    public RoutingRule withCondition(RoutingRuleCondition condition) {
        setCondition(condition);
        return this;
    }

    public void setRedirect(RedirectRule redirect) {
        this.redirect = redirect;
    }

    public RedirectRule getRedirect() {
        return this.redirect;
    }

    public RoutingRule withRedirect(RedirectRule redirect) {
        setRedirect(redirect);
        return this;
    }
}
