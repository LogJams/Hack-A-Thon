package main;

public class Controller {

	private Render draw;
	WorldGenerator worldGen;
	
	private int worldSize = 20;
	Land[][] world;
	
	public Controller() {
		draw = new Render();
		worldGen = new WorldGenerator();
		world = new Land[worldSize][worldSize];
	}
	
	public void update() {
		//update world generator
		worldGen.update(world);
		//render
		draw.update(world);
	}
	
}
