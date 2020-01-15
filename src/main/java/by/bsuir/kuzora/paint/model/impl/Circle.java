package by.bsuir.kuzora.paint.model.impl;

import by.bsuir.kuzora.paint.model.constants.FigureType;
import by.bsuir.kuzora.paint.model.interfaces.Editable;
import by.bsuir.kuzora.paint.model.interfaces.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static by.bsuir.kuzora.paint.model.constants.Constants.DEFAULT_LINE_WIDTH;
import static java.lang.Math.min;

/**
 * Circle class extends {@link Figure} and if possible implements {@link Editable}.
 * <p>
 * Class {@link Circle} includes implementations for 2 abstract methods getType(), draw() which come from {@link Figure} class.
 * <p>
 * <i>This class is a member of the {@link by.bsuir.kuzora.paint.model.impl} package.</i>
 */
public class Circle extends Figure implements Editable {

    /**
     * Constructor Circle.
     *
     * @param x1 x coordinate for first point
     * @param x2 x coordinate for second point
     * @param y1 y coordinate for first point
     * @param y2 y coordinate for second point
     */
    public Circle(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    /**
     * Implementation for method draw.
     *
     * @param context object of {@link GraphicsContext} type.
     */
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.valueOf(getFillColor()));
        context.setStroke(Color.valueOf(getPenColor()));
        context.setLineWidth(DEFAULT_LINE_WIDTH);
        double w = getWidth();
        double h = getHeight();
        context.fillOval(getX1(), getY1(), min(w, h), min(w, h));
        context.strokeOval(getX1(), getY1(), min(w, h), min(w, h));
    }

    /**
     * Implementation for method getType.
     *
     * @return object of {@link FigureType} type.
     */
    @Override
    public FigureType getType() {
        return FigureType.CIRCLE;
    }
}
