package dao.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractCar that = (AbstractCar) o;

        if (!Objects.equals(nameOfMark, that.nameOfMark)) return false;
        if (!Objects.equals(price, that.price)) return false;
        if (!Objects.equals(power, that.power)) return false;
        if (!Objects.equals(accelerationTillHundred, that.accelerationTillHundred))
            return false;
        if (!Objects.equals(consumption, that.consumption)) return false;
        if (!Objects.equals(engineVolume, that.engineVolume)) return false;
        if (!Objects.equals(tankVolume, that.tankVolume)) return false;
        if (!Objects.equals(trunkVolume, that.trunkVolume)) return false;
        if (!Objects.equals(maxSpeed, that.maxSpeed)) return false;
        if (!Objects.equals(imagePath, that.imagePath)) return false;
        if (!Objects.equals(type, that.type)) return false;
        if (!Objects.equals(fullLoad, that.fullLoad)) return false;
        return Objects.equals(curbWeight, that.curbWeight);
    }

    @Override
    public int hashCode() {
        int result = nameOfMark != null ? nameOfMark.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (power != null ? power.hashCode() : 0);
        result = 31 * result + (accelerationTillHundred != null ? accelerationTillHundred.hashCode() : 0);
        result = 31 * result + (consumption != null ? consumption.hashCode() : 0);
        result = 31 * result + (engineVolume != null ? engineVolume.hashCode() : 0);
        result = 31 * result + (tankVolume != null ? tankVolume.hashCode() : 0);
        result = 31 * result + (trunkVolume != null ? trunkVolume.hashCode() : 0);
        result = 31 * result + (maxSpeed != null ? maxSpeed.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (fullLoad != null ? fullLoad.hashCode() : 0);
        result = 31 * result + (curbWeight != null ? curbWeight.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractCar{" +
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