package com.amazon.device.ads;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

class BridgeSelector {
    private static BridgeSelector instance = new BridgeSelector();
    private final AmazonViewableAdSDKBridgeFactory amazonAdSDKViewableBridgeFactory;
    private HashMap<AAXCreative, HashSet<AdSDKBridgeFactory>> bridgesForCT;
    private HashMap<String, HashSet<AdSDKBridgeFactory>> bridgesForPattern;
    private HashMap<String, HashSet<AdSDKBridgeFactory>> bridgesForResourcePattern;
    private HashMap<String, Pattern> patterns;

    public static BridgeSelector getInstance() {
        return instance;
    }

    BridgeSelector() {
        this(new AmazonViewableAdSDKBridgeFactory());
        initialize();
    }

    BridgeSelector(AmazonViewableAdSDKBridgeFactory amazonViewableAdSDKBridgeFactory) {
        this.amazonAdSDKViewableBridgeFactory = amazonViewableAdSDKBridgeFactory;
    }

    void initialize() {
        this.bridgesForCT = new HashMap();
        this.bridgesForPattern = new HashMap();
        this.patterns = new HashMap();
        this.bridgesForResourcePattern = new HashMap();
        addBridgeFactoryForScript("amazon.js", new AmazonAdSDKBridgeFactory());
        AdSDKBridgeFactory mraidAdSDKBridgeFactory = new MraidAdSDKBridgeFactory();
        addBridgeFactory(AAXCreative.MRAID1, mraidAdSDKBridgeFactory);
        addBridgeFactory(AAXCreative.MRAID2, mraidAdSDKBridgeFactory);
        addBridgeFactory(AAXCreative.INTERSTITIAL, mraidAdSDKBridgeFactory);
        addBridgeFactoryForScript("mraid.js", mraidAdSDKBridgeFactory);
    }

    public void addBridgeFactory(AAXCreative aAXCreative, AdSDKBridgeFactory adSDKBridgeFactory) {
        HashSet hashSet = (HashSet) this.bridgesForCT.get(aAXCreative);
        if (hashSet == null) {
            hashSet = new HashSet();
            this.bridgesForCT.put(aAXCreative, hashSet);
        }
        hashSet.add(adSDKBridgeFactory);
    }

    public void addBridgeFactoryForScript(String str, AdSDKBridgeFactory adSDKBridgeFactory) {
        addBridgeFactoryForHtmlScriptTag(str, adSDKBridgeFactory);
        addBridgeFactoryForResourceLoad(str, adSDKBridgeFactory);
    }

    public void addBridgeFactoryForHtmlScriptTag(String str, AdSDKBridgeFactory adSDKBridgeFactory) {
        String str2 = "<[Ss][Cc][Rr][Ii][Pp][Tt](\\s[^>]*\\s|\\s)[Ss][Rr][Cc]\\s*=\\s*[\"']%s[\"']";
        String format = String.format("<[Ss][Cc][Rr][Ii][Pp][Tt](\\s[^>]*\\s|\\s)[Ss][Rr][Cc]\\s*=\\s*[\"']%s[\"']", new Object[]{str});
        HashSet hashSet = (HashSet) this.bridgesForPattern.get(format);
        if (hashSet == null) {
            hashSet = new HashSet();
            this.bridgesForPattern.put(format, hashSet);
        }
        hashSet.add(adSDKBridgeFactory);
    }

    public void addBridgeFactoryForResourceLoad(String str, AdSDKBridgeFactory adSDKBridgeFactory) {
        String str2 = ".*\\W%s$|^%s$";
        String format = String.format(".*\\W%s$|^%s$", new Object[]{str, str});
        HashSet hashSet = (HashSet) this.bridgesForResourcePattern.get(format);
        if (hashSet == null) {
            hashSet = new HashSet();
            this.bridgesForResourcePattern.put(format, hashSet);
        }
        hashSet.add(adSDKBridgeFactory);
        hashSet.add(this.amazonAdSDKViewableBridgeFactory);
    }

    public Set<AdSDKBridgeFactory> getBridgeFactories(AAXCreative aAXCreative) {
        Set<AdSDKBridgeFactory> set = (Set) this.bridgesForCT.get(aAXCreative);
        if (set == null) {
            set = new HashSet();
        }
        set.add(this.amazonAdSDKViewableBridgeFactory);
        return set;
    }

    public Set<AdSDKBridgeFactory> getBridgeFactories(String str) {
        Set<AdSDKBridgeFactory> hashSet = new HashSet();
        for (String str2 : this.bridgesForPattern.keySet()) {
            if (getPattern(str2).matcher(str).find()) {
                hashSet.addAll((Collection) this.bridgesForPattern.get(str2));
            }
        }
        hashSet.add(this.amazonAdSDKViewableBridgeFactory);
        return hashSet;
    }

    public Set<AdSDKBridgeFactory> getBridgeFactoriesForResourceLoad(String str) {
        Set<AdSDKBridgeFactory> hashSet = new HashSet();
        for (String str2 : this.bridgesForResourcePattern.keySet()) {
            if (getPattern(str2).matcher(str).find()) {
                hashSet.addAll((Collection) this.bridgesForResourcePattern.get(str2));
            }
        }
        hashSet.add(this.amazonAdSDKViewableBridgeFactory);
        return hashSet;
    }

    private Pattern getPattern(String str) {
        Pattern pattern = (Pattern) this.patterns.get(str);
        if (pattern != null) {
            return pattern;
        }
        pattern = Pattern.compile(str);
        this.patterns.put(str, pattern);
        return pattern;
    }
}
