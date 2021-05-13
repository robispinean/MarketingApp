package Controllers;

import Models.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item,String> NameCol;

    @FXML
    private TableColumn<Item,String> DescriptionCol;

    @FXML
    private TableColumn<Item,String> priceCol;

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

    public static List<Item> items = new ArrayList<>();

    public void getCartItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Item> data = getProducts();

        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(data);
    }

    ObservableList<Item> getProducts(){
        ObservableList<Item> data = FXCollections.observableArrayList();
        for(int i = 0; i < items.size(); i++){
            data.add(items.get(i));
        }
        return data;
    }

    public void returnHomePage() {
        try{
            closeButton.getScene().getWindow().hide();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
