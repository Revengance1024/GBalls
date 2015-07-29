package com.litleman.gballs.elements;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.litleman.gballs.ball.Ball;
import com.litleman.gballs.graphics.Textures;

import java.awt.geom.Line2D;

/**
 * Created by andri on 28.07.2015.
 */
public abstract class Tile {

    protected String id;
    protected Vector2 position;
    protected Vector2 coordinates;
    protected Line2D[] sides;               //1-N, 2-E, 3-S, 4-W
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
        this.texture = new Sprite(texture, Math.round(Ball.BALL_RADIUS), Math.round(Ball.BALL_RADIUS));

        coordinates = new Vector2(x*(2*Ball.BALL_RADIUS), y*(2*Ball.BALL_RADIUS));
        sides = new Line2D[4];
        sides[0] = new Line2D.Float(coordinates.x, coordinates.y+(2*Ball.BALL_RADIUS), coordinates.x+(2*Ball.BALL_RADIUS), coordinates.y+(2*Ball.BALL_RADIUS));
        sides[1] = new Line2D.Float(coordinates.x+(2*Ball.BALL_RADIUS), coordinates.y+(2*Ball.BALL_RADIUS), coordinates.x+(2*Ball.BALL_RADIUS), coordinates.y);
        sides[2] = new Line2D.Float(coordinates.x+(2*Ball.BALL_RADIUS), coordinates.y, coordinates.x, coordinates.y);
        sides[3] = new Line2D.Float(coordinates.x, coordinates.y, coordinates.x, coordinates.y+(2*Ball.BALL_RADIUS));
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

    public Vector2 getPosition(){
        return position;
    }

    public boolean isHit(){
        return hit;
    }

    public boolean onHit(Ball ball){       //returns false if destroyed
        if(!hit) {
            if (solid) {
                int sideHit = getSideHit(ball);
                if (sideHit == 1 || sideHit == 3) {
                    hit = true;
                    ball.interact(bounce, -bounce, 2);
                } else if (sideHit == 2 || sideHit == 4) {
                    hit = true;
                    ball.interact(-bounce, bounce, 2);
                }
            }
            if(hardness >= 0.0f){
                float damage;
                if(hardness == 0.0f)damage = health;
                else damage = ball.getPower();
                health -= damage;
            }
        }

        if(health <= 0)return false;
        else return true;
    }

    public int getSideHit(Ball ball){
        for(int i = 0; i < 4; i++){
            if(Ball.BALL_RADIUS <= Intersector.distanceLinePoint(Math.round(sides[i].getX1()), Math.round(sides[i].getY1()),
                    Math.round(sides[i].getX2()), Math.round(sides[i].getY2()), ball.getX() + Ball.BALL_RADIUS, ball.getY() + Ball.BALL_RADIUS)){
                return i+1;
            }
        }
        return 0;
    }

    public void clearHit(){
        hit = false;
    }

    public Rectangle getBounds(){
        return new Rectangle(coordinates.x, coordinates.y, (2*Ball.BALL_RADIUS), (2*Ball.BALL_RADIUS));
    }

    public void draw(GL20 gl, SpriteBatch batch){

    }
}
