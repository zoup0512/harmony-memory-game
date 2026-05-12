package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Node;

class VastCompanionAdXmlManager {
    private static final String AD_SLOT_ID = "adSlotID";
    private static final String COMPANION_CLICK_THROUGH = "CompanionClickThrough";
    private static final String COMPANION_CLICK_TRACKING = "CompanionClickTracking";
    private static final String CREATIVE_VIEW = "creativeView";
    private static final String EVENT = "event";
    private static final String HEIGHT = "height";
    private static final String TRACKING_EVENTS = "TrackingEvents";
    private static final String VIDEO_TRACKER = "Tracking";
    private static final String WIDTH = "width";
    @NonNull
    private final Node mCompanionNode;
    @NonNull
    private final VastResourceXmlManager mResourceXmlManager;

    VastCompanionAdXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node, "companionNode cannot be null");
        this.mCompanionNode = node;
        this.mResourceXmlManager = new VastResourceXmlManager(node);
    }

    @Nullable
    Integer getWidth() {
        return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "width");
    }

    @Nullable
    Integer getHeight() {
        return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "height");
    }

    @Nullable
    String getAdSlotId() {
        return XmlUtils.getAttributeValue(this.mCompanionNode, AD_SLOT_ID);
    }

    @NonNull
    VastResourceXmlManager getResourceXmlManager() {
        return this.mResourceXmlManager;
    }

    @Nullable
    String getClickThroughUrl() {
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, COMPANION_CLICK_THROUGH));
    }

    @NonNull
    List<VastTracker> getClickTrackers() {
        List<VastTracker> arrayList = new ArrayList();
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(this.mCompanionNode, COMPANION_CLICK_TRACKING);
        if (matchingChildNodes == null) {
            return arrayList;
        }
        for (Node nodeValue : matchingChildNodes) {
            Object nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (!TextUtils.isEmpty(nodeValue2)) {
                arrayList.add(new VastTracker(nodeValue2));
            }
        }
        return arrayList;
    }

    @NonNull
    List<VastTracker> getCompanionCreativeViewTrackers() {
        List<VastTracker> arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, TRACKING_EVENTS);
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node firstMatchingChildNode2 : XmlUtils.getMatchingChildNodes(firstMatchingChildNode2, VIDEO_TRACKER, "event", Collections.singletonList(CREATIVE_VIEW))) {
            arrayList.add(new VastTracker(XmlUtils.getNodeValue(firstMatchingChildNode2)));
        }
        return arrayList;
    }

    boolean hasResources() {
        return (TextUtils.isEmpty(this.mResourceXmlManager.getStaticResource()) && TextUtils.isEmpty(this.mResourceXmlManager.getHTMLResource()) && TextUtils.isEmpty(this.mResourceXmlManager.getIFrameResource())) ? false : true;
    }
}
