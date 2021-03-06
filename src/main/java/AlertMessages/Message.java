package AlertMessages;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import jdk.jfr.internal.tool.Main;

public class Message {

    public void setWarningMessage(String str){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Alert Message");
        alert.setContentText(str);
        alert.show();
    }
    public void setConfirmationMessage(String str){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmation Message");
        alert.setContentText(str);
        alert.show();
    }
}