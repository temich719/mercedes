package dao.databaseConnectionImpl;

import dao.ConnectionPool;
import dao.DataBaseConfigReader;
import dao.exception.DAOException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static dao.DAOFinals.*;

public class ConnectionPoolImpl implements ConnectionPool{

    private final String DRIVER;
    private final String FIXED_URL;
    private static boolean driverIsLoaded = false;

    private final BlockingQueue<Connection> availableConnections;
    private final BlockingQueue<Connection> takenConnections;


    public ConnectionPoolImpl(DataBaseConfigReader databaseConfigReader) {
        this.DRIVER = databaseConfigReader.get(SQL_DRIVER);
        String url = databaseConfigReader.get(DB_URL);
        String user = databaseConfigReader.get(DB_LOGIN);
        String password = databaseConfigReader.get(DB_PASSWORD);
        this.FIXED_URL = url + "?user=" + user + "&password=" + password;//может быть не сработает
        int amountOfConnections = Integer.parseInt(databaseConfigReader.get(DB_CONNECTIONS));
        this.availableConnections = new ArrayBlockingQueue<>(amountOfConnections);
        this.takenConnections = new ArrayBlockingQueue<>(amountOfConnections);

        try {
            getJDBCDriver();
            for (int i = 0; i < amountOfConnections; i++) {
                availableConnections.add(getConnection());
            }
        } catch (DAOException e) {}
    }

    @Override
    public Connection provide() throws DAOException {
        Connection newConnection;
        try{
            newConnection = availableConnections.take();
            takenConnections.add(newConnection);
        } catch (InterruptedException e) {
            throw new DAOException(e);
        }
        return newConnection;
    }

    @Override
    public void retrieve(Connection connection) throws DAOException {
        if (connection != null) {
            takenConnections.remove(connection);
            availableConnections.add(connection);
        } else {
            System.out.println("Connection is NULL");
        }
    }

    /**
     * creates a connection
     */
    private Connection getConnection() throws DAOException{
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(FIXED_URL);
        } catch (SQLException e) {
            shutdownTomcat();
            throw new DAOException("Connection to database failed", e);
        }
        return connection;
    }

    /**
     * method loads database driver
     */
    private void getJDBCDriver() throws DAOException {
        if (!driverIsLoaded) {
            try {
                Class.forName(DRIVER);
                driverIsLoaded = true;
            } catch (ClassNotFoundException e) {
                shutdownTomcat();
                throw new DAOException("MySQL driver is not loaded", e);
            }
        }
    }

    /**
     * in case of errors we can stop tomcat
     */
    private static void shutdownTomcat() throws DAOException{
        try {
            Socket socket = new Socket("localhost", 8005);
            if (socket.isConnected()) {
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println("SHUTDOWN");
                pw.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new DAOException("Can NOT stop TOMCAT", e);
        }
    }
}
