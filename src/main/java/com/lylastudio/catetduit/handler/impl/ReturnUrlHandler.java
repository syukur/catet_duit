package com.lylastudio.catetduit.handler.impl;

import com.lylastudio.catetduit.db.entity.TOneTimeAccess;
import com.lylastudio.catetduit.handler.Handler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class ReturnUrlHandler extends Handler {

    Calendar calendar = Calendar.getInstance();

    @Override
    public void execute() {

        String fromId = String.valueOf(
                        update.getMessage().getFrom().getId()
        );

        String updateId = String.valueOf(
                update.getUpdateId()
        );

        String fullUrl = handlerConfig.getReturnUrl();

        URL url = null;
        String pathUrl = "";

        try {
            url = new URL(fullUrl);
            pathUrl = url.getPath();
        } catch (MalformedURLException e) {
            pathUrl = "";
            throw new RuntimeException(e);
        }


        TOneTimeAccess existingAccess = tOneTimeAccessRepository.findByFromIdAndUrl(fromId, pathUrl);
        TOneTimeAccess newAccess = new TOneTimeAccess();
        if (existingAccess != null){
            newAccess = existingAccess;
        }

        newAccess.setFromId(fromId);

        String date = new Date().toString();
        String signature = DigestUtils.sha256Hex(fromId + url + date + updateId );
        newAccess.setSignature(signature);
        newAccess.setUrl(pathUrl);


        Date now = new Date();
        newAccess.setUpdatedDate(now);
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, 1);
        Date expired = calendar.getTime();
        newAccess.setExpired(expired);

        tOneTimeAccessRepository.save(newAccess);
        sendMessage.setText("Berikut link report nya pak "+ url);

    }

}
