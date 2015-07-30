package com.litleman.gballs.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.litleman.gballs.ball.Ball;
import com.litleman.gballs.elements.shapes.Line;
import com.litleman.gballs.graphics.Textures;

import java.util.Arrays;

/**
 * Created by andri on 28.07.2015.
 */
public class Tile {

    protected String id;
    protected Vector2 position;
    protected Vector2 coordinates;
    protected Rectangle bounds;
    protected Line[] sides;               //1-N, 2-E, 3-S, 4-W
    protected Sprite texture;

    protected boolean solid = true;
    protected float hardness = -1.0f;       //(-1 indestructible)
    protected float bounce = 1.0f;          //Speed multiplication on hit

    protected boolean hit = false;          //Is the ball currently in contact
    protected float health = 100.0f;

    public Tile(String id, int x, int y){
        this(id, x, y, true, -1.0f, 1.0f, Textures.airTile);
    }

    public Tile(String id, int x, int y, boolean solid, float hardness, float bounce, Texture texture){
        this.id = id;
        this.position = new Vector2(x, y);
        this.solid = solid;
        this.hardness = hardness;
        this.bounce = bounce;
        this.texture = new Sprite(texture);
        this.texture.setSize(2 * Ball.BALL_RADIUS, 2 * Ball.BALL_RADIUS);

        coordinates = new Vector2(x*(2*Ball.BALL_RADIUS), y*(2*Ball.BALL_RADIUS));
        this.bounds = new Rectangle(coordinates.x, coordinates.y, (2*Ball.BALL_RADIUS), (2*Ball.BALL_RADIUS));
        this.texture.setPosition(coordinates.x, coordinates.y);
        sides = new Line[4];
        sides[0] = new Line(coordinates.x, coordinates.y+(2*Ball.BALL_RADIUS), coordinates.x+(2*Ball.BALL_RADIUS), coordinates.y+(2*Ball.BALL_RADIUS));
        sides[1] = new Line(coordinates.x+(2*Ball.BALL_RADIUS), coordinates.y+(2*Ball.BALL_RADIUS), coordinates.x+(2*Ball.BALL_RADIUS), coordinates.y);
        sides[2] = new Line(coordinates.x+(2*Ball.BALL_RADIUS), coordinates.y, coordinates.x, coordinates.y);
        sides[3] = new Line(coordinates.x, coordinates.y, coordinates.x, coordinates.y+(2*Ball.BALL_RADIUS));
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

    public String getId(){
        return id;
    }

    public Vector2 getPosition(){
        return position;
    }

    public boolean isHit(){
        return hit;
    }

    public boolean onHit(Ball ball){       //returns false if destroyed
        Gdx.app.debug("GBALLS", "HIT");
        if(!hit) {
            if (hardness >= 0.0f) {
                float damage;
                if (hardness == 0.0f) damage = health;
                else damage = ball.getPower()/hardness;
                health -= damage;
            }

        }
        if (solid) {
            int side = getSideTouch(ball);
            if(side == 1){
                if(ball.getYVelocity() < 0){
                    ball.rebound(bounce, -bounce);
                }
            }else if(side == 3){
                if(ball.getYVelocity() > 0){
                    ball.rebound(bounce, -bounce);
                }
            }else if(side == 2){
                if(ball.getXVelocity() < 0){
                    ball.rebound(-bounce, bounce);
                }
            }else if(side == 4){
                if(ball.getXVelocity() > 0){
                    ball.rebound(-bounce, bounce);
                }
            }
        }else{
            if(!hit)
            ball.rebound(bounce, bounce);
        }

        if(health <= 0)return false;
        else return true;
    }

    public int getSideTouch(Ball ball){
        if(Math.abs(ball.getX()-coordinates.x) > Math.abs(ball.getY()-coordinates.y)){
            if(ball.getX() > coordinates.x){
                return 2;
            }else{
                return 4;
            }
        }else{
            if(ball.getY() > coordinates.y){
                return 1;
            }else{
                return 3;
            }
        }
    }

    public void clearHit(){
        hit = false;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void draw(GL20 gl, SpriteBatch batch){
        texture.draw(batch);
    }
}
