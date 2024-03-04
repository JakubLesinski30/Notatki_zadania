package Notatki_zadania.Notatki_i_zadania;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Szyfrowanie {

    private final SecretKey secretKey;

    public Szyfrowanie(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public String szyfruj(String dane) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(dane.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String deszyfruj(String zaszyfrowaneDane) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(zaszyfrowaneDane));
        return new String(decryptedBytes);
    }
}