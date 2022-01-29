import java.awt.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;



public class Frame extends JFrame implements KeyListener{
    static public BufferedImage bg_img;
    static public BufferedImage cat_img;
    static public BufferedImage cat_mir_img;
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
        try {
            bg_img = ImageIO.read(new File("C:\\Java\\Cat Chasing Mouse\\src\\grass.jpg"));
            cat_img = ImageIO.read(new File("C:\\Java\\Cat Chasing Mouse\\src\\cat.png"));
            cat_mir_img = ImageIO.read(new File("C:\\Java\\Cat Chasing Mouse\\src\\cat_mirror.png"));
            cat = new Cat((float) width/2,(float) height/2);
            addKeyListener(this);

        }catch (java.io.IOException e) {}
    }


    public void draw(){
        Graphics rGraphics;
        Image raster;
        play_sound();
        while(true)
        {
            raster = this.createImage(width, height);
            rGraphics = raster.getGraphics();
            play_sound();
            while(keyPressed){
                rGraphics.drawImage(bg_img,0,0,width,height,null);
                cat.move(mouse_position());
                cat.draw(rGraphics);
                try{Thread.sleep(15);}catch(Exception e){}
                getGraphics().drawImage(raster,0,0,getWidth(),getHeight(),null);
            }
            rGraphics.drawImage(bg_img,0,0,width,height,null);
            getGraphics().drawImage(raster,0,0,getWidth(),getHeight(),null);
            rGraphics.dispose();
        }
    }

    public void play_sound(){
        try{
            AudioInputStream bgm = AudioSystem.getAudioInputStream(new File("C:\\Java\\Cat Chasing Mouse\\src\\meow_lofi.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(bgm);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch (Exception e){ System.out.println("Audio could not be found");}
    }

    public Point mouse_position(){
        return this.getMousePosition();
    }


    public void keyTyped(KeyEvent e){
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            keyPressed = !keyPressed;
            System.out.println("escape pressed");
        }

    }

    public void keyReleased(KeyEvent e){
    }


}
