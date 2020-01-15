package by.bsuir.kuzora.paint.dao.interfaces;

import by.bsuir.kuzora.paint.dao.exception.DAOException;

/**
 * Interface {@link SerializationWriter}.
 * <p>
 * Interface {@link SerializationWriter} includes 2 methods for write and close stream for serialization.
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.dao.interfaces}
 * package.</i>
 */
public interface SerializationWriter {

    void write(Object object) throws DAOException;

    void close() throws DAOException;
}
