package cs.put.poznan.bsr;

import cs.put.poznan.bsr.model.Account;
import cs.put.poznan.bsr.model.Client;
import cs.put.poznan.bsr.repository.AccountRepository;
import cs.put.poznan.bsr.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.xml.ws.Endpoint;
import java.math.BigDecimal;

@SpringBootApplication
public class BsrApplication implements CommandLineRunner {

	@Autowired
    AccountRepository accountRepository;
	@Autowired
    ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(BsrApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {

		Account account = new Account("CCAAAAAAAABBBBBBBBBBBBBBBB", BigDecimal.valueOf(123.45));
        accountRepository.save(account);

		clientRepository.save(new Client("123","Jan","Nowak","1234"));
	}
}
