package dao.entity.car;

import dao.entity.AbstractCar;

public class Car extends AbstractCar {

    public Car(String nameOfMark, String price, String power, String accelerationTillHundred, String consumption, String engineVolume, String tankVolume, String trunkVolume, String maxSpeed, String imagePath, String type) {
        this.nameOfMark = nameOfMark;
        this.price = price;
        this.power = power;
        this.accelerationTillHundred = accelerationTillHundred;
        this.consumption = consumption;
        this.engineVolume = engineVolume;
        this.tankVolume = tankVolume;
        this.trunkVolume = trunkVolume;
        this.maxSpeed = maxSpeed;
        this.imagePath = imagePath;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Car{" +
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
