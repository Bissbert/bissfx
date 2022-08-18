package ch.bissbert.bissfx.canvas;

import javafx.scene.canvas.Canvas;

public abstract class ManualCanvasWriter implements CanvasWriter {
    private final Canvas canvas;

    protected ManualCanvasWriter(Canvas canvas) {
        this.canvas = canvas;
    }

    public abstract void draw(Canvas canvas);

    public void draw(){
        draw(canvas);
    }
}
