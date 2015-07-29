package com.litleman.gballs.elements;

/**
 * Created by andri on 28.07.2015.
 */
public abstract class Tile {

    private String id;

    protected boolean solid = true;
    protected float hardness = -1.0f;       //(-1 indestructible)
    protected float bounce = 1.0f;

    public Tile(String id){

    }

    public boolean isSolid(){
        return this.solid;
    }

    public float getHardness(){
        return this.hardness;
    }

    public float getBounce(){
        return this.bounce;
    }
}
