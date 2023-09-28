package com.lylastudio.catetduit.handler;

import com.lylastudio.catetduit.db.repository.MAccountRespository;
import com.lylastudio.catetduit.db.repository.MHandlerRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class HandlerHolder {
    private HashMap<String, Handler> handlers;



    public HandlerHolder(){
        handlers = new HashMap<>();
    }

    public Handler getHandler(String keyword, String chatId){

        Handler handler = handlers.get(keyword);
        if( handler == null ){
            handler = tryGetAnotherHandler(keyword, chatId);
        }

        return handler;
    }


    public void putHandler(String keyword, Handler handler){
        handlers.put(keyword, handler);
    }

    private Handler tryGetAnotherHandler(String keyword, String chatId) {
        String[] data = keyword.split("\\:");

        String newKeyword= data[0];
        Handler handler = getHandler(newKeyword);
        if( handler == null){
            handler = getHandler("keyword-not-define");
        }

        return handler;
    }

    public HashMap<String, String> getInfo(){
        HashMap<String,String>result = new HashMap<>();

        handlers.forEach((key, value)->{
            result.put(key, value.handlerConfig.getHandler());
        });

        return result;

    }
}
