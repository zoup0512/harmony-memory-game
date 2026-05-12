package com.amazonaws.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.util.Base64;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class SimpleTypeJsonUnmarshallers {

    public static class BigDecimalJsonUnmarshaller implements Unmarshaller<BigDecimal, JsonUnmarshallerContext> {
        private static BigDecimalJsonUnmarshaller instance;

        public BigDecimal unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String s = unmarshallerContext.getReader().nextString();
            return s == null ? null : new BigDecimal(s);
        }

        public static BigDecimalJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new BigDecimalJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class BigIntegerJsonUnmarshaller implements Unmarshaller<BigInteger, JsonUnmarshallerContext> {
        private static BigIntegerJsonUnmarshaller instance;

        public BigInteger unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String intString = unmarshallerContext.getReader().nextString();
            return intString == null ? null : new BigInteger(intString);
        }

        public static BigIntegerJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new BigIntegerJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class BooleanJsonUnmarshaller implements Unmarshaller<Boolean, JsonUnmarshallerContext> {
        private static BooleanJsonUnmarshaller instance;

        public Boolean unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String booleanString = unmarshallerContext.getReader().nextString();
            return booleanString == null ? null : Boolean.valueOf(Boolean.parseBoolean(booleanString));
        }

        public static BooleanJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new BooleanJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class ByteBufferJsonUnmarshaller implements Unmarshaller<ByteBuffer, JsonUnmarshallerContext> {
        private static ByteBufferJsonUnmarshaller instance;

        public ByteBuffer unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            return ByteBuffer.wrap(Base64.decode(unmarshallerContext.getReader().nextString()));
        }

        public static ByteBufferJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new ByteBufferJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class ByteJsonUnmarshaller implements Unmarshaller<Byte, JsonUnmarshallerContext> {
        private static ByteJsonUnmarshaller instance;

        public Byte unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String byteString = unmarshallerContext.getReader().nextString();
            return byteString == null ? null : Byte.valueOf(byteString);
        }

        public static ByteJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new ByteJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class DateJsonUnmarshaller implements Unmarshaller<Date, JsonUnmarshallerContext> {
        private static DateJsonUnmarshaller instance;

        public Date unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String dateString = unmarshallerContext.getReader().nextString();
            if (dateString == null) {
                return null;
            }
            try {
                return new Date(NumberFormat.getInstance(new Locale("en")).parse(dateString).longValue() * 1000);
            } catch (ParseException e) {
                throw new AmazonClientException("Unable to parse date '" + dateString + "':  " + e.getMessage(), e);
            }
        }

        public static DateJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new DateJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class DoubleJsonUnmarshaller implements Unmarshaller<Double, JsonUnmarshallerContext> {
        private static DoubleJsonUnmarshaller instance;

        public Double unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String doubleString = unmarshallerContext.getReader().nextString();
            return doubleString == null ? null : Double.valueOf(Double.parseDouble(doubleString));
        }

        public static DoubleJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new DoubleJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class FloatJsonUnmarshaller implements Unmarshaller<Float, JsonUnmarshallerContext> {
        private static FloatJsonUnmarshaller instance;

        public Float unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String floatString = unmarshallerContext.getReader().nextString();
            return floatString == null ? null : Float.valueOf(floatString);
        }

        public static FloatJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new FloatJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class IntegerJsonUnmarshaller implements Unmarshaller<Integer, JsonUnmarshallerContext> {
        private static IntegerJsonUnmarshaller instance;

        public Integer unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String intString = unmarshallerContext.getReader().nextString();
            return intString == null ? null : Integer.valueOf(Integer.parseInt(intString));
        }

        public static IntegerJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new IntegerJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class LongJsonUnmarshaller implements Unmarshaller<Long, JsonUnmarshallerContext> {
        private static LongJsonUnmarshaller instance;

        public Long unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            String longString = unmarshallerContext.getReader().nextString();
            return longString == null ? null : Long.valueOf(Long.parseLong(longString));
        }

        public static LongJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new LongJsonUnmarshaller();
            }
            return instance;
        }
    }

    public static class StringJsonUnmarshaller implements Unmarshaller<String, JsonUnmarshallerContext> {
        private static StringJsonUnmarshaller instance;

        public String unmarshall(JsonUnmarshallerContext unmarshallerContext) throws Exception {
            return unmarshallerContext.getReader().nextString();
        }

        public static StringJsonUnmarshaller getInstance() {
            if (instance == null) {
                instance = new StringJsonUnmarshaller();
            }
            return instance;
        }
    }
}
