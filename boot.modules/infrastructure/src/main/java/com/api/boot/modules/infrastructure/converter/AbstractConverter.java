package com.api.boot.modules.infrastructure.converter;

import com.api.boot.modules.infrastructure.constant.XML;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractConverter implements Converter {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> XMLConverterMap(String XML) {
        Map<String,Object> map = new HashMap<>();
        Document document;
        try {
            document = DocumentHelper.parseText(XML.trim().replaceAll("[\r|\n]",""));
            Element rootElt = document.getRootElement();
            List<Element> list = rootElt.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
        } catch (DocumentException ex) {
            ex.printStackTrace();
            return null;
        }
        return map;
    }

    @Override
    public String MapConverterXML(Map<String, Object> map) {
        StringBuilder builder = new StringBuilder(XML.PREFIX_XML.toString());
        if (map != null && !(map.isEmpty())) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                builder.append("<").append(entry.getKey()).append(">");
                if ((entry.getValue()) != null) {
                    builder.append(entry.getValue());
                }
                builder.append("</").append(entry.getKey()).append(">");
            }
        }
        return builder.append(XML.SUFFIX_XML).toString().trim();
    }

    @Override
    public String MapConverterXML(Map<String, Object> map, Boolean bool) {
        StringBuilder builder = new StringBuilder(XML.PREFIX_XML.getXml().trim());
        if (map != null && !(map.isEmpty())) {
            for (Map.Entry<String,Object> en : map.entrySet()) {
                builder.append("<").append(en.getKey().trim()).append(">");
                if (bool && en.getValue() != null) {
                    builder.append(XML.PREFIX_CDATA.getXml());
                    builder.append(en.getValue());
                    builder.append(XML.SUFFIX_CDATA.getXml());
                } else {
                    builder.append(en.getValue());
                }
                builder.append("</").append(en.getKey()).append(">");
            }
        }

        return builder.append(XML.SUFFIX_XML.getXml()).toString().trim();
    }

}
