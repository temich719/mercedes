package dao.entity;

public class Pair {
    private final String first;
    private final String second;

    public Pair(final String first, final String second){
        this.first = first;
        this.second = second;
    }

    public String getFirst(){return first;}

    public String getSecond(){return second;}

}