package edu.bsu.cs;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Converter {
    ArrayList<Pair> locationPairs;

    public Converter(ArrayList<Pair> locationPairs){
        this.locationPairs = locationPairs;
}

    public int fahrenheitToCelsius(int fTemp){
        float cTemp = (float) (fTemp-32)*5/9;
        return Math.round(cTemp);
    }

    public String locationToLatLong(String name){
        int i;
        for(i=0; i<this.locationPairs.size(); i++){
            if(name.equals(this.locationPairs.get(i).getLeft())){
                return this.locationPairs.get(i).getRight();
            }
        }
        return null;
    }
}
