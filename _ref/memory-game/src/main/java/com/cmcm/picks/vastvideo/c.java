package com.cmcm.picks.vastvideo;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.view.Surface;

/* compiled from: VastPlayer */
public class c extends MediaPlayer {
    @TargetApi(14)
    public void a(SurfaceTexture surfaceTexture) {
        if (surfaceTexture != null) {
            super.setSurface(new Surface(surfaceTexture));
        }
    }
}
