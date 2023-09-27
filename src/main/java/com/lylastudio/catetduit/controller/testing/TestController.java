package com.lylastudio.catetduit.controller.testing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/echo")
    public HashMap<String,String> testing(){
        HashMap<String, String> response = new HashMap<>();
        response.put("message","success");
        response.put("ok","true");
        return response;
    }

}
