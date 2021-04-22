package Controllers;

import AlertMessages.Message;
import Models.Item;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;


public class ItemController {
    public static String CURRENCY = "$";

    @FXML
    private Text itemName;

    @FXML
    private Text itemPrice;

    @FXML
    private Text itemDescription;

    @FXML
    private ImageView itemImage;

    @FXML
    private void click(MouseEvent mouseEvent){
        clickListener.onClickListener(item);
        System.out.println(mouseEvent.getX());
        System.out.println(mouseEvent.getY());
    }
    private Item item;

    private Listener clickListener;


    public void setData(Item item,Listener clickListener){
        this.item = item;
        this.clickListener = clickListener;
        itemName.setText(item.getName());
        itemPrice.setText(CURRENCY + " " + item.getPrice());
        itemDescription.setText(item.getDescription());
        try {
            //Image image = new Image(item.getImagePath());
            Image image = new Image("img/item"+item.getId()+".png");
            itemImage.setImage(image);
        }catch(Exception e){
            System.out.println(item.getId() + " " + item.getName() + " " + item.getImagePath() + " " + e);
            Image image = new Image("img/item1.png");
            itemImage.setImage(image);
        }
    }


}
