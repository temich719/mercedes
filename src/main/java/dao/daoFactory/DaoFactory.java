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
    private final UserDAOTime userDAOTime;
    private final JspDAOImpl dataBase;

    private DaoFactory(){
        this.carDAO = new CarDAOImpl(connectionPool);
        this.orderDAO = new OrderDAOImpl(connectionPool);
        this.userDAOTime = new UserDAOImpl(connectionPool);
        this.dataBase = new JspDAOImpl(connectionPool);
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

    public UserDAOTime getUserDAOTime() {
        return userDAOTime;
    }

    public JspDAOImpl getDataBase(){return dataBase;}
}
