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

    private Vector2 position;
    private Vector2 velocity;

    private float power;

    private Sprite sprite;

    public Ball(float xPos, float yPos, Texture texture){
        position = new Vector2(xPos, yPos);
        velocity = new Vector2(0f, 0f);

        sprite = new Sprite(texture);
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

    public float getPower(){
        return power;
    }

    public void update(){
        position.add(velocity.x, velocity.y);
        if(position.x <= 0 || (position.x+(2*BALL_RADIUS)) >= GBalls.getScreenWidth()){
            velocity.x *= -1;
        }
        if(position.y <= 0 || (position.y+(2*BALL_RADIUS)) >= GBalls.getScreenHeight()){
            velocity.y *= -1;
        }
    }

    public void interact(float xForce, float yForce, int type){     //1-add, 2-multiply
        switch (type){
            case 1:
                velocity.add(xForce*0.01f, yForce*0.01f);
            break;
            case 2:
                velocity.x *= xForce;
                velocity.y *= yForce;
            break;

        }
    }

    public void draw(GL20 gl, SpriteBatch batch){
        sprite.setSize((2*BALL_RADIUS), (2*BALL_RADIUS));
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public Circle getBounds(){
        return new Circle(position.x+BALL_RADIUS, position.y+BALL_RADIUS, BALL_RADIUS);
    }
}
