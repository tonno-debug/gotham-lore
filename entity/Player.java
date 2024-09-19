package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import java.lang.Math;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp,KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction="down";

        spritePerAnimation=8; //sprite per animation
        framePerSprite=(int)Math.floor(60/spritePerAnimation);
        
        up=new BufferedImage[spritePerAnimation];
        down=new BufferedImage[spritePerAnimation];
        left=new BufferedImage[spritePerAnimation];
        right=new BufferedImage[spritePerAnimation];
    }

    public void getPlayerImage(){
        String img=null;
        try{
            for(int i=0;i<spritePerAnimation;i++)
            {
                img="res\\chr_run\\chrUpWalk"+(i+1)+".png";
                up[i]=ImageIO.read(new File(img));
            }
            for(int i=0;i<spritePerAnimation;i++)
            {
                img="res\\chr_run\\chrDownWalk"+(i+1)+".png";
                down[i]=ImageIO.read(new File(img));
            }
            for(int i=0;i<spritePerAnimation;i++)
            {
                img="res\\chr_run\\chrLeftWalk"+(i+1)+".png";
                left[i]=ImageIO.read(new File(img));
            }
            for(int i=0;i<spritePerAnimation;i++)
            {
                img="res\\chr_run\\chrRightWalk"+(i+1)+".png";
                right[i]=ImageIO.read(new File(img));
            }
        }catch(Exception e){
            System.out.println(img);
        }
        
    }

    public void update(){
        if(keyH.upPressed==true){
            direction="up";
            y-=speed;
        }
        else if(keyH.downPressed==true){
            direction="down";
            y+=speed;
        }
        else if(keyH.leftPressed==true){
            direction="left";
            x-=speed;
        }
        else if(keyH.rightPressed==true){
            direction="right";
            x+=speed;
        }

        frameCounter++;
        if(frameCounter>=framePerSprite){
            spriteNum=(spriteNum>=spritePerAnimation-1)?0:spriteNum+1;
            frameCounter=0;
        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image=null;
        switch (direction) {
            case "up":
                image=up[spriteNum];
                break;
                case "down":
                image=down[spriteNum];
                break;
                case "left":
                image=left[spriteNum];
                break;
                case "right":
                image=right[spriteNum];
                break;
        }

        g2.drawImage(image, x, y,400,400,null);
    }

    
}
