package com.lylastudio.catetduit.controller.webhook;

import com.lylastudio.catetduit.handler.Handler;
import com.lylastudio.catetduit.handler.HandlerHolder;
import com.lylastudio.catetduit.model.http.Update;
import com.lylastudio.catetduit.db.repository.TransactionRepository;
import com.lylastudio.catetduit.util.JSONFormater;
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

        String keyword = update.getMessage().getText();
        Handler handler = handlerHolder.getHandler(keyword);
        handler.prepare(update);
        handler.execute(update);
        handler.sendMessage();

        return "True";
    }

}
