package com.litleman.gballs.menu;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.litleman.gballs.GBalls;
import com.litleman.gballs.menu.elements.Button;
import com.litleman.gballs.menu.elements.MenuObject;

import java.util.ArrayList;

/**
 * Created by andri on 28.07.2015.
 */
public class Menu {
    private ArrayList<MenuObject> objects;
    private Texture background;
    private int index=0;
    private String label;

    public Menu(Texture background,String label){
        this.background = background;
        this.label = label;
        init();
    }

    private void init(){

    }

    public void add(MenuObject object){
        objects.add(index, object);
        index++;
    }

    public void draw(GL20 gl,SpriteBatch batch){
        Sprite sprite = new Sprite();
        sprite.setPosition(0,GBalls.getScreenHeight());
        sprite.setSize(GBalls.getScreenWidth(),GBalls.getScreenHeight());
        sprite.setRegion(background);
        sprite.draw(batch);
        for(MenuObject object : objects) {
            object.draw(gl,batch);
        }
    }
}
