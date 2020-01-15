package by.bsuir.kuzora.paint.dao.exception;

/**
 * Class {@link DAOException}.
 * <p>
 * Class DAOException - user exception class for {@link by.bsuir.kuzora.paint.dao}
 * layer, extends Exception Class and overloads 3 constructors with different
 * parameters.
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.dao.exception}
 * package.</i>
 */
public class DAOException extends Exception {
    /**
     * Constructor.
     *
     * @param e       as Exception object for saving first cause of exception
     * @param message as info for creating user exception
     */
    public DAOException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Constructor.
     *
     * @param message as info for creating user exception
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param e as Exception object for saving first cause of exception
     */
    public DAOException(Exception e) {
        super(e);
    }
}
