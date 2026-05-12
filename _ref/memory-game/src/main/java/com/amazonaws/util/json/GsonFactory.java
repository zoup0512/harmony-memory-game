package com.amazonaws.util.json;

import com.amazonaws.util.BinaryUtils;
import com.facebook.internal.ServerProtocol;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Date;

final class GsonFactory implements AwsJsonFactory {

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    private static final class GsonReader implements AwsJsonReader {
        private final JsonReader reader;

        public GsonReader(Reader in) {
            this.reader = new JsonReader(in);
        }

        public void beginArray() throws IOException {
            this.reader.beginArray();
        }

        public void endArray() throws IOException {
            this.reader.endArray();
        }

        public void beginObject() throws IOException {
            this.reader.beginObject();
        }

        public void endObject() throws IOException {
            this.reader.endObject();
        }

        public boolean isContainer() throws IOException {
            JsonToken token = this.reader.peek();
            return JsonToken.BEGIN_ARRAY.equals(token) || JsonToken.BEGIN_OBJECT.equals(token);
        }

        public boolean hasNext() throws IOException {
            return this.reader.hasNext();
        }

        public String nextName() throws IOException {
            return this.reader.nextName();
        }

        public String nextString() throws IOException {
            JsonToken token = this.reader.peek();
            if (JsonToken.NULL.equals(token)) {
                this.reader.nextNull();
                return null;
            } else if (JsonToken.BOOLEAN.equals(token)) {
                return this.reader.nextBoolean() ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
            } else {
                return this.reader.nextString();
            }
        }

        public void skipValue() throws IOException {
            this.reader.skipValue();
        }

        public AwsJsonToken peek() throws IOException {
            try {
                return GsonFactory.convert(this.reader.peek());
            } catch (EOFException e) {
                return null;
            }
        }

        public void close() throws IOException {
            this.reader.close();
        }
    }

    private static final class GsonWriter implements AwsJsonWriter {
        private final JsonWriter writer;

        public GsonWriter(Writer out) {
            this.writer = new JsonWriter(out);
        }

        public AwsJsonWriter beginArray() throws IOException {
            this.writer.beginArray();
            return this;
        }

        public AwsJsonWriter endArray() throws IOException {
            this.writer.endArray();
            return this;
        }

        public AwsJsonWriter beginObject() throws IOException {
            this.writer.beginObject();
            return this;
        }

        public AwsJsonWriter endObject() throws IOException {
            this.writer.endObject();
            return this;
        }

        public AwsJsonWriter name(String name) throws IOException {
            this.writer.name(name);
            return this;
        }

        public AwsJsonWriter value(String value) throws IOException {
            this.writer.value(value);
            return this;
        }

        public AwsJsonWriter value(boolean value) throws IOException {
            this.writer.value(value);
            return this;
        }

        public AwsJsonWriter value(double value) throws IOException {
            this.writer.value(value);
            return this;
        }

        public AwsJsonWriter value(long value) throws IOException {
            this.writer.value(value);
            return this;
        }

        public AwsJsonWriter value(Number value) throws IOException {
            this.writer.value(value);
            return this;
        }

        public AwsJsonWriter value(Date value) throws IOException {
            this.writer.value(BigDecimal.valueOf(value.getTime()).scaleByPowerOfTen(-3));
            return this;
        }

        public AwsJsonWriter value(ByteBuffer value) throws IOException {
            value.mark();
            byte[] bytes = new byte[value.remaining()];
            value.get(bytes, 0, bytes.length);
            value.reset();
            this.writer.value(BinaryUtils.toBase64(bytes));
            return this;
        }

        public AwsJsonWriter value() throws IOException {
            this.writer.nullValue();
            return this;
        }

        public void flush() throws IOException {
            this.writer.flush();
        }

        public void close() throws IOException {
            this.writer.close();
        }
    }

    GsonFactory() {
    }

    public AwsJsonReader getJsonReader(Reader in) {
        return new GsonReader(in);
    }

    public AwsJsonWriter getJsonWriter(Writer out) {
        return new GsonWriter(out);
    }

    private static AwsJsonToken convert(JsonToken token) {
        if (token == null) {
            return null;
        }
        switch (AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[token.ordinal()]) {
            case 1:
                return AwsJsonToken.BEGIN_ARRAY;
            case 2:
                return AwsJsonToken.END_ARRAY;
            case 3:
                return AwsJsonToken.BEGIN_OBJECT;
            case 4:
                return AwsJsonToken.END_OBJECT;
            case 5:
                return AwsJsonToken.FIELD_NAME;
            case 6:
                return AwsJsonToken.VALUE_BOOLEAN;
            case 7:
                return AwsJsonToken.VALUE_NUMBER;
            case 8:
                return AwsJsonToken.VALUE_NULL;
            case 9:
                return AwsJsonToken.VALUE_STRING;
            case 10:
                return null;
            default:
                return AwsJsonToken.UNKNOWN;
        }
    }
}
