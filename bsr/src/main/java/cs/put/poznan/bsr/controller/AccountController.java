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

    public static final int MAX_FILD_LENTGH = 1024;
    @Autowired
    private NrbService nrbService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @RequestMapping(path = "/accounts/{nrb}/history", method = RequestMethod.POST)
    public ResponseEntity get(@PathVariable String nrb, @RequestBody Transfer transfer) {

        if (nrb == null || !nrbService.validateNrb(nrb)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            Account accountByNrb = accountRepository.findAccountByNrb(nrb);

            if (accountByNrb == null)
                return new ResponseEntity(HttpStatus.NOT_FOUND);

            if (!validateTransfer(transfer))
                return new ResponseEntity(HttpStatus.BAD_REQUEST);

            History history = History.builder()
                    .destination(History.Destination.INCOME)
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

    private boolean validateTransfer(Transfer transfer){

        if(transfer == null)
            return false;

        if(transfer.getAmount() <= 0 || transfer.getAmount() > 1000000000)
            return  false;

        if(transfer.getSource_account() == null || !nrbService.validateNrb(transfer.getSource_account()))
            return false;

        if (transfer.getTitle().isEmpty() || transfer.getTitle().length() > MAX_FILD_LENTGH)
            return false;

        if(transfer.getSource_name().length() > MAX_FILD_LENTGH)
            return false;

        if(transfer.getDestination_name().isEmpty() || transfer.getDestination_name().length() >= MAX_FILD_LENTGH)
            return false;

        return true;
    }
}
