package com.cmcm.picks.gaid;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: GooglePlayServiceConnection */
public class c implements ServiceConnection {
    boolean a = false;
    private final BlockingQueue<IBinder> b = new LinkedBlockingQueue();

    public void onServiceConnected(ComponentName name, IBinder service) {
        try {
            this.b.put(service);
        } catch (InterruptedException e) {
        }
    }

    public void onServiceDisconnected(ComponentName name) {
    }

    public IBinder a() throws InterruptedException {
        if (this.a) {
            throw new IllegalStateException();
        }
        this.a = true;
        return (IBinder) this.b.take();
    }
}
