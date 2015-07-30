package com.litleman.gballs.elements.tiles;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.litleman.gballs.ball.Ball;
import com.litleman.gballs.elements.Tile;
import com.litleman.gballs.graphics.Textures;

/**
 * Created by andri on 29.07.2015.
 */
public class StoneTile extends Tile {

    private Sprite textureHit;

    public StoneTile(int x, int y) {
        super("tile.stone", x, y, true, 5.0f, 0.8f, Textures.stoneTile);
        textureHit = new Sprite(Textures.stoneHitTile);
        textureHit.setSize(Ball.BALL_RADIUS*2, Ball.BALL_RADIUS*2);
        textureHit.setPosition(coordinates.x, coordinates.y);
    }


    public void draw(GL20 gl, SpriteBatch batch){
        if(isHit()){
            textureHit.draw(batch);
        }else{
            texture.draw(batch);
        }
    }


}
