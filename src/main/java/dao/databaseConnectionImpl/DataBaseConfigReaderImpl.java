package dao.databaseConnectionImpl;

import dao.DataBaseConfigReader;
import dao.exception.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static dao.DAOFinals.DATABASE_SETTINGS_PATH;

public class DataBaseConfigReaderImpl implements DataBaseConfigReader {
    private Properties properties;

    public DataBaseConfigReaderImpl() {
        try {
            load();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Loads properties' file</p>
     * @throws DAOException module exception
     */
    private void load() throws DAOException {
        try (InputStream is = DataBaseConfigReaderImpl.class.getClassLoader().getResourceAsStream(DATABASE_SETTINGS_PATH)) {
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            throw new DAOException("Error while initializing DatabaseConfigReader", e);
        }
    }

    @Override
    public String get(String name) {
        return properties.getProperty(name);
    }
}
