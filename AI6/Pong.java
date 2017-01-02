import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


/*
 * Pong engine
 * 
 * 
 * 
 */
public class Pong extends JPanel implements KeyListener, ActionListener{
	
	/*
	 * This is going to create the JFrame and the board
	 * So you need to make the JFrame,
	 *  
	 */
	Players p1 = new Players("Player1", 30, 250);
	Players p2 = new Players("PLayer2", 700, 60);
	Ball b = new Ball("Ball", 300, 300, 2, (-1));
	public int gameState = 0;
	
	//Class constructor
	public Pong(){
		
		JFrame f = new JFrame("Pong");
		setBackground(Color.BLACK);
		setLayout(null);
		setPreferredSize(new Dimension(750, 500));
		//f.setDefaultCloseOperation(operation);
		f.add(this);
	    f.addKeyListener(this);
	    f.setPreferredSize(new Dimension(750, 500));
	  
	    f.pack();
	    f.setResizable(false);
	    f.setVisible(true);
		Game_Loop();
		
	}

		
	public static void main(String[] args){
        
		new Pong();
	}

	
	
	
	public void Game_Loop(){
		
		
		
		int counter = 0;
		while(gameState == 0)
		{
			
			//Write in ball logic
	       
			if(counter % 100000 == 5)
			b.update();		    
		
		  if(b.getY() <= 0)	
		  {
			  b.setY(0 + 1);
			  b.velocityY *= (-1); 
			  
		  }
		  else if((b.getY() + b.img.getHeight(null)) >= this.getHeight())
		  {
			  b.setY((this.getHeight() - b.img.getHeight(null)) - 1);
			  b.velocityY *= (-1);  
			  
		  }
			
		  if(b.getX() <= 0)
		  {
			  b.setX(0 + 1);
			  b.velocityX *= (-1);  
		  }
		  else if((b.getX() + b.img.getWidth(null)) >= this.getWidth())
		  {
			  b.setX((this.getWidth() - b.img.getWidth(null)) - 1);
			  b.velocityX *= (-1);  
		  }
		  
		  //Paddle collisions
		  if(b.getX() <= (p1.getX() + p1.img.getWidth(null)))
		  {
			  //Sets the x at the border of the paddle
			  b.setX((p1.img.getWidth(null)));
			  
			  //Reverses the velocity 
			  b.velocityX *= (-1);  
		  }
		  else if((b.getX() + b.img.getWidth(null)) >= p2.getX())
		  {
			  b.setX(p2.getX() - b.img.getWidth(null));
			  b.velocityX *= (-1); 
		  }
		  
		  counter++;
		}
		
		if(gameState == 1)
			System.out.println("Player One Wins!");
		if(gameState == -1)
			System.out.println("Player Two Wins!");
		
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics g1 = g;
		Graphics g2 = g;
		Graphics g3 = g;
		
		g1.drawImage(p1.img, p1.getX(), p1.getY(), null);
		g2.drawImage(p2.img, p2.getX(), p2.getY(), null);
        g3.drawImage(b.img, b.getX(), b.getY(),null);
		repaint();	
	}

	//Need to figure out how to make the the players move independently
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int Kc = e.getKeyCode();
		
		if(Kc == KeyEvent.VK_W)
		{
			//System.out.println("The Pope");
         if(p1.getY() == 0)
        	 p1.setY(0);
         else
			p1.setY(p1.getY() - 10);
		}
		else if (Kc == KeyEvent.VK_UP)
		{
	      if(p2.getY() == 0)
	          p2.setY(0);
	      else
			p2.setY(p2.getY() - 10);
		}
		else if (Kc == KeyEvent.VK_S)
		{
			if(p1.getY() + p1.img.getHeight(null) == this.getHeight())
				p1.setY(this.getHeight() - 50);
			else
			 p1.setY(p1.getY() + 10);
		}
		else if (Kc == KeyEvent.VK_DOWN)
		{
			if(p2.getY() + p2.img.getHeight(null) == this.getHeight())
				p2.setY(this.getHeight() - 50);
			else
			 p2.setY(p2.getY() + 10);
		}
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
