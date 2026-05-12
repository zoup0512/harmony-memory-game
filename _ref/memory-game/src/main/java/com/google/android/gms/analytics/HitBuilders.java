package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HitBuilders {

    protected static class HitBuilder<T extends HitBuilder> {
        private Map<String, String> zzcsn = new HashMap();
        ProductAction zzcso;
        Map<String, List<Product>> zzcsp = new HashMap();
        List<Promotion> zzcsq = new ArrayList();
        List<Product> zzcsr = new ArrayList();

        protected HitBuilder() {
        }

        private T zzo(String str, String str2) {
            if (str == null) {
                zzae.zzcx("HitBuilder.setIfNonNull() called with a null paramName.");
            } else if (str2 != null) {
                this.zzcsn.put(str, str2);
            }
            return this;
        }

        public T addImpression(Product product, String str) {
            if (product == null) {
                zzae.zzcx("product should be non-null");
            } else {
                Object obj;
                if (str == null) {
                    obj = "";
                }
                if (!this.zzcsp.containsKey(obj)) {
                    this.zzcsp.put(obj, new ArrayList());
                }
                ((List) this.zzcsp.get(obj)).add(product);
            }
            return this;
        }

        public T addProduct(Product product) {
            if (product == null) {
                zzae.zzcx("product should be non-null");
            } else {
                this.zzcsr.add(product);
            }
            return this;
        }

        public T addPromotion(Promotion promotion) {
            if (promotion == null) {
                zzae.zzcx("promotion should be non-null");
            } else {
                this.zzcsq.add(promotion);
            }
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> hashMap = new HashMap(this.zzcsn);
            if (this.zzcso != null) {
                hashMap.putAll(this.zzcso.build());
            }
            int i = 1;
            for (Promotion zzee : this.zzcsq) {
                hashMap.putAll(zzee.zzee(zzc.zzbi(i)));
                i++;
            }
            i = 1;
            for (Product zzee2 : this.zzcsr) {
                hashMap.putAll(zzee2.zzee(zzc.zzbg(i)));
                i++;
            }
            int i2 = 1;
            for (Entry entry : this.zzcsp.entrySet()) {
                List<Product> list = (List) entry.getValue();
                String zzbl = zzc.zzbl(i2);
                int i3 = 1;
                for (Product product : list) {
                    String valueOf = String.valueOf(zzbl);
                    String valueOf2 = String.valueOf(zzc.zzbk(i3));
                    hashMap.putAll(product.zzee(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i3++;
                }
                if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                    String valueOf3 = String.valueOf(zzbl);
                    String valueOf4 = String.valueOf("nm");
                    hashMap.put(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), (String) entry.getKey());
                }
                i2++;
            }
            return hashMap;
        }

        protected String get(String str) {
            return (String) this.zzcsn.get(str);
        }

        public final T set(String str, String str2) {
            if (str != null) {
                this.zzcsn.put(str, str2);
            } else {
                zzae.zzcx("HitBuilder.set() called with a null paramName.");
            }
            return this;
        }

        public final T setAll(Map<String, String> map) {
            if (map != null) {
                this.zzcsn.putAll(new HashMap(map));
            }
            return this;
        }

        public T setCampaignParamsFromUrl(String str) {
            Object zzez = zzao.zzez(str);
            if (!TextUtils.isEmpty(zzez)) {
                Map zzex = zzao.zzex(zzez);
                zzo("&cc", (String) zzex.get("utm_content"));
                zzo("&cm", (String) zzex.get("utm_medium"));
                zzo("&cn", (String) zzex.get("utm_campaign"));
                zzo("&cs", (String) zzex.get("utm_source"));
                zzo("&ck", (String) zzex.get("utm_term"));
                zzo("&ci", (String) zzex.get("utm_id"));
                zzo("&anid", (String) zzex.get("anid"));
                zzo("&gclid", (String) zzex.get("gclid"));
                zzo("&dclid", (String) zzex.get("dclid"));
                zzo("&aclid", (String) zzex.get("aclid"));
                zzo("&gmob_t", (String) zzex.get("gmob_t"));
            }
            return this;
        }

        public T setCustomDimension(int i, String str) {
            set(zzc.zzbc(i), str);
            return this;
        }

        public T setCustomMetric(int i, float f) {
            set(zzc.zzbe(i), Float.toString(f));
            return this;
        }

        protected T setHitType(String str) {
            set("&t", str);
            return this;
        }

        public T setNewSession() {
            set("&sc", "start");
            return this;
        }

        public T setNonInteraction(boolean z) {
            set("&ni", zzao.zzat(z));
            return this;
        }

        public T setProductAction(ProductAction productAction) {
            this.zzcso = productAction;
            return this;
        }

        public T setPromotionAction(String str) {
            this.zzcsn.put("&promoa", str);
            return this;
        }
    }

    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder> {
        public AppViewBuilder() {
            set("&t", "screenview");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }
    }

    public static class EventBuilder extends HitBuilder<EventBuilder> {
        public EventBuilder() {
            set("&t", "event");
        }

        public EventBuilder(String str, String str2) {
            this();
            setCategory(str);
            setAction(str2);
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public EventBuilder setAction(String str) {
            set("&ea", str);
            return this;
        }

        public EventBuilder setCategory(String str) {
            set("&ec", str);
            return this;
        }

        public EventBuilder setLabel(String str) {
            set("&el", str);
            return this;
        }

        public EventBuilder setValue(long j) {
            set("&ev", Long.toString(j));
            return this;
        }
    }

    @Deprecated
    public static class ItemBuilder extends HitBuilder<ItemBuilder> {
        public ItemBuilder() {
            set("&t", "item");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public ItemBuilder setCategory(String str) {
            set("&iv", str);
            return this;
        }

        public ItemBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public ItemBuilder setName(String str) {
            set("&in", str);
            return this;
        }

        public ItemBuilder setPrice(double d) {
            set("&ip", Double.toString(d));
            return this;
        }

        public ItemBuilder setQuantity(long j) {
            set("&iq", Long.toString(j));
            return this;
        }

        public ItemBuilder setSku(String str) {
            set("&ic", str);
            return this;
        }

        public ItemBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }

    @Deprecated
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder> {
        public TransactionBuilder() {
            set("&t", "transaction");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public TransactionBuilder setAffiliation(String str) {
            set("&ta", str);
            return this;
        }

        public TransactionBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public TransactionBuilder setRevenue(double d) {
            set("&tr", Double.toString(d));
            return this;
        }

        public TransactionBuilder setShipping(double d) {
            set("&ts", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTax(double d) {
            set("&tt", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }
}
