package Controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.UUID;
import AlertMessages.Message;

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

    public boolean addUser() throws IOException {
        try {
            if (!service.Validate(email,password)){
                msg.setMessage("Invalid email or password");
                return false;
            }

            Connection con=c.connectDatabase();
            System.out.println("connected to database");

            String id = createID();
            String EncodedPassword=service.hashPassword(password,id);

            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO APP_USERS VALUES ('" + id +"', '" + EncodedPassword + "', '" + roleBox.getValue().toString() + "', '" + email.getText() + "')");
            System.out.println("Added new user with id: " + id + " name: " + email.getText() + " Role: " + roleBox.getValue().toString());

            msg.setMessage("Registration Complete!");
            c.closeConnection(con);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

}
