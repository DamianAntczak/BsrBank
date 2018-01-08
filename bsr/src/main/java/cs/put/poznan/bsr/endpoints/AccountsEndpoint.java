package cs.put.poznan.bsr.endpoints;


import cs.put.poznan.bsr.GetAccounts;
import cs.put.poznan.bsr.GetAccountsResponse;
import cs.put.poznan.bsr.model.Account;
import cs.put.poznan.bsr.repository.AccountRepository;
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

    private static final String NAMESPACE_URI = "http://bsr.poznan.put.cs";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccounts")
    @ResponsePayload
    public GetAccountsResponse getAccounts(@RequestPayload GetAccounts request) {
        GetAccountsResponse response = new GetAccountsResponse();

        List<GetAccountsResponse.Accounts> accounts = response.getAccounts();

        List<Account> all = accountRepository.findAll();
        all.forEach(account -> {
            GetAccountsResponse.Accounts accountRes = new GetAccountsResponse.Accounts();
            accountRes.setAmount(account.getAmount().intValue());
            accountRes.setNbr(account.getNrb());
            accounts.add(accountRes);
        });

        return response;
    }

}
