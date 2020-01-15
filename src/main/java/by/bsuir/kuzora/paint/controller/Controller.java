package by.bsuir.kuzora.paint.controller;

import by.bsuir.kuzora.paint.model.FigureFactory;
import by.bsuir.kuzora.paint.model.constants.FigureType;
import by.bsuir.kuzora.paint.model.interfaces.Figure;
import by.bsuir.kuzora.paint.service.ServiceFactory;
import by.bsuir.kuzora.paint.service.constants.State;
import by.bsuir.kuzora.paint.service.exception.ServiceException;
import by.bsuir.kuzora.paint.service.interfaces.FigureCanvas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Class {@link Controller}.
 * <p>
 * Class Controller includes methods for control GUI interface.
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.controller}
 * package.</i>
 */
public class Controller {

    /**
     * CTRL + Z hot key combination.
     */
    private static final KeyCombination CTRL_Z = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);

    /**
     * CTRL + Y hot key combination.
     */
    private static final KeyCombination CTRL_Y = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);

    /**
     * Figure canvas.
     */
    private static final FigureCanvas FIGURE_CANVAS = ServiceFactory.getInstance().getFigureCanvas();

    private double dragPrevX = -1.0;
    private double dragPrevY = -1.0;

    /**
     * Default selection state.
     */
    private State state = State.SELECT;

    /**
     * Default selection state.
     */
    @FXML
    private Canvas mainCanvas;

    /**
     * Color picker for figure borders.
     */
    @FXML
    private ColorPicker colorPickerOutside;

    /**
     * Color picker for inside figure area.
     */
    @FXML
    public ColorPicker colorPickerInside;

    /**
     * List with available figure + selection mode.
     */
    @FXML
    private ListView<FigureType> listViewMain;

    /**
     * Inputs with coordinates for mouse action(last dragged, last edited, last figure added)
     */
    @FXML
    private TextField xInput, yInput;

    /**
     * Confirmation button for editing new x and y
     */
    @FXML
    private Button confirmButton;

    /**
     * Inputs labels
     */
    @FXML
    private Label xLabel, yLabel;

    /**
     * Method initialize.
     * Init GUI interface with default values.
     */
    @FXML
    void initialize() {
        listViewMain.getItems().addAll(FigureType.values());
        listViewMain.getSelectionModel().selectFirst();
        xInput.setVisible(false);
        yInput.setVisible(false);
        confirmButton.setVisible(false);
        xLabel.setVisible(false);
        yLabel.setVisible(false);
        colorPickerOutside.setValue(Color.BLUE);
        colorPickerInside.setValue(Color.WHITE);
    }

    /**
     * Public method actionOnMousePressed.
     * <p>
     * Action listener on mouse pressed in main canvas area.
     *
     * @param event {@link MouseEvent} object.
     */
    @FXML
    public void actionOnMousePressed(MouseEvent event) {
        if (isSelecting()) {
            state = State.SELECT;
        } else {
            state = State.DRAW;
        }
        dragPrevX = (int) event.getX();
        dragPrevY = (int) event.getY();
        xInput.setText(String.valueOf(dragPrevX));
        yInput.setText(String.valueOf(dragPrevY));
        xInput.setVisible(true);
        yInput.setVisible(true);
        confirmButton.setVisible(true);
        xLabel.setVisible(true);
        yLabel.setVisible(true);
        switch (state) {
            case DRAW:
                Figure figure = FigureFactory.getInstance().create(getSelectedFigureType(), dragPrevX, dragPrevY);
                figure.setFillColor(colorPickerInside.getValue().toString());
                figure.setPenColor(colorPickerOutside.getValue().toString());
                FIGURE_CANVAS.add(figure);
                FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
                break;
            case SELECT:
                FIGURE_CANVAS.selectAndRedraw(event.getX(), event.getY(), mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
                break;
        }
    }

    /**
     * Public method actionOnMouseDragged.
     * <p>
     * Action listener on mouse dragged in main canvas area.
     *
     * @param event {@link MouseEvent} object.
     */
    @FXML
    public void actionOnMouseDragged(MouseEvent event) {
        double oldX = dragPrevX;
        double oldY = dragPrevY;
        double newX = event.getX();
        double newY = event.getY();
        double deltaX = newX - dragPrevX;
        double deltaY = newY - dragPrevY;
        dragPrevX = newX;
        dragPrevY = newY;
        xInput.setText(String.valueOf(dragPrevX));
        yInput.setText(String.valueOf(dragPrevY));
        switch (state) {
            case DRAW:
                FIGURE_CANVAS.resizeLast(deltaX, deltaY);
                FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
                break;
            case SELECT:
                FIGURE_CANVAS.resizeMoveSelected(oldX, oldY, deltaX, deltaY);
                FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
                break;
        }
    }

    /**
     * Public method setConfirmButton.
     * <p>
     * Action listener on click  for OK button
     *
     * @param actionEvent {@link ActionEvent} object.
     */
    @FXML
    public void setConfirmButton(ActionEvent actionEvent) {
        double oldX = dragPrevX;
        double oldY = dragPrevY;
        double newX = Double.parseDouble(xInput.getText());
        double newY = Double.parseDouble(yInput.getText());
        double deltaX = newX - dragPrevX;
        double deltaY = newY - dragPrevY;
        dragPrevX = newX;
        dragPrevY = newY;
        switch (state) {
            case DRAW:
                FIGURE_CANVAS.resizeLast(deltaX, deltaY);
                FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
                break;
            case SELECT:
                FIGURE_CANVAS.resizeMoveSelected(oldX, oldY, deltaX, deltaY);
                FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
                break;
        }
    }

    /**
     * Public method actionOnKeyPressed.
     * <p>
     * Action listener for UNDO and REDO and DELETE
     *
     * @param event {@link KeyEvent} object.
     */
    @FXML
    public void actionOnKeyPressed(KeyEvent event) {
        if (CTRL_Z.match(event)) {
            FIGURE_CANVAS.removeLast();
            FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
        }
        if (CTRL_Y.match(event)) {
            FIGURE_CANVAS.redo();
            FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
        }
        if (event.getCode() == KeyCode.DELETE) {
            FIGURE_CANVAS.deleteSelected(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
        }
    }


    /**
     * Public method undo.
     * <p>
     * Action listener for UNDO button from top menu
     *
     * @param actionEvent {@link ActionEvent} object.
     */
    @FXML
    public void undo(ActionEvent actionEvent) {
        FIGURE_CANVAS.removeLast();
        FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
    }

    /**
     * Public method redo.
     * <p>
     * Action listener for REDO button from top menu
     *
     * @param actionEvent {@link ActionEvent} object.
     */
    @FXML
    public void redo(ActionEvent actionEvent) {
        FIGURE_CANVAS.redo();
        FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
    }

    /**
     * Public method clear.
     * <p>
     * Action listener for CLEAR button from top menu
     *
     * @param actionEvent {@link ActionEvent} object.
     */
    @FXML
    public void clear(ActionEvent actionEvent) {
        FIGURE_CANVAS.clear(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
    }


    /**
     * Private method getSelectedFigureType.
     * <p>
     * Method for getting selected figure type from GUI.
     *
     * @return {@link FigureType} object
     */
    private FigureType getSelectedFigureType() {
        return listViewMain.getFocusModel().getFocusedItem();
    }

    /**
     * Private method isSelecting.
     * <p>
     * Method for checking selection state.
     *
     * @return boolean value
     */
    private boolean isSelecting() {
        return getSelectedFigureType() == FigureType.SELECTION;
    }

    /**
     * Public method loadFromFile.
     * <p>
     * Action listener for deserialization
     *
     * @param actionEvent {@link ActionEvent} object.
     */
    public void loadFromFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainCanvas.getScene().getWindow());
        if (file != null) {
            try {
                FIGURE_CANVAS.loadFromFile(file);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            FIGURE_CANVAS.redraw(mainCanvas.getGraphicsContext2D(), mainCanvas.getWidth(), mainCanvas.getHeight());
        }
    }

    /**
     * Public method saveToFile.
     * <p>
     * Action listener for serialization
     *
     * @param actionEvent {@link ActionEvent} object.
     */
    public void saveToFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(mainCanvas.getScene().getWindow());
        if (file != null) {
            try {
                FIGURE_CANVAS.saveToFile(file);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
