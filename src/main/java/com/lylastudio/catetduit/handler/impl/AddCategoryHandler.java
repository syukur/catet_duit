package com.lylastudio.catetduit.handler.impl;

import com.lylastudio.catetduit.db.entity.MAccount;
import com.lylastudio.catetduit.db.entity.MTransactionCategory;
import com.lylastudio.catetduit.handler.Handler;
import com.lylastudio.catetduit.model.http.Update;

import java.math.BigInteger;

public class AddCategoryHandler extends Handler {
    @Override
    public void execute() {
        Long fromId = update.getMessage().getFrom().getId();

        String categoryName = requestParameter.get(1);
        categoryName = categoryName.trim();

        MAccount account = mAccountRespository.findByFromId(
                String.valueOf(fromId)
        );

        MTransactionCategory transactionCategory = new MTransactionCategory();
        transactionCategory.setAccount(account);
        transactionCategory.setName(categoryName);

        mTransactionCategoryRepository.save(transactionCategory);

    }
}
