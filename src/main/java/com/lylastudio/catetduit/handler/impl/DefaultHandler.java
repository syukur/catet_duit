package com.lylastudio.catetduit.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lylastudio.catetduit.handler.Handler;
import com.lylastudio.catetduit.model.http.Update;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultHandler extends Handler {

    @Override
    public void execute(Update update){
        try {
            log.info("receiving-update: {}", jsonFormater.toJSONString(update));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
