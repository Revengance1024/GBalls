package com.litleman.gballs.ball;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.litleman.gballs.GBalls;

/**
 * Created by andri on 28.07.2015.
 */
public class Ball {

    private Vector2 position;
    private Vector2 velocity;

    private Sprite sprite;

    public Ball(float xPos, float yPos, Texture texture){
        position = new Vector2(xPos, yPos);
        velocity = new Vector2(0f, 0f);

        sprite = new Sprite(texture);
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
        position.add(velocity.x, velocity.y);
        if(position.x <= 0 || (position.x+32) >= GBalls.getScreenWidth()){
            velocity.x *= -1;
        }
        if(position.y <= 0 || (position.y+32) >= GBalls.getScreenHeight()){
            velocity.y *= -1;
        }
    }

    public void interact(float xForce, float yForce){
        velocity.add(xForce*0.01f, yForce*0.01f);
    }

    public void draw(GL20 gl, SpriteBatch batch){
        sprite.setSize(32, 32);
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
