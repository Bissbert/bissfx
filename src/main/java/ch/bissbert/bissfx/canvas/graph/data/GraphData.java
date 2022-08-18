package ch.bissbert.bissfx.canvas.graph.data;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(builderMethodName = "hiddenBuilder")
@Getter
@Setter
public class GraphData<T extends GraphDataAddition> {
    @Builder.Default
    private Font font = Font.getDefault();
    private double canvasBorder;
    private boolean dashed;
    @Builder.Default
    private Color graphColor = Color.BLACK;
    private T graphAddition;

    public static <T extends GraphDataAddition> GraphDataBuilder<T> builder(T graphAddition) {
        return (GraphDataBuilder<T>) hiddenBuilder().graphAddition(graphAddition);
    }
}
