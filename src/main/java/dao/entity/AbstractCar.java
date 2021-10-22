package dao.entity;

public class AbstractCar {

    protected String nameOfMark;
    protected String price;
    protected String power;
    protected String accelerationTillHundred;
    protected String consumption;
    protected String engineVolume;
    protected String tankVolume;
    protected String trunkVolume;
    protected String maxSpeed;
    protected String imagePath;
    protected String type;
    protected String fullLoad;
    protected String curbWeight;

    public String getNameOfMark() {
        return nameOfMark;
    }

    public String getPrice() {
        return price;
    }

    public String getPower() {
        return power;
    }

    public String getAccelerationTillHundred() {
        return accelerationTillHundred;
    }

    public String getConsumption() {
        return consumption;
    }

    public String getEngineVolume() {
        return engineVolume;
    }

    public String getTankVolume() {
        return tankVolume;
    }

    public String getTrunkVolume() {
        return trunkVolume;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getType(){
        return type;
    }
}