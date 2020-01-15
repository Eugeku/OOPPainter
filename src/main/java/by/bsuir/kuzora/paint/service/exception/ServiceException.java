package by.bsuir.kuzora.paint.service.exception;

/**
 * Class {@link ServiceException}.
 * <p>
 * Class ServiceException - user exception class for {@link by.bsuir.kuzora.paint.service.exception}
 * layer, extends Exception Class and overloads 3 constructors with different
 * parameters.
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.service.exception}
 * package.</i>
 */
public class ServiceException extends Exception {
    /**
     * Constructor.
     *
     * @param e       as Exception object for saving first cause of exception
     * @param message as info for creating user exception
     */
    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Constructor.
     *
     * @param message as info for creating user exception
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param e as Exception object for saving first cause of exception
     */
    public ServiceException(Exception e) {
        super(e);
    }
}
