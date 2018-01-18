package cs.put.poznan.bsr.endpoints;



import cs.put.poznan.bsr.model.*;
import cs.put.poznan.bsr.model.Account;
import cs.put.poznan.bsr.model.Client;
import cs.put.poznan.bsr.model.History;
import cs.put.poznan.bsr.repository.AccountRepository;
import cs.put.poznan.bsr.repository.ClientRepository;
import cs.put.poznan.bsr.repository.HistoryRepository;
import cs.put.poznan.bsr.service.TransferService;
import cs.put.poznan.bsr.utils.NrbService;
import cs.put.poznan.bsr.ws.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Endpoint
public class AccountsEndpoint {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private NrbService nrbService;

    @Autowired
    private TransferService transferService;

    @Value( "${transfer.user.name}" )
    private String username;

    @Value( "${transfer.user.password}" )
    private String password;

    private static final String NAMESPACE_URI = "http://bsr.poznan.put.cs/ws";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccountsRequest")
    @ResponsePayload
    public GetAccountsResponse getAccounts(@RequestPayload GetAccountsRequest request) {

        GetAccountsResponse response = new GetAccountsResponse();

        List<Account> accounts = response.getAccounts();
        accounts.addAll(accountRepository.findAccountByClientId(request.getClientId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccountRequest")
    @ResponsePayload
    public GetAccountResponse getAccount(@RequestPayload GetAccountRequest request){

        Account accountByNrb = accountRepository.findAccountByNrb(request.getAccountNbr());

        GetAccountResponse getAccountResponse = new GetAccountResponse();
        getAccountResponse.setAccount(accountByNrb);
        return getAccountResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPaymentRequest")
    @ResponsePayload
    public AddPaymentResponse addPayment(@RequestPayload AddPaymentRequest addPaymentRequest){

        Payment payment = addPaymentRequest.getPayment();

        if(!nrbService.validateNrb(payment.getNrb()))
            throw new IllegalArgumentException("bad nrb");

        Account accountByNbr = accountRepository.findAccountByNrb(payment.getNrb());
        
        if(payment.getAmount() > 0){
            History history = History.builder().timestamp(new Date())
                    .nrb(payment.getNrb())
                    .balanceBefore(accountByNbr.getBalance())
                    .destination(History.Destination.PAYMENT)
                    .build();

            BigDecimal amount = accountByNbr.getBalance().add(BigDecimal.valueOf(payment.getAmount()));
            accountByNbr.setBalance(amount);
            accountRepository.save(accountByNbr);

            history.setBalanceAfter(amount);

            Transfer transfer = getTransfer(payment, accountByNbr, amount);
            history.setTransfer(transfer);
            historyRepository.save(history);
        }

        AddPaymentResponse addPaymentResponse = new AddPaymentResponse();
        addPaymentResponse.setMassage("OK");
        return addPaymentResponse;
    }

    private Transfer getTransfer(Payment payment, Account accountByNbr, BigDecimal amount) {
        Client clientById = clientRepository.getClientById(accountByNbr.getClientId());
        Transfer transfer = new Transfer();
        transfer.setTitle(payment.getTitle());
        transfer.setDestination_name(clientById.getName());
        transfer.setSource_account(clientById.getId());
        transfer.setAmount((int)amount.doubleValue()*100);
        return transfer;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addWithdrawalRequest")
    @ResponsePayload
    public AddWithdrawalResponse addWithdrawal(@RequestPayload  AddWithdrawalRequest addWithdrawalRequest){
        Payment payment = addWithdrawalRequest.getPayment();

        if(!nrbService.validateNrb(payment.getNrb()))
            throw new IllegalArgumentException("bad nrb");

        Account accountByNbr = accountRepository.findAccountByNrb(payment.getNrb());

        if(payment.getAmount() > 0){
            History history = History.builder().timestamp(new Date())
                    .nrb(payment.getNrb())
                    .balanceBefore(accountByNbr.getBalance())
                    .destination(History.Destination.WITHDRAWAL)
                    .build();

            BigDecimal amount = accountByNbr.getBalance().subtract(BigDecimal.valueOf(payment.getAmount()));
            accountByNbr.setBalance(amount);
            accountRepository.save(accountByNbr);

            history.setBalanceAfter(amount);

            Transfer transfer = getTransfer(payment, accountByNbr, amount);
            history.setTransfer(transfer);
            historyRepository.save(history);
        }

        AddWithdrawalResponse addWithdrawalResponse = new AddWithdrawalResponse();
        addWithdrawalResponse.setMessage("OK");
        return addWithdrawalResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientRequest")
    @ResponsePayload
    public GetClientResponse getClientRequest(@RequestPayload GetClientRequest getClientRequest){

        Client clientById = clientRepository.getClientById(getClientRequest.getClientId());

        GetClientResponse getClientResponse = new GetClientResponse();
        getClientResponse.setClient(clientById);

        return getClientResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTransactionsRequest")
    @ResponsePayload
    public GetTransactionsResponse getTransactionsRequest(@RequestPayload GetTransactionsRequest getTransactionsRequest){

        GetTransactionsResponse getTransactionsResponse = new GetTransactionsResponse();
        List<History> allByNrb = getTransactionsResponse.getTransactions();
        allByNrb.addAll(historyRepository.findAllByNrb(getTransactionsRequest.getNrb()));
        return getTransactionsResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "makeTransferRequest")
    @ResponsePayload
    public MakeTransferResponse makeTransferRequest(@RequestPayload MakeTransferRequest makeTransferRequest){

        Transfer transfer = makeTransferRequest.getTransfer();
        String destinationAccount = makeTransferRequest.getDestinationAccount().replace("\\s+","");

        if(!nrbService.validateNrb(transfer.getSource_account()))
            throw new IllegalArgumentException("source_account");

        if(!nrbService.validateNrb(transfer.getSource_account()))
            throw new IllegalArgumentException("destination_account");

        if(transfer.getTitle().isEmpty())
            throw new IllegalArgumentException("empty title");

        if(!nrbService.validateNrb(destinationAccount))
            throw new IllegalArgumentException("wrong destinationAccount");

        MakeTransferResponse makeTransferResponse = new MakeTransferResponse();

        if(transferService.makeTransfer(destinationAccount, transfer)) {
            makeTransferResponse.setStatus("S");
            makeTransferResponse.setMassage("ok");
        }
        else{
            makeTransferResponse.setStatus("E");
            makeTransferResponse.setMassage("ok");
        }
        return makeTransferResponse;
    }
}
