package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class PaddleInstance extends GameObject {

    private final int WINDOW_WIDTH = 960 ;
    private final int WINDOW_HEIGHT = 640;
    private int MOVEMENT = 400;
    private boolean left;
    private Texture texture;
    private int score = 0;

    //Default constructor
    public PaddleInstance(int x, int y) {
        super(x,y);
        velocity = new Vector3(0,400,0);
        bounds = new Rectangle(x,y,texture.getWidth(), texture.getHeight());
        texture = new Texture("paddle.png");

    }

    //Constructor for setting different sizes
    public PaddleInstance(int x, int y, int ratio){
        super(x,y);
        texture = new Texture("paddle.png");
        velocity = new Vector3(0,400,0);
        position = new Vector3(x,y,0);
        bounds = new Rectangle(x,y,texture.getWidth()/ratio, texture.getHeight()/ratio);
    }

    public void update(float dt, boolean up){

        if(up){
            position.add(0, velocity.y*dt, 0);
        } else {
            position.add(0, -velocity.y*dt, 0);
        }
        bounds.setPosition(position.x, position.y);
    }

    public void dispose(){
        texture.dispose();
    }

    public void incrementScore(){
        score ++;
    }

    public void resetScore(){
        score = 0;
    }

    public int getScore(){
        return score;
    }

    public boolean collides(Rectangle ball){
        return ball.overlaps(bounds);
    }

    public Texture getTexture(){
        return this.texture;
    }

    public Vector3 getPosition(){
        return position;
    }

    @Override
    public void update(float dt) {
    }

}
