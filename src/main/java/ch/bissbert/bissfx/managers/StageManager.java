package ch.bissbert.bissfx.managers;

import ch.bissbert.bissfx.managers.exception.StageNotFoundException;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StageManager {
    private StageManager() {
    }

    private static final Map<String, Stage> stages = new HashMap<>();

    public static void addStage(String name, Stage stage) {
        stages.put(name, stage);
    }

    public static Stage addStage(String name, Scene scene) {
        Stage stage = new Stage();
        stage.setScene(scene);
        addStage(name, stage);
        return stage;
    }

    public static Stage getStage(String name) {
        return stages.get(name);
    }

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

    public static void closeAllStages() {
        for (Stage stage : stages.values()) {
            stage.close();
        }
        stages.clear();
    }

    public static void setScene(String name, Scene scene) {
        Stage stage = stages.get(name);
        if (stage != null) {
            stage.setScene(scene);
        } else {
            throw new StageNotFoundException(name);
        }
    }

    public static void setModality(String name, Modality modality) {
        Stage stage = stages.get(name);
        if (stage != null) {
            stage.initModality(modality);
        } else {
            throw new StageNotFoundException(name);
        }
    }

    public static void show(String name) {
        Stage stage = stages.get(name);
        if (stage != null) {
            stage.show();
        } else {
            throw new StageNotFoundException(name);
        }
    }

    public static void hide(String name) {
        Stage stage = stages.get(name);
        if (stage != null) {
            stage.hide();
        } else {
            throw new StageNotFoundException(name);
        }
    }
}
