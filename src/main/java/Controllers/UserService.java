package Controllers;

import javafx.scene.control.PasswordField;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    String hashPassword(PasswordField p) {
        try {
            // use PBKDF2 algorithm to engode the password
            MessageDigest md = MessageDigest.getInstance("PBKDF2");

            byte[] messageDigest = md.digest(p.getText().getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            // convert message digest into hex value
            String hashtext = no.toString(16);

            return hashtext;
        }
        // handle missing/wrongly named algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
