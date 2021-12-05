package dao.entity.car;

import dao.entity.AbstractCar;

import java.util.Objects;

public class Car extends AbstractCar {

    private final String price;
    private final String power;
    private final String accelerationTillHundred;
    private final String consumption;
    private final String engineVolume;
    private final String tankVolume;
    private final String trunkVolume;
    private final String maxSpeed;
    private final String type;

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

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (!Objects.equals(price, car.price)) return false;
        if (!Objects.equals(power, car.power)) return false;
        if (!Objects.equals(accelerationTillHundred, car.accelerationTillHundred))
            return false;
        if (!Objects.equals(consumption, car.consumption)) return false;
        if (!Objects.equals(engineVolume, car.engineVolume)) return false;
        if (!Objects.equals(tankVolume, car.tankVolume)) return false;
        if (!Objects.equals(trunkVolume, car.trunkVolume)) return false;
        if (!Objects.equals(maxSpeed, car.maxSpeed)) return false;
        return Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (power != null ? power.hashCode() : 0);
        result = 31 * result + (accelerationTillHundred != null ? accelerationTillHundred.hashCode() : 0);
        result = 31 * result + (consumption != null ? consumption.hashCode() : 0);
        result = 31 * result + (engineVolume != null ? engineVolume.hashCode() : 0);
        result = 31 * result + (tankVolume != null ? tankVolume.hashCode() : 0);
        result = 31 * result + (trunkVolume != null ? trunkVolume.hashCode() : 0);
        result = 31 * result + (maxSpeed != null ? maxSpeed.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price='" + price + '\'' +
                ", power='" + power + '\'' +
                ", accelerationTillHundred='" + accelerationTillHundred + '\'' +
                ", consumption='" + consumption + '\'' +
                ", engineVolume='" + engineVolume + '\'' +
                ", tankVolume='" + tankVolume + '\'' +
                ", trunkVolume='" + trunkVolume + '\'' +
                ", maxSpeed='" + maxSpeed + '\'' +
                ", type='" + type + '\'' +
                ", nameOfMark='" + nameOfMark + '\'' +
                ", price='" + price + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
