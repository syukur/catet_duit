package com.lylastudio.catetduit.controller.web;

import com.lylastudio.catetduit.db.repository.TransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("web/v1/")
public class WebController {

    private final TransactionRepository transactionRepository;

    public WebController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/report")
    public String home(Model model){
        model.addAttribute("trxs", transactionRepository.findByTelegramFromId("70855858"));
        model.addAttribute("total", transactionRepository.calculateAmountBySenderId("70855858"));
        return "index";
    }
}
