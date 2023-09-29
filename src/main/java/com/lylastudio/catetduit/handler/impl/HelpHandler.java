package com.lylastudio.catetduit.handler.impl;

import com.lylastudio.catetduit.db.entity.TTransaction;
import com.lylastudio.catetduit.handler.Handler;

import java.util.List;

public class HelpHandler extends Handler {
    @Override
    public void execute() {

        StringBuilder response = new StringBuilder("Help:\n");
        mHandlerRepository.findByDescriptionNot("-").forEach(handler->{
            response.append("- ").append(handler.getDescription()).append("\n");
        });

        sendMessage.setText(response.toString());
    }


}
