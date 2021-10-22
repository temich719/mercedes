package dao.database;

import java.sql.SQLException;

public interface OrderDAO {
    void addOrder(String name, String surname, String email, String service, String carName, String price, String phone, String date)throws SQLException;
}
