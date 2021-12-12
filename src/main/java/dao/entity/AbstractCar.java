package dao.entity;

import java.util.Objects;

public class AbstractCar {

    protected int id;
    protected String nameOfMark;
    protected String price;
    protected String imagePath;

    public AbstractCar(String nameOfMark, String price, String imagePath) {
        this.nameOfMark = nameOfMark;
        this.price = price;
        this.imagePath = imagePath;
    }

    public AbstractCar(int id, String nameOfMark, String price, String imagePath) {
        this.id = id;
        this.nameOfMark = nameOfMark;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getNameOfMark() {
        return nameOfMark;
    }

    public String getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCar that = (AbstractCar) o;

        if (!Objects.equals(nameOfMark, that.nameOfMark)) return false;
        if (!Objects.equals(price, that.price)) return false;
        return Objects.equals(imagePath, that.imagePath);
    }

    @Override
    public int hashCode() {
        int result = nameOfMark != null ? nameOfMark.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractCar{" +
                "nameOfMark='" + nameOfMark + '\'' +
                ", price='" + price + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}