package com.cmcm.adsdk.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.config.ConfigResponse.AdPosInfo;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import com.cmcm.picks.gaid.a;
import com.cmcm.utils.Commons;
import com.cmcm.utils.g;
import com.facebook.appevents.AppEventsConstants;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestUFS {
    private static final String AGE21 = "1";
    private static final String AGE27 = "2";
    private static final String AGE35 = "3";
    private static final String AGE41 = "4";
    private static final String AGE_21 = "21";
    private static final String AGE_27 = "27";
    private static final String AGE_35 = "35";
    private static final String AGE_44 = "44";
    private static final String AGE_DEFAULT = "-1";
    private static final String SEX_M = "1";
    private static final String SEX_MAN = "M";
    private static final String SEX_U = "3";
    private static final String SEX_UNKNOW = "U";
    private static final String SEX_W = "2";
    private static final String SEX_WOMAN = "F";
    private static final String TAG = "RequestUFS";
    private static final int UFS_REQUESTTIME_DEFAULT = 86400000;
    private static Context mContext;
    private static RequestUFS sInstance = null;
    private static SharedPreferences sSharedPreferences;
    private final String PREFS_NAME = "cmcmadsdk_config";
    private Map<String, AdPosInfo> mConfigMap;
    private Editor mEditor;
    private String mMid;

    class UfsRunnable implements Runnable {
        private String mStrUFS;

        public UfsRunnable(String strUfs) {
            this.mStrUFS = strUfs;
        }

        public void run() {
            byte[] access$000 = RequestUFS.this.getUfsInfoFromNet(Const.CONFIG_URL_UFS, this.mStrUFS);
            if (access$000 != null && access$000.length >= 0) {
                RequestUFS.this.decryptCore(access$000);
            }
        }
    }

    public static RequestUFS getInstance(Context context) {
        if (sInstance == null) {
            mContext = context;
            sInstance = new RequestUFS(context);
        }
        return sInstance;
    }

    private RequestUFS(Context context) {
        sSharedPreferences = context.getSharedPreferences("cmcmadsdk_config", 0);
        this.mEditor = sSharedPreferences.edit();
    }

    public void setMid(String mid) {
        this.mMid = mid;
    }

    public String getUFSInfo() {
        String str = null;
        if (isRequestUFS()) {
            str = String.format("m_age:%s,m_gender:%s,", new Object[]{sSharedPreferences.getString("age", SEX_UNKNOW), sSharedPreferences.getString("gender", "-1")}) + "," + sSharedPreferences.getString("interests", "");
            g.b(TAG, "keyWords =" + str);
        }
        requestUFSInfo();
        return str;
    }

    private boolean isRequestUFS() {
        if (this.mConfigMap.isEmpty()) {
            return false;
        }
        return getInstance(mContext).isUSFConfig(this.mConfigMap);
    }

    public void requestUFSInfo() {
        Long valueOf = Long.valueOf(sSharedPreferences.getLong(Model.KEY_loadtime, 0));
        g.b(TAG, "requestufs lasttime = " + valueOf);
        if (System.currentTimeMillis() - valueOf.longValue() > 86400000) {
            this.mEditor.putLong(Model.KEY_loadtime, System.currentTimeMillis());
            if (VERSION.SDK_INT >= 9) {
                this.mEditor.apply();
            } else {
                this.mEditor.commit();
            }
            new Thread(new UfsRunnable(buildParamsUFS(this.mMid, Commons.getAndroidId(), TextUtils.isEmpty(a.c().a()) ? null : a.c().a()))).start();
        }
    }

    private byte[] getUfsInfoFromNet(String baseUrl, String param) {
        if (TextUtils.isEmpty(param)) {
            baseUrl = baseUrl + "?" + param;
        }
        g.a("RequestTask", "Get Url:" + baseUrl);
        try {
            HttpUriRequest httpGet = new HttpGet(baseUrl);
            HttpParams params = httpGet.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 15000);
            HttpConnectionParams.setSoTimeout(params, 15000);
            HttpResponse execute = new DefaultHttpClient().execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return readInputSream(execute.getEntity().getContent());
            }
        } catch (Exception e) {
            g.d(TAG, "get response error..." + e.getMessage());
        }
        return null;
    }

    private byte[] readInputSream(InputStream inStream) throws IOException {
        if (inStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private void decryptCore(byte[] result) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
            Key secretKeySpec = new SecretKeySpec(isGetSk2().getBytes(), "AES");
            byte[] bArr = new byte[16];
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) 0;
            }
            instance.init(2, secretKeySpec, new IvParameterSpec(bArr));
            String str = new String(instance.doFinal(result));
            if (!TextUtils.isEmpty(str)) {
                saveUFSInfo(new JSONObject(str));
            }
            g.a(TAG, "resultJson=" + str);
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
        }
    }

    private void saveUFSInfo(JSONObject json) {
        g.a(TAG, "saveUFSInfo=" + json);
        try {
            String string = json.getString("age");
            String string2 = json.getString("gender");
            JSONArray jSONArray = json.getJSONArray("interests");
            StringBuffer stringBuffer = new StringBuffer();
            if (jSONArray.length() != 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    stringBuffer.append("m_interests:").append(jSONArray.getString(i));
                    if (i != jSONArray.length() - 1) {
                        stringBuffer.append(",");
                    }
                }
            }
            this.mEditor.putString("interests", stringBuffer.toString());
            String str = SEX_UNKNOW;
            if (string2.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                str = SEX_MAN;
            } else if (string2.equals("2")) {
                str = SEX_WOMAN;
            } else if (string2.equals("3")) {
                str = SEX_UNKNOW;
            }
            this.mEditor.putString("gender", str);
            str = "-1";
            if (string.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                str = AGE_21;
            } else if (string.equals("2")) {
                str = AGE_27;
            } else if (string.equals("3")) {
                str = AGE_35;
            } else if (string.equals(AGE41)) {
                str = AGE_44;
            } else {
                str = "-1";
            }
            this.mEditor.putString("age", str);
            this.mEditor.putLong(Model.KEY_loadtime, System.currentTimeMillis());
            if (VERSION.SDK_INT >= 9) {
                this.mEditor.apply();
            } else {
                this.mEditor.commit();
            }
            g.a(TAG, "save success");
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
        }
    }

    private String buildParamsUFS(String mId, String androidId, String gaId) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("c=" + isGetC());
        stringBuffer.append("&gaid=" + gaId);
        stringBuffer.append("&mid=" + mId);
        stringBuffer.append("&androidid=" + androidId);
        stringBuffer.append("&sig=" + getUFSRrequest(stringBuffer.toString() + "&" + isGetSk1()));
        return stringBuffer.toString();
    }

    private String getUFSRrequest(String md5) {
        try {
            MessageDigest instance = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
            instance.update(md5.getBytes(), 0, md5.length());
            return new BigInteger(1, instance.digest()).toString(16).toString();
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public Map<String, AdPosInfo> getConfigMap() {
        return this.mConfigMap;
    }

    public boolean isOpenUFSSetting(Map<String, AdPosInfo> configMap) {
        if (this.mConfigMap != null) {
            this.mConfigMap.clear();
        }
        this.mConfigMap = configMap;
        try {
            Boolean valueOf = Boolean.valueOf(isUSFConfig(configMap));
            if (valueOf != null) {
                return valueOf.booleanValue();
            }
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
        }
        g.b(TAG, "report person infomation mainfase is exit sk1 sk2 c and mp ad  weight is < = 0");
        return false;
    }

    @Nullable
    private boolean isUSFConfig(Map<String, AdPosInfo> configMap) {
        if (TextUtils.isEmpty(isGetSk1()) || TextUtils.isEmpty(isGetSk2()) || TextUtils.isEmpty(isGetC())) {
            g.b(TAG, "report person infomation mainfase is not exit sk1 sk2 c");
            return false;
        }
        g.b(TAG, "report person infomation mainfase is exit sk1 sk2 c");
        for (String str : configMap.keySet()) {
            List list = ((AdPosInfo) configMap.get(str)).orders;
            int i = 0;
            while (i < list.size()) {
                PosBean posBean = (PosBean) list.get(i);
                if (!posBean.name.split(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)[0].equals(Const.KEY_MP) || posBean.weight.intValue() <= 0) {
                    i++;
                } else {
                    g.b(TAG, "report person infomation mainfase is exit sk1 sk2 c and mp ad  weight is > 0");
                    return true;
                }
            }
        }
        return false;
    }

    private String isGetSk1() {
        try {
            Object string = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), 128).metaData.getString("sk1");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return string;
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private String isGetC() {
        try {
            Object string = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), 128).metaData.getString("c");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return string;
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private String isGetSk2() {
        try {
            Object string = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), 128).metaData.getString("sk2");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return string;
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
