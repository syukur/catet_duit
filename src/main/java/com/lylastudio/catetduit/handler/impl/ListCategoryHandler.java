package com.lylastudio.catetduit.handler.impl;

import com.lylastudio.catetduit.db.entity.MAccount;
import com.lylastudio.catetduit.db.entity.MTransactionCategory;
import com.lylastudio.catetduit.handler.Handler;

import java.util.List;

public class ListCategoryHandler extends Handler {
    @Override
    public void execute() {

        String fromId = String.valueOf(
                update.getMessage().getFrom().getId()
        );

        String firtName = update.getMessage().getFrom().getFirstName();

        MAccount account = mAccountRespository.findByFromId(fromId);

        StringBuilder categories = new StringBuilder("Ok Pak ").append(firtName).append(" Berikut list kategorinya:\n\n");

        account.getTransactionCategories().forEach(category->{
            categories.append("-").append(category.getName()).append("\n");

        });

        sendMessage.setText(categories.toString());


    }
}
