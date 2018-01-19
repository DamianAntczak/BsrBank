package cs.put.poznan.bsr.service;

import cs.put.poznan.bsr.model.Client;
import cs.put.poznan.bsr.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class SessionsService {

    @Autowired
    private ClientRepository clientRepository;

    private HashMap<String, String> tokenMap;

    public SessionsService(){
        this.tokenMap = new HashMap<>();
    }

    public boolean isTokenRegister(String token){
        String tokenFromMap = tokenMap.get(token);
        return tokenFromMap != null;
    }

    private String getToken(String clientId){
        String uuid = UUID.randomUUID().toString();
        tokenMap.put(uuid,clientId);
        return uuid;
    }

    public String login(String clientId, String password){
        Client client = clientRepository.getClientById(clientId);
        if (client != null && client.getPassword().equals(password)){
            return getToken(clientId);
        }else {
            return null;
        }
    }
}
