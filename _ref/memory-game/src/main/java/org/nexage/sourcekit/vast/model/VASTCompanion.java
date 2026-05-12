package org.nexage.sourcekit.vast.model;

import com.mopub.common.VisibleForTesting;
import com.mopub.mobileads.VastResourceXmlManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.nexage.sourcekit.mraid.internal.MRAIDHtmlProcessor;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.util.XmlTools;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VASTCompanion {
    private final String SUPPORTED_STATIC_TYPE_REGEX = "image/.*(?i)(gif|jpeg|png)";
    private final String TAG = "VASTCompanion";
    @VisibleForTesting
    String clickThrough;
    public int height;
    private VASTMediaFile htmlResource;
    private VASTMediaFile iFrameResource;
    private VASTMediaFile staticResource;
    private HashMap<TRACKING_EVENTS_TYPE, List<String>> trackings = new HashMap();
    public int width;

    public VASTCompanion(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        this.height = Integer.valueOf(attributes.getNamedItem("height").getNodeValue()).intValue();
        this.width = Integer.valueOf(attributes.getNamedItem("width").getNodeValue()).intValue();
        VASTLog.d("VASTCompanion", "VASTCompanion");
        NodeList childNodes = node.getChildNodes();
        if (childNodes != null) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                String nodeName = item.getNodeName();
                VASTMediaFile vASTMediaFile;
                if (nodeName.equalsIgnoreCase(VastResourceXmlManager.STATIC_RESOURCE) && this.staticResource == null) {
                    vASTMediaFile = new VASTMediaFile();
                    vASTMediaFile.setType(item.getAttributes().getNamedItem(VastResourceXmlManager.CREATIVE_TYPE).getNodeValue());
                    vASTMediaFile.setValue(XmlTools.getElementValue(item));
                    if (isMediaFileCompatible(vASTMediaFile)) {
                        this.staticResource = vASTMediaFile;
                    }
                } else if (nodeName.equalsIgnoreCase(VastResourceXmlManager.IFRAME_RESOURCE) && this.iFrameResource == null) {
                    vASTMediaFile = new VASTMediaFile();
                    vASTMediaFile.setValue(XmlTools.getElementValue(item));
                    this.iFrameResource = vASTMediaFile;
                } else if (nodeName.equalsIgnoreCase(VastResourceXmlManager.HTML_RESOURCE) && this.htmlResource == null) {
                    vASTMediaFile = new VASTMediaFile();
                    vASTMediaFile.setValue(XmlTools.getElementValue(item));
                    this.htmlResource = vASTMediaFile;
                } else if (nodeName.equalsIgnoreCase("CompanionClickThrough")) {
                    this.clickThrough = XmlTools.getElementValue(item);
                } else if (nodeName.equalsIgnoreCase("NonLinearClickThrough")) {
                    this.clickThrough = XmlTools.getElementValue(item);
                } else if (nodeName.equalsIgnoreCase("TrackingEvents")) {
                    this.trackings = new HashMap();
                    NodeList childNodes2 = item.getChildNodes();
                    if (childNodes2 != null) {
                        for (int i2 = 0; i2 < childNodes2.getLength(); i2++) {
                            item = childNodes2.item(i2);
                            if (item.getNodeName().equalsIgnoreCase("Tracking")) {
                                String nodeValue = item.getAttributes().getNamedItem("event").getNodeValue();
                                try {
                                    TRACKING_EVENTS_TYPE valueOf = TRACKING_EVENTS_TYPE.valueOf(nodeValue);
                                    String elementValue = XmlTools.getElementValue(item);
                                    if (this.trackings.containsKey(valueOf)) {
                                        ((List) this.trackings.get(valueOf)).add(elementValue);
                                    } else {
                                        List arrayList = new ArrayList();
                                        arrayList.add(elementValue);
                                        this.trackings.put(valueOf, arrayList);
                                    }
                                } catch (IllegalArgumentException e) {
                                    VASTLog.w("VASTCompanion", "Event:" + nodeValue + " is not valid. Skipping it.");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isMediaFileCompatible(VASTMediaFile vASTMediaFile) {
        return vASTMediaFile.getType().matches("image/.*(?i)(gif|jpeg|png)");
    }

    public String getStaticRecourceUrl() {
        if (this.staticResource != null) {
            return this.staticResource.getValue();
        }
        return null;
    }

    public String getHtml(int i, int i2, float f) {
        String processRawHtml;
        float f2;
        float f3;
        int round;
        int round2;
        String format;
        if (this.htmlResource != null) {
            processRawHtml = MRAIDHtmlProcessor.processRawHtml(this.htmlResource.getValue());
            f2 = ((float) i) / ((float) i2);
            f3 = ((float) this.width) / ((float) this.height);
            if (!Float.isNaN(f3)) {
                if (f3 <= f2) {
                    i = Math.round(((float) i2) * f3);
                } else {
                    i2 = Math.round(((float) i) / f3);
                }
            }
            round = Math.round(((float) i) / f);
            round2 = Math.round(((float) i2) / f);
            format = String.format("body, p {margin:0; padding:0} img {max-width:%dpx; max-height:%dpx} #appnext-interstitial {min-width:%dpx; min-height:%dpx;}img[width='%d'][height='%d'] {width: %dpx; height: %dpx} .appodeal-outer {display: table; position: absolute; height: 100%%; width: 100%%;}.appodeal-middle {display: table-cell; vertical-align: middle;}.appodeal-inner {margin-left: auto; margin-right: auto; width: %dpx; height: %dpx;}.ad_slug_table {margin-left: auto !important; margin-right: auto !important;} #ad[align='center'] {height: %dpx;} #voxelPlayer {position: relative !important;} #lsm_mobile_ad #wrapper, #lsm_overlay {position: relative !important;}", new Object[]{Integer.valueOf(round), Integer.valueOf(round2), Integer.valueOf(round), Integer.valueOf(round2), Integer.valueOf(this.width), Integer.valueOf(this.height), Integer.valueOf(round), Integer.valueOf(round2), Integer.valueOf(round), Integer.valueOf(round2), Integer.valueOf(round2)});
            return String.format("<style type='text/css'>%s</style><div class='appodeal-outer'><div class='appodeal-middle'><div class='appodeal-inner'>%s</div></div></div>", new Object[]{format, processRawHtml});
        } else if (this.staticResource != null) {
            processRawHtml = MRAIDHtmlProcessor.processRawHtml(String.format("<a href='%s'><img width='%s' height='%s' src='%s'/></a>", new Object[]{this.clickThrough, Integer.valueOf(this.width), Integer.valueOf(this.height), getStaticRecourceUrl()}));
            f2 = ((float) i) / ((float) i2);
            f3 = ((float) this.width) / ((float) this.height);
            if (!Float.isNaN(f3)) {
                if (f3 <= f2) {
                    i = Math.round(((float) i2) * f3);
                } else {
                    i2 = Math.round(((float) i) / f3);
                }
            }
            round = Math.round(((float) i) / f);
            round2 = Math.round(((float) i2) / f);
            format = String.format("body, p {margin:0; padding:0} img {max-width:%dpx; max-height:%dpx} #appnext-interstitial {min-width:%dpx; min-height:%dpx;}img[width='%d'][height='%d'] {width: %dpx; height: %dpx} .appodeal-outer {display: table; position: absolute; height: 100%%; width: 100%%;}.appodeal-middle {display: table-cell; vertical-align: middle;}.appodeal-inner {margin-left: auto; margin-right: auto; width: %dpx; height: %dpx;}.ad_slug_table {margin-left: auto !important; margin-right: auto !important;} #ad[align='center'] {height: %dpx;} #voxelPlayer {position: relative !important;} #lsm_mobile_ad #wrapper, #lsm_overlay {position: relative !important;}", new Object[]{Integer.valueOf(round), Integer.valueOf(round2), Integer.valueOf(round), Integer.valueOf(round2), Integer.valueOf(this.width), Integer.valueOf(this.height), Integer.valueOf(round), Integer.valueOf(round2), Integer.valueOf(round), Integer.valueOf(round2), Integer.valueOf(round2)});
            return String.format("<style type='text/css'>%s</style><div class='appodeal-outer'><div class='appodeal-middle'><div class='appodeal-inner'>%s</div></div></div>", new Object[]{format, processRawHtml});
        } else if (this.iFrameResource == null) {
            return null;
        } else {
            return String.format("<html style=\"overflow: hidden\"><body style=\"overflow: hidden\"><iframe style=\"overflow: hidden\" scrolling=\"no\" frameborder=\"no\" width=\"%s\" height=\"%s\" src=\"%s\"></iframe></body></html>", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), this.iFrameResource.getValue()});
        }
    }

    public HashMap<TRACKING_EVENTS_TYPE, List<String>> getTrackings() {
        return this.trackings;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
