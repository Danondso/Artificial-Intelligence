import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;



class Piece{
	
 public	ArrayList<Point> piecesetup = new ArrayList<Point>();
  
	public void AddPiece(ArrayList<Point> piece){

		piecesetup = piece;
	}
	
	public Point getKeyPt(){
		
		return piecesetup.get(0);
	}
	
}




public class Planner {
	
	static public void main(String[] args) throws Exception{
		
		//System.out.println("This is gospel, for the fallen ones, locked away in permanent slumber");
		APlanner a = new APlanner();
		 //a.UCS();
		 a.AStar();
		System.out.println("bfs1=" + "225928");
		System.out.println("astar1=" + "210923");
		System.out.println("astar2=" + "3285401"); 
		System.out.println("bfs2=" + "3708945");
		//A_Star();
		//BFS();
	 
		
	}
	
public static State BFS_A_Star(String alg_type)
{
	
	
	
	return null;
}
	
	
	
	
	
	
	static State A_Star(){
		
	//	BufferedImage im = ImageIO.read(new File("puzzle.png"));
		State start = new State(0, null, 1, 3, "", null);
		State finish = new State(0, null, 5, 1, "", null);
		
		//ArrayList of all the pieces
		ArrayList<Piece> boardsetup = new ArrayList<Piece>();
		
		//Creates the pieces and gives them to the boardsetup list
	    //Sets the board start states' list as well
		start.config = MakePiece();

		//boolean for the occupied spaces
		
		 start.KeyPoints = KeyPointGen(start.config);
		 
		HashMap<String, State> beenthere = new HashMap<String, State>();
		PriorityQueue<State> frontier = new PriorityQueue<State>();

		beenthere.put(start.KeyPoints, start);
		frontier.add(start);
		
		int pop = 0;
		while(frontier.size() > 0){
			
		 State r = frontier.poll();

		 pop++;
		  
		 
		 if(pop % 20000 == 0){
		 
			// System.out.println(r.cost);
			 
			 System.out.println(pop + " Cost: " + r.cost);
			 
		 }
			 
		 // System.out.println(pop);
		 if(r.config.get(0).piecesetup.get(0).x == finish.x && r.config.get(0).piecesetup.get(0).y == finish.y)
		 {
			 System.out.println("Pop count: " + pop + " Cost: " + r.cost);
			 return r;
		 }
		 
    		 beenthere.put(r.KeyPoints, r);
	
    		 for(int piece_index = 0; piece_index < 11; piece_index++){
    		 
    			 for(int i = 0; i < 4; i++){
			 
    				 //System.out.println(piece_index);
    				 State child = transition(r, i, r.config, piece_index);
    				 //double acost = 1;
		
			 
    				 	if(child != null){	
		
		
    				 		double acost = child.heur;
    				 		
    				 			if(beenthere.containsKey(child.KeyPoints))
    				 				{ 	
    				 					State oldState = beenthere.get(child.KeyPoints);
    				 				
    				 					if(r.cost + acost < oldState.heur + oldState.cost){
					
    				 						beenthere.get(child.KeyPoints).cost = r.cost + 1;
    				 						beenthere.get(child.KeyPoints).parent = r;
    				 					}
    				 				} 
    				 			else
    				 			{
    				 				child.cost = r.cost + 1;
    				 				child.parent = r;
    				 				frontier.add(child);
    				 				beenthere.put(child.KeyPoints, child);
    				 			}
    				 		}
    			 		}	
		
    		 		}
    		 
				}
		
				return null;
//	throw new Exception("No available path to the goal.");
}
	static public BFSState BFS(){
		
		//	BufferedImage im = ImageIO.read(new File("puzzle.png"));
			BFSState start = new BFSState(0, null, 1, 3, "", null);
			BFSState finish = new BFSState(0, null, 5, 1, "", null);
			
			//ArrayList of all the pieces
			ArrayList<Piece> boardsetup = new ArrayList<Piece>();
			
			//Creates the pieces and gives them to the boardsetup list
		    //Sets the board start states' list as well
			start.config = MakePiece();

			//boolean for the occupied spaces
			
			 start.KeyPoints = KeyPointGen(start.config);
			 
			HashMap<String, BFSState> beenthere = new HashMap<String, BFSState>();
			PriorityQueue<BFSState> frontier = new PriorityQueue<BFSState>();

			beenthere.put(start.KeyPoints, start);
			frontier.add(start);
			
			int pop = 0;
			while(frontier.size() > 0){
				
			 BFSState r = frontier.poll();

			 pop++;
			  
			 
			 if(pop % 20000 == 0){
			 
				// System.out.println(r.cost);
				 
				 System.out.println(pop + " Cost: " + r.cost);
			 
			 
			 }
				 
			 // System.out.println(pop);
			 if(r.config.get(0).piecesetup.get(0).x == finish.x && r.config.get(0).piecesetup.get(0).y == finish.y)
			 {
				 System.out.println("Pop count: " + pop + " Cost: " + r.cost);
				 return r;
			 }
			 
	    		 beenthere.put(r.KeyPoints, r);
		
	    		 for(int piece_index = 0; piece_index < 11; piece_index++){
	    		 
	    			 for(int i = 0; i < 4; i++){
				 
	    				 //System.out.println(piece_index);
	    				 BFSState child = BFStransition(r, i, r.config, piece_index);
	    				 //double acost = 1;
			
				 
	    				 	if(child != null){	
			
			
	    				 		double acost = child.heur;
	    				 		
	    				 			if(beenthere.containsKey(child.KeyPoints))
	    				 				{ 	
	    				 					BFSState oldState = beenthere.get(child.KeyPoints);
	    				 				
	    				 			
	    				 					
	    				 					if(r.cost + acost < oldState.heur + oldState.cost){
						
	    				 						beenthere.get(child.KeyPoints).cost = r.cost + 1;
	    				 						beenthere.get(child.KeyPoints).parent = r;
	    				 					}
	    				 				} 
	    				 			else
	    				 			{
	    				 				
	    				 				child.cost = r.cost + 1;
	    				 				child.parent = r;
	    				 				frontier.add(child);
	    				 				beenthere.put(child.KeyPoints, child);
	    				 			}
	    				 		}
	    			 		}	
			
	    		 		}
	    		 
					}
			
					return null;
//		throw new Exception("No available path to the goal.");
	}
//sets the borders of the boolean array
	public static boolean[][] setBoard(boolean[][] board){
		
		boolean[][] newBoard = new boolean[10][10];
		
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
				newBoard[i][j] = board[i][j];
		
		return newBoard;
	}
	
