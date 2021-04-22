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
    Message msg = new Message();

    public boolean addUser() {
        try{
            if (service.addUserToDB(email.getText(),password.getText(),roleBox.getValue().toString())) {
                registerButton.getScene().getWindow().hide();
                Stage login = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
                Scene scene = new Scene(root);
                login.setScene(scene);
                login.show();
                msg.setConfirmationMessage("Registration Completed you can login!");
            }
            return true;
        }catch(Exception e){
            System.out.println(e);
            msg.setWarningMessage("Registration Failed!" + "\n" + e);
            return false;
        }
    }




}
