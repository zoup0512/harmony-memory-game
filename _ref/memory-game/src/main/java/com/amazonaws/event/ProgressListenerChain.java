package com.amazonaws.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProgressListenerChain implements ProgressListener {
    private static final Log log = LogFactory.getLog(ProgressListenerChain.class);
    private final List<ProgressListener> listeners;
    private final ProgressEventFilter progressEventFilter;

    public interface ProgressEventFilter {
        ProgressEvent filter(ProgressEvent progressEvent);
    }

    public ProgressListenerChain(ProgressListener... listeners) {
        this(null, listeners);
    }

    public ProgressListenerChain(ProgressEventFilter progressEventFilter, ProgressListener... listeners) {
        this.listeners = new CopyOnWriteArrayList();
        if (listeners == null) {
            throw new IllegalArgumentException("Progress Listeners cannot be null.");
        }
        for (ProgressListener listener : listeners) {
            addProgressListener(listener);
        }
        this.progressEventFilter = progressEventFilter;
    }

    public synchronized void addProgressListener(ProgressListener listener) {
        if (listener != null) {
            this.listeners.add(listener);
        }
    }

    public synchronized void removeProgressListener(ProgressListener listener) {
        if (listener != null) {
            this.listeners.remove(listener);
        }
    }

    protected List<ProgressListener> getListeners() {
        return this.listeners;
    }

    public void progressChanged(ProgressEvent progressEvent) {
        ProgressEvent filteredEvent = progressEvent;
        if (this.progressEventFilter != null) {
            filteredEvent = this.progressEventFilter.filter(progressEvent);
            if (filteredEvent == null) {
                return;
            }
        }
        for (ProgressListener listener : this.listeners) {
            try {
                listener.progressChanged(filteredEvent);
            } catch (RuntimeException e) {
                log.warn("Couldn't update progress listener", e);
            }
        }
    }
}
