package by.bsuir.kuzora.paint.dao.impl;

import by.bsuir.kuzora.paint.dao.exception.DAOException;
import by.bsuir.kuzora.paint.dao.interfaces.SerializationWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Class {@link FileSerializationWriter}.
 * <p>
 * Class {@link FileSerializationWriter} includes 2 implementation for methods from {@link SerializationWriter} interface
 * <p>
 * <i>This Class is a member of the {@link by.bsuir.kuzora.paint.dao.impl}
 * package.</i>
 */
public class FileSerializationWriter implements SerializationWriter {
    /**
     * Output stream for serialization process.
     */
    private ObjectOutputStream writer;

    /**
     * Constructor FileSerializationWriter.
     *
     * @param file {@link File} filename for stream.
     * @throws DAOException if method generate any exception(IO, FileNotFound and etc) on DAO layer.
     */
    public FileSerializationWriter(File file) throws DAOException {
        try {
            writer = new ObjectOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Public method write.
     * <p>
     * Serialization method.
     *
     * @param object {@link Object} object for serialization.
     * @throws DAOException if method generate any exception(IO, FileNotFound and etc) on DAO layer.
     */
    @Override
    public void write(Object object) throws DAOException {
        try {
            writer.writeObject(object);
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Public method close.
     * <p>
     * Serialization method for close stream.
     *
     * @throws DAOException if method generate any exception(IO, FileNotFound and etc) on DAO layer.
     */
    @Override
    public void close() throws DAOException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }
}
