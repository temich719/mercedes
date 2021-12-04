package dao.entity;

import java.util.Objects;

public class UserDTO {
    private int id;
    private final String name;
    private final String surname;
    private final String accessType;
    private final String email;

    public UserDTO(String name, String surname, String email, String accessType) {
        this.name = name;
        this.surname = surname;
        this.accessType = accessType;
        this.email = email;
    }

    public UserDTO(int id, String name, String surname, String accessType, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accessType = accessType;
        this.email = email;
    }

    public int getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != userDTO.id) return false;
        if (!Objects.equals(name, userDTO.name)) return false;
        if (!Objects.equals(surname, userDTO.surname)) return false;
        if (!Objects.equals(accessType, userDTO.accessType)) return false;
        return Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (accessType != null ? accessType.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accessType='" + accessType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
