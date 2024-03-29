package de.legoshi.taskmodeller.gui.windows.editwindow;

import de.legoshi.taskmodeller.gui.symbol.ModelNode;
import de.legoshi.taskmodeller.gui.symbol.connection.ProgressConnection;
import de.legoshi.taskmodeller.gui.windows.PaintWindow;
import de.legoshi.taskmodeller.gui.windows.Workplace;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ItemEditWindow extends EditWindow<ModelNode> {

    private final Workplace workplace;

    public ItemEditWindow(Workplace workplace, ModelNode item) {
        super(item, "Edit Object");

        this.workplace = workplace;

        Shape shape = item.getPolyShape();
        Label label = item.getLabel();

        TextField textField = new TextField(label.getText());
        this.gridPane.add(new Text("Name: "), 0, 0);
        this.gridPane.add(textField, 1, 0);
        textField.textProperty().addListener((observableValue, s, t1) -> label.setText(t1));

        TextArea textArea = new TextArea(item.getDescription());
        this.gridPane.add(new Text("Description: "), 0, 1);
        this.gridPane.add(textArea, 1, 1);
        textArea.textProperty().addListener((observableValue, s, t1) -> item.setDescription(t1));


        Slider slider = new Slider(0.4, 5, item.getScaleX());
        this.gridPane.add(new Text("Scaling:"), 0, 2);
        this.gridPane.add(slider, 1, 2);
        slider.valueProperty().addListener((observableValue, number, t1) -> onScale(item, number.doubleValue(), t1.doubleValue()));

        Button connectBtn = new Button("Connection");
        this.gridPane.add(connectBtn, 0, 3);
        connectBtn.setOnMouseClicked(this::onConnect);

        ColorPicker colorPicker = new ColorPicker((Color) shape.getFill());
        this.gridPane.add(colorPicker, 1, 3);
        colorPicker.valueProperty().addListener((observableValue, color, t1) -> shape.setFill(t1));
    }

    private void onConnect(MouseEvent mouseEvent) {
        for (ModelNode dS : workplace.getSelectedPaintWindow().getDrawnNodes()) {
            dS.setAttemptsConnect(false);
        }
        this.item.setAttemptsConnect(true);
        ProgressConnection progressConnection = new ProgressConnection(workplace, this.item);
        this.item.setProgressConnection(progressConnection);
        this.close();
    }

    private void onScale(ModelNode modelNode, double number, double t1) {
        Shape polygon = modelNode.getPolyShape();
        Label label = modelNode.getLabel();

        modelNode.setScaleX(modelNode.getScaleX() + (t1 - number));
        modelNode.setScaleY(modelNode.getScaleY() + (t1 - number));
        label.setFont(new Font("Arial", 12/ modelNode.getScaleX()));

        polygon.setStrokeWidth(3 / modelNode.getScaleX());
    }

    @Override
    public void onDelete() {
        for (PaintWindow paintWindow : workplace.getAllWindows()) {
            paintWindow.removeNode(this.item);
        }
        this.close();
    }

}
