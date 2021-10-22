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

}