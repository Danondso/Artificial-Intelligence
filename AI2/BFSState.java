

	import java.awt.Point;
	import java.util.ArrayList;
	import java.util.HashMap;
		

	public class BFSState extends HashMap<String, BFSState> implements Comparable<State>{

		
		public Double cost;
		BFSState parent;
		public int x;
		public int y;
	    public int heur;
	    //All the key points on the board, e.g. the points that were inserted into the arraylist first. 
	    public String KeyPoints;
	    public ArrayList<Piece> config;
//	    public boolean[][] spaces;
		
		BFSState(double costIn, BFSState par, int xIn, int yIn, String key, ArrayList<Piece> board)
		{
			x = xIn;
			y = yIn;
			//String of all the key points
			KeyPoints = key;
			parent = par;
			cost = costIn;
			heur = 0; 
	        //List of all the pieces
			config = board;
			//boolean array of available positions
		//	spaces = available_spaces;
		}

		@Override
		public int compareTo(State b) {
		Double totalcost = cost + heur;
			return totalcost.compareTo(b.cost + b.heur);
		}
		

		
	}

