package org.nexage.sourcekit.vast.model;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.nexage.sourcekit.util.HttpTools;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.util.XmlTools;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VASTModel implements Serializable {
    public static final int ERROR_CODE_BAD_FILE = 403;
    public static final int ERROR_CODE_BAD_MODEL = 200;
    public static final int ERROR_CODE_BAD_SIZE = 203;
    public static final int ERROR_CODE_BAD_URI = 301;
    public static final int ERROR_CODE_COMPANION_NODE_NOT_FOUND = 603;
    public static final int ERROR_CODE_COMPANION_NOT_FOUND = 604;
    public static final int ERROR_CODE_ERROR_SHOWING = 405;
    public static final int ERROR_CODE_EXCEEDED_WRAPPER_LIMIT = 302;
    public static final int ERROR_CODE_NONLINEAR_NODE_NOT_FOUND = 703;
    public static final int ERROR_CODE_NONLINEAR_NOT_FOUND = 704;
    public static final int ERROR_CODE_NO_FILE = 401;
    public static final int ERROR_CODE_UNKNOWN = 900;
    public static final int ERROR_CODE_XML_PARSING = 100;
    public static final int ERROR_CODE_XML_VALIDATE = 101;
    private static String TAG = "VASTModel";
    private static final String adParametersXPATH = "//AdParameters";
    private static final String combinedExtensionPATH = "/VASTS/VAST/Ad/Wrapper/Extensions|/VASTS/VAST/Ad/InLine/Extensions";
    private static final String combinedTrackingXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/InLine/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking";
    private static final String companionsXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/CompanionAds/Companion";
    private static final String durationXPATH = "//Duration";
    private static final String errorUrlXPATH = "//Error";
    private static final String extensionPATH = "/VASTS/VAST/Ad/InLine/Extensions";
    private static final String impressionXPATH = "//Impression";
    private static final String inlineLinearTrackingXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/Linear/TrackingEvents/Tracking";
    private static final String inlineNonLinearTrackingXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking";
    private static final String linearXPATH = "//Linear";
    private static final String mediaFileXPATH = "//MediaFile";
    private static final String nonLinearXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/NonLinearAds/NonLinear";
    private static final long serialVersionUID = 4318368258447283733L;
    private static final String videoClicksXPATH = "//VideoClicks";
    private static final String wrapperExtensionPATH = "/VASTS/VAST/Ad/Wrapper/Extensions";
    private static final String wrapperLinearTrackingXPATH = "/VASTS/VAST/Ad/Wrapper/Creatives/Creative/Linear/TrackingEvents/Tracking";
    private static final String wrapperNonLinearTrackingXPATH = "/VASTS/VAST/Ad/Wrapper/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking";
    private String pickedMediaFileType = null;
    private String pickedMediaFileURL = null;
    private transient Document vastsDocument;

    public VASTModel(Document document) {
        this.vastsDocument = document;
    }

    public Document getVastsDocument() {
        return this.vastsDocument;
    }

    public HashMap<TRACKING_EVENTS_TYPE, List<String>> getTrackingUrls() {
        VASTLog.d(TAG, "getTrackingUrls");
        HashMap<TRACKING_EVENTS_TYPE, List<String>> hashMap = new HashMap();
        NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(combinedTrackingXPATH, this.vastsDocument, XPathConstants.NODESET);
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node item = nodeList.item(i);
                try {
                    TRACKING_EVENTS_TYPE valueOf = TRACKING_EVENTS_TYPE.valueOf(item.getAttributes().getNamedItem("event").getNodeValue());
                    try {
                        String elementValue = XmlTools.getElementValue(item);
                        if (hashMap.containsKey(valueOf)) {
                            ((List) hashMap.get(valueOf)).add(elementValue);
                        } else {
                            List arrayList = new ArrayList();
                            arrayList.add(elementValue);
                            hashMap.put(valueOf, arrayList);
                        }
                    } catch (Throwable e) {
                        VASTLog.e(TAG, e.getMessage(), e);
                        return null;
                    }
                } catch (IllegalArgumentException e2) {
                    VASTLog.w(TAG, "Event:" + r4 + " is not valid. Skipping it.");
                }
            }
        }
        return hashMap;
    }

    public List<VASTMediaFile> getMediaFiles() {
        VASTLog.d(TAG, "getMediaFiles");
        ArrayList arrayList = new ArrayList();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(mediaFileXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    String str;
                    BigInteger bigInteger;
                    Boolean bool;
                    VASTMediaFile vASTMediaFile = new VASTMediaFile();
                    Node item = nodeList.item(i);
                    NamedNodeMap attributes = item.getAttributes();
                    Node namedItem = attributes.getNamedItem("apiFramework");
                    if (namedItem == null) {
                        str = null;
                    } else {
                        str = namedItem.getNodeValue();
                    }
                    vASTMediaFile.setApiFramework(str);
                    Node namedItem2 = attributes.getNamedItem("bitrate");
                    if (namedItem2 == null) {
                        bigInteger = null;
                    } else {
                        bigInteger = new BigInteger(namedItem2.getNodeValue());
                    }
                    vASTMediaFile.setBitrate(bigInteger);
                    namedItem = attributes.getNamedItem("delivery");
                    if (namedItem == null) {
                        str = null;
                    } else {
                        str = namedItem.getNodeValue();
                    }
                    vASTMediaFile.setDelivery(str);
                    namedItem2 = attributes.getNamedItem("height");
                    if (namedItem2 == null) {
                        bigInteger = null;
                    } else {
                        bigInteger = new BigInteger(namedItem2.getNodeValue());
                    }
                    vASTMediaFile.setHeight(bigInteger);
                    namedItem = attributes.getNamedItem("id");
                    if (namedItem == null) {
                        str = null;
                    } else {
                        str = namedItem.getNodeValue();
                    }
                    vASTMediaFile.setId(str);
                    namedItem = attributes.getNamedItem("maintainAspectRatio");
                    if (namedItem == null) {
                        bool = null;
                    } else {
                        bool = Boolean.valueOf(namedItem.getNodeValue());
                    }
                    vASTMediaFile.setMaintainAspectRatio(bool);
                    namedItem = attributes.getNamedItem("scalable");
                    if (namedItem == null) {
                        bool = null;
                    } else {
                        bool = Boolean.valueOf(namedItem.getNodeValue());
                    }
                    vASTMediaFile.setScalable(bool);
                    namedItem = attributes.getNamedItem("type");
                    if (namedItem == null) {
                        str = null;
                    } else {
                        str = namedItem.getNodeValue();
                    }
                    vASTMediaFile.setType(str);
                    Node namedItem3 = attributes.getNamedItem("width");
                    if (namedItem3 == null) {
                        bigInteger = null;
                    } else {
                        bigInteger = new BigInteger(namedItem3.getNodeValue());
                    }
                    vASTMediaFile.setWidth(bigInteger);
                    vASTMediaFile.setValue(XmlTools.getElementValue(item));
                    arrayList.add(vASTMediaFile);
                }
            }
            return arrayList;
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public String getDuration() {
        VASTLog.d(TAG, "getDuration");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(durationXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodeList == null) {
                return null;
            }
            int i = 0;
            String str = null;
            while (i < nodeList.getLength()) {
                String elementValue = XmlTools.getElementValue(nodeList.item(i));
                i++;
                str = elementValue;
            }
            return str;
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public VASTCompanion getCompanion(Pair<Integer, Integer> pair) {
        VASTLog.d(TAG, "checkCompanion");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(companionsXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodeList == null) {
                try {
                    sendError(ERROR_CODE_COMPANION_NODE_NOT_FOUND);
                } catch (Exception e) {
                    VASTLog.e(TAG, e.getMessage());
                }
            } else if (nodeList.getLength() == 0) {
                try {
                    sendError(ERROR_CODE_COMPANION_NODE_NOT_FOUND);
                } catch (Exception e2) {
                    VASTLog.e(TAG, e2.getMessage());
                }
            } else {
                Map hashMap = new HashMap();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node item = nodeList.item(i);
                    NamedNodeMap attributes = item.getAttributes();
                    Node namedItem = attributes.getNamedItem("height");
                    Node namedItem2 = attributes.getNamedItem("width");
                    if (!(namedItem == null || namedItem2 == null)) {
                        int intValue = Integer.valueOf(namedItem.getNodeValue()).intValue();
                        int intValue2 = Integer.valueOf(namedItem2.getNodeValue()).intValue();
                        float max = ((float) Math.max(intValue2, intValue)) / ((float) Math.min(intValue2, intValue));
                        if (Math.min(intValue2, intValue) >= Callback.DEFAULT_SWIPE_ANIMATION_DURATION && ((double) max) <= 2.5d) {
                            hashMap.put(Float.valueOf(((float) intValue2) / ((float) intValue)), new VASTCompanion(item));
                        }
                    }
                }
                if (hashMap.isEmpty()) {
                    try {
                        sendError(ERROR_CODE_COMPANION_NOT_FOUND);
                    } catch (Exception e22) {
                        VASTLog.e(TAG, e22.getMessage());
                    }
                } else {
                    float intValue3 = ((float) ((Integer) pair.first).intValue()) / ((float) ((Integer) pair.second).intValue());
                    Set<Float> keySet = hashMap.keySet();
                    float floatValue = ((Float) keySet.iterator().next()).floatValue();
                    for (Float floatValue2 : keySet) {
                        float floatValue3 = floatValue2.floatValue();
                        if (Math.abs(floatValue - intValue3) <= Math.abs(floatValue3 - intValue3)) {
                            floatValue3 = floatValue;
                        }
                        floatValue = floatValue3;
                    }
                    return (VASTCompanion) hashMap.get(Float.valueOf(floatValue));
                }
            }
            return null;
        } catch (Throwable e3) {
            VASTLog.e(TAG, e3.getMessage(), e3);
            try {
                sendError(ERROR_CODE_COMPANION_NODE_NOT_FOUND);
            } catch (Exception e222) {
                VASTLog.e(TAG, e222.getMessage());
            }
            return null;
        }
    }

    public VASTCompanion getBanner() {
        int i = 0;
        VASTLog.d(TAG, "checkCompanion");
        XPath newXPath = XPathFactory.newInstance().newXPath();
        try {
            NodeList nodeList = (NodeList) newXPath.evaluate(nonLinearXPATH, this.vastsDocument, XPathConstants.NODESET);
            Node item;
            Node item2;
            NamedNodeMap attributes;
            Node namedItem;
            int intValue;
            if (nodeList != null) {
                if (nodeList.getLength() > 0) {
                    for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                        item = nodeList.item(i2);
                        NamedNodeMap attributes2 = item.getAttributes();
                        Node namedItem2 = attributes2.getNamedItem("height");
                        Node namedItem3 = attributes2.getNamedItem("width");
                        if (!(namedItem2 == null || namedItem3 == null)) {
                            int intValue2 = Integer.valueOf(namedItem2.getNodeValue()).intValue();
                            int intValue3 = Integer.valueOf(namedItem3.getNodeValue()).intValue();
                            if (Appodeal.b != null && an.n(Appodeal.b) && intValue3 == 728 && intValue2 == 90) {
                                return new VASTCompanion(item);
                            }
                            if (Appodeal.b != null && !an.n(Appodeal.b) && intValue3 == 320 && intValue2 == 50) {
                                return new VASTCompanion(item);
                            }
                        }
                    }
                } else {
                    try {
                        sendError(ERROR_CODE_NONLINEAR_NODE_NOT_FOUND);
                    } catch (Exception e) {
                        VASTLog.e(TAG, e.getMessage());
                    }
                }
                try {
                    nodeList = (NodeList) newXPath.evaluate(companionsXPATH, this.vastsDocument, XPathConstants.NODESET);
                    if (nodeList != null) {
                        try {
                            sendError(ERROR_CODE_COMPANION_NODE_NOT_FOUND);
                        } catch (Exception e2) {
                            VASTLog.e(TAG, e2.getMessage());
                        }
                    } else if (nodeList.getLength() != 0) {
                        try {
                            sendError(ERROR_CODE_COMPANION_NODE_NOT_FOUND);
                        } catch (Exception e22) {
                            VASTLog.e(TAG, e22.getMessage());
                        }
                    } else {
                        while (i < nodeList.getLength()) {
                            item2 = nodeList.item(i);
                            attributes = item2.getAttributes();
                            item = attributes.getNamedItem("height");
                            namedItem = attributes.getNamedItem("width");
                            if (!(item == null || namedItem == null)) {
                                intValue = Integer.valueOf(item.getNodeValue()).intValue();
                                if (Integer.valueOf(namedItem.getNodeValue()).intValue() == 320 && intValue == 50) {
                                    return new VASTCompanion(item2);
                                }
                            }
                            i++;
                        }
                        try {
                            sendError(ERROR_CODE_COMPANION_NOT_FOUND);
                        } catch (Exception e222) {
                            VASTLog.e(TAG, e222.getMessage());
                        }
                    }
                    return null;
                } catch (Throwable e3) {
                    VASTLog.e(TAG, e3.getMessage(), e3);
                    try {
                        sendError(ERROR_CODE_COMPANION_NODE_NOT_FOUND);
                    } catch (Exception e2222) {
                        VASTLog.e(TAG, e2222.getMessage());
                    }
                    return null;
                }
            }
            try {
                sendError(ERROR_CODE_NONLINEAR_NODE_NOT_FOUND);
            } catch (Exception e22222) {
                VASTLog.e(TAG, e22222.getMessage());
            }
            nodeList = (NodeList) newXPath.evaluate(companionsXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodeList != null) {
                sendError(ERROR_CODE_COMPANION_NODE_NOT_FOUND);
            } else if (nodeList.getLength() != 0) {
                while (i < nodeList.getLength()) {
                    item2 = nodeList.item(i);
                    attributes = item2.getAttributes();
                    item = attributes.getNamedItem("height");
                    namedItem = attributes.getNamedItem("width");
                    intValue = Integer.valueOf(item.getNodeValue()).intValue();
                    return new VASTCompanion(item2);
                }
                sendError(ERROR_CODE_COMPANION_NOT_FOUND);
            } else {
                sendError(ERROR_CODE_COMPANION_NODE_NOT_FOUND);
            }
            return null;
        } catch (Throwable e32) {
            VASTLog.e(TAG, e32.getMessage(), e32);
            try {
                sendError(ERROR_CODE_NONLINEAR_NODE_NOT_FOUND);
            } catch (Exception e222222) {
                VASTLog.e(TAG, e222222.getMessage());
            }
        }
    }

    public int getSkipoffset() {
        VASTLog.d(TAG, "getSkipoffset");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(linearXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodeList == null) {
                return 0;
            }
            int i = 0;
            for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                Node namedItem = nodeList.item(i2).getAttributes().getNamedItem("skipoffset");
                if (namedItem != null) {
                    String nodeValue = namedItem.getNodeValue();
                    if (nodeValue != null) {
                        String[] split = nodeValue.split(":");
                        i = Integer.parseInt(split[2]) + (((Integer.parseInt(split[0]) * 60) * 60) + (Integer.parseInt(split[1]) * 60));
                    }
                }
            }
            return i;
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
            return 0;
        }
    }

    public VideoClicks getVideoClicks() {
        VASTLog.d(TAG, "getVideoClicks");
        VideoClicks videoClicks = new VideoClicks();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(videoClicksXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    NodeList childNodes = nodeList.item(i).getChildNodes();
                    for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                        Node item = childNodes.item(i2);
                        String nodeName = item.getNodeName();
                        if (nodeName.equalsIgnoreCase("ClickTracking")) {
                            videoClicks.getClickTracking().add(XmlTools.getElementValue(item));
                        } else if (nodeName.equalsIgnoreCase("ClickThrough")) {
                            videoClicks.setClickThrough(XmlTools.getElementValue(item));
                        } else if (nodeName.equalsIgnoreCase("CustomClick")) {
                            videoClicks.getCustomClick().add(XmlTools.getElementValue(item));
                        }
                    }
                }
            }
            return videoClicks;
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public List<String> getImpressions() {
        VASTLog.d(TAG, "getImpressions");
        return getListFromXPath(impressionXPATH);
    }

    public List<String> getErrorUrl() {
        VASTLog.d(TAG, "getErrorUrl");
        return getListFromXPath(errorUrlXPATH);
    }

    private List<String> getListFromXPath(String str) {
        VASTLog.d(TAG, "getListFromXPath");
        ArrayList arrayList = new ArrayList();
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(str, this.vastsDocument, XPathConstants.NODESET);
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    arrayList.add(XmlTools.getElementValue(nodeList.item(i)));
                }
            }
            return arrayList;
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public String getAdParameterms() {
        VASTLog.d(TAG, "getAdParameterms");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(adParametersXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodeList == null) {
                return null;
            }
            int i = 0;
            String str = null;
            while (i < nodeList.getLength()) {
                String elementValue = XmlTools.getElementValue(nodeList.item(i));
                i++;
                str = elementValue;
            }
            return str;
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public Extensions getExtensions() {
        VASTLog.d(TAG, "getExtensions");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate(combinedExtensionPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodeList == null || nodeList.getLength() <= 0) {
                return null;
            }
            return new Extensions(nodeList);
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        VASTLog.d(TAG, "writeObject: about to write");
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(XmlTools.xmlDocumentToString(this.vastsDocument));
        VASTLog.d(TAG, "done writing");
    }

    private void readObject(ObjectInputStream objectInputStream) {
        VASTLog.d(TAG, "readObject: about to read");
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        VASTLog.d(TAG, "vastString data is:\n" + str + "\n");
        this.vastsDocument = XmlTools.stringToDocument(str);
        VASTLog.d(TAG, "done reading");
    }

    public String getPickedMediaFileURL() {
        return this.pickedMediaFileURL;
    }

    public String getPickedMediaFileType() {
        return this.pickedMediaFileType;
    }

    public void setPickedMediaFile(VASTMediaFile vASTMediaFile) {
        this.pickedMediaFileURL = vASTMediaFile.getValue();
        this.pickedMediaFileType = vASTMediaFile.getType();
    }

    public void sendError(int i) {
        List<String> errorUrl = getErrorUrl();
        if (errorUrl != null) {
            for (String replaceMacros : errorUrl) {
                String replaceMacros2 = replaceMacros(replaceMacros2, i);
                VASTLog.v(TAG, "Fire error url:" + replaceMacros2);
                HttpTools.httpGetURL(replaceMacros2);
            }
            return;
        }
        VASTLog.d(TAG, "Error url list is null");
    }

    private String replaceMacros(String str, int i) {
        if (str == null) {
            return str;
        }
        if (str.contains("[ERRORCODE]")) {
            str = str.replace("[ERRORCODE]", String.valueOf(i));
        }
        if (str.contains("%5BERRORCODE%5D")) {
            return str.replace("%5BERRORCODE%5D", String.valueOf(i));
        }
        return str;
    }
}
