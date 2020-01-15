package by.bsuir.kuzora.paint.service.interfaces;

import by.bsuir.kuzora.paint.model.interfaces.Figure;
import by.bsuir.kuzora.paint.service.exception.ServiceException;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;

/**
 * Interface {@link FigureCanvas}.
 * <p>
 * Interface {@link FigureCanvas} 11 abstract methods.
 * <p>
 * <i>This class is a member of the {@link by.bsuir.kuzora.paint.service.interfaces}
 * package.</i>
 */
public interface FigureCanvas {

    /**
     * Abstract method clear.
     * <p>
     * Method for clear canvas.
     *
     * @param context object of {@link GraphicsContext} type.
     * @param w       width of painting canvas
     * @param h       height of painting canvas
     */
    void clear(GraphicsContext context, double w, double h);

    /**
     * Abstract method redraw.
     * <p>
     * Method for redraw canvas.
     *
     * @param context object of {@link GraphicsContext} type.
     * @param w       width of painting canvas
     * @param h       height of painting canvas
     */
    void redraw(GraphicsContext context, double w, double h);

    /**
     * Abstract method deleteSelected.
     * <p>
     * Method for delete selected figure on canvas.
     *
     * @param context object of {@link GraphicsContext} type.
     * @param w       width of painting canvas
     * @param h       height of painting canvas
     */
    void deleteSelected(GraphicsContext context, double w, double h);

    /**
     * Abstract method add.
     * <p>
     * Method for adding figure in list for redraw.
     *
     * @param figure object of {@link Figure} type.
     */
    void add(Figure figure);

    /**
     * Abstract method resizeLast.
     *
     * @param deltaX delta x for resize
     * @param deltaY delta y for resize
     */
    void resizeLast(double deltaX, double deltaY);

    /**
     * Abstract method removeLast.
     * <p>
     * Method for removing last figure.
     */
    void removeLast();

    /**
     * Abstract method redo.
     */
    void redo();

    /**
     * Abstract method selectAndRedraw.
     *
     * @param x       current action x
     * @param y       current action y
     * @param context object of {@link GraphicsContext} type.
     * @param w       width of painting canvas
     * @param h       height of painting canvas
     */
    void selectAndRedraw(double x, double y, GraphicsContext context, double w, double h);

    /**
     * Abstract method resizeMoveSelected.
     *
     * @param x      current action x
     * @param y      current action y
     * @param deltaX delta x for resize
     * @param deltaY delta y for resize
     */
    void resizeMoveSelected(double x, double y, double deltaX, double deltaY);

    /**
     * Abstract method saveToFile.
     *
     * @param file object of {@link File}
     * @throws ServiceException exception from {@link by.bsuir.kuzora.paint.dao} layer
     */
    void saveToFile(File file) throws ServiceException;

    /**
     * Abstract method loadFromFile.
     *
     * @param file object of {@link File}
     * @throws ServiceException exception from {@link by.bsuir.kuzora.paint.dao} layer
     */
    void loadFromFile(File file) throws ServiceException;
}
