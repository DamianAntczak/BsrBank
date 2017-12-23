package cs.put.poznan.bsr;

import cs.put.poznan.bsr.model.Account;
import cs.put.poznan.bsr.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class BsrApplication implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(BsrApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        Account account = new Account("CCAAAAAAAABBBBBBBBBBBBBBBB", BigDecimal.valueOf(123.45));
        accountRepository.save(account);
    }
}
