package com.lylastudio.catetduit.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

@Component
public class JSONFormater {

    ObjectMapper objectMapper = new ObjectMapper();

    ObjectWriter objectWriter;

    private JSONFormater(){
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
    }

    public String toJSONString( Object object ) throws JsonProcessingException {
      return objectWriter.writeValueAsString(object);
    }
}
