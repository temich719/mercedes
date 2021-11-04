package dao.daoFactory;

import dao.database.impl.DataBaseImpl;

public class DaoFactory {

    private static final DataBaseImpl INSTANCE = new DataBaseImpl();

    private DaoFactory(){}

    public static DataBaseImpl getInstance(){
        return INSTANCE;
    }
}
