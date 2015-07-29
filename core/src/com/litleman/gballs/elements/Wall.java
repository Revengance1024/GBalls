package com.litleman.gballs.elements;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by andri on 29.07.2015.
 */
public class Wall {

    private Vector2 startPoint;
    private Vector2 endPoint;

    public Vector2 getStartPoint(){
        return startPoint;
    }

    public Vector2 getEndPoint(){
        return endPoint;
    }

    public void onHit(){

    }
}
