package com.amazon.device.ads;

import android.view.ViewTreeObserver.OnWindowFocusChangeListener;

class AmazonOnWindowFocusChangeListenerFactory {

    private class AmazonOnWindowFocusChangeListener implements OnWindowFocusChangeListener {
        private final ViewabilityObserver viewabilityObserver;

        AmazonOnWindowFocusChangeListener(ViewabilityObserver viewabilityObserver) {
            this.viewabilityObserver = viewabilityObserver;
        }

        public void onWindowFocusChanged(boolean z) {
            this.viewabilityObserver.fireViewableEvent(false);
        }
    }

    AmazonOnWindowFocusChangeListenerFactory() {
    }

    public OnWindowFocusChangeListener buildOnWindowFocusChangeListener(ViewabilityObserver viewabilityObserver) {
        return new AmazonOnWindowFocusChangeListener(viewabilityObserver);
    }
}
