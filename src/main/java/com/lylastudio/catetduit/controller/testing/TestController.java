package com.lylastudio.catetduit.controller.testing;

import com.lylastudio.catetduit.handler.HandlerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    HandlerHolder handlerHolder;

    public TestController(HandlerHolder handlerHolder){
        this.handlerHolder = handlerHolder;
    }

    @GetMapping("/echo")
    public HashMap<String,String> testing(){
        HashMap<String, String> response = new HashMap<>();
        response.put("message","success");
        response.put("ok","true");
        return response;
    }

    @GetMapping("/show-handlers")
    public HashMap<String,String> showHandlers(){
        return handlerHolder.getInfo();
    }

}