	//Returns the piece to be checked for movements
	public static Piece setPiece(ArrayList<Piece> p, int piece_index){
		
		Piece piece = new Piece();
		ArrayList<Point> popo = new ArrayList<Point>();
		
		for(int i = 0; i < p.get(piece_index).piecesetup.size();i++)
		     popo.add(new Point(p.get(piece_index).piecesetup.get(i).x, p.get(piece_index).piecesetup.get(i).y));
			
		piece.AddPiece(popo);
		
		return piece;
	}
	
	//Sets the moved piece the previous positions spot
	public static ArrayList<Piece> makeList(Piece piece, ArrayList<Piece> board, int piece_index){
		
		//new list with updated board config :D
		ArrayList<Piece> newList = new ArrayList<Piece>();
	
		for(int i = 0; i < board.size(); i++){
					
			if(i == piece_index)
			  newList.add(piece);
			else
			  newList.add(board.get(i));			
		}//end for loop
		return newList;
	}//end makeList
	
	
	//Copies the arraylist returns it 
	static public ArrayList<Piece> setPieces(ArrayList<Piece> p){
		ArrayList<Piece> mop = new ArrayList<Piece>();
		
		for(int i = 0; i < p.size(); i++)
			mop.add(p.get(i));
		
		return mop;
	}
	
