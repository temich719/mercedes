package dao.databaseConnectionImpl;

import dao.DataBaseConfigReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DataBaseConfigReaderImpl implements DataBaseConfigReader {
    private Properties properties;

    public DataBaseConfigReaderImpl() {
        load();
    }

    private void load() {
        properties = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("C:\\Projects\\Servlet\\src\\main\\resources\\databaseConfig.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(String name) {
        return properties.getProperty(name);
    }
}
