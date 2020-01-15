package by.bsuir.kuzora.paint.model.interfaces;

import by.bsuir.kuzora.paint.model.constants.FigurePart;
import by.bsuir.kuzora.paint.model.constants.FigureType;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

import static by.bsuir.kuzora.paint.model.constants.Constants.DEFAULT_SIZE;

/**
 * Abstract class {@link Figure}.
 * <p>
 * Class {@link Figure} includes default getters and setters, 2 abstract methods getType(), draw().
 * Also class implements {@link Serializable} interface for serialization.
 * <p>
 * <i>This class is a member of the {@link by.bsuir.kuzora.paint.model.interfaces}
 * package.</i>
 */
public abstract class Figure implements Serializable {
    /**
     * Serial version uuid.
     */
    private static final long serialversionUID = 1L;
    /**
     * X1, Point(0, 0) is on the right top.
     */
    private double x1;
    /**
     * Y1.
     */
    private double y1;
    /**
     * X2.
     */
    private double x2;
    /**
     * Y2.
     */
    private double y2;
    /**
     * Name of fill color in format xxxxxx(where x is number from 0 to F).
     */
    private String fillColor;
    /**
     * Name of border color in format xxxxxx(where x is number from 0 to F).
     */
    private String penColor;

    /**
     * Constructor Figure.
     *
     * @param x1 x coordinate for first point
     * @param x2 x coordinate for second point
     * @param y1 y coordinate for first point
     * @param y2 y coordinate for second point
     */
    public Figure(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    protected String getFillColor() {
        return fillColor;
    }

    protected String getPenColor() {
        return penColor;
    }

    public void setPenColor(String penColor) {
        this.penColor = penColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Abstract method draw.
     *
     * @param context object of {@link GraphicsContext} type.
     */
    public abstract void draw(GraphicsContext context);

    /**
     * Abstract method getType.
     *
     * @return object of {@link FigureType} type.
     */
    public abstract FigureType getType();

    /**
     * Method resize.
     *
     * @param deltaX x offset
     * @param deltaY y offset
     */
    public final void resize(double deltaX, double deltaY) {
        x2 += deltaX;
        y2 += deltaY;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getWidth() {
        return x2 - x1;
    }

    public double getHeight() {
        return y2 - y1;
    }

    public boolean contains(double x, double y) {
        return x1 <= x + DEFAULT_SIZE && x - DEFAULT_SIZE <= x2 && y1 <= y + DEFAULT_SIZE && y - DEFAULT_SIZE <= y2;
    }

    /**
     * Method moveDelta.
     *
     * @param deltaX x offset
     * @param deltaY y offset
     */
    void moveDelta(double deltaX, double deltaY) {
        x1 += deltaX;
        x2 += deltaX;
        y1 += deltaY;
        y2 += deltaY;
    }

    /**
     * Private method editable.
     *
     * @return boolean value as the result of confirm that implementation of {@link Figure} is implemented from {@link Editable} interface.
     */
    private boolean editable() {
        return this instanceof Editable;
    }

    public void resizeMove(FigurePart part, double deltaX, double deltaY) {
        switch (part) {
            case LEFT_TOP:
                if (editable()) {
                    x1 += deltaX;
                    y1 += deltaY;
                }
                break;
            case RIGHT_BOTTOM:
                if (editable()) {
                    x2 += deltaX;
                    y2 += deltaY;
                }
                break;
            case INSIDE:
                moveDelta(deltaX, deltaY);
                break;
            case OUTSIDE:
                break;
        }
    }

    /**
     * Private method getFigurePart.
     *
     * @param x x mouse action coordinate
     * @param y y mouse action coordinate
     * @return FigurePart
     * value as part of checking where is mouse cursor.
     */
    public FigurePart getFigurePart(double x, double y) {
        if (isNear(x1, x) && isNear(y1, y)) {
            return FigurePart.LEFT_TOP;
        }
        if (isNear(x2, x) && isNear(y2, y)) {
            return FigurePart.RIGHT_BOTTOM;
        }
        if (x1 <= x && x <= x2 && y1 <= y && y <= y2) {
            return FigurePart.INSIDE;
        }
        return FigurePart.OUTSIDE;
    }

    private static boolean isNear(double a, double b) {
        return Math.abs(a - b) < DEFAULT_SIZE;
    }
}