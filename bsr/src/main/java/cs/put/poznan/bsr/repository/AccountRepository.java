package cs.put.poznan.bsr.repository;


import cs.put.poznan.bsr.ws.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {

    List<Account> findAccountByClientId(String clientId);

    Account findAccountByNbr(String nbr);
}
