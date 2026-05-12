package org.nexage.sourcekit.vast.model;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.util.XmlTools;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Extensions {
    private final String TAG = "Extensions";
    private List<String> addClick = new ArrayList();
    private boolean controls = true;
    private boolean controlsSet;
    private boolean isClickable = true;
    private boolean isClickableSet;
    private String linkTxt;
    private List<String> skipAd = new ArrayList();
    private int skipTime2;

    public Extensions(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList childNodes = nodeList.item(i).getChildNodes();
            if (childNodes != null) {
                for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                    Node item = childNodes.item(i2);
                    if (item.getNodeName().equalsIgnoreCase("Extension")) {
                        String nodeValue = item.getAttributes().getNamedItem("type").getNodeValue();
                        if (nodeValue.equals("skipTime2")) {
                            setSkipTime2(XmlTools.getElementValue(item));
                        } else if (nodeValue.equals("controls")) {
                            setControls(Integer.valueOf(XmlTools.getElementValue(item)).intValue());
                        } else if (nodeValue.equals("skipAd")) {
                            addSkipAd(XmlTools.getElementValue(item));
                        } else if (nodeValue.equals("addClick")) {
                            addAddClick(XmlTools.getElementValue(item));
                        } else if (nodeValue.equals("linkTxt")) {
                            setLinkTxt(XmlTools.getElementValue(item));
                        } else if (nodeValue.equals("isClickable")) {
                            setClickable(Integer.valueOf(XmlTools.getElementValue(item)).intValue());
                        }
                    }
                }
            }
        }
    }

    private int parseTimeToSeconds(String str) {
        int i = 0;
        try {
            String[] split = str.split(":");
            int parseInt = Integer.parseInt(split[0]);
            return Integer.parseInt(split[1]) + (parseInt * 60);
        } catch (Exception e) {
            VASTLog.w("Extensions", e.getMessage());
            return i;
        }
    }

    private void setSkipTime2(String str) {
        if (this.skipTime2 == 0) {
            this.skipTime2 = parseTimeToSeconds(str);
        }
    }

    private void setControls(int i) {
        if (!this.controlsSet) {
            this.controls = i == 1;
            this.controlsSet = true;
        }
    }

    private void setClickable(int i) {
        if (!this.isClickableSet) {
            this.isClickable = i == 1;
            this.isClickableSet = true;
        }
    }

    private void addSkipAd(String str) {
        this.skipAd.add(str);
    }

    private void addAddClick(String str) {
        this.addClick.add(str);
    }

    private void setLinkTxt(String str) {
        if (this.linkTxt == null) {
            try {
                this.linkTxt = URLDecoder.decode(str, "UTF-8");
            } catch (Exception e) {
                VASTLog.w("Extensions", e.getMessage());
            }
        }
    }

    public int getSkipTime2() {
        return this.skipTime2;
    }

    public boolean isControls() {
        return this.controls;
    }

    public List<String> getSkipAd() {
        return this.skipAd;
    }

    public List<String> getAddClick() {
        return this.addClick;
    }

    public String getLinkTxt() {
        return this.linkTxt;
    }

    public boolean isClickable() {
        return this.isClickable;
    }
}
