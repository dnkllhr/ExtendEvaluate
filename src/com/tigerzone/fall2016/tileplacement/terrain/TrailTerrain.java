package com.tigerzone.fall2016.tileplacement.terrain;

import com.tigerzone.fall2016.tileplacement.Direction;

/**
 * Created by Aidan on 11/7/2016.
 */
public class TrailTerrain extends Terrain {

    @Override
    public boolean accept(TerrainVisitor terrainVisitor) {
        return terrainVisitor.visit(this);
    }

    @Override
    public void accept(SegmentVisitor segmentVisitor, Direction direction) {
        segmentVisitor.visitTrail(this, direction);
    }

    @Override
    public boolean visit(LakeTerrain lakeTerrain) {
        return false;
    }

    @Override
    public boolean visit(JungleTerrain jungleTerrain) {
        return false;
    }

    @Override
    public boolean visit(TrailTerrain trailTerrain) {
        return true;
    }

    @Override
    public boolean visit(DenTerrain denTerrainTerrain) {
        return false;
    }

    @Override
    public boolean visit(FreeTerrain freeTerrain) {
        return true;
    }

}
