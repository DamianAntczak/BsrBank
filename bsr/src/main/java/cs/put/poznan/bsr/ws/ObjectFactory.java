//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.19 at 10:31:47 AM CET 
//


package cs.put.poznan.bsr.ws;

import cs.put.poznan.bsr.model.Account;
import cs.put.poznan.bsr.model.Client;
import cs.put.poznan.bsr.model.History;
import cs.put.poznan.bsr.model.Transfer;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cs.put.poznan.bsr.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cs.put.poznan.bsr.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPaymentRequest }
     * 
     */
    public AddPaymentRequest createAddPaymentRequest() {
        return new AddPaymentRequest();
    }

    /**
     * Create an instance of {@link Payment }
     * 
     */
    public Payment createPayment() {
        return new Payment();
    }

    /**
     * Create an instance of {@link GetTransactionsRequest }
     * 
     */
    public GetTransactionsRequest createGetTransactionsRequest() {
        return new GetTransactionsRequest();
    }

    /**
     * Create an instance of {@link AddWithdrawalRequest }
     * 
     */
    public AddWithdrawalRequest createAddWithdrawalRequest() {
        return new AddWithdrawalRequest();
    }

    /**
     * Create an instance of {@link AddWithdrawalResponse }
     * 
     */
    public AddWithdrawalResponse createAddWithdrawalResponse() {
        return new AddWithdrawalResponse();
    }

    /**
     * Create an instance of {@link AddPaymentResponse }
     * 
     */
    public AddPaymentResponse createAddPaymentResponse() {
        return new AddPaymentResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link LoginRequest }
     * 
     */
    public LoginRequest createLoginRequest() {
        return new LoginRequest();
    }

    /**
     * Create an instance of {@link Authoryzation }
     * 
     */
    public Authoryzation createAuthoryzation() {
        return new Authoryzation();
    }

    /**
     * Create an instance of {@link GetAccountsResponse }
     * 
     */
    public GetAccountsResponse createGetAccountsResponse() {
        return new GetAccountsResponse();
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link GetAccountRequest }
     * 
     */
    public GetAccountRequest createGetAccountRequest() {
        return new GetAccountRequest();
    }

    /**
     * Create an instance of {@link GetAccountsRequest }
     * 
     */
    public GetAccountsRequest createGetAccountsRequest() {
        return new GetAccountsRequest();
    }

    /**
     * Create an instance of {@link MakeTransferResponse }
     * 
     */
    public MakeTransferResponse createMakeTransferResponse() {
        return new MakeTransferResponse();
    }

    /**
     * Create an instance of {@link GetClientRequest }
     * 
     */
    public GetClientRequest createGetClientRequest() {
        return new GetClientRequest();
    }

    /**
     * Create an instance of {@link GetTransactionsResponse }
     * 
     */
    public GetTransactionsResponse createGetTransactionsResponse() {
        return new GetTransactionsResponse();
    }

    /**
     * Create an instance of {@link History }
     * 
     */
    public History createHistory() {
        return new History();
    }

    /**
     * Create an instance of {@link GetAccountResponse }
     * 
     */
    public GetAccountResponse createGetAccountResponse() {
        return new GetAccountResponse();
    }

    /**
     * Create an instance of {@link MakeTransferRequest }
     * 
     */
    public MakeTransferRequest createMakeTransferRequest() {
        return new MakeTransferRequest();
    }

    /**
     * Create an instance of {@link Transfer }
     * 
     */
    public Transfer createTransfer() {
        return new Transfer();
    }

    /**
     * Create an instance of {@link GetClientResponse }
     * 
     */
    public GetClientResponse createGetClientResponse() {
        return new GetClientResponse();
    }

    /**
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

    /**
     * Create an instance of {@link Transaction }
     * 
     */
    public Transaction createTransaction() {
        return new Transaction();
    }

    /**
     * Create an instance of {@link Token }
     * 
     */
    public Token createToken() {
        return new Token();
    }

}
