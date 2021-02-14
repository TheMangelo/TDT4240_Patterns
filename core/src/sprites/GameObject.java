package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class GameObject {

    protected final int WINDOW_WIDTH = 960;
    protected final int WINDOW_HEIGHT = 640;

    protected Texture texture;
    protected Vector3 position;
    protected Vector3 velocity;
    protected Rectangle bounds;
    protected int ratio;


    public GameObject(int x, int y){
        position = new Vector3(x,y,0);
    }


    public void setVelocity(int x, int y){
        velocity.x = x;
        velocity.y = y;
    }

    public Texture getTexture(){
        return texture;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public abstract void update(float dt);
    public abstract void update(float dt, boolean up);


    public boolean collides(Rectangle rect){
        return rect.overlaps(this.bounds);
    }

    public void scaleTexture(int ratio){
        bounds.height = bounds.height/ratio;
        bounds.width = bounds.width/ratio;
    }

    public float getWidth(){
        return bounds.getWidth();
    }

    public float getHeight(){
        return bounds.getHeight();
    }


    public void dispose(){
        texture.dispose();
    }

}
