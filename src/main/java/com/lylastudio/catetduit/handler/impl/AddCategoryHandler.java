package com.lylastudio.catetduit.handler.impl;

import com.lylastudio.catetduit.handler.Handler;
import com.lylastudio.catetduit.model.http.Update;

import java.math.BigInteger;

public class AddCategoryHandler extends Handler {
    @Override
    public void execute() {

        String text = update.getMessage().getText();
        Long fromId = update.getMessage().getFrom().getId();
        String firtName = update.getMessage().getFrom().getFirstName();

        String[] trx = text.split("\\:");
        BigInteger amount = new BigInteger(trx[0].trim());
        String category = trx[1].trim();
        String note = trx[2].trim();
//
//        TTransaction transaction = new TTransaction();
//        transaction.setAmount(amount);
//        transaction.setCategory(category);
//        transaction.setNote(note);
//        transaction.setTelegramFromId(fromId);
//        transaction.setName(firtName);
//
//        transactionRepository.save(transaction);

    }
}
