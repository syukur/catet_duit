package com.lylastudio.catetduit.controller.webhook;

import com.lylastudio.catetduit.handler.Handler;
import com.lylastudio.catetduit.handler.HandlerHolder;
import com.lylastudio.catetduit.model.http.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/door")
public class DoorController {

    private final HandlerHolder handlerHolder;

    public DoorController(HandlerHolder handlerHolder) {
        this.handlerHolder = handlerHolder;
    }

    @PostMapping("/put")
    public String putMessage(@RequestBody Update update ){

        String keyword = update.getMessage()
                        .getText()
                        .toLowerCase()
                        .trim()
                        .replace(" ", "");

        String chatId = String.valueOf( update.getMessage().getChat().getId() );

        Handler handler = handlerHolder.getHandler(keyword,chatId);
        handler.prepare(update);
        handler.execute(update);
        handler.sendMessage();

        return "True";
    }

}
