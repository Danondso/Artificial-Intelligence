import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Players {

	Image img;
	private int x; 
	private int y;
	private String PName;
	
	public Players(String name, int xIn, int yIn){
		
		x = xIn;
		y = yIn;
		PName = name;
		
		try {
			 img = ImageIO.read(new File("Player"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setX(int xIn){
		x = xIn;
	}
	
	public void setY(int yIn){
		y = yIn;
	}
	
	public int getX(){
		return x;
	}
	
    public int getY(){
		return y;
	}
	
	
}
