package com.litleman.gballs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import com.litleman.gballs.ball.Ball;
import com.litleman.gballs.input.Controls;

public class GBalls extends ApplicationAdapter {
	public static GBalls game;

	OrthographicCamera camera;
	SpriteBatch batch;
	InputProcessor inputProcessor;
	BitmapFont defaultFont;

	boolean play;
	Vector2 touch;

	Ball ball;
	//Level currentLevel;

	public GBalls(){}
	
	@Override
	public void create () {
		game = new GBalls();

		play = false;
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
		if(play) {
			updateEvents();
			updateNPC();
			ball.update();

			collisionCheck();
		}else{
			updateMenu();
		}

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (play) {
			//currentLevel.draw(Gdx.gl, batch);
			ball.draw(Gdx.gl, batch);
		}else{

		}
			defaultFont.draw(batch, Gdx.input.getX() + " : " + Gdx.input.getY(), 100, 100);
		batch.end();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}


	private void updateEvents(){

	}

	private void updateNPC(){

	}

	private boolean collisionCheck(){
		return false;
	}

	private void updateMenu(){

	}
}
