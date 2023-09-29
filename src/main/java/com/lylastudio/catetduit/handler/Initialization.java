package com.lylastudio.catetduit.handler;

import com.lylastudio.catetduit.db.entity.MHandler;
import com.lylastudio.catetduit.db.entity.MTransactionCategory;
import com.lylastudio.catetduit.db.repository.MAccountRespository;
import com.lylastudio.catetduit.db.repository.MHandlerRepository;
import com.lylastudio.catetduit.db.repository.MTransactionCategoryRepository;
import com.lylastudio.catetduit.db.repository.TransactionRepository;
import com.lylastudio.catetduit.util.JSONFormater;
import com.lylastudio.catetduit.util.StringHelper;
import com.lylastudio.catetduit.util.TelegramClient;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Locale;

@Slf4j
@Component
public class Initialization {

    private final MHandlerRepository handlerRepository;
    private final HandlerHolder handlerHolder;

    private final TransactionRepository transactionRepository;

    private final TelegramClient telegramClient;

    private final MTransactionCategoryRepository mTransactionCategoryRepository;

    private final JSONFormater jsonFormater;

    private final StringHelper stringHelper;

    private final MAccountRespository mAccountRespository;

    public Initialization(MHandlerRepository handlerRepository,
                          HandlerHolder handlerHolder,
                          TelegramClient telegramClient,
                          TransactionRepository transactionRepository,
                          MTransactionCategoryRepository mTransactionCategoryRepository,
                          JSONFormater jsonFormater,
                          StringHelper stringHelper,
                          MAccountRespository mAccountRespository
    ){

        this.handlerRepository = handlerRepository;
        this.handlerHolder = handlerHolder;
        this.telegramClient = telegramClient;
        this.transactionRepository = transactionRepository;
        this.mTransactionCategoryRepository = mTransactionCategoryRepository;
        this.jsonFormater = jsonFormater;
        this.stringHelper = stringHelper;
        this.mAccountRespository = mAccountRespository;
    }

    @PostConstruct
    private void initHandler(){
        List<MHandler> handlers = handlerRepository.findAll();
        handlers.forEach(handler -> {
            log.info("keyword: {}", handler.getKeyword());
            try {

                //1. Init Handler Class
                Class cs = Class.forName(handler.getHandler());
                Constructor constructor = cs.getConstructor();
                Handler objectHandler = (Handler) constructor.newInstance();
                objectHandler.setTelegramClient(telegramClient);
                objectHandler.setTransactionRepository(transactionRepository);
                objectHandler.setHandlerConfig(handler);
                objectHandler.setJsonFormater(jsonFormater);
                objectHandler.setStringHelper(stringHelper);
                objectHandler.setmAccountRespository(mAccountRespository);
                objectHandler.setmTransactionCategoryRepository(mTransactionCategoryRepository);
                objectHandler.setmHandlerRepository(handlerRepository);


                //2. Put Handler & keyword to collection
                String[] keywordArray = handler.getKeyword().split("\\;");
                for ( String keyword : keywordArray ){
                    handlerHolder.putHandler(
                            keyword.toLowerCase().trim()
                            , objectHandler
                    );
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

//    @PostConstruct
//    private void insert(){
//        MTransactionCategory category1 = new MTransactionCategory();
//        category1.setName("Personal");
//
//        mTransactionCategoryRepository.save(category1);
//    }






}
