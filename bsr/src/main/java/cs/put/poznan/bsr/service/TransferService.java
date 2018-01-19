package cs.put.poznan.bsr.service;

import cs.put.poznan.bsr.model.Account;
import cs.put.poznan.bsr.model.History;
import cs.put.poznan.bsr.model.Transfer;
import cs.put.poznan.bsr.repository.AccountRepository;
import cs.put.poznan.bsr.repository.ClientRepository;
import cs.put.poznan.bsr.repository.HistoryRepository;
import cs.put.poznan.bsr.utils.NrbService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransferService {
    public static final String BANKS_ENDPOINTS_CSV = "banksEndpoints.csv";

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    NrbService nrbService;

    @Value("${send.user.name}")
    private String username;

    @Value("${send.user.password}")
    private String password;

    private Map<String, String> bankCodeToEndpointMap;

    @PostConstruct
    public void init() throws FileNotFoundException {
        bankCodeToEndpointMap = new HashMap<>();

        try {
            Reader in = new FileReader(new File(this.getClass().getClassLoader().getResource(BANKS_ENDPOINTS_CSV).getFile()));
            for (CSVRecord record : CSVFormat.RFC4180.parse(in)) {
                String key = record.get(0);
                String endpoint = record.get(1);
                bankCodeToEndpointMap.put(key, endpoint);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public boolean makeTransfer(String destinationAccount, Transfer transfer) {

        if(!nrbService.validateNrb(destinationAccount))
            return false;
        if(bankCodeToEndpointMap.get(nrbService.getBankCodeFromNrb(destinationAccount)) == null)
            return false;

        String uri = createUrl(destinationAccount);

        Account accountByNrb = accountRepository.findAccountByNrb(transfer.getSource_account());
        if (accountByNrb != null) {
            BigDecimal toCheck = accountByNrb.getBalance().subtract(BigDecimal.valueOf(transfer.getAmount()).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_EVEN));
            if (toCheck.compareTo(BigDecimal.ZERO) > 0) {

                RestTemplate restTemplate = new RestTemplate();
                HttpEntity entity = new HttpEntity(transfer, getHeaders());
                ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

                if(response.getStatusCode().equals(HttpStatus.UNAUTHORIZED))
                    return false;

                if (response.getStatusCode().equals(HttpStatus.CREATED)) {

                    History history = History.builder()
                            .destination(History.Destination.OUTFLOW)
                            .balanceAfter(accountByNrb.getBalance())
                            .nrb(transfer.getSource_account())
                            .timestamp(new Date())
                            .build();

                    BigDecimal amountInPln = BigDecimal.valueOf(transfer.getAmount()).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_EVEN);
                    BigDecimal newAccountBalance = accountByNrb.getBalance().subtract(amountInPln);
                    accountByNrb.setBalance(newAccountBalance);
                    accountRepository.save(accountByNrb);

                    history.setBalanceBefore(accountByNrb.getBalance());
                    history.setTransfer(transfer);
                    historyRepository.save(history);

                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

    private String createUrl(String destinationAccount) {
        return getEndpoint(destinationAccount) + "/accounts/" + destinationAccount + "/history";
    }

    private String getEndpoint(String nrb) {
        String bankCodeFromNrb = nrbService.getBankCodeFromNrb(nrb);
        return bankCodeToEndpointMap.get(bankCodeFromNrb);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String plainCreds = String.format("%s:%s", username, password);
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        base64Creds = "Basic " + base64Creds;
        headers.add("Authorization", base64Creds);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
