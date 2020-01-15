package by.bsuir.kuzora.paint.dao.interfaces;

import by.bsuir.kuzora.paint.dao.exception.DAOException;

/**
 * Interface {@link SerializationReader}.
 * <p>
 * Interface {@link SerializationReader} includes 2 methods for read and close stream for deserialization.
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.dao.interfaces}
 * package.</i>
 */
public interface SerializationReader {

    <T> T read(T tClass) throws DAOException;

    void close() throws DAOException;
}
