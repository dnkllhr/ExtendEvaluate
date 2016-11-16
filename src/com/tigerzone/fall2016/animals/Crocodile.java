package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;

/**
 * Created by Aidan on 11/9/2016.
 */
public class Crocodile extends Predator {
    @Override
    public boolean placeableInDen() {
        return false;
    }

    @Override
    public boolean placeableInJungle() {
        return false;
    }

    @Override
    public boolean placeableInTrail() {
        return true;
    }

    @Override
    public boolean placeableInLake() {
        return true;
    }

    @Override
    public void placeInArea(Area area) {
        area.placePredator(this);
    }

    @Override
    public void addToArea(Area area){ area.addAnimal(this);}
}