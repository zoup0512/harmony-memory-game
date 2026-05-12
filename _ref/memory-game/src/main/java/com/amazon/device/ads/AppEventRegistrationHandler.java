package com.amazon.device.ads;

import com.amazon.device.ads.FileOutputHandler.WriteMethod;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class AppEventRegistrationHandler {
    protected static final String APP_EVENTS_FILE = "AppEventsJsonFile";
    protected static final long APP_EVENTS_FILE_MAX_SIZE = 1048576;
    protected static final String APP_EVENT_NAME_KEY = "evtName";
    protected static final String APP_EVENT_TIMESTAMP_KEY = "ts";
    protected static final String INSTALL_REFERRER_EVENT_NAME = "INSTALL_REFERRER";
    private static final String LOGTAG = AppEventRegistrationHandler.class.getSimpleName();
    protected static AppEventRegistrationHandler instance = new AppEventRegistrationHandler(MobileAdsInfoStore.getInstance(), new DefaultFileHandlerFactory());
    protected final Object appEventsFileLock;
    protected final Set<String> eventsSent;
    private final FileHandlerFactory fileHandlerFactory;
    private FileInputHandler fileInputHandler;
    private FileOutputHandler fileOutputHandler;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    protected final Set<String> newEventsToSave;

    protected AppEventRegistrationHandler(MobileAdsInfoStore mobileAdsInfoStore, FileHandlerFactory fileHandlerFactory) {
        this.infoStore = mobileAdsInfoStore;
        this.fileHandlerFactory = fileHandlerFactory;
        this.appEventsFileLock = new Object();
        this.newEventsToSave = Collections.synchronizedSet(new HashSet());
        this.eventsSent = Collections.synchronizedSet(new HashSet());
    }

    public static AppEventRegistrationHandler getInstance() {
        return instance;
    }

    public void addEventToAppEventsCacheFile(final AppEvent appEvent) {
        ThreadUtils.scheduleRunnable(new Runnable() {
            public void run() {
                AppEventRegistrationHandler.this.appendAppEventToFile(appEvent);
                if (appEvent.getEventName().equals(AppEventRegistrationHandler.INSTALL_REFERRER_EVENT_NAME) && AppEventRegistrationHandler.this.infoStore.getRegistrationInfo().isRegisteredWithSIS()) {
                    AppEventRegistrationHandler.this.infoStore.getSISRegistration().registerEvents();
                }
            }
        });
    }

    private boolean createFileOutputHandlerIfNeeded() {
        if (this.fileOutputHandler == null) {
            File filesDir = this.infoStore.getFilesDir();
            if (filesDir == null) {
                this.logger.e("No files directory has been set.");
                return false;
            }
            this.fileOutputHandler = this.fileHandlerFactory.createFileOutputHandler(filesDir, APP_EVENTS_FILE);
        }
        if (this.fileOutputHandler != null) {
            return true;
        }
        return false;
    }

    private boolean createFileInputHandlerIfNeeded() {
        if (this.fileInputHandler == null) {
            File filesDir = this.infoStore.getFilesDir();
            if (filesDir == null) {
                this.logger.e("No files directory has been set.");
                return false;
            }
            this.fileInputHandler = this.fileHandlerFactory.createFileInputHandler(filesDir, APP_EVENTS_FILE);
        }
        if (this.fileInputHandler != null) {
            return true;
        }
        return false;
    }

    protected void appendAppEventToFile(AppEvent appEvent) {
        if (createFileOutputHandlerIfNeeded()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(APP_EVENT_NAME_KEY, appEvent.getEventName());
                jSONObject.put(APP_EVENT_TIMESTAMP_KEY, appEvent.getTimestamp());
                for (Entry entry : appEvent.getPropertyEntries()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
                this.newEventsToSave.add(jSONObject.toString());
                synchronized (this.appEventsFileLock) {
                    String str = jSONObject.toString() + "\n";
                    if (this.fileOutputHandler.getFileLength() + ((long) str.length()) > APP_EVENTS_FILE_MAX_SIZE) {
                        this.logger.w("Couldn't write the application event %s to the cache file. Maximum size limit reached.", appEvent.toString());
                        return;
                    }
                    if (this.fileOutputHandler.open(WriteMethod.APPEND)) {
                        try {
                            this.fileOutputHandler.write(str);
                            this.logger.d("Added the application event %s to the cache file.", appEvent.toString());
                        } catch (IOException e) {
                            this.logger.w("Couldn't write the application event %s to the file.", appEvent.toString());
                        }
                    }
                    this.fileOutputHandler.close();
                    return;
                }
            } catch (JSONException e2) {
                this.logger.w("Internal error while persisting the application event %s.", appEvent.toString());
                return;
            }
        }
        this.logger.e("Error creating file output handler.");
    }

    public JSONArray getAppEventsJSONArray() {
        if (createFileInputHandlerIfNeeded()) {
            synchronized (this.appEventsFileLock) {
                if (!this.fileInputHandler.doesFileExist()) {
                    return null;
                } else if (this.fileInputHandler.open()) {
                    JSONArray jSONArray = new JSONArray();
                    while (true) {
                        String readLine = this.fileInputHandler.readLine();
                        if (readLine == null) {
                            break;
                        }
                        JSONObject jSONObjectFromString = JSONUtils.getJSONObjectFromString(readLine);
                        if (jSONObjectFromString == null) {
                            onAppEventsRegistered();
                            this.fileInputHandler.close();
                            return null;
                        }
                        jSONArray.put(jSONObjectFromString);
                        this.eventsSent.add(jSONObjectFromString.toString());
                    }
                    this.fileInputHandler.close();
                    if (jSONArray.length() > 0) {
                        return jSONArray;
                    }
                    return null;
                } else {
                    this.logger.e("App Events File could not be opened.");
                    return null;
                }
            }
        }
        this.logger.e("Error creating file input handler.");
        return null;
    }

    public void onAppEventsRegistered() {
        if (createFileOutputHandlerIfNeeded()) {
            synchronized (this.appEventsFileLock) {
                this.newEventsToSave.removeAll(this.eventsSent);
                if (this.newEventsToSave.isEmpty()) {
                    this.infoStore.getApplicationContext().deleteFile(APP_EVENTS_FILE);
                    this.eventsSent.clear();
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    synchronized (this.newEventsToSave) {
                        for (String append : this.newEventsToSave) {
                            stringBuilder.append(append).append("\n");
                        }
                    }
                    if (this.fileOutputHandler.open(WriteMethod.APPEND)) {
                        try {
                            this.fileOutputHandler.write(stringBuilder.toString());
                            this.newEventsToSave.clear();
                            this.eventsSent.clear();
                        } catch (IOException e) {
                            this.logger.w("Couldn't write the application event(s) to the file.");
                        }
                    }
                    this.fileOutputHandler.close();
                }
            }
            return;
        }
        this.logger.e("Error creating file output handler.");
    }
}
