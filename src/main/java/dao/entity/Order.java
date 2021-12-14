package dao.entity;

import java.util.Objects;

public class Order {
    private int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String service;
    private final String mark;
    private final String price;
    private final String phone;
    private final String date;
    private final String status;

    public Order(String name, String surname, String email, String service, String mark, String price, String phone, String date, String status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.service = service;
        this.mark = mark;
        this.price = price;
        this.phone = phone;
        this.date = date;
        this.status = status;
    }

    public Order(int id ,String name, String surname, String email, String service, String mark, String price, String phone, String date, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.service = service;
        this.mark = mark;
        this.price = price;
        this.phone = phone;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus(){
        return status;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getService() {
        return service;
    }

    public String getMark() {
        return mark;
    }

    public String getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!Objects.equals(name, order.name)) return false;
        if (!Objects.equals(surname, order.surname)) return false;
        if (!Objects.equals(email, order.email)) return false;
        if (!Objects.equals(service, order.service)) return false;
        if (!Objects.equals(mark, order.mark)) return false;
        if (!Objects.equals(price, order.price)) return false;
        if (!Objects.equals(phone, order.phone)) return false;
        if (!Objects.equals(date, order.date)) return false;
        return Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", service='" + service + '\'' +
                ", mark='" + mark + '\'' +
                ", price='" + price + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
