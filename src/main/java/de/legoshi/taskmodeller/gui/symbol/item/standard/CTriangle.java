package de.legoshi.taskmodeller.gui.symbol.item.standard;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.util.PolygonHelper;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class CTriangle extends ModelNode {

    public CTriangle(Shape shape) {
        super(shape);

        this.getLabel().setText("Task");
    }

    public static CTriangle generateShape() {
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(25,50);
        Point2D p3 = new Point2D(50,0);
        Polygon polygon = PolygonHelper.createPolygon(p1, p2, p3);
        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(3);
        return new CTriangle(polygon);
    }

}