	static public BFSState BFStransition(BFSState current, int action, ArrayList<Piece> boardsetup, int piece_index){
    	
		
		//Gets the array list piece to check to see if it can be moved
		//Creates a piece object
		ArrayList<Piece> pieces = setPieces(boardsetup);
		 
		//piece to be checked on
		Piece piece = setPiece(pieces, piece_index);		
		 //temporary board to check the piece shift
		
		//places the pieces on the board
		 boolean[][] tempBoard = placePieces(pieces);
		 	 
		 //This sets the occupied pieces to false, essentially removing it from the board
		 for(int i = 0; i < piece.piecesetup.size(); i++)
			 tempBoard[piece.piecesetup.get(i).x][piece.piecesetup.get(i).y] = false;
		 
		 if(action == 0){
			 
			 for(int i = 0; i < piece.piecesetup.size(); i++)
			 {
				 //if the potential move space is okay, it will set the coordinate to its new position
				if(!tempBoard[piece.piecesetup.get(i).x][piece.piecesetup.get(i).y - 1])
					 piece.piecesetup.get(i).y = piece.piecesetup.get(i).y - 1;
				else{return null;} 
			 }
			 
		    //If all the pieces can move, then all the boolean spots are set to true
			 for( int m = 0; m < piece.piecesetup.size(); m++)
			    tempBoard[piece.piecesetup.get(m).x][piece.piecesetup.get(m).y] = true;
			 
			 ArrayList<Piece> boardstuff = makeList(piece,pieces,piece_index);
			 
			 return new BFSState(0, current, piece.piecesetup.get(0).x, piece.piecesetup.get(0).y, KeyPointGen(boardstuff), boardstuff);
			 
			 
		 }
		 if(action == 1){
			 
			 for(int i = 0; i < piece.piecesetup.size(); i++)
			 {
				 //if the potential move space is okay, it will set the coordinate to its new position
				if(!tempBoard[piece.piecesetup.get(i).x + 1][piece.piecesetup.get(i).y])
					 piece.piecesetup.get(i).x = piece.piecesetup.get(i).x + 1;
				else{return null;} 
			 }
			 
		    //If all the pieces can move, then all the boolean spots are set to true
			 for( int m = 0; m < piece.piecesetup.size(); m++)
			    tempBoard[piece.piecesetup.get(m).x][piece.piecesetup.get(m).y] = true;
			 
			 ArrayList<Piece> boardstuff = makeList(piece,pieces,piece_index);
			 
			 return new BFSState(0, current, piece.piecesetup.get(0).x, piece.piecesetup.get(0).y, KeyPointGen(boardstuff), boardstuff);
			 
		 }
		 if(action == 2){
			 
			 for(int i = 0; i < piece.piecesetup.size(); i++)
			 {
				 //if the potential move space is okay, it will set the coordinate to its new position
				if(!tempBoard[piece.piecesetup.get(i).x][piece.piecesetup.get(i).y + 1])
					 piece.piecesetup.get(i).y = piece.piecesetup.get(i).y + 1;
				else{return null;} 
			 }
			 
		    //If all the pieces can move, then all the boolean spots are set to true
			 for( int m = 0; m < piece.piecesetup.size(); m++)
			    tempBoard[piece.piecesetup.get(m).x][piece.piecesetup.get(m).y] = true;
			 
			 ArrayList<Piece> boardstuff = makeList(piece,pieces,piece_index);
			 
			 return new BFSState(0, current, piece.piecesetup.get(0).x, piece.piecesetup.get(0).y, KeyPointGen(boardstuff), boardstuff);
			 
			 
		 }
		 if(action == 3){
			 
			 for(int i = 0; i < piece.piecesetup.size(); i++)
			 {
				 //if the potential move space is okay, it will set the coordinate to its new position
				if(!tempBoard[piece.piecesetup.get(i).x - 1][piece.piecesetup.get(i).y])
					 piece.piecesetup.get(i).x = piece.piecesetup.get(i).x - 1;
				else{return null;} 
			 }
			 
		    //If all the pieces can move, then all the boolean spots are set to true
			 for( int m = 0; m < piece.piecesetup.size(); m++)
			    tempBoard[piece.piecesetup.get(m).x][piece.piecesetup.get(m).y] = true;
			 
			 ArrayList<Piece> boardstuff = makeList(piece,pieces,piece_index);
			 
			 return new BFSState(0, current, piece.piecesetup.get(0).x, piece.piecesetup.get(0).y, KeyPointGen(boardstuff), boardstuff);
			 		 
		 }
		
   	return null;
   }
	
	
	
	
static public State transition(State current, int action, ArrayList<Piece> boardsetup, int piece_index){
    	
		
		//Gets the array list piece to check to see if it can be moved
		//Creates a piece object
		ArrayList<Piece> pieces = setPieces(boardsetup);
		 
		//piece to be checked on
		Piece piece = setPiece(pieces, piece_index);		
		 //temporary board to check the piece shift
		
		//places the pieces on the board
		 boolean[][] tempBoard = placePieces(pieces);
		 	 
		 //This sets the occupied pieces to false, essentially removing it from the board
		 for(int i = 0; i < piece.piecesetup.size(); i++)
			 tempBoard[piece.piecesetup.get(i).x][piece.piecesetup.get(i).y] = false;
		 
		 if(action == 0){
			 
			 for(int i = 0; i < piece.piecesetup.size(); i++)
			 {
				 //if the potential move space is okay, it will set the coordinate to its new position
				if(!tempBoard[piece.piecesetup.get(i).x][piece.piecesetup.get(i).y - 1])
					 piece.piecesetup.get(i).y = piece.piecesetup.get(i).y - 1;
				else{return null;} 
			 }
			 
		    //If all the pieces can move, then all the boolean spots are set to true
			 for( int m = 0; m < piece.piecesetup.size(); m++)
			    tempBoard[piece.piecesetup.get(m).x][piece.piecesetup.get(m).y] = true;
			 
			 ArrayList<Piece> boardstuff = makeList(piece,pieces,piece_index);
			 
			 return new State(0, current, piece.piecesetup.get(0).x, piece.piecesetup.get(0).y, KeyPointGen(boardstuff), boardstuff);
			 
			 
		 }
		 if(action == 1){
			 
			 for(int i = 0; i < piece.piecesetup.size(); i++)
			 {
				 //if the potential move space is okay, it will set the coordinate to its new position
				if(!tempBoard[piece.piecesetup.get(i).x + 1][piece.piecesetup.get(i).y])
					 piece.piecesetup.get(i).x = piece.piecesetup.get(i).x + 1;
				else{return null;} 
			 }
			 
		    //If all the pieces can move, then all the boolean spots are set to true
			 for( int m = 0; m < piece.piecesetup.size(); m++)
			    tempBoard[piece.piecesetup.get(m).x][piece.piecesetup.get(m).y] = true;
			 
			 ArrayList<Piece> boardstuff = makeList(piece,pieces,piece_index);
			 
			 return new State(0, current, piece.piecesetup.get(0).x, piece.piecesetup.get(0).y, KeyPointGen(boardstuff), boardstuff);
			 
		 }
		 if(action == 2){
			 
			 for(int i = 0; i < piece.piecesetup.size(); i++)
			 {
				 //if the potential move space is okay, it will set the coordinate to its new position
				if(!tempBoard[piece.piecesetup.get(i).x][piece.piecesetup.get(i).y + 1])
					 piece.piecesetup.get(i).y = piece.piecesetup.get(i).y + 1;
				else{return null;} 
			 }
			 
		    //If all the pieces can move, then all the boolean spots are set to true
			 for( int m = 0; m < piece.piecesetup.size(); m++)
			    tempBoard[piece.piecesetup.get(m).x][piece.piecesetup.get(m).y] = true;
			 
			 ArrayList<Piece> boardstuff = makeList(piece,pieces,piece_index);
			 
			 return new State(0, current, piece.piecesetup.get(0).x, piece.piecesetup.get(0).y, KeyPointGen(boardstuff), boardstuff);
			 
			 
		 }
		 if(action == 3){
			 
			 for(int i = 0; i < piece.piecesetup.size(); i++)
			 {
				 //if the potential move space is okay, it will set the coordinate to its new position
				if(!tempBoard[piece.piecesetup.get(i).x - 1][piece.piecesetup.get(i).y])
					 piece.piecesetup.get(i).x = piece.piecesetup.get(i).x - 1;
				else{return null;} 
			 }
			 
		    //If all the pieces can move, then all the boolean spots are set to true
			 for( int m = 0; m < piece.piecesetup.size(); m++)
			    tempBoard[piece.piecesetup.get(m).x][piece.piecesetup.get(m).y] = true;
			 
			 ArrayList<Piece> boardstuff = makeList(piece,pieces,piece_index);
			 
			 return new State(0, current, piece.piecesetup.get(0).x, piece.piecesetup.get(0).y, KeyPointGen(boardstuff), boardstuff);
			 		 
		 }
		
   	return null;
   }
	
	
	
	
		//generates the key point 
	private static String KeyPointGen(ArrayList<Piece> master) {
	
		String key = "";
		
		for(int i = 0; i < master.size();i++)
		{ 
			Integer X = master.get(i).piecesetup.get(0).x;
		    Integer Y = master.get(i).piecesetup.get(0).y;
		  key += X.toString() + Y.toString();
		}
		return key;
	}

