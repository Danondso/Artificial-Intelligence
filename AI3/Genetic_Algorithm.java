import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;


public class Genetic_Algorithm {

	public ArrayList<Path> population;
	Random rand = new Random();
	static int Max_Pop = 30;
	

	public void Genetic_Algo() throws IOException{
		//System.out.println("You're a dick tommy. ");
		
		BufferedImage im = ImageIO.read(new File("terrain.png"));
	   
		Genetic_Algo(im);
		
	}
	
	public void Genetic_Algo(BufferedImage im){
		
		population = new ArrayList<Path>(Max_Pop);
		
		for(int i = 0; i < Max_Pop; i++)
		{
			Double angle = rand.nextDouble() * (2 * Math.PI);
			//System.out.println(angle);
			Path p = new Path();
			p.setCh(new Pair(angle, rand.nextInt(Max_Pop)));
			population.add(p);
			//Create the population (making new paths)
		}	
		int n = 0;		
		while(n < 20000)
		{
			Events(im, n);
		   n++;		
		}
	}
	
	public double getCosts(BufferedImage im, String check){
		
		double arr[] = new double[30];
		   
		   for(int i = 0; i < population.size(); i++)
		   {
			   arr[i] = Pathfinder(population.get(i).chromosome, im);
		     //  System.out.println(arr[i]);
			   
		   }
		   
		  // System.out.println("Best Pop Cost Chance " + arr[0]);	
		   
		   Arrays.sort(arr);
		   
		   
		   if(check != "Schnooker"){
		   for(int i = 0; i < population.size(); i++)
		   {
			   if(arr[0] == Pathfinder(population.get(i).chromosome, im));
				   return i;
		   }
		   }
		   else
		   {
			   for(int i = 0; i < population.size(); i++)
			   {
				   if(arr[1] == Pathfinder(population.get(i).chromosome, im));
					   return i;
			   }
		   }
		  
		   return 0;
	}
	
	public void showBest(BufferedImage im, int n){
		
		double arr[] = new double[30];
		   
		   for(int i = 0; i < population.size(); i++)
			   arr[i] = Pathfinder(population.get(i).chromosome, im);
	
		   Arrays.sort(arr);
		   System.out.println((int)arr[0] + " " + n);	
	}
	
	public void Events(BufferedImage im, int n){
		
	//	System.out.println("Call me maybe?");
		
		double d = rand.nextDouble();
			
		if(n % 150 == 0)
		   showBest(im, n);
			
		if(d < 0.25)
		{
			Mutate_Add();
		}
		else if(d < 0.40)
		{	
			Tourney(im);
		}
		else if(d < 0.56)
		{
			Mutate_Simp();
		}
		else if(d < 0.68)
		{
			Mutate_Dir();
		}
		else if(d < 0.88)
		{
			Mutate_Drop();
		}
	}

	private void Mutate_Dir() {
		// TODO Auto-generated method stub
		int r = rand.nextInt(population.size());
			
		if(population.get(r).chromosome.size() > 0)
		{
			int size = 0;
			if(population.get(r).chromosome.size() == 1)
				size = 0;
			else
		 	size = rand.nextInt(population.get(r).chromosome.size());
			
			population.get(r).chromosome.get(size).setFirst((rand.nextDouble() * (2 * Math.PI)));
	
		}
		
		}

	private void Mutate_Add() {
		// TODO Auto-generated method stub
		int r = rand.nextInt(Max_Pop);
		
		Double angle = rand.nextDouble() * (2*Math.PI);
		//Pseudo uses r.nextD and r.nextInt
		population.get(rand.nextInt(population.size())).chromosome.add(new Pair(angle, rand.nextInt(Max_Pop)));
		
	}					//WUB WUB WUB WUB 
	private void Mutate_Drop(){
		
		int r = rand.nextInt(population.size());
		
		if(population.get(r).chromosome.size() > 0)
		{
			int drop = rand.nextInt(population.get(r).chromosome.size());
			population.get(r).chromosome.remove(drop);
		}
		
	}
	
	
	private void Tourney(BufferedImage im) {
		// TODO Auto-generated method stub
		
		int r = rand.nextInt(population.size());
		int q = rand.nextInt(population.size());
		int s = rand.nextInt(population.size());
		double chance = rand.nextDouble();
				
		Path A = population.get(r);
		Path S = population.get(q);
		Path Hooker = population.get((int)getCosts(im, "Hooker"));
		Path Schnooker = population.get((int) getCosts(im, "Schnooker"));
		
		int sizeA = Hooker.chromosome.size();
		int sizeB = Schnooker.chromosome.size();
		
	
		
		double a = Pathfinder(A.chromosome ,im);
		
		double b = Pathfinder(S.chromosome ,im);
		
		double d = rand.nextDouble();
		
		
		if(a < b)
		{	
		
			Path child = new Path();
			
			if(d < .1)
			population.remove(r);
			else
			population.remove(q);
			
			if(sizeA < 2 && sizeB < 2)
			{
				if(sizeA != 0)
				{
				   double angle = Hooker.chromosome.get(0).getFirst();
						   int steps = Hooker.chromosome.get(0).getSecond();
					
					child.chromosome.add(new Pair(angle,steps));
				
				}
				if(sizeB != 0)
				{
					double angle = Schnooker.chromosome.get(0).getFirst();
					   int steps = Schnooker.chromosome.get(0).getSecond();
				
					
					child.chromosome.add(new Pair(angle, steps));
				
				
				}
				
				}
			else{
				int counter = 0;
				
				if(sizeA == 1)
					 counter = 1;
					else 
					 counter = rand.nextInt(sizeA);
				
				for(int i = 0; i < counter; i++)
				{
					double angle = Hooker.chromosome.get(i).getFirst();
					   int steps = Hooker.chromosome.get(i).getSecond();
				
					child.chromosome.add(new Pair(angle, steps));
					
				}
				if(sizeB == 1)
					 counter = 1;
					else 
					 counter = rand.nextInt(sizeB);
				
				for(int i = 0; i < counter; i++)
				{
					
					double angle = Schnooker.chromosome.get(0).getFirst();
				     int steps = Schnooker.chromosome.get(0).getSecond();
					
					child.chromosome.add(new Pair(angle, steps));
					
				}
			}	
			population.add(child);
		}
		else if(b < a)
		{
		  Path child = new Path();
		  
		  if(d < .1)
		  population.remove(q);
		  else
		  population.remove(r);
		  
			if(sizeA < 2 && sizeB < 2)
			{
				if(sizeA != 0){
				
					double angle = Hooker.chromosome.get(0).getFirst();
					   int steps = Hooker.chromosome.get(0).getSecond();
				
					
					child.chromosome.add(new Pair(angle, steps));
				}
				if(sizeB != 0)
				{
					double angle = Schnooker.chromosome.get(0).getFirst();
					   int steps = Schnooker.chromosome.get(0).getSecond();
				
					
					child.chromosome.add(new Pair(angle, steps));
				}
			
			}
			
			else if(sizeA != 0 && sizeB != 0){
				for(int i = 0; i < rand.nextInt(sizeA) + 1; i++)
				{
					double angle = Hooker.chromosome.get(0).getFirst();
					   int steps = Hooker.chromosome.get(0).getSecond();
				
					child.chromosome.add(new Pair(angle, steps));
					
				}
				for(int i = 0; i < rand.nextInt(sizeB); i++)
				{
					double angle = Schnooker.chromosome.get(0).getFirst();
					   int steps = Schnooker.chromosome.get(0).getSecond();
						
					child.chromosome.add(new Pair(angle, steps));
					
				}
			}
			
			population.add(child);
		}
	}

	
	//use an if statement to make sure that there's a pair to be manipulated
	
