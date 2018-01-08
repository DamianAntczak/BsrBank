package cs.put.poznan.bsr.endpoints;



import cs.put.poznan.bsr.*;
import cs.put.poznan.bsr.repository.AccountRepository;
import cs.put.poznan.bsr.ws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;


@Endpoint
public class AccountsEndpoint {

    @Autowired
    AccountRepository accountRepository;

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

        Account accountByNrb = accountRepository.findAccountByNbr(request.getAccountNbr());

        GetAccountResponse getAccountResponse = new GetAccountResponse();
        getAccountResponse.setAccount(accountByNrb);
        return getAccountResponse;
    }

}
