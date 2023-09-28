package com.lylastudio.catetduit.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lylastudio.catetduit.db.entity.MHandler;
import com.lylastudio.catetduit.db.repository.MAccountRespository;
import com.lylastudio.catetduit.db.repository.TransactionRepository;
import com.lylastudio.catetduit.model.http.ResponseRoot;
import com.lylastudio.catetduit.model.http.SendMessage;
import com.lylastudio.catetduit.model.http.Update;
import com.lylastudio.catetduit.util.Constants;
import com.lylastudio.catetduit.util.JSONFormater;
import com.lylastudio.catetduit.util.StringHelper;
import com.lylastudio.catetduit.util.TelegramClient;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public abstract class Handler {

    protected TelegramClient telegramClient;

    protected TransactionRepository transactionRepository;

    protected SendMessage sendMessage = new SendMessage();

    protected MHandler handlerConfig;

    protected HashMap<String,String> specialData = new HashMap<>();

    protected JSONFormater jsonFormater;

    protected StringHelper stringHelper;

    protected MAccountRespository mAccountRespository;

    public void prepare(Update update){

        specialData.put("fist_name",update.getMessage().getFrom().getFirstName());
        specialData.put("last_name",update.getMessage().getFrom().getLastName());
        specialData.put("admin_name", Constants.ADMIN_NAME);

        sendMessage.setChatId(
                update.getMessage().getChat().getId()
        );

        specialData.forEach((key,value)->{
            log.info("key: {}, value: {}", key, value);
        });


        String text = stringHelper.replacePattern(
                handlerConfig.getResponseFormat(),
                specialData
        );

        sendMessage.setText(text);

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

    public void setJsonFormater(JSONFormater jsonFormater) {
        this.jsonFormater = jsonFormater;
    }

    public void setStringHelper(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

    public void setmAccountRespository(MAccountRespository mAccountRespository) {
        this.mAccountRespository = mAccountRespository;
    }
}
