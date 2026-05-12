package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import com.cube.memorygames.SharingDialog;
import com.cube.memorygames.billing.IabHelper;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzkh;
import com.mopub.volley.DefaultRetryPolicy;
import io.branch.referral.BranchError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@TargetApi(14)
@zzin
public class zzc extends zzi implements OnAudioFocusChangeListener, OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener {
    private static final Map<Integer, String> zzbrr = new HashMap();
    private final zzx zzbrs;
    private final boolean zzbrt;
    private int zzbru = 0;
    private int zzbrv = 0;
    private MediaPlayer zzbrw;
    private Uri zzbrx;
    private int zzbry;
    private int zzbrz;
    private int zzbsa;
    private int zzbsb;
    private int zzbsc;
    private float zzbsd = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private boolean zzbse;
    private boolean zzbsf;
    private zzw zzbsg;
    private boolean zzbsh;
    private int zzbsi;
    private zzh zzbsj;

    static {
        zzbrr.put(Integer.valueOf(IabHelper.IABHELPER_SEND_INTENT_FAILED), "MEDIA_ERROR_IO");
        zzbrr.put(Integer.valueOf(IabHelper.IABHELPER_MISSING_TOKEN), "MEDIA_ERROR_MALFORMED");
        zzbrr.put(Integer.valueOf(IabHelper.IABHELPER_INVALID_CONSUMPTION), "MEDIA_ERROR_UNSUPPORTED");
        zzbrr.put(Integer.valueOf(BranchError.ERR_BRANCH_NO_SHARE_OPTION), "MEDIA_ERROR_TIMED_OUT");
        zzbrr.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
        zzbrr.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
        zzbrr.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
        zzbrr.put(Integer.valueOf(SharingDialog.DOLLAR1_COINS), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzbrr.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
        zzbrr.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
        zzbrr.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
        zzbrr.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
        zzbrr.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
        zzbrr.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
        zzbrr.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
        zzbrr.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
    }

    public zzc(Context context, boolean z, boolean z2, zzx com_google_android_gms_ads_internal_overlay_zzx) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzbrs = com_google_android_gms_ads_internal_overlay_zzx;
        this.zzbsh = z;
        this.zzbrt = z2;
        this.zzbrs.zza((zzi) this);
    }

    private void zzad(int i) {
        if (i == 3) {
            this.zzbrs.zzpi();
        } else if (this.zzbru == 3) {
            this.zzbrs.zzpj();
        }
        this.zzbru = i;
    }

    private void zzae(int i) {
        this.zzbrv = i;
    }

    private void zzb(float f) {
        if (this.zzbrw != null) {
            try {
                this.zzbrw.setVolume(f, f);
                return;
            } catch (IllegalStateException e) {
                return;
            }
        }
        zzb.zzcx("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
    }

    private void zznj() {
        Throwable e;
        String valueOf;
        zzkd.v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzbrx != null && surfaceTexture != null) {
            zzy(false);
            try {
                SurfaceTexture zzox;
                this.zzbrw = zzu.zzgd().zzov();
                this.zzbrw.setOnBufferingUpdateListener(this);
                this.zzbrw.setOnCompletionListener(this);
                this.zzbrw.setOnErrorListener(this);
                this.zzbrw.setOnInfoListener(this);
                this.zzbrw.setOnPreparedListener(this);
                this.zzbrw.setOnVideoSizeChangedListener(this);
                this.zzbsa = 0;
                if (this.zzbsh) {
                    this.zzbsg = new zzw(getContext());
                    this.zzbsg.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzbsg.start();
                    zzox = this.zzbsg.zzox();
                    if (zzox == null) {
                        this.zzbsg.zzow();
                        this.zzbsg = null;
                    }
                    this.zzbrw.setDataSource(getContext(), this.zzbrx);
                    this.zzbrw.setSurface(zzu.zzge().zza(zzox));
                    this.zzbrw.setAudioStreamType(3);
                    this.zzbrw.setScreenOnWhilePlaying(true);
                    this.zzbrw.prepareAsync();
                    zzad(1);
                }
                zzox = surfaceTexture;
                this.zzbrw.setDataSource(getContext(), this.zzbrx);
                this.zzbrw.setSurface(zzu.zzge().zza(zzox));
                this.zzbrw.setAudioStreamType(3);
                this.zzbrw.setScreenOnWhilePlaying(true);
                this.zzbrw.prepareAsync();
                zzad(1);
            } catch (IOException e2) {
                e = e2;
                valueOf = String.valueOf(this.zzbrx);
                zzb.zzd(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzbrw, 1, 0);
            } catch (IllegalArgumentException e3) {
                e = e3;
                valueOf = String.valueOf(this.zzbrx);
                zzb.zzd(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzbrw, 1, 0);
            } catch (IllegalStateException e4) {
                e = e4;
                valueOf = String.valueOf(this.zzbrx);
                zzb.zzd(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzbrw, 1, 0);
            }
        }
    }

