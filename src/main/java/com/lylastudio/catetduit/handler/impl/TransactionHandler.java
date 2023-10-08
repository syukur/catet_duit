package com.lylastudio.catetduit.handler.impl;

import com.lylastudio.catetduit.db.entity.MAccount;
import com.lylastudio.catetduit.db.entity.MTransactionCategory;
import com.lylastudio.catetduit.db.entity.TTransaction;
import com.lylastudio.catetduit.handler.Handler;
import com.lylastudio.catetduit.util.Constants;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionHandler extends Handler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public void execute() {

        //keyword-format: expense [amount] [category] [note]
        //example: expense 100000 personal "buy a book"

        String fromId = String.valueOf(
                update.getMessage().getFrom().getId()
        );

        String firtName = update.getMessage().getFrom().getFirstName();


        BigInteger amount = new BigInteger(requestParameter.get(1));
        String categoryName = requestParameter.get(2);
        String note = requestParameter.get(3);

        MTransactionCategory category = mTransactionCategoryRepository.findByNameIgnoreCase(categoryName);

        if(category == null){

            MAccount account = mAccountRespository.findByFromId(fromId);
            List<MTransactionCategory> categoryList = account.getTransactionCategories();
            if(categoryList.size() < 1){

                StringBuilder response =  new StringBuilder("Waduh bapak blm setup kategori nih\n");
                response.append("berikut command untuk tambah kategory:\n");
                response.append("category.add [nama-kategory]\n");
                response.append("contoh:\n");
                response.append("category.add Personal");

                sendMessage.setText(response.toString());

            }else {

                StringBuilder response = new StringBuilder("Duh, Kategori nya blm kedaftar pak\n");
                response.append("Berikut List Kategori yang udah ada:\n");

                categoryList.forEach(item ->{
                    response.append("-").append(item.getName()).append("\n");
                });
                sendMessage.setText(response.toString());
            }
        }else {
            TTransaction transaction = new TTransaction();
            transaction.setAmount(amount);
            transaction.setNote(note);
            transaction.setTelegramFromId(fromId);
            transaction.setName(firtName);
            transaction.setTransactionCategory(category);
            transactionRepository.save(transaction);

            String tgl = LocalDateTime.now().format(formatter);
            List<TTransaction> transactions = transactionRepository.findBySenderIdAndDay(fromId);

            StringBuilder response = new StringBuilder("Sudah " + Constants.ADMIN_NAME + ", catet pak\n");

            response.append("Berikut pengeluaran hari ini:\n");

            transactions.forEach(trx->{
                response.append(trx.getAmount()).append("\t|\t")
                        .append(trx.getTransactionCategory().getName()).append("\t|\t")
                        .append(trx.getNote())
                        .append("\n");

            });

            response.append("Total : ").append(transactionRepository.calculateAmountBySenderId(fromId));

            sendMessage.setText(response.toString());

        }

    }
}
