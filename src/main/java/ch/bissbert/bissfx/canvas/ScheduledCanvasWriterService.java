package ch.bissbert.bissfx.canvas;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;

/**
 * A service that writes to a canvas at a fixed rate.
 * <p>
 * The rate is defined by the {@link #periodProperty()}.
 * The canvas is to be provided in the constructor.
 * The implemented {@link #draw(Canvas)} method is called at the fixed rate.
 * The {@link #draw(Canvas)} method is called on the JavaFX Application Thread.
 *
 * @author Bissbert
 */
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
