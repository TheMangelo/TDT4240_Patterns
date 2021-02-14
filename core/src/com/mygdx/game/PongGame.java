package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sprites.BallObject;
import sprites.PaddleInstance;

public class PongGame extends ApplicationAdapter {

	private static PongGame single_pong = null;

	private final int WINDOW_WIDTH = 960;
	private final int WINDOW_HEIGHT = 640;
	private OrthographicCamera camera;

	SpriteBatch batch;
	Texture background;
	PaddleInstance paddle1, paddle2;
	BallObject ball;
	BitmapFont font;

	private PongGame(){}

	public static PongGame getInstance() {
		if (
				single_pong == null
		) {
			single_pong = new PongGame();}

			return single_pong;
		}

	@Override
	public void create () {
		background = new Texture("CloudBackground.png");
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);

		ball = new BallObject(50,100);
		System.out.println("This is an " + ball.getClass().getName());
		paddle1 = new PaddleInstance(10,10,2);
		paddle2 = new PaddleInstance(WINDOW_WIDTH-50,10,2);
		font = new BitmapFont();
		font.setColor(Color.RED);
		font.getData().setScale(3);

	}


	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(Gdx.graphics.getDeltaTime());
		batch.begin();
		batch.draw(background, 0, 0,WINDOW_WIDTH,WINDOW_HEIGHT);
		batch.draw(ball.getTexture(),ball.getPosition().x,ball.getPosition().y, ball.getSize(),ball.getSize());
		batch.draw(
				paddle1.getTexture(),
				paddle1.getPosition().x,
				paddle1.getPosition().y,
				paddle1.getWidth(),
				paddle1.getHeight());
		batch.draw(
				paddle2.getTexture(),
				paddle2.getPosition().x,
				paddle2.getPosition().y,
				paddle2.getWidth(),
				paddle2.getHeight());

		/*font.draw(batch, ""+paddle1.getScore(), WINDOW_WIDTH/2-100, WINDOW_HEIGHT-50);
		font.draw(batch, ""+paddle2.getScore(), WINDOW_WIDTH/2+100, WINDOW_HEIGHT-50);*/
		font.draw(batch, "W=Up, S=down",20, WINDOW_HEIGHT-50);
		font.draw(batch, "Up, Down",WINDOW_WIDTH-200, WINDOW_HEIGHT-50);
		batch.end();

	}

	protected void handleInput() {

		//INPUTHANDLER FOR PADDLE 2, PLAYER2
		if(Gdx.input.isKeyPressed(Input.Keys.UP) && paddle2.getPosition().y+paddle2.getHeight() < WINDOW_HEIGHT ){
			paddle2.update(Gdx.graphics.getDeltaTime(),true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN) &&  paddle2.getPosition().y>0){
			paddle2.update(Gdx.graphics.getDeltaTime(),false);
		}

		//INPUTHANDLER FOR PADDLE1, PLAYER1
		if(Gdx.input.isKeyPressed(Input.Keys.W) && paddle1.getPosition().y+paddle1.getHeight() < WINDOW_HEIGHT ){
			paddle1.update(Gdx.graphics.getDeltaTime(),true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S) &&  paddle1.getPosition().y>0){
			paddle1.update(Gdx.graphics.getDeltaTime(),false);
		}

	}

	//Ball collides with paddles
	public void paddleDetection(){
		if(paddle1.collides(ball.getBounds()) || paddle2.collides(ball.getBounds())){
			ball.parry();
		}
	}

	//Ball collides with the top or bottom of the screen
	public void windowCollison(){
		if(ball.getPosition().y + ball.getSize() >= WINDOW_HEIGHT || ball.getPosition().y <= 1){
			ball.changeYDirection();
			System.out.println("It bounced");
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		paddle1.dispose();
		paddle2.dispose();
		ball.dispose();
	}

	//Detects if the ball is out of bounds or
	public void scoreUpdate(float dt){
		if(ball.getPosition().x < 0){
			paddle2.incrementScore();
			ball.reset(dt);
		}

		if(ball.getPosition().x > WINDOW_WIDTH){
			paddle2.incrementScore();
			ball.reset(dt);
		}

		if(paddle1.getScore() == 21 ||paddle2.getScore() == 21) {
			paddle1.resetScore();
			paddle2.resetScore();
		}

	}

	public void update(float dt){
		handleInput();
		ball.update(dt);

		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}

		//Bounce from the top of the scren
		windowCollison();
		//Collides with paddles
		paddleDetection();
		scoreUpdate(dt);

	}
}
