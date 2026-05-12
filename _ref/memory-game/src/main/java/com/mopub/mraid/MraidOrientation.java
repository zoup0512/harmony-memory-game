package com.mopub.mraid;

enum MraidOrientation {
    PORTRAIT(1),
    LANDSCAPE(0),
    NONE(-1);
    
    private final int mActivityInfoOrientation;

    private MraidOrientation(int i) {
        this.mActivityInfoOrientation = i;
    }

    int getActivityInfoOrientation() {
        return this.mActivityInfoOrientation;
    }
}
