package ch.bissbert.bissfx.canvas;

import javafx.scene.canvas.Canvas;

/**
 * An abstract class that takes a canvas and lets the implementing class draw on it using the {@link #draw()} method.
 *
 * @author Bissbert
 */
public abstract class ManualCanvasWriter implements CanvasWriter {
    private final Canvas canvas;

    protected ManualCanvasWriter(Canvas canvas) {
        this.canvas = canvas;
    }

    public abstract void draw(Canvas canvas);

    /**
     * Draws on the canvas using the {@link #draw(Canvas)} method with the canvas provided in the constructor.
     */
    public void draw(){
        draw(canvas);
    }
}
