package com.yandex.metrica.impl.ob;

public enum ay {
    FOREGROUND(0),
    BACKGROUND(1);
    
    private final int c;

    private ay(int i) {
        this.c = i;
    }

    public int a() {
        return this.c;
    }

    public static ay a(Integer num) {
        ay ayVar = FOREGROUND;
        if (num == null) {
            return ayVar;
        }
        switch (num.intValue()) {
            case 0:
                return FOREGROUND;
            case 1:
                return BACKGROUND;
            default:
                return ayVar;
        }
    }
}
