package manuel;

import javax.swing.JPanel; 

import java.lang.Thread;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize=16;
    final int scale=3;

    final int tileSize=originalTileSize*scale;
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWidth=tileSize*maxScreenCol;
    final int screenHeight=tileSize*maxScreenRow;

    int FPS=60;

    KeyHandler keyH=new KeyHandler();
    Thread gameThread;

    int playerX=100;
    int playerY=100;
    int playerSpeed=3;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval=1000000000/FPS;
        double delta=0;
        double lastTime=System.nanoTime();
        long currentTime;

        while(gameThread !=null){
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            lastTime=currentTime;
            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        if(keyH.upPressed==true){
            playerY-=playerSpeed;
        }
        else if(keyH.downPressed==true){
            playerY+=playerSpeed;
        }
        else if(keyH.leftPressed==true){
            playerX-=playerSpeed;
        }
        else if(keyH.rightPressed==true){
            playerX+=playerSpeed;
        }
    
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }
}