package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttributeValue implements Serializable {
    private ByteBuffer b;
    private Boolean bOOL;
    private List<ByteBuffer> bS;
    private List<AttributeValue> l;
    private Map<String, AttributeValue> m;
    private String n;
    private List<String> nS;
    private Boolean nULL;
    private String s;
    private List<String> sS;

    public AttributeValue(String s) {
        setS(s);
    }

    public AttributeValue(List<String> sS) {
        setSS(sS);
    }

    public String getS() {
        return this.s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public AttributeValue withS(String s) {
        this.s = s;
        return this;
    }

    public String getN() {
        return this.n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public AttributeValue withN(String n) {
        this.n = n;
        return this;
    }

    public ByteBuffer getB() {
        return this.b;
    }

    public void setB(ByteBuffer b) {
        this.b = b;
    }

    public AttributeValue withB(ByteBuffer b) {
        this.b = b;
        return this;
    }

    public List<String> getSS() {
        return this.sS;
    }

    public void setSS(Collection<String> sS) {
        if (sS == null) {
            this.sS = null;
        } else {
            this.sS = new ArrayList(sS);
        }
    }

    public AttributeValue withSS(String... sS) {
        if (getSS() == null) {
            this.sS = new ArrayList(sS.length);
        }
        for (String value : sS) {
            this.sS.add(value);
        }
        return this;
    }

    public AttributeValue withSS(Collection<String> sS) {
        setSS(sS);
        return this;
    }

    public List<String> getNS() {
        return this.nS;
    }

    public void setNS(Collection<String> nS) {
        if (nS == null) {
            this.nS = null;
        } else {
            this.nS = new ArrayList(nS);
        }
    }

    public AttributeValue withNS(String... nS) {
        if (getNS() == null) {
            this.nS = new ArrayList(nS.length);
        }
        for (String value : nS) {
            this.nS.add(value);
        }
        return this;
    }

    public AttributeValue withNS(Collection<String> nS) {
        setNS(nS);
        return this;
    }

    public List<ByteBuffer> getBS() {
        return this.bS;
    }

    public void setBS(Collection<ByteBuffer> bS) {
        if (bS == null) {
            this.bS = null;
        } else {
            this.bS = new ArrayList(bS);
        }
    }

    public AttributeValue withBS(ByteBuffer... bS) {
        if (getBS() == null) {
            this.bS = new ArrayList(bS.length);
        }
        for (ByteBuffer value : bS) {
            this.bS.add(value);
        }
        return this;
    }

    public AttributeValue withBS(Collection<ByteBuffer> bS) {
        setBS(bS);
        return this;
    }

    public Map<String, AttributeValue> getM() {
        return this.m;
    }

    public void setM(Map<String, AttributeValue> m) {
        this.m = m;
    }

    public AttributeValue withM(Map<String, AttributeValue> m) {
        this.m = m;
        return this;
    }

    public AttributeValue addMEntry(String key, AttributeValue value) {
        if (this.m == null) {
            this.m = new HashMap();
        }
        if (this.m.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.m.put(key, value);
        return this;
    }

    public AttributeValue clearMEntries() {
        this.m = null;
        return this;
    }

    public List<AttributeValue> getL() {
        return this.l;
    }

    public void setL(Collection<AttributeValue> l) {
        if (l == null) {
            this.l = null;
        } else {
            this.l = new ArrayList(l);
        }
    }

    public AttributeValue withL(AttributeValue... l) {
        if (getL() == null) {
            this.l = new ArrayList(l.length);
        }
        for (AttributeValue value : l) {
            this.l.add(value);
        }
        return this;
    }

    public AttributeValue withL(Collection<AttributeValue> l) {
        setL(l);
        return this;
    }

    public Boolean isNULL() {
        return this.nULL;
    }

    public Boolean getNULL() {
        return this.nULL;
    }

    public void setNULL(Boolean nULL) {
        this.nULL = nULL;
    }

    public AttributeValue withNULL(Boolean nULL) {
        this.nULL = nULL;
        return this;
    }

    public Boolean isBOOL() {
        return this.bOOL;
    }

    public Boolean getBOOL() {
        return this.bOOL;
    }

    public void setBOOL(Boolean bOOL) {
        this.bOOL = bOOL;
    }

    public AttributeValue withBOOL(Boolean bOOL) {
        this.bOOL = bOOL;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getS() != null) {
            sb.append("S: " + getS() + ",");
        }
        if (getN() != null) {
            sb.append("N: " + getN() + ",");
        }
        if (getB() != null) {
            sb.append("B: " + getB() + ",");
        }
        if (getSS() != null) {
            sb.append("SS: " + getSS() + ",");
        }
        if (getNS() != null) {
            sb.append("NS: " + getNS() + ",");
        }
        if (getBS() != null) {
            sb.append("BS: " + getBS() + ",");
        }
        if (getM() != null) {
            sb.append("M: " + getM() + ",");
        }
        if (getL() != null) {
            sb.append("L: " + getL() + ",");
        }
        if (getNULL() != null) {
            sb.append("NULL: " + getNULL() + ",");
        }
        if (getBOOL() != null) {
            sb.append("BOOL: " + getBOOL());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((getS() == null ? 0 : getS().hashCode()) + 31) * 31) + (getN() == null ? 0 : getN().hashCode())) * 31) + (getB() == null ? 0 : getB().hashCode())) * 31) + (getSS() == null ? 0 : getSS().hashCode())) * 31) + (getNS() == null ? 0 : getNS().hashCode())) * 31) + (getBS() == null ? 0 : getBS().hashCode())) * 31) + (getM() == null ? 0 : getM().hashCode())) * 31) + (getL() == null ? 0 : getL().hashCode())) * 31) + (getNULL() == null ? 0 : getNULL().hashCode())) * 31;
        if (getBOOL() != null) {
            i = getBOOL().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttributeValue)) {
            return false;
        }
        AttributeValue other = (AttributeValue) obj;
        if (((other.getS() == null ? 1 : 0) ^ (getS() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getS() != null && !other.getS().equals(getS())) {
            return false;
        }
        int i;
        if (other.getN() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getN() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getN() != null && !other.getN().equals(getN())) {
            return false;
        }
        if (other.getB() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getB() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getB() != null && !other.getB().equals(getB())) {
            return false;
        }
        if (other.getSS() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getSS() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getSS() != null && !other.getSS().equals(getSS())) {
            return false;
        }
        if (other.getNS() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getNS() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getNS() != null && !other.getNS().equals(getNS())) {
            return false;
        }
        if (other.getBS() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getBS() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getBS() != null && !other.getBS().equals(getBS())) {
            return false;
        }
        if (other.getM() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getM() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getM() != null && !other.getM().equals(getM())) {
            return false;
        }
        if (other.getL() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getL() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getL() != null && !other.getL().equals(getL())) {
            return false;
        }
        if (other.getNULL() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getNULL() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getNULL() != null && !other.getNULL().equals(getNULL())) {
            return false;
        }
        if (other.getBOOL() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getBOOL() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getBOOL() == null || other.getBOOL().equals(getBOOL())) {
            return true;
        }
        return false;
    }
}
