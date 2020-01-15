package by.bsuir.kuzora.paint.dao.impl;

import by.bsuir.kuzora.paint.dao.exception.DAOException;
import by.bsuir.kuzora.paint.dao.interfaces.SerializationReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Class {@link FileSerializationReader}.
 * <p>
 * Class {@link FileSerializationReader} includes 2 implementation for methods from {@link SerializationReader} interface
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.dao.impl}
 * package.</i>
 */
public class FileSerializationReader implements SerializationReader {
    /**
     * Input stream for deserialization process.
     */
    private ObjectInputStream reader;

    /**
     * Constructor FileSerializationReader.
     *
     * @param file {@link File} filename for stream.
     * @throws DAOException if method generate any exception(IO, FileNotFound and etc) on DAO layer.
     */
    public FileSerializationReader(File file) throws DAOException {
        try {
            reader = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Public method read.
     * <p>
     * Deserialization method.
     *
     * @param castObject object casting object
     * @return casted deserialization object
     * @throws DAOException if method generate any exception(IO, FileNotFound and etc) on DAO layer.
     */
    @Override
    public <T> T read(T castObject) throws DAOException {
        try {
            return (T) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Public method close.
     * <p>
     * Deserialization method for close stream.
     *
     * @throws DAOException if method generate any exception(IO, FileNotFound and etc) on DAO layer.
     */
    @Override
    public void close() throws DAOException {
        try {
            reader.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
