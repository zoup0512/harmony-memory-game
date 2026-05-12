package com.mopub.mraid;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.mraid.MraidBridge.MraidBridgeListener;
import java.net.URI;

class MraidController$4 implements MraidBridgeListener {
    final /* synthetic */ MraidController this$0;

    MraidController$4(MraidController mraidController) {
        this.this$0 = mraidController;
    }

    public void onPageLoaded() {
        this.this$0.handleTwoPartPageLoad();
    }

    public void onPageFailedToLoad() {
    }

    public void onVisibilityChanged(boolean z) {
        MraidController.access$200(this.this$0).notifyViewability(z);
        MraidController.access$100(this.this$0).notifyViewability(z);
    }

    public boolean onJsAlert(@NonNull String str, @NonNull JsResult jsResult) {
        return this.this$0.handleJsAlert(str, jsResult);
    }

    public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
        return this.this$0.handleConsoleMessage(consoleMessage);
    }

    public void onResize(int i, int i2, int i3, int i4, @NonNull ClosePosition closePosition, boolean z) {
        throw new MraidCommandException("Not allowed to resize from an expanded state");
    }

    public void onExpand(@Nullable URI uri, boolean z) {
    }

    public void onClose() {
        this.this$0.handleClose();
    }

    public void onUseCustomClose(boolean z) {
        this.this$0.handleCustomClose(z);
    }

    public void onSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) {
        this.this$0.handleSetOrientationProperties(z, mraidOrientation);
    }

    public void onOpen(URI uri) {
        this.this$0.handleOpen(uri.toString());
    }

    public void onPlayVideo(@NonNull URI uri) {
        this.this$0.handleShowVideo(uri.toString());
    }
}
