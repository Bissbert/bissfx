package ch.bissbert.bissfx.canvas.graph.frame;

import ch.bissbert.bissfx.canvas.graph.GraphOrientation;
import ch.bissbert.bissfx.canvas.graph.data.GraphData;
import ch.bissbert.bissfx.canvas.graph.data.GraphDataAddition2Axis;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

/**
 * Draws a basic 2d graph frame to the canvas using the values set in the {@link GraphData< GraphDataAddition2Axis >}
 * <p>
 * The base consists of 2 lines representing the axis and a legend on each of them, they are also numbered
 *
 * @author Bissbert
 * @version 1.0
 * @see GraphData
 * @see ch.bissbert.bissfx.canvas.graph.data.GraphDataAddition
 * @see GraphDataAddition2Axis
 */
public class GraphFrame2D implements GraphFrame<GraphDataAddition2Axis> {
    @Override
    public void drawFrame(Canvas canvas, GraphData<GraphDataAddition2Axis> graphData) {
        drawLines(canvas, graphData);
        drawLegend(canvas, graphData);
    }

    private void drawLegend(Canvas canvas, GraphData<GraphDataAddition2Axis> graphData) {
        //get graphics context
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        //calculate center position on canvas for drawing
        final double heightCenter = canvas.getHeight() / 2;
        final double widthCenter = canvas.getWidth() / 2;

        //set context font
        graphicsContext.setFont(graphData.getFont());
        graphicsContext.setTextAlign(TextAlignment.CENTER);

        //draw legend bottom
        final String bottomLegend = graphData.getGraphAddition().getOrientation() == GraphOrientation.CENTER ?
                graphData.getGraphAddition().getYLegend() :
                graphData.getGraphAddition().getXLegend();
        final double bottomLegendHeight = canvas.getHeight() - (2 * graphData.getCanvasBorder());
        graphicsContext.fillText(bottomLegend, widthCenter, bottomLegendHeight);

        //draw legend side
        final String sideLegend = graphData.getGraphAddition().getOrientation() == GraphOrientation.CENTER ?
                graphData.getGraphAddition().getXLegend() :
                graphData.getGraphAddition().getYLegend();

        graphicsContext.translate(graphData.getCanvasBorder(), heightCenter);
        graphicsContext.rotate(90);

        graphicsContext.fillText(sideLegend, 0, 0);

        graphicsContext.rotate(-90);
        graphicsContext.translate(-graphData.getCanvasBorder(), -heightCenter);

    }

    private void drawLines(Canvas canvas, GraphData<GraphDataAddition2Axis> graphData) {

    }
}
