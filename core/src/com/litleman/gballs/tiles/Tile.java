package com.litleman.gballs.tiles;

import java.util.ArrayList;

/**
 * Created by andri on 28.07.2015.
 */
public abstract class Tile {

    protected String id;

    protected boolean solid = true;
    protected float hardness = -1.0f;       //(-1 indestructible)
    protected float bounce = 1.0f;

    protected Wall wallTop;
    protected Wall wallBottom;
    protected Wall wallLeft;
    protected Wall wallRight;

    public Tile(String id, ){

    }

    public boolean isSolid(){
        return this.solid;
    }

    public float getHardness(){
        return this.hardness;
    }
}
