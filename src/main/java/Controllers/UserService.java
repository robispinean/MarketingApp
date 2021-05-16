package Controllers;

import AlertMessages.Message;
import Models.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
    DatabaseController c= new DatabaseController();
    Message msg = new Message();

    String hashPassword(String p, String salt) {
        try {
            // use PBKDF2 algorithm to encode the password
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());

            byte[] messageDigest = md.digest(p.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            // convert message digest into hex value
            String hashtext = no.toString(16);

            return hashtext;
        }
        // handle missing/wrongly named algorithms
        catch (NoSuchAlgorithmException e) {
            msg.setWarningMessage("Something went wrong!");
            throw new RuntimeException(e);
        }
    }

     boolean Validate(String email, String password){
        if(email.length() >= 10 && email.length() <=30 && password.length() >= 6)
            return true;
        return false;
    }

    public List<User> getUsers(){
        try {
            Connection con = c.connectDatabase();
            List<User> users = new ArrayList<User>();
            final String str= "SELECT * FROM APP_USERS";
            java.sql.PreparedStatement stmt=con.prepareStatement(str);
            ResultSet results=stmt.executeQuery();
            while(results.next()){
                User s = new User(results.getString("id"), results.getString("email"), results.getString("role"));
                users.add(s);
            }
            c.closeConnection(con);
            return users;
        }catch(Exception e){
            System.out.println(e);
            return new ArrayList<User>();
        }
    }

    private String createID(){
        String id = UUID.randomUUID().toString();
        return id;
    }

    public boolean addUserToDB(String email, String password, String role){
        try {
            if (!Validate(email,password)){
                msg.setWarningMessage("Invalid email or password");
                return false;
            }

            List<User> users = getUsers();
            for(User u : users) {
                if(u.getEmail().equals(email)) {
                    throw new Exception("User already registered.");
                }
            }

            Connection con=c.connectDatabase();
            System.out.println("connected to database");

            String id = createID();
            String EncodedPassword=hashPassword(password,id);

            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO APP_USERS VALUES ('" + id +"', '" + EncodedPassword + "', '" + role + "', '" + email + "')");

            User newUser = new User(id, email, role);
            users.add(newUser);

            System.out.println("Added new user with id: " + id + " email: " + email + " Role: " + role);


            c.closeConnection(con);
            return true;
        }catch(Exception e){
            System.out.println(e);
            msg.setWarningMessage("Registration Failed!" + "\n" + e);
            return false;
        }
    }

}
