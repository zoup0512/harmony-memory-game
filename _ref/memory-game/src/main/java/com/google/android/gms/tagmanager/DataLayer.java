package com.google.android.gms.tagmanager;

import com.yalantis.ucrop.util.FileUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    static final String[] avk = "gtm.lifetime".toString().split("\\.");
    private static final Pattern avl = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<zzb, Integer> avm;
    private final Map<String, Object> avn;
    private final ReentrantLock avo;
    private final LinkedList<Map<String, Object>> avp;
    private final zzc avq;
    private final CountDownLatch avr;

    interface zzc {

        public interface zza {
            void zzaf(List<zza> list);
        }

        void zza(zza com_google_android_gms_tagmanager_DataLayer_zzc_zza);

        void zza(List<zza> list, long j);

        void zzoo(String str);
    }

    static final class zza {
        public final String zzaxp;
        public final Object zzcnn;

        zza(String str, Object obj) {
            this.zzaxp = str;
            this.zzcnn = obj;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_tagmanager_DataLayer_zza = (zza) obj;
            return this.zzaxp.equals(com_google_android_gms_tagmanager_DataLayer_zza.zzaxp) && this.zzcnn.equals(com_google_android_gms_tagmanager_DataLayer_zza.zzcnn);
        }

        public int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.zzaxp.hashCode()), Integer.valueOf(this.zzcnn.hashCode())});
        }

        public String toString() {
            String str = this.zzaxp;
            String valueOf = String.valueOf(this.zzcnn.toString());
            return new StringBuilder((String.valueOf(str).length() + 13) + String.valueOf(valueOf).length()).append("Key: ").append(str).append(" value: ").append(valueOf).toString();
        }
    }

    interface zzb {
        void zzaw(Map<String, Object> map);
    }

    DataLayer() {
        this(new zzc() {
            public void zza(zza com_google_android_gms_tagmanager_DataLayer_zzc_zza) {
                com_google_android_gms_tagmanager_DataLayer_zzc_zza.zzaf(new ArrayList());
            }

            public void zza(List<zza> list, long j) {
            }

            public void zzoo(String str) {
            }
        });
    }

    DataLayer(zzc com_google_android_gms_tagmanager_DataLayer_zzc) {
        this.avq = com_google_android_gms_tagmanager_DataLayer_zzc;
        this.avm = new ConcurrentHashMap();
        this.avn = new HashMap();
        this.avo = new ReentrantLock();
        this.avp = new LinkedList();
        this.avr = new CountDownLatch(1);
        zzcax();
    }

    public static List<Object> listOf(Object... objArr) {
        List<Object> arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static Map<String, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        Map<String, Object> hashMap = new HashMap();
        int i = 0;
        while (i < objArr.length) {
            if (objArr[i] instanceof String) {
                hashMap.put((String) objArr[i], objArr[i + 1]);
                i += 2;
            } else {
                String valueOf = String.valueOf(objArr[i]);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 21).append("key is not a string: ").append(valueOf).toString());
            }
        }
        return hashMap;
    }

    private void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Entry entry : map.entrySet()) {
            String str2 = str.length() == 0 ? "" : FileUtils.HIDDEN_PREFIX;
            String str3 = (String) entry.getKey();
            str3 = new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(str).append(str2).append(str3).toString();
            if (entry.getValue() instanceof Map) {
                zza((Map) entry.getValue(), str3, collection);
            } else if (!str3.equals("gtm.lifetime")) {
                collection.add(new zza(str3, entry.getValue()));
            }
        }
    }

    private void zzay(Map<String, Object> map) {
        this.avo.lock();
        try {
            this.avp.offer(map);
            if (this.avo.getHoldCount() == 1) {
                zzcay();
            }
            zzaz(map);
        } finally {
            this.avo.unlock();
        }
    }

    private void zzaz(Map<String, Object> map) {
        Long zzba = zzba(map);
        if (zzba != null) {
            List zzbc = zzbc(map);
            zzbc.remove("gtm.lifetime");
            this.avq.zza(zzbc, zzba.longValue());
        }
    }

    private Long zzba(Map<String, Object> map) {
        Object zzbb = zzbb(map);
        return zzbb == null ? null : zzon(zzbb.toString());
    }

    private Object zzbb(Map<String, Object> map) {
        String[] strArr = avk;
        int length = strArr.length;
        int i = 0;
        Object obj = map;
        while (i < length) {
            Object obj2 = strArr[i];
            if (!(obj instanceof Map)) {
                return null;
            }
            i++;
            obj = ((Map) obj).get(obj2);
        }
        return obj;
    }

    private List<zza> zzbc(Map<String, Object> map) {
        Object arrayList = new ArrayList();
        zza(map, "", arrayList);
        return arrayList;
    }

    private void zzbd(Map<String, Object> map) {
        synchronized (this.avn) {
            for (String str : map.keySet()) {
                zzd(zzo(str, map.get(str)), this.avn);
            }
        }
        zzbe(map);
    }

    private void zzbe(Map<String, Object> map) {
        for (zzb zzaw : this.avm.keySet()) {
            zzaw.zzaw(map);
        }
    }

    private void zzcax() {
        this.avq.zza(new zza(this) {
            final /* synthetic */ DataLayer avs;

            {
                this.avs = r1;
            }

            public void zzaf(List<zza> list) {
                for (zza com_google_android_gms_tagmanager_DataLayer_zza : list) {
                    this.avs.zzay(this.avs.zzo(com_google_android_gms_tagmanager_DataLayer_zza.zzaxp, com_google_android_gms_tagmanager_DataLayer_zza.zzcnn));
                }
                this.avs.avr.countDown();
            }
        });
    }

    private void zzcay() {
        int i = 0;
        while (true) {
            Map map = (Map) this.avp.poll();
            if (map != null) {
                zzbd(map);
                int i2 = i + 1;
                if (i2 > 500) {
                    this.avp.clear();
                    throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                }
                i = i2;
            } else {
                return;
            }
        }
    }

    static Long zzon(String str) {
        String str2;
        String valueOf;
        Matcher matcher = avl.matcher(str);
        if (matcher.matches()) {
            long parseLong;
            try {
                parseLong = Long.parseLong(matcher.group(1));
            } catch (NumberFormatException e) {
                str2 = "illegal number in _lifetime value: ";
                valueOf = String.valueOf(str);
                zzbn.zzcx(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                parseLong = 0;
            }
            if (parseLong <= 0) {
                str2 = "non-positive _lifetime: ";
                valueOf = String.valueOf(str);
                zzbn.zzcw(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                return null;
            }
            valueOf = matcher.group(2);
            if (valueOf.length() == 0) {
                return Long.valueOf(parseLong);
            }
            switch (valueOf.charAt(0)) {
                case 'd':
                    return Long.valueOf((((parseLong * 1000) * 60) * 60) * 24);
                case 'h':
                    return Long.valueOf(((parseLong * 1000) * 60) * 60);
                case 'm':
                    return Long.valueOf((parseLong * 1000) * 60);
                case 's':
                    return Long.valueOf(parseLong * 1000);
                default:
                    str2 = "unknown units in _lifetime: ";
                    valueOf = String.valueOf(str);
                    zzbn.zzcx(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    return null;
            }
        }
        str2 = "unknown _lifetime: ";
        valueOf = String.valueOf(str);
        zzbn.zzcw(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        return null;
    }

    public Object get(String str) {
        synchronized (this.avn) {
            Map map = this.avn;
            String[] split = str.split("\\.");
            int length = split.length;
            Object obj = map;
            int i = 0;
            while (i < length) {
                Object obj2 = split[i];
                if (obj instanceof Map) {
                    obj2 = ((Map) obj).get(obj2);
                    if (obj2 == null) {
                        return null;
                    }
                    i++;
                    obj = obj2;
                } else {
                    return null;
                }
            }
            return obj;
        }
    }

    public void push(String str, Object obj) {
        push(zzo(str, obj));
    }

    public void push(Map<String, Object> map) {
        try {
            this.avr.await();
        } catch (InterruptedException e) {
            zzbn.zzcx("DataLayer.push: unexpected InterruptedException");
        }
        zzay(map);
    }

    public void pushEvent(String str, Map<String, Object> map) {
        Map hashMap = new HashMap(map);
        hashMap.put("event", str);
        push(hashMap);
    }

    public String toString() {
        String stringBuilder;
        synchronized (this.avn) {
            StringBuilder stringBuilder2 = new StringBuilder();
            for (Entry entry : this.avn.entrySet()) {
                stringBuilder2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[]{entry.getKey(), entry.getValue()}));
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    void zza(zzb com_google_android_gms_tagmanager_DataLayer_zzb) {
        this.avm.put(com_google_android_gms_tagmanager_DataLayer_zzb, Integer.valueOf(0));
    }

    void zzb(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                zzb((List) obj, (List) list2.get(i));
            } else if (obj instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap());
                }
                zzd((Map) obj, (Map) list2.get(i));
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i, obj);
            }
        }
    }

    void zzd(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                zzb((List) obj, (List) map2.get(str));
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                zzd((Map) obj, (Map) map2.get(str));
            } else {
                map2.put(str, obj);
            }
        }
    }

    Map<String, Object> zzo(String str, Object obj) {
        Map hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        Map map = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap2 = new HashMap();
            map.put(split[i], hashMap2);
            i++;
            Object obj2 = hashMap2;
        }
        map.put(split[split.length - 1], obj);
        return hashMap;
    }

    void zzom(String str) {
        push(str, null);
        this.avq.zzoo(str);
    }
}
