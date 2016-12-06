package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.TileStack;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 12/3/2016.
 */
public class TileStackGeneratorTest {


    @Test
    public void generateTiles() throws Exception {
        LinkedList<PlayableTile> tileStack = TileStackGenerator.generateTiles(123);
        for (PlayableTile playableTile : tileStack) {
            if (playableTile.getTileString() == "LTLT-") {
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }

    @Test
    public void generateTiles1() throws Exception {

    }

}