    private void zznk() {
        if (this.zzbrt && zznn() && this.zzbrw.getCurrentPosition() > 0 && this.zzbrv != 3) {
            zzkd.v("AdMediaPlayerView nudging MediaPlayer");
            zzb(0.0f);
            this.zzbrw.start();
            int currentPosition = this.zzbrw.getCurrentPosition();
            long currentTimeMillis = zzu.zzfu().currentTimeMillis();
            while (zznn() && this.zzbrw.getCurrentPosition() == currentPosition) {
                if (zzu.zzfu().currentTimeMillis() - currentTimeMillis > 250) {
                    break;
                }
            }
            this.zzbrw.pause();
            zzns();
        }
    }

    private void zznl() {
        AudioManager zznt = zznt();
        if (zznt != null && !this.zzbsf) {
            if (zznt.requestAudioFocus(this, 3, 2) == 1) {
                zznq();
            } else {
                zzb.zzcx("AdMediaPlayerView audio focus request failed");
            }
        }
    }

    private void zznm() {
        zzkd.v("AdMediaPlayerView abandon audio focus");
        AudioManager zznt = zznt();
        if (zznt != null && this.zzbsf) {
            if (zznt.abandonAudioFocus(this) == 1) {
                this.zzbsf = false;
            } else {
                zzb.zzcx("AdMediaPlayerView abandon audio focus failed");
            }
        }
    }

    private boolean zznn() {
        return (this.zzbrw == null || this.zzbru == -1 || this.zzbru == 0 || this.zzbru == 1) ? false : true;
    }

    private void zznq() {
        zzkd.v("AdMediaPlayerView audio focus gained");
        this.zzbsf = true;
        zzns();
    }

    private void zznr() {
        zzkd.v("AdMediaPlayerView audio focus lost");
        this.zzbsf = false;
        zzns();
    }

    private void zzns() {
        if (this.zzbse || !this.zzbsf) {
            zzb(0.0f);
        } else {
            zzb(this.zzbsd);
        }
    }

    private AudioManager zznt() {
        return (AudioManager) getContext().getSystemService("audio");
    }

    private void zzy(boolean z) {
        zzkd.v("AdMediaPlayerView release");
        if (this.zzbsg != null) {
            this.zzbsg.zzow();
            this.zzbsg = null;
        }
        if (this.zzbrw != null) {
            this.zzbrw.reset();
            this.zzbrw.release();
            this.zzbrw = null;
            zzad(0);
            if (z) {
                this.zzbrv = 0;
                zzae(0);
            }
            zznm();
        }
    }

    public int getCurrentPosition() {
        return zznn() ? this.zzbrw.getCurrentPosition() : 0;
    }

    public int getDuration() {
        return zznn() ? this.zzbrw.getDuration() : -1;
    }

    public int getVideoHeight() {
        return this.zzbrw != null ? this.zzbrw.getVideoHeight() : 0;
    }

    public int getVideoWidth() {
        return this.zzbrw != null ? this.zzbrw.getVideoWidth() : 0;
    }

