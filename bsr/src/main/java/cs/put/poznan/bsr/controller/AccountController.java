package cs.put.poznan.bsr.controller;

import cs.put.poznan.bsr.model.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(method = RequestMethod.GET)
    public Account get(){
        Account account = new Account("CCAAAAAAAABBBBBBBBBBBBBBBB",BigDecimal.valueOf(123.45));
        return account;
    }
}
