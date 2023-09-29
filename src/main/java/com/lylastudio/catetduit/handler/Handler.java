package com.lylastudio.catetduit.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lylastudio.catetduit.db.entity.MHandler;
import com.lylastudio.catetduit.db.entity.MTransactionCategory;
import com.lylastudio.catetduit.db.repository.MAccountRespository;
import com.lylastudio.catetduit.db.repository.MHandlerRepository;
import com.lylastudio.catetduit.db.repository.MTransactionCategoryRepository;
import com.lylastudio.catetduit.db.repository.TransactionRepository;
import com.lylastudio.catetduit.model.http.ResponseRoot;
import com.lylastudio.catetduit.model.http.SendMessage;
import com.lylastudio.catetduit.model.http.Update;
import com.lylastudio.catetduit.util.Constants;
import com.lylastudio.catetduit.util.JSONFormater;
import com.lylastudio.catetduit.util.StringHelper;
import com.lylastudio.catetduit.util.TelegramClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
public abstract class Handler {

    protected TelegramClient telegramClient;

    protected TransactionRepository transactionRepository;

    protected MTransactionCategoryRepository mTransactionCategoryRepository;

    protected SendMessage sendMessage = new SendMessage();

    protected MHandler handlerConfig;

    protected HashMap<String,String> specialData = new HashMap<>();

    protected JSONFormater jsonFormater;

    protected StringHelper stringHelper;

    protected MAccountRespository mAccountRespository;

    protected ArrayList<String> requestParameter;

    protected Update update;

    protected MHandlerRepository mHandlerRepository;

    public void prepare() {

        try {
            log.info("receive-from-telegram: {}", jsonFormater.toJSONString(update));
        } catch (JsonProcessingException e) {
            log.error("Error When Try To extract JSON");
            throw new RuntimeException(e);
        }

        specialData.put("first_name",update.getMessage().getFrom().getFirstName());
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

    abstract public void execute();

    public void sendMessage(){
        try {
            log.info("send-to-telegram: {} ", jsonFormater.toJSONString(sendMessage));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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

    public void setRequestParameter(ArrayList<String> requestParameter) {
        this.requestParameter = requestParameter;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public void setmTransactionCategoryRepository(MTransactionCategoryRepository mTransactionCategoryRepository) {
        this.mTransactionCategoryRepository = mTransactionCategoryRepository;
    }

    public void setmHandlerRepository(MHandlerRepository mHandlerRepository) {
        this.mHandlerRepository = mHandlerRepository;
    }
}
