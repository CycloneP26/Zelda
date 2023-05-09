import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity 
{
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		this.gp=gp;
		this.keyH=keyH;
		setDefaultValue();
		getPlayerImage();
	}
	public void setDefaultValue()
	{
		x=100;
		y=100;
		speed=4;
		direction="down";
	}
	
	public void getPlayerImage()
	{
		try {
			up1=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingBack.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingBack1.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingDown.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingDown1.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingHorizontal.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingHorizontal1.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingRight.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/player/linkMovingRight1.png"));
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void getPlayerAttackImage() throws IOException
	{
		swordUp=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordUp1.png"));
		swordLeft=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordLeft1.png"));
		swordRight=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordRight1.png"));
		swordDown=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordDown1.png"));
		swordUp1=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordUp.png"));
		swordLeft1=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordLeft.png"));
		swordRight1=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordRight.png"));
		swordDown1=ImageIO.read(getClass().getResourceAsStream("/player/linkSwordDown.png"));
	}
	
	public void attacking()
	{
		spriteCounter++;
		if(spriteCounter<=5)
		{
			spriteNum=1;
		}
		if(spriteCounter>5&&spriteCounter<=25)
		{
			spriteNum=2;
		}
		if(spriteCounter>25)
		{
			spriteNum=1;
			spriteCounter=0;
			attacking=false;
		}
	}
	
	public void update()
	{
		if(attacking==true)
		{
			attacking();
		}
		if(keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true)
		{
			if(keyH.swordPressed==true)
			{
				attacking=true;
			}
			if(keyH.upPressed==true)
			{
				direction="up";
				y-=speed;
			}
			else if(keyH.downPressed==true)
			{
				direction="down";
				y+=speed;
			}
			else if(keyH.leftPressed==true)
			{
				direction="left";
				x-=speed;
			}
			else if(keyH.rightPressed==true)
			{
				direction="right";
				x+=speed;
			}
			spriteCounter++;
			if(spriteCounter>12) {
				if(spriteNum==1)
				{
					spriteNum=2;
				}
				else if(spriteNum==2)
				{
					spriteNum=1;
				}
				spriteCounter=0;
			}
		}
	}
		
	public void draw(Graphics2D g2)
	{
		BufferedImage image=null;
		switch(direction) {
		case "up":
			if(attacking==false)
			{
				if(spriteNum==1){image=up1;}
				if(spriteNum==2){image=up2;}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=swordUp;}
				if(spriteNum==2) {image=swordUp1;}
			}
			break;
		case "down":
			if(attacking==false)
			{
				if(spriteNum==1){image=down1;}
				if(spriteNum==2){image=down2;}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=swordDown;}
				if(spriteNum==2) {image=swordDown1;}
			}
			break;
		case "left":
			if(attacking==false)
			{
				if(spriteNum==1){image=left1;}
				if(spriteNum==2){image=left2;}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=swordLeft;}
				if(spriteNum==2) {image=swordLeft1;}
			}
			break;
		case "right":
			if(attacking==false)
			{
				if(spriteNum==1){image=right1;}
				if(spriteNum==2){image=right2;}
			}
			if(attacking==true)
			{
				if(spriteNum==1) {image=swordRight;}
				if(spriteNum==2) {image=swordRight1;}
			}
			break;
		}
		
			
		g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
		
		
	}

}
