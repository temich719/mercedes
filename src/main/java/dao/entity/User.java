package dao.entity;

public class User {

    private static int id = 0;
    private final String name;
    private final String surname;
    private final String accessType;
    private final String email;
    private final String password;

    public User(String name, String surname, String accessType, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.accessType = accessType;
        this.email = email;
        this.password = password;
    }

}
