package by.bsuir.kuzora.paint.service;

import by.bsuir.kuzora.paint.service.impl.FigureCanvasImpl;
import by.bsuir.kuzora.paint.service.interfaces.FigureCanvas;

/**
 * Class {@link ServiceFactory}.
 * <p>
 * Class ServiceFactory includes methods which return need type of {@link FigureCanvas} now available only one.
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.service}
 * package.</i>
 */
public class ServiceFactory {

    /**
     * Instance of {@link ServiceFactory}
     */
    private static final ServiceFactory instance = new ServiceFactory();

    /**
     * Instance of {@link FigureCanvas}
     */
    private static final FigureCanvas figureCanvasImpl = new FigureCanvasImpl();

    /**
     * @return instance
     * of {@link ServiceFactory} object
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * @return instance
     * of {@link FigureCanvas} object
     */
    public FigureCanvas getFigureCanvas() {
        return figureCanvasImpl;
    }
}
