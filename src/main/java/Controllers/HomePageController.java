package Controllers;

import Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.List;
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

    private List<Item> items;
    private Listener clickListener;

    private void setChosenItem(Item item) {
        ChosenItemName.setText(item.getName());
        ChosenItemPrice.setText(ItemController.CURRENCY + item.getPrice());
        try {
            System.out.println(item.getName() + " " + item.getImagePath());
            String imagePath = "src/main/resources/img/item2.png";
            Image image = new Image(item.getImagePath());
            imageLeft.setImage(image);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        items = is.getItems();
        System.out.println(items);
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
                GridPane.setMargin(ItemBox, new Insets(10));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}