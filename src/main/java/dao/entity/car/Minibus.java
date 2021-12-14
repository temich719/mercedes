package dao.entity.car;

import dao.entity.AbstractCar;

import java.util.Objects;

public class Minibus extends AbstractCar {

    private final String fullLoad;
    private final String curbWeight;

    public Minibus(String nameOfMark, String price, String imagePass, String fullLoad, String curbWeight) {
        super(nameOfMark, price, imagePass);
        this.fullLoad = fullLoad;
        this.curbWeight = curbWeight;
    }

    public Minibus(int id ,String nameOfMark, String price, String imagePass, String fullLoad, String curbWeight) {
        super(id, nameOfMark, price, imagePass);
        this.fullLoad = fullLoad;
        this.curbWeight = curbWeight;
    }

    public String getFullLoad() {
        return fullLoad;
    }

    public String getCurbWeight() {
        return curbWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Minibus minibus = (Minibus) o;

        if (!Objects.equals(fullLoad, minibus.fullLoad)) return false;
        return Objects.equals(curbWeight, minibus.curbWeight);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (fullLoad != null ? fullLoad.hashCode() : 0);
        result = 31 * result + (curbWeight != null ? curbWeight.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Minibus{" +
                "fullLoad='" + fullLoad + '\'' +
                ", curbWeight='" + curbWeight + '\'' +
                '}';
    }
}