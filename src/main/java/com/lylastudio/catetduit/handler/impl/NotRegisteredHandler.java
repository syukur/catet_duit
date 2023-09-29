package com.lylastudio.catetduit.handler.impl;

import com.lylastudio.catetduit.db.entity.MAccount;
import com.lylastudio.catetduit.handler.Handler;
import com.lylastudio.catetduit.model.http.Update;

public class NotRegisteredHandler extends Handler {
    @Override
    public void execute() {

        MAccount account = new MAccount();
        account.setFirstName(update.getMessage().getFrom().getFirstName());
        account.setLastName(update.getMessage().getFrom().getLastName());
        account.setChatId(String.valueOf(update.getMessage().getChat().getId()));
        account.setFromId(String.valueOf(update.getMessage().getFrom().getId()));
        account.setUserName(update.getMessage().getFrom().getUserName());

        mAccountRespository.save(account);

    }
}
