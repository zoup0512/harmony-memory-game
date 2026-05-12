package com.cmcm.adsdk.nativead;

import com.cmcm.adsdk.Const;
import com.cmcm.utils.g;
import java.util.Timer;
import java.util.TimerTask;

class TimeoutTask extends TimerTask {
    Runnable mRun;
    boolean mTimeout = false;
    Timer mTimer = null;
    String name;

    TimeoutTask(Runnable r, String name) {
        this.mRun = r;
        this.name = name;
    }

    public void run() {
        g.a(Const.TAG, this.name + " timeout, to check this load finish");
        this.mTimeout = true;
        if (this.mRun != null) {
            this.mRun.run();
        }
    }

    public void start(int time) {
        this.mTimeout = false;
        try {
            this.mTimer = new Timer();
            this.mTimer.schedule(this, (long) time);
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        try {
            if (this.mTimer != null) {
                this.mTimeout = true;
                this.mTimer.cancel();
                this.mTimer = null;
            }
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
        }
    }
}
