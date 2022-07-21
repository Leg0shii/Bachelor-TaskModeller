package de.legoshi.taskmodeller.gui.model.windows.editwindows;

import de.legoshi.taskmodeller.gui.model.symbols.ModelNode;
import de.legoshi.taskmodeller.gui.model.windows.Workplace;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class CommentEditWindow extends EditWindow<ModelNode> {

    private final Workplace workplace;

    public CommentEditWindow(Workplace workplace, ModelNode item) {
        super(item, "Bearbeite Kommentar");
        this.workplace = workplace;

        Label label = (Label) item.getChildren().get(1);

        TextArea textArea = new TextArea(label.getText());
        this.gridPane.add(new Text("Kommentar: "), 0, 0);
        this.gridPane.add(textArea, 1, 0);
        textArea.textProperty().addListener((observableValue, s, t1) -> {
            label.setText(t1);
        });

        this.deleteBtn.setOnMouseClicked(mouseEvent -> onDelete());
    }

    @Override
    public void onDelete() {
        workplace.getChildren().remove(this.item);
        this.close();
    }

}
