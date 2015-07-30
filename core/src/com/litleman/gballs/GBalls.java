package com.litleman.gballs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
import com.litleman.gballs.ball.Ball;
import com.litleman.gballs.elements.tiles.AirTile;
import com.litleman.gballs.elements.tiles.StoneTile;
import com.litleman.gballs.elements.tiles.WoodTile;
import com.litleman.gballs.graphics.Textures;
import com.litleman.gballs.input.Controls;
import com.litleman.gballs.levels.Level;

public class GBalls extends ApplicationAdapter {
	private static OrthographicCamera camera;
	private static SpriteBatch batch;
	private static ShapeRenderer shapes;
	private static InputProcessor inputProcessor;
	private static BitmapFont defaultFont;
	public static Logger logger;

	private static boolean play;
	private static Vector2 touch;
	public static boolean isTouched = false;
	private static float gameSpeed;

	private static int screenWidth;
	private static int screenHeight;

	private static Ball ball;
	private static Level currentLevel;

	private static Texture defaultBallTexture;
	private static int cameraBuffer;	//TODO put in constants file
	private static Vector2 cameraOffset;
	public static String toDisplay;

	@Override
	public void create () {
		play = true;
		touch = new Vector2(0, 0);
		batch = new SpriteBatch();
		shapes = new ShapeRenderer();
		defaultFont = new BitmapFont();
		logger = new Logger("GBalls log");

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		inputProcessor = new Controls();
		Gdx.input.setInputProcessor(inputProcessor);

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();


		///FOR TESTING ONLY
		gameSpeed = 1.0f;
		toDisplay = "";
		cameraOffset = new Vector2(0, 0);
		cameraBuffer = 200;
		Gdx.app.debug("GBALLS", "Started adding test shit");
		currentLevel = new Level("level.test", 30, 30);
		generateLevel();
		currentLevel.prepare();
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
			Thread.sleep((long) (1000 / 60 - Gdx.graphics.getDeltaTime()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(play) {
			if(isTouched)gameSpeed = 0.05f;
			else gameSpeed = 1.0f;
			updateEvents();
			ball.update(gameSpeed);
			updateNPC();

			updateCamera();
			currentLevel.collisionCheck(ball);
		}else{
			updateMenu();
		}

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			if (play) {
				currentLevel.draw(Gdx.gl, batch);
				ball.draw(Gdx.gl, batch);
			}else{
				//TODO draw menu
			}
		defaultFont.draw(batch, toDisplay, cameraOffset.x + screenWidth - 200, cameraOffset.y + 100);
		batch.end();

		if(isTouched) {
			shapes.setProjectionMatrix(camera.combined);
			shapes.begin(ShapeRenderer.ShapeType.Line);
				shapes.setColor(0, 0, 0, 1);
				shapes.line(cameraOffset.x+touch.x, cameraOffset.y+touch.y, cameraOffset.x+Gdx.input.getX(), cameraOffset.y+screenHeight-Gdx.input.getY());
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

	private static void updateCamera(){
		if(play){
			if(!isTouched){
				float xt = 0, yt = 0;
				Vector2 realBallPos = new Vector2(ball.getX()-(camera.position.x-camera.viewportWidth/2.0f), ball.getY()-(camera.position.y-camera.viewportHeight/2.0f));
				if(realBallPos.x > screenWidth - cameraBuffer){
					xt = realBallPos.x + cameraBuffer - screenWidth;
				}
				if(realBallPos.x < cameraBuffer){
					xt = realBallPos.x - cameraBuffer;
				}
				if(realBallPos.y > screenHeight - cameraBuffer){
					yt = realBallPos.y + cameraBuffer - screenHeight;
				}
				if(realBallPos.y < cameraBuffer){
					yt = realBallPos.y - cameraBuffer;
				}
				camera.translate(xt, yt);
				cameraOffset.add(xt, yt);
			}
		}else{
			camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight /2.0f, 0);
		}
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
		ball.interact(x, y);
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
	
	
	
	public static void generateLevel(){
		currentLevel.addTile(new StoneTile(0, 0));
		currentLevel.addTile(new StoneTile(1, 0));
		currentLevel.addTile(new StoneTile(2, 0));
		currentLevel.addTile(new StoneTile(3, 0));
		currentLevel.addTile(new StoneTile(4, 0));
		currentLevel.addTile(new StoneTile(5, 0));
		currentLevel.addTile(new StoneTile(6, 0));
		currentLevel.addTile(new StoneTile(7, 0));
		currentLevel.addTile(new StoneTile(8, 0));
		currentLevel.addTile(new StoneTile(9, 0));
		currentLevel.addTile(new StoneTile(10, 0));
		currentLevel.addTile(new StoneTile(11, 0));
		currentLevel.addTile(new StoneTile(12, 0));
		currentLevel.addTile(new StoneTile(13, 0));
		currentLevel.addTile(new StoneTile(14, 0));
		currentLevel.addTile(new StoneTile(15, 0));
		currentLevel.addTile(new StoneTile(16, 0));
		currentLevel.addTile(new StoneTile(17, 0));
		currentLevel.addTile(new StoneTile(18, 0));
		currentLevel.addTile(new StoneTile(19, 0));
		currentLevel.addTile(new StoneTile(20, 0));
		currentLevel.addTile(new StoneTile(21, 0));
		currentLevel.addTile(new StoneTile(22, 0));
		currentLevel.addTile(new StoneTile(23, 0));
		currentLevel.addTile(new StoneTile(24, 0));
		currentLevel.addTile(new StoneTile(25, 0));
		currentLevel.addTile(new StoneTile(26, 0));
		currentLevel.addTile(new StoneTile(27, 0));
		currentLevel.addTile(new StoneTile(28, 0));
		currentLevel.addTile(new StoneTile(29, 0));
		
		currentLevel.addTile(new StoneTile(0, 29));
		currentLevel.addTile(new StoneTile(1, 29));
		currentLevel.addTile(new StoneTile(2, 29));
		currentLevel.addTile(new StoneTile(3, 29));
		currentLevel.addTile(new StoneTile(4, 29));
		currentLevel.addTile(new StoneTile(5, 29));
		currentLevel.addTile(new StoneTile(6, 29));
		currentLevel.addTile(new StoneTile(7, 29));
		currentLevel.addTile(new StoneTile(8, 29));
		currentLevel.addTile(new StoneTile(9, 29));
		currentLevel.addTile(new StoneTile(10, 29));
		currentLevel.addTile(new StoneTile(11, 29));
		currentLevel.addTile(new StoneTile(12, 29));
		currentLevel.addTile(new StoneTile(13, 29));
		currentLevel.addTile(new StoneTile(14, 29));
		currentLevel.addTile(new StoneTile(15, 29));
		currentLevel.addTile(new StoneTile(16, 29));
		currentLevel.addTile(new StoneTile(17, 29));
		currentLevel.addTile(new StoneTile(18, 29));
		currentLevel.addTile(new StoneTile(19, 29));
		currentLevel.addTile(new StoneTile(20, 29));
		currentLevel.addTile(new StoneTile(21, 29));
		currentLevel.addTile(new StoneTile(22, 29));
		currentLevel.addTile(new StoneTile(23, 29));
		currentLevel.addTile(new StoneTile(24, 29));
		currentLevel.addTile(new StoneTile(25, 29));
		currentLevel.addTile(new StoneTile(26, 29));
		currentLevel.addTile(new StoneTile(27, 29));
		currentLevel.addTile(new StoneTile(28, 29));
		currentLevel.addTile(new StoneTile(29, 29));

		currentLevel.addTile(new WoodTile(0, 1));
		currentLevel.addTile(new WoodTile(0, 2));
		currentLevel.addTile(new WoodTile(0, 3));
		currentLevel.addTile(new WoodTile(0, 4));
		currentLevel.addTile(new WoodTile(0, 5));
		currentLevel.addTile(new WoodTile(0, 6));
		currentLevel.addTile(new WoodTile(0, 7));
		currentLevel.addTile(new WoodTile(0, 8));
		currentLevel.addTile(new WoodTile(0, 9));
		currentLevel.addTile(new WoodTile(0, 10));
		currentLevel.addTile(new WoodTile(0, 11));
		currentLevel.addTile(new WoodTile(0, 12));
		currentLevel.addTile(new WoodTile(0, 13));
		currentLevel.addTile(new WoodTile(0, 14));
		currentLevel.addTile(new WoodTile(0, 15));
		currentLevel.addTile(new WoodTile(0, 16));
		currentLevel.addTile(new WoodTile(0, 17));
		currentLevel.addTile(new WoodTile(0, 18));
		currentLevel.addTile(new WoodTile(0, 19));
		currentLevel.addTile(new WoodTile(0, 20));
		currentLevel.addTile(new WoodTile(0, 21));
		currentLevel.addTile(new WoodTile(0, 22));
		currentLevel.addTile(new WoodTile(0, 23));
		currentLevel.addTile(new WoodTile(0, 24));
		currentLevel.addTile(new WoodTile(0, 25));
		currentLevel.addTile(new WoodTile(0, 26));
		currentLevel.addTile(new WoodTile(0, 27));
		currentLevel.addTile(new WoodTile(0, 28));

		currentLevel.addTile(new WoodTile(29, 1));
		currentLevel.addTile(new WoodTile(29, 2));
		currentLevel.addTile(new WoodTile(29, 3));
		currentLevel.addTile(new WoodTile(29, 4));
		currentLevel.addTile(new WoodTile(29, 5));
		currentLevel.addTile(new WoodTile(29, 6));
		currentLevel.addTile(new WoodTile(29, 7));
		currentLevel.addTile(new WoodTile(29, 8));
		currentLevel.addTile(new WoodTile(29, 9));
		currentLevel.addTile(new WoodTile(29, 10));
		currentLevel.addTile(new WoodTile(29, 11));
		currentLevel.addTile(new WoodTile(29, 12));
		currentLevel.addTile(new WoodTile(29, 13));
		currentLevel.addTile(new WoodTile(29, 14));
		currentLevel.addTile(new WoodTile(29, 15));
		currentLevel.addTile(new WoodTile(29, 16));
		currentLevel.addTile(new WoodTile(29, 17));
		currentLevel.addTile(new WoodTile(29, 18));
		currentLevel.addTile(new WoodTile(29, 19));
		currentLevel.addTile(new WoodTile(29, 20));
		currentLevel.addTile(new WoodTile(29, 21));
		currentLevel.addTile(new WoodTile(29, 22));
		currentLevel.addTile(new WoodTile(29, 23));
		currentLevel.addTile(new WoodTile(29, 24));
		currentLevel.addTile(new WoodTile(29, 25));
		currentLevel.addTile(new WoodTile(29, 26));
		currentLevel.addTile(new WoodTile(29, 27));
		currentLevel.addTile(new WoodTile(29, 28));
		
		
	}

}
