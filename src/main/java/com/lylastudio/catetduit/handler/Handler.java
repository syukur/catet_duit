package com.lylastudio.catetduit.handler;

import com.lylastudio.catetduit.db.entity.MHandler;
import com.lylastudio.catetduit.db.repository.TransactionRepository;
import com.lylastudio.catetduit.model.http.ResponseRoot;
import com.lylastudio.catetduit.model.http.SendMessage;
import com.lylastudio.catetduit.model.http.Update;
import com.lylastudio.catetduit.util.TelegramClient;


public abstract class Handler {

    protected TelegramClient telegramClient;

    protected TransactionRepository transactionRepository;

    protected SendMessage sendMessage = new SendMessage();

    protected MHandler handlerConfig;


    public void prepare(Update update){
        sendMessage.setChatId(
                update.getMessage().getChat().getId()
        );

        sendMessage.setText(
                handlerConfig.getResponseFormat()
        );
    }

    abstract public void execute(Update update);

    public void sendMessage(){
        ResponseRoot responseRoot = telegramClient.sendMessage(sendMessage);
    }

    public void setTelegramClient(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public void setHandlerConfig(MHandler handlerConfig) {
        this.handlerConfig = handlerConfig;
    }
}
