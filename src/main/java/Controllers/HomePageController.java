package Controllers;

import Models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable{
    ItemService is = new ItemService();

    @FXML
    private TextField search;

    @FXML
    private Label ChosenItemName;

    @FXML
    private Label ChosenItemPrice;

    @FXML
    private ImageView imageLeft;

    @FXML
    private Text email;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private HBox ItemLayout;

    @FXML
    private Button CartButton;

    public Button AddToCart;


    private CartController cc = new CartController();
    private List<Item> items;
    private Listener clickListener;
    private Item chosenItem;
    private List<Item> cart = new ArrayList<>();

    private void setChosenItem(Item item) {
        ChosenItemName.setText(item.getName());
        ChosenItemPrice.setText(ItemController.CURRENCY + item.getPrice());
        try {
            Image image = new Image(item.getImagePath());
            imageLeft.setImage(image);
            chosenItem = new Item(item.getId(),item.getName(), item.getDescription(), item.getPrice(),item.getImagePath());
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void addItemToCart() {
        cart.add(chosenItem);
        System.out.println(cart);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        items = is.getItems();
        if(items.size() > 0){
            setChosenItem(items.get(0));
            clickListener = item -> setChosenItem(item);
        }
        int column=0;
        int row=1;
        try {
            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getClassLoader().getResource("Item.fxml"));
                AnchorPane ItemBox = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(items.get(i),clickListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(ItemBox, column++, row);
                GridPane.setMargin(ItemBox, new Insets(0));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void openCart() {
        try{
            cc.getCartItems(cart);
            Stage cartStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Cart.fxml")));
            Scene scene = new Scene(root);
            cartStage.setScene(scene);
            cartStage.initStyle(StageStyle.UNDECORATED);
            cartStage.initModality(Modality.APPLICATION_MODAL);
            cartStage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}