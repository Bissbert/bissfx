package ch.bissbert.bissfx.managers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A class to manage messages/alert dialogs.
 * <p>
 * all methods are static and can be called without an instance of this class.
 *
 * @author Bissbert
 */
public class MessageManager {
    private MessageManager() {
    }

    //INFO

    /**
     * Shows an info message and is application modal.
     *
     * @param message the message to show
     */
    public static void info(String message) {
        info(message, "Info");
    }

    /**
     * Shows an info message with a custom title and is application modal.
     *
     * @param message the message to show
     * @param title   the title of the message
     */
    public static void info(String message, String title) {
        info(message, title, null);
    }

    /**
     * Shows an info message with a custom title that is bound to a stage.
     *
     * @param message the message to show
     * @param title   the title of the message
     * @param owner   the owner of the message
     */
    public static void info(String message, String title, Stage owner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        showAlert(alert, message, title, owner);
    }

    //WARNING

    /**
     * Shows a warning message and is application modal.
     *
     * @param message the message to show
     */
    public static void warn(String message) {
        warn(message, "Warning");
    }

    /**
     * Shows a warning message with a custom title and is application modal.
     *
     * @param message the message to show
     * @param title   the title of the message
     */
    public static void warn(String message, String title) {
        warn(message, title, null);
    }

    /**
     * Shows a warning message with a custom title that is bound to a stage.
     *
     * @param message the message to show
     * @param title   the title of the message
     * @param stage   the owner of the message
     */
    public static void warn(String message, String title, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        showAlert(alert, message, title, stage);
    }

    //ERROR

    /**
     * Shows an error message and is application modal.
     *
     * @param message the message to show
     */
    public static void error(String message) {
        error(message, "Error");
    }

    /**
     * Shows an error message with a custom title and is application modal.
     *
     * @param message the message to show
     * @param title   the title of the message
     */
    public static void error(String message, String title) {
        error(message, title, null);
    }

    /**
     * Shows an error message with a custom title that is bound to a stage.
     *
     * @param message the message to show
     * @param title   the title of the message
     * @param stage   the owner of the message
     */
    public static void error(String message, String title, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        showAlert(alert, message, title, stage);
    }

    //CONFIRMATION

    /**
     * Shows a confirmation message and is application modal.
     * <p>
     * The message has two buttons: OK and Cancel.
     * The method returns true if OK was clicked and false if Cancel was clicked.
     *
     * @param message the message to show
     * @return true if the user confirmed, false otherwise
     */
    public static boolean confirm(String message) {
        return confirm(message, "Confirmation");
    }

    /**
     * Shows a confirmation message with a custom title and is application modal.
     * <p>
     * The message has two buttons: OK and Cancel.
     * The method returns true if OK was clicked and false if Cancel was clicked.
     *
     * @param message the message to show
     * @param title   the title of the message
     * @return true if the user confirmed, false otherwise
     */
    public static boolean confirm(String message, String title) {
        return confirm(message, title, null);
    }

    /**
     * Shows a confirmation message with a custom title that is bound to a stage.
     * <p>
     * The message has two buttons: OK and Cancel.
     * The method returns true if OK was clicked and false if Cancel was clicked.
     *
     * @param message the message to show
     * @param title   the title of the message
     * @param stage   the owner of the message
     * @return true if the user confirmed, false otherwise
     */
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
