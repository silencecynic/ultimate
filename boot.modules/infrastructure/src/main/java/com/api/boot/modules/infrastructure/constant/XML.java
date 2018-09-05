package com.api.boot.modules.infrastructure.constant;

public enum  XML {

    PREFIX_XML("<xml>"),
    SUFFIX_XML("</xml>"),
    PREFIX_CDATA("<![CDATA["),
    SUFFIX_CDATA("]]>");

    private String xml;

    XML(String xml) {
        this.xml = xml;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    @Override
    public String toString() {
        return xml;
    }

}
