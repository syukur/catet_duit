package com.lylastudio.catetduit.util;

import org.springframework.stereotype.Component;

@Component
public class Logging {
    public String addTraceid(String traceId , String data){
        return "traceId: " + traceId + "data: " + data;
    }
}
