package cs.put.poznan.bsr;


import cs.put.poznan.bsr.model.Account;
import cs.put.poznan.bsr.model.Client;
import cs.put.poznan.bsr.repository.AccountRepository;
import cs.put.poznan.bsr.repository.ClientRepository;
import cs.put.poznan.bsr.utils.NrbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class BsrApplication implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    NrbService nrbService;

    public static void main(String[] args) {
        SpringApplication.run(BsrApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {

        List<Account> accountList = accountRepository.findAll();

        if(accountList.isEmpty()) {

            Account account = new Account();
            account.setBalance(BigDecimal.valueOf(0));

            String generatedNrb = nrbService.generateNrb();

            System.out.println(nrbService.format(generatedNrb));

            account.setNrb(generatedNrb);
            account.setClientId("BSR");
            accountRepository.save(account);

            clientRepository.save(new Client("BSR", "Jan", "Nowak", "bsr1"));

            Account accountB = new Account();
            accountB.setBalance(BigDecimal.valueOf(0));
            String generatedNrbB = nrbService.generateNrb();
            accountB.setNrb(generatedNrbB);
            accountB.setClientId("BSR");
            accountRepository.save(accountB);
        }
    }
}
