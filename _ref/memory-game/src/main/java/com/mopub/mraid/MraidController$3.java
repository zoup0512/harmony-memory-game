package com.mopub.mraid;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.mraid.MraidBridge.MraidBridgeListener;
import java.net.URI;

class MraidController$3 implements MraidBridgeListener {
    final /* synthetic */ MraidController this$0;

    MraidController$3(MraidController mraidController) {
        this.this$0 = mraidController;
    }

    public void onPageLoaded() {
        this.this$0.handlePageLoad();
    }

    public void onPageFailedToLoad() {
        if (MraidController.access$000(this.this$0) != null) {
            MraidController.access$000(this.this$0).onFailedToLoad();
        }
    }

    public void onVisibilityChanged(boolean z) {
        if (!MraidController.access$100(this.this$0).isAttached()) {
            MraidController.access$200(this.this$0).notifyViewability(z);
        }
    }

    public boolean onJsAlert(@NonNull String str, @NonNull JsResult jsResult) {
        return this.this$0.handleJsAlert(str, jsResult);
    }

    public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
        return this.this$0.handleConsoleMessage(consoleMessage);
    }

    public void onClose() {
        this.this$0.handleClose();
    }

    public void onResize(int i, int i2, int i3, int i4, @NonNull ClosePosition closePosition, boolean z) {
        this.this$0.handleResize(i, i2, i3, i4, closePosition, z);
    }

    public void onExpand(@Nullable URI uri, boolean z) {
        this.this$0.handleExpand(uri, z);
    }

    public void onUseCustomClose(boolean z) {
        this.this$0.handleCustomClose(z);
    }

    public void onSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) {
        this.this$0.handleSetOrientationProperties(z, mraidOrientation);
    }

    public void onOpen(@NonNull URI uri) {
        this.this$0.handleOpen(uri.toString());
    }

    public void onPlayVideo(@NonNull URI uri) {
        this.this$0.handleShowVideo(uri.toString());
    }
}
