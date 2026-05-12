package com.cmcm.adsdk.config;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.cmcm.utils.g;

public class ConfigChangeMonitor {
    private static final String ALARM_RECEIVER_ACTION = "com.cmcm.adsdk.ConfigMonitor_Action";
    private static final long DEFAULT_INTERVAL = 7200000;
    private static final String TAG = "ConfigChangeMonitor";
    private static ConfigChangeMonitor sInstance = null;
    private PendingIntent mAlarmPendingItent;
    private AlarmReceiver mAlarmReceiver;
    private Context mContext;
    private boolean mIsStart = false;
    private String mMid;

    private class AlarmReceiver extends BroadcastReceiver {
        private AlarmReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(ConfigChangeMonitor.ALARM_RECEIVER_ACTION)) {
                g.a(ConfigChangeMonitor.TAG, "monitor requestConfig...");
                RequestConfig.getInstance().requestConfig(true);
            }
        }
    }

    private ConfigChangeMonitor(Context context) {
        this.mContext = context;
    }

    public static synchronized ConfigChangeMonitor getInstance(Context context) {
        ConfigChangeMonitor configChangeMonitor;
        synchronized (ConfigChangeMonitor.class) {
            if (sInstance == null) {
                sInstance = new ConfigChangeMonitor(context);
            }
            configChangeMonitor = sInstance;
        }
        return configChangeMonitor;
    }

    public synchronized void start(String mid) {
        if (this.mIsStart) {
            g.a(TAG, "has start monitor, avoid repeat monitor ...");
        } else {
            g.a(TAG, "start monitor...");
            this.mMid = mid;
            this.mIsStart = true;
            try {
                if (this.mAlarmReceiver == null) {
                    this.mAlarmReceiver = new AlarmReceiver();
                }
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ALARM_RECEIVER_ACTION);
                this.mContext.getApplicationContext().registerReceiver(this.mAlarmReceiver, intentFilter);
                Intent intent = new Intent();
                intent.setAction(ALARM_RECEIVER_ACTION);
                if (this.mAlarmPendingItent == null) {
                    this.mAlarmPendingItent = PendingIntent.getBroadcast(this.mContext, 0, intent, 0);
                }
                ((AlarmManager) this.mContext.getSystemService("alarm")).setRepeating(1, System.currentTimeMillis() + DEFAULT_INTERVAL, DEFAULT_INTERVAL, this.mAlarmPendingItent);
            } catch (Exception e) {
                if (g.a) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void stop() {
        g.a(TAG, "stop monitor...");
        if (this.mContext != null) {
            try {
                AlarmManager alarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
                if (this.mAlarmPendingItent != null) {
                    alarmManager.cancel(this.mAlarmPendingItent);
                    this.mAlarmPendingItent = null;
                }
                if (this.mAlarmReceiver != null) {
                    this.mContext.getApplicationContext().unregisterReceiver(this.mAlarmReceiver);
                    this.mAlarmReceiver = null;
                }
            } catch (Exception e) {
                if (g.a) {
                    e.printStackTrace();
                }
            }
        }
        this.mIsStart = false;
    }
}
