package com.amazonaws.util;

import com.facebook.appevents.AppEventsConstants;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class BinaryUtils {
    public static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte toHexString : data) {
            String hex = Integer.toHexString(toHexString);
            if (hex.length() == 1) {
                sb.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            } else if (hex.length() == 8) {
                hex = hex.substring(6);
            }
            sb.append(hex);
        }
        return StringUtils.lowerCase(sb.toString());
    }

    public static byte[] fromHex(String hexData) {
        byte[] result = new byte[((hexData.length() + 1) / 2)];
        int stringOffset = 0;
        int byteOffset = 0;
        while (stringOffset < hexData.length()) {
            String hexNumber = hexData.substring(stringOffset, stringOffset + 2);
            stringOffset += 2;
            int byteOffset2 = byteOffset + 1;
            result[byteOffset] = (byte) Integer.parseInt(hexNumber, 16);
            byteOffset = byteOffset2;
        }
        return result;
    }

    public static String toBase64(byte[] data) {
        return Base64.encodeAsString(data);
    }

    public static byte[] fromBase64(String b64Data) {
        return b64Data == null ? null : Base64.decode(b64Data);
    }

    public static InputStream toStream(ByteBuffer byteBuffer) {
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return new ByteArrayInputStream(bytes);
    }
}