    public void onAudioFocusChange(int i) {
        if (i > 0) {
            zznq();
        } else if (i < 0) {
            zznr();
        }
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzbsa = i;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        zzkd.v("AdMediaPlayerView completion");
        zzad(5);
        zzae(5);
        zzkh.zzclc.post(new 2(this));
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzbrr.get(Integer.valueOf(i));
        String str2 = (String) zzbrr.get(Integer.valueOf(i2));
        zzb.zzcx(new StringBuilder((String.valueOf(str).length() + 38) + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer error: ").append(str).append(":").append(str2).toString());
        zzad(-1);
        zzae(-1);
        zzkh.zzclc.post(new 3(this, str, str2));
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzbrr.get(Integer.valueOf(i));
        String str2 = (String) zzbrr.get(Integer.valueOf(i2));
        zzkd.v(new StringBuilder((String.valueOf(str).length() + 37) + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer info: ").append(str).append(":").append(str2).toString());
        return true;
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.zzbry, i);
        int defaultSize2 = getDefaultSize(this.zzbrz, i2);
        if (this.zzbry > 0 && this.zzbrz > 0 && this.zzbsg == null) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (this.zzbry * defaultSize2 < this.zzbrz * size) {
                    defaultSize = (this.zzbry * defaultSize2) / this.zzbrz;
                } else if (this.zzbry * defaultSize2 > this.zzbrz * size) {
                    defaultSize2 = (this.zzbrz * size) / this.zzbry;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == 1073741824) {
                defaultSize = (this.zzbrz * size) / this.zzbry;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == 1073741824) {
                defaultSize = (this.zzbry * defaultSize2) / this.zzbrz;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i3 = this.zzbry;
                defaultSize = this.zzbrz;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = i3;
                } else {
                    defaultSize = (this.zzbry * defaultSize2) / this.zzbrz;
                }
                if (mode == Integer.MIN_VALUE && r1 > size) {
                    defaultSize2 = (this.zzbrz * size) / this.zzbry;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        if (this.zzbsg != null) {
            this.zzbsg.zzg(defaultSize, defaultSize2);
        }
        if (VERSION.SDK_INT == 16) {
            if ((this.zzbsb > 0 && this.zzbsb != defaultSize) || (this.zzbsc > 0 && this.zzbsc != defaultSize2)) {
                zznk();
            }
            this.zzbsb = defaultSize;
            this.zzbsc = defaultSize2;
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        zzkd.v("AdMediaPlayerView prepared");
        zzad(2);
        this.zzbrs.zzoj();
        zzkh.zzclc.post(new 1(this));
        this.zzbry = mediaPlayer.getVideoWidth();
        this.zzbrz = mediaPlayer.getVideoHeight();
        if (this.zzbsi != 0) {
            seekTo(this.zzbsi);
        }
        zznk();
        int i = this.zzbry;
        zzb.zzcw("AdMediaPlayerView stream dimensions: " + i + " x " + this.zzbrz);
        if (this.zzbrv == 3) {
            play();
        }
        zznl();
        zzns();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzkd.v("AdMediaPlayerView surface created");
        zznj();
        zzkh.zzclc.post(new 4(this));
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzkd.v("AdMediaPlayerView surface destroyed");
        if (this.zzbrw != null && this.zzbsi == 0) {
            this.zzbsi = this.zzbrw.getCurrentPosition();
        }
        if (this.zzbsg != null) {
            this.zzbsg.zzow();
        }
        zzkh.zzclc.post(new 5(this));
        zzy(true);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Object obj = 1;
        zzkd.v("AdMediaPlayerView surface changed");
        Object obj2 = this.zzbrv == 3 ? 1 : null;
        if (!(this.zzbry == i && this.zzbrz == i2)) {
            obj = null;
        }
        if (!(this.zzbrw == null || obj2 == null || r1 == null)) {
            if (this.zzbsi != 0) {
                seekTo(this.zzbsi);
            }
            play();
        }
        if (this.zzbsg != null) {
            this.zzbsg.zzg(i, i2);
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzbrs.zzb(this);
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        zzkd.v("AdMediaPlayerView size changed: " + i + " x " + i2);
        this.zzbry = mediaPlayer.getVideoWidth();
        this.zzbrz = mediaPlayer.getVideoHeight();
        if (this.zzbry != 0 && this.zzbrz != 0) {
            requestLayout();
        }
    }

    public void pause() {
        zzkd.v("AdMediaPlayerView pause");
        if (zznn() && this.zzbrw.isPlaying()) {
            this.zzbrw.pause();
            zzad(4);
            zzkh.zzclc.post(new 7(this));
        }
        zzae(4);
    }

    public void play() {
        zzkd.v("AdMediaPlayerView play");
        if (zznn()) {
            this.zzbrw.start();
            zzad(3);
            zzkh.zzclc.post(new 6(this));
        }
        zzae(3);
    }

    public void seekTo(int i) {
        zzkd.v("AdMediaPlayerView seek " + i);
        if (zznn()) {
            this.zzbrw.seekTo(i);
            this.zzbsi = 0;
            return;
        }
        this.zzbsi = i;
    }

    public void setMimeType(String str) {
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        this.zzbrx = uri;
        this.zzbsi = 0;
        zznj();
        requestLayout();
        invalidate();
    }

    public void stop() {
        zzkd.v("AdMediaPlayerView stop");
        if (this.zzbrw != null) {
            this.zzbrw.stop();
            this.zzbrw.release();
            this.zzbrw = null;
            zzad(0);
            zzae(0);
            zznm();
        }
        this.zzbrs.onStop();
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getName());
        String valueOf2 = String.valueOf(Integer.toHexString(hashCode()));
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("@").append(valueOf2).toString();
    }

    public void zza(float f) {
        this.zzbsd = f;
        zzns();
    }

    public void zza(float f, float f2) {
        if (this.zzbsg != null) {
            this.zzbsg.zzb(f, f2);
        }
    }

    public void zza(zzh com_google_android_gms_ads_internal_overlay_zzh) {
        this.zzbsj = com_google_android_gms_ads_internal_overlay_zzh;
    }

    public String zzni() {
        String str = "MediaPlayer";
        String valueOf = String.valueOf(this.zzbsh ? " spherical" : "");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public void zzno() {
        this.zzbse = true;
        zzns();
    }

    public void zznp() {
        this.zzbse = false;
        zzns();
    }
}
