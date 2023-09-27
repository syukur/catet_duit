package com.lylastudio.catetduit.handler.impl;

import com.lylastudio.catetduit.handler.Handler;
import com.lylastudio.catetduit.model.http.Update;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultHandler extends Handler {

    @Override
    public void execute(Update update) {
        log.info("receiving-update: {}", update);
    }
}
