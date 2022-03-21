package com.exchanger.api.exchangerapi.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PBKDF2PasswordEncoder implements PasswordEncoder {

    private final String secret = System.getenv("PASSWORD_SECRET");
    private final Integer iteration = Integer.parseInt(System.getenv("PASSWORD_ITERATION"));
    private final Integer keyLength = Integer.parseInt(System.getenv("PASSWORD_KEY_LENGTH"));

    /**
     * More info (https://www.owasp.org/index.php/Hashing_Java) 404 :(
     * @param cs password
     * @return encoded password
     */
    @Override
    public String encode(CharSequence cs) {
        try {
            byte[] result = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iteration, keyLength))
                .getEncoded();

            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        return encode(cs).equals(string);
    }
}
