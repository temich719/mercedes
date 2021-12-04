package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public abstract class AbstractDAO {

    private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class);

    protected static ConnectionPool connectionPool;

    public AbstractDAO(ConnectionPool connectionPool1) {
        connectionPool = connectionPool1;
    }

    protected boolean isAlreadyExistsInDatabase(String parameter, String query, Connection connection) throws SQLException {
        boolean isAlreadyExists = false;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            if (resultSet.getString(2).equals(parameter)){
                isAlreadyExists = true;
                break;
            }
        }
        return isAlreadyExists;
    }

    protected void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable autoCloseable : autoCloseables) {
            if (Objects.nonNull(autoCloseable)) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

}
