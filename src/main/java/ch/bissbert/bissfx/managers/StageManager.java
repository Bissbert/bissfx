package ch.bissbert.bissfx.managers;

import ch.bissbert.bissfx.managers.exception.StageNotFoundException;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to manage the stages of the application.
 * <p>
 * all methods are static and can be called without an instance of this class.
 *
 * @author Bissbert
 */
public class StageManager {
    private StageManager() {
    }

    private static final Map<String, Stage> stages = new HashMap<>();

    /**
     * Adds a stage to the stage manager.
     *
     * @param name  the name of the stage
     * @param stage the stage to add
     */
    public static void addStage(String name, Stage stage) {
        stages.put(name, stage);
    }

    /**
     * Creates a new stage from a scene and adds it to the stage manager.
     *
     * @param name  the name of the stage
     * @param scene the scene of the stage
     * @return the created stage
     */
    public static Stage addStage(String name, Scene scene) {
        Stage stage = new Stage();
        stage.setScene(scene);
        addStage(name, stage);
        return stage;
    }

    /**
     * Fetches a stage from the stage manager by its name.
     *
     * @param name the name of the stage
     * @return the stage
     */
    public static Stage getStage(String name) {
        return stages.get(name);
    }

    /**
     * Closes a stage by its name.
     * <p>
     * the stage is closed by calling {@link Stage#close()}.
     *
     * @param name the name of the stage
     * @throws StageNotFoundException if the stage is not found
     */
    public static void closeStage(String name) {
        Stage stage = stages.get(name);
        //close stage if it is not null
        if (stage != null) {
            stage.close();
            //remove stage from map
            stages.remove(name);
        } else {
            throw new StageNotFoundException(name);
        }
    }

    /**
     * Closes all stages.
     * <p>
     * the stages are closed by calling {@link Stage#close()}.
     */
    public static void closeAllStages() {
        for (Stage stage : stages.values()) {
            stage.close();
        }
        stages.clear();
    }

    /**
     * Sets the scene of a stage by its name.
     *
     * @param name  the name of the stage
     * @param scene the new scene for the stage
     * @throws StageNotFoundException if the stage is not found
     */
    public static void setScene(String name, Scene scene) {
        Stage stage = stages.get(name);
        if (stage != null) {
            stage.setScene(scene);
        } else {
            throw new StageNotFoundException(name);
        }
    }

    /**
     * Sets the modality of a stage by its name.
     *
     * @param name     the name of the stage
     * @param modality the new modality for the stage
     * @throws StageNotFoundException if the stage is not found
     */
    public static void setModality(String name, Modality modality) {
        Stage stage = stages.get(name);
        if (stage != null) {
            stage.initModality(modality);
        } else {
            throw new StageNotFoundException(name);
        }
    }

    /**
     * Shows a stage by its name.
     *
     * @param name the name of the stage
     * @throws StageNotFoundException if the stage is not found
     */
    public static void show(String name) {
        Stage stage = stages.get(name);
        if (stage != null) {
            stage.show();
        } else {
            throw new StageNotFoundException(name);
        }
    }

    /**
     * Hide a stage by its name.
     *
     * @param name the name of the stage
     * @throws StageNotFoundException if the stage is not found
     */
    public static void hide(String name) {
        Stage stage = stages.get(name);
        if (stage != null) {
            stage.hide();
        } else {
            throw new StageNotFoundException(name);
        }
    }
}
