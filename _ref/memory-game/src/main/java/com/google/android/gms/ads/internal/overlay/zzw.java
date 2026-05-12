package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.ads.internal.overlay.zzv.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzcy;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzin;
import com.mopub.volley.DefaultRetryPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@TargetApi(14)
@zzin
public class zzw extends Thread implements OnFrameAvailableListener, zza {
    private static final float[] zzbur = new float[]{-1.0f, -1.0f, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, -1.0f, -1.0f, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, -1.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, -1.0f};
    private int zzaie;
    private int zzaif;
    private final float[] zzbun;
    private final zzv zzbus;
    private final float[] zzbut;
    private final float[] zzbuu;
    private final float[] zzbuv;
    private final float[] zzbuw;
    private final float[] zzbux;
    private final float[] zzbuy;
    private float zzbuz;
    private float zzbva;
    private float zzbvb;
    private SurfaceTexture zzbvc;
    private SurfaceTexture zzbvd;
    private int zzbve;
    private int zzbvf;
    private int zzbvg;
    private FloatBuffer zzbvh = ByteBuffer.allocateDirect(zzbur.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    private final CountDownLatch zzbvi;
    private final Object zzbvj;
    private EGL10 zzbvk;
    private EGLDisplay zzbvl;
    private EGLContext zzbvm;
    private EGLSurface zzbvn;
    private volatile boolean zzbvo;
    private volatile boolean zzbvp;

    zzw(Context context) {
        super("SphericalVideoProcessor");
        this.zzbvh.put(zzbur).position(0);
        this.zzbun = new float[9];
        this.zzbut = new float[9];
        this.zzbuu = new float[9];
        this.zzbuv = new float[9];
        this.zzbuw = new float[9];
        this.zzbux = new float[9];
        this.zzbuy = new float[9];
        this.zzbuz = Float.NaN;
        this.zzbus = new zzv(context);
        this.zzbus.zza((zza) this);
        this.zzbvi = new CountDownLatch(1);
        this.zzbvj = new Object();
    }

    private void zza(float[] fArr, float f) {
        fArr[0] = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = (float) Math.cos((double) f);
        fArr[5] = (float) (-Math.sin((double) f));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin((double) f);
        fArr[8] = (float) Math.cos((double) f);
    }

    private void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = ((fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3])) + (fArr2[2] * fArr3[6]);
        fArr[1] = ((fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4])) + (fArr2[2] * fArr3[7]);
        fArr[2] = ((fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5])) + (fArr2[2] * fArr3[8]);
        fArr[3] = ((fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3])) + (fArr2[5] * fArr3[6]);
        fArr[4] = ((fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4])) + (fArr2[5] * fArr3[7]);
        fArr[5] = ((fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5])) + (fArr2[5] * fArr3[8]);
        fArr[6] = ((fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3])) + (fArr2[8] * fArr3[6]);
        fArr[7] = ((fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4])) + (fArr2[8] * fArr3[7]);
        fArr[8] = ((fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5])) + (fArr2[8] * fArr3[8]);
    }

    private float[] zza(float[] fArr, float[] fArr2) {
        return new float[]{((fArr[0] * fArr2[0]) + (fArr[1] * fArr2[1])) + (fArr[2] * fArr2[2]), ((fArr[3] * fArr2[0]) + (fArr[4] * fArr2[1])) + (fArr[5] * fArr2[2]), ((fArr[6] * fArr2[0]) + (fArr[7] * fArr2[1])) + (fArr[8] * fArr2[2])};
    }

    private void zzb(float[] fArr, float f) {
        fArr[0] = (float) Math.cos((double) f);
        fArr[1] = (float) (-Math.sin((double) f));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin((double) f);
        fArr[4] = (float) Math.cos((double) f);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    }

    private void zzbx(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("SphericalVideoRenderer", new StringBuilder(String.valueOf(str).length() + 21).append(str).append(": glError ").append(glGetError).toString());
        }
    }

    private float zzc(float[] fArr) {
        float[] zza = zza(fArr, new float[]{0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f});
        return ((float) Math.atan2((double) zza[1], (double) zza[0])) - 1.5707964f;
    }

    private int zzc(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        zzbx("createShader");
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            zzbx("shaderSource");
            GLES20.glCompileShader(glCreateShader);
            zzbx("compileShader");
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            zzbx("getShaderiv");
            if (iArr[0] == 0) {
                Log.e("SphericalVideoRenderer", "Could not compile shader " + i + ":");
                Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
                GLES20.glDeleteShader(glCreateShader);
                zzbx("deleteShader");
                return 0;
            }
        }
        return glCreateShader;
    }

    private void zzoz() {
        GLES20.glViewport(0, 0, this.zzaie, this.zzaif);
        zzbx("viewport");
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.zzbve, "uFOVx");
        int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.zzbve, "uFOVy");
        if (this.zzaie > this.zzaif) {
            GLES20.glUniform1f(glGetUniformLocation, 0.87266463f);
            GLES20.glUniform1f(glGetUniformLocation2, (((float) this.zzaif) * 0.87266463f) / ((float) this.zzaie));
            return;
        }
        GLES20.glUniform1f(glGetUniformLocation, (((float) this.zzaie) * 0.87266463f) / ((float) this.zzaif));
        GLES20.glUniform1f(glGetUniformLocation2, 0.87266463f);
    }

    private int zzpb() {
        int zzc = zzc(35633, zzpe());
        if (zzc == 0) {
            return 0;
        }
        int zzc2 = zzc(35632, zzpf());
        if (zzc2 == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        zzbx("createProgram");
        if (glCreateProgram != 0) {
            GLES20.glAttachShader(glCreateProgram, zzc);
            zzbx("attachShader");
            GLES20.glAttachShader(glCreateProgram, zzc2);
            zzbx("attachShader");
            GLES20.glLinkProgram(glCreateProgram);
            zzbx("linkProgram");
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
            zzbx("getProgramiv");
            if (iArr[0] != 1) {
                Log.e("SphericalVideoRenderer", "Could not link program: ");
                Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(glCreateProgram));
                GLES20.glDeleteProgram(glCreateProgram);
                zzbx("deleteProgram");
                return 0;
            }
            GLES20.glValidateProgram(glCreateProgram);
            zzbx("validateProgram");
        }
        return glCreateProgram;
    }

    @Nullable
    private EGLConfig zzpd() {
        int[] iArr = new int[1];
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        return !this.zzbvk.eglChooseConfig(this.zzbvl, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344}, eGLConfigArr, 1, iArr) ? null : iArr[0] > 0 ? eGLConfigArr[0] : null;
    }

    private String zzpe() {
        zzcy com_google_android_gms_internal_zzcy = zzdc.zzbam;
        return !((String) com_google_android_gms_internal_zzcy.get()).equals(com_google_android_gms_internal_zzcy.zzjw()) ? (String) com_google_android_gms_internal_zzcy.get() : "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}";
    }

    private String zzpf() {
        zzcy com_google_android_gms_internal_zzcy = zzdc.zzban;
        return !((String) com_google_android_gms_internal_zzcy.get()).equals(com_google_android_gms_internal_zzcy.zzjw()) ? (String) com_google_android_gms_internal_zzcy.get() : "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}";
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzbvg++;
        synchronized (this.zzbvj) {
            this.zzbvj.notifyAll();
        }
    }

    public void run() {
        boolean z = false;
        if (this.zzbvd == null) {
            zzb.e("SphericalVideoProcessor started with no output texture.");
            this.zzbvi.countDown();
            return;
        }
        boolean zzpc = zzpc();
        int zzpa = zzpa();
        if (this.zzbve != 0) {
            z = true;
        }
        if (zzpc && r0) {
            this.zzbvc = new SurfaceTexture(zzpa);
            this.zzbvc.setOnFrameAvailableListener(this);
            this.zzbvi.countDown();
            this.zzbus.start();
            try {
                this.zzbvo = true;
                while (!this.zzbvp) {
                    zzoy();
                    if (this.zzbvo) {
                        zzoz();
                        this.zzbvo = false;
                    }
                    try {
                        synchronized (this.zzbvj) {
                            if (!(this.zzbvp || this.zzbvo || this.zzbvg != 0)) {
                                this.zzbvj.wait();
                            }
                        }
                    } catch (InterruptedException e) {
                    }
                }
            } catch (IllegalStateException e2) {
                zzb.zzcx("SphericalVideoProcessor halted unexpectedly.");
            } catch (Throwable th) {
                zzb.zzb("SphericalVideoProcessor died.", th);
                zzu.zzft().zzb(th, true);
            } finally {
                this.zzbus.stop();
                this.zzbvc.setOnFrameAvailableListener(null);
                this.zzbvc = null;
                zzpg();
            }
        } else {
            String str = "EGL initialization failed: ";
            String valueOf = String.valueOf(GLUtils.getEGLErrorString(this.zzbvk.eglGetError()));
            valueOf = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
            zzb.e(valueOf);
            zzu.zzft().zzb(new Throwable(valueOf), true);
            zzpg();
            this.zzbvi.countDown();
        }
    }

    void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzaie = i;
        this.zzaif = i2;
        this.zzbvd = surfaceTexture;
    }

    void zzb(float f, float f2) {
        float f3;
        float f4;
        if (this.zzaie > this.zzaif) {
            f3 = (1.7453293f * f) / ((float) this.zzaie);
            f4 = (1.7453293f * f2) / ((float) this.zzaie);
        } else {
            f3 = (1.7453293f * f) / ((float) this.zzaif);
            f4 = (1.7453293f * f2) / ((float) this.zzaif);
        }
        this.zzbva -= f3;
        this.zzbvb -= f4;
        if (this.zzbvb < -1.5707964f) {
            this.zzbvb = -1.5707964f;
        }
        if (this.zzbvb > 1.5707964f) {
            this.zzbvb = 1.5707964f;
        }
    }

    void zzg(int i, int i2) {
        synchronized (this.zzbvj) {
            this.zzaie = i;
            this.zzaif = i2;
            this.zzbvo = true;
            this.zzbvj.notifyAll();
        }
    }

    public void zznz() {
        synchronized (this.zzbvj) {
            this.zzbvj.notifyAll();
        }
    }

    void zzow() {
        synchronized (this.zzbvj) {
            this.zzbvp = true;
            this.zzbvd = null;
            this.zzbvj.notifyAll();
        }
    }

    public SurfaceTexture zzox() {
        if (this.zzbvd == null) {
            return null;
        }
        try {
            this.zzbvi.await();
        } catch (InterruptedException e) {
        }
        return this.zzbvc;
    }

    void zzoy() {
        while (this.zzbvg > 0) {
            this.zzbvc.updateTexImage();
            this.zzbvg--;
        }
        if (this.zzbus.zzb(this.zzbun)) {
            if (Float.isNaN(this.zzbuz)) {
                this.zzbuz = -zzc(this.zzbun);
            }
            zzb(this.zzbux, this.zzbuz + this.zzbva);
        } else {
            zza(this.zzbun, -1.5707964f);
            zzb(this.zzbux, this.zzbva);
        }
        zza(this.zzbut, 1.5707964f);
        zza(this.zzbuu, this.zzbux, this.zzbut);
        zza(this.zzbuv, this.zzbun, this.zzbuu);
        zza(this.zzbuw, this.zzbvb);
        zza(this.zzbuy, this.zzbuw, this.zzbuv);
        GLES20.glUniformMatrix3fv(this.zzbvf, 1, false, this.zzbuy, 0);
        GLES20.glDrawArrays(5, 0, 4);
        zzbx("drawArrays");
        GLES20.glFinish();
        this.zzbvk.eglSwapBuffers(this.zzbvl, this.zzbvn);
    }

    int zzpa() {
        this.zzbve = zzpb();
        GLES20.glUseProgram(this.zzbve);
        zzbx("useProgram");
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.zzbve, "aPosition");
        GLES20.glVertexAttribPointer(glGetAttribLocation, 3, 5126, false, 12, this.zzbvh);
        zzbx("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        zzbx("enableVertexAttribArray");
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        zzbx("genTextures");
        glGetAttribLocation = iArr[0];
        GLES20.glBindTexture(36197, glGetAttribLocation);
        zzbx("bindTextures");
        GLES20.glTexParameteri(36197, 10240, 9729);
        zzbx("texParameteri");
        GLES20.glTexParameteri(36197, 10241, 9729);
        zzbx("texParameteri");
        GLES20.glTexParameteri(36197, 10242, 33071);
        zzbx("texParameteri");
        GLES20.glTexParameteri(36197, 10243, 33071);
        zzbx("texParameteri");
        this.zzbvf = GLES20.glGetUniformLocation(this.zzbve, "uVMat");
        GLES20.glUniformMatrix3fv(this.zzbvf, 1, false, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}, 0);
        return glGetAttribLocation;
    }

    boolean zzpc() {
        this.zzbvk = (EGL10) EGLContext.getEGL();
        this.zzbvl = this.zzbvk.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (this.zzbvl == EGL10.EGL_NO_DISPLAY) {
            return false;
        }
        if (!this.zzbvk.eglInitialize(this.zzbvl, new int[2])) {
            return false;
        }
        EGLConfig zzpd = zzpd();
        if (zzpd == null) {
            return false;
        }
        this.zzbvm = this.zzbvk.eglCreateContext(this.zzbvl, zzpd, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
        if (this.zzbvm == null || this.zzbvm == EGL10.EGL_NO_CONTEXT) {
            return false;
        }
        this.zzbvn = this.zzbvk.eglCreateWindowSurface(this.zzbvl, zzpd, this.zzbvd, null);
        return (this.zzbvn == null || this.zzbvn == EGL10.EGL_NO_SURFACE) ? false : this.zzbvk.eglMakeCurrent(this.zzbvl, this.zzbvn, this.zzbvn, this.zzbvm);
    }

    boolean zzpg() {
        boolean z = false;
        if (!(this.zzbvn == null || this.zzbvn == EGL10.EGL_NO_SURFACE)) {
            z = (this.zzbvk.eglMakeCurrent(this.zzbvl, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | 0) | this.zzbvk.eglDestroySurface(this.zzbvl, this.zzbvn);
            this.zzbvn = null;
        }
        if (this.zzbvm != null) {
            z |= this.zzbvk.eglDestroyContext(this.zzbvl, this.zzbvm);
            this.zzbvm = null;
        }
        if (this.zzbvl == null) {
            return z;
        }
        z |= this.zzbvk.eglTerminate(this.zzbvl);
        this.zzbvl = null;
        return z;
    }
}