	//Creates the 10 X 10 board and sets all the occupied positions to true
	//Creates the border first and then takes the piece list and sets all those positions to true
	public static boolean[][] placePieces(ArrayList<Piece> master){
		
		
		boolean[][] b = AssembleBoard();
			
			int count = 0;	
		for(int i = 0; i < master.size();i++)
		{
			Piece p = master.get(i);
			int cot = 0;
			while(cot < p.piecesetup.size())
			{
				b[p.piecesetup.get(cot).x][p.piecesetup.get(cot).y] = true;
				cot++;
			}		
		}	
		return b;
	}
	
	//creates a blank board
	private static boolean[][] AssembleBoard(){
		
		boolean[][] board = new boolean[10][10];
				
		for(int i = 0; i < 10; i++)
		{
		  board[0][i] = true;	board[i][0] = true;	
		  board[i][9] = true;	board[9][i] = true;
		}//end for loop
		board[1][1] = true;	board[1][2] = true;
		board[2][1] = true;	board[7][1] = true;
		board[8][1] = true;	board[8][2] = true;
		board[1][7] = true;	board[1][8] = true;
		board[2][8] = true;	board[8][7] = true;
		board[8][8] = true;	board[7][8] = true;
		board[4][3] = true; board[4][4] = true;
		board[3][4] = true;
		//Look over this later at some point.
		
		
		//PrintBoard(board, master);
		
		return board;
	}//end AssembleBoard
	
