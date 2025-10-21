package edu.bsu.cs;

import java.util.ArrayList;

public class Converter {
    ArrayList<Pair> locationPairs;

    public Converter(ArrayList<Pair> locationPairs){
        this.locationPairs = locationPairs;
}

    //use for temperature and dewpoint, both are temperatures
    public int fahrenheitToCelsius(int fUnit){
        float cUnit = (float) (fUnit-32)*5/9;
        return Math.round(cUnit);
    }

    public String locationToLatLong(String name){
        int i;
        for(i=0; i<this.locationPairs.size(); i++){
            if(name.equals(this.locationPairs.get(i).getName())){
                return this.locationPairs.get(i).getLatLong();
            }
        }
        return null;
    }

    public int milesToKilometers(int mUnit){
        float kUnit = (float) (mUnit *1.609344);
        return Math.round(kUnit);
    }
}
