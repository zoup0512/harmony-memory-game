package org.nexage.sourcekit.vast.processor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import javax.xml.parsers.DocumentBuilderFactory;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.util.XmlTools;
import org.nexage.sourcekit.vast.model.VASTModel;
import org.nexage.sourcekit.vast.model.VAST_DOC_ELEMENTS;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public final class VASTProcessor {
    private static final int MAX_VAST_LEVELS = 5;
    private static final String TAG = "VASTProcessor";
    private VASTMediaPicker mediaPicker;
    private StringBuilder mergedVastDocs = new StringBuilder(500);
    private VASTModel vastModel;

    public VASTProcessor(VASTMediaPicker vASTMediaPicker) {
        this.mediaPicker = vASTMediaPicker;
    }

    public VASTModel getModel() {
        return this.vastModel;
    }

    public int process(String str) {
        VASTLog.d(TAG, "process");
        this.vastModel = null;
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(Charset.defaultCharset().name()));
            int processUri = processUri(byteArrayInputStream, 0);
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
            }
            if (processUri != 0) {
                return processUri;
            }
            Document wrapMergedVastDocWithVasts = wrapMergedVastDocWithVasts();
            this.vastModel = new VASTModel(wrapMergedVastDocWithVasts);
            if (wrapMergedVastDocWithVasts == null) {
                return 3;
            }
            return !VASTModelPostValidator.validate(this.vastModel, this.mediaPicker) ? 5 : 0;
        } catch (Throwable e2) {
            VASTLog.e(TAG, e2.getMessage(), e2);
            return 3;
        }
    }

    private Document wrapMergedVastDocWithVasts() {
        VASTLog.d(TAG, "wrapmergedVastDocWithVasts");
        this.mergedVastDocs.insert(0, "<VASTS>");
        this.mergedVastDocs.append("</VASTS>");
        String stringBuilder = this.mergedVastDocs.toString();
        VASTLog.v(TAG, "Merged VAST doc:\n" + stringBuilder);
        return XmlTools.stringToDocument(stringBuilder);
    }

    private int processUri(InputStream inputStream, int i) {
        VASTLog.d(TAG, "processUri");
        if (i >= 5) {
            VASTLog.e(TAG, "VAST wrapping exceeded max limit of 5.");
            try {
                if (this.vastModel != null) {
                    this.vastModel.sendError(VASTModel.ERROR_CODE_EXCEEDED_WRAPPER_LIMIT);
                }
            } catch (Exception e) {
                VASTLog.e(TAG, e.getMessage());
            }
            return 6;
        }
        Document createDoc = createDoc(inputStream);
        if (createDoc == null) {
            return 3;
        }
        merge(createDoc);
        NodeList elementsByTagName = createDoc.getElementsByTagName(VAST_DOC_ELEMENTS.vastAdTagURI.getValue());
        if (elementsByTagName == null || elementsByTagName.getLength() == 0) {
            return 0;
        }
        VASTLog.d(TAG, "Doc is a wrapper. ");
        String elementValue = XmlTools.getElementValue(elementsByTagName.item(0));
        VASTLog.d(TAG, "Wrapper URL: " + elementValue);
        try {
            InputStream openStream = new URL(elementValue).openStream();
            int processUri = processUri(openStream, i + 1);
            try {
                openStream.close();
                return processUri;
            } catch (IOException e2) {
                return processUri;
            }
        } catch (Throwable e3) {
            VASTLog.e(TAG, e3.getMessage(), e3);
            try {
                if (this.vastModel != null) {
                    this.vastModel.sendError(301);
                }
            } catch (Exception e4) {
                VASTLog.e(TAG, e4.getMessage());
            }
            return 2;
        }
    }

    private Document createDoc(InputStream inputStream) {
        VASTLog.d(TAG, "About to create doc from InputStream");
        try {
            Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
            parse.getDocumentElement().normalize();
            VASTLog.d(TAG, "Doc successfully created.");
            return parse;
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    private void merge(Document document) {
        VASTLog.d(TAG, "About to merge doc into main doc.");
        this.mergedVastDocs.append(XmlTools.xmlDocumentToString(document.getElementsByTagName("VAST").item(0)));
        VASTLog.d(TAG, "Merge successful.");
    }
}
