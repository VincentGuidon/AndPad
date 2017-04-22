package com.andpad.andpad;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Neilug on 22/04/2017.
 */

public class SecurityString {

    public static  String nextSessionId() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
