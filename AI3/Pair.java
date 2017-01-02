

public class Pair{
    private double first;
    private int second;

    public Pair(Double first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(Double first) {
        this.first = first;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public double getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public void set(Double first, Integer second) {
        setFirst(first);
        setSecond(second);
    }
    
}



