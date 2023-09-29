package com.lylastudio.catetduit.controller.webhook;

import com.lylastudio.catetduit.handler.Handler;
import com.lylastudio.catetduit.handler.HandlerHolder;
import com.lylastudio.catetduit.model.http.Update;
import com.lylastudio.catetduit.util.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("api/v1/door")
public class DoorController {

    private final HandlerHolder handlerHolder;
    
    private final StringHelper stringHelper;

    public DoorController(HandlerHolder handlerHolder, StringHelper stringHelper) {
        this.handlerHolder = handlerHolder;
        this.stringHelper = stringHelper;
    }

    @PostMapping("/put")
    public String putMessage(@RequestBody Update update ){

        String text = update.getMessage()
                        .getText()
                        .trim();

        ArrayList<String> splitedString = stringHelper.splitString(text);
        
        String keyword = splitedString.get(0);
        keyword = keyword.toLowerCase();

        Handler handler = handlerHolder.getHandler(keyword,update);
        handler.setRequestParameter(splitedString);
        handler.setUpdate(update);
        
        handler.prepare();
        handler.execute();
        handler.sendMessage();

        return "True";
    }

    

}