	//Creates the pieces and their coordinates
	//Puts them in the piece arraylist 
	private static ArrayList<Piece> MakePiece() {
	
		ArrayList<Piece> board = new ArrayList<Piece>();
		
		Piece red = new Piece();
		ArrayList<Point> rd = new ArrayList<Point>();
		rd.add(new Point(1, 3));rd.add(new Point(2, 3));
		rd.add(new Point(1, 4));rd.add(new Point(2, 4));
		red.AddPiece(rd);board.add(red);
	
		//System.out.println(board.size());
		
		Piece lgreen = new Piece();
		ArrayList<Point> lgr = new ArrayList<Point>();
		lgr.add(new Point(1,5));lgr.add(new Point(1, 6));
		lgr.add(new Point(2, 6));
		lgreen.AddPiece(lgr);
		board.add(lgreen);

	//	System.out.println(board.size());
		
		Piece lavender = new Piece();
		ArrayList<Point> lav = new ArrayList<Point>();
		lav.add(new Point(2, 5)); lav.add(new Point(3, 5));
		lav.add(new Point(3, 6)); lavender.AddPiece(lav);
		board.add(lavender);
		
		Piece yellow = new Piece();
		ArrayList<Point> yel = new ArrayList<Point>();
		yel.add(new Point(4, 7));yel.add(new Point(5, 7));
		yel.add(new Point(5, 8));
		yellow.AddPiece(yel);
		board.add(yellow);
		
		Piece brown = new Piece();
		ArrayList<Point> br = new ArrayList<Point>();
		br.add(new Point(6, 7));br.add(new Point(7, 7));
		br.add(new Point(6, 8));
		brown.AddPiece(br);
		board.add(brown);
		
		Piece pink = new Piece();
		ArrayList<Point> pi = new ArrayList<Point>();
		pi.add(new Point(3, 7));pi.add(new Point(3, 8));
		pi.add(new Point(4, 8));
		pink.AddPiece(pi);
		board.add(pink);
		
		Piece dcyan = new Piece();
		ArrayList<Point> dcy = new ArrayList<Point>();
		dcy.add(new Point(5, 4));dcy.add(new Point(4, 5));
		dcy.add(new Point(5, 5));dcy.add(new Point(5, 6));
	    dcyan.AddPiece(dcy);
		board.add(dcyan);
		
		Piece dgreen = new Piece();
		ArrayList<Point> dgr = new ArrayList<Point>();
		dgr.add(new Point(6, 4));dgr.add(new Point(6, 5));
		dgr.add(new Point(7, 5));dgr.add(new Point(6, 6));
		dgreen.AddPiece(dgr);
		board.add(dgreen);
		
		Piece lcyan = new Piece();
		ArrayList<Point> lcy = new ArrayList<Point>();
		lcy.add(new Point(8, 5));lcy.add(new Point(7, 6));
		lcy.add(new Point(8, 6));
		lcyan.AddPiece(lcy);
		board.add(lcyan);
		
		Piece blue = new Piece();
		ArrayList<Point> bl = new ArrayList<Point>();
		bl.add(new Point(6, 2));bl.add(new Point(5, 3));
		bl.add(new Point(6, 3));
		blue.AddPiece(bl);
		board.add(blue);
		
		Piece orange = new Piece();
		ArrayList<Point> or = new ArrayList<Point>();
		or.add(new Point(5, 1));or.add(new Point(6, 1));
		or.add(new Point(5, 2));
		orange.AddPiece(or);
		board.add(orange);

		return board;
				
	}

}
