
package Controllers;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.util.*;

import AlertMessages.Message;
import javafx.stage.Stage;

public class LoginController {
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

    public boolean loginUser(){
        String hashPassword;
        List<User> users = service.getUsers();
        for(User u : users) {
            if(u.getEmail().equals(email.getText())) {
                hashPassword = service.hashPassword(password.getText(),u.getID());
                if(hashPassword.equals(u.getPassword())){
                    if(roleBox.getValue().toString().equals(u.getRole())){
                        //go to main page
                        return true;
                    }
                    else{
                        msg.setWarningMessage("Invalid role!");
                        return false;
                    }
                }
                else {
                    msg.setWarningMessage("Invalid password!");
                    return false;
                }
            }
        }
        msg.setWarningMessage("User not registered!");
        return false;
    }

    public void goToRegistration(){
        try {
            registerButton.getScene().getWindow().hide();
            Stage login = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Registration.fxml")));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
            msg.setConfirmationMessage("Registration Completed you can login!");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}