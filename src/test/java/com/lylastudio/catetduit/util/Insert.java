package com.lylastudio.catetduit.util;

import com.lylastudio.catetduit.CatetduitApplication;
import com.lylastudio.catetduit.db.entity.MHandler;
import com.lylastudio.catetduit.db.repository.MHandlerRepository;
import com.lylastudio.catetduit.db.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Insert {

    @Test
    void insertHandler(){
        ApplicationContext applicationContext= new AnnotationConfigApplicationContext(CatetduitApplication.class);
        MHandlerRepository mHandlerRepository = applicationContext.getBean(MHandlerRepository.class);

        MHandler handler1 = new MHandler();
        handler1.setKeyword("gus");
        handler1.setHandler("com.dwidasa.com");

        mHandlerRepository.save(handler1);
    }
}
