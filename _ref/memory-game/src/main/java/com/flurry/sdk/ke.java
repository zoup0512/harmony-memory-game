package com.flurry.sdk;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.zip.CRC32;

public final class ke extends MessageDigest {
    private CRC32 a = new CRC32();

    public ke() {
        super("CRC");
    }

    protected final void engineUpdate(byte b) {
        this.a.update(b);
    }

    protected final void engineUpdate(byte[] bArr, int i, int i2) {
        this.a.update(bArr, i, i2);
    }

    protected final byte[] engineDigest() {
        long value = this.a.getValue();
        return new byte[]{(byte) ((int) ((-16777216 & value) >> 24)), (byte) ((int) ((16711680 & value) >> 16)), (byte) ((int) ((65280 & value) >> 8)), (byte) ((int) ((value & 255) >> 0))};
    }

    protected final void engineReset() {
        this.a.reset();
    }

    public final byte[] a() {
        return engineDigest();
    }

    public final int b() {
        return ByteBuffer.wrap(engineDigest()).getInt();
    }
}
