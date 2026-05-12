package com.github.florent37.viewanimator;

public class AnimationListener {

    public interface Stop {
        void onStop();
    }

    public interface Start {
        void onStart();
    }

    private AnimationListener() {
    }
}
