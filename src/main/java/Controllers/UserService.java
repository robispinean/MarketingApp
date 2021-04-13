package Controllers;

import AlertMessages.Message;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    Message msg = new Message();

    String hashPassword(PasswordField p, String salt) {
        try {
            // use PBKDF2 algorithm to encode the password
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());

            byte[] messageDigest = md.digest(p.getText().getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            // convert message digest into hex value
            String hashtext = no.toString(16);

            return hashtext;
        }
        // handle missing/wrongly named algorithms
        catch (NoSuchAlgorithmException e) {
            msg.setMessage("Something went wrong!");
            throw new RuntimeException(e);
        }
    }

     boolean Validate(TextField email, PasswordField password){
        String e = email.getText();
        String p = password.getText();
        if(e.length() >= 10 && e.length() <=30 && p.length() >= 6)
            return true;
        return false;
    }

}
