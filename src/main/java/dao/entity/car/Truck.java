package dao.entity.car;

import dao.entity.AbstractCar;

public class Truck extends AbstractCar {
    public Truck(String nameOfMark, String price, String imagePath) {
        this.nameOfMark = nameOfMark;
        this.price = price;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "nameOfMark='" + nameOfMark + '\'' +
                ", price='" + price + '\'' +
                ", power='" + power + '\'' +
                ", accelerationTillHundred='" + accelerationTillHundred + '\'' +
                ", consumption='" + consumption + '\'' +
                ", engineVolume='" + engineVolume + '\'' +
                ", tankVolume='" + tankVolume + '\'' +
                ", trunkVolume='" + trunkVolume + '\'' +
                ", maxSpeed='" + maxSpeed + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", type='" + type + '\'' +
                ", fullLoad='" + fullLoad + '\'' +
                ", curbWeight='" + curbWeight + '\'' +
                '}';
    }
}