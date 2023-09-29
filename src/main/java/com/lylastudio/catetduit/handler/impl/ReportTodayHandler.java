package com.lylastudio.catetduit.handler.impl;

import com.lylastudio.catetduit.db.entity.TTransaction;
import com.lylastudio.catetduit.handler.Handler;

import java.util.List;

public class ReportTodayHandler extends Handler {
    @Override
    public void execute() {

        String fromId = String.valueOf(
                update.getMessage().getFrom().getId()
        );

        List<TTransaction> transactions = transactionRepository.findByTelegramFromId(fromId);
        if(transactions.size() < 1){
            sendMessage.setText("Blm ada catatan pengeluaran hari ini pak.");
        }else {
            StringBuilder response = new StringBuilder("Pengeluaran hari ini pa:\n");

            transactions.forEach(transaction->{
                response.append(transaction.getAmount()).append("\t|\t")
                        .append(transaction.getTransactionCategory().getName()).append("\t|\t")
                        .append(transaction.getNote())
                        .append("\n");

            });

           response.append("Total : ").append(transactionRepository.calculateAmountBySenderId(fromId));

            sendMessage.setText(response.toString());
        }

    }
}
