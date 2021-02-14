package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class BallObject extends GameObject {
    private int size;

    public BallObject(int x, int y){
        super(x,y);
        size = 50;
        position = new Vector3(x,y,0);
        texture = new Texture("Ball.png");
        bounds = new Rectangle(x,y,size, size);
        velocity = new Vector3(300,200,0);
    }


    //Constructor setting different size of ball
    public BallObject(int x, int y, int size){
        super(x,y);
        this.size = size;
        position = new Vector3(x,y,0);
        texture = new Texture("Ball.png");
        bounds = new Rectangle(x,y,size, size);
        velocity = new Vector3(300,200,0);
    }

    public void reset(float dt){
        position.x = WINDOW_WIDTH/2;
        position.y = WINDOW_HEIGHT/2;
        bounds.setPosition(position.x, position.y);
        velocity.x = getRandomNumber(100,300);
        velocity.y = getRandomNumber(100,300);

        //To randomize initial direction
        if( velocity.x % 2 == 0){
            velocity.x = - velocity.x;
        }
        if( velocity.y% 2 == 0){
            velocity.y = - velocity.y;
        }

        update(dt);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void changeYDirection(){
        velocity.y = - velocity.y;
    }

    public void changeXDirection() {
        velocity.x = -velocity.x ;
    }

    //When the ball is sucsessfully parried. It increases speed and flips direction
    public void parry(){
        velocity.x =  -velocity.x;
        velocity.x += velocity.x*0.1;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void update(float dt) {
        position.add(velocity.x*dt, velocity.y*dt, 0);
        bounds.setPosition(position.x, position.y);
    }

    @Override
    public void update(float dt, boolean up) {
        System.out.println("Wrong update method in ballobject");
    }

}
