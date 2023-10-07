package com.lylastudio.catetduit.handler;

import com.lylastudio.catetduit.db.entity.MAccount;
import com.lylastudio.catetduit.db.repository.MAccountRespository;
import com.lylastudio.catetduit.db.repository.MHandlerRepository;
import com.lylastudio.catetduit.model.http.Update;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class HandlerHolder {
    private HashMap<String, Handler> handlers;

    private MAccountRespository mAccountRespository;


    public HandlerHolder(MAccountRespository mAccountRespository){
        this.mAccountRespository = mAccountRespository;
        handlers = new HashMap<>();
    }

    public Handler getHandler(String keyword, Update update){

        if(notRegisteredAccount(update)){
            return handlers.get("not-registered");
        }

        Handler handler = handlers.get(keyword);
        if(handler == null){
            handler = handlers.get("keyword-not-define");
        }

        return handler;
    }


    public void putHandler(String keyword, Handler handler){
        handlers.put(keyword, handler);
    }

//    private Handler tryGetAnotherHandler(String keyword) {
//        String[] data = keyword.split("\\:");
//
//        String newKeyword= data[0];
//        Handler handler = handlers.get(newKeyword);
//        if( handler == null){
//            handler = handlers.get("keyword-not-define");
//        }
//
//        return handler;
//    }

    private boolean notRegisteredAccount(Update update){

        String fromId = String.valueOf(update.getMessage().getFrom().getId());
        MAccount account = mAccountRespository.findByFromId(fromId);

        return (account == null) ;

    }

    public HashMap<String, String> getInfo(){
        HashMap<String,String>result = new HashMap<>();

        handlers.forEach((key, value)->{
            result.put(key, value.handlerConfig.getHandler());
        });

        return result;

    }
}
