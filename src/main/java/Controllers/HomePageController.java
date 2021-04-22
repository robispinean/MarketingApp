package Controllers;

import Models.Item;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HomePageController {

    @FXML
    private TextField search;

    @FXML
    private ImageView imageLeft;

    @FXML
    private Text email;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Item> items = new ArrayList<>();

    private List<Item> getItems(){

    }
}