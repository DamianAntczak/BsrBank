package cs.put.poznan.bsr.repository;

import cs.put.poznan.bsr.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}
