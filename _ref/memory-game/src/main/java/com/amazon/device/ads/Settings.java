package com.amazon.device.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.amazon.device.ads.JSONUtils.JSONUtilities;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

class Settings {
    private static final String LOGTAG = Settings.class.getSimpleName();
    private static final String PREFS_NAME = "AmazonMobileAds";
    public static final String SETTING_ENABLE_WEBVIEW_PAUSE_LOGIC = "shouldPauseWebViewTimersInWebViewRelatedActivities";
    protected static final String SETTING_TESTING_ENABLED = "testingEnabled";
    protected static final String SETTING_TLS_ENABLED = "tlsEnabled";
    private static Settings instance = new Settings();
    private final ConcurrentHashMap<String, Value> cache;
    private DebugProperties debugProperties;
    private JSONUtilities jsonUtilities;
    private LinkedBlockingQueue<SettingsListener> listeners;
    private final MobileAdsLogger logger;
    private final CountDownLatch settingsLoadedLatch;
    private SharedPreferences sharedPreferences;
    private final ReentrantLock writeToSharedPreferencesLock;

    public interface SettingsListener {
        void settingsLoaded();
    }

    static final class SettingsStatics {
        static final String IU_SERVICE_LAST_CHECKIN = "amzn-ad-iu-last-checkin";
        static final String IU_SERVICE_LAST_CHECKIN_USING_SESSIONID = "amzn-ad-iu-last-checkin-using-sessionid";
        static final String VIEWABLE_JS_SETTINGS_NAME = "viewableJSSettingsNameAmazonAdSDK";
        static final String VIEWABLE_JS_VERSION_STORED = "viewableJSVersionStored";

        SettingsStatics() {
        }
    }

    class Value {
        public Class<?> clazz;
        public boolean isTransientData;
        public Object value;

        public Value(Class<?> cls, Object obj) {
            this.clazz = cls;
            this.value = obj;
        }
    }

    class TransientValue extends Value {
        public TransientValue(Class<?> cls, Object obj) {
            super(cls, obj);
            this.isTransientData = true;
        }
    }

    public Settings() {
        this(new JSONUtilities(), DebugProperties.getInstance());
    }

