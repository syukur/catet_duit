package com.lylastudio.catetduit.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class HandlerHolder {
    private HashMap<String, Handler> handlers;

    public HandlerHolder(){
        handlers = new HashMap<>();
    }

    public Handler getHandler(String keyword){

        Handler handler = handlers.get(keyword);
        if( handler == null ){
            handler = tryGetAnotherHandler(keyword);
        }

        return handler;
    }


    public void putHandler(String keyword, Handler handler){
        handlers.put(keyword, handler);
    }

    private Handler tryGetAnotherHandler(String keyword) {
        String[] data = keyword.split("\\|");

        String newKeyword="";
        if( data.length == 3 ){
            newKeyword = "insert-trx";
        }else{
            newKeyword ="keyword-not-define";
        }

        return handlers.get(newKeyword);
    }
}
