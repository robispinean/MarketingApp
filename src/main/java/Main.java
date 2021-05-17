import Controllers.DatabaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    DatabaseController db = new DatabaseController();
    @Override
    public void start(Stage primaryStage) throws Exception {
        db.initialize();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Registration.fxml")));
        primaryStage.setTitle("Registration Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args){ launch(args); }
}