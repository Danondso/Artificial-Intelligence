import java.util.ArrayList;


public class Path{

	ArrayList<Pair> chromosome = new ArrayList<Pair>();
	
    public Path(){
    	
    }
	
	public void setCh(Pair p){
		
		chromosome.add(p);
	}
	
	
	public void Insert(Pair newPoint)
	{
		chromosome.add(newPoint);
	}


	
	
	
	
}
