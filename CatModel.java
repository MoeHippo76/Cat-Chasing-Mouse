import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class CatModel {
    public static void main(String[] arg){
        Frame sim = new Frame(900,900);
        try{
            Image customImage = ImageIO.read(new File("C:\\Java\\Cat Chasing Mouse\\src\\yarn.png"));
            Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(customImage, new Point(0, 0), "customCursor");
            sim.setCursor(customCursor);
        } catch (Exception e){}
        sim.draw();
    }
}
