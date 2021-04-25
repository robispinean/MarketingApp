package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

public class CartController {
    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> NameCol;

    @FXML
    private TableColumn<?, ?> DescriptionCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private ChoiceBox ChoiceBox;

    @FXML
    private Button CheckoutButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button closeButton;

    public void returnHomePage() {
        try{
            closeButton.getScene().getWindow().hide();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
