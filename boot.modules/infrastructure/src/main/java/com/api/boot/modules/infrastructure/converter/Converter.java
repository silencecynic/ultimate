package com.api.boot.modules.infrastructure.converter;

import java.util.Map;

public interface Converter {

    String MapConverterXML(Map<String,Object> map);

    String MapConverterXML(Map<String,Object> map ,Boolean bool);

    Map<String,Object> XMLConverterMap(String XML);

}