	//Method that randomly mutates the steps 
	private void Mutate_Simp() {
		// TODO Auto-generated method stub
		//random variable for choosing an individual
		int r = rand.nextInt(population.size());

		if(population.get(r).chromosome.size() > 0){
			
			int size = rand.nextInt(population.get(r).chromosome.size());
		population.get(r).chromosome.get(size).setSecond((rand.nextInt(population.size())));
		}
	}
	
	public double Pathfinder(ArrayList<Pair> p, BufferedImage im){
	
		//Initialize the cost of the plan
		double cost = 0;
		//System.out.println("Checking Path");
		//Initial coordinates (for each chromosome, it always starts at 100, 100 
		double x = 100; 
		double y = 100;
		double tempx = 0;
		double tempy = 0;
		
		//If it's empty, there's no point in checking, just return cost of 0
		if(p.size()  > 0)
		{
		//	System.out.println(p.chromosome.size());
			//For loop goes through, each chromosome
			//Obtaining the steps and the angle
			Color colour = new Color(im.getRGB((int)x, (int)y));
			for(int i = 0; i < p.size(); i++)
		{
			//max amount of steps and the step counter
		    int count = (int) p.get(i).getSecond();
			int c = 0;
		//	System.out.println(count);
	
			//Get's the anglular direction
			double d = p.get(i).getFirst();
		   
	
			
		loop: while(c < count)
			{
				
			//	System.out.println("We can.");
				//Makes you step
				 x = (x + Math.cos(d));
				 y = (y + Math.sin(d));
				 
				 if(x < 0 || y < 0 || x > 499 || y > 499 )
				 {
					 //System.out.println("You fucked up.");	
					
					 if(x < 0)
					 x = (x - Math.cos(d));
					 
					 if(x > 499)
						 x = (x - Math.cos(d));
					 
						 if(y > 499)
					     y = (y - Math.sin(d));
					 
					    if(y < 0)
						 y = (y - Math.sin(d));
					 
					   p.get(i).setSecond(c - 1);
					   
				     break loop;	   
				 }
			
				 
			    if(x <= 499 || x >= 0 || y <= 499 || y >= 0)
			    {
			    	
					colour = new Color(im.getRGB((int)x, (int)y));
			        cost += colour.getGreen();
			           
			    }
			
			    
			    /*
			    
			  if((int)x == 400 && (int)y == 400)
				  {
					  System.out.println("You got there at a cost of " + (cost + Euclidean_Dist((int)x, (int)y))); 
				     System.out.println("X: " + x + " Y: " + y);		      
				      System.exit(0);
				  }	  
			  
			  */
			  
				c++; //#TopKek
			}//End while loop
			
			//sizea aren't moving D:
			
			tempx = x;
			tempy = y;
			
		}//End for loop
		
		
	 }//end size IF statement
		else{return Euclidean_Dist(1000000000, 0);}//If size is 0, then it's going to lose automatically
		//Return the compounded cost at the end of it all.
		
		 cost = cost + Euclidean_Dist((int) tempx, (int) tempy);
		 return cost;
	}
	
 private double Euclidean_Dist (int x, int y) {
	   
		int a, b;
	    a = Math.abs(400 - x);
	    b = Math.abs(400 - y);    
	    double dist = Math.sqrt(((a)*(a)) +((b)*(b)));
	    dist = dist * 500;
	    return dist; 
	    }	
	
}



