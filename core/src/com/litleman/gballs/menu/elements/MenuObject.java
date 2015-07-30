package com.litleman.gballs.menu.elements;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arturs on 7/28/15.
 */
public abstract class MenuObject {
    private Vector2 position= new Vector2();
    private Vector2 dimension= new Vector2();
    public abstract void draw(GL20 x, SpriteBatch y);

    public Vector2 getPosition(){
        return position;
    }
    public Vector2 getSize(){return dimension;}
}
