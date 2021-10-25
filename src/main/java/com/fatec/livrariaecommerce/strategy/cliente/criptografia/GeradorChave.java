package com.fatec.livrariaecommerce.strategy.cliente.criptografia;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class GeradorChave {

    public static final String ALGORITHM = "AES";
    public static final byte[] keyValue = new byte[32];

    public static Key generateKey() {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }
}
