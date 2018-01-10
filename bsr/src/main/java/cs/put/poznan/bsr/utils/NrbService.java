package cs.put.poznan.bsr.utils;


import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigInteger;

@Service
public class NrbService {

    public static final String BANK_CODE = "10907276";
    public static final int ACCOUNT_NUMBER_LENGTH = 16;
    public static final String PL_CODE = "252100";


    public String genereteNbr(){

        String accountNumberString = RandomStringUtils.random(ACCOUNT_NUMBER_LENGTH, false, true);
        String nr = BANK_CODE+accountNumberString+PL_CODE;

        int modulo = 0;
        for (char znak : nr.toCharArray()) {
            modulo = (10 * modulo + (int) znak) % 97;
            modulo = 98 - modulo;
        }
        int checkSum = 98 - modulo;

        return String.format("%d%s", checkSum,BANK_CODE+accountNumberString);
    }
}
