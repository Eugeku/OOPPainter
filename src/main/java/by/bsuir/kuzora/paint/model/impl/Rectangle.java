package by.bsuir.kuzora.paint.model.impl;

import by.bsuir.kuzora.paint.model.constants.FigureType;
import by.bsuir.kuzora.paint.model.interfaces.Editable;
import by.bsuir.kuzora.paint.model.interfaces.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static by.bsuir.kuzora.paint.model.constants.Constants.DEFAULT_LINE_WIDTH;

/**
 * Rectangle class extends {@link Figure} and if possible implements {@link Editable}.
 * <p>
 * Class {@link Rectangle} includes implementations for 2 abstract methods getType(), draw() which come from {@link Figure} class.
 * <p>
 * <i>This class is a member of the {@link by.bsuir.kuzora.paint.model.impl} package.</i>
 */
public class Rectangle extends Figure implements Editable {

    /**
     * Constructor Rectangle.
     *
     * @param x1 x coordinate for first point
     * @param x2 x coordinate for second point
     * @param y1 y coordinate for first point
     * @param y2 y coordinate for second point
     */
    public Rectangle(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    /**
     * Implementation for method draw.
     *
     * @param context object of {@link GraphicsContext} type.
     */
    public void draw(GraphicsContext context) {
        context.setFill(Color.valueOf(getFillColor()));
        context.setStroke(Color.valueOf(getPenColor()));
        context.setLineWidth(DEFAULT_LINE_WIDTH);
        double x1 = getX1(), x2 = getX2(), y1 = getY1(), y2 = getY2();
        double xs[] = new double[]{x1, x1, x2, x2};
        double ys[] = new double[]{y1, y2, y2, y1};
        context.fillPolygon(xs, ys, xs.length);
        context.strokePolygon(xs, ys, xs.length);
    }

    /**
     * Implementation for method getType.
     *
     * @return object of {@link FigureType} type.
     */
    @Override
    public FigureType getType() {
        return FigureType.RECTANGLE;
    }
}