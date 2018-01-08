package cs.put.poznan.bsr;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Damian on 25.12.2017.
 */

@WebService
public class TestEndpoint {

    @WebMethod
    public String sayHello(){
        return "Test";
    }
}
