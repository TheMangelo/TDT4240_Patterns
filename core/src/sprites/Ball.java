package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Ball {

    private final int WINDOW_WIDTH = 960 ;
    private final int WINDOW_HEIGHT = 640;
    private Rectangle bounds;
    private float MOVEMENT_Y =300;
    private float MOVEMENT_X = 200;
    private Texture texture;
    private Vector3 position;
    private int size = 50;
    private Random rand;



    public Ball(int x, int y){
        position = new Vector3(x,y,0);
        texture = new Texture("Ball.png");
        bounds = new Rectangle(x,y,size, size);

    }

    public void reset(float dt){
        position.x = WINDOW_WIDTH/2;
        position.y = WINDOW_HEIGHT/2;
        bounds.setPosition(position.x, position.y);
        MOVEMENT_X = getRandomNumber(100,300);
        MOVEMENT_Y = getRandomNumber(100,300);

        if( MOVEMENT_X % 2 == 0){
            MOVEMENT_X = - MOVEMENT_X;
        }
        if( MOVEMENT_Y% 2 == 0){
            MOVEMENT_Y = - MOVEMENT_Y;
        }

        update(dt);

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void update(float dt){
        position.add(MOVEMENT_X*dt, MOVEMENT_Y*dt, 0);
        bounds.setPosition(position.x, position.y);
    }


    public void changeYDirection(){
        MOVEMENT_Y = - MOVEMENT_Y;
    }

    public void changeXDirection() {
        MOVEMENT_X = -MOVEMENT_X;
    }

    //to increase speed
    public void parry(){
        MOVEMENT_X =  -MOVEMENT_X;
        MOVEMENT_X += MOVEMENT_X*0.1;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector3 getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }

    public void dispose(){
        texture.dispose();
    }


}
