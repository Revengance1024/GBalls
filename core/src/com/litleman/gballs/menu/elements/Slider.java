package com.litleman.gballs.menu.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arturs on 7/29/15.
 */
public class Slider extends MenuObject {
    Button button1;
    Button button2;
    Label label;
    Sprite slide;
    Sprite rail;
    int amount;
    int maxAmount;
    int minAmunt;
    private Vector2 position;
    private Vector2 dimension;
    private final Texture button = new Texture(Gdx.files.internal("Slider1.png"));
    private final Texture slideTexture = new Texture(Gdx.files.internal("Slider2.png"));
    private final Texture railTexture = new Texture(Gdx.files.internal("Slider3.png"));

    public Slider(String label;float positionX,float positionY,float width,float height,int originalAmount){
        position.x = positionX;
        position.y = positionY;
        dimension.x = width;
        dimension.y = height;
        button1 = new Button(position.x,position.y,button.getWidth(),button.getHeight(),button);
        button2 = new Button(position.x+dimension.x-button.getWidth(),position.y,button.getWidth(),button.getHeight(),button);
        button2.mirror(true);
        label 
        amount = originalAmount;
        rail = new Sprite(railTexture,
                (int)position.x+button.getWidth(),
                (int)position.y,(int)dimension.x - 2*button.getWidth(),
                railTexture.getWidth());

        slide = new Sprite(slideTexture,(int)position.x+button.getWidth()+);



    }

    @Override
    public void draw(GL20 x, SpriteBatch y) {

    }
}
