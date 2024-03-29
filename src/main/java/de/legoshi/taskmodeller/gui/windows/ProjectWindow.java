package de.legoshi.taskmodeller.gui.windows;

import de.legoshi.taskmodeller.util.ModelType;
import de.legoshi.taskmodeller.util.PWInitObject;
import de.legoshi.taskmodeller.util.StatusType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ProjectWindow extends GridPane {

    private final Workplace workplace;

    private int existentCount = 1;
    private int compositeCount = 1;
    private int envisionedCount = 1;

    public static final int HGAP = 100;
    public static final int VGAP = 100;
    public static final int SIZE = 750;

    private ArrayList<PaintWindow> existentWindows;
    private ArrayList<PaintWindow> compositeWindows;
    private ArrayList<PaintWindow> envisionedWindows;

    public ProjectWindow(Workplace workplace) {
        this.workplace = workplace;
        this.setHgap(HGAP);
        this.setVgap(VGAP);

        existentWindows = new ArrayList<>();
        compositeWindows = new ArrayList<>();
        envisionedWindows = new ArrayList<>();
    }

    public ArrayList<PaintWindow> getAllWindows() {
        ArrayList<PaintWindow> allWindows = new ArrayList<>();
        allWindows.addAll(existentWindows);
        allWindows.addAll(compositeWindows);
        allWindows.addAll(envisionedWindows);
        return allWindows;
    }

    public void generatePaintWindows(ArrayList<PWInitObject> exO, ArrayList<PWInitObject> coO, ArrayList<PWInitObject> evO) {
        this.minHeight(Math.max(Math.max(existentCount, compositeCount), envisionedCount) * SIZE);
        this.maxHeight(SIZE*3);

        this.existentCount = exO.size();
        this.compositeCount = coO.size();
        this.envisionedCount = evO.size();

        clearAll();
        initPaintWindows(existentWindows, Color.color(227.0/255.0, 251.0/255.0, 227.0/255.0), exO, 0);
        initPaintWindows(compositeWindows, Color.color(1.0, 250.0/255.0, 205.0/255.0), coO, 1);
        initPaintWindows(envisionedWindows, Color.color(1.0, 220.0/255.0, 223.0/255.0), evO, 2);
        applyShiftOffset();

        // sets active window on first project creation to first window
        workplace.setSelectedPaintWindow(existentWindows.get(0));
        existentWindows.get(0).setActiveWindow();
        workplace.getItemBarManager().reloadItemBarWithModel(existentWindows.get(0), existentWindows.get(0).getModelType());
    }

    private void initPaintWindows(ArrayList<PaintWindow> windows, Color color, ArrayList<PWInitObject> pwInitObjects, int xShift) {
        int i = 0;
        for (PWInitObject initObject : pwInitObjects) {

            StatusType statusType = StatusType.values()[xShift];
            ModelType modelType = ModelType.valueOf(initObject.getModelType());

            PaintWindow paintWindow = new PaintWindow(this.workplace, xShift, i, statusType, modelType);
            paintWindow.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
            paintWindow.setName(initObject.getName());
            paintWindow.setTranslateX(100);
            paintWindow.setTranslateY(100);
            Label label = new Label(initObject.getName() + " (" + modelType.name() + ", " + statusType.name() + ")");
            label.setPadding(new Insets(10, 10, 10, 10));
            paintWindow.getChildren().add(label);
            windows.add(paintWindow);
            this.add(paintWindow, xShift, i);
            i++;
        }
    }

    private void applyShiftOffset() {
        int existCount = existentWindows.size();
        int compCount = compositeWindows.size();
        int envCount = envisionedWindows.size();

        int max = Math.max(compCount, Math.max(existCount, envCount));
        setYOffsetShift(existentWindows, max, existCount);
        setYOffsetShift(compositeWindows, max, compCount);
        setYOffsetShift(envisionedWindows, max, envCount);
    }

    private void setYOffsetShift(ArrayList<PaintWindow> windows, int max, int count) {
        int offset = max - count;
        for (PaintWindow pW : windows) {
            double yOffsetShift = offset * 600.0/2.0;
            pW.setTranslateY(pW.getTranslateY() + yOffsetShift);
            pW.setYOffsetShift(yOffsetShift);
        }
    }

    public void clearAll() {
        this.compositeWindows.clear();
        this.existentWindows.clear();
        this.envisionedWindows.clear();
        this.getChildren().clear();
    }

}
