package com.litleman.gballs.input;

import com.badlogic.gdx.InputProcessor;
import com.litleman.gballs.GBalls;

/**
 * Created by andri on 28.07.2015.
 */
public class Controls implements InputProcessor{

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        GBalls.setTouch(screenX, GBalls.getScreenHeight()-screenY);
        GBalls.isTouched = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        GBalls.ballInteract(GBalls.getTouch().x - screenX, GBalls.getTouch().y - (GBalls.getScreenHeight()-screenY));
        GBalls.isTouched = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
