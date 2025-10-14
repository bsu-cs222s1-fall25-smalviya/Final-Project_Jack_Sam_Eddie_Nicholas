package edu.bsu.cs;

public class Pair {
    String name;
    String latLong;

    public Pair(String name, String latLong){
        this.name = name;
        this.latLong = latLong;
    }

    protected String getName(){
        return this.name;
    }

    protected String getLatLong(){
        return this.latLong;
    }
}
