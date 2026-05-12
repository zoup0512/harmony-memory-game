package com.yandex.metrica;

import android.content.ContentValues;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.ResultReceiver;
import com.cube.memorygames.Games;
import com.yandex.metrica.impl.ax;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.y;
import java.util.Map;

public class CounterConfiguration implements Parcelable {
    public static final Creator<CounterConfiguration> CREATOR = new Creator<CounterConfiguration>() {
        public /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new CounterConfiguration[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new CounterConfiguration(parcel);
        }
    };
    private ContentValues a;
    private ResultReceiver b;

    public enum a {
        UNDEFINED(-1),
        FALSE(0),
        TRUE(1);
        
        public final int d;

        private a(int i) {
            this.d = i;
        }

        public static a a(int i) {
            switch (i) {
                case -1:
                    return UNDEFINED;
                case 0:
                    return FALSE;
                case 1:
                    return TRUE;
                default:
                    return UNDEFINED;
            }
        }
    }

    public CounterConfiguration(CounterConfiguration other) {
        this.b = null;
        this.a = new ContentValues();
        this.a.putAll(other.a);
        this.b = other.b;
    }

    public CounterConfiguration() {
        this.b = null;
        this.a = new ContentValues();
        this.a.put("CFG_DISPATCH_PERIOD", Integer.valueOf(90));
        this.a.put("CFG_MAX_REPORTS_COUNT", Integer.valueOf(7));
        this.a.put("CFG_SESSION_TIMEOUT", Integer.valueOf(10));
        this.a.put("CFG_REPORTS", Boolean.valueOf(true));
        this.a.put("CFG_REPORTS_CRASHES", Boolean.valueOf(true));
        this.a.put("CFG_REPORTS_NATIVE_CRASHES", Boolean.valueOf(true));
        this.a.put("CFG_REPORT_LOCATION", Boolean.valueOf(true));
        this.a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(a.FALSE.d));
        this.a.putNull("CFG_HOST_URL");
        this.a.putNull("CFG_MANUAL_LOCATION");
        this.a.putNull("CFG_APP_VERSION");
        this.a.putNull("CFG_APP_VERSION_CODE");
        this.a.putNull("CFG_API_KEY");
        this.a.putNull("CFG_PACKAGE_NAME");
        this.a.putNull("CFG_UUID");
        this.a.putNull("CFG_DEVICE_ID");
        this.a.putNull("CFG_DEVICE_SIZE_TYPE");
        this.a.putNull("CFG_CLIDS");
        this.a.put("CFG_MAIN_REPORTER", Boolean.valueOf(true));
        this.a.put("CFG_IS_LOG_ENABLED", Boolean.valueOf(false));
        this.a.put("CFG_APP_FRAMEWORK", ax.c());
    }

    public void a(e eVar) {
        Object obj;
        Object obj2 = 1;
        if (eVar.getSessionTimeout() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            c(eVar.getSessionTimeout().intValue());
        }
        if (eVar.getLocation() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a(eVar.getLocation());
        }
        if (eVar.isTrackLocationEnabled() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            c(eVar.isTrackLocationEnabled().booleanValue());
        }
        if (eVar.isCollectInstalledApps() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            d(eVar.isCollectInstalledApps().booleanValue());
        }
        if (eVar.isReportCrashEnabled() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a(eVar.isReportCrashEnabled().booleanValue());
        }
        if (eVar.isReportNativeCrashEnabled() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            b(eVar.isReportNativeCrashEnabled().booleanValue());
        }
        if (eVar.d() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a(eVar.d());
        }
        if (eVar.h() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a(eVar.h().intValue());
        }
        if (eVar.g() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            b(eVar.g().intValue());
        }
        if (!be.a(eVar.getAppVersion())) {
            h(eVar.getAppVersion());
        }
        if (eVar.c() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            d(eVar.c().intValue());
        }
        if (eVar.b() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            g(eVar.b());
        }
        if (eVar.j() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            f(eVar.j().booleanValue());
        }
        if (eVar.e() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a(eVar.e());
        }
        if (eVar.f() == null) {
            obj2 = null;
        }
        if (obj2 != null) {
            i(eVar.f());
        }
    }

    public void a(ResultReceiver resultReceiver) {
        this.b = resultReceiver;
    }

    public ResultReceiver a() {
        return this.b;
    }

    public void a(int i) {
        this.a.put("CFG_DISPATCH_PERIOD", Integer.valueOf(i));
    }

    public int b() {
        return this.a.getAsInteger("CFG_DISPATCH_PERIOD").intValue();
    }

    public void b(int i) {
        ContentValues contentValues = this.a;
        String str = "CFG_MAX_REPORTS_COUNT";
        if (i <= 0) {
            i = Integer.MAX_VALUE;
        }
        contentValues.put(str, Integer.valueOf(i));
    }

    public int c() {
        return this.a.getAsInteger("CFG_MAX_REPORTS_COUNT").intValue();
    }

    public void c(int i) {
        this.a.put("CFG_SESSION_TIMEOUT", Integer.valueOf(Math.max(10, i)));
    }

    public int d() {
        return this.a.getAsInteger("CFG_SESSION_TIMEOUT").intValue();
    }

    public void a(a aVar) {
        this.a.put("CFG_DEVICE_SIZE_TYPE", aVar == null ? null : aVar.a());
    }

    public a e() {
        return a.a(this.a.getAsString("CFG_DEVICE_SIZE_TYPE"));
    }

    public void a(String str) {
        bg.b(str);
        this.a.put("CFG_API_KEY", str);
    }

    public void b(String str) {
        this.a.put("CFG_API_KEY", str);
    }

    public void c(String str) {
        this.a.put("CFG_PACKAGE_NAME", str);
    }

    public String f() {
        return this.a.getAsString("CFG_PACKAGE_NAME");
    }

    public void d(String str) {
        this.a.put("CFG_UUID", str);
    }

    public String g() {
        return this.a.getAsString("CFG_UUID");
    }

    public void e(String str) {
        this.a.put("CFG_DEVICE_ID", str);
    }

    public String h() {
        return this.a.getAsString("CFG_DEVICE_ID");
    }

    public void f(String str) {
        this.a.put("CFG_POSSIBLE_DEVICE_ID", str);
    }

    public String i() {
        return this.a.getAsString("CFG_POSSIBLE_DEVICE_ID");
    }

    public String j() {
        return this.a.getAsString("CFG_API_KEY");
    }

    public void a(boolean z) {
        this.a.put("CFG_REPORTS_CRASHES", Boolean.valueOf(z));
    }

    public boolean k() {
        return this.a.getAsBoolean("CFG_REPORTS_CRASHES").booleanValue();
    }

    public void b(boolean z) {
        this.a.put("CFG_REPORTS_NATIVE_CRASHES", Boolean.valueOf(z));
    }

    public boolean l() {
        return this.a.getAsBoolean("CFG_REPORTS_NATIVE_CRASHES").booleanValue();
    }

    public void c(boolean z) {
        this.a.put("CFG_REPORT_LOCATION", Boolean.valueOf(z));
    }

    public boolean m() {
        return this.a.getAsBoolean("CFG_REPORT_LOCATION").booleanValue();
    }

    public void g(String str) {
        bg.a(str, "Custom Host URL");
        this.a.put("CFG_HOST_URL", str);
    }

    public String n() {
        return this.a.getAsString("CFG_HOST_URL");
    }

    public void h(String str) {
        this.a.put("CFG_APP_VERSION", str);
    }

    public String o() {
        return this.a.getAsString("CFG_APP_VERSION");
    }

    public void d(int i) {
        this.a.put("CFG_APP_VERSION_CODE", String.valueOf(i));
    }

    public String p() {
        return this.a.getAsString("CFG_APP_VERSION_CODE");
    }

    public void d(boolean z) {
        this.a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(z ? a.TRUE.d : a.FALSE.d));
    }

    public boolean q() {
        switch (r()) {
            case TRUE:
                return true;
            default:
                return false;
        }
    }

    public a r() {
        return a(this.a.get("CFG_COLLECT_INSTALLED_APPS"));
    }

    public void a(Location location) {
        this.a.put("CFG_MANUAL_LOCATION", y.b(location));
    }

    public void e(boolean z) {
        this.a.put("CFG_IS_LOG_ENABLED", Boolean.valueOf(z));
    }

    public boolean s() {
        if (this.a.containsKey("CFG_IS_LOG_ENABLED")) {
            return this.a.getAsBoolean("CFG_IS_LOG_ENABLED").booleanValue();
        }
        return false;
    }

    public Location t() {
        Location a = y.a(this.a.getAsByteArray("CFG_MANUAL_LOCATION"));
        if (a != null || !z()) {
            return a;
        }
        Double x = x();
        Double y = y();
        a = new Location("NONE");
        a.setLatitude(x.doubleValue());
        a.setLongitude(y.doubleValue());
        a.setTime(System.currentTimeMillis());
        return a;
    }

    public void a(Map<String, String> map) {
        this.a.put("CFG_CLIDS", bg.b((Map) map));
    }

    public Map<String, String> u() {
        return bg.d(this.a.getAsString("CFG_CLIDS"));
    }

    public String v() {
        return this.a.getAsString("CFG_DISTRIBUTION_REFERRER");
    }

    public void i(String str) {
        this.a.put("CFG_DISTRIBUTION_REFERRER", str);
    }

    public boolean w() {
        Boolean asBoolean = this.a.getAsBoolean("CFG_AUTO_PRELOAD_INFO_DETECTION");
        return asBoolean != null ? asBoolean.booleanValue() : false;
    }

    public void f(boolean z) {
        this.a.put("CFG_AUTO_PRELOAD_INFO_DETECTION", Boolean.valueOf(z));
    }

    Double x() {
        return this.a.getAsDouble("CFG_LOCATION_LATITUDE");
    }

    Double y() {
        return this.a.getAsDouble("CFG_LOCATION_LONGITUDE");
    }

    boolean z() {
        int i;
        int i2 = 1;
        if (this.a.getAsDouble("CFG_LOCATION_LONGITUDE") != null) {
            i = 1;
        } else {
            i = 0;
        }
        if (this.a.getAsDouble("CFG_LOCATION_LATITUDE") == null) {
            i2 = 0;
        }
        return i & i2;
    }

    public CounterConfiguration(Parcel srcObj) {
        this.b = null;
        a(srcObj);
    }

    public int describeContents() {
        return 0;
    }

    public void a(Parcel parcel) {
        this.a = (ContentValues) parcel.readParcelable(ContentValues.class.getClass().getClassLoader());
        this.b = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClass().getClassLoader());
    }

    public void writeToParcel(Parcel destObj, int flags) {
        destObj.writeParcelable(this.a, 0);
        ResultReceiver resultReceiver = this.b;
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        destObj.writeParcelable(resultReceiver, 0);
    }

    public void g(boolean z) {
        this.a.put("CFG_MAIN_REPORTER", Boolean.valueOf(z));
    }

    public boolean A() {
        Boolean asBoolean = this.a.getAsBoolean("CFG_MAIN_REPORTER");
        return asBoolean != null ? asBoolean.booleanValue() : true;
    }

    public boolean B() {
        return bg.c(j());
    }

    public String C() {
        return this.a.getAsString("CFG_APP_FRAMEWORK");
    }

    public Bundle D() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("COUNTER_CFG_OBJ", this);
        return bundle;
    }

    public void a(CounterConfiguration counterConfiguration) {
        if (this.a.containsKey("CFG_DISPATCH_PERIOD")) {
            this.a.put("CFG_DISPATCH_PERIOD", counterConfiguration.a.getAsInteger("CFG_DISPATCH_PERIOD"));
        }
        if (this.a.containsKey("CFG_SESSION_TIMEOUT")) {
            this.a.put("CFG_SESSION_TIMEOUT", counterConfiguration.a.getAsInteger("CFG_SESSION_TIMEOUT"));
        }
        if (this.a.containsKey("CFG_MAX_REPORTS_COUNT")) {
            this.a.put("CFG_MAX_REPORTS_COUNT", counterConfiguration.a.getAsInteger("CFG_MAX_REPORTS_COUNT"));
        }
        if (this.a.containsKey("CFG_REPORTS_CRASHES")) {
            this.a.put("CFG_REPORTS_CRASHES", counterConfiguration.a.getAsBoolean("CFG_REPORTS_CRASHES"));
        }
        if (this.a.containsKey("CFG_REPORTS_NATIVE_CRASHES")) {
            this.a.put("CFG_REPORTS_NATIVE_CRASHES", counterConfiguration.a.getAsBoolean("CFG_REPORTS_NATIVE_CRASHES"));
        }
        if (this.a.containsKey("CFG_REPORT_LOCATION")) {
            this.a.put("CFG_REPORT_LOCATION", counterConfiguration.a.getAsBoolean("CFG_REPORT_LOCATION"));
        }
        if (this.a.containsKey("CFG_MANUAL_LOCATION")) {
            this.a.put("CFG_MANUAL_LOCATION", counterConfiguration.a.getAsByteArray("CFG_MANUAL_LOCATION"));
        }
        if (this.a.containsKey("CFG_COLLECT_INSTALLED_APPS")) {
            this.a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(a(counterConfiguration.a.get("CFG_COLLECT_INSTALLED_APPS")).d));
        }
        if (this.a.containsKey("CFG_DEVICE_SIZE_TYPE")) {
            this.a.put("CFG_DEVICE_SIZE_TYPE", counterConfiguration.a.getAsString("CFG_DEVICE_SIZE_TYPE"));
        }
        if (this.a.containsKey("CFG_IS_LOG_ENABLED")) {
            this.a.put("CFG_IS_LOG_ENABLED", counterConfiguration.a.getAsBoolean("CFG_IS_LOG_ENABLED"));
        }
        if (this.a.containsKey("CFG_CLIDS")) {
            this.a.put("CFG_CLIDS", counterConfiguration.a.getAsString("CFG_CLIDS"));
        }
        if (this.a.containsKey("CFG_AUTO_PRELOAD_INFO_DETECTION")) {
            this.a.put("CFG_AUTO_PRELOAD_INFO_DETECTION", counterConfiguration.a.getAsBoolean("CFG_AUTO_PRELOAD_INFO_DETECTION"));
        }
    }

    public void a(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getInt("CFG_DISPATCH_PERIOD") != 0) {
                a(bundle.getInt("CFG_DISPATCH_PERIOD"));
            }
            if (bundle.getInt("CFG_SESSION_TIMEOUT") != 0) {
                c(bundle.getInt("CFG_SESSION_TIMEOUT"));
            }
            if (bundle.getInt("CFG_MAX_REPORTS_COUNT") != 0) {
                b(bundle.getInt("CFG_MAX_REPORTS_COUNT"));
            }
            if (bundle.getString("CFG_API_KEY") != null && !Games.SMART_PROMO_GAME_ID.equals(bundle.getString("CFG_API_KEY"))) {
                a(bundle.getString("CFG_API_KEY"));
            }
        }
    }

    private static a a(Object obj) {
        if (obj != null) {
            if (obj instanceof Integer) {
                return a.a(((Integer) obj).intValue());
            }
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue() ? a.TRUE : a.FALSE;
            }
        }
        return a.UNDEFINED;
    }
}
