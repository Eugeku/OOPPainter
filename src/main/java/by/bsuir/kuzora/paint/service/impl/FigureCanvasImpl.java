package by.bsuir.kuzora.paint.service.impl;

import by.bsuir.kuzora.paint.dao.DAOFactory;
import by.bsuir.kuzora.paint.dao.exception.DAOException;
import by.bsuir.kuzora.paint.dao.interfaces.SerializationReader;
import by.bsuir.kuzora.paint.dao.interfaces.SerializationWriter;
import by.bsuir.kuzora.paint.model.constants.FigurePart;
import by.bsuir.kuzora.paint.model.interfaces.Figure;
import by.bsuir.kuzora.paint.service.exception.ServiceException;
import by.bsuir.kuzora.paint.service.interfaces.FigureCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static by.bsuir.kuzora.paint.service.constants.Constants.DEFAULT_LINE_WIDTH;
import static by.bsuir.kuzora.paint.service.constants.Constants.LINE_DASHES_0;
import static by.bsuir.kuzora.paint.service.constants.Constants.LINE_DASHES_20;
import static by.bsuir.kuzora.paint.service.constants.Constants.SELECTION_PADDING;

/**
 * Class {@link FigureCanvasImpl}.
 * <p>
 * Implementation of interface {@link FigureCanvas} with 11 overriding methods.
 * <p>
 * <i>This class is a member of the {@link by.bsuir.kuzora.paint.service.impl}
 * package.</i>
 */
public class FigureCanvasImpl implements FigureCanvas {

    /**
     * List with actual figure on canvas.
     */
    private List<Figure> figureList = new ArrayList<>();

    /**
     * Selected figure on canvas.
     */
    private Figure selectedFigure = null;

    /**
     * Stack with figures for redo and undo methods.
     */
    private final Stack<Figure> redo = new Stack<>();

    /**
     * Implementation  of method redraw.
     * <p>
     * Method for redraw canvas.
     *
     * @param context object of {@link GraphicsContext} type.
     * @param w       width of painting canvas
     * @param h       height of painting canvas
     */
    @Override
    public void redraw(GraphicsContext context, double w, double h) {
        context.clearRect(0, 0, w, h);
        for (Figure figure : figureList) {
            figure.draw(context);
        }
        if (selectedFigure != null) {
            context.setStroke(Color.GREEN);
            context.setLineWidth(DEFAULT_LINE_WIDTH);
            context.setLineDashes(LINE_DASHES_20);
            context.strokeRect(
                    selectedFigure.getX1() - SELECTION_PADDING,
                    selectedFigure.getY1() - SELECTION_PADDING,
                    selectedFigure.getWidth() + DEFAULT_LINE_WIDTH * SELECTION_PADDING,
                    selectedFigure.getHeight() + DEFAULT_LINE_WIDTH * SELECTION_PADDING);
            context.setLineDashes(LINE_DASHES_0);
        }
    }

    /**
     * Implementation  of method clear.
     * <p>
     * Method for clear canvas.
     *
     * @param context object of {@link GraphicsContext} type.
     * @param w       width of painting canvas
     * @param h       height of painting canvas
     */
    @Override
    public void clear(GraphicsContext context, double w, double h) {
        context.clearRect(0, 0, w, h);
        figureList.clear();
    }

    /**
     * Implementation  of method deleteSelected.
     * <p>
     * Method for delete selected figure on canvas.
     *
     * @param context object of {@link GraphicsContext} type.
     * @param w       width of painting canvas
     * @param h       height of painting canvas
     */
    @Override
    public void deleteSelected(GraphicsContext context, double w, double h) {
        context.clearRect(0, 0, w, h);
        if (selectedFigure != null) {
            figureList.remove(selectedFigure);
        }
        for (Figure figure : figureList) {
            figure.draw(context);
        }
    }

    /**
     * Implementation of method add.
     * <p>
     * Method for adding figure in list for redraw.
     *
     * @param figure object of {@link Figure} type.
     */
    @Override
    public void add(Figure figure) {
        figureList.add(figure);
        redo.clear();
    }

    /**
     * Implementation of method resizeLast.
     *
     * @param deltaX delta x for resize
     * @param deltaY delta y for resize
     */
    @Override
    public void resizeLast(double deltaX, double deltaY) {
        Figure figure = figureList.get(figureList.size() - 1);
        figure.resize(deltaX, deltaY);
    }

    /**
     * Implementation of method removeLast.
     * <p>
     * Method for removing last figure.
     */
    @Override
    public void removeLast() {
        if (!figureList.isEmpty()) {
            redo.add(figureList.get(figureList.size() - 1));
            figureList.remove(figureList.size() - 1);
        }
    }

    /**
     * Implementation of method redo.
     */
    @Override
    public void redo() {
        if (!redo.isEmpty()) {
            figureList.add(redo.pop());
        }
    }

    /**
     * Implementation of method selectAndRedraw.
     *
     * @param x       current action x
     * @param y       current action y
     * @param context object of {@link GraphicsContext} type.
     * @param w       width of painting canvas
     * @param h       height of painting canvas
     */
    @Override
    public void selectAndRedraw(double x, double y, GraphicsContext context, double w, double h) {
        Figure newSelected = getSelected(x, y);
        if (selectedFigure != newSelected) {
            selectedFigure = newSelected;
            redraw(context, w, h);
        }
    }

    /**
     * Implementation of method resizeMoveSelected.
     *
     * @param x      current action x
     * @param y      current action y
     * @param deltaX delta x for resize
     * @param deltaY delta y for resize
     */
    @Override
    public void resizeMoveSelected(double x, double y, double deltaX, double deltaY) {
        if (selectedFigure != null) {
            FigurePart part = selectedFigure.getFigurePart(x, y);
            selectedFigure.resizeMove(part, deltaX, deltaY);
        }
    }

    /**
     * Implementation of method saveToFile.
     *
     * @param file object of {@link File}
     * @throws ServiceException exception from {@link by.bsuir.kuzora.paint.dao} layer
     */
    @Override
    public void saveToFile(File file) throws ServiceException {
        try {
            SerializationWriter writer = DAOFactory.getInstance().getFileSerializationWriter(file);
            writer.write(figureList);
            writer.close();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Implementation of method loadFromFile.
     *
     * @param file object of {@link File}
     * @throws ServiceException exception from {@link by.bsuir.kuzora.paint.dao} layer
     */
    @Override
    public void loadFromFile(File file) throws ServiceException {
        try {
            SerializationReader reader = DAOFactory.getInstance().getFileSerializationReader(file);
            figureList.clear();
            figureList = reader.read(figureList);
            reader.close();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private Figure getSelected(double x, double y) {
        for (int i = figureList.size() - 1; i >= 0; i--) {
            Figure figure = figureList.get(i);
            if (figure.contains(x, y)) {
                return figure;
            }
        }
        return null;
    }
}
