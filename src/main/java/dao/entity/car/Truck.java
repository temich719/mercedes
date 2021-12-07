package dao.entity.car;

import dao.entity.AbstractCar;

public class Truck extends AbstractCar {
    public Truck(String nameOfMark, String price, String imagePath) {
        super(nameOfMark, price, imagePath);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "nameOfMark='" + nameOfMark + '\'' +
                ", price='" + price + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}