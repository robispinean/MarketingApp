package Controllers;

import Models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Button AddToCart;

    private List<Item> items;
    private Listener clickListener;
    private Item chosenItem;
    private List<Item> cartItems = new ArrayList<>();

    private void setChosenItem(Item item) {
        chosenItem =new Item(item.getId(),item.getName(),item.getDescription(),item.getPrice(),item.getImagePath());
        ChosenItemName.setText(item.getName());
        ChosenItemPrice.setText(ItemController.CURRENCY + item.getPrice());
        try {
            Image image = new Image("img/item" + item.getId() + ".png");
            imageLeft.setImage(image);
        }catch(Exception e){
            System.out.println("Chosen Item:" + item.getName() + " " + item.getImagePath()+ " " + e);
            Image image = new Image("img/item1.png");
            imageLeft.setImage(image);
        }
    }

    private Item getChosenItem(){
        return chosenItem;
    }

    public void addCart() {
        addToList(getChosenItem());
    }

    public void addToList(Item item){
        cartItems.add(item);
        System.out.println(cartItems);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        items = is.getItems();
        if(items.size() > 0){
            setChosenItem(items.get(0));
            clickListener = new Listener() {
                @Override
                public void onClickListener(Item item) {
                    setChosenItem(item);
                }
            };
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
                GridPane.setMargin(ItemBox, new Insets(10));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}