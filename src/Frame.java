import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;


public class Frame extends JFrame implements KeyListener{
    private Image raster;
    private Graphics rGraphics;
    private final int height;
    private final int width;
    private  boolean keyPressed;
    private Cat cat;


    Frame(int height, int width){
        this.height = height;
        this.width = width;
        keyPressed = true;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width,height);
        setVisible(true);
        setup();
    }

    public void setup(){
        raster = this.createImage(width,height);
        rGraphics = raster.getGraphics();
        cat = new Cat(width/2,height/2);
        addKeyListener(this);
    }


    public void draw(){
        while(keyPressed)
        {
            drawBG();  //draws background
            cat.move(mouse_position());
            try {
                cat.draw(rGraphics);} catch (java.io.IOException e) {}
            mouse_position();
            getGraphics().drawImage(raster,0,0,getWidth(),getHeight(),null);
            try{Thread.sleep(15);}catch(Exception e){}
        }
    }

    public Point mouse_position(){
        return this.getMousePosition();
    }


    private void drawBG(){
        try {
            BufferedImage img1 = ImageIO.read(new File("C:\\Java\\Cat Chasing Mouse\\src\\grass.jpg"));
            rGraphics.drawImage(img1,0 ,0,width,height,null);
        }catch (java.io.IOException e) {}
    }

    public void keyTyped(KeyEvent e){
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            keyPressed = false;
            System.out.println("escape pressed");
        }

    }

    public void keyReleased(KeyEvent e){
    }


}
