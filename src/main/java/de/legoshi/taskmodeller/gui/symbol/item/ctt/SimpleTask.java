package de.legoshi.taskmodeller.gui.symbol.item.ctt;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SimpleTask extends ModelNode {

    public SimpleTask(Shape shape) {
        super(shape);

        this.getLabel().setText("Task");
    }

    public static SimpleTask generateShape() {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(Color.LIGHTGRAY);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        return new SimpleTask(rectangle);
    }

}
