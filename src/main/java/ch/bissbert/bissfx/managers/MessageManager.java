package ch.bissbert.bissfx.managers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageManager {
    private MessageManager() {
    }

    //INFO

    public static void info(String message) {
        info(message, "Info");
    }

    public static void info(String message, String title) {
        info(message, title, null);
    }

    public static void info(String message, String title, Stage owner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        showAlert(alert, message, title, owner);
    }

    //WARNING

    public static void warn(String message) {
        warn(message, "Warning");
    }

    public static void warn(String message, String title) {
        warn(message, title, null);
    }

    public static void warn(String message, String title, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        showAlert(alert, message, title, stage);
    }

    //ERROR

    public static void error(String message) {
        error(message, "Error");
    }

    public static void error(String message, String title) {
        error(message, title, null);
    }

    public static void error(String message, String title, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        showAlert(alert, message, title, stage);
    }

    //CONFIRMATION

    public static boolean confirm(String message) {
        return confirm(message, "Confirmation");
    }

    public static boolean confirm(String message, String title) {
        return confirm(message, title, null);
    }

    public static boolean confirm(String message, String title, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        if (stage != null) {
            alert.initOwner(stage);
            alert.initModality(Modality.WINDOW_MODAL);
        } else {
            alert.initModality(Modality.APPLICATION_MODAL);
        }
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }


    //GENERAL

    private static void showAlert(Alert alert, String message, String title, Stage stage) {
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        if (stage != null) {
            alert.initOwner(stage);
            alert.initModality(Modality.WINDOW_MODAL);
        } else {
            alert.initModality(Modality.APPLICATION_MODAL);
        }
        alert.showAndWait();
    }
}
