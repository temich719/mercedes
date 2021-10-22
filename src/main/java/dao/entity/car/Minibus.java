package dao.entity.car;

import dao.entity.AbstractCar;

public class Minibus extends AbstractCar {

    public Minibus(String nameOfMark, String price, String imagePass, String fullLoad, String curbWeight){
        this.nameOfMark = nameOfMark;
        this.price = price;
        this.imagePath = imagePass;
        this.fullLoad = fullLoad;
        this.curbWeight = curbWeight;
    }

    @Override
    public String toString() {
        return "Minibus{" +
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