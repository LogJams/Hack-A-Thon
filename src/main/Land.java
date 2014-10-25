package main;

public class Land {
	
	// x coord?
	// y coord?
	
	public float height;
	public boolean isWet;
	public int xIndex = 0;
	public int zIndex = 0;
	
	public Land(float height, int x, int z){
		this.height = height;
		xIndex = x;
		zIndex = z;
	}
}
