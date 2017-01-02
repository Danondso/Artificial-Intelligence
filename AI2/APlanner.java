import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

import javax.imageio.ImageIO;

  class NC implements Comparator<AState>{
	public int compare(AState e1, AState e2) {
		if(e1.y < e2.y) return -1;
		else if(e1.y > e2.y) return 1;
		else if(e1.x < e2.x) return -1;
		else if(e1.x > e2.x) return 1;
		else return 0;
	}
}  

public class APlanner {

	
	
public	static AState AStar() throws Exception{
	
	AState start = new AState(0, null, 100, 100);	
	BufferedImage im = ImageIO.read(new File("terrain.png"));
	AState finish = new AState(getCost(im, 400, 400), null, 400, 400);
	PriorityQueue<AState> frontier = new PriorityQueue<AState>();
	TreeSet<AState> beenthere = new TreeSet<AState>(new NC());
	//boolean array marking spaces as found
	//#QuickAndDirty

            frontier.add(start);	
            beenthere.add(start);
            
	//counter for the green lines
	int count = 0;

	int pop = 0;
	while(frontier.size() > 0)
	{
		//pops the next state off the priority queue
		 AState r = frontier.poll();
		 pop++;
			 //checks to see if the goal has been found
			 if(r.x == finish.x && r.y == finish.y)
			 {
				//draws the red line(shortest past
				 while(r != start){
				 im.setRGB(r.x, r.y , 0xFFFF0000);
				 r = r.parent;
			 }
		 		//saves the image as "path.png"
		 		ImageIO.write(im, "png", new File("path.png"));
		  		//return the goal state
		 		System.out.println("astar1=" + pop);
		 		return r;
	 		}

		 //sets the popped state to true	
		 beenthere.add(r);

	 //neighbor evaluation loop
	 for(int i = 0; i < 4; i++){
		 
		//based on the action (clockwise), creates the neighbors
		 AState child = transition(r, i);
		 double acost = action_cost(im, r, i);
		
		//if the child has been found transform returns null
		//skips the entire cost additive step and frontier addition
		 if(child != null){
		 
		 if(beenthere.contains(child))
		 {
			 //System.out.println("Goomba");
			 if(r.cost + acost < child.cost){
				 child.cost = r.cost + acost + manhattanDist(child);
				 child.parent = r;
				//System.out.println("Goomba");
				// System.out.println("New X, Y : " + r.x +" " + r.y + " found");
			 }
		 } 
		 else
		    {
			 //if (r.x == 56 && r.y == 146)
			//	 System.out.println("hi");
		//	 System.out.println("New X, Y : " + r.x +" " + r.y + " NOT found");
			   
			 	child.cost = r.cost + acost + manhattanDist(child);
	            		child.parent = r;
	            		frontier.add(child);
	            		beenthere.add(child);
		    }
		 	 
	// throw new Exception("No available path to the goal.");
		 }
		 }
	 //count increments for each pass thru the while loop
	count++;
	//sets the pixel value to green 
	if(count % 5000 < 1000)
	{
		im.setRGB(r.x, r.y, 0xFF00FF00);
	}
}
	//return if something didn't work
	throw new Exception("No available path to goal.");
}


private static Double manhattanDist(AState child) {
	// TODO Auto-generated method stub
	
	Double Man = (double) (14* (Math.abs(400 - child.x) + Math.abs(400- child.y)));
	
	return Man;
}


public	static AState UCS() throws Exception{
	AState start = new AState(0, null, 100, 100);	
	BufferedImage im = ImageIO.read(new File("terrain.png"));
	AState finish = new AState(getCost(im, 400, 400), null, 400, 400);
	PriorityQueue<AState> frontier = new PriorityQueue<AState>();
	
	//boolean array marking spaces as found
	//#QuickAndDirty
	boolean[][] found = new boolean[500][500];
	
	 //System.out.println("start x: " + start.x + " start Y: " + start.y);

	//sets the popped state to found		
	found[start.x][start.y] = true;
	 
	
            frontier.add(start);
	
	//counter for the green lines
	int count = 0;

	int pop = 0;
	while(frontier.size() > 0)
	{
		//pops the next state off the priority queue
		 AState r = frontier.poll();
		 pop++;
			 //checks to see if the goal has been found
			 if(r.x == finish.x && r.y == finish.y)
			 {
				//draws the red line(shortest past
				 while(r != start){
				 im.setRGB(r.x, r.y , 0xFFFF0000);
				 r = r.parent;
			 }
		 		//saves the image as "path.png"
		 		ImageIO.write(im, "png", new File("path.png"));
		  		//return the goal state
		 		System.out.println("bfs1=" + pop);
		 		return r;
	 		}

		 //sets the popped state to true	
		 found[r.x][r.y] = true;
	 

	 //neighbor evaluation loop
	 for(int i = 0; i < 4; i++){
		 
		//based on the action (clockwise), creates the neighbors
		 AState child = transition(r, i);
		 double acost = action_cost(im, r, i);
		
		//if the child has been found transform returns null
		//skips the entire cost additive step and frontier addition
		 if(child != null){
		 
		 if(found[child.x][child.y])
		 {
			 //System.out.println("Goomba");
			 if(r.cost + acost < child.cost){
				 child.cost = r.cost + acost;
				 child.parent = r;
				//System.out.println("Goomba");
				// System.out.println("New X, Y : " + r.x +" " + r.y + " found");
			 }
		 } 
		 else
		    {
			 //if (r.x == 56 && r.y == 146)
			//	 System.out.println("hi");
		//	 System.out.println("New X, Y : " + r.x +" " + r.y + " NOT found");
			    found[child.x][child.y] = true;
			 	child.cost = r.cost + acost;
	            		child.parent = r;
	            		frontier.add(child);
		    }
		 	 
	// throw new Exception("No available path to the goal.");
		 }
		 }
	 //count increments for each pass thru the while loop
	count++;
	//sets the pixel value to green 
	if(count % 5000 < 1000)
	{
		im.setRGB(r.x, r.y, 0xFF00FF00);
	}
}
	//return if something didn't work
	throw new Exception("No available path to goal.");
//	return null;
}



//sets the pixel to green
static public void setGreen(BufferedImage image, int x, int y){
			        
		image.setRGB(x, y, 0xFF00FF00);			
		//ImageIO.write((RenderedImage)image, "png", "terrain.png");
	
}
	

static public double action_cost(BufferedImage im, AState current, int action)
{
//gets the pixel value for the above
	if(action == 0){
	  if(current.y != 0)
	 return	getCost(im, current.x, current.y - 1);    	
	}
	 //the right
	 if(action == 1){
       if(current.x != 499)	
      return getCost(im ,current.x + 1, current.y);
	} 
//the bottom
   	if(action == 2){
      if(current.y != 499)
	   return getCost(im, current.x, current.y + 1);
	}
//the left
   	 if(action == 3){
       if(current.x != 0)
	  return getCost(im, current.x - 1, current.y);
	}
	return 0; //return if nothing worked
}



static public AState transition(AState current, int action){
	
	//System.out.println("Current x: " + current.x + " Current Y: " + current.y);
/*
	Same as action cost, but returns a new state object with 0 cost 
	a null parent, and the coordinates with respect to the currently occupied state
*/	

     if(action == 0){
       if(current.y != 0)
         return new AState(0, null, current.x, current.y - 1);        	 
     }
     if(action == 1){
       if(current.x != 499)
         return new AState(0, null, current.x + 1, current.y);
     }
     if(action == 2){
       if(current.y != 499)
         return new AState(0, null, current.x, current.y + 1);
     }
     if(action == 3){
       if(current.x != 0)
        return new AState(0, null, current.x - 1, current.y);
     }
	return null;
}

public static int getCost(BufferedImage image, int x, int y)
{
	//color object used to acquire the green channel value
	Color c = new Color(image.getRGB(x, y));
	 // System.out.println(c.getGreen());
	
//if for some reason the cost is negative
	if(c.getGreen() < 0)
	   return  c.getGreen() + 256;
		
		return c.getGreen(); //returns the green channel value	
}	

	
}
