package com.litleman.gballs.ball;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.litleman.gballs.GBalls;

/**
 * Created by andri on 28.07.2015.
 */
public class Ball {

    public static float BALL_RADIUS = 16;
    public static float DEFAULT_FORCE_MODIFIER = 0.025f;
    public static float MAX_VELOCITY = 30.0f;
    public static float MIN_VELOCITY = 1.0f;

    private Vector2 position;
    private Vector2 velocity;

    private float power;

    private Sprite sprite;

    public Ball(float xPos, float yPos, Texture texture){
        position = new Vector2(xPos, yPos);
        velocity = new Vector2(0f, 0f);

        sprite = new Sprite(texture);
        sprite.setSize(2*BALL_RADIUS, 2*BALL_RADIUS);
        power = 100.0f;
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

    public float getXVelocity(){
        return velocity.x;
    }

    public float getYVelocity(){
        return velocity.y;
    }

    public float getPower(){
        return power;
    }

    public void update(float gameSpeed){
        if(velocity.len() < MIN_VELOCITY){
            velocity.x = 0;
            velocity.y = 0;
        }
        position.add(velocity.x*gameSpeed, velocity.y*gameSpeed);
        if(position.x <= -20.0f || (position.x+(2*BALL_RADIUS)) >= 980.0f || position.y <= -20.0f || (position.y+(2*BALL_RADIUS)) >= 980.0f){
            move(100, 100);
        }
    }

    public void move(float x, float y){
        position.x = x;
        position.y = y;
    }

    public void rebound(float xMultiply, float yMultiply){
        velocity.x *= xMultiply;
        velocity.y *= yMultiply;
    }

    public void interact(float xForce, float yForce){
        velocity.add(xForce*DEFAULT_FORCE_MODIFIER, yForce*DEFAULT_FORCE_MODIFIER);
        if(velocity.x > MAX_VELOCITY)velocity.x = MAX_VELOCITY;
        if(velocity.y > MAX_VELOCITY)velocity.y = MAX_VELOCITY;
    }

    public void draw(GL20 gl, SpriteBatch batch){
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public Circle getBounds(){
        return new Circle(position.x+BALL_RADIUS, position.y+BALL_RADIUS, BALL_RADIUS);
    }
}
