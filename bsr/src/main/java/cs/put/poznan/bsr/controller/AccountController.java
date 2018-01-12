package cs.put.poznan.bsr.controller;

import cs.put.poznan.bsr.model.Account;
import cs.put.poznan.bsr.model.History;
import cs.put.poznan.bsr.model.Transfer;
import cs.put.poznan.bsr.repository.AccountRepository;
import cs.put.poznan.bsr.repository.HistoryRepository;
import cs.put.poznan.bsr.utils.NrbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
public class AccountController {

    @Autowired
    private NrbService nrbService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @RequestMapping(path = "/accounts/{nrb}/history", method = RequestMethod.POST)
    public ResponseEntity get(@PathVariable String nrb, @RequestBody Transfer transfer) {

        if (!nrbService.validateNrb(nrb)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            Account accountByNrb = accountRepository.findAccountByNrb(nrb);

            if (accountByNrb == null)
                return new ResponseEntity(HttpStatus.NOT_FOUND);

            if (transfer == null)
                return new ResponseEntity("error", HttpStatus.BAD_REQUEST);

            History history = History.builder()
                    .balanceAfter(accountByNrb.getBalance())
                    .nrb(nrb)
                    .timestamp(new Date())
                    .build();

            BigDecimal newBalance = BigDecimal.valueOf(transfer.getAmount()).divide(BigDecimal.valueOf(100),2,BigDecimal.ROUND_HALF_EVEN).add(accountByNrb.getBalance());
            accountByNrb.setBalance(newBalance);
            accountRepository.save(accountByNrb);

            history.setBalanceBefore(accountByNrb.getBalance());
            history.setTransfer(transfer);
            historyRepository.save(history);

            return new ResponseEntity(HttpStatus.CREATED);
        }
    }
}
