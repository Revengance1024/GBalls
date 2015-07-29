package com.litleman.gballs.levels;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.litleman.gballs.ball.Ball;
import com.litleman.gballs.elements.Tile;
import com.litleman.gballs.elements.Wall;
import com.litleman.gballs.elements.tiles.AirTile;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andri on 28.07.2015.
 */
public class Level {

    private String levelId;

    private int width;
    private int height;

    private Tile[] tiles;
    private ArrayList<Wall> walls;

    public Level(String levelId, int width, int height){
        this.levelId = levelId;
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width*height];
        this.walls = new ArrayList<>();
    }

    public void addTile(Tile tile, int xPos, int yPos){
        if(xPos < width && yPos < height)
            tiles[(yPos*width)+xPos] = tile;
    }

    public void addWall(Wall wall){
        walls.add(wall);
    }

    public void prepare(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                if (tiles[j*width+i] == null) {
                    tiles[j*width+i] = new AirTile(i, j);
                }
            }
        }
    }

    public void draw(GL20 gl, SpriteBatch batch){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                if (tiles[j*width+i] == null) {
                    tiles[j*width+i].draw(gl, batch);
                }
            }
        }
    }

    public boolean collisionCheck(Ball ball){
        boolean hit = false;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                if(Intersector.overlaps(ball.getBounds(), tiles[j * width + i].getBounds())){
                    tiles[j * width + i].onHit(ball);
                    if(tiles[j * width + i].isSolid())hit = true;
                }
            }
        }
        return hit;
    }

}
