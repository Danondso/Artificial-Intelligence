import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Ball { 
	
	Image img;
	private int x = 0;
	private int y = 0;
	public int velocityX;
	public int velocityY;
	 
	
	public Ball(String name,int xIn, int yIn,int vX, int vY){
		
		this.x =  xIn;
		this.y = yIn;
		this.velocityX = vX;
		this.velocityY = vY;
		
		try {
			 img = ImageIO.read(new File("Ball"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update(){
		/*
		 * Method needs to start with a random angle 
		 * and a positive or negative X direction
		 */
		this.setX(this.getX() + this.velocityX);
		this.setY(this.getY() + this.velocityY);
		
	}
	
	
	public void setX(int xIn){
		this.x = xIn;
	}
	
	public void setY(int yIn){
		this.y = yIn;
	}
	
	public int getX(){
		return x;
	}
	
    public int getY(){
		return y;
	}
    public void setVx(int xIn){
    	this.velocityX = xIn;
    }
    public void setVy(int yIn){
    	this.velocityY = yIn;
    }
    public int getVx(){
    	return this.velocityX;
    }
    public int getVy(){
    	return this.velocityY;
    }

}
