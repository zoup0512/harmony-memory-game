package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.util.DeviceUtils$ForceOrientation;
import com.mopub.mobileads.util.XmlUtils;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class VastXmlManager {
    private static final String AD = "Ad";
    private static final String CUSTOM_CLOSE_ICON = "MoPubCloseIcon";
    private static final String CUSTOM_CTA_TEXT = "MoPubCtaText";
    private static final String CUSTOM_FORCE_ORIENTATION = "MoPubForceOrientation";
    private static final String CUSTOM_SKIP_TEXT = "MoPubSkipText";
    private static final String ERROR = "Error";
    private static final int MAX_CTA_TEXT_LENGTH = 15;
    private static final int MAX_SKIP_TEXT_LENGTH = 8;
    private static final String MP_IMPRESSION_TRACKER = "MP_TRACKING_URL";
    private static final String ROOT_TAG = "MPMoVideoXMLDocRoot";
    private static final String ROOT_TAG_CLOSE = "</MPMoVideoXMLDocRoot>";
    private static final String ROOT_TAG_OPEN = "<MPMoVideoXMLDocRoot>";
    @Nullable
    private Document mVastDoc;

    VastXmlManager() {
    }

    void parseVastXml(@NonNull String str) {
        Preconditions.checkNotNull(str, "xmlString cannot be null");
        String str2 = ROOT_TAG_OPEN + str.replaceFirst("<\\?.*\\?>", "") + ROOT_TAG_CLOSE;
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setCoalescing(true);
        this.mVastDoc = newInstance.newDocumentBuilder().parse(new InputSource(new StringReader(str2)));
    }

    @NonNull
    List<VastAdXmlManager> getAdXmlManagers() {
        List<VastAdXmlManager> arrayList = new ArrayList();
        if (this.mVastDoc == null) {
            return arrayList;
        }
        NodeList elementsByTagName = this.mVastDoc.getElementsByTagName(AD);
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            arrayList.add(new VastAdXmlManager(elementsByTagName.item(i)));
        }
        return arrayList;
    }

    @Nullable
    VastTracker getErrorTracker() {
        if (this.mVastDoc == null) {
            return null;
        }
        Object firstMatchingStringData = XmlUtils.getFirstMatchingStringData(this.mVastDoc, ERROR);
        if (TextUtils.isEmpty(firstMatchingStringData)) {
            return null;
        }
        return new VastTracker(firstMatchingStringData);
    }

    @NonNull
    List<VastTracker> getMoPubImpressionTrackers() {
        List<String> stringDataAsList = XmlUtils.getStringDataAsList(this.mVastDoc, MP_IMPRESSION_TRACKER);
        List<VastTracker> arrayList = new ArrayList(stringDataAsList.size());
        for (String vastTracker : stringDataAsList) {
            arrayList.add(new VastTracker(vastTracker));
        }
        return arrayList;
    }

    @Nullable
    String getCustomCtaText() {
        String firstMatchingStringData = XmlUtils.getFirstMatchingStringData(this.mVastDoc, CUSTOM_CTA_TEXT);
        return (firstMatchingStringData == null || firstMatchingStringData.length() > 15) ? null : firstMatchingStringData;
    }

    @Nullable
    String getCustomSkipText() {
        String firstMatchingStringData = XmlUtils.getFirstMatchingStringData(this.mVastDoc, CUSTOM_SKIP_TEXT);
        return (firstMatchingStringData == null || firstMatchingStringData.length() > 8) ? null : firstMatchingStringData;
    }

    @Nullable
    String getCustomCloseIconUrl() {
        return XmlUtils.getFirstMatchingStringData(this.mVastDoc, CUSTOM_CLOSE_ICON);
    }

    @NonNull
    DeviceUtils$ForceOrientation getCustomForceOrientation() {
        return DeviceUtils$ForceOrientation.getForceOrientation(XmlUtils.getFirstMatchingStringData(this.mVastDoc, CUSTOM_FORCE_ORIENTATION));
    }
}
