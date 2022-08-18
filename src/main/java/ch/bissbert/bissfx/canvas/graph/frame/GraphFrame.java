package ch.bissbert.bissfx.canvas.graph.frame;

import ch.bissbert.bissfx.canvas.graph.data.GraphData;
import ch.bissbert.bissfx.canvas.graph.data.GraphDataAddition;
import javafx.scene.canvas.Canvas;

public interface GraphFrame<T extends GraphDataAddition> {
    void drawFrame(Canvas canvas, GraphData<T> graphData);
}
