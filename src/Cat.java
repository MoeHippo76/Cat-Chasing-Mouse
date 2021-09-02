import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class Cat {
    private Vector2D position = new Vector2D();
    private Vector2D velocity = new Vector2D();
    private Vector2D origin = new Vector2D();
    private double phi;
    private float v;

    public Cat(float x, float y){
        origin.set(x,y);
        position.set(0,0);
        v = 5;
        phi = 0;
        velocity.set(v * (float) Math.cos(Math.toRadians(phi)),v * (float) Math.sin(Math.toRadians(phi)));
    }

    private Vector2D target(Point mouse_point){
        Vector2D target = new Vector2D();
        target.set(mouse_point.x - origin.getX(),origin.getY()-mouse_point.y);
        return target;
    }

    private Vector2D steer(Vector2D target){
        Vector2D difference = target.subtract(this.position);
        Vector2D desired = difference.normalize().multiply(v);
        Vector2D steer = desired.subtract(this.velocity);
        return steer;
    }

    private void  update(Point mouse_point){
        velocity = velocity.add(steer(target(mouse_point)));
        position = position.add(velocity);
    }

    public void move(Point mouse_point){
        if(mouse_point != null)
            update(mouse_point);
    }

    public void draw(Graphics g) throws java.io.IOException {
        BufferedImage img1 = ImageIO.read(new File("C:\\Java\\Cat Chasing Mouse\\src\\cat.png"));
        g.drawImage(img1, (int) (origin.getX() + position.getX()) ,(int) (origin.getY() - position.getY()),66,50,null);
    }

}
