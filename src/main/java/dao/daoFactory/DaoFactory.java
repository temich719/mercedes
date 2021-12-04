package dao.daoFactory;

import dao.*;
import dao.impl.JspDAOImpl;
import dao.databaseConnectionImpl.ConnectionPoolImpl;
import dao.databaseConnectionImpl.DataBaseConfigReaderImpl;
import dao.impl.CarDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.UserDAOImpl;

public class DaoFactory {

    private static final DataBaseConfigReader dataBaseConfigReader = new DataBaseConfigReaderImpl();
    private static final ConnectionPool connectionPool = new ConnectionPoolImpl(dataBaseConfigReader);

    private static final DaoFactory INSTANCE = new DaoFactory();
    private final CarDAO carDAO;
    private final OrderDAO orderDAO;
    private final UserDAO userDAO;
    private final JspDAOImpl jspDAO;

    private DaoFactory(){
        this.carDAO = new CarDAOImpl(connectionPool);
        this.orderDAO = new OrderDAOImpl(connectionPool);
        this.userDAO = new UserDAOImpl(connectionPool);
        this.jspDAO = new JspDAOImpl(connectionPool);
    }

    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public JspDAOImpl getJSPDao(){return jspDAO;}
}
