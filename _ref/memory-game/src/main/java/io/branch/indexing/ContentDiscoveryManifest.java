package io.branch.indexing;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.cube.memorygames.Games;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentDiscoveryManifest {
    public static final String CONTENT_DISCOVER_KEY = "cd";
    static final int DEF_MAX_DISCOVERY_REPEAT = 15;
    private static final String DISCOVERY_REPEAT_INTERVAL = "dri";
    static final int DRI_MINIMUM_THRESHOLD = 500;
    private static final String FILTERED_KEYS = "ck";
    static final String HASH_MODE_KEY = "h";
    private static final String MANIFEST_KEY = "m";
    public static final String MANIFEST_VERSION_KEY = "mv";
    private static final String MAX_DISCOVERY_REPEAT = "mdr";
    private static final String MAX_PACKET_SIZE_KEY = "mps";
    private static final String MAX_TEXT_LEN_KEY = "mtl";
    private static final String MAX_VIEW_HISTORY_LENGTH = "mhl";
    public static final String PACKAGE_NAME_KEY = "pn";
    private static final String PATH_KEY = "p";
    private static ContentDiscoveryManifest thisInstance_;
    private final String PREF_KEY = "BNC_CD_MANIFEST";
    private JSONObject cdManifestObject_;
    private JSONArray contentPaths_;
    private boolean isCDEnabled_ = false;
    private String manifestVersion_;
    private int maxPacketSize_ = 0;
    private int maxTextLen_ = 0;
    private int maxViewHistoryLength_ = 1;
    private SharedPreferences sharedPref;

    class CDPathProperties {
        private int discoveryRepeatInterval_;
        private boolean isClearText_;
        private int maxDiscoveryRepeat_ = 15;
        final JSONObject pathInfo_;

        int getDiscoveryRepeatInterval() {
            return this.discoveryRepeatInterval_;
        }

        int getMaxDiscoveryRepeatNumber() {
            return this.maxDiscoveryRepeat_;
        }

        CDPathProperties(JSONObject pathInfo) {
            this.pathInfo_ = pathInfo;
            if (pathInfo.has(ContentDiscoveryManifest.HASH_MODE_KEY)) {
                try {
                    this.isClearText_ = !pathInfo.getBoolean(ContentDiscoveryManifest.HASH_MODE_KEY);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (pathInfo.has(ContentDiscoveryManifest.DISCOVERY_REPEAT_INTERVAL)) {
                    this.discoveryRepeatInterval_ = pathInfo.getInt(ContentDiscoveryManifest.DISCOVERY_REPEAT_INTERVAL);
                }
                if (pathInfo.has(ContentDiscoveryManifest.MAX_DISCOVERY_REPEAT)) {
                    this.maxDiscoveryRepeat_ = pathInfo.getInt(ContentDiscoveryManifest.MAX_DISCOVERY_REPEAT);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }

        JSONArray getFilteredElements() {
            JSONArray elementArray = null;
            if (this.pathInfo_.has(ContentDiscoveryManifest.FILTERED_KEYS)) {
                try {
                    elementArray = this.pathInfo_.getJSONArray(ContentDiscoveryManifest.FILTERED_KEYS);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return elementArray;
        }

        boolean isClearTextRequested() {
            return this.isClearText_;
        }

        boolean isSkipContentDiscovery() {
            JSONArray filteredElements = getFilteredElements();
            return filteredElements != null && filteredElements.length() == 0;
        }
    }

    private ContentDiscoveryManifest(Context context) {
        this.sharedPref = context.getSharedPreferences("bnc_content_discovery_manifest_storage", 0);
        retrieve(context);
    }

    public static ContentDiscoveryManifest getInstance(Context context) {
        if (thisInstance_ == null) {
            thisInstance_ = new ContentDiscoveryManifest(context);
        }
        return thisInstance_;
    }

    private void persist() {
        this.sharedPref.edit().putString("BNC_CD_MANIFEST", this.cdManifestObject_.toString()).apply();
    }

    private void retrieve(Context context) {
        String jsonStr = this.sharedPref.getString("BNC_CD_MANIFEST", null);
        if (jsonStr != null) {
            try {
                this.cdManifestObject_ = new JSONObject(jsonStr);
                if (this.cdManifestObject_.has(MANIFEST_VERSION_KEY)) {
                    this.manifestVersion_ = this.cdManifestObject_.getString(MANIFEST_VERSION_KEY);
                }
                if (this.cdManifestObject_.has(MANIFEST_KEY)) {
                    this.contentPaths_ = this.cdManifestObject_.getJSONArray(MANIFEST_KEY);
                    return;
                }
                return;
            } catch (JSONException e) {
                this.cdManifestObject_ = new JSONObject();
                return;
            }
        }
        this.cdManifestObject_ = new JSONObject();
    }

    public void onBranchInitialised(JSONObject branchInitResp) {
        if (branchInitResp.has(CONTENT_DISCOVER_KEY)) {
            this.isCDEnabled_ = true;
            try {
                JSONObject cdObj = branchInitResp.getJSONObject(CONTENT_DISCOVER_KEY);
                if (cdObj.has(MANIFEST_VERSION_KEY)) {
                    this.manifestVersion_ = cdObj.getString(MANIFEST_VERSION_KEY);
                }
                if (cdObj.has(MAX_VIEW_HISTORY_LENGTH)) {
                    this.maxViewHistoryLength_ = cdObj.getInt(MAX_VIEW_HISTORY_LENGTH);
                }
                if (cdObj.has(MANIFEST_KEY)) {
                    this.contentPaths_ = cdObj.getJSONArray(MANIFEST_KEY);
                }
                if (cdObj.has(MAX_TEXT_LEN_KEY)) {
                    int maxTextLength = cdObj.getInt(MAX_TEXT_LEN_KEY);
                    if (maxTextLength > 0) {
                        this.maxTextLen_ = maxTextLength;
                    }
                }
                if (cdObj.has(MAX_PACKET_SIZE_KEY)) {
                    this.maxPacketSize_ = cdObj.getInt(MAX_PACKET_SIZE_KEY);
                }
                this.cdManifestObject_.put(MANIFEST_VERSION_KEY, this.manifestVersion_);
                this.cdManifestObject_.put(MANIFEST_KEY, this.contentPaths_);
                persist();
                return;
            } catch (JSONException e) {
                return;
            }
        }
        this.isCDEnabled_ = false;
    }

    CDPathProperties getCDPathProperties(Activity activity) {
        if (this.contentPaths_ == null) {
            return null;
        }
        String viewPath = "/" + activity.getClass().getSimpleName();
        int i = 0;
        while (i < this.contentPaths_.length()) {
            try {
                JSONObject pathObj = this.contentPaths_.getJSONObject(i);
                if (pathObj.has(PATH_KEY) && pathObj.getString(PATH_KEY).equals(viewPath)) {
                    return new CDPathProperties(pathObj);
                }
                i++;
            } catch (JSONException e) {
                return null;
            }
        }
        return null;
    }

    boolean isCDEnabled() {
        return this.isCDEnabled_;
    }

    int getMaxTextLen() {
        return this.maxTextLen_;
    }

    int getMaxPacketSize() {
        return this.maxPacketSize_;
    }

    int getMaxViewHistorySize() {
        return this.maxViewHistoryLength_;
    }

    public String getManifestVersion() {
        if (TextUtils.isEmpty(this.manifestVersion_)) {
            return Games.SMART_PROMO_GAME_ID;
        }
        return this.manifestVersion_;
    }
}
