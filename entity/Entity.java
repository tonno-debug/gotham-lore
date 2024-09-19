package entity;

import java.awt.image.BufferedImage;

public class Entity{
    public int x,y;
    public int speed;

    public BufferedImage[] up;
    public BufferedImage[] down;
    public BufferedImage[] left;
    public BufferedImage[] right;
    public String direction;

    public int frameCounter=0;
    public int framePerSprite=0;
    public int spritePerAnimation; 
    public int spriteNum=0;
}