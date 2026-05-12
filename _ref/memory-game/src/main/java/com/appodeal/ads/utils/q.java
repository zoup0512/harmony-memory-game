package com.appodeal.ads.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ak;
import com.appodeal.ads.an;
import com.appodeal.ads.ar;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class q extends AsyncTask<Void, Void, Void> {
    private final Activity a;
    private final ar b;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    public q(Activity activity, int i) {
        this.a = activity;
        this.b = (ar) ak.m.get(i);
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ q a;

            {
                this.a = r1;
            }

            public void run() {
                if (VERSION.SDK_INT >= 11) {
                    this.a.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    this.a.execute(new Void[0]);
                }
            }
        });
    }

    protected Void a(Void... voidArr) {
        HttpURLConnection httpURLConnection;
        Throwable th;
        HttpURLConnection httpURLConnection2 = null;
        try {
            int i;
            int i2;
            int length = ak.r.length();
            char[] cArr = new char[(length / 2)];
            for (i = 0; i < length; i += 2) {
                cArr[i / 2] = (char) ((Character.digit(ak.r.charAt(i), 16) << 4) + Character.digit(ak.r.charAt(i + 1), 16));
            }
            for (i = 0; i < cArr.length; i++) {
                cArr[i] = (char) (cArr[i] ^ i);
            }
            String[] split = String.valueOf(cArr).split("\\|", 2);
            String str = split[0];
            String str2 = "user_id=" + String.valueOf(Appodeal.getUserSettings(this.a).getUserId()) + "&amount=" + ak.b() + "&currency=" + String.valueOf(ak.c()) + "&impression_id=" + this.b.B + "&timestamp=" + (System.currentTimeMillis() / 1000);
            byte[] bytes = str2.getBytes("UTF-8");
            int[] iArr = new int[((((bytes.length + 8) >> 6) + 1) * 16)];
            i = 0;
            while (i < bytes.length) {
                i2 = i >> 2;
                iArr[i2] = iArr[i2] | (bytes[i] << (24 - ((i % 4) * 8)));
                i++;
            }
            i2 = i >> 2;
            iArr[i2] = (128 << (24 - ((i % 4) * 8))) | iArr[i2];
            iArr[iArr.length - 1] = bytes.length * 8;
            int[] iArr2 = new int[80];
            int i3 = 1732584193;
            int i4 = -271733879;
            int i5 = -1732584194;
            int i6 = 271733878;
            int i7 = -1009589776;
            for (int i8 = 0; i8 < iArr.length; i8 += 16) {
                length = 0;
                i2 = i7;
                int i9 = i6;
                int i10 = i5;
                int i11 = i4;
                int i12 = i3;
                while (length < 80) {
                    if (length < 16) {
                        i = iArr[i8 + length];
                    } else {
                        i = a(((iArr2[length - 3] ^ iArr2[length - 8]) ^ iArr2[length - 14]) ^ iArr2[length - 16], 1);
                    }
                    iArr2[length] = i;
                    i2 = iArr2[length] + (a(i12, 5) + i2);
                    i = length < 20 ? 1518500249 + ((i11 & i10) | ((i11 ^ -1) & i9)) : length < 40 ? 1859775393 + ((i11 ^ i10) ^ i9) : length < 60 ? -1894007588 + (((i11 & i10) | (i11 & i9)) | (i10 & i9)) : -899497514 + ((i11 ^ i10) ^ i9);
                    int i13 = i2 + i;
                    i2 = a(i11, 30);
                    length++;
                    i11 = i12;
                    i12 = i13;
                    int i14 = i2;
                    i2 = i9;
                    i9 = i10;
                    i10 = i14;
                }
                i3 += i12;
                i4 += i11;
                i5 += i10;
                i6 += i9;
                i7 += i2;
            }
            int[] iArr3 = new int[]{i3, i4, i5, i6, i7};
            ByteBuffer allocate = ByteBuffer.allocate(iArr3.length * 4);
            allocate.asIntBuffer().put(iArr3);
            bytes = allocate.array();
            if (this.b.B != null) {
                this.b.B = null;
                cArr = new char[(bytes.length * 2)];
                char[] toCharArray = "0123456789ABCDEF".toCharArray();
                for (i = 0; i < bytes.length; i++) {
                    i7 = bytes[i] & 255;
                    cArr[i * 2] = toCharArray[i7 >>> 4];
                    cArr[(i * 2) + 1] = toCharArray[i7 & 15];
                }
                bytes = (str2 + "&hash=" + new String(cArr)).getBytes("UTF-8");
                MessageDigest instance = MessageDigest.getInstance("SHA-256");
                instance.reset();
                instance.update(split[1].getBytes("UTF-8"));
                Key secretKeySpec = new SecretKeySpec(instance.digest(), "AES");
                byte[] bArr = new byte[16];
                new SecureRandom().nextBytes(bArr);
                AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
                Cipher instance2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance2.init(1, secretKeySpec, ivParameterSpec);
                byte[] bArr2 = new byte[instance2.getOutputSize(bytes.length)];
                instance2.doFinal(bArr2, instance2.update(bytes, 0, bytes.length, bArr2, 0));
                char[] cArr2 = new char[(bArr.length * 2)];
                for (i = 0; i < bArr.length; i++) {
                    i2 = bArr[i] & 255;
                    cArr2[i * 2] = toCharArray[i2 >>> 4];
                    cArr2[(i * 2) + 1] = toCharArray[i2 & 15];
                }
                String str3 = new String(cArr2);
                cArr2 = new char[(bArr2.length * 2)];
                for (i = 0; i < bArr2.length; i++) {
                    i7 = bArr2[i] & 255;
                    cArr2[i * 2] = toCharArray[i7 >>> 4];
                    cArr2[(i * 2) + 1] = toCharArray[i7 & 15];
                }
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str + (str.contains("?") ? "&" : "?") + "data1=" + str3 + "&data2=" + new String(cArr2)).openConnection();
                try {
                    httpURLConnection3.setConnectTimeout(20000);
                    httpURLConnection3.setReadTimeout(20000);
                    an.a(httpURLConnection3.getInputStream());
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                } catch (Throwable e) {
                    Throwable th2 = e;
                    httpURLConnection = httpURLConnection3;
                    th = th2;
                    try {
                        Appodeal.a(th);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        httpURLConnection2 = httpURLConnection;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable e2) {
                    httpURLConnection2 = httpURLConnection3;
                    th = e2;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
                return null;
            } else if (httpURLConnection2 == null) {
                return null;
            } else {
                httpURLConnection2.disconnect();
                return null;
            }
        } catch (Exception e3) {
            th = e3;
            httpURLConnection = httpURLConnection2;
            Appodeal.a(th);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    private static int a(int i, int i2) {
        return (i << i2) | (i >>> (32 - i2));
    }
}
