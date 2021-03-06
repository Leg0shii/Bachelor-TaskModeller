package de.legoshi.taskmodeller.gui.windows.newwindow;

import de.legoshi.taskmodeller.gui.windows.ProjectWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class NewProjectSettingsWindow extends NewProject {

    public NewProjectSettingsWindow(ProjectWindow project) {
        Label existingModelL = new Label("Existing Model");
        Label compositeModelL = new Label("Composite Model");
        Label envisionedModelL = new Label("Envisioned Model");

        VBox exModel = new VBox();
        exModel.getChildren().add(existingModelL);
        ArrayList<HBox> exHBoxes = generateDisplayThing(project.getExistentCount());
        exModel.getChildren().addAll(exHBoxes);

        VBox coModel = new VBox();
        coModel.getChildren().add(compositeModelL);
        ArrayList<HBox> coHBoxes = generateDisplayThing(project.getCompositeCount());
        coModel.getChildren().addAll(coHBoxes);

        VBox evModel = new VBox();
        evModel.getChildren().add(envisionedModelL);
        ArrayList<HBox> evHBoxes = generateDisplayThing(project.getEnvisionedCount());
        evModel.getChildren().addAll(evHBoxes);

        this.gridPane.add(exModel, 0, 0);
        this.gridPane.add(coModel, 1, 0);
        this.gridPane.add(evModel, 2, 0);

        Button continueBtn = new Button("Continue");
        continueBtn.setOnMouseClicked(mouseEvent -> {
            NewProjectSaveWindow newProjectSaveWindow = new NewProjectSaveWindow(project, exHBoxes, coHBoxes, evHBoxes);
            newProjectSaveWindow.show();
            this.close();
        });

        this.gridPane.add(continueBtn, 1, 3);
    }

    private ArrayList<HBox> generateDisplayThing(int count) {
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            HBox hBox = new HBox();
            TextField textField = new TextField("Task Model " + (i+1));
            ComboBox<String> comboBox = new ComboBox<>();
            ObservableList<String> options = FXCollections.observableArrayList("FREE", "CTT");
            comboBox.setValue("FREE");
            comboBox.setItems(options);

            hBox.getChildren().add(textField);
            hBox.getChildren().add(comboBox);
            hBoxes.add(hBox);
        }
        return hBoxes;
    }

}
