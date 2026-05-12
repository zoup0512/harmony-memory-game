package com.google.android.gms.tagmanager;

import android.content.Context;
import com.applovin.sdk.AppLovinEventParameters;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzdm extends zzdj {
    private static final String ID = zzaf.UNIVERSAL_ANALYTICS.toString();
    private static final String ayD = zzag.ACCOUNT.toString();
    private static final String ayE = zzag.ANALYTICS_PASS_THROUGH.toString();
    private static final String ayF = zzag.ENABLE_ECOMMERCE.toString();
    private static final String ayG = zzag.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String ayH = zzag.ECOMMERCE_MACRO_DATA.toString();
    private static final String ayI = zzag.ANALYTICS_FIELDS.toString();
    private static final String ayJ = zzag.TRACK_TRANSACTION.toString();
    private static final String ayK = zzag.TRANSACTION_DATALAYER_MAP.toString();
    private static final String ayL = zzag.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> ayM = Arrays.asList(new String[]{ProductAction.ACTION_DETAIL, "checkout", ProductAction.ACTION_CHECKOUT_OPTION, "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND});
    private static final Pattern ayN = Pattern.compile("dimension(\\d+)");
    private static final Pattern ayO = Pattern.compile("metric(\\d+)");
    private static Map<String, String> ayP;
    private static Map<String, String> ayQ;
    private final DataLayer auG;
    private final Set<String> ayR;
    private final zzdi ayS;

    public zzdm(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzdi(context));
    }

    zzdm(Context context, DataLayer dataLayer, zzdi com_google_android_gms_tagmanager_zzdi) {
        super(ID, new String[0]);
        this.auG = dataLayer;
        this.ayS = com_google_android_gms_tagmanager_zzdi;
        this.ayR = new HashSet();
        this.ayR.add("");
        this.ayR.add(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.ayR.add("false");
    }

    private void zza(Tracker tracker, Map<String, zza> map) {
        String zzpn = zzpn("transactionId");
        if (zzpn == null) {
            zzbn.e("Cannot find transactionId in data layer.");
            return;
        }
        List<Map> linkedList = new LinkedList();
        try {
            Map zzm = zzm((zza) map.get(ayI));
            zzm.put("&t", "transaction");
            for (Entry entry : zzbh(map).entrySet()) {
                zze(zzm, (String) entry.getValue(), zzpn((String) entry.getKey()));
            }
            linkedList.add(zzm);
            List<Map> zzpo = zzpo("transactionProducts");
            if (zzpo != null) {
                for (Map map2 : zzpo) {
                    if (map2.get("name") == null) {
                        zzbn.e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map zzm2 = zzm((zza) map.get(ayI));
                    zzm2.put("&t", "item");
                    zzm2.put("&ti", zzpn);
                    for (Entry entry2 : zzbi(map).entrySet()) {
                        zze(zzm2, (String) entry2.getValue(), (String) map2.get(entry2.getKey()));
                    }
                    linkedList.add(zzm2);
                }
            }
            for (Map map22 : linkedList) {
                tracker.send(map22);
            }
        } catch (Throwable e) {
            zzbn.zzb("Unable to send transaction", e);
        }
    }

    private Double zzat(Object obj) {
        String str;
        String valueOf;
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                str = "Cannot convert the object to Double: ";
                valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            str = "Cannot convert the object to Double: ";
            valueOf = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    private Integer zzau(Object obj) {
        String str;
        String valueOf;
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                str = "Cannot convert the object to Integer: ";
                valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            str = "Cannot convert the object to Integer: ";
            valueOf = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    private void zzb(Tracker tracker, Map<String, zza> map) {
        Object obj;
        Map map2;
        String str;
        HitBuilders$ScreenViewBuilder hitBuilders$ScreenViewBuilder = new HitBuilders$ScreenViewBuilder();
        Map zzm = zzm((zza) map.get(ayI));
        hitBuilders$ScreenViewBuilder.setAll(zzm);
        if (zzj(map, ayG)) {
            obj = this.auG.get("ecommerce");
            map2 = obj instanceof Map ? (Map) obj : null;
        } else {
            obj = zzdl.zzl((zza) map.get(ayH));
            map2 = obj instanceof Map ? (Map) obj : null;
        }
        if (map2 != null) {
            Map map3;
            List<Map> list;
            String str2 = (String) zzm.get("&cu");
            if (str2 == null) {
                str2 = (String) map2.get("currencyCode");
            }
            if (str2 != null) {
                hitBuilders$ScreenViewBuilder.set("&cu", str2);
            }
            obj = map2.get("impressions");
            if (obj instanceof List) {
                for (Map map4 : (List) obj) {
                    try {
                        hitBuilders$ScreenViewBuilder.addImpression(zzbg(map4), (String) map4.get("list"));
                    } catch (RuntimeException e) {
                        str = "Failed to extract a product from DataLayer. ";
                        str2 = String.valueOf(e.getMessage());
                        zzbn.e(str2.length() != 0 ? str.concat(str2) : new String(str));
                    }
                }
            }
            List list2 = map2.containsKey("promoClick") ? (List) ((Map) map2.get("promoClick")).get("promotions") : map2.containsKey("promoView") ? (List) ((Map) map2.get("promoView")).get("promotions") : null;
            if (r0 != null) {
                for (Map map42 : r0) {
                    try {
                        hitBuilders$ScreenViewBuilder.addPromotion(zzbf(map42));
                    } catch (RuntimeException e2) {
                        str = "Failed to extract a promotion from DataLayer. ";
                        str2 = String.valueOf(e2.getMessage());
                        zzbn.e(str2.length() != 0 ? str.concat(str2) : new String(str));
                    }
                }
                if (map2.containsKey("promoClick")) {
                    hitBuilders$ScreenViewBuilder.set("&promoa", "click");
                    obj = null;
                    if (obj != null) {
                        for (String str22 : ayM) {
                            if (map2.containsKey(str22)) {
                                map3 = (Map) map2.get(str22);
                                list = (List) map3.get("products");
                                if (list != null) {
                                    for (Map map22 : list) {
                                        try {
                                            hitBuilders$ScreenViewBuilder.addProduct(zzbg(map22));
                                        } catch (RuntimeException e3) {
                                            str = "Failed to extract a product from DataLayer. ";
                                            String valueOf = String.valueOf(e3.getMessage());
                                            zzbn.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                                        }
                                    }
                                }
                                try {
                                    hitBuilders$ScreenViewBuilder.setProductAction(map3.containsKey("actionField") ? zzh(str22, (Map) map3.get("actionField")) : new ProductAction(str22));
                                } catch (RuntimeException e22) {
                                    String str3 = "Failed to extract a product action from DataLayer. ";
                                    str22 = String.valueOf(e22.getMessage());
                                    zzbn.e(str22.length() != 0 ? str3.concat(str22) : new String(str3));
                                }
                            }
                        }
                    }
                } else {
                    hitBuilders$ScreenViewBuilder.set("&promoa", "view");
                }
            }
            int i = 1;
            if (obj != null) {
                for (String str222 : ayM) {
                    if (map22.containsKey(str222)) {
                        map3 = (Map) map22.get(str222);
                        list = (List) map3.get("products");
                        if (list != null) {
                            while (r4.hasNext()) {
                                hitBuilders$ScreenViewBuilder.addProduct(zzbg(map22));
                            }
                        }
                        if (map3.containsKey("actionField")) {
                        }
                        hitBuilders$ScreenViewBuilder.setProductAction(map3.containsKey("actionField") ? zzh(str222, (Map) map3.get("actionField")) : new ProductAction(str222));
                    }
                }
            }
        }
        tracker.send(hitBuilders$ScreenViewBuilder.build());
    }

    private Promotion zzbf(Map<String, String> map) {
        Promotion promotion = new Promotion();
        String str = (String) map.get("id");
        if (str != null) {
            promotion.setId(String.valueOf(str));
        }
        str = (String) map.get("name");
        if (str != null) {
            promotion.setName(String.valueOf(str));
        }
        str = (String) map.get("creative");
        if (str != null) {
            promotion.setCreative(String.valueOf(str));
        }
        str = (String) map.get("position");
        if (str != null) {
            promotion.setPosition(String.valueOf(str));
        }
        return promotion;
    }

    private Product zzbg(Map<String, Object> map) {
        String str;
        Product product = new Product();
        Object obj = map.get("id");
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        obj = map.get("name");
        if (obj != null) {
            product.setName(String.valueOf(obj));
        }
        obj = map.get("brand");
        if (obj != null) {
            product.setBrand(String.valueOf(obj));
        }
        obj = map.get("category");
        if (obj != null) {
            product.setCategory(String.valueOf(obj));
        }
        obj = map.get("variant");
        if (obj != null) {
            product.setVariant(String.valueOf(obj));
        }
        obj = map.get(Param.COUPON);
        if (obj != null) {
            product.setCouponCode(String.valueOf(obj));
        }
        obj = map.get("position");
        if (obj != null) {
            product.setPosition(zzau(obj).intValue());
        }
        obj = map.get(Param.PRICE);
        if (obj != null) {
            product.setPrice(zzat(obj).doubleValue());
        }
        obj = map.get(Param.QUANTITY);
        if (obj != null) {
            product.setQuantity(zzau(obj).intValue());
        }
        for (String str2 : map.keySet()) {
            String str22;
            Matcher matcher = ayN.matcher(str22);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str22)));
                } catch (NumberFormatException e) {
                    str = "illegal number in custom dimension value: ";
                    str22 = String.valueOf(str22);
                    zzbn.zzcx(str22.length() != 0 ? str.concat(str22) : new String(str));
                }
            } else {
                matcher = ayO.matcher(str22);
                if (matcher.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher.group(1)), zzau(map.get(str22)).intValue());
                    } catch (NumberFormatException e2) {
                        str = "illegal number in custom metric value: ";
                        str22 = String.valueOf(str22);
                        zzbn.zzcx(str22.length() != 0 ? str.concat(str22) : new String(str));
                    }
                }
            }
        }
        return product;
    }

    private Map<String, String> zzbh(Map<String, zza> map) {
        zza com_google_android_gms_internal_zzai_zza = (zza) map.get(ayK);
        if (com_google_android_gms_internal_zzai_zza != null) {
            return zzc(com_google_android_gms_internal_zzai_zza);
        }
        if (ayP == null) {
            Map hashMap = new HashMap();
            hashMap.put("transactionId", "&ti");
            hashMap.put("transactionAffiliation", "&ta");
            hashMap.put("transactionTax", "&tt");
            hashMap.put("transactionShipping", "&ts");
            hashMap.put("transactionTotal", "&tr");
            hashMap.put("transactionCurrency", "&cu");
            ayP = hashMap;
        }
        return ayP;
    }

    private Map<String, String> zzbi(Map<String, zza> map) {
        zza com_google_android_gms_internal_zzai_zza = (zza) map.get(ayL);
        if (com_google_android_gms_internal_zzai_zza != null) {
            return zzc(com_google_android_gms_internal_zzai_zza);
        }
        if (ayQ == null) {
            Map hashMap = new HashMap();
            hashMap.put("name", "&in");
            hashMap.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, "&ic");
            hashMap.put("category", "&iv");
            hashMap.put(Param.PRICE, "&ip");
            hashMap.put(Param.QUANTITY, "&iq");
            hashMap.put("currency", "&cu");
            ayQ = hashMap;
        }
        return ayQ;
    }

    private Map<String, String> zzc(zza com_google_android_gms_internal_zzai_zza) {
        Object zzl = zzdl.zzl(com_google_android_gms_internal_zzai_zza);
        if (!(zzl instanceof Map)) {
            return null;
        }
        Map map = (Map) zzl;
        Map<String, String> linkedHashMap = new LinkedHashMap();
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private void zze(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private ProductAction zzh(String str, Map<String, Object> map) {
        ProductAction productAction = new ProductAction(str);
        Object obj = map.get("id");
        if (obj != null) {
            productAction.setTransactionId(String.valueOf(obj));
        }
        obj = map.get("affiliation");
        if (obj != null) {
            productAction.setTransactionAffiliation(String.valueOf(obj));
        }
        obj = map.get(Param.COUPON);
        if (obj != null) {
            productAction.setTransactionCouponCode(String.valueOf(obj));
        }
        obj = map.get("list");
        if (obj != null) {
            productAction.setProductActionList(String.valueOf(obj));
        }
        obj = map.get("option");
        if (obj != null) {
            productAction.setCheckoutOptions(String.valueOf(obj));
        }
        obj = map.get("revenue");
        if (obj != null) {
            productAction.setTransactionRevenue(zzat(obj).doubleValue());
        }
        obj = map.get(Param.TAX);
        if (obj != null) {
            productAction.setTransactionTax(zzat(obj).doubleValue());
        }
        obj = map.get(Param.SHIPPING);
        if (obj != null) {
            productAction.setTransactionShipping(zzat(obj).doubleValue());
        }
        obj = map.get("step");
        if (obj != null) {
            productAction.setCheckoutStep(zzau(obj).intValue());
        }
        return productAction;
    }

    private boolean zzj(Map<String, zza> map, String str) {
        zza com_google_android_gms_internal_zzai_zza = (zza) map.get(str);
        return com_google_android_gms_internal_zzai_zza == null ? false : zzdl.zzk(com_google_android_gms_internal_zzai_zza).booleanValue();
    }

    private Map<String, String> zzm(zza com_google_android_gms_internal_zzai_zza) {
        if (com_google_android_gms_internal_zzai_zza == null) {
            return new HashMap();
        }
        Map<String, String> zzc = zzc(com_google_android_gms_internal_zzai_zza);
        if (zzc == null) {
            return new HashMap();
        }
        String str = (String) zzc.get("&aip");
        if (str != null && this.ayR.contains(str.toLowerCase())) {
            zzc.remove("&aip");
        }
        return zzc;
    }

    private String zzpn(String str) {
        Object obj = this.auG.get(str);
        return obj == null ? null : obj.toString();
    }

    private List<Map<String, String>> zzpo(String str) {
        Object obj = this.auG.get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                if (!(obj2 instanceof Map)) {
                    throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
                }
            }
            return (List) obj;
        }
        throw new IllegalArgumentException("transactionProducts should be of type List.");
    }

    public /* bridge */ /* synthetic */ zza zzav(Map map) {
        return super.zzav(map);
    }

    public void zzax(Map<String, zza> map) {
        Tracker zzpf = this.ayS.zzpf("_GTM_DEFAULT_TRACKER_");
        zzpf.enableAdvertisingIdCollection(zzj(map, "collect_adid"));
        if (zzj(map, ayF)) {
            zzb(zzpf, map);
        } else if (zzj(map, ayE)) {
            zzpf.send(zzm((zza) map.get(ayI)));
        } else if (zzj(map, ayJ)) {
            zza(zzpf, map);
        } else {
            zzbn.zzcx("Ignoring unknown tag.");
        }
    }

    public /* bridge */ /* synthetic */ boolean zzcag() {
        return super.zzcag();
    }

    public /* bridge */ /* synthetic */ String zzcbp() {
        return super.zzcbp();
    }

    public /* bridge */ /* synthetic */ Set zzcbq() {
        return super.zzcbq();
    }
}
