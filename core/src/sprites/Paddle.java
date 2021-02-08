package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Paddle {

    private final int WINDOW_WIDTH = 960 ;
    private final int WINDOW_HEIGHT = 640;
    private Vector3 position;
    private int ratio = 2;
    private int HEIGHT;
    private int WIDTH;
    private Texture paddle;
    private Rectangle bounds;
    private int MOVEMENT = 400;
    private boolean left;


    public Paddle(int x, int y, boolean left) {
        position = new Vector3(x,y,0);
        paddle = new Texture("paddle.png");
        HEIGHT = paddle.getHeight()/ratio;
        WIDTH = paddle.getWidth()/ratio;
        bounds = new Rectangle(x,y,WIDTH, HEIGHT);


    }

    public void update(float dt, boolean up){

        if(up){
            position.add(0, MOVEMENT*dt, 0);
        } else {
            position.add(0, -MOVEMENT*dt, 0);
        }
        bounds.setPosition(position.x, position.y);


    }


    public boolean collides(Rectangle ball){
        return ball.overlaps(bounds);
    }

    public Texture getTexture(){
        return this.paddle;
    }



    public int getHeight() {
        return this.HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public Vector3 getPosition(){
        return position;
    }

    public void dispose() {
        paddle.dispose();
    }
}
