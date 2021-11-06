package dao.entity;

// TODO: 06.11.2021 изменить password на char[], добавить equals hashCode toString
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

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAccessType() {
        return accessType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
