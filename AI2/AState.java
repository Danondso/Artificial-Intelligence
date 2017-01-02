
public class AState implements Comparable<AState> {

	/*
	State object variables
	the cost, states parent (where it came from)
	and the coordinates 
	*/
	public Double cost;
	AState parent;
	public int x;
	public int y;

	//Boop Boop Bee Doop
	AState(double costIn, AState par, int xIn, int yIn)
	{
		//Ziggy Stardust
		x = xIn;
		y = yIn;
		parent = par;
		cost = costIn;
		//Pacman
	}

	@Override
	public int compareTo(AState b) {
		// TODO Auto-generated method stub
	 return cost.compareTo(b.cost);
	}
}
