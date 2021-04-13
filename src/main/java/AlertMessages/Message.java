package AlertMessages;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import jdk.jfr.internal.tool.Main;

public class Message {
    Main myapp = new Main();
    public void setMessage(String str){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Alert Message");
        alert.setContentText(str);
        alert.show();
    }
}