    Settings(JSONUtilities jSONUtilities, DebugProperties debugProperties) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.listeners = new LinkedBlockingQueue();
        this.writeToSharedPreferencesLock = new ReentrantLock();
        this.settingsLoadedLatch = new CountDownLatch(1);
        this.cache = new ConcurrentHashMap();
        this.jsonUtilities = jSONUtilities;
        this.debugProperties = debugProperties;
    }

    public static Settings getInstance() {
        return instance;
    }

    void contextReceived(Context context) {
        if (context != null) {
            beginFetch(context);
        }
    }

    void beginFetch(final Context context) {
        ThreadUtils.scheduleRunnable(new Runnable() {
            public void run() {
                Settings.this.fetchSharedPreferences(context);
            }
        });
    }

    public boolean isSettingsLoaded() {
        return this.sharedPreferences != null;
    }

    public void listenForSettings(SettingsListener settingsListener) {
        if (isSettingsLoaded()) {
            settingsListener.settingsLoaded();
            return;
        }
        try {
            this.listeners.put(settingsListener);
        } catch (InterruptedException e) {
            this.logger.e("Interrupted exception while adding listener: %s", e.getMessage());
        }
    }

    SharedPreferences getSharedPreferencesFromContext(Context context) {
        return context.getSharedPreferences(PREFS_NAME, 0);
    }

    SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    ConcurrentHashMap<String, Value> getCache() {
        return this.cache;
    }

    private void putSetting(String str, Value value) {
        if (value.value == null) {
            this.logger.w("Could not set null value for setting: %s", str);
            return;
        }
        putSettingWithNoFlush(str, value);
        if (!value.isTransientData && isSettingsLoaded()) {
            flush();
        }
    }

    private void putSettingWithNoFlush(String str, Value value) {
        if (value.value == null) {
            this.logger.w("Could not set null value for setting: %s", str);
            return;
        }
        this.cache.put(str, value);
    }

    void readSharedPreferencesIntoCache(SharedPreferences sharedPreferences) {
        cacheAdditionalEntries(sharedPreferences.getAll());
    }

    void cacheAdditionalEntries(Map<String, ?> map) {
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (!(str == null || this.cache.containsKey(str))) {
                Object value = entry.getValue();
                if (value != null) {
                    this.cache.put(str, new Value(value.getClass(), value));
                } else {
                    this.logger.w("Could not cache null value for SharedPreferences setting: %s", str);
                }
            }
        }
    }

    private void writeCacheToSharedPreferences() {
        writeCacheToSharedPreferences(this.sharedPreferences);
    }

    void writeCacheToSharedPreferences(final SharedPreferences sharedPreferences) {
        ThreadUtils.scheduleRunnable(new Runnable() {
            public void run() {
                Settings.this.writeToSharedPreferencesLock.lock();
                Editor edit = sharedPreferences.edit();
                edit.clear();
                for (Entry entry : Settings.this.cache.entrySet()) {
                    Value value = (Value) entry.getValue();
                    if (!value.isTransientData) {
                        if (value.clazz == String.class) {
                            edit.putString((String) entry.getKey(), (String) value.value);
                        } else if (value.clazz == Long.class) {
                            edit.putLong((String) entry.getKey(), ((Long) value.value).longValue());
                        } else if (value.clazz == Integer.class) {
                            edit.putInt((String) entry.getKey(), ((Integer) value.value).intValue());
                        } else if (value.clazz == Boolean.class) {
                            edit.putBoolean((String) entry.getKey(), ((Boolean) value.value).booleanValue());
                        }
                    }
                }
                Settings.this.commit(edit);
                Settings.this.writeToSharedPreferencesLock.unlock();
            }
        });
    }

    void flush() {
        writeCacheToSharedPreferences();
    }

    public boolean containsKey(String str) {
        return this.cache.containsKey(str);
    }

    public JSONObject getJSONObject(String str, JSONObject jSONObject) {
        Value value = (Value) this.cache.get(str);
        if (value == null) {
            return jSONObject;
        }
        JSONObject jSONObjectFromString = this.jsonUtilities.getJSONObjectFromString((String) value.value);
        if (jSONObjectFromString != null) {
            return jSONObjectFromString;
        }
        return jSONObject;
    }

    public void putJSONObject(String str, JSONObject jSONObject) {
        putSetting(str, new Value(String.class, jSONObject.toString()));
    }

    public void putJSONObjectWithNoFlush(String str, JSONObject jSONObject) {
        putSettingWithNoFlush(str, new Value(String.class, jSONObject.toString()));
    }

    public void putTransientJSONObject(String str, JSONObject jSONObject) {
        putSettingWithNoFlush(str, new TransientValue(String.class, jSONObject.toString()));
    }

    public JSONObject getWrittenJSONObject(String str, JSONObject jSONObject) {
        if (!isSettingsLoaded()) {
            return jSONObject;
        }
        return this.jsonUtilities.getJSONObjectFromString(this.sharedPreferences.getString(str, jSONObject.toString()));
    }

    public String getString(String str, String str2) {
        Value value = (Value) this.cache.get(str);
        return value == null ? str2 : (String) value.value;
    }

    void putString(String str, String str2) {
        putSetting(str, new Value(String.class, str2));
    }

    void putStringWithNoFlush(String str, String str2) {
        putSettingWithNoFlush(str, new Value(String.class, str2));
    }

    void putTransientString(String str, String str2) {
        putSettingWithNoFlush(str, new TransientValue(String.class, str2));
    }

    public String getWrittenString(String str, String str2) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getString(str, str2);
        }
        return str2;
    }

    public int getInt(String str, int i) {
        Value value = (Value) this.cache.get(str);
        return value == null ? i : ((Integer) value.value).intValue();
    }

    void putInt(String str, int i) {
        putSetting(str, new Value(Integer.class, Integer.valueOf(i)));
    }

    void putIntWithNoFlush(String str, int i) {
        putSettingWithNoFlush(str, new Value(Integer.class, Integer.valueOf(i)));
    }

    void putTransientInt(String str, int i) {
        putSettingWithNoFlush(str, new TransientValue(Integer.class, Integer.valueOf(i)));
    }

    public int getWrittenInt(String str, int i) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getInt(str, i);
        }
        return i;
    }

    public long getLong(String str, long j) {
        Value value = (Value) this.cache.get(str);
        return value == null ? j : ((Long) value.value).longValue();
    }

    void putLong(String str, long j) {
        putSetting(str, new Value(Long.class, Long.valueOf(j)));
    }

    void putLongWithNoFlush(String str, long j) {
        putSettingWithNoFlush(str, new Value(Long.class, Long.valueOf(j)));
    }

    void putTransientLong(String str, long j) {
        putSettingWithNoFlush(str, new TransientValue(Long.class, Long.valueOf(j)));
    }

    public long getWrittenLong(String str, long j) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getLong(str, j);
        }
        return j;
    }

    public boolean getBoolean(String str, boolean z) {
        Boolean bool = getBoolean(str, null);
        return bool == null ? z : bool.booleanValue();
    }

    public Boolean getBoolean(String str, Boolean bool) {
        Value value = (Value) this.cache.get(str);
        return value == null ? bool : (Boolean) value.value;
    }

    void putBoolean(String str, boolean z) {
        putSetting(str, new Value(Boolean.class, Boolean.valueOf(z)));
    }

    void putBooleanWithNoFlush(String str, boolean z) {
        putSettingWithNoFlush(str, new Value(Boolean.class, Boolean.valueOf(z)));
    }

    void putTransientBoolean(String str, boolean z) {
        putSettingWithNoFlush(str, new TransientValue(Boolean.class, Boolean.valueOf(z)));
    }

    public boolean getWrittenBoolean(String str, boolean z) {
        if (isSettingsLoaded()) {
            return this.sharedPreferences.getBoolean(str, z);
        }
        return z;
    }

    public void putTransientObject(String str, Object obj) {
        putSettingWithNoFlush(str, new TransientValue(obj.getClass(), obj));
    }

    public <T> T getObject(String str, T t, Class<T> cls) {
        Value value = (Value) this.cache.get(str);
        if (value == null || !cls.isInstance(value.value)) {
            return t;
        }
        return value.value;
    }

    void remove(String str) {
        Value value = (Value) this.cache.remove(str);
        if (value != null && !value.isTransientData && isSettingsLoaded()) {
            flush();
        }
    }

    void removeWithNoFlush(String str) {
        this.cache.remove(str);
    }

    private void commit(Editor editor) {
        editor.apply();
    }

    void notifySettingsListeners() {
        while (true) {
            SettingsListener settingsListener = (SettingsListener) this.listeners.poll();
            if (settingsListener != null) {
                settingsListener.settingsLoaded();
            } else {
                return;
            }
        }
    }

    void fetchSharedPreferences(Context context) {
        if (!isSettingsLoaded()) {
            SharedPreferences sharedPreferencesFromContext = getSharedPreferencesFromContext(context);
            readSharedPreferencesIntoCache(sharedPreferencesFromContext);
            this.sharedPreferences = sharedPreferencesFromContext;
            writeCacheToSharedPreferences(sharedPreferencesFromContext);
        }
        this.settingsLoadedLatch.countDown();
        notifySettingsListeners();
    }
}
