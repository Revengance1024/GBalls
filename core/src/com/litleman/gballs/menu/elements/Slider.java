package com.litleman.gballs.menu.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.litleman.gballs.GBalls;

/**
 * Created by Arturs on 7/29/15.
 */
public class Slider extends MenuObject {
    Button button1;
    Button button2;
    //Label label;
    String label;
    Sprite slide;
    Sprite rail;
    int amount;
    int maxAmount;
    int minAmount;
    private Vector2 position = new Vector2();
    private Vector2 dimension= new Vector2();
    private final Texture button = new Texture(Gdx.files.internal("Slider1.png"));
    private final Texture slideTexture = new Texture(Gdx.files.internal("Slider2.png"));
    private final Texture railTexture = new Texture(Gdx.files.internal("Slider3.png"));

    public Slider(String label,
                  float positionX,
                  float positionY,
                  float width,
                  float height,
                  int originalAmount){
        maxAmount = 100;
        minAmount = 0;
        this.position.x = positionX;
        this.position.y = positionY;
        this.dimension.x = width;
        this.dimension.y = height;
        //label = new Label(label, GBalls.getDefaultFont(),position.x-)
        this.label=label;
        amount = originalAmount;
        init();

    }

    private void init(){
        button1 = new Button(position.x,position.y,button.getWidth(),button.getHeight(),button);
        button2 = new Button(position.x+dimension.x-button.getWidth(),position.y,button.getWidth(),button.getHeight(),button);
        button2.mirror(true);
        rail = new Sprite(railTexture,
                (int)position.x+button.getWidth(),
                (int)position.y,(int)dimension.x - 2*button.getWidth(),
                railTexture.getWidth());
        int delta = (int)(amount*(dimension.x - 2*button.getWidth())/(maxAmount-minAmount));
        slide = new Sprite(slideTexture,
                (int)position.x+button.getWidth()+delta,
                (int)position.y,
                slideTexture.getWidth(),
                slideTexture.getHeight());
    }

    public void add(){
        if(amount>=maxAmount)amount++;
        else amount = maxAmount;
    }

    public void add(int ak){
        if(amount>=maxAmount)amount+=ak;
        else amount = maxAmount;
    }

    public void subtract(){
        if(amount<=minAmount)amount--;
        else amount = minAmount;
    }

    public void subtract(int ak){
        if(amount<=minAmount)amount-=ak;
        else amount = minAmount;
    }

    @Override
    public void draw(GL20 x, SpriteBatch y) {
        rail.draw(y);
        button1.draw(x,y);
        button2.draw(x,y);
        slide.draw(y);
    }
}
