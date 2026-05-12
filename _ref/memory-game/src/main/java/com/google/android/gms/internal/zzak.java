package com.google.android.gms.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.internal.zzae.zzc;
import com.google.android.gms.internal.zzae.zzf;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzak {
    static boolean zzxe = false;
    private static MessageDigest zzxf = null;
    private static final Object zzxg = new Object();
    private static final Object zzxh = new Object();
    static CountDownLatch zzxi = new CountDownLatch(1);

    private static final class zza implements Runnable {
        private zza() {
        }

        public void run() {
            try {
                zzak.zzxf = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
            } catch (NoSuchAlgorithmException e) {
            } finally {
                zzak.zzxi.countDown();
            }
        }
    }

    private static int zza(boolean z) {
        return z ? 239 : 255;
    }

    static String zza(com.google.android.gms.internal.zzae.zza com_google_android_gms_internal_zzae_zza, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return zza(zzapv.zzf(com_google_android_gms_internal_zzae_zza), str, z);
    }

    static String zza(String str, String str2, boolean z) {
        byte[] zzb = zzb(str, str2, z);
        return zzb != null ? zzaj.zza(zzb, true) : Integer.toString(7);
    }

    static String zza(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return zzaj.zza(z ? zzb(bArr, str) : zza(bArr, str), true);
    }

    static Vector<byte[]> zza(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + i) - 1) / i;
        Vector<byte[]> vector = new Vector();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * i;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > i ? i3 + i : bArr.length));
                i2++;
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return vector;
    }

    static void zza(String str, byte[] bArr) throws UnsupportedEncodingException {
        if (str.length() > 32) {
            str = str.substring(0, 32);
        }
        new zzaoq(str.getBytes("UTF-8")).zzax(bArr);
    }

    static byte[] zza(byte[] bArr, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Vector zza = zza(bArr, 255);
        if (zza == null || zza.size() == 0) {
            return zzb(zzapv.zzf(zzb(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)), str);
        }
        zzapv com_google_android_gms_internal_zzae_zzf = new zzf();
        com_google_android_gms_internal_zzae_zzf.zzey = new byte[zza.size()][];
        Iterator it = zza.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            com_google_android_gms_internal_zzae_zzf.zzey[i] = zzb((byte[]) it.next(), str, false);
            i = i2;
        }
        com_google_android_gms_internal_zzae_zzf.zzet = zzg(bArr);
        return zzapv.zzf(com_google_android_gms_internal_zzae_zzf);
    }

    static void zzar() {
        synchronized (zzxh) {
            if (!zzxe) {
                zzxe = true;
                new Thread(new zza()).start();
            }
        }
    }

    static MessageDigest zzas() {
        zzar();
        boolean z = false;
        try {
            z = zzxi.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        return (z && zzxf != null) ? zzxf : null;
    }

    static com.google.android.gms.internal.zzae.zza zzb(long j) {
        com.google.android.gms.internal.zzae.zza com_google_android_gms_internal_zzae_zza = new com.google.android.gms.internal.zzae.zza();
        com_google_android_gms_internal_zzae_zza.zzdl = Long.valueOf(j);
        return com_google_android_gms_internal_zzae_zza;
    }

    static byte[] zzb(String str, String str2, boolean z) {
        zzapv com_google_android_gms_internal_zzae_zzc = new zzc();
        try {
            com_google_android_gms_internal_zzae_zzc.zzer = str.length() < 3 ? str.getBytes("ISO-8859-1") : zzaj.zza(str, true);
            byte[] bytes = z ? str2.length() < 3 ? str2.getBytes("ISO-8859-1") : zzaj.zza(str2, true) : (str2 == null || str2.length() == 0) ? Integer.toString(5).getBytes("ISO-8859-1") : zzaj.zza(zza(str2.getBytes("ISO-8859-1"), null, true), true);
            com_google_android_gms_internal_zzae_zzc.zzes = bytes;
            return zzapv.zzf(com_google_android_gms_internal_zzae_zzc);
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (NoSuchAlgorithmException e2) {
            return null;
        }
    }

    static byte[] zzb(byte[] bArr, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return zzb(bArr, str, true);
    }

    private static byte[] zzb(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bArr2;
        byte[] array;
        int zza = zza(z);
        if (bArr.length > zza) {
            bArr = zzapv.zzf(zzb(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM));
        }
        if (bArr.length < zza) {
            bArr2 = new byte[(zza - bArr.length)];
            new SecureRandom().nextBytes(bArr2);
            array = ByteBuffer.allocate(zza + 1).put((byte) bArr.length).put(bArr).put(bArr2).array();
        } else {
            array = ByteBuffer.allocate(zza + 1).put((byte) bArr.length).put(bArr).array();
        }
        if (z) {
            array = ByteBuffer.allocate(256).put(zzg(array)).put(array).array();
        }
        bArr2 = new byte[256];
        new zzal().zzb(array, bArr2);
        if (str != null && str.length() > 0) {
            zza(str, bArr2);
        }
        return bArr2;
    }

    public static byte[] zzg(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] digest;
        synchronized (zzxg) {
            MessageDigest zzas = zzas();
            if (zzas == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
            zzas.reset();
            zzas.update(bArr);
            digest = zzxf.digest();
        }
        return digest;
    }
}
