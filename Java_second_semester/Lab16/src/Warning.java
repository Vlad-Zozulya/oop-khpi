package test007;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Warning {
    public static void showAlertWithHeaderText(String text) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
