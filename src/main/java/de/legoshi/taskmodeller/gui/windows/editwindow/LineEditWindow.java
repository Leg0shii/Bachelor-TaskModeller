package de.legoshi.taskmodeller.gui.windows.editwindow;

import de.legoshi.taskmodeller.gui.symbol.connection.Connection;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LineEditWindow extends EditWindow<Connection> {

    private final Workplace workplace;

    public LineEditWindow(Workplace workplace, Connection item) {
        super(item, "Edit Line");

        Label label = item.getLabel();

        Label tFName = new Label("Set Connection Name");
        gridPane.add(tFName, 0, 0);

        TextField textField = new TextField(label.getText());
        gridPane.add(textField, 1, 0);
        textField.textProperty().addListener((observableValue, s, t1) -> label.setText(t1));

        Label colorName = new Label("Change Color");
        gridPane.add(colorName, 0, 1);

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue((Color) item.getStroke());
        gridPane.add(colorPicker, 1, 1);
        colorPicker.valueProperty().addListener((observableValue, color, t1) -> item.setStroke(t1));

        Label widthName = new Label("Change Line Width");
        gridPane.add(widthName, 0, 2);

        TextField numberField = new TextField(item.getStrokeWidth() + "");
        numberField.textProperty().addListener((observableValue, s, t1) -> {
            try {
                double sWidth = Double.parseDouble(numberField.getText());
                item.setStrokeWidth(sWidth);
            } catch (NumberFormatException e) {
                item.setStrokeWidth(3.0);
            }
        });
        gridPane.add(numberField, 1, 2);

        this.workplace = workplace;
    }

    @Override
    public void onDelete() {
        PaintWindow paintWindow = workplace.getSelectedPaintWindow();
        paintWindow.removeConnection(this.item);
        this.close();
    }

}
