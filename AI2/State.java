import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
	

public class State extends HashMap<String, State> implements Comparable<State>{

	public Double cost;
	State parent;
	public int x;
	public int y;
    public int heur;
    //All the key points on the board, e.g. the points that were inserted into the arraylist first. 
    public String KeyPoints;
    public ArrayList<Piece> config;
//    public boolean[][] spaces;
	
	State(double costIn, State par, int xIn, int yIn, String key, ArrayList<Piece> board)
	{
		x = xIn;
		y = yIn;
		//String of all the key points
		KeyPoints = key;
		parent = par;
		cost = costIn;
		heur = (Math.abs(5 - x) + Math.abs(1 - y)); 
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
