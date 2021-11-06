package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {
    protected static ConnectionPool connectionPool;

    public AbstractDAO(ConnectionPool connectionPool1){
        connectionPool = connectionPool1;
    }

    protected boolean isAlreadyExistsInDatabase(String parameter, String query, Connection connection)throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            if (resultSet.getString(2).equals(parameter))
                return true;
        }
        return false;
    }

}
