package by.bsuir.kuzora.paint.dao;

import by.bsuir.kuzora.paint.dao.exception.DAOException;
import by.bsuir.kuzora.paint.dao.impl.FileSerializationReader;
import by.bsuir.kuzora.paint.dao.impl.FileSerializationWriter;
import by.bsuir.kuzora.paint.dao.interfaces.SerializationReader;
import by.bsuir.kuzora.paint.dao.interfaces.SerializationWriter;

import java.io.File;

/**
 * Class {@link DAOFactory}.
 * <p>
 * Class DAOFactory includes methods which return need type of {@link SerializationReader} / {@link SerializationWriter}
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.dao}
 * package.</i>
 */
public class DAOFactory {

    /**
     * Instance of {@link DAOFactory}
     */
    private static final DAOFactory instance = new DAOFactory();

    /**
     * @return instance
     *            of {@link DAOFactory} object
     */
    public static DAOFactory getInstance() {
        return instance;
    }

    /**
     * @return instance
     *            of {@link FileSerializationReader} object
     */
    public SerializationReader getFileSerializationReader(File file) throws DAOException {
        return new FileSerializationReader(file);
    }

    /**
     * @return instance
     *            of {@link SerializationWriter} object
     */
    public SerializationWriter getFileSerializationWriter(File file) throws DAOException {
        return new FileSerializationWriter(file);
    }
}
