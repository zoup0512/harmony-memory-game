package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

public class VastExtensionXmlManager {
    public static final String TYPE = "type";
    public static final String VIDEO_VIEWABILITY_TRACKER = "MoPubViewabilityTracker";
    private final Node mExtensionNode;

    public VastExtensionXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node);
        this.mExtensionNode = node;
    }

    @Nullable
    VideoViewabilityTracker getVideoViewabilityTracker() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mExtensionNode, VIDEO_VIEWABILITY_TRACKER);
        if (firstMatchingChildNode == null) {
            return null;
        }
        VideoViewabilityTrackerXmlManager videoViewabilityTrackerXmlManager = new VideoViewabilityTrackerXmlManager(firstMatchingChildNode);
        Integer viewablePlaytimeMS = videoViewabilityTrackerXmlManager.getViewablePlaytimeMS();
        Integer percentViewable = videoViewabilityTrackerXmlManager.getPercentViewable();
        Object videoViewabilityTrackerUrl = videoViewabilityTrackerXmlManager.getVideoViewabilityTrackerUrl();
        if (viewablePlaytimeMS == null || percentViewable == null || TextUtils.isEmpty(videoViewabilityTrackerUrl)) {
            return null;
        }
        return new VideoViewabilityTracker(viewablePlaytimeMS.intValue(), percentViewable.intValue(), videoViewabilityTrackerUrl);
    }

    @Nullable
    String getType() {
        return XmlUtils.getAttributeValue(this.mExtensionNode, "type");
    }
}
