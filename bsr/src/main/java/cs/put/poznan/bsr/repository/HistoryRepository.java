package cs.put.poznan.bsr.repository;

import cs.put.poznan.bsr.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface HistoryRepository extends MongoRepository<History, Date> {

    List<History> findAllByNrb(String nrb);
}
