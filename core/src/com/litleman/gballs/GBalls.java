package com.litleman.gballs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.litleman.gballs.input.Controls;

public class GBalls extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	InputProcessor inputProcessor;
	BitmapFont defaultFont;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		defaultFont = new BitmapFont();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		inputProcessor = new Controls();
		Gdx.input.setInputProcessor(inputProcessor);

	}

	@Override
	public void dispose(){
		batch.dispose();
		defaultFont.dispose();
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			defaultFont.draw(batch, Gdx.input.getX() + " : " + Gdx.input.getY(), 100, 100);
		batch.end();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}
}
