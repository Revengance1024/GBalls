package com.litleman.gballs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.litleman.gballs.ball.Ball;
import com.litleman.gballs.input.Controls;
import com.litleman.gballs.levels.Level;

public class GBalls extends ApplicationAdapter {
	private static OrthographicCamera camera;
	private static SpriteBatch batch;
	private static ShapeRenderer shapes;
	private static InputProcessor inputProcessor;
	private static BitmapFont defaultFont;

	private static boolean play;
	private static Vector2 touch;
	public static boolean isTouched = false;

	private static int screenWidth;
	private static int screenHeight;

	private static Ball ball;
	private static Level currentLevel;

	private static Texture defaultBallTexture;

	@Override
	public void create () {
		play = true;
		touch = new Vector2(0, 0);
		batch = new SpriteBatch();
		shapes = new ShapeRenderer();
		defaultFont = new BitmapFont();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		inputProcessor = new Controls();
		Gdx.input.setInputProcessor(inputProcessor);

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();


		///FOR TESTING ONLY
		currentLevel = new Level("level.test", 30, 30);
		defaultBallTexture = new Texture(Gdx.files.internal("ball_base_256x256.png"));
		ball = new Ball(100, 100, defaultBallTexture);
		///END TESTING
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
		try {
			Thread.sleep((long) (1000 / 30 - Gdx.graphics.getDeltaTime()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(play) {
			updateEvents();
			ball.update();
			updateNPC();

			collisionCheck();
		}else{
			updateMenu();
		}

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			if (play) {
				ball.draw(Gdx.gl, batch);
				currentLevel.draw(Gdx.gl, batch);
			}else{
				//TODO draw menu
			}
		batch.end();

		if(isTouched) {
			shapes.setProjectionMatrix(camera.combined);
			shapes.begin(ShapeRenderer.ShapeType.Line);
				shapes.setColor(0, 0, 0, 1);
				shapes.line(touch.x, touch.y, Gdx.input.getX(), screenHeight-Gdx.input.getY());
			shapes.end();
		}
		camera.update();
		super.render();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}


	private static void updateEvents(){

	}

	private static void updateNPC(){

	}

	private static boolean collisionCheck(){	//return false if nothing is hit
		return currentLevel.collisionCheck(ball);
	}

	private static void updateMenu(){

	}

	public static boolean isPlay(){
		return play;
	}

	public static void setTouch(float x, float y){
		touch.set(x, y);
	}

	public static Vector2 getTouch(){
		return touch;
	}

	public static void ballInteract(float x, float y){
		ball.interact(x, y, 1);
	}

	public static int getScreenWidth(){
		return screenWidth;
	}

	public static int getScreenHeight(){
		return screenHeight;
	}

	public static BitmapFont getDefaultFont(){
		return defaultFont;
	}

}
