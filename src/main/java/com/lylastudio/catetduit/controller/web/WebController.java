package com.lylastudio.catetduit.controller.web;

import com.lylastudio.catetduit.db.repository.TransactionRepository;
import com.lylastudio.catetduit.model.web.BetweenSearchInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("web/v1/")
public class WebController {

    private final TransactionRepository transactionRepository;

    public WebController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/report")
    public String report(Model model, @RequestParam(value = "r", defaultValue = "default-val") String token){

        log.info("token__ : {}", token);

        LocalDate to = LocalDate.now().withDayOfMonth(25);
        LocalDate from = to.minusMonths(1);


        BetweenSearchInput searchInput = new BetweenSearchInput();
        searchInput.setFrom(from.toString());
        searchInput.setTo(to.toString());
        searchInput.setToken(token);

        model.addAttribute("searchInput", searchInput);
        model.addAttribute("trxs", transactionRepository.findBetween(from.toString(), to.toString(),"70855858"));
        model.addAttribute("total", transactionRepository.calculateAmountBySenderId("70855858"));
        return "report-header";
    }

    @PostMapping("/report")
    public String search(Model model, @ModelAttribute("searchInput") BetweenSearchInput searchInput){
        log.info("from: {}, to: {}",searchInput.getFrom(), searchInput.getTo());
        model.addAttribute("trxs", transactionRepository.findBetween(searchInput.getFrom(),searchInput.getTo(),"70855858"));
        model.addAttribute("total", transactionRepository.calculateAmountBySenderId("70855858"));
        return "report-header";
    }

//    public static void main(String[] args) {
//
//        LocalDate from = LocalDate.now().withDayOfMonth(25);
//        log.info("month: {}", from.toString());
//
//        LocalDate to = from.plusMonths(1);
//        log.info("month: {}", to.toString());
//    }
}
