package com.amazonaws.services.s3;

public class S3ClientOptions {
    public static final boolean DEFAULT_ACCELERATE_MODE_ENABLED = false;
    public static final boolean DEFAULT_PATH_STYLE_ACCESS = false;
    private boolean accelerateModeEnabled;
    private boolean pathStyleAccess;

    public static class Builder {
        private boolean accelerateModeEnabled;
        private boolean pathStyleAccess;

        private Builder() {
            this.pathStyleAccess = false;
            this.accelerateModeEnabled = false;
        }

        public S3ClientOptions build() {
            return new S3ClientOptions(this.pathStyleAccess, this.accelerateModeEnabled);
        }

        public Builder setPathStyleAccess(boolean pathStyleAccess) {
            this.pathStyleAccess = pathStyleAccess;
            return this;
        }

        public Builder setAccelerateModeEnabled(boolean accelerateModeEnabled) {
            this.accelerateModeEnabled = accelerateModeEnabled;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Deprecated
    public S3ClientOptions(S3ClientOptions other) {
        this.pathStyleAccess = other.pathStyleAccess;
        this.accelerateModeEnabled = other.accelerateModeEnabled;
    }

    private S3ClientOptions(boolean pathStyleAccess, boolean accelerateModeEnabled) {
        this.pathStyleAccess = pathStyleAccess;
        this.accelerateModeEnabled = accelerateModeEnabled;
    }

    public boolean isPathStyleAccess() {
        return this.pathStyleAccess;
    }

    public boolean isAccelerateModeEnabled() {
        return this.accelerateModeEnabled;
    }

    @Deprecated
    public void setPathStyleAccess(boolean pathStyleAccess) {
        this.pathStyleAccess = pathStyleAccess;
    }

    @Deprecated
    public S3ClientOptions withPathStyleAccess(boolean pathStyleAccess) {
        setPathStyleAccess(pathStyleAccess);
        return this;
    }
}
