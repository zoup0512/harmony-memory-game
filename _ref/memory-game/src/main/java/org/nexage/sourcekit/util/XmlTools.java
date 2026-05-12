package org.nexage.sourcekit.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlTools {
    private static String TAG = "XmlTools";

    public static void logXmlDocument(Document document) {
        VASTLog.d(TAG, "logXmlDocument");
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "no");
            newTransformer.setOutputProperty("method", "xml");
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("encoding", "UTF-8");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            Writer stringWriter = new StringWriter();
            newTransformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            VASTLog.d(TAG, stringWriter.toString());
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
        }
    }

    public static String xmlDocumentToString(Document document) {
        String str = null;
        VASTLog.d(TAG, "xmlDocumentToString");
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "no");
            newTransformer.setOutputProperty("method", "xml");
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("encoding", "UTF-8");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            Writer stringWriter = new StringWriter();
            newTransformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            str = stringWriter.toString();
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
        }
        return str;
    }

    public static String xmlDocumentToString(Node node) {
        String str = null;
        VASTLog.d(TAG, "xmlDocumentToString");
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
            newTransformer.setOutputProperty("method", "xml");
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("encoding", "UTF-8");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            Writer stringWriter = new StringWriter();
            newTransformer.transform(new DOMSource(node), new StreamResult(stringWriter));
            str = stringWriter.toString();
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
        }
        return str;
    }

    public static Document stringToDocument(String str) {
        VASTLog.d(TAG, "stringToDocument");
        Document document = null;
        try {
            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(str));
            document = newDocumentBuilder.parse(inputSource);
        } catch (Throwable e) {
            VASTLog.e(TAG, e.getMessage(), e);
        }
        return document;
    }

    public static String stringFromStream(InputStream inputStream) {
        VASTLog.d(TAG, "stringFromStream");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static String getElementValue(Node node) {
        NodeList childNodes = node.getChildNodes();
        String str = null;
        int i = 0;
        while (i < childNodes.getLength()) {
            String trim = ((CharacterData) childNodes.item(i)).getData().trim();
            if (trim.length() == 0) {
                i++;
                str = trim;
            } else {
                VASTLog.v(TAG, "getElementValue: " + trim);
                return trim;
            }
        }
        return str;
    }
}
