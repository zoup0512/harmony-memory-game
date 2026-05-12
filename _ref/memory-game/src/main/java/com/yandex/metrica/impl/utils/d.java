package com.yandex.metrica.impl.utils;

import android.util.Base64;
import com.yandex.metrica.impl.bg;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class d {
    private final String a;
    private final String b;

    public d() {
        this("AES/CBC/PKCS7Padding", "RSA/ECB/PKCS1Padding");
    }

    d(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public byte[] a(byte[] bArr) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[16];
            secureRandom.nextBytes(bArr3);
            secureRandom.nextBytes(bArr2);
            return a(bArr, bArr3, bArr2, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDhmH/m2qrRjxDHP794CeaZpENQNYydf8pqyXJilo6XxK+n+pvo27VxWfB3Z1yHrtKow+eZXKLQzrQ8wZMfRgADrYCQJ20y2hGZEUCN1tGSM+xqVKMeCtVi3NvQa54Cx7mT5ECVsH5DKEs/aeScDHP56FzcgEbtOSwyRZ8dsEM0wwIDAQAB", 0))));
        } catch (InvalidKeySpecException e) {
            return null;
        } catch (NoSuchAlgorithmException e2) {
            return null;
        }
    }

    byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, PublicKey publicKey) {
        Closeable byteArrayOutputStream;
        Throwable th;
        byte[] bArr4 = null;
        try {
            Key secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance(this.a);
            instance.init(1, secretKeySpec, new IvParameterSpec(bArr3));
            Cipher instance2 = Cipher.getInstance(this.b);
            instance2.init(1, publicKey);
            byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            try {
                byte[] doFinal = instance.doFinal(bArr);
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(bArr2.length + bArr3.length);
                byteArrayOutputStream2.write(bArr2);
                byteArrayOutputStream2.write(bArr3);
                byte[] toByteArray = byteArrayOutputStream2.toByteArray();
                byteArrayOutputStream2.close();
                byteArrayOutputStream.write(instance2.doFinal(toByteArray));
                byteArrayOutputStream.write(doFinal);
                bArr4 = byteArrayOutputStream.toByteArray();
                bg.a(byteArrayOutputStream);
            } catch (Exception e) {
                bg.a(byteArrayOutputStream);
                return bArr4;
            } catch (Throwable th2) {
                th = th2;
                bg.a(byteArrayOutputStream);
                throw th;
            }
        } catch (Exception e2) {
            byteArrayOutputStream = bArr4;
            bg.a(byteArrayOutputStream);
            return bArr4;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            byteArrayOutputStream = bArr4;
            th = th4;
            bg.a(byteArrayOutputStream);
            throw th;
        }
        return bArr4;
    }
}
