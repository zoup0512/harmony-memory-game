package com.amazonaws.auth.policy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Statement {
    private List<Action> actions = new ArrayList();
    private List<Condition> conditions = new ArrayList();
    private Effect effect;
    private String id;
    private List<Principal> principals = new ArrayList();
    private List<Resource> resources;

    public enum Effect {
        Allow,
        Deny
    }

    public Statement(Effect effect) {
        this.effect = effect;
        this.id = null;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Statement withId(String id) {
        setId(id);
        return this;
    }

    public Effect getEffect() {
        return this.effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public void setActions(Collection<Action> actions) {
        this.actions = new ArrayList(actions);
    }

    public Statement withActions(Action... actions) {
        setActions(Arrays.asList(actions));
        return this;
    }

    public List<Resource> getResources() {
        return this.resources;
    }

    public void setResources(Collection<Resource> resources) {
        this.resources = new ArrayList(resources);
    }

    public Statement withResources(Resource... resources) {
        setResources(Arrays.asList(resources));
        return this;
    }

    public List<Condition> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public Statement withConditions(Condition... conditions) {
        setConditions(Arrays.asList(conditions));
        return this;
    }

    public List<Principal> getPrincipals() {
        return this.principals;
    }

    public void setPrincipals(Collection<Principal> principals) {
        this.principals = new ArrayList(principals);
    }

    public void setPrincipals(Principal... principals) {
        setPrincipals(new ArrayList(Arrays.asList(principals)));
    }

    public Statement withPrincipals(Principal... principals) {
        setPrincipals(principals);
        return this;
    }
}
