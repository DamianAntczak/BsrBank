package cs.put.poznan.bsr;

import cs.put.poznan.bsr.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class GuiWebService {

    @Autowired
    private ClientRepository clientRepository;


}
