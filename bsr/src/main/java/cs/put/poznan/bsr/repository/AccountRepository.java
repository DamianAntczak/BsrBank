package cs.put.poznan.bsr.repository;

import cs.put.poznan.bsr.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

}
