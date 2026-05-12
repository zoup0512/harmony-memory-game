package com.mopub.common.event;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;

public class EventDispatcher {
    private final Iterable<EventRecorder> mEventRecorders;
    private final Callback mHandlerCallback = new Callback() {
        public boolean handleMessage(Message message) {
            if (message.obj instanceof BaseEvent) {
                for (EventRecorder record : EventDispatcher.this.mEventRecorders) {
                    record.record((BaseEvent) message.obj);
                }
            } else {
                MoPubLog.d("EventDispatcher received non-BaseEvent message type.");
            }
            return true;
        }
    };
    private final Looper mLooper;
    private final Handler mMessageHandler = new Handler(this.mLooper, this.mHandlerCallback);

    @VisibleForTesting
    EventDispatcher(Iterable<EventRecorder> iterable, Looper looper) {
        this.mEventRecorders = iterable;
        this.mLooper = looper;
    }

    public void dispatch(BaseEvent baseEvent) {
        Message.obtain(this.mMessageHandler, 0, baseEvent).sendToTarget();
    }

    @VisibleForTesting
    Iterable<EventRecorder> getEventRecorders() {
        return this.mEventRecorders;
    }

    @VisibleForTesting
    Callback getHandlerCallback() {
        return this.mHandlerCallback;
    }
}
