package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.bg;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public final class az {
    public static final Boolean a = Boolean.valueOf(false);
    public static final int b = YandexMetrica.getLibraryApiLevel();
    static final SparseArray<k> c;
    static final SparseArray<k> d;
    static final HashMap<String, String[]> e;

    static abstract class k {
        protected abstract void a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException;

        k() {
        }
    }

    private static class a extends k {
        private a() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    private static class b extends k {
        private b() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
        }
    }

    private static class c extends k {
        private c() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL(v.b);
            sQLiteDatabase.execSQL(w.b);
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    private static class d extends k {
        private d() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS reports");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sessions");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
        }
    }

    private static class e extends k {
        private e() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS GeoLocationInfo (GeoLocation BLOB )");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS startup (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.insert("GeoLocationInfo", "GeoLocation", new ContentValues());
        }
    }

    private static class f extends k {
        private f() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_id_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS GeoLocationInfo");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS api_level_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS startup");
        }
    }

    private static class g extends k {
        private g() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS api_level_info (API_LEVEL INT )");
            ContentValues contentValues = new ContentValues();
            contentValues.put("API_LEVEL", Integer.valueOf(YandexMetrica.getLibraryApiLevel()));
            sQLiteDatabase.insert("api_level_info", "API_LEVEL", contentValues);
        }
    }

    private static class h extends k {
        private h() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS GeoLocationInfo (GeoLocation BLOB )");
            sQLiteDatabase.insert("GeoLocationInfo", "GeoLocation", new ContentValues());
        }
    }

    private static class i extends k {
        private i() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS api_level_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_id_info");
        }
    }

    private static class j extends k {
        private j() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS startup (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    private static class l extends k {
        private l() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            Cursor cursor = null;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS sessions_BACKUP (");
            stringBuilder.append("id INTEGER,");
            stringBuilder.append("start_time INTEGER,");
            stringBuilder.append("connection_type INTEGER,");
            stringBuilder.append("network_type TEXT,");
            stringBuilder.append("country_code INTEGER,");
            stringBuilder.append("operator_id INTEGER,");
            stringBuilder.append("lac INTEGER,");
            stringBuilder.append("report_request_parameters TEXT );");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            CharSequence stringBuilder2 = new StringBuilder();
            stringBuilder2.append("id,");
            stringBuilder2.append("start_time,");
            stringBuilder2.append("connection_type,");
            stringBuilder2.append("network_type,");
            stringBuilder2.append("country_code,");
            stringBuilder2.append("operator_id,");
            stringBuilder2.append("lac,");
            stringBuilder2.append("report_request_parameters");
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("INSERT INTO sessions_BACKUP");
            stringBuilder3.append(" SELECT ").append(stringBuilder2);
            stringBuilder3.append(" FROM sessions;");
            sQLiteDatabase.execSQL(stringBuilder3.toString());
            sQLiteDatabase.execSQL("DROP TABLE sessions;");
            sQLiteDatabase.execSQL(w.b);
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM sessions_BACKUP", null);
                while (cursor.moveToNext()) {
                    ContentValues contentValues = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                    List<String> arrayList = new ArrayList();
                    arrayList.add("id");
                    arrayList.add("start_time");
                    arrayList.add("report_request_parameters");
                    ContentValues contentValues2 = new ContentValues(contentValues);
                    for (Entry entry : contentValues.valueSet()) {
                        if (!arrayList.contains(entry.getKey())) {
                            contentValues2.remove((String) entry.getKey());
                        }
                    }
                    for (String remove : arrayList) {
                        contentValues.remove(remove);
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("conn_type", contentValues.getAsInteger("connection_type"));
                    jSONObject.putOpt("net_type", contentValues.get("network_type"));
                    jSONObject.putOpt("operator_id", contentValues.get("operator_id"));
                    jSONObject.putOpt("lac", contentValues.get("lac"));
                    jSONObject.putOpt("country_code", contentValues.get("country_code"));
                    contentValues2.put("network_info", jSONObject.toString());
                    sQLiteDatabase.insertOrThrow("sessions", null, contentValues2);
                }
                sQLiteDatabase.execSQL("DROP TABLE sessions_BACKUP;");
                stringBuilder = new StringBuilder();
                stringBuilder.append("ALTER TABLE reports");
                stringBuilder.append(" ADD COLUMN wifi_network_info");
                stringBuilder.append(" TEXT DEFAULT ''");
                sQLiteDatabase.execSQL(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                stringBuilder.append("ALTER TABLE reports");
                stringBuilder.append(" ADD COLUMN cell_info");
                stringBuilder.append(" TEXT DEFAULT ''");
                sQLiteDatabase.execSQL(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                stringBuilder.append("ALTER TABLE reports");
                stringBuilder.append(" ADD COLUMN location_info");
                stringBuilder.append(" TEXT DEFAULT ''");
                sQLiteDatabase.execSQL(stringBuilder.toString());
            } finally {
                bg.a(cursor);
            }
        }
    }

    private static class m extends k {
        private m() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN environment");
            stringBuilder.append(" TEXT ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN user_info");
            stringBuilder.append(" TEXT ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN session_type");
            stringBuilder.append(" INTEGER DEFAULT ").append(ay.FOREGROUND.a());
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE reports");
            stringBuilder.append(" SET session_type = ");
            stringBuilder.append(ay.BACKGROUND.a());
            stringBuilder.append(" WHERE session_id");
            stringBuilder.append(" = -2");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE sessions");
            stringBuilder.append(" ADD COLUMN server_time_offset");
            stringBuilder.append(" INTEGER ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE sessions");
            stringBuilder.append(" ADD COLUMN type");
            stringBuilder.append(" INTEGER DEFAULT ").append(ay.FOREGROUND.a());
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE sessions");
            stringBuilder.append(" SET type = ");
            stringBuilder.append(ay.BACKGROUND.a());
            stringBuilder.append(" WHERE id");
            stringBuilder.append(" = -2");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class n extends k {
        private static final String a = ("CREATE TABLE IF NOT EXISTS reports (id INTEGER PRIMARY KEY,name TEXT,value TEXT,number INTEGER,type INTEGER,time INTEGER,session_id TEXT,wifi_network_info TEXT DEFAULT '',cell_info TEXT DEFAULT '',location_info TEXT DEFAULT '',error_environment TEXT,user_info TEXT,session_type INTEGER DEFAULT " + ay.FOREGROUND.a() + ",app_environment TEXT DEFAULT '{}',app_environment_revision INTEGER DEFAULT 0 )");

        private n() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            Cursor cursor = null;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN app_environment");
            stringBuilder.append(" TEXT DEFAULT '{}'");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN app_environment_revision");
            stringBuilder.append(" INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            sQLiteDatabase.execSQL("ALTER TABLE reports RENAME TO reports_backup");
            sQLiteDatabase.execSQL(a);
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM reports_backup", null);
                while (cursor.moveToNext()) {
                    ContentValues contentValues = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                    String asString = contentValues.getAsString("environment");
                    contentValues.remove("environment");
                    contentValues.put("error_environment", asString);
                    sQLiteDatabase.insert("reports", null, contentValues);
                }
                sQLiteDatabase.execSQL("DROP TABLE reports_backup");
            } finally {
                bg.a(cursor);
            }
        }
    }

    private static class o extends k {
        private o() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN truncated");
            stringBuilder.append(" INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class p extends k {
        private p() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN connection_type");
            stringBuilder.append(" INTEGER DEFAULT 2");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN cellular_connection_type");
            stringBuilder.append(" TEXT ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class q extends k {
        private q() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN custom_type");
            stringBuilder.append(" INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class r extends k {
        private r() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE sessions");
            stringBuilder.append(" ADD COLUMN wifi_network_info");
            stringBuilder.append(" TEXT DEFAULT ''");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class s extends k {
        private s() {
        }

        protected void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE sessions");
            stringBuilder.append(" ADD COLUMN report_request_parameters");
            stringBuilder.append(" TEXT DEFAULT ''");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    interface t {
        public static final String[] a = new String[]{TransferTable.COLUMN_KEY, Param.VALUE, "type"};
    }

    public static final class u implements t {
    }

    public static final class v {
        public static final String[] a = new String[]{"id", "number", "name", Param.VALUE, "type", Model.KEY_loadtime, "session_id", "wifi_network_info", "cell_info", "location_info", "error_environment", "user_info", "session_type", "app_environment", "app_environment_revision", "truncated", "connection_type", "cellular_connection_type", "custom_type"};
        static final String b = ("CREATE TABLE IF NOT EXISTS reports (id INTEGER PRIMARY KEY,name TEXT,value TEXT,number INTEGER,type INTEGER,time INTEGER,session_id TEXT,wifi_network_info TEXT DEFAULT '',cell_info TEXT DEFAULT '',location_info TEXT DEFAULT '',error_environment TEXT,user_info TEXT,session_type INTEGER DEFAULT " + ay.FOREGROUND.a() + ",app_environment TEXT DEFAULT '{}',app_environment_revision INTEGER DEFAULT 0,truncated INTEGER DEFAULT 0,connection_type INTEGER DEFAULT 2,cellular_connection_type TEXT,custom_type INTEGER DEFAULT 0 )");
    }

    public static final class w {
        public static final String[] a = new String[]{"id", "start_time", "network_info", "report_request_parameters", "server_time_offset", "type"};
        static final String b = ("CREATE TABLE IF NOT EXISTS sessions (id INTEGER,start_time INTEGER,network_info TEXT,report_request_parameters TEXT,server_time_offset INTEGER,type INTEGER DEFAULT " + ay.FOREGROUND.a() + " )");
        public static final String c = String.format(Locale.US, "(select count(%s.%s) from %s where %s.%s = %s.%s) = 0 and %s != ?", new Object[]{"reports", "id", "reports", "reports", "session_id", "sessions", "id", "id"});
    }

    public static final class x implements t {
    }

    static {
        SparseArray sparseArray = new SparseArray();
        c = sparseArray;
        sparseArray.put(6, new r());
        c.put(7, new s());
        c.put(14, new l());
        c.put(29, new m());
        c.put(37, new n());
        c.put(39, new o());
        c.put(45, new p());
        c.put(47, new q());
        sparseArray = new SparseArray();
        d = sparseArray;
        sparseArray.put(12, new g());
        d.put(14, new h());
        d.put(29, new i());
        d.put(47, new j());
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put("reports", v.a);
        e.put("sessions", w.a);
        e.put("preferences", u.a);
    }

    public static be a() {
        return new be(new c(), new d(), c, new bg(e));
    }

    public static be b() {
        HashMap hashMap = new HashMap();
        hashMap.put("preferences", u.a);
        hashMap.put("startup", x.a);
        return new be(new e(), new f(), d, new bg(hashMap));
    }

    public static be c() {
        HashMap hashMap = new HashMap();
        hashMap.put("preferences", u.a);
        return new be(new a(), new b(), new SparseArray(), new bg(hashMap));
    }
}
