package com.mopub.common.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.ScribeCategory;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class ErrorEvent extends BaseEvent {
    @Nullable
    private final String mErrorClassName;
    @Nullable
    private final String mErrorExceptionClassName;
    @Nullable
    private final String mErrorFileName;
    @Nullable
    private final Integer mErrorLineNumber;
    @Nullable
    private final String mErrorMessage;
    @Nullable
    private final String mErrorMethodName;
    @Nullable
    private final String mErrorStackTrace;

    public static class Builder extends com.mopub.common.event.BaseEvent.Builder {
        @Nullable
        private String mErrorClassName;
        @Nullable
        private String mErrorExceptionClassName;
        @Nullable
        private String mErrorFileName;
        @Nullable
        private Integer mErrorLineNumber;
        @Nullable
        private String mErrorMessage;
        @Nullable
        private String mErrorMethodName;
        @Nullable
        private String mErrorStackTrace;

        public Builder(@NonNull Name name, @NonNull Category category, double d) {
            super(ScribeCategory.EXCHANGE_CLIENT_ERROR, name, category, d);
        }

        @NonNull
        public Builder withErrorExceptionClassName(@Nullable String str) {
            this.mErrorExceptionClassName = str;
            return this;
        }

        @NonNull
        public Builder withErrorMessage(@Nullable String str) {
            this.mErrorMessage = str;
            return this;
        }

        @NonNull
        public Builder withErrorStackTrace(@Nullable String str) {
            this.mErrorStackTrace = str;
            return this;
        }

        @NonNull
        public Builder withErrorFileName(@Nullable String str) {
            this.mErrorFileName = str;
            return this;
        }

        @NonNull
        public Builder withErrorClassName(@Nullable String str) {
            this.mErrorClassName = str;
            return this;
        }

        @NonNull
        public Builder withErrorMethodName(@Nullable String str) {
            this.mErrorMethodName = str;
            return this;
        }

        @NonNull
        public Builder withErrorLineNumber(@Nullable Integer num) {
            this.mErrorLineNumber = num;
            return this;
        }

        @NonNull
        public Builder withException(@Nullable Exception exception) {
            this.mErrorExceptionClassName = exception.getClass().getName();
            this.mErrorMessage = exception.getMessage();
            Writer stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            this.mErrorStackTrace = stringWriter.toString();
            if (exception.getStackTrace().length > 0) {
                this.mErrorFileName = exception.getStackTrace()[0].getFileName();
                this.mErrorClassName = exception.getStackTrace()[0].getClassName();
                this.mErrorMethodName = exception.getStackTrace()[0].getMethodName();
                this.mErrorLineNumber = Integer.valueOf(exception.getStackTrace()[0].getLineNumber());
            }
            return this;
        }

        @NonNull
        public ErrorEvent build() {
            return new ErrorEvent();
        }
    }

    private ErrorEvent(@NonNull Builder builder) {
        super(builder);
        this.mErrorExceptionClassName = builder.mErrorExceptionClassName;
        this.mErrorMessage = builder.mErrorMessage;
        this.mErrorStackTrace = builder.mErrorStackTrace;
        this.mErrorFileName = builder.mErrorFileName;
        this.mErrorClassName = builder.mErrorClassName;
        this.mErrorMethodName = builder.mErrorMethodName;
        this.mErrorLineNumber = builder.mErrorLineNumber;
    }

    @Nullable
    public String getErrorExceptionClassName() {
        return this.mErrorExceptionClassName;
    }

    @Nullable
    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    @Nullable
    public String getErrorStackTrace() {
        return this.mErrorStackTrace;
    }

    @Nullable
    public String getErrorFileName() {
        return this.mErrorFileName;
    }

    @Nullable
    public String getErrorClassName() {
        return this.mErrorClassName;
    }

    @Nullable
    public String getErrorMethodName() {
        return this.mErrorMethodName;
    }

    @Nullable
    public Integer getErrorLineNumber() {
        return this.mErrorLineNumber;
    }

    public String toString() {
        return super.toString() + "ErrorEvent\nErrorExceptionClassName: " + getErrorExceptionClassName() + "\nErrorMessage: " + getErrorMessage() + "\nErrorStackTrace: " + getErrorStackTrace() + "\nErrorFileName: " + getErrorFileName() + "\nErrorClassName: " + getErrorClassName() + "\nErrorMethodName: " + getErrorMethodName() + "\nErrorLineNumber: " + getErrorLineNumber() + "\n";
    }
}
