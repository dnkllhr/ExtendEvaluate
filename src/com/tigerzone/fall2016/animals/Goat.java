package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

/**
 * Created by Aidan on 12/3/2016.
 */
public class Goat extends Prey implements Predator {

    private Player owner;

    public Goat(Player player){
        this.owner = player;
    }



    @Override
    public void addToArea(Area area){ area.addAnimal(this);}

    public Player getOwner(){
        return this.owner;
    }

    public boolean hasOwner() {
        if(this.owner!=null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isDeer() {
        return false;
    }

    @Override
    public boolean isBoar() {
        return false;
    }

    @Override
    public boolean isBuffalo() {
        return false;
    }

    @Override
    public boolean isGoat() {
        return true;
    }

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
        area.addAnimal(this);
    }
}
