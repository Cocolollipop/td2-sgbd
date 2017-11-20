package TP2;

public class Pair {
	private int max,min;
	
	public Pair(int min,int max) {
		this.min=min;
		this.max=max;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return this.min;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return this.max;
	}
	public String toString(){
		return "" + this.getX() + "," + this.getY();
	}

}