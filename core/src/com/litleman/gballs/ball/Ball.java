package com.litleman.gballs.ball;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by andri on 28.07.2015.
 */
public class Ball {

    private Vector2 position;
    private Vector2 velocity;

    public Ball(){

    }

    public Vector2 getPosition(){
        return position;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public void update(){

    }

    public void interact(float xForce, float yForce){

    }

    public void draw(GL20 gl, SpriteBatch batch){

    }
}
