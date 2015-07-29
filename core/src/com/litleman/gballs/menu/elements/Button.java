package com.litleman.gballs.menu.elements;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by andri on 28.07.2015.
 */
public class Button extends MenuObject {
    private Vector2 position=new Vector2();
    private Vector2 dimension=new Vector2();
    private Sprite sprite;

    public Button(float x,float y,float width,float height,Texture texture){
        this.position.x = x;
        this.position.y = y;
        this.dimension.x = width;
        this.dimension.y = height;
        sprite = new Sprite(texture,(int)position.x,(int)position.y,(int)dimension.x,(int)dimension.y);

    }

    public void mirror(boolean x){
        if(x)sprite.flip(true,false);
        else sprite.flip(false,true);
    }





    //draws button
    @Override
    public void draw(GL20 gl, SpriteBatch batch) {
        sprite.draw(batch);
    }


}
