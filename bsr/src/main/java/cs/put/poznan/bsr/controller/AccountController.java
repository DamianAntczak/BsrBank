package cs.put.poznan.bsr.controller;

import cs.put.poznan.bsr.model.Account;
import cs.put.poznan.bsr.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> get(){
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }
}
