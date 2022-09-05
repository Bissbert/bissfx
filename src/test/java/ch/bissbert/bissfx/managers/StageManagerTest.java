package ch.bissbert.bissfx.managers;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StageManagerTest {

    @BeforeEach
    void setUp() {
        StageManager.closeAllStages();
    }

    @Test
    void addStage(@Mock Stage stageFirst, @Mock Stage stageSecond) {
        StageManager.addStage("FIRST", stageFirst);
        assertEquals(stageFirst, StageManager.getStage("FIRST"));
        StageManager.addStage("FIRST", stageSecond);
        assertEquals(stageSecond, StageManager.getStage("FIRST"));
        assertNotEquals(stageFirst, StageManager.getStage("FIRST"));
    }

    @Test
    void testAddStage() {
        Scene scene = mock(Scene.class);
        try (MockedConstruction<Stage> mocked = Mockito.mockConstruction(Stage.class)) {
            Stage firstStage = StageManager.addStage("FIRST", scene);
            assertEquals(firstStage, StageManager.getStage("FIRST"));

            Stage secondStage = StageManager.addStage("FIRST", mock(Scene.class));
            assertEquals(secondStage, StageManager.getStage("FIRST"));
            assertNotEquals(firstStage, StageManager.getStage("FIRST"));
        }
    }

    @Test
    void getStage(@Mock Stage stageFirst, @Mock Stage stageSecond) {
        StageManager.addStage("FIRST", stageFirst);
        assertEquals(stageFirst, StageManager.getStage("FIRST"));
        StageManager.addStage("FIRST", stageSecond);
        assertEquals(stageSecond, StageManager.getStage("FIRST"));
        assertNotEquals(stageFirst, StageManager.getStage("FIRST"));
    }

    @Test
    void closeStage(@Mock Stage stageFirst, @Mock Stage stageSecond) {
        StageManager.addStage("FIRST", stageFirst);
        StageManager.addStage("SECOND", stageSecond);

        StageManager.closeStage("FIRST");

        assertNull(StageManager.getStage("FIRST"));
        assertEquals(stageSecond, StageManager.getStage("SECOND"));

        StageManager.closeStage("SECOND");
        assertNull(StageManager.getStage("SECOND"));
    }


    @Test
    void closeAllStages(@Mock Stage stageFirst, @Mock Stage stageSecond) {
        StageManager.addStage("FIRST", stageFirst);
        StageManager.addStage("SECOND", stageSecond);

        assertEquals(stageFirst, StageManager.getStage("FIRST"));
        assertEquals(stageSecond, StageManager.getStage("SECOND"));

        StageManager.closeAllStages();

        assertNull(StageManager.getStage("FIRST"));
        assertNull(StageManager.getStage("SECOND"));
    }

    @Test
    void setScene(@Mock Stage stage) {
    }

    @Test
    void setModality() {
    }

    @Test
    void show() {
    }

    @Test
    void hide() {
    }
}