package com.litleman.gballs.menu.elements;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arturs on 7/28/15.
 */
public class Label extends MenuObject {
    private String text;
    private Vector2 position=new Vector2();
    private BitmapFont font;

    public Label(String text,BitmapFont font,float positionX,float positionY){
        this.text = text;
        this.font = font;
        position.x = positionX;
        position.y = positionY;

    }

    @Override
    public void draw(GL20 gl, SpriteBatch batch) {
        font.draw(batch,text,position.x,position.y);
    }
}
