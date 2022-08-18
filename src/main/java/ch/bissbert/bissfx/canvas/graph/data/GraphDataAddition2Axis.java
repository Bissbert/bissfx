package ch.bissbert.bissfx.canvas.graph.data;

import ch.bissbert.bissfx.canvas.graph.GraphOrientation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder()
public class GraphDataAddition2Axis extends GraphDataAddition {
    @Builder.Default
    private String xLegend = "";
    @Builder.Default
    private String yLegend = "";
    private double xMin, xMax;
    private double yMin, yMax;
    @Builder.Default
    private double xStep = 1;
    private GraphOrientation orientation;
}
