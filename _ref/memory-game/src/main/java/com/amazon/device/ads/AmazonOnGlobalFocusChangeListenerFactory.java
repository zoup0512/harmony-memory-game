package com.amazon.device.ads;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;

class AmazonOnGlobalFocusChangeListenerFactory {

    private class AmazonOnGlobalFocusChangeListener implements OnGlobalFocusChangeListener {
        private final ViewabilityObserver viewabilityObserver;

        public AmazonOnGlobalFocusChangeListener(ViewabilityObserver viewabilityObserver) {
            this.viewabilityObserver = viewabilityObserver;
        }

        public void onGlobalFocusChanged(View view, View view2) {
            this.viewabilityObserver.fireViewableEvent(false);
        }
    }

    AmazonOnGlobalFocusChangeListenerFactory() {
    }

    public OnGlobalFocusChangeListener buildAmazonOnGlobalFocusChangedListener(ViewabilityObserver viewabilityObserver) {
        return new AmazonOnGlobalFocusChangeListener(viewabilityObserver);
    }
}
