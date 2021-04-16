package Controllers;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import AlertMessages.Message;
import javafx.stage.Stage;

public class RegistrationController {
    ObservableList role = FXCollections.observableArrayList(
            "Customer",
                "Employee"
                );
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private ChoiceBox roleBox;
    @FXML
    private Button registerButton;
    @FXML
    private void initialize(){
        roleBox.setValue("Customer");
        roleBox.setItems(role);
    }

    UserService service = new UserService();
    DatabaseController c= new DatabaseController();
    Message msg = new Message();

    private String createID(){
        String id = UUID.randomUUID().toString();
        return id;
    }

    public boolean addUser(){
        try {
            if (!service.Validate(email,password)){
                msg.setMessage("Invalid email or password");
                return false;
            }

            List<User> users = getUsers();
            Connection con=c.connectDatabase();
            System.out.println("connected to database");

            String id = createID();
            String EncodedPassword=service.hashPassword(password,id);

            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO APP_USERS VALUES ('" + id +"', '" + EncodedPassword + "', '" + roleBox.getValue().toString() + "', '" + email.getText() + "')");

            User newUser = new User(id, email.getText(), roleBox.getValue().toString());
            users.add(newUser);

            System.out.println("Added new user with id: " + id + " email: " + email.getText() + " Role: " + roleBox.getValue().toString());
            registerButton.getScene().getWindow().hide();
            Stage login = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();

            c.closeConnection(con);
            return true;
        }catch(Exception e){
            System.out.println(e);
            msg.setMessage("Registration Failed!");
            return false;
        }
    }


    public List<User> getUsers(){
        try {
            Connection con = c.connectDatabase();
            List<User> users = new ArrayList<>();
            final String str= "SELECT * FROM APP_USERS";
            java.sql.PreparedStatement stmt=con.prepareStatement(str);
            ResultSet results=stmt.executeQuery();
            while(results.next()){
                User s = new User(results.getString("id"), results.getString("email"), results.getString("role"));
                users.add(s);
            }
            return users;
        }catch(Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }
    }

}
