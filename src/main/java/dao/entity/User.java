package dao.entity;

public class User {

    private static int id = 0;
    private final String name;
    private final String surname;
    private final String status;
    private final String email;
    private final String password;

    public User(String name, String surname, String status, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.email = email;
        this.password = password;
    }

}
