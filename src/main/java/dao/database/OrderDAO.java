package dao.database;

import java.sql.SQLException;

public interface OrderDAO {
    void addOrder(String name, String surname, String email, String service, String carName, String price, String phone, String date)throws SQLException;
    void deleteOrder(String name, String surname, String email, String service, String mark, String price, String date, String phone)throws SQLException;
}
