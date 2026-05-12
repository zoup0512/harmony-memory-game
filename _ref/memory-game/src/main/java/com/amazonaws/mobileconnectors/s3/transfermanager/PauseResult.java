package com.amazonaws.mobileconnectors.s3.transfermanager;

@Deprecated
public final class PauseResult<T> {
    private final T infoToResume;
    private final PauseStatus pauseStatus;

    public PauseResult(PauseStatus pauseStatus, T infoToResume) {
        if (pauseStatus == null) {
            throw new IllegalArgumentException();
        }
        this.pauseStatus = pauseStatus;
        this.infoToResume = infoToResume;
    }

    public PauseResult(PauseStatus pauseStatus) {
        this(pauseStatus, null);
    }

    public PauseStatus getPauseStatus() {
        return this.pauseStatus;
    }

    public T getInfoToResume() {
        return this.infoToResume;
    }
}
