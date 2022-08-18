package ch.bissbert.bissfx.canvas;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;

public abstract class ScheduledCanvasWriterService extends ScheduledService<Void> implements CanvasWriter {

    private final Canvas canvas;

    public ScheduledCanvasWriterService(Canvas canvas) {
        this.canvas = canvas;
    }

    abstract public void draw(Canvas canvas);

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() {
                draw(canvas);
                return null;
            }
        };
    }
}
