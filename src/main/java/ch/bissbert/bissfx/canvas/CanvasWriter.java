package ch.bissbert.bissfx.canvas;

import javafx.scene.canvas.Canvas;

/**
 * A class implementing this interface can write to a canvas.
 *
 * @author Bissbert
 */
public interface CanvasWriter {
    /**
     * Writes to the canvas.
     * @param canvas the canvas to write to
     */
    void draw(Canvas canvas);
}
