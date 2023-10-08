package com.lylastudio.catetduit.controller.web;

import com.lylastudio.catetduit.db.entity.TOneTimeAccess;
import com.lylastudio.catetduit.db.repository.TOneTimeAccessRepository;
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

    private final TOneTimeAccessRepository tOneTimeAccessRepository;

    public WebController(TransactionRepository transactionRepository,
                         TOneTimeAccessRepository tOneTimeAccessRepository) {

        this.transactionRepository = transactionRepository;
        this.tOneTimeAccessRepository = tOneTimeAccessRepository;
    }

    @GetMapping("/report")
    public String report(Model model, @RequestParam(value = "r") String signature){

        log.info("token__ : {}", signature);

        TOneTimeAccess access = tOneTimeAccessRepository.findBySignature(signature);

        LocalDate to = LocalDate.now().withDayOfMonth(25);
        LocalDate from = to.minusMonths(1);

        BetweenSearchInput searchInput = new BetweenSearchInput();
        searchInput.setFrom(from.toString());
        searchInput.setTo(to.toString());
        searchInput.setToken(signature);

        model.addAttribute("searchInput", searchInput);
        model.addAttribute("trxs", transactionRepository.findBetween(from.toString(), to.toString(),access.getFromId()));
        model.addAttribute("total", transactionRepository.calculateAmountBySenderId(access.getFromId()));

        return "report-header";
    }

    @PostMapping("/report")
    public String search(Model model, @ModelAttribute("searchInput") BetweenSearchInput searchInput){
        log.info("from: {}, to: {}",searchInput.getFrom(), searchInput.getTo());
        TOneTimeAccess access = tOneTimeAccessRepository.findBySignature(searchInput.getToken());
        model.addAttribute("trxs", transactionRepository.findBetween(searchInput.getFrom(),searchInput.getTo(),access.getFromId()));
        model.addAttribute("total", transactionRepository.calculateAmountBySenderId(access.getFromId()));
        return "report-header";
    }

}
