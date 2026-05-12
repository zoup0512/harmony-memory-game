package com.amazonaws.services.s3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AccessControlList implements Serializable {
    private static final long serialVersionUID = 8095040648034788376L;
    private HashSet<Grant> grants = new HashSet();
    private Owner owner = null;

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void grantPermission(Grantee grantee, Permission permission) {
        this.grants.add(new Grant(grantee, permission));
    }

    public void grantAllPermissions(Grant... grantsVarArg) {
        for (Grant gap : grantsVarArg) {
            grantPermission(gap.getGrantee(), gap.getPermission());
        }
    }

    public void revokeAllPermissions(Grantee grantee) {
        ArrayList<Grant> grantsToRemove = new ArrayList();
        Iterator it = this.grants.iterator();
        while (it.hasNext()) {
            Grant gap = (Grant) it.next();
            if (gap.getGrantee().equals(grantee)) {
                grantsToRemove.add(gap);
            }
        }
        this.grants.removeAll(grantsToRemove);
    }

    public Set<Grant> getGrants() {
        return this.grants;
    }

    public String toString() {
        return "AccessControlList [owner=" + this.owner + ", grants=" + getGrants() + "]";
    }
}
