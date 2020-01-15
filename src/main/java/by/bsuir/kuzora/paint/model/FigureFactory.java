package by.bsuir.kuzora.paint.model;

import by.bsuir.kuzora.paint.model.constants.FigureType;
import by.bsuir.kuzora.paint.model.impl.Circle;
import by.bsuir.kuzora.paint.model.impl.Line;
import by.bsuir.kuzora.paint.model.impl.Oval;
import by.bsuir.kuzora.paint.model.impl.Rectangle;
import by.bsuir.kuzora.paint.model.impl.RightTriangle;
import by.bsuir.kuzora.paint.model.impl.Square;
import by.bsuir.kuzora.paint.model.impl.Triangle;
import by.bsuir.kuzora.paint.model.interfaces.Figure;

import static by.bsuir.kuzora.paint.model.constants.Constants.DEFAULT_SIZE;

/**
 * Class {@link FigureFactory}.
 * <p>
 * Class DAOFactory includes method which returns need created figure object.
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.model}
 * package.</i>
 */
public class FigureFactory {

    /**
     * Instance of {@link FigureFactory}
     */
    private static final FigureFactory instance = new FigureFactory();

    /**
     * @return instance
     * of {@link FigureFactory} object
     */
    public static FigureFactory getInstance() {
        return instance;
    }

    /**
     * Method create
     *
     * @param figureType   object of enum {@link FigureType}
     * @param bottomRightX x coordinate for right bottom figure point.
     * @param bottomRightY y coordinate for right bottom figure point.
     * @return instance of {@link FigureFactory} object
     */
    public Figure create(FigureType figureType, double bottomRightX, double bottomRightY) {
        switch (figureType) {
            case RECTANGLE:
                return new Rectangle(bottomRightX - DEFAULT_SIZE, bottomRightY - DEFAULT_SIZE, bottomRightX, bottomRightY);
            case SQUARE:
                return new Square(bottomRightX - DEFAULT_SIZE, bottomRightY - DEFAULT_SIZE, bottomRightX, bottomRightY);
            case OVAL:
                return new Oval(bottomRightX - DEFAULT_SIZE, bottomRightY - DEFAULT_SIZE, bottomRightX, bottomRightY);
            case CIRCLE:
                return new Circle(bottomRightX - DEFAULT_SIZE, bottomRightY - DEFAULT_SIZE, bottomRightX, bottomRightY);
            case TRIANGLE:
                return new Triangle(bottomRightX - DEFAULT_SIZE, bottomRightY - DEFAULT_SIZE, bottomRightX, bottomRightY);
            case RIGHT_TRIANGLE:
                return new RightTriangle(bottomRightX - DEFAULT_SIZE, bottomRightY - DEFAULT_SIZE, bottomRightX, bottomRightY);
            case LINE:
                return new Line(bottomRightX, bottomRightY, bottomRightX, bottomRightY);
            default:
                throw new IllegalArgumentException("Unsupported figure type: " + figureType);
        }
    }
}
