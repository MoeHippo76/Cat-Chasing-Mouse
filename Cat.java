import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;


public class Cat {
    private Vector2D position = new Vector2D();
    private Vector2D velocity = new Vector2D();
    final private Vector2D origin = new Vector2D();
    private double phi;
    final private float v;

    public Cat(float x, float y){
        origin.set(x,y);
        position.set(0,0);
        v = 10;
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
        return desired.subtract(this.velocity);
    }

    private void  update(Point mouse_point){
        velocity = velocity.add(steer(target(mouse_point)));
        position = position.add(velocity);
    }

    public void move(Point mouse_point){
        if(mouse_point != null)
            update(mouse_point);
    }

    public void draw(Graphics g)  {
        BufferedImage img1 = Frame.cat_img;
        BufferedImage img_mirr = Frame.cat_mir_img;
        double phi_next = Math.toDegrees(Math.atan2(velocity.getY(), velocity.getX()));

        if(phi_next >= 90 || phi_next < -180)
            img1 = img_mirr;
        img1 = rotate(img1,phi-phi_next);
        g.drawImage(img1, (int) (origin.getX() + position.getX()) ,(int) (origin.getY() - position.getY()),img1.getWidth()/2, img1.getHeight()/2,null);
        phi = phi_next;
    }

    public BufferedImage rotate(BufferedImage img, double angle) {

        double rads = -Math.toRadians(phi);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }

}
