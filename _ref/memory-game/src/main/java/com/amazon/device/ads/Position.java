package com.amazon.device.ads;

import org.json.JSONObject;

class Position {
    private Size size;
    private int x;
    private int y;

    public Position() {
        this.size = new Size(0, 0);
        this.x = 0;
        this.y = 0;
    }

    public Position(Size size, int i, int i2) {
        this.size = size;
        this.x = i;
        this.y = i2;
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int i) {
        this.y = i;
    }

    public JSONObject toJSONObject() {
        JSONObject toJSONObject = this.size.toJSONObject();
        JSONUtils.put(toJSONObject, "x", this.x);
        JSONUtils.put(toJSONObject, "y", this.y);
        return toJSONObject;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position position = (Position) obj;
        if (this.size.equals(position.size) && this.x == position.x && this.y == position.y) {
            return true;
        }
        return false;
    }
